package com.zjw.dr.entity;

import com.google.gson.annotations.SerializedName;
import com.zjw.dr.entity.base.Team;

import java.io.Serializable;

/**
 * Created by 祝锦伟 on 2017/12/20.
 */

public class UserLikeEntity implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("shot")
    private ShotEntity shotEntity;

    @SerializedName("team")
    private Team  team;

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

    public ShotEntity getShotEntity() {
        return shotEntity;
    }

    public void setShotEntity(ShotEntity shotEntity) {
        this.shotEntity = shotEntity;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "UserLikeEntity{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", shotEntity=" + shotEntity +
                ", team=" + team +
                '}';
    }

    public UserLikeEntity(int id, String createdAt, ShotEntity shotEntity, Team team) {
        this.id = id;
        this.createdAt = createdAt;
        this.shotEntity = shotEntity;
        this.team = team;
    }
}
