package com.yuki.zhangyue.module.main.explorer.book.bookDetail.bookCapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.slim.annotation.LayoutId
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.BOOK
import com.yuki.zhangyue.app.common.NAME
import com.yuki.zhangyue.app.common.SECTION_COUNT
import com.yuki.zhangyue.app.common.ToolbarActivity
import com.yuki.zhangyue.app.entity.Book
import com.yuki.zhangyue.app.entity.BookSectionItem
import com.yuki.zhangyue.app.widget.RecyclerViewItemDecoration
import kotlinx.android.synthetic.main.activity_book_chapter.*


@LayoutId(R.layout.activity_book_chapter)
class BookChapterActivity : ToolbarActivity<BookChapterPresenter>() {
    private val bookChapterAdapter: BookChapterAdapter by lazy { BookChapterAdapter() }
    private lateinit var bookId: String
    private var sectionCount: Int = 0
    private var dialog: MaterialDialog? = null


    @SuppressLint("SetTextI18n")
    public override fun initData(savedInstanceState: Bundle?) {
        initSection()
        initChapters()
        mPresenter?.initData(bookId, 1)
        mPresenter?.initSection(sectionCount)

    }

    private fun initChapters() {
        recycler_view.run {
            layoutManager = LinearLayoutManager(this@BookChapterActivity)
            addItemDecoration(RecyclerViewItemDecoration.Builder(this@BookChapterActivity)
                .color(ContextCompat.getColor(this@BookChapterActivity, R.color.colorDivider))
                .thickness(1)
                .create())
            adapter = bookChapterAdapter
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initSection() {
        toolbar.title = intent.getStringExtra(NAME) + "目录"
        sectionCount = intent.getIntExtra(SECTION_COUNT, 50)
        tv_section_count.text = """共${sectionCount}章"""
        bookId = (intent.getSerializableExtra(BOOK) as Book).id

        ll_section_selection.setOnClickListener {
            dialog?.show()
        }

    }

    fun initBookSectionList(data: List<BookSectionItem>) {
        bookChapterAdapter.setNewData(data)
    }

    fun initSectionData(data: List<CharSequence>) {
        tv_section_selection.text = data[0]
        dialog = MaterialDialog.Builder(this)
            .items(data)
            .itemsGravity(GravityEnum.CENTER)
            .dividerColorRes(R.color.colorAccent)
            .itemsCallback { dialog, _, which, text ->
                tv_section_selection.text = text
                mPresenter?.initData(bookId, which + 1)
                dialog.dismiss()
                recycler_view.scrollToPosition(0)
            }
            .build()
        dialog?.recyclerView?.addItemDecoration(RecyclerViewItemDecoration.Builder(this)
            .color(ContextCompat.getColor(this, R.color.colorDivider))
            .thickness(1)
            .create())
    }

}
