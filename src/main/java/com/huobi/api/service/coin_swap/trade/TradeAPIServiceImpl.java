package com.huobi.api.service.coin_swap.trade;

import com.alibaba.fastjson.JSON;
import com.huobi.api.constants.HuobiCoinMarginedSwapAPIOptions;
import com.huobi.api.exception.ApiException;
import com.huobi.api.request.coin_swap.trade.*;
import com.huobi.api.request.usdt.trade.SwapHisordersExactV3Request;
import com.huobi.api.request.usdt.trade.SwapHisordersV3Request;
import com.huobi.api.request.usdt.trade.SwapMatchResultsExactV3Request;
import com.huobi.api.request.usdt.trade.SwapMatchResultsV3Request;
import com.huobi.api.response.coin_swap.trade.*;
import com.huobi.api.response.usdt.trade.SwapHisordersExactV3Response;
import com.huobi.api.response.usdt.trade.SwapHisordersV3Response;
import com.huobi.api.response.usdt.trade.SwapMatchResultsExactV3Response;
import com.huobi.api.response.usdt.trade.SwapMatchResultsV3Response;
import com.huobi.api.util.HbdmHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeAPIServiceImpl implements TradeAPIService {


    String api_key = ""; // huobi申请的apiKey
    String secret_key = ""; // huobi申请的secretKey
    String url_prex = "https://api.hbdm.com";

    Logger logger = LoggerFactory.getLogger(getClass());

    public TradeAPIServiceImpl(String api_key, String secret_key) {
        this.api_key = api_key;
        this.secret_key = secret_key;
    }


    @Override
    public SwapOrderResponse swapOrderRequest(SwapOrderRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();

            params.put("volume", request.getVolume());
            params.put("direction", request.getDirection().getValue());
            params.put("offset", request.getOffset().getValue());
            params.put("order_price_type", request.getOrderPriceType());
            params.put("lever_rate", request.getLeverRate());
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getPrice() != null) {
                params.put("price", request.getPrice());
            }
            if (request.getClientOrderId() != null) {
                params.put("client_order_id", request.getClientOrderId());
            }
            if (request.getTpTriggerPrice()!=null && request.getTpTriggerPrice()!= BigDecimal.valueOf(0) ){
                params.put("tp_trigger_price",request.getTpTriggerPrice());
            }
            if (request.getTpOrderPrice()!=null && request.getTpOrderPrice()!= BigDecimal.valueOf(0) ){
                params.put("tp_order_price",request.getTpOrderPrice());
            }
            if (request.getTpOrderPriceType()!=null){
                params.put("tp_order_price_type",request.getTpOrderPriceType());
            }
            if (request.getSlTriggerPrice()!=null && request.getSlTriggerPrice()!=BigDecimal.valueOf(0)){
                params.put("sl_trigger_price",request.getSlTriggerPrice());
            }
            if (request.getSlOrderPrice()!=null && request.getSlOrderPrice()!=BigDecimal.valueOf(0)){
                params.put("sl_order_price",request.getSlOrderPrice());
            }
            if (request.getSlOrderPriceType()!=null){
                params.put("sl_order_price_type",request.getSlOrderPriceType());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_ORDER, params);
            logger.debug("body:{}", body);
            SwapOrderResponse response = JSON.parseObject(body, SwapOrderResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapBatchorderResponse swapBatchorderRequest(SwapBatchorderRequest request) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        String body;
        try {

            request.getList().stream()
                    .forEach(e -> {
                        Map<String, Object> params = new HashMap<>();
                        params.put("volume", e.getVolume());
                        params.put("direction", e.getDirection().getValue());
                        params.put("offset", e.getOffset().getValue());
                        params.put("order_price_type", e.getOrderPriceType());
                        params.put("lever_rate", e.getLeverRate());
                        params.put("contract_code", e.getContractCode());

                        if (e.getPrice() != null) {
                            params.put("price", e.getPrice());
                        }
                        if (e.getClientOrderId() != null) {
                            params.put("client_order_id", e.getClientOrderId());
                        }
                        if (e.getTpTriggerPrice()!=null && e.getTpTriggerPrice()!= BigDecimal.valueOf(0) ){
                            params.put("tp_trigger_price",e.getTpTriggerPrice());
                        }
                        if (e.getTpOrderPrice()!=null && e.getTpOrderPrice()!= BigDecimal.valueOf(0) ){
                            params.put("tp_order_price",e.getTpOrderPrice());
                        }
                        if (e.getTpOrderPriceType()!=null){
                            params.put("tp_order_price_type",e.getTpOrderPriceType());
                        }
                        if (e.getSlTriggerPrice()!=null && e.getSlTriggerPrice()!=BigDecimal.valueOf(0)){
                            params.put("sl_trigger_price",e.getSlTriggerPrice());
                        }
                        if (e.getSlOrderPrice()!=null && e.getSlOrderPrice()!=BigDecimal.valueOf(0)){
                            params.put("sl_order_price",e.getSlOrderPrice());
                        }
                        if (e.getSlOrderPriceType()!=null){
                            params.put("sl_order_price_type",e.getSlOrderPriceType());
                        }

                        listMap.add(params);
                    });
            Map<String, Object> params = new HashMap<>();

            params.put("orders_data", listMap);

            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_BATCHORDER, params);
            logger.debug("body:{}", body);
            SwapBatchorderResponse response = JSON.parseObject(body, SwapBatchorderResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    /**
     * 撤销订单
     */
    @Override
    public SwapCancelResponse swapCancelRequest(SwapCancelRequest request) {
        String body = "";
        try {
            Map<String, Object> params = new HashMap<>();
            if (request.getOrderId() != null) {
                params.put("order_id", request.getOrderId());
            }
            if (request.getClientOrderId() != null) {
                params.put("client_order_id", request.getClientOrderId());
            }
            params.put("contract_code", request.getContractCode().toUpperCase());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_CANCEL, params);
            logger.debug("body:{}", body);
            SwapCancelResponse response = JSON.parseObject(body, SwapCancelResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            System.out.println("body:" + body);
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    /**
     * 全部撤单
     */
    @Override
    public SwapCancelallResponse swapCancelallRequest(SwapCancelallRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode());
            if (request.getDirection()!=null){
                params.put("direction",request.getDirection());
            }
            if (request.getOffset()!=null){
                params.put("offset",request.getOffset());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_CANCELALL, params);
            logger.debug("body:{}", body);
            SwapCancelallResponse response = JSON.parseObject(body, SwapCancelallResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapOrderInfoResponse swapOrderInfoRequest(SwapOrderInfoRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getClientOrderId() != null) {
                params.put("client_order_id", request.getClientOrderId());
            }
            if (request.getOrderId() != null) {
                params.put("order_id", request.getOrderId());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_ORDER_INFO, params);
            logger.debug("body:{}", body);
            SwapOrderInfoResponse response = JSON.parseObject(body, SwapOrderInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    /**
     * 获取订单明细信息
     */
    @Override
    public SwapOrderDetailResponse swapOrderDetailRequest(SwapOrderDetailRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("order_id", request.getOrderId());
            if (request.getCreatedAt() != null) {
                params.put("created_at", request.getCreatedAt());
            }
            if (request.getOrderType() != null) {
                params.put("order_type", request.getOrderType());
            }
            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_ORDER_DETAIL, params);
            logger.debug("body:{}", body);
            SwapOrderDetailResponse response = JSON.parseObject(body, SwapOrderDetailResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    /**
     * 获取合约当前未成交委托
     */
    @Override
    public SwapOpenordersResponse swapOpenordersRequest(SwapOpenordersRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            if (request.getSortBy()!=null){
                params.put("sort_by",request.getSortBy());
            }
            if (request.getTradeType()!=null){
                params.put("trade_type",request.getTradeType());
            }
            params.put("contract_code", request.getContractCode().toUpperCase());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_OPENORDERS, params);
            logger.debug("body:{}", body);
            SwapOpenordersResponse response = JSON.parseObject(body, SwapOpenordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapHisordersResponse swapHisordersRequest(SwapHisordersRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("trade_type", request.getTradeType());
            params.put("type", request.getType());
            params.put("status", request.getStatus());
            params.put("create_date", request.getCreateDate());
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            if (request.getContractCode() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getSortBy()!=null){
                params.put("sort_by",request.getSortBy());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_HISORDERS, params);
            logger.debug("body:{}", body);
            SwapHisordersResponse response = JSON.parseObject(body, SwapHisordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapMatchresultsResponse swapMatchresultsRequest(SwapMatchresultsRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("trade_type", request.getTradeType());
            params.put("create_date", request.getCreateDate());
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_MATCHRESULTS, params);
            logger.debug("body:{}", body);
            SwapMatchresultsResponse response = JSON.parseObject(body, SwapMatchresultsResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapLightningClosePositionResponse swapLightningClosePositionRequest(SwapLightningClosePositionRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(request.getOrderPriceType())) {
                params.put("order_price_type", request.getOrderPriceType());
            }
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("direction", request.getDirection());
            params.put("volume", request.getVolume());
            if (request.getClientOrderId() != null) {
                params.put("client_order_id", request.getClientOrderId());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_LIGHTNING_CLOSE_POSITION, params);
            logger.debug("body:{}", body);
            SwapLightningClosePositionResponse response = JSON.parseObject(body, SwapLightningClosePositionResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    public SwapTriggerOrderResponse swapTriggerOrderResponse(SwapTriggerOrderRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("trigger_type", request.getTriggerType());
            params.put("trigger_price", request.getTriggerPrice());
            params.put("volume", request.getVolume());
            params.put("direction", request.getDirection());
            params.put("offset", request.getOffset());
            if (request.getOrderPrice() != null) {
                params.put("order_price", request.getOrderPrice());
            }
            if (request.getOrderPriceType() != null) {
                params.put("order_price_type", request.getOrderPriceType());
            }
            if (request.getLeverRate() != null) {
                params.put("lever_rate", request.getLeverRate());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRIGGER_ORDER, params);
            logger.debug("body:{}", body);
            SwapTriggerOrderResponse response = JSON.parseObject(body, SwapTriggerOrderResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTriggerCancelResponse swapTriggerCancelResponse(SwapTriggerCancelRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("order_id", request.getOrderId());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRIGGER_CANCEL, params);
            logger.debug("body:{}", body);
            SwapTriggerCancelResponse response = JSON.parseObject(body, SwapTriggerCancelResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);

    }

    @Override
    public SwapTriggerCancelallResponse swapTriggerCancelallResponse(SwapTriggerCancelallRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode());
            if (request.getDirection()!=null){
                params.put("direction",request.getDirection());
            }
            if (request.getOffset()!=null){
                params.put("offset",request.getOffset());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRIGGER_CANCELALL, params);
            logger.debug("body:{}", body);
            SwapTriggerCancelallResponse response = JSON.parseObject(body, SwapTriggerCancelallResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTriggerOpenordersResponse swapTriggerOpenordersResponse(SwapTriggerOpenordersRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            if (request.getTradeType()!=null){
                params.put("trade_type",request.getTradeType());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRIGGER_OPENORDERS, params);
            logger.debug("body:{}", body);
            SwapTriggerOpenordersResponse response = JSON.parseObject(body, SwapTriggerOpenordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTriggerHisordersResponse swapTriggerHisordersResponse(SwapTriggerHisordersRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("trade_type", request.getTradeType());
            params.put("status", request.getStatus());
            params.put("create_date", request.getCreateDate());

            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            if (request.getSortBy()!=null){
                params.put("sort_by",request.getSortBy());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRIGGER_HISORDERS, params);
            logger.debug("body:{}", body);
            SwapTriggerHisordersResponse response = JSON.parseObject(body, SwapTriggerHisordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapSwitchLeverRateResponse swapSwitchLeverRateResponse(String contractCdoe, Integer leverRate) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", contractCdoe.toUpperCase());
            params.put("lever_rate", leverRate);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_SWITCH_LEVER_RATE, params);
            logger.debug("body:{}", body);
            SwapSwitchLeverRateResponse response = JSON.parseObject(body, SwapSwitchLeverRateResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapHisordersExactResponse swapHisordersExactResponse(SwapHisordersExectRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("trade_type", request.getTradeType());
            params.put("type",request.getType());
            params.put("status",request.getStatus());
            if(request.getOrderPriceType()!=null){
                params.put("order_price_type",request.getOrderPriceType());
            }
            if (request.getStartTime()!=null){
                params.put("start_time",request.getStartTime());
            }
            if (request.getEndTime()!=null){
                params.put("end_time",request.getEndTime());
            }
            if (request.getFromId()!=null){
                params.put("size",request.getSize());
            }
            if (request.getDirect()!=null){
                params.put("direct",request.getDirect());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_HISORDERS_EXACT, params);
            logger.debug("body:{}", body);
            SwapHisordersExactResponse response = JSON.parseObject(body, SwapHisordersExactResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapMatchresultsExactResponse swapMatchresultsExactResponse(SwapMatchresultsExactRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("trade_type", request.getTradeType());
            if (request.getStartTime()!=null){
                params.put("start_time",request.getStartTime());
            }
            if (request.getEndTime()!=null){
                params.put("end_time",request.getEndTime());
            }
            if (request.getFromId()!=null){
                params.put("size",request.getSize());
            }
            if (request.getDirect()!=null){
                params.put("direct",request.getDirect());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_MATCHRESULTS_EXACT, params);
            logger.debug("body:{}", body);
            SwapMatchresultsExactResponse response = JSON.parseObject(body, SwapMatchresultsExactResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapRelationTpslOrderResponse swapRelationTpslOrderResponse(SwapRelationTpslOrderRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code",request.getContractCode().toUpperCase());
            params.put("order_id",request.getOrderId());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_RELATION_TPSL_ORDER, params);
            logger.debug("body:{}", body);
            SwapRelationTpslOrderResponse response = JSON.parseObject(body, SwapRelationTpslOrderResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTpslHisordersResponse swapTpslHisordersResponse(SwapTpslHisordersRequset request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code",request.getContractCode().toUpperCase());
            params.put("status",request.getStatus());
            params.put("create_date",request.getCreateDate());
            if (request.getPageIndex()!=null){
                params.put("page_index",request.getPageIndex());
            }
            if (request.getPageSize()!=null){
                params.put("page_size",request.getPageSize());
            }
            if (StringUtils.isNotEmpty(request.getSortBy())){
                params.put("sort_by",request.getSortBy());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TPSL_HISORDERS, params);
            logger.debug("body:{}", body);
            SwapTpslHisordersResponse response = JSON.parseObject(body, SwapTpslHisordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTpslOpenordersResponse swapTpslOpenordersResponse(SwapTpslOpenordersRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code",request.getContractCode().toUpperCase());
            if (request.getPageIndex()!=null){
                params.put("page_index",request.getPageIndex());
            }
            if (request.getPageSize()!=null){
                params.put("page_size",request.getPageSize());
            }
            if (request.getTradeType()!=null){
                params.put("trade_type",request.getTradeType());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TPSL_OPENORDERS, params);
            logger.debug("body:{}", body);
            SwapTpslOpenordersResponse response = JSON.parseObject(body, SwapTpslOpenordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTpslCancelallResponse swapTpslCancelallResponse(SwapTpslCancelallRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code",request.getContractCode().toUpperCase());
            if (request.getDirection()!=null){
                params.put("direction",request.getDirection());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TPSL_CANCELALL, params);
            logger.debug("body:{}", body);
            SwapTpslCancelallResponse response = JSON.parseObject(body, SwapTpslCancelallResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTpslCancelResponse swapTpslCancelResponse(SwapTpslCancelRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code",request.getContractCode().toUpperCase());
            params.put("order_id",request.getOrderId());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TPSL_CANCEL, params);
            logger.debug("body:{}", body);
            SwapTpslCancelResponse response = JSON.parseObject(body, SwapTpslCancelResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTpslOrderResponse swapTpslOrderResponse(SwapTpslOrderRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code",request.getContractCode().toUpperCase());
            params.put("direction",request.getDirection());
            params.put("volume",request.getVolume());
            if (request.getTpTriggerPrice()!=null && request.getTpTriggerPrice()!= BigDecimal.valueOf(0) ){
                params.put("tp_trigger_price",request.getTpTriggerPrice());
            }
            if (request.getTpOrderPrice()!=null && request.getTpOrderPrice()!= BigDecimal.valueOf(0) ){
                params.put("tp_order_price",request.getTpOrderPrice());
            }
            if (request.getTpOrderPriceType()!=null){
                params.put("tp_order_price_type",request.getTpOrderPriceType());
            }
            if (request.getSlTriggerPrice()!=null && request.getSlTriggerPrice()!=BigDecimal.valueOf(0)){
                params.put("sl_trigger_price",request.getSlTriggerPrice());
            }
            if (request.getSlOrderPrice()!=null && request.getSlOrderPrice()!=BigDecimal.valueOf(0)){
                params.put("sl_order_price",request.getSlOrderPrice());
            }
            if (request.getSlOrderPriceType()!=null){
                params.put("sl_order_price_type",request.getSlOrderPriceType());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TPSL_ORDER, params);
            logger.debug("body:{}", body);
            SwapTpslOrderResponse response = JSON.parseObject(body, SwapTpslOrderResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTrackOrderResponse swapTrackOrderResponse(SwapTrackOrderRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("direction", request.getDirection());
            params.put("offset", request.getOffset());
            if (request.getLeverRate() != null && request.getLeverRate() != 0) {
                params.put("lever_rate", request.getLeverRate());
            }
            params.put("volume", request.getVolume());
            params.put("callback_rate", request.getCallbackRate());
            params.put("active_price", request.getActivePrice());
            params.put("order_price_type", request.getOrderPriceType());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRACK_ORDER, params);
            logger.debug("body:{}", body);
            SwapTrackOrderResponse response = JSON.parseObject(body, SwapTrackOrderResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTrackCancelResponse swapTrackCancelResponse(SwapTrackCancelRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("order_id", request.getOrderId());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRACK_CANCEL, params);
            logger.debug("body:{}", body);
            SwapTrackCancelResponse response = JSON.parseObject(body, SwapTrackCancelResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTrackCancelallResponse swapTrackCancelallResponse(SwapTrackCancelallRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getDirection()!=null) {
                params.put("direction", request.getDirection());
            }
            if (request.getOffset()!=null) {
                params.put("offset", request.getOffset());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRACK_CANCELALL, params);
            logger.debug("body:{}", body);
            SwapTrackCancelallResponse response = JSON.parseObject(body, SwapTrackCancelallResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTrackOpenordersResponse swapTrackOpenordersResponse(SwapTrackOpenordersRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getTradeType()!=null) {
                params.put("trade_type", request.getTradeType());
            }
            if (request.getPageIndex()!=null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize()!=null){
                params.put("page_size",request.getPageSize());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRACK_OPENORDERS, params);
            logger.debug("body:{}", body);
            SwapTrackOpenordersResponse response = JSON.parseObject(body, SwapTrackOpenordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTrackHisordersResponse swapTrackHisordersResponse(SwapTrackHisordersRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code", request.getContractCode().toUpperCase());
            params.put("status",request.getStatus());
            params.put("trade_type", request.getTradeType());
            params.put("create_date",request.getCreateDate());
            if (request.getPageIndex()!=null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize()!=null){
                params.put("page_size",request.getPageSize());
            }
            if (request.getSortBy()!=null){
                params.put("sort_by",request.getSortBy());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_TRACK_HISORDERS, params);
            logger.debug("body:{}", body);
            SwapTrackHisordersResponse response = JSON.parseObject(body, SwapTrackHisordersResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCancelAfterResponse swapCancelAfterResponse(SwapCancelAfterRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (request.getOnOff() != null) {
                params.put("on_off", request.getOnOff());
            }
            if (request.getTimeOut() != null) {
                params.put("time_out", request.getTimeOut());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_CANCEL_AFTER, params);
            logger.debug("body:{}", body);
            SwapCancelAfterResponse response = JSON.parseObject(body, SwapCancelAfterResponse.class);
            if (response.getCode() != null && response.getCode() == 200){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapHisordersV3Response swapHisordersV3Response(SwapHisordersV3Request request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (request.getStartTime() != null) {
                params.put("start_time", request.getStartTime());
            }
            if (request.getEndTime() != null) {
                params.put("end_time", request.getEndTime());
            }
            if (StringUtils.isNotEmpty(request.getDirect())) {
                params.put("direct", request.getDirect());
            }
            if (request.getFromId() != null) {
                params.put("from_id", request.getFromId());
            }
            if (StringUtils.isNotEmpty(request.getContract())) {
                params.put("contract", request.getContract().toUpperCase());
            }
            if (request.getTradeType() != null) {
                params.put("trade_type", request.getTradeType());
            }
            if (request.getType() != null) {
                params.put("type", request.getType());
            }
            if (StringUtils.isNotEmpty(request.getStatus())) {
                params.put("status", request.getStatus());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_HISORDERS_V3, params);
            logger.debug("body:{}", body);
            SwapHisordersV3Response response = JSON.parseObject(body, SwapHisordersV3Response.class);
            if (response.getCode() != null && response.getCode() == 200){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapHisordersExactV3Response swapHisordersExactV3Response(SwapHisordersExactV3Request request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (request.getStartTime() != null) {
                params.put("start_time", request.getStartTime());
            }
            if (request.getEndTime() != null) {
                params.put("end_time", request.getEndTime());
            }
            if (StringUtils.isNotEmpty(request.getDirect())) {
                params.put("direct", request.getDirect());
            }
            if (request.getFromId() != null) {
                params.put("from_id", request.getFromId());
            }
            if (StringUtils.isNotEmpty(request.getContract())) {
                params.put("contract", request.getContract().toUpperCase());
            }
            if (request.getTradeType() != null) {
                params.put("trade_type", request.getTradeType());
            }
            if (request.getType() != null) {
                params.put("type", request.getType());
            }
            if (StringUtils.isNotEmpty(request.getStatus())) {
                params.put("status", request.getStatus());
            }
            if (StringUtils.isNotEmpty(request.getPriceType())) {
                params.put("price_type", request.getPriceType());
            }
            body = HbdmHttpClient.getInstance().doGet(url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_HISORDERS_EXACT_V3, params);
            logger.debug("body:{}", body);
            SwapHisordersExactV3Response response = JSON.parseObject(body, SwapHisordersExactV3Response.class);
            if (response.getCode() != null && response.getCode() == 200){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapMatchResultsV3Response swapMatchResultsV3Response(SwapMatchResultsV3Request request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (request.getStartTime() != null) {
                params.put("start_time", request.getStartTime());
            }
            if (request.getEndTime() != null) {
                params.put("end_time", request.getEndTime());
            }
            if (StringUtils.isNotEmpty(request.getDirect())) {
                params.put("direct", request.getDirect());
            }
            if (request.getFromId() != null) {
                params.put("from_id", request.getFromId());
            }
            if (StringUtils.isNotEmpty(request.getContract())) {
                params.put("contract", request.getContract().toUpperCase());
            }
            if (request.getTradeType() != null) {
                params.put("trade_type", request.getTradeType());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_MATCHRESULTS_V3, params);
            logger.debug("body:{}", body);
            SwapMatchResultsV3Response response = JSON.parseObject(body, SwapMatchResultsV3Response.class);
            if (response.getCode() != null && response.getCode() == 200){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapMatchResultsExactV3Response swapMatchResultsExactV3Response(SwapMatchResultsExactV3Request request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (request.getStartTime() != null) {
                params.put("start_time", request.getStartTime());
            }
            if (request.getEndTime() != null) {
                params.put("end_time", request.getEndTime());
            }
            if (StringUtils.isNotEmpty(request.getDirect())) {
                params.put("direct", request.getDirect());
            }
            if (request.getFromId() != null) {
                params.put("from_id", request.getFromId());
            }
            if (StringUtils.isNotEmpty(request.getContract())) {
                params.put("contract", request.getContract().toUpperCase());
            }
            if (request.getTradeType() != null) {
                params.put("trade_type", request.getTradeType());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiCoinMarginedSwapAPIOptions.SWAP_MATCHRESULTS_EXACT_V3, params);
            logger.debug("body:{}", body);
            SwapMatchResultsExactV3Response response = JSON.parseObject(body, SwapMatchResultsExactV3Response.class);
            if (response.getCode() != null && response.getCode() == 200){
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }
}