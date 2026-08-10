package com.huobi.wss.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * v5 WebSocket 合约下单 (place_order) 的 data 参数。
 * 对齐文档 #55 请求参数表。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WssPlaceOrderData {

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

    @SerializedName("time_in_force")
    private String timeInForce;

    @SerializedName("client_order_id")
    private String clientOrderId;

    @SerializedName("price")
    private String price;

    @SerializedName("volume")
    private String volume;

    @SerializedName("reduce_only")
    private Integer reduceOnly;

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

    @SerializedName("price_protect")
    private Boolean priceProtect;

    @SerializedName("self_match_prevent")
    private Boolean selfMatchPrevent;
}
