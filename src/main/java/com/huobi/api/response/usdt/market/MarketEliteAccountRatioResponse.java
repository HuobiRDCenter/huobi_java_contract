package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询精英账户多空持仓对比-账户数响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketEliteAccountRatioResponse {
    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<EliteAccountRatioData> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    /**
     * 精英账户多空持仓对比数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EliteAccountRatioData {
        /**
         * 合约代码
         */
        @SerializedName("contract_code")
        private String contractCode;

        /**
         * 净多仓的账户比例
         */
        @SerializedName("buy_ratio")
        private String buyRatio;

        /**
         * 净空仓的账户比例
         */
        @SerializedName("sell_ratio")
        private String sellRatio;

        /**
         * 数据生成时间，UNIX时间戳，以毫秒为单位
         */
        @SerializedName("ts")
        private String ts;
    }
}