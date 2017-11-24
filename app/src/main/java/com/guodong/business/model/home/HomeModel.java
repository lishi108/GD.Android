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
        gameInfo1.setName("地下城勇士");
        gameInfo1.setIconId(R.drawable.sp1);
        mDatas.add(gameInfo1);

        GameInfo gameInfo2 = new GameInfo();
        gameInfo2.setName("ddddddddd");
        gameInfo2.setIconId(R.drawable.sp2);
        mDatas.add(gameInfo2);

        GameInfo gameInfo3 = new GameInfo();
        gameInfo3.setName("mmmmmm");
        gameInfo3.setIconId(R.drawable.spalash1);
        mDatas.add(gameInfo3);

        GameInfo gameInfo4 = new GameInfo();
        gameInfo4.setName("地下城勇士");
        gameInfo4.setIconId(R.drawable.sp1);
        mDatas.add(gameInfo4);

        GameInfo gameInfo5 = new GameInfo();
        gameInfo5.setName("地下城勇士");
        gameInfo5.setIconId(R.drawable.sp1);
        mDatas.add(gameInfo5);

        GameInfo gameInfo6 = new GameInfo();
        gameInfo6.setName("地下城勇士");
        gameInfo6.setIconId(R.drawable.sp1);
        mDatas.add(gameInfo6);


        GameInfo gameInfo7 = new GameInfo();
        gameInfo7.setName("地下城勇士");
        gameInfo7.setIconId(R.drawable.sp1);
        mDatas.add(gameInfo7);


        GameInfo gameInfo8 = new GameInfo();
        gameInfo8.setName("地下城勇士");
        gameInfo8.setIconId(R.drawable.sp1);
        mDatas.add(gameInfo8);


        return Observable.just(mDatas)
                .compose(RxSchedulers.<List<GameInfo>> io_main());
    }

    @Override
    public Observable<List<HotEquipmentInfo>> getHotEquipment() {
        List<HotEquipmentInfo> mDatas = new ArrayList<>();
        HotEquipmentInfo gameInfo1 = new HotEquipmentInfo();
        gameInfo1.setTitle("地下城勇士");
        gameInfo1.setUrlId(R.drawable.sp1);
        gameInfo1.setPrivce(200);
        gameInfo1.setAddress("广东区-电信服务器-游戏1区");
        mDatas.add(gameInfo1);

        return Observable.just(mDatas)
                .compose(RxSchedulers.<List<HotEquipmentInfo>> io_main());
    }
}
