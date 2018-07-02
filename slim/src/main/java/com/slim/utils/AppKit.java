package com.slim.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

/**
 * ================================================
 * 本框架常用的工具
 * ================================================
 */
public class AppKit {
    public static Context context;

    /**
     * 初始化 context
     */
    public static void init(Context context) {
        AppKit.context = context;
    }

    /**
     *
     * @param colorRes
     * @return
     */
    public static int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(AppKit.context, colorRes);
    }
    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * dp转px
     */
    public static int dp2px( float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2px( float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
            spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float px2dp(float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float px2sp( float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }


}
