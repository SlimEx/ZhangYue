package com.yuki.zhangyue.module.main.explorer.book.bookDetail.bookCapter

import com.slim.base.mvp.Model
import com.slim.respository.IRepository
import com.yuki.zhangyue.app.Api
import com.yuki.zhangyue.app.entity.BookSectionItem
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class BookChapterModel @Inject
constructor(repository: IRepository) : Model(repository) {
    fun getBookSectionList(bookId: String, isOrderByAsc: Boolean, page: Int, size: Int = 50): Observable<List<BookSectionItem>> {
        val hashMap = HashMap<String, Any>()
        if (isOrderByAsc) {
            hashMap["order"] = "asc"
        } else {
            hashMap["order"] = "desc"
        }
        hashMap["page_index"] = page
        hashMap["page_size"] = size
        return repository.http(Api::class.java)
            .getBookSectionList(bookId)
            .map {
                return@map it.data
            }

    }
}
