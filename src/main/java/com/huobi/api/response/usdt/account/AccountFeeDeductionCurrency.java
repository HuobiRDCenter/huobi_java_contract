package com.huobi.api.response.usdt.account;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountFeeDeductionCurrency {
    @SerializedName("fee_option")
    private Integer feeOption;
    @SerializedName("deduction_currency")
    private String deductionCurrency;
}
