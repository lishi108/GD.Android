package com.guodong.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**屏幕自动适配工具类
 *（1） 在Activity中设置：
 AutoUtils.setSize(this, false, 720, 1280);
 //第一个参数this，是Activity对象。
 //第二个参数false，是boolean变量，表示没有状态栏。如果你的APP要有状态栏，就设置为true。
 //第三个参数720，是int变量，是设计尺寸的宽度。
 //第四个参数1280，是int变量，是设计尺寸的高度。
 （2）像RecyclerView、ListView这些，
 有一点要注意的是：item的背景图要拿出来独立用一个ImageView显示，不要在root view中设置background，因为在列表中它的拉伸不对。
 然后在你的ViewHolder构造方法中：AutoUtils.auto(view);
 （3）对于那些宽高不定的控件，例如TextView，当然是wrap_content，有些需要match_parent的地方就match_parent。
     对于图片ImageView，一定要设置fitXY    android:scaleType="fitXY"
 */

public class AutoUtils {

	private static int displayWidth;
	private static int displayHeight;
	
	private static int designWidth;
	private static int designHeight;
	
	private static double textPixelsRate;
	
	public static void setSize(Activity act, boolean hasStatusBar, int designWidth, int designHeight){
		if(act==null || designWidth<1 || designHeight<1)return;
		
        Display display = act.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
        int height = display.getHeight();
        
		if (hasStatusBar) {
			height -= getStatusBarHeight(act);
		}
		
		AutoUtils.displayWidth=width;
		AutoUtils.displayHeight=height;
		
		AutoUtils.designWidth=designWidth;
		AutoUtils.designHeight=designHeight;
		
		double displayDiagonal=Math.sqrt(Math.pow(AutoUtils.displayWidth, 2)+Math.pow(AutoUtils.displayHeight, 2));
		double designDiagonal=Math.sqrt(Math.pow(AutoUtils.designWidth, 2)+Math.pow(AutoUtils.designHeight, 2));
		AutoUtils.textPixelsRate=displayDiagonal/designDiagonal;	
	}
	
    public static int getStatusBarHeight(Context context)
    {
		int result = 0;
		try {
			int resourceId = context.getResources().getIdentifier(
					"status_bar_height", "dimen", "android");
			if (resourceId > 0) {
				result = context.getResources().getDimensionPixelSize(
						resourceId);
			}
		} catch (Resources.NotFoundException e) {
			e.printStackTrace();
		}
		return result;
    }
    
    public static void auto(Activity act){
    	if(act==null || displayWidth<1 || displayHeight<1)return;
    	
    	View view=act.getWindow().getDecorView();
    	auto(view);
    }
    
    public static void auto(View view){
    	if(view==null || displayWidth<1 || displayHeight<1)return;
    	
    	AutoUtils.autoTextSize(view);
    	AutoUtils.autoSize(view);
    	AutoUtils.autoPadding(view);
    	AutoUtils.autoMargin(view);
    	
    	if(view instanceof ViewGroup){
    		auto((ViewGroup)view);
    	}
    	
    }
    
    private static void auto(ViewGroup viewGroup){
    	int count = viewGroup.getChildCount();
    	
		for (int i = 0; i < count; i++) {

			View child = viewGroup.getChildAt(i);
			
			if(child!=null){
				auto(child);
			}
		}
    }
    
    public static void autoMargin(View view){
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if(lp == null)return ;

        lp.leftMargin = getDisplayWidthValue(lp.leftMargin);
        lp.topMargin = getDisplayHeightValue(lp.topMargin);
        lp.rightMargin = getDisplayWidthValue(lp.rightMargin);
        lp.bottomMargin = getDisplayHeightValue(lp.bottomMargin);
        
    }

    public static void autoPadding(View view){
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();

        l = getDisplayWidthValue(l);
        t = getDisplayHeightValue(t);
        r = getDisplayWidthValue(r);
        b = getDisplayHeightValue(b);

        view.setPadding(l, t, r, b);
    }

    public static void autoSize(View view){
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (lp == null) return;
        
        if(lp.width>0){
        	lp.width = getDisplayWidthValue(lp.width);
        }

        if(lp.height>0){
        	lp.height = getDisplayHeightValue(lp.height);
        }
        
    }
    
    public static void autoTextSize(View view){
    	if(view instanceof TextView){
    		double designPixels=((TextView) view).getTextSize();
    		double displayPixels=textPixelsRate*designPixels;
    		((TextView) view).setIncludeFontPadding(false);
    		((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) displayPixels);
    	}
    }

    public static int getDisplayWidthValue(int designWidthValue){
    	if(designWidthValue<2){
    		return designWidthValue;
    	}
        return designWidthValue * displayWidth / designWidth;
    }

    public static int getDisplayHeightValue(int designHeightValue){
    	if(designHeightValue<2){
    		return designHeightValue;
    	}
        return designHeightValue * displayHeight / designHeight;
    }
}
