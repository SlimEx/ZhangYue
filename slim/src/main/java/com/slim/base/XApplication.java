package com.slim.base;

import android.app.Application;
import android.content.Context;
import com.slim.base.delegate.ActivityDelegate;
import com.slim.base.delegate.ActivityLifecycleCallback;
import com.slim.config.AppConfiguration;
import com.slim.config.ManifestParser;
import com.slim.di.module.ConfigModule;
import dagger.android.support.DaggerApplication;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 本框架由 MVP + Dagger2 + Retrofit + RxJava2 + RxCache 组成
 * ================================================
 */
public abstract class XApplication extends DaggerApplication {
    protected List<AppConfiguration> mAppConfigurations;
    private List<ActivityLifecycleCallback> mActivityLifecycles;
    protected ActivityDelegate mActivityDelegate;

    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        mAppConfigurations = new ManifestParser(context).parse();
        mActivityLifecycles = new ArrayList<>();
        for (AppConfiguration module : mAppConfigurations) {
            module.injectActivityLifecycle(context, mActivityLifecycles);
        }
        mActivityDelegate = new ActivityDelegate(mAppConfigurations, this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(mActivityDelegate);
        //通过ConfigModule往Activity生命周期注入相关方法
        for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
            registerActivityLifecycleCallbacks(lifecycle);
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mActivityDelegate != null)
            this.unregisterActivityLifecycleCallbacks(mActivityDelegate);

        if (mActivityLifecycles != null && mActivityLifecycles.size() > 0) {
            for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
                this.unregisterActivityLifecycleCallbacks(lifecycle);
            }
        }
        this.mActivityLifecycles = null;
    }

    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     * 需要在AndroidManifest中声明{@link AppConfiguration}的实现类,和Glide的配置方式相似
     */
    protected ConfigModule getConfigModule() {
        if (mAppConfigurations == null)
            throw new RuntimeException("请勿在onCreate前进行dagger初始化");
        ConfigModule.Builder builder = ConfigModule
                .builder();
        for (AppConfiguration module : mAppConfigurations) {
            module.applyOptions(this, builder);
        }

        return builder.build();
    }

}
