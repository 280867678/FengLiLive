package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class HJShopEntity implements Parcelable {
    public static final Creator<HJShopEntity> CREATOR = new Creator<HJShopEntity>() { // from class: com.slzhibo.library.model.HJShopEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJShopEntity createFromParcel(Parcel parcel) {
            return new HJShopEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJShopEntity[] newArray(int i) {
            return new HJShopEntity[i];
        }
    };
    public long followerCount;
    public String id;
    public String isDisplayShelf;
    public long limitFollowerCount;
    public long limitLiveDuration;
    public long liveDuration;
    public String shelfId;
    public String shopId;
    public String status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isFillFansFlag() {
        return this.followerCount >= this.limitFollowerCount;
    }

    public boolean isFillDurationFlag() {
        return this.liveDuration >= this.limitLiveDuration;
    }

    public boolean isApplyOpenFlag() {
        return isFillFansFlag() && isFillDurationFlag();
    }

    public boolean isDisplayShelfFlag() {
        return TextUtils.equals("1", this.isDisplayShelf);
    }

    public String formatLimitLiveDurationStr() {
        long j = this.limitLiveDuration;
        if (j > 0) {
            return String.valueOf(new Double(Math.ceil(new Double(String.valueOf(j)).doubleValue() / 60.0d)).longValue());
        }
        if (j == 0) {
        }
        return "0";
    }

    public HJShopEntity() {
        this.limitFollowerCount = 0L;
        this.limitLiveDuration = 0L;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.shopId);
        parcel.writeString(this.shelfId);
        parcel.writeLong(this.followerCount);
        parcel.writeLong(this.liveDuration);
        parcel.writeLong(this.limitFollowerCount);
        parcel.writeLong(this.limitLiveDuration);
    }

    protected HJShopEntity(Parcel parcel) {
        this.limitFollowerCount = 0L;
        this.limitLiveDuration = 0L;
        this.id = parcel.readString();
        this.shopId = parcel.readString();
        this.shelfId = parcel.readString();
        this.followerCount = parcel.readLong();
        this.liveDuration = parcel.readLong();
        this.limitFollowerCount = parcel.readLong();
        this.limitLiveDuration = parcel.readLong();
    }

    public String toString() {
        return "HJShopEntity{shopId='" + this.shopId + "', shelfId='" + this.shelfId + "'}";
    }
}
