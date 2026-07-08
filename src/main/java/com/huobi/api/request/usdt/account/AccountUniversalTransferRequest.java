package com.huobi.api.request.usdt.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUniversalTransferRequest {
    /**
     * 币种
     */
    private String currency;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 转出账户类型
     */
    private String from_account_type;

    /**
     * 转入账户类型
     */
    private String to_account_type;

    /**
     * 转出资产类型（可选）
     */
    private String from_asset_type;

    /**
     * 转入资产类型（可选）
     */
    private String to_asset_type;
}
