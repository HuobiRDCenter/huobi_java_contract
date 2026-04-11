package com.huobi.api.service.usdt.algo;

import com.alibaba.fastjson.JSON;
import com.huobi.api.constants.HuobiFutureAPIConstants;
import com.huobi.api.exception.ApiException;
import com.huobi.api.request.usdt.algo.*;
import com.huobi.api.response.usdt.algo.*;
import com.huobi.api.util.HbdmHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AlgoAPIServiceImpl implements AlgoAPIService {

    String api_key = ""; // huobi申请的apiKey
    String secret_key = ""; // huobi申请的secretKey
    String url_prex = "https://api.hbdm.com";

    Logger logger = LoggerFactory.getLogger(getClass());

    public AlgoAPIServiceImpl(String api_key, String secret_key) {
        this.api_key = api_key;
        this.secret_key = secret_key;
    }

    @Override
    public AlgoOrderResponse algoOrder(AlgoOrderRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode());
            }

            if (StringUtils.isNotEmpty(request.getType())) {
                params.put("type", request.getType());
            }

            if (StringUtils.isNotEmpty(request.getPositionSide())) {
                params.put("position_side", request.getPositionSide());
            }

            if (StringUtils.isNotEmpty(request.getSide())) {
                params.put("side", request.getSide());
            }

            if (StringUtils.isNotEmpty(request.getMarginMode())) {
                params.put("margin_mode", request.getMarginMode());
            }

            // 可选字段
            if (StringUtils.isNotEmpty(request.getAlgoClientOrderId())) {
                params.put("algo_client_order_id", request.getAlgoClientOrderId());
            }

            if (StringUtils.isNotEmpty(request.getVolume())) {
                params.put("volume", request.getVolume());
            }

            // 止盈相关参数
            if (StringUtils.isNotEmpty(request.getTpTriggerPrice())) {
                params.put("tp_trigger_price", request.getTpTriggerPrice());
            }

            if (StringUtils.isNotEmpty(request.getTpOrderPrice())) {
                params.put("tp_order_price", request.getTpOrderPrice());
            }

            if (StringUtils.isNotEmpty(request.getTpType())) {
                params.put("tp_type", request.getTpType());
            }

            if (StringUtils.isNotEmpty(request.getTpTriggerPriceType())) {
                params.put("tp_trigger_price_type", request.getTpTriggerPriceType());
            }

            // 止损相关参数
            if (StringUtils.isNotEmpty(request.getSlTriggerPrice())) {
                params.put("sl_trigger_price", request.getSlTriggerPrice());
            }

            if (StringUtils.isNotEmpty(request.getSlOrderPrice())) {
                params.put("sl_order_price", request.getSlOrderPrice());
            }

            if (StringUtils.isNotEmpty(request.getSlType())) {
                params.put("sl_type", request.getSlType());
            }

            if (StringUtils.isNotEmpty(request.getSlTriggerPriceType())) {
                params.put("sl_trigger_price_type", request.getSlTriggerPriceType());
            }

            // 计划委托相关参数
            if (StringUtils.isNotEmpty(request.getPrice())) {
                params.put("price", request.getPrice());
            }

            if (StringUtils.isNotEmpty(request.getPriceMatch())) {
                params.put("price_match", request.getPriceMatch());
            }

            if (StringUtils.isNotEmpty(request.getTriggerPrice())) {
                params.put("trigger_price", request.getTriggerPrice());
            }

            if (StringUtils.isNotEmpty(request.getTriggerPriceType())) {
                params.put("trigger_price_type", request.getTriggerPriceType());
            }

            // 跟踪委托相关参数
            if (StringUtils.isNotEmpty(request.getActivePrice())) {
                params.put("active_price", request.getActivePrice());
            }

            if (StringUtils.isNotEmpty(request.getOrderPriceType())) {
                params.put("order_price_type", request.getOrderPriceType());
            }

            if (StringUtils.isNotEmpty(request.getCallbackRate())) {
                params.put("callback_rate", request.getCallbackRate());
            }

            if (request.getReduceOnly() != null) {
                params.put("reduce_only", request.getReduceOnly());
            }

            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiFutureAPIConstants.ALGO_ORDER, params);
            logger.debug("body:{}", body);
            AlgoOrderResponse response = JSON.parseObject(body, AlgoOrderResponse.class);
            if (response.getCode() != null && response.getCode() == 200){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public CancelAlgoOrdersResponse cancelAlgoOrder(CancelAlgoOrdersRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (StringUtils.isNotEmpty(request.getAlgoId())) {
                params.put("algo_id", request.getAlgoId());
            }

            if (StringUtils.isNotEmpty(request.getAlgoClientOrderId())) {
                params.put("algo_client_order_id", request.getAlgoClientOrderId());
            }

            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode());
            }

            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiFutureAPIConstants.ALGO_ORDER_CANCEL, params);
            logger.debug("body:{}", body);
            CancelAlgoOrdersResponse response = JSON.parseObject(body, CancelAlgoOrdersResponse.class);
            if (response.getCode() != null && response.getCode() == 200){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public QueryAlgoOrdersResponse queryAlgoOrder(QueryAlgoOrdersRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            // 必填参数校验
            if (StringUtils.isEmpty(request.getType())) {
                throw new IllegalArgumentException("type参数为必填项");
            }
            params.put("type", request.getType());

            // 可选参数
            if (StringUtils.isNotEmpty(request.getAlgoId())) {
                params.put("algo_id", request.getAlgoId());
            }

            if (StringUtils.isNotEmpty(request.getAlgoClientOrderId())) {
                params.put("algo_client_order_id", request.getAlgoClientOrderId());
            }

            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode());
            }

            // 使用GET请求查询
            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiFutureAPIConstants.ALGO_ORDER_QUERY, params);
            logger.debug("查询策略订单返回body:{}", body);

            QueryAlgoOrdersResponse response = JSON.parseObject(body, QueryAlgoOrdersResponse.class);
            if (response.getCode() != null && response.getCode() == 200) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public QueryOpenAlgoOrdersResponse queryOpenAlgoOrders(QueryOpenAlgoOrdersRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("type", request.getType());

            // 可选参数
            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode());
            }

            if (StringUtils.isNotEmpty(request.getAlgoId())) {
                params.put("algo_id", request.getAlgoId());
            }

            if (StringUtils.isNotEmpty(request.getAlgoClientOrderId())) {
                params.put("algo_client_order_id", request.getAlgoClientOrderId());
            }

            if (request.getFrom() != null) {
                params.put("from", request.getFrom());
            }

            if (request.getLimit() != null) {
                params.put("limit", request.getLimit());
            }

            if (StringUtils.isNotEmpty(request.getDirect())) {
                params.put("direct", request.getDirect());
            }

            // 使用GET请求查询
            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiFutureAPIConstants.ALGO_ORDER_OPENS_QUERY, params);
            logger.debug("查询当前未触发策略订单返回body:{}", body);

            QueryOpenAlgoOrdersResponse response = JSON.parseObject(body, QueryOpenAlgoOrdersResponse.class);
            if (response.getCode() != null && response.getCode() == 200) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public QueryAlgoOrderHistoryResponse queryAlgoOrderHistory(QueryAlgoOrderHistoryRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            // 必填参数校验
            if (StringUtils.isEmpty(request.getType())) {
                throw new IllegalArgumentException("type参数为必填项");
            }
            params.put("type", request.getType());

            // 可选参数
            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode());
            }

            if (StringUtils.isNotEmpty(request.getMarginMode())) {
                params.put("margin_mode", request.getMarginMode());
            }

            if (request.getStates() != null && !request.getStates().isEmpty()) {
                params.put("states", String.join(",", request.getStates()));
            }

            if (request.getStartTime() != null) {
                params.put("start_time", request.getStartTime());
            }

            if (request.getEndTime() != null) {
                params.put("end_time", request.getEndTime());
            }

            if (request.getFrom() != null) {
                params.put("from", request.getFrom());
            }

            if (request.getLimit() != null) {
                params.put("limit", request.getLimit());
            }

            if (StringUtils.isNotEmpty(request.getDirect())) {
                params.put("direct", request.getDirect());
            }

            // 使用GET请求查询
            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiFutureAPIConstants.ALGO_ORDER_HISTORY_QUERY, params);
            logger.debug("查询历史策略订单返回body:{}", body);

            QueryAlgoOrderHistoryResponse response = JSON.parseObject(body, QueryAlgoOrderHistoryResponse.class);
            if (response.getCode() != null && response.getCode() == 200) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }
}
