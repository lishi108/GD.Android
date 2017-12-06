package com.guodong.business.bean;

import java.io.Serializable;

/**
 * Description:
 * Created by Administrator on 2017/11/24.
 */

public class GameInfo implements Serializable{
    private String name;
    private String iconUrl;
    private int iconId;
    private String gameInfo;

    public String getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
