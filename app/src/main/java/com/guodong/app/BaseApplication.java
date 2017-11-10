package com.guodong.app;

import android.app.Activity;
import android.app.Application;

import com.guodong.mvp.AbsActivity;

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
//        if (BuildConfig.DEBUG) {
//            ARouter.openLog();     // 打印日志
//            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        application = this;
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
