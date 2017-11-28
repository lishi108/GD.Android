package com.guodong.business.model.user;


import com.guodong.business.bean.User;
import com.guodong.business.contract.RegisterContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.RxSchedulers;
import com.guodong.business.http.callback.JsonConvert;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class RegisterModel implements RegisterContract.IRegisterModel {


    @Override
    public Observable<Integer> getCode(@NonNull String phoneNumber) {
//        return Api.getDefaultService().getRegisterCode(phoneNumber)
//                .compose(RxSchedulers.<Integer> io_main());
//        int code = 1234;
//        return Observable.just(code).compose(RxSchedulers.<Integer> io_main());

        return OkGo.<BaseEntity<Integer>>post("http://userservice.api.guodong.com/Mobile/SendValidCodeAsync")
                .params("mobileNumber",phoneNumber)
                .params("mobileValidType",2)
                .converter(new JsonConvert<BaseEntity<Integer>>(){})
                .adapt(new ObservableBody<BaseEntity<Integer>>())
                .subscribeOn(Schedulers.io())
//                .compose(RxSchedulers.<BaseEntity<ServerModel>>io_main())
//                .map(new ComFunction<ServerModel>());
                .map(new Function<BaseEntity<Integer>, Integer>() {
                    @Override
                    public Integer apply(@NonNull BaseEntity<Integer> serverModelBaseEntity) throws Exception {
                        return serverModelBaseEntity.getData();
                    }
                });


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
