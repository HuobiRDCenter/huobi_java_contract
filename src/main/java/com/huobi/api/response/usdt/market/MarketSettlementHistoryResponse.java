package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询平台结算历史响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketSettlementHistoryResponse {
    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<SettlementHistoryData> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    /**
     * 结算历史数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SettlementHistoryData {
        /**
         * 查询ID，可作为下一次查询请求的from字段
         */
        @SerializedName("id")
        private String id;

        /**
         * 合约代码
         */
        @SerializedName("contract_code")
        private String contractCode;

        /**
         * 结算时间（时间戳，单位毫秒）
         * 当settlement_type为交割时，该时间为交割时间
         * 当settlement_type为结算时，该时间为结算时间
         */
        @SerializedName("settlement_time")
        private String settlementTime;

        /**
         * 分摊比例
         */
        @SerializedName("clawback_ratio")
        private String clawbackRatio;

        /**
         * 结算价格
         * 当settlement_type为交割时，该价格为交割价格
         * 当settlement_type为结算时，该价格为结算价格
         */
        @SerializedName("settlement_price")
        private String settlementPrice;
    }
}