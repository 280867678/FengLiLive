package com.blmvl.blvl.util;

//package p067e.p103c.p104a.p113k;

import android.text.TextUtils;
import com.blmvl.blvl.AppContext;
import com.blmvl.blvl.R;

//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.k.n */
/* loaded from: classes.dex */
public class CustomWordUtil {
    /* renamed from: a */
    public static String m9463a(int i) {
        return AppContext.m21299a().getString(i);
    }

    /* renamed from: b */
    public static String m9461b(String str) {
        return !TextUtils.isEmpty(str) ? str : m9463a((int) R.string.signature_empty_hint);
    }

    /* renamed from: a */
    public static String m9462a(String str) {
        return !TextUtils.isEmpty(str) ? str : "";
    }
}

