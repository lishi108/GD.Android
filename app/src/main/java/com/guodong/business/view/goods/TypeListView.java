package com.guodong.business.view.goods;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.guodong.R;
import com.guodong.business.bean.Category;
import com.guodong.business.bean.SubCategory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by Administrator on 2017/12/6.
 */

public class TypeListView extends LinearLayout {

    /**
     * 父列表
     */
    @BindView(R.id.view_listView_main)
    RecyclerView view_listView_super;
    /**
     * 子列表
     */
    @BindView(R.id.view_listView_child)
    RecyclerView view_listView_child;
    /**
     * 父列表的数据
     */
    private List<Category> superItemDatas = new ArrayList<>();
    /**
     * 所有子列表数据
     */
    private SparseArray<List<SubCategory>> allChildItemDatas = new SparseArray<>();
    /**
     * 父列表对应的子列表的数据
     */
    private List<SubCategory> childItemDatas = new ArrayList<>();
    private CommonAdapter<Category> superListAdapter;
    private CommonAdapter<SubCategory> childListAdapter;
    /**
     * 选择监听
     */
    private OnSelectListener mOnSelectListener;
    /**
     * 父列表的默认选择位置
     */
    private int superPosition = 0;
    /**
     * 子列表的默认选择位置
     */
    private int childPosition = 0;
    /**
     * 显示的文本
     */
    private String showStr = "";

    /**
     * 构造一个双选择列表
     *
     * @param context
     * @param defPos            默认选中的一级列表位置
     * @param defPosChild       默认选中个二级列表位置
     * @param showStr           默认显示的文本
     * @param superItemDatas    一级列表数据
     * @param allChildItemDatas 二级列表数据
     */
    public TypeListView(Context context, String showStr, List<Category> superItemDatas, SparseArray<List<SubCategory>> allChildItemDatas,
                        int defPos, int defPosChild) {
        super(context);
        this.superItemDatas = superItemDatas;
        this.allChildItemDatas = allChildItemDatas;
        this.showStr = showStr;
        superPosition = defPos;
        childPosition = defPosChild;
        init(context);
    }

    /**
     * 控件初始化，构造时调用
     *
     * @param context
     */
    private void init(final Context context) {
        View view =  LayoutInflater.from(context).inflate(R.layout.view_filter_list_double, this, true);;
        ButterKnife.bind(this,view);

        view_listView_super.setLayoutManager(new LinearLayoutManager(context));
        view_listView_child.setLayoutManager(new GridLayoutManager(context, 2));

        superListAdapter = new CommonAdapter<Category>(context, R.layout.item_textview, superItemDatas) {

            @Override
            protected void convert(final ViewHolder holder, Category category, int position) {
                holder.setText(R.id.textView, category.getCategoryName());
                Log.e("TAG", "superPosition  :" + superPosition);
                if(superPosition==position){
                    holder.setBackgroundColor(R.id.textView, Color.WHITE);
                    holder.setTextColor(R.id.textView,context.getResources().getColor(R.color.colorPrimary));
                }else {
                    holder.setBackgroundColor(R.id.textView,context.getResources().getColor(R.color.colorf1f1f1));
                    holder.setTextColor(R.id.textView,context.getResources().getColor(R.color.color666666));
                }
            }
        };
        view_listView_super.setAdapter(superListAdapter);

        superListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Log.e("TAG", "superList position:" + position + "; adapter position:" + holder.getAdapterPosition());
                position = holder.getAdapterPosition();
                superPosition = position;
                childPosition = 0;
                superListAdapter.notifyDataSetChanged();
                showStr = superItemDatas.get(position).getCategoryName();
                childItemDatas.clear();
                if (allChildItemDatas.get(position) != null) {
                    childItemDatas.addAll(allChildItemDatas.get(position));
                }
                childListAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }

        });

        // 设置子列表数据
        if (superPosition < allChildItemDatas.size() && allChildItemDatas.get(superPosition) != null) {
            childItemDatas.addAll(allChildItemDatas.get(superPosition));
        }

        childListAdapter = new CommonAdapter<SubCategory>(context, R.layout.item_textview_border, childItemDatas) {

            @Override
            protected void convert(ViewHolder holder, SubCategory subCategory, int position) {
                holder.setText(R.id.borderTextView, subCategory.getCategoryName());
//                if(childPosition==position){
//                    holder.setBackgroundColor(R.id.borderTextView,context.getResources().getColor(R.color.colorf1f1f1));
//                }else {
//                    holder.setBackgroundColor(R.id.borderTextView,context.getResources().getColor(R.color.colorWhite));
//                }
            }
        };

        view_listView_child.setAdapter(childListAdapter);


        childListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Log.e("TAG", "childList position:" + position + "; adapter position:" + holder.getAdapterPosition());
                childPosition = position;
                childListAdapter.notifyDataSetChanged();
                showStr = childItemDatas.get(position).getCategoryName();

                Log.e("TAG","点击选中了："+superItemDatas.get(superPosition)+"-"+allChildItemDatas.get(superPosition).get(childPosition));
                if (mOnSelectListener != null) {
                    mOnSelectListener.getValue(showStr, superPosition, childPosition);// 点击监听回掉
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }

        });
        if (childPosition < childItemDatas.size()) {
            showStr = childItemDatas.get(childPosition).getCategoryName();
        }
        setDefSelected();
    }

    /**
     * 设置默认选择项
     */
    public void setDefSelected() {
    }

    /**
     * 获取当前显示的文本
     *
     * @return
     */
    public String getShowText() {
        return showStr;
    }

    /**
     * 设置点击（选择）监听 需要实现 OnSelectListener接口
     *
     * @param onSelectListener
     */
    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    /**
     * 设置点击（选择监听需要实现的接口）
     *
     * @author warmdoc_ANDROID_001
     */
    public interface OnSelectListener {
        /**
         * 监听实现此方法
         *
         * @param showText      选择到的数据
         * @param superPosition 选择的位置所在的父类列表的位置
         * @param position      选择的位置
         */
        void getValue(String showText, int superPosition, int position);
    }
    public void setChildItemDatas(@NonNull List<SubCategory> subCategories){
        if(childItemDatas.size()>0) childItemDatas.clear();
        childItemDatas.addAll(subCategories);
        childListAdapter.notifyDataSetChanged();
    }
}