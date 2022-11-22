package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import com.comod.baselib.list.BaseListViewAdapter;

/* loaded from: classes.dex */
public class UploadTaskInfo extends BaseListViewAdapter.C0890c {
    public long addTime;
    public String coverUrl;
    public long duration;
    public boolean isOnUpload;
    public boolean isUploadError = false;
    public String localCoverUrl;
    public String localVideoUrl;
    public int progress;
    public String tags;
    public long taskId;
    public String title;
    public int videoPrice;
    public String videoUrl;

    public long getAddTime() {
        return this.addTime;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public long getDuration() {
        return this.duration;
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

    public int getVideoPrice() {
        return this.videoPrice;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public boolean isOnUpload() {
        return this.isOnUpload;
    }

    public boolean isUploadError() {
        return this.isUploadError;
    }

    public void setAddTime(long j) {
        this.addTime = j;
    }

    public void setCoverUrl(String str) {
        this.coverUrl = str;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setLocalCoverUrl(String str) {
        this.localCoverUrl = str;
    }

    public void setLocalVideoUrl(String str) {
        this.localVideoUrl = str;
    }

    public void setOnUpload(boolean z) {
        this.isOnUpload = z;
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

    public void setUploadError(boolean z) {
        this.isUploadError = z;
    }

    public void setVideoPrice(int i) {
        this.videoPrice = i;
    }

    public void setVideoUrl(String str) {
        this.videoUrl = str;
    }
}

