package com.yuki.zhangyue.module.main.bookcase;

import android.support.annotation.IdRes;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by zchu on 16-12-1.
 * 如有其他定制化的封装可以自行添加
 */

public class DragViewHolder extends BaseViewHolder {
    public DragViewHolder(View view) {
        super(view);
    }

    public DragViewHolder setSelected(@IdRes int viewId, boolean selected) {
        getView(viewId).setSelected(selected);
        return this;
    }

}
