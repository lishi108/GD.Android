package com.guodong.business.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:商品小类
 * Created by Administrator on 2017/12/7.
 */
@Entity
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
    @Id(autoincrement = true)
    private Long index;
    @Property(nameInDb = "categoryId")
    private String id;             //主分类ID
    private String categoryName;   //主分类名称
    private String subCategoryName;// 商品分类名称
    private String pinYin;         //全拼
    private String py;             //简拼
    @Transient
    private int canPublish;        //是否发表
    private String nextAction;     //下一步接口地址
    private String displayName;    //在前端显示的名称
    @Generated(hash = 815610377)
    public SubCategory(Long index, String id, String categoryName,
            String subCategoryName, String pinYin, String py, String nextAction,
            String displayName) {
        this.index = index;
        this.id = id;
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
        this.pinYin = pinYin;
        this.py = py;
        this.nextAction = nextAction;
        this.displayName = displayName;
    }
    @Generated(hash = 1008922175)
    public SubCategory() {
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
    public String getSubCategoryName() {
        return this.subCategoryName;
    }
    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
