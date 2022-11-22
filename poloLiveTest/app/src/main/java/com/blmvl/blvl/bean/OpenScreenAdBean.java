package com.blmvl.blvl.bean;

//package com.blmvl.blvl.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class OpenScreenAdBean implements Parcelable {
    public static final Parcelable.Creator<OpenScreenAdBean> CREATOR = new Parcelable.Creator<OpenScreenAdBean>() { // from class: com.blmvl.blvl.bean.OpenScreenAdBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpenScreenAdBean createFromParcel(Parcel parcel) {
            return new OpenScreenAdBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OpenScreenAdBean[] newArray(int i) {
            return new OpenScreenAdBean[i];
        }
    };

    /* renamed from: id */
    public String f1046id;
    public String img_url;
    public String jump_url;
    public String local_path;
    public String name;
    public String type;

    public OpenScreenAdBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.f1046id;
    }

    public String getImg_url() {
        return this.img_url;
    }

    public String getJump_url() {
        return this.jump_url;
    }

    public String getLocal_path() {
        return this.local_path;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setId(String str) {
        this.f1046id = str;
    }

    public void setImg_url(String str) {
        this.img_url = str;
    }

    public void setJump_url(String str) {
        this.jump_url = str;
    }

    public void setLocal_path(String str) {
        this.local_path = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.img_url);
        parcel.writeString(this.jump_url);
        parcel.writeString(this.type);
        parcel.writeString(this.local_path);
        parcel.writeString(this.f1046id);
        parcel.writeString(this.name);
    }

    public OpenScreenAdBean(Parcel parcel) {
        this.img_url = parcel.readString();
        this.jump_url = parcel.readString();
        this.type = parcel.readString();
        this.local_path = parcel.readString();
        this.f1046id = parcel.readString();
        this.name = parcel.readString();
    }
}

