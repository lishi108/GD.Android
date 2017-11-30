package com.guodong.business.contract;


import android.content.Context;
import android.support.annotation.NonNull;

import com.guodong.business.bean.PictureInfo;
import com.guodong.mvp.BaseContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface SplashContract {
    interface ISplashView extends BaseContract.IBaseView{
        void setImages(List<PictureInfo> images);
    }
    interface  ISplashPresenter extends BaseContract.IBasePresennter{
        void getImages(@NonNull Context context);
    }
    interface ISplashModel  extends BaseContract.IBaseModel{
//        Observable<User> Login(String userName, String pwd);
        Observable<List<PictureInfo>> getImages();
    }
}
