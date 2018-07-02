package com.yuki.zhangyue.module.main.explorer.book.bookDetail

import com.slim.base.mvp.Presenter
import com.yuki.zhangyue.app.common.applySchedulers
import com.yuki.zhangyue.app.common.toast
import com.yuki.zhangyue.app.entity.Book
import java.util.*

import javax.inject.Inject

class BookDetailPresenter @Inject
constructor(model: BookDetailModel, view: BookDetailActivity) : Presenter<BookDetailModel, BookDetailActivity>(model, view) {


    fun initData(book: Book) {
        mModel.initData(book.id)
            .applySchedulers(mView)
            .subscribe({
                mView.initBookDetail(it)
            }, {
                mView.toast(rxErrorListener?.handleRxError(it).toString())
            })
        initRecommend()
    }

    private fun initRecommend() {
        mModel.initRecommend(Random().nextInt(10)+1, 6)
            .applySchedulers(mView)
            .subscribe({
                mView.initRecommend(it)
            }, {
                mView.toast(rxErrorListener?.handleRxError(it).toString())
            })
    }
}
