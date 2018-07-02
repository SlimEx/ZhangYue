package com.yuki.zhangyue.module.main.explorer.book

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.slim.imageloader.ImageLoader
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.formatW
import com.yuki.zhangyue.app.entity.Book

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/25
 * 邮箱：125508663@qq.com
 */
class BookAdapter: BaseQuickAdapter<Book, BaseViewHolder>(R.layout.list_item_book) {
    override fun convert(helper: BaseViewHolder, item: Book) {
        ImageLoader.loadImage(helper.getView(R.id.iv_cover), item.coverUrl, R.drawable.ic_book_cover_default)
        helper
            .setText(R.id.tv_author, item.author)
            .setText(R.id.tv_describe, item.describe)
            .setText(R.id.tv_is_finish, if (item.isFinished) mContext.getString(R.string.book_finished) else mContext.getString(R.string.book_unfinished))
            .setText(R.id.tv_word_count, formatW(item.bookWordNum) + "字")
            .setText(R.id.tv_title, item.name)
    }


}