package com.huobi;

import com.google.gson.annotations.SerializedName;
import com.huobi.api.annotation.Required;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 测试断言工具：用反射遍历对象所有标了 @SerializedName 的字段，
 * 任一为 null 则断言失败。用于校验 API 返回的必填字段非空。
 */
public final class AssertFields {

    private AssertFields() {
    }

    /**
     * 断言对象所有 @SerializedName 字段非 null。
     * 若对象本身为 null，断言失败。
     * 若类中有任何字段标了 @Required，则只断言 @Required 字段（用于区分文档必填/可选字段）。
     */
    public static void assertAllFieldsNotNull(String msg, Object obj) {
        Assert.assertNotNull(msg + " [对象本身为 null]", obj);
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean hasRequired = false;
        for (Field f : fields) {
            if (f.isAnnotationPresent(Required.class)) {
                hasRequired = true;
                break;
            }
        }
        for (Field f : fields) {
            boolean serial = f.isAnnotationPresent(SerializedName.class);
            boolean required = f.isAnnotationPresent(Required.class);
            if (hasRequired ? required : serial) {
                f.setAccessible(true);
                try {
                    Assert.assertNotNull(msg + " [字段 " + f.getName() + " 为 null]", f.get(obj));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 断言 List 非空且第一个元素的所有 @SerializedName 字段非 null。
     */
    public static void assertListFirstElementFields(String msg, List<?> list) {
        Assert.assertNotNull(msg + " [data 为 null]", list);
        Assert.assertFalse(msg + " [data 列表为空]", list.isEmpty());
        if (list.get(0) != null) {
            assertAllFieldsNotNull(msg, list.get(0));
        }
    }
}
