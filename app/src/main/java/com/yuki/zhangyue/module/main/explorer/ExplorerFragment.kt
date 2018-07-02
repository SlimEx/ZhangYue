package com.yuki.zhangyue.module.main.explorer

import android.os.Bundle
import com.slim.annotation.LayoutId
import com.slim.base.XFragment
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.entity.Channel
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_explorer.*

@LayoutId(R.layout.fragment_explorer)
class ExplorerFragment: XFragment<ExplorerPresenter>() {

    private var viewPagerAdapter: ViewPagerAdapter? = null

     override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.initChannel()
    }

    fun initChannel(selectChannels: MutableList<Channel>) {
        viewPagerAdapter = ViewPagerAdapter(selectChannels, childFragmentManager)
        vpContent.adapter = viewPagerAdapter
        tabExplore.setupWithViewPager(vpContent)
    }

    override fun initEvent() {
        ivBookMore.setOnClickListener {
            mPresenter?.goToBookTypeActivity()
        }
    }

    fun notifyData(newData: ArrayList<Channel>?, currentIndex: Int) {
        viewPagerAdapter?.data = newData
        viewPagerAdapter?.notifyDataSetChanged()
        vpContent.setCurrentItem(currentIndex, false)
    }

    fun getTabCurrentName(): String? {
        return vpContent.adapter?.getPageTitle(vpContent.currentItem).toString()
    }

    companion object {

        fun newInstance(): ExplorerFragment {
            val fragment = ExplorerFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}
