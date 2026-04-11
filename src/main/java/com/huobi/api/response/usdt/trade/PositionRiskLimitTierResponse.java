package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PositionRiskLimitTierResponse {

    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<DataBean> data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;

    private class DataBean {
        @SerializedName("contract_code")
        private String contractCode;

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

        @SerializedName("volume_unit")
        private String volumeUnit;
    }
}
