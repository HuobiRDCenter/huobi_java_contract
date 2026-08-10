package com.huobi.usdt.api;

import com.alibaba.fastjson.JSON;
import com.huobi.api.request.usdt.account.LinearSwapBasisRequest;
import com.huobi.api.request.usdt.account.SwapMarketHistoryKlineRequest;
import com.huobi.api.request.usdt.market.*;
import com.huobi.api.response.usdt.market.*;
import com.huobi.api.service.usdt.market.MarketAPIServiceImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class MarketAPITest implements BaseTest {
    MarketAPIServiceImpl huobiAPIService = new MarketAPIServiceImpl();

    @Test
    public void getSwapMarketDepth() {
        SwapMarketDepthResponse result =
                huobiAPIService.getSwapMarketDepth("btc-usdt", "step15");
        logger.debug("1.获取行情深度数据：{}", JSON.toJSONString(result));
    }

    @Test
    public void getMarketBbo(){
        MarketBboResponse response= huobiAPIService.getMarketBbo("","");
        logger.debug("2、获取市场最优挂单:{}",JSON.toJSONString(response));
    }

    @Test
    public void getSwapMarketHistoryKline() {
        SwapMarketHistoryKlineRequest result = SwapMarketHistoryKlineRequest.builder()
                .contractCode("BTC-USDT")
                .period("15min")
                .size(1)
                //.from()
                //.to()
                .build();
        SwapMarketHistoryKlineResponse response = huobiAPIService.getSwapMarketHistoryKline(result);
        logger.debug("3.获取K线数据：{}", JSON.toJSONString(response));
    }

    @Test
    public void getLinearSwapMarkPriceKline() {
        LinearSwapMarkPriceKlineResponse response = huobiAPIService.getLinearSwapMarkPriceKline("btc-usdt", "1min", 10);
        logger.debug("4、获取标记价格的K线数据:{}", JSON.toJSONString(response));
    }

    @Test
    public void getSwapMarketDetailMergedResponse() {
        SwapMarketDetailMergedResponse result =
                huobiAPIService.getSwapMarketDetailMerged("BTC-USDT");
        logger.debug("5.获取聚合行情：{}", JSON.toJSONString(result));
    }

    @Test
    public void getBatchMergedV2(){
        BatchMergedV2Response response = huobiAPIService.getBatchMergedV2(null, null);
        logger.debug("6、批量获取聚合行情（V2）:{}",JSON.toJSONString(response));
    }

    @Test
    public void getSwapMarketTradeResponse() {
        SwapMarketTradeResponse response = huobiAPIService.getSwapMarketTrade("BTC-USDT","");
        logger.debug("7.获取市场最近成交记录:{}", JSON.toJSONString(response));
    }

    @Test
    public void getSwapMarketHistoryTradeResponse() {
        SwapMarketHistoryTradeResponse response = huobiAPIService.getSwapMarketHistoryTrade("BTC-USDT", 100);
        logger.debug("8.批量获取最近的交易记录:{}", JSON.toJSONString(response));
    }

    @Test
    public void getSwapHisOpenInterestResponse() {
        SwapHisOpenInterestResponse response = huobiAPIService.getSwapHisOpenInterest("BTC-USDT", "", "", "60min",10,1);
        logger.debug("9.平台持仓量的查询:{}", JSON.toJSONString(response));
    }

    @Test
    public void getLinearSwapPremiumIndexKlineResponse() {
        LinearSwapPremiumIndexKlineResponse response =
                huobiAPIService.getLinearSwapPremiumIndexKline("BTC-USDT", "5min", 10);
        logger.debug("10.获取合约的溢价指数K线:{}", JSON.toJSONString(response));
    }

    @Test
    public void getLinearSwapEstimatedRateKlineResponse() {
        LinearSwapEstimatedRateKlineResponse response =
                huobiAPIService.getLinearSwapEstimatedRateKline("BTC-USDT", "5min", 10);
        logger.debug("11.获取实时预测资金费率的K线数据:{}", JSON.toJSONString(response));
    }

    @Test
    public void getLinearSwapBasisResponse() {
        LinearSwapBasisRequest request = LinearSwapBasisRequest.builder()
                .contractCode("BTC-USDT")
                .period("60min")
                .basisPriceType("open")
                .size(10)
                .build();
        LinearSwapBasisResponse response = huobiAPIService.getLinearSwapBasis(request);
        logger.debug("12.获取基差数据:{}", JSON.toJSONString(response));
    }

    @Test
    public void marketRiskLimitResponse() {
        MarketRiskLimitRequest request = MarketRiskLimitRequest.builder()
                .contractCode("BTC-USDT")
                .marginMode("cross")
                .build();
        MarketRiskLimitResponse response = huobiAPIService.marketRiskLimitResponse(request);
        logger.debug("v5.查询合约风险限额：{}", JSON.toJSONString(response));
    }

    @Test
    public void assetsDeductionCurrencyResponse() {
        AssetsDeductionCurrencyResponse response = huobiAPIService.assetsDeductionCurrencyResponse();
        logger.debug("v5.查询可抵扣手续费币种：{}", JSON.toJSONString(response));
    }

    @Test
    public void marketMultiAssetsMarginListResponse() {
        MarketMultiAssetsMarginListResponse response = huobiAPIService.marketMultiAssetsMarginListResponse();
        logger.debug("v5.查询联合保证金支持币种：{}", JSON.toJSONString(response));
    }

    @Test
    public void getFundingRate() {
        MarketFundingRateRequest request = MarketFundingRateRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketFundingRateResponse response = huobiAPIService.getFundingRate(request);
        logger.debug("v5.查询资金费率：{}", JSON.toJSONString(response));
    }

    @Test
    public void getFundingRateHistory() {
        MarketFundingRateHistoryRequest request = MarketFundingRateHistoryRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketFundingRateHistoryResponse response = huobiAPIService.getFundingRateHistory(request);
        logger.debug("v5.查询历史资金费率：{}", JSON.toJSONString(response));
    }

    @Test
    public void getOpenInterest() {
        MarketOpenInterestRequest request = MarketOpenInterestRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketOpenInterestResponse response = huobiAPIService.getOpenInterest(request);
        logger.debug("v5.查询合约总持仓量：{}", JSON.toJSONString(response));
    }

    @Test
    public void getPriceLimit() {
        MarketPriceLimitRequest request = MarketPriceLimitRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketPriceLimitResponse response = huobiAPIService.getPriceLimit(request);
        logger.debug("v5.查询限价：{}", JSON.toJSONString(response));
    }

    @Test
    public void getLiquidationOrders() {
        MarketLiquidationOrdersRequest request = MarketLiquidationOrdersRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketLiquidationOrdersResponse response = huobiAPIService.getLiquidationOrders(request);
        logger.debug("v5.查询强平订单：{}", JSON.toJSONString(response));
    }

    @Test
    public void getSettlementHistory() {
        MarketSettlementHistoryRequest request = MarketSettlementHistoryRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketSettlementHistoryResponse response = huobiAPIService.getSettlementHistory(request);
        logger.debug("v5.查询结算历史：{}", JSON.toJSONString(response));
    }

    @Test
    public void getEliteAccountRatio() {
        MarketEliteAccountRatioRequest request = MarketEliteAccountRatioRequest.builder()
                .contractCode("BTC-USDT")
                .period("5min")
                .build();
        MarketEliteAccountRatioResponse response = huobiAPIService.getEliteAccountRatio(request);
        logger.debug("v5.查询精英账户多空比：{}", JSON.toJSONString(response));
    }

    @Test
    public void getElitePositionRatio() {
        MarketElitePositionRatioRequest request = MarketElitePositionRatioRequest.builder()
                .contractCode("BTC-USDT")
                .period("5min")
                .build();
        MarketElitePositionRatioResponse response = huobiAPIService.getElitePositionRatio(request);
        logger.debug("v5.查询精英持仓多空比：{}", JSON.toJSONString(response));
    }

    @Test
    public void getEstimatedSettlementPrice() {
        MarketEstimatedSettlementPriceRequest request = MarketEstimatedSettlementPriceRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketEstimatedSettlementPriceResponse response = huobiAPIService.getEstimatedSettlementPrice(request);
        logger.debug("v5.查询预估结算价：{}", JSON.toJSONString(response));
    }
}
