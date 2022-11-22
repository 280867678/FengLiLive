package com.slzhibo.library.utils.litepal.crud.async;

import com.slzhibo.library.utils.litepal.crud.callback.SaveCallback;

/* loaded from: classes12.dex */
public class SaveExecutor extends AsyncExecutor {
    private SaveCallback cb;

    public void listen(SaveCallback saveCallback) {
        this.cb = saveCallback;
        execute();
    }

    public SaveCallback getListener() {
        return this.cb;
    }
}
