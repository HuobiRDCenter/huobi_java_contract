package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderQueryContractLeverResponse {
    @SerializedName("tid")
    private String tid;  // 请求ID

    @SerializedName("data")
    private Data data;  // 数据对象

    @SerializedName("code")
    private long code;  // 状态码

    public static class Data {
        @SerializedName("contract_code")
        private String contractCode;  // 交易对

        @SerializedName("margin_mode")
        private String marginMode;  // 保证金模式: isolated(逐仓), cross(全仓)

        @SerializedName("lever_rate_range")
        private Integer leverRateRange;  // 允许修改的杠杆倍数范围

        @SerializedName("current_lever_rate")
        private Integer currentLeverRate;  // 用户当前的杠杆倍数
    }

}
