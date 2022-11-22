package com.slzhibo.library.utils.litepal.crud.async;

import com.slzhibo.library.utils.litepal.crud.callback.UpdateOrDeleteCallback;

/* loaded from: classes12.dex */
public class UpdateOrDeleteExecutor extends AsyncExecutor {
    private UpdateOrDeleteCallback cb;

    public void listen(UpdateOrDeleteCallback updateOrDeleteCallback) {
        this.cb = updateOrDeleteCallback;
        execute();
    }

    public UpdateOrDeleteCallback getListener() {
        return this.cb;
    }
}
