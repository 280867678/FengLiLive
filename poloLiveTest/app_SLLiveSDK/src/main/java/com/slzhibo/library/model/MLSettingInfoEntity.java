package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class MLSettingInfoEntity implements Parcelable {
    public static final Creator<MLSettingInfoEntity> CREATOR = new Creator<MLSettingInfoEntity>() { // from class: com.slzhibo.library.model.MLSettingInfoEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLSettingInfoEntity createFromParcel(Parcel parcel) {
            return new MLSettingInfoEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLSettingInfoEntity[] newArray(int i) {
            return new MLSettingInfoEntity[i];
        }
    };
    public String auditStatus;
    public String lastQuota;
    public String orderTakingStatus;
    public String rejectReason;
    public String signature;
    public String talentImageNumber;
    public String talentVideoNumber;
    public String videoCallAvatar;
    public String videoCallIsFrozen;
    public String videoCallUnitPrice;
    public String videoCallUnitPriceCeil;
    public String videoCallUnitPriceFloor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isOrderTakingStatus() {
        return TextUtils.equals(this.orderTakingStatus, "1");
    }

    public boolean isAuthSuccess() {
        return TextUtils.equals(this.auditStatus, String.valueOf(2));
    }

    public boolean isVideoCallIsFrozen() {
        return TextUtils.equals("1", this.videoCallIsFrozen);
    }

    public MLSettingInfoEntity() {
        this.lastQuota = "0";
        this.videoCallUnitPrice = "0";
        this.talentVideoNumber = "0";
        this.talentImageNumber = "0";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.orderTakingStatus);
        parcel.writeString(this.lastQuota);
        parcel.writeString(this.videoCallUnitPrice);
        parcel.writeString(this.videoCallAvatar);
        parcel.writeString(this.talentVideoNumber);
        parcel.writeString(this.talentImageNumber);
        parcel.writeString(this.signature);
        parcel.writeString(this.auditStatus);
        parcel.writeString(this.rejectReason);
        parcel.writeString(this.videoCallUnitPriceFloor);
        parcel.writeString(this.videoCallUnitPriceCeil);
    }

    protected MLSettingInfoEntity(Parcel parcel) {
        this.lastQuota = "0";
        this.videoCallUnitPrice = "0";
        this.talentVideoNumber = "0";
        this.talentImageNumber = "0";
        this.orderTakingStatus = parcel.readString();
        this.lastQuota = parcel.readString();
        this.videoCallUnitPrice = parcel.readString();
        this.videoCallAvatar = parcel.readString();
        this.talentVideoNumber = parcel.readString();
        this.talentImageNumber = parcel.readString();
        this.signature = parcel.readString();
        this.auditStatus = parcel.readString();
        this.rejectReason = parcel.readString();
        this.videoCallUnitPriceFloor = parcel.readString();
        this.videoCallUnitPriceCeil = parcel.readString();
    }
}
