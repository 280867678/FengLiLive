package com.slzhibo.library.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: lambda */
/* renamed from: com.slzhibo.library.http.-$$Lambda$EventReportRetrofit$n-VCqH62vNWLUIQerIsTvDVV-sY  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$EventReportRetrofit$nVCqH62vNWLUIQerIsTvDVVsY implements HostnameVerifier {
    public static final /* synthetic */ $$Lambda$EventReportRetrofit$nVCqH62vNWLUIQerIsTvDVVsY INSTANCE = new $$Lambda$EventReportRetrofit$nVCqH62vNWLUIQerIsTvDVVsY();

    private /* synthetic */ $$Lambda$EventReportRetrofit$nVCqH62vNWLUIQerIsTvDVVsY() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return EventReportRetrofit.lambda$initApiService$0(str, sSLSession);
    }
}
