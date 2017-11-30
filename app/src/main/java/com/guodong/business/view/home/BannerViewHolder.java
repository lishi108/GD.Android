package com.guodong.business.view.home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.guodong.R;
import com.guodong.business.adapter.BannerAdapter;
import com.guodong.business.adapter.BannerMaterialIndicator;
import com.guodong.business.bean.PictureInfo;
import com.guodong.utils.glide.GlideRoundTransform;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by Administrator on 2017/11/17.
 */

public class BannerViewHolder {
    @BindView(R.id.bannerViewPager)
    ViewPager bannerViewPager;
    @BindView(R.id.bannerIndicator)
    BannerMaterialIndicator bannerIndicator;
    @BindView(R.id.bannerLayout)
    LinearLayout bannerLayout;
    private List<PictureInfo> images = new ArrayList<>();
    private List<ImageView> imageViewList = new ArrayList<>();
    private Context context;
    private ImageHandler handler = new ImageHandler(new WeakReference<BannerViewHolder>(this));


    public BannerViewHolder(Context context, @NonNull View view) {
        this.context = context;
        ButterKnife.bind(this, view);
        initViewPager();
    }

    public void setImages(@NonNull List<PictureInfo> imagesList){
        if (images != null) {
            images.clear();
            imageViewList.clear();
            this.images.addAll(imagesList);
            for (int i = 0; i < images.size(); i++) {
                ImageView imageView = new ImageView(context);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                AutoUtils.auto(imageView);
                Glide.with(context)
                        .load(images.get(i).getResourceId())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .transform(new GlideRoundTransform(context, context.getResources().getDimensionPixelOffset(R.dimen.dp1)))
                        .into(imageView);
                imageViewList.add(imageView);
            }
            BannerAdapter adapter = new BannerAdapter(imageViewList);
            bannerViewPager.setAdapter(adapter);
            bannerIndicator.setCount(images.size());
            bannerIndicator.setImageHandler(handler);
            bannerViewPager.addOnPageChangeListener(bannerIndicator);
            bannerViewPager.setCurrentItem(images.size());//默认在中间，使用户看不到边界
            //开始轮播效果
            handler.sendEmptyMessageDelayed(ImageHandler.MSG_BREAK_SILENT, ImageHandler.MSG_DELAY);
        }
    }

    private void initViewPager() {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        ViewGroup.LayoutParams params = bannerViewPager.getLayoutParams();
        params.width = (int) (metrics.widthPixels * 0.86); // 宽度设置成屏幕宽度的86%，这里根据自己喜好设置
//        params.height = params.width * 240 / 386; // 利用已知图片的宽高比计算高度
        bannerViewPager.setOffscreenPageLimit(3);
        bannerViewPager.setLayoutParams(params);
        bannerViewPager.setPageMargin(context.getResources().getDimensionPixelSize(R.dimen.dp10));
        bannerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return bannerViewPager.dispatchTouchEvent(motionEvent);
            }
        });


    }



    public class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        public static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        public static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        public static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        public static final int MSG_PAGE_CHANGED = 4;

        //轮播间隔时间
        public static final long MSG_DELAY = 2000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<BannerViewHolder> weakReference;
        private int currentItem = images.size();

        public ImageHandler(WeakReference<BannerViewHolder> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BannerViewHolder bannerViewHolder = weakReference.get();
            if (bannerViewHolder == null) {
                //Activity已经回收，无需再处理UI了
                return;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (bannerViewHolder.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                bannerViewHolder.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    bannerViewHolder.bannerViewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    bannerViewHolder.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    bannerViewHolder.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }

    public void clear(){
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
        if(images!=null){
            images.clear();
            images = null;
        }
        if(imageViewList!=null){
            imageViewList.clear();
            imageViewList = null;
        }
    }

}
