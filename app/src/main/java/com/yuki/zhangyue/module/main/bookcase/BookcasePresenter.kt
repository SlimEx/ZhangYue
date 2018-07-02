package com.yuki.zhangyue.module.main.bookcase

import com.slim.base.mvp.Presenter
import com.yuki.zhangyue.app.entity.BookDao
import javax.inject.Inject

/**
 * 项目：ZhangYue
 * 作者：Yuki- 2018/6/22
 * 邮箱：125508663@qq.com
 */
class BookcasePresenter @Inject
constructor(model: BookcaseModel, view: BookcaseFragment): Presenter<BookcaseModel, BookcaseFragment>(model, view) {

    @BookcaseSort
    private var bookcaseSort: Int = 0
    fun initData(position: Int) {
        val data= mutableListOf<BookDao>()
        mView.initData(data)
    }

    fun getDefaultSelectedSortSpinnerItem(): Int {
        return when (bookcaseSort) {
            BookcaseSort.LATEST_READ_TIME -> 0
            BookcaseSort.MOST_READ_NUMBER -> 1
            BookcaseSort.NAME -> 2
            BookcaseSort.CREATE_TIME -> 3
            else -> 0
        }
    }
}

