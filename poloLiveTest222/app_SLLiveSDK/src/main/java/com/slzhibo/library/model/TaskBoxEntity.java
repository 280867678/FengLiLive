package com.slzhibo.library.model;

/* loaded from: classes8.dex */
public class TaskBoxEntity {
    private String id;
    private String openTime;
    private int position;
    private String propImg;
    private String propName;
    private int propNumber;
    private int status;

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setPropNumber(int i) {
        this.propNumber = i;
    }

    public int getPropNumber() {
        return this.propNumber;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getPropImg() {
        return this.propImg;
    }

    public void setPropImg(String str) {
        this.propImg = str;
    }

    public String getPropName() {
        return this.propName;
    }

    public void setPropName(String str) {
        this.propName = str;
    }

    public String getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(String str) {
        this.openTime = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String toString() {
        return "TaskBoxEntity{id='" + this.id + ", propName='" + this.propName + "'}";
    }
}
