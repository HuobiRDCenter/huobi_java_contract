package com.huobi.api.response.usdt.account;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EarnOrderDemandAdd {
    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private OrderSubscribeResDTO data;
}
class OrderSubscribeResDTO {

    @SerializedName("currency")
    private String currency;

    @SerializedName("amount")
    private String amount;

    @SerializedName("orderId")
    private Long orderId;

    @SerializedName("status")
    private Integer status;
}
