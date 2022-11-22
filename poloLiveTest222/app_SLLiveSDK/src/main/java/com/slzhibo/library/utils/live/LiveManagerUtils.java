package com.slzhibo.library.utils.live;

import android.text.TextUtils;

import com.slzhibo.library.model.LiveEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class LiveManagerUtils {
    private String currentLiveId;
    private LiveEntity currentLiveItem;
    private List<LiveEntity> liveList;
    private Map<String, Boolean> userConsumptionMap;

    private LiveManagerUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final LiveManagerUtils INSTANCE = new LiveManagerUtils();

        private SingletonHolder() {
        }
    }

    public static LiveManagerUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initCurrentLiveItemInfo(String str, LiveEntity liveEntity) {
        this.currentLiveId = str;
        this.currentLiveItem = liveEntity;
    }

    public ArrayList<LiveEntity> getLiveList() {
        List<LiveEntity> list = this.liveList;
        return list == null ? new ArrayList<>() : (ArrayList) list;
    }

    public void setLiveList(List<LiveEntity> list) {
        this.liveList = list;
    }

    public String getCurrentLiveId() {
        return this.currentLiveId;
    }

    public void setCurrentLiveId(String str) {
        this.currentLiveId = str;
    }

    public LiveEntity getCurrentLiveItem() {
        return this.currentLiveItem;
    }

    public int getCurrentLivePosition() {
        if (TextUtils.isEmpty(this.currentLiveId)) {
            return 0;
        }
        for (int i = 0; i < getLiveList().size(); i++) {
            if (TextUtils.equals(this.currentLiveId, getLiveList().get(i).liveId)) {
                return i;
            }
        }
        return 0;
    }

    public void addUserConsumptionMap(String str, String str2, boolean z) {
        if (this.userConsumptionMap == null) {
            this.userConsumptionMap = new HashMap();
        }
        this.userConsumptionMap.put(formatUserConsumptionKey(str, str2), Boolean.valueOf(z));
    }

    public boolean isUserConsumptionFlag(String str, String str2) {
        try {
            if (this.userConsumptionMap != null) {
                return this.userConsumptionMap.get(formatUserConsumptionKey(str, str2)).booleanValue();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private String formatUserConsumptionKey(String str, String str2) {
        return str + "_" + str2;
    }
}
