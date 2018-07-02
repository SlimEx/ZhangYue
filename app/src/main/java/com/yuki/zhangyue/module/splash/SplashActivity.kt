package com.yuki.zhangyue.module.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.slim.annotation.LayoutId
import com.slim.base.XActivity
import com.slim.base.mvp.IPresenter
import com.yuki.zhangyue.R
import com.yuki.zhangyue.app.common.startActivity
import com.yuki.zhangyue.module.main.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

@LayoutId(R.layout.activity_splash)
class SplashActivity: XActivity<IPresenter>() {
    override fun initData(savedInstanceState: Bundle?) {
        startCountDown()
        tvSkip.setOnClickListener {
            toMain()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun startCountDown() {
        Observable.interval(0,1, TimeUnit.SECONDS)
                .map { 3 - it }
                .take(3)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { toMain() }
                .compose(bindToLifecycle())
                .subscribe {
                    tvSkip.text = "跳过 $it"
                }

    }

    private fun toMain() {
        startActivity<MainActivity>()
        finish()
    }

}
