package com.guodong.business.http;

import java.io.Serializable;

/**
 * Description:
 * Created by LSQ108 on 2017/10/27.
 */

public class BaseEntity <T> implements Serializable {
    private int resCode;
    private String msg;
    private boolean isSuccess;
    private T data;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
