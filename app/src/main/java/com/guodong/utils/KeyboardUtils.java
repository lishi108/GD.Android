package com.guodong.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

/**
 * Description: 用于键盘弹出和关闭的优化
 * Created by Administrator on 2017/12/5.
 */
public class KeyboardUtils {
    private View mChildOfContent;
    private int usableHeightPrevious;
    private int statusBarHeight;
    private FrameLayout.LayoutParams frameLayoutParams;
    private Activity mActivity;

    private KeyboardUtils(Activity activity) {
        mActivity = activity;
        FrameLayout content = activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        statusBarHeight = getStatusBarHeight();
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }
    public static void assistActivity(Activity activity) {
        new KeyboardUtils(activity);
    }
    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                // 如果有高度变化，mChildOfContent.requestLayout()之后界面才会重新测量
                // 这里就随便让原来的高度减去了1
                frameLayoutParams.height = usableHeightSansKeyboard - 1;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }
    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return r.bottom - r.top + statusBarHeight;
    }
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            int dimensionPixelSize = mActivity.getResources().getDimensionPixelSize(x);
            return dimensionPixelSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
