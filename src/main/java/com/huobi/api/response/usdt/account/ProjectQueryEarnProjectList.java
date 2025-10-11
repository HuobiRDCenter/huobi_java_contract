package com.huobi.api.response.usdt.account;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProjectQueryEarnProjectList {
    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private PageInfoResVo data;
}

class PageInfoResVo {
    @SerializedName("total")
    private Integer total;

    @SerializedName("items")
    private List<SavingProjectDTO> items;
}

class SavingProjectDTO {
    @SerializedName("projectId")
    private Long projectId;

    @SerializedName("productId")
    private Long productId;

    @SerializedName("calculationType")
    private Integer calculationType;

    @SerializedName("type")
    private Integer type;

    @SerializedName("viewYearRate")
    private BigDecimal viewYearRate;

    @SerializedName("finishAmount")
    private BigDecimal finishAmount;

    @SerializedName("projectStatus")
    private Integer projectStatus;

    @SerializedName("totalAmount")
    private BigDecimal totalAmount;

    @SerializedName("currency")
    private String currency;

    @SerializedName("startAmount")
    private BigDecimal startAmount;

    @SerializedName("apyType")
    private Integer apyType;

    @SerializedName("tieredRates")
    private List<TieredRateVo> tieredRates;

    @SerializedName("marketPerkUpLimit")
    private String marketPerkUpLimit;

    @SerializedName("marketTimeApy")
    private BigDecimal marketTimeApy;

    @SerializedName("marketPerkApy")
    private BigDecimal marketPerkApy;
}

class TieredRateVo {
    @SerializedName("amountStart")
    private BigDecimal amountStart;

    @SerializedName("amountEnd")
    private BigDecimal amountEnd;

    @SerializedName("rate")
    private BigDecimal rate;
}
