package com.slim.config;

import android.content.Context;

import retrofit2.Retrofit;


public interface RetrofitConfiguration {
	void configRetrofit(Context context, Retrofit.Builder retrofitBuilder);
}
