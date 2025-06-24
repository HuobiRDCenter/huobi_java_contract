package com.huobi.api.request.usdt.copytrading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class CopytradingTraderQueryFollowersRequest {
    /**
     * 查询开始时间, 以数据按创建时间进行查询。毫秒时间戳
     * 查询窗口为最近90天
     */
    public Long startTime;

    /**
     * 查询结束时间, 以数据按创建时间进行查询。毫秒时间戳
     * 查询窗口为最近90天
     */
    public Long endTime;

    /**
     * 查询方向
     * prev表示向前查询，next表示向后查询
     * 默认值: now
     */
    public String direct;

    /**
     * 如果是向前(prev)查询，则赋值为上一次查询结果中得到的最小query_id
     * 如果是向后(next)查询，则赋值为上一次查询结果中得到的最大query_id
     */
    public Long fromId;

    /**
     * 分页返回的结果集数量
     * 最大为100，不填默认返回100条
     */
    public Long limit;

    /**
     * 如果需要数组字段可以这样定义
     * 示例字段(根据实际需要添加)
     */
    public List<String> exampleList;
}
