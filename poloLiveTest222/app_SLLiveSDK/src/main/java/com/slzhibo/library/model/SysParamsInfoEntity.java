package com.slzhibo.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.slzhibo.library.utils.ConstantUtils;

import java.util.ArrayList;

/* loaded from: classes8.dex */
public class SysParamsInfoEntity implements Parcelable {
    public static final Creator<SysParamsInfoEntity> CREATOR = new Creator<SysParamsInfoEntity>() { // from class: com.slzhibo.library.model.SysParamsInfoEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SysParamsInfoEntity createFromParcel(Parcel parcel) {
            return new SysParamsInfoEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SysParamsInfoEntity[] newArray(int i) {
            return new SysParamsInfoEntity[i];
        }
    };
    public String anchorDataCenterUrl;
    public String enableAchievement;
    public String enableAnchorDataCenter;
    public String enableAnchorHomepageJump;
    public String enableBluetooth;
    public String enableContactCardImpart;
    public String enableFeeTag;
    public String enableFestival;
    public String enableGiftWall;
    public String enableGradeUpperLimit;
    public String enableGuard;
    public String enableInteract;
    public String enableIntimate;
    public String enableLog;
    public String enableNearbyModule;
    public String enableNobility;
    public String enableOffence;
    public String enablePaster;
    public String enablePrivateMessage;
    public String enableProductRank;
    public String enableScore;
    public String enableStartLiveAppAndroid;
    public String enableTease;
    public String enableTranslate;
    public String enableTranslationLevel;
    public String enableTurntable;
    public String enableTurntableUpdateTip;
    public String enableUserHomepageJump;
    public String enableVideoRoom;
    public String enableVideoStreamEncode;
    public String enableVisitorLive;
    public String enableWeekStar;
    public String entryNoticeLevelThreshold;
    public String giftTrumpetPlayPeriod;
    public String gradeSet10CharacterLimit;
    public String liveInitOnlineUserListSize;
    public String liveOnlineUserListLevelFilter;
    public ArrayList<String> liveRankConfig;
    public int nobilityTypeThresholdForHasPreventBanned;
    public String onlineCountSynInterval;
    public String resourceDownloadUrl;
    public String resourceVersion;
    public String socketHeartBeatInterval;
    public WatermarkConfigEntity watermarkConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isEnableGrade120() {
        return TextUtils.equals(this.enableGradeUpperLimit, "1");
    }

    public boolean isEnableReport() {
        return TextUtils.equals(this.enableOffence, "1");
    }

    public boolean isEnableSticker() {
        if (TextUtils.isEmpty(this.enablePaster)) {
            return true;
        }
        return TextUtils.equals(this.enablePaster, "1");
    }

    public boolean isEnableVideoStreamEncode() {
        if (TextUtils.isEmpty(this.enableVideoStreamEncode)) {
            return false;
        }
        return TextUtils.equals(this.enableVideoStreamEncode, "1");
    }

    public boolean isEnableNobility() {
        if (TextUtils.isEmpty(this.enableNobility)) {
            return true;
        }
        return TextUtils.equals(this.enableNobility, "1");
    }

    public boolean isEnableTurntable() {
        if (TextUtils.isEmpty(this.enableTurntable)) {
            return false;
        }
        return TextUtils.equals(this.enableTurntable, "1");
    }

    public boolean isEnableUserHomepage() {
        if (TextUtils.isEmpty(this.enableUserHomepageJump)) {
            return false;
        }
        return TextUtils.equals(this.enableUserHomepageJump, "1");
    }

    public boolean isEnableAnchorHomepage() {
        if (TextUtils.isEmpty(this.enableAnchorHomepageJump)) {
            return false;
        }
        return TextUtils.equals(this.enableAnchorHomepageJump, "1");
    }

    public boolean isEnableGuard() {
        if (TextUtils.isEmpty(this.enableGuard)) {
            return true;
        }
        return TextUtils.equals(this.enableGuard, "1");
    }

    public boolean isEnableVisitorLive() {
        if (TextUtils.isEmpty(this.enableVisitorLive)) {
            return false;
        }
        return TextUtils.equals(this.enableVisitorLive, "1");
    }

    public boolean isEnableScore() {
        if (TextUtils.isEmpty(this.enableScore)) {
            return true;
        }
        return TextUtils.equals(this.enableScore, "1");
    }

    public boolean isEnableTurntableUpdateTip() {
        if (TextUtils.isEmpty(this.enableTurntableUpdateTip)) {
            return true;
        }
        return TextUtils.equals(this.enableTurntableUpdateTip, "1");
    }

    public boolean isEnableWeekStar() {
        if (TextUtils.isEmpty(this.enableWeekStar)) {
            return true;
        }
        return TextUtils.equals(this.enableWeekStar, "1");
    }

    public boolean isEnableCommerce() {
        if (TextUtils.isEmpty(this.enableProductRank)) {
            return true;
        }
        return TextUtils.equals(this.enableProductRank, "1");
    }

    public boolean isEnableGiftWall() {
        if (TextUtils.isEmpty(this.enableGiftWall)) {
            return true;
        }
        return TextUtils.equals(this.enableGiftWall, "1");
    }

    public boolean isEnableAchievement() {
        if (TextUtils.isEmpty(this.enableAchievement)) {
            return true;
        }
        return TextUtils.equals(this.enableAchievement, "1");
    }

    public boolean isEnableComponents() {
        if (TextUtils.isEmpty(this.enableInteract)) {
            return true;
        }
        return TextUtils.equals(this.enableInteract, "1");
    }

    public boolean isEnableTranslation() {
        if (TextUtils.isEmpty(this.enableTranslate)) {
            return true;
        }
        return TextUtils.equals(this.enableTranslate, "1");
    }

    public boolean isEnablePrivateMsg() {
        if (TextUtils.isEmpty(this.enablePrivateMessage)) {
            return true;
        }
        return TextUtils.equals(this.enablePrivateMessage, "1");
    }

    public boolean isEnableFeeTag() {
        if (TextUtils.isEmpty(this.enableFeeTag)) {
            return false;
        }
        return TextUtils.equals(this.enableFeeTag, "1");
    }

    public boolean isEnableLiveHelperJump() {
        if (TextUtils.isEmpty(this.enableStartLiveAppAndroid)) {
            return false;
        }
        return TextUtils.equals(this.enableStartLiveAppAndroid, "1");
    }

    public boolean isEnableLogEventReport() {
        if (TextUtils.isEmpty(this.enableLog)) {
            return false;
        }
        return TextUtils.equals(this.enableLog, "1");
    }

    public boolean isEnableIntimate() {
        if (TextUtils.isEmpty(this.enableIntimate)) {
            return true;
        }
        return TextUtils.equals(this.enableIntimate, "1");
    }

    public boolean isEnableVideoRoom() {
        if (TextUtils.isEmpty(this.enableVideoRoom)) {
            return true;
        }
        return TextUtils.equals(this.enableVideoRoom, "1");
    }

    public boolean isEnableNewYearSkin() {
        if (TextUtils.isEmpty(this.enableFestival)) {
            return false;
        }
        return TextUtils.equals(this.enableFestival, "1");
    }

    public boolean isEnableBluetooth() {
        if (TextUtils.isEmpty(this.enableBluetooth)) {
            return false;
        }
        return TextUtils.equals(this.enableBluetooth, "1");
    }

    public boolean isEnableNearbyModule() {
        if (TextUtils.isEmpty(this.enableNearbyModule)) {
            return true;
        }
        return TextUtils.equals(this.enableNearbyModule, "1");
    }

    public boolean isEnableTease() {
        if (TextUtils.isEmpty(this.enableTease)) {
            return false;
        }
        return TextUtils.equals(this.enableTease, "1");
    }

    public boolean isEnableCardSetup() {
        if (TextUtils.isEmpty(this.enableContactCardImpart)) {
            return false;
        }
        return TextUtils.equals(this.enableContactCardImpart, "1");
    }

    public boolean getEnableAnchorDataCenter() {
        if (TextUtils.isEmpty(this.enableAnchorDataCenter)) {
            return false;
        }
        return TextUtils.equals(this.enableAnchorDataCenter, "1");
    }

    public SysParamsInfoEntity() {
        this.enableTranslationLevel = "1";
        this.enableVideoStreamEncode = "2";
        this.entryNoticeLevelThreshold = "";
        this.enableGradeUpperLimit = "";
        this.enableOffence = "";
        this.enableNobility = "";
        this.enableTurntable = "";
        this.enablePaster = "";
        this.gradeSet10CharacterLimit = ConstantUtils.DEFAULT_CHARACTER_LIMIT;
        this.nobilityTypeThresholdForHasPreventBanned = -1;
    }

    public String toString() {
        return "SysParamsInfoEntity{onlineCountSynInterval='" + this.onlineCountSynInterval + "', giftTrumpetPlayPeriod='" + this.giftTrumpetPlayPeriod + "', enableTranslationLevel='" + this.enableTranslationLevel + "', entryNoticeLevelThreshold='" + this.entryNoticeLevelThreshold + "', enableGradeUpperLimit='" + this.enableGradeUpperLimit + "', enableOffence='" + this.enableOffence + "', enableNobility='" + this.enableNobility + "', enableTurntable='" + this.enableTurntable + "', enablePaster='" + this.enablePaster + "', gradeSet10CharacterLimit=" + this.gradeSet10CharacterLimit + ", nobilityTypeThresholdForHasPreventBanned=" + this.nobilityTypeThresholdForHasPreventBanned + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.enableTranslationLevel);
        parcel.writeString(this.enableVideoStreamEncode);
        parcel.writeString(this.entryNoticeLevelThreshold);
        parcel.writeString(this.enableGradeUpperLimit);
        parcel.writeString(this.enableOffence);
        parcel.writeString(this.enableNobility);
        parcel.writeString(this.enableTurntable);
        parcel.writeString(this.enablePaster);
        parcel.writeString(this.onlineCountSynInterval);
        parcel.writeString(this.giftTrumpetPlayPeriod);
        parcel.writeString(this.gradeSet10CharacterLimit);
        parcel.writeInt(this.nobilityTypeThresholdForHasPreventBanned);
        parcel.writeString(this.socketHeartBeatInterval);
        parcel.writeStringList(this.liveRankConfig);
        parcel.writeString(this.liveOnlineUserListLevelFilter);
        parcel.writeString(this.liveInitOnlineUserListSize);
        parcel.writeString(this.enableUserHomepageJump);
        parcel.writeString(this.enableAnchorHomepageJump);
        parcel.writeString(this.enableGuard);
        parcel.writeString(this.enableVisitorLive);
        parcel.writeString(this.enableScore);
        parcel.writeString(this.enableTurntableUpdateTip);
        parcel.writeString(this.enableWeekStar);
        parcel.writeString(this.resourceVersion);
        parcel.writeString(this.resourceDownloadUrl);
        parcel.writeString(this.enableGiftWall);
        parcel.writeString(this.enableAchievement);
        parcel.writeString(this.enableInteract);
        parcel.writeString(this.enableTranslate);
        parcel.writeString(this.enablePrivateMessage);
        parcel.writeString(this.enableFeeTag);
        parcel.writeString(this.enableStartLiveAppAndroid);
        parcel.writeString(this.enableLog);
        parcel.writeString(this.enableIntimate);
        parcel.writeString(this.enableVideoRoom);
        parcel.writeParcelable(this.watermarkConfig, i);
    }

    protected SysParamsInfoEntity(Parcel parcel) {
        this.enableTranslationLevel = "1";
        this.enableVideoStreamEncode = "2";
        this.entryNoticeLevelThreshold = "";
        this.enableGradeUpperLimit = "";
        this.enableOffence = "";
        this.enableNobility = "";
        this.enableTurntable = "";
        this.enablePaster = "";
        this.gradeSet10CharacterLimit = ConstantUtils.DEFAULT_CHARACTER_LIMIT;
        this.nobilityTypeThresholdForHasPreventBanned = -1;
        this.enableTranslationLevel = parcel.readString();
        this.enableVideoStreamEncode = parcel.readString();
        this.entryNoticeLevelThreshold = parcel.readString();
        this.enableGradeUpperLimit = parcel.readString();
        this.enableOffence = parcel.readString();
        this.enableNobility = parcel.readString();
        this.enableTurntable = parcel.readString();
        this.enablePaster = parcel.readString();
        this.onlineCountSynInterval = parcel.readString();
        this.giftTrumpetPlayPeriod = parcel.readString();
        this.gradeSet10CharacterLimit = parcel.readString();
        this.nobilityTypeThresholdForHasPreventBanned = parcel.readInt();
        this.socketHeartBeatInterval = parcel.readString();
        this.liveRankConfig = parcel.createStringArrayList();
        this.liveOnlineUserListLevelFilter = parcel.readString();
        this.liveInitOnlineUserListSize = parcel.readString();
        this.enableUserHomepageJump = parcel.readString();
        this.enableAnchorHomepageJump = parcel.readString();
        this.enableGuard = parcel.readString();
        this.enableVisitorLive = parcel.readString();
        this.enableScore = parcel.readString();
        this.enableTurntableUpdateTip = parcel.readString();
        this.enableWeekStar = parcel.readString();
        this.resourceVersion = parcel.readString();
        this.resourceDownloadUrl = parcel.readString();
        this.enableGiftWall = parcel.readString();
        this.enableAchievement = parcel.readString();
        this.enableInteract = parcel.readString();
        this.enableTranslate = parcel.readString();
        this.enablePrivateMessage = parcel.readString();
        this.enableFeeTag = parcel.readString();
        this.enableStartLiveAppAndroid = parcel.readString();
        this.enableLog = parcel.readString();
        this.enableIntimate = parcel.readString();
        this.enableVideoRoom = parcel.readString();
        this.watermarkConfig = (WatermarkConfigEntity) parcel.readParcelable(WatermarkConfigEntity.class.getClassLoader());
    }
}
