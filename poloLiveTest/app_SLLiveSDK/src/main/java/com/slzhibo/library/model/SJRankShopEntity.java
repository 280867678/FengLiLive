package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.AppUtils;

/* loaded from: classes8.dex */
public class SJRankShopEntity implements Parcelable {
    public static final Creator<SJRankShopEntity> CREATOR = new Creator<SJRankShopEntity>() { // from class: com.slzhibo.library.model.SJRankShopEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SJRankShopEntity createFromParcel(Parcel parcel) {
            return new SJRankShopEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SJRankShopEntity[] newArray(int i) {
            return new SJRankShopEntity[i];
        }
    };
    public String anchorAvatar;
    public String anchorName;
    public String liveId;
    public String liveStatus;
    public String productNum;
    public String soldScore;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SJRankShopEntity() {
        this.productNum = "0";
        this.soldScore = "0";
    }

    public boolean hasLive() {
        return TextUtils.equals("1", this.liveStatus);
    }

    public String getSoldScore() {
        return AppUtils.formatTenThousandUnit(this.soldScore);
    }

    protected SJRankShopEntity(Parcel parcel) {
        this.productNum = "0";
        this.soldScore = "0";
        this.anchorAvatar = parcel.readString();
        this.anchorName = parcel.readString();
        this.productNum = parcel.readString();
        this.soldScore = parcel.readString();
        this.liveStatus = parcel.readString();
        this.liveId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.anchorAvatar);
        parcel.writeString(this.anchorName);
        parcel.writeString(this.productNum);
        parcel.writeString(this.soldScore);
        parcel.writeString(this.liveStatus);
        parcel.writeString(this.liveId);
    }
}
