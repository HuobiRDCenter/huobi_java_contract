package com.huobi.api.request.usdt.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class AccountBillsRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("margin_mode")
    private String marginMode;

    @SerializedName("type")
    private String type;

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
