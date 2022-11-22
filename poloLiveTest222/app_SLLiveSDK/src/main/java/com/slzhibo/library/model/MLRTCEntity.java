package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class MLRTCEntity implements Parcelable {
    public static final Creator<MLRTCEntity> CREATOR = new Creator<MLRTCEntity>() { // from class: com.slzhibo.library.model.MLRTCEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLRTCEntity createFromParcel(Parcel parcel) {
            return new MLRTCEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLRTCEntity[] newArray(int i) {
            return new MLRTCEntity[i];
        }
    };
    private String rtcAppId;
    private String rtcRoomId;
    private String rtcToken;
    private String rtcUid;
    private String userId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MLRTCEntity() {
    }

    protected MLRTCEntity(Parcel parcel) {
        this.rtcRoomId = parcel.readString();
        this.userId = parcel.readString();
        this.rtcUid = parcel.readString();
        this.rtcAppId = parcel.readString();
        this.rtcToken = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.rtcRoomId);
        parcel.writeString(this.userId);
        parcel.writeString(this.rtcUid);
        parcel.writeString(this.rtcAppId);
        parcel.writeString(this.rtcToken);
    }

    public String getRtcRoomId() {
        return this.rtcRoomId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRtcUid() {
        return this.rtcUid;
    }

    public String getRtcAppId() {
        return this.rtcAppId;
    }

    public String getRtcToken() {
        return this.rtcToken;
    }

    public void setRtcRoomId(String str) {
        this.rtcRoomId = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setRtcUid(String str) {
        this.rtcUid = str;
    }

    public void setRtcAppId(String str) {
        this.rtcAppId = str;
    }

    public void setRtcToken(String str) {
        this.rtcToken = str;
    }
}
