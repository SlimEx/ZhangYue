package com.yuki.zhangyue.module.main.explorer.book.bookDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.slim.annotation.LayoutId
import com.slim.imageloader.ImageLoader
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.*
import com.yuki.zhangyue.app.entity.Book

import com.yuki.zhangyue.app.entity.BookDetail
import com.yuki.zhangyue.app.entity.DataList
import com.yuki.zhangyue.module.main.explorer.book.bookDetail.bookCapter.BookChapterActivity
import com.yuki.zhangyue.module.read.ReadActivity
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.layout_book_detail_catalog.*
import kotlinx.android.synthetic.main.layout_book_detail_copyright.*
import kotlinx.android.synthetic.main.layout_book_detail_header.*
import kotlinx.android.synthetic.main.layout_book_detail_recommend.*

@LayoutId(R.layout.activity_book_detail)
class BookDetailActivity : ToolbarActivity<BookDetailPresenter>(), View.OnClickListener {

    private val book: Book by lazy { intent.getSerializableExtra(BOOK) as Book }
    private var bookDetail: BookDetail? = null

    public override fun initData(savedInstanceState: Bundle?) {
        initBookInfo()
        bindOnClickLister(this, R.id.fl_add_bookcase, R.id.fl_download_book, R.id.fl_open_book, R.id.ll_book_detail_catalog)
        mPresenter?.initData(book)
    }


    @SuppressLint("SetTextI18n")
    private fun initBookInfo() {
        toolbar.title = book.name
        ImageLoader.loadImage(iv_cover, book.coverUrl, R.drawable.ic_book_cover_default)
        tv_read_count.text = "${formatW(book.clickNum)}人读过"
        tv_is_finish.text = if (book.isFinished) "已完结" else "连载中"
        tv_author.text = "${book.bookTypeName} | ${book.author}"
        tv_word_count.text = "${formatW(book.bookWordNum)}字"
        tv_word_count_copyright.text = "${tv_word_count_copyright.text as String}${formatW(book.bookWordNum)}字"
        tv_create_date_copyright.text = tv_create_date_copyright.text as String + book.createDateTime
        tv_describe.text = book.describe
    }

    private fun bindOnClickLister(listener: View.OnClickListener, @IdRes vararg ids: Int) {
        for (id in ids) {
            findViewById<View?>(id)?.setOnClickListener(listener)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fl_open_book -> {
                val intent = Intent(this, ReadActivity::class.java)
                intent.putExtra(BOOK, book)
                startActivity(intent)
            }
            R.id.fl_add_bookcase ->
                toast("已加入书架")
            R.id.ll_book_detail_catalog ->
                if (bookDetail != null) {
                    val intent = Intent(this, BookChapterActivity::class.java)
                    intent.putExtra(BOOK, book)
                    intent.putExtra(NAME, book.name)
                    intent.putExtra(SECTION_COUNT, bookDetail?.chapterCount ?: 0)
                    startActivity(intent)
                }
        }
    }


    @SuppressLint("SetTextI18n")
    fun initBookDetail(data: BookDetail) {
        bookDetail = data
        when {
            data.book.isFinished -> {
                tv_catalog_title.text = """查看目录：共${data.chapterCount}章"""
                tv_update_time.text = "已完结"
            }
            else -> {
                tv_catalog_title.text = """最新章节：${data.latestChapter.chapterName}"""
                val aLong = java.lang.Long.parseLong("""${data.latestChapter.createTime}000""")
                tv_update_time.text = formatSomeAgo(aLong)
            }
        }
    }

    fun initRecommend(it: DataList<Book>?) {
        rv_recommend_book.run {
            layoutManager = GridLayoutManager(this@BookDetailActivity, 3)
            adapter = BookDetailAdapter(it?.dataList)
        }
    }


}
