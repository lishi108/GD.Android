package com.guodong.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.widget.TextView;

import com.guodong.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Created by LSQ108 on 2017/10/27.
 */

public class LoadingDialog extends Dialog {
    @BindView(R.id.loadingContent)
    TextView loadingText;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }
    private void init() {
        this.getContext().setTheme(android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        setContentView(R.layout.loading_dialog);
        ButterKnife.bind(this);
        this.setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
//        WindowManager windowManager = getWindow().getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//        // Dialog宽度
////        lp.width = (int) (display.getWidth() * 0.7);
//        Window window = getWindow();
//        window.setAttributes(lp);
//        window.getDecorView().getBackground().setAlpha(0);
    }
    public LoadingDialog setMessage(String message){
        if(message == null||message.equals(""))
            loadingText.setText(R.string.loadingText);
        else
            loadingText.setText(message);
        return this;
    }
}
