package com.slzhibo.library.model;



import android.text.TextUtils;
import com.blankj.utilcode.util.RegexUtils;
import java.io.Serializable;

/* loaded from: classes8.dex */
public class ActivityListEntity implements Serializable {
    public long endTime;
    public String frequency;
    public String id;
    public String name;
    public String remark;
    public String repetition;
    public String scope;
    public long startTime;
    public String triggerArea;
    public String urlLink;

    public boolean isHomeActivityAd() {
        return !TextUtils.isEmpty(this.triggerArea) && this.triggerArea.contains("1");
    }

    public boolean isLiveActivityAd() {
        return !TextUtils.isEmpty(this.triggerArea) && this.triggerArea.contains("2");
    }

    public boolean isCorrectLink() {
        return !TextUtils.isEmpty(this.urlLink) && RegexUtils.isURL(this.urlLink);
    }
}

