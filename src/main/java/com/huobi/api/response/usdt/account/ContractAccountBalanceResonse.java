package com.huobi.api.response.usdt.account;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ContractAccountBalanceResonse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    private class DataBean {
        @SerializedName("state")
        private String state;

        @SerializedName("equity")
        private String equity;

        @SerializedName("initial_margin")
        private String initialMargin;

        @SerializedName("maintenance_margin")
        private String maintenanceMargin;

        @SerializedName("maintenance_margin_rate")
        private String maintenanceMarginRate;

        @SerializedName("profit_unreal")
        private String profitUnreal;

        @SerializedName("available_margin")
        private String availableMargin;

        @SerializedName("created_time")
        private String createdTime;

        @SerializedName("updated_time")
        private String updatedTime;
        @SerializedName("details")
        private List<Details> details;
    }



    @Data
    @AllArgsConstructor
    public class Details {

        @SerializedName("currency")
        private String currency;

        @SerializedName("equity")
        private String equity;

        @SerializedName("isolated_equity")
        private String isolatedEquity;

        @SerializedName("available")
        private String available;

        @SerializedName("profit_unreal")
        private String profitUnreal;

        @SerializedName("initial_margin")
        private String initialMargin;

        @SerializedName("maintenance_margin")
        private String maintenanceMargin;

        @SerializedName("maintenance_margin_rate")
        private String maintenanceMarginRate;

        @SerializedName("initial_margin_rate")
        private String initialMarginRate;

        @SerializedName("created_time")
        private String createdTime;

        @SerializedName("updated_time")
        private String updatedTime;
    }
}
