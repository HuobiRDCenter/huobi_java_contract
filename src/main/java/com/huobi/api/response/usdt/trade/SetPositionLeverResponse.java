package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetPositionLeverResponse {
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

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("lever_rate")
        private String leverRate;
    }
}
