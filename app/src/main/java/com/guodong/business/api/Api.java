package com.guodong.business.api;


import com.guodong.business.config.AppConfig;
import com.guodong.http.RetrofitManager;

/**
 * Description:
 * Created by LSQ108 on 2017/10/29.
 */

public class Api {
    private static ApiService apiService;
    public static ApiService getDefaultService(){
        if(apiService == null){
            synchronized (Api.class){
                apiService = RetrofitManager.getInstance(AppConfig.BASE_URL).create(ApiService.class);
            }
        }
        return apiService;
    }
}
