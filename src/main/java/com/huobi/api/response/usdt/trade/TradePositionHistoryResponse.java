package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradePositionHistoryResponse {
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

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("cost_open")
        private String costOpen;

        @SerializedName("close_avg_price")
        private String closeAvgPrice;

        @SerializedName("volume")
        private String volume;

        @SerializedName("available")
        private String available;

        @SerializedName("lever_rate")
        private String leverRate;

        @SerializedName("profit")
        private String profit;

        @SerializedName("profit_ratio")
        private String profitRatio;

        @SerializedName("state")
        private String state;

        @SerializedName("fee")
        private String fee;

        @SerializedName("funding_fee")
        private String fundingFee;

        @SerializedName("margin_currency")
        private String marginCurrency;

        @SerializedName("last")
        private Double last;

        @SerializedName("business_type")
        private String businessType;

        @SerializedName("contract_type")
        private String contractType;

        @SerializedName("created_time")
        private String createdTime;

        @SerializedName("updated_time")
        private String updatedTime;
    }
}
