package com.huobi.wss.constants;

public class HuobiV5WSSConstants {

    // v5 订阅地址
    public static final String V5_NOTIFICATION_HOST = "api.hbdm.com";
    public static final String V5_NOTIFICATION_URL = "/ws/v5/notification";

    // v5 WebSocket 交易地址
    public static final String V5_TRADE_HOST = "api.hbdm.com";
    public static final String V5_TRADE_URL = "/linear-swap-trade";

    // v5 notification 推送 topic
    public static final String TOPIC_ORDERS = "orders";
    public static final String TOPIC_TRADE = "trade";
    public static final String TOPIC_TRADE_DETAIL = "trade_detail";
    public static final String TOPIC_POSITIONS = "positions";
    public static final String TOPIC_ACCOUNT = "account";
    public static final String TOPIC_MATCH_ORDERS = "match_orders";
    public static final String TOPIC_ALGO_ORDERS = "algo_orders";
}
