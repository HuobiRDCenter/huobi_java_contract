package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询当前平台合约持仓总量响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketOpenInterestResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private OpenInterestData data;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("ts")
    private Long ts;

    /**
     * 持仓总量数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OpenInterestData {
        /**
         * 合约代码
         */
        @SerializedName("contract_code")
        private String contractCode;

        /**
         * 持仓量(币)，单边数量
         */
        @SerializedName("amount")
        private String amount;

        /**
         * 持仓量(张)，单边数量
         */
        @SerializedName("volume")
        private String volume;

        /**
         * 总持仓额（单位为合约的计价币种，如USDT）
         */
        @SerializedName("value")
        private String value;

        /**
         * 最近24小时成交量（币）（当前时间-24小时）,值是买卖双边之和
         */
        @SerializedName("trade_amount")
        private String tradeAmount;

        /**
         * 最近24小时成交量（张）（当前时间-24小时）,值是买卖双边之和
         */
        @SerializedName("trade_volume")
        private String tradeVolume;

        /**
         * 最近24小时成交额 （当前时间-24小时）,值是买卖双边之和
         */
        @SerializedName("trade_turnover")
        private String tradeTurnover;
    }
}