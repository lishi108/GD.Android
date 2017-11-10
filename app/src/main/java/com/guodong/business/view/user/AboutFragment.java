package com.guodong.business.view.user;

import com.guodong.R;
import com.guodong.mvp.BaseFragment;
import com.guodong.mvp.BasePresenter;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class AboutFragment extends BaseFragment  {


    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.about;
    }

    @Override
    protected void initData() {
        mActivity.setTitle(R.string.setting_about);
    }

}
