package com.guodong.business.bean;


/**
 * Description:商品大类
 * Created by Administrator on 2017/12/7.
 */

public class Category {

    /**
     * id : 201711301448279742  //游戏id
     * categoryName : 账号
     * pinYin : pingguo
     * py : pg
     * isExistsSubCategory : false
     * canPublish : 0
     * nextAction : null
     * displayName : null
     */

    private String id;   //主键
    private String categoryName;   //类别名称
    private String pinYin;    //全拼
    private String py;  //简拼
    private boolean isExistsSubCategory; //是否有小分类
    private int canPublish;  //是否发表
    private String nextAction;  //下一步接口地址
    private String displayName;  //在前端显示的名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public boolean isExistsSubCategory() {
        return isExistsSubCategory;
    }

    public void setExistsSubCategory(boolean existsSubCategory) {
        isExistsSubCategory = existsSubCategory;
    }

    public int getCanPublish() {
        return canPublish;
    }

    public void setCanPublish(int canPublish) {
        this.canPublish = canPublish;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
