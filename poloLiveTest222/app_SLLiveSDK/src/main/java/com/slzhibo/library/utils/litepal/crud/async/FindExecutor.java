package com.slzhibo.library.utils.litepal.crud.async;

import com.slzhibo.library.utils.litepal.crud.callback.FindCallback;

/* loaded from: classes12.dex */
public class FindExecutor<T> extends AsyncExecutor {
    private FindCallback<T> cb;

    public void listen(FindCallback<T> findCallback) {
        this.cb = findCallback;
        execute();
    }

    public FindCallback<T> getListener() {
        return this.cb;
    }
}
