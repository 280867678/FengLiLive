package com.slzhibo.library.model;

import android.text.TextUtils;

import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.SysConfigInfoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class LiveItemEntity {
    public String anchorContribution;
    public String anchorGuardCount;
    public String backgroundUrl;
    public String banPostAllStatus;
    public String banPostStatus;
    public String banPostTimeLeft;
    public String bluetoothStatus;
    public String drawStatus;
    public String drawType;
    public String gameId;
    public String giftImg;
    public String giftMarkId;
    public String giftName;
    public String giftPrice;
    public String joinNum;
    public String lianmaiTargetAnchorAvatar;
    public String lianmaiTargetAnchorId;
    public String lianmaiTargetAnchorName;
    public String lianmaiTargetLiveId;
    public String liveCount;
    public String liveDrawRecordId;
    public String liveDrawTimeRemain;
    public String liveType;
    public String onlineUserCount;
    public String pkPunishTime;
    public String pkTimeRemain;
    public String postIntervalTimes;
    public String prizeName;
    public String pullStreamUrl;
    public String pullStreamUrlH265;
    public String shelfId;
    public String speakLevel;
    public String topic;
    public String vipCount;
    public String warnStatus;
    public String wsAddress;
    public String ticketPrice = "0";
    public String lianmaiStatus = "0";
    public String prizeNum = "0";
    public String liveDrawScope = "";

    public boolean isEnableHdLottery() {
        return TextUtils.equals("1", this.drawStatus) || TextUtils.equals("2", this.drawStatus) || TextUtils.equals("3", this.drawStatus);
    }

    public boolean isHdLotterySuccessToast(String str) {
        return isEnableHdLottery() && TextUtils.equals(this.giftMarkId, str);
    }

    public boolean isBanAll() {
        return TextUtils.equals(this.banPostAllStatus, "1");
    }

    public String getDefPullStreamUrlStr() {
        return getDefPullStreamUrlStr(getPullStreamUrlList());
    }

    public String getDefPullStreamUrlStr(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        for (String str : list) {
            if (str.startsWith("rtmp://")) {
                return str;
            }
        }
        return list.get(0);
    }

    public List<String> getPullStreamUrlList() {
        String str = SysConfigInfoManager.getInstance().isPlayH265Video() ? this.pullStreamUrlH265 : this.pullStreamUrl;
        if (TextUtils.isEmpty(this.pullStreamUrlH265)) {
            str = this.pullStreamUrl;
        }
        return splitPullStreamUrlList(str);
    }

    public List<String> getPullStreamUrlListByH264() {
        return splitPullStreamUrlList(this.pullStreamUrl);
    }

    public List<String> getPullStreamUrlListByH265() {
        if (TextUtils.isEmpty(this.pullStreamUrlH265)) {
            return getPullStreamUrlListByH264();
        }
        return splitPullStreamUrlList(this.pullStreamUrlH265);
    }

    private List<String> splitPullStreamUrlList(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        return Arrays.asList(str.split(","));
    }

    public boolean isBanStatus() {
        return TextUtils.equals(this.banPostStatus, "1");
    }

    public boolean isPKLiveRoom() {
        return TextUtils.equals(this.lianmaiStatus, "1") || TextUtils.equals(this.lianmaiStatus, "2");
    }

    public boolean isPKStart() {
        return TextUtils.equals(this.lianmaiStatus, "2");
    }

    public boolean isPKEnd() {
        return isPKStart() && NumberUtils.string2long(this.pkTimeRemain) <= 0;
    }

    public boolean isVoiceRoomType() {
        return TextUtils.equals(this.liveType, String.valueOf(2));
    }

    public boolean isBluetoothDeviceStatus() {
        return TextUtils.equals(this.bluetoothStatus, "1");
    }

    public void setBluetoothDeviceStatus(boolean z) {
        this.bluetoothStatus = z ? "1" : "0";
    }
}
