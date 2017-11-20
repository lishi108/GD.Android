package com.guodong.business.presenter.user;


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
    public void login(String userName, String pwd) {
//        Observable userObservable = Observable.just(userName);
//        Observable passwordObservable = Observable.just(pwd);
//        Observable.combineLatest(userObservable, passwordObservable, new BiFunction<String,String,Boolean>() {
//            @Override
//            public Boolean apply(@NonNull String o, @NonNull String o2) throws Exception {
//                if(!StringUtils.checkPhoneNumber(o)){
//                    ToastUtil.showToast(getView().getContext(), R.string.phone_login_badnumber);
//                    return Boolean.FALSE;
//                }
//                if(StringUtils.checkNullString(o2)||o2.length()<6||o2.length()>12){
//                    ToastUtil.showToast(getView().getContext(),R.string.phone_login_badpwd);
//                    return Boolean.FALSE;
//                }
//                return Boolean.TRUE;
//            }
//        }).
//
//        mModel.Login(userName,pwd)
//                .subscribe(new BaseObserver<User>(getView().getContext(),"login",true) {
//                    @Override
//                    public void onSuccess(User user) {
//                        Logger.e(user.getPhone());
//                        DataManager.saveLoginInfo(user);
//                        DataManager.saveIsFirst(false);
//                        mView.startToActivity(MainActivity.class);
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        ToastUtil.showToast(getView().getContext(),message);
//                    }
//                });

        mModel.Login("222","2222")
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new BaseObserver<ServerModel>(getView().getContext(),true) {
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
                        ToastUtil.showToast(getView().getContext(),message);
                    }
                });

    }

    @Override
    public void login(int type) {

    }

//    @Override
//    public void getTextData(String key) {
//        getModel().getUser("2017-10-29")
//                .subscribe(new BaseObserver<User>(getView().getContext(),key,true) {
//                    @Override
//                    public void onSuccess(User User) {
//                        getView().setText(User.getWeekday());
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        ToastUtil.showToast(getView().getContext(),message);
//                    }
//                })
//        ;
//
//    }
}

