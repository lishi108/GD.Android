package com.guodong.business.presenter.goods;

import com.guodong.business.contract.GoodsContract;
import com.guodong.business.model.goods.GoodsModel;
import com.guodong.mvp.BasePresenter;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class GoodsPresenter extends BasePresenter<GoodsContract.IGoodsView,GoodsContract.IGoodsModel> implements GoodsContract.IGoodsPresenter{

    @Override
    public GoodsContract.IGoodsModel loadModel() {
        return new GoodsModel();
    }
}
