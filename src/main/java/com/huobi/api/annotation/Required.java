package com.huobi.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记 Response DTO 字段为文档必填字段。
 * 测试断言工具 AssertFields 优先断言标了此注解的字段非 null；
 * 若类中无任何 @Required 字段，回退为断言所有 @SerializedName 字段。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
}
