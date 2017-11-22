package com.guodong.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.guodong.R;


public class ClearEditText extends android.support.v7.widget.AppCompatEditText implements OnFocusChangeListener, TextWatcher {
	
	private Drawable cleardrawable;
	private boolean hasFocus;

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ClearEditText(Context context) {
		super(context);
		init();
	}
	public void insertDrawable(int id) {
		final SpannableString ss = new SpannableString("easy");
		//得到drawable对象，即所有插入的图片
		Drawable d = getResources().getDrawable(id);
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		//用这个drawable对象代替字符串easy
		ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
		//包括0但是不包括"easy".length()即：4。[0,4)
		ss.setSpan(span, 0, "easy".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		append(ss);
	}


	private void init(){
		cleardrawable = getCompoundDrawables()[2];
		if (cleardrawable == null) {
			cleardrawable = getResources().getDrawable(R.drawable.set_exit);
		}
		cleardrawable.setBounds(0, 0, cleardrawable.getIntrinsicWidth(), cleardrawable.getIntrinsicHeight());
		setClearIconVisible(false);
		setOnFocusChangeListener(this);
		addTextChangedListener(this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {
				boolean t = event.getX() > (getWidth() - getTotalPaddingRight() )&& (event.getX() < ((getWidth() - getPaddingRight())));
				if (t) {
					this.setText("");
				}
			}
		}
		return super.onTouchEvent(event);
	}
	

	private void setClearIconVisible(boolean b) {
		Drawable right = b ? cleardrawable : null;
		setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		this.hasFocus = hasFocus;
		if (hasFocus) {
			setClearIconVisible(getText().length() > 0 );
		}else{
			setClearIconVisible(false);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
		if (hasFocus) {
			setClearIconVisible(text.length() > 0);
		}
	}
	
	public void setAnimation(){
		this.setAnimation(shakeAnimation(3));
	}
	
	public static Animation shakeAnimation(int counts){
		Animation hh = new TranslateAnimation(0, 10, 0, 0);
		hh.setInterpolator(new CycleInterpolator(counts));
		hh.setDuration(1000);
		return hh;
	}

}
