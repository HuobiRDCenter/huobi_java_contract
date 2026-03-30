package com.huobi.api.response.usdt.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountBillsResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<DataBean> data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    private class DataBean {

        @SerializedName("id")
        private String id;

        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("type")
        private String type;

        @SerializedName("currency")
        private String currency;

        @SerializedName("amount")
        private String amount;

        @SerializedName("created_time")
        private String createdTime;
    }
}
