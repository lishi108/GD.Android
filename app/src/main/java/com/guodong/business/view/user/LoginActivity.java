package com.guodong.business.view.user;

import android.view.View;
import android.widget.EditText;

import com.guodong.R;
import com.guodong.business.contract.LoginContract;
import com.guodong.business.presenter.user.LoginPresenter;
import com.guodong.mvp.BaseTitleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseTitleActivity<LoginPresenter> implements LoginContract.ILoginView {
    @BindView(R.id.userNameEdit)
    EditText userNameEdit;
    @BindView(R.id.pwdEdit)
    EditText pwdEdit;
    @Override
    protected LoginPresenter loadPresenter() {
        return new LoginPresenter();
    }

    public int initTitle() {
        return R.string.login;
    }
    @Override
    protected void initData() {
        super.initData();
        userNameEdit.setText("17781139662");
        pwdEdit.setText("123456");
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public String getUserName() {
        return userNameEdit.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return pwdEdit.getText().toString().trim();
    }

    @Override
    public void startToActivity(Class activityClass) {
        startActivity(activityClass);
        finish();
    }

    @OnClick(R.id.forgetPwdView)
    void onForgetPwd(View view) {

    }

    @OnClick(R.id.phoneLoginView)
    void onPhoneLogin(View view) {

    }

    @OnClick(R.id.loginButton)
    void onLoginButton(View view) {
        mPresenter.login(getUserName(),getPassword());
    }

    @OnClick(R.id.login_select_wechart)
    void onWeiChartView(View view) {

    }

    @OnClick(R.id.login_select_qq)
    void onQQView(View view) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
