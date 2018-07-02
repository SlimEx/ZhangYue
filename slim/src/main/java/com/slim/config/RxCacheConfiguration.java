package com.slim.config;

import android.content.Context;

import io.rx_cache2.internal.RxCache;


public interface RxCacheConfiguration {
	
	void configRxCache(Context context, RxCache.Builder rxCacheBuilder);
}
