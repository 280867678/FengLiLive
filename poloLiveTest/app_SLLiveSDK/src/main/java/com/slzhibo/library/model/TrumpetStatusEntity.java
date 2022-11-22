package com.slzhibo.library.model;

import java.io.Serializable;

/* loaded from: classes8.dex */
public class TrumpetStatusEntity implements Serializable {
    public int count;
    public int status;

    public boolean isEnable() {
        return this.status == 1;
    }
}
