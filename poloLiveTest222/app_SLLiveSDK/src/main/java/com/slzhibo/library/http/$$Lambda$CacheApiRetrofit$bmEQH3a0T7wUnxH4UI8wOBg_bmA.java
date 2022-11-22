package com.slzhibo.library.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: lambda */
/* renamed from: com.slzhibo.library.http.-$$Lambda$CacheApiRetrofit$bmEQH3a0T7wUnxH4UI8wOBg_bmA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$CacheApiRetrofit$bmEQH3a0T7wUnxH4UI8wOBg_bmA implements HostnameVerifier {
    public static final /* synthetic */ $$Lambda$CacheApiRetrofit$bmEQH3a0T7wUnxH4UI8wOBg_bmA INSTANCE = new $$Lambda$CacheApiRetrofit$bmEQH3a0T7wUnxH4UI8wOBg_bmA();

    private /* synthetic */ $$Lambda$CacheApiRetrofit$bmEQH3a0T7wUnxH4UI8wOBg_bmA() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return CacheApiRetrofit.lambda$initApiService$0(str, sSLSession);
    }
}
