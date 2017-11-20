package com.guodong.business.model.user;


import com.guodong.business.contract.PhoneLoginContract;
import com.guodong.business.http.RxSchedulers;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class PhoneLoginModel implements PhoneLoginContract.IPhoneLoginModel {

    @Override
    public  Observable<Integer> getPhoneCode(){
        return Observable.just(1142)
                .compose(RxSchedulers.<Integer>io_main());
    }
}
