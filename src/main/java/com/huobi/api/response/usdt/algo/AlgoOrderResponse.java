package com.huobi.api.response.usdt.algo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class AlgoOrderResponse {

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
    private class DataBean {

        @SerializedName("algo_id")
        private String algoId;

        @SerializedName("algo_client_order_id")
        private String algoClientOrderId;
    }
}

