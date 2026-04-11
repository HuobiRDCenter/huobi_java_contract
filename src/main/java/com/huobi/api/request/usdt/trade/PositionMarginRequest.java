package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PositionMarginRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("position_side")
    private String position_side;

    @SerializedName("amount")
    private String amount;

    @SerializedName("type")
    private String type;
}
