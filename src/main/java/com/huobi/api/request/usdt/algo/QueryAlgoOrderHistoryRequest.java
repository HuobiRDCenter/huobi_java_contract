package com.huobi.api.request.usdt.algo;

// 请求参数类
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 查询历史策略委托单请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryAlgoOrderHistoryRequest {

    /**
     * 合约代码
     * 必填: 否
     * 示例: "BTC-USDT"
     */
    private String contractCode;

    /**
     * 保证金模式
     * 必填: 否
     * cross：全仓
     * isolated: 逐仓
     */
    private String marginMode;

    /**
     * 订单状态
     * 必填: 否
     * 可以查多个状态，使用逗号分隔
     * effective：完全触发
     * canceled：完全取消
     * failed: 失败
     */
    private List<String> states;

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