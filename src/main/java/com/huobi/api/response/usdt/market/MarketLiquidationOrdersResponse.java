package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询强平订单响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketLiquidationOrdersResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<LiquidationOrderData> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    /**
     * 强平订单数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LiquidationOrderData {
        /**
         * 查询ID
         */
        @SerializedName("id")
        private String id;

        /**
         * 合约代码
         */
        @SerializedName("contract_code")
        private String contractCode;

        /**
         * 强平时间（毫秒时间戳）
         */
        @SerializedName("liquidation_time")
        private String liquidationTime;

        /**
         * 买卖方向
         * buy: 买
         * sell: 卖
         */
        @SerializedName("side")
        private String side;

        /**
         * 持仓方向
         * long: 多
         * short: 空
         * both: 单向持仓
         */
        @SerializedName("position_side")
        private String positionSide;

        /**
         * 强平数量（张）
         */
        @SerializedName("volume")
        private String volume;

        /**
         * 强平数量（币）
         */
        @SerializedName("amount")
        private String amount;

        /**
         * 破产价格
         */
        @SerializedName("bankrupt_price")
        private String bankruptPrice;

        /**
         * 强平金额（计价币种）
         */
        @SerializedName("trade_turnover")
        private String tradeTurnover;
    }
}