package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.NumberUtils;

/* loaded from: classes8.dex */
public class MLCallAnchorEntity extends MLInviteInfoEntity implements Parcelable {
    public static final Creator<MLCallAnchorEntity> CREATOR = new Creator<MLCallAnchorEntity>() { // from class: com.slzhibo.library.model.MLCallAnchorEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLCallAnchorEntity createFromParcel(Parcel parcel) {
            return new MLCallAnchorEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLCallAnchorEntity[] newArray(int i) {
            return new MLCallAnchorEntity[i];
        }
    };
    public String nobilityGoldFrozenStatus;
    public String nobilityPrice;
    public String price;
    public String score;

    @Override // com.slzhibo.library.model.MLInviteInfoEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isFrozen() {
        return TextUtils.equals(this.nobilityGoldFrozenStatus, "1");
    }

    public String getAccountBalance() {
        if (isFrozen()) {
            return this.price;
        }
        return String.valueOf(NumberUtils.string2long(this.price) + NumberUtils.string2long(this.nobilityPrice));
    }

    public MLCallAnchorEntity() {
        this.price = "0";
        this.nobilityPrice = "0";
        this.score = "0";
    }

    @Override // com.slzhibo.library.model.MLInviteInfoEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.nobilityGoldFrozenStatus);
        parcel.writeString(this.price);
        parcel.writeString(this.nobilityPrice);
        parcel.writeString(this.score);
    }

    protected MLCallAnchorEntity(Parcel parcel) {
        super(parcel);
        this.price = "0";
        this.nobilityPrice = "0";
        this.score = "0";
        this.nobilityGoldFrozenStatus = parcel.readString();
        this.price = parcel.readString();
        this.nobilityPrice = parcel.readString();
        this.score = parcel.readString();
    }
}
