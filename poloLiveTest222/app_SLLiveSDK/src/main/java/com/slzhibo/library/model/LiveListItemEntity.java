package com.slzhibo.library.model;

import android.text.TextUtils;

import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.UserInfoManager;

/* loaded from: classes8.dex */
public class LiveListItemEntity {
    public String appId;
    public String avatar;
    public String chargeType;
    public String coverIdentityUrl;
    public String expGrade;
    public boolean isAd = false;
    public String isPrivateAnchor;
    public String liveCount;
    public String liveCoverUrl;
    public String liveId;
    public String liveStatus;
    public String liveType;
    public String markerUrl;
    public String nickname;
    public String onlineUserCount;
    public String openId;
    public String otherChannelCoverIdentityUrl;
    public String pendantUrl;
    public String popularity;
    public String privateAnchorPrice;
    public String pullStreamUrl;
    public String sex;
    public String tag;
    public String ticketPrice;
    public String topic;
    public String userId;

    public String getLivePopularityStr() {
        return AppUtils.formatLivePopularityCount(NumberUtils.string2long(this.popularity));
    }

    public String getCoverIdentityUrl() {
        if (TextUtils.equals(this.appId, UserInfoManager.getInstance().getAppId()) || TextUtils.isEmpty(this.otherChannelCoverIdentityUrl)) {
            return this.coverIdentityUrl;
        }
        return this.otherChannelCoverIdentityUrl;
    }

    public boolean isOnLiving() {
        return TextUtils.equals("1", this.liveStatus);
    }

    public boolean isPayLiveTicket() {
        if (TextUtils.equals(this.chargeType, "1") || isTimePayLive()) {
            return true;
        }
        return isPrivateAnchorByAppId();
    }

    public boolean isTimePayLive() {
        return TextUtils.equals(this.chargeType, "2");
    }

    public boolean isPrivateAnchorByAppId() {
        return TextUtils.equals(this.isPrivateAnchor, "1") && !TextUtils.equals(this.appId, UserInfoManager.getInstance().getAppId());
    }

    public String getPayLivePrice() {
        if (TextUtils.equals(this.chargeType, "1") || isTimePayLive()) {
            return this.ticketPrice;
        }
        return isPrivateAnchorByAppId() ? this.privateAnchorPrice : "0";
    }
}
