package com.huobi.usdt.wss;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.huobi.TestKeys;
import com.huobi.api.request.usdt.algo.AlgoOrderRequest;
import com.huobi.api.request.usdt.algo.CancelAlgoOrdersRequest;
import com.huobi.api.request.usdt.trade.CannelTradeBatchOrderRequest;
import com.huobi.api.request.usdt.trade.TradeOrderRequest;
import com.huobi.api.response.usdt.algo.AlgoOrderResponse;
import com.huobi.api.response.usdt.trade.TradeOrderResponse;
import com.huobi.api.service.usdt.algo.AlgoAPIServiceImpl;
import com.huobi.api.service.usdt.trade.TradeAPIServiceImpl;
import com.huobi.wss.constants.HuobiV5WSSConstants;
import com.huobi.wss.handle.WssV5NotificationHandle;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * V5 WS 推送类测试（自包含自动跑）。
 *
 * 每个测试：订阅 → 等鉴权+订阅 ack → REST 触发对应数据 → latch 等推送到达 → 自动结束。
 * 不再 Thread.sleep(MAX_VALUE) 无限阻塞，跑一条命令即可完成验证。
 *
 * 鉴权+订阅 ack 由 onMessage 日志可见（op:auth/op:sub err-code:0）；
 * 实际推送数据 op:notify 触发 callback，latch 倒计释放，测试结束。
 */
public class WssV5NotificationSubTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    WssV5NotificationHandle handle = new WssV5NotificationHandle(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
    TradeAPIServiceImpl tradeService = new TradeAPIServiceImpl(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);
    AlgoAPIServiceImpl algoService = new AlgoAPIServiceImpl(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);

    private Map<String, Object> contractCode(String code) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("contract_code", code);
        return ext;
    }

    /**
     * 校验推送帧的 data 关键字段非空（与 REST 的 AssertFields 同标准：必填字段非空）。
     * data 结构因 topic 而异：orders/account 为单对象，其余为对象数组。
     * 订单类推送的成交字段（trade_id/trade_price 等）只在 state==filled 非空，按 state 条件断言。
     * 校验失败不抛（callback 在线程池里抛会被吞），记入 errorRef，由主线程 latch 后断言。
     * @return true 表示这是一帧含数据的有效推送（可 countDown）；false 表示空快照应跳过。
     */
    private boolean checkNotifyData(String topic, String response, java.util.concurrent.atomic.AtomicReference<String> errorRef) {
        try {
            JSONObject msg = JSON.parseObject(response);
            if (!topic.equals(msg.getString("topic"))) {
                errorRef.set(topic + " 推送 topic 不符: " + msg.getString("topic"));
                return true;
            }
            Object dataObj = msg.get("data");
            if (dataObj == null) {
                return false; // 无 data 帧跳过
            }
            JSONObject d;
            if (dataObj instanceof JSONArray) {
                JSONArray arr = (JSONArray) dataObj;
                if (arr.isEmpty()) {
                    return false; // 空快照跳过
                }
                d = arr.getJSONObject(0);
            } else {
                d = (JSONObject) dataObj;
            }
            if (d == null) {
                return false;
            }
            String state = d.getString("state");
            for (String f : requiredFields(topic, state)) {
                if (d.get(f) == null) {
                    errorRef.set(topic + " 推送字段 " + f + " 为空 (state=" + state + ")");
                    return true;
                }
            }
            return true; // 有效数据帧
        } catch (Exception e) {
            errorRef.set(topic + " 推送解析异常: " + e.getMessage());
            return true;
        }
    }

    /** 各 topic 的必填关键字段（无条件非空）。成交类字段仅 state==filled 时必填。 */
    private static java.util.List<String> requiredFields(String topic, String state) {
        boolean filled = "filled".equalsIgnoreCase(state);
        java.util.List<String> f = new java.util.ArrayList<>();
        switch (topic) {
            case "orders":
                f.addAll(java.util.Arrays.asList("order_id", "contract_code", "side", "type", "volume", "state", "margin_mode", "position_side", "lever_rate", "created_time", "updated_time"));
                if (filled) f.addAll(java.util.Arrays.asList("trade_avg_price", "trade_volume", "trade_turnover"));
                break;
            case "trade":
                f.addAll(java.util.Arrays.asList("trade_id", "order_id", "contract_code", "direction", "trade_price", "trade_volume", "trade_turnover", "role", "position_side", "created_time"));
                break;
            case "trade_detail":
                f.addAll(java.util.Arrays.asList("trade_id", "order_id", "contract_code", "direction", "trade_price", "trade_volume", "trade_turnover", "trade_fee", "fee_currency", "position_side", "created_time"));
                break;
            case "positions":
                f.addAll(java.util.Arrays.asList("contract_code", "position_side", "direction", "margin_mode", "volume", "lever_rate", "open_avg_price", "margin", "state", "created_time"));
                break;
            case "account":
                f.addAll(java.util.Arrays.asList("equity", "state", "details", "initial_margin", "maintenance_margin", "available_margin"));
                break;
            case "match_orders":
                f.addAll(java.util.Arrays.asList("order_id", "contract_code", "side", "type", "volume", "state", "match_time", "position_side"));
                if (filled) f.addAll(java.util.Arrays.asList("trade_id", "trade_price", "trade_volume", "role"));
                break;
            case "algo_orders":
                f.addAll(java.util.Arrays.asList("algo_id", "contract_code", "type", "side", "volume", "state", "margin_mode", "position_side", "trigger_price", "created_time"));
                break;
            default:
                break;
        }
        return f;
    }

    /** SPX500-USDT 远价限价单（7300，low_limit≈7296），0 成交，用于触发订单/委托类推送 */
    private String spxBuyLimitOrder() {
        TradeOrderRequest req = TradeOrderRequest.builder()
                .contractCode("SPX500-USDT").marginMode("cross").positionSide("long")
                .side("buy").type("limit").price("7300").volume("1").build();
        TradeOrderResponse resp = tradeService.tradeOrderResponse(req);
        return resp.getData().getOrderId();
    }

    /** SPX500-USDT 市价买 1 张成交，用于触发成交/持仓/账户类推送。成交后立即全平清理 */
    private void spxBuyMarketAndClose() {
        TradeOrderRequest place = TradeOrderRequest.builder()
                .contractCode("SPX500-USDT").marginMode("cross").positionSide("long")
                .side("buy").type("market").volume("1").build();
        tradeService.tradeOrderResponse(place);
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        try {
            com.huobi.api.request.usdt.trade.TradePositionRequest close =
                    com.huobi.api.request.usdt.trade.TradePositionRequest.builder()
                            .contractCode("SPX500-USDT").marginMode("cross").positionSide("long").build();
            tradeService.tradePositionResponse(close);
        } catch (Exception ce) {
            logger.debug("SPX500 市价成交后全平清理(忽略):{}", ce.getMessage());
        }
    }

    /** 下 SPX500 远价触发策略单，用于触发策略推送。返回 algoId，外层可撤 */
    private String spxAlgoOrder() {
        AlgoOrderRequest req = AlgoOrderRequest.builder()
                .contractCode("SPX500-USDT").type("trigger").positionSide("long").side("buy")
                .marginMode("cross").volume("1").price("7300")
                .triggerPrice("7300").triggerPriceType("last").build();
        AlgoOrderResponse resp = algoService.algoOrder(req);
        return resp.getData().get(0).getAlgoId();
    }

    private void cancelSpxAlgo(String algoId) {
        if (algoId == null) return;
        try {
            CancelAlgoOrdersRequest req = CancelAlgoOrdersRequest.builder()
                    .contractCode("SPX500-USDT").algoId(algoId).build();
            algoService.cancelAlgoOrder(req);
        } catch (Exception ce) {
            logger.debug("策略单清理(忽略):{}", ce.getMessage());
        }
    }

    private void cancelSpxOrder(String orderId) {
        if (orderId == null) return;
        try {
            CannelTradeBatchOrderRequest req = CannelTradeBatchOrderRequest.builder()
                    .contractCode("SPX500-USDT").orderId(orderId).build();
            tradeService.cannelTradeBatchOrderResponse(req);
        } catch (Exception ce) {
            logger.debug("挂单清理(忽略):{}", ce.getMessage());
        }
    }

    @Test
    public void testSubOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<String> errorRef = new java.util.concurrent.atomic.AtomicReference<>();
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ORDERS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("orders 推收到:{}", JSON.toJSON(response));
            if (checkNotifyData("orders", response, errorRef)) {
                latch.countDown();
            }
        });
        Thread.sleep(3000); // 等鉴权+订阅 ack
        String orderId = spxBuyLimitOrder(); // 触发订单推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        cancelSpxOrder(orderId); // 清理挂单
        Assert.assertTrue("orders 推送 20s 内未收到", ok);
        Assert.assertNull(errorRef.get(), errorRef.get());
    }

    @Test
    public void testSubTrade() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<String> errorRef = new java.util.concurrent.atomic.AtomicReference<>();
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_TRADE);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("trade 推收到:{}", JSON.toJSON(response));
            if (checkNotifyData("trade", response, errorRef)) {
                latch.countDown();
            }
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 成交触发 trade 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("trade 推送 20s 内未收到", ok);
        Assert.assertNull(errorRef.get(), errorRef.get());
    }

    @Test
    public void testSubTradeDetail() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<String> errorRef = new java.util.concurrent.atomic.AtomicReference<>();
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_TRADE_DETAIL);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("trade_detail 推收到:{}", JSON.toJSON(response));
            if (checkNotifyData("trade_detail", response, errorRef)) {
                latch.countDown();
            }
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 成交触发 trade_detail 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("trade_detail 推送 20s 内未收到", ok);
        Assert.assertNull(errorRef.get(), errorRef.get());
    }

    @Test
    public void testSubPositions() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<String> errorRef = new java.util.concurrent.atomic.AtomicReference<>();
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_POSITIONS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("positions 推收到:{}", JSON.toJSON(response));
            // positions 订阅先推空 snapshot，checkNotifyData 对空帧返回 false 跳过，只对 filled 帧断言放行
            if (checkNotifyData("positions", response, errorRef)) {
                latch.countDown();
            }
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 成交产生持仓触发 positions 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("positions 推送 20s 内未收到", ok);
        Assert.assertNull(errorRef.get(), errorRef.get());
    }

    @Test
    public void testSubAccount() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<String> errorRef = new java.util.concurrent.atomic.AtomicReference<>();
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ACCOUNT);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("account 推收到:{}", JSON.toJSON(response));
            if (checkNotifyData("account", response, errorRef)) {
                latch.countDown();
            }
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 账户余额变化触发 account 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("account 推送 20s 内未收到", ok);
        Assert.assertNull(errorRef.get(), errorRef.get());
    }

    @Test
    public void testSubMatchOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<String> errorRef = new java.util.concurrent.atomic.AtomicReference<>();
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_MATCH_ORDERS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("match_orders 推收到:{}", JSON.toJSON(response));
            if (checkNotifyData("match_orders", response, errorRef)) {
                latch.countDown();
            }
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 撮合触发 match_orders 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("match_orders 推送 20s 内未收到", ok);
        Assert.assertNull(errorRef.get(), errorRef.get());
    }

    @Test
    public void testSubAlgoOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<String> errorRef = new java.util.concurrent.atomic.AtomicReference<>();
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ALGO_ORDERS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("algo_orders 推收到:{}", JSON.toJSON(response));
            if (checkNotifyData("algo_orders", response, errorRef)) {
                latch.countDown();
            }
        });
        Thread.sleep(3000);
        String algoId = spxAlgoOrder(); // 策略下单触发 algo_orders 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        cancelSpxAlgo(algoId); // 清理策略单
        Assert.assertTrue("algo_orders 推送 20s 内未收到", ok);
        Assert.assertNull(errorRef.get(), errorRef.get());
    }
}
