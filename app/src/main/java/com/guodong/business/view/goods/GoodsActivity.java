package com.guodong.business.view.goods;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guodong.R;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.GoodsType;
import com.guodong.business.contract.GoodsContract;
import com.guodong.business.presenter.games.GoodsPresenter;
import com.guodong.mvp.BaseTitleActivity;
import com.guodong.widget.DropDownMenu;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * Description:
 * Created by Administrator on 2017/12/6.
 */

public class GoodsActivity extends BaseTitleActivity<GoodsPresenter> implements GoodsContract.IGoodsView {

    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private List<View> popupViews = new ArrayList<>(); //下拉选项View
    private View contentView;  //内容显示区域

    private List<GoodsType> mainTypes = new ArrayList<>();
    private SparseArray<List<GoodsType>> smallTypes = new SparseArray<>();
    private List<String> flowList = new ArrayList<>();  //筛选标签
    private List<String> sortData = new ArrayList<>();

    @Override
    protected int initTitle() {
        return R.string.NullText;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods;
    }

    @Override
    protected GoodsPresenter loadPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        initTestData();
        Bundle bundle = getIntent().getExtras();
        GameInfo gameInfo = (GameInfo) bundle.getSerializable("game");
        getmTitle().setText(gameInfo.getName());

        String[] menus = mContext.getResources().getStringArray(R.array.goods_menu);


//        类目View
        final TypeListView typeView = new TypeListView(this, "金币", mainTypes, smallTypes, 0, 0);


//        游戏区服View
        final GameAreaView areaView = new GameAreaView(this, "游戏区服", mainTypes, smallTypes, 0, 0);
//        筛选View
        View filterView = LayoutInflater.from(this).inflate(R.layout.item_filter, null, false);
        final TagFlowLayout flowLayout = (TagFlowLayout) filterView.findViewById(R.id.flowLayout);
        LinearLayout buttonLayout = (LinearLayout) filterView.findViewById(R.id.buttonLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(buttonLayout.getLayoutParams());
        params.setMargins(0, 0, 0, 40);
        buttonLayout.setLayoutParams(params);
        flowLayout.setAdapter(new TagAdapter<String>(flowList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(flowLayout.getContext()).inflate(R.layout.item_filter_textview,
                        flowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Iterator iterator = selectPosSet.iterator();
                while (iterator.hasNext()) {
                    Log.e("TAG", "select position:" + iterator.next());
                }
            }
        });
//        排序View
        View sortView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, null);
        SortViewHolder sortViewHolder = new SortViewHolder(mContext, sortView);


        contentView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, null);
        GoodContentHolder goodContentHolder = new GoodContentHolder(mContext,contentView);
        //init popupViews
        popupViews.add(typeView);
        popupViews.add(areaView);
        popupViews.add(filterView);
        popupViews.add(sortView);
        //init dropdownview
        dropDownMenu.setDropDownMenu(Arrays.asList(menus), popupViews, contentView);
    }

    @Override
    public void setGoods(List<GameInfo> goods) {

    }

    private void initTestData() {

        mainTypes.add(new GoodsType("金币"));
        mainTypes.add(new GoodsType("衣服"));
        mainTypes.add(new GoodsType("鞋子"));
        mainTypes.add(new GoodsType("武器"));
        for (int i = 0; i < mainTypes.size(); i++) {
            List<GoodsType> infos = new ArrayList<>();
            switch (i) {
                case 0:
                    infos.add(new GoodsType("银币"));
                    infos.add(new GoodsType("金币"));
                    break;
                case 1:
                    infos.add(new GoodsType("魔法衣"));
                    infos.add(new GoodsType("裤子"));
                    infos.add(new GoodsType("防弹衣"));
                    break;
                default:
                    infos.add(new GoodsType("魔法衣"));
                    infos.add(new GoodsType("魔法衣"));
                    infos.add(new GoodsType("魔法衣"));
                    infos.add(new GoodsType("魔法衣"));
                    break;
            }
            smallTypes.put(i,infos);
        }
    }
}
