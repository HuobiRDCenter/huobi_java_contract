package com.huobi.api.response.usdt.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountFeeDeductionCurrencyResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    private class DataBean {
        @SerializedName("fee_option")
        private Integer feeOption;
        @SerializedName("deduction_currency")
        private String deductionCurrency;
    }
}
