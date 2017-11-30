package com.guodong.business.presenter.find;

import com.guodong.business.contract.FindContract;
import com.guodong.business.model.find.FindModel;
import com.guodong.mvp.BasePresenter;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class FindPresenter extends BasePresenter<FindContract.IFindView,FindContract.IFindModel> implements FindContract.IFindPresenter{

    @Override
    public FindContract.IFindModel loadModel() {
        return new FindModel();
    }
}
