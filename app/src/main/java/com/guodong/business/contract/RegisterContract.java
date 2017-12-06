package com.guodong.business.contract;


import android.content.Context;

import com.guodong.business.bean.User;
import com.guodong.business.http.BaseEntity;
import com.guodong.mvp.BaseContract;

import org.json.JSONException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface RegisterContract {
    interface IRegisterView extends BaseContract.IBaseView{
       void intentToCodeInput(String phoneNumber);
    }
    interface  IRegisterPresenter extends BaseContract.IBasePresennter{
        /**
         * 获取验证码
         */
        void getCode(@NonNull Context context,@NonNull String phone);
    }
    interface IRegisterModel  extends BaseContract.IBaseModel{
        /**
         * 获取验证码
         */
        Observable<BaseEntity> getCode(String phoneNumber) throws JSONException;

        /**
         * 注册
         */
        Observable<User> register(String phone, String code, String pwd, String pwdTwo);
    }
}
