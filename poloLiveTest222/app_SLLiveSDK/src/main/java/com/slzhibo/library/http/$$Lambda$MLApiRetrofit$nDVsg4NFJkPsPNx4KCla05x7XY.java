package com.slzhibo.library.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: lambda */
/* renamed from: com.slzhibo.library.http.-$$Lambda$MLApiRetrofit$nDVsg-4NFJkPsPNx4KCla05x7XY  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$MLApiRetrofit$nDVsg4NFJkPsPNx4KCla05x7XY implements HostnameVerifier {
    public static final /* synthetic */ $$Lambda$MLApiRetrofit$nDVsg4NFJkPsPNx4KCla05x7XY INSTANCE = new $$Lambda$MLApiRetrofit$nDVsg4NFJkPsPNx4KCla05x7XY();

    private /* synthetic */ $$Lambda$MLApiRetrofit$nDVsg4NFJkPsPNx4KCla05x7XY() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return MLApiRetrofit.lambda$initApiService$0(str, sSLSession);
    }
}
