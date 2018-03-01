package com.zjw.dr.entity.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 祝锦伟 on 2017/10/18.
 */

public class Team implements Serializable{

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
    private Link mLinks;

    @SerializedName("buckets_count")
    private int bucketsCount;

    @SerializedName("comments_received_count")
    private int coReCount;

    @SerializedName("followers_count")
    private int followersCount;

    @SerializedName("followings_count")
    private int followingsCount;

    @SerializedName("likes_count")
    private int likesCount;

    @SerializedName("likes_received_count")
    private int liReCount;

    @SerializedName("members_count")
    private int membersCount;

    @SerializedName("projects_count")
    private int projectsCount;

    @SerializedName("rebounds_received_count")
    private int rebReCount;

    @SerializedName("shots_count")
    private int shotsCount;

    @SerializedName("can_upload_shot")
    private boolean canUploadShot;

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
    private String likesUrl;

    @SerializedName("members_url")
    private String membersUrl;

    @SerializedName("shots_url")
    private String shotsUrl;

    @SerializedName("team_shots_url")
    private String teamShotsUrl;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String uploadedAt;

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

    public Link getmLinks() {
        return mLinks;
    }

    public void setmLinks(Link mLinks) {
        this.mLinks = mLinks;
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

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
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

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }

    public int getProjectsCount() {
        return projectsCount;
    }

    public void setProjectsCount(int projectsCount) {
        this.projectsCount = projectsCount;
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

    public boolean isCanUploadShot() {
        return canUploadShot;
    }

    public void setCanUploadShot(boolean canUploadShot) {
        this.canUploadShot = canUploadShot;
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

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public String getMembersUrl() {
        return membersUrl;
    }

    public void setMembersUrl(String membersUrl) {
        this.membersUrl = membersUrl;
    }

    public String getShotsUrl() {
        return shotsUrl;
    }

    public void setShotsUrl(String shotsUrl) {
        this.shotsUrl = shotsUrl;
    }

    public String getTeamShotsUrl() {
        return teamShotsUrl;
    }

    public void setTeamShotsUrl(String teamShotsUrl) {
        this.teamShotsUrl = teamShotsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", mLinks=" + mLinks +
                ", bucketsCount=" + bucketsCount +
                ", coReCount=" + coReCount +
                ", followersCount=" + followersCount +
                ", followingsCount=" + followingsCount +
                ", likesCount=" + likesCount +
                ", liReCount=" + liReCount +
                ", membersCount=" + membersCount +
                ", projectsCount=" + projectsCount +
                ", rebReCount=" + rebReCount +
                ", shotsCount=" + shotsCount +
                ", canUploadShot=" + canUploadShot +
                ", type='" + type + '\'' +
                ", pro=" + pro +
                ", bucketsUrl='" + bucketsUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", likesUrl='" + likesUrl + '\'' +
                ", membersUrl='" + membersUrl + '\'' +
                ", shotsUrl='" + shotsUrl + '\'' +
                ", teamShotsUrl='" + teamShotsUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", uploadedAt='" + uploadedAt + '\'' +
                '}';
    }
}
