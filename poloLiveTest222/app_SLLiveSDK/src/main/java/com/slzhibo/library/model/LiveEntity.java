package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

//import com.seven.movie.common.utils.IOUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.StringUtils;
import com.slzhibo.library.utils.SysConfigInfoManager;
import com.slzhibo.library.utils.UserInfoManager;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class LiveEntity extends AnchorEntity implements MultiItemEntity, Parcelable {
    public static final Creator<LiveEntity> CREATOR = new Creator<LiveEntity>() { // from class: com.slzhibo.library.model.LiveEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveEntity createFromParcel(Parcel parcel) {
            return new LiveEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveEntity[] newArray(int i) {
            return new LiveEntity[i];
        }
    };
    public String anchorId;
    public List<BannerEntity> bannerList;
    public String chargeType;
    public String coverIdentityUrl;
    public String herald;
    public String id;
    public boolean isAd;
    public String isPrivateAnchor;
    public String isRelation;
    public int itemType;
    public List<LiveListItemEntity> liveCarousel;
    public String liveType;
    public String markerUrl;
    public String otherChannelCoverIdentityUrl;
    public String pendantUrl;
    public String popularity;
    public String privateAnchorPrice;
    public String publishTime;
    public String roomWatchWhite;
    public String ticketPrice;

    @Override // com.slzhibo.library.model.AnchorEntity, com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isOnLiving() {
        return TextUtils.equals("1", this.liveStatus) || TextUtils.equals("1", this.isLiving);
    }

    public boolean isLiving() {
        if (this.isAd) {
            return true;
        }
        return isOnLiving();
    }

    public String getDefPullStreamUrlStr() {
        List<String> pullStreamUrlList = getPullStreamUrlList();
        if (pullStreamUrlList.isEmpty()) {
            return "";
        }
        for (String str : pullStreamUrlList) {
            if (str.startsWith("rtmp://")) {
                return str;
            }
        }
        return pullStreamUrlList.get(0);
    }

    public List<String> getPullStreamUrlList() {
        String str = SysConfigInfoManager.getInstance().isPlayH265Video() ? this.pullStreamUrlH265 : this.pullStreamUrl;
        if (TextUtils.isEmpty(this.pullStreamUrlH265)) {
            str = this.pullStreamUrl;
        }
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        return Arrays.asList(str.split(","));
    }

    public String getLivePopularityStr() {
        return AppUtils.formatLivePopularityCount(NumberUtils.string2long(this.popularity));
    }

    public boolean isPayLiveNeedBuyTicket() {
        if (TextUtils.equals(this.chargeType, "1") || isTimePayLive()) {
            return !isRoomWatchWhite();
        }
        if (isPrivateAnchorByAppId()) {
            return !isRoomWatchWhite();
        }
        return false;
    }

    public String getPayLivePrice() {
        if (TextUtils.equals(this.chargeType, "1") || isTimePayLive()) {
            return this.ticketPrice;
        }
        return isPrivateAnchorByAppId() ? this.privateAnchorPrice : "0";
    }

    public boolean isPayLiveTicket() {
        if (TextUtils.equals(this.chargeType, "1") || isTimePayLive()) {
            return true;
        }
        return isPrivateAnchorByAppId();
    }

    public boolean isRelationBoolean() {
        return TextUtils.equals("1", this.isRelation);
    }

    public boolean isPrivateAnchorByAppId() {
        return TextUtils.equals(this.isPrivateAnchor, "1") && !TextUtils.equals(this.appId, UserInfoManager.getInstance().getAppId());
    }

    public boolean isRoomWatchWhite() {
        List<String> listByCommaSplit = StringUtils.getListByCommaSplit(this.roomWatchWhite);
        boolean z = false;
        if (listByCommaSplit == null) {
            return false;
        }
        for (String str : listByCommaSplit) {
            if (TextUtils.equals(this.appId, UserInfoManager.getInstance().getAppId()) && TextUtils.equals(str, UserInfoManager.getInstance().getAppOpenId())) {
                z = true;
            }
        }
        return z;
    }

    public boolean isTimePayLive() {
        return TextUtils.equals(this.chargeType, "2");
    }

    public boolean isCoverPreview() {
        int i;
        return !(isPayLiveNeedBuyTicket() || this.isAd || (i = this.itemType) == 2 || i == 3 || i == 5 || isVoiceRoomType() || this.itemType == 4);
    }

    public String getCoverIdentityUrl() {
        if (TextUtils.equals(this.appId, UserInfoManager.getInstance().getAppId()) || TextUtils.isEmpty(this.otherChannelCoverIdentityUrl)) {
            return this.coverIdentityUrl;
        }
        return this.otherChannelCoverIdentityUrl;
    }

    public boolean isShowPreNotice() {
        return !this.isAd && !TextUtils.isEmpty(this.herald) && !isOnLiving();
    }

    public String getPreNoticeStr() {
        if (this.isAd) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.herald);
        if (!TextUtils.isEmpty(this.publishTime)) {
            sb.append("\n");
            sb.append("(");
            sb.append(DateUtils.getShortTime(NumberUtils.string2long(this.publishTime) * 1000));
            sb.append(")");
        }
        return sb.toString();
    }

    public boolean isVoiceRoomType() {
        return TextUtils.equals(this.liveType, String.valueOf(2));
    }

    public boolean isToLiveRoomByItemType() {
        return this.itemType == 1;
    }

    @Override // com.slzhibo.library.utils.adapter.entity.MultiItemEntity
    public int getItemType() {
        return this.itemType;
    }

    public LiveEntity() {
        this.anchorId = "";
        this.herald = "";
        this.publishTime = "";
        this.popularity = "0";
        this.markerUrl = "";
        this.isAd = false;
        this.itemType = 1;
        this.bannerList = null;
        this.chargeType = "";
        this.isPrivateAnchor = "";
        this.privateAnchorPrice = "";
        this.ticketPrice = "";
        this.isRelation = "";
        this.roomWatchWhite = "";
        this.liveCarousel = null;
    }

    @Override // com.slzhibo.library.model.AnchorEntity
    public String toString() {
        return "LiveEntity{anchorId='" + this.userId + "', liveId='" + this.liveId + "', avatar='" + this.avatar + "', sex='" + this.sex + "', nickname='" + this.nickname + "', expGrade='" + this.expGrade + "'}";
    }

    @Override // com.slzhibo.library.model.AnchorEntity, com.slzhibo.library.model.BaseUserEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.id);
        parcel.writeString(this.anchorId);
        parcel.writeString(this.herald);
        parcel.writeString(this.publishTime);
        parcel.writeString(this.popularity);
        parcel.writeString(this.markerUrl);
        parcel.writeString(this.pendantUrl);
        parcel.writeString(this.coverIdentityUrl);
        parcel.writeString(this.otherChannelCoverIdentityUrl);
        parcel.writeByte(this.isAd ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.itemType);
        parcel.writeTypedList(this.bannerList);
        parcel.writeString(this.chargeType);
        parcel.writeString(this.isPrivateAnchor);
        parcel.writeString(this.privateAnchorPrice);
        parcel.writeString(this.ticketPrice);
        parcel.writeString(this.isRelation);
        parcel.writeString(this.roomWatchWhite);
        parcel.writeString(this.liveType);
    }

    protected LiveEntity(Parcel parcel) {
        super(parcel);
        this.anchorId = "";
        this.herald = "";
        this.publishTime = "";
        this.popularity = "0";
        this.markerUrl = "";
        boolean z = false;
        this.isAd = false;
        this.itemType = 1;
        this.bannerList = null;
        this.chargeType = "";
        this.isPrivateAnchor = "";
        this.privateAnchorPrice = "";
        this.ticketPrice = "";
        this.isRelation = "";
        this.roomWatchWhite = "";
        this.liveCarousel = null;
        this.id = parcel.readString();
        this.anchorId = parcel.readString();
        this.herald = parcel.readString();
        this.publishTime = parcel.readString();
        this.popularity = parcel.readString();
        this.markerUrl = parcel.readString();
        this.pendantUrl = parcel.readString();
        this.coverIdentityUrl = parcel.readString();
        this.otherChannelCoverIdentityUrl = parcel.readString();
        this.isAd = parcel.readByte() != 0 ? true : z;
        this.itemType = parcel.readInt();
        this.bannerList = parcel.createTypedArrayList(BannerEntity.CREATOR);
        this.chargeType = parcel.readString();
        this.isPrivateAnchor = parcel.readString();
        this.privateAnchorPrice = parcel.readString();
        this.ticketPrice = parcel.readString();
        this.isRelation = parcel.readString();
        this.roomWatchWhite = parcel.readString();
        this.liveType = parcel.readString();
    }
}
