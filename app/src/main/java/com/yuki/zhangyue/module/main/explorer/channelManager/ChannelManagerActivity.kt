package com.yuki.zhangyue.module.main.explorer.channelManager

import android.os.Bundle
import com.slim.annotation.LayoutId
import com.slim.base.mvp.IPresenter
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.EXTRA_CHANNEL
import com.yuki.zhangyue.app.common.EXTRA_SELECTED_NAME
import com.yuki.zhangyue.app.common.ToolbarActivity
import com.yuki.zhangyue.app.entity.Channel
import com.yuki.zhangyue.app.event.OnSelectionEditFinishEvent
import com.zchu.labelselection.Label
import com.zchu.labelselection.LabelSelectionFragment
import com.zchu.labelselection.OnEditFinishListener
import kotlinx.android.synthetic.main.activity_book_type.*
import org.greenrobot.eventbus.EventBus
import java.io.Serializable

@LayoutId(R.layout.activity_book_type)
class ChannelManagerActivity : ToolbarActivity<IPresenter>(), OnEditFinishListener {

    private lateinit var selectedLabels: ArrayList<Label<Serializable>>
    private lateinit var unselectedLabels: ArrayList<Label<Serializable>>
    private lateinit var fixedLabels: ArrayList<Label<Serializable>>

    private lateinit var selectedName: String

    public override fun initData(savedInstanceState: Bundle?) {
        toolbar.title = "全部频道"
        val channels: ArrayList<Channel> = intent.getSerializableExtra(EXTRA_CHANNEL) as ArrayList<Channel>
        val fixedChannels: ArrayList<Channel> = arrayListOf()
        val selectChannels: ArrayList<Channel> = arrayListOf()
        val unSelectChannels: ArrayList<Channel> = arrayListOf()
        channels.forEach {
            when {
                it.selectedStatus == Channel.STATUS_UNSELECTED -> unSelectChannels.add(it)
                it.selectedStatus == Channel.STATUS_SELECTED -> selectChannels.add(it)
                it.selectedStatus == Channel.STATUS_FIXED -> fixedChannels.add(it)
            }
        }

        initFixedLabels(fixedChannels)

        initSelectLabels(selectChannels)

        initUnselectedLabels(unSelectChannels)

        //创建LabelSelectionFragment绑定到你的Activity即可
        selectedName = intent.getStringExtra(EXTRA_SELECTED_NAME)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.content_view, initLabelSelectionFragment())
            .commit()
    }

    private fun initLabelSelectionFragment(): LabelSelectionFragment {
        return LabelSelectionFragment.newInstance(
            selectedLabels,
            unselectedLabels,
            fixedLabels,
            selectedName
        ).apply {
            setOnLabelClickListener { _, item ->
                selectedName = item.label.name
                finish()
            }
        }
    }

    private fun initUnselectedLabels(unSelectChannels: ArrayList<Channel>) {
        //其他标签
        unselectedLabels = ArrayList()
        for (unselectedBookType in unSelectChannels) {
            unselectedLabels.add(Label(unselectedBookType.channelName, unselectedBookType))
        }
    }

    private fun initSelectLabels(selectChannels: ArrayList<Channel>) {
        //创建选择标签
        selectedLabels = ArrayList()
        for (selectedBookType in selectChannels) {
            selectedLabels.add(Label(selectedBookType.channelName, selectedBookType))
        }
    }

    private fun initFixedLabels(fixedChannels: ArrayList<Channel>) {
        //创建固定标签
        fixedLabels = ArrayList()
        for (fixedChannel in fixedChannels) {
            fixedLabels.add(Label(fixedChannel.channelName, fixedChannel))
        }
    }


    override fun onEditFinish(selectedLabels: ArrayList<Label<Serializable>>, unselectedLabel: ArrayList<Label<Serializable>>, alwaySelectedLabels: ArrayList<Label<Serializable>>?) {
        this.selectedLabels = selectedLabels
        this.unselectedLabels = unselectedLabel
    }


    private fun findIndexByName(name: String, selectChannels: ArrayList<Channel>): Int {
        var currentIndex = -1
        for (item in selectChannels.withIndex()) {
            if (name == item.value.channelName)
                currentIndex = item.index + fixedLabels.size
        }
        if (currentIndex == -1)
            return selectChannels.size
        return currentIndex
    }

    override fun finish() {
        notifyDataSetChanged()
        super.finish()
    }

    private fun notifyDataSetChanged() {
        val newChannels: ArrayList<Channel> = ArrayList()
        val selectChannels: ArrayList<Channel> = ArrayList()

        fixedLabels.mapTo(selectChannels) { it.data as Channel }

        selectedLabels.forEach { item ->
            val channel = item.data as Channel
            channel.selectedStatus = Channel.STATUS_SELECTED
            selectChannels.add(channel)
        }
        newChannels.addAll(selectChannels)
        unselectedLabels.forEach { item ->
            val channel = item.data as Channel
            channel.selectedStatus = Channel.STATUS_UNSELECTED
            newChannels.add(channel)
        }

        EventBus.getDefault().post(OnSelectionEditFinishEvent(newChannels, selectChannels, findIndexByName(selectedName, selectChannels)))
    }


}
