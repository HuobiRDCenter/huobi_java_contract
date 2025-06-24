package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderCloseAllPositionResponse {
    @SerializedName("tid")
    private String tid;          // 请求ID

    @SerializedName("data")
    private Boolean data;        // 下单结果

    @SerializedName("code")
    private Long code;         // 状态码
}
