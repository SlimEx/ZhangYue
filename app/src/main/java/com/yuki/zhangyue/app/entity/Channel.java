package com.yuki.zhangyue.app.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 项目：ZhangYue
 * 作者：Yuki- 2018/6/23
 * 邮箱：125508663@qq.com
 */
public class Channel implements Serializable {

    /**
     * channel_id : 1
     * channel_name : 精选
     * channel_type : book-ranking
     * content_type : json
     * content_query_id : featured
     * selected_status : 2
     */
    public static final int STATUS_SELECTED = 1;
    public static final int STATUS_UNSELECTED = 0;
    public static final int STATUS_FIXED = 2;
    @SerializedName("channel_id")
    public long channelId;

    @SerializedName("channel_name")
    public String channelName;

    @SerializedName("channel_type")
    public String channelType;

    @SerializedName("content_query_id")
    public String contentQueryId;

    @SerializedName("content_type")
    public String contentType;

    @SerializedName("selected_status")
    public int selectedStatus;


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Channel) {
            Channel channel = (Channel) obj;
            return this.channelId == channel.channelId;
        }
        return super.equals(obj);
    }


}
