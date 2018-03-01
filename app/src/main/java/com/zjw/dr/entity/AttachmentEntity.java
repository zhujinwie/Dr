package com.zjw.dr.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 祝锦伟 on 2017/10/18.
 */

public class AttachmentEntity {

    @SerializedName("id")
    private int id;

    @SerializedName("url")
    private String url;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("size")
    private int size;

    @SerializedName("content_type")
    private String contentType;

    @SerializedName("views_count")
    private int viewsCount;

    @SerializedName("created_at")
    private String createedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getCreateedAt() {
        return createedAt;
    }

    public void setCreateedAt(String createedAt) {
        this.createedAt = createedAt;
    }

    @Override
    public String toString() {
        return "AttachmentEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                ", viewsCount=" + viewsCount +
                ", createedAt='" + createedAt + '\'' +
                '}';
    }
}
