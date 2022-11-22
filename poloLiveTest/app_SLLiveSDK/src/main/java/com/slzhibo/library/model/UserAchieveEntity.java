package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.blankj.utilcode.util.TimeUtils;
//import com.slzhibo.library.utils.DateUtils;

import java.text.SimpleDateFormat;

/* loaded from: classes8.dex */
public class UserAchieveEntity implements Parcelable {
    public static final Creator<UserAchieveEntity> CREATOR = new Creator<UserAchieveEntity>() { // from class: com.slzhibo.library.model.UserAchieveEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserAchieveEntity createFromParcel(Parcel parcel) {
            return new UserAchieveEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserAchieveEntity[] newArray(int i) {
            return new UserAchieveEntity[i];
        }
    };
    public int days;
    public long endTime;
    public long getTime;
    public String name;
    public String remark;
    public int times;
    public String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCreateTime() {
        return TimeUtils.millis2String(this.getTime * 1000, new SimpleDateFormat("yyyy-MM-dd"));
    }

    public String getTimes() {
        if (this.times < 0) {
            this.times = 0;
        }
        int i = this.times;
        return i > 1 ? String.valueOf(i) : "";
    }

    public String getDays() {
        long currentTimeMillis = ((this.endTime * 1000) - System.currentTimeMillis()) / 86400000;
        if (currentTimeMillis >= 0) {
            currentTimeMillis++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentTimeMillis);
        return sb.toString();
    }

    public boolean isForeverValid() {
        return this.days == -1;
    }

    public UserAchieveEntity() {
        this.times = 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.name);
        parcel.writeString(this.remark);
        parcel.writeLong(this.getTime);
        parcel.writeLong(this.endTime);
        parcel.writeInt(this.days);
        parcel.writeInt(this.times);
    }

    protected UserAchieveEntity(Parcel parcel) {
        this.times = 0;
        this.url = parcel.readString();
        this.name = parcel.readString();
        this.remark = parcel.readString();
        this.getTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.days = parcel.readInt();
        this.times = parcel.readInt();
    }
}
