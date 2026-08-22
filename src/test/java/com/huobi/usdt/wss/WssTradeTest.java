package com.huobi.usdt.wss;

import com.alibaba.fastjson.JSON;
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

/**
 * V5 WS 交易类测试（自包含自动跑）。
 *
 * 每个测试：connect → 等鉴权 ack(op:auth err-code:0) → 发 place_order/cancel 指令 →
 * 等对应 cid 的应答 code==200 → 自动结束。SPX500-USDT 远价限价单(7300)挂撤，0 成交 0 损失。
 *
 * dual_side 模式下下单/撤单必带 position_side(long)。
 * 注：下单参数直接用 Map 构造（fastjson 不认 WssPlaceOrderData 上的 gson @SerializedName，
 *    若走 DTO 序列化会丢下划线键导致 1066，该 DTO 序列化 bug 另行记录）。
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
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        handle.connect(response -> {
            logger.info("trade 回报:{}", JSON.toJSON(response));
            JSONObject msg = JSON.parseObject(response);
            if ("auth".equalsIgnoreCase(msg.getString("op")) && Integer.valueOf(0).equals(msg.getInteger("err-code"))) {
                authLatch.countDown();
            } else if ("1".equals(msg.getString("cid")) && Integer.valueOf(200).equals(msg.getInteger("code"))) {
                recvLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        boolean ok = recvLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("place_order 应答 10s 内未收到", ok);
    }

    @Test
    public void testPlaceBatchOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch recvLatch = new CountDownLatch(1);
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        handle.connect(response -> {
            logger.info("trade 回报:{}", JSON.toJSON(response));
            JSONObject msg = JSON.parseObject(response);
            if ("auth".equalsIgnoreCase(msg.getString("op")) && Integer.valueOf(0).equals(msg.getInteger("err-code"))) {
                authLatch.countDown();
            } else if ("1".equals(msg.getString("cid")) && Integer.valueOf(200).equals(msg.getInteger("code"))) {
                recvLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        // place_batch_orders 的 data 是订单数组（opend 文档：data 类型 array，无包装 key）
        handle.placeBatchOrders(java.util.Collections.singletonList(spxLimitOrderData()));
        boolean ok = recvLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("place_batch_orders 应答 10s 内未收到", ok);
    }

    @Test
    public void testCancelOrder() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch placeLatch = new CountDownLatch(1);
        CountDownLatch cancelLatch = new CountDownLatch(1);
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        final String[] placedOrderId = new String[1];
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
                    placedOrderId[0] = data.getString("order_id");
                }
                placeLatch.countDown();
            } else if ("2".equals(cid)) {
                cancelLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        Assert.assertTrue("place_order 应答 10s 内未收到", placeLatch.await(10, TimeUnit.SECONDS));
        Assert.assertNotNull("未拿到 order_id", placedOrderId[0]);
        Map<String, Object> cancelData = new HashMap<>();
        cancelData.put("contract_code", "SPX500-USDT");
        cancelData.put("order_id", placedOrderId[0]);
        handle.cancelOrder(cancelData);
        boolean ok = cancelLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("cancel_order 应答 10s 内未收到", ok);
    }

    @Test
    public void testCancelBatchOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch placeLatch = new CountDownLatch(1);
        CountDownLatch cancelLatch = new CountDownLatch(1);
        WssTradeHandle handle = new WssTradeHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
        final String[] placedOrderId = new String[1];
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
                    placedOrderId[0] = data.getString("order_id");
                }
                placeLatch.countDown();
            } else if ("2".equals(cid)) {
                cancelLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        Assert.assertTrue("place_order 应答 10s 内未收到", placeLatch.await(10, TimeUnit.SECONDS));
        Assert.assertNotNull("未拿到 order_id", placedOrderId[0]);
        // cancel_batch_orders 的 data 与 REST 一致：contract_code + order_id(字符串数组)
        Map<String, Object> cancelData = new HashMap<>();
        cancelData.put("contract_code", "SPX500-USDT");
        cancelData.put("order_id", java.util.Collections.singletonList(placedOrderId[0]));
        handle.cancelBatchOrders(cancelData);
        boolean ok = cancelLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("cancel_batch_orders 应答 10s 内未收到", ok);
    }

    @Test
    public void testCancelAllOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch authLatch = new CountDownLatch(1);
        CountDownLatch placeLatch = new CountDownLatch(1);
        CountDownLatch cancelLatch = new CountDownLatch(1);
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
                placeLatch.countDown();
            } else if ("2".equals(cid)) {
                cancelLatch.countDown();
            }
        });
        Assert.assertTrue("鉴权 10s 内未成功", awaitAuth(handle, authLatch));
        handle.placeOrder(spxLimitOrderData());
        Assert.assertTrue("place_order 应答 10s 内未收到", placeLatch.await(10, TimeUnit.SECONDS));
        Map<String, Object> data = new HashMap<>();
        data.put("contract_code", "SPX500-USDT");
        data.put("margin_mode", "cross");
        handle.cancelAllOrders(data);
        boolean ok = cancelLatch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue("cancel_all_orders 应答 10s 内未收到", ok);
    }
}
