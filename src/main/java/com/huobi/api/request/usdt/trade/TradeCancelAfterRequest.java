package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TradeCancelAfterRequest {
    @SerializedName("on_off")
    private String onOff;

    @SerializedName("time_out")
    private String timeOut;
}
