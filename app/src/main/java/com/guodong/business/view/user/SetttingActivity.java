package com.guodong.business.view.user;

import android.view.View;

import com.guodong.R;
import com.guodong.mvp.BaseTitleActivity;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class SetttingActivity extends BaseTitleActivity {

    public int initTitle() {
        return R.string.setting;
    }
    @Override
    protected int getFragmentContentId() {
        return R.id.settingFrameLayout;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }
    @Override
    public void initData() {
        super.initData();
        addFragment(new SettingFragment());
    }

    @Override
    public void onBackClick(View view) {
        removeFragment();
    }
}
