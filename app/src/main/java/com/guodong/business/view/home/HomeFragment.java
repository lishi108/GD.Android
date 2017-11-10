package com.guodong.business.view.home;

import com.guodong.R;
import com.guodong.business.contract.HomeContract;
import com.guodong.business.presenter.home.HomePresenter;
import com.guodong.mvp.BaseFragment;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeView {
    @Override
    protected HomePresenter loadPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
    }
}
