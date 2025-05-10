package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class GetTradeOrderResponse {
    private Integer code;
    private String message;
    private Long ts;
    private List<DataBean> data;

    @Data
    @AllArgsConstructor
    public static class DataBean {
        @SerializedName("id")
        private String id;

        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("side")
        private String side;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("type")
        private String type;

        @SerializedName("price_match")
        private String priceMatch;

        @SerializedName("order_id")
        private String orderId;

        @SerializedName("client_order_id")
        private String clientOrderId;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("price")
        private String price;

        @SerializedName("volume")
        private String volume;

        @SerializedName("lever_rate")
        private Long leverRate;

        @SerializedName("state")
        private String state;

        @SerializedName("order_source")
        private String orderSource;

        @SerializedName("reduce_only")
        private Boolean reduceOnly;

        @SerializedName("time_in_force")
        private String timeInForce;

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

        @SerializedName("trade_avg_price")
        private String tradeAvgPrice;

        @SerializedName("trade_volume")
        private String tradeVolume;

        @SerializedName("trade_turnover")
        private String tradeTurnover;

        @SerializedName("fee_currency")
        private String feeCurrency;

        @SerializedName("fee")
        private String fee;

        @SerializedName("price_protect")
        private Boolean priceProtect;

        @SerializedName("profit")
        private String profit;

        @SerializedName("contract_type")
        private String contractType;

        @SerializedName("created_time")
        private Long createdTime;

        @SerializedName("updated_time")
        private Long updatedTime;
    }
}
