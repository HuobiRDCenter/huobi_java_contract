package com.huobi.api.service.usdt.copyTradingTrade;

import com.alibaba.fastjson.JSON;
import com.huobi.api.constants.HuobiLinearSwapAPIConstants;
import com.huobi.api.exception.ApiException;
import com.huobi.api.request.usdt.copyTradingTrade.*;
import com.huobi.api.response.usdt.copyTradingTrade.*;
import com.huobi.api.util.HbdmHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

public class CopyTradingTradeAPIServiceImpl implements CopyTradingTradeAPIService {

    String api_key = ""; // huobi申请的apiKey
    String secret_key = ""; // huobi申请的secretKey
    String url_prex = "https://api.hbdm.com";
    String successCode = "0";

    Logger logger = LoggerFactory.getLogger(getClass());

    public CopyTradingTradeAPIServiceImpl(String api_key, String secret_key) {
        this.api_key = api_key;
        this.secret_key = secret_key;
    }


    @Override
    public BaseArrayResult<InstrumentResult> queryTraderInstruments(String instType) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", instType);

            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Instruments, params);
            logger.debug("body:{}", body);
            BaseArrayResult<InstrumentResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<InstrumentResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<StatisticsResult> queryTraderStatistics(String instType) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", instType);

            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Statistics, params);
            logger.debug("body:{}", body);
            BaseArrayResult<StatisticsResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<StatisticsResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<ProfitSharingHistoryResult> queryTraderProfitSharingHistory(TraderProfitSharingHistoryParam param) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instId", param.getInstId());
            params.put("instType", param.getInstType());
            params.put("begin", param.getBegin());
            params.put("end", param.getEnd());
            params.put("after", param.getAfter());
            params.put("before", param.getBefore());
            params.put("limit", param.getLimit());

            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Profit_Sharing_History, params);
            logger.debug("body:{}", body);
            BaseArrayResult<ProfitSharingHistoryResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<ProfitSharingHistoryResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<ProfitSharingHistorySummaryResult> queryTraderProfitSharingHistorySummary(String instType) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", instType);

            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Profit_Sharing_History_Summary, params);
            logger.debug("body:{}", body);
            BaseArrayResult<ProfitSharingHistorySummaryResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<ProfitSharingHistorySummaryResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<UnrealizedProfitSharingSummaryResult> queryTraderUnrealizedProfitSharingSummary(TraderUnrealizedProfitSharingSummaryParam param) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", param.getInstType());
            params.put("ccy", param.getCcy());

            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Unrealized_Profit_Sharing_Summary, params);
            logger.debug("body:{}", body);
            BaseArrayResult<UnrealizedProfitSharingSummaryResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<UnrealizedProfitSharingSummaryResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<FollowersResult> queryTraderFollowers(TraderFollowersParam param) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", param.getInstType());
            params.put("begin", param.getBegin());
            params.put("end", param.getEnd());
            params.put("after", param.getAfter());
            params.put("before", param.getBefore());
            params.put("limit", param.getLimit());

            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Followers, params);
            logger.debug("body:{}", body);
            BaseArrayResult<FollowersResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<FollowersResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<DeleteFollowerResult> deleteTraderFollower(TraderFollowerDeleteParam param) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", param.getInstType());
            params.put("followerUids", param.getFollowerUids());

            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Follower, params);
            logger.debug("body:{}", body);
            BaseArrayResult<DeleteFollowerResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<DeleteFollowerResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<TransferResult> traderTransfer(TraderTransferParam param) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amt", param.getAmt());
            params.put("from", param.getFrom());
            params.put("to", param.getTo());
            params.put("ccy", param.getCcy());

            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Transfer, params);
            logger.debug("body:{}", body);
            BaseArrayResult<TransferResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<TransferResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<FollowerSettingsResult> traderFollowerSettings(TraderFollowerSettingsParam param) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", param.getInstType());
            params.put("enable", param.getEnable() != null ? String.valueOf(param.getEnable()) : null);
            params.put("profitSharingRatio", param.getProfitSharingRatio());
            params.put("maxFollowers", param.getMaxFollowers());

            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Follower_Settings, params);
            logger.debug("body:{}", body);
            BaseArrayResult<FollowerSettingsResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<FollowerSettingsResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<ConfigResult> traderConfig(String instType) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", instType);

            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Config, params);
            logger.debug("body:{}", body);
            BaseArrayResult<ConfigResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<ConfigResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public BaseArrayResult<ApikeyResult> traderApikey(TraderApikeyParam param) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("instType", param.getInstType());

            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.CopytradingV6_Trader_Apikey, params);
            logger.debug("body:{}", body);
            BaseArrayResult<ApikeyResult> response = JSON.parseObject(body, new TypeReference<BaseArrayResult<ApikeyResult>>() {});
            if (successCode.equals(response.getCode())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }
}
