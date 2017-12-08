package com.guodong.business.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:商品大类
 * Created by Administrator on 2017/12/7.
 */
@Entity
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

    //不能用int
    @Id(autoincrement = true)
    private Long index;
    @Property(nameInDb = "gameId")
    private String id;   //主键
    private String categoryName;   //类别名称
    private String pinYin;    //全拼
    private String py;  //简拼
    private boolean isExistsSubCategory; //是否有小分类
    @Transient
    private int canPublish;  //是否发表
    private String nextAction;  //下一步接口地址
    private String displayName;  //在前端显示的名称
    @Generated(hash = 237458484)
    public Category(Long index, String id, String categoryName, String pinYin,
            String py, boolean isExistsSubCategory, String nextAction,
            String displayName) {
        this.index = index;
        this.id = id;
        this.categoryName = categoryName;
        this.pinYin = pinYin;
        this.py = py;
        this.isExistsSubCategory = isExistsSubCategory;
        this.nextAction = nextAction;
        this.displayName = displayName;
    }
    @Generated(hash = 1150634039)
    public Category() {
    }
    public Long getIndex() {
        return this.index;
    }
    public void setIndex(Long index) {
        this.index = index;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategoryName() {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    public boolean getIsExistsSubCategory() {
        return this.isExistsSubCategory;
    }
    public void setIsExistsSubCategory(boolean isExistsSubCategory) {
        this.isExistsSubCategory = isExistsSubCategory;
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
}
