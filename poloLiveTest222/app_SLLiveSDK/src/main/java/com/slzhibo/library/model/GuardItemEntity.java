package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class GuardItemEntity extends MyAccountEntity implements Parcelable {
    public static final Creator<GuardItemEntity> CREATOR = new Creator<GuardItemEntity>() { // from class: com.slzhibo.library.model.GuardItemEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GuardItemEntity createFromParcel(Parcel parcel) {
            return new GuardItemEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GuardItemEntity[] newArray(int i) {
            return new GuardItemEntity[i];
        }
    };
    public String anchorGuardCount;
    public String anchorId;
    public String anchorName;
    public String endTime;
    public String expGrade;
    public String guardRatio;
    public String guardType;
    public String id;
    public String isOpenWeekGuard;
    public String liveCount;
    public String name;
    public String totResult;
    public String type;
    public String userGuardExpireTime;
    public String userGuardType;

    @Override // com.slzhibo.library.model.MyAccountEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isOpenWeekGuardBoolean() {
        return TextUtils.equals(this.isOpenWeekGuard, "1");
    }

    @Override // com.slzhibo.library.model.MyAccountEntity
    public String getAccountBalance() {
        return super.getAccountBalance();
    }

    public GuardItemEntity() {
        this.totResult = "";
    }

    public String toString() {
        return "GuardItemEntity{anchorId='" + this.anchorId + "', guardRatio='" + this.guardRatio + "', expGrade='" + this.expGrade + "', userGuardType='" + this.userGuardType + "', userGuardExpireTime='" + this.userGuardExpireTime + "', liveCount='" + this.liveCount + "'}";
    }

    @Override // com.slzhibo.library.model.MyAccountEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.anchorId);
        parcel.writeString(this.anchorName);
        parcel.writeString(this.guardRatio);
        parcel.writeString(this.expGrade);
        parcel.writeString(this.price);
        parcel.writeString(this.totResult);
        parcel.writeString(this.endTime);
        parcel.writeString(this.type);
        parcel.writeString(this.userGuardType);
        parcel.writeString(this.guardType);
        parcel.writeString(this.userGuardExpireTime);
        parcel.writeString(this.isOpenWeekGuard);
        parcel.writeString(this.anchorGuardCount);
        parcel.writeString(this.liveCount);
    }

    protected GuardItemEntity(Parcel parcel) {
        this.totResult = "";
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.anchorId = parcel.readString();
        this.anchorName = parcel.readString();
        this.guardRatio = parcel.readString();
        this.expGrade = parcel.readString();
        this.price = parcel.readString();
        this.totResult = parcel.readString();
        this.endTime = parcel.readString();
        this.type = parcel.readString();
        this.userGuardType = parcel.readString();
        this.guardType = parcel.readString();
        this.userGuardExpireTime = parcel.readString();
        this.isOpenWeekGuard = parcel.readString();
        this.anchorGuardCount = parcel.readString();
        this.liveCount = parcel.readString();
    }
}
