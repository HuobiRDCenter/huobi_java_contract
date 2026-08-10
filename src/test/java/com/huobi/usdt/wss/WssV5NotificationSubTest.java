package com.huobi.usdt.wss;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.huobi.wss.constants.HuobiV5WSSConstants;
import com.huobi.wss.event.v5.V5NotificationResponse;
import com.huobi.wss.handle.WssV5NotificationHandle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WssV5NotificationSubTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    WssV5NotificationHandle handle = new WssV5NotificationHandle("", "");

    private Map<String, Object> contractCode(String code) {
        Map<String, Object> ext = new HashMap<>();
        ext.put("contract_code", code);
        return ext;
    }

    @Test
    public void testSubOrders() throws URISyntaxException, InterruptedException {
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ORDERS);
        handle.sub(topics, contractCode("BTC-USDT"), response -> {
            logger.info("orders 推收到:{}", JSON.toJSON(response));
            V5NotificationResponse event = JSON.parseObject(response, V5NotificationResponse.class);
            logger.info("topic={}, ts={}", event.getTopic(), event.getTs());
        });
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testSubTrade() throws URISyntaxException, InterruptedException {
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_TRADE);
        handle.sub(topics, contractCode("BTC-USDT"), response -> logger.info("trade 推收到:{}", JSON.toJSON(response)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testSubTradeDetail() throws URISyntaxException, InterruptedException {
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_TRADE_DETAIL);
        handle.sub(topics, contractCode("BTC-USDT"), response -> logger.info("trade_detail 推收到:{}", JSON.toJSON(response)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testSubPositions() throws URISyntaxException, InterruptedException {
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_POSITIONS);
        handle.sub(topics, contractCode("BTC-USDT"), response -> logger.info("positions 推收到:{}", JSON.toJSON(response)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testSubAccount() throws URISyntaxException, InterruptedException {
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ACCOUNT);
        handle.sub(topics, contractCode("BTC-USDT"), response -> logger.info("account 推收到:{}", JSON.toJSON(response)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testSubMatchOrders() throws URISyntaxException, InterruptedException {
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_MATCH_ORDERS);
        handle.sub(topics, contractCode("BTC-USDT"), response -> logger.info("match_orders 推收到:{}", JSON.toJSON(response)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testSubAlgoOrders() throws URISyntaxException, InterruptedException {
        List<String> topics = Lists.newArrayList(HuobiV5WSSConstants.TOPIC_ALGO_ORDERS);
        handle.sub(topics, contractCode("BTC-USDT"), response -> logger.info("algo_orders 推收到:{}", JSON.toJSON(response)));
        Thread.sleep(Integer.MAX_VALUE);
    }
}
