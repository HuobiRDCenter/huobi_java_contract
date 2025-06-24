package com.huobi.api.request.usdt.copytrading;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Data
@Builder
public class CopytradingTraderProfitHistoryDetailsRequest {
    /**
     * 查询开始时间, 以数据按创建时间进行查询。毫秒时间戳
     * 必填: false
     * 取值范围: 查询窗口为最近90天
     */
    private Long startTime;

    /**
     * 查询结束时间, 以数据按创建时间进行查询。毫秒时间戳
     * 必填: false
     * 取值范围: 查询窗口为最近90天
     */
    private Long endTime;

    /**
     * 查询方向
     * 必填: false
     * 取值范围: prev表示向前查询，next表示向后查询
     * 默认值: now
     */
    private String direct;

    /**
     * 查询起始ID
     * 必填: false
     * 描述: 如果是向前(prev)查询，则赋值为上一次查询结果中得到的最小query_id；
     *       如果是向后(next)查询，则赋值为上一次查询结果中得到的最大query_id
     */
    private Long fromId;

    /**
     * 分页大小
     * 必填: false
     * 描述: 分页返回的结果集数量，最大为100，不填默认返回100条
     */
    private Long limit;
}
