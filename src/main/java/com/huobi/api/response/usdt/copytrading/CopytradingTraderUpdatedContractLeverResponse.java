package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderUpdatedContractLeverResponse {
    @SerializedName("tid")
    private String tid;  // 请求ID

    @SerializedName("data")
    private Data data;   // 数据内容

    @SerializedName("code")
    private long code;   // 状态码

    /**
     * 数据内容类
     */
    public static class Data {
        @SerializedName("contract_code")
        private String contractCode;  // 交易对

        @SerializedName("lever")
        private Integer lever;       // 修改后的杠杆等级

        @SerializedName("margin_mode")
        private String marginMode;    // 保证金模式: isolated(逐仓), cross(全仓)
    }
}
