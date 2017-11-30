package com.guodong.business.view.find;

import com.guodong.R;
import com.guodong.business.contract.FindContract;
import com.guodong.business.presenter.find.FindPresenter;
import com.guodong.mvp.BaseFragment;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.IFindView {
    @Override
    protected FindPresenter loadPresenter() {
        return new FindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initData() {

    }
}
