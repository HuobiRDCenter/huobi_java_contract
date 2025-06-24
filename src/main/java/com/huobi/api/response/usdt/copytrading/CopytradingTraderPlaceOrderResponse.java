package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderPlaceOrderResponse {
    @SerializedName("tid")
    private String tid;          // 请求ID

    @SerializedName("data")
    private boolean data;       // 下单结果

    @SerializedName("code")
    private long code;          // 状态码
}
