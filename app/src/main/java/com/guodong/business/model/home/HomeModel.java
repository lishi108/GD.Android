package com.guodong.business.model.home;


import com.guodong.R;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.HomeContract;
import com.guodong.business.http.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class HomeModel implements HomeContract.IHomeModel {

    @Override
    public Observable<List<PictureInfo>> getBannerImages() {

        List<PictureInfo> infoList = new ArrayList<>();
        PictureInfo p1 = new PictureInfo();
        p1.setResourceId(R.drawable.spalash1);
        PictureInfo p2 = new PictureInfo();
        p2.setResourceId(R.drawable.spalash2);
        PictureInfo p3 = new PictureInfo();
        p3.setResourceId(R.drawable.spalash3);
        PictureInfo p4 = new PictureInfo();
        p4.setResourceId(R.drawable.sp1);
        PictureInfo p5 = new PictureInfo();
        p5.setResourceId(R.drawable.sp2);
        infoList.add(p1);
        infoList.add(p2);
        infoList.add(p3);
        infoList.add(p4);
        infoList.add(p5);
        return Observable.just(infoList)
                .compose(RxSchedulers.<List<PictureInfo>> io_main());
    }

    @Override
    public Observable<List<String>> getData() {
        List<String> mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++)
        {
            mDatas.add((char) i + "");
        }
        return Observable.just(mDatas)
                .compose(RxSchedulers.<List<String>> io_main());
    }
}
