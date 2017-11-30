package com.guodong.business.view.user;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.guodong.R;
import com.guodong.mvp.BaseFragment;
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
        return R.id.frameLayout;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common;
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

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack(fragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);//这是当前fragment在打开fragment相当于先按了返回键
            //这是目前我找到惟一保证Fragment二次打开后返回栈元素惟一而又能刷新的 无bug的方法

            FragmentTransaction transaction = fm.beginTransaction();
//            transaction.setCustomAnimations(R.animator.slide_right_in,R.animator.slide_left_out,R.animator.slide_left_in,R.animator.slide_right_out);
            transaction.replace(getFragmentContentId(), fragment, fragment.getClass().getName());
            transaction.isAddToBackStackAllowed();
            transaction.addToBackStack(fragment.getClass().getName());
            transaction.commitAllowingStateLoss();




//            getSupportFragmentManager().beginTransaction()
//                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
//                    .addToBackStack(fragment.getClass().getSimpleName())
//                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
