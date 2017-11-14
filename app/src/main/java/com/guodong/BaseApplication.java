package com.guodong;

import android.app.Activity;
import android.app.Application;

import com.guodong.business.config.DataManager;
import com.guodong.mvp.AbsActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

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
}
