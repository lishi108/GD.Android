package com.guodong.business.model.spalash;


import com.guodong.R;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.SplashContract;
import com.guodong.business.http.RxSchedulers;

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


//
//    public Observable<List<PictureInfo>> getImages() {
////        HttpHeaders headers = new HttpHeaders();
////        headers.put("aaa", "header");
////        HttpParams params = new HttpParams();
////        params.put("bbb", "param");
//        return OkGo.<BaseEntity<List<PictureInfo>>>get("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510832788065&di=694cfc5ba73a1cfc8cc7d91a46290670&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F3801213fb80e7bec36aa2529252eb9389a506b81.jpg")
////                .headers(headers)
////                .params(params)
//                .converter(new JsonConvert<BaseEntity<List<PictureInfo>>>())
//                .adapt(new ObservableBody<BaseEntity<List<PictureInfo>>>())
//                .map(new Function<BaseEntity<List<PictureInfo>>, List<PictureInfo>>() {
//                    @Override
//                    public List<PictureInfo> apply(@NonNull BaseEntity<List<PictureInfo>> listBaseEntity) throws Exception {
//                        return listBaseEntity.getData();
//                    }
//                })
//                .compose(RxSchedulers.<List<PictureInfo>>io_main());
//    }

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
