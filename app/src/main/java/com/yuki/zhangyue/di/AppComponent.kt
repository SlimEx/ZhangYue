package com.yuki.zhangyue.di

import com.slim.di.module.ClientModule
import com.slim.di.module.ConfigModule
import com.yuki.zhangyue.app.App

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [(AppModule::class), (ClientModule::class), (ConfigModule::class), (AndroidSupportInjectionModule::class)])
interface AppComponent: AndroidInjector<App>
