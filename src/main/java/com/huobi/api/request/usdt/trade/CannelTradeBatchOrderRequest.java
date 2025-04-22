package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CannelTradeBatchOrderRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("client_order_id")
    private String clientOrderId;
}
