package com.guodong.business.view.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guodong.R;
import com.guodong.business.contract.RegisterContract;
import com.guodong.business.presenter.user.RegisterPresenter;
import com.guodong.mvp.BaseTitleActivity;
import com.guodong.widget.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseTitleActivity<RegisterPresenter> implements RegisterContract.IRegisterView {
    @BindView(R.id.registerPhoneEdit)
    ClearEditText registerPhoneEdit;
    @BindView(R.id.registerCodeEdit)
    EditText registerCodeEdit;
    @BindView(R.id.registerCodeButton)
    Button registerCodeButton;
    @BindView(R.id.registerPwdEdit)
    ClearEditText registerPwdEdit;
    @BindView(R.id.registerConfirmPwdEdit)
    ClearEditText registerConfirmPwdEdit;
    @BindView(R.id.registerButton)
    Button registerButton;

    @Override
    protected RegisterPresenter loadPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public int initTitle() {
        return R.string.register;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public String getPhone() {
        return registerPhoneEdit.getText().toString().trim();
    }

    @Override
    public void setCodeButton(String data) {
        registerCodeButton.setText(data);
    }

    @Override
    public String getCode() {
        return registerCodeEdit.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return registerPwdEdit.getText().toString().trim();
    }

    @Override
    public String getPwdAgain() {
        return registerConfirmPwdEdit.getText().toString().trim();
    }

    @Override
    public void setCodeButtonEnable(boolean enable) {
        registerCodeButton.setClickable(enable);
        registerCodeButton.setEnabled(enable);
    }

    @Override
    public void setRegisterButtonEnable(boolean enable) {
        registerButton.setClickable(enable);
        registerButton.setEnabled(enable);
    }

    @OnClick({R.id.registerCodeButton,R.id.registerButton})
    void onClicke(View view){
        switch (view.getId()){
            case R.id.registerCodeButton:
                mPresenter.getCode();
                break;
            case R.id.registerButton:
                mPresenter.register();
                break;
        }
    }
}
