package com.yuki.zhangyue.module.read

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.App


enum class ReadThemes(context: Context, @ColorRes pageBackgroundRes: Int, @ColorRes textColorRes: Int) {
    WHITE(App.context, R.color.read_theme_white_page_background, R.color.read_theme_white_text),
    AMBER(App.context, R.color.read_theme_amber_page_background, R.color.read_theme_amber_text),
    GREEN(App.context, R.color.read_theme_green_page_background, R.color.read_theme_green_text),
    BROWN(App.context, R.color.read_theme_brown_page_background, R.color.read_theme_brown_text),
    BLACK(App.context, R.color.read_theme_black_page_background, R.color.read_theme_black_text),
    NIGHT(App.context, R.color.read_theme_night_page_background, R.color.read_theme_night_text),
    DEFAULT(App.context, R.color.read_theme_default_page_background, R.color.read_theme_default_text);

    var pageBackground: Int = 0
    var textColor: Int = 0

    init {
        this.pageBackground = ContextCompat.getColor(context, pageBackgroundRes)
        this.textColor = ContextCompat.getColor(context, textColorRes)
    }

    companion object {
        fun checkReadTheme(pageBackground: Int, textColor: Int, readThemes: ReadThemes): Boolean {
            return readThemes.pageBackground == pageBackground && readThemes.textColor == textColor
        }

        fun getReadTheme(pageBackground: Int, textColor: Int): ReadThemes? {
            for (readTheme in ReadThemes.values()) {
                if (readTheme.pageBackground == pageBackground && readTheme.textColor == textColor) {
                    return readTheme
                }
            }
            return null
        }
    }
}
