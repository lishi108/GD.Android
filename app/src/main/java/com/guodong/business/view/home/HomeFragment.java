package com.guodong.business.view.home;

import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guodong.R;
import com.guodong.business.adapter.CustomLinearLayoutManager;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.HotEquipmentInfo;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.contract.HomeContract;
import com.guodong.business.presenter.home.HomePresenter;
import com.guodong.mvp.BaseFragment;
import com.guodong.utils.glide.GlideRoundTransform;
import com.guodong.widget.BetterPtrFrameLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeView{
    @BindView(R.id.ptrFrameLayout)
    BetterPtrFrameLayout ptrFrameLayout;
    @BindView(R.id.homeNestScrollview)
    NestedScrollView homeNestScrollview;
    @BindView(R.id.homeRecyclerView)
    RecyclerView homeRecyclerView;
    private List<PictureInfo> bannerImages;
    private CommonAdapter<HotEquipmentInfo> commonAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private List<HotEquipmentInfo> mDataList;
    private View bannarView;   //轮播图View
    private View gameListView;  //横向列表View
    private BannerViewHolder bannerViewHolder; //轮播图辅助类
    private int viewHeight = 0; //轮播图的高度

    private List<GameInfo> gameInfoList;
    private GameListViewHolder gameListViewHolder;


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
        if(gameInfoList==null) gameInfoList = new ArrayList<>();
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
               mPresenter.getBannerImage();
                ptrFrameLayout.refreshComplete();
                ptrFrameLayout.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        ptrFrameLayout.setHorizontalScrollBarEnabled(true);
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh();
            }
        }, 100);

        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(getContext());
        linearLayoutManager.setScrollEnabled(false);
        homeRecyclerView.setLayoutManager(linearLayoutManager);

        homeRecyclerView.setNestedScrollingEnabled(false);
        homeRecyclerView.setFocusable(false);
        commonAdapter = new CommonAdapter<HotEquipmentInfo>(getContext(),R.layout.item_home_recyclerview,mDataList) {
            @Override
            protected void convert(ViewHolder holder, HotEquipmentInfo hotEquipmentInfo, int position) {
                ImageView imageView = holder.getView(R.id.hotImageView);
                Glide.with(getContext())
                        .load(hotEquipmentInfo.getUrlId())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .transform(new GlideRoundTransform(getContext(), getContext().getResources().getDimensionPixelOffset(R.dimen.dp5)))
                        .into(imageView);
                holder.setText(R.id.titleNameText,hotEquipmentInfo.getTitle());
            }
        };
        initHeaderAndFooter();
        homeRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mPresenter.getBannerImage();
        mPresenter.getGameData();
        mPresenter.getHotEquipment();
    }

    @Override
    public void setBannerImages(List<PictureInfo> images) {
        if(bannerImages!=null){
            bannerImages.clear();
            bannerImages.addAll(images);
            bannerViewHolder.setImages(bannerImages);
//            mHeaderAndFooterWrapper.notifyDataSetChanged();
        }
    }

    private void initHeaderAndFooter()
    {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(commonAdapter);

        //轮播View
        bannarView = LayoutInflater.from(getContext()).inflate(R.layout.item_banner,null);
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(PtrFrameLayout.LayoutParams.MATCH_PARENT, PtrFrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,getResources().getDimensionPixelOffset(R.dimen.dp10),0,0);
        bannarView.setLayoutParams(layoutParams);
        bannerViewHolder = new BannerViewHolder(getContext(),bannarView);

       //横向列表View
        gameListView = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_recyclerview,null);
        LinearLayoutCompat.LayoutParams layoutParams2 = new LinearLayoutCompat.LayoutParams(PtrFrameLayout.LayoutParams.MATCH_PARENT, PtrFrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,getResources().getDimensionPixelOffset(R.dimen.dp10),0,0);
        gameListView.setLayoutParams(layoutParams2);
        gameListViewHolder = new GameListViewHolder(getContext(),gameListView);

        mHeaderAndFooterWrapper.addHeaderView(bannarView);
        mHeaderAndFooterWrapper.addHeaderView(gameListView);
//        bannarView.post(new Runnable() {
//            @Override
//            public void run() {
//                viewHeight = bannarView.getHeight();
//            }
//        });
        //热门type
        View hotView = LayoutInflater.from(getContext()).inflate(R.layout.item_type_hot,null);
        LinearLayoutCompat.LayoutParams layoutParams3 = new LinearLayoutCompat.LayoutParams(PtrFrameLayout.LayoutParams.MATCH_PARENT, PtrFrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,getResources().getDimensionPixelOffset(R.dimen.dp10),0,0);
        hotView.setLayoutParams(layoutParams3);
        mHeaderAndFooterWrapper.addHeaderView(hotView);
    }

    @Override
    public void setGameData(@NonNull List<GameInfo> data) {
        if(data!=null){
            gameInfoList.clear();
            gameInfoList.addAll(data);
            gameListViewHolder.setData(data);
        }
    }

    @Override
    public void setHotEquipment(@NonNull List<HotEquipmentInfo> hotEquipments) {
        if(hotEquipments.size()>0){
            mDataList.clear();
            mDataList.addAll(hotEquipments);
            commonAdapter.notifyDataSetChanged();
        }
    }
}
