package com.slzhibo.library.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: lambda */
/* renamed from: com.slzhibo.library.http.-$$Lambda$EventConfigRetrofit$4mwsI8ip3Kpngitt53RPIVkC80Q  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$EventConfigRetrofit$4mwsI8ip3Kpngitt53RPIVkC80Q implements HostnameVerifier {
    public static final /* synthetic */ $$Lambda$EventConfigRetrofit$4mwsI8ip3Kpngitt53RPIVkC80Q INSTANCE = new $$Lambda$EventConfigRetrofit$4mwsI8ip3Kpngitt53RPIVkC80Q();

    private /* synthetic */ $$Lambda$EventConfigRetrofit$4mwsI8ip3Kpngitt53RPIVkC80Q() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return EventConfigRetrofit.lambda$initApiService$0(str, sSLSession);
    }
}
