package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderTpslOrderResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private boolean data;

    @SerializedName("code")
    private long code;
}
