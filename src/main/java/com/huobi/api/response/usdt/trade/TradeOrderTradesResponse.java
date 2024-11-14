package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeOrderTradesResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    private class DataBean {
        @SerializedName("id")
        private String id;

        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("order_id")
        private String orderId;

        @SerializedName("trade_id")
        private String tradeId;

        @SerializedName("side")
        private String side;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("order_type")
        private String orderType;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("type")
        private String type;

        @SerializedName("client_order_id")
        private String clientOrderId;

        @SerializedName("role")
        private String role;

        @SerializedName("trade_price")
        private String tradePrice;

        @SerializedName("trade_volume")
        private String tradeVolume;

        @SerializedName("trade_turnover")
        private String tradeTurnover;

        @SerializedName("created_time")
        private Long createdTime;

        @SerializedName("updated_time")
        private Long updatedTime;

        @SerializedName("order_source")
        private String orderSource;

        @SerializedName("fee_currency")
        private String feeCurrency;

        @SerializedName("trade_fee")
        private String tradeFee;

        @SerializedName("deduction_currency")
        private String deductionCurrency;

        @SerializedName("deduction_amount")
        private String deductionAmount;

        @SerializedName("deduction_price")
        private String deductionPrice;

        @SerializedName("profit")
        private String profit;

        @SerializedName("contract_type")
        private String contractType;
    }

}
