package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

/**
 * 参数	数据类型	是否必填	描述
 * id	String	 true	记录ID
 * instType	String	 true	产品类型 PERP 永续合约
 * followerName	String	 true	跟单人的昵称
 * ccy	String	 true	分润币种
 * profitSharingAmt	String	 true	分润金额
 * ts	String	 true	分润时间 Unix微秒精度时间戳格式，例如：1759161600000000
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfitSharingHistoryResult {

    /** 记录ID */
    private String id;

    /** 产品类型 PERP 永续合约 */
    private String instType;

    /** 跟单人的昵称 */
    private String followerName;

    /** 分润币种 */
    private String ccy;

    /** 分润金额 */
    private String profitSharingAmt;

    /** 分润时间 Unix微秒精度时间戳格式，例如：1759161600000000 */
    private String ts;
}
