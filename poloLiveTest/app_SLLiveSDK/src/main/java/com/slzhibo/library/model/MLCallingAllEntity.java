package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class MLCallingAllEntity implements Parcelable {
    public static final Creator<MLCallingAllEntity> CREATOR = new Creator<MLCallingAllEntity>() { // from class: com.slzhibo.library.model.MLCallingAllEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLCallingAllEntity createFromParcel(Parcel parcel) {
            return new MLCallingAllEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MLCallingAllEntity[] newArray(int i) {
            return new MLCallingAllEntity[i];
        }
    };
    private MLCallAnchorEntity mlCallAnchorEntity;
    private MLRTCEntity mlrtcEntity;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MLCallingAllEntity(MLRTCEntity mLRTCEntity, MLCallAnchorEntity mLCallAnchorEntity) {
        this.mlrtcEntity = mLRTCEntity;
        this.mlCallAnchorEntity = mLCallAnchorEntity;
    }

    protected MLCallingAllEntity(Parcel parcel) {
        this.mlrtcEntity = (MLRTCEntity) parcel.readParcelable(MLRTCEntity.class.getClassLoader());
        this.mlCallAnchorEntity = (MLCallAnchorEntity) parcel.readParcelable(MLCallAnchorEntity.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mlrtcEntity, i);
        parcel.writeParcelable(this.mlCallAnchorEntity, i);
    }

    public MLRTCEntity getMlrtcEntity() {
        return this.mlrtcEntity;
    }

    public MLCallAnchorEntity getMlCallAnchorEntity() {
        return this.mlCallAnchorEntity;
    }
}
