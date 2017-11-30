package com.guodong.business.presenter.user;


import android.content.Context;

import com.guodong.business.contract.RegisterContract;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.user.RegisterModel;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;
import com.orhanobut.logger.Logger;

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
    public void getCode(@NonNull final Context context, @NonNull String phone) {
//        final long time = 60;
//
//        //获取验证码
        mModel.getCode(phone)
                .subscribe(new BaseObserver<Integer>(context,false) {
                    @Override
                    public void addonSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Logger.e("Register code is:"+integer);
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.showToast(context,message);
                    }
                });
//
//        //倒计时功能
//        Observable.interval(0,1, TimeUnit.SECONDS)
//                .take(time+1)
//                .map(new Function<Long, Long>() {
//                    @Override
//                    public Long apply(@NonNull Long aLong) throws Exception {
//                        return time-aLong;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        mView.setCodeButtonEnable(false);
//                    }
//                })
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Long aLong) {
//                        mView.setCodeButton(aLong+" s后重发");
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        ToastUtil.showToast(context,e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.setCodeButtonEnable(true);
//                        mView.setCodeButton("获取验证码");
//                    }
//                });

    }

    @Override
    public void register(@NonNull Context context) {

    }
}

