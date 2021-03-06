package com.guodong.business.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:普通类型PagerAdapter
 * Created by LSQ108 on 2017/10/29.
 */

public class CustomPagerAdapter extends PagerAdapter {
    private List<View> mImageViewList;

    public CustomPagerAdapter(List<View> mImageViewList) {
        this.mImageViewList = mImageViewList;
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup parent = (ViewGroup) mImageViewList.get(position).getParent();
        if (parent != null) {
            parent.removeView(mImageViewList.get(position));
        }
        container.addView(mImageViewList.get(position));
        return mImageViewList.get(position);
    }


}
