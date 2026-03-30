package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询当前资金费率响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketFundingRateResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<DataBean> data;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("ts")
    private Long ts;

    /**
     * 资金费率数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataBean {
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

        /**
         * 下一期资金费率时间
         */
        @SerializedName("next_funding_time")
        private String nextFundingTime;

        /**
         * 资金费率下限
         */
        @SerializedName("min_funding_rate")
        private String minFundingRate;

        /**
         * 资金费率上限
         */
        @SerializedName("max_funding_rate")
        private String maxFundingRate;
    }
}
