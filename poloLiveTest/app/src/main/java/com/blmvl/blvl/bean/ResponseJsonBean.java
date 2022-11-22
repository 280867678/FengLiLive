package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

/* loaded from: classes.dex */
public class ResponseJsonBean {
    public String data;
    public int errcode;
    public long timestamp;

    public String getData() {
        return this.data;
    }

    public int getErrcode() {
        return this.errcode;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setErrcode(int i) {
        this.errcode = i;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}

