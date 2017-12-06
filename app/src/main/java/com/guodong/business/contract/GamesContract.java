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

public interface GamesContract {
    interface IGamesView extends BaseContract.IBaseView{
        void setGames(List<GameInfo> games);
    }
    interface  IGamesPresenter extends BaseContract.IBasePresennter{
        void getGames(Context context);
    }
    interface IGamesModel  extends BaseContract.IBaseModel{
        Observable<List<GameInfo>> getGames();
    }
}
