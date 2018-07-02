package com.yuki.zhangyue.module.main

import android.os.Bundle
import android.view.MenuItem
import com.slim.annotation.LayoutId
import com.slim.base.XActivity
import com.slim.base.XFragment
import com.slim.base.mvp.IPresenter
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.toast
import com.yuki.zhangyue.module.main.bookcase.BookcaseFragment
import com.yuki.zhangyue.module.main.explorer.ExplorerFragment
import com.yuki.zhangyue.module.main.mine.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

@LayoutId(R.layout.activity_main)
class MainActivity : XActivity<IPresenter>() {
    private val mFragments by lazy { arrayOfNulls<XFragment<*>>(4) }
    override fun initData(savedInstanceState: Bundle?) {
        when (savedInstanceState) {
            null -> {
                mFragments[0] = BookcaseFragment.newInstance()
                mFragments[1] = ExplorerFragment.newInstance()
                mFragments[2] = MineFragment.newInstance()
                supportDelegate.loadMultipleRootFragment(R.id.container, 1, mFragments[0], mFragments[1], mFragments[2])
            }
            else -> {
                mFragments[0] = findFragment(BookcaseFragment::class.java)
                mFragments[1] = findFragment(ExplorerFragment::class.java)
                mFragments[2] = findFragment(MineFragment::class.java)
            }
        }
    }

     override fun initEvent() {
        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.tab_bookcase -> {
                    supportDelegate.showHideFragment(mFragments[0])
                }
                R.id.tabExplore -> {
                    supportDelegate.showHideFragment(mFragments[1])
                }
                R.id.tab_mine -> {
                    supportDelegate.showHideFragment(mFragments[2])
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    //	实现2s重复点击退出app
    private var mExitTime: Long = 0

    override fun onBackPressedSupport() {
        if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
            super.onBackPressedSupport()
        } else {
            mExitTime = System.currentTimeMillis()
            toast("再按一次退出程序")
        }
    }
}
