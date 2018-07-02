package com.yuki.zhangyue.module.main.bookcase;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@IntDef({BookcaseSort.CREATE_TIME, BookcaseSort.LATEST_READ_TIME, BookcaseSort.MOST_READ_NUMBER, BookcaseSort.NAME})
public @interface BookcaseSort {
    int CREATE_TIME = 0;
    int LATEST_READ_TIME = 1;
    int MOST_READ_NUMBER = 2;
    int NAME = 3;

}
