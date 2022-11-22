package com.slzhibo.library.utils.permission;

import android.os.Build;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.slzhibo.library.utils.permission.request.PermissionBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PermissionCollection {
    private final FragmentActivity activity;
    private Fragment fragment;

    public PermissionCollection(FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
    }

    public PermissionBuilder permissions(String... strArr) {
        return permissions(new ArrayList(Arrays.asList(strArr)));
    }

    public PermissionBuilder permissions(List<String> list) {
        boolean z;
        int i;
        HashSet hashSet = new HashSet(list);
        HashSet hashSet2 = new HashSet();
        if (hashSet.contains("android.permission.ACCESS_BACKGROUND_LOCATION")) {
            int i2 = Build.VERSION.SDK_INT;
            Fragment fragment2 = this.fragment;
            if (fragment2 == null || fragment2.getContext() == null) {
                i = this.activity.getApplicationInfo().targetSdkVersion;
            } else {
                i = this.fragment.getContext().getApplicationInfo().targetSdkVersion;
            }
            if (i2 >= 30 && i >= 30) {
                hashSet.remove("android.permission.ACCESS_BACKGROUND_LOCATION");
                z = true;
                return new PermissionBuilder(this.activity, this.fragment, hashSet, z, hashSet2);
            } else if (i2 < 29) {
                hashSet.remove("android.permission.ACCESS_BACKGROUND_LOCATION");
                hashSet2.add("android.permission.ACCESS_BACKGROUND_LOCATION");
            }
        }
        z = false;
        return new PermissionBuilder(this.activity, this.fragment, hashSet, z, hashSet2);
    }
}
