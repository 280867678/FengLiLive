package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class LogEventConfigEntity implements Parcelable {
    public static final Creator<LogEventConfigEntity> CREATOR = new Creator<LogEventConfigEntity>() { // from class: com.slzhibo.library.model.LogEventConfigEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LogEventConfigEntity createFromParcel(Parcel parcel) {
            return new LogEventConfigEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LogEventConfigEntity[] newArray(int i) {
            return new LogEventConfigEntity[i];
        }
    };
    private String events;
    private String id;
    private int quantityLimit;
    private int timeLimit;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(int i) {
        this.timeLimit = i;
    }

    public int getQuantityLimit() {
        return this.quantityLimit;
    }

    public void setQuantityLimit(int i) {
        this.quantityLimit = i;
    }

    public String getEvents() {
        return this.events;
    }

    public void setEvents(String str) {
        this.events = str;
    }

    public String toString() {
        return "LogEventConfigEntity{id='" + this.id + "', timeLimit=" + this.timeLimit + ", quantityLimit=" + this.quantityLimit + ", events='" + this.events + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeInt(this.timeLimit);
        parcel.writeInt(this.quantityLimit);
        parcel.writeString(this.events);
    }

    public LogEventConfigEntity() {
    }

    protected LogEventConfigEntity(Parcel parcel) {
        this.id = parcel.readString();
        this.timeLimit = parcel.readInt();
        this.quantityLimit = parcel.readInt();
        this.events = parcel.readString();
    }
}
