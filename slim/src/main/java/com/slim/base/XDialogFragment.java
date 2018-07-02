package com.slim.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.slim.annotation.LayoutId;

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/27
 * 邮箱：125508663@qq.com
 */
public abstract class XDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData(savedInstanceState);
        initEvent();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setGravity(Gravity.CENTER);
    }

    protected void initEvent() {

    }

    protected abstract void initData(Bundle savedInstanceState);

    protected void initView() {

    }

    private int initLayoutId() {
        return getClass().getAnnotation(LayoutId.class).value();
    }
}
