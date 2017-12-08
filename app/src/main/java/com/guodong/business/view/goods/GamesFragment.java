package com.guodong.business.view.goods;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guodong.R;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.contract.GamesContract;
import com.guodong.business.presenter.games.GamesPresenter;
import com.guodong.mvp.BaseFragment;
import com.guodong.utils.glide.GlideRoundTransform;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class GamesFragment extends BaseFragment<GamesPresenter> implements GamesContract.IGamesView {
    @BindView(R.id.gamesRecyclerView)
    RecyclerView gamesRecyclerView;
    private List<GameInfo> gameInfoList;
    private CommonAdapter<GameInfo> commonAdapter;
    @Override
    protected GamesPresenter loadPresenter() {
        return new GamesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_games;
    }

    @Override
    protected void initData() {
        if(gameInfoList == null) gameInfoList = new ArrayList<>();
        commonAdapter = new CommonAdapter<GameInfo>(mContext,R.layout.item_games,gameInfoList) {
            @Override
            protected void convert(ViewHolder holder, GameInfo gameInfo, int position) {
                holder.setText(R.id.gameName, gameInfoList.get(position).getGameName());
//                holder.setText(R.id.gameInfo,gameInfoList.get(position).getGameInfo());
                ImageView imageView = holder.getView(R.id.gameIcon);
                Glide.with(mContext)
                        .load(gameInfoList.get(position).getLogoImg())
                        .error(R.mipmap.ic_launcher)
                        .transform(new GlideRoundTransform(mContext, 5))
                        .into(imageView);

            }
        };
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        gamesRecyclerView.setAdapter(commonAdapter);
        mPresenter.getGames(mContext);

        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(gameInfoList.get(position)!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("game", gameInfoList.get(position));
                    mActivity.startActivity(GoodsActivity.class, bundle);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void setGames(@NonNull List<GameInfo> games) {
        if(games.size()>0){
            gameInfoList.clear();
            gameInfoList.addAll(games);
            commonAdapter.notifyDataSetChanged();
        }
    }
}
