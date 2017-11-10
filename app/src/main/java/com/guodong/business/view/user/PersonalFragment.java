package com.guodong.business.view.user;

import com.guodong.R;
import com.guodong.business.contract.PersonalContract;
import com.guodong.business.presenter.user.PersonalPresenter;
import com.guodong.mvp.BaseFragment;

import butterknife.OnClick;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class PersonalFragment extends BaseFragment<PersonalPresenter> implements PersonalContract.IPersonalView {
    @Override
    protected PersonalPresenter loadPresenter() {
        return new PersonalPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.personalSetting)
    void onViewClick(){
        mActivity.startActivity(SetttingActivity.class);
    }
}
