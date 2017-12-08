package com.guodong.business.model.home;


import com.guodong.R;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.HotEquipmentInfo;
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
        return Observable.just(infoList).compose(RxSchedulers.<List<PictureInfo>> io_main());
    }

    @Override
    public Observable<List<GameInfo>> getGameData() {
        List<GameInfo> mDatas = new ArrayList<>();
        GameInfo gameInfo1 = new GameInfo();
        gameInfo1.setGameName("地下城勇士");
        mDatas.add(gameInfo1);

        return Observable.just(mDatas)
                .compose(RxSchedulers.<List<GameInfo>> io_main());
    }

    @Override
    public Observable<List<HotEquipmentInfo>> getHotEquipment() {
        List<HotEquipmentInfo> mDatas = new ArrayList<>();
        HotEquipmentInfo gameInfo1 = new HotEquipmentInfo();
        gameInfo1.setTitle("地下城勇士");
        gameInfo1.setUrlId(R.drawable.spalash2);
        gameInfo1.setPrivce(200);
        gameInfo1.setAddress("广东区-电信服务器-游戏1区");
        mDatas.add(gameInfo1);

        return Observable.just(mDatas)
                .compose(RxSchedulers.<List<HotEquipmentInfo>> io_main());
    }
}
