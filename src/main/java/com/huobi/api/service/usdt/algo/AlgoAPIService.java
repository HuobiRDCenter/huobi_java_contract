package com.huobi.api.service.usdt.algo;

import com.huobi.api.request.usdt.algo.*;
import com.huobi.api.response.usdt.algo.*;

public interface AlgoAPIService {

    /**
     * 创建策略委托订单
     */
    AlgoOrderResponse algoOrder(AlgoOrderRequest request);

    /**
     * 撤销单个策略委托订单
     *
     * @param request 撤销请求参数
     * @return 撤销结果
     */
    CancelAlgoOrdersResponse cancelAlgoOrder(CancelAlgoOrdersRequest request);

    /**
     * 查询策略委托订单
     *
     * @param request 查询请求参数
     * @return 查询结果
     */
    QueryAlgoOrdersResponse queryAlgoOrder(QueryAlgoOrdersRequest request);

    /**
     * 查询当前未触发的策略委托
     *
     * @param request 查询请求参数
     * @return 查询结果
     */
    QueryOpenAlgoOrdersResponse queryOpenAlgoOrders(QueryOpenAlgoOrdersRequest request);

    /**
     * 查询历史策略委托单
     *
     * @param request 查询请求参数
     * @return 查询结果
     */
    QueryAlgoOrderHistoryResponse queryAlgoOrderHistory(QueryAlgoOrderHistoryRequest request);
}
