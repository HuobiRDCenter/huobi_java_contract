package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderPositionListResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private Data data;

    @SerializedName("code")
    private long code;

    public static class Data {
        @SerializedName("positions")
        private List<Position> positions;
    }

    public static class Position {
        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("lever")
        private String lever;

        @SerializedName("open_avg_price")
        private String openAvgPrice;

        @SerializedName("position_margin")
        private String positionMargin;

        @SerializedName("margin_rate")
        private String marginRate;

        @SerializedName("volume")
        private String volume;

        @SerializedName("liquidation_price")
        private String liquidationPrice;

        @SerializedName("unreal_profit")
        private String unrealProfit;

        @SerializedName("profit")
        private String profit;

        @SerializedName("profit_rate")
        private String profitRate;

        @SerializedName("contract_code")
        private String contractCode;
    }
}
