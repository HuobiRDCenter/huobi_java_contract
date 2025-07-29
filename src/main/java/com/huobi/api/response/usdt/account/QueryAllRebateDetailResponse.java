package com.huobi.api.response.usdt.account;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QueryAllRebateDetailResponse {
    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public RebateData data;

    @SerializedName("ts")
    public long ts;

    @SerializedName("nextId")
    public String nextId;

    public class RebateData {

        @SerializedName("invitee_uid")
        public Integer inviteeUid;

        @SerializedName("invitee_type")
        public String inviteeType;

        @SerializedName("invitee_rebate_rate_spot_m2")
        public String inviteeRebateRateSpotM2;

        @SerializedName("invitee_rebate_rate_contract_m2")
        public String inviteeRebateRateContractM2;

        @SerializedName("invitee_rebate_rate_partner_spot")
        public String inviteeRebateRatePartnerSpot;

        @SerializedName("invitee_rebate_rate_partner_contract")
        public String inviteeRebateRatePartnerContract;

        @SerializedName("join_time_m2")
        public String joinTimeM2;

        @SerializedName("join_time_partner")
        public String joinTimePartner;

        @SerializedName("Invitee_total_commission_usdt")
        public String inviteeTotalCommissionUsdt;

        @SerializedName("Invitee_total_commission_trx")
        public String inviteeTotalCommissionTrx;

        @SerializedName("Invitee_total_commission_htx")
        public String inviteeTotalCommissionHtx;

        @SerializedName("partner_total_commission_usdt")
        public String partnerTotalCommissionUsdt;

        @SerializedName("partner_total_commission_trx")
        public String partnerTotalCommissionTrx;

        @SerializedName("partner_total_commission_htx")
        public String partnerTotalCommissionHtx;
    }
}
