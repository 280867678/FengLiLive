package com.slzhibo.library.utils.litepal.crud.async;

import com.slzhibo.library.utils.litepal.crud.callback.AverageCallback;

/* loaded from: classes12.dex */
public class AverageExecutor extends AsyncExecutor {
    private AverageCallback cb;

    public void listen(AverageCallback averageCallback) {
        this.cb = averageCallback;
        execute();
    }

    public AverageCallback getListener() {
        return this.cb;
    }
}
