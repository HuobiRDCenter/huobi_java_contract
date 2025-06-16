package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderTotalProfitHistoryResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private Data data;

    @SerializedName("code")
    private long code;

    public static class Data {
        @SerializedName("items")
        private List<Item> items;
    }

    public static class Item {
        @SerializedName("profit_currency")
        private String profitCurrency;

        @SerializedName("total_profit_amont")
        private String totalProfitAmount;
    }
}
