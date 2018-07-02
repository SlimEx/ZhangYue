package com.yuki.zhangyue.app.common

import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.widget.Toast
import com.slim.base.mvp.IView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.DecimalFormat
import java.util.*

/**
 * 项目：Xndroid
 * 作者：Yuki - 2018/1/23
 * 邮箱：125508663@qq.com
 **/


fun formatW(value: Long): String {
    if (value >= 10000) {
        val temp = value / 10000f
        val decimalFormat = DecimalFormat()
        decimalFormat.applyPattern("#.##万")
        return decimalFormat.format(temp)
    }
    return value.toString()
}

/**
 * 时间友好显示
 * 刚刚-%s分钟前-%s小时前-昨天-前天-%s天前
 */
fun formatSomeAgo(timeInMillis: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillis
    val mCurrentDate = Calendar.getInstance()
    val crim = mCurrentDate.timeInMillis // current
    val trim = calendar.timeInMillis // target
    val diff = crim - trim

    val year = mCurrentDate.get(Calendar.YEAR)
    val month = mCurrentDate.get(Calendar.MONTH)
    val day = mCurrentDate.get(Calendar.DATE)

    if (diff < 60 * 1000) {
        return "刚刚"
    }
    if (diff >= 60 * 1000 && diff < AlarmManager.INTERVAL_HOUR) {
        return String.format("%s分钟前", diff / 60 / 1000)
    }
    mCurrentDate.set(year, month, day, 0, 0, 0)
    if (trim >= mCurrentDate.timeInMillis) {
        return String.format("%s小时前", diff / AlarmManager.INTERVAL_HOUR)
    }
    mCurrentDate.set(year, month, day - 1, 0, 0, 0)
    if (trim >= mCurrentDate.timeInMillis) {
        return "昨天"
    }
    mCurrentDate.set(year, month, day - 2, 0, 0, 0)
    if (trim >= mCurrentDate.timeInMillis) {
        return "前天"
    }
    if (diff < AlarmManager.INTERVAL_DAY * 30) {
        return String.format("%s天前", diff / AlarmManager.INTERVAL_DAY)
    }
    return if (diff < AlarmManager.INTERVAL_DAY * 30 * 12) {
        String.format("%s月前", diff / (AlarmManager.INTERVAL_DAY * 30))
    } else String.format("%s年前", mCurrentDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR))
}


//线程切换
fun <T> Observable<T>.applySchedulers(mView: IView<*>): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(mView.bindToLifecycle())

}

var toast: Toast? = null

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    if (toast == null)
        toast = Toast.makeText(this, msg, duration)
    toast?.show()
}

inline fun <reified Y : Activity> Context.startActivity() {
    this.startActivity(Intent(this, Y::class.java))
}


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
inline fun <reified Y : Activity> Context.startActivity(options: ActivityOptionsCompat?) {
    this.startActivity(Intent(this, Y::class.java), options?.toBundle())
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, msg, duration).show()
}