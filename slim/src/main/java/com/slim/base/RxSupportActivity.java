package com.slim.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.slim.base.mvp.IView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 项目：Slim
 * 作者：Sagiri - 2018/1/6
 * 邮箱：125508663@qq.com
 **/
public abstract class RxSupportActivity extends SupportActivity implements IView<ActivityEvent> {
	private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
	
	@Override
	@NonNull
	@CheckResult
	public final Observable<ActivityEvent> lifecycle() {
		return lifecycleSubject.hide();
	}
	
	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
		return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
	}
	
	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindToLifecycle() {
		return RxLifecycleAndroid.bindActivity(lifecycleSubject);
	}
	
	@Override
	@CallSuper
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lifecycleSubject.onNext(ActivityEvent.CREATE);
	}
	
	@Override
	@CallSuper
	protected void onStart() {
		super.onStart();
		lifecycleSubject.onNext(ActivityEvent.START);
	}
	
	@Override
	@CallSuper
	protected void onResume() {
		super.onResume();
		lifecycleSubject.onNext(ActivityEvent.RESUME);
	}
	
	@Override
	@CallSuper
	protected void onPause() {
		lifecycleSubject.onNext(ActivityEvent.PAUSE);
		super.onPause();
	}
	
	@Override
	@CallSuper
	protected void onStop() {
		lifecycleSubject.onNext(ActivityEvent.STOP);
		super.onStop();
	}
	
	@Override
	@CallSuper
	protected void onDestroy() {
		lifecycleSubject.onNext(ActivityEvent.DESTROY);
		super.onDestroy();
	}
}
