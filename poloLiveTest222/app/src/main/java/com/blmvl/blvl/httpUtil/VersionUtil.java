package com.blmvl.blvl.httpUtil;

//package p067e.p130f.p131a.p135d;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

/* renamed from: e.f.a.d.n0 */
/* loaded from: classes.dex */
public class VersionUtil {

    /* renamed from: a */
    public static String f13202a;

    /* renamed from: a */
    public static String m9100a(Context context) {
        if (TextUtils.isEmpty(f13202a)) {
            try {
                f13202a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return f13202a;
    }

    /* renamed from: b */
    public static boolean m9098b(Context context, String str) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            if (installedPackages.get(i).packageName.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m9097c(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String a = m9100a(context);
        if (TextUtils.isEmpty(a)) {
            return true;
        }
        return a.equals(str);
    }

    /* renamed from: a */
    public static void m9099a(Context context, String str) {
        if (m9098b(context, str)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.DELETE");
            intent.setData(Uri.parse("package:" + str));
            context.startActivity(intent);
        }
    }
}

