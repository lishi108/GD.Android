package com.guodong.business.contract;


import android.content.Context;
import android.support.annotation.NonNull;

import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.Category;
import com.guodong.business.bean.SubCategory;
import com.guodong.mvp.BaseContract;

import org.json.JSONException;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface GoodsContract {
    interface IGoodsView extends BaseContract.IBaseView{
        void setGoods(List<GameInfo> goods);
        void setCategory(@NonNull List<Category> categoryList);
        void setSubCategory(@NonNull List<SubCategory> sbCategoryList);
    }
    interface  IGoodsPresenter extends BaseContract.IBasePresennter{
        void getCategory(Context context, @NonNull String gameId);
        void getSubCategory(Context context,@NonNull String categoryId );
        void getGoods(Context context);
    }
    interface IGoodsModel  extends BaseContract.IBaseModel{
        Observable<List<GameInfo>> getGoods();
        Observable<List<Category>>  getCategory(@NonNull String gameId) throws JSONException;
        Observable<List<SubCategory>>  getSubCategory(@NonNull String categoryId) throws JSONException;
    }
}
