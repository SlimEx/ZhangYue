package com.yuki.zhangyue.di

import com.yuki.zhangyue.module.main.explorer.ExplorerFragment
import com.yuki.zhangyue.module.main.explorer.book.BookFragment
import com.yuki.zhangyue.module.main.explorer.book.bookDetail.BookDetailActivity
import com.yuki.zhangyue.module.main.explorer.book.bookDetail.bookCapter.BookChapterActivity
import com.yuki.zhangyue.module.read.ReadActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/23
 * 邮箱：125508663@qq.com
 */
@Module
abstract class AppModule {
    @ContributesAndroidInjector
    abstract fun explorerFragment(): ExplorerFragment

    @ContributesAndroidInjector
    abstract fun bookFragment(): BookFragment

    @ContributesAndroidInjector
    abstract fun bookDetailActivity(): BookDetailActivity

    @ContributesAndroidInjector
    abstract fun readActivity(): ReadActivity

    @ContributesAndroidInjector
    abstract fun bookchapterActivity(): BookChapterActivity


}