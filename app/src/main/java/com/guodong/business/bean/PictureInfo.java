package com.guodong.business.bean;

/**
 * Description:
 * Created by Administrator on 2017/11/7.
 */

public class PictureInfo {
    private String url;
    private String cachePath;
    private int resourceId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
