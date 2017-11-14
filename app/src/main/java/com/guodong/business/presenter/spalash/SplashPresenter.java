package com.guodong.business.presenter.spalash;


import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.SplashContract;
import com.guodong.business.model.spalash.SplashModel;
import com.guodong.http.BaseObserver;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;

import java.util.List;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */
public class SplashPresenter extends BasePresenter<SplashContract.ISplashView,SplashContract.ISplashModel> implements SplashContract.ISplashPresenter{


    @Override
    public SplashContract.ISplashModel loadModel() {
        return new SplashModel();
    }

    @Override
    public void getImages() {
        getModel().getImages()
                .subscribe(new BaseObserver<List<PictureInfo>>(getView().getContext(),"spalash",false) {
                    @Override
                    public void onSuccess(List<PictureInfo> pictureInfoList) {
                        getView().setImages(pictureInfoList);
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.showToast(getView().getContext(),message);
                    }
                });
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

