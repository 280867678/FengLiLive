package com.slzhibo.library.model;

import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;
//import com.example.boluouitest2zhibo.R;
//import com.slzhibo.library.R;
import com.slzhibo.library.R;
import com.slzhibo.library.utils.SystemUtils;

//import org.apache.commons.cli.HelpFormatter;

/* loaded from: classes8.dex */
public class MedalEntity {
    public String desc;
    public long endTime;
    public String markId;
    public String markUrl;
    public int pos = -1;

    public long getEndTime() {
        return this.endTime * 1000;
    }

    public String getEndTimeStr() {
        String fitTimeSpanByNow = TimeUtils.getFitTimeSpanByNow(getEndTime(), 1);
        if (TextUtils.isEmpty(fitTimeSpanByNow)) {
            return SystemUtils.getResString(R.string.fq_1_day);
        }
        if (fitTimeSpanByNow.contains("-")) {
            return null;
        }
        return fitTimeSpanByNow;
    }
}
