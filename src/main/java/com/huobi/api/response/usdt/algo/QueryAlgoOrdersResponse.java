package com.huobi.api.response.usdt.algo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询策略委托订单响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryAlgoOrdersResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<DataBean> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    /**
     * 策略订单数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataBean {
        @SerializedName("id")
        private String id;

        @SerializedName("algo_id")
        private String algoId;

        @SerializedName("algo_client_order_id")
        private String algoClientOrderId;

        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("volume")
        private String volume;

        @SerializedName("type")
        private String type;

        @SerializedName("state")
        private String state;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("side")
        private String side;

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

        @SerializedName("active_price")
        private String activePrice;

        @SerializedName("order_price_type")
        private String orderPriceType;

        @SerializedName("callback_rate")
        private String callbackRate;

        @SerializedName("reduce_only")
        private Boolean reduceOnly;

        @SerializedName("actual_volume")
        private String actualVolume;

        @SerializedName("actual_price")
        private String actualPrice;

        @SerializedName("actual_time")
        private String actualTime;

        @SerializedName("relation_order_id")
        private String relationOrderId;

        @SerializedName("created_time")
        private String createdTime;

        @SerializedName("updated_time")
        private String updatedTime;

        @SerializedName("order_source")
        private String orderSource;
    }
}