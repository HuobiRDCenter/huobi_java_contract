package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

/**
 * 参数	数据类型	是否必填	描述
 * enable	Boolean	 true	当前带单开启状态 true 开启 false 关闭
 * profitSharingRatio	String	 true	当前分润比例 带单状态为开启时存在。 目前支持档位： 0.1（10%分润） 0.15（15%分润） 0.20（20%分润） 0.25（25%分润）
 * maxFollowers	String	 true	跟单人数，带单状态为开启时存在。 默认为100。 目前支持档位： 100 200 500 1000
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigResult {

    /** 当前带单开启状态 true 开启 false 关闭 */
    private Boolean enable;

    /** 当前分润比例 带单状态为开启时存在。 目前支持档位： 0.1（10%分润） 0.15（15%分润） 0.20（20%分润） 0.25（25%分润） */
    private String profitSharingRatio;

    /** 跟单人数，带单状态为开启时存在。 默认为100。 目前支持档位： 100 200 500 1000 */
    private String maxFollowers;
}
