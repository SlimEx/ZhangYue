package com.slim.base.mvp;


import android.app.Application;

import com.slim.respository.IRepository;

import javax.inject.Inject;

/**
 * 项目：SlimEx
 * 作者：Yuki - 2018/3/8
 */

public abstract class Model implements IModel {
    protected IRepository repository;//用于管理网络请求层,以及数据缓存层
    @Inject
    protected Application application;

    public Model(IRepository repository) {
        this.repository = repository;
    }


}
