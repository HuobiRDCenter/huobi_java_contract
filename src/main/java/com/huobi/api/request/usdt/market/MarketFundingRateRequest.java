package com.huobi.api.request.usdt.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询当前资金费率请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketFundingRateRequest {

    /**
     * 交易对名称
     * 必填: 是
     * 使用逗号分隔，可查询多个，最多查询10个
     * 示例: "BTC-USDT,ETH-USDT"
     */
    private String contractCode;
}
