package com.slzhibo.library.model;

import android.text.TextUtils;

/* loaded from: classes8.dex */
public class MyCarEntity extends CarEntity {
    public String isUsed;
    public String remainDay;
    public String uniqueId;
    public String userId;

    public boolean isEquipage() {
        return TextUtils.equals(this.isUsed, "1");
    }
}
