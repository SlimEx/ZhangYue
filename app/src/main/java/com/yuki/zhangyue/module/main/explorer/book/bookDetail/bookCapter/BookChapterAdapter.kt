package com.yuki.zhangyue.module.main.explorer.book.bookDetail.bookCapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.BOOK
import com.yuki.zhangyue.app.entity.BookSectionItem
import com.yuki.zhangyue.module.read.ReadActivity

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/26
 * 邮箱：125508663@qq.com
 */

class BookChapterAdapter : BaseQuickAdapter<BookSectionItem, BaseViewHolder>(R.layout.list_item_book_section) {
    override fun convert(helper: BaseViewHolder, item: BookSectionItem) {
        helper.setText(R.id.tvSectionName, item.sectionName)
        helper.itemView.setOnClickListener {
            val intent = Intent(mContext, ReadActivity::class.java)
            intent.putExtra(BOOK, item)
            mContext.startActivity(intent)
        }
    }
}