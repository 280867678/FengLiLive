package com.blmvl.blvl.util;

//package p067e.p130f.p131a.p135d;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* renamed from: e.f.a.d.p0 */
/* loaded from: classes.dex */
public class WebViewUtil {
    /* renamed from: a */
    public static void m9087a(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        context.startActivity(intent);
    }
}

