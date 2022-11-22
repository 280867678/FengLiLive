package com.slzhibo.library.http;

/* loaded from: classes6.dex */
public interface ResultCallBack<T> {
    void onError(int i, String str);

    void onSuccess(T t);
}
