package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

/**
 * 参数	数据类型	是否必填	描述
 * instId	String	 false	产品ID，如：BTC-USDT-PERP
 * instType	String	 false	产品类型 PERP 永续合约
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstrumentResult {

    private String instId;

    private String instType;
}
