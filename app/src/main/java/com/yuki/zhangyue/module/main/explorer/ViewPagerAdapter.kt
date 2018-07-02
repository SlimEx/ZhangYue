package com.yuki.zhangyue.module.main.explorer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yuki.zhangyue.app.entity.Channel
import com.yuki.zhangyue.module.main.explorer.book.BookFragment

class ViewPagerAdapter(var data: List<Channel>?, fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment? {
        return BookFragment.newInstance(data?.get(position)?.channelType.toString(), data?.get(position)?.channelId?: 0)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return data?.get(position)?.channelName

    }

    override fun getCount(): Int {
        return data?.size ?: 0
    }

//    override fun getItemPosition(`object`: Any): Int {
//        return PagerAdapter.POSITION_NONE
//    }


}
