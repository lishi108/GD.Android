package com.guodong.business.view;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.guodong.R;
import com.guodong.business.view.find.FindFragment;
import com.guodong.business.view.goods.GamesFragment;
import com.guodong.business.view.home.HomeFragment;
import com.guodong.business.view.user.PersonalFragment;
import com.guodong.mvp.BaseActivity;
import com.guodong.mvp.BaseContract;
import com.guodong.mvp.BasePresenter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<BasePresenter> implements BaseContract.IBaseView {
    @BindView(R.id.homeImageView)
    ImageView homeImageView;
    @BindView(R.id.buyImageView)
    ImageView buyImageView;
    @BindView(R.id.findImageView)
    ImageView findImageView;
    @BindView(R.id.personImageView)
    ImageView personImageView;
    private Context mContext;
    private FragmentTransaction transaction;

    private HomeFragment homeFragment;
    private GamesFragment gamesFragment;
    private FindFragment findFragment;
    private PersonalFragment personalFragment;


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
        super.initData();
        mContext = MainActivity.this;
        changeTableStatus();
        selectFragment(0);

    }

    @OnClick({R.id.homeLayout, R.id.buyLayout, R.id.findLayout, R.id.personLayout})
    void onTableClick(View view) {
//        if (firstTag) firstTag = false;
//        else
            changeTableStatus();
        switch (view.getId()) {
            case R.id.homeLayout:
                selectFragment(0);
                showGif(homeImageView,R.drawable.home);
                break;
            case R.id.buyLayout:
                selectFragment(1);
                showGif(buyImageView,R.drawable.cart);
                break;
            case R.id.findLayout:
                selectFragment(2);
                showGif(findImageView,R.drawable.find);
                break;
            case R.id.personLayout:
                selectFragment(3);
                showGif(personImageView,R.drawable.person);
                break;
        }
    }

    /**
     * 显示Gif动画，播放一次
     * @param imageView
     * @param resourceId
     */
    private void showGif(ImageView imageView,int resourceId){
        Glide.with(MainActivity.this)
                .load(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new GlideDrawableImageViewTarget(imageView, 1));
    }

    /**
     * fragment 切换
     * @param position
     */
    private void selectFragment(int position){
        transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (position){
            case 0:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.mainFrameLayout,homeFragment);
                }else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if(gamesFragment == null){
                    gamesFragment = new GamesFragment();
                    transaction.add(R.id.mainFrameLayout, gamesFragment);
                }else {
                    transaction.show(gamesFragment);
                }
                break;
            case 2:
                if(findFragment == null){
                    findFragment = new FindFragment();
                    transaction.add(R.id.mainFrameLayout,findFragment);
                }else {
                    transaction.show(findFragment);
                }
                break;
            case 3:
                if(personalFragment == null){
                    personalFragment = new PersonalFragment();
                    transaction.add(R.id.mainFrameLayout,personalFragment);
                }else {
                    transaction.show(personalFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 初始化底部状态
     */
    private void changeTableStatus() {
        Glide.with(mContext).load(R.drawable.home0).into(homeImageView);
        Glide.with(mContext).load(R.drawable.cart0).into(buyImageView);
        Glide.with(mContext).load(R.drawable.find0).into(findImageView);
        Glide.with(mContext).load(R.drawable.person0).into(personImageView);
    }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (gamesFragment != null) {
            transaction.hide(gamesFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (personalFragment != null) {
            transaction.hide(personalFragment);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.e("Main Activity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.e("Main Activity destory!");
    }

    @Override
    protected BasePresenter loadPresenter() {
        return new BasePresenter() {
            @Override
            public BaseContract.IBaseModel loadModel() {
                return null;
            }
        };
    }
}
