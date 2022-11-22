package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.slzhibo.library.utils.adapter.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class WeekStarRankingEntity implements MultiItemEntity, Parcelable {
    public static final Creator<WeekStarRankingEntity> CREATOR = new Creator<WeekStarRankingEntity>() { // from class: com.slzhibo.library.model.WeekStarRankingEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeekStarRankingEntity createFromParcel(Parcel parcel) {
            return new WeekStarRankingEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeekStarRankingEntity[] newArray(int i) {
            return new WeekStarRankingEntity[i];
        }
    };
    public List<MenuEntity> anchorRewardList;
    public List<GiftDownloadItemEntity> giftLabelList;
    public int itemType;
    public List<WeekStarAnchorEntity> shineList;
    public List<MenuEntity> userRewardList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.slzhibo.library.utils.adapter.entity.MultiItemEntity
    public int getItemType() {
        return this.itemType;
    }

    public WeekStarRankingEntity() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.itemType);
        parcel.writeTypedList(this.shineList);
        parcel.writeList(this.anchorRewardList);
        parcel.writeList(this.userRewardList);
        parcel.writeList(this.giftLabelList);
    }

    protected WeekStarRankingEntity(Parcel parcel) {
        this.itemType = parcel.readInt();
        this.shineList = parcel.createTypedArrayList(WeekStarAnchorEntity.CREATOR);
        this.anchorRewardList = new ArrayList();
        parcel.readList(this.anchorRewardList, MenuEntity.class.getClassLoader());
        this.userRewardList = new ArrayList();
        parcel.readList(this.userRewardList, MenuEntity.class.getClassLoader());
        this.giftLabelList = new ArrayList();
        parcel.readList(this.giftLabelList, GiftDownloadItemEntity.class.getClassLoader());
    }
}
