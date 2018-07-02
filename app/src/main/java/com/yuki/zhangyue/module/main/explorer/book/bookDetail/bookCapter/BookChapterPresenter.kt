package com.yuki.zhangyue.module.main.explorer.book.bookDetail.bookCapter

import com.slim.base.mvp.Presenter
import com.yuki.zhangyue.app.common.PAGE_SIZE
import com.yuki.zhangyue.app.common.applySchedulers
import com.yuki.zhangyue.app.common.toast
import java.util.*
import javax.inject.Inject

class BookChapterPresenter @Inject
constructor(model: BookChapterModel, view: BookChapterActivity) : Presenter<BookChapterModel, BookChapterActivity>(model, view) {


    fun initSection( sectionCount: Int){
        mView.initSectionData(createSectionData(sectionCount))
    }

    fun initData(id: String,page:Int) {
        mModel.getBookSectionList(id, true,page)
            .applySchedulers(mView)
            .subscribe({
                mView.initBookSectionList(it)
            }, {
                mView.toast(rxErrorListener?.handleRxError(it).toString())
            })


    }

    private fun createSectionData(sectionCount: Int): List<String> {
        val sectionTitles = ArrayList<String>()
        val size = sectionCount / 50
        for (i in 0 until size) {
            sectionTitles.add((i * 50 + 1).toString() + "-" + (1 + i) * 50 + "章")
        }
        if (PAGE_SIZE * size < sectionCount) {
            sectionTitles.add((1 + size * 50).toString() + "-" + sectionCount + "章")
        }
        return sectionTitles
    }
}
