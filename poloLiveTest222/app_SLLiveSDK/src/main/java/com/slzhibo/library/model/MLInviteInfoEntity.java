package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class MLInviteInfoEntity implements Parcelable {
    public static final Creator<MLInviteInfoEntity> CREATOR = new Creator<MLInviteInfoEntity>() { // from class: com.slzhibo.library.model.MLInviteInfoEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLInviteInfoEntity createFromParcel(Parcel parcel) {
            return new MLInviteInfoEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLInviteInfoEntity[] newArray(int i) {
            return new MLInviteInfoEntity[i];
        }
    };
    public String callSign;
    public String inviteeAvatar;
    public String inviteeId;
    public String inviteeNickname;
    public String inviterAvatar;
    public String inviterId;
    public String inviterNickname;
    public String isFollow;
    public String videoCallNo;
    public String videoCallUnitPrice;
    public String videoId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MLInviteInfoEntity() {
    }

    public boolean isFollowFlag() {
        return TextUtils.equals("1", this.isFollow);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.videoCallNo);
        parcel.writeString(this.inviterId);
        parcel.writeString(this.inviterAvatar);
        parcel.writeString(this.inviterNickname);
        parcel.writeString(this.inviteeId);
        parcel.writeString(this.inviteeAvatar);
        parcel.writeString(this.inviteeNickname);
        parcel.writeString(this.videoCallUnitPrice);
        parcel.writeString(this.callSign);
        parcel.writeString(this.videoId);
        parcel.writeString(this.isFollow);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MLInviteInfoEntity(Parcel parcel) {
        this.videoCallNo = parcel.readString();
        this.inviterId = parcel.readString();
        this.inviterAvatar = parcel.readString();
        this.inviterNickname = parcel.readString();
        this.inviteeId = parcel.readString();
        this.inviteeAvatar = parcel.readString();
        this.inviteeNickname = parcel.readString();
        this.videoCallUnitPrice = parcel.readString();
        this.callSign = parcel.readString();
        this.videoId = parcel.readString();
        this.isFollow = parcel.readString();
    }
}
