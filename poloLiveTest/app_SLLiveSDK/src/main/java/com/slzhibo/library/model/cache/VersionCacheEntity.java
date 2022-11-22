package com.slzhibo.library.model.cache;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class VersionCacheEntity implements Parcelable {
    public static final Creator<VersionCacheEntity> CREATOR = new Creator<VersionCacheEntity>() { // from class: com.slzhibo.library.model.cache.VersionCacheEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VersionCacheEntity createFromParcel(Parcel parcel) {
            return new VersionCacheEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VersionCacheEntity[] newArray(int i) {
            return new VersionCacheEntity[i];
        }
    };
    private long createTime;
    private String v;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getVersion() {
        return this.v;
    }

    public void setVersion(String str) {
        this.v = str;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public String toString() {
        return "VersionCacheEntity{v='" + this.v + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.v);
        parcel.writeLong(this.createTime);
    }

    public VersionCacheEntity() {
    }

    protected VersionCacheEntity(Parcel parcel) {
        this.v = parcel.readString();
        this.createTime = parcel.readLong();
    }
}
