package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TradeOrderOpensRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("side")
    private String side;

    @SerializedName("margin_mode")
    private String marginMode;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("client_order_id")
    private String clientOrderId;

    @SerializedName("from")
    private Long from;

    @SerializedName("limit")
    private Integer limit;

    @SerializedName("direct")
    private String direct;
}
