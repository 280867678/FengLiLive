package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class GameEntity implements Parcelable {
    public static final Creator<GameEntity> CREATOR = new Creator<GameEntity>() { // from class: com.slzhibo.library.model.GameEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GameEntity createFromParcel(Parcel parcel) {
            return new GameEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GameEntity[] newArray(int i) {
            return new GameEntity[i];
        }
    };
    public String anchorIncomePrice;
    public String anchorName;
    public String clanAdminIncomePrice;
    public String costPrice;
    public String endTime;
    public String gameId;
    public String gameImgUrl;
    public String gameName;
    public String gameRatio;
    public String incomeAmount;
    public String userName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GameEntity() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.gameId);
        parcel.writeString(this.gameName);
        parcel.writeString(this.gameImgUrl);
        parcel.writeString(this.gameRatio);
        parcel.writeString(this.endTime);
        parcel.writeString(this.userName);
        parcel.writeString(this.anchorName);
        parcel.writeString(this.costPrice);
        parcel.writeString(this.anchorIncomePrice);
        parcel.writeString(this.incomeAmount);
        parcel.writeString(this.clanAdminIncomePrice);
    }

    protected GameEntity(Parcel parcel) {
        this.gameId = parcel.readString();
        this.gameName = parcel.readString();
        this.gameImgUrl = parcel.readString();
        this.gameRatio = parcel.readString();
        this.endTime = parcel.readString();
        this.userName = parcel.readString();
        this.anchorName = parcel.readString();
        this.costPrice = parcel.readString();
        this.anchorIncomePrice = parcel.readString();
        this.incomeAmount = parcel.readString();
        this.clanAdminIncomePrice = parcel.readString();
    }
}
