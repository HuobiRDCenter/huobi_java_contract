package com.huobi.api.response.usdt.account;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EarnOrderUserAssetsList {
    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private PageInfoResVo1 data;

    @SerializedName("total")
    private Integer total;

    @SerializedName("items")
    private List<UserAssetsInfoCouponExpandResDto> items;
}
class PageInfoResVo1 {

    @SerializedName("total")
    private Integer total;

    @SerializedName("items")
    private List<UserAssetsInfoCouponExpandResDto> items;
}

class UserAssetsInfoCouponExpandResDto {

    @SerializedName("projectId")
    private Long projectId;

    @SerializedName("orderId")
    private Long orderId;

    @SerializedName("projectType")
    private Integer projectType;

    @SerializedName("currency")
    private String currency;

    @SerializedName("yesterdayIncome")
    private String yesterdayIncome;

    @SerializedName("totalIncomeAmount")
    private String totalIncomeAmount;

    @SerializedName("totalAmount")
    private String totalAmount;

    @SerializedName("miningYearRate")
    private String miningYearRate;

    @SerializedName("apyType")
    private Integer apyType;
}