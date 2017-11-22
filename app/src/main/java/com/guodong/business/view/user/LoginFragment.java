package com.guodong.business.view.user;

import android.view.View;
import android.widget.EditText;

import com.guodong.R;
import com.guodong.business.contract.LoginContract;
import com.guodong.business.presenter.user.LoginPresenter;
import com.guodong.business.view.MainActivity;
import com.guodong.mvp.BaseTitleActivity;
import com.guodong.mvp.BaseTitleFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseTitleFragment<LoginPresenter> implements LoginContract.ILoginView {
    @BindView(R.id.userNameEdit)
    EditText userNameEdit;
    @BindView(R.id.pwdEdit)
    EditText pwdEdit;
    @Override
    protected LoginPresenter loadPresenter() {
        return new LoginPresenter();
    }
    @Override
    protected void initData() {
        ((BaseTitleActivity)getActivity()).getOther().setVisibility(View.VISIBLE);
        ((BaseTitleActivity)getActivity()).getOther().setText(R.string.register);
        ((BaseTitleActivity)getActivity()).getOther().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
                addFragment(new LoginFragment());
            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public String getUserName() {
        return userNameEdit.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return pwdEdit.getText().toString().trim();
    }


    @OnClick(R.id.forgetPwdView)
    void onForgetPwd(View view) {

    }

    @OnClick(R.id.phoneLoginView)
    void onPhoneLogin(View view) {
        addFragment(new PhoneLoginFragment());
    }

    @OnClick(R.id.loginButton)
    void onLoginButton(View view) {
//        mPresenter.login(getUserName(),getPassword());
        mActivity.startActivity(MainActivity.class);
    }

    @OnClick(R.id.login_select_wechart)
    void onWeiChartView(View view) {

    }

    @OnClick(R.id.login_select_qq)
    void onQQView(View view) {

    }


}
