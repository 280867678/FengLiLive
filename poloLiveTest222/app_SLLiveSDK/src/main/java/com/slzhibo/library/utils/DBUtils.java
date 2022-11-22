package com.slzhibo.library.utils;



import android.content.ContentValues;
import android.text.TextUtils;
//import com.slzhibo.library.analytics.AopConstants;
import com.slzhibo.library.model.ActivityListEntity;
import com.slzhibo.library.model.GiftItemEntity;
import com.slzhibo.library.model.UserPrivateMessageEntity;
import com.slzhibo.library.model.db.ActivityBagDBEntity;
import com.slzhibo.library.model.db.ActivityDBEntity;
import com.slzhibo.library.model.db.AnchorLiveDataEntity;
import com.slzhibo.library.model.db.AttentionAnchorDBEntity;
import com.slzhibo.library.model.db.GiftBoxEntity;
import com.slzhibo.library.model.db.GiftDownloadItemDBEntity;
import com.slzhibo.library.model.db.LightImpressionDBEntity;
import com.slzhibo.library.model.db.LiveDataEntity;
import com.slzhibo.library.model.db.MsgDetailListEntity;
import com.slzhibo.library.model.db.MsgListEntity;
import com.slzhibo.library.model.db.MsgStatusEntity;
import com.slzhibo.library.model.db.PayLiveDBEntity;
import com.slzhibo.library.model.db.SearchKeywordEntity;
import com.slzhibo.library.model.db.ShieldUserDBEntity;
import com.slzhibo.library.model.db.ShortcutItemEntity;
import com.slzhibo.library.model.db.StickerEntity;
import com.slzhibo.library.model.db.WatchRecordEntity;
import com.slzhibo.library.utils.litepal.LitePal;
import com.slzhibo.library.utils.litepal.crud.LitePalSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class DBUtils {
    public static <T extends LitePalSupport> List<T> findAll(Class<T> cls) {
        return LitePal.findAll(cls, new long[0]);
    }

    public static <T extends LitePalSupport> List<T> findAllWithWhere(Class<T> cls, String... strArr) {
        return LitePal.where(strArr).find(cls);
    }

    public static <T extends LitePalSupport> List<T> findAllWithWhere(Class<T> cls, boolean z, String... strArr) {
        return LitePal.where(strArr).find(cls, z);
    }

    public static long getCount(Class cls) throws Throwable {
        return LitePal.count(cls);
    }

    public static <T extends LitePalSupport> List<T> findAllWithWhereOrder(Class<T> cls, String str, String... strArr) {
        return LitePal.where(strArr).order(str).find(cls);
    }

    public static <T extends LitePalSupport> List<T> findAllWithOrder(Class<T> cls, String str) {
        return LitePal.order(str).find(cls);
    }

    public static <T extends LitePalSupport> void saveAll(List<T> list) {
        LitePal.saveAll(list);
    }

    public static <T extends LitePalSupport> boolean saveItem(T t) {
        return t.save();
    }

    public static <T extends LitePalSupport> void clear(T t) {
        t.clearSavedState();
    }

    public static <T extends LitePalSupport> T findLastItem(Class<T> cls) {
        return (T) ((LitePalSupport) LitePal.findLast(cls));
    }

    public static <T extends LitePalSupport> int deleteItem(T t) {
        return t.delete();
    }

    public static <T extends LitePalSupport> int deleteFirst(Class<T> cls) {
        return LitePal.delete(cls, 0L);
    }

    public static <T extends LitePalSupport> int deleteAll(Class<T> cls) {
        return LitePal.deleteAll((Class<?>) cls, new String[0]);
    }

    public static <T extends LitePalSupport> int deleteAllWithCondition(Class<T> cls, String... strArr) {
        return LitePal.deleteAll((Class<?>) cls, strArr);
    }

    public static <T extends LitePalSupport> int updateAll(Class<T> cls, String str, int i, String... strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Integer.valueOf(i));
        return LitePal.updateAll((Class<?>) cls, contentValues, strArr);
    }

    public static <T extends LitePalSupport> int updateAll(Class<T> cls, String str, String str2, String... strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, str2);
        return LitePal.updateAll((Class<?>) cls, contentValues, strArr);
    }

    public static <T extends LitePalSupport> int updateAll(Class<T> cls, String str, long j, String... strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Long.valueOf(j));
        return LitePal.updateAll((Class<?>) cls, contentValues, strArr);
    }

    public static <T extends LitePalSupport> T selectOneItemByOrder(Class<T> cls, String str, String... strArr) {
        List find = LitePal.where(strArr).order(str).limit(1).find(cls);
        if (find == null || find.isEmpty()) {
            return null;
        }
        return (T) ((LitePalSupport) find.get(0));
    }

    public static <T extends LitePalSupport> T selectOneItem(Class<T> cls, String... strArr) {
        List find = LitePal.where(strArr).limit(1).find(cls);
        if (find == null || find.isEmpty()) {
            return null;
        }
        return (T) ((LitePalSupport) find.get(0));
    }

    public static <T extends LitePalSupport> List<T> selectItemList(Class<T> cls, int i, String... strArr) {
        List<T> find = LitePal.where(strArr).limit(i).find(cls);
        if (find == null || find.isEmpty()) {
            return null;
        }
        return find;
    }

    public static void saveOrUpdateKeyword(String str) {
        if (((SearchKeywordEntity) selectOneItem(SearchKeywordEntity.class, "keyword = ?", str)) == null) {
            new SearchKeywordEntity(System.currentTimeMillis(), str).save();
        } else {
            updateAll(SearchKeywordEntity.class, "insertTime", System.currentTimeMillis(), "keyword = ?", str);
        }
    }

    public static void saveOrUpdateWatchRecord(WatchRecordEntity watchRecordEntity) {
        if (((WatchRecordEntity) selectOneItem(WatchRecordEntity.class, "liveId = ? and userId = ?", watchRecordEntity.liveId, UserInfoManager.getInstance().getUserId())) == null) {
            watchRecordEntity.save();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", watchRecordEntity.userId);
        contentValues.put("liveId", watchRecordEntity.liveId);
        contentValues.put("title", watchRecordEntity.title);
        contentValues.put("label", watchRecordEntity.label);
        contentValues.put("anchorNickname", watchRecordEntity.anchorNickname);
        contentValues.put("liveTime", Long.valueOf(watchRecordEntity.liveTime));
        LitePal.updateAll(WatchRecordEntity.class, contentValues, "liveId = ? and userId = ?", watchRecordEntity.liveId, UserInfoManager.getInstance().getUserId());
    }

    public static void saveOrUpdateLiveData(LiveDataEntity liveDataEntity) {
        if (((LiveDataEntity) selectOneItem(LiveDataEntity.class, "liveId = ? ", liveDataEntity.liveId)) == null) {
            liveDataEntity.save();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("anchorId", liveDataEntity.anchorId);
        contentValues.put(LogConstants.APP_ID, liveDataEntity.appId);
        contentValues.put("liveId", liveDataEntity.liveId);
        contentValues.put("tag", liveDataEntity.tag);
        contentValues.put(ConstantUtils.EXP_GRADE_ICON_KEY, liveDataEntity.expGrade);
        contentValues.put("nickname", liveDataEntity.nickname);
        contentValues.put("viewerLevel", liveDataEntity.viewerLevel);
        contentValues.put("endTime", Long.valueOf(liveDataEntity.endTime));
        contentValues.put("startTime", Long.valueOf(liveDataEntity.startTime));
        LitePal.updateAll(LiveDataEntity.class, contentValues, "liveId = ? ", liveDataEntity.liveId);
    }

    public static LiveDataEntity getLiveData() {
        return (LiveDataEntity) selectOneItem(LiveDataEntity.class, new String[0]);
    }

    public static void saveOrUpdateAnchorLiveData(AnchorLiveDataEntity anchorLiveDataEntity) {
        if (((AnchorLiveDataEntity) selectOneItem(AnchorLiveDataEntity.class, "liveId = ? ", anchorLiveDataEntity.liveId)) == null) {
            anchorLiveDataEntity.save();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("anchorId", anchorLiveDataEntity.anchorId);
        contentValues.put(LogConstants.APP_ID, anchorLiveDataEntity.appId);
        contentValues.put("liveId", anchorLiveDataEntity.liveId);
        contentValues.put("tag", anchorLiveDataEntity.tag);
        contentValues.put(ConstantUtils.EXP_GRADE_ICON_KEY, anchorLiveDataEntity.expGrade);
        contentValues.put("nickname", anchorLiveDataEntity.nickname);
        contentValues.put("endTime", Long.valueOf(anchorLiveDataEntity.endTime));
        contentValues.put("startTime", Long.valueOf(anchorLiveDataEntity.startTime));
        contentValues.put("coinNum", anchorLiveDataEntity.coinNum);
        contentValues.put("barrageNum", anchorLiveDataEntity.barrageNum);
        contentValues.put(LogConstants.VIEWER_NUM, anchorLiveDataEntity.viewerCount);
        LitePal.updateAll(AnchorLiveDataEntity.class, contentValues, "liveId = ? ", anchorLiveDataEntity.liveId);
    }

    public static AnchorLiveDataEntity getAnchorLiveData() {
        return (AnchorLiveDataEntity) selectOneItem(AnchorLiveDataEntity.class, new String[0]);
    }

    public static void saveStickerList(List<StickerEntity> list) {
        deleteStickerList();
        saveAll(list);
    }

    public static void deleteStickerList() {
        deleteAllWithCondition(StickerEntity.class, "userId = ?", UserInfoManager.getInstance().getUserId());
    }

    public static List<StickerEntity> getStickerList() {
        return findAllWithWhere(StickerEntity.class, "userId = ?", UserInfoManager.getInstance().getUserId());
    }

    public static StickerEntity getStickerByUUID(String str) {
        return (StickerEntity) selectOneItem(StickerEntity.class, "uuID = ? and userId = ?", str, UserInfoManager.getInstance().getUserId());
    }

    public static int getStickerListCount() {
        return getStickerList().size();
    }

    public static List<String> getGiftBoxIdList(String str) {
        ArrayList arrayList = new ArrayList();
        for (GiftBoxEntity giftBoxEntity : findAllWithWhere(GiftBoxEntity.class, "userId = ? and liveId = ?", UserInfoManager.getInstance().getUserId(), str)) {
            arrayList.add(giftBoxEntity.giftBoxUniqueCode);
        }
        return arrayList;
    }

    public static void deleteGiftBoxList(String str) {
        deleteAllWithCondition(GiftBoxEntity.class, "userId = ? and liveId = ?", UserInfoManager.getInstance().getUserId(), str);
    }

    public static void saveOneGiftBox(GiftBoxEntity giftBoxEntity) {
        if (giftBoxEntity != null) {
            giftBoxEntity.save();
        }
    }

    public static void saveOnePrivateMsgDetail(MsgDetailListEntity msgDetailListEntity) {
        if (msgDetailListEntity != null) {
            msgDetailListEntity.save();
        }
    }

    public static void updatePrivateMsgDetail(String str, String str2, String str3) {
        if (((MsgDetailListEntity) selectOneItem(MsgDetailListEntity.class, "userId = ? and messageId = ? ", str, str2)) != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", str3);
            LitePal.updateAll(MsgDetailListEntity.class, contentValues, "userId = ? and messageId = ? ", str, str2);
        }
    }

    public static boolean isShowOfflinePrivateMsgDialog(List<UserPrivateMessageEntity> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<UserPrivateMessageEntity> it2 = list.iterator();
        while (it2.hasNext()) {
            if (((MsgDetailListEntity) selectOneItem(MsgDetailListEntity.class, "messageId = ? ", it2.next().messageId)) != null) {
                return true;
            }
        }
        return false;
    }

    public static List<MsgDetailListEntity> getAllPrivateMsgDetail(String str, String str2) {
        return findAllWithWhere(MsgDetailListEntity.class, "userId = ? and targetId = ?", str, str2);
    }

    public static void saveOrUpdateMsgList(MsgListEntity msgListEntity) {
        if (((MsgListEntity) selectOneItem(MsgListEntity.class, "userId = ? and appId = ? and targetId = ?", UserInfoManager.getInstance().getUserId(), UserInfoManager.getInstance().getAppId(), msgListEntity.targetId)) == null) {
            msgListEntity.save();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(LogConstants.APP_ID, msgListEntity.appId);
        contentValues.put("userId", msgListEntity.userId);
        contentValues.put("targetId", msgListEntity.targetId);
        contentValues.put("time", msgListEntity.time);
        LitePal.updateAll(MsgListEntity.class, contentValues, "userId = ? and appId = ? and targetId = ?", UserInfoManager.getInstance().getUserId(), UserInfoManager.getInstance().getAppId(), msgListEntity.targetId);
    }

    public static void saveOrUpdateMsgStatus(MsgStatusEntity msgStatusEntity) {
        if (((MsgStatusEntity) selectOneItem(MsgStatusEntity.class, "userId = ? and appId = ? ", UserInfoManager.getInstance().getUserId(), UserInfoManager.getInstance().getAppId())) == null) {
            msgStatusEntity.save();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(LogConstants.APP_ID, msgStatusEntity.appId);
        contentValues.put("userId", msgStatusEntity.userId);
        contentValues.put("readStatus", msgStatusEntity.readStatus);
        LitePal.updateAll(MsgStatusEntity.class, contentValues, "userId = ? and appId = ? ", UserInfoManager.getInstance().getUserId(), UserInfoManager.getInstance().getAppId());
    }

    public static boolean isUnReadBoolean() {
        MsgStatusEntity msgStatusEntity = (MsgStatusEntity) selectOneItem(MsgStatusEntity.class, "userId = ? and appId = ? ", UserInfoManager.getInstance().getUserId(), UserInfoManager.getInstance().getAppId());
        return msgStatusEntity != null && TextUtils.equals(msgStatusEntity.readStatus, "0");
    }

    public static List<MsgListEntity> getAllMsgList() {
        return findAllWithWhereOrder(MsgListEntity.class, "time desc", "userId = ? and appId = ?", UserInfoManager.getInstance().getUserId(), UserInfoManager.getInstance().getAppId());
    }

    public static boolean isClearAllMsgList() {
        List<MsgListEntity> allMsgList = getAllMsgList();
        return allMsgList != null && !allMsgList.isEmpty();
    }

    public static boolean isExistPrivateMsgList(String str) {
        return ((MsgListEntity) selectOneItem(MsgListEntity.class, "userId = ? and targetId = ?", UserInfoManager.getInstance().getUserId(), str)) != null;
    }

    public static void deletePrivateMsgList() {
        deleteAllWithCondition(MsgListEntity.class, "userId = ? and appId = ?", UserInfoManager.getInstance().getUserId(), UserInfoManager.getInstance().getAppId());
        deleteAllWithCondition(MsgDetailListEntity.class, "userId = ?", UserInfoManager.getInstance().getUserId());
    }

    public static void deleteOldPrivateMsgDetailList(String str) {
        for (MsgDetailListEntity msgDetailListEntity : selectItemList(MsgDetailListEntity.class, 50, "userId = ? and targetId = ?", UserInfoManager.getInstance().getUserId(), str)) {
            msgDetailListEntity.delete();
        }
    }

    public static void saveOneShortcut(ShortcutItemEntity shortcutItemEntity) {
        if (shortcutItemEntity != null) {
            shortcutItemEntity.save();
        }
    }

    public static List<ShortcutItemEntity> getAllShortcut() {
        return findAllWithWhere(ShortcutItemEntity.class, "userId = ? ", UserInfoManager.getInstance().getUserId());
    }

    public static void saveGiftDownloadItemList(List<GiftDownloadItemDBEntity> list) {
        try {
            deleteAll(GiftDownloadItemDBEntity.class);
            saveAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateGiftDownloadItem(String str, String str2, int i) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("giftDirPath", str2);
            contentValues.put("downloadStatus", Integer.valueOf(i));
            LitePal.updateAll(GiftDownloadItemDBEntity.class, contentValues, "markId = ? ", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isGiftDownloadItem(GiftItemEntity giftItemEntity) {
        try {
            return ((GiftDownloadItemDBEntity) selectOneItem(GiftDownloadItemDBEntity.class, "markId = ?", giftItemEntity.markId)) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static GiftDownloadItemDBEntity getGiftDownloadItemDBEntity(String str) {
        try {
            return (GiftDownloadItemDBEntity) selectOneItem(GiftDownloadItemDBEntity.class, "markId = ?", str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void saveAllAttentionAnchor(List<String> list) {
        if (!(list == null || list.isEmpty())) {
            deleteAll(AttentionAnchorDBEntity.class);
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    AttentionAnchorDBEntity attentionAnchorDBEntity = new AttentionAnchorDBEntity();
                    attentionAnchorDBEntity.userId = UserInfoManager.getInstance().getUserId();
                    attentionAnchorDBEntity.anchorId = str;
                    arrayList.add(attentionAnchorDBEntity);
                }
            }
            saveAll(arrayList);
        }
    }

    public static boolean isAttentionAnchor(String str) {
        return ((AttentionAnchorDBEntity) selectOneItem(AttentionAnchorDBEntity.class, "userId = ? and anchorId = ?", UserInfoManager.getInstance().getUserId(), str)) != null;
    }

    public static void attentionAnchor(String str, boolean z) {
        if (z) {
            AttentionAnchorDBEntity attentionAnchorDBEntity = new AttentionAnchorDBEntity();
            attentionAnchorDBEntity.userId = UserInfoManager.getInstance().getUserId();
            attentionAnchorDBEntity.anchorId = str;
            saveItem(attentionAnchorDBEntity);
            return;
        }
        deleteAllWithCondition(AttentionAnchorDBEntity.class, "userId = ? and anchorId = ?", UserInfoManager.getInstance().getUserId(), str);
    }

    public static void saveAllShieldUser(List<String> list) {
        if (!(list == null || list.isEmpty())) {
            deleteAll(ShieldUserDBEntity.class);
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    ShieldUserDBEntity shieldUserDBEntity = new ShieldUserDBEntity();
                    shieldUserDBEntity.userId = UserInfoManager.getInstance().getUserId();
                    shieldUserDBEntity.targetId = str;
                    arrayList.add(shieldUserDBEntity);
                }
            }
            saveAll(arrayList);
        }
    }

    public static List<String> getShieldList() {
        ArrayList arrayList = new ArrayList();
        List<ShieldUserDBEntity> findAllWithWhere = findAllWithWhere(ShieldUserDBEntity.class, "userId = ?", UserInfoManager.getInstance().getUserId());
        if (findAllWithWhere != null && !findAllWithWhere.isEmpty()) {
            for (ShieldUserDBEntity shieldUserDBEntity : findAllWithWhere) {
                arrayList.add(shieldUserDBEntity.targetId);
            }
        }
        return arrayList;
    }

    public static boolean isShieldUser(String str) {
        return ((ShieldUserDBEntity) selectOneItem(ShieldUserDBEntity.class, "userId = ? and targetId = ?", UserInfoManager.getInstance().getUserId(), str)) != null;
    }

    public static void updateShieldUser(String str, boolean z) {
        if (z) {
            ShieldUserDBEntity shieldUserDBEntity = new ShieldUserDBEntity();
            shieldUserDBEntity.userId = UserInfoManager.getInstance().getUserId();
            shieldUserDBEntity.targetId = str;
            saveItem(shieldUserDBEntity);
            return;
        }
        deleteAllWithCondition(ShieldUserDBEntity.class, "userId = ? and targetId = ?", UserInfoManager.getInstance().getUserId(), str);
    }

    public static void updateLightImpression(LightImpressionDBEntity lightImpressionDBEntity) {
        if (((LightImpressionDBEntity) selectOneItem(LightImpressionDBEntity.class, "appId = ? and userId = ? and anchorId = ? and anchorAppId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), lightImpressionDBEntity.anchorId, lightImpressionDBEntity.anchorAppId)) == null) {
            lightImpressionDBEntity.save();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(LogConstants.APP_ID, lightImpressionDBEntity.appId);
        contentValues.put("userId", lightImpressionDBEntity.userId);
        contentValues.put("anchorId", lightImpressionDBEntity.anchorId);
        contentValues.put(LogConstants.ANCHOR_APP_ID, lightImpressionDBEntity.anchorAppId);
        contentValues.put("impressionIds", lightImpressionDBEntity.impressionIds);
        LitePal.updateAll(LightImpressionDBEntity.class, contentValues, "appId = ? and userId = ? and anchorId = ? and anchorAppId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), lightImpressionDBEntity.anchorId, lightImpressionDBEntity.anchorAppId);
    }

    public static List<String> getLightImpressionList(String str, String str2) {
        LightImpressionDBEntity lightImpressionDBEntity = (LightImpressionDBEntity) selectOneItem(LightImpressionDBEntity.class, "appId = ? and userId = ? and anchorId = ? and anchorAppId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str, str2);
        if (lightImpressionDBEntity == null) {
            return null;
        }
        return StringUtils.getListByCommaSplit(lightImpressionDBEntity.impressionIds);
    }

    public static void savePayLiveInfo(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !isPayLiveValidState(str, str2)) {
            PayLiveDBEntity payLiveDBEntity = new PayLiveDBEntity();
            payLiveDBEntity.appId = UserInfoManager.getInstance().getAppId();
            payLiveDBEntity.userId = UserInfoManager.getInstance().getUserId();
            payLiveDBEntity.liveId = str;
            payLiveDBEntity.liveCount = str2;
            payLiveDBEntity.createTime = str3;
            payLiveDBEntity.save();
        }
    }

    public static void deletePayLiveInfo(String str, String str2) {
        deleteAllWithCondition(PayLiveDBEntity.class, "appId = ? and userId = ? and liveId = ? and liveCount = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str, str2);
    }

    public static boolean isPayLiveValidState(String str, String str2) {
        return ((PayLiveDBEntity) selectOneItem(PayLiveDBEntity.class, "appId = ? and userId = ? and liveId = ? and liveCount = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str, str2)) != null;
    }

    public static void saveOrUpdateActivityItemInfo(ActivityListEntity activityListEntity) {
        if (activityListEntity != null) {
            ActivityDBEntity activityItemInfoById = getActivityItemInfoById(activityListEntity.id);
            long currentTimeMillis = System.currentTimeMillis();
            String appId = UserInfoManager.getInstance().getAppId();
            String userId = UserInfoManager.getInstance().getUserId();
            String str = activityListEntity.id;
            if (activityItemInfoById != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(LogConstants.APP_ID, appId);
                contentValues.put("userId", userId);
                contentValues.put("activityId", str);
                contentValues.put("activityName", activityListEntity.name);
                contentValues.put("startTime", Long.valueOf(activityListEntity.startTime));
                contentValues.put("endTime", Long.valueOf(activityListEntity.endTime));
                contentValues.put("createTime", Long.valueOf(currentTimeMillis));
                contentValues.put("frequency", activityListEntity.frequency);
                contentValues.put("todayRemindStatus", activityItemInfoById.todayRemindStatus);
                LitePal.updateAll(ActivityDBEntity.class, contentValues, "appId = ? and userId = ? and activityId = ?", appId, userId, str);
                return;
            }
            ActivityDBEntity activityDBEntity = new ActivityDBEntity();
            activityDBEntity.appId = appId;
            activityDBEntity.userId = userId;
            activityDBEntity.activityId = str;
            activityDBEntity.activityName = activityListEntity.name;
            activityDBEntity.startTime = activityListEntity.startTime;
            activityDBEntity.endTime = activityListEntity.endTime;
            activityDBEntity.createTime = currentTimeMillis;
            activityDBEntity.frequency = activityListEntity.frequency;
            activityDBEntity.todayRemindStatus = "0";
            activityDBEntity.save();
        }
    }

    public static ActivityDBEntity getActivityItemInfoById(String str) {
        return (ActivityDBEntity) selectOneItem(ActivityDBEntity.class, "appId = ? and userId = ? and activityId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str);
    }

    public static List<ActivityDBEntity> getActivityList() {
        return findAllWithWhere(ActivityDBEntity.class, "appId = ? and userId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId());
    }

    public static int deleteActivityItemInfoById(String str) {
        return deleteAllWithCondition(ActivityDBEntity.class, "appId = ? and userId = ? and activityId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str);
    }

    public static int updateActivityOfTodayRemindStatus(ActivityListEntity activityListEntity, String str) {
        if (activityListEntity == null) {
            return -1;
        }
        return updateAll(ActivityDBEntity.class, "todayRemindStatus", str, "appId = ? and userId = ? and activityId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), activityListEntity.id);
    }

    public static void saveActivityBagItemInfo(ActivityListEntity activityListEntity, String str, String str2) {
        if (activityListEntity != null) {
            ActivityBagDBEntity activityBagItemInfoById = getActivityBagItemInfoById(activityListEntity.id, str);
            long currentTimeMillis = System.currentTimeMillis();
            String appId = UserInfoManager.getInstance().getAppId();
            String userId = UserInfoManager.getInstance().getUserId();
            String str3 = activityListEntity.id;
            if (activityBagItemInfoById != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(LogConstants.APP_ID, appId);
                contentValues.put("userId", userId);
                contentValues.put("activityId", str3);
                contentValues.put("bagId", str);
                contentValues.put("createTime", Long.valueOf(currentTimeMillis));
                contentValues.put("buyStatus", str2);
                LitePal.updateAll(ActivityBagDBEntity.class, contentValues, "appId = ? and userId = ? and activityId = ? and bagId = ?", appId, userId, str3, str);
                return;
            }
            ActivityBagDBEntity activityBagDBEntity = new ActivityBagDBEntity();
            activityBagDBEntity.appId = appId;
            activityBagDBEntity.userId = userId;
            activityBagDBEntity.activityId = str3;
            activityBagDBEntity.bagId = str;
            activityBagDBEntity.createTime = currentTimeMillis;
            activityBagDBEntity.buyStatus = str2;
            activityBagDBEntity.save();
        }
    }

    public static ActivityBagDBEntity getActivityBagItemInfoById(String str, String str2) {
        return (ActivityBagDBEntity) selectOneItem(ActivityBagDBEntity.class, "appId = ? and userId = ? and activityId = ? and bagId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str, str2);
    }

    public static int deleteActivityBagItemInfoById(String str) {
        return deleteAllWithCondition(ActivityBagDBEntity.class, "appId = ? and userId = ? and activityId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str);
    }

    public static List<ActivityBagDBEntity> getActivityBagList(String str) {
        return findAllWithWhere(ActivityBagDBEntity.class, "appId = ? and userId = ? and activityId = ?", UserInfoManager.getInstance().getAppId(), UserInfoManager.getInstance().getUserId(), str);
    }

    public static void deleteActivityBagInfo() {
        List<ActivityDBEntity> activityList = getActivityList();
        if (!(activityList == null || activityList.isEmpty())) {
            for (ActivityDBEntity activityDBEntity : activityList) {
                if (System.currentTimeMillis() > activityDBEntity.endTime * 1000) {
                    deleteActivityItemInfoById(activityDBEntity.activityId);
                    deleteActivityBagItemInfoById(activityDBEntity.activityId);
                }
            }
        }
    }
}

