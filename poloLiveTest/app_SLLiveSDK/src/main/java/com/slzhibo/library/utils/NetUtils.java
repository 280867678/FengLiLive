package com.slzhibo.library.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.slzhibo.library.SLLiveSDK;

/* loaded from: classes3.dex */
public class NetUtils {
    public static boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    @SuppressLint({"MissingPermission"})
    public static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager;
        Application application = SLLiveSDK.getSingleton().getApplication();
        if (application == null || (connectivityManager = (ConnectivityManager) application.getSystemService("connectivity")) == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static int getNetWorkState() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return -1;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        return activeNetworkInfo.getType() == 0 ? 0 : -1;
    }
}
