package com.huobi.api.request.coin_swap.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SwapTrackOpenordersRequest {
    private String contractCode;
    private Integer tradeType;
    private Integer pageIndex;
    private Integer pageSize;
}
