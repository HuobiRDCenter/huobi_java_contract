package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseArrayResult<T> {

    private String code;

    private String msg;

    private List<T> data;
}
