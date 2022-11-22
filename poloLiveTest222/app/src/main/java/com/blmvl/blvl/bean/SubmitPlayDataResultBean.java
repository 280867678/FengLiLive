package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import com.alibaba.fastjson.annotation.JSONField;

/* loaded from: classes.dex */
public class SubmitPlayDataResultBean {
    public int canWatchCount;
    public int left;
    public long timestamp;
    public int watched;

    public int getCanWatchCount() {
        return this.canWatchCount;
    }

    public int getLeft() {
        return this.left;
    }

    @JSONField(name = "todayTimestamp")
    public long getTimestamp() {
        return this.timestamp;
    }

    public int getWatched() {
        return this.watched;
    }

    public void setCanWatchCount(int i) {
        this.canWatchCount = i;
    }

    public void setLeft(int i) {
        this.left = i;
    }

    @JSONField(name = "todayTimestamp")
    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setWatched(int i) {
        this.watched = i;
    }
}

