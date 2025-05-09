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

    @SerializedName("business_type")
    private String businessType;

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

    @SerializedName("margin_mode")
    private String marginMode;
}
