package com.guodong.business.bean;

import java.io.Serializable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public class User implements Serializable {
    private String phone;  //手机号码
    private String nickName; //匿名

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
