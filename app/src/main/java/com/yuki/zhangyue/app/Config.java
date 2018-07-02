package com.yuki.zhangyue.app;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.slim.base.delegate.ActivityLifecycleCallback;
import com.slim.config.AppConfiguration;
import com.slim.di.module.ConfigModule;
import com.slim.respository.http.RequestInterceptor;
import com.yuki.zhangyue.app.common.AppRxErrorListener;

import java.util.List;

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/22
 * 邮箱：125508663@qq.com
 */
public class Config implements AppConfiguration {

    @Override
    public void applyOptions(Context context, ConfigModule.Builder configBuilder) {
        configBuilder.baseUrl(Api.BASE_URL)
            .printHttpLogLevel(RequestInterceptor.Level.RESPONSE)
            .handleRxError(new AppRxErrorListener(context));

    }

    @Override
    public void injectActivityLifecycle(Context context, List<ActivityLifecycleCallback> activityLifecycles) {

    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycles) {

    }
}
