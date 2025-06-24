package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderRemoveFollowerResponse {
    @SerializedName("tid")
    public String tid;

    @SerializedName("data")
    public boolean data;

    @SerializedName("code")
    public long code;
}
