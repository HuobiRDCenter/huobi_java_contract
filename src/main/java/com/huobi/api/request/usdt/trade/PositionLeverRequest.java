package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PositionLeverRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("margin_mode")
    private String marginMode;
}
