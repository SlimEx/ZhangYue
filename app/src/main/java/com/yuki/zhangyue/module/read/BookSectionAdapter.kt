package com.yuki.zhangyue.module.read

import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.entity.BookSectionItem


class BookSectionAdapter : BaseQuickAdapter<BookSectionItem, BaseViewHolder>(R.layout.list_item_book_section) {

    private var textColor = -1

    override fun convert(helper: BaseViewHolder, item: BookSectionItem) {
        val textView = helper.getView<TextView>(R.id.tvSectionName)
        textView.text = item.sectionName
        if (textColor == -1) {
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.textPrimary))
        } else {
            textView.setTextColor(textColor)
        }
    }

    fun setTextColor(color: Int) {
        textColor = color
        notifyDataSetChanged()
    }
}
