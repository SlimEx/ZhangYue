package com.yuki.zhangyue.app;

import com.yuki.zhangyue.app.entity.*;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.HashMap;
import java.util.List;

/**
 * 项目：ZhangYue
 * 作者：Yuki- 2018/6/23
 * 邮箱：125508663@qq.com
 */
public interface Api {

    String BASE_URL = "http://api.laiyoushu.com";

    /**
     * 获取所有小说
     */
    @GET("/v1/books")
    Observable<HttpResult<DataList<Book>>> getBookList(@Query("page_index") int index, @Query("page_size") int pageSize);

    /**
     * 获取小说详情
     */
    @GET("/v1/books/{bookId}")
    Observable<HttpResult<BookDetail>> getBookDetail(@Path("bookId") String bookId);

    /**
     * 书籍最新章节信息
     */
    @POST("v1/books/latest-chapter")
    Observable<HttpResult<List<LatestChapter>>> getLatestChapter(@Body List<String> bookIds);

    /**
     * 搜索小说
     */
    @GET("/v1/search/books")
    Observable<HttpResult<DataList<Book>>> searchBooks(@QueryMap HashMap<String, Object> map);

    /**
     * 获取小说类别
     */
    @GET("/v1/book-types")
    Observable<HttpResult<List<BookType>>> getBookType();

    /**
     * 获取小说章节列表
     */
    @GET("/v1/books/{bookId}/chapters")
    Observable<HttpResult<List<BookSectionItem>>> getBookSectionList(@Path("bookId") String bookId);


    /**
     * 获取小说章节中的内容
     */
    @GET("/v1/books/{bookId}/chapters/{position}")
    Observable<HttpResult<BookSectionContent>> getBookSectionContent(@Path("bookId") String bookId,
                                                                     @Path("position") int position
    );

    /**
     * 获取所有频道
     */
    @GET("/v1/channels")
    Observable<HttpResult<List<Channel>>> getChannels();

    /**
     * 获取频道内容-榜单
     */
    @GET("/v1/channels/books/{channelId}")
    Observable<HttpResult<DataList<Book>>> getChannelBooks(@Path("channelId") long channelId,
                                                           @Query("page_index") int index, @Query("page_size") int pageSize);

    /**
     * 获取频道内容-书籍列表
     */
    @GET("/v1/channels/book-ranking/{channelId}")
    Observable<HttpResult<DataList<Book>>> getChannelBookRanking(@Path("channelId") long channelId,
                                                                 @Query("page_index") int index, @Query("page_size") int pageSize);


}

