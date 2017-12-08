package com.guodong.business.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description:
 * Created by Administrator on 2017/12/7.
 */

public class LoginInfo implements Parcelable{
    /**
     * userId : 201712071019494149
     * accessToken : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjIwMTcxMjA3MTAxOTQ5NDE0OSIsInJlbWVtYmVyTWUiOiJGYWxzZSIsIm5iZiI6MTUxMjYxMzIzMywiZXhwIjoxNTEyNjEzODMzLCJpYXQiOjE1MTI2MTMyMzMsImlzcyI6Iklzc3VlciIsImF1ZCI6IkF1ZGllbmNlIn0.IaDTLVLSmhFKGa6zOBgjCkFFY6CkkPMLMCZWIN-qRmb14SvnCq-3Ge4ULcqYAXIArxm5jSUCtktG7nfT9bHV2IMN_9zrgjmCWGW6WhXyp8-u4mrW_KmXM_b-1iBxNnCnz3BX_7Ula8rWHx1moDJdUt5gAwRWNirMcMxT1TMBad5udbzVLURlgNpctanIa_yVwVPTVSMXt8EoFNwmqGMhEFYhB-T2EZIwQsg6snzZkQsvNjbY1VQD8gIEOQTSqgb2LgarMaBT92oey1snD7Nm9GDR3YoG-b8pIfsbwotWFdd08_t3xFq6AYtrCI2frcu9a4QQLs-ZoWODE-NQgedMtw
     * expires : 1512613833015
     * grantType : 1
     * openId : be52d86c-4621-41ab-96af-304a97034612
     */

    private String userId;   //用户ID
    private String accessToken; //用户Token
    private long expires;  //token过期时间
    private int grantType;  //授权类型
    private String openId;  //

    protected LoginInfo(Parcel in) {
        userId = in.readString();
        accessToken = in.readString();
        expires = in.readLong();
        grantType = in.readInt();
        openId = in.readString();
    }

    public static final Creator<LoginInfo> CREATOR = new Creator<LoginInfo>() {
        @Override
        public LoginInfo createFromParcel(Parcel in) {
            return new LoginInfo(in);
        }

        @Override
        public LoginInfo[] newArray(int size) {
            return new LoginInfo[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public int getGrantType() {
        return grantType;
    }

    public void setGrantType(int grantType) {
        this.grantType = grantType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(accessToken);
        dest.writeLong(expires);
        dest.writeInt(grantType);
        dest.writeString(openId);
    }
}
