package com.yuki.zhangyue.module.main.explorer.book.bookDetail

import android.app.Activity
import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.slim.imageloader.ImageLoader
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.BOOK
import com.yuki.zhangyue.app.entity.Book
import java.util.ArrayList

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/26
 * 邮箱：125508663@qq.com
 */
class BookDetailAdapter(data: ArrayList<Book>?) : BaseQuickAdapter<Book, BaseViewHolder>(R.layout.grid_item_bookcase_book,data ) {
    override fun convert(helper: BaseViewHolder, item: Book) {
        ImageLoader.loadImage(helper.getView(R.id.iv_cover),item.coverUrl,R.drawable.ic_book_cover_default)
        helper.setText(R.id.tv_title,item.name)
        helper.setOnClickListener(R.id.iv_cover) {
            val intent = Intent(mContext, BookDetailActivity::class.java)
            intent.putExtra(BOOK, item)
            mContext.startActivity(intent)
            (mContext as Activity).finish()
        }
    }
}