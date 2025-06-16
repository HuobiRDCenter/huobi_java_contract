package com.huobi.api.request.usdt.copytrading;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Data
@Builder
public class CopytradingTraderPositionPositionsRequest {
    /**
     * 交易对，例如：BTC-USDT
     * 必填: true
     */
    private String contractCode;

    /**
     * 查询开始时间（毫秒时间戳）
     * 必填: false
     * 说明: 查询窗口为最近90天
     */
    private Long startTime;

    /**
     * 查询结束时间（毫秒时间戳）
     * 必填: false
     * 说明: 查询窗口为最近90天
     */
    private Long endTime;

    /**
     * 查询方向
     * 必填: false
     * 取值: prev(向前查询，时间倒序), next(向后查询，时间正序)
     */

    private String direct;

    /**
     * 查询起始ID
     * 必填: false
     * 说明: prev查询使用上次最小query_id，next查询使用上次最大query_id
     */

    private Long fromId;


    private Long limit = 100L;
}

