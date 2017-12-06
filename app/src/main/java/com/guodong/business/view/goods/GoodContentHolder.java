package com.guodong.business.view.goods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guodong.R;
import com.guodong.business.bean.GoodsType;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by Administrator on 2017/12/6.
 */

public class GoodContentHolder {
    private Context context;
    private List<GoodsType> goodsList;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonAdapter<GoodsType> commonAdapter;
    public GoodContentHolder(Context context, @NonNull View view){
        this.context = context;
        ButterKnife.bind(this,view);
        init();
        initTestData();
    }

    private void init() {
        goodsList = new ArrayList<>();
        commonAdapter = new CommonAdapter<GoodsType>(context,R.layout.item_goods_content,goodsList) {
            @Override
            protected void convert(ViewHolder holder, GoodsType type, int position) {

            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(commonAdapter);
    }

    public void setGoodsList(List<GoodsType> data){
        if(data!=null&&data.size()>0){
            goodsList.clear();
            goodsList.addAll(data);
            commonAdapter.notifyDataSetChanged();
        }
    }

    private void initTestData(){
        for(int i=0;i<24;i++){
            goodsList.add(new GoodsType("11111"));
        }
    }

}
