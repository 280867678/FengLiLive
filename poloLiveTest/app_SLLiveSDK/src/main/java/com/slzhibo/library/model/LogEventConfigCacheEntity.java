package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/* loaded from: classes8.dex */
public class LogEventConfigCacheEntity implements Parcelable {
    public static final Creator<LogEventConfigCacheEntity> CREATOR = new Creator<LogEventConfigCacheEntity>() { // from class: com.slzhibo.library.model.LogEventConfigCacheEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LogEventConfigCacheEntity createFromParcel(Parcel parcel) {
            return new LogEventConfigCacheEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LogEventConfigCacheEntity[] newArray(int i) {
            return new LogEventConfigCacheEntity[i];
        }
    };
    private List<LogEventConfigEntity> eventList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<LogEventConfigEntity> getEventList() {
        return this.eventList;
    }

    public void setEventList(List<LogEventConfigEntity> list) {
        this.eventList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.eventList);
    }

    public LogEventConfigCacheEntity() {
    }

    protected LogEventConfigCacheEntity(Parcel parcel) {
        this.eventList = parcel.createTypedArrayList(LogEventConfigEntity.CREATOR);
    }
}
