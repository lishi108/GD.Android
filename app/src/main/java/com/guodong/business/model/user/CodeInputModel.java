package com.guodong.business.model.user;


import android.support.annotation.NonNull;

import com.guodong.business.bean.LoginInfo;
import com.guodong.business.contract.CodeInputContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.RxSchedulers;
import com.guodong.business.http.callback.JsonConvert;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class CodeInputModel implements CodeInputContract.ICodeInputModel {
    /**
     * 验证码验证
     *
     * @param phoneNumber
     * @param code
     * @return
     * @throws JSONException
     */
    @Override
    public Observable<BaseEntity> justCode(@NonNull String phoneNumber, @NonNull String code) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("mobilePhone", phoneNumber);
        object.put("mobileValidType", 1);
        object.put("code", code);
        return OkGo.<BaseEntity>post("http://userservice.api.guodong.com/Mobile/ValidateCodeAsync")
                .upJson(object.toString())
                .converter(new JsonConvert<BaseEntity>() {
                })
                .adapt(new ObservableBody<BaseEntity>())
                .compose(RxSchedulers.<BaseEntity>io_main());
    }

    @Override
    public Observable<BaseEntity<LoginInfo>> loginPhone(@NonNull String phoneNumber, @NonNull String code) throws JSONException {

        JSONObject params = new JSONObject();
        params.put("phone", phoneNumber);
        params.put("code", code);
        return OkGo.<BaseEntity<LoginInfo>>post("http://userpassport.api.guodong.com/Password/RapidUserLoginAsync")
                .upJson(params.toString())
                .converter(new JsonConvert<BaseEntity<LoginInfo>>() {
                })
                .adapt(new ObservableBody<BaseEntity<LoginInfo>>())
                .compose(RxSchedulers.<BaseEntity<LoginInfo>>io_main());
    }

    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return
     * @throws JSONException
     */
    @Override
    public Observable<BaseEntity> getCodeAgain(@NonNull String phoneNumber) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("mobileNumber", phoneNumber);
        object.put("mobileValidType", 1);
        return OkGo.<BaseEntity>post("http://userservice.api.guodong.com/Mobile/SendValidCodeAsync")
                .upJson(object.toString())
                .converter(new JsonConvert<BaseEntity>() {
                })
                .adapt(new ObservableBody<BaseEntity>())
                .compose(RxSchedulers.<BaseEntity>io_main());
    }

}
