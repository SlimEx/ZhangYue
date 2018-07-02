package com.yuki.zhangyue.module.main.explorer.book

import android.support.annotation.StringDef
import com.yuki.zhangyue.app.common.BOOK
import com.yuki.zhangyue.app.common.BOOK_RANKING

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/23
 * 邮箱：125508663@qq.com
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
@StringDef(BOOK, BOOK_RANKING)
annotation class ChannelType {
}