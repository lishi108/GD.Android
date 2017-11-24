package com.guodong.business.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guodong.business.bean.PictureInfo;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Description:
 * Created by Administrator on 2017/11/22.
 */

public class MyViewpagerAdapter extends PagerAdapter {

    private List<PictureInfo> list;
    private Context context;

    public MyViewpagerAdapter(List<PictureInfo> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView vp_iv = new ImageView(context);
        vp_iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams params = new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        vp_iv.setLayoutParams(params);
        vp_iv.setImageResource(list.get(position%list.size()).getResourceId());
        container.addView(vp_iv);
        return vp_iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
