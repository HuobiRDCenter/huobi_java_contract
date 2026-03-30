package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

/**
 * 参数	数据类型	是否必填	描述
 * instType	String	 true	产品类型 PERP 永续合约
 * enable	Boolean	 true	开启或关闭跟单 true 开启 false 关闭
 * profitSharingRatio	String	 true	分润比例，开启跟单时可配。 目前支持档位： 0.1（10%分润） 0.15（15%分润） 0.20（20%分润） 0.25（25%分润）
 * maxFollowers	String	 true	跟单人数上限，开启跟单时可配。 默认为100。 目前支持档位： 100 200 500 1000
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowerSettingsResult {

    /** 产品类型 PERP 永续合约 */
    private String instType;

    /** 开启或关闭跟单 true 开启 false 关闭 */
    private Boolean enable;

    /** 分润比例，开启跟单时可配。 目前支持档位： 0.1（10%分润） 0.15（15%分润） 0.20（20%分润） 0.25（25%分润） */
    private String profitSharingRatio;

    /** 跟单人数上限，开启跟单时可配。 默认为100。 目前支持档位： 100 200 500 1000 */
    private String maxFollowers;
}
