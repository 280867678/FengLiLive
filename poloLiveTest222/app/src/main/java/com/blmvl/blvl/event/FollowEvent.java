package com.blmvl.blvl.event;

//package com.blmvl.blvl.event;

/* loaded from: classes.dex */
public class FollowEvent {
    public int mFrom;
    public int mIsAttention;
    public int mToUid;

    public FollowEvent(int i, int i2) {
        this.mToUid = i;
        this.mIsAttention = i2;
    }

    public int getFrom() {
        return this.mFrom;
    }

    public int getIsAttention() {
        return this.mIsAttention;
    }

    public int getToUid() {
        return this.mToUid;
    }

    public void setFrom(int i) {
        this.mFrom = i;
    }

    public void setIsAttention(int i) {
        this.mIsAttention = i;
    }

    public void setToUid(int i) {
        this.mToUid = i;
    }

    public FollowEvent(int i, int i2, int i3) {
        this.mFrom = i;
        this.mToUid = i2;
        this.mIsAttention = i3;
    }
}

