package com.yuki.zhangyue.module.main.explorer

import android.content.Intent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.slim.base.mvp.Presenter
import com.yuki.zhangyue.app.common.*
import com.yuki.zhangyue.app.entity.Channel
import com.yuki.zhangyue.app.event.OnSelectionEditFinishEvent
import com.yuki.zhangyue.app.utils.DataHelper
import com.yuki.zhangyue.module.main.explorer.channelManager.ChannelManagerActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class ExplorerPresenter @Inject
constructor(model: ExplorerModel, view: ExplorerFragment) : Presenter<ExplorerModel, ExplorerFragment>(model, view) {

    @Inject
    lateinit var gson: Gson


    private lateinit var localChannels: ArrayList<Channel>
    fun initChannel() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
        //如果是第一次启动则先从本地获取数据
        localChannels = gson.fromJson<ArrayList<Channel>>(DataHelper.getStringSF(mView.context, LOCAL_CHANNEL, ChannelToJson), object : TypeToken<ArrayList<Channel>>() {}.type)
        mView.initChannel(splitSelectChannel(localChannels))

        //获取网路数据
        mModel.getChannel()
            .applySchedulers(mView)
            .subscribe { data ->
                data.forEach {
                    if (!localChannels.contains(it))
                        localChannels.add(it)
                }
            }
    }

    private fun splitSelectChannel(data: ArrayList<Channel>): ArrayList<Channel> {
        val channels = ArrayList<Channel>()
        data.forEach {
            when {
                it.selectedStatus == Channel.STATUS_SELECTED ->
                    channels.add(it)
                it.selectedStatus == Channel.STATUS_FIXED ->
                    channels.add(0, it)
            }
        }
        return channels
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun subscribeEvent(messageEvent: OnSelectionEditFinishEvent) {
        localChannels = messageEvent.newChannels
        mView.notifyData(messageEvent.selectChannels, messageEvent.index)
    }

    private fun saveChannelsToLocal() {
        DataHelper.setStringSF(mView.context, LOCAL_CHANNEL, gson.toJson(localChannels))
    }

    fun goToBookTypeActivity() {
        val intent = Intent(mView.context, ChannelManagerActivity::class.java)
        intent.putExtra(EXTRA_CHANNEL, localChannels)
        intent.putExtra(EXTRA_SELECTED_NAME, mView.getTabCurrentName())
        mView.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        saveChannelsToLocal()
    }

}
