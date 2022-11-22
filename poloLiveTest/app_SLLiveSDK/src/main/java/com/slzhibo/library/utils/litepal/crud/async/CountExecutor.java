package com.slzhibo.library.utils.litepal.crud.async;

import com.slzhibo.library.utils.litepal.crud.callback.CountCallback;

/* loaded from: classes12.dex */
public class CountExecutor extends AsyncExecutor {
    private CountCallback cb;

    public void listen(CountCallback countCallback) {
        this.cb = countCallback;
        execute();
    }

    public CountCallback getListener() {
        return this.cb;
    }
}
