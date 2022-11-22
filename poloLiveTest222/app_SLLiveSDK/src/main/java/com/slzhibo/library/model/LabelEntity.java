package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.ConstantUtils;

/* loaded from: classes8.dex */
public class LabelEntity implements Parcelable {
    public static final Creator<LabelEntity> CREATOR = new Creator<LabelEntity>() { // from class: com.slzhibo.library.model.LabelEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LabelEntity createFromParcel(Parcel parcel) {
            return new LabelEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LabelEntity[] newArray(int i) {
            return new LabelEntity[i];
        }
    };
    public String coordinate;
    public String id;
    public String img;
    public String isSecondLevelMenu;
    public boolean isSelected;
    public String keyword;
    public String labelType;
    public String name;
    public String style;
    public String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LabelEntity() {
        this.isSelected = false;
    }

    public LabelEntity(String str) {
        this.isSelected = false;
        this.name = str;
    }

    public LabelEntity(String str, boolean z) {
        this.isSelected = false;
        this.name = str;
        this.isSelected = z;
    }

    public LabelEntity(String str, String str2, boolean z) {
        this.isSelected = false;
        this.id = str;
        this.name = str2;
        this.isSelected = z;
    }

    public boolean isMoreTypeTag() {
        return TextUtils.equals(this.id, ConstantUtils.ML_TYPE_TAG_MORE_ID);
    }

    public boolean isSecondLevelMenuFlag() {
        return TextUtils.equals("1", this.isSecondLevelMenu);
    }

    public boolean isExternalCoordinateFlag() {
        return TextUtils.equals("2", this.coordinate);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.keyword);
        parcel.writeString(this.img);
        parcel.writeString(this.style);
        parcel.writeString(this.labelType);
        parcel.writeString(this.url);
        parcel.writeString(this.coordinate);
        parcel.writeString(this.isSecondLevelMenu);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected LabelEntity(Parcel parcel) {
        boolean z = false;
        this.isSelected = false;
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.keyword = parcel.readString();
        this.img = parcel.readString();
        this.style = parcel.readString();
        this.labelType = parcel.readString();
        this.url = parcel.readString();
        this.coordinate = parcel.readString();
        this.isSecondLevelMenu = parcel.readString();
        this.isSelected = parcel.readByte() != 0 ? true : z;
    }
}
