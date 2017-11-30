package com.guodong.mvp;

import butterknife.ButterKnife;


/**
 * Description:
 * Created by LSQ108 on 2017/10/24.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AbsActivity implements BaseContract.IBaseView {
    protected P mPresenter;


    @Override
    protected void initData() {
        ButterKnife.bind(this);
        mPresenter = loadPresenter();
        try {
            mPresenter.attachView(this);
        } catch (Exception e) {
            throw new ClassCastException(this.toString() + "实现IBaseView或者IBaseView子类接口");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
    protected abstract P loadPresenter();
}
