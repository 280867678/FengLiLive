package com.slzhibo.library;



import com.slzhibo.library.http.ApiRetrofit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* compiled from: lambda */
/* renamed from: e.t.a.g.a */
/* loaded from: classes2.dex */
public final /* synthetic */ class C4833a implements HostnameVerifier {

    /* renamed from: a */
    public static final /* synthetic */ C4833a f17645a = new C4833a();

    private /* synthetic */ C4833a() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return ApiRetrofit.m18390a(str, sSLSession);
    }
}

