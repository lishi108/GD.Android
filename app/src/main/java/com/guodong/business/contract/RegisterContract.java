package com.guodong.business.contract;


import com.guodong.business.bean.User;
import com.guodong.mvp.BaseContract;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface RegisterContract {
    interface IRegisterView extends BaseContract.IBaseView{

    }
    interface  IRegisterPresenter extends BaseContract.IBasePresennter{
        /**
         * 获取验证码
         */
        void getCode(@NonNull String phone);

        /**
         * 注册
         */
        void register();
    }
    interface IRegisterModel  extends BaseContract.IBaseModel{
        /**
         * 获取验证码
         */
        Observable<Integer> getCode(String phoneNumber);

        /**
         * 注册
         */
        public Observable<User> register(String phone, String code, String pwd, String pwdTwo);
    }
}
