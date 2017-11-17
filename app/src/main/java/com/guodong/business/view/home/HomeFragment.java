package com.guodong.business.view.home;

import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.guodong.R;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.HomeContract;
import com.guodong.business.presenter.home.HomePresenter;
import com.guodong.mvp.BaseFragment;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

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
    @BindView(R.id.search_bar)
    RelativeLayout search_bar;
    private List<PictureInfo> bannerImages;
    private CommonAdapter<String> commonAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private List<String> mDataList;

    private View bannarView;   //轮播图View
    private BannerViewHolder bannerViewHolder; //轮播图辅助类
    private int viewHeight = 0; //轮播图的高度

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
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        homeNestScrollview.getViewTreeObserver().addOnScrollChangedListener(   new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Logger.e("TAG", "View's size is :" + viewHeight);
                //改变toolbar的透明度
                int scrollY = homeNestScrollview.getScrollY();
                Logger.e("TAG", "scrollY is :" + scrollY);
                //滚动距离>=大图高度-toolbar高度 即toolbar完全盖住大图的时候 且不是伸展状态 进行伸展操作
                if (0 < scrollY && scrollY <= viewHeight) {

                    float scale = (float) scrollY / viewHeight;
                    float alpha = (255 * scale);
                    search_bar.setBackgroundColor(Color.argb((int) alpha, 144, 151, 166));

                }
                //滚动距离<=0时 即滚动到顶部时  且当前伸展状态 进行收缩操作
                else if (scrollY <= 0) {
//                    search_bar.setBackgroundResource(R.color.transparent);
                    search_bar.setBackgroundColor(Color.argb(0, 144, 151, 166));

                } else {
                    search_bar.setBackgroundColor(Color.argb(255, 144, 151, 166));
                }
            }
        });
    }

    @Override
    public void setBannerImages(List<PictureInfo> bannerImages) {
        if(bannerImages!=null){
            ptrHomeFrameLayout.refreshComplete();
            bannerImages.clear();
            bannerImages.addAll(bannerImages);
            bannerViewHolder = new BannerViewHolder(getContext(),bannarView,bannerImages);
        }
    }

    private void initHeaderAndFooter()
    {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);

        bannarView = LayoutInflater.from(getContext()).inflate(R.layout.item_banner,null);

        bannarView.setLayoutParams(new PtrFrameLayout.LayoutParams(PtrFrameLayout.LayoutParams.MATCH_PARENT, PtrFrameLayout.LayoutParams.WRAP_CONTENT));

        mHeaderAndFooterWrapper.addHeaderView(bannarView);
        bannarView.post(new Runnable() {
            @Override
            public void run() {
                viewHeight = bannarView.getHeight();
            }
        });

    }

    @Override
    public void setData(List<String> data) {

    }
}
