package com.guodong.business.api;

import com.guodong.business.bean.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description:
 * Created by LSQ108 on 2017/10/29.
 */

public interface ApiService {
//    @GET("day?key=1863a50c31d7c")
//    Observable<String> calendarJson(@Query("date") String date);
//
//    @GET("day?key=1863a50c31d7c")
//    Observable<BaseEntity<Calendar>> calendarBean(@Query("date") String date);

    /**
     * 获取注册验证码
     * @param phoneNumber
     * @return
     */
    @FormUrlEncoded
    @POST()
    Observable<Integer> getRegisterCode(@Field("phone") String phoneNumber);

    /**
     * 注册
     * @param phoneNumber
     * @return
     */
    @FormUrlEncoded
    @POST()
    Observable<User> getRegister(@Field("phone") String phoneNumber,
                                 @Field("code") String code,
                                 @Field("pwd") String pwd,
                                 @Field("pwdTwo") String pwdTwo);
}
