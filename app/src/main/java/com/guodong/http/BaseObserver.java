package com.guodong.http;

import android.content.Context;

import com.guodong.utils.ToastUtil;
import com.guodong.widget.LoadingDialog;

import java.io.EOFException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by LSQ108 on 2017/10/27.
 */

public abstract class BaseObserver<T> implements Observer<T>{
    private RxManager rxManager;
    private String mKey;
    private LoadingDialog mDialog;
    private Context mContext;
    private boolean isShowDialog;
    public BaseObserver(Context context, String key, boolean isShowDialog){
        this.mKey = key;
        this.mContext = context;
        this.isShowDialog = isShowDialog;
        mDialog = new LoadingDialog(context);
        rxManager = RxManager.getInstance();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        rxManager.add(mKey,d);
        if(isShowDialog){
            mDialog.show();
        }
        onStart();
    }

    @Override
    public void onNext(@NonNull T value) {
            onSuccess(value);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (mDialog!=null&&mDialog.isShowing()){
            mDialog.dismiss();
        }
        if(e instanceof EOFException|| e instanceof ConnectException || e instanceof SocketException || e instanceof BindException || e instanceof SocketTimeoutException || e instanceof UnknownHostException){
            ToastUtil.showToast(mContext,"网络异常，请稍后重试！");
        }else if (e instanceof ApiException){
            onError(e.getMessage());
        }else {
            ToastUtil.showToast(mContext,"发生未知错误！");
        }

    }

    @Override
    public void onComplete() {
        if(mDialog!=null&&mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
    public void onStart(){}
    public abstract void onSuccess( T t);
    public abstract void onError(String message);

}
