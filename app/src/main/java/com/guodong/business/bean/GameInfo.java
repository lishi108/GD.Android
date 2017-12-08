package com.guodong.business.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Description:
 * Created by Administrator on 2017/11/24.
 */
@Entity
public class GameInfo implements Parcelable{

    /**
     * id : 201711301441420699
     * type : 1
     * platformType : 3
     * gameName : 放置奇兵
     * short : AD
     * pinYin : fangzhiqibing
     * py : fzqb
     * state : 1
     * logoImg : http://czdcosfile-1251142424.file.myqcloud.com/origin/2017-48-23/D4C24C794C96447529F1A412B1B4605C.jpg
     * mainPicture : http://czdcosfile-1251142424.file.myqcloud.com/origin/2017-40-30/A3847BA59B5A3A9F53E487CB9A60CF24.jpg
     * synopsis : 放置奇兵111
     * sort : 1
     * isHot : true
     * canPublish : 0
     * nextAction : null
     * displayName : null
     */

    @Unique
    private String id;  //游戏id
    private int type;  //类型 网游/页游
    private int platformType;   //平台类型（0：无 1：安卓 2：IOS 3：所有 备注：或运算结果） ,
    private String gameName;    //游戏名称
    private String shortX;      //游戏简称
    private String pinYin;      //全拼
    private String py;          //简拼
    private int state;          //状态 （0：启用 1：禁用）
    private String logoImg;     //游戏Logo
    private String mainPicture; //主图
    private String synopsis;    //概要
    private int sort;           //排序
    private boolean isHot;      //是否热门
    private int canPublish;     //是否发表
    private String nextAction;  //下一步接口地址
    private String displayName; //在前端显示的名称
    private Long  updateTime;   //更新时间
    @Generated(hash = 1519111471)
    public GameInfo(String id, int type, int platformType, String gameName, String shortX, String pinYin, String py,
            int state, String logoImg, String mainPicture, String synopsis, int sort, boolean isHot, int canPublish,
            String nextAction, String displayName, Long updateTime) {
        this.id = id;
        this.type = type;
        this.platformType = platformType;
        this.gameName = gameName;
        this.shortX = shortX;
        this.pinYin = pinYin;
        this.py = py;
        this.state = state;
        this.logoImg = logoImg;
        this.mainPicture = mainPicture;
        this.synopsis = synopsis;
        this.sort = sort;
        this.isHot = isHot;
        this.canPublish = canPublish;
        this.nextAction = nextAction;
        this.displayName = displayName;
        this.updateTime = updateTime;
    }
    @Generated(hash = 1257372648)
    public GameInfo() {
    }

    protected GameInfo(Parcel in) {
        id = in.readString();
        type = in.readInt();
        platformType = in.readInt();
        gameName = in.readString();
        shortX = in.readString();
        pinYin = in.readString();
        py = in.readString();
        state = in.readInt();
        logoImg = in.readString();
        mainPicture = in.readString();
        synopsis = in.readString();
        sort = in.readInt();
        isHot = in.readByte() != 0;
        canPublish = in.readInt();
        nextAction = in.readString();
        displayName = in.readString();
    }

    public static final Creator<GameInfo> CREATOR = new Creator<GameInfo>() {
        @Override
        public GameInfo createFromParcel(Parcel in) {
            return new GameInfo(in);
        }

        @Override
        public GameInfo[] newArray(int size) {
            return new GameInfo[size];
        }
    };

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getPlatformType() {
        return this.platformType;
    }
    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }
    public String getGameName() {
        return this.gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public String getShortX() {
        return this.shortX;
    }
    public void setShortX(String shortX) {
        this.shortX = shortX;
    }
    public String getPinYin() {
        return this.pinYin;
    }
    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
    public String getPy() {
        return this.py;
    }
    public void setPy(String py) {
        this.py = py;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getLogoImg() {
        return this.logoImg;
    }
    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }
    public String getMainPicture() {
        return this.mainPicture;
    }
    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }
    public String getSynopsis() {
        return this.synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public boolean getIsHot() {
        return this.isHot;
    }
    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }
    public int getCanPublish() {
        return this.canPublish;
    }
    public void setCanPublish(int canPublish) {
        this.canPublish = canPublish;
    }
    public String getNextAction() {
        return this.nextAction;
    }
    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }
    public String getDisplayName() {
        return this.displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public Long getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(type);
        dest.writeInt(platformType);
        dest.writeString(gameName);
        dest.writeString(shortX);
        dest.writeString(pinYin);
        dest.writeString(py);
        dest.writeInt(state);
        dest.writeString(logoImg);
        dest.writeString(mainPicture);
        dest.writeString(synopsis);
        dest.writeInt(sort);
        dest.writeByte((byte) (isHot ? 1 : 0));
        dest.writeInt(canPublish);
        dest.writeString(nextAction);
        dest.writeString(displayName);
    }
}
