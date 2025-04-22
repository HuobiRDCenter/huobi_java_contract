package com.huobi.api.request.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MarketRiskLimitRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("contract_type")
    private String contractType;

    @SerializedName("margin_mode")
    private String marginMode;

    @SerializedName("tier")
    private String tier;
}
