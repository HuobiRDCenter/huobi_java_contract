package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderAddMarginResponse {
    @SerializedName("tid")
    private String tid;  // 请求ID

    @SerializedName("data")
    private Data data;   // 响应数据

    @SerializedName("code")
    private long code;   // 状态码

    public static class Data {
        @SerializedName("contract_code")
        private String contractCode;  // 交易对

        @SerializedName("amount")
        private String amount;  // 调整金额

        @SerializedName("type")
        private int type;  // 调整方向 1:增加 2:减少

        @SerializedName("position_side")
        private String positionSide;  // 持仓方向 long/short
    }
}
