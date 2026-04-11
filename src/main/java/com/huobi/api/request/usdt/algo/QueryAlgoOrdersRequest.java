package com.huobi.api.request.usdt.algo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询策略委托订单请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryAlgoOrdersRequest {

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
     * 订单类型
     * 必填: 是
     * tp：止盈
     * sl: 止损
     * tpsl: 止盈止损
     * trigger：计划委托
     * trailing_stop：跟踪委托
     */
    private String type;

    /**
     * 交易对
     * 必填: 可选
     */
    private String contractCode;
}