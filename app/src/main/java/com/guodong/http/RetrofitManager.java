package com.guodong.http;

import android.util.Log;

import com.guodong.app.AppConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Description:
 * Created by LSQ108 on 2017/10/28.
 */

public class RetrofitManager {
    private static volatile Retrofit retrofit;
    public static Retrofit getInstance(){
       return getInstance(null);
    }
    public static Retrofit getInstance(String baseUrl){
        if(baseUrl == null){
            baseUrl = AppConfig.BASE_URL;
        }
        //开启log
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("HTTP",message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        if(AppConfig.CACHE_PATH!=null&&!AppConfig.CACHE_PATH.equals("")){
            File dir = new File(AppConfig.CACHE_PATH);
            if(!dir.exists()) dir.mkdirs();
        }
        File cacheFile = new File(AppConfig.CACHE_PATH,"cache");
        Cache cache = new Cache(cacheFile,AppConfig.CACHE_SIZE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .retryOnConnectionFailure(true)
                .readTimeout(AppConfig.READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(AppConfig.CONNECT_TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(AppConfig.WRITE_TIME_OUT,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit;
    }
}
