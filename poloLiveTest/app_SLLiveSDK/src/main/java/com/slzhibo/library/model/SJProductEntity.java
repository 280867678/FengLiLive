package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class SJProductEntity implements Parcelable {
    public static final Creator<SJProductEntity> CREATOR = new Creator<SJProductEntity>() { // from class: com.slzhibo.library.model.SJProductEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SJProductEntity createFromParcel(Parcel parcel) {
            return new SJProductEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SJProductEntity[] newArray(int i) {
            return new SJProductEntity[i];
        }
    };
    public String anchorAvatar;
    public String anchorName;
    public String liveId;
    public String liveStatus;
    public String productImage;
    public String productName;
    public String soldNum;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SJProductEntity(Parcel parcel) {
        this.soldNum = "0";
        this.soldNum = parcel.readString();
        this.anchorAvatar = parcel.readString();
        this.productName = parcel.readString();
        this.liveId = parcel.readString();
        this.liveStatus = parcel.readString();
        this.productImage = parcel.readString();
        this.anchorName = parcel.readString();
    }

    public SJProductEntity() {
        this.soldNum = "0";
    }

    public boolean hasLive() {
        return TextUtils.equals("1", this.liveStatus);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.soldNum);
        parcel.writeString(this.anchorAvatar);
        parcel.writeString(this.productName);
        parcel.writeString(this.liveId);
        parcel.writeString(this.liveStatus);
        parcel.writeString(this.productImage);
        parcel.writeString(this.anchorName);
    }
}
