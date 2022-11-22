package com.slzhibo.library.analytics.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
//import com.eclipsesource.p029v8.Platform;
import com.slzhibo.library.analytics.AopConstants;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.LogConstants;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class SASystemUtils {
    public static String booleanToString(boolean z) {
        return z ? "1" : "0";
    }

    public static String getLibVersion() {
        return "3.6.0";
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    private SASystemUtils() {
    }

    public static Map<String, Object> getDeviceInfo(Context context) {
        int i;
        int i2;
        HashMap hashMap = new HashMap();
        hashMap.put("platformType", "ANDROID");
        hashMap.put("os", "ANDROID");
        String str = Build.VERSION.RELEASE;
        if (str == null) {
            str = "UNKNOWN";
        }
        hashMap.put("osVersion", str);
        hashMap.put(LogConstants.MANUFACTURER, SensorsDataUtils.getManufacturer());
        if (TextUtils.isEmpty(Build.MODEL)) {
            hashMap.put("model", "UNKNOWN");
        } else {
            hashMap.put("model", Build.MODEL.trim());
        }
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int rotation = defaultDisplay.getRotation();
            Point point = new Point();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
                i2 = point.x;
                i = point.y;
            } else if (Build.VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
                i2 = point.x;
                i = point.y;
            } else {
                int width = defaultDisplay.getWidth();
                i = defaultDisplay.getHeight();
                i2 = width;
            }
            hashMap.put("screenWidth", Integer.valueOf(SensorsDataUtils.getNaturalWidth(rotation, i2, i)));
            hashMap.put("screenHeight", Integer.valueOf(SensorsDataUtils.getNaturalHeight(rotation, i2, i)));
        } catch (Exception unused) {
            if (context.getResources() != null) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                hashMap.put("screenWidth", Integer.valueOf(displayMetrics.widthPixels));
                hashMap.put("screenHeight", Integer.valueOf(displayMetrics.heightPixels));
            }
        }
        String carrier = SensorsDataUtils.getCarrier(context);
        if (!TextUtils.isEmpty(carrier)) {
            hashMap.put("carrier", carrier);
        }
        hashMap.put(LogConstants.DEVICE_ID, DeviceUtils.getAndroidID());
        return hashMap;
    }

    public static int isFirstDay() {
        long j = SPUtils.getInstance().getLong(AopConstants.IS_FIRST_DAY);
        if (j < 0) {
            SPUtils.getInstance().put(AopConstants.IS_FIRST_DAY, System.currentTimeMillis());
            return 1;
        } else if (TextUtils.equals(DateFormatUtils.formatTime(j, "yyyy-MM-dd"), DateFormatUtils.formatTime(System.currentTimeMillis(), "yyyy-MM-dd"))) {
            return 0;
        } else {
            SPUtils.getInstance().put(AopConstants.IS_FIRST_DAY, System.currentTimeMillis());
            return 1;
        }
    }

    public static String duration(long j, long j2) {
        long j3 = j2 - j;
        try {
            if (j3 < 0 || j3 > DateUtils.ONE_DAY_MILLIONS) {
                return String.valueOf(0);
            }
            float f = ((float) j3) / 1000.0f;
            return f < 0.0f ? String.valueOf(0) : String.format(Locale.CHINA, "%.3f", Float.valueOf(f));
        } catch (Exception unused) {
            return String.valueOf(0);
        }
    }
}
