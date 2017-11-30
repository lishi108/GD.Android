package com.guodong.business.contract;


import android.content.Context;

import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.HotEquipmentInfo;
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
        void setGameData(List<GameInfo> data);
        void setHotEquipment(List<HotEquipmentInfo> hotEquipments);
    }
    interface  IHomePresenter extends BaseContract.IBasePresennter{
        void getBannerImage(Context context);
        void getGameData(Context context);
        void getHotEquipment(Context context);
    }
    interface IHomeModel  extends BaseContract.IBaseModel{
        Observable<List<PictureInfo>> getBannerImages();
        Observable<List<GameInfo>> getGameData();
        Observable<List<HotEquipmentInfo>> getHotEquipment();
    }
}
