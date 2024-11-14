package com.huobi.api.request.usdt.trade;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TradeOrderRequest {
    @SerializedName("contract_code")
    private String contractCode;

    @SerializedName("margin_mode")
    private String marginMode;

    @SerializedName("position_side")
    private String positionSide;

    @SerializedName("side")
    private String side;

    @SerializedName("type")
    private String type;

    @SerializedName("price_match")
    private String priceMatch;

    @SerializedName("client_order_id")
    private String clientOrderId;

    @SerializedName("price")
    private String price;

    @SerializedName("volume")
    private String volume;

    @SerializedName("reduce_only")
    private Integer reduceOnly;

    @SerializedName("time_in_force")
    private String timeInForce;

    @SerializedName("tp_trigger_price")
    private String tpTriggerPrice;

    @SerializedName("tp_order_price")
    private String tpOrderPrice;

    @SerializedName("tp_type")
    private String tpType;

    @SerializedName("tp_trigger_price_type")
    private String tpTriggerPriceType;

    @SerializedName("sl_trigger_price")
    private String slTriggerPrice;

    @SerializedName("sl_order_price")
    private String slOrderPrice;

    @SerializedName("sl_type")
    private String slType;

    @SerializedName("sl_trigger_price_type")
    private String slTriggerPriceType;
}
