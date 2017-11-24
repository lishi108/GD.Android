package com.guodong.business.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Description:轮播适配器
 * Created by LSQ108 on 2017/10/29.
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> mImageViewList;

    public BannerAdapter(List<ImageView> mImageViewList) {
        this.mImageViewList = mImageViewList;
    }

    @Override
    public int getCount() {
//        return mImageViewList.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(mImageViewList.size()>0) {
            position %= mImageViewList.size();
        }
        if (position<0){
            position = mImageViewList.size()+position;
        }
        ViewGroup parent = (ViewGroup) mImageViewList.get(position).getParent();
        if (parent != null) {
            parent.removeView(mImageViewList.get(position));
        }
        ((ViewPager)container).addView(mImageViewList.get(position));
        return mImageViewList.get(position);
    }


}
