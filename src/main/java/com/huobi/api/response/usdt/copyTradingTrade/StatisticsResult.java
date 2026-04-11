package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

import java.util.List;

/**
 * 参数	数据类型	是否必填	描述
 * instType	String	 true	产品类型 PERP 永续合约
 * totalFollowerNum	String	 true	累计跟随人数
 * curFollowerNum	String	 true	当前跟随人数
 * totalPnl	String	 true	总收益(USDT)
 * followerTotalPnl	String	 true	跟单者总收益(USDT)
 * winRate	String	 true	胜率 例如： 0.1 代表 10%
 * winNum	String	 true	盈利带单笔数
 * lossNum	String	 true	亏损带单笔数
 * profitDetails90d	Array of objects	 true	最近90天内收益详情
 * > pnlRate	String	 true	收益率 例如： 0.1 代表 10%
 * > pnl	String	 true	收益(USDT)
 * > ts	String	 true	结算周期，精度为微秒时间戳。 结算单位为天，代表对应收益信息为该天之内产生。 例如：1759161600000000 对应时间是2025-09-30 00:00:00.000 ，代表在25年9月30号这一天内产生的收益。
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatisticsResult {

    /** 产品类型 PERP 永续合约 */
    private String instType;

    /** 累计跟随人数 */
    private String totalFollowerNum;

    /** 当前跟随人数 */
    private String curFollowerNum;

    /** 总收益(USDT) */
    private String totalPnl;

    /** 跟单者总收益(USDT) */
    private String followerTotalPnl;

    /** 胜率 例如： 0.1 代表 10% */
    private String winRate;

    /** 盈利带单笔数 */
    private String winNum;

    /** 亏损带单笔数 */
    private String lossNum;

    /** 最近90天内收益详情 */
    private List<ProfitDetail> profitDetails90d;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ProfitDetail {

        /** 收益率 例如： 0.1 代表 10% */
        private String pnlRate;

        /** 收益(USDT) */
        private String pnl;

        /** 结算周期，精度为微秒时间戳。 结算单位为天，代表对应收益信息为该天之内产生。 例如：1759161600000000 对应时间是2025-09-30 00:00:00.000 ，代表在25年9月30号这一天内产生的收益。 */
        private String ts;
    }
}
