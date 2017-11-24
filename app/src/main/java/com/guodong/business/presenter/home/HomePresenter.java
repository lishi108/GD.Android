package com.guodong.business.presenter.home;

import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.HotEquipmentInfo;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.HomeContract;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.home.HomeModel;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class HomePresenter extends BasePresenter<HomeContract.IHomeView, HomeContract.IHomeModel> implements HomeContract.IHomePresenter {

    @Override
    public HomeContract.IHomeModel loadModel() {
        return new HomeModel();
    }

    @Override
    public void getBannerImage() {
        mModel.getBannerImages()
                .subscribe(new BaseObserver<List<PictureInfo>>(getView().getContext(), false) {


                    @Override
                    public void addonSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<PictureInfo> pictureInfoList) {
                        mView.setBannerImages(pictureInfoList);
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.showToast(getView().getContext(), message);
                    }
                });
    }

    @Override
    public void getGameData() {
        mModel.getGameData()
                .subscribe(new BaseObserver<List<GameInfo>>(mView.getContext(), true) {
                    @Override
                    public void addonSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<GameInfo> gameInfos) {
                        mView.setGameData(gameInfos);
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.showToast(getView().getContext(), message);
                    }
                });
    }

    @Override
    public void getHotEquipment() {
        mModel.getHotEquipment()
                .map(new Function<List<HotEquipmentInfo>, List<HotEquipmentInfo>>() {
                    @Override
                    public List<HotEquipmentInfo> apply(@NonNull List<HotEquipmentInfo> hotEquipmentInfos) throws Exception {
                        hotEquipmentInfos.add(hotEquipmentInfos.get(0));
                        hotEquipmentInfos.add(hotEquipmentInfos.get(0));
                        hotEquipmentInfos.add(hotEquipmentInfos.get(0));
                        hotEquipmentInfos.add(hotEquipmentInfos.get(0));
                        hotEquipmentInfos.add(hotEquipmentInfos.get(0));
                        hotEquipmentInfos.add(hotEquipmentInfos.get(0));
                        hotEquipmentInfos.add(hotEquipmentInfos.get(0));
                        return hotEquipmentInfos;
                    }
                })
                .subscribe(new BaseObserver<List<HotEquipmentInfo>>(mView.getContext(),true) {
                    @Override
                    public void addonSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<HotEquipmentInfo> hotEquipmentInfos) {
                        mView.setHotEquipment(hotEquipmentInfos);
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.showToast(mView.getContext(),message);
                    }
                });
    }
}
