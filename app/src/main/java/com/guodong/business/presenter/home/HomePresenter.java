package com.guodong.business.presenter.home;

import com.guodong.business.contract.HomeContract;
import com.guodong.business.model.home.HomeModel;
import com.guodong.mvp.BasePresenter;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class HomePresenter extends BasePresenter<HomeContract.IHomeView,HomeContract.IHomeModel> implements HomeContract.IHomePresenter{

    @Override
    public HomeContract.IHomeModel loadModel() {
        return new HomeModel();
    }
}
