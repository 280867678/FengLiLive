package com.comod.baselib.view.banner;

import android.content.Context;
import android.util.TypedValue;

/* renamed from: e.f.a.e.b.e */
/* loaded from: classes.dex */
public class XBannerUtils {
    /* renamed from: a */
    public static int m9061a(Context context, float f) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, f, context.getResources().getDisplayMetrics());
    }

    /* renamed from: b */
    public static int m9060b(Context context, float f) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, f, context.getResources().getDisplayMetrics());
    }

    /* renamed from: a */
    public static int m9062a(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
