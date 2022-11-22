package com.ibase.glide;

//package p067e.p260n.p261a.p262i;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: lambda */
/* renamed from: e.n.a.i.b */
/* loaded from: classes.dex */
public final /* synthetic */ class C4717b implements Interceptor {

    /* renamed from: a */
    public static final /* synthetic */ C4717b f17168a = new C4717b();

    private /* synthetic */ C4717b() {
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) throws IOException {
        return ProgressManager.m4942a(chain);
    }
}

