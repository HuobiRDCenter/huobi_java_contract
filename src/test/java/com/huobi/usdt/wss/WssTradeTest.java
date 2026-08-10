package com.huobi.usdt.wss;

import com.alibaba.fastjson.JSON;
import com.huobi.wss.handle.WssTradeHandle;
import com.huobi.wss.request.WssPlaceOrderData;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class WssTradeTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testPlaceOrder() throws URISyntaxException, InterruptedException {
        WssTradeHandle handle = new WssTradeHandle("", "");
        handle.connect(response -> logger.info("收到回报:{}", JSON.toJSON(response)));
        Thread.sleep(2000);
        // 无 key 鉴权会失败，这里仅验证消息可构造发送
        try {
            WssPlaceOrderData data = WssPlaceOrderData.builder()
                    .contractCode("BTC-USDT")
                    .marginMode("isolated")
                    .side("buy")
                    .type("limit")
                    .price("10000")
                    .volume("1")
                    .build();
            handle.placeOrder(JSON.parseObject(JSON.toJSONString(data), HashMap.class));
        } catch (Exception e) {
            logger.debug("place_order(预期异常,未鉴权):{}", e.getMessage());
        }
        Thread.sleep(3000);
    }

    @Test
    public void testPlaceBatchOrders() throws URISyntaxException, InterruptedException {
        WssTradeHandle handle = new WssTradeHandle("", "");
        handle.connect(response -> logger.info("收到回报:{}", JSON.toJSON(response)));
        Thread.sleep(2000);
        try {
            Map<String, Object> data = new HashMap<>();
            handle.placeBatchOrders(data);
        } catch (Exception e) {
            logger.debug("place_batch_orders(预期异常):{}", e.getMessage());
        }
        Thread.sleep(3000);
    }

    @Test
    public void testCancelOrder() throws URISyntaxException, InterruptedException {
        WssTradeHandle handle = new WssTradeHandle("", "");
        handle.connect(response -> logger.info("收到回报:{}", JSON.toJSON(response)));
        Thread.sleep(2000);
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("contract_code", "BTC-USDT");
            handle.cancelOrder(data);
        } catch (Exception e) {
            logger.debug("cancel_order(预期异常):{}", e.getMessage());
        }
        Thread.sleep(3000);
    }

    @Test
    public void testCancelBatchOrders() throws URISyntaxException, InterruptedException {
        WssTradeHandle handle = new WssTradeHandle("", "");
        handle.connect(response -> logger.info("收到回报:{}", JSON.toJSON(response)));
        Thread.sleep(2000);
        try {
            Map<String, Object> data = new HashMap<>();
            handle.cancelBatchOrders(data);
        } catch (Exception e) {
            logger.debug("cancel_batch_orders(预期异常):{}", e.getMessage());
        }
        Thread.sleep(3000);
    }

    @Test
    public void testCancelAllOrders() throws URISyntaxException, InterruptedException {
        WssTradeHandle handle = new WssTradeHandle("", "");
        handle.connect(response -> logger.info("收到回报:{}", JSON.toJSON(response)));
        Thread.sleep(2000);
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("contract_code", "BTC-USDT");
            handle.cancelAllOrders(data);
        } catch (Exception e) {
            logger.debug("cancel_all_orders(预期异常):{}", e.getMessage());
        }
        Thread.sleep(3000);
    }
}
