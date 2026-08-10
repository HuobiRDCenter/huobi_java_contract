package com.huobi.wss.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huobi.wss.SubscriptionListener;
import com.huobi.wss.constants.HuobiV5WSSConstants;
import com.huobi.wss.util.ApiSignature;
import com.huobi.wss.util.ZipUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * v5 WebSocket 合约交易处理器（下单/撤单）
 * 地址: wss://api.hbdm.com/linear-swap-trade
 */
public class WssTradeHandle {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private WebSocketClient webSocketClient;
    private final String accessKey;
    private final String secretKey;
    private final String host;
    private final String url;
    private final AtomicLong cidSeq = new AtomicLong(0);

    public WssTradeHandle(String accessKey, String secretKey) {
        this(HuobiV5WSSConstants.V5_TRADE_HOST, HuobiV5WSSConstants.V5_TRADE_URL, accessKey, secretKey);
    }

    public WssTradeHandle(String host, String url, String accessKey, String secretKey) {
        this.host = host;
        this.url = url;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void connect(SubscriptionListener<String> callback) throws URISyntaxException {
        String pushUrl = "wss://" + host + url;
        webSocketClient = new WebSocketClient(new URI(pushUrl)) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                addAuth();
                dealReconnect();
            }

            @Override
            public void onMessage(String s) {
                logger.debug("onMessage:{}", s);
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                executorService.execute(() -> {
                    try {
                        String message = new String(ZipUtil.decompress(bytes.array()), "UTF-8");
                        JSONObject JSONMessage = JSONObject.parseObject(message);
                        Object opVal = JSONMessage.get("op");
                        // 成交回报/撤单回报 op=notify
                        if (opVal != null && opVal.toString().equalsIgnoreCase("notify")) {
                            callback.onReceive(message);
                        }
                        // 请求响应 op=recv (鉴权/下单应答等)
                        if (opVal != null && opVal.toString().equalsIgnoreCase("recv")) {
                            callback.onReceive(message);
                        }
                        // 心跳
                        if (opVal != null && opVal.toString().equalsIgnoreCase("ping")) {
                            dealPong(JSONMessage.get("ts"));
                        }
                    } catch (Exception e) {
                        logger.error("onMessage异常", e);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                logger.error("onClose i:{},s:{},b:{}", i, s, b);
            }

            @Override
            public void onError(Exception e) {
                logger.error("onError:{}", e);
            }
        };
        webSocketClient.connect();
    }

    public void placeOrder(Map<String, Object> data) {
        sendOp("place_order", data);
    }

    public void placeBatchOrders(Map<String, Object> data) {
        sendOp("place_batch_orders", data);
    }

    public void cancelOrder(Map<String, Object> data) {
        sendOp("cancel_order", data);
    }

    public void cancelBatchOrders(Map<String, Object> data) {
        sendOp("cancel_batch_orders", data);
    }

    public void cancelAllOrders(Map<String, Object> data) {
        sendOp("cancel_all_orders", data);
    }

    private void sendOp(String op, Map<String, Object> data) {
        if (webSocketClient == null || !webSocketClient.isOpen()) {
            throw new IllegalStateException("WebSocket 未连接，请先调用 connect()");
        }
        JSONObject msg = new JSONObject();
        msg.put("op", op);
        msg.put("cid", String.valueOf(cidSeq.incrementAndGet()));
        msg.put("data", data);
        webSocketClient.send(msg.toString());
    }

    public void addAuth() {
        if (StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(accessKey)) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        ApiSignature as = new ApiSignature();
        try {
            as.createSignature(accessKey, secretKey, "GET", host, url, map);
        } catch (Exception e) {
            logger.error("trade addAuth异常", e);
        }
        map.put("op", "auth");
        map.put("type", "api");
        webSocketClient.send(JSON.toJSONString(map));
    }

    private void dealPong(Object ts) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("op", "pong");
            if (ts != null) {
                jsonMessage.put("ts", ts);
            }
            webSocketClient.send(jsonMessage.toString());
        } catch (Throwable t) {
            logger.error("dealPong出现了异常", t);
        }
    }

    private void dealReconnect() {
        try {
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (webSocketClient.isClosed() && !webSocketClient.isClosing()) {
                            logger.error("isClosed:{},isClosing:{}，准备重连", webSocketClient.isClosed(), webSocketClient.isClosing());
                            Boolean reconnectResult = webSocketClient.reconnectBlocking();
                            logger.error("重连的结果为：{}", reconnectResult);
                            if (!reconnectResult) {
                                webSocketClient.closeBlocking();
                            }
                        }
                    } catch (Throwable e) {
                        logger.error("dealReconnect异常", e);
                    }
                }
            }, 60, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("dealReconnect scheduledExecutorService异常", e);
        }
    }
}
