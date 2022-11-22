package com.slzhibo.library.model;

import android.text.TextUtils;

import java.io.Serializable;

/* loaded from: classes8.dex */
public class MyLiveEntity implements Serializable {
    public String refuseCall;
    public String role = "";
    public boolean openCar = true;
    public String expGrade = "";
    public int nobilityType = 0;

    public boolean isRefuseCall() {
        return TextUtils.equals("1", this.refuseCall);
    }
}
