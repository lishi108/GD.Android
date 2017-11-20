package com.guodong.business.view.user;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.guodong.R;
import com.guodong.business.contract.PhoneLoginContract;
import com.guodong.business.presenter.user.PhoneLoginPresenter;
import com.guodong.mvp.BaseTitleFragment;
import com.guodong.utils.StringUtils;
import com.guodong.widget.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class PhoneLoginFragment extends BaseTitleFragment<PhoneLoginPresenter> implements PhoneLoginContract.IPhoneLoginView {

    @BindView(R.id.codePhoneEdit)
    ClearEditText codePhoneEdit;
    @BindView(R.id.getCodeButton)
    Button getCodeButton;
    @Override
    protected PhoneLoginPresenter loadPresenter() {
        return new PhoneLoginPresenter();
    }
    @Override
    protected void initData() {
        mActivity.getOther().setVisibility(View.VISIBLE);
        mActivity.getOther().setText(R.string.pwdLogin);
        mActivity.getOther().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
                addFragment(new LoginFragment());
            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phone_login;
    }



    
    @OnClick(R.id.getCodeButton)
    void onGetCodeButton(View view) {
        mPresenter.getPhoneLoginCode(codePhoneEdit.getText().toString().trim());
    }

    @OnClick(R.id.login_select_wechart)
    void onWeiChartView(View view) {

    }

    @OnClick(R.id.login_select_qq)
    void onQQView(View view) {

    }

    @OnTextChanged(value = R.id.codePhoneEdit,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s){
        if(StringUtils.checkPhoneNumber(s.toString())) {
            getCodeButton.setEnabled(true);
            getCodeButton.setBackgroundResource(R.mipmap.button_enable);
        }else {
            getCodeButton.setEnabled(false);
            getCodeButton.setBackgroundResource(R.drawable.button_background);
        }
    }

    @Override
    public void intentToCodeInput(String phone) {
        mBundle = new Bundle();
        mBundle.putString("phone",phone);
        CodeInputFragment codeInputFragment = new CodeInputFragment();
        codeInputFragment.setArguments(mBundle);
        addFragment(codeInputFragment);
    }
}
