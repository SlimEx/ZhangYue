package com.slim.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.slim.R;

/**
 * 项目：Test
 * 作者：Yuki - 2018/7/2
 * 邮箱：125508663@qq.com
 */
public class StateLayout extends FrameLayout {

    private View errorView;
    private View loadingView;
    private View emptyView;
    private int errorLayoutRes;
    private int loadingLayoutRes;
    private int emptyRes;
    private LayoutInflater inflater;
    private View contentView;

    public StateLayout(@NonNull Context context) {
        this(context, null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (getChildCount() > 1)
            throw new RuntimeException("StateLayout必须只有一个子View");
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StateLayout);
        try {
            errorLayoutRes = array.getResourceId(R.styleable.StateLayout_error, 0);
            loadingLayoutRes = array.getResourceId(R.styleable.StateLayout_loading, 0);
            emptyRes = array.getResourceId(R.styleable.StateLayout_empty, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
    }

    public void empty() {
        if (emptyView == null)
            emptyView = inflater.inflate(emptyRes, this, false);
         if (getChildAt(1) != emptyView) {
            addView(emptyView,1);
        }
    }

    public void loading() {
        if (loadingView == null)
            loadingView = inflater.inflate(loadingLayoutRes, this, false);
         if (getChildAt(1) != loadingView) {
            addView(loadingView,1);
        }
    }

    public void content() {
        if (getChildAt(1)!=null)
        removeViewAt(1);
    }

    public void error() {
        if (errorView == null)
            errorView = inflater.inflate(errorLayoutRes, this, false);
         if (getChildAt(1) != errorView) {
            addView(errorView,1);
        }
    }

}
