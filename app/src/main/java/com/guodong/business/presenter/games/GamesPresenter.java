package com.guodong.business.presenter.games;

import android.content.Context;

import com.guodong.business.bean.GameInfo;
import com.guodong.business.contract.GamesContract;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.games.GamesModel;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class GamesPresenter extends BasePresenter<GamesContract.IGamesView,GamesContract.IGamesModel> implements GamesContract.IGamesPresenter{

    @Override
    public GamesContract.IGamesModel loadModel() {
        return new GamesModel();
    }

    @Override
    public void getGames(final Context context) {
        mModel.getGames().subscribe(new BaseObserver<List<GameInfo>>(context,true) {
            @Override
            public void addonSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(List<GameInfo> gameInfos) {
                mView.setGames(gameInfos);
            }

            @Override
            public void onError(String message) {
                ToastUtil.showToast(context,message);
            }
        });
    }
}
