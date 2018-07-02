package com.yuki.zhangyue.module.read

import com.slim.base.mvp.Model
import com.slim.respository.IRepository
import com.yuki.zhangyue.app.Api
import com.yuki.zhangyue.app.entity.BookSectionContent
import com.yuki.zhangyue.app.entity.BookSectionItem
import io.reactivex.Observable
import javax.inject.Inject

class ReadModel @Inject
constructor(repository: IRepository) : Model(repository) {
    fun initSectionList(bookId: String, updata: Boolean = false): Observable<List<BookSectionItem>> {
        return repository.http(Api::class.java)
            .getBookSectionList(bookId)
            .map {
                return@map it.data
            }

    }

    fun initSectionContent(bookId: String, sectionIndex: Int): Observable<BookSectionContent> {
        return repository.http(Api::class.java)
            .getBookSectionContent(bookId, sectionIndex)
            .map {
                return@map it.data
            }

    }
}
