package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderProfitHistoryDetailsResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private Data data;

    @SerializedName("code")
    private long code;

    public static class Data {
        @SerializedName("profit_items")
        private List<ProfitItem> profitItems;

        @SerializedName("query_id")
        private long queryId;
    }

    public static class ProfitItem {
        @SerializedName("user_id")
        private String userId;

        @SerializedName("profit_currency")
        private String profitCurrency;

        @SerializedName("profit_amont")
        private String profitAmount;

        @SerializedName("profit_time")
        private String profitTime;
    }
}
