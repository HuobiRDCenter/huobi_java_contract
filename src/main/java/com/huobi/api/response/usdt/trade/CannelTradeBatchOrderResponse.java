package com.huobi.api.response.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CannelTradeBatchOrderResponse {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<DataBean> data;
    @SerializedName("message")
    private String message;
    @SerializedName("ts")
    private Long ts;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataBean {
        @SerializedName("order_id")
        private String orderId;

        @SerializedName("client_order_id")
        private String clientOrderId;
    }
}
