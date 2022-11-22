package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/* loaded from: classes.dex */
@Entity
public class UploadVideoTaskBean {
    public String CoverUrl;
    public long addTime;
    public int coverHeight;
    public int coverWidth;
    public long duration;

    /* renamed from: id */
    public Long f1055id;
    public String localCoverUrl;
    public String localVideoUrl;
    public int progress;
    public String tags;
    public long taskId;
    public String title;
    public String userId;
    public int videoPrice;
    public String videoUrl;

    public UploadVideoTaskBean(Long l, String str, long j, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, int i3, int i4, long j2, long j3) {
        this.f1055id = l;
        this.userId = str;
        this.taskId = j;
        this.title = str2;
        this.tags = str3;
        this.localCoverUrl = str4;
        this.CoverUrl = str5;
        this.localVideoUrl = str6;
        this.videoUrl = str7;
        this.videoPrice = i;
        this.coverHeight = i2;
        this.coverWidth = i3;
        this.progress = i4;
        this.addTime = j2;
        this.duration = j3;
    }

    public long getAddTime() {
        return this.addTime;
    }

    public int getCoverHeight() {
        return this.coverHeight;
    }

    public String getCoverUrl() {
        return this.CoverUrl;
    }

    public int getCoverWidth() {
        return this.coverWidth;
    }

    public long getDuration() {
        return this.duration;
    }

    public Long getId() {
        return this.f1055id;
    }

    public String getLocalCoverUrl() {
        return this.localCoverUrl;
    }

    public String getLocalVideoUrl() {
        return this.localVideoUrl;
    }

    public int getProgress() {
        return this.progress;
    }

    public String getTags() {
        return this.tags;
    }

    public long getTaskId() {
        return this.taskId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUserId() {
        return this.userId;
    }

    public int getVideoPrice() {
        return this.videoPrice;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setAddTime(long j) {
        this.addTime = j;
    }

    public void setCoverHeight(int i) {
        this.coverHeight = i;
    }

    public void setCoverUrl(String str) {
        this.CoverUrl = str;
    }

    public void setCoverWidth(int i) {
        this.coverWidth = i;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setId(Long l) {
        this.f1055id = l;
    }

    public void setLocalCoverUrl(String str) {
        this.localCoverUrl = str;
    }

    public void setLocalVideoUrl(String str) {
        this.localVideoUrl = str;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public void setTaskId(long j) {
        this.taskId = j;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setVideoPrice(int i) {
        this.videoPrice = i;
    }

    public void setVideoUrl(String str) {
        this.videoUrl = str;
    }

    public Long getF1055id() {
        return this.f1055id;
    }

    public void setF1055id(Long f1055id) {
        this.f1055id = f1055id;
    }

    public UploadVideoTaskBean() {
    }

    @Generated(hash = 1576980095)
    public UploadVideoTaskBean(String CoverUrl, long addTime, int coverHeight, int coverWidth, long duration, Long f1055id, String localCoverUrl, String localVideoUrl, int progress, String tags,
            long taskId, String title, String userId, int videoPrice, String videoUrl) {
        this.CoverUrl = CoverUrl;
        this.addTime = addTime;
        this.coverHeight = coverHeight;
        this.coverWidth = coverWidth;
        this.duration = duration;
        this.f1055id = f1055id;
        this.localCoverUrl = localCoverUrl;
        this.localVideoUrl = localVideoUrl;
        this.progress = progress;
        this.tags = tags;
        this.taskId = taskId;
        this.title = title;
        this.userId = userId;
        this.videoPrice = videoPrice;
        this.videoUrl = videoUrl;
    }
}

