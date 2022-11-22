package com.slzhibo.library.utils.litepal.crud.async;

import com.slzhibo.library.utils.litepal.crud.callback.FindMultiCallback;

/* loaded from: classes12.dex */
public class FindMultiExecutor<T> extends AsyncExecutor {
    private FindMultiCallback<T> cb;

    public void listen(FindMultiCallback<T> findMultiCallback) {
        this.cb = findMultiCallback;
        execute();
    }

    public FindMultiCallback<T> getListener() {
        return this.cb;
    }
}
