package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

/**
 * 参数	数据类型	是否必填	描述
 * followerUid	String	 true	跟随者用户id
 * sCode	String	 true	结果代码
 * sMsg	String	 true	结果消息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeleteFollowerResult {

    /** 跟随者用户id */
    private String followerUid;

    /** 结果代码 */
    private String sCode;

    /** 结果消息 */
    private String sMsg;
}
