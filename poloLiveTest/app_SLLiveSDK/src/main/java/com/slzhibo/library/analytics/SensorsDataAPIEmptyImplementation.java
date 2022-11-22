package com.slzhibo.library.analytics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

//import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes6.dex */
public class SensorsDataAPIEmptyImplementation extends SensorsDataAPI {
    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void addHeatMapActivities(List<Class<?>> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void addHeatMapActivity(Class<?> cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void addVisualizedAutoTrackActivities(List<Class<?>> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void addVisualizedAutoTrackActivity(Class<?> cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearGPSLocation() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearLastScreenUrl() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearReferrerWhenAppEnd() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearSuperProperties() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void clearTrackTimer() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void deleteAll() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void disableAutoTrack(AutoTrackEventType autoTrackEventType) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void disableAutoTrack(List<AutoTrackEventType> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableAppHeatMapConfirmDialog(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void enableAutoTrack() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableAutoTrack(List<AutoTrackEventType> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public void enableAutoTrackFragment(Class<?> cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public void enableAutoTrackFragments(List<Class<?>> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableHeatMap() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableLog(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableNetworkRequest(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableReactNativeAutoTrack() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableTrackScreenOrientation(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableVisualizedAutoTrack() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void enableVisualizedAutoTrackConfirmDialog(boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void flush() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void flushSync() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public String getAnonymousId() {
        return null;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public String getCookie(boolean z) {
        return null;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public String getDistinctId() {
        return null;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public int getFlushBulkSize() {
        return 0;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public int getFlushInterval() {
        return 0;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public String getLastScreenUrl() {
        return null;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public String getLoginId() {
        return null;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public String getMainProcessName() {
        return "";
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public long getMaxCacheSize() {
        return 0L;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public int getSessionIntervalTime() {
        return 30000;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void identify(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreAutoTrackActivities(List<Class<?>> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreAutoTrackActivity(Class<?> cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void ignoreAutoTrackEventType(AutoTrackEventType autoTrackEventType) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void ignoreAutoTrackEventType(List<AutoTrackEventType> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public void ignoreAutoTrackFragment(Class<?> cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public void ignoreAutoTrackFragments(List<Class<?>> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreView(View view) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreView(View view, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void ignoreViewType(Class cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isActivityAutoTrackAppClickIgnored(Class<?> cls) {
        return true;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isActivityAutoTrackAppViewScreenIgnored(Class<?> cls) {
        return true;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI
    boolean isAppHeatMapConfirmDialogEnabled() {
        return true;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isAutoTrackEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isAutoTrackEventTypeIgnored(AutoTrackEventType autoTrackEventType) {
        return true;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isDebugMode() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public boolean isFragmentAutoTrackAppViewScreen(Class<?> cls) {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isHeatMapActivity(Class<?> cls) {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isHeatMapEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isReactNativeAutoTrackEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public boolean isTrackFragmentAppViewScreenEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isVisualizedAutoTrackActivity(Class<?> cls) {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI
    boolean isVisualizedAutoTrackConfirmDialogEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public boolean isVisualizedAutoTrackEnabled() {
        return false;
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void itemDelete(String str, String str2) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void itemSet(String str, String str2, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void login(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void login(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void logout() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileAppend(String str, String str2) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileAppend(String str, Set<String> set) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileDelete() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileIncrement(String str, Number number) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileIncrement(Map<String, ? extends Number> map) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profilePushId(String str, String str2) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSet(String str, Object obj) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSet(JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSetOnce(String str, Object obj) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileSetOnce(JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileUnset(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void profileUnsetPushId(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void registerDynamicSuperProperties(SensorsDataDynamicSuperProperties sensorsDataDynamicSuperProperties) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void registerSuperProperties(JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void resetAnonymousId() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void resumeAutoTrackActivities(List<Class<?>> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void resumeAutoTrackActivity(Class<?> cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public void resumeIgnoredAutoTrackFragment(Class<?> cls) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public void resumeIgnoredAutoTrackFragments(List<Class<?>> list) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void resumeTrackScreenOrientation() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setCookie(String str, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setFlushBulkSize(int i) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setFlushInterval(int i) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setFlushNetworkPolicy(int i) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setGPSLocation(double d, double d2) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setMaxCacheSize(long j) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setServerUrl(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setSessionIntervalTime(int i) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setTrackEventCallBack(SensorsDataTrackEventCallBack sensorsDataTrackEventCallBack) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewActivity(View view, Activity activity) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewFragmentName(View view, String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewID(Dialog dialog, String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewID(View view, String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewID(Object obj, String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void setViewProperties(View view, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    @Deprecated
    public void showUpWebView(WebView webView, JSONObject jSONObject, boolean z, boolean z2) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    @Deprecated
    public void showUpWebView(WebView webView, boolean z, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean z, boolean z2) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void showUpX5WebView(Object obj) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void showUpX5WebView(Object obj, JSONObject jSONObject, boolean z, boolean z2) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void showUpX5WebView(Object obj, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void startTrackThread() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void stopTrackScreenOrientation() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void stopTrackThread() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void track(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void track(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackAppCrash() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackChannelEvent(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackChannelEvent(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackEventFromH5(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackEventFromH5(String str, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.internal.IFragmentAPI
    public void trackFragmentAppViewScreen() {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackInstallation(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackInstallation(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackInstallation(String str, JSONObject jSONObject, boolean z) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackSignUp(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackSignUp(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackTimer(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    @Deprecated
    public void trackTimer(String str, TimeUnit timeUnit) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerBegin(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerBegin(String str, TimeUnit timeUnit) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerEnd(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackTimerEnd(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewAppClick(View view) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewAppClick(View view, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewScreen(Activity activity) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewScreen(Object obj) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void trackViewScreen(String str, JSONObject jSONObject) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void unregisterSuperProperty(String str) {
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public JSONObject getPresetProperties() {
        return new JSONObject();
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public List<Class> getIgnoredViewTypeList() {
        return new ArrayList();
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public void removeTimer(String str) {
        super.removeTimer(str);
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public String trackTimerStart(String str) {
        Log.i("meme", "走到空实现。。。");
        return "";
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public JSONObject getLastScreenTrackProperties() {
        return new JSONObject();
    }

    @Override // com.slzhibo.library.analytics.SensorsDataAPI, com.slzhibo.library.analytics.ISensorsDataAPI
    public JSONObject getSuperProperties() {
        return new JSONObject();
    }
}
