package com.slzhibo.library.http;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
//import com.facebook.common.util.UriUtil;
//import com.google.android.exoplayer2.text.ttml.TtmlNode;
//import com.seven.movie.commonservice.api.ApiConstant;
//import com.seven.movie.hotfix.pack.axmleditor.utils.NodeValue;
//import com.blmvl.API;
import com.google.android.exoplayer2.util.UriUtil;
import com.slzhibo.library.SLLiveSDK;
//import com.slzhibo.library.analytics.AopConstants;
import com.slzhibo.library.model.GuardItemEntity;
import com.slzhibo.library.model.HJProductEntity;
import com.slzhibo.library.model.RelationLastLiveEntity;
//import com.slzhibo.library.ui.view.dialog.LiveEndEvaluationDialog;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.LogConstants;
import com.slzhibo.library.utils.StringUtils;
import com.slzhibo.library.utils.UserInfoManager;
//import com.yalantis1.ucrop1.util.MimeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class RequestParams {
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    private final int PAGE_SIZE_COUNT = 20;
    private String userId = UserInfoManager.getInstance().getUserId();

    public Map<String, Object> getUserIdParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getUserIdByIdParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        return hashMap;
    }

    public Map<String, Object> getUserIdParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getAnchorIdParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getUserCardInfoParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        return hashMap;
    }

    public Map<String, Object> getDefaultParams() {
//        HashMap hashMap = new HashMap();
//        hashMap.put("token", API.Token);
//        hashMap.put("userId", API.UserId);

//        Log.e("RequestParams：", "token："+UserInfoManager.getInstance().getToken());
//        Log.e("RequestParams：", "userId："+UserInfoManager.getInstance().getUserId());
//        Log.e("RequestParams：", "ChannelToken："+UserInfoManager.getInstance().getChannelToken());
//        Log.e("RequestParams：", "AppOpenId："+UserInfoManager.getInstance().getAppOpenId());
//        .addHeader("token", Api.Token.replaceAll("\\n\\r\n\r",""))      //getToken token
//                .addHeader("userId", Api.openId)
        return new HashMap();
    }

    public Map<String, Object> getRankHiddenParams(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("hidden", z ? "1" : "0");
        return hashMap;
    }

    public Map<String, Object> getLiveId(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getRecommendParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("anonymous", str);
        hashMap.put("liveId", str2);
        return hashMap;
    }

    public Map<String, Object> getAppIdParams() {
        HashMap hashMap = new HashMap();
        hashMap.put(LogConstants.APP_ID, UserInfoManager.getInstance().getAppId());
        return hashMap;
    }

    public Map<String, Object> getAppIdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(LogConstants.APP_ID, str);
        return hashMap;
    }

    public Map<String, Object> getAnchorIdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        return hashMap;
    }

    public Map<String, Object> getSDKLoginParams() {
        String appOpenId = UserInfoManager.getInstance().getAppOpenId();

        String userNickname = UserInfoManager.getInstance().getUserNickname();
        String userSex = UserInfoManager.getInstance().getUserSex();
        String avatar = UserInfoManager.getInstance().getAvatar();
        String channelToken = UserInfoManager.getInstance().getChannelToken();
//        Log.e("RequestParams：", "userNickname："+UserInfoManager.getInstance().getUserNickname());
//        Log.e("RequestParams：", "userSex："+UserInfoManager.getInstance().getUserSex());
//        Log.e("RequestParams：", "avatar："+UserInfoManager.getInstance().getAvatar());
//        Log.e("RequestParams：", "channelToken："+UserInfoManager.getInstance().getChannelToken());
//        Log.e("RequestParams：", "Risk："+UserInfoManager.getInstance().getRisk());

        Map<String, Object> updatePersonalInfoParams = getUpdatePersonalInfoParams(appOpenId, avatar, userNickname, userSex, UserInfoManager.getInstance().getRisk());
        updatePersonalInfoParams.put("ntoken", channelToken);
        return updatePersonalInfoParams;
    }

    public Map<String, Object> getUpdateAvatarParams(String str) {
        return getUpdatePersonalInfoParams(getUpdateUserId(), str, UserInfoManager.getInstance().getUserNickname(), UserInfoManager.getInstance().getUserSex(), UserInfoManager.getInstance().getRisk());
    }

    public Map<String, Object> getUpdateNicknameParams(String str) {
        return getUpdatePersonalInfoParams(getUpdateUserId(), UserInfoManager.getInstance().getAvatar(), str, UserInfoManager.getInstance().getUserSex(), UserInfoManager.getInstance().getRisk());
    }

    public Map<String, Object> getUpdateSexParams(String str) {
        return getUpdatePersonalInfoParams(getUpdateUserId(), UserInfoManager.getInstance().getAvatar(), UserInfoManager.getInstance().getUserNickname(), str, UserInfoManager.getInstance().getRisk());
    }

    public Map<String, Object> getUpdateIsRiskParams(String str) {
        return getUpdatePersonalInfoParams(getUpdateUserId(), UserInfoManager.getInstance().getAvatar(), UserInfoManager.getInstance().getUserNickname(), UserInfoManager.getInstance().getUserSex(), str);
    }

    private Map<String, Object> getUpdatePersonalInfoParams(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        hashMap.put("name", str3);
        hashMap.put("sex", str4);
        hashMap.put("avatar", str2);
        hashMap.put("isLogin", String.valueOf(1));
        hashMap.put("isRisk", str5);
        return hashMap;
    }

    private String getUpdateUserId() {
        return this.userId;
    }

    public Map<String, Object> getTagPageListParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("tag", str);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getPageListByIdParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getPageListParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getPageListParams(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(i2));
        return hashMap;
    }

    public Map<String, Object> getPageListByLiveIdParams(String str, int i) {
        return getPageListByLiveIdParams(str, i, 20);
    }

    public Map<String, Object> getPageListByLiveIdParams(String str, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(i2));
        return hashMap;
    }

    public Map<String, Object> getPageListByLiveIdCountParams(String str, String str2, int i, int i2) {
        Map<String, Object> liveIdCountParams = getLiveIdCountParams(str, str2);
        liveIdCountParams.put(PAGE_NUMBER, Integer.valueOf(i));
        liveIdCountParams.put(PAGE_SIZE, Integer.valueOf(i2));
        return liveIdCountParams;
    }

    public Map<String, Object> getSearchAnchorListParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("key", str);
        hashMap.put("userId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getPageListByKeyParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("key", str);
        hashMap.put(PAGE_NUMBER, String.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getAdvChannelPageListParams(int i) {
        return getAdvChannelPageListParams(i, null);
    }

    public Map<String, Object> getAdvChannelPageListParams(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("virtualCityNumber", str);
        }
        if (!SLLiveSDK.getSingleton().isLiveAdvChannel()) {
            hashMap.put("branchChannelId", SLLiveSDK.getSingleton().ADV_CHANNEL_ID);
        }
        return hashMap;
    }

    public Map<String, Object> getAttentionAnchorParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("follower", this.userId);
        hashMap.put("userId", str);
        hashMap.put("followFlag", String.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getHomeTopParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("dateType", str);
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getHomeStrengthTopParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("dateType", str);
        return hashMap;
    }

    public Map<String, Object> getBannerListParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getAnchorAuthParams(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap hashMap = new HashMap();
        hashMap.put(LogConstants.REAL_NAME, str);
        hashMap.put("idCardNo", str2);
        hashMap.put("phone", str3);
        hashMap.put("verifyCode", str4);
        hashMap.put("idCardTop", str5);
        hashMap.put("idCardButtom", str6);
        hashMap.put("countryCode", str7);
        hashMap.put("userId", this.userId);
        hashMap.put("nickname", UserInfoManager.getInstance().getUserNickname());
        return hashMap;
    }

    public Map<String, Object> getStartLiveParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, RelationLastLiveEntity relationLastLiveEntity) {
        Map<String, Object> startLiveSubmitParams = getStartLiveSubmitParams(str4, str5, str6, str7, relationLastLiveEntity);
        startLiveSubmitParams.put("topic", str);
        startLiveSubmitParams.put("tag", str2);
        startLiveSubmitParams.put("enableLiveNotify", str3);
        LogUtils.json(LogConstants.START_LIVE_EVENT_NAME, startLiveSubmitParams);
        return startLiveSubmitParams;
    }

    public Map<String, Object> getLiveEndInfoParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        hashMap.put("liveId", str2);
        hashMap.put("liveCount", str3);
        return hashMap;
    }

    public Map<String, Object> getAnchorInfoParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getPreStartLiveInfoParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        return hashMap;
    }

    public Map<String, Object> getAnchorLiveInfoParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getUploadLiveCoverParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("liveCoverUrl", str);
        hashMap.put("recomCoverUrl", "");
        return hashMap;
    }

    public Map<String, Object> getUploadLiveCoverParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        hashMap.put("liveCoverUrl", str2);
        hashMap.put("recomCoverUrl", str3);
        return hashMap;
    }

    public Map<String, Object> getContributionListParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put("dateType", str);
        return hashMap;
    }

    public Map<String, Object> getContributionListParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str2);
        hashMap.put("dateType", str);
        return hashMap;
    }

    public Map<String, Object> getHouseSettingParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put("userIds", str);
        hashMap.put("action", String.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getBannedSettingParams(String str, String str2, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put("userId", str);
        hashMap.put("duration", str2);
        hashMap.put("action", String.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getSearchUsersParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("nickname", str);
        }
        return hashMap;
    }

    public Map<String, Object> getExitSDKParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        return hashMap;
    }

    public Map<String, Object> getSendPhoneCodeParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", str);
        hashMap.put("countryCode", str2);
        hashMap.put("methodId", "001");
        return hashMap;
    }

    public Map<String, Object> getUserOverParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("memberId", this.userId);
        hashMap.put("methodId", "006");
        return hashMap;
    }

    public Map<String, Object> getDeviceParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceId", DeviceUtils.getAndroidID());
        String str = "2";
        hashMap.put("deviceType", str);
        hashMap.put("deviceOS", DeviceUtils.getSDKVersionName());
        hashMap.put("deviceModel", DeviceUtils.getManufacturer());
        if (!NetworkUtils.isWifiConnected()) {
            str = "1";
        }
        hashMap.put("linkType", str);
        hashMap.put("num", String.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getLiveStatisticsParams(String str) {
        HashMap hashMap = new HashMap(getDeviceParams(3));
        hashMap.put("roomId", str);
        return hashMap;
    }

    public Map<String, Object> getLiveTimeStatisticsParams(String str) {
        HashMap hashMap = new HashMap(getDeviceParams(4));
        hashMap.put("roomId", str);
        return hashMap;
    }

    public Map<String, Object> getExitLiveTimeStatisticsParams(String str) {
        HashMap hashMap = new HashMap(getDeviceParams(4));
        hashMap.put("roomId", str);
        hashMap.put("key", SPUtils.getInstance().getString(ConstantUtils.STATISTICS_TIME_KEY_ROOM));
        return hashMap;
    }

    public Map<String, Object> getGiftStatisticsParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap(getDeviceParams(5));
        hashMap.put("markId", str);
        hashMap.put("giftTypeId", str2);
        hashMap.put("giftTypeName", str3);
        return hashMap;
    }

    public Map<String, Object> getBannerStatisticsParams(String str, String str2) {
        HashMap hashMap = new HashMap(getDeviceParams(6));
        hashMap.put("adId", str);
        hashMap.put("adName", str2);
        return hashMap;
    }

    public Map<String, Object> getCurrentOnlineUserList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getLivePopularity(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put(LogConstants.APP_ID, str2);
        return hashMap;
    }

    public Map<String, Object> getLiveInitInfoParams(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("enterWay", str2);
        hashMap.put("needBuyTicket", z ? "1" : "0");
        return hashMap;
    }

    public Map<String, Object> getLiveCountPageService(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put("liveCount", String.valueOf(i2));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

//    public Map<String, Object> getLivePreNoticeParams(String str) {
//        HashMap hashMap = new HashMap();
//        hashMap.put(UriUtil.LOCAL_CONTENT_SCHEME, str);
//        hashMap.put("userId", this.userId);
//        return hashMap;
//    }

    public Map<String, Object> getErrorReportParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getAnchorLiveActionParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("enterType", str2);
        return hashMap;
    }

    public Map<String, Object> getUserLiveActionParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("enterType", str);
        return hashMap;
    }

    public Map<String, Object> getAnchorGuardListParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 100);
        LogUtils.json("json", hashMap);
        return hashMap;
    }

//    public Map<String, Object> getOpenGuardParams(GuardItemEntity guardItemEntity, String str) {
//        HashMap hashMap = new HashMap();
//        if (guardItemEntity == null) {
//            return hashMap;
//        }
//        hashMap.put("guardId", guardItemEntity.id);
//        hashMap.put(ConstantUtils.GUARD_TYPE_ICON_KEY, guardItemEntity.type);
//        hashMap.put("anchorId", guardItemEntity.anchorId);
//        hashMap.put(ApiConstant.PRICE, guardItemEntity.price);
//        hashMap.put("userId", this.userId);
//        hashMap.put("guardRatio", guardItemEntity.guardRatio);
//        hashMap.put("avatar", UserInfoManager.getInstance().getAvatar());
//        hashMap.put("userName", UserInfoManager.getInstance().getUserNickname());
//        hashMap.put(ConstantUtils.EXP_GRADE_ICON_KEY, guardItemEntity.expGrade);
//        hashMap.put("liveCount", str);
//        return hashMap;
//    }

    public Map<String, Object> getBroadcastClickParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getPersonalGuardInfoParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getWebSocketAddressParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("enterType", str2);
        return hashMap;
    }

    public Map<String, Object> getIncomeConsumeParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("date", str);
        return hashMap;
    }

    public Map<String, Object> getIncomeConsumeDetailParams(int i, String str) {
        Map<String, Object> userIdParams = getUserIdParams(i);
        userIdParams.put("date", str);
        return userIdParams;
    }

    public Map<String, Object> getIncomeConsumeDetailParams(int i, String str, boolean z) {
        Map<String, Object> userIdParams = getUserIdParams(i);
        userIdParams.put("date", str);
        userIdParams.put("isFree", z ? "1" : "0");
        return userIdParams;
    }

    public Map<String, Object> getScopeParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("scope", "0");
        return hashMap;
    }

    public Map<String, Object> getAllCarParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("scope", "1");
        return hashMap;
    }

//    public Map<String, Object> getBuyCarParams(String str, String str2, String str3) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("userId", this.userId);
//        hashMap.put(LogConstants.CAR_ID, str);
//        hashMap.put("type", str2);
//        hashMap.put(ApiConstant.PRICE, str3);
//        return hashMap;
//    }

    public Map<String, Object> getUseCarParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("uniqueId", str);
        hashMap.put("isUsed", str2);
        return hashMap;
    }

    public Map<String, Object> getGiftBoxListParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getTaskBoxListParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put(LogConstants.APP_ID, UserInfoManager.getInstance().getAppId());
        return hashMap;
    }

    public Map<String, Object> getTaskChangeParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("taskBoxId", str);
        return hashMap;
    }

    public Map<String, Object> getClickAdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

//    public Map<String, Object> getReportLiveParams(String str, String str2, String str3, String str4, String str5) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("offenceUserId", this.userId);
//        hashMap.put("beOffenceUserId", str);
//        hashMap.put("contentCode", str2);
//        hashMap.put(UriUtil.LOCAL_CONTENT_SCHEME, str3);
//        if (!TextUtils.isEmpty(str4)) {
//            hashMap.put(MimeType.MIME_TYPE_PREFIX_IMAGE, str4);
//        }
//        hashMap.put("verifyCode", str5);
//        return hashMap;
//    }

    public Map<String, Object> getTrumpetSendParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("content", str);
        return hashMap;
    }

    public Map<String, Object> getTrumpetSendUpdateTrumpetClickCountParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getNobilityBuyParams(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put(ConstantUtils.NOBILITY_TYPE_ICON_KEY, str);
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("anchorId", str2);
        }
        hashMap.put("openType", str3);
        hashMap.put("liveCount", str4);
        return hashMap;
    }

    public Map<String, Object> getVipSeatListParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getNobilityEnterHideParams(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isHide", z ? "1" : "0");
        return hashMap;
    }

    public Map<String, Object> getTurntableAwardInfoParams(String str, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("type", z ? "20" : "1");
        return hashMap;
    }

    public Map<String, Object> getTurntableDrawParams(String str, boolean z, int i, String str2) {
        Map<String, Object> turntableAwardInfoParams = getTurntableAwardInfoParams(str, z);
        turntableAwardInfoParams.put("drawTimes", String.valueOf(i));
        turntableAwardInfoParams.put("version", str2);
        return turntableAwardInfoParams;
    }

    public Map<String, Object> getTurntableLuckValueList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getLotteryTicketParams(int i, String str, int i2) {
        Map<String, Object> pageListParams = getPageListParams(i);
        pageListParams.put("userId", this.userId);
        pageListParams.put("date", str);
        pageListParams.put("lotteryTicketBalanceType", String.valueOf(i2));
        return pageListParams;
    }

    public Map<String, Object> getJudgeUserBanPostParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("userId", str2);
        return hashMap;
    }

    public Map<String, Object> getLiveStatusParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(LogConstants.APP_ID, str);
        hashMap.put("anchorId", str2);
        return hashMap;
    }

    public Map<String, Object> getIdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getStarGiftAnchorListParams(String str) {
        Map<String, Object> appIdParams = getAppIdParams();
        appIdParams.put("markId", str);
        return appIdParams;
    }

    public Map<String, Object> getStarGiftAnchorRankParams(String str, String str2) {
        Map<String, Object> starGiftAnchorListParams = getStarGiftAnchorListParams(str);
        starGiftAnchorListParams.put("anchorId", str2);
        return starGiftAnchorListParams;
    }

    public Map<String, Object> getStarGiftUserRankParams(String str) {
        return getStarGiftAnchorListParams(str);
    }

    public Map<String, Object> getCodeParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("code", str);
        return hashMap;
    }

    public Map<String, Object> getMarkIdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("markId", str);
        return hashMap;
    }

    public Map<String, Object> getImpressionParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(LogConstants.ANCHOR_ID, str);
        hashMap.put(LogConstants.ANCHOR_APP_ID, str2);
        return hashMap;
    }

    public Map<String, Object> getUpdateImpressionParams(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put(LogConstants.ANCHOR_ID, str);
        hashMap.put(LogConstants.ANCHOR_APP_ID, str2);
        hashMap.put("increment", str3);
        hashMap.put("decrement", str4);
        return hashMap;
    }

    public Map<String, Object> getAchievementWallParams(String str, String str2, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        hashMap.put(ConstantUtils.ROLE_ICON_KEY, str2);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

    public Map<String, Object> getUserCardParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getWatchHistoryParams(String str, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("duration", String.valueOf(j));
        return hashMap;
    }
//
//    public Map<String, Object> getAnchorFeedbackParams(String str, int i, String str2, String str3) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("liveId", str);
//        hashMap.put("liveCount", String.valueOf(i));
//        hashMap.put("feedbackContent", str2);
//        hashMap.put(LiveEndEvaluationDialog.CHARGE_TYPE, str3);
//        hashMap.put(AopConstants.NETWORK_TYPE, NetworkUtils.isMobileData() ? "1" : "0");
//        return hashMap;
//    }

    public Map<String, Object> getStartLiveSubmitParams(String str, String str2, String str3, String str4, RelationLastLiveEntity relationLastLiveEntity) {
        HashMap hashMap = new HashMap();
//        hashMap.put(LiveEndEvaluationDialog.CHARGE_TYPE, str);
//        hashMap.put(LiveEndEvaluationDialog.TICKET_PRICE, str2);
        hashMap.put("isAllowTicket", str3);
        if (TextUtils.equals(str4, "1")) {
            hashMap.put("isRelation", str4);
            if (relationLastLiveEntity != null) {
                hashMap.put("relationStartLiveId", relationLastLiveEntity.relationStartLiveId);
                hashMap.put("relationStartLiveTag", relationLastLiveEntity.relationStartLiveTag);
                hashMap.put("relationStartLiveTopic", relationLastLiveEntity.relationStartLiveTopic);
            }
        }
        return hashMap;
    }

    public Map<String, Object> getStartPayLiveVerifyParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("myUserId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getTicketRoomBaseInfoParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("liveCount", str2);
        return hashMap;
    }

    public Map<String, Object> getSwitchTicketRoomParams(String str, String str2, String str3) {
        Map<String, Object> ticketRoomBaseInfoParams = getTicketRoomBaseInfoParams(str, str2);
        ticketRoomBaseInfoParams.put("action", str3);
        return ticketRoomBaseInfoParams;
    }

    public Map<String, Object> getTicketRoomBaseInfoParams(String str, String str2, boolean z, int i) {
        Map<String, Object> ticketRoomBaseInfoParams = getTicketRoomBaseInfoParams(str, str2);
        ticketRoomBaseInfoParams.put("sort", z ? "up" : "down");
        ticketRoomBaseInfoParams.put(PAGE_NUMBER, Integer.valueOf(i));
        ticketRoomBaseInfoParams.put(PAGE_SIZE, 20);
        return ticketRoomBaseInfoParams;
    }

//    public Map<String, Object> getLiveEndEvaluationParams(String str, String str2, String str3, long j, String str4, String str5, List<String> list) {
//        Map<String, Object> ticketRoomBaseInfoParams = getTicketRoomBaseInfoParams(str, str2);
//        ticketRoomBaseInfoParams.put(LiveEndEvaluationDialog.CHARGE_TYPE, str3);
//        ticketRoomBaseInfoParams.put("score", str4);
//        ticketRoomBaseInfoParams.put("evaluationContent", str5);
//        ticketRoomBaseInfoParams.put("userName", UserInfoManager.getInstance().getUserNickname());
//        ticketRoomBaseInfoParams.put(LiveEndEvaluationDialog.WATCH_DURATION, String.valueOf(j));
//        if (list != null && !list.isEmpty()) {
//            ticketRoomBaseInfoParams.put("tags", StringUtils.getCommaSpliceStrByList(list));
//        }
//        return ticketRoomBaseInfoParams;
//    }

    public Map<String, Object> getUserCheckTicketParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getMyClanListParams(int i, int i2) {
        Map<String, Object> pageListParams = getPageListParams(i2);
        pageListParams.put(LogConstants.LIVE_STATUS, String.valueOf(i));
        return pageListParams;
    }

    public Map<String, Object> getLiveOpenListParams(int i, int i2, int i3) {
        HashMap hashMap = new HashMap();
        hashMap.put("branchChannelId", SLLiveSDK.getSingleton().ADV_CHANNEL_ID);
        hashMap.put("type", String.valueOf(i));
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i2));
        hashMap.put(PAGE_SIZE, Integer.valueOf(i3));
        return hashMap;
    }

    public Map<String, Object> getEndLiveDrawInfoParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("drawId", str);
        return hashMap;
    }

    public Map<String, Object> getAwardListParams(int i) {
        Map<String, Object> pageListParams = getPageListParams(i, 100);
        pageListParams.put("myUserId", this.userId);
        return pageListParams;
    }

    public Map<String, Object> getAwardDetailParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("winningRecordId", str);
        return hashMap;
    }

//    public Map<String, Object> getAddMessageParams(String str, String str2, String str3) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("userWinningRecordId", str);
//        hashMap.put(UriUtil.LOCAL_CONTENT_SCHEME, str2);
//        hashMap.put("receiverUserId", str3);
//        return hashMap;
//    }

    public Map<String, Object> getMessageDetailParams(String str, int i) {
        Map<String, Object> pageListParams = getPageListParams(i, 100);
        pageListParams.put("liveDrawRecordId", str);
        return pageListParams;
    }

//    public Map<String, Object> getAddAddressParams(String str, String str2) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("winningRecordId", str);
//        hashMap.put(ApiConstant.PHONE.ADDRESS, str2);
//        return hashMap;
//    }

    public Map<String, Object> getAppealInfoParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveDrawAppealRecordId", str);
        return hashMap;
    }

    public Map<String, Object> getAppealInfoParamsByAward(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userWinningRecordId", str);
        return hashMap;
    }

    public Map<String, Object> getGivenAwardHistoryInfoParams(String str, String str2, int i) {
        Map<String, Object> pageListParams = getPageListParams(i, 100);
        if (TextUtils.isEmpty(str)) {
            str = String.valueOf(System.currentTimeMillis() / 1000);
        }
        pageListParams.put(LogConstants.OPEN_ID, UserInfoManager.getInstance().getAppOpenId());
        pageListParams.put(LogConstants.APP_ID, UserInfoManager.getInstance().getAppId());
        pageListParams.put("winningTime", str);
        pageListParams.put("winningStatus", str2);
        return pageListParams;
    }

    public Map<String, Object> getSubmitAppealParams(String str, String str2, String str3, String str4, String str5, String str6) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveDrawRecordId", str);
        hashMap.put("userWinningRecordId", str2);
        hashMap.put("appealDesc", str3);
        hashMap.put("image1", str4);
        hashMap.put("image2", str5);
        hashMap.put("image3", str6);
        return hashMap;
    }

    public Map<String, Object> getTurntableDrawRecordParams(int i, String str) {
        Map<String, Object> pageListParams = getPageListParams(i, 30);
        pageListParams.put("dateStr", str);
        return pageListParams;
    }

    public Map<String, Object> getInteractTaskUpdateParams(String str, String str2, String str3, String str4, String str5) {
        Map<String, Object> taskIdParams = getTaskIdParams(str);
        taskIdParams.put("taskName", str2);
        taskIdParams.put("giftMarkId", str3);
        taskIdParams.put("giftUrl", str4);
        taskIdParams.put("giftNum", str5);
        return taskIdParams;
    }

    public Map<String, Object> getTaskIdParams(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("taskId", str);
        }
        return hashMap;
    }

    public Map<String, Object> getTaskNameTagParams(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("taskName", str);
        }
        LogUtils.json(hashMap);
        return hashMap;
    }

    public Map<String, Object> getTaskTagDelParams(List<String> list) {
        HashMap hashMap = new HashMap();
        hashMap.put("taskIdListString", StringUtils.getCommaSpliceStrByList(list));
        return hashMap;
    }

    public Map<String, Object> getTaskStatusUpdateParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        hashMap.put("action", getStatusAction(str2));
        return hashMap;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String getStatusAction(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == 48627) {
            if (str.equals(ConstantUtils.QM_TASK_STATUS_102)) {
                c = 5;
            }
            c = 65535;
        } else if (hashCode != 48629) {
            switch (hashCode) {
                case 49588:
                    if (str.equals(ConstantUtils.QM_TASK_STATUS_202)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 49589:
                    if (str.equals(ConstantUtils.QM_TASK_STATUS_203)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 49590:
                    if (str.equals(ConstantUtils.QM_TASK_STATUS_204)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 49591:
                    if (str.equals(ConstantUtils.QM_TASK_STATUS_205)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals(ConstantUtils.QM_TASK_STATUS_104)) {
                c = 4;
            }
            c = 65535;
        }
        return c != 0 ? c != 1 ? c != 2 ? (c == 3 || c == 4) ? "finished" : c != 5 ? "" : "cancel" : "TtmlNode.START" : "accept" : "reject";
    }

    public Map<String, Object> getIntimateTaskListParams(int i, String str, String str2) {
        Map<String, Object> pageListParams = getPageListParams(i, 50);
        pageListParams.putAll(getIntimateTaskListParams(str, str2));
        return pageListParams;
    }

    public Map<String, Object> getIntimateTaskListParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("tab", str2);
        return hashMap;
    }

    public Map<String, Object> getUserCommitIntimateParams(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("taskName", str2);
        hashMap.put("giftMarkId", str3);
        hashMap.put("giftPrice", str4);
        hashMap.put("giftNum", str5);
        return hashMap;
    }

    public Map<String, Object> getConfigGiftThresholdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("giftThreshold", str);
        return hashMap;
    }

    public Map<String, Object> getYYLinkMicParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put("channelName", "ROOM1");
        return hashMap;
    }

    public Map<String, Object> getLiveIdCountParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("liveCount", str2);
        return hashMap;
    }

    public Map<String, Object> getYYLinkActionMenuParams(String str, String str2, String str3) {
        Map<String, Object> liveIdCountParams = getLiveIdCountParams(str, str2);
        liveIdCountParams.put("targetUserId", str3);
        return liveIdCountParams;
    }

    public Map<String, Object> getYYLinkMuteSeatParams(String str, String str2, String str3, int i) {
        Map<String, Object> yYLinkActionMenuParams = getYYLinkActionMenuParams(str, str2, str3);
        yYLinkActionMenuParams.put("isQuiet", String.valueOf(i));
        LogUtils.json(yYLinkActionMenuParams);
        return yYLinkActionMenuParams;
    }

    public Map<String, Object> getVideoLinkUserConnectSuccessParams(String str, String str2, String str3) {
        Map<String, Object> liveIdCountParams = getLiveIdCountParams(str, str2);
        liveIdCountParams.put("rtcRoomId", str3);
        return liveIdCountParams;
    }

    public Map<String, Object> getVideoRoomAcceptParams(String str, String str2, boolean z) {
        Map<String, Object> liveIdCountParams = getLiveIdCountParams(str, str2);
        liveIdCountParams.put("isAccept", z ? "1" : "0");
        return liveIdCountParams;
    }

    public Map<String, Object> getBagIdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("bagId", str);
        return hashMap;
    }

    public Map<String, Object> getDateParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("beginDate", str);
        hashMap.put("endDate", str2);
        LogUtils.json(hashMap);
        return hashMap;
    }

    public Map<String, Object> getDatePageParams(String str, String str2, int i, int i2) {
        Map<String, Object> dateParams = getDateParams(str, str2);
        dateParams.put(PAGE_NUMBER, Integer.valueOf(i));
        dateParams.put(PAGE_SIZE, Integer.valueOf(i2));
        return dateParams;
    }

    public Map<String, Object> getAuthorizedLoginParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(ConstantUtils.LOGIN_ACCESS_TOKEN, str);
        return hashMap;
    }

    public Map<String, Object> getPageListByShelfIdParams(String str, int i) {
        return getPageListByShelfIdParams(str, i, 20);
    }

    public Map<String, Object> getPageListByShelfIdParams(String str, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("shelfId", str);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(i2));
        return hashMap;
    }

    public Map<String, Object> getProductDetailListParams(String str, String str2, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("productId", str);
        hashMap.put("sortType", str2);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, 20);
        return hashMap;
    }

//    public Map<String, Object> getBuyProductParams(String str, String str2) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("productId", str);
//        hashMap.put(ApiConstant.PRICE, str2);
//        return hashMap;
//    }

    public Map<String, Object> getProductIdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("productId", str);
        return hashMap;
    }

    public Map<String, Object> getPlayUrlParam(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("productItemId", str);
        return hashMap;
    }

    public Map<String, Object> getSubmitProductReport(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("productId", str);
        hashMap.put("productItemId", str2);
        hashMap.put("reportType", str3);
        return hashMap;
    }

    public Map<String, Object> getOperationProductParams(int i, List<String> list) {
        HashMap hashMap = new HashMap();
        hashMap.put("operation", String.valueOf(i));
        hashMap.put("productIds", StringUtils.getCommaSpliceStrByList(list));
        LogUtils.json(list);
        return hashMap;
    }

//    public Map<String, Object> getAddProductParams(HJProductEntity hJProductEntity, List<String> list) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("name", hJProductEntity.name);
//        hashMap.put(NodeValue.Application.description, hJProductEntity.description);
//        hashMap.put(ApiConstant.PRICE, hJProductEntity.price);
//        hashMap.put("promotionPrice", hJProductEntity.promotionPrice);
//        hashMap.put("isPutAway", hJProductEntity.isPutAway);
//        hashMap.put("isPromotion", hJProductEntity.isPromotion);
//        hashMap.put(MimeType.MIME_TYPE_PREFIX_IMAGE, hJProductEntity.image);
//        hashMap.put("upLoadIds", StringUtils.getCommaSpliceStrByList(list));
//        LogUtils.json(hashMap);
//        return hashMap;
//    }

//    public Map<String, Object> getEditProductParams(HJProductEntity hJProductEntity, List<String> list, List<String> list2) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("productId", hJProductEntity.id);
//        hashMap.put("name", hJProductEntity.name);
//        hashMap.put(NodeValue.Application.description, hJProductEntity.description);
//        hashMap.put(ApiConstant.PRICE, hJProductEntity.price);
//        hashMap.put("promotionPrice", hJProductEntity.promotionPrice);
//        hashMap.put("isPutAway", hJProductEntity.isPutAway);
//        hashMap.put("isPromotion", hJProductEntity.isPromotion);
//        hashMap.put(MimeType.MIME_TYPE_PREFIX_IMAGE, hJProductEntity.image);
//        hashMap.put("upLoadIds", StringUtils.getCommaSpliceStrByList(list));
//        hashMap.put("deleteProductItemIds", StringUtils.getCommaSpliceStrByList(list2));
//        LogUtils.json(hashMap);
//        return hashMap;
//    }

    public Map<String, Object> getProductPreUploadParam(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("fileName", str);
        hashMap.put("md5", str2);
        return hashMap;
    }

    public Map<String, Object> getProductUploadCompleteParam(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("uploadId", str);
        hashMap.put("duration", str2);
        hashMap.put("fileSize", str3);
        return hashMap;
    }

    public Map<String, Object> getOnLineProductListParams(int i, String str) {
        Map<String, Object> pageListParams = getPageListParams(i, 100);
        pageListParams.put("productId", str);
        return pageListParams;
    }

    public Map<String, Object> getBluetoothSendParams(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("id", str);
        }
        hashMap.put("price", str5);
        hashMap.put("liveId", str3);
        hashMap.put("waveBand", str2);
        hashMap.put("giftMarkId", str4);
        return hashMap;
    }

    public Map<String, Object> getThermometerBombParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put("userAppId", UserInfoManager.getInstance().getAppId());
        return hashMap;
    }

    public Map<String, Object> getMLAddSettingParams(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoCallAvatar", str);
        hashMap.put("uploadIds", str2);
        hashMap.put("images", str3);
        hashMap.put("signature", str4);
        return hashMap;
    }

    public Map<String, Object> getMLSettingParams(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("orderTakingStatus", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("videoCallUnitPrice", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("signature", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("assignTalentVideoId", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            hashMap.put("videoCallAvatar", str5);
        }
        return hashMap;
    }

    public Map<String, Object> getMLUpdateImgListParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("deleteVideoCallTalentImageIds", str);
        hashMap.put("addImgUrls", str2);
        LogUtils.json(hashMap);
        return hashMap;
    }

    public Map<String, Object> getMLUpdateVideoListParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("deleteVideoCallTalentVideoIds", str);
        hashMap.put("addUploadIds", str2);
        LogUtils.json(hashMap);
        return hashMap;
    }

    public Map<String, Object> getMLAnchorInfoTabParams(String str, String str2, int i, int i2) {
        Map<String, Object> anchorIdParams = getAnchorIdParams(str);
        anchorIdParams.put("tab", str2);
        anchorIdParams.put(PAGE_NUMBER, Integer.valueOf(i));
        anchorIdParams.put(PAGE_SIZE, Integer.valueOf(i2));
        return anchorIdParams;
    }

    public Map<String, Object> getMLRankParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        return hashMap;
    }

    public Map<String, Object> getMLReportParams(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put("reportType", str2);
        hashMap.put("reportImage", str3);
        hashMap.put("reportContent", str4);
        LogUtils.json(hashMap);
        return hashMap;
    }

//    public Map<String, Object> getSubmitEvaluateParams(String str, String str2, List<String> list, String str3) {
//        HashMap hashMap = new HashMap();
//        hashMap.put("videoCallNo", str);
//        hashMap.put("score", str2);
//        if (list != null && !list.isEmpty()) {
//            hashMap.put("descriptionTag", StringUtils.getCommaSpliceStrByList(list));
//        }
//        hashMap.put(NodeValue.Application.description, str3);
//        return hashMap;
//    }

    public Map<String, Object> getMLFinishedDetailParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoCallNo", str);
        return hashMap;
    }

    public Map<String, Object> getMLVideoUrlParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoCallTalentVideoId", str);
        return hashMap;
    }

    public Map<String, Object> getTargetUserIdParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("targetUserId", str);
        return hashMap;
    }

    public Map<String, Object> getMLDisturbChangeParams(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isRefuseCall", z ? "1" : "0");
        return hashMap;
    }

    public Map<String, Object> getMLIncomeDetailParams(int i, String str) {
        Map<String, Object> pageListParams = getPageListParams(i);
        pageListParams.put("anchorId", this.userId);
        pageListParams.put("date", str);
        return pageListParams;
    }

    public Map<String, Object> getMLFriendRankHideParams(String str, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put("action", z ? "1" : "2");
        return hashMap;
    }

    public Map<String, Object> getMLRecommendAnchorParams(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("lastChatAnchorId", str);
        }
        return hashMap;
    }
}
