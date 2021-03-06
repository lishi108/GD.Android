package com.guodong.business.presenter.user;


import android.content.Context;

import com.guodong.business.contract.RegisterContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.user.RegisterModel;
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
public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView,RegisterContract.IRegisterModel> implements RegisterContract.IRegisterPresenter{


    @Override
    public RegisterContract.IRegisterModel loadModel() {
        return new RegisterModel();
    }

    @Override
    public void getCode(@NonNull final Context context, @NonNull final String phone) {
        //获取验证码
        try {
            mModel.getCode(phone)
                    .subscribe(new BaseObserver<BaseEntity>(context,true) {
                        @Override
                        public void addonSubscribe(@NonNull Disposable d) {
                            addDisposable(d);
                        }

                        @Override
                        public void onSuccess(BaseEntity data) {
                            Logger.e("Register code is:"+data);
                            if(data.getResCode()==200&&data.isSuccess()) mView.intentToCodeInput(phone);
                        }

                        @Override
                        public void onError(String message) {
                            ToastUtil.showToast(context,message);
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtil.showToast(context,e.getMessage());
        }
    }
}

