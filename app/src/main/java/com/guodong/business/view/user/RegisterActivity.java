package com.guodong.business.view.user;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.guodong.R;
import com.guodong.business.contract.RegisterContract;
import com.guodong.business.presenter.user.RegisterPresenter;
import com.guodong.mvp.BaseTitleActivity;
import com.guodong.utils.StringUtils;
import com.guodong.utils.ToastUtil;
import com.guodong.widget.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class RegisterActivity extends BaseTitleActivity<RegisterPresenter> implements RegisterContract.IRegisterView {
    @BindView(R.id.codeLine)
    View codeLine;
    @BindView(R.id.codePhoneEdit)
    ClearEditText codePhoneEdit;
    @BindView(R.id.getCodeButton)
    Button getCodeButton;
    private boolean phoneOk = false;

    @Override
    protected RegisterPresenter loadPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int initTitle() {
        return R.string.register;
    }

    @Override
    protected void initData() {
        super.initData();
        getOther().setVisibility(View.GONE);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phone_login;
    }



    
    @OnClick(R.id.getCodeButton)
    void onGetCodeButton(View view) {
        if (phoneOk) {
            mPresenter.getCode(mContext,codePhoneEdit.getText().toString().trim());
        }else {
            ToastUtil.showToast(mContext,R.string.phone_login_badnumber);
        }
    }

    @OnClick(R.id.login_select_wechart)
    void onWeiChartView(View view) {

    }

    @OnClick(R.id.login_select_qq)
    void onQQView(View view) {

    }

    /**
     * 验证手机号码，通过更改状态
     * @param s
     */
    @OnTextChanged(value = R.id.codePhoneEdit,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s){
        if(StringUtils.checkPhoneNumber(s.toString())) {
            phoneOk = true;
            codeLine.setBackgroundResource(R.color.colorPrimary);
            getCodeButton.setBackgroundResource(R.drawable.button_enable);
        }else {
            phoneOk = false;
            codeLine.setBackgroundResource(R.color.colorLine);
            getCodeButton.setBackgroundResource(R.drawable.button);
        }
    }

    @Override
    public void intentToCodeInput(String phone) {
        Bundle mBundle = new Bundle();
        mBundle.putString("phone",phone);
        startActivity(CodeInputActivity.class,mBundle);
    }
}
