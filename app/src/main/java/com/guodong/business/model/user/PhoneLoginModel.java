package com.guodong.business.model.user;


import com.guodong.business.contract.PhoneLoginContract;
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

public class PhoneLoginModel implements PhoneLoginContract.IPhoneLoginModel {

    @Override
    public  Observable<BaseEntity> getPhoneCode(String phoneNumber) throws JSONException{
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
}
