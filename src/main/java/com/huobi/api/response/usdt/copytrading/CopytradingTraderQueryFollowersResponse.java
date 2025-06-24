package com.huobi.api.response.usdt.copytrading;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CopytradingTraderQueryFollowersResponse {
    @SerializedName("tid")
    private String tid;

    @SerializedName("data")
    private Data data;

    @SerializedName("code")
    private long code;

    public static class Data {

        @SerializedName("follower")
        private List<Follower> followers;

        @SerializedName("query_id")
        private String queryId;
    }

    public static class Follower {

        @SerializedName("follower_head_pic")
        private String followerHeadPic;

        @SerializedName("follower_name")
        private String followerName;

        @SerializedName("follower_uid")
        private String followerUid;

        @SerializedName("follower_time")
        private long followerTime;

        @SerializedName("follower_profit_amont")
        private String followerProfitAmount;

        @SerializedName("follower_trade_amount")
        private String followerTradeAmount;
    }
}
