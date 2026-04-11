package com.huobi.api.request.usdt.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询当前平台合约持仓总量请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketOpenInterestRequest {

    /**
     * 合约代码
     * 必填: 是
     * 永续："BTC-USDT"... ，交割："BTC-USDT-210625"...
     */
    private String contractCode;
}
