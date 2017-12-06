package com.guodong.business.model.user;


import com.guodong.business.bean.ServerModel;
import com.guodong.business.bean.User;
import com.guodong.business.config.AppConfig;
import com.guodong.business.contract.LoginContract;
import com.guodong.business.http.BaseEntity;
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

public class LoginModel implements LoginContract.ILoginModel {
//    @Override
//    public Observable<User> Login(String userName, String pwd) {
//        User user = new User();
//        user.setPhone("17781139662");
//        user.setNickName("LSQ108");
//        return Observable.just(user)
//                .compose(RxSchedulers.<User> io_main());
//    }

    @Override
    public Observable<ServerModel> Login(String userName, String pwd) {

        return OkGo.<BaseEntity<ServerModel>>get(AppConfig.URL_JSONOBJECT)
                .headers("aaa","111")
                .params("bbb","222")
                .converter(new JsonConvert<BaseEntity<ServerModel>>(){})
                .adapt(new ObservableBody<BaseEntity<ServerModel>>())
                .subscribeOn(Schedulers.io())
//                .compose(RxSchedulers.<BaseEntity<ServerModel>>io_main())
//                .map(new ComFunction<ServerModel>());
                .map(new Function<BaseEntity<ServerModel>, ServerModel>() {
                    @Override
                    public ServerModel apply(@NonNull BaseEntity<ServerModel> serverModelBaseEntity) throws Exception {
                        return serverModelBaseEntity.getData();
                    }
                });

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
