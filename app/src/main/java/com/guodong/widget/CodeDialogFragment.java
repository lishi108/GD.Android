package com.guodong.widget;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guodong.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by Administrator on 2017/11/24.
 */

public class CodeDialogFragment extends DialogFragment {
    @BindView(R.id.codeDialogImage)
    ImageView codeImage;
    @BindView(R.id.codeInfoTextView)
    TextView infoText;

    public static CodeDialogFragment getInstance(boolean isOk){
        CodeDialogFragment dialogFragment = new CodeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("ok",isOk);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.code_dialogframent, container);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        if(bundle!=null){
            boolean ok = bundle.getBoolean("ok");
            if(ok){
                codeImage.setImageResource(R.drawable.correct);
                infoText.setText(R.string.get_code_success);
            }else {
                codeImage.setImageResource(R.drawable.warning);
                infoText.setText(R.string.get_code_failed);
            }
        }
        return view;
    }
}
