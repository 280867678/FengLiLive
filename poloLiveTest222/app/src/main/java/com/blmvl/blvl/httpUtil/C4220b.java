package com.blmvl.blvl.httpUtil;

//package p067e.p103c.p104a.p111i;

import com.blmvl.blvl.L;
import com.blmvl.blvl.bean.ResponseJsonBean;
import com.blmvl.blvl.util.SpUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import okhttp3.OkHttpClient;
//import p067e.p103c.p104a.p113k.SpUtil;
//import p067e.p130f.p131a.p135d.L;

/* compiled from: HttpClient.java */
/* renamed from: e.c.a.i.b */
/* loaded from: classes.dex */
public class C4220b {

    /* renamed from: b */
    public static C4220b f12973b;

    /* renamed from: a */
    public OkHttpClient f12974a;

    /* renamed from: b */
    public static C4220b m9794b() {
        if (f12973b == null) {
            synchronized (C4220b.class) {
                if (f12973b == null) {
                    f12973b = new C4220b();
                }
            }
        }
        return f12973b;
    }

    /* renamed from: a */
    public final String m9798a() {
        return SpUtil.m9573D().m9542f();
    }

    /* renamed from: a */
    public GetRequest<ResponseJsonBean> m9796a(String str, String str2) {
        return m9795a(m9798a(), str, str2);
    }

    /* renamed from: a */
    public GetRequest<ResponseJsonBean> m9795a(String str, String str2, String str3) {
        L.m9079a("base_url--->" + str);
        return (GetRequest) OkGo.get(str + str2).tag(str3);
    }

    /* renamed from: a */
    public void m9797a(String str) {
        OkGo.cancelTag(this.f12974a, str);
    }

    /* renamed from: b */
    public PostRequest<ResponseJsonBean> m9793b(String str, String str2) {
        return m9792b(m9798a(), str, str2);
    }

    /* renamed from: b */
    public PostRequest<ResponseJsonBean> m9792b(String str, String str2, String str3) {
        L.m9079a("base_url--->" + str + str2);
        return (PostRequest) OkGo.post(str + str2).tag(str3);
    }
}

