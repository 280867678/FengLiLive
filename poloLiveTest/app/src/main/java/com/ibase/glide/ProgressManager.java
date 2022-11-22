package com.ibase.glide;

//package p067e.p260n.p261a.p262i;

import android.text.TextUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//import p067e.p260n.p261a.p262i.ProgressResponseBody;

/* renamed from: e.n.a.i.e */
/* loaded from: classes.dex */
public class ProgressManager {

    /* renamed from: b */
    public static OkHttpClient f17171b;

    /* renamed from: a */
    public static Map<String, OnProgressListener> f17170a = Collections.synchronizedMap(new HashMap());

    /* renamed from: c */
    public static final ProgressResponseBody.AbstractC4720b f17172c = C4716a.f17167a;

    /* renamed from: a */
    public static OkHttpClient m4945a() {
        if (f17171b == null) {
            f17171b = new OkHttpClient.Builder().addNetworkInterceptor(C4717b.f17168a).build();
        }
        return f17171b;
    }

    /* renamed from: b */
    public static void m4941b(String str) {
        if (!TextUtils.isEmpty(str)) {
            f17170a.remove(str);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ Response m4942a(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        return proceed.newBuilder().body(new ProgressResponseBody(request.url().toString(), f17172c, proceed.body())).build();
    }

    /* renamed from: a */
    public static /* synthetic */ void m4943a(String str, long j, long j2) {
        OnProgressListener a = m4944a(str);
        if (a != null) {
            int i = (int) (((((float) j) * 1.0f) / ((float) j2)) * 100.0f);
            boolean z = i >= 100;
            a.m4946a(z, i, j, j2);
            if (z) {
                m4941b(str);
            }
        }
    }

    /* renamed from: a */
    public static OnProgressListener m4944a(String str) {
        Map<String, OnProgressListener> map;
        OnProgressListener dVar;
        if (TextUtils.isEmpty(str) || (map = f17170a) == null || map.size() == 0 || (dVar = f17170a.get(str)) == null) {
            return null;
        }
        return dVar;
    }
}

