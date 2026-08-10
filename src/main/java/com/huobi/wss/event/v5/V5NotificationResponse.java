package com.huobi.wss.event.v5;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * v5 notification 推送通用响应壳。
 * data 为原始 JSON 字符串，具体结构因 topic 而异，由调用方按 topic 解析。
 */
@Data
public class V5NotificationResponse {

    @SerializedName("op")
    private String op;

    @SerializedName("topic")
    private String topic;

    @SerializedName("ts")
    private Long ts;

    @SerializedName("uid")
    private String uid;

    @SerializedName("data")
    private List<String> data;
}
