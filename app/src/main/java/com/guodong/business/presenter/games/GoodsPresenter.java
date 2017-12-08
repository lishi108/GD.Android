package com.guodong.business.presenter.games;

import android.content.Context;

import com.guodong.business.bean.Category;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.SubCategory;
import com.guodong.business.contract.GoodsContract;
import com.guodong.business.http.BaseObserver;
import com.guodong.business.model.games.GoodsModel;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.ToastUtil;

import org.json.JSONException;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class GoodsPresenter extends BasePresenter<GoodsContract.IGoodsView,GoodsContract.IGoodsModel> implements GoodsContract.IGoodsPresenter{

    @Override
    public GoodsContract.IGoodsModel loadModel() {
        return new GoodsModel();
    }

    @Override
    public void getGoods(final Context context) {
        mModel.getGoods().subscribe(new BaseObserver<List<GameInfo>>(context,true) {
            @Override
            public void addonSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(List<GameInfo> gameInfos) {
                mView.setGoods(gameInfos);
            }

            @Override
            public void onError(String message) {
                ToastUtil.showToast(context,message);
            }
        });
    }

    @Override
    public void getCategory(final Context context, @android.support.annotation.NonNull String gameId) {
        try {
            mModel.getCategory(gameId)
                    .subscribe(new BaseObserver<List<Category>>(context,false) {
                        @Override
                        public void addonSubscribe(@NonNull Disposable d) {
                            addDisposable(d);
                        }

                        @Override
                        public void onSuccess(List<Category> categories) {

                        }

                        @Override
                        public void onError(String message) {
                            ToastUtil.showToast(context,message);
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtil.showToast(context,e.getMessage());
        }
    }

    @Override
    public void getSubCategory(final Context context, @android.support.annotation.NonNull String categoryId) {
        try {
            mModel.getSubCategory(categoryId)
                    .subscribe(new BaseObserver<List<SubCategory>>(context,false) {
                        @Override
                        public void addonSubscribe(@NonNull Disposable d) {
                            addDisposable(d);
                        }

                        @Override
                        public void onSuccess(List<SubCategory> subCategories) {

                        }

                        @Override
                        public void onError(String message) {
                        ToastUtil.showToast(context,message);
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtil.showToast(context,e.getMessage());
        }
    }
}
