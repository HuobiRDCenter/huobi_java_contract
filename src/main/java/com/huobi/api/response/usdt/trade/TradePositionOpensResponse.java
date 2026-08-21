package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import com.huobi.api.annotation.Required;
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
    @Data
    @AllArgsConstructor
    public static class DataBean {
        @Required
        @SerializedName("contract_code")
        private String contractCode;

        @Required
        @SerializedName("position_side")
        private String positionSide;

        @Required
        @SerializedName("direction")
        private String direction;

        @Required
        @SerializedName("open_avg_price")
        private String openAvgPrice;

        @Required
        @SerializedName("margin_mode")
        private String marginMode;

        @Required
        @SerializedName("volume")
        private String volume;

        @Required
        @SerializedName("available")
        private String available;

        @Required
        @SerializedName("lever_rate")
        private String leverRate;

        @Required
        @SerializedName("liquidation_price")
        private String liquidationPrice;

        @Required
        @SerializedName("initial_margin")
        private String initialMargin;

        @Required
        @SerializedName("maintenance_margin")
        private String maintenanceMargin;

        @Required
        @SerializedName("margin")
        private String margin;

        @Required
        @SerializedName("profit_unreal")
        private String profitUnreal;

        @Required
        @SerializedName("profit_rate")
        private String profitRate;

        @Required
        @SerializedName("margin_rate")
        private String marginRate;

        @Required
        @SerializedName("margin_currency")
        private String marginCurrency;

        @Required
        @SerializedName("last_price")
        private String lastPrice;

        @Required
        @SerializedName("mark_price")
        private String markPrice;

        @Required
        @SerializedName("contract_type")
        private String contractType;

        @Required
        @SerializedName("created_time")
        private String createdTime;

        @Required
        @SerializedName("updated_time")
        private String updatedTime;
    }
}
