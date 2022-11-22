package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.text.DecimalFormat;

/* loaded from: classes8.dex */
public class MLAnchorEntity extends AnchorEntity implements Parcelable {
    public static final Creator<MLAnchorEntity> CREATOR = new Creator<MLAnchorEntity>() { // from class: com.slzhibo.library.model.MLAnchorEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLAnchorEntity createFromParcel(Parcel parcel) {
            return new MLAnchorEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLAnchorEntity[] newArray(int i) {
            return new MLAnchorEntity[i];
        }
    };
    public String anchorAvatar;
    public String anchorId;
    public String anchorName;
    public String anchorOpenId;
    public String followStatus;
    public String friendAvatar;
    public String isBlack;
    public String liveTime;
    public String status;
    public String totalScore = "0";
    public String videoCallAvatar;
    public String videoCallUnitPrice;

    @Override // com.slzhibo.library.model.AnchorEntity, com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isBusyStatus() {
        return TextUtils.equals(this.status, "2");
    }

    public boolean isKXStatus() {
        return TextUtils.equals(this.status, "1");
    }

    public boolean isFollowStatus() {
        return TextUtils.equals(this.followStatus, "1");
    }

    public void setFollowStatus(boolean z) {
        this.followStatus = z ? "1" : "0";
    }

    public boolean isBlacklistStatus() {
        return TextUtils.equals(this.isBlack, "1");
    }

    public void setBlacklistStatus(boolean z) {
        this.isBlack = z ? "1" : "0";
    }

    public String getTotalScore() {
        return new DecimalFormat("#.#").format(Float.valueOf(this.totalScore).floatValue());
    }

    public MLAnchorEntity() {
    }

    @Override // com.slzhibo.library.model.AnchorEntity, com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.anchorId);
        parcel.writeString(this.anchorName);
        parcel.writeString(this.anchorAvatar);
        parcel.writeString(this.videoCallUnitPrice);
        parcel.writeString(this.status);
    }

    public void readFromParcel(Parcel parcel) {
        this.anchorId = parcel.readString();
        this.anchorName = parcel.readString();
        this.anchorAvatar = parcel.readString();
        this.videoCallUnitPrice = parcel.readString();
        this.status = parcel.readString();
    }

    protected MLAnchorEntity(Parcel parcel) {
        super(parcel);
        this.anchorId = parcel.readString();
        this.anchorName = parcel.readString();
        this.anchorAvatar = parcel.readString();
        this.videoCallUnitPrice = parcel.readString();
        this.status = parcel.readString();
    }
}
