package com.huobi.wss.event;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountSubResponse {
    /**
     * 操作名称
     * 固定值：notify
     */
    @SerializedName("op")
    private String op;

    /**
     * 推送的主题
     * 固定值：account
     */
    @SerializedName("topic")
    private String topic;

    /**
     * 服务端应答时间戳
     */
    @SerializedName("ts")
    private Long ts;

    /**
     * 账户变化通知相关事件说明
     * 事件类型：
     * snapshot：首推快照
     * create_order: 订单下单
     * cancel_order: 订单撤单
     * delivered：交割
     * transferred：划转
     * filled：成交
     * liquidation：强平
     * adl：ADL自动减仓
     * funding_fee：资金费
     * set_leverage：设置杠杆
     * price_update ：价格更新 （api 用户不要）
     * auto_exchange：自动兑换
     */
    @SerializedName("event")
    private String event;

    /**
     * 用户uid
     */
    @SerializedName("uid")
    private String uid;

    /**
     * 账户数据
     */
    @SerializedName("data")
    private List<AccountData> data;

    /**
     * 账户数据明细
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountData {

        /**
         * 账户状态
         * 1(NORMAL): 正常（交易，划转）
         * 2(LOCKED): 暂时未用到
         * 3(LIQUIDATING)：强平中，不允许交易、划转、收取资金费
         * 4(BANKRUPTCY): 暂时未用到
         * 5(ADL): adl中，不允许交易、收取资金费、划转
         * 6(OPEN_LIMIT): 风控撤单中，合约交易只允许下减仓单，可以转入，不允许转出
         */
        @SerializedName("state")
        private String state;

        /**
         * 账户权益
         */
        @SerializedName("equity")
        private String equity;

        /**
         * 初始保证金
         */
        @SerializedName("initial_margin")
        private String initialMargin;

        /**
         * 维持保证金
         */
        @SerializedName("maintenance_margin")
        private String maintenanceMargin;

        /**
         * 维持保证金率
         */
        @SerializedName("maintenance_margin_rate")
        private String maintenanceMarginRate;

        /**
         * 未实现盈亏
         */
        @SerializedName("profit_unreal")
        private String profitUnreal;

        /**
         * 可用保证金
         */
        @SerializedName("available_margin")
        private String availableMargin;

        /**
         * 体验金(usdt)
         */
        @SerializedName("voucher_value")
        private String voucherValue;

        /**
         * 账户创建时间
         */
        @SerializedName("created_time")
        private String createdTime;

        /**
         * 账户更新时间
         */
        @SerializedName("updated_time")
        private String updatedTime;

        /**
         * 版本号
         */
        @SerializedName("version")
        private String version;

        /**
         * 币种详情列表
         */
        @SerializedName("details")
        private List<CurrencyDetail> details;
    }

    /**
     * 币种详情
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrencyDetail {

        /**
         * 币种
         */
        @SerializedName("currency")
        private String currency;

        /**
         * 该币种资产权益
         */
        @SerializedName("equity")
        private String equity;

        /**
         * 该币种逐仓资产权益
         */
        @SerializedName("isolated_equity")
        private String isolatedEquity;

        /**
         * 该币种可用
         */
        @SerializedName("available")
        private String available;

        /**
         * 该币种可转
         */
        @SerializedName("withdraw_available")
        private String withdrawAvailable;

        /**
         * 该币种全仓未实现盈亏
         */
        @SerializedName("profit_unreal")
        private String profitUnreal;

        /**
         * 该币种逐仓未实现盈亏
         */
        @SerializedName("isolated_profit_unreal")
        private String isolatedProfitUnreal;

        /**
         * 该币种初始保证金
         */
        @SerializedName("initial_margin")
        private String initialMargin;

        /**
         * 该币种维持保证金
         */
        @SerializedName("maintenance_margin")
        private String maintenanceMargin;

        /**
         * 该币种维持保证金率
         */
        @SerializedName("maintenance_margin_rate")
        private String maintenanceMarginRate;

        /**
         * 该币种初始保证金率
         */
        @SerializedName("initial_margin_rate")
        private String initialMarginRate;

        /**
         * 体验金
         */
        @SerializedName("voucher")
        private String voucher;

        /**
         * 体验金(usdt)
         */
        @SerializedName("voucher_value")
        private String voucherValue;

        /**
         * 创建时间
         */
        @SerializedName("created_time")
        private String createdTime;

        /**
         * 更新时间
         */
        @SerializedName("updated_time")
        private String updatedTime;
    }
}
