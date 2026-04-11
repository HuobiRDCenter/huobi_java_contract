package com.huobi.api.request.usdt.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询预估结算价请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketEstimatedSettlementPriceRequest {
    /**
     * 合约代码
     * 可选参数
     * 永续："BTC-USDT"... ，交割："BTC-USDT-210625"...
     * 如果不传，返回所有合约的预估结算价
     */
    private String contractCode;
}