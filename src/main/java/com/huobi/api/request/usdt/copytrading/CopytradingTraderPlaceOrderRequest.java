package com.huobi.api.request.usdt.copytrading;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Data
@Builder
public class CopytradingTraderPlaceOrderRequest {
    private String contractCode;      // 交易对(必填)
    private String price;             // 委托价格
    private String amount;            // 委托数量(必填)
    private String marginMode;        // 保证金模式(必填): isolated(逐仓), cross(全仓)
    private Integer orderPriceType;    // 下单价格类型(必填): 1-限价,2-限价-对手价,6-限价-最优20档,8-fok,13-fok-对手价,16-最优20档-FOK,17-市价单
    private Integer orderDirection;    // 买卖方向(必填): 1-buy,2-sell
    private Integer lever;            // 杠杆倍数(必填)
    private String tpTriggerPrice;    // 止盈价格
    private String slTriggerPrice;    // 止损价格
}
