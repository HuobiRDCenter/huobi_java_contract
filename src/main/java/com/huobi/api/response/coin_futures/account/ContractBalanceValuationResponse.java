package com.huobi.api.response.coin_futures.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ContractBalanceValuationResponse {
    private String status;
    private Long ts ;
    private List<DataBean> data;

    @Data
    @Builder
    @AllArgsConstructor
    public static class DataBean{
        @SerializedName("valuation_asset")
        private String valuationAsset;
        private String balance;
    }
}
