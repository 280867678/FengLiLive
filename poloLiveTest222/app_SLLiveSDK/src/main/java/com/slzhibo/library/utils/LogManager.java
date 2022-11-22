package com.slzhibo.library.utils;



import android.util.Log;

/* loaded from: classes6.dex */
public class LogManager {
    public static final String TAG_S = "debug_s";
    private static final String TAG_T = "debug_t";
    static boolean isDebug = false;

    /* renamed from: s */
    public static void s(String str) {
        if (isDebug) {
            Log.i(TAG_S, str);
        }
    }

    /* renamed from: t */
    public static void t(String str) {
        if (isDebug) {
            Log.i(TAG_T, str);
        }
    }
}

