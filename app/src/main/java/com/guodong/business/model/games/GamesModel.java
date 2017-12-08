package com.guodong.business.model.games;


import com.guodong.business.bean.GameInfo;
import com.guodong.business.contract.GamesContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.RxSchedulers;
import com.guodong.business.http.callback.JsonConvert;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class GamesModel implements GamesContract.IGamesModel {

    @Override
    public Observable<List<GameInfo>> getGames() {

        return OkGo.<BaseEntity<List<GameInfo>>>get("http://category.api.guodong.com/Game/GetGameListGroupByStateToBuyAsync")
                .converter(new JsonConvert<BaseEntity<List<GameInfo>>>() {
                })
                .adapt(new ObservableBody<BaseEntity<List<GameInfo>>>())
                .compose(RxSchedulers.<BaseEntity<List<GameInfo>>>io_main())
                .map(new Function<BaseEntity<List<GameInfo>>, List<GameInfo>>() {
                    @Override
                    public List<GameInfo> apply(@NonNull BaseEntity<List<GameInfo>> listBaseEntity) throws Exception {
                        return listBaseEntity.getData();
                    }
                });


    }
}
