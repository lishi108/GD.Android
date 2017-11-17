package com.guodong;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.guodong.business.config.DataManager;
import com.guodong.business.http.OkHttpManager;
import com.guodong.mvp.AbsActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class BaseApplication extends Application {
    private static BaseApplication application;
    private List<AbsActivity> mActivitys = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        application = this;
        DataManager.initDataManage(this);

//        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        HttpHeaders headers = new HttpHeaders();
////        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
////        headers.put("commonHeaderKey2", "commonHeaderValue2");
//        HttpParams params = new HttpParams();
////        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
////        params.put("commonParamsKey2", "这里支持中文参数");
//-------------------------------------------------------------------------------------//

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(OkHttpManager.getOkHttpClient())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3) ;                              //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params);                       //全局公共参数

        refWatcher = setupLeakCanary();


    }

    public static BaseApplication getApplication(){
        return application;
    }

    public void addActivity(AbsActivity mActivity){
        mActivitys.add(mActivity);
    }

    public void finishActivity(AbsActivity mActivity){
        if(mActivity != null){
            mActivitys.remove(mActivity);
        }
    }
    public void finishAll(){
        for(Activity mActivity:mActivitys){
            mActivity.finish();
        }
        System.exit(0);
    }
    protected RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        return application.refWatcher;
    }


}
