package com.guodong.business.model.games;


import android.support.annotation.NonNull;

import com.guodong.business.bean.Category;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.SubCategory;
import com.guodong.business.contract.GoodsContract;
import com.guodong.business.http.BaseEntity;
import com.guodong.business.http.RxSchedulers;
import com.guodong.business.http.callback.JsonConvert;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class GoodsModel implements GoodsContract.IGoodsModel {

    @Override
    public Observable<List<GameInfo>> getGoods() {
        List<GameInfo> gameInfos = new ArrayList<>();
        GameInfo gameInfo1 = new GameInfo();

        return Observable.just(gameInfos);
    }

    @Override
    public Observable<List<Category>> getCategory(@NonNull String gameId) throws JSONException {

        return OkGo.<BaseEntity<List<Category>>>get("http://category.api.guodong.com/ProductCategory/GetCategoryListByGameIdToBuyAsync")
                .params("gameId",gameId)
                .converter(new JsonConvert<BaseEntity<List<Category>>>() {
                })
                .adapt(new ObservableBody<BaseEntity<List<Category>>>())
                .compose(RxSchedulers.<BaseEntity<List<Category>>>io_main())
                .map(new Function<BaseEntity<List<Category>>, List<Category>>() {
                    @Override
                    public List<Category> apply(@NonNull BaseEntity<List<Category>> listBaseEntity) throws Exception {
                        return listBaseEntity.getData();
                    }
                });
    }

    @Override
    public Observable<List<SubCategory>> getSubCategory(@NonNull String categoryId) throws JSONException {
        return OkGo.<BaseEntity<List<SubCategory>>>get("http://category.api.guodong.com/ProductSubCategory/GetSubCategoryByParentIdToBuyAsny")
                .params("parentId",categoryId)
                .converter(new JsonConvert<BaseEntity<List<SubCategory>>>() {
                })
                .adapt(new ObservableBody<BaseEntity<List<SubCategory>>>())
                .compose(RxSchedulers.<BaseEntity<List<SubCategory>>>io_main())
                .map(new Function<BaseEntity<List<SubCategory>>, List<SubCategory>>() {
                    @Override
                    public List<SubCategory> apply(@NonNull BaseEntity<List<SubCategory>> listBaseEntity) throws Exception {
                        return listBaseEntity.getData();
                    }
                });
    }
}
