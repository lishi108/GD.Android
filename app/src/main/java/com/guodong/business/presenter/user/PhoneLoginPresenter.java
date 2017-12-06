package com.guodong.business.presenter.user;


import android.content.Context;

import com.guodong.business.contract.PhoneLoginContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.user.PhoneLoginModel;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */
public class PhoneLoginPresenter extends BasePresenter<PhoneLoginContract.IPhoneLoginView, PhoneLoginContract.IPhoneLoginModel> implements PhoneLoginContract.IPhoneLoginPresenter {

    @Override
    public PhoneLoginContract.IPhoneLoginModel loadModel() {
        return new PhoneLoginModel();
    }

    @Override
    public void getPhoneLoginCode(@NonNull final Context context, @NonNull final String phone) {
        try {
            mModel.getPhoneCode(phone)
                    .subscribe(new BaseObserver<BaseEntity>(context, true) {
                        @Override
                        public void addonSubscribe(@NonNull Disposable d) {
                            addDisposable(d);
                        }

                        @Override
                        public void onSuccess(BaseEntity data) {
                            Logger.e("Register code is:" + data);
                            if ((Boolean) data.getData())
                                mView.intentToCodeInput(phone);
                            else{
                                ToastUtil.showToast(context, data.getMsg());
                            }

                        }

                        @Override
                        public void onError(String message) {
                            ToastUtil.showToast(context, message);
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtil.showToast(context, e.getMessage());
        }
    }
}

