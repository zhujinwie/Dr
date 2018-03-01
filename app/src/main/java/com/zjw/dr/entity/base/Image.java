package com.zjw.dr.entity.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 祝锦伟 on 2017/10/18.
 */

public class Image implements Serializable{

    @SerializedName("hidpi")
    private String hidpi;

    @SerializedName("normal")
    private String normal;

    @SerializedName("teaser")
    private String teaser;

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    @Override
    public String toString() {
        return "Image{" +
                "hidpi='" + hidpi + '\'' +
                ", normal='" + normal + '\'' +
                ", teaser='" + teaser + '\'' +
                '}';
    }
}
