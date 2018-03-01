package com.zjw.dr.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 祝锦伟 on 2017/10/24.
 */

public class LikesEntity {

    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String createdAr;

    @SerializedName("user")
    private UserEntity userEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAr() {
        return createdAr;
    }

    public void setCreatedAr(String createdAr) {
        this.createdAr = createdAr;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "LikesEntity{" +
                "id=" + id +
                ", createdAr='" + createdAr + '\'' +
                ", userEntity=" + userEntity +
                '}';
    }
}
