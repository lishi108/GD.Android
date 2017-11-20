package com.guodong.business.view.user;

import android.view.View;
import android.widget.TextView;

import com.guodong.R;
import com.guodong.business.contract.CodeInputContract;
import com.guodong.business.presenter.user.CodeInputPresenter;
import com.guodong.mvp.BaseTitleFragment;
import com.guodong.utils.StringUtils;
import com.guodong.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class CodeInputFragment extends BaseTitleFragment<CodeInputPresenter> implements CodeInputContract.ICodeInputView {

    @BindView(R.id.codePhoneTextView)
    TextView codePhoneTextView;
    @BindView(R.id.codeTimeTextView)
    TextView codeTimeTextView;

    @BindView(R.id.sendCodeTextView)
    TextView sendCodeTextView;
    @Override
    protected CodeInputPresenter loadPresenter() {
        return new CodeInputPresenter();
    }
    @Override
    protected void initData() {
        mActivity.getOther().setVisibility(View.VISIBLE);
        mActivity.getOther().setText(R.string.pwdLogin);
        mBundle = getBundle();
        String phone  = mBundle.getString("phone");
        if(!StringUtils.checkNullString(phone)) codePhoneTextView.setText(phone);

        final long time = 60;
        //倒计时功能
        Observable.interval(0,1, TimeUnit.SECONDS)
                .take(time+1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return time-aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        sendCodeTextView.setVisibility(View.INVISIBLE);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        codeTimeTextView.setText(aLong+"");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtil.showToast(codeTimeTextView.getContext(),e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        sendCodeTextView.setVisibility(View.VISIBLE);
                    }
                });

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_code_input;
    }



}
