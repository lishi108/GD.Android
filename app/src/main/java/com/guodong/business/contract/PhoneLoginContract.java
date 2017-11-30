package com.guodong.business.contract;


import android.content.Context;

import com.guodong.mvp.BaseContract;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface PhoneLoginContract {
    interface IPhoneLoginView extends BaseContract.IBaseView {
        void intentToCodeInput(String phone);
    }

    interface IPhoneLoginPresenter extends BaseContract.IBasePresennter {
        void getPhoneLoginCode(@NonNull Context context,@NonNull String phone);
    }

    interface IPhoneLoginModel extends BaseContract.IBaseModel {
        Observable<Integer> getPhoneCode();
    }
}
