package com.njsoft.retrofittest;

import android.util.Log;

import com.njsoft.retrofittest.api.WanAndroidInterface;
import com.njsoft.retrofittest.utils.UrlConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 基础接口
 *
 * @author cuiernao
 */
public class ApiManager {
    private WanAndroidInterface apiService;
    private static ApiManager apiManager;

    public synchronized static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public WanAndroidInterface getApiService() {
        if (apiService == null) {
            //日志
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("RxJava", message);
                }
            });
            //这行必须加 不然默认不打印
            /**
             * setlevel用来设置日志打印的级别，共包括了四个级别：NONE,BASIC,HEADER,BODY
             BASEIC:请求/响应行
             HEADER:请求/响应行 + 头
             BODY:请求/响应航 + 头 + 体
             */
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(UrlConstants.BASE_URL)
                    .client(client)
                    // 支持RxJava平台
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(WanAndroidInterface.class);
        }

        return apiService;
    }

}