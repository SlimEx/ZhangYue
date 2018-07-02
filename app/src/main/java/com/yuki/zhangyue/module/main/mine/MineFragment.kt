package com.yuki.zhangyue.module.main.mine

import android.os.Bundle

import com.slim.annotation.LayoutId
import com.slim.base.XFragment
import com.slim.base.mvp.IPresenter
import com.yuki.zhangyue.R

@LayoutId(R.layout.fragment_mine)
class MineFragment: XFragment<IPresenter>() {
    override fun initData(savedInstanceState: Bundle?) {
    }


    companion object {

        fun newInstance(): MineFragment {
            val fragment = MineFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}
