package com.guodong.business.model.user;


import com.guodong.business.bean.User;
import com.guodong.business.contract.RegisterContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.RxSchedulers;
import com.guodong.business.http.callback.JsonConvert;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class RegisterModel implements RegisterContract.IRegisterModel {

    /**
     * 获取验证码
     * @param phoneNumber
     * @return
     * @throws JSONException
     */
    @Override
    public Observable<BaseEntity> getCode(@NonNull String phoneNumber) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("mobileNumber", phoneNumber);
        object.put("mobileValidType", 2);
        return OkGo.<BaseEntity>post("http://userservice.api.guodong.com/Mobile/SendValidCodeAsync")
                .upJson(object.toString())
                .converter(new JsonConvert<BaseEntity>() {
                })
                .adapt(new ObservableBody<BaseEntity>())
                .compose(RxSchedulers.<BaseEntity>io_main());

    }

    @Override
    public Observable<User> register(String phone, String code, String pwd, String pwdTwo) {
//        return Api.getDefaultService().getRegister(phone,code,pwd,pwdTwo)
//                .compose(RxSchedulers.<User> io_main());
        User user = new User();
        user.setPhone("17781139662");
        user.setNickName("LSQ108");
        return Observable.just(user)
                .compose(RxSchedulers.<User>io_main());
    }
}
