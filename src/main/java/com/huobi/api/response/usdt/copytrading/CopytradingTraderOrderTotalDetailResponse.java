package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderOrderTotalDetailResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private Data data;

    @SerializedName("code")
    private long code;

    public static class Data {
        @SerializedName("total_follower_num")
        private String totalFollowerNum;

        @SerializedName("current_follower_num")
        private String currentFollowerNum;

        @SerializedName("total_pl")
        private String totalPl;

        @SerializedName("win_rate")
        private String winRate;

        @SerializedName("last_24_hours_profit")
        private List<HourlyProfit> last24HoursProfit;

        @SerializedName("last_90_days_profit")
        private List<DailyProfit> last90DaysProfit;
    }

    public static class HourlyProfit {
        @SerializedName("rate")
        private String rate;

        @SerializedName("amount")
        private String amount;

        @SerializedName("ts")
        private String ts;
    }

    public static class DailyProfit {
        @SerializedName("rate")
        private String rate;

        @SerializedName("amount")
        private String amount;

        @SerializedName("ts")
        private String ts;
    }
}
