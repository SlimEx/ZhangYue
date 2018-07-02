package com.slim.base.mvp;

import com.slim.respository.http.RxErrorListener;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * 项目：BookVision
 * 作者：Sagiri - 2018/3/29
 * 邮箱：125508663@qq.com
 */
public abstract class Presenter<M extends IModel, V extends IView> implements IPresenter {
    protected M mModel;
    protected V mView;

    @Nullable
    @Inject
    protected RxErrorListener rxErrorListener;

    public Presenter(M model, V view) {
        mModel = model;
        mView = view;
    }

    public Presenter(V view) {
        mView = view;
    }

    public void onDestroy() {

    }
}
