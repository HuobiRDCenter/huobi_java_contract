package com.huobi.wss.constants;

public class HuobiUsdtWSSConstants {

    //市场行情接口 订阅
    public static final String MARKET_KLINE_PERIOD_SUB = "market.$contract_code.kline.$period"; // 1.订阅 KLine 数据
    public static final String MARKET_DEPTH_SUB = "market.$contract_code.depth.$type"; // 2.订阅 Market Depth 数据
    public static final String MARKET_DETAIL_SUB = "market.$contract_code.detail";// 3.订阅 Market detail 数据
    public static final String MARKET_DEPTH_DIFF_SUB = "market.$contract_code.depth.size_${size}.high_freq"; // 订阅Market Depth增量数据
    public static final String MARKET_BBO_SUB = "market.$contract_code.bbo"; // 订阅买一卖一逐笔行情推送
    public static final String MARKET_INDEX_SUB = "market.$contract_code.index.$period"; // 订阅(sub)指数K线数据
    public static final String MARKET_PREMIUM_INDEX_SUB = "market.$contract_code.premium_index.$period"; // 订阅溢价指数K线数据
    public static final String MARKET_ESTIMATED_RATE_SUB = "market.$contract_code.estimated_rate.$period"; // 订阅预测资金费率K线数据
    public static final String MARKET_BASIS_PRICE_TYPE_SUB = "market.$contract_code.basis.$period.$basis_price_type"; // 订阅基差数据
    public static final String MARKET_MARK_PRICE_SUB = "market.$contract_code.mark_price.$period"; // 订阅标记价格K线数据

    //市场行情接口 请求
    public static final String MARKET_KLINE_PERIOD_REQ = "market.$contract_code.kline.$period"; // 1.请求 KLine 数据
    public static final String MARKET_DETAIL_REQ = "market.$contract_code.trade.detail";// 2.请求 Market detail 数据
    public static final String MARKET_INDEX_REQ = "market.$contract_code.index.$period"; // 请求(req)指数K线数据
    public static final String MARKET_PREMIUM_INDEX_REQ = "market.$contract_code.premium_index.$period"; // 请求溢价指数K线数据
    public static final String MARKET_ESTIMATED_RATE_REQ = "market.$contract_code.estimated_rate.$period"; // 请求预测资金费率K线数据
    public static final String MARKET_BASIS_PRICE_TYPE_REQ = "market.$contract_code.basis.$period.$basis_price_type"; // 请求基差数据
    public static final String MARKET_MARK_PRICE_REQ = "market.$contract_code.mark_price.$period"; // 请求标记价格K线数据


    //交易接口
    public static final String ORDERS_SYMBOL = "orders.$symbol-$partition"; // 1.订阅订单成交数据


    //资产接口
    public static final String ACCOUNTS_SYMBOL = "accounts.$symbol-$partition"; // 1.订阅某个品种下的资产变动信息
    public static final String POSITIONS_SYMBOL = "positions.$symbol-$partition"; // 2.订阅某个品种下的持仓变动信息

    //订单和用户数据接口
    public static final String ORDERS_CONTRACT_CODE = "orders.$contract_code"; // 订阅订单成交数据（sub）
    public static final String ORDERS_CROSS_CONTRACT_CODE = "orders_cross.$contract_code"; // 订阅订单成交数据（sub）
    public static final String ACCOUNTS_CONTRACT_CODE = "accounts.$contract_code"; // 资产变动数据（sub）
    public static final String ACCOUNTS_CROSS_MARGIN_ACCOUNT = "accounts_cross.$margin_account"; // 资产变动数据（sub）
    public static final String POSITIONS_CONTRACT_CODE = "positions.$contract_code"; // 持仓变动更新数据（sub）
    public static final String POSITIONS_CROSS_CONTRACT_CODE = "positions_cross.$contract_code"; // 持仓变动更新数据（sub）
    public static final String PUBLIC_LIQUIDATION_ORDERS = "public.$contract_code.liquidation_orders"; // 订阅强平订单数据(免鉴权)（sub）
    public static final String PUBLIC_FUNDING_RATE = "public.$contract_code.funding_rate";
    public static final String PUBLIC_CONTRACT_INFO = "public.$contract_code.contract_info";
    public static final String TRIGGER_ORDER_CONTRACT_CODE = "trigger_order.$contract_code";
    public static final String TRIGGER_ORDER_CROSS_CONTRACT_CODE = "trigger_order_cross.$contract_code";
    public static final String PUBLIC_HEARTBEAT = "public.$service.heartbeat";
    public static final String ACCOUNTS_UNIFY_USDT = "accounts_unify.USDT";
    public static final String MATCHORDERS_CONTRACT_CODE = "matchOrders.$contract_code";
    public static final String MATCHORDERS_CROSS_CONTRACT_CODE = "matchOrders_cross.$contract_code";
}
