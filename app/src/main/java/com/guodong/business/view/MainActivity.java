package com.guodong.business.view;

import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.guodong.R;
import com.guodong.business.view.goods.GoodsFragment;
import com.guodong.business.view.home.HomeFragment;
import com.guodong.business.view.user.PersonalFragment;
import com.guodong.mvp.BaseActivity;
import com.guodong.mvp.BaseFragment;
import com.guodong.mvp.BasePresenter;
import com.guodong.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.mainFrameLayout)
    FrameLayout mainFrameLayout;
    @BindView(R.id.homeButton)
    RadioButton homeButton;
    @BindView(R.id.goodsButton)
    RadioButton goodsButton;
    @BindView(R.id.personButton)
    RadioButton personButton;
    @BindView(R.id.tablGroupButton)
    RadioGroup radioGroup;


    @Override
    protected int getFragmentContentId() {
        return R.id.mainFrameLayout;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public void initData() {
        changeImageSize();
        final List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new GoodsFragment());
        fragments.add(new PersonalFragment());

        addFragment(fragments.get(0));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.homeButton:
                        addFragment(fragments.get(0));
                        break;
                    case R.id.goodsButton:
                        addFragment(fragments.get(1));;
                        break;
                    case R.id.personButton:
                        addFragment(fragments.get(2));
                        break;
                }
            }
        });

    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    private void changeImageSize() {
        //定义底部标签图片大小
        Drawable drawableFirst = getResources().getDrawable(R.drawable.home_table_selector);
        drawableFirst.setBounds(0, 0, DensityUtils.dp2px(mContext,mContext.getResources().getDimension(R.dimen.dp20)), DensityUtils.dp2px(mContext,mContext.getResources().getDimension(R.dimen.dp20)));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        homeButton.setCompoundDrawables(null, drawableFirst, null, null);//只放上面

        Drawable drawableSearch = getResources().getDrawable(R.drawable.message_table_selector);
        drawableSearch.setBounds(0, 0, DensityUtils.dp2px(mContext,mContext.getResources().getDimension(R.dimen.dp20)), DensityUtils.dp2px(mContext,mContext.getResources().getDimension(R.dimen.dp20)));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        goodsButton.setCompoundDrawables(null, drawableSearch, null, null);//只放上面

        Drawable drawableMe = getResources().getDrawable(R.drawable.person_table_selector);
        drawableMe.setBounds(0, 0, DensityUtils.dp2px(mContext,mContext.getResources().getDimension(R.dimen.dp20)), DensityUtils.dp2px(mContext,mContext.getResources().getDimension(R.dimen.dp20)));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        personButton.setCompoundDrawables(null, drawableMe, null, null);//只放上面
    }
}
