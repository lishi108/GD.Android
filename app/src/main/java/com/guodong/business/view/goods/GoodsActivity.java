package com.guodong.business.view.goods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guodong.R;
import com.guodong.business.bean.Category;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.bean.SubCategory;
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

    private List<String> flowList = new ArrayList<>();  //筛选标签
    private List<String> sortData = new ArrayList<>();

    //
    private List<Category> categories = new ArrayList<>();  //商品大类
    private SparseArray<List<SubCategory>> sparseArray = new SparseArray<>();  //大类下的小类关联



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
        Bundle bundle = getIntent().getExtras();
        GameInfo gameInfo = (GameInfo) bundle.getParcelable("game");
        getmTitle().setText(gameInfo.getGameName());

        String[] menus = mContext.getResources().getStringArray(R.array.goods_menu);


//        类目View
        final TypeListView typeView = new TypeListView(this, "金币", categories, sparseArray, 0, 0);


//        游戏区服View
        final GameAreaView areaView = new GameAreaView(this, "游戏区服", categories, sparseArray, 0, 0);
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

    @Override
    public void setCategory(@NonNull List<Category> categoryList) {

    }

    @Override
    public void setSubCategory(@NonNull List<SubCategory> sbCategoryList) {

    }


}
