package com.huobi.api.request.usdt.copyTradingTrade;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TraderUnrealizedProfitSharingSummaryParam {

    private String instType;
    private String ccy;
}
