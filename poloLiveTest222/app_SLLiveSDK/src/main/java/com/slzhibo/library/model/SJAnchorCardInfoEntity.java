package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.AppUtils;

/* loaded from: classes8.dex */
public class SJAnchorCardInfoEntity implements Parcelable {
    public static final Creator<SJAnchorCardInfoEntity> CREATOR = new Creator<SJAnchorCardInfoEntity>() { // from class: com.slzhibo.library.model.SJAnchorCardInfoEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SJAnchorCardInfoEntity createFromParcel(Parcel parcel) {
            return new SJAnchorCardInfoEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SJAnchorCardInfoEntity[] newArray(int i) {
            return new SJAnchorCardInfoEntity[i];
        }
    };
    public String anchorName;
    public String avatar;
    public int contactTool;
    public String content;
    public String createTime;
    public String icon;
    public String isOpen;
    public int progress;
    public String remark;
    public String state;
    public int threshold;
    public String toolName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setAnchorName(String str) {
        this.anchorName = str;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public void setThreshold(int i) {
        this.threshold = i;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public void setContactTool(int i) {
        this.contactTool = i;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public boolean isOpenAnchorCard() {
        return TextUtils.equals("1", this.isOpen);
    }

    public boolean isCloseAnchorCard() {
        return !isOpenAnchorCard();
    }

    public boolean isUnlockStatus() {
        return TextUtils.equals("1", this.state);
    }

    public void setUnlockStatus(boolean z) {
        this.state = z ? "1" : "0";
    }

    public String getProgressTextTips() {
        return AppUtils.formatDisplayPrice(String.valueOf(this.progress), false) + "/" + AppUtils.formatDisplayPrice(String.valueOf(this.threshold), false);
    }

    public String getProgressMaxTextTips() {
        return AppUtils.formatDisplayPrice(String.valueOf(this.threshold), false) + "/" + AppUtils.formatDisplayPrice(String.valueOf(this.threshold), false);
    }

    public SJAnchorCardInfoEntity() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.anchorName);
        parcel.writeString(this.avatar);
        parcel.writeString(this.remark);
        parcel.writeInt(this.threshold);
        parcel.writeInt(this.progress);
        parcel.writeString(this.state);
        parcel.writeInt(this.contactTool);
        parcel.writeString(this.icon);
        parcel.writeString(this.isOpen);
        parcel.writeString(this.content);
        parcel.writeString(this.toolName);
        parcel.writeString(this.createTime);
    }

    protected SJAnchorCardInfoEntity(Parcel parcel) {
        this.anchorName = parcel.readString();
        this.avatar = parcel.readString();
        this.remark = parcel.readString();
        this.threshold = parcel.readInt();
        this.progress = parcel.readInt();
        this.state = parcel.readString();
        this.contactTool = parcel.readInt();
        this.icon = parcel.readString();
        this.isOpen = parcel.readString();
        this.content = parcel.readString();
        this.toolName = parcel.readString();
        this.createTime = parcel.readString();
    }
}
