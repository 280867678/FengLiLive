package com.slzhibo.library.model.cache;

import android.os.Parcel;
import android.os.Parcelable;

import com.slzhibo.library.model.ComponentsEntity;

import java.util.List;

/* loaded from: classes8.dex */
public class ComponentsCacheEntity implements Parcelable {
    public static final Creator<ComponentsCacheEntity> CREATOR = new Creator<ComponentsCacheEntity>() { // from class: com.slzhibo.library.model.cache.ComponentsCacheEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComponentsCacheEntity createFromParcel(Parcel parcel) {
            return new ComponentsCacheEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComponentsCacheEntity[] newArray(int i) {
            return new ComponentsCacheEntity[i];
        }
    };
    private List<ComponentsEntity> componentsList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<ComponentsEntity> getComponentsList() {
        return this.componentsList;
    }

    public void setComponentsList(List<ComponentsEntity> list) {
        this.componentsList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.componentsList);
    }

    public ComponentsCacheEntity() {
    }

    protected ComponentsCacheEntity(Parcel parcel) {
        this.componentsList = parcel.createTypedArrayList(ComponentsEntity.CREATOR);
    }
}
