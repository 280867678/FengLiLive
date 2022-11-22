package com.slzhibo.library.utils;



import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.CacheDiskUtils;
import com.blankj.utilcode.util.LogUtils;
//import com.google.android.exoplayer2.util.Log;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.http.CacheApiRetrofit;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.ActivityListEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.ComponentsEntity;
import com.slzhibo.library.model.cache.BannerCacheEntity;
import com.slzhibo.library.model.cache.ComponentsCacheEntity;
import com.slzhibo.library.model.cache.VersionCacheEntity;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class CacheUtils {
    private static String TAG = "CacheUtils";
    public static long lastCacheVersionTimeMillis;

    private CacheUtils() {
    }

    public static void updateCacheVersion() {
        long j = lastCacheVersionTimeMillis;
        if (j == 0 || ((System.currentTimeMillis() - j) % DateUtils.ONE_HOUR_MILLIONS) / 60000 >= 10) {
            LogUtils.iTag(TAG, "重新请求缓存版本接口");
            CacheApiRetrofit.getInstance().getApiService().getCacheVersionService(UserInfoManager.getInstance().getAppId()).map(new ServerResultFunction<VersionCacheEntity>() { // from class: com.slzhibo.library.utils.CacheUtils.3
            }).onErrorResumeNext(new HttpResultFunction<VersionCacheEntity>() { // from class: com.slzhibo.library.utils.CacheUtils.2
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<VersionCacheEntity>() { // from class: com.slzhibo.library.utils.CacheUtils.1
                public void accept(VersionCacheEntity versionCacheEntity) {
                    CacheUtils.lastCacheVersionTimeMillis = System.currentTimeMillis();
                    VersionCacheEntity versionCacheEntity2 = (VersionCacheEntity) CacheDiskUtils.getInstance().getParcelable(ConstantUtils.CACHE_DISK_VERSION_KEY, VersionCacheEntity.CREATOR);
                    CacheDiskUtils.getInstance().put(ConstantUtils.CACHE_DISK_VERSION_KEY, versionCacheEntity);
                    LogUtils.iTag(CacheUtils.TAG, versionCacheEntity.toString());
                    if (versionCacheEntity2 != null && versionCacheEntity != null && !TextUtils.equals(versionCacheEntity2.getVersion(), versionCacheEntity.getVersion())) {
                        CacheUtils.cleanCacheDisk(ConstantUtils.CACHE_DISK_NOTICE_KEY, CacheUtils.getBannerTypeByCacheDisk("1"), CacheUtils.getBannerTypeByCacheDisk("5"), CacheUtils.getBannerTypeByCacheDisk("2"), CacheUtils.getBannerTypeByCacheDisk("3"), CacheUtils.getBannerTypeByCacheDisk("7"));
                    }
                }
            });
        }
    }

    public static void saveLocalComponentsCache(List<ComponentsEntity> list) {
        ComponentsCacheEntity componentsCacheEntity = new ComponentsCacheEntity();
        componentsCacheEntity.setComponentsList(list);
        CacheDiskUtils.getInstance().put(ConstantUtils.CACHE_DISK_COMPONENTS_KEY, componentsCacheEntity);
    }

    public static boolean isLocalCacheComponents(String str) {
        return getLocalCacheComponentsByGameId(str) != null;
    }

    public static ComponentsEntity getLocalCacheComponentsById(String str) {
        List<ComponentsEntity> localCacheComponentsList;
        if (TextUtils.isEmpty(str) || (localCacheComponentsList = getLocalCacheComponentsList()) == null || localCacheComponentsList.isEmpty()) {
            return null;
        }
        for (ComponentsEntity componentsEntity : localCacheComponentsList) {
            if (componentsEntity != null && TextUtils.equals(componentsEntity.id, str)) {
                return componentsEntity;
            }
        }
        return null;
    }

    public static ComponentsEntity getLocalCacheComponentsByGameId(String str) {
        List<ComponentsEntity> localCacheComponentsList;
        if (TextUtils.isEmpty(str) || (localCacheComponentsList = getLocalCacheComponentsList()) == null || localCacheComponentsList.isEmpty()) {
            return null;
        }
        for (ComponentsEntity componentsEntity : localCacheComponentsList) {
            if (componentsEntity != null && TextUtils.equals(componentsEntity.gameId, str)) {
                return componentsEntity;
            }
        }
        return null;
    }

    public static ComponentsEntity getLocalCacheRecommendComponents(String str) {
        ComponentsEntity localCacheComponentsByGameId = getLocalCacheComponentsByGameId(str);
        if (localCacheComponentsByGameId != null) {
            return localCacheComponentsByGameId;
        }
        List<ComponentsEntity> localCacheComponentsList = getLocalCacheComponentsList();
        if (localCacheComponentsList == null || localCacheComponentsList.isEmpty()) {
            return null;
        }
        for (ComponentsEntity componentsEntity : localCacheComponentsList) {
            if (componentsEntity != null && componentsEntity.isRecommendComponents()) {
                return componentsEntity;
            }
        }
        return null;
    }

    public static List<ComponentsEntity> getLocalCacheComponentsList() {
        ComponentsCacheEntity componentsCacheEntity = (ComponentsCacheEntity) CacheDiskUtils.getInstance().getParcelable(ConstantUtils.CACHE_DISK_COMPONENTS_KEY, ComponentsCacheEntity.CREATOR);
        if (componentsCacheEntity == null) {
            return null;
        }
        return componentsCacheEntity.getComponentsList();
    }

    public static String getBannerTypeByCacheDisk(String str) {
        return ConstantUtils.CACHE_DISK_BANNER_KEY + str;
    }

    public static boolean isBannerListByCacheDisk(String str) {
        List<BannerEntity> bannerListByCacheDisk = getBannerListByCacheDisk(str);
        return SLLiveSDK.getSingleton().isLiveAdvChannel() && bannerListByCacheDisk != null && !bannerListByCacheDisk.isEmpty();
    }
//
    public static void saveBannerListByCacheDisk(String str, List<BannerEntity> list) {
        if (SLLiveSDK.getSingleton().isLiveAdvChannel() && list != null && !list.isEmpty()) {
            BannerCacheEntity bannerCacheEntity = new BannerCacheEntity();
            bannerCacheEntity.setDataList(list);
            CacheDiskUtils.getInstance().put(getBannerTypeByCacheDisk(str), bannerCacheEntity);
        }
    }

    public static List<BannerEntity> getBannerListByCacheDisk(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BannerCacheEntity bannerCacheEntity = (BannerCacheEntity) CacheDiskUtils.getInstance().getParcelable(getBannerTypeByCacheDisk(str), BannerCacheEntity.CREATOR);
        if (bannerCacheEntity == null) {
            return null;
        }
        return bannerCacheEntity.getDataList();
    }

    public static boolean isLiveNoticeByCacheDisk() {
        return SLLiveSDK.getSingleton().isLiveAdvChannel() && getLiveNoticeByCacheDisk() != null;
    }

    public static void saveLiveNoticeByCacheDisk(BannerEntity bannerEntity) {
        if (SLLiveSDK.getSingleton().isLiveAdvChannel()) {
            CacheDiskUtils.getInstance().put(ConstantUtils.CACHE_DISK_NOTICE_KEY, bannerEntity);
        }
    }

    public static BannerEntity getLiveNoticeByCacheDisk() {
        return (BannerEntity) CacheDiskUtils.getInstance().getParcelable(ConstantUtils.CACHE_DISK_NOTICE_KEY, BannerEntity.CREATOR);
    }

    public static void saveOperateActivityList(ArrayList<ActivityListEntity> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            cleanCacheDisk(ConstantUtils.GIFT_BAG_KEY);
        } else {
            CacheDiskUtils.getInstance().put(ConstantUtils.GIFT_BAG_KEY, arrayList);
        }
    }

    public static ArrayList<ActivityListEntity> getOperateActivityList() {
        Object serializable = CacheDiskUtils.getInstance().getSerializable(ConstantUtils.GIFT_BAG_KEY);
//        Log.e("CacheUtils:::166","getOperateActivityList:::"+serializable.toString());
        if(serializable == null){
            Log.e("CacheUtils:::168","getOperateActivityList:::serializable==null");
        }
        Object serializables = CacheDiskUtils.getInstance().getSerializable(ConstantUtils.GIFT_BOX_ANIM_PATH);
        if(serializables == null){
            Log.e("CacheUtils:::172","getOperateActivityList:::serializables=1111=null");
        }
        if (serializable != null && (serializable instanceof ArrayList)) {
            Log.e("CacheUtils:::169","getOperateActivityList::"+ ((ArrayList<?>) serializable).size());
            return (ArrayList) serializable;
        }
        Log.e("CacheUtils:::172","getOperateActivityList:::null");
        return null;
    }

    public static ArrayList<ActivityListEntity> getOperateActivityListByType(boolean z) {
        ArrayList<ActivityListEntity> operateActivityList = getOperateActivityList();
        if (operateActivityList == null || operateActivityList.isEmpty()) {
            Log.e("CacheUtils:::","getOperateActivityListByType:::null");
            return null;
        }
        DBUtils.deleteActivityBagInfo();
        ArrayList<ActivityListEntity> arrayList = new ArrayList<>();
        Iterator<ActivityListEntity> it2 = operateActivityList.iterator();
        while (it2.hasNext()) {
            ActivityListEntity next = it2.next();

            if (z) {
                if (next.isLiveActivityAd()) {
                    arrayList.add(next);
                    Log.e("CacheUtils:::","getOperateActivityListByType:187:nextnext:"+arrayList.size());
                }
            } else if (next.isHomeActivityAd()) {
                arrayList.add(next);
            }
        }
        Log.e("CacheUtils:::","getOperateActivityListByType:191::"+arrayList.size());
        return arrayList;
    }

    public static void cleanCacheDisk(String... strArr) {
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                CacheDiskUtils.getInstance().remove(str);
            }
        }
    }

    public static void clearAllCacheDisk() {
        CacheDiskUtils.getInstance().clear();
    }
}

