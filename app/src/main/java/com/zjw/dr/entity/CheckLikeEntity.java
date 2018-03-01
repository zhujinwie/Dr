package com.zjw.dr.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 祝锦伟 on 2017/10/24.
 */

public class CheckLikeEntity {

    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CheckLikeEntity{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
