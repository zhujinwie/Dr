package com.zjw.dr.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 祝锦伟 on 2017/12/20.
 */

public class UserBucketEntity implements Serializable {

    public UserBucketEntity(int id, String name, String desc, int shotsCount, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.shotsCount = shotsCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String desc;

    @SerializedName("shots_count")
    private int shotsCount;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getShotsCount() {
        return shotsCount;
    }

    public void setShotsCount(int shotsCount) {
        this.shotsCount = shotsCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BucketEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", shotsCount=" + shotsCount +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
