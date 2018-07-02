package com.yuki.zhangyue.app.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/25
 * 邮箱：125508663@qq.com
 */
public class Book implements Serializable {
    /**
     * BookId : 0a4d9876-fa33-4bfa-a76f-3f2833674745
     * BookName : 混沌战尊
     * BookAuthor : 作者：蓝色蝌蚪
     * BookOneType : 6
     * BookOneTypeName : 玄幻小说
     * BookIntro : 意外来到异世，获得阴阳混沌决，他需要让自己强大起来，来解开封印的功法，笑看异世风云，拥红颜知己，战天下群豪。
     * BookImg : http://pic.quanshuwu.com/files/book/1/3954/201610100732144489.jpg
     * IsFinish : true
     * BookWordNum : 3353200
     */


    @SerializedName("book_id")
    public String id;

    @SerializedName("book_img")
    public String coverUrl;

    @SerializedName("book_name")
    public String name;

    @SerializedName("book_intro")
    public String describe;

    @SerializedName("book_author")
    public String author;

    @SerializedName("is_finish")
    public boolean isFinished;

    @SerializedName("book_type_id")
    public int bookTypeId;

    @SerializedName("book_type_name")
    public String bookTypeName;

    @SerializedName("book_word_num")
    public long bookWordNum;

    @SerializedName("click_num")
    public long clickNum;

    @SerializedName("collection_num")
    public long collectionNum;

    @SerializedName("recommend_num")
    public long recommendNum;

    @SerializedName("create_time")
    public String createDateTime;

    @SerializedName("chapter_count")
    public Integer chapterCount;

}
