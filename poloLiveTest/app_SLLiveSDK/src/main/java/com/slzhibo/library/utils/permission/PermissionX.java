package com.slzhibo.library.utils.permission;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.content.ContextCompat;

public class PermissionX {
    public static PermissionCollection init(FragmentActivity fragmentActivity) {
        return new PermissionCollection(fragmentActivity);
    }

    public static boolean isGranted(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }
}
