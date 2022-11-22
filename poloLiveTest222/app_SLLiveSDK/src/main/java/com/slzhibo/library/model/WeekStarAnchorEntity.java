package com.slzhibo.library.model;

import android.os.Parcel;

/* loaded from: classes8.dex */
public class WeekStarAnchorEntity extends BaseUserEntity {
    public static final Creator<WeekStarAnchorEntity> CREATOR = new Creator<WeekStarAnchorEntity>() { // from class: com.slzhibo.library.model.WeekStarAnchorEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeekStarAnchorEntity createFromParcel(Parcel parcel) {
            return new WeekStarAnchorEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeekStarAnchorEntity[] newArray(int i) {
            return new WeekStarAnchorEntity[i];
        }
    };
    public String anchorId;
    public String anchorName;
    public String anchorStarGiftNum;
    public String giftName;
    public String giftNum;
    public String imgurl;
    public int rank;
    public String starGiftNum;
    public String userStarGiftNum;

    @Override // com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WeekStarAnchorEntity() {
        this.giftNum = "0";
        this.anchorStarGiftNum = "0";
        this.userStarGiftNum = "0";
        this.starGiftNum = "0";
        this.rank = -1;
    }

    @Override // com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.anchorId);
        parcel.writeString(this.anchorName);
        parcel.writeString(this.giftName);
        parcel.writeString(this.giftNum);
        parcel.writeString(this.imgurl);
        parcel.writeString(this.anchorStarGiftNum);
        parcel.writeString(this.userStarGiftNum);
        parcel.writeString(this.starGiftNum);
        parcel.writeInt(this.rank);
    }

    protected WeekStarAnchorEntity(Parcel parcel) {
        super(parcel);
        this.giftNum = "0";
        this.anchorStarGiftNum = "0";
        this.userStarGiftNum = "0";
        this.starGiftNum = "0";
        this.rank = -1;
        this.anchorId = parcel.readString();
        this.anchorName = parcel.readString();
        this.giftName = parcel.readString();
        this.giftNum = parcel.readString();
        this.imgurl = parcel.readString();
        this.anchorStarGiftNum = parcel.readString();
        this.userStarGiftNum = parcel.readString();
        this.starGiftNum = parcel.readString();
        this.rank = parcel.readInt();
    }
}
