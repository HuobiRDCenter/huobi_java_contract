package com.huobi.api.request.usdt.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询限价最高买价与最低卖价请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketPriceLimitRequest {

    /**
     * 合约代码
     * 必填: 否
     * 永续："BTC-USDT"...
     * 交割："BTC-USDT-210625"...
     * 如果不传，返回所有合约的价格限制
     */
    private String contractCode;
}
