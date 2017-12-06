package com.guodong.business.presenter.user;


import android.content.Context;

import com.guodong.business.bean.ServerModel;
import com.guodong.business.contract.LoginContract;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.user.LoginModel;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */
public class LoginPresenter extends BasePresenter<LoginContract.ILoginView,LoginContract.ILoginModel> implements LoginContract.ILoginPresenter{


    @Override
    public LoginContract.ILoginModel loadModel() {
        return new LoginModel();
    }

    @Override
    public void login(@NonNull final Context context, @NonNull String userName, @NonNull String pwd) {

        mModel.Login("222","2222")
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new BaseObserver<ServerModel>(context,true) {
                    @Override
                    public void addonSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(ServerModel user) {
                        Logger.e(user.toString());
                        Logger.e(user.ip);
//                        DataManager.saveIsFirst(false);
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.showToast(context,message);
                    }
                });

    }

    @Override
    public void login(int type) {

    }

//    @Override
//    public void getTextData(String key) {
//        getModel().getUser("2017-10-29")
//                .subscribe(new BaseObserver<User>(context,key,true) {
//                    @Override
//                    public void onSuccess(User User) {
//                        getView().setText(User.getWeekday());
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        ToastUtil.showToast(context,message);
//                    }
//                })
//        ;
//
//    }
}

