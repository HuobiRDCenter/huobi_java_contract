package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderQueryContractResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("code")
    private long code;

    public static class DataItem {
        @SerializedName("contract_code")
        private String contractCode;
    }
}
