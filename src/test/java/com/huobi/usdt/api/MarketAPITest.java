package com.huobi.usdt.api;

import com.alibaba.fastjson.JSON;
import com.huobi.TestKeys;
import com.huobi.api.request.usdt.account.LinearSwapBasisRequest;
import com.huobi.api.request.usdt.account.SwapMarketHistoryKlineRequest;
import com.huobi.api.request.usdt.market.*;
import com.huobi.api.response.usdt.market.*;
import com.huobi.api.service.usdt.market.MarketAPIServiceImpl;
import org.junit.Assert;
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
        Assert.assertEquals("查询合约风险限额失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        MarketRiskLimitResponse.DataBean d = response.getData().get(0);
        Assert.assertNotNull("contract_code 为空", d.getContractCode());
        Assert.assertNotNull("margin_mode 为空", d.getMarginMode());
        Assert.assertNotNull("tier 为空", d.getTier());
        Assert.assertNotNull("max_lever 为空", d.getMaxLever());
        Assert.assertNotNull("maintenance_margin_rate 为空", d.getMaintenanceMarginRate());
        Assert.assertNotNull("max_volume 为空", d.getMaxVolume());
        Assert.assertNotNull("min_volume 为空", d.getMinVolume());
        Assert.assertNotNull("volume_unit 为空", d.getVolumeUnit());
    }

    @Test
    public void assetsDeductionCurrencyResponse() {
        AssetsDeductionCurrencyResponse response = huobiAPIService.assetsDeductionCurrencyResponse();
        logger.debug("v5.查询可抵扣手续费币种：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询可抵扣手续费币种失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        Assert.assertNotNull("currency 为空", response.getData().getCurrency());
    }

    @Test
    public void marketMultiAssetsMarginListResponse() {
        MarketMultiAssetsMarginListResponse response = huobiAPIService.marketMultiAssetsMarginListResponse();
        logger.debug("v5.查询联合保证金支持币种：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询联合保证金支持币种失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        Assert.assertNotNull("multi_assets 为空", response.getData().getMultiAssets());
    }

    @Test
    public void getFundingRate() {
        MarketFundingRateRequest request = MarketFundingRateRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketFundingRateResponse response = huobiAPIService.getFundingRate(request);
        logger.debug("v5.查询资金费率：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询资金费率失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        MarketFundingRateResponse.DataBean d = response.getData().get(0);
        Assert.assertNotNull("contract_code 为空", d.getContractCode());
        Assert.assertNotNull("funding_rate 为空", d.getFundingRate());
        Assert.assertNotNull("funding_time 为空", d.getFundingTime());
        Assert.assertNotNull("next_funding_time 为空", d.getNextFundingTime());
        Assert.assertNotNull("min_funding_rate 为空", d.getMinFundingRate());
        Assert.assertNotNull("max_funding_rate 为空", d.getMaxFundingRate());
    }

    @Test
    public void getFundingRateHistory() {
        MarketFundingRateHistoryRequest request = MarketFundingRateHistoryRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketFundingRateHistoryResponse response = huobiAPIService.getFundingRateHistory(request);
        logger.debug("v5.查询历史资金费率：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询历史资金费率失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        MarketFundingRateHistoryResponse.FundingRateHistoryData d = response.getData().get(0);
        Assert.assertNotNull("id 为空", d.getId());
        Assert.assertNotNull("contract_code 为空", d.getContractCode());
        Assert.assertNotNull("funding_rate 为空", d.getFundingRate());
        Assert.assertNotNull("funding_time 为空", d.getFundingTime());
    }

    @Test
    public void getOpenInterest() {
        MarketOpenInterestRequest request = MarketOpenInterestRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketOpenInterestResponse response = huobiAPIService.getOpenInterest(request);
        logger.debug("v5.查询合约总持仓量：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询合约总持仓量失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        MarketOpenInterestResponse.OpenInterestData d = response.getData();
        Assert.assertNotNull("contract_code 为空", d.getContractCode());
        Assert.assertNotNull("amount 为空", d.getAmount());
        Assert.assertNotNull("volume 为空", d.getVolume());
        Assert.assertNotNull("value 为空", d.getValue());
        Assert.assertNotNull("trade_amount 为空", d.getTradeAmount());
        Assert.assertNotNull("trade_volume 为空", d.getTradeVolume());
        Assert.assertNotNull("trade_turnover 为空", d.getTradeTurnover());
    }

    @Test
    public void getPriceLimit() {
        MarketPriceLimitRequest request = MarketPriceLimitRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketPriceLimitResponse response = huobiAPIService.getPriceLimit(request);
        logger.debug("v5.查询限价：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询限价失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        MarketPriceLimitResponse.PriceLimitData d = response.getData().get(0);
        Assert.assertNotNull("contract_code 为空", d.getContractCode());
        Assert.assertNotNull("high_limit 为空", d.getHighLimit());
        Assert.assertNotNull("low_limit 为空", d.getLowLimit());
    }

    @Test
    public void getLiquidationOrders() {
        MarketLiquidationOrdersRequest request = MarketLiquidationOrdersRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketLiquidationOrdersResponse response = huobiAPIService.getLiquidationOrders(request);
        logger.debug("v5.查询强平订单：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询强平订单失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        // 强平订单可能无数据，data 为 null 时不算失败
        if (response.getData() != null && !response.getData().isEmpty()) {
            MarketLiquidationOrdersResponse.LiquidationOrderData d = response.getData().get(0);
            Assert.assertNotNull("id 为空", d.getId());
            Assert.assertNotNull("contract_code 为空", d.getContractCode());
            Assert.assertNotNull("liquidation_time 为空", d.getLiquidationTime());
            Assert.assertNotNull("side 为空", d.getSide());
            Assert.assertNotNull("position_side 为空", d.getPositionSide());
            Assert.assertNotNull("volume 为空", d.getVolume());
            Assert.assertNotNull("amount 为空", d.getAmount());
            Assert.assertNotNull("bankrupt_price 为空", d.getBankruptPrice());
            Assert.assertNotNull("trade_turnover 为空", d.getTradeTurnover());
        }
    }

    @Test
    public void getSettlementHistory() {
        MarketSettlementHistoryRequest request = MarketSettlementHistoryRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketSettlementHistoryResponse response = huobiAPIService.getSettlementHistory(request);
        logger.debug("v5.查询结算历史：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询结算历史失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        if (response.getData() != null && !response.getData().isEmpty()) {
            MarketSettlementHistoryResponse.SettlementHistoryData d = response.getData().get(0);
            Assert.assertNotNull("id 为空", d.getId());
            Assert.assertNotNull("contract_code 为空", d.getContractCode());
            Assert.assertNotNull("settlement_time 为空", d.getSettlementTime());
            Assert.assertNotNull("clawback_ratio 为空", d.getClawbackRatio());
            Assert.assertNotNull("settlement_price 为空", d.getSettlementPrice());
        }
    }

    @Test
    public void getEliteAccountRatio() {
        MarketEliteAccountRatioRequest request = MarketEliteAccountRatioRequest.builder()
                .contractCode("BTC-USDT")
                .period("5min")
                .build();
        MarketEliteAccountRatioResponse response = huobiAPIService.getEliteAccountRatio(request);
        logger.debug("v5.查询精英账户多空比：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询精英账户多空比失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        if (response.getData() != null && !response.getData().isEmpty()) {
            MarketEliteAccountRatioResponse.EliteAccountRatioData d = response.getData().get(0);
            Assert.assertNotNull("contract_code 为空", d.getContractCode());
            Assert.assertNotNull("buy_ratio 为空", d.getBuyRatio());
            Assert.assertNotNull("sell_ratio 为空", d.getSellRatio());
            Assert.assertNotNull("ts 为空", d.getTs());
        }
    }

    @Test
    public void getElitePositionRatio() {
        MarketElitePositionRatioRequest request = MarketElitePositionRatioRequest.builder()
                .contractCode("BTC-USDT")
                .period("5min")
                .build();
        MarketElitePositionRatioResponse response = huobiAPIService.getElitePositionRatio(request);
        logger.debug("v5.查询精英持仓多空比：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询精英持仓多空比失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        if (response.getData() != null && !response.getData().isEmpty()) {
            MarketElitePositionRatioResponse.ElitePositionRatioData d = response.getData().get(0);
            Assert.assertNotNull("contract_code 为空", d.getContractCode());
            Assert.assertNotNull("buy_ratio 为空", d.getBuyRatio());
            Assert.assertNotNull("sell_ratio 为空", d.getSellRatio());
            Assert.assertNotNull("ts 为空", d.getTs());
        }
    }

    @Test
    public void getEstimatedSettlementPrice() {
        MarketEstimatedSettlementPriceRequest request = MarketEstimatedSettlementPriceRequest.builder()
                .contractCode("BTC-USDT")
                .build();
        MarketEstimatedSettlementPriceResponse response = huobiAPIService.getEstimatedSettlementPrice(request);
        logger.debug("v5.查询预估结算价：{}", JSON.toJSONString(response));
        Assert.assertEquals("查询预估结算价失败: " + JSON.toJSONString(response),
                Integer.valueOf(200), response.getCode());
        Assert.assertNotNull("data 为空", response.getData());
        MarketEstimatedSettlementPriceResponse.EstimatedSettlementPriceData d = response.getData().get(0);
        Assert.assertNotNull("contract_code 为空", d.getContractCode());
        Assert.assertNotNull("settlement_type 为空", d.getSettlementType());
        Assert.assertNotNull("estimated_settlement_price 为空", d.getEstimatedSettlementPrice());
    }
}
