package com.zjw.dr.entity;

import com.google.gson.annotations.SerializedName;
import com.zjw.dr.entity.base.Link;

import java.io.Serializable;

/**
 * Created by 祝锦伟 on 2017/10/16.
 */

public class UserEntity implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String userName;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("bio")
    private String bio;

    @SerializedName("location")
    private String location;

    @SerializedName("links")
    private Link links;

    @SerializedName("buckets_count")
    private int bucketsCount;

    @SerializedName("comments_received_count")
    private int coReCount;

    @SerializedName("followers_count")
    private int followerCount;

    @SerializedName("followings_count")
    private int followingsCount;

    @SerializedName("likes_count")
    private int likesCount;

    @SerializedName("likes_received_count")
    private int liReCount;

    @SerializedName("projects_count")
    private int proCount;

    @SerializedName("rebounds_received_count")
    private int rebReCount;

    @SerializedName("shots_count")
    private int shotsCount;

    @SerializedName("teams_count")
    private int teamsCount;

    @SerializedName("can_upload")
    private boolean canUpload;

    @SerializedName("type")
    private String type;

    @SerializedName("pro")
    private boolean pro;

    @SerializedName("buckets_url")
    private String bucketsUrl;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("likes_url")
    private String likesUrk;

    @SerializedName("shots_url")
    private String shotsUrl;

    @SerializedName("teams_url")
    private String teamsUrl;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    public int getBucketsCount() {
        return bucketsCount;
    }

    public void setBucketsCount(int bucketsCount) {
        this.bucketsCount = bucketsCount;
    }

    public int getCoReCount() {
        return coReCount;
    }

    public void setCoReCount(int coReCount) {
        this.coReCount = coReCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowingsCount() {
        return followingsCount;
    }

    public void setFollowingsCount(int followingsCount) {
        this.followingsCount = followingsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getLiReCount() {
        return liReCount;
    }

    public void setLiReCount(int liReCount) {
        this.liReCount = liReCount;
    }

    public int getProCount() {
        return proCount;
    }

    public void setProCount(int proCount) {
        this.proCount = proCount;
    }

    public int getRebReCount() {
        return rebReCount;
    }

    public void setRebReCount(int rebReCount) {
        this.rebReCount = rebReCount;
    }

    public int getShotsCount() {
        return shotsCount;
    }

    public void setShotsCount(int shotsCount) {
        this.shotsCount = shotsCount;
    }

    public int getTeamsCount() {
        return teamsCount;
    }

    public void setTeamsCount(int teamsCount) {
        this.teamsCount = teamsCount;
    }

    public boolean isCanUpload() {
        return canUpload;
    }

    public void setCanUpload(boolean canUpload) {
        this.canUpload = canUpload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPro() {
        return pro;
    }

    public void setPro(boolean pro) {
        this.pro = pro;
    }

    public String getBucketsUrl() {
        return bucketsUrl;
    }

    public void setBucketsUrl(String bucketsUrl) {
        this.bucketsUrl = bucketsUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getLikesUrk() {
        return likesUrk;
    }

    public void setLikesUrk(String likesUrk) {
        this.likesUrk = likesUrk;
    }

    public String getShotsUrl() {
        return shotsUrl;
    }

    public void setShotsUrl(String shotsUrl) {
        this.shotsUrl = shotsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
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
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", links=" + links +
                ", bucketsCount=" + bucketsCount +
                ", coReCount=" + coReCount +
                ", followerCount=" + followerCount +
                ", followingsCount=" + followingsCount +
                ", likesCount=" + likesCount +
                ", liReCount=" + liReCount +
                ", proCount=" + proCount +
                ", rebReCount=" + rebReCount +
                ", shotsCount=" + shotsCount +
                ", teamsCount=" + teamsCount +
                ", canUpload=" + canUpload +
                ", type='" + type + '\'' +
                ", pro=" + pro +
                ", bucketsUrl='" + bucketsUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", likesUrk='" + likesUrk + '\'' +
                ", shotsUrl='" + shotsUrl + '\'' +
                ", teamsUrl='" + teamsUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
