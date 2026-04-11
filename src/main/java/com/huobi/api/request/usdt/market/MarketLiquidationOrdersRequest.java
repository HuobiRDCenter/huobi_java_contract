package com.huobi.api.request.usdt.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询强平订单请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketLiquidationOrdersRequest {
    /**
     * 合约代码，contract_code 和 pair 必须传一个
     */
    private String contractCode;

    /**
     * 交易对，contract_code 和 pair 必须传一个
     */
    private String pair;

    /**
     * 查询开始时间，UNIX时间戳（毫秒）
     * 默认值：当前时间 - 2小时
     */
    private Long startTime;

    /**
     * 查询结束时间，UNIX时间戳（毫秒）
     * 默认值：当前时间
     */
    private Long endTime;

    /**
     * 查询方向
     * prev：向前查询，数据按时间倒序排列
     * next：向后查询，数据按时间正序排列
     * 默认值：next
     */
    private String direct;

    /**
     * 查询起始ID
     * 如果是向前(prev)查询，则赋值为上一次查询结果中得到的最小id
     * 如果是向后(next)查询，则赋值为上一次查询结果中得到的最大id
     */
    private Long from;

    /**
     * 分页页面大小
     * 默认值：10
     * 最大值：100
     */
    private Integer limit;
}