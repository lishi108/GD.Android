package com.guodong.business.presenter.user;


import android.content.Context;

import com.guodong.business.contract.PhoneLoginContract;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.user.PhoneLoginModel;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */
public class PhoneLoginPresenter extends BasePresenter<PhoneLoginContract.IPhoneLoginView,PhoneLoginContract.IPhoneLoginModel> implements PhoneLoginContract.IPhoneLoginPresenter{

    @Override
    public PhoneLoginContract.IPhoneLoginModel loadModel() {
        return new PhoneLoginModel();
    }

    @Override
    public void getPhoneLoginCode(@NonNull final Context context, @NonNull final String phone) {
        mModel.getPhoneCode().subscribe(new BaseObserver<Integer>(context,true) {
            @Override
            public void addonSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(Integer integer) {
                mView.intentToCodeInput(phone);
            }

            @Override
            public void onError(String message) {
                ToastUtil.showToast(context,message);
            }
        });
    }
}

