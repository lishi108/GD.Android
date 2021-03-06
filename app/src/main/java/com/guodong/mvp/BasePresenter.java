package com.guodong.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Description:
 * Created by LSQ108 on 2017/10/24.
 */

public abstract class BasePresenter<V extends BaseContract.IBaseView, M extends BaseContract.IBaseModel> implements BaseContract.IBasePresennter {
    private WeakReference<V> viewReference;
//    protected LoadingDialog loadingDialog;
    protected V mView;
    protected M mModel;

    public Function<Observable, ObservableSource> composeFunction;

    public M getModel() {
        return loadModel();
    }

    private CompositeDisposable compositeDisposable;
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null){
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }


    @UiThread
    @Override
    public void attachView(@NonNull BaseContract.IBaseView iView) {
        Log.e("TAG", "MVP attachView begin!!!");
        viewReference = new WeakReference(iView);
        mView = viewReference.get();
        mModel = getModel();
    }

    @Override
    public void detachView() {
        Log.e("TAG", "MVP detachView begin!!!");
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
//        if(loadingDialog!=null&&loadingDialog.isShowing())
//            loadingDialog.dismiss();
        dispose();
    }

    @Override
    public V getView() {
        return viewReference == null ? null : viewReference.get();
    }

    public HashMap<String, BaseContract.IBaseModel> loadModelMap(BaseContract.IBaseModel... models) {
        return null;
    }

    public HashMap<String, BaseContract.IBaseModel> getModelMap() {
        return null;
    }

    public abstract M loadModel();
}
