package com.guodong.business.view.home;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guodong.R;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.HomeContract;
import com.guodong.business.presenter.home.HomePresenter;
import com.guodong.mvp.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeView{

    @BindView(R.id.ptrHomeFrameLayout)
    PtrClassicFrameLayout ptrHomeFrameLayout;
    @BindView(R.id.homeNestScrollview)
    NestedScrollView homeNestScrollview;
    @BindView(R.id.homeRecyclerView)
    RecyclerView homeRecyclerView;
    private List<PictureInfo> bannerImages;
    private CommonAdapter<String> commonAdapter;
    private List<String> mDataList;
    @Override
    protected HomePresenter loadPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        if(bannerImages == null) bannerImages = new ArrayList<>();
        if(mDataList == null) mDataList = new ArrayList<>();
        commonAdapter = new CommonAdapter<String>(getContext(),R.layout.item_home_recyclerview,mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.itemTextView, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };

        ptrHomeFrameLayout.setLastUpdateTimeRelateObject(this);
        ptrHomeFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                mDataList.clear();
                mPresenter.getBannerImage();
                mPresenter.getData();
//                ptrHomeFrameLayout.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    @Override
    public void setBannerImages(List<PictureInfo> bannerImages) {

    }

    @Override
    public void setData(List<String> data) {

    }
}
