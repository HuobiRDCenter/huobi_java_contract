package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import com.huobi.api.annotation.Required;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradePositionOpensResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<DataBean> data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataBean {
        @Required
        @SerializedName("contract_code")
        private String contractCode;

        @Required
        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("direction")
        private String direction;

        @SerializedName("open_avg_price")
        private String openAvgPrice;

        @Required
        @SerializedName("margin_mode")
        private String marginMode;

        @Required
        @SerializedName("volume")
        private String volume;

        @SerializedName("available")
        private String available;

        @Required
        @SerializedName("lever_rate")
        private String leverRate;

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

        @SerializedName("last_price")
        private String lastPrice;

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
