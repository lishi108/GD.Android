package com.guodong.business.contract;


import com.guodong.business.bean.PictureInfo;
import com.guodong.mvp.BaseContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface HomeContract {
    interface IHomeView extends BaseContract.IBaseView{
        void setBannerImages(List<PictureInfo> bannerImages);
        void setData(List<String> data);
    }
    interface  IHomePresenter extends BaseContract.IBasePresennter{
        void getBannerImage();
        void getData();
    }
    interface IHomeModel  extends BaseContract.IBaseModel{
        Observable<List<PictureInfo>> getBannerImages();
        Observable<List<String>> getData();
    }
}
