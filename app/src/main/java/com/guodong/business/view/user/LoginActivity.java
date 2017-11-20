package com.guodong.business.view.user;

import android.view.View;

import com.guodong.R;
import com.guodong.mvp.BaseTitleActivity;

/**
 * Description:
 * Created by Administrator on 2017/11/20.
 */

public class LoginActivity extends BaseTitleActivity {
    public int initTitle() {
        return R.string.NullText;
    }
    @Override
    protected int getFragmentContentId() {
        return R.id.frameLayout;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common;
    }
    @Override
    public void initData() {
        super.initData();
        addFragment(new LoginFragment());
    }

    @Override
    public void onBackClick(View view) {
        removeFragment();
    }

}
