package com.guodong.business.view.user;

import android.view.View;

import com.guodong.R;
import com.guodong.mvp.BaseFragment;
import com.guodong.mvp.BasePresenter;

import butterknife.OnClick;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class SettingFragment extends BaseFragment  {


    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {
    }
    @OnClick({R.id.settingPwdLayout,R.id.settingPhoneLayout,R.id.settingAboutLayout})
    void onViewClick(View view){
        switch (view.getId()){
            case R.id.settingPwdLayout:
                addFragment(new PasswordFragment());
                break;
            case R.id.settingPhoneLayout:
                addFragment(new PhoneFragment());
                break;
            case R.id.settingAboutLayout:
                addFragment(new AboutFragment());
                break;
        }
    }
}
