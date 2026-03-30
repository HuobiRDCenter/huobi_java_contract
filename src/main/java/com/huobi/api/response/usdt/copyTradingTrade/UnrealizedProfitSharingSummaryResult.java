package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

/**
 * 参数	数据类型	是否必填	描述
 * instType	String	 true	产品类型 PERP 永续合约
 * ccy	String	 true	分润币种
 * totalAmt	String	 true	总待分润金额
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnrealizedProfitSharingSummaryResult {

    /** 产品类型 PERP 永续合约 */
    private String instType;

    /** 分润币种 */
    private String ccy;

    /** 总待分润金额 */
    private String totalAmt;
}
