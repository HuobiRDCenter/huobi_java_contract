package com.huobi.api.response.usdt.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultiAssetsMarginResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    private class DataBean {
        @SerializedName("assets_mode")
        private Integer assetsMode;
    }
}
