package com.slzhibo.library.model;

import android.text.TextUtils;

/* loaded from: classes8.dex */
public class YYNoticeEntity {
    public String content;
    public String state;

    public boolean isVerify() {
        return TextUtils.equals(this.state, "1");
    }
}
