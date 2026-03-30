package com.huobi.api.request.usdt.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询精英账户多空持仓对比-持仓数请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketElitePositionRatioRequest {
    /**
     * 合约代码
     * 必填参数
     * 永续："BTC-USDT"... , 交割："BTC-USDT-FUTURES" ...
     */
    private String contractCode;

    /**
     * 时间周期
     * 必填参数
     * 可选值：5min, 15min, 30min, 60min, 4hour, 1day
     */
    private String period;
}