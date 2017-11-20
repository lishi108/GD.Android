package com.guodong.business.contract;


import com.guodong.business.bean.ServerModel;
import com.guodong.business.bean.User;
import com.guodong.mvp.BaseContract;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface LoginContract {
    interface ILoginView extends BaseContract.IBaseView {
        String getUserName();

        String getPassword();
    }

    interface ILoginPresenter extends BaseContract.IBasePresennter {
        void login(String userName, String pwd);

        void login(int type);
    }

    interface ILoginModel extends BaseContract.IBaseModel {
        //        Observable<User> Login(String userName, String pwd);
        Observable<ServerModel> Login(String userName, String pwd);

        Observable<User> Login(int type);
    }
}
