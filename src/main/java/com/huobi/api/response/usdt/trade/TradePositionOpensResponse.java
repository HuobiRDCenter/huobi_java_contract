package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradePositionOpensResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    private class DataBean {
        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("cost_open")
        private String costOpen;

        @SerializedName("volume")
        private String volume;

        @SerializedName("available")
        private String available;

        @SerializedName("lever_rate")
        private String leverRate;

        @SerializedName("adl_risk_percent")
        private String adlRiskPercent;

        @SerializedName("liquidation_price")
        private String liquidationPrice;

        @SerializedName("initial_margin")
        private String initialMargin;

        @SerializedName("maintenance_margin")
        private String maintenanceMargin;

        @SerializedName("margin")
        private String margin;

        @SerializedName("profit_unreal")
        private String profitUnreal;

        @SerializedName("profit_rate")
        private String profitRate;

        @SerializedName("margin_rate")
        private String marginRate;

        @SerializedName("margin_currency")
        private String marginCurrency;

        @SerializedName("position_mode")
        private String positionMode;

        @SerializedName("last")
        private double last;

        @SerializedName("contract_type")
        private String contractType;

        @SerializedName("created_time")
        private String createdTime;

        @SerializedName("updated_time")
        private String updatedTime;
    }
}
