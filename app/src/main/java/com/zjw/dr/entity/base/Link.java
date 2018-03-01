package com.zjw.dr.entity.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 祝锦伟 on 2017/10/16.
 */

public class Link implements Serializable{

    @SerializedName("web")
    private String web;

    @SerializedName("twitter")
    private String twitter;

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "Links{" +
                "web='" + web + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
