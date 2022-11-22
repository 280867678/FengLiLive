package com.slzhibo.library.model;

import com.blankj.utilcode.util.TimeUtils;
//import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.NumberUtils;

/* loaded from: classes8.dex */
public class RelationLastLiveEntity {
    public String createTime;
    public String relationStartLiveId;
    public String relationStartLiveTag;
    public String relationStartLiveTopic;

    public String getCreateTime() {
        return TimeUtils.millis2String(NumberUtils.string2long(this.createTime) * 1000, "yyyy-MM-dd HH:mm");
    }
}
