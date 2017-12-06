package com.guodong.business.view.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guodong.R;
import com.guodong.business.contract.CodeInputContract;
import com.guodong.business.presenter.user.CodeInputPresenter;
import com.guodong.business.view.MainActivity;
import com.guodong.mvp.AppManager;
import com.guodong.mvp.BaseTitleActivity;
import com.guodong.utils.LogUtils;
import com.guodong.utils.StringUtils;
import com.guodong.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class CodeInputActivity extends BaseTitleActivity<CodeInputPresenter> implements CodeInputContract.ICodeInputView {

    @BindView(R.id.codePhoneTextView)
    TextView codePhoneTextView;
    @BindView(R.id.codeTimeTextView)
    TextView codeTimeTextView;

    @BindView(R.id.sendCodeTextView)
    TextView sendCodeTextView;
    @BindView(R.id.codeEdit1)
    EditText codeEdit1;
    @BindView(R.id.codeEdit2)
    EditText codeEdit2;
    @BindView(R.id.codeEdit3)
    EditText codeEdit3;
    @BindView(R.id.codeEdit4)
    EditText codeEdit4;
    @BindView(R.id.codeLine1)
    View codeLine1;
    @BindView(R.id.codeLine2)
    View codeLine2;
    @BindView(R.id.codeLine3)
    View codeLine3;
    @BindView(R.id.codeLine4)
    View codeLine4;

    @BindView(R.id.codeTimeLayout)
    LinearLayout codeTimeLayout;

    private String phone;  //手机号码
    private Disposable timeDisposable;  //用于倒计时取消订阅

    @Override
    protected CodeInputPresenter loadPresenter() {
        return new CodeInputPresenter();
    }

    @Override
    protected int initTitle() {
        return R.string.NullText;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_code_input;
    }

    @Override
    protected void initData() {
        super.initData();
        getOther().setVisibility(View.VISIBLE);
        getOther().setText(R.string.pwdLogin);
        Bundle mBundle = getIntent().getExtras();
        phone = mBundle.getString("phone");
        if (!StringUtils.checkNullString(phone)) codePhoneTextView.setText(phone);
        codeEdit1.requestFocus();
        showInputMethod();
        codeEdit1.setOnFocusChangeListener(new FocusListener());
        codeEdit2.setOnFocusChangeListener(new FocusListener());
        codeEdit3.setOnFocusChangeListener(new FocusListener());
        codeEdit4.setOnFocusChangeListener(new FocusListener());
        changeTimeCode();
        codeEdit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.e("codeEdit1 afterTextChanged!");
                if (s.toString().length() == 1) {
                    codeEdit2.requestFocus();
                    codeLine1.setBackgroundResource(R.color.color3779d5);
                }else {
                    codeLine1.setBackgroundResource(R.color.color999999);
                }
            }
        });
        codeEdit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.e("codeEdit2 afterTextChanged!");
                if (s.toString().length() == 1){
                    codeEdit3.requestFocus();
                    codeLine2.setBackgroundResource(R.color.color3779d5);
                }else {
                    codeLine2.setBackgroundResource(R.color.color999999);
                }
            }
        });
        codeEdit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.e("codeEdit3 afterTextChanged!");
                if (s.toString().length() == 1) {
                    codeEdit4.requestFocus();
                    codeLine3.setBackgroundResource(R.color.color3779d5);
                }else {
                    codeLine3.setBackgroundResource(R.color.color999999);
                }
            }
        });
         codeEdit4.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {
                 LogUtils.e("codeEdit4 afterTextChanged!");
                 if (s.toString().length() == 1) {
                     codeLine4.setBackgroundResource(R.color.color3779d5);
                     StringBuilder builder = new StringBuilder();
                     builder.append(codeEdit1.getText().toString().trim());
                     builder.append(codeEdit2.getText().toString().trim());
                     builder.append(codeEdit3.getText().toString().trim());
                     builder.append(codeEdit4.getText().toString().trim());
                     mPresenter.justCode(mContext, phone, builder.toString());
                 } else {
                     codeLine4.setBackgroundResource(R.color.color999999);
                     }
             }
         });
        codeEdit2.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    codeEdit1.requestFocus();
                    LogUtils.e("codeEdit2 delete!");
                }
                return false;
            }
        });
        codeEdit3.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    codeEdit2.requestFocus();
                    LogUtils.e("codeEdit3 delete!");
                }
                return false;
            }
        });

        codeEdit4.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                   codeEdit3.requestFocus();
                    LogUtils.e("codeEdit4 delete!");
                }
                return false;
            }
        });
    }

    @Override
    public void intentToMain() {
        startActivity(MainActivity.class);
//        BaseApplication.getApplication().finishActivity(this);
        AppManager.getAppManager().finishActivity(this);
        AppManager.getAppManager().finishActivity(LoginActivity.class);
        AppManager.getAppManager().finishActivity(RegisterActivity.class);
        AppManager.getAppManager().finishActivity(CodeInputActivity.class);
    }

    /**
     * 倒计时显示
     */
    @Override
    public void changeTimeCode() {
        final long time = 60;
        //倒计时功能
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(time + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return time - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        sendCodeTextView.setVisibility(View.INVISIBLE);
                        codeTimeLayout.setVisibility(View.VISIBLE);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        timeDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        codeTimeTextView.setText(aLong + "");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtil.showToast(codeTimeTextView.getContext(), e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        sendCodeTextView.setVisibility(View.VISIBLE);
                        codeTimeLayout.setVisibility(View.INVISIBLE);
                    }
                });
    }

    @OnClick(R.id.sendCodeTextView)
    void onSendCodeClick(View view){
        mPresenter.getCodeAgain(this,phone);
    }



    class FocusListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus) ((EditText)v).setText(R.string.NullText);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeDisposable.dispose();
    }
}
