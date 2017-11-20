package com.guodong.business.contract;


import com.guodong.mvp.BaseContract;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface PhoneLoginContract {
    interface IPhoneLoginView extends BaseContract.IBaseView {
        void intentToCodeInput(String phone);
    }

    interface IPhoneLoginPresenter extends BaseContract.IBasePresennter {
        void getPhoneLoginCode(String phone);
    }

    interface IPhoneLoginModel extends BaseContract.IBaseModel {
        Observable<Integer> getPhoneCode();
    }
}
