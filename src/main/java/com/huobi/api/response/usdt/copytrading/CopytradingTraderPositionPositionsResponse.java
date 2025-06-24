package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderPositionPositionsResponse {
    @SerializedName("tid")
    public String tid;

    @SerializedName("data")
    public Data data;

    @SerializedName("code")
    public long code;

    public static class Data {
        @SerializedName("positions")
        public List<Position> positions;

        @SerializedName("query_id")
        public long queryId;
    }

    public static class Position {
        @SerializedName("sub_position_id")
        public String subPositionId;

        @SerializedName("margin_mode")
        public String marginMode;

        @SerializedName("position_side")
        public String positionSide;

        @SerializedName("lever")
        public String lever;

        @SerializedName("open_order_id")
        public String openOrderId;

        @SerializedName("open_avg_price")
        public String openAvgPrice;

        @SerializedName("open_time")
        public long openTime;

        @SerializedName("volume")
        public String volume;

        @SerializedName("close_time")
        public long closeTime;

        @SerializedName("close_avg_price")
        public String closeAvgPrice;

        @SerializedName("open_fee")
        public String openFee;

        @SerializedName("close_fee")
        public String closeFee;

        @SerializedName("profit")
        public String profit;

        @SerializedName("profit_rate")
        public String profitRate;

        @SerializedName("close_type")
        public int closeType;

        @SerializedName("follow_takes")
        public String followTakes;
    }
}
