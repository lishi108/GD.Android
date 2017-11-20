package com.guodong.mvp;

import android.content.Context;

/**
 * Description:
 * Created by Administrator on 2017/11/20.
 */

public abstract class BaseTitleFragment<P extends BasePresenter> extends BaseFragment<P>{
    protected BaseTitleActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseTitleActivity) context;
    }
}