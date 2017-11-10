package com.guodong.business.model.user;


import com.guodong.business.bean.User;
import com.guodong.business.contract.RegisterContract;
import com.guodong.http.RxSchedulers;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class RegisterModel implements RegisterContract.IRegisterModel {


    @Override
    public Observable<Integer> getCode(String phoneNumber) {
//        return Api.getDefaultService().getRegisterCode(phoneNumber)
//                .compose(RxSchedulers.<Integer> io_main());
        int code = 1234;
        return Observable.just(code).compose(RxSchedulers.<Integer> io_main());
    }

    @Override
    public Observable<User> register(String phone,String code,String pwd,String pwdTwo) {
//        return Api.getDefaultService().getRegister(phone,code,pwd,pwdTwo)
//                .compose(RxSchedulers.<User> io_main());
        User user = new User();
        user.setPhone("17781139662");
        user.setNickName("LSQ108");
        return Observable.just(user)
                .compose(RxSchedulers.<User> io_main());
    }
}
