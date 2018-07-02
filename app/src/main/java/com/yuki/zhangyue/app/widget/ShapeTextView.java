package com.yuki.zhangyue.app.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;



public class ShapeTextView extends android.support.v7.widget.AppCompatTextView {

    public ShapeTextView(Context context) {
        super(context);
    }

    public ShapeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSuperShapeView(attrs);
    }

    public ShapeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSuperShapeView(attrs);
    }

    private void initSuperShapeView(AttributeSet attrs) {
        new SuperConfig().beSuperView(attrs, this);
    }
}
