package com.guodong.mvp;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.guodong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description:
 * Created by LSQ108 on 2017/10/24.
 */

public abstract class BaseTitleActivity<P extends BasePresenter> extends BaseActivity<P> {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.other)
    TextView mOther;  //标题右键
    private Unbinder unbinder;

    public void setTitle(@StringRes int title) {
        mTitle.setText(title);
    }

    /**
     * 设置标题内容的资源ID
     *
     * @return
     */
    public int initTitle() {
        return -1;
    }

    @Override
    protected void initData() {
        if (loadPresenter() != null)
            super.initData();
        setTitle(initTitle());
    }

    /**
     * 返回标题栏右控件
     *
     * @return
     */
    public TextView getOther() {
        return mOther;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.app_bar_base;
    }

    @OnClick(R.id.back)
    public void onBackClick(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected P loadPresenter() {
        return null;
    }
}
