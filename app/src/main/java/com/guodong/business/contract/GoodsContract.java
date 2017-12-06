package com.guodong.business.contract;


import android.content.Context;

import com.guodong.business.bean.GameInfo;
import com.guodong.mvp.BaseContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface GoodsContract {
    interface IGoodsView extends BaseContract.IBaseView{
        void setGoods(List<GameInfo> goods);
    }
    interface  IGoodsPresenter extends BaseContract.IBasePresennter{
        void getGoods(Context context);
    }
    interface IGoodsModel  extends BaseContract.IBaseModel{
        Observable<List<GameInfo>> getGoods();
    }
}
