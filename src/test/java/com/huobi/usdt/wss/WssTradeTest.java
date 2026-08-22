package com.huobi.usdt.wss;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huobi.TestKeys;
import com.huobi.wss.handle.WssTradeHandle;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * V5 WS 交易类测试（自包含自动跑）。
 *
 * 每个测试：connect → 等鉴权 ack(op:auth err-code:0) → 发 place_order/cancel 指令 →
 * 等对应 cid 的应答 code==200 → 主线程断言应答 data 字段非空 → 自动结束。
 * SPX500-USDT 远价限价单(7300)挂撤，0 成交 0 损失。
 *
 * dual_side 模式下下单/撤单必带 position_side(long)。
 * 应答帧无 op 字段，靠 cid 关联；cid 由 handle 内 AtomicLong 从 1 递增，单测串行可按值断言。
 * 下单参数直接用 Map 构造（fastjson 不认 WssPlaceOrderData 上的 gson @SerializedName，
 * 若走 DTO 序列化会丢下划线键导致 1066，该 DTO 序列化 bug 另行记录）。
 */
public class WssTradeTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** SPX500-USDT 远价限价单（7300，low_limit≈7296），0 成交，用于 WS 下单/撤单验证 */
    private Map<String, Object> spxLimitOrderData() {
        Map<String, Object> data = new HashMap<>();
        data.put("contract_code", "SPX500-USDT");
        data.put("margin_mode", "cross");
        data.put("position_side", "long");
        data.put("side", "buy");
        data.put("type", "limit");
        data.put("price", "7300");
        data.put("volume", "1");
        return data;
    }

    /** 等鉴权应答：收到 op:auth err-code:0 才算连上，否则下单指令会被服务端拒。 */
    private boolean awaitAuth(WssTradeHandle handle, CountDownLatch authLatch) throws InterruptedException {
        long deadline = System.currentTimeMillis() + 10000;
        while (authLatch.getCount() > 0 && System.currentTimeMillis() < deadline) {
            Thread.sleep(100);
        }
        return authLatch.getCount() == 0;
    }

    @Test
    public void testPlaceOrder() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch recvLatch = new CountDownLatch(1);
        AtomicReference<JSONObject> placeData = new AtomicReference<>();
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        handle.connect(response -> {
            logger.info("trade 回报:{}", JSON.toJSON(response));
            JSONObject msg = JSON.parseObject(response);
            if ("auth".equalsIgnoreCase(msg.getString("op")) && Integer.valueOf(0).equals(msg.getInteger("err-code"))) {
                authLatch.countDown();
            } else if ("1".equals(msg.getString("cid")) && Integer.valueOf(200).equals(msg.getInteger("code"))) {
                placeData.set(msg.getJSONObject("data"));
                recvLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        boolean ok = recvLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("place_order 应答 10s 内未收到", ok);
        JSONObject data = placeData.get();
        Assert.assertNotNull("place_order 应答 data 为空", data);
        Assert.assertNotNull("place_order 应答 order_id 为空", data.getString("order_id"));
    }

    @Test
    public void testPlaceBatchOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch recvLatch = new CountDownLatch(1);
        AtomicReference<JSONArray> batchData = new AtomicReference<>();
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        handle.connect(response -> {
            logger.info("trade 回报:{}", JSON.toJSON(response));
            JSONObject msg = JSON.parseObject(response);
            if ("auth".equalsIgnoreCase(msg.getString("op")) && Integer.valueOf(0).equals(msg.getInteger("err-code"))) {
                authLatch.countDown();
            } else if ("1".equals(msg.getString("cid")) && Integer.valueOf(200).equals(msg.getInteger("code"))) {
                batchData.set(msg.getJSONArray("data"));
                recvLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        // place_batch_orders 的 data 是订单数组（opend 文档：data 类型 array，无包装 key）
        handle.placeBatchOrders(java.util.Collections.singletonList(spxLimitOrderData()));
        boolean ok = recvLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("place_batch_orders 应答 10s 内未收到", ok);
        JSONArray data = batchData.get();
        Assert.assertNotNull("place_batch_orders 应答 data 为空", data);
        Assert.assertFalse("place_batch_orders 应答 data 列表为空", data.isEmpty());
        JSONObject first = data.getJSONObject(0);
        Assert.assertEquals("批量下单首单 code 非 200", Integer.valueOf(200), first.getInteger("code"));
        Assert.assertNotNull("place_batch_orders 应答 order_id 为空", first.getString("order_id"));
    }

    @Test
    public void testCancelOrder() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch placeLatch = new CountDownLatch(1);
        CountDownLatch cancelLatch = new CountDownLatch(1);
        AtomicReference<String> placedOrderId = new AtomicReference<>();
        AtomicReference<JSONObject> cancelData = new AtomicReference<>();
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        handle.connect(response -> {
            logger.info("trade 回报:{}", JSON.toJSON(response));
            JSONObject msg = JSON.parseObject(response);
            if ("auth".equalsIgnoreCase(msg.getString("op")) && Integer.valueOf(0).equals(msg.getInteger("err-code"))) {
                authLatch.countDown();
                return;
            }
            String cid = msg.getString("cid");
            if (!Integer.valueOf(200).equals(msg.getInteger("code"))) {
                return;
            }
            if ("1".equals(cid)) {
                JSONObject data = msg.getJSONObject("data");
                if (data != null) {
                    placedOrderId.set(data.getString("order_id"));
                }
                placeLatch.countDown();
            } else if ("2".equals(cid)) {
                cancelData.set(msg.getJSONObject("data"));
                cancelLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        Assert.assertTrue("place_order 应答 10s 内未收到", placeLatch.await(10, TimeUnit.SECONDS));
        Assert.assertNotNull("未拿到 order_id", placedOrderId.get());
        Map<String, Object> cancelReq = new HashMap<>();
        cancelReq.put("contract_code", "SPX500-USDT");
        cancelReq.put("order_id", placedOrderId.get());
        handle.cancelOrder(cancelReq);
        boolean ok = cancelLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("cancel_order 应答 10s 内未收到", ok);
        JSONObject data = cancelData.get();
        Assert.assertNotNull("cancel_order 应答 data 为空", data);
        Assert.assertNotNull("cancel_order 应答 order_id 为空", data.getString("order_id"));
    }

    @Test
    public void testCancelBatchOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch placeLatch = new CountDownLatch(1);
        CountDownLatch cancelLatch = new CountDownLatch(1);
        AtomicReference<String> placedOrderId = new AtomicReference<>();
        AtomicReference<JSONArray> cancelData = new AtomicReference<>();
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        handle.connect(response -> {
            logger.info("trade 回报:{}", JSON.toJSON(response));
            JSONObject msg = JSON.parseObject(response);
            if ("auth".equalsIgnoreCase(msg.getString("op")) && Integer.valueOf(0).equals(msg.getInteger("err-code"))) {
                authLatch.countDown();
                return;
            }
            String cid = msg.getString("cid");
            if (!Integer.valueOf(200).equals(msg.getInteger("code"))) {
                return;
            }
            if ("1".equals(cid)) {
                JSONObject data = msg.getJSONObject("data");
                if (data != null) {
                    placedOrderId.set(data.getString("order_id"));
                }
                placeLatch.countDown();
            } else if ("2".equals(cid)) {
                cancelData.set(msg.getJSONArray("data"));
                cancelLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        Assert.assertTrue("place_order 应答 10s 内未收到", placeLatch.await(10, TimeUnit.SECONDS));
        Assert.assertNotNull("未拿到 order_id", placedOrderId.get());
        // cancel_batch_orders 的 data 与 REST 一致：contract_code + order_id(字符串数组)
        Map<String, Object> cancelReq = new HashMap<>();
        cancelReq.put("contract_code", "SPX500-USDT");
        cancelReq.put("order_id", java.util.Collections.singletonList(placedOrderId.get()));
        handle.cancelBatchOrders(cancelReq);
        boolean ok = cancelLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("cancel_batch_orders 应答 10s 内未收到", ok);
        JSONArray data = cancelData.get();
        Assert.assertNotNull("cancel_batch_orders 应答 data 为空", data);
        Assert.assertFalse("cancel_batch_orders 应答 data 列表为空", data.isEmpty());
        JSONObject first = data.getJSONObject(0);
        Assert.assertEquals("批量撤单首单 code 非 200", Integer.valueOf(200), first.getInteger("code"));
        Assert.assertNotNull("cancel_batch_orders 应答 order_id 为空", first.getString("order_id"));
    }

    @Test
    public void testCancelAllOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch placeLatch = new CountDownLatch(1);
        CountDownLatch cancelLatch = new CountDownLatch(1);
        AtomicReference<JSONObject> placeData = new AtomicReference<>();
        AtomicReference<JSONArray> cancelData = new AtomicReference<>();
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        handle.connect(response -> {
            logger.info("trade 回报:{}", JSON.toJSON(response));
            JSONObject msg = JSON.parseObject(response);
            if ("auth".equalsIgnoreCase(msg.getString("op")) && Integer.valueOf(0).equals(msg.getInteger("err-code"))) {
                authLatch.countDown();
                return;
            }
            String cid = msg.getString("cid");
            if (!Integer.valueOf(200).equals(msg.getInteger("code"))) {
                return;
            }
            if ("1".equals(cid)) {
                placeData.set(msg.getJSONObject("data"));
                placeLatch.countDown();
            } else if ("2".equals(cid)) {
                cancelData.set(msg.getJSONArray("data"));
                cancelLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        Assert.assertTrue("place_order 应答 10s 内未收到", placeLatch.await(10, TimeUnit.SECONDS));
        JSONObject pData = placeData.get();
        Assert.assertNotNull("place_order 应答 data 为空", pData);
        Assert.assertNotNull("place_order 应答 order_id 为空", pData.getString("order_id"));
        Map<String, Object> data = new HashMap<>();
        data.put("contract_code", "SPX500-USDT");
        data.put("margin_mode", "cross");
        handle.cancelAllOrders(data);
        boolean ok = cancelLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("cancel_all_orders 应答 10s 内未收到", ok);
        JSONArray cData = cancelData.get();
        Assert.assertNotNull("cancel_all_orders 应答 data 为空", cData);
        Assert.assertFalse("cancel_all_orders 应答 data 列表为空", cData.isEmpty());
        JSONObject first = cData.getJSONObject(0);
        Assert.assertEquals("全部撤单首单 code 非 200", Integer.valueOf(200), first.getInteger("code"));
    }
}
