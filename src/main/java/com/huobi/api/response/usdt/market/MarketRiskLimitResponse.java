package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarketRiskLimitResponse {
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

        @SerializedName("contract_type")
        private String contractType;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("tier")
        private String tier;

        @SerializedName("max_lever")
        private String maxLever;

        @SerializedName("maintenance_margin_rate")
        private String maintenanceMarginRate;

        @SerializedName("max_volume")
        private String maxVolume;

        @SerializedName("min_volume")
        private String minVolume;
    }
}
