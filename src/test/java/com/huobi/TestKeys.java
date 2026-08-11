package com.huobi;

/**
 * 测试用 API Key 工具类。
 *
 * 从环境变量 HTX_ACCESS_KEY / HTX_SECRET_KEY 读取真实密钥，
 * 未设置时回退到空串（保持原有行为，私有接口测试预期抛 ApiException）。
 *
 * 用法：export HTX_ACCESS_KEY=xxx; export HTX_SECRET_KEY=xxx
 * 这样真实 key 不进代码、不进 git。
 */
public final class TestKeys {

    public static final String ACCESS_KEY = System.getenv("HTX_ACCESS_KEY") != null
            ? System.getenv("HTX_ACCESS_KEY") : "";
    public static final String SECRET_KEY = System.getenv("HTX_SECRET_KEY") != null
            ? System.getenv("HTX_SECRET_KEY") : "";

    private TestKeys() {
    }
}
