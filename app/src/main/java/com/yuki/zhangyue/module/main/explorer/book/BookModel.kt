package com.yuki.zhangyue.module.main.explorer.book

import com.slim.base.mvp.Model
import com.slim.respository.IRepository
import com.yuki.zhangyue.app.Api
import com.yuki.zhangyue.app.common.BOOK
import com.yuki.zhangyue.app.common.PAGE_SIZE
import com.yuki.zhangyue.app.entity.Book
import io.reactivex.Observable
import javax.inject.Inject

class BookModel @Inject
constructor(repository: IRepository) : Model(repository) {
    private var pageIndex: Int = 1

    fun initData(@ChannelType channelType: String, loadMore: Boolean, channelId: Long): Observable<MutableList<Book>> {
        if (loadMore)
            ++pageIndex
        else
            pageIndex = 1
        val http = repository.http(Api::class.java)
        when (channelType) {
            BOOK ->
                return http
                    .getChannelBooks(channelId, pageIndex, PAGE_SIZE)
                    .map {
                        return@map it.data.dataList
                    }
            else ->
                return http
                    .getChannelBookRanking(channelId, pageIndex, PAGE_SIZE)
                    .map {
                        return@map it.data.dataList
                    }
        }


    }
}
