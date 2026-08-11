package com.huobi.usdt.api;

import com.alibaba.fastjson.JSON;
import com.huobi.TestKeys;
import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;
import com.huobi.api.request.usdt.trade.*;
import com.huobi.api.response.usdt.trade.*;
import com.huobi.api.service.usdt.trade.TradeAPIServiceImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.JVM)
public class TradeAPITest implements BaseTest {

    TradeAPIServiceImpl huobiAPIService = new TradeAPIServiceImpl(TestKeys.ACCESS_KEY, TestKeys.SECRET_KEY);

    @Test
    public void linearCancelAfterResponse(){
        LinearCancelAfterRequest request = LinearCancelAfterRequest.builder()
                .onOff(1)
                .build();
        LinearCancelAfterResponse response = huobiAPIService.linearCancelAfterResponse(request);
        logger.debug("1.【通用】自动撤单：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapSwitchPositionMode(){
        SwapSwitchPositionModeResponse response=huobiAPIService.swapSwitchPositionModeResponse("btc-usdt","dual_side");
        logger.debug("2.切换持仓模式:{}", JSON.toJSONString(response));
    }

    @Test
    public void swapOrderRequest() {
        SwapOrderRequest request = SwapOrderRequest.builder()
                .contractCode("xrp-usdt")
                .volume(1l)
                .price(BigDecimal.valueOf(0.2))
                .direction(DirectionEnum.BUY)
                .offset(OffsetEnum.OPEN)
                .leverRate(10)
                .orderPriceType("post_only")
                .tpTriggerPrice(BigDecimal.valueOf(0.5))
                .tpOrderPrice(BigDecimal.valueOf(0.5))
                .tpOrderPriceType("limit")
                .slTriggerPrice(BigDecimal.valueOf(0.1))
                .slOrderPrice(BigDecimal.valueOf(0.1))
                .slOrderPriceType("limit")
                .reduceOnly(1)
                .build();
        SwapOrderResponse response =
                huobiAPIService.swapOrderRequest(request);
        logger.debug("3.合约下单：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapBatchorderRequest() {
        List<SwapOrderRequest> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SwapOrderRequest request = SwapOrderRequest.builder()
                    .contractCode("ETH-USDT")
                    .volume(100l)
                    .price(BigDecimal.valueOf(456))
                    .direction(DirectionEnum.SELL)
                    .offset(OffsetEnum.OPEN)
                    .leverRate(5)
                    .orderPriceType("limit")
                    .build();
            list.add(request);
        }
        SwapBatchorderRequest request = SwapBatchorderRequest.builder()
                .list(list)
                .build();
        SwapBatchorderResponse response =
                huobiAPIService.swapBatchorderRequest(request);
        logger.debug("4.合约批量下单：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapCancelRequest() {
        SwapCancelRequest request = SwapCancelRequest.builder()
                .contractCode("eth-usdt")
                .orderId("759098746146705408")
                //.clientOrderId("")
                .build();
        SwapCancelResponse response =
                huobiAPIService.swapCancelRequest(request);
        logger.debug("5.撤销订单：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapCancelallRequest() {
        SwapCancelallRequest request = SwapCancelallRequest.builder()
                .contractCode("eth-usdt")
                .build();
        SwapCancelallResponse response =
                huobiAPIService.swapCancelallRequest(request);
        logger.debug("6.全部撤单：{}", JSON.toJSONString(response));
    }

    @Test
    public void getSwapSwitchLeverRateResponse() {
        SwapSwitchLeverRateResponse response = huobiAPIService.getSwapSwitchLeverRate("BTC-USDT", 10);
        logger.debug("7.切换杠杆：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapOrderInfoRequest() {
        SwapOrderInfoRequest request = SwapOrderInfoRequest.builder()
                .contractCode("BTC-USDT")
                //.clientOrderId("")
                .orderId("758644298199887872")
                .build();
        SwapOrderInfoResponse response =
                huobiAPIService.swapOrderInfoRequest(request);
        logger.debug("8.获取合约订单信息：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapOrderDetailRequest() {
        SwapOrderDetailRequest request = SwapOrderDetailRequest.builder()
                .contractCode("BTC-usdt")
                .orderId(758644298199887872l)
                // .createdAt(System.currentTimeMillis())
                //.orderType(1)
                //.pageIndex(1)
                //.pageSize(20)
                .build();
        SwapOrderDetailResponse response =
                huobiAPIService.swapOrderDetailRequest(request);
        logger.debug("9.获取订单明细信息：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapOpenordersRequest() {
        SwapOpenordersRequest request = SwapOpenordersRequest.builder()
                .contractCode("btc-usdt")
                .pageIndex(1)
                .pageSize(20)
                .build();
        SwapOpenordersResponse response =
                huobiAPIService.swapOpenordersRequest(request);
        logger.debug("10.获取合约当前未成交委托：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapHisordersV3Response(){
        SwapHisordersV3Request request = SwapHisordersV3Request.builder()
                .tradeType(0)
                .type(1)
                .status("0")
                .build();
        SwapHisordersV3Response response = huobiAPIService.swapHisordersV3Response(request);
        logger.debug("11.【逐仓】获取合约历史委托(新)：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapHisordersExactV3Response(){
        SwapHisordersExactV3Request request = SwapHisordersExactV3Request.builder()
                .tradeType(0)
                .type(1)
                .status("0")
                .build();
        SwapHisordersExactV3Response response = huobiAPIService.swapHisordersExactV3Response(request);
        logger.debug("12.【逐仓】组合查询合约历史委托(新)：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapMatchResultsV3Response(){
        SwapMatchResultsV3Request request = SwapMatchResultsV3Request.builder()
                .tradeType(0)
                .build();
        SwapMatchResultsV3Response response = huobiAPIService.swapMatchResultsV3Response(request);
        logger.debug("13.【逐仓】获取历史成交记录(新)：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapMatchResultsExactV3Response(){
        SwapMatchResultsExactV3Request request = SwapMatchResultsExactV3Request.builder()
                .contract("BTC-USDT")
                .tradeType(0)
                .build();
        SwapMatchResultsExactV3Response response = huobiAPIService.swapMatchResultsExactV3Response(request);
        logger.debug("14.【逐仓】组合查询用户历史成交记录(新)：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapLightningClosePositionRequest() {
        SwapLightningClosePositionRequest request = SwapLightningClosePositionRequest.builder()
                .contractCode("ETH-USDT")
                .direction("sell")
                .build();
        SwapLightningClosePositionResponse response =
                huobiAPIService.swapLightningClosePositionRequest(request);
        logger.debug("15.闪电平仓下单：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapPositionSideResponse(){
        SwapPositionSideRequest request = SwapPositionSideRequest.builder()
                .marginAccount("BTC-USDT")
                .build();
        SwapPositionSideResponse response = huobiAPIService.swapPositionSideResponse(request);
        logger.debug("16.【逐仓】查询持仓模式：{}", JSON.toJSONString(response));
    }

    @Test
    public void tradeOrderResponse() {
        try {
            TradeOrderRequest request = TradeOrderRequest.builder()
                    .contractCode("BTC-USDT")
                    .marginMode("cross")
                    .side("buy")
                    .type("limit")
                    .price("10000")
                    .volume("1")
                    .build();
            TradeOrderResponse response = huobiAPIService.tradeOrderResponse(request);
            logger.debug("v5.合约下单：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.合约下单(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradeBachOrder() {
        try {
            TradeBachOrderRequest request = TradeBachOrderRequest.builder()
                    .contractCode("BTC-USDT")
                    .marginMode("cross")
                    .build();
            TradeBachOrderResponse response = huobiAPIService.tradeBachOrder(request);
            logger.debug("v5.合约批量下单：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.合约批量下单(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void cancelTradeOrder() {
        try {
            CancelTradeOrderRequest request = CancelTradeOrderRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            CancelTradeOrderResponse response = huobiAPIService.cancelTradeOrder(request);
            logger.debug("v5.撤单：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.撤单(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void cannelTradeBatchOrderResponse() {
        try {
            CannelTradeBatchOrderRequest request = CannelTradeBatchOrderRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            CannelTradeBatchOrderResponse response = huobiAPIService.cannelTradeBatchOrderResponse(request);
            logger.debug("v5.批量撤单：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.批量撤单(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void cannelTradeAllOrderResponse() {
        try {
            CannelTradeAllOrderRequest request = CannelTradeAllOrderRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            CannelTradeAllOrderResponse response = huobiAPIService.cannelTradeAllOrderResponse(request);
            logger.debug("v5.全部撤单：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.全部撤单(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradePositionResponse() {
        try {
            TradePositionRequest request = TradePositionRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            TradePositionResponse response = huobiAPIService.tradePositionResponse(request);
            logger.debug("v5.市价全平：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.市价全平(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradePositionAllResponse() {
        try {
            TradePositionAllResponse response = huobiAPIService.tradePositionAllResponse();
            logger.debug("v5.一键全平：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.一键全平(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradeOrderOpensResponse() {
        try {
            TradeOrderOpensRequest request = TradeOrderOpensRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            TradeOrderOpensResponse response = huobiAPIService.tradeOrderOpensResponse(request);
            logger.debug("v5.查询当前委托：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询当前委托(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradeOrderTradesResponse() {
        try {
            TradeOrderTradesRequest request = TradeOrderTradesRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            TradeOrderTradesResponse response = huobiAPIService.tradeOrderTradesResponse(request);
            logger.debug("v5.查询成交明细：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询成交明细(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradeOrderHistoryResponse() {
        try {
            TradeOrderHistoryRequest request = TradeOrderHistoryRequest.builder()
                    .contractCode("BTC-USDT")
                    .states("filled")
                    .build();
            TradeOrderHistoryResponse response = huobiAPIService.tradeOrderHistoryResponse(request);
            logger.debug("v5.查询历史委托：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询历史委托(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void getTradeOrderResponse() {
        try {
            GetTradeOrderRequest request = GetTradeOrderRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            GetTradeOrderResponse response = huobiAPIService.getTradeOrderResponse(request);
            logger.debug("v5.查询订单信息：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询订单信息(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradeCancelAfterResponse() {
        try {
            TradeCancelAfterRequest request = TradeCancelAfterRequest.builder()
                    .build();
            TradeCancelAfterResponse response = huobiAPIService.tradeCancelAfterResponse(request);
            logger.debug("v5.自动撤单：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.自动撤单(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void tradePositionOpensResponse() {
        try {
            TradePositionOpensRequest request = TradePositionOpensRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            TradePositionOpensResponse response = huobiAPIService.tradePositionOpensResponse(request);
            logger.debug("v5.查询当前持仓：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询当前持仓(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void positionLeverResponse() {
        try {
            PositionLeverRequest request = PositionLeverRequest.builder()
                    .contractCode("BTC-USDT")
                    .marginMode("cross")
                    .positionSide("long")
                    .build();
            PositionLeverResponse response = huobiAPIService.positionLeverResponse(request);
            logger.debug("v5.查询杠杆等级：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询杠杆等级(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void setPositionLeverResponse() {
        try {
            SetPositionLeverRequest request = SetPositionLeverRequest.builder()
                    .contractCode("BTC-USDT")
                    .marginMode("isolated")
                    .leverRate("5")
                    .positionSide("long")
                    .build();
            SetPositionLeverResponse response = huobiAPIService.setPositionLeverResponse(request);
            logger.debug("v5.设置杠杆等级：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.设置杠杆等级(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void positionRiskLimitResponse() {
        try {
            PositionRiskLimitRequest request = PositionRiskLimitRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            PositionRiskLimitResponse response = huobiAPIService.positionRiskLimitResponse(request);
            logger.debug("v5.查询持仓风险限额：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询持仓风险限额(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void positionRiskLimitTierResponse() {
        try {
            PositionRiskLimitTierRequest request = PositionRiskLimitTierRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            PositionRiskLimitTierResponse response = huobiAPIService.positionRiskLimitTierResponse(request);
            logger.debug("v5.查询持仓风险限额阶梯：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.查询持仓风险限额阶梯(预期异常,无key):{}", e.getMessage());
        }
    }

    @Test
    public void positionMarginResponse() {
        try {
            PositionMarginRequest request = PositionMarginRequest.builder()
                    .contractCode("BTC-USDT")
                    .build();
            PositionMarginResponse response = huobiAPIService.positionMarginResponse(request);
            logger.debug("v5.调整逐仓保证金：{}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.debug("v5.调整逐仓保证金(预期异常,无key):{}", e.getMessage());
        }
    }
}
