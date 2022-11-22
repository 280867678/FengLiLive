package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.blankj.utilcode.util.RegexUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes8.dex */
public class ComponentsEntity implements Parcelable {
    public static final Creator<ComponentsEntity> CREATOR = new Creator<ComponentsEntity>() { // from class: com.slzhibo.library.model.ComponentsEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComponentsEntity createFromParcel(Parcel parcel) {
            return new ComponentsEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComponentsEntity[] newArray(int i) {
            return new ComponentsEntity[i];
        }
    };
    public int callType;
    public String gameId;
    public int height;
    public String id;
    public String imgUrl;
    public String isPartRecommend;
    public String isRecommend;
    public boolean isRedDot;
    public String name;
    public String ratio;
    public String targetUrl;
    public int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ComponentsEntity() {
        this.gameId = "";
        this.width = 0;
        this.height = 0;
        this.ratio = "0";
        this.isRedDot = false;
    }

    public boolean isCacheLotteryComponents() {
        return this.callType == 2 && TextUtils.equals(this.gameId, "1");
    }

    public boolean isRecommendComponents() {
        return TextUtils.equals("1", this.isRecommend);
    }

    public double getHeightProportion() {
        try {
            double doubleValue = new BigDecimal(Double.toString(this.width)).divide(new BigDecimal(Double.toString(this.height)), 6, RoundingMode.HALF_UP).doubleValue();
            if (doubleValue <= 0.0d) {
                return 1.0d;
            }
            return doubleValue;
        } catch (Exception unused) {
            return 1.0d;
        }
    }

    public boolean isCorrectLink() {
        return !TextUtils.isEmpty(this.targetUrl) && RegexUtils.isURL(this.targetUrl);
    }

    public boolean isPartRecommendFlag() {
        return TextUtils.equals(this.isPartRecommend, "1");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.imgUrl);
        parcel.writeInt(this.callType);
        parcel.writeString(this.targetUrl);
        parcel.writeString(this.gameId);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.isRecommend);
        parcel.writeString(this.ratio);
        parcel.writeString(this.isPartRecommend);
        parcel.writeByte(this.isRedDot ? (byte) 1 : (byte) 0);
    }

    protected ComponentsEntity(Parcel parcel) {
        this.gameId = "";
        boolean z = false;
        this.width = 0;
        this.height = 0;
        this.ratio = "0";
        this.isRedDot = false;
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.imgUrl = parcel.readString();
        this.callType = parcel.readInt();
        this.targetUrl = parcel.readString();
        this.gameId = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.isRecommend = parcel.readString();
        this.ratio = parcel.readString();
        this.isPartRecommend = parcel.readString();
        this.isRedDot = parcel.readByte() != 0 ? true : z;
    }
}
