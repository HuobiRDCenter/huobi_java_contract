package com.huobi.api.response.usdt.market;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询预估结算价响应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketEstimatedSettlementPriceResponse {
    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<EstimatedSettlementPriceData> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    /**
     * 预估结算价数据
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EstimatedSettlementPriceData {
        /**
         * 合约代码
         */
        @SerializedName("contract_code")
        private String contractCode;

        /**
         * 结算类型
         * "delivery"：交割
         * "settlement"：结算
         */
        @SerializedName("settlement_type")
        private String settlementType;

        /**
         * 本期预估结算价格
         */
        @SerializedName("estimated_settlement_price")
        private String estimatedSettlementPrice;
    }
}