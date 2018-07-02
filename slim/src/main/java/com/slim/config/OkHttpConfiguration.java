package com.slim.config;

import android.content.Context;

import okhttp3.OkHttpClient;


public interface OkHttpConfiguration {
	void configOkHttp(Context context, OkHttpClient.Builder okHttpBuilder);
}