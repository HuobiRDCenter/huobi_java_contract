package com.huobi.api.service.usdt.copytrading;
import com.alibaba.fastjson.JSON;
import com.huobi.api.constants.HuobiLinearSwapAPIConstants;
import com.huobi.api.exception.ApiException;
import com.huobi.api.request.usdt.copytrading.*;
import com.huobi.api.response.usdt.copytrading.*;
import com.huobi.api.util.HbdmHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
public class CopytradingAPIServiceImpl implements CopytradingAPIService{
    String api_key = ""; // huobi申请的apiKey
    String secret_key = ""; // huobi申请的secretKey
    String url_prex = "https://api.hbdm.com";

    Logger logger = LoggerFactory.getLogger(getClass());

    public CopytradingAPIServiceImpl(String api_key, String secret_key) {
        this.api_key = api_key;
        this.secret_key = secret_key;
    }


    public CopytradingTraderPlaceOrderResponse copytradingTraderPlaceOrderResponse(CopytradingTraderPlaceOrderRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("contract_code", request.getContractCode());
            params.put("price", request.getPrice());
            params.put("amount", request.getAmount());
            params.put("margin_mode", request.getPrice());
            params.put("order_price_type", request.getOrderPriceType());
            params.put("order_direction", request.getOrderDirection());
            params.put("lever", request.getLever());
            params.put("tp_trigger_price", request.getTpTriggerPrice());
            params.put("sl_trigger_price", request.getSlTriggerPrice());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Place_Order, params);
            logger.debug("body:{}", body);
            CopytradingTraderPlaceOrderResponse response = JSON.parseObject(body, CopytradingTraderPlaceOrderResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderQueryContractLeverResponse copytradingTraderQueryContractLeverResponse(String contractCode, String marginMode) {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode);
            }
            if (StringUtils.isNotEmpty(marginMode)) {
                params.put("margin_mode", marginMode);
            }
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Query_Contract_Lever,params);
            logger.debug("body:{}",body);
            CopytradingTraderQueryContractLeverResponse response=JSON.parseObject(body,CopytradingTraderQueryContractLeverResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderUpdatedContractLeverResponse copytradingTraderUpdatedContractLeverResponse(String contractCode, Integer lever, String marginMode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("contract_code", contractCode);
            params.put("lever", lever);
            params.put("margin_mode", marginMode);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Updated_Contract_Lever, params);
            logger.debug("body:{}", body);
            CopytradingTraderUpdatedContractLeverResponse response = JSON.parseObject(body, CopytradingTraderUpdatedContractLeverResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderCloseOrderResponse copytradingTraderCloseOrderResponse(String subPositionId) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("sub_position_id", subPositionId);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Close_Order, params);
            logger.debug("body:{}", body);
            CopytradingTraderCloseOrderResponse response = JSON.parseObject(body, CopytradingTraderCloseOrderResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderCloseAllPositionResponse copytradingTraderCloseAllPositionResponse(String contractCode, String marginMode, String positionSide) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("contract_code", contractCode);
            params.put("margin_mode", marginMode);
            params.put("position_side", positionSide);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Close_All_Position, params);
            logger.debug("body:{}", body);
            CopytradingTraderCloseAllPositionResponse response = JSON.parseObject(body, CopytradingTraderCloseAllPositionResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderAddMarginResponse copytradingTraderAddMarginResponse(String contractCode, String amount, Integer type, String positionSide) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("contract_code", contractCode);
            params.put("amount", amount);
            params.put("type", type);
            params.put("position_side", positionSide);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Add_Margin, params);
            logger.debug("body:{}", body);
            CopytradingTraderAddMarginResponse response = JSON.parseObject(body, CopytradingTraderAddMarginResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderPositionListResponse copytradingTraderPositionListResponse() {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Position_List,params);
            logger.debug("body:{}",body);
            CopytradingTraderPositionListResponse response=JSON.parseObject(body,CopytradingTraderPositionListResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderAccountTransferResponse copytradingTraderAccountTransferResponse(String amount, Integer type, String currency) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("contract_code", amount);
            params.put("amount", type);
            params.put("type", currency);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Account_Transfer, params);
            logger.debug("body:{}", body);
            CopytradingTraderAccountTransferResponse response = JSON.parseObject(body, CopytradingTraderAccountTransferResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderCurrentPositionsResponse copytradingTraderCurrentPositionsResponse(CopytradingTraderCurrentPositionsRequest request) {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode());
            }
            params.put("start_time", request.getStartTime());
            params.put("end_time", request.getEndTime());
            params.put("direct", request.getDirect());
            params.put("from_id", request.getFromId());
            params.put("limit", request.getLimit());
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Current_Positions,params);
            logger.debug("body:{}",body);
            CopytradingTraderCurrentPositionsResponse response=JSON.parseObject(body,CopytradingTraderCurrentPositionsResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderPositionPositionsResponse copytradingTraderPositionPositionsResponse(CopytradingTraderPositionPositionsRequest request) {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode());
            }
            params.put("start_time", request.getStartTime());
            params.put("end_time", request.getEndTime());
            params.put("direct", request.getDirect());
            params.put("from_id", request.getFromId());
            params.put("limit", request.getLimit());
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Position_Positions,params);
            logger.debug("body:{}",body);
            CopytradingTraderPositionPositionsResponse response=JSON.parseObject(body,CopytradingTraderPositionPositionsResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderTpslOrderResponse copytradingTraderTpslOrderResponse(String subPositionId, String tpTriggerPrice, String slTriggerPrice) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("sub_position_id", subPositionId);
            params.put("tp_trigger_price", tpTriggerPrice);
            params.put("sl_trigger_price", slTriggerPrice);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Tpsl_Order, params);
            logger.debug("body:{}", body);
            CopytradingTraderTpslOrderResponse response = JSON.parseObject(body, CopytradingTraderTpslOrderResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderQueryContractResponse copytradingTraderQueryContractResponse() {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Query_Contract,params);
            logger.debug("body:{}",body);
            CopytradingTraderQueryContractResponse response=JSON.parseObject(body,CopytradingTraderQueryContractResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderOrderTotalDetailResponse copytradingTraderOrderTotalDetailResponse() {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Order_Total_Detail,params);
            logger.debug("body:{}",body);
            CopytradingTraderOrderTotalDetailResponse response=JSON.parseObject(body,CopytradingTraderOrderTotalDetailResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderProfitHistoryDetailsResponse copytradingTraderProfitHistoryDetailsResponse(CopytradingTraderProfitHistoryDetailsRequest request) {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            params.put("start_time", request.getStartTime());
            params.put("end_time", request.getEndTime());
            params.put("direct", request.getDirect());
            params.put("from_id", request.getFromId());
            params.put("limit", request.getLimit());
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Profit_History_Details,params);
            logger.debug("body:{}",body);
            CopytradingTraderProfitHistoryDetailsResponse response=JSON.parseObject(body,CopytradingTraderProfitHistoryDetailsResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderTotalProfitHistoryResponse copytradingTraderTotalProfitHistoryResponse() {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Total_Profit_History,params);
            logger.debug("body:{}",body);
            CopytradingTraderTotalProfitHistoryResponse response=JSON.parseObject(body,CopytradingTraderTotalProfitHistoryResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderQueryFollowersResponse copytradingTraderQueryFollowersResponse(CopytradingTraderQueryFollowersRequest request) {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            params.put("start_time", request.getStartTime());
            params.put("end_time", request.getEndTime());
            params.put("direct", request.getDirect());
            params.put("from_id", request.getFromId());
            params.put("limit", request.getLimit());
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Query_Followers,params);
            logger.debug("body:{}",body);
            CopytradingTraderQueryFollowersResponse response=JSON.parseObject(body,CopytradingTraderQueryFollowersResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderRemoveFollowerResponse copytradingTraderRemoveFollowerResponse(String followerUid) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("follower_uid", followerUid);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Remove_Follower, params);
            logger.debug("body:{}", body);
            CopytradingTraderRemoveFollowerResponse response = JSON.parseObject(body, CopytradingTraderRemoveFollowerResponse.class);
            if (response.getCode() == 200) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderOpenOrdersResponse copytradingTraderOpenOrdersResponse(String contractCode) {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            params.put("contract_code", contractCode);
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Open_Orders,params);
            logger.debug("body:{}",body);
            CopytradingTraderOpenOrdersResponse response=JSON.parseObject(body,CopytradingTraderOpenOrdersResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public CopytradingTraderTpslOpenOrdersResponse copytradingTraderTpslOpenOrdersResponse(String contractCode) {
        String body;
        try{
            Map<String,Object> params=new HashMap<>();
            params.put("contract_code", contractCode);
            body=HbdmHttpClient.getInstance().doGetKey(api_key,secret_key,url_prex + HuobiLinearSwapAPIConstants.Copytrading_Trader_Tpsl_Open_Orders,params);
            logger.debug("body:{}",body);
            CopytradingTraderTpslOpenOrdersResponse response=JSON.parseObject(body,CopytradingTraderTpslOpenOrdersResponse.class);
            if (response.getCode() == 200){
                return response;
            }
        }catch(Exception e){
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }
}
