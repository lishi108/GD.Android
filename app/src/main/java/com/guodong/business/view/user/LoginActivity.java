package com.guodong.business.view.user;

import android.os.Bundle;
import android.view.View;

import com.guodong.R;
import com.guodong.mvp.BaseFragment;
import com.guodong.mvp.BaseTitleActivity;
import com.orhanobut.logger.Logger;

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
    protected BaseFragment getFirstFragment() {
        return new LoginFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common;
    }

    @Override
    public void initData() {
        super.initData();

//        addFragment(new LoginFragment());
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,getFirstFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onBackClick(View view) {
        removeFragment();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Logger.e("LoginActivity destory!!!!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
    //添加fragment
//    protected void addFragment(BaseFragment fragment) {
//        if (fragment != null) {
//            FragmentManager fm = getSupportFragmentManager();
//            fm.popBackStack(fragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);//这是当前fragment在打开fragment相当于先按了返回键
//            //这是目前我找到惟一保证Fragment二次打开后返回栈元素惟一而又能刷新的 无bug的方法
//
//            FragmentTransaction transaction = fm.beginTransaction();
////            transaction.setCustomAnimations(R.animator.slide_right_in,R.animator.slide_left_out,R.animator.slide_left_in,R.animator.slide_right_out);
//            transaction.replace(getFragmentContentId(), fragment, fragment.getClass().getName());
//            transaction.isAddToBackStackAllowed();
//            transaction.addToBackStack(fragment.getClass().getName());
//            transaction.commitAllowingStateLoss();
//
//
//
//
////            getSupportFragmentManager().beginTransaction()
////                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
////                    .addToBackStack(fragment.getClass().getSimpleName())
////                    .commitAllowingStateLoss();
//        }
//    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            LoginActivity.this.finish();
        }
    }
}
