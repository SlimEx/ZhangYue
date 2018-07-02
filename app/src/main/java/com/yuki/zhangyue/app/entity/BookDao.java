package com.yuki.zhangyue.app.entity;


/**
 * Created by Chu on 2017/5/29.
 */


public class BookDao {
    public String id;

    public String name;

    public String coverUrl;

    public String describe;

    public String author;

    public boolean isFinished;

    public int readNumber; //阅读次数

    public long latestReadTimestamp;//最后一次的阅读时间

    public Integer latestReadSection;//最后一次阅读的章节

    public String latestReadSectionId;//最后一次阅读的章节的id

    public int latestReadPage;//最后一次阅读章节的页码

    public boolean hasUpdate;//是否有新的更新

    public Integer sectionCount;

    public int sort; //保存自定义排序的顺序

    public long createTimestamp;

    public long updateTimestamp;
}
