package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.AppUtils;

/* loaded from: classes8.dex */
public class AnchorEntity extends BaseUserEntity implements Parcelable {
    public static final Creator<AnchorEntity> CREATOR = new Creator<AnchorEntity>() { // from class: com.slzhibo.library.model.AnchorEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AnchorEntity createFromParcel(Parcel parcel) {
            return new AnchorEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AnchorEntity[] newArray(int i) {
            return new AnchorEntity[i];
        }
    };
    public String anchor_id;
    public String contribution;
    public String count;
    public String expend;
    public String followAnchorCount;
    public String followerCount;
    public String fp;
    public String giftIncomePrice;
    public String income;
    public int isChecked;
    public int isFrozen;
    public String liveCount;
    public String liveCoverUrl;
    public String pkStatus;
    public String pullStreamUrl;
    public String pullStreamUrlH265;
    public String tag;
    public String topic;

    @Override // com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isAttention() {
        return AppUtils.isAttentionAnchor(this.userId);
    }

    public boolean isFrozenFlag() {
        return this.isFrozen == 1;
    }

    public String getAnchorIncomePrice() {
        return AppUtils.formatDisplayPrice(this.giftIncomePrice, true);
    }

    public boolean isInvitePK() {
        return TextUtils.equals("1", this.pkStatus);
    }

    public AnchorEntity() {
        this.followerCount = "0";
        this.followAnchorCount = "0";
        this.count = "0";
        this.income = "0";
        this.expend = "0";
        this.contribution = "";
        this.liveCoverUrl = "";
        this.liveCount = "";
        this.tag = "";
        this.topic = "";
    }

    public String toString() {
        return "AnchorEntity{, isChecked=" + this.isChecked + ", isFrozen=" + this.isFrozen + '}';
    }

    @Override // com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.followerCount);
        parcel.writeString(this.followAnchorCount);
        parcel.writeString(this.count);
        parcel.writeString(this.income);
        parcel.writeString(this.expend);
        parcel.writeString(this.contribution);
        parcel.writeString(this.anchor_id);
        parcel.writeString(this.liveCoverUrl);
        parcel.writeString(this.liveCount);
        parcel.writeString(this.tag);
        parcel.writeString(this.topic);
        parcel.writeInt(this.isChecked);
        parcel.writeInt(this.isFrozen);
        parcel.writeString(this.pullStreamUrl);
        parcel.writeString(this.pullStreamUrlH265);
        parcel.writeString(this.giftIncomePrice);
        parcel.writeString(this.pkStatus);
        parcel.writeString(this.fp);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnchorEntity(Parcel parcel) {
        super(parcel);
        this.followerCount = "0";
        this.followAnchorCount = "0";
        this.count = "0";
        this.income = "0";
        this.expend = "0";
        this.contribution = "";
        this.liveCoverUrl = "";
        this.liveCount = "";
        this.tag = "";
        this.topic = "";
        this.followerCount = parcel.readString();
        this.followAnchorCount = parcel.readString();
        this.count = parcel.readString();
        this.income = parcel.readString();
        this.expend = parcel.readString();
        this.contribution = parcel.readString();
        this.anchor_id = parcel.readString();
        this.liveCoverUrl = parcel.readString();
        this.liveCount = parcel.readString();
        this.tag = parcel.readString();
        this.topic = parcel.readString();
        this.isChecked = parcel.readInt();
        this.isFrozen = parcel.readInt();
        this.pullStreamUrl = parcel.readString();
        this.pullStreamUrlH265 = parcel.readString();
        this.giftIncomePrice = parcel.readString();
        this.pkStatus = parcel.readString();
        this.fp = parcel.readString();
    }
}
