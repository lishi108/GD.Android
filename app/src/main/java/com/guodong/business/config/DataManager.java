package com.guodong.business.config;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.guodong.business.bean.LoginInfo;
import com.guodong.utils.SharedPreferencesUtils;

/**
 * Description:
 * Created by Administrator on 2017/11/9.
 */

public class DataManager {
    private static DataManager dataManager;
    private DataManager(){}

    /**
     * 数据中心注册
     * @param context
     */
    public static void initDataManage(Context context){
        SharedPreferencesUtils.init(context, AppConfig.SHARED_FILE_NAME);
        getInstance();
    }

    /**
     * 清理本地的数据
     */
    public void releaseDataManage(){
        SharedPreferencesUtils.clear();
    }
    private static DataManager getInstance(){
        if(dataManager == null){
            synchronized (DataManager.class){
                dataManager = new DataManager();
            }
        }
        return dataManager;
    }
    public static void saveLoginInfo(LoginInfo loginInfo){
        if(loginInfo!=null){
            String userInfo = new Gson().toJson(loginInfo);
            SharedPreferencesUtils.put(AppConfig.LOGINE_KEY,userInfo);
        }else {
            throw new NullPointerException("保存的用户信息为空，请检查");
        }
    }
    public static LoginInfo getLoginInfo(){
        String loginInfo = SharedPreferencesUtils.get(AppConfig.LOGINE_KEY,"");
        if (TextUtils.isEmpty(loginInfo)) {
            return null;
        }
        return new Gson().fromJson(loginInfo,LoginInfo.class);
    }
    public static void saveIsFirst(boolean isFirst){
        SharedPreferencesUtils.put(AppConfig.LOGINE_FIRST_KEY,isFirst);
    }
    public static boolean getIsFirst(){
        return SharedPreferencesUtils.get(AppConfig.LOGINE_FIRST_KEY,true);
    }
}
