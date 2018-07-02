package com.yuki.zhangyue.app.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.slim.base.XActivity;
import com.slim.base.mvp.IPresenter;
import com.yuki.zhangyue.R;

/**
 * 项目：ZhangYue
 * 作者：Yuki - 2018/6/23
 * 邮箱：125508663@qq.com
 */
public abstract class ToolbarActivity<P extends IPresenter> extends XActivity<P> {
    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
