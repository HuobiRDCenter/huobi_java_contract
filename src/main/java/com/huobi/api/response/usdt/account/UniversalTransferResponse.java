package com.huobi.api.response.usdt.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversalTransferResponse {

    private Integer code;

    private String msg;

    private Long ts;

    private Long data;

}