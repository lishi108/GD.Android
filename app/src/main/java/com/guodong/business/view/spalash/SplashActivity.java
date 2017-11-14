package com.guodong.business.view.spalash;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.guodong.R;
import com.guodong.animation.ZoomOutPageTransformer;
import com.guodong.business.adapter.CustomPagerAdapter;
import com.guodong.business.bean.PictureInfo;
import com.guodong.business.config.DataManager;
import com.guodong.business.contract.SplashContract;
import com.guodong.business.presenter.spalash.SplashPresenter;
import com.guodong.business.view.user.LoginActivity;
import com.guodong.mvp.BaseActivity;
import com.guodong.utils.DensityUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.ISplashView, ViewPager.OnPageChangeListener {

    @BindView(R.id.welcomeLayout)
    RelativeLayout welcomeLayout;
    @BindView(R.id.splashImage)
    ImageView splashImage;
    @BindView(R.id.spalashViewPager)
    ViewPager spalashViewPager;
    @BindView(R.id.pointGroupLayout)
    LinearLayout pointGroupLayout;     //引导圆点的父控件

//    @BindView(R.id.pointView)
//    View pointView;   //选中的圆点
    @BindView(R.id.startButton)
    Button startButton;   //开始体验按钮
    @BindView(R.id.skipButton)
    Button skipButton;    //跳过按钮

    // 引导页背景图片的id数组
    private List<PictureInfo> imageList;
    private int mPointWidth = 0;// 圆点间的距离
    private ArrayList<View> mImageViewList;
    private CustomPagerAdapter adapter;

    @Override
    protected SplashPresenter loadPresenter() {
        return new SplashPresenter();
    }


    @Override
    protected void initData() {
        super.initData();
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
                if(DataManager.getIsFirst()) e.onNext(true);
                else {
                    splashImage.setVisibility(View.VISIBLE);
                    splashImage.setBackgroundResource(R.mipmap.welcome);
                    welcomeLayout.setVisibility(View.GONE);
                    intentToLoginActivity();
                    e.onComplete();
                    Logger.e("不是第一次进入");
                }
            }
        })
        .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Logger.e("第一次进入,准备请求图片");
                splashImage.setVisibility(View.GONE);
                welcomeLayout.setVisibility(View.VISIBLE);

                mPresenter.getImages();
                mImageViewList = new ArrayList<>();

                //    初始化引导页的三个页面

                adapter = new CustomPagerAdapter(mImageViewList);
                spalashViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                spalashViewPager.setAdapter(adapter);
                spalashViewPager.addOnPageChangeListener(SplashActivity.this);
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spalash;
    }

    @Override
    public void setImages(List<PictureInfo> images) {
        if (images!=null){
            if(imageList!=null)  imageList.clear();
            imageList = images;
            mImageViewList.clear();
            for (int i = 0; i < imageList.size(); i++) {
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundResource(imageList.get(i).getResourceId()); // 设置引导页的背景图片
                mImageViewList.add(imageView);

                Log.d("Point View", "第" + i + "个圆点");
                View point = new View(this);
//       设置引导页默认圆点背景
                point.setBackgroundResource(R.drawable.shape_point_gray);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DensityUtils.dp2px(this, 12), DensityUtils.dp2px(this, 12));
                if (i > 0) {
                    params.leftMargin = DensityUtils.dp2px(this, 10); //从第二个圆点开始设置左间距
                }
                point.setLayoutParams(params);

                pointGroupLayout.addView(point); //将圆点添加到线性布局中
            }


            adapter.notifyDataSetChanged();
            spalashViewPager.setCurrentItem(0);
            if (imageList.size()>0) pointGroupLayout.getChildAt(0).setBackgroundResource(R.drawable.shape_point_white);
        }
    }

    private void intentToLoginActivity() {
        splashImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class);
                finish();
            }
        },1000);
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == imageList.size() - 1) {
//          最后一个页面，设置开始体验按钮显示
            startButton.setVisibility(View.VISIBLE);
        } else {
            startButton.setVisibility(View.INVISIBLE);
        }
        changePointStatus(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changePointStatus(int position){
        for(int i=0;i<imageList.size();i++){
            if(position == i) pointGroupLayout.getChildAt(i).setBackgroundResource(R.drawable.shape_point_white);
            else pointGroupLayout.getChildAt(i).setBackgroundResource(R.drawable.shape_point_gray);
        }
    }


    @OnClick({R.id.skipButton,R.id.startButton})
    void onUseViewClick(View view) {
        intentToLoginActivity();
    }
}
