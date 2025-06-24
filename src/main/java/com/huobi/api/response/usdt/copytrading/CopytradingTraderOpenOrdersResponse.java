package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderOpenOrdersResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private List<OrderData> data;

    @SerializedName("code")
    private long code;

    public static class OrderData {
        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("price")
        private String price;

        @SerializedName("volume")
        private String volume;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("order_direction")
        private int orderDirection;

        @SerializedName("lever")
        private int lever;

        @SerializedName("avg_price")
        private String avgPrice;

        @SerializedName("avg_volume")
        private String avgVolume;

        @SerializedName("fee")
        private String fee;
    }
}
