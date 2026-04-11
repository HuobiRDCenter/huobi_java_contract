package com.huobi.api.response.usdt.algo;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

/**
 * 查询当前未触发策略委托响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryOpenAlgoOrdersResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<DataBean> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    /**
     * 当前未触发策略订单数据
     * 注意：这个结构与查询单笔订单的AlgoOrderData不同
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataBean {
        @SerializedName("id")
        private String id;

        @SerializedName("volume")
        private String volume;

        @SerializedName("type")
        private String type;

        @SerializedName("state")
        private String state;

        @SerializedName("side")
        private String side;

        @SerializedName("algo_id")
        private String algoId;

        @SerializedName("algo_client_order_id")
        private String algoClientOrderId;

        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("created_time")
        private String createdTime;

        @SerializedName("updated_time")
        private String updatedTime;

        @SerializedName("order_source")
        private String orderSource;

        @SerializedName("active_price")
        private String activePrice;

        @SerializedName("callback_rate")
        private String callbackRate;

        @SerializedName("reduce_only")
        private Boolean reduceOnly;

        @SerializedName("order_price_type")
        private String orderPriceType;

        // 以下是可选字段，根据不同的订单类型可能有不同的值
        @SerializedName("tp_trigger_price")
        private String tpTriggerPrice;

        @SerializedName("tp_order_price")
        private String tpOrderPrice;

        @SerializedName("tp_type")
        private String tpType;

        @SerializedName("tp_trigger_price_type")
        private String tpTriggerPriceType;

        @SerializedName("sl_trigger_price")
        private String slTriggerPrice;

        @SerializedName("sl_order_price")
        private String slOrderPrice;

        @SerializedName("sl_type")
        private String slType;

        @SerializedName("sl_trigger_price_type")
        private String slTriggerPriceType;

        @SerializedName("price")
        private String price;

        @SerializedName("price_match")
        private String priceMatch;

        @SerializedName("trigger_price")
        private String triggerPrice;

        @SerializedName("trigger_price_type")
        private String triggerPriceType;

        @SerializedName("actual_volume")
        private String actualVolume;

        @SerializedName("actual_price")
        private String actualPrice;

        @SerializedName("actual_time")
        private String actualTime;

        @SerializedName("relation_order_id")
        private String relationOrderId;
    }
}