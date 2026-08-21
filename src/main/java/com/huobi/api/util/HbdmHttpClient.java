package com.huobi.api.util;

import com.alibaba.fastjson.JSON;
import com.huobi.api.exception.HttpRequestException;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HbdmHttpClient {

    private OkHttpClient httpClient;


    static final MediaType JSON_TYPE = MediaType.parse("application/json");

    private HbdmHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(200, 10, TimeUnit.SECONDS)).connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS);
        httpClient = builder.build();
    }

    public static HbdmHttpClient getInstance() {
        return new HbdmHttpClient();
    }

    public String doGet(String url, Map<String, Object> params) {
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach((k, v) -> {
                urlBuilder.addQueryParameter(k, v.toString());
            });
        }
        reqBuild.url(urlBuilder.build());

        Response response = null;
        try {
            response = httpClient.newCall(reqBuild.build()).execute();
        } catch (IOException e) {
            throw new HttpRequestException("http执行异常，url=" + url, e);
        }
        if (response.isSuccessful()) {
            try {
                return response.body().string();
            } catch (IOException e) {
                throw new HttpRequestException("http结果解析异常", e);
            }
        } else {
            int statusCode = response.code();
            throw new HttpRequestException("响应码不为200，返回响应码：" + statusCode + "，url：" + urlBuilder.build());
        }
    }


    public String doPost(String appKey, String appSecretKey, String uri, Map<String, Object> params) {
        ApiSignature sign = new ApiSignature();
        sign.createSignature(appKey, appSecretKey, "POST", uri, params);
        try {
            RequestBody body = RequestBody.create(JSON_TYPE, JSON.toJSONString(params));
            Request.Builder builder = new Request.Builder().url(uri + "?" + toQueryString(params)).post(body);
            Request request = builder.build();
            Response response = httpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException("IOException 目标url：" + uri, e);
        }
    }

    /**
     * 发送数组请求体的 POST 请求（V5 批量接口专用）。
     *
     * 按 HTX 签名说明（opend id=5578）：POST 请求业务参数不参与签名，
     * 只对 AccessKeyId/SignatureMethod/SignatureVersion/Timestamp 四个鉴权参数签名，
     * 业务参数放在 body 中。批量接口的 body 是顶层 JSON 数组（无 orders_data 包装）。
     *
     * @param orderList 订单 Map 列表，序列化为 [{...},{...}] 数组作为请求体
     */
    public String doPost(String appKey, String appSecretKey, String uri, List<Map<String, Object>> orderList) {
        Map<String, Object> signParams = new HashMap<>();
        ApiSignature sign = new ApiSignature();
        sign.createSignature(appKey, appSecretKey, "POST", uri, signParams);
        try {
            RequestBody body = RequestBody.create(JSON_TYPE, JSON.toJSONString(orderList));
            Request.Builder builder = new Request.Builder().url(uri + "?" + toQueryString(signParams)).post(body);
            Request request = builder.build();
            Response response = httpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException("IOException 目标url：" + uri, e);
        }
    }


    private String toQueryString(Map<String, Object> params) {
        return String.join("&",
                params.entrySet().stream()
                        .map((entry) -> entry.getKey() + "=" + ApiSignature.urlEncode(entry.getValue().toString()))
                        .collect(Collectors.toList()));
    }

    public String doGetKey(String appKey, String appSecretKey, String url, Map<String, Object> params) {
        ApiSignature sign = new ApiSignature();
        sign.createSignature(appKey, appSecretKey, "GET", url, params);

        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach((k, v) -> {
                urlBuilder.addQueryParameter(k, v.toString());
            });
        }
        reqBuild.url(urlBuilder.build());

        Response response = null;
        try {
            response = httpClient.newCall(reqBuild.build()).execute();
        } catch (IOException e) {
            throw new HttpRequestException("http执行异常，url=" + url, e);
        }
        if (response.isSuccessful()) {
            try {
                return response.body().string();
            } catch (IOException e) {
                throw new HttpRequestException("http结果解析异常", e);
            }
        } else {
            int statusCode = response.code();
            throw new HttpRequestException("响应码不为200，返回响应码：" + statusCode + "，url：" + urlBuilder.build());
        }
    }

}
