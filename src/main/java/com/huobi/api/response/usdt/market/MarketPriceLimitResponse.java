package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询限价最高买价与最低卖价响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketPriceLimitResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<PriceLimitData> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    /**
     * 价格限制数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PriceLimitData {
        /**
         * 合约代码
         */
        @SerializedName("contract_code")
        private String contractCode;

        /**
         * 最高买价
         */
        @SerializedName("high_limit")
        private String highLimit;

        /**
         * 最低卖价
         */
        @SerializedName("low_limit")
        private String lowLimit;
    }
}