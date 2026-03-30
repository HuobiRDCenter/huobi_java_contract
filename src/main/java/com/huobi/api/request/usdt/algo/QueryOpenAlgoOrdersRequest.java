package com.huobi.api.request.usdt.algo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询当前未触发策略委托请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryOpenAlgoOrdersRequest {

    /**
     * 交易对
     * 必填: 否
     */
    private String contractCode;

    /**
     * 策略委托单ID
     * 必填: 否
     */
    private String algoId;

    /**
     * 客户订单ID
     * 必填: 否
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
     * 查询的起始ID
     * 必填: 否
     * 默认从0开始
     */
    private Long from;

    /**
     * 分页页面大小
     * 必填: 否
     * 默认为10，最大为100
     */
    private Integer limit;

    /**
     * 翻页方向
     * 必填: 否
     * prev: 上一页
     * next: 下一页
     * 默认next
     */
    private String direct;
}
