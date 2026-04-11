package com.huobi.api.service.usdt.copyTradingTrade;

import com.huobi.api.request.usdt.copyTradingTrade.*;
import com.huobi.api.response.usdt.copyTradingTrade.*;

import java.util.List;

public interface CopyTradingTradeAPIService {

    BaseArrayResult<InstrumentResult> queryTraderInstruments(String instType);

    BaseArrayResult<StatisticsResult> queryTraderStatistics(String instType);

    BaseArrayResult<ProfitSharingHistoryResult> queryTraderProfitSharingHistory(TraderProfitSharingHistoryParam param);

    BaseArrayResult<ProfitSharingHistorySummaryResult> queryTraderProfitSharingHistorySummary(String instType);

    BaseArrayResult<UnrealizedProfitSharingSummaryResult> queryTraderUnrealizedProfitSharingSummary(TraderUnrealizedProfitSharingSummaryParam param);

    BaseArrayResult<FollowersResult> queryTraderFollowers(TraderFollowersParam param);

    BaseArrayResult<DeleteFollowerResult> deleteTraderFollower(TraderFollowerDeleteParam param);

    BaseArrayResult<TransferResult> traderTransfer(TraderTransferParam param);

    BaseArrayResult<FollowerSettingsResult> traderFollowerSettings(TraderFollowerSettingsParam param);

    BaseArrayResult<ConfigResult> traderConfig(String instType);

    BaseArrayResult<ApikeyResult> traderApikey(TraderApikeyParam param);
}
