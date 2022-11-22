package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/* loaded from: classes8.dex */
public class LotteryTurntableDrawEntity implements Parcelable {
    public static final Creator<LotteryTurntableDrawEntity> CREATOR = new Creator<LotteryTurntableDrawEntity>() { // from class: com.slzhibo.library.model.LotteryTurntableDrawEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LotteryTurntableDrawEntity createFromParcel(Parcel parcel) {
            return new LotteryTurntableDrawEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LotteryTurntableDrawEntity[] newArray(int i) {
            return new LotteryTurntableDrawEntity[i];
        }
    };
    public List<LotteryPrizeEntity> awardList;
    public int code;
    public int luckTicket;
    public int luckValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LotteryTurntableDrawEntity() {
        this.luckTicket = 0;
        this.luckValue = 0;
        this.code = 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.luckTicket);
        parcel.writeInt(this.luckValue);
        parcel.writeInt(this.code);
        parcel.writeTypedList(this.awardList);
    }

    protected LotteryTurntableDrawEntity(Parcel parcel) {
        this.luckTicket = 0;
        this.luckValue = 0;
        this.code = 0;
        this.luckTicket = parcel.readInt();
        this.luckValue = parcel.readInt();
        this.code = parcel.readInt();
        this.awardList = parcel.createTypedArrayList(LotteryPrizeEntity.CREATOR);
    }
}
