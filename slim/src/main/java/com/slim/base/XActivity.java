package com.slim.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.slim.annotation.LayoutId;
import com.slim.base.mvp.IPresenter;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerApplication;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;


/**
 * ================================================================================
 * 一个拥有DataBinding框架的基Activity
 * 这里根据项目业务可以换成你自己熟悉的BaseActivity
 * =================================================================================
 */

public abstract class XActivity<P extends IPresenter> extends RxSupportActivity {

    @Inject
    @Nullable
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getApplication() instanceof DaggerApplication && ((DaggerApplication) getApplication()).activityInjector().maybeInject(this))
            AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        if (useEventBus())
            EventBus.getDefault().register(this);
        initView();
        initData(savedInstanceState);
        initEvent();
    }

    protected void initEvent() {

    }


    protected abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
    }

    protected int initLayoutId() {
        return getClass().getAnnotation(LayoutId.class).value();
    }

    protected void initView() {
    }

    public void initOnClickLister(View.OnClickListener listener, View... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }

    public boolean injectFragmentLifecycle() {
        return true;
    }


    public boolean useEventBus() {
        return false;
    }


}
