package com.yuki.zhangyue.module.main.explorer.book

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.slim.annotation.LayoutId
import com.slim.base.XFragment
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.BOOK
import com.yuki.zhangyue.app.common.BUNDLE_CHANNEL_ID
import com.yuki.zhangyue.app.common.BUNDLE_CHANNEL_TYPE
import com.yuki.zhangyue.app.entity.Book
import com.yuki.zhangyue.app.widget.RecyclerViewItemDecoration
import com.yuki.zhangyue.module.main.explorer.book.bookDetail.BookDetailActivity
import kotlinx.android.synthetic.main.fragment_book.*
import java.io.Serializable

@LayoutId(R.layout.fragment_book)
class BookFragment : XFragment<BookPresenter>() {

    private val bookAdapter: BookAdapter by lazy { BookAdapter() }
    override fun initData(savedInstanceState: Bundle?) {
        println(arguments?.getLong(BUNDLE_CHANNEL_ID))
        initRecyclerView()
    }

    override fun initDataLazy(savedInstanceState: Bundle?) {
        mPresenter?.initData(false)
        stateLayout.loading()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(RecyclerViewItemDecoration.Builder(context)
                .color(ContextCompat.getColor(_mActivity, R.color.colorDivider))
                .thickness(1)
                .create())
            //初始化adapter
            adapter = bookAdapter.apply {
                setOnLoadMoreListener({
                    mPresenter?.initData(true)
                }, recyclerView)
                setOnItemClickListener { adapter, view, position ->
                    val intent = Intent(context, BookDetailActivity::class.java)
                    intent.putExtra(BOOK, adapter.data[position] as Serializable)
                    startActivity(intent)
                }
            }
        }
    }

    override fun initEvent() {
        swipeRefresh.setOnRefreshListener {
            mPresenter?.initData(false)
        }
    }


    fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    fun initNewData(dataList: MutableList<Book>) {
        stateLayout.content()
        bookAdapter.setNewData(dataList)
    }

    fun showNewError() {
        if (bookAdapter.data.size==0)
            stateLayout.error()
    }
    fun initMoreData(data: MutableList<Book>) {
        bookAdapter.addData(data)
        bookAdapter.loadMoreComplete()
    }

    fun showMoreError() {
        bookAdapter.loadMoreFail()
    }

    fun showMoreEnd() {
        bookAdapter.loadMoreEnd()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideLoading()
    }




    companion object {
        fun newInstance(@ChannelType channelType: String, channelId: Long): BookFragment {
            val fragment = BookFragment()
            val args = Bundle()
            args.putString(BUNDLE_CHANNEL_TYPE, channelType)
            args.putLong(BUNDLE_CHANNEL_ID, channelId)
            fragment.arguments = args
            return fragment
        }
    }

}


