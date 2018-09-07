package com.njsoft.retrofittest;

import com.njsoft.retrofittest.utils.UrlConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(UrlConstants.BASE_URL) // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .build();
    }
}
