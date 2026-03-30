package com.huobi.wss.event;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 合约订单推送响应实体类
 * 对应 topic: match_orders
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchOrdersSubResponse {

    /**
     * 操作名称，推送固定值为 notify
     */
    private String op;

    /**
     * 推送的主题
     */
    private String topic;

    /**
     * 服务端应答时间戳
     */
    private Long ts;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 订单数据列表
     */
    private List<OrderData> data;

    /**
     * 订单数据实体
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderData {

        /**
         * 记录ID
         */
        private String id;

        /**
         * 订单ID
         */
        @SerializedName("order_id")
        private String orderId;

        /**
         * 用户自定义订单ID
         */
        @SerializedName("client_order_id")
        private String clientOrderId;

        /**
         * 合约代码
         */
        @SerializedName("contract_code")
        private String contractCode;

        /**
         * 合约类型
         */
        @SerializedName("contract_type")
        private String contractType;

        /**
         * 买卖方向
         * buy:买 "sell":卖
         */
        private String side;

        /**
         * 仓位方向
         * "long":多 "short":空 "both":单向持仓
         */
        @SerializedName("position_side")
        private String positionSide;

        /**
         * 订单类型
         * "market": 市价，"limit":限价, "post_only":只挂单
         */
        private String type;

        /**
         * 价格匹配
         * 行情当前没有
         */
        @SerializedName("price_match")
        private String priceMatch;

        /**
         * 成交ID
         */
        @SerializedName("trade_id")
        private String tradeId;

        /**
         * 保证金模式
         * 枚举 cross
         */
        @SerializedName("margin_mode")
        private String marginMode;

        /**
         * 委托价格（字符串格式，可转换为BigDecimal）
         */
        private String price;

        /**
         * 委托数量（字符串格式，可转换为BigDecimal）
         */
        private String volume;

        /**
         * 废弃参数
         */
        @SerializedName("lever_rate")
        private String leverRate;

        /**
         * 订单状态
         * new, partially_filled, partially_canceled, filled, canceled
         */
        private String state;

        /**
         * 订单来源
         * system:系统、web:用户网页、api:用户API、m:用户M站、risk:风控系统、
         * settlement:交割结算、ios：ios客户端、android：安卓客户端、windows：windows客户端、
         * mac：mac客户端、trigger：计划委托触发、tpsl:止盈止损触发、ADL: adl订单
         */
        @SerializedName("order_source")
        private String orderSource;

        /**
         * 订单时效
         * fok, ioc, gtc，非必填，默认是gtc
         */
        @SerializedName("time_in_force")
        private String timeInForce;

        /**
         * 只减仓
         */
        @SerializedName("reduce_only")
        private Boolean reduceOnly;

        /**
         * 成交均价（字符串格式，可转换为BigDecimal）
         */
        @SerializedName("trade_price")
        private String tradePrice;

        /**
         * 成交数量（字符串格式，可转换为BigDecimal）
         */
        @SerializedName("trade_volume")
        private String tradeVolume;

        /**
         * 累计成交数量（字符串格式，可转换为BigDecimal）
         */
        @SerializedName("total_trade_volume")
        private String totalTradeVolume;

        /**
         * 成交金额（字符串格式，可转换为BigDecimal）
         */
        @SerializedName("trade_turnover")
        private String tradeTurnover;

        /**
         * 撤单原因
         */
        @SerializedName("cancel_reason")
        private String cancelReason;

        /**
         * 订单创建时间, UTC时间戳(MS)
         */
        @SerializedName("created_time")
        private String createdTime;

        /**
         * 撮合更新时间, UTC时间戳(MS)
         */
        @SerializedName("match_time")
        private String matchTime;

        /**
         * 防自成交策略
         * allow: 允许自成交
         * cancel_taker: 撤销taker单
         * cancel_maker: 撤销maker单
         * cancel_both: 撤销全部订单
         */
        @SerializedName("self_match_prevent")
        private String selfMatchPrevent;

        /**
         * 成交角色
         * taker，maker
         */
        private String role;
    }
}