package com.guodong.business.view.goods;

import com.guodong.R;
import com.guodong.business.contract.GoodsContract;
import com.guodong.business.presenter.goods.GoodsPresenter;
import com.guodong.mvp.BaseFragment;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class GoodsFragment extends BaseFragment<GoodsPresenter> implements GoodsContract.IGoodsView {
    @Override
    protected GoodsPresenter loadPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initData() {

    }
}
