package com.guodong.business.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.guodong.R;
import com.guodong.business.view.goods.GoodsFragment;
import com.guodong.business.view.home.HomeFragment;
import com.guodong.business.view.user.PersonalFragment;
import com.guodong.mvp.BaseActivity;
import com.guodong.mvp.BaseFragment;
import com.guodong.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.homeImageView)
    ImageView homeImageView;
    @BindView(R.id.buyImageView)
    ImageView buyImageView;
    @BindView(R.id.findImageView)
    ImageView findImageView;
    @BindView(R.id.personImageView)
    ImageView personImageView;
    private Context mContext;

    @Override
    protected int getFragmentContentId() {
        return R.id.mainFrameLayout;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mContext = MainActivity.this;
        final List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new GoodsFragment());
        fragments.add(new PersonalFragment());

        addFragment(fragments.get(0));
        changeTableStatus();

    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @OnClick({R.id.homeLayout, R.id.buyLayout, R.id.findLayout, R.id.personLayout})
    void onTableClick(View view) {
//        if (firstTag) firstTag = false;
//        else
            changeTableStatus();
        switch (view.getId()) {
            case R.id.homeLayout:
                addFragment(new HomeFragment());
                Glide.with(mContext)
                        .load(R.drawable.home)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new GlideDrawableImageViewTarget(homeImageView, 1));
                break;
            case R.id.buyLayout:
                addFragment(new GoodsFragment());
                Glide.with(MainActivity.this)
                        .load(R.drawable.cart)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new GlideDrawableImageViewTarget(buyImageView, 1));
                break;
            case R.id.findLayout:
                addFragment(new GoodsFragment());
                Glide.with(MainActivity.this)
                        .load(R.drawable.find)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new GlideDrawableImageViewTarget(findImageView, 1));
                break;
            case R.id.personLayout:
                addFragment(new PersonalFragment());
                Glide.with(MainActivity.this)
                        .load(R.drawable.person)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new GlideDrawableImageViewTarget(personImageView, 1));
                break;
        }
    }

    private void changeTableStatus() {
        Glide.with(mContext).load(R.drawable.home0).into(homeImageView);
        Glide.with(mContext).load(R.drawable.cart0).into(buyImageView);
        Glide.with(mContext).load(R.drawable.find0).into(findImageView);
        Glide.with(mContext).load(R.drawable.person0).into(personImageView);
    }
}
