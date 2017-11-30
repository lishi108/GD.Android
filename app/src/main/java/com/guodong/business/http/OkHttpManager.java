package com.guodong.business.http;

import android.support.annotation.NonNull;

import com.guodong.BaseApplication;
import com.guodong.business.config.AppConfig;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Description:
 * Created by Administrator on 2017/11/16.
 */

public class OkHttpManager {
    private static volatile OkHttpClient client;
    public static OkHttpClient getOkHttpClient(){
        if(client == null){
            synchronized (OkHttpManager.class){
                if(client == null){
                    //开启log
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(@NonNull String message) {
                            Logger.e("HTTP", message);
                        }
                    });
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                    //缓存
//                    if (AppConfig.CACHE_PATH != null && !AppConfig.CACHE_PATH.equals("")) {
//                        File dir = new File(AppConfig.CACHE_PATH);
//                        if (!dir.exists()) dir.mkdirs();
//                    }
//                    File cacheFile = new File(AppConfig.CACHE_PATH, "cache");
//                    Cache cache = new Cache(cacheFile, AppConfig.CACHE_SIZE);
                    client = new OkHttpClient.Builder()
                            .readTimeout(AppConfig.READ_TIME_OUT, TimeUnit.SECONDS)
                            .connectTimeout(AppConfig.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(AppConfig.WRITE_TIME_OUT, TimeUnit.SECONDS)
                            .addInterceptor(loggingInterceptor)
                            //使用sp保持cookie，如果cookie不过期，则一直有效
                            .cookieJar(new CookieJarImpl(new SPCookieStore(BaseApplication.getApplication().getBaseContext())))
                            .addNetworkInterceptor(loggingInterceptor)
//                            .cache(cache)
                            .build();
                }
            }
        }
        return client;
    }
}
