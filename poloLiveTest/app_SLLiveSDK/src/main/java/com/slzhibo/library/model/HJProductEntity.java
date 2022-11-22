package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.StringUtils;

import java.util.Calendar;
import java.util.Date;

/* loaded from: classes8.dex */
public class HJProductEntity implements Parcelable {
    public static final Creator<HJProductEntity> CREATOR = new Creator<HJProductEntity>() { // from class: com.slzhibo.library.model.HJProductEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJProductEntity createFromParcel(Parcel parcel) {
            return new HJProductEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HJProductEntity[] newArray(int i) {
            return new HJProductEntity[i];
        }
    };
    public String anchorAvatar;
    public String anchorName;
    public String createTime;
    public String description;
    public String id;
    public String image;
    public String isFollowAnchor;
    public String isForbidden;
    public String isHot;
    public String isLiveSelected;
    public String isPromotion;
    public String isPurchased;
    public String isPutAway;
    public String isStartExplain;
    public String isSubscribe;
    public String isUpdate;
    public String itemCount;
    public String name;
    public String price;
    public String productId;
    public String productImage;
    public String productItemCount;
    public String productName;
    public String productPrice;
    public String promotionPrice;
    public String purchasedPrice;
    public String sequence;
    public String shelfId;
    public String shopLiveId;
    public String shopUserAvatar;
    public String shopUserName;
    public String tag;
    public String updateTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isSpeaking() {
        return TextUtils.equals(this.isHot, "1");
    }

    public boolean isForbiddenFlag() {
        return TextUtils.equals(this.isForbidden, "1");
    }

    public boolean isUpdateFlag() {
        return TextUtils.equals(this.isUpdate, "1");
    }

    public void setUpdateFlag(boolean z) {
        this.isUpdate = z ? "1" : "0";
    }

    public boolean isFollowAnchorFlag() {
        return TextUtils.equals(this.isFollowAnchor, "1");
    }

    public boolean isSubscribeFlag() {
        return TextUtils.equals(this.isSubscribe, "1");
    }

    public boolean isProductPurchasedFlag() {
        return TextUtils.equals(this.isPurchased, "1");
    }

    public void setProductPurchasedStatus(boolean z) {
        this.isPurchased = z ? "1" : "0";
    }

    public boolean isPromotionFlag() {
        return TextUtils.equals(this.isPromotion, "1");
    }

    public boolean isPutAwayFlag() {
        return TextUtils.equals(this.isLiveSelected, "1") || TextUtils.equals(this.isPutAway, "1");
    }

    public void setPutAwayFlag(boolean z) {
        String str = "1";
        this.isLiveSelected = z ? str : "0";
        if (!z) {
            str = "0";
        }
        this.isPutAway = str;
    }

    public boolean isStartExplainFlag() {
        return !TextUtils.equals(this.isHot, "1");
    }

    public void setStartExplainFlag(boolean z) {
        this.isHot = z ? "0" : "1";
    }

    public boolean isLastSelectedFlag() {
        return TextUtils.equals(this.isLiveSelected, "1");
    }

    public void setSelectedFlag(boolean z) {
        this.isLiveSelected = z ? "1" : "0";
    }

    public boolean isItemCount() {
        return NumberUtils.string2int(this.itemCount, 0) > 1;
    }

    public boolean isHotFlag() {
        return TextUtils.equals(this.isHot, "1");
    }

    public void setHotFlag(boolean z) {
        this.isHot = z ? "1" : "0";
    }

    public String getPurchasedPrice() {
        return isPromotionFlag() ? this.promotionPrice : this.price;
    }

    public String getOriginalPrice() {
        if (!TextUtils.isEmpty(this.price)) {
            return this.price;
        }
        if (!TextUtils.isEmpty(this.productPrice)) {
            return this.productPrice;
        }
        return this.price;
    }

    public String getProductName() {
        if (!TextUtils.isEmpty(this.name)) {
            return this.name;
        }
        if (!TextUtils.isEmpty(this.productName)) {
            return this.productName;
        }
        return this.name;
    }

    public String formatProductNameStr() {
        return StringUtils.formatStrLen(getProductName(), 15);
    }

    public String getSectionHeaderText() {
        return DateUtils.formatSecondToDateFormat(this.createTime, "yyyy-MM-dd");
    }

    public String getLatelyUpdateDateStr() {
        long string2long = NumberUtils.string2long(this.updateTime) * 1000;
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        if (DateUtils.isSameYear(new Date(instance.getTimeInMillis()), new Date(string2long))) {
            return DateUtils.formatMillisecondToDateFormat(string2long, DateUtils.C_DATE_PATTON_DATE_CHINA_10);
        }
        return DateUtils.formatMillisecondToDateFormat(string2long, "yyyy-MM-dd");
    }

    public String toString() {
        return "HJProductEntity{productId='" + this.productId + "', productName='" + this.productName + "'}";
    }

    public HJProductEntity() {
        this.sequence = "";
        this.itemCount = "0";
        this.productItemCount = "0";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.shelfId);
        parcel.writeString(this.id);
        parcel.writeString(this.productId);
        parcel.writeString(this.name);
        parcel.writeString(this.productName);
        parcel.writeString(this.image);
        parcel.writeString(this.price);
        parcel.writeString(this.promotionPrice);
        parcel.writeString(this.isPromotion);
        parcel.writeString(this.updateTime);
        parcel.writeString(this.createTime);
        parcel.writeString(this.isPutAway);
        parcel.writeString(this.isStartExplain);
        parcel.writeString(this.description);
        parcel.writeString(this.itemCount);
        parcel.writeString(this.isLiveSelected);
        parcel.writeString(this.anchorName);
        parcel.writeString(this.anchorAvatar);
        parcel.writeString(this.isForbidden);
        parcel.writeString(this.isUpdate);
        parcel.writeString(this.isFollowAnchor);
        parcel.writeString(this.isSubscribe);
        parcel.writeString(this.isHot);
        parcel.writeString(this.isPurchased);
    }

    protected HJProductEntity(Parcel parcel) {
        this.sequence = "";
        this.itemCount = "0";
        this.productItemCount = "0";
        this.shelfId = parcel.readString();
        this.id = parcel.readString();
        this.productId = parcel.readString();
        this.name = parcel.readString();
        this.productName = parcel.readString();
        this.image = parcel.readString();
        this.price = parcel.readString();
        this.promotionPrice = parcel.readString();
        this.isPromotion = parcel.readString();
        this.updateTime = parcel.readString();
        this.createTime = parcel.readString();
        this.isPutAway = parcel.readString();
        this.isStartExplain = parcel.readString();
        this.description = parcel.readString();
        this.itemCount = parcel.readString();
        this.isLiveSelected = parcel.readString();
        this.anchorName = parcel.readString();
        this.anchorAvatar = parcel.readString();
        this.isForbidden = parcel.readString();
        this.isUpdate = parcel.readString();
        this.isFollowAnchor = parcel.readString();
        this.isSubscribe = parcel.readString();
        this.isHot = parcel.readString();
        this.isPurchased = parcel.readString();
    }
}
