package com.yuki.zhangyue.app.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;



public class DataList<T> implements Serializable {
    @SerializedName("Count")
    public int count;
    @SerializedName("DataList")
    public ArrayList<T> dataList;
}
