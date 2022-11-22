package com.blmvl.blvl.util;

//package p067e.p130f.p131a.p135d;

import android.content.Context;
import android.text.TextUtils;
import com.comod.baselib.BaseAppContext;
//import p067e.p130f.p131a.p136e.CustomToast;

/* renamed from: e.f.a.d.m0 */
/* loaded from: classes.dex */
public class ToastUtil {
    /* renamed from: a */
    public static void m9104a(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m9103b(BaseAppContext.m20167a(), charSequence);
        }
    }

    /* renamed from: b */
    public static void m9103b(Context context, CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        CustomToast.makeText(context, charSequence, 0).show();
    }

    /* renamed from: c */
    public static void m9102c(Context context, CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m9103b(context, charSequence);
        }
    }

    /* renamed from: a */
    public static void m9106a(Context context, int i) {
        m9102c(context, context.getResources().getString(i));
    }

    /* renamed from: a */
    public static void m9105a(Context context, CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        CustomToast.m9065a(context, charSequence, 0).show();
    }
}

