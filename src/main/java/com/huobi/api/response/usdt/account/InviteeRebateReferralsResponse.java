package com.huobi.api.response.usdt.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteeRebateReferralsResponse {

    private Integer code;

    private String message;

    private Long ts;

    private ReferralData data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReferralData {

        private Long nextId;

        private List<ReferralDetailVO> rebateDetailList;
    }
}