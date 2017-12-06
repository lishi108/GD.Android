package com.guodong.business.model.games;


import com.guodong.R;
import com.guodong.business.bean.GameInfo;
import com.guodong.business.contract.GamesContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class GamesModel implements GamesContract.IGamesModel {

    @Override
    public Observable<List<GameInfo>> getGames() {
        List<GameInfo> gameInfos = new ArrayList<>();
        GameInfo gameInfo1 = new GameInfo();
        gameInfo1.setIconId(R.drawable.dnf);
        gameInfo1.setName("地下城勇士");
        gameInfo1.setGameInfo("6.8折");
        gameInfos.add(gameInfo1);

        GameInfo gameInfo2 = new GameInfo();
        gameInfo1.setIconId(R.drawable.dnf);
        gameInfo1.setName("冒险岛2");
        gameInfo1.setGameInfo("热门");
        gameInfos.add(gameInfo2);


        GameInfo gameInfo3 = new GameInfo();
        gameInfo1.setIconId(R.drawable.dnf);
        gameInfo1.setName("地下城勇士");
        gameInfo1.setGameInfo("9折");
        gameInfos.add(gameInfo3);

        GameInfo gameInfo4 = new GameInfo();
        gameInfo1.setIconId(R.drawable.dnf);
        gameInfo1.setName("地下城勇士");
        gameInfo1.setGameInfo("8折");
        gameInfos.add(gameInfo4);

        GameInfo gameInfo5 = new GameInfo();
        gameInfo1.setIconId(R.drawable.dnf);
        gameInfo1.setName("地下城勇士");
        gameInfo1.setGameInfo("6.8折");
        gameInfos.add(gameInfo5);

        return Observable.just(gameInfos);
    }
}
