package com.huobi.api.service.usdt.market;

import com.huobi.api.request.usdt.market.*;
import com.huobi.api.request.usdt.account.LinearSwapBasisRequest;
import com.huobi.api.request.usdt.account.SwapMarketHistoryKlineRequest;
import com.huobi.api.response.usdt.market.*;

public interface MarketAPIService {
    SwapMarketDepthResponse getSwapMarketDepth(String contractCode, String type);// 1.获取行情深度数据

    MarketBboResponse getMarketBbo(String contractCode,String businessType);// 2.获取市场最优挂单

    SwapMarketHistoryKlineResponse getSwapMarketHistoryKline(SwapMarketHistoryKlineRequest request);// 3.获取K线数据

    LinearSwapMarkPriceKlineResponse getLinearSwapMarkPriceKline(String contractCode, String period, Integer size);// 4.获取标记价格的 K 线数据

    SwapMarketDetailMergedResponse getSwapMarketDetailMerged(String contractCode); // 5.获取聚合行情

    BatchMergedV2Response getBatchMergedV2(String contractCode,String businessType);// 6.批量获取聚合行情（V2）

    SwapMarketTradeResponse getSwapMarketTrade(String contractCode, String businessType);// 7.获取市场最近成交记录

    SwapMarketHistoryTradeResponse getSwapMarketHistoryTrade(String contractCode, Integer size);// 8.批量获取最近的交易记录

    SwapHisOpenInterestResponse getSwapHisOpenInterest(String contractCode,String pair,String contractType, String period, Integer size, Integer amountType);// 9.平台持仓量的查询

    LinearSwapPremiumIndexKlineResponse getLinearSwapPremiumIndexKline(String contractCode, String period, Integer size);// 10.获取溢价指数K线数据

    LinearSwapEstimatedRateKlineResponse getLinearSwapEstimatedRateKline(String contractCode, String period, Integer size);// 11.获取预测资金费率的K线数据

    LinearSwapBasisResponse getLinearSwapBasis(LinearSwapBasisRequest request);// 12.获取基差数据

    MarketRiskLimitResponse marketRiskLimitResponse(MarketRiskLimitRequest request);

    AssetsDeductionCurrencyResponse assetsDeductionCurrencyResponse();

    MarketMultiAssetsMarginListResponse marketMultiAssetsMarginListResponse();

    /**
     * 查询当前资金费率
     * @param request 请求参数
     * @return 资金费率响应
     */
    MarketFundingRateResponse getFundingRate(MarketFundingRateRequest request);

    /**
     * 查询历史资金费率
     * @param request 请求参数
     * @return 历史资金费率响应
     */
    MarketFundingRateHistoryResponse getFundingRateHistory(MarketFundingRateHistoryRequest request);

    /**
     * 查询当前平台合约持仓总量
     * @param request 请求参数
     * @return 持仓总量响应
     */
    MarketOpenInterestResponse getOpenInterest(MarketOpenInterestRequest request);

    /**
     * 查询限价最高买价与最低卖价
     * @param request 请求参数
     * @return 价格限制响应
     */
    MarketPriceLimitResponse getPriceLimit(MarketPriceLimitRequest request);

    /**
     * 查询强平订单
     * @param request 请求参数
     * @return 强平订单响应
     */
    MarketLiquidationOrdersResponse getLiquidationOrders(MarketLiquidationOrdersRequest request);

    /**
     * 查询平台结算历史
     * @param request 请求参数
     * @return 结算历史响应
     */
    MarketSettlementHistoryResponse getSettlementHistory(MarketSettlementHistoryRequest request);

    /**
     * 查询精英账户多空持仓对比-账户数
     * @param request 请求参数
     * @return 精英账户多空持仓对比响应
     */
    MarketEliteAccountRatioResponse getEliteAccountRatio(MarketEliteAccountRatioRequest request);

    /**
     * 查询精英账户多空持仓对比-持仓数
     * @param request 请求参数
     * @return 精英账户多空持仓对比响应
     */
    MarketElitePositionRatioResponse getElitePositionRatio(MarketElitePositionRatioRequest request);

    /**
     * 查询预估结算价
     * @param request 请求参数
     * @return 预估结算价响应
     */
    MarketEstimatedSettlementPriceResponse getEstimatedSettlementPrice(MarketEstimatedSettlementPriceRequest request);
}
