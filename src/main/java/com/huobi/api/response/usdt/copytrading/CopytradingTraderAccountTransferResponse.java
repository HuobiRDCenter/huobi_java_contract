package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderAccountTransferResponse {
    @SerializedName("tid")
    private String tid;          // 请求ID

    @SerializedName("data")
    private boolean data;       // 提交结果：true-成功，false-失败

    @SerializedName("code")
    private long code;          // 状态码
}
