package com.huobi.usdt.wss;

import com.alibaba.fastjson.JSON;
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
import com.huobi.wss.event.v5.V5NotificationResponse;
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
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ORDERS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("orders 推收到:{}", JSON.toJSON(response));
            V5NotificationResponse event = JSON.parseObject(response, V5NotificationResponse.class);
            Assert.assertNotNull("orders 推送 topic 为空", event.getTopic());
            latch.countDown();
        });
        Thread.sleep(3000); // 等鉴权+订阅 ack
        String orderId = spxBuyLimitOrder(); // 触发订单推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        cancelSpxOrder(orderId); // 清理挂单
        Assert.assertTrue("orders 推送 20s 内未收到", ok);
    }

    @Test
    public void testSubTrade() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_TRADE);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("trade 推收到:{}", JSON.toJSON(response));
            latch.countDown();
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 成交触发 trade 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("trade 推送 20s 内未收到", ok);
    }

    @Test
    public void testSubTradeDetail() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_TRADE_DETAIL);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("trade_detail 推收到:{}", JSON.toJSON(response));
            latch.countDown();
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 成交触发 trade_detail 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("trade_detail 推送 20s 内未收到", ok);
    }

    @Test
    public void testSubPositions() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_POSITIONS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("positions 推收到:{}", JSON.toJSON(response));
            latch.countDown();
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 成交产生持仓触发 positions 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("positions 推送 20s 内未收到", ok);
    }

    @Test
    public void testSubAccount() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ACCOUNT);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("account 推收到:{}", JSON.toJSON(response));
            latch.countDown();
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 账户余额变化触发 account 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("account 推送 20s 内未收到", ok);
    }

    @Test
    public void testSubMatchOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_MATCH_ORDERS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("match_orders 推收到:{}", JSON.toJSON(response));
            latch.countDown();
        });
        Thread.sleep(3000);
        spxBuyMarketAndClose(); // 撮合触发 match_orders 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        Assert.assertTrue("match_orders 推送 20s 内未收到", ok);
    }

    @Test
    public void testSubAlgoOrders() throws URISyntaxException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ALGO_ORDERS);
        handle.sub(topics, contractCode("SPX500-USDT"), response -> {
            logger.info("algo_orders 推收到:{}", JSON.toJSON(response));
            latch.countDown();
        });
        Thread.sleep(3000);
        String algoId = spxAlgoOrder(); // 策略下单触发 algo_orders 推送
        boolean ok = latch.await(20, TimeUnit.SECONDS);
        cancelSpxAlgo(algoId); // 清理策略单
        Assert.assertTrue("algo_orders 推送 20s 内未收到", ok);
    }
}
