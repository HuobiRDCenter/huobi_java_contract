package com.huobi.api.response.usdt.account;

import lombok.Data;

@Data
public class ReferralDetailVO {

    private Integer id;

    private Integer inviteUid;

    private String remark;

    private String referralCode;

    /**
     * 用户类型. 0: 直客(m2)，1: 我的合伙人(kn)，2：直客 + 我的合伙人
     */
    private Integer inviteeType;

    /**
     * m2的现货返佣比例
     */
    private String inviteeRebateRateSpotM2;

    /**
     * m2的合约返佣比例
     */
    private String inviteeRebateRateContractM2;

    /**
     * 合伙人现货返佣比例
     */
    private String inviteeRebateRatePartnerSpot;

    /**
     * 合伙人合约返佣比例
     */
    private String inviteeRebateRatePartnerContract;

    /**
     * 成为m2的时间
     */
    private Long joinTimeM2;

    /**
     * 成为多级时间
     */
    private Long joinTimePartner;

    private String spotTradingVolume;

    private String futuresTradingVolume;
}
