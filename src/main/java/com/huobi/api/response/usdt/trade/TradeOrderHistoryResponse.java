package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import com.huobi.api.annotation.Required;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeOrderHistoryResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    @Data
    @AllArgsConstructor
    public static class DataBean {
        @Required
        @SerializedName("id")
        private String id;

        @Required
        @SerializedName("contract_code")
        private String contractCode;

        @Required
        @SerializedName("side")
        private String side;

        @Required
        @SerializedName("position_side")
        private String positionSide;

        @Required
        @SerializedName("type")
        private String type;

        @SerializedName("price_match")
        private String priceMatch;

        @Required
        @SerializedName("order_id")
        private String orderId;

        @Required
        @SerializedName("client_order_id")
        private String clientOrderId;

        @Required
        @SerializedName("margin_mode")
        private String marginMode;

        @Required
        @SerializedName("price")
        private String price;

        @Required
        @SerializedName("volume")
        private String volume;

        @Required
        @SerializedName("lever_rate")
        private String leverRate;

        @Required
        @SerializedName("state")
        private String state;

        @SerializedName("canceled_source")
        private String canceledSource;

        @Required
        @SerializedName("order_source")
        private String orderSource;

        @Required
        @SerializedName("reduce_only")
        private Boolean reduceOnly;

        @Required
        @SerializedName("time_in_force")
        private String timeInForce;

        @Required
        @SerializedName("tp_trigger_price")
        private String tpTriggerPrice;

        @Required
        @SerializedName("tp_order_price")
        private String tpOrderPrice;

        @Required
        @SerializedName("tp_type")
        private String tpType;

        @SerializedName("tp_trigger_price_type")
        private Integer tpTriggerPriceType;

        @Required
        @SerializedName("sl_trigger_price")
        private String slTriggerPrice;

        @Required
        @SerializedName("sl_order_price")
        private String slOrderPrice;

        @Required
        @SerializedName("sl_type")
        private String slType;

        @SerializedName("sl_trigger_price_type")
        private Integer slTriggerPriceType;

        @Required
        @SerializedName("trade_avg_price")
        private String tradeAvgPrice;

        @Required
        @SerializedName("trade_volume")
        private String tradeVolume;

        @Required
        @SerializedName("trade_turnover")
        private String tradeTurnover;

        @Required
        @SerializedName("fee_currency")
        private String feeCurrency;

        @Required
        @SerializedName("fee")
        private String fee;

        @SerializedName("deduction_currency")
        private String deductionCurrency;

        @SerializedName("deduction_amount")
        private String deductionAmount;

        @SerializedName("price_protect")
        private Boolean priceProtect;

        @Required
        @SerializedName("profit")
        private String profit;

        @Required
        @SerializedName("contract_type")
        private String contractType;

        @Required
        @SerializedName("cancel_reason")
        private String cancelReason;

        @Required
        @SerializedName("created_time")
        private String createdTime;

        @Required
        @SerializedName("updated_time")
        private String updatedTime;

        @Required
        @SerializedName("self_match_prevent")
        private String selfMatchPrevent;

        @Required
        @SerializedName("cancel_volume")
        private String cancelVolume;
    }
}
