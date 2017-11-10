package com.guodong.business.model.user;


import com.guodong.business.bean.User;
import com.guodong.business.contract.LoginContract;
import com.guodong.http.RxSchedulers;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public Observable<User> Login(String userName, String pwd) {
        User user = new User();
        user.setPhone("17781139662");
        user.setNickName("LSQ108");
        return Observable.just(user)
                .compose(RxSchedulers.<User> io_main());
    }

    @Override
    public Observable<User> Login(int type) {
        return null;
    }

//    @Override
//    public Observable<User> getCalendar(String date) {
//        return ((GDService)Api.getDefaultService())
//                .calendarBean(date)
//                .map(new ComFunction<User>())
//                .compose(RxSchedulers.<User> io_main() )
//                ;
//
//    }
}
