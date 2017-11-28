package com.guodong.business.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

import com.guodong.R;
import com.guodong.business.view.home.BannerViewHolder;

/**
 * Description: 游标指示器
 * Created by Administrator on 2017/11/14.
 */

public class BannerMaterialIndicator extends View implements ViewPager.OnPageChangeListener {

    private static final String TAG = BannerMaterialIndicator.class.getSimpleName();
    private static final int UNDEFINED_PADDING = -1;
    private final Interpolator interpolator = new FastOutSlowInInterpolator();
    private final Paint indicatorPaint;
    private final Paint selectedIndicatorPaint;
    private final float indicatorRadius;
    private final float indicatorPadding;

    private final RectF selectorRect;
    private int count;
    private int selectedPage = 0;
    private float deselectedAlpha = 0.2f;
    private float offset;
    private PageListener pageListener;
    private boolean firstDraw = true;



    private BannerViewHolder.ImageHandler imageHandler;

    public BannerMaterialIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerMaterialIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialIndicator, 0, R.style.MaterialIndicator);
        selectedIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indicatorPaint.setColor(typedArray.getColor(R.styleable.MaterialIndicator_mi_defaultColor, 0));
        indicatorPaint.setAlpha((int) (deselectedAlpha * 255));
        selectorRect = new RectF();
        if (isInEditMode()) {
            count = 3;
        }
       try {
            indicatorRadius = typedArray.getDimension(R.styleable.MaterialIndicator_mi_indicatorRadius, 0);
            indicatorPadding = typedArray.getDimension(R.styleable.MaterialIndicator_mi_indicatorPadding, UNDEFINED_PADDING);
            selectedIndicatorPaint.setColor(typedArray.getColor(R.styleable.MaterialIndicator_mi_indicatorColor, 0));
        } finally {
            typedArray.recycle();
        }
    }
    public BannerViewHolder.ImageHandler getImageHandler() {
        return imageHandler;
    }

    public void setImageHandler(BannerViewHolder.ImageHandler imageHandler) {
        this.imageHandler = imageHandler;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        selectedPage = position%count;
        offset = positionOffset;
//        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        selectedPage = position%count;
        offset = 0.0f;
        invalidate();
        Log.e("TAG","selectedPage:"+selectedPage+";position:"+position+";count:"+count);

        if (pageListener != null)
            pageListener.onPageListener(position%count);
        imageHandler.sendMessage(Message.obtain(imageHandler, BannerViewHolder.ImageHandler.MSG_PAGE_CHANGED, position, 0));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                imageHandler.sendEmptyMessage(BannerViewHolder.ImageHandler.MSG_KEEP_SILENT);
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                imageHandler.sendEmptyMessageDelayed(BannerViewHolder.ImageHandler.MSG_UPDATE_IMAGE, BannerViewHolder.ImageHandler.MSG_DELAY);
                break;
            default:
                break;
        }
    }

    public void setCount(int indicators) {
        this.count = indicators;
        requestLayout();
        invalidate();
        firstDraw = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        if (getLayoutParams().width == ViewPager.LayoutParams.WRAP_CONTENT) {
            width = getSuggestedMinimumWidth();
        }
        setMeasuredDimension(width, getSuggestedMinimumHeight());
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) (indicatorDiameter() * count + getInternalPadding());
    }

    private float getInternalPadding() {
        if (indicatorPadding == UNDEFINED_PADDING || indicatorPadding == 0 || count == 0) {
            return 0;
        }
        return indicatorPadding * (count - 1);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return getPaddingTop() + getPaddingBottom() + (int) indicatorDiameter();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float gap = getGapBetweenIndicators();
        for (int i = 0; i < count; i++) {
            float position = indicatorStartX(gap, i);
            canvas.drawCircle(position + indicatorRadius, midY(), indicatorRadius, indicatorPaint);
            if(i==selectedPage){
                selectorRect.set((float) (position - indicatorRadius*1.5), midY() - indicatorRadius, position+indicatorRadius*2, midY() + indicatorRadius);
                canvas.drawRoundRect(selectorRect, indicatorRadius, indicatorRadius, selectedIndicatorPaint);
            }

        }
    }

    private float getGapBetweenIndicators() {
        if (indicatorPadding == UNDEFINED_PADDING) {
            return (getWidth() - indicatorDiameter()) / (count+1);
        } else {
            return indicatorPadding;
        }
    }

    private float indicatorStartX(float gap, int page) {
        return ViewCompat.getPaddingStart(this) + gap * page + indicatorRadius;
    }

    private float interpolatedOffset() {
        return interpolator.getInterpolation(offset);
    }

    private float indicatorDiameter() {
        return indicatorRadius * 2;
    }

    private float midY() {
        return getHeight() / 2f;
    }

    public interface PageListener {
        void onPageListener(int position);
    }

    public PageListener getPageListener() {
        return pageListener;
    }

    public void setPageListener(PageListener pageListener) {
        this.pageListener = pageListener;
    }
}

