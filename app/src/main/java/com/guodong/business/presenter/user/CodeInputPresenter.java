package com.guodong.business.presenter.user;


import android.content.Context;

import com.guodong.R;
import com.guodong.business.config.DataManager;
import com.guodong.business.contract.CodeInputContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.user.CodeInputModel;
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
public class CodeInputPresenter extends BasePresenter<CodeInputContract.ICodeInputView,CodeInputContract.ICodeInputModel> implements CodeInputContract.ICodeInputPresenter{


    @Override
    public CodeInputContract.ICodeInputModel loadModel() {
        return new CodeInputModel();
    }

    @Override
    public void justCode(final Context context, String phone, String code) {
        try {
            mModel.justCode(phone,code)
                    .subscribe(new BaseObserver<BaseEntity>(context,true) {
                        @Override
                        public void addonSubscribe(@NonNull Disposable d) {
                            addDisposable(d);
                        }

                        @Override
                        public void onSuccess(BaseEntity baseEntity) {
                            Logger.e("just code:"+baseEntity.getResCode());
                            if((Boolean) baseEntity.getData()){
                                mView.intentToMain();
                                DataManager.saveIsFirst(false);
                            }else {
                                ToastUtil.showToast(context,"验证码验证错误");
                            }
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

    @Override
    public void getCodeAgain(@NonNull final Context context, @NonNull final String phone) {
        //获取验证码
        try {
            mModel.getCodeAgain(phone)
                    .subscribe(new BaseObserver<BaseEntity>(context,true) {
                        @Override
                        public void addonSubscribe(@NonNull Disposable d) {
                            addDisposable(d);
                        }

                        @Override
                        public void onSuccess(BaseEntity data) {
                            if((Boolean) data.getData()) mView.changeTimeCode();
                            else {
                                if(data.getMsg().equals("")) data.setMsg(context.getResources().getString(R.string.get_code_failed));
                                ToastUtil.showToast(context,data.getMsg());
                            }
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

