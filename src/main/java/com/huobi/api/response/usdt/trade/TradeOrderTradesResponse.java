package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import com.huobi.api.annotation.Required;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeOrderTradesResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<DataBean> data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataBean {
        @Required
        @SerializedName("id")
        private String id;

        @Required
        @SerializedName("contract_code")
        private String contractCode;

        @Required
        @SerializedName("order_id")
        private String orderId;

        @Required
        @SerializedName("trade_id")
        private String tradeId;

        @Required
        @SerializedName("side")
        private String side;

        @Required
        @SerializedName("position_side")
        private String positionSide;

        @Required
        @SerializedName("order_type")
        private String orderType;

        @Required
        @SerializedName("margin_mode")
        private String marginMode;

        @Required
        @SerializedName("type")
        private String type;

        @SerializedName("client_order_id")
        private String clientOrderId;

        @Required
        @SerializedName("role")
        private String role;

        @Required
        @SerializedName("trade_price")
        private String tradePrice;

        @Required
        @SerializedName("trade_volume")
        private String tradeVolume;

        @Required
        @SerializedName("trade_turnover")
        private String tradeTurnover;

        @Required
        @SerializedName("created_time")
        private Long createdTime;

        @Required
        @SerializedName("updated_time")
        private Long updatedTime;

        @Required
        @SerializedName("order_source")
        private String orderSource;

        @Required
        @SerializedName("fee_currency")
        private String feeCurrency;

        @Required
        @SerializedName("trade_fee")
        private String tradeFee;

        @SerializedName("deduction_currency")
        private String deductionCurrency;

        @SerializedName("deduction_amount")
        private String deductionAmount;

        @Required
        @SerializedName("deduction_price")
        private String deductionPrice;

        @Required
        @SerializedName("profit")
        private String profit;

        @Required
        @SerializedName("contract_type")
        private String contractType;
    }

}
