package com.guodong.business.presenter.user;


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
    public void getPhoneLoginCode(final String phone) {
        mModel.getPhoneCode().subscribe(new BaseObserver<Integer>(mView.getContext(),true) {
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
                ToastUtil.showToast(mView.getContext(),message);
            }
        });
    }
}

