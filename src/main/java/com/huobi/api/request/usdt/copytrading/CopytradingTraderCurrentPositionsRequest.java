package com.huobi.api.request.usdt.copytrading;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Data
@Builder
public class CopytradingTraderCurrentPositionsRequest {
    /**
     * 交易对，例如：BTC-USDT
     * 必填: true
     */
    private String contractCode;

    /**
     * 查询开始时间(毫秒时间戳)
     * 必填: false
     * 说明: 查询窗口为最近90天
     */
    private Long startTime;

    /**
     * 查询结束时间(毫秒时间戳)
     * 必填: false
     * 说明: 查询窗口为最近90天
     */
    private Long endTime;

    /**
     * 查询方向
     * 必填: false
     * 取值范围: prev(向前查询), next(向后查询)
     * prev表示数据按时间倒序返回，next表示数据按时间正序返回
     */
    private String direct;

    /**
     * 查询起始ID
     * 必填: false
     * 说明: 向前查询时使用上一次查询的最小query_id，向后查询时使用上一次查询的最大query_id
     */
    private Long fromId;

    /**
     * 分页大小
     * 必填: false
     * 取值范围: 最大100
     * 默认值: 100
     */
    private Long limit = 100L;
}
