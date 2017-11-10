package com.guodong.app;


import com.guodong.utils.SDCardUtils;

/**
 * Description:APP应用的配置文件
 * Created by LSQ108 on 2017/10/28.
 */

public class AppConfig {
    public static final String BASE_URL = "Http://apicloud.mob.com/appstore/calendar/";
    public static final int SUCCESS_CODE = 000;  //网络请求成功
    public static final int ERROR_TOKEN_CODE = 002;  //token 失效
    public static final int READ_TIME_OUT = 10;  //网络读取超时时间
    public static final int CONNECT_TIME_OUT = 10;  //网络连接超时时间
    public static final int WRITE_TIME_OUT = 10;  //网络连接超时时间
    public static final long CACHE_SIZE = 1024 * 1024 * 100;  //缓存空间
    public static final String BASE_PATH = SDCardUtils.getSDCardPath()+"/";
    public static final String CACHE_PATH = BASE_PATH;



}
