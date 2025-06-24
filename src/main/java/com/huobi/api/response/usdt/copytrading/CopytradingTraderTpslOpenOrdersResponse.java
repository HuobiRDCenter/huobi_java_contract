package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderTpslOpenOrdersResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private List<OrderData> data;

    @SerializedName("code")
    private long code;

    /**
     * 订单数据内部类
     */
    public static class OrderData {
        @SerializedName("contract_code")
        private String contractCode;

        @SerializedName("volume")
        private String volume;

        @SerializedName("margin_mode")
        private String marginMode;

        @SerializedName("position_side")
        private String positionSide;

        @SerializedName("trigger_type")
        private String triggerType;

        @SerializedName("tpsl_order_type")
        private String tpslOrderType;

        @SerializedName("price")
        private String price;
    }
}
