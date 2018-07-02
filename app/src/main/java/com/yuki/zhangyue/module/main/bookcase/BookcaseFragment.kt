package com.yuki.zhangyue.module.main.bookcase

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SimpleItemAnimator
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.slim.annotation.LayoutId
import com.slim.base.XFragment
import com.slim.utils.AppKit
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.startActivity
import com.yuki.zhangyue.app.entity.BookDao
import com.yuki.zhangyue.module.main.bookcase.search.BookcaseSortSpinnerAdapter
import com.yuki.zhangyue.module.main.bookcase.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_bookcase.*
import kotlinx.android.synthetic.main.toolbar_bookcase_edit.*

@LayoutId(R.layout.fragment_bookcase)
class BookcaseFragment: XFragment<BookcasePresenter>() {
    private val bookcaseAdapter = BookcaseAdapter()
    lateinit var spinnerAdapter: BookcaseSortSpinnerAdapter
     override fun initData(savedInstanceState: Bundle?) {
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.bookcase)
        toolbarEdit.inflateMenu(R.menu.bookcase_edit)
        spinnerAdapter = BookcaseSortSpinnerAdapter(context, resources.getStringArray(R.array.bookcase_spinner))
        spinnerSort.adapter = spinnerAdapter
        //        mPresenter?.getDefaultSelectedSortSpinnerItem()?.let { spinnerSort.setSelection(it,true) }
        initRecyclerView()
    }
    fun initData(data:MutableList<BookDao>) {
        bookcaseAdapter.setNewData(data)
    }



    override fun initEvent() {
        toolbar.menu.findItem(R.id.action_search).setOnMenuItemClickListener {
            context?.startActivity<SearchActivity>()
            true
        }
        spinnerSort.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                    position: Int, id: Long) {
                val tv = view as TextView
                val drawable = ContextCompat.getDrawable(view.getContext(), R.drawable.ic_triangle)
                drawable!!.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                tv.setCompoundDrawables(null, null, drawable, null)
                tv.setTextColor(Color.WHITE)    //设置颜色
                tv.setPadding(0, 0, 0, 0)
                tv.compoundDrawablePadding = AppKit.dp2px(view.getContext(), 8f) //设置图片和text之间的间距
                tv.textSize = 16.0f    //设置大小
                val paint = tv.paint
                paint.isFakeBoldText = true
                tv.gravity = Gravity.CENTER_VERTICAL or Gravity.START   //设置居中
                spinnerAdapter.setSelectedPosition(position)
                mPresenter?.initData(position)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        toolbarEdit.setOnMenuItemClickListener {
            toggleEditMenu()
            true
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        recyclerView.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        val i = resources.displayMetrics.widthPixels
        val y = AppKit.dp2px(context, 90f)
        val p = AppKit.dp2px(context, 15f)
        val padding = (3 * y + 2 * 3 * p + 2 * p - i) / (2 * 3)

        recyclerView.setPadding(
                padding,
                AppKit.dp2px(context, 8f),
                padding,
                AppKit.dp2px(context, 8f)
        )
        bookcaseAdapter.bindToRecyclerView(recyclerView)
        bookcaseAdapter.setEmptyView(R.layout.view_empty)
    }

    /**
     * 切换菜单栏的可视状态
     * 默认是隐藏的
     */
    private fun toggleEditMenu() {

    }




    companion object {
        fun newInstance(): BookcaseFragment {
            val fragment = BookcaseFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}
