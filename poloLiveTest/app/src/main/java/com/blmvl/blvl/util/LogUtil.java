package com.blmvl.blvl.util;

//package p067e.p103c.p104a.p113k;

import android.util.Log;

import com.blmvl.blvl.AppContext;
import com.blmvl.blvl.httpUtil.VersionUtil;
//import com.flurry.android.FlurryAgent;
//import com.slzhibo.library.utils.litepal.parser.LitePalParser;
import java.util.HashMap;
import java.util.Map;
//import p067e.p130f.p131a.p135d.VersionUtil;

/* renamed from: e.c.a.k.v */
/* loaded from: classes.dex */
public class LogUtil {
    /* renamed from: a */
    public static void m9410a(String str) {
        Log.e("LogUtil:::","FlurryAgent.logEvent::"+str);
//        FlurryAgent.logEvent(str, m9411a());
    }
//
//    /* renamed from: a */
//    public static void m9409a(String str, int i) {
//        Map<String, String> a = m9411a();
//        a.put("vid", String.valueOf(i));
//        FlurryAgent.logEvent(str, a);
//    }
//
//    /* renamed from: a */
//    public static void m9408a(String str, String str2) {
//        Map<String, String> a = m9411a();
//        a.put("from", str2);
//        FlurryAgent.logEvent(str, a);
//    }
//
//    /* renamed from: a */
//    public static void m9407a(String str, String str2, String str3, String str4) {
//        Map<String, String> a = m9411a();
//        a.put("from", str2);
//        a.put("pt", str3);
//        a.put("pm", str4);
//        FlurryAgent.logEvent(str, a);
//    }

    /* renamed from: a */
    public static Map<String, String> m9411a() {
        HashMap hashMap = new HashMap();
        hashMap.put("oauth_id", SpUtil.m9573D().m9535i());
        hashMap.put("channel", "blvl");
        hashMap.put("time", String.valueOf(System.currentTimeMillis()));
        hashMap.put("version", VersionUtil.m9100a(AppContext.m21299a()));
        return hashMap;
    }
}

