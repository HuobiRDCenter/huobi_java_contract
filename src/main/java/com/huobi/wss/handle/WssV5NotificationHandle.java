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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * v5 合约订单/资产/持仓推送处理器
 * 订阅地址: wss://api.hbdm.com/ws/v5/notification
 */
public class WssV5NotificationHandle {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private WebSocketClient webSocketClient;
    private String accessKey;
    private String secretKey;
    private String host;
    private String url;
    private String pushUrl;
    private Long lastPingTime = System.currentTimeMillis();


    public WssV5NotificationHandle(String accessKey, String secretKey) {
        this(HuobiV5WSSConstants.V5_NOTIFICATION_HOST, HuobiV5WSSConstants.V5_NOTIFICATION_URL, accessKey, secretKey);
    }

    public WssV5NotificationHandle(String host, String url, String accessKey, String secretKey) {
        this.host = host;
        this.url = url;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void sub(List<String> topics, Map<String, Object> extReq, SubscriptionListener<String> callback) throws URISyntaxException {
        doConnect("sub", null, topics, extReq, callback);
    }

    public void unsub(List<String> topics, Map<String, Object> extReq, SubscriptionListener<String> callback) throws URISyntaxException {
        doConnect("unsub", null, topics, extReq, callback);
    }

    private void doConnect(String op, String cid, List<String> topics, Map<String, Object> extReq, SubscriptionListener<String> callback) throws URISyntaxException {
        pushUrl = "wss://" + host + url;
        webSocketClient = new WebSocketClient(new URI(pushUrl)) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                addAuth();
                doSub(op, cid, topics, extReq);
                dealReconnect();
            }

            @Override
            public void onMessage(String s) {
                executorService.execute(() -> dispatch(s, callback));
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                executorService.execute(() -> {
                    try {
                        lastPingTime = System.currentTimeMillis();
                        String message = new String(ZipUtil.decompress(bytes.array()), "UTF-8");
                        dispatch(message, callback);
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

    private void doSub(String op, String cid, List<String> topics, Map<String, Object> extReq) {
        for (String topic : topics) {
            JSONObject sub = new JSONObject();
            sub.put("op", op);
            if (StringUtils.hasText(cid)) {
                sub.put("cid", cid);
            }
            sub.put("topic", topic);
            if (extReq != null && !extReq.isEmpty()) {
                sub.putAll(extReq);
            }
            webSocketClient.send(sub.toString());
        }
    }

    /**
     * 统一处理文本帧与 gzip 帧解码后的消息：notify 触发 callback、ping 回 pong、其余 ack 仅记录日志。
     * V5 notification 推送为明文文本帧（非 gzip），故 onMessage(String) 也需分发 notify。
     */
    private void dispatch(String message, SubscriptionListener<String> callback) {
        try {
            logger.debug("onMessage:{}", message);
            JSONObject JSONMessage = JSONObject.parseObject(message);
            Object opVal = JSONMessage.get("op");
            if (opVal == null) {
                return;
            }
            String op = opVal.toString();
            if (op.equalsIgnoreCase("notify")) {
                callback.onReceive(message);
            } else if (op.equalsIgnoreCase("ping")) {
                dealPong(JSONMessage.get("ts"));
            }
        } catch (Exception e) {
            logger.error("dispatch异常", e);
        }
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

    public void addAuth() {
        if (StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(accessKey)) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        ApiSignature as = new ApiSignature();
        try {
            as.createSignature(accessKey, secretKey, "GET", host, url, map);
        } catch (Exception e) {
            logger.error("v5 addAuth异常", e);
        }
        map.put("op", "auth");
        map.put("type", "api");
        String req = JSON.toJSONString(map);
        webSocketClient.send(req);
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
