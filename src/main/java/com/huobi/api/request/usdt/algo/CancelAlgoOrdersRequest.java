package com.huobi.api.request.usdt.algo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 撤销策略委托订单请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelAlgoOrdersRequest {

    /**
     * 策略委托单ID
     * 必填: 可选
     * 备注: algoId和algoClientOrderId必须传一个，若两个都传，以algoId为准
     */
    private String algoId;

    /**
     * 客户订单ID
     * 必填: 可选
     * 备注: algoId和algoClientOrderId必须传一个，若两个都传，以algoId为准
     */
    private String algoClientOrderId;

    /**
     * 交易对
     * 必填: 是
     */
    private String contractCode;
}
