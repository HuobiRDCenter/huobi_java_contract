package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderCurrentPositionsResponse {
    @SerializedName("tid")
    public String tid;

    @SerializedName("data")
    public Data data;

    @SerializedName("code")
    public long code;

    public static class Data {
        @SerializedName("positions")
        public Position[] positions;

        @SerializedName("query_id")
        public long queryId;
    }

    public static class Position {
        @SerializedName("sub_position_id")
        public String subPositionId;

        @SerializedName("margin_mode")
        public String marginMode; // isolated/cross

        @SerializedName("position_side")
        public String positionSide; // long/short

        @SerializedName("lever")
        public String lever;

        @SerializedName("open_order_id")
        public String openOrderId;

        @SerializedName("open_avg_price")
        public String openAvgPrice;

        @SerializedName("open_fee")
        public String openFee;

        @SerializedName("open_time")
        public long openTime;

        @SerializedName("position_margin")
        public String positionMargin;

        @SerializedName("volume")
        public String volume;

        @SerializedName("liquidation_price")
        public String liquidationPrice;

        @SerializedName("tp_trigger_price")
        public String tpTriggerPrice;

        @SerializedName("sl_trigger_price")
        public String slTriggerPrice;
    }
}
