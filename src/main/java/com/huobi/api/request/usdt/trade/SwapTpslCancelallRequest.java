package com.huobi.api.request.usdt.trade;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SwapTpslCancelallRequest {
    private String contractCode;
    private String direction;
    private String pair;
    private String contractType;
}
