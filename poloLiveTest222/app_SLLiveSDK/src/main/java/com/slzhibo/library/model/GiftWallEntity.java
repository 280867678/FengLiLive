package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

//import org.apache.commons.cli.HelpFormatter;

import java.util.List;

/* loaded from: classes8.dex */
public class GiftWallEntity implements Parcelable {
    public static final Creator<GiftWallEntity> CREATOR = new Creator<GiftWallEntity>() { // from class: com.slzhibo.library.model.GiftWallEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GiftWallEntity createFromParcel(Parcel parcel) {
            return new GiftWallEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GiftWallEntity[] newArray(int i) {
            return new GiftWallEntity[i];
        }
    };
    public String avatar;
    public List<GiftWallGiftItemEntity> giftList;
    public String markIds;
    public String name;
    public String num;
    public int rank;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getUnitCommaStr() {
        return ",";
    }

    public int getGiftLightCount() {
        if (TextUtils.isEmpty(this.markIds)) {
            return 0;
        }
        if (!this.markIds.contains(getUnitCommaStr())) {
            return 1;
        }
        return this.markIds.split(getUnitCommaStr()).length;
    }

    public String getRankStr() {
        int i = this.rank;
        return i == 0 ? "--" : i > 50 ? "50+" : String.valueOf(i);
    }

    public GiftWallEntity() {
        this.rank = 0;
        this.num = "";
    }

    /* loaded from: classes8.dex */
    public static class GiftWallGiftItemEntity implements Parcelable {
        public static final Creator<GiftWallGiftItemEntity> CREATOR = new Creator<GiftWallGiftItemEntity>() { // from class: com.slzhibo.library.model.GiftWallEntity.GiftWallGiftItemEntity.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GiftWallGiftItemEntity createFromParcel(Parcel parcel) {
                return new GiftWallGiftItemEntity(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GiftWallGiftItemEntity[] newArray(int i) {
                return new GiftWallGiftItemEntity[i];
            }
        };
        public String imgurl;
        public boolean isLight;
        public String markId;
        public String name;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.name);
            parcel.writeString(this.imgurl);
            parcel.writeString(this.markId);
            parcel.writeByte(this.isLight ? (byte) 1 : (byte) 0);
        }

        public GiftWallGiftItemEntity() {
            this.isLight = false;
        }

        protected GiftWallGiftItemEntity(Parcel parcel) {
            boolean z = false;
            this.isLight = false;
            this.name = parcel.readString();
            this.imgurl = parcel.readString();
            this.markId = parcel.readString();
            this.isLight = parcel.readByte() != 0 ? true : z;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.avatar);
        parcel.writeString(this.markIds);
        parcel.writeInt(this.rank);
        parcel.writeString(this.num);
        parcel.writeTypedList(this.giftList);
    }

    protected GiftWallEntity(Parcel parcel) {
        this.rank = 0;
        this.num = "";
        this.name = parcel.readString();
        this.avatar = parcel.readString();
        this.markIds = parcel.readString();
        this.rank = parcel.readInt();
        this.num = parcel.readString();
        this.giftList = parcel.createTypedArrayList(GiftWallGiftItemEntity.CREATOR);
    }
}
