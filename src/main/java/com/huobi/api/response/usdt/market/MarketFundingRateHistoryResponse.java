package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询历史资金费率响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketFundingRateHistoryResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<FundingRateHistoryData> data;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("ts")
    private Long ts;

    /**
     * 历史资金费率数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FundingRateHistoryData {
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
         * 当期资金费率
         */
        @SerializedName("funding_rate")
        private String fundingRate;

        /**
         * 当期资金费率时间
         */
        @SerializedName("funding_time")
        private String fundingTime;
    }
}
