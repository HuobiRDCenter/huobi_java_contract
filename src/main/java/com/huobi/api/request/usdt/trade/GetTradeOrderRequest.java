package com.huobi.api.request.usdt.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GetTradeOrderRequest {
    private String contractCode;
    private String marginMode;
    private String orderId;
    private String clientOrderId;
}
