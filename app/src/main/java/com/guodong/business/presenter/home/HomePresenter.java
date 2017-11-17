package com.guodong.business.presenter.home;

import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.HomeContract;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.home.HomeModel;
import com.guodong.mvp.BasePresenter;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class HomePresenter extends BasePresenter<HomeContract.IHomeView,HomeContract.IHomeModel> implements HomeContract.IHomePresenter{

    @Override
    public HomeContract.IHomeModel loadModel() {
        return new HomeModel();
    }

    @Override
    public void getBannerImage() {
        mModel.getBannerImages().subscribe(new BaseObserver<List<PictureInfo>>(mView.getContext(),false) {
            @Override
            public void addonSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(List<PictureInfo> pictureInfoList) {

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void getData() {
        mModel.getData().subscribe(new BaseObserver<List<String>>(mView.getContext(),false) {
            @Override
            public void addonSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(List<String> strings) {

            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
