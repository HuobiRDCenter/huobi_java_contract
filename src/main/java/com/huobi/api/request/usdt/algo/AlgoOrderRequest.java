package com.huobi.api.request.usdt.algo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 算法订单请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlgoOrderRequest {

    /**
     * 合约代码
     * 必填: true
     */
    private String contractCode;

    /**
     * 订单类型
     * 必填: true
     * 取值: tp(止盈), sl(止损), tpsl(止盈止损), trigger(计划委托), trailing_stop(跟踪委托)
     */
    private String type;

    /**
     * 仓位方向
     * 必填: true
     * 取值: long(多), short(空), both(单向持仓)
     */
    private String positionSide;

    /**
     * 订单方向
     * 必填: true
     * 取值: buy(买), sell(卖)
     */
    private String side;

    /**
     * 客户端策略订单ID
     * 必填: false
     */
    private String algoClientOrderId;

    /**
     * 保证金模式
     * 必填: true
     * 取值: isolated(逐仓), cross(全仓)
     */
    private String marginMode;

    /**
     * 委托张数
     * 必填: false
     */
    private String volume;

    /**
     * 止盈触发价格
     * 必填: false
     * 适用于: 止盈/止盈止损委托
     */
    private String tpTriggerPrice;

    /**
     * 止盈委托价格
     * 必填: false
     * 适用于: 止盈/止盈止损委托
     * 备注: 最优N档委托类型时无需填写价格
     */
    private String tpOrderPrice;

    /**
     * 止盈委托类型
     * 必填: false
     * 取值: market(市价), limit(限价), optimal_5(最优5档),
     *      optimal_10(最优10档), optimal_20(最优20档)
     * 默认值: market
     * 适用于: 止盈/止盈止损委托
     */
    private String tpType;

    /**
     * 止盈价格触发类型
     * 必填: false
     * 取值: last(最新价), mark(标记价格)
     * 默认值: last
     * 适用于: 止盈/止盈止损委托
     */
    private String tpTriggerPriceType;

    /**
     * 止损触发价格
     * 必填: false
     * 适用于: 止损/止盈止损委托
     */
    private String slTriggerPrice;

    /**
     * 止损委托价格
     * 必填: false
     * 适用于: 止损/止盈止损委托
     * 备注: 最优N档委托类型时无需填写价格
     */
    private String slOrderPrice;

    /**
     * 止损委托类型
     * 必填: false
     * 取值: market(市价), limit(限价), optimal_5(最优5档),
     *      optimal_10(最优10档), optimal_20(最优20档)
     * 默认值: market
     * 适用于: 止损/止盈止损委托
     */
    private String slType;

    /**
     * 止损价格触发类型
     * 必填: false
     * 取值: last(最新价), mark(标记价格)
     * 默认值: last
     * 适用于: 止损/止盈止损委托
     */
    private String slTriggerPriceType;

    /**
     * 委托价格
     * 必填: false
     * 适用于: 计划委托
     */
    private String price;

    /**
     * 最优档位
     * 必填: false
     * 取值: opponent(对手价), optimal_5(最优5档),
     *      optimal_10(最优10档), optimal_20(最优20档)
     * 适用于: 计划委托
     */
    private String priceMatch;

    /**
     * 计划委托触发价格
     * 必填: false
     * 适用于: 计划委托
     */
    private String triggerPrice;

    /**
     * 计划委托触发价格类型
     * 必填: false
     * 取值: last(最新价)
     * 默认值: last
     * 适用于: 计划委托
     */
    private String triggerPriceType;

    /**
     * 激活价格
     * 必填: false
     * 备注: 如果不填写激活价格，即下单后就被激活
     * 适用于: 跟踪委托
     */
    private String activePrice;

    /**
     * 最优档位
     * 必填: false
     * 取值: optimal_5(最优5档), optimal_10(最优10档),
     *      optimal_20(最优20档), formula_price(理论价格)
     * 适用于: 跟踪委托
     */
    private String orderPriceType;

    /**
     * 回调幅度的比例
     * 必填: false
     * 示例: "0.05"代表"5%"
     * 适用于: 跟踪委托
     */
    private String callbackRate;

    /**
     * 只减仓
     * 必填: false
     * 适用于: 跟踪委托
     */
    private Boolean reduceOnly;
}
