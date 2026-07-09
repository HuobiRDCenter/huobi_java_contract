package com.huobi.api.response.usdt.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversalTransferRecordsResponse {

    private Integer code;

    private String msg;

    private Long ts;

    private List<DataBean> data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataBean {

        /**
         * 分页id
         */
        private Long id;

        /**
         * 划转id
         */
        @SerializedName("transfer_id")
        private Long transferId;

        /**
         * 金额
         */
        private BigDecimal amount;

        /**
         * 币种
         */
        private String currency;

        /**
         * 划转状态
         * "success":"成功" "pending":"处理中" "failed":"失败"
         */
        private String status;

        /**
         * 转出账户类型
         * "otc":"法币"
         * "spot":"现货"
         * "linear-swap":"u本位合约"
         * "futures":"币本位交割"
         * "swap":"币本位永续"
         * "margin":"逐仓杠杆"
         * "super-margin":"全仓杠杆"
         * "otc-options":"期权"
         */
        @SerializedName("from_account_type")
        private String fromAccountType;

        /**
         * 转入账户类型
         * "otc":"法币"
         * "spot":"现货"
         * "linear-swap":"u本位合约"
         * "futures":"币本位交割"
         * "swap":"币本位永续"
         * "margin":"逐仓杠杆"
         * "super-margin":"全仓杠杆"
         * "otc-options":"期权"
         */
        @SerializedName("to_account_type")
        private String toAccountType;

        /**
         * 转出资产类型
         */
        @SerializedName("from_asset_type")
        private String fromAssetType;

        /**
         * 转入资产类型
         */
        @SerializedName("to_asset_type")
        private String toAssetType;

        /**
         * 创建时间
         */
        private Long ts;
    }
}