package com.slzhibo.library.model;

import android.os.Parcel;

/* loaded from: classes8.dex */
public class LotteryLuckReportEntity extends PropConfigEntity {
    public static final Creator<LotteryLuckReportEntity> CREATOR = new Creator<LotteryLuckReportEntity>() { // from class: com.slzhibo.library.model.LotteryLuckReportEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LotteryLuckReportEntity createFromParcel(Parcel parcel) {
            return new LotteryLuckReportEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LotteryLuckReportEntity[] newArray(int i) {
            return new LotteryLuckReportEntity[i];
        }
    };
    public String drawWay;
    public String userName;

    @Override // com.slzhibo.library.model.PropConfigEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LotteryLuckReportEntity() {
    }

    @Override // com.slzhibo.library.model.PropConfigEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.userName);
        parcel.writeString(this.drawWay);
    }

    protected LotteryLuckReportEntity(Parcel parcel) {
        super(parcel);
        this.userName = parcel.readString();
        this.drawWay = parcel.readString();
    }

    public String toString() {
        return "LotteryLuckReportEntity{userName='" + this.userName + "', drawWay='" + this.drawWay + "'}";
    }
}
