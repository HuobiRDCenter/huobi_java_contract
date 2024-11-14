package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CannelTradeAllOrderRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("side")
    private String side;

    @SerializedName("position_side")
    private String positionSide;
}
