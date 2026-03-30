package com.huobi.api.response.usdt.algo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 撤销策略委托订单响应
 */
@Builder
@AllArgsConstructor
@Data
public class CancelAlgoOrdersResponse {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private List<DataBean> data;

    @SerializedName("message")
    private String message;

    @SerializedName("ts")
    private Long ts;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataBean {
        /**
         * 订单ID
         */
        @SerializedName("algo_id")
        private String algoId;

        /**
         * 客户订单ID
         */
        @SerializedName("algo_client_order_id")
        private String algoClientOrderId;

        /**
         * 是否撤销成功
         */
        @SerializedName("success")
        private Boolean success;

        /**
         * 错误信息（撤销失败时返回）
         */
        @SerializedName("error_msg")
        private String errorMsg;

        /**
         * 错误码（撤销失败时返回）
         */
        @SerializedName("error_code")
        private Integer errorCode;
    }
}
