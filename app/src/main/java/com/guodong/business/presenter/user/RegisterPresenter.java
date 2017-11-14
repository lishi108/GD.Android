package com.guodong.business.presenter.user;


import com.guodong.business.contract.RegisterContract;
import com.guodong.business.model.user.RegisterModel;
import com.guodong.http.BaseObserver;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
    public void getCode() {
        final long time = 60;

        //获取验证码
        mModel.getCode(mView.getPhone())
                .subscribe(new BaseObserver<Integer>(mView.getContext(),"register",false) {
                    @Override
                    public void onSuccess(Integer integer) {
                        Logger.e(integer+"");
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.showToast(mView.getContext(),message);
                    }
                });

        //倒计时功能
        Observable.interval(0,1, TimeUnit.SECONDS)
                .take(time+1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return time-aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.setCodeButtonEnable(false);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        mView.setCodeButton(aLong+" s后重发");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtil.showToast(mView.getContext(),e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.setCodeButtonEnable(true);
                        mView.setCodeButton("获取验证码");
                    }
                });

    }

    @Override
    public void register() {

    }
}

