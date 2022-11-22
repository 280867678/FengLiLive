package com.slzhibo.library.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: lambda */
/* renamed from: com.slzhibo.library.http.-$$Lambda$StatisticsApiRetrofit$6xUMh0QTYm6XTv874NZ4hx1jDfg  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$StatisticsApiRetrofit$6xUMh0QTYm6XTv874NZ4hx1jDfg implements HostnameVerifier {
    public static final /* synthetic */ $$Lambda$StatisticsApiRetrofit$6xUMh0QTYm6XTv874NZ4hx1jDfg INSTANCE = new $$Lambda$StatisticsApiRetrofit$6xUMh0QTYm6XTv874NZ4hx1jDfg();

    private /* synthetic */ $$Lambda$StatisticsApiRetrofit$6xUMh0QTYm6XTv874NZ4hx1jDfg() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return StatisticsApiRetrofit.lambda$new$0(str, sSLSession);
    }
}
