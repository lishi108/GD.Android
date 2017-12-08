package com.guodong.business.bean;

/**
 * Description:商品小类
 * Created by Administrator on 2017/12/7.
 */

public class SubCategory {

    /**
     * id : 201711271108040444
     * categoryName : 材料2
     * subCategoryName : c2
     * pinYin : c2
     * py : c2
     * canPublish : 0
     * nextAction : null
     * displayName : null
     */

    private Long index;
    private String id;             //主分类ID
    private String categoryName;   //主分类名称
    private String subCategoryName;// 商品分类名称
    private String pinYin;         //全拼
    private String py;             //简拼

    private int canPublish;        //是否发表
    private String nextAction;     //下一步接口地址
    private String displayName;    //在前端显示的名称

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

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

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
