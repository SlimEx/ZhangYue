package com.yuki.zhangyue.module.main.explorer.book.bookDetail

import javax.inject.Inject

import com.slim.base.mvp.Model
import com.slim.respository.IRepository
import com.yuki.zhangyue.app.Api
import com.yuki.zhangyue.app.entity.Book
import com.yuki.zhangyue.app.entity.BookDetail
import com.yuki.zhangyue.app.entity.DataList
import io.reactivex.Observable

class BookDetailModel @Inject
constructor(repository: IRepository) : Model(repository) {


    fun initData(id: String): Observable<BookDetail> {
        return repository.http(Api::class.java)
            .getBookDetail(id)
            .map {
                return@map it.data
            }
    }

    fun initRecommend(page: Int, size: Int): Observable<DataList<Book>> {
        return repository.http(Api::class.java)
            .getBookList(page, size)
            .map { return@map it.data }
    }
}
