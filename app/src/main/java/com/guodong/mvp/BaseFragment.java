package com.guodong.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guodong.BaseApplication;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * Description:
 * Created by LSQ108 on 2017/10/24.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseContract.IBaseView {
    protected Bundle mBundle;
    protected P mPresenter;
    protected BaseActivity mActivity;
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }
    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ?
                    new Bundle() : getArguments();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            View view = inflater.inflate(getLayoutId(),null,false);
            rootView = view;
        }else{
            if(rootView.getParent()!=null){
                ((ViewGroup)rootView.getParent()).removeView(rootView);
            }
        }
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        //创建presenter
        mPresenter = loadPresenter();
        initData();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
        try {
            mPresenter.attachView(this);
        }catch (Exception e) {
            new ClassCastException(this.toString() + "实现IBaseView或者IBaseView子类接口");
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getContext());
        refWatcher.watch(this);
    }
    public Bundle getBundle() {
        return mBundle;
    }
    public BaseFragment getFragment() {
        return this;
    }
    protected abstract P loadPresenter();
    protected abstract int getLayoutId();
    protected abstract void initData();
}
