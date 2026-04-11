package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

/**
 * 参数	数据类型	是否必填	描述
 * id	String	 true	记录ID
 * instType	String	 true	产品类型 PERP 永续合约
 * followerAvatarLink	String	 true	跟随者头像链接地址
 * followerName	String	 true	跟随者昵称
 * followerUid	String	 true	跟随者用户id
 * followTime	String	 true	跟随日期 Unix时间戳的微秒数格式，如 1597026383085000
 * followerAssetAmt	String	 true	跟单者资产金额，单位为USDT
 * followerProfitSharingAmt	String	 true	跟单收益金额，单位为 USDT
 * followerTradeAmt	String	 true	跟随者交易额，单位为 USDT
 * totalProfitSharingAmt	String	 true	总分润金额，单位为 USDT
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowersResult {

    /** 记录ID */
    private String id;

    /** 产品类型 PERP 永续合约 */
    private String instType;

    /** 跟随者头像链接地址 */
    private String followerAvatarLink;

    /** 跟随者昵称 */
    private String followerName;

    /** 跟随者用户id */
    private String followerUid;

    /** 跟随日期 Unix时间戳的微秒数格式，如 1597026383085000 */
    private String followTime;

    /** 跟单者资产金额，单位为USDT */
    private String followerAssetAmt;

    /** 跟单收益金额，单位为 USDT */
    private String followerProfitSharingAmt;

    /** 跟随者交易额，单位为 USDT */
    private String followerTradeAmt;

    /** 总分润金额，单位为 USDT */
    private String totalProfitSharingAmt;
}
