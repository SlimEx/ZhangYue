package com.slim.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.slim.annotation.LayoutId;
import com.slim.base.mvp.IPresenter;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerApplication;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;


public abstract class XFragment<P extends IPresenter> extends RxSupportFragment  {

    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    private boolean hasLazyInitData;

    @Override
    public void onAttach(Activity activity) {
        if (activity.getApplication() instanceof DaggerApplication && ((DaggerApplication) activity.getApplication()).supportFragmentInjector().maybeInject(this))
            AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initLayoutId(), container, false);
    }

    protected abstract void initData(@Nullable Bundle savedInstanceState);

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (!hasLazyInitData) {
            initDataLazy(savedInstanceState);
            hasLazyInitData = true;
        }

    }

    protected void initDataLazy(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (useEventBus())
            EventBus.getDefault().register(this);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData(savedInstanceState);
        initEvent();
    }

    protected void initView() {

    }

    protected void initEvent() {

    }


    private int initLayoutId() {
        return getClass().getAnnotation(LayoutId.class).value();
    }


    public boolean useEventBus() {
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus())
            EventBus.getDefault().unregister(this);
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }
}
