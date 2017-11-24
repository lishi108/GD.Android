package com.guodong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Description:
 * Created by Administrator on 2017/11/24.
 */

public class BetterPtrFrameLayout extends PtrClassicFrameLayout {
    public BetterPtrFrameLayout(Context context) {
        super(context);
    }

    public BetterPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BetterPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private boolean disallowInterceptTouchEvent = false;
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        disallowInterceptTouchEvent = disallowIntercept;

        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                this.requestDisallowInterceptTouchEvent(false);
                disableWhenHorizontalMove(true);
                break;
        }
        if (disallowInterceptTouchEvent) {
            return dispatchTouchEventSupper(e);
        }
        return super.dispatchTouchEvent(e);
    }
}
