package com.guodong.business.http;

import java.io.Serializable;

/**
 * Description:
 * Created by Administrator on 2017/11/16.
 */

public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public BaseEntity toBaseEntity(){
        BaseEntity baseResponse = new BaseEntity();
        baseResponse.setResCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
}
