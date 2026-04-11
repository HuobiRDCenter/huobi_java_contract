package com.huobi.api.request.usdt.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询历史资金费率请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketFundingRateHistoryRequest {

    /**
     * 合约代码
     * 必填: 是
     * 永续："BTC-USDT"... ，交割："BTC-USDT-210625"...
     */
    private String contractCode;

    /**
     * 查询开始时间
     * 必填: 否
     * UNIX时间戳，以毫秒为单位
     * 取值范围 [((end-time) – 48h), (end-time)]
     */
    private Long startTime;

    /**
     * 查询结束时间
     * 必填: 否
     * UNIX时间戳，以毫秒为单位
     * 取值范围 [(present-90d), present]
     */
    private Long endTime;

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