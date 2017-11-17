package com.guodong.business.model.home;


import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.HomeContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class HomeModel implements HomeContract.IHomeModel {

    @Override
    public Observable<List<PictureInfo>> getBannerImages() {
        return null;
    }

    @Override
    public Observable<List<String>> getData() {
        return null;
    }
}
