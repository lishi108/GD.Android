package com.guodong.business.model.spalash;


import com.guodong.R;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.SplashContract;
import com.guodong.http.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class SplashModel implements SplashContract.ISplashModel {
    @Override
    public Observable<List<PictureInfo>> getImages() {
        List<PictureInfo> infoList = new ArrayList<>();
        PictureInfo p1 = new PictureInfo();
        p1.setResourceId(R.mipmap.spalash1);
        PictureInfo p2 = new PictureInfo();
        p2.setResourceId(R.mipmap.spalash2);
        PictureInfo p3 = new PictureInfo();
        p3.setResourceId(R.mipmap.spalash3);
        PictureInfo p4 = new PictureInfo();
        p4.setResourceId(R.mipmap.sp1);
        PictureInfo p5 = new PictureInfo();
        p5.setResourceId(R.mipmap.sp2);
        infoList.add(p1);
        infoList.add(p2);
        infoList.add(p3);
        infoList.add(p4);
        infoList.add(p5);
        return Observable.just(infoList)
                .compose(RxSchedulers.<List<PictureInfo>> io_main());
    }


//    @Override
//    public Observable<User> getCalendar(String date) {
//        return ((GDService)Api.getDefaultService())
//                .calendarBean(date)
//                .map(new ComFunction<User>())
//                .compose(RxSchedulers.<User> io_main() )
//                ;
//
//    }
}
