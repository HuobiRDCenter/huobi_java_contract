package com.huobi.api.request.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ContractSubAccountInfoListRequest {
    private String symbol;
    private Integer pageIndex;
    private Integer pageSize;
}
