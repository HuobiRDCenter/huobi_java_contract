package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TradePositionHistoryRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("contract_type")
    private String contractType;

    @SerializedName("margin_mode")
    private String marginMode;

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
