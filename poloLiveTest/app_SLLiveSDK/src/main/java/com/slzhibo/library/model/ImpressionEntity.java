package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class ImpressionEntity implements Parcelable {
    public static final Creator<ImpressionEntity> CREATOR = new Creator<ImpressionEntity>() { // from class: com.slzhibo.library.model.ImpressionEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImpressionEntity createFromParcel(Parcel parcel) {
            return new ImpressionEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImpressionEntity[] newArray(int i) {
            return new ImpressionEntity[i];
        }
    };
    public String anchorAppId;
    public String anchorId;
    public String anchorName;
    public String anchorOpenId;
    public String impressionCount;
    public String impressionId;
    public String impressionName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ImpressionEntity() {
    }

    public ImpressionEntity(String str, String str2) {
        this.impressionId = str;
        this.impressionName = str2;
    }

    public ImpressionEntity(String str) {
        this.impressionName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.anchorId);
        parcel.writeString(this.anchorName);
        parcel.writeString(this.anchorOpenId);
        parcel.writeString(this.anchorAppId);
        parcel.writeString(this.impressionId);
        parcel.writeString(this.impressionName);
        parcel.writeString(this.impressionCount);
    }

    protected ImpressionEntity(Parcel parcel) {
        this.anchorId = parcel.readString();
        this.anchorName = parcel.readString();
        this.anchorOpenId = parcel.readString();
        this.anchorAppId = parcel.readString();
        this.impressionId = parcel.readString();
        this.impressionName = parcel.readString();
        this.impressionCount = parcel.readString();
    }
}
