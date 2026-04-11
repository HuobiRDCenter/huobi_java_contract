package com.huobi.api.response.usdt.copyTradingTrade;

import lombok.*;

import java.util.List;

/**
 * 参数	数据类型	是否必填	描述
 * label	String	 true	APIKey的备注
 * accessKey	String	 true	API公钥
 * secretKey	String	 true	API的私钥
 * perm	Array of strings	 true	APIKey权限 read:可读 trade:交易 例如:"perm": ["read","trade"]
 * ts	String	 true	创建时间 Unix微秒精度时间戳格式，例如：1759161600000000
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApikeyResult {
    /** APIKey的备注 */
    private String label;

    /** API公钥 */
    private String accessKey;

    /** API的私钥 */
    private String secretKey;

    /** APIKey权限 read:可读 trade:交易 例如:"perm": ["read","trade"] */
    private List<String> perm;

    /** 创建时间 Unix微秒精度时间戳格式，例如：1759161600000000 */
    private String ts;
}
