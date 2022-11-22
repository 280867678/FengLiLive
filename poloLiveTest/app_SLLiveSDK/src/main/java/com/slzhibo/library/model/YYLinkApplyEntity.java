package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class YYLinkApplyEntity implements Parcelable {
    public static final Creator<YYLinkApplyEntity> CREATOR = new Creator<YYLinkApplyEntity>() { // from class: com.slzhibo.library.model.YYLinkApplyEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public YYLinkApplyEntity createFromParcel(Parcel parcel) {
            return new YYLinkApplyEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public YYLinkApplyEntity[] newArray(int i) {
            return new YYLinkApplyEntity[i];
        }
    };
    public String expGrade;
    public String guardType;
    public String isQuiet;
    public boolean isSpeak;
    public String likeCount;
    public int nobilityType;
    public String role;
    public long rtcUid;
    public String seat;
    public String seatStatus;
    public String sex;
    public String status;
    public String surplusNum;
    public String userAppId;
    public String userAvatar;
    public String userId;
    public String userName;
    public String userOpenId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isLinking() {
        return TextUtils.equals(this.status, "1");
    }

    public boolean isLinkApplying() {
        return TextUtils.equals(this.status, "2");
    }

    public void setMuteStatus(boolean z) {
        this.isQuiet = z ? "1" : "0";
    }

    public boolean isMuteStatus() {
        return TextUtils.equals("1", this.isQuiet);
    }

    public void setLockSeatStatus(boolean z) {
        this.seatStatus = z ? "2" : "1";
    }

    public boolean isLockSeatStatus() {
        return TextUtils.equals("2", this.seatStatus);
    }

    public YYLinkApplyEntity() {
        this.expGrade = "1";
        this.guardType = "";
        this.nobilityType = 0;
        this.likeCount = "0";
        this.surplusNum = "0";
        this.isQuiet = "0";
        this.seatStatus = "1";
        this.isSpeak = false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.rtcUid);
        parcel.writeString(this.userId);
        parcel.writeString(this.userOpenId);
        parcel.writeString(this.userAppId);
        parcel.writeString(this.role);
        parcel.writeString(this.sex);
        parcel.writeString(this.userName);
        parcel.writeString(this.userAvatar);
        parcel.writeString(this.expGrade);
        parcel.writeString(this.guardType);
        parcel.writeInt(this.nobilityType);
        parcel.writeString(this.status);
        parcel.writeString(this.seat);
        parcel.writeString(this.likeCount);
        parcel.writeString(this.surplusNum);
        parcel.writeString(this.isQuiet);
        parcel.writeString(this.seatStatus);
        parcel.writeByte(this.isSpeak ? (byte) 1 : (byte) 0);
    }

    protected YYLinkApplyEntity(Parcel parcel) {
        this.expGrade = "1";
        this.guardType = "";
        boolean z = false;
        this.nobilityType = 0;
        this.likeCount = "0";
        this.surplusNum = "0";
        this.isQuiet = "0";
        this.seatStatus = "1";
        this.isSpeak = false;
        this.rtcUid = parcel.readLong();
        this.userId = parcel.readString();
        this.userOpenId = parcel.readString();
        this.userAppId = parcel.readString();
        this.role = parcel.readString();
        this.sex = parcel.readString();
        this.userName = parcel.readString();
        this.userAvatar = parcel.readString();
        this.expGrade = parcel.readString();
        this.guardType = parcel.readString();
        this.nobilityType = parcel.readInt();
        this.status = parcel.readString();
        this.seat = parcel.readString();
        this.likeCount = parcel.readString();
        this.surplusNum = parcel.readString();
        this.isQuiet = parcel.readString();
        this.seatStatus = parcel.readString();
        this.isSpeak = parcel.readByte() != 0 ? true : z;
    }

    public String toString() {
        return "YYLinkApplyEntity{userId='" + this.userId + "', userName='" + this.userName + "', status='" + this.status + "', isQuiet='" + this.isQuiet + "', seatStatus='" + this.seatStatus + "'}";
    }
}
