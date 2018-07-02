package com.yuki.zhangyue.app.event

import com.yuki.zhangyue.app.entity.Channel
import java.util.*

class OnSelectionEditFinishEvent(val newChannels: ArrayList<Channel>, val selectChannels: ArrayList<Channel>, var index: Int)

