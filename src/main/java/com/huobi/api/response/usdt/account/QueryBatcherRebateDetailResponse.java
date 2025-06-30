package com.huobi.api.response.usdt.account;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import java.util.List;

@Data
@AllArgsConstructor
public class QueryBatcherRebateDetailResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private RebateDetail data;

    @SerializedName("ts")
    private long ts;

    public static class RebateDetail {

        @SerializedName("invitee_uid")
        private Integer inviteeUid;

        @SerializedName("invitee_type")
        private String inviteeType;

        @SerializedName("invitee_rebate_rate_spot_m2")
        private String inviteeRebateRateSpotM2;

        @SerializedName("invitee_rebate_rate_contract_m2")
        private String inviteeRebateRateContractM2;

        @SerializedName("invitee_rebate_rate_partner_spot")
        private String inviteeRebateRatePartnerSpot;

        @SerializedName("invitee_rebate_rate_partner_contract")
        private String inviteeRebateRatePartnerContract;

        @SerializedName("join_time_m2")
        private String joinTimeM2;

        @SerializedName("join_time_partner")
        private String joinTimePartner;

        @SerializedName("Invitee_total_commission_usdt")
        private String inviteeTotalCommissionUsdt;

        @SerializedName("Invitee_total_commission_trx")
        private String inviteeTotalCommissionTrx;

        @SerializedName("Invitee_total_commission_htx")
        private String inviteeTotalCommissionHtx;

        @SerializedName("partner_total_commission_usdt")
        private String partnerTotalCommissionUsdt;

        @SerializedName("partner_total_commission_trx")
        private String partnerTotalCommissionTrx;

        @SerializedName("partner_total_commission_htx")
        private String partnerTotalCommissionHtx;
    }
}
