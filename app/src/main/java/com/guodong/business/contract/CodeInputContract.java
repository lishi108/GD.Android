package com.guodong.business.contract;


import android.content.Context;
import android.support.annotation.NonNull;

import com.guodong.business.bean.LoginInfo;
import com.guodong.business.http.BaseEntity;
import com.guodong.mvp.BaseContract;

import org.json.JSONException;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface CodeInputContract {
    interface ICodeInputView extends BaseContract.IBaseView {
        void intentToMain();
        void changeTimeCode();
    }

    interface ICodeInputPresenter extends BaseContract.IBasePresennter {
        void justCode(Context context,String phone, String code);
        void getCodeAgain(@NonNull final Context context, @NonNull final String phone);
        void loginPhone(Context context,@NonNull String phoneNumber,@NonNull String code);
    }

    interface ICodeInputModel extends BaseContract.IBaseModel {
        Observable<BaseEntity> getCodeAgain(@NonNull String phoneNumber)throws JSONException;
        Observable<BaseEntity> justCode(@NonNull String phoneNumber,@NonNull String code) throws JSONException;
        Observable<BaseEntity<LoginInfo>> loginPhone(@NonNull String phoneNumber,@NonNull String code) throws JSONException;
    }
}
