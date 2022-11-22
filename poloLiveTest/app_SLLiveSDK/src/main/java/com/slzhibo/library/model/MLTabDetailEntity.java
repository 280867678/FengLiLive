package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class MLTabDetailEntity implements Parcelable {
    public static final Creator<MLTabDetailEntity> CREATOR = new Creator<MLTabDetailEntity>() { // from class: com.slzhibo.library.model.MLTabDetailEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLTabDetailEntity createFromParcel(Parcel parcel) {
            return new MLTabDetailEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLTabDetailEntity[] newArray(int i) {
            return new MLTabDetailEntity[i];
        }
    };
    public String createTime;
    public String description;
    public String descriptionTag;
    public String fileName;
    public String id;
    public String imgUrl;
    public String score;
    public String videoUrl;
    public String watchTimes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<String> getTagList() {
        if (TextUtils.isEmpty(this.descriptionTag)) {
            return new ArrayList();
        }
        return StringUtils.getListByCommaSplit(this.descriptionTag);
    }

    public MLTabDetailEntity() {
        this.watchTimes = "0";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.imgUrl);
        parcel.writeString(this.videoUrl);
        parcel.writeString(this.watchTimes);
        parcel.writeString(this.descriptionTag);
        parcel.writeString(this.description);
        parcel.writeString(this.score);
        parcel.writeString(this.fileName);
        parcel.writeString(this.createTime);
    }

    protected MLTabDetailEntity(Parcel parcel) {
        this.watchTimes = "0";
        this.id = parcel.readString();
        this.imgUrl = parcel.readString();
        this.videoUrl = parcel.readString();
        this.watchTimes = parcel.readString();
        this.descriptionTag = parcel.readString();
        this.description = parcel.readString();
        this.score = parcel.readString();
        this.fileName = parcel.readString();
        this.createTime = parcel.readString();
    }
}
