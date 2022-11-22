package com.slzhibo.library.analytics.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;

//import com.seven.movie.commonres.utils.RomUtils;
//import com.slzhibo.library.C6134R;
import com.slzhibo.library.analytics.AopConstants;
import com.slzhibo.library.analytics.ScreenAutoTracker;
import com.slzhibo.library.analytics.SensorsDataAutoTrackAppViewScreenUrl;
import com.slzhibo.library.analytics.SensorsDataSDKRemoteConfig;
import com.yanzhenjie.permission.runtime.Permission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes6.dex */
public final class SensorsDataUtils {
    private static final String SHARED_PREF_CHANNEL_EVENT = "sensorsdata.channel.event";
    private static final String SHARED_PREF_DEVICE_ID_KEY = "sensorsdata.device.id";
    private static final String SHARED_PREF_EDITS_FILE = "sensorsdata";
    private static final String SHARED_PREF_REQUEST_TIME = "sensorsdata.request.time";
    private static final String SHARED_PREF_REQUEST_TIME_RANDOM = "sensorsdata.request.time.random";
    private static final String SHARED_PREF_USER_AGENT_KEY = "sensorsdata.user.agent";
    private static final String TAG = "SA.SensorsDataUtils";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";
    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final Map<String, String> sCarrierMap = new HashMap<String, String>() { // from class: com.slzhibo.library.analytics.util.SensorsDataUtils.1
        {
            put("46000", "中国移动");
            put("46002", "中国移动");
            put("46007", "中国移动");
            put("46008", "中国移动");
            put("46001", "中国联通");
            put("46006", "中国联通");
            put("46009", "中国联通");
            put("46003", "中国电信");
            put("46005", "中国电信");
            put("46011", "中国电信");
            put("46004", "中国卫通");
            put("46020", "中国铁通");
        }
    };
    private static final List<String> sManufacturer = new ArrayList<String>() { // from class: com.slzhibo.library.analytics.util.SensorsDataUtils.2
        {
            add("HUAWEI");
            add("OPPO");
            add("vivo");
        }
    };
    private static Set<String> channelEvents = new HashSet();
    private static final List<String> mInvalidAndroidId = new ArrayList<String>() { // from class: com.slzhibo.library.analytics.util.SensorsDataUtils.3
        {
            add("9774d56d682e549c");
            add("0123456789abcdef");
        }
    };

    public static int getNaturalHeight(int i, int i2, int i3) {
        return (i == 0 || i == 2) ? i3 : i2;
    }

    public static int getNaturalWidth(int i, int i2, int i3) {
        return (i == 0 || i == 2) ? i2 : i3;
    }

    public static SensorsDataSDKRemoteConfig toSDKRemoteConfig(String str) {
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = new SensorsDataSDKRemoteConfig();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                sensorsDataSDKRemoteConfig.setV(jSONObject.optString("v"));
                if (!TextUtils.isEmpty(jSONObject.optString("configs"))) {
                    JSONObject jSONObject2 = new JSONObject(jSONObject.optString("configs"));
                    sensorsDataSDKRemoteConfig.setDisableDebugMode(jSONObject2.optBoolean("disableDebugMode", false));
                    sensorsDataSDKRemoteConfig.setDisableSDK(jSONObject2.optBoolean("disableSDK", false));
                    sensorsDataSDKRemoteConfig.setAutoTrackMode(jSONObject2.optInt("autoTrackMode", -1));
                } else {
                    sensorsDataSDKRemoteConfig.setDisableDebugMode(false);
                    sensorsDataSDKRemoteConfig.setDisableSDK(false);
                    sensorsDataSDKRemoteConfig.setAutoTrackMode(-1);
                }
            }
        } catch (Exception unused) {
        }
        return sensorsDataSDKRemoteConfig;
    }

    public static String getManufacturer() {
        String str = Build.MANUFACTURER;
        String trim = str == null ? "UNKNOWN" : str.trim();
        try {
            if (!TextUtils.isEmpty(trim)) {
                for (String str2 : sManufacturer) {
                    if (str2.equalsIgnoreCase(trim)) {
                        return str2;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return trim;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v7, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public static ArrayList<String> getAutoTrackFragments(Context context) {
        IOException e;
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(context.getAssets().open("sa_autotrack_fragment.config")));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
//                            bufferedReader = TextUtils.isEmpty(readLine);
//                            if (bufferedReader == 0 && (bufferedReader = readLine.startsWith("#")) == 0) {
//                                arrayList.add(readLine);
//                            }
                        } catch (IOException e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            e.toString().contains("FileNotFoundException");
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                bufferedReader = bufferedReader;
                            }
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                    bufferedReader = bufferedReader;
                } catch (IOException unused2) {
                }
            } catch (IOException e3) {
                e = e3;
            }
            return arrayList;
        } catch (Throwable th2) {
            Throwable th = th2;
        }
        return null;
    }

    private static String getJsonFromAssets(String str, Context context) {
        Throwable th;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (IOException unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return sb.toString();
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
            } catch (IOException unused3) {
            }
        } catch (IOException unused4) {
        } catch (Throwable th3) {
            th = th3;
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033 A[Catch: Exception -> 0x004c, TryCatch #0 {Exception -> 0x004c, blocks: (B:3:0x0001, B:5:0x0009, B:7:0x0013, B:9:0x001d, B:11:0x0027, B:13:0x002d, B:15:0x0033, B:17:0x003a, B:19:0x0041, B:21:0x0047), top: B:25:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0047 A[Catch: Exception -> 0x004c, TRY_LEAVE, TryCatch #0 {Exception -> 0x004c, blocks: (B:3:0x0001, B:5:0x0009, B:7:0x0013, B:9:0x001d, B:11:0x0027, B:13:0x002d, B:15:0x0033, B:17:0x003a, B:19:0x0041, B:21:0x0047), top: B:25:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getCarrier(Context context) {
        TelephonyManager telephonyManager;
        String str;
        try {
            if (checkHasPermission(context, Permission.READ_PHONE_STATE) && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
                String simOperator = telephonyManager.getSimOperator();
                if (Build.VERSION.SDK_INT >= 28) {
                    CharSequence simCarrierIdName = telephonyManager.getSimCarrierIdName();
                    if (!TextUtils.isEmpty(simCarrierIdName)) {
                        str = simCarrierIdName.toString();
                        if (TextUtils.isEmpty(str)) {
                            str = telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY ? telephonyManager.getSimOperatorName() : "未知";
                        }
                        if (!TextUtils.isEmpty(simOperator)) {
                            return operatorToCarrier(context, simOperator, str);
                        }
                    }
                }
                str = null;
                if (TextUtils.isEmpty(str)) {
                }
                if (!TextUtils.isEmpty(simOperator)) {
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001b A[Catch: Exception -> 0x004b, TryCatch #0 {Exception -> 0x004b, blocks: (B:4:0x0003, B:6:0x0009, B:10:0x0015, B:12:0x001b, B:13:0x0023, B:15:0x0029, B:17:0x002f, B:19:0x0042), top: B:23:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getActivityTitle(Activity activity) {
        String str;
        PackageManager packageManager;
        if (activity != null) {
            try {
                if (Build.VERSION.SDK_INT >= 11) {
                    str = getToolbarTitle(activity);
                    if (!TextUtils.isEmpty(str)) {
                        if (TextUtils.isEmpty(str)) {
                            str = activity.getTitle().toString();
                        }
                        if (!TextUtils.isEmpty(str) && (packageManager = activity.getPackageManager()) != null) {
                            ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 0);
                            return !TextUtils.isEmpty(activityInfo.loadLabel(packageManager)) ? activityInfo.loadLabel(packageManager).toString() : str;
                        }
                    }
                }
                str = null;
                if (TextUtils.isEmpty(str)) {
                }
                return !TextUtils.isEmpty(str) ? str : str;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String getMainProcessName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getApplicationContext().getApplicationInfo().processName;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getCurrentProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (!(activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null)) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo != null && runningAppProcessInfo.pid == myPid) {
                        return runningAppProcessInfo.processName;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static boolean isMainProcess(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String currentProcessName = getCurrentProcessName(context.getApplicationContext());
        return TextUtils.isEmpty(currentProcessName) || str.equals(currentProcessName);
    }

    private static String operatorToCarrier(Context context, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (sCarrierMap.containsKey(str)) {
            return sCarrierMap.get(str);
        }
        String jsonFromAssets = getJsonFromAssets("sa_mcc_mnc_mini.json", context);
        if (TextUtils.isEmpty(jsonFromAssets)) {
            sCarrierMap.put(str, str2);
            return str2;
        }
        String carrierFromJsonObject = getCarrierFromJsonObject(new JSONObject(jsonFromAssets), str);
        if (!TextUtils.isEmpty(carrierFromJsonObject)) {
            sCarrierMap.put(str, carrierFromJsonObject);
            return carrierFromJsonObject;
        }
        return str2;
    }

    private static String getCarrierFromJsonObject(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return jSONObject.optString(str);
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_EDITS_FILE, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(11)
    public static String getToolbarTitle(Activity activity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object invoke;
        CharSequence charSequence;
        if (!"com.tencent.connect.common.AssistActivity".equals(activity.getClass().getCanonicalName())) {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar == null) {
                Class<?> compatActivity = compatActivity();
                if (!(compatActivity == null || !compatActivity.isInstance(activity) || (invoke = activity.getClass().getMethod("getSupportActionBar", new Class[0]).invoke(activity, new Object[0])) == null || (charSequence = (CharSequence) invoke.getClass().getMethod("getTitle", new Class[0]).invoke(invoke, new Object[0])) == null)) {
                    return charSequence.toString();
                }
            } else if (!TextUtils.isEmpty(actionBar.getTitle())) {
                return actionBar.getTitle().toString();
            }
            return null;
        } else if (!TextUtils.isEmpty(activity.getTitle())) {
            return activity.getTitle().toString();
        } else {
            return null;
        }
    }

    private static Class<?> compatActivity() {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.appcompat.app.AppCompatActivity");
        } catch (Exception unused) {
            cls = null;
        }
        if (cls != null) {
            return cls;
        }
        try {
            return Class.forName("androidx.appcompat.app.AppCompatActivity");
        } catch (Exception unused2) {
            return cls;
        }
    }

    public static void getScreenNameAndTitleFromActivity(JSONObject jSONObject, Activity activity) {
        PackageManager packageManager;
        if (!(activity == null || jSONObject == null)) {
            try {
                jSONObject.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                String str = null;
                if (!TextUtils.isEmpty(activity.getTitle())) {
                    str = activity.getTitle().toString();
                }
                if (Build.VERSION.SDK_INT >= 11) {
                    String toolbarTitle = getToolbarTitle(activity);
                    if (!TextUtils.isEmpty(toolbarTitle)) {
                        str = toolbarTitle;
                    }
                }
                if (TextUtils.isEmpty(str) && (packageManager = activity.getPackageManager()) != null) {
                    str = packageManager.getActivityInfo(activity.getComponentName(), 0).loadLabel(packageManager).toString();
                }
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("title", str);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void cleanUserAgent(Context context) {
        try {
            SharedPreferences.Editor edit = getSharedPreferences(context).edit();
            edit.putString(SHARED_PREF_USER_AGENT_KEY, null);
            edit.apply();
        } catch (Exception unused) {
        }
    }

    public static void mergeJSONObject(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (!(obj instanceof Date) || "$time".equals(next)) {
                    jSONObject2.put(next, obj);
                } else {
                    jSONObject2.put(next, DateFormatUtils.formatDate((Date) obj, Locale.CHINA));
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void mergeSuperJSONObject(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Iterator<String> keys2 = jSONObject2.keys();
                while (true) {
                    if (!keys2.hasNext()) {
                        break;
                    }
                    String next2 = keys2.next();
                    if (!TextUtils.isEmpty(next) && next.toLowerCase(Locale.getDefault()).equals(next2.toLowerCase(Locale.getDefault()))) {
                        jSONObject2.remove(next2);
                        break;
                    }
                }
                Object obj = jSONObject.get(next);
                if (!(obj instanceof Date) || "$time".equals(next)) {
                    jSONObject2.put(next, obj);
                } else {
                    jSONObject2.put(next, DateFormatUtils.formatDate((Date) obj, Locale.CHINA));
                }
            }
        } catch (Exception unused) {
        }
    }

    @Deprecated
    public static String getUserAgent(Context context) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(context);
            String string = sharedPreferences.getString(SHARED_PREF_USER_AGENT_KEY, null);
            if (TextUtils.isEmpty(string)) {
                if (Build.VERSION.SDK_INT >= 17) {
                    try {
                        if (Class.forName("android.webkit.WebSettings").getMethod("getDefaultUserAgent", Context.class) != null) {
                            string = WebSettings.getDefaultUserAgent(context);
                        }
                    } catch (Exception unused) {
                    }
                }
                if (TextUtils.isEmpty(string)) {
                    string = System.getProperty("http.agent");
                }
                if (!TextUtils.isEmpty(string)) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SHARED_PREF_USER_AGENT_KEY, string);
                    edit.apply();
                }
            }
            return string;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String getDeviceID(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String string = sharedPreferences.getString(SHARED_PREF_DEVICE_ID_KEY, null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(SHARED_PREF_DEVICE_ID_KEY, uuid);
        edit.apply();
        return uuid;
    }

    public static boolean checkHasPermission(Context context, String str) {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.core.content.ContextCompat");
        } catch (Exception unused) {
            cls = null;
        }
        if (cls == null) {
            try {
                cls = Class.forName("androidx.core.content.ContextCompat");
            } catch (Exception unused2) {
            }
        }
        if (cls == null) {
            return true;
        }
        try {
            return ((Integer) cls.getMethod("checkSelfPermission", Context.class, String.class).invoke(null, context, str)).intValue() == 0;
        } catch (Exception unused3) {
            return true;
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager;
        String str;
        try {
            if (!checkHasPermission(context, Permission.READ_PHONE_STATE) || (telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)) == null) {
                return "";
            }
            if (Build.VERSION.SDK_INT > 28) {
                if (!telephonyManager.hasCarrierPrivileges()) {
                    return "";
                }
                str = telephonyManager.getImei();
            } else if (Build.VERSION.SDK_INT >= 26) {
                str = telephonyManager.getImei();
            } else {
                str = telephonyManager.getDeviceId();
            }
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getIMEIOld(Context context) {
        return getDeviceID(context, -1);
    }

    public static String getSlot(Context context, int i) {
        return getDeviceID(context, i);
    }

    public static String getMEID(Context context) {
        return getDeviceID(context, -2);
    }

    @SuppressLint({"MissingPermission"})
    private static String getDeviceID(Context context, int i) {
        TelephonyManager telephonyManager;
        String deviceId;
        try {
            if (!checkHasPermission(context, Permission.READ_PHONE_STATE) || (telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)) == null) {
                return "";
            }
            if (i == -1) {
                deviceId = telephonyManager.getDeviceId();
            } else if (i == -2 && Build.VERSION.SDK_INT >= 26) {
                deviceId = telephonyManager.getMeid();
            } else if (Build.VERSION.SDK_INT < 23) {
                return "";
            } else {
                deviceId = telephonyManager.getDeviceId(i);
            }
            return deviceId;
        } catch (Exception unused) {
            return "";
        }
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            return "";
        }
    }

    public static Integer getZoneOffset() {
        try {
            return Integer.valueOf(Calendar.getInstance(Locale.getDefault()).get(15) / 1000);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getApplicationMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationContext().getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            String string = applicationInfo.metaData.getString(str);
            int i = string == null ? applicationInfo.metaData.getInt(str, -1) : -1;
            return i != -1 ? String.valueOf(i) : string;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getMacAddressByInterface() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @SuppressLint({"HardwareIds"})
    public static String getMacAddress(Context context) {
        WifiManager wifiManager;
        String macAddressByInterface = null;
        if (!checkHasPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE)) == null) {
            return "";
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null || !marshmallowMacAddress.equals(connectionInfo.getMacAddress())) {
            if (!(connectionInfo == null || connectionInfo.getMacAddress() == null)) {
                return connectionInfo.getMacAddress();
            }
            return "";
        }
        try {
            macAddressByInterface = getMacAddressByInterface();
        } catch (Exception unused) {
        }
        return macAddressByInterface != null ? macAddressByInterface : marshmallowMacAddress;
    }

    public static boolean isValidAndroidId(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !mInvalidAndroidId.contains(str.toLowerCase(Locale.getDefault()));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a A[Catch: Exception -> 0x0054, TRY_LEAVE, TryCatch #0 {Exception -> 0x0054, blocks: (B:5:0x0008, B:8:0x0021, B:10:0x002c, B:15:0x003a), top: B:18:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isRequestValid(Context context, int i, int i2) {
        if (i > i2) {
            return true;
        }
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(context);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            long j = sharedPreferences.getLong(SHARED_PREF_REQUEST_TIME, 0L);
            boolean z = false;
            int i3 = sharedPreferences.getInt(SHARED_PREF_REQUEST_TIME_RANDOM, 0);
            if (!(j == 0 || i3 == 0)) {
                float elapsedRealtime = (float) (SystemClock.elapsedRealtime() - j);
                if (elapsedRealtime > 0.0f && elapsedRealtime / 1000.0f < i3 * 3600) {
                    if (z) {
                        edit.putLong(SHARED_PREF_REQUEST_TIME, SystemClock.elapsedRealtime());
                        edit.putInt(SHARED_PREF_REQUEST_TIME_RANDOM, new Random().nextInt((i2 - i) + 1) + i);
                        edit.apply();
                    }
                    return z;
                }
            }
            z = true;
            if (z) {
            }
            return z;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean hasUtmProperties(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        return jSONObject.has("$utm_source") || jSONObject.has("$utm_medium") || jSONObject.has("$utm_term") || jSONObject.has("$utm_content") || jSONObject.has("$utm_campaign");
    }

    public static boolean isFirstChannelEvent(Context context, String str) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(context);
            if (channelEvents.isEmpty()) {
                String string = sharedPreferences.getString(SHARED_PREF_CHANNEL_EVENT, "");
                if (!TextUtils.isEmpty(string)) {
                    JSONArray jSONArray = new JSONArray(string);
                    if (jSONArray.length() > 0) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            channelEvents.add(jSONArray.getString(i));
                        }
                    }
                }
            }
            if (!channelEvents.isEmpty() && channelEvents.contains(str)) {
                return false;
            }
            channelEvents.add(str);
            sharedPreferences.edit().putString(SHARED_PREF_CHANNEL_EVENT, channelEvents.toString()).apply();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static CharSequence getAppName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return "";
            }
            return packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).loadLabel(packageManager);
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isDoubleClick(View view) {
        long currentTimeMillis = 1658888888;
        String str;
        if (view == null) {
            return false;
        }
        try {
            currentTimeMillis = System.currentTimeMillis();
//            str = (String) view.getTag(C6134R.C6139id.common_analytics_tag_view_onclick_timestamp);
        } catch (Exception unused) {
        }
//        if (!TextUtils.isEmpty(str) && currentTimeMillis - Long.parseLong(str) < 500) {
//            return true;
//        }
        view.setTag(0, String.valueOf(currentTimeMillis));
        return false;
    }

    public static String getScreenUrl(Object obj) {
        String str = null;
        if (obj == null) {
            return null;
        }
        try {
            if (obj instanceof ScreenAutoTracker) {
                str = ((ScreenAutoTracker) obj).getScreenUrl();
            } else {
                SensorsDataAutoTrackAppViewScreenUrl sensorsDataAutoTrackAppViewScreenUrl = (SensorsDataAutoTrackAppViewScreenUrl) obj.getClass().getAnnotation(SensorsDataAutoTrackAppViewScreenUrl.class);
                if (sensorsDataAutoTrackAppViewScreenUrl != null) {
                    str = sensorsDataAutoTrackAppViewScreenUrl.url();
                }
            }
        } catch (Exception unused) {
        }
        return str == null ? obj.getClass().getCanonicalName() : str;
    }
}
