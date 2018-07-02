package com.yuki.zhangyue.app;

import android.content.Context;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.slim.base.XApplication;
import com.slim.di.module.ClientModule;
import com.yuki.zhangyue.BuildConfig;
import com.yuki.zhangyue.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/22
 * 邮箱：125508663@qq.com
 */
public class App extends XApplication {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        PrettyFormatStrategy prettyFormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // 隐藏线程信息 默认：显示
            .methodCount(1)         // 决定打印多少行（每一行代表一个方法）默认：2
            .tag("Xmvp")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build();
        Logger.addLogAdapter(new AndroidLogAdapter(prettyFormatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
            .clientModule(new ClientModule(this))
            .configModule(getConfigModule())
            .build();
    }
}
