package com.zjw.dr.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 祝锦伟 on 2017/10/26.
 */

public class FollowerEntity {

    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("follower")
    private UserEntity follower;

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

    public UserEntity getFollower() {
        return follower;
    }

    public void setFollower(UserEntity follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "FollowerEntity{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", follower=" + follower +
                '}';
    }
}
