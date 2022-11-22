package com.slzhibo.library.model;

import com.blankj.utilcode.util.TimeUtils;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.DateUtils;

/* loaded from: classes8.dex */
public class MyClanEntity {
    public String anchorName;
    public long lastLiveStartTime = 0;
    public String liveId;
    public int liveStatus;
    public String liveStatusName;
    public long maxPopularity;

    public String formatLiveTime() {
        long j = this.lastLiveStartTime;
        return j == 0 ? "" : TimeUtils.millis2String(j * 1000, DateUtils.C_TIME_PATTON_DEFAULT_2);
    }

    public String getMaxPopularity() {
        return AppUtils.formatLivePopularityCount(this.maxPopularity);
    }
}
