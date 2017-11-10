package com.guodong.mvp;

import android.util.Log;

import com.guodong.widget.LoadingDialog;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Description:
 * Created by LSQ108 on 2017/10/24.
 */

public abstract class BasePresenter<V extends BaseContract.IBaseView, M extends BaseContract.IBaseModel> implements BaseContract.IBasePresennter {
    private WeakReference viewReference;
    protected LoadingDialog loadingDialog;
    protected V mView;
    protected M mModel;

    public Function<Observable, ObservableSource> composeFunction;

    public M getModel() {
        return loadModel();
    }


    @Override
    public void attachView(BaseContract.IBaseView iView) {
        Log.e("TAG", "MVP attachView begin!!!");
        viewReference = new WeakReference(iView);
        mView = (V) viewReference.get();
        mModel = getModel();
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog(getView().getContext());
    }

    @Override
    public void detachView() {
        Log.e("TAG", "MVP detachView begin!!!");
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    @Override
    public V getView() {
        return (V) viewReference.get();
    }

    public HashMap<String, BaseContract.IBaseModel> loadModelMap(BaseContract.IBaseModel... models) {
        return null;
    }

    public HashMap<String, BaseContract.IBaseModel> getModelMap() {
        return null;
    }

    public abstract M loadModel();
}
