package com.guodong.business.contract;


import com.guodong.business.bean.User;
import com.guodong.mvp.BaseContract;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface RegisterContract {
    interface IRegisterView extends BaseContract.IBaseView{
        /**
         * 获取手机号码
         * @return
         */
        String getPhone();

        /**
         * 设置倒计时时间
         * @param time
         */
        void setCodeButton(String time);

        /**
         * 获取验证码编辑内容
         * @return
         */
        String getCode();

        /**
         * 获取密码编辑内容
         * @return
         */
        String  getPwd();

        /**
         * 获取确认密码编辑内容
         * @return
         */
        String getPwdAgain();

        /**
         * 获取验证码Button是否高亮可点击
         * @param enable
         */
        void setCodeButtonEnable(boolean enable);

        /**
         * 获取注册Button是否高亮可点击
         * @param enable
         */
        void setRegisterButtonEnable(boolean enable);
    }
    interface  IRegisterPresenter extends BaseContract.IBasePresennter{
        /**
         * 获取验证码
         */
        void getCode();

        /**
         * 注册
         */
        void register();
    }
    interface IRegisterModel  extends BaseContract.IBaseModel{
        /**
         * 获取验证码
         */
        public Observable<Integer> getCode(String phoneNumber);

        /**
         * 注册
         */
        public Observable<User> register(String phone, String code, String pwd, String pwdTwo);
    }
}
