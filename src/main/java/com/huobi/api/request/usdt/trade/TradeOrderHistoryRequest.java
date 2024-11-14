package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TradeOrderHistoryRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("side")
    private String side;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("client_order_id")
    private String clientOrderId;

    @SerializedName("state")
    private String state;

    @SerializedName("type")
    private String type;

    @SerializedName("price_match")
    private String priceMatch;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("from")
    private Long from;

    @SerializedName("limit")
    private Integer limit;

    @SerializedName("direct")
    private String direct;
}
