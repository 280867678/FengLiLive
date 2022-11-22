package com.slzhibo.library.model.db;



import android.text.TextUtils;
import com.blankj.utilcode.util.TimeUtils;

/* loaded from: classes8.dex */
public class ActivityDBEntity extends BaseDBEntity {
    public String activityId;
    public String activityName;
    public String appId;
    public long createTime;
    public long endTime;
    public String frequency;
    public long startTime;
    public String todayRemindStatus;
    public String userId;

    public boolean isTodayNoRemind() {
        return TextUtils.equals("1", this.todayRemindStatus) && TimeUtils.isToday(this.createTime);
    }

    public boolean isShowActivityDialog() {
        if (!TextUtils.equals("2", this.frequency) || !TimeUtils.isToday(this.createTime)) {
            return !isTodayNoRemind();
        }
        return false;
    }
}

