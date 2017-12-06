package com.guodong.business.view.goods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guodong.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by Administrator on 2017/12/6.
 */

public class SortViewHolder {
    private Context context;
    private List<String> sortName;
    private List<String> sortInfo;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CommonAdapter<String> commonAdapter;
    public  SortViewHolder(Context context , @NonNull View view) {
        this.context = context;
        ButterKnife.bind(this,view);
        init();

    }

    private void init() {
        sortName = Arrays.asList(context.getResources().getStringArray(R.array.sort_menu));
        sortInfo = Arrays.asList(context.getResources().getStringArray(R.array.sort_info));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        commonAdapter = new CommonAdapter<String>(context,R.layout.item_sort,sortName) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.sortName,sortName.get(position));
                holder.setText(R.id.sortInfo,sortInfo.get(position));
            }
        };
        recyclerView.setAdapter(commonAdapter);
    }
}
