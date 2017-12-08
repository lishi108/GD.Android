package com.guodong.business.view.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guodong.R;
import com.guodong.business.bean.GameInfo;
import com.guodong.utils.glide.GlideRoundTransform;
import com.guodong.widget.BetterRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:首页home的横向列表辅助
 * Created by Administrator on 2017/11/24.
 */

public class GameListViewHolder {
    private Context context;
    @BindView(R.id.horizontalRecyclerview)
    BetterRecyclerView gameRecyclerView;
    private CommonAdapter<GameInfo> adapter;
    private List<GameInfo> gameInfoList;
    private int radius;

    public GameListViewHolder(Context context, @NonNull View view) {
        this.context = context;
        ButterKnife.bind(this, view);
        init();
    }

    public void setData(@NonNull List<GameInfo> gameInfos) {
        if (gameInfoList.size() > 0) gameInfoList.clear();
        gameInfoList.addAll(gameInfos);
        adapter.notifyDataSetChanged();
    }

    private void init() {
        if (gameInfoList == null) gameInfoList = new ArrayList<>();
        radius = context.getResources().getDimensionPixelOffset(R.dimen.dp5);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gameRecyclerView.setLayoutManager(layoutManager);
        adapter = new CommonAdapter<GameInfo>(context, R.layout.item_game_horizontal, gameInfoList) {
            @Override
            protected void convert(ViewHolder holder, GameInfo gameInfo, int position) {
                holder.setText(R.id.gameNameText, gameInfoList.get(position).getGameName());
                ImageView imageView = holder.getView(R.id.gameImage);
                Glide.with(context)
                        .load(gameInfoList.get(position).getLogoImg())
                        .error(R.mipmap.ic_launcher)
                        .transform(new GlideRoundTransform(context, radius))
                        .into(imageView);
            }
        };
        gameRecyclerView.setAdapter(adapter);
    }
}
