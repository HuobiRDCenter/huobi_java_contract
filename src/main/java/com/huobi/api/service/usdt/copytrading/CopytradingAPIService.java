package com.huobi.api.service.usdt.copytrading;
import com.huobi.api.request.usdt.copytrading.*;
import com.huobi.api.request.usdt.trade.LinearCancelAfterRequest;
import com.huobi.api.response.usdt.copytrading.*;
import com.huobi.api.response.usdt.trade.LinearCancelAfterResponse;

public interface CopytradingAPIService {
    CopytradingTraderPlaceOrderResponse copytradingTraderPlaceOrderResponse(CopytradingTraderPlaceOrderRequest request);
    CopytradingTraderQueryContractLeverResponse copytradingTraderQueryContractLeverResponse(String contractCode, String marginMode);
    CopytradingTraderUpdatedContractLeverResponse copytradingTraderUpdatedContractLeverResponse(String contractCode, Integer lever, String marginMode);
    CopytradingTraderCloseOrderResponse copytradingTraderCloseOrderResponse(String subPositionId);
    CopytradingTraderCloseAllPositionResponse copytradingTraderCloseAllPositionResponse(String contractCode, String marginMode, String positionSide);
    CopytradingTraderAddMarginResponse copytradingTraderAddMarginResponse(String contractCode, String amount, Integer type, String positionSide);
    CopytradingTraderPositionListResponse copytradingTraderPositionListResponse();
    CopytradingTraderAccountTransferResponse copytradingTraderAccountTransferResponse(String amount, Integer type, String currency);
    CopytradingTraderCurrentPositionsResponse copytradingTraderCurrentPositionsResponse(CopytradingTraderCurrentPositionsRequest request);
    CopytradingTraderPositionPositionsResponse copytradingTraderPositionPositionsResponse(CopytradingTraderPositionPositionsRequest request);
    CopytradingTraderTpslOrderResponse copytradingTraderTpslOrderResponse(String subPositionId, String tpTriggerPrice, String slTriggerPrice);
    CopytradingTraderQueryContractResponse copytradingTraderQueryContractResponse();
    CopytradingTraderOrderTotalDetailResponse copytradingTraderOrderTotalDetailResponse();
    CopytradingTraderProfitHistoryDetailsResponse copytradingTraderProfitHistoryDetailsResponse(CopytradingTraderProfitHistoryDetailsRequest request);
    CopytradingTraderTotalProfitHistoryResponse copytradingTraderTotalProfitHistoryResponse();
    CopytradingTraderQueryFollowersResponse copytradingTraderQueryFollowersResponse(CopytradingTraderQueryFollowersRequest request);
    CopytradingTraderRemoveFollowerResponse copytradingTraderRemoveFollowerResponse(String followerUid);
    CopytradingTraderOpenOrdersResponse copytradingTraderOpenOrdersResponse(String contractCode);
    CopytradingTraderTpslOpenOrdersResponse copytradingTraderTpslOpenOrdersResponse(String contractCode);

}
