package com.yuki.zhangyue.module.main.explorer.book

import com.slim.base.mvp.Presenter
import com.yuki.zhangyue.app.common.*
import javax.inject.Inject

class BookPresenter @Inject
constructor(model: BookModel, view: BookFragment) : Presenter<BookModel, BookFragment>(model, view) {

    private val channelId: Long by lazy { mView.arguments?.getLong(BUNDLE_CHANNEL_ID) ?: 0 }
    private val channelType: String by lazy { mView.arguments?.getString(BUNDLE_CHANNEL_TYPE).toString() }
    fun initData(loadMore: Boolean) {
        mModel.initData(channelType, loadMore, channelId)
            .applySchedulers(mView)
            .doFinally {
                mView.hideLoading()
            }
            .subscribe({
                when (loadMore) {
                    true -> {
                        mView.initMoreData(it)
                        if (it.size < PAGE_SIZE)
                            mView.showMoreEnd()
                    }
                    else -> mView.initNewData(it)
                }
            }, {
                when (loadMore) {
                    true -> {
                        mView.showMoreError()
                    }
                    else -> {
                        mView.showNewError()
                        mView.toast(rxErrorListener?.handleRxError(it).toString())
                    }
                }
            })
    }
}
