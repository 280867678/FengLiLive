package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class HJProductDetailEntity implements Parcelable {
    public static final Creator<HJProductDetailEntity> CREATOR = new Creator<HJProductDetailEntity>() { // from class: com.slzhibo.library.model.HJProductDetailEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJProductDetailEntity createFromParcel(Parcel parcel) {
            return new HJProductDetailEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJProductDetailEntity[] newArray(int i) {
            return new HJProductDetailEntity[i];
        }
    };
    public String createTime;
    public String duration;
    public String fileName;
    public String id;
    public String imgUrl;
    public String productId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.productId);
        parcel.writeString(this.fileName);
        parcel.writeString(this.duration);
        parcel.writeString(this.createTime);
        parcel.writeString(this.imgUrl);
    }

    public HJProductDetailEntity() {
    }

    protected HJProductDetailEntity(Parcel parcel) {
        this.id = parcel.readString();
        this.productId = parcel.readString();
        this.fileName = parcel.readString();
        this.duration = parcel.readString();
        this.createTime = parcel.readString();
        this.imgUrl = parcel.readString();
    }
}
