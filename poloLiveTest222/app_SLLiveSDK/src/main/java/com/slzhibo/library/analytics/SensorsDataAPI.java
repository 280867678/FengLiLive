package com.slzhibo.library.analytics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;


//import com.example.boluouitest2zhibo.R;
import com.slzhibo.library.analytics.data.DbAdapter;
import com.slzhibo.library.analytics.data.PersistentLoader;
import com.slzhibo.library.analytics.data.persistent.PersistentRemoteSDKConfig;
import com.slzhibo.library.analytics.data.persistent.PersistentSuperProperties;
import com.slzhibo.library.analytics.exceptions.InvalidDataException;
import com.slzhibo.library.analytics.internal.FragmentAPI;
import com.slzhibo.library.analytics.internal.IFragmentAPI;
import com.slzhibo.library.analytics.util.AopUtil;
import com.slzhibo.library.analytics.util.DateFormatUtils;
import com.slzhibo.library.analytics.util.SADeviceUtils;
import com.slzhibo.library.analytics.util.SensorsDataUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes6.dex */
public class SensorsDataAPI implements ISensorsDataAPI {
    private static final String TAG = "SA.SensorsDataAPI";
    static final int VTRACK_SUPPORTED_MIN_API = 16;
    private static SensorsDataGPSLocation mGPSLocation;
    private static SAConfigOptions mSAConfigOptions;
    private static SensorsDataSDKRemoteConfig mSDKRemoteConfig;
    private SensorsDataActivityLifecycleCallbacks lifecycleCallbacks;
    private String mAndroidId;
    private boolean mAutoTrack;
    private List<Integer> mAutoTrackIgnoredActivities;
    private boolean mClearReferrerWhenAppEnd;
    private final Context mContext;
    private String mCookie;
    private CustomProperties mCustomProperties;
    private DebugMode mDebugMode;
    private boolean mDisableDefaultRemoteConfig;
    private boolean mDisableTrackDeviceId;
    private com.slzhibo.library.analytics.SensorsDataDynamicSuperProperties mDynamicSuperProperties;
    private boolean mEnableNetworkRequest;
    private IFragmentAPI mFragmentAPI;
    private List<Integer> mHeatMapActivities;
    private List<Class> mIgnoredViewTypeList;
    private SimpleDateFormat mIsFirstDayDateFormat;
    private JSONObject mLastScreenTrackProperties;
    private String mLastScreenUrl;
    private final Object mLoginIdLock;
    private String mMainProcessName;
    private final AnalyticsMessages mMessages;
    private SensorsDataScreenOrientationDetector mOrientationDetector;
    private final PersistentRemoteSDKConfig mPersistentRemoteSDKConfig;
    private boolean mSDKConfigInit;
    private SSLSocketFactory mSSLSocketFactory;
    private final PersistentSuperProperties mSuperProperties;
    private SensorsDataTrackEventCallBack mTrackEventCallBack;
    private TrackTaskManager mTrackTaskManager;
    private TrackTaskManagerThread mTrackTaskManagerThread;
    private final Map<String, EventTimer> mTrackTimer;
    private List<Integer> mVisualizedAutoTrackActivities;
    private static final Map<Context, SensorsDataAPI> sInstanceMap = new HashMap();
    static boolean mIsMainProcess = false;
    static boolean SHOW_DEBUG_INFO_VIEW = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public interface InstanceProcessor {
        void process(SensorsDataAPI sensorsDataAPI);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assertKey(String str) throws InvalidDataException {
    }

    private void assertPropertyTypes(JSONObject jSONObject) throws InvalidDataException {
    }

    private void trackItemEvent(String str, String str2, String str3, JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _trackEventFromH5(String str) {
        return false;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableAppHeatMapConfirmDialog(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableHeatMap() {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableLog(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableVisualizedAutoTrack() {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableVisualizedAutoTrackConfirmDialog(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public String getAnonymousId() {
        return null;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void identify(String str) {
    }

    boolean isAppHeatMapConfirmDialogEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isHeatMapEnabled() {
        return false;
    }

    boolean isVisualizedAutoTrackConfirmDialogEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isVisualizedAutoTrackEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void itemDelete(String str, String str2) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void itemSet(String str, String str2, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void login(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void logout() {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileAppend(String str, String str2) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileAppend(String str, Set<String> set) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileDelete() {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileIncrement(String str, Number number) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileIncrement(Map<String, ? extends Number> map) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profilePushId(String str, String str2) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSet(String str, Object obj) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSet(JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSetOnce(String str, Object obj) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSetOnce(JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileUnset(String str) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileUnsetPushId(String str) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void resetAnonymousId() {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setServerUrl(String str) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackEventFromH5(String str) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackEventFromH5(String str, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackInstallation(String str, JSONObject jSONObject, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackSignUp(String str) {
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackSignUp(String str, JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SensorsDataAPI() {
        this.mLoginIdLock = new Object();
        this.mIgnoredViewTypeList = new ArrayList();
        this.mAndroidId = null;
        this.mDebugMode = DebugMode.DEBUG_OFF;
        this.mEnableNetworkRequest = true;
        this.mClearReferrerWhenAppEnd = false;
        this.mDisableDefaultRemoteConfig = false;
        this.mDisableTrackDeviceId = false;
        this.mContext = null;
        this.mMessages = null;
        this.mSuperProperties = null;
        this.mPersistentRemoteSDKConfig = null;
        this.mTrackTimer = null;
        this.mMainProcessName = null;
    }

    SensorsDataAPI(Context context, DebugMode debugMode) {
        this.mLoginIdLock = new Object();
        this.mIgnoredViewTypeList = new ArrayList();
        this.mAndroidId = null;
        this.mDebugMode = DebugMode.DEBUG_OFF;
        this.mEnableNetworkRequest = true;
        this.mClearReferrerWhenAppEnd = false;
        this.mDisableDefaultRemoteConfig = false;
        this.mDisableTrackDeviceId = false;
        this.mContext = context;
        setDebugMode(debugMode);
        String packageName = context.getApplicationContext().getPackageName();
        this.mAutoTrackIgnoredActivities = new ArrayList();
        this.mHeatMapActivities = new ArrayList();
        this.mVisualizedAutoTrackActivities = new ArrayList();
        PersistentLoader.initLoader(context);
        this.mSuperProperties = (PersistentSuperProperties) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.SUPER_PROPERTIES);
        this.mPersistentRemoteSDKConfig = (PersistentRemoteSDKConfig) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.REMOTE_CONFIG);
        this.mTrackTaskManager = TrackTaskManager.getInstance();
        this.mTrackTaskManagerThread = new TrackTaskManagerThread();
        new Thread(this.mTrackTaskManagerThread, ThreadNameConstants.THREAD_TASK_QUEUE).start();
        SensorsDataExceptionHandler.init();
        initSAConfig(packageName);
        DbAdapter.getInstance(context, packageName);
        this.mMessages = AnalyticsMessages.getInstance(this.mContext);
        this.mAndroidId = SensorsDataUtils.getAndroidID(this.mContext);
        applySDKConfigFromCache();
        if (Build.VERSION.SDK_INT >= 14) {
            Application application = (Application) context.getApplicationContext();
            this.lifecycleCallbacks = new SensorsDataActivityLifecycleCallbacks(this, context);
            application.registerActivityLifecycleCallbacks(this.lifecycleCallbacks);
            application.registerActivityLifecycleCallbacks(AppSateManager.getInstance());
        }
        this.mTrackTimer = new HashMap();
        this.mFragmentAPI = new FragmentAPI(context);
    }

    public static SensorsDataAPI sharedInstance(Context context) {
        if (isSDKDisabled()) {
            return new SensorsDataAPIEmptyImplementation();
        }
        if (context == null) {
            return new SensorsDataAPIEmptyImplementation();
        }
        synchronized (sInstanceMap) {
            SensorsDataAPI sensorsDataAPI = sInstanceMap.get(context.getApplicationContext());
            if (sensorsDataAPI != null) {
                return sensorsDataAPI;
            }
            return new SensorsDataAPIEmptyImplementation();
        }
    }

    @Deprecated
    public static SensorsDataAPI sharedInstance(Context context, DebugMode debugMode) {
        return getInstance(context, debugMode);
    }

    @Deprecated
    public static SensorsDataAPI sharedInstance(Context context, SAConfigOptions sAConfigOptions) {
        mSAConfigOptions = sAConfigOptions;
        SensorsDataAPI instance = getInstance(context, DebugMode.DEBUG_OFF);
        if (!instance.mSDKConfigInit) {
            instance.applySAConfigOptions();
        }
        return instance;
    }

    public static void startWithConfigOptions(Context context, SAConfigOptions sAConfigOptions) {
        if (context == null || sAConfigOptions == null) {
            throw new NullPointerException("Context、SAConfigOptions 不可以为 null");
        }
        mSAConfigOptions = sAConfigOptions;
        SensorsDataAPI instance = getInstance(context, DebugMode.DEBUG_OFF);
        if (!instance.mSDKConfigInit) {
            instance.applySAConfigOptions();
        }
    }

    private static SensorsDataAPI getInstance(Context context, DebugMode debugMode) {
        SensorsDataAPI sensorsDataAPI;
        if (context == null) {
            return new SensorsDataAPIEmptyImplementation();
        }
        synchronized (sInstanceMap) {
            Context applicationContext = context.getApplicationContext();
            sensorsDataAPI = sInstanceMap.get(applicationContext);
            if (sensorsDataAPI == null) {
                sensorsDataAPI = new SensorsDataAPI(applicationContext, debugMode);
                sInstanceMap.put(applicationContext, sensorsDataAPI);
            }
        }
        return sensorsDataAPI;
    }

    public static SensorsDataAPI sharedInstance() {
        if (isSDKDisabled()) {
            return new SensorsDataAPIEmptyImplementation();
        }
        synchronized (sInstanceMap) {
            if (sInstanceMap.size() > 0) {
                Iterator<SensorsDataAPI> it2 = sInstanceMap.values().iterator();
                if (it2.hasNext()) {
                    return it2.next();
                }
            }
            return new SensorsDataAPIEmptyImplementation();
        }
    }

    public static boolean isSDKDisabled() {
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = mSDKRemoteConfig;
        if (sensorsDataSDKRemoteConfig == null) {
            return false;
        }
        return sensorsDataSDKRemoteConfig.isDisableSDK();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void allInstances(InstanceProcessor instanceProcessor) {
        synchronized (sInstanceMap) {
            for (SensorsDataAPI sensorsDataAPI : sInstanceMap.values()) {
                instanceProcessor.process(sensorsDataAPI);
            }
        }
    }

    private void setSDKRemoteConfig(SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig, boolean z) {
        try {
            if (sensorsDataSDKRemoteConfig.isDisableSDK() && !SensorsDataUtils.toSDKRemoteConfig(this.mPersistentRemoteSDKConfig.get()).isDisableSDK()) {
                track("DisableSensorsDataSDK");
            }
            this.mPersistentRemoteSDKConfig.commit(sensorsDataSDKRemoteConfig.toJson().toString());
            if (z) {
                mSDKRemoteConfig = sensorsDataSDKRemoteConfig;
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void applySDKConfigFromCache() {
        try {
            SensorsDataSDKRemoteConfig sDKRemoteConfig = SensorsDataUtils.toSDKRemoteConfig(this.mPersistentRemoteSDKConfig.get());
            if (sDKRemoteConfig == null) {
                sDKRemoteConfig = new SensorsDataSDKRemoteConfig();
            }
            if (sDKRemoteConfig.isDisableDebugMode()) {
                setDebugMode(DebugMode.DEBUG_OFF);
            }
            if (sDKRemoteConfig.getAutoTrackEventType() != 0) {
                enableAutoTrack(sDKRemoteConfig.getAutoTrackEventType());
            }
            if (sDKRemoteConfig.isDisableSDK()) {
                try {
                    flush();
                } catch (Exception unused) {
                }
            }
            mSDKRemoteConfig = sDKRemoteConfig;
        } catch (Exception unused2) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public JSONObject getPresetProperties() {
        return new JSONObject();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public long getMaxCacheSize() {
        return mSAConfigOptions.mMaxCacheSize;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setMaxCacheSize(long j) {
        mSAConfigOptions.setMaxCacheSize(j);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setFlushNetworkPolicy(int i) {
        mSAConfigOptions.setNetworkTypePolicy(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFlushNetworkPolicy() {
        return mSAConfigOptions.mNetworkTypePolicy;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public int getFlushInterval() {
        return mSAConfigOptions.mFlushInterval;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setFlushInterval(int i) {
        mSAConfigOptions.setFlushInterval(i);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public int getFlushBulkSize() {
        return mSAConfigOptions.mFlushBulkSize;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setFlushBulkSize(int i) {
        mSAConfigOptions.setFlushBulkSize(i);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public int getSessionIntervalTime() {
        return DbAdapter.getInstance() == null ? 30000 : DbAdapter.getInstance().getSessionIntervalTime();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setSessionIntervalTime(int i) {
        if (DbAdapter.getInstance() != null && i >= 10000 && i <= 300000) {
            DbAdapter.getInstance().commitSessionIntervalTime(i);
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setGPSLocation(double d, double d2) {
        try {
            if (mGPSLocation == null) {
                mGPSLocation = new SensorsDataGPSLocation();
            }
            mGPSLocation.setLatitude((long) (d * Math.pow(10.0d, 6.0d)));
            mGPSLocation.setLongitude((long) (d2 * Math.pow(10.0d, 6.0d)));
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearGPSLocation() {
        mGPSLocation = null;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableTrackScreenOrientation(boolean z) {
        try {
            if (z) {
                if (this.mOrientationDetector == null) {
                    this.mOrientationDetector = new SensorsDataScreenOrientationDetector(this.mContext, 3);
                }
                this.mOrientationDetector.enable();
            } else if (this.mOrientationDetector != null) {
                this.mOrientationDetector.disable();
                this.mOrientationDetector = null;
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void resumeTrackScreenOrientation() {
        try {
            if (this.mOrientationDetector != null) {
                this.mOrientationDetector.enable();
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void stopTrackScreenOrientation() {
        try {
            if (this.mOrientationDetector != null) {
                this.mOrientationDetector.disable();
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public String getScreenOrientation() {
        try {
            if (this.mOrientationDetector != null) {
                return this.mOrientationDetector.getOrientation();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setCookie(String str, boolean z) {
        try {
            if (z) {
                this.mCookie = URLEncoder.encode(str, "UTF-8");
            } else {
                this.mCookie = str;
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public String getCookie(boolean z) {
        try {
            if (z) {
                return URLDecoder.decode(this.mCookie, "UTF-8");
            }
            return this.mCookie;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void enableAutoTrack() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(AutoTrackEventType.APP_START);
        arrayList.add(AutoTrackEventType.APP_END);
        arrayList.add(AutoTrackEventType.APP_VIEW_SCREEN);
        enableAutoTrack(arrayList);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableAutoTrack(List<AutoTrackEventType> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    this.mAutoTrack = true;
                    for (AutoTrackEventType autoTrackEventType : list) {
                        mSAConfigOptions.setAutoTrackEventType(autoTrackEventType.eventValue | mSAConfigOptions.mAutoTrackEventType);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    private void enableAutoTrack(int i) {
        if (i > 0 && i <= 15) {
            try {
                this.mAutoTrack = true;
                mSAConfigOptions.setAutoTrackEventType(i | mSAConfigOptions.mAutoTrackEventType);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void disableAutoTrack(List<AutoTrackEventType> list) {
        ignoreAutoTrackEventType(list);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void disableAutoTrack(AutoTrackEventType autoTrackEventType) {
        ignoreAutoTrackEventType(autoTrackEventType);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackAppCrash() {
        SensorsDataExceptionHandler.enableAppCrash();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isAutoTrackEnabled() {
        if (isSDKDisabled()) {
            return false;
        }
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = mSDKRemoteConfig;
        if (sensorsDataSDKRemoteConfig != null) {
            if (sensorsDataSDKRemoteConfig.getAutoTrackMode() == 0) {
                return false;
            }
            if (mSDKRemoteConfig.getAutoTrackMode() > 0) {
                return true;
            }
        }
        return this.mAutoTrack;
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public void trackFragmentAppViewScreen() {
        this.mFragmentAPI.trackFragmentAppViewScreen();
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public boolean isTrackFragmentAppViewScreenEnabled() {
        return this.mFragmentAPI.isTrackFragmentAppViewScreenEnabled();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableReactNativeAutoTrack() {
        mSAConfigOptions.enableReactNativeAutoTrack(true);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isReactNativeAutoTrackEnabled() {
        return mSAConfigOptions.mRNAutoTrackEnabled;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean z) {
        showUpWebView(webView, z, (JSONObject) null);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean z, boolean z2) {
        showUpWebView(webView, null, z, z2);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    @Deprecated
    public void showUpWebView(WebView webView, JSONObject jSONObject, boolean z, boolean z2) {
        if ((Build.VERSION.SDK_INT >= 17 || z) && webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new AppWebViewInterface(this.mContext, jSONObject, z2), "SensorsData_APP_JS_Bridge");
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    @Deprecated
    public void showUpWebView(WebView webView, boolean z, JSONObject jSONObject) {
        showUpWebView(webView, jSONObject, z, false);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void showUpX5WebView(Object obj, JSONObject jSONObject, boolean z, boolean z2) {
        Method method;
        try {
            if ((Build.VERSION.SDK_INT >= 17 || z) && obj != null && (method = obj.getClass().getMethod("addJavascriptInterface", Object.class, String.class)) != null) {
                method.invoke(obj, new AppWebViewInterface(this.mContext, jSONObject, z2), "SensorsData_APP_JS_Bridge");
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void showUpX5WebView(Object obj, boolean z) {
        if (obj != null) {
            try {
                Method method = obj.getClass().getMethod("addJavascriptInterface", Object.class, String.class);
                if (method != null) {
                    method.invoke(obj, new AppWebViewInterface(this.mContext, null, z), "SensorsData_APP_JS_Bridge");
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void showUpX5WebView(Object obj) {
        showUpX5WebView(obj, false);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreAutoTrackActivities(List<Class<?>> list) {
        if (!(list == null || list.size() == 0)) {
            if (this.mAutoTrackIgnoredActivities == null) {
                this.mAutoTrackIgnoredActivities = new ArrayList();
            }
            for (Class<?> cls : list) {
                if (cls != null) {
                    int hashCode = cls.hashCode();
                    if (!this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                        this.mAutoTrackIgnoredActivities.add(Integer.valueOf(hashCode));
                    }
                }
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void resumeAutoTrackActivities(List<Class<?>> list) {
        if (list != null && list.size() != 0) {
            if (this.mAutoTrackIgnoredActivities == null) {
                this.mAutoTrackIgnoredActivities = new ArrayList();
            }
            try {
                for (Class<?> cls : list) {
                    if (cls != null) {
                        int hashCode = cls.hashCode();
                        if (this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                            this.mAutoTrackIgnoredActivities.remove(Integer.valueOf(hashCode));
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreAutoTrackActivity(Class<?> cls) {
        if (cls != null) {
            if (this.mAutoTrackIgnoredActivities == null) {
                this.mAutoTrackIgnoredActivities = new ArrayList();
            }
            try {
                int hashCode = cls.hashCode();
                if (!this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                    this.mAutoTrackIgnoredActivities.add(Integer.valueOf(hashCode));
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void resumeAutoTrackActivity(Class<?> cls) {
        if (cls != null) {
            if (this.mAutoTrackIgnoredActivities == null) {
                this.mAutoTrackIgnoredActivities = new ArrayList();
            }
            try {
                int hashCode = cls.hashCode();
                if (this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                    this.mAutoTrackIgnoredActivities.remove(Integer.valueOf(hashCode));
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public void enableAutoTrackFragment(Class<?> cls) {
        this.mFragmentAPI.enableAutoTrackFragment(cls);
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public void enableAutoTrackFragments(List<Class<?>> list) {
        this.mFragmentAPI.enableAutoTrackFragments(list);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isActivityAutoTrackAppViewScreenIgnored(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        List<Integer> list = this.mAutoTrackIgnoredActivities;
        return ((list == null || !list.contains(Integer.valueOf(cls.hashCode()))) && cls.getAnnotation(SensorsDataIgnoreTrackAppViewScreenAndAppClick.class) == null && cls.getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class) == null) ? false : true;
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public boolean isFragmentAutoTrackAppViewScreen(Class<?> cls) {
        return this.mFragmentAPI.isFragmentAutoTrackAppViewScreen(cls);
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public void ignoreAutoTrackFragments(List<Class<?>> list) {
        this.mFragmentAPI.ignoreAutoTrackFragments(list);
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public void ignoreAutoTrackFragment(Class<?> cls) {
        this.mFragmentAPI.ignoreAutoTrackFragment(cls);
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public void resumeIgnoredAutoTrackFragments(List<Class<?>> list) {
        this.mFragmentAPI.resumeIgnoredAutoTrackFragments(list);
    }

    @Override // com.slzhibo.library.analytics.internal.IFragmentAPI
    public void resumeIgnoredAutoTrackFragment(Class<?> cls) {
        this.mFragmentAPI.resumeIgnoredAutoTrackFragment(cls);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isActivityAutoTrackAppClickIgnored(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        List<Integer> list = this.mAutoTrackIgnoredActivities;
        return ((list == null || !list.contains(Integer.valueOf(cls.hashCode()))) && cls.getAnnotation(SensorsDataIgnoreTrackAppViewScreenAndAppClick.class) == null && cls.getAnnotation(SensorsDataIgnoreTrackAppClick.class) == null) ? false : true;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void ignoreAutoTrackEventType(AutoTrackEventType autoTrackEventType) {
        int i;
        if (autoTrackEventType != null && (i = mSAConfigOptions.mAutoTrackEventType) != 0) {
            int i2 = i | autoTrackEventType.eventValue;
            if (i2 == autoTrackEventType.eventValue) {
                mSAConfigOptions.setAutoTrackEventType(0);
            } else {
                mSAConfigOptions.setAutoTrackEventType(autoTrackEventType.eventValue ^ i2);
            }
            if (mSAConfigOptions.mAutoTrackEventType == 0) {
                this.mAutoTrack = false;
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void ignoreAutoTrackEventType(List<AutoTrackEventType> list) {
        if (!(list == null || mSAConfigOptions.mAutoTrackEventType == 0)) {
            for (AutoTrackEventType autoTrackEventType : list) {
                int i = mSAConfigOptions.mAutoTrackEventType | autoTrackEventType.eventValue;
                SAConfigOptions sAConfigOptions = mSAConfigOptions;
                int i2 = sAConfigOptions.mAutoTrackEventType;
                if (i == i2) {
                    sAConfigOptions.setAutoTrackEventType(autoTrackEventType.eventValue ^ i2);
                }
            }
            if (mSAConfigOptions.mAutoTrackEventType == 0) {
                this.mAutoTrack = false;
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isAutoTrackEventTypeIgnored(AutoTrackEventType autoTrackEventType) {
        if (autoTrackEventType == null) {
            return false;
        }
        return isAutoTrackEventTypeIgnored(autoTrackEventType.eventValue);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isAutoTrackEventTypeIgnored(int i) {
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = mSDKRemoteConfig;
        if (sensorsDataSDKRemoteConfig == null || sensorsDataSDKRemoteConfig.getAutoTrackMode() == -1) {
            int i2 = mSAConfigOptions.mAutoTrackEventType;
            return (i | i2) != i2;
        } else if (mSDKRemoteConfig.getAutoTrackMode() == 0) {
            return true;
        } else {
            return mSDKRemoteConfig.isAutoTrackEventTypeIgnored(i);
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewID(View view, String str) {
        if (view != null && !TextUtils.isEmpty(str)) {
            view.setTag(0, str);
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewID(Dialog dialog, String str) {
        if (dialog != null) {
            try {
                if (!TextUtils.isEmpty(str) && dialog.getWindow() != null) {
                    dialog.getWindow().getDecorView().setTag(0, str);
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewID(Object obj, String str) {
        Class<?> cls;
        Method method;
        Window window;
        if (obj != null) {
            Class<?> cls2 = null;
            try {
                cls = Class.forName("androidx.appcompat.app.AlertDialog");
            } catch (Exception unused) {
                cls = null;
            }
            try {
                cls2 = Class.forName("androidx.appcompat.app.AlertDialog");
            } catch (Exception unused2) {
            }
            if (cls != null) {
                cls2 = cls;
            }
            if (cls2 != null) {
                try {
                    if (cls2.isInstance(obj) && !TextUtils.isEmpty(str) && (method = obj.getClass().getMethod("getWindow", new Class[0])) != null && (window = (Window) method.invoke(obj, new Object[0])) != null) {
                        window.getDecorView().setTag(0, str);
                    }
                } catch (Exception unused3) {
                }
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewActivity(View view, Activity activity) {
        if (!(view == null || activity == null)) {
            try {
                view.setTag(0, activity);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewFragmentName(View view, String str) {
        if (view != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    view.setTag(0, str);
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreView(View view) {
        if (view != null) {
            view.setTag(0, "1");
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreView(View view, boolean z) {
        if (view != null) {
            view.setTag(0, z ? "1" : "0");
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewProperties(View view, JSONObject jSONObject) {
        if (view != null && jSONObject != null) {
            view.setTag(0, jSONObject);
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public List<Class> getIgnoredViewTypeList() {
        if (this.mIgnoredViewTypeList == null) {
            this.mIgnoredViewTypeList = new ArrayList();
        }
        return this.mIgnoredViewTypeList;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreViewType(Class cls) {
        if (cls != null) {
            if (this.mIgnoredViewTypeList == null) {
                this.mIgnoredViewTypeList = new ArrayList();
            }
            if (!this.mIgnoredViewTypeList.contains(cls)) {
                this.mIgnoredViewTypeList.add(cls);
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isVisualizedAutoTrackActivity(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        if (this.mVisualizedAutoTrackActivities.size() == 0) {
            return true;
        }
        return this.mVisualizedAutoTrackActivities.contains(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void addVisualizedAutoTrackActivity(Class<?> cls) {
        if (cls != null) {
            try {
                this.mVisualizedAutoTrackActivities.add(Integer.valueOf(cls.hashCode()));
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void addVisualizedAutoTrackActivities(List<Class<?>> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    for (Class<?> cls : list) {
                        if (cls != null) {
                            int hashCode = cls.hashCode();
                            if (!this.mVisualizedAutoTrackActivities.contains(Integer.valueOf(hashCode))) {
                                this.mVisualizedAutoTrackActivities.add(Integer.valueOf(hashCode));
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isHeatMapActivity(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        if (this.mHeatMapActivities.size() == 0) {
            return true;
        }
        return this.mHeatMapActivities.contains(Integer.valueOf(cls.hashCode()));
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void addHeatMapActivity(Class<?> cls) {
        if (cls != null) {
            try {
                this.mHeatMapActivities.add(Integer.valueOf(cls.hashCode()));
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void addHeatMapActivities(List<Class<?>> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    for (Class<?> cls : list) {
                        if (cls != null) {
                            int hashCode = cls.hashCode();
                            if (!this.mHeatMapActivities.contains(Integer.valueOf(hashCode))) {
                                this.mHeatMapActivities.add(Integer.valueOf(hashCode));
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public String getDistinctId() {
        String loginId = getLoginId();
        return !TextUtils.isEmpty(loginId) ? loginId : getAnonymousId();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public String getLoginId() {
        return DbAdapter.getInstance().getLoginId();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void login(String str) {
        login(str, null);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackInstallation(String str, JSONObject jSONObject) {
        trackInstallation(str, jSONObject, false);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackInstallation(String str) {
        trackInstallation(str, null, false);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackChannelEvent(String str) {
        trackChannelEvent(str, null);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackChannelEvent(final String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        JSONObject finalJSONObject = jSONObject;
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    finalJSONObject.put("$is_channel_callback_event", SensorsDataUtils.isFirstChannelEvent(SensorsDataAPI.this.mContext, str));
                    if (!SensorsDataUtils.hasUtmProperties(finalJSONObject)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("SENSORS_ANALYTICS_UTM_SOURCE", "$utm_source");
                        hashMap.put("SENSORS_ANALYTICS_UTM_MEDIUM", "$utm_medium");
                        hashMap.put("SENSORS_ANALYTICS_UTM_TERM", "$utm_term");
                        hashMap.put("SENSORS_ANALYTICS_UTM_CONTENT", "$utm_content");
                        hashMap.put("SENSORS_ANALYTICS_UTM_CAMPAIGN", "$utm_campaign");
                        for (Object entrys : hashMap.entrySet()) {
                            Map.Entry entry = (Map.Entry) entrys;
                            if (entry != null) {
                                String applicationMetaData = SensorsDataUtils.getApplicationMetaData(SensorsDataAPI.this.mContext, (String) entry.getKey());
                                if (!TextUtils.isEmpty(applicationMetaData)) {
                                    finalJSONObject.put((String) entry.getValue(), applicationMetaData);
                                }
                            }
                        }
                    }
                    if (!SensorsDataUtils.hasUtmProperties(finalJSONObject)) {
                        finalJSONObject.put("$channel_device_info", String.format("android_id=%s##imei=%s##imei_old=%s##imei_slot1=%s##imei_slot2=%s##imei_meid=%s##mac=%s##oaid=%s", SensorsDataAPI.this.mAndroidId, SensorsDataUtils.getIMEI(SensorsDataAPI.this.mContext), SensorsDataUtils.getIMEIOld(SensorsDataAPI.this.mContext), SensorsDataUtils.getSlot(SensorsDataAPI.this.mContext, 0), SensorsDataUtils.getSlot(SensorsDataAPI.this.mContext, 1), SensorsDataUtils.getMEID(SensorsDataAPI.this.mContext), SensorsDataUtils.getMacAddress(SensorsDataAPI.this.mContext), SADeviceUtils.getOAID(SensorsDataAPI.this.mContext)));
                    }
                } catch (Exception unused) {
                }
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, finalJSONObject);
                } catch (Exception unused2) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void track(final String str, final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, jSONObject);
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void track(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, null);
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackTimer(String str) {
        trackTimer(str, TimeUnit.MILLISECONDS);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackTimer(final String str, final TimeUnit timeUnit) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        SensorsDataAPI.this.mTrackTimer.put(str, new EventTimer(timeUnit, elapsedRealtime));
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void removeTimer(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        SensorsDataAPI.this.mTrackTimer.remove(str);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public String trackTimerStart(String str) {
        try {
            String format = String.format("%s_%s_%s", str, UUID.randomUUID().toString().replace("-", "_"), "SATimer");
            trackTimerBegin(format, TimeUnit.SECONDS);
            trackTimerBegin(str, TimeUnit.SECONDS);
            return format;
        } catch (Exception unused) {
            return "";
        }
    }

    private void trackTimerState(final String str, final boolean z) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        EventTimer eventTimer = (EventTimer) SensorsDataAPI.this.mTrackTimer.get(str);
                        if (!(eventTimer == null || eventTimer.isPaused() == z)) {
                            eventTimer.setTimerState(z, elapsedRealtime);
                        }
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerPause(String str) {
        trackTimerState(str, true);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerResume(String str) {
        trackTimerState(str, false);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackTimerBegin(String str) {
        trackTimer(str);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackTimerBegin(String str, TimeUnit timeUnit) {
        trackTimer(str, timeUnit);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerEnd(final String str, final JSONObject jSONObject) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (str != null) {
            synchronized (this.mTrackTimer) {
                EventTimer eventTimer = this.mTrackTimer.get(str);
                if (eventTimer != null) {
                    eventTimer.setEndTime(elapsedRealtime);
                }
            }
        }
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, jSONObject);
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerEnd(String str) {
        trackTimerEnd(str, null);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearTrackTimer() {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        SensorsDataAPI.this.mTrackTimer.clear();
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public String getLastScreenUrl() {
        return this.mLastScreenUrl;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearReferrerWhenAppEnd() {
        this.mClearReferrerWhenAppEnd = true;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearLastScreenUrl() {
        if (this.mClearReferrerWhenAppEnd) {
            this.mLastScreenUrl = null;
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public String getMainProcessName() {
        return this.mMainProcessName;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public JSONObject getLastScreenTrackProperties() {
        return this.mLastScreenTrackProperties;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackViewScreen(final String str, final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!TextUtils.isEmpty(str) || jSONObject != null) {
                        JSONObject jSONObject2 = new JSONObject();
                        SensorsDataAPI.this.mLastScreenTrackProperties = jSONObject;
                        if (SensorsDataAPI.this.mLastScreenUrl != null) {
                            jSONObject2.put(AopConstants.REFERRER, SensorsDataAPI.this.mLastScreenUrl);
                        }
                        jSONObject2.put("url", str);
                        SensorsDataAPI.this.mLastScreenUrl = str;
                        if (jSONObject != null) {
                            SensorsDataUtils.mergeJSONObject(jSONObject, jSONObject2);
                        }
                        SensorsDataAPI.this.track(AopConstants.APP_VIEW_SCREEN_EVENT_NAME, jSONObject2);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewScreen(final Activity activity) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (activity != null) {
                        SensorsDataAPI.this.trackViewScreen(SensorsDataUtils.getScreenUrl(activity), AopUtil.buildTitleAndScreenName(activity));
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewScreen(final Object obj) {
        Class<?> cls;
        Class<?> cls2;
        if (obj != null) {
            Class<?> cls3 = null;
            try {
                cls = Class.forName("androidx.fragment.app.Fragment");
            } catch (Exception unused) {
                cls = null;
            }
            try {
                cls2 = Class.forName("android.app.Fragment");
            } catch (Exception unused2) {
                cls2 = null;
            }
            try {
                cls3 = Class.forName("androidx.fragment.app.Fragment");
            } catch (Exception unused3) {
            }
            if ((cls != null && cls.isInstance(obj)) || ((cls2 != null && cls2.isInstance(obj)) || (cls3 != null && cls3.isInstance(obj)))) {
                this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.11
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject trackProperties;
                        SensorsDataFragmentTitle sensorsDataFragmentTitle;
                        try {
                            JSONObject jSONObject = new JSONObject();
                            String canonicalName = obj.getClass().getCanonicalName();
                            Activity activity = null;
                            String title = (!obj.getClass().isAnnotationPresent(SensorsDataFragmentTitle.class) || (sensorsDataFragmentTitle = (SensorsDataFragmentTitle) obj.getClass().getAnnotation(SensorsDataFragmentTitle.class)) == null) ? null : sensorsDataFragmentTitle.title();
                            if (Build.VERSION.SDK_INT >= 11) {
                                try {
                                    Method method = obj.getClass().getMethod("getActivity", new Class[0]);
                                    if (method != null) {
                                        activity = (Activity) method.invoke(obj, new Object[0]);
                                    }
                                } catch (Exception unused4) {
                                }
                                if (activity != null) {
                                    if (TextUtils.isEmpty(title)) {
                                        title = SensorsDataUtils.getActivityTitle(activity);
                                    }
                                    canonicalName = String.format(Locale.CHINA, "%s|%s", activity.getClass().getCanonicalName(), canonicalName);
                                }
                            }
                            if (!TextUtils.isEmpty(title)) {
                                jSONObject.put("title", title);
                            }
                            jSONObject.put(AopConstants.SCREEN_NAME, canonicalName);
                            if ((obj instanceof ScreenAutoTracker) && (trackProperties = ((ScreenAutoTracker) obj).getTrackProperties()) != null) {
                                SensorsDataUtils.mergeJSONObject(trackProperties, jSONObject);
                            }
                            SensorsDataAPI.this.trackViewScreen(SensorsDataUtils.getScreenUrl(obj), jSONObject);
                        } catch (Exception unused5) {
                        }
                    }
                });
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewAppClick(View view) {
        trackViewAppClick(view, null);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewAppClick(View view, JSONObject jSONObject) {
        if (view != null) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            if (AopUtil.injectClickInfo(view, jSONObject, true)) {
                track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appEnterBackground() {
        EventTimer value;
        synchronized (this.mTrackTimer) {
            try {
                for (Map.Entry<String, EventTimer> entry : this.mTrackTimer.entrySet()) {
                    if (entry != null && !AopConstants.APP_END_EVENT_NAME.equals(entry.getKey().toString()) && (value = entry.getValue()) != null && !value.isPaused()) {
                        value.setEventAccumulatedDuration(((value.getEventAccumulatedDuration() + SystemClock.elapsedRealtime()) - value.getStartTime()) - getSessionIntervalTime());
                        value.setStartTime(SystemClock.elapsedRealtime());
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appBecomeActive() {
        EventTimer value;
        synchronized (this.mTrackTimer) {
            try {
                for (Map.Entry<String, EventTimer> entry : this.mTrackTimer.entrySet()) {
                    if (!(entry == null || (value = entry.getValue()) == null)) {
                        value.setStartTime(SystemClock.elapsedRealtime());
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void flush() {
        this.mMessages.flush();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void flushSync() {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.slzhibo.library.analytics.SensorsDataAPI.12
            @Override // java.lang.Runnable
            public void run() {
                SensorsDataAPI.this.mMessages.flush();
            }
        });
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void registerDynamicSuperProperties(com.slzhibo.library.analytics.SensorsDataDynamicSuperProperties sensorsDataDynamicSuperProperties) {
        this.mDynamicSuperProperties = sensorsDataDynamicSuperProperties;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setCustomExtraProperties(CustomProperties customProperties) {
        this.mCustomProperties = customProperties;
        this.lifecycleCallbacks.setCustomProperties(customProperties);
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setTrackEventCallBack(SensorsDataTrackEventCallBack sensorsDataTrackEventCallBack) {
        this.mTrackEventCallBack = sensorsDataTrackEventCallBack;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void stopTrackThread() {
        TrackTaskManagerThread trackTaskManagerThread = this.mTrackTaskManagerThread;
        if (trackTaskManagerThread != null && !trackTaskManagerThread.isStopped()) {
            this.mTrackTaskManagerThread.setStop(true);
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void startTrackThread() {
        TrackTaskManagerThread trackTaskManagerThread = this.mTrackTaskManagerThread;
        if (trackTaskManagerThread == null || trackTaskManagerThread.isStopped()) {
            this.mTrackTaskManagerThread = new TrackTaskManagerThread();
            new Thread(this.mTrackTaskManagerThread).start();
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void deleteAll() {
        this.mMessages.deleteAll();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public JSONObject getSuperProperties() {
        JSONObject jSONObject;
        synchronized (this.mSuperProperties) {
            try {
                try {
                    jSONObject = new JSONObject(this.mSuperProperties.get().toString());
                } catch (JSONException unused) {
                    return new JSONObject();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return jSONObject;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void registerSuperProperties(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                assertPropertyTypes(jSONObject);
                synchronized (this.mSuperProperties) {
                    JSONObject jSONObject2 = this.mSuperProperties.get();
                    SensorsDataUtils.mergeSuperJSONObject(jSONObject, jSONObject2);
                    this.mSuperProperties.commit(jSONObject2);
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void unregisterSuperProperty(String str) {
        try {
            synchronized (this.mSuperProperties) {
                JSONObject jSONObject = this.mSuperProperties.get();
                jSONObject.remove(str);
                this.mSuperProperties.commit(jSONObject);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearSuperProperties() {
        synchronized (this.mSuperProperties) {
            this.mSuperProperties.commit(new JSONObject());
        }
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isDebugMode() {
        return this.mDebugMode.isDebugMode();
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isNetworkRequestEnable() {
        return this.mEnableNetworkRequest;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableNetworkRequest(boolean z) {
        this.mEnableNetworkRequest = z;
    }

    public DebugMode getDebugMode() {
        return this.mDebugMode;
    }

    void setDebugMode(DebugMode debugMode) {
        this.mDebugMode = debugMode;
        if (debugMode == DebugMode.DEBUG_OFF) {
            enableLog(false);
        } else {
            enableLog(true);
        }
    }

    private boolean isEnterDb(String str, JSONObject jSONObject) {
        SensorsDataTrackEventCallBack sensorsDataTrackEventCallBack = this.mTrackEventCallBack;
        boolean z = true;
        if (sensorsDataTrackEventCallBack != null) {
            try {
                z = sensorsDataTrackEventCallBack.onTrackEvent(str, jSONObject);
            } catch (Exception unused) {
            }
            if (z) {
                try {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        try {
                            assertKey(next);
                            Object opt = jSONObject.opt(next);
                            if (!(opt instanceof CharSequence) && !(opt instanceof Number) && !(opt instanceof JSONArray) && !(opt instanceof Boolean) && !(opt instanceof Date)) {
                                return false;
                            }
                            if ("app_crashed_reason".equals(next)) {
                                if ((opt instanceof String) && ((String) opt).length() > 16382) {
                                    opt = ((String) opt).substring(0, 16382) + "$";
                                }
                            } else if ((opt instanceof String) && ((String) opt).length() > 8191) {
                                opt = ((String) opt).substring(0, 8191) + "$";
                            }
                            if (opt instanceof Date) {
                                jSONObject.put(next, DateFormatUtils.formatDate((Date) opt, Locale.CHINA));
                            } else {
                                jSONObject.put(next, opt);
                            }
                        } catch (Exception unused2) {
                            return false;
                        }
                    }
                } catch (Exception unused3) {
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackEvent(EventType eventType, String str, JSONObject jSONObject) {
        EventTimer eventTimer;
        JSONObject dynamicSuperProperties;
        JSONObject appPublicProperties;
        try {
            if (!TextUtils.isEmpty(str)) {
                synchronized (this.mTrackTimer) {
                    eventTimer = this.mTrackTimer.get(str);
                    this.mTrackTimer.remove(str);
                }
                if (str.endsWith("_SATimer") && str.length() > 45) {
                    str = str.substring(0, str.length() - 45);
                }
            } else {
                eventTimer = null;
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    if (!(this.mCustomProperties == null || (appPublicProperties = this.mCustomProperties.getAppPublicProperties(str)) == null)) {
                        SensorsDataUtils.mergeJSONObject(appPublicProperties, jSONObject2);
                    }
                } catch (Exception unused) {
                }
                synchronized (this.mSuperProperties) {
                    SensorsDataUtils.mergeJSONObject(this.mSuperProperties.get(), jSONObject2);
                }
                try {
                    if (!(this.mDynamicSuperProperties == null || (dynamicSuperProperties = this.mDynamicSuperProperties.getDynamicSuperProperties()) == null)) {
                        SensorsDataUtils.mergeJSONObject(dynamicSuperProperties, jSONObject2);
                    }
                } catch (Exception unused2) {
                }
                try {
                    if (TextUtils.isEmpty(jSONObject2.optString("carrier"))) {
                        String carrier = SensorsDataUtils.getCarrier(this.mContext);
                        if (!TextUtils.isEmpty(carrier)) {
                            jSONObject2.put("carrier", carrier);
                        }
                    }
                } catch (Exception unused3) {
                }
                if (eventTimer != null) {
                    try {
                        Double valueOf = Double.valueOf(eventTimer.duration());
                        if (valueOf.doubleValue() > 0.0d) {
                            jSONObject.put("duration", valueOf);
                        }
                    } catch (Exception unused4) {
                    }
                }
                if (this.mCustomProperties != null) {
                    this.mCustomProperties.dealRawProperties(str, jSONObject);
                }
                jSONObject2.put(AopConstants.APP_EVENT_KEY, str);
                jSONObject2.put("data", jSONObject);
                this.mMessages.enqueueEventMessage(jSONObject2);
            } catch (JSONException unused5) {
                throw new InvalidDataException("Unexpected property");
            }
        } catch (Exception unused6) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isMultiProcess() {
        return mSAConfigOptions.enableMultiProcess;
    }

    private void assertValue(String str) throws InvalidDataException {
        if (TextUtils.isEmpty(str)) {
            throw new InvalidDataException("The value is empty.");
        } else if (str.length() > 255) {
            throw new InvalidDataException("The " + str + " is too long, max length is 255.");
        }
    }

    private void initSAConfig(String str) {
        Bundle bundle;
        try {
            bundle = this.mContext.getApplicationContext().getPackageManager().getApplicationInfo(str, PackageManager.GET_META_DATA).metaData;
        } catch (PackageManager.NameNotFoundException unused) {
            bundle = null;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (mSAConfigOptions == null) {
            this.mSDKConfigInit = false;
            mSAConfigOptions = new SAConfigOptions();
        } else {
            this.mSDKConfigInit = true;
        }
        if (mSAConfigOptions.mEnableTrackAppCrash) {
            SensorsDataExceptionHandler.enableAppCrash();
        }
        SAConfigOptions sAConfigOptions = mSAConfigOptions;
        if (sAConfigOptions.mFlushInterval == 0) {
            sAConfigOptions.setFlushInterval(bundle.getInt("com.sensorsdata.analytics.android.FlushInterval", 15000));
        }
        SAConfigOptions sAConfigOptions2 = mSAConfigOptions;
        if (sAConfigOptions2.mFlushBulkSize == 0) {
            sAConfigOptions2.setFlushBulkSize(bundle.getInt("com.sensorsdata.analytics.android.FlushBulkSize", 100));
        }
        SAConfigOptions sAConfigOptions3 = mSAConfigOptions;
        if (sAConfigOptions3.mMaxCacheSize == 0) {
            sAConfigOptions3.setMaxCacheSize(33554432L);
        }
        this.mAutoTrack = bundle.getBoolean("com.sensorsdata.analytics.android.AutoTrack", false);
        int i = mSAConfigOptions.mAutoTrackEventType;
        if (i != 0) {
            enableAutoTrack(i);
            this.mAutoTrack = true;
        }
        enableTrackScreenOrientation(mSAConfigOptions.mTrackScreenOrientationEnabled);
        if (!TextUtils.isEmpty(mSAConfigOptions.mAnonymousId)) {
            identify(mSAConfigOptions.mAnonymousId);
        }
        SHOW_DEBUG_INFO_VIEW = bundle.getBoolean("com.sensorsdata.analytics.android.ShowDebugInfoView", true);
        this.mDisableDefaultRemoteConfig = bundle.getBoolean("com.sensorsdata.analytics.android.DisableDefaultRemoteConfig", false);
        this.mMainProcessName = SensorsDataUtils.getMainProcessName(this.mContext);
        if (TextUtils.isEmpty(this.mMainProcessName)) {
            this.mMainProcessName = bundle.getString("com.sensorsdata.analytics.android.MainProcessName");
        }
        mIsMainProcess = SensorsDataUtils.isMainProcess(this.mContext, this.mMainProcessName);
        this.mDisableTrackDeviceId = bundle.getBoolean("com.sensorsdata.analytics.android.DisableTrackDeviceId", false);
    }

    private void applySAConfigOptions() {
        if (mSAConfigOptions.mEnableTrackAppCrash) {
            SensorsDataExceptionHandler.enableAppCrash();
        }
        if (mSAConfigOptions.mAutoTrackEventType != 0) {
            this.mAutoTrack = true;
        }
        enableLog(false);
        enableTrackScreenOrientation(mSAConfigOptions.mTrackScreenOrientationEnabled);
        if (!TextUtils.isEmpty(mSAConfigOptions.mAnonymousId)) {
            identify(mSAConfigOptions.mAnonymousId);
        }
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.mSSLSocketFactory = sSLSocketFactory;
    }

    @Override // com.slzhibo.library.analytics.ISensorsDataAPI
    public void setAnalyticsDataListener(GetAnalyticsDataListener getAnalyticsDataListener) {
        AnalyticsMessages.getInstance(this.mContext).setAnalyticsDataListener(getAnalyticsDataListener);
    }

    /* loaded from: classes6.dex */
    public enum DebugMode {
        DEBUG_OFF(false, false),
        DEBUG_ONLY(true, false),
        DEBUG_AND_TRACK(true, true);
        
        private final boolean debugMode;
        private final boolean debugWriteData;

        DebugMode(boolean z, boolean z2) {
            this.debugMode = z;
            this.debugWriteData = z2;
        }

        boolean isDebugMode() {
            return this.debugMode;
        }

        boolean isDebugWriteData() {
            return this.debugWriteData;
        }
    }

    /* loaded from: classes6.dex */
    public enum AutoTrackEventType {
        APP_START(1),
        APP_END(2),
        APP_CLICK(4),
        APP_VIEW_SCREEN(8);
        
        private final int eventValue;

        AutoTrackEventType(int i) {
            this.eventValue = i;
        }

        static AutoTrackEventType autoTrackEventTypeFromEventName(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            char c = 65535;
            switch (str.hashCode()) {
                case 401582162:
                    if (str.equals(AopConstants.APP_VIEW_SCREEN_EVENT_NAME)) {
                        c = 3;
                        break;
                    }
                    break;
                case 1206381575:
                    if (str.equals(AopConstants.APP_CLICK_EVENT_NAME)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1221389025:
                    if (str.equals(AopConstants.APP_START_EVENT_NAME)) {
                        c = 0;
                        break;
                    }
                    break;
                case 1967735578:
                    if (str.equals(AopConstants.APP_END_EVENT_NAME)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                return APP_START;
            }
            if (c == 1) {
                return APP_END;
            }
            if (c == 2) {
                return APP_CLICK;
            }
            if (c != 3) {
                return null;
            }
            return APP_VIEW_SCREEN;
        }

        static boolean isAutoTrackType(String str) {
            if (!TextUtils.isEmpty(str)) {
                char c = 65535;
                switch (str.hashCode()) {
                    case 401582162:
                        if (str.equals(AopConstants.APP_VIEW_SCREEN_EVENT_NAME)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1206381575:
                        if (str.equals(AopConstants.APP_CLICK_EVENT_NAME)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1221389025:
                        if (str.equals(AopConstants.APP_START_EVENT_NAME)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1967735578:
                        if (str.equals(AopConstants.APP_END_EVENT_NAME)) {
                            c = 1;
                            break;
                        }
                        break;
                }
                if (c == 0 || c == 1 || c == 2 || c == 3) {
                    return true;
                }
            }
            return false;
        }

        int getEventValue() {
            return this.eventValue;
        }
    }

    /* loaded from: classes6.dex */
    public final class NetworkType {
        public static final int TYPE_2G = 1;
        public static final int TYPE_3G = 2;
        public static final int TYPE_4G = 4;
        public static final int TYPE_5G = 16;
        public static final int TYPE_ALL = 255;
        public static final int TYPE_NONE = 0;
        public static final int TYPE_WIFI = 8;

        public NetworkType() {
        }
    }
}
