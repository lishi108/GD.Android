package com.guodong.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.guodong.BaseApplication;
import com.guodong.utils.AutoUtils;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;


/**
 * Description:
 * Created by LSQ108 on 2017/10/24.
 */

public abstract class AbsActivity extends AppCompatActivity {
    protected View view;
    protected Context mContext;
    //退出时的时间
    private long mExitTime;
    protected String TAG;

    /** 是否沉浸状态栏 **/
    private boolean isSetStatusBar = true;
    /** 是否横屏竖屏  false  横屏   true  竖屏 **/
    private boolean isAllowScreenRotate = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.setSize(this, false, 720, 1280);
        setContentView(getView());
        initCommonData();
        initData();
        hideBottomUIMenu();
    }

    private void initCommonData() {
        AutoUtils.auto(this);
        ButterKnife.bind(this);
        /** 是否沉浸状态栏 **/
        if (isSetStatusBar) {
            steepStatusBar();
        }
        /** 是否旋转屏幕 **/
        if (!isAllowScreenRotate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }


        mContext = this;

        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
        BaseApplication.getApplication().addActivity(this);
    }

    /**
     * 是否设置沉浸状态栏
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 是否屏幕旋转
     * @param isAllowScreenRotate
     */
    public void setScreenRoate(boolean isAllowScreenRotate) {
        this.isAllowScreenRotate = isAllowScreenRotate;
    }
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
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
            transaction.commit();




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

    /**
     * 返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*此处多Fragment返回键切换Fragment与双击返回键推出应用冲突，根据项目情况做调整
         */
//        if (KeyEvent.KEYCODE_BACK == keyCode) {
//            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//                finish();
//                return true;
//            }
//        }
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            BaseApplication.getApplication().finishAll();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getApplication().finishActivity(this);

        RefWatcher refWatcher = BaseApplication.getRefWatcher(mContext);
        refWatcher.watch(this);
    }

    /**
     * 跳转Activity
     *
     * @param toClass
     */
    public void startActivity(Class<?> toClass) {
        Intent intent = new Intent(mContext, toClass);
        startActivity(intent);
    }

    /**
     * 带值跳转Activity
     *
     * @param toClass
     * @param bundle
     */
    public void startActivity(Class<?> toClass, Bundle bundle) {
        Intent intent = new Intent(mContext, toClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转，并返回值
     *
     * @param toClass
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> toClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(mContext, toClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 取得上一页面传递的Intent值
     *
     * @param intent
     */
    protected void handleIntent(Intent intent) {

    }

    /**
     * 华为等设备底部虚拟按键
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 点击软键盘之外的空白处，隐藏软件盘
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideSoftInput();
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 显示软键盘
     */
    public void showInputMethod(){
        if (getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),0);
        }
    }

    //布局中Fragment的ID
    protected  int getFragmentContentId(){
        return  -1;
    }

    protected BaseFragment getFirstFragment(){
        return null;
    }

    protected abstract int getLayoutId();

    protected abstract void initData();
}
