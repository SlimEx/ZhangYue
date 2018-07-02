package com.yuki.zhangyue.module.read

import com.slim.base.mvp.Presenter
import com.yuki.zhangyue.app.common.applySchedulers
import com.yuki.zhangyue.app.common.toast
import io.reactivex.Observable
import javax.inject.Inject

class ReadPresenter @Inject
constructor(model: ReadModel, view: ReadActivity) : Presenter<ReadModel, ReadActivity>(model, view) {
    private var max = 0
    private var readAdapter: ReadAdapter? = null
    fun initData(bookId: String, sectionIndex: Int, readAdapter: ReadAdapter) {
        this.readAdapter = readAdapter
        initSectionList(sectionIndex, bookId)
    }

    private fun initSectionList(sectionIndex: Int, bookId: String) {
        mModel.initSectionList(bookId)
            .applySchedulers(mView)
            .subscribe({
                mView.initSectionList(it)
                initSectionContent(bookId, sectionIndex)
                max = it.size
            }, {
                mView.toast(rxErrorListener?.handleRxError(it).toString())
            })

    }

     fun initSectionContent(bookId: String, sectionIndex: Int) {
        Observable.merge(
            mModel.initSectionContent(bookId, sectionIndex - 1),
            mModel.initSectionContent(bookId, sectionIndex),
            mModel.initSectionContent(bookId, sectionIndex + 1)
        )
            .applySchedulers(mView)
            .subscribe({
                println(it)
                mView.initSectionContent(it)
                if (it.sectionIndex-1==sectionIndex)
                    mView.openSection(sectionIndex)
            }, {
                mView.toast(rxErrorListener?.handleRxError(it).toString())
            })


    }

    fun cacheSectionContent(bookId: String, sectionIndex: Int) {
        if (readAdapter?.hasSection(sectionIndex) == false)
            mModel.initSectionContent(bookId, sectionIndex)
                .applySchedulers(mView)
                .subscribe({
                    mView.initSectionContent(it)
                }, {
                    mView.toast(rxErrorListener?.handleRxError(it).toString())
                })

    }


}
