package com.slzhibo.library.utils;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.CacheDiskUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
//import com.slzhibo.library.R;
import com.slzhibo.library.R;
import com.slzhibo.library.analytics.CustomProperties;
import com.slzhibo.library.analytics.GetAnalyticsDataListener;
import com.slzhibo.library.analytics.SAConfigOptions;
import com.slzhibo.library.analytics.SensorsDataAPI;
import com.slzhibo.library.analytics.data.DbAdapter;
import com.slzhibo.library.analytics.util.JSONUtils;
import com.slzhibo.library.analytics.util.NetworkUtils;
import com.slzhibo.library.analytics.util.SASystemUtils;
import com.slzhibo.library.http.EventConfigRetrofit;
import com.slzhibo.library.http.EventReportRetrofit;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.LogEventConfigCacheEntity;
import com.slzhibo.library.model.LogEventConfigEntity;
import com.slzhibo.library.model.db.AnchorLiveDataEntity;
import com.slzhibo.library.model.db.LiveDataEntity;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SensorsDataAPIUtils {
    public static String TAG = "SensorsData";
    private static final AtomicInteger errorCount = new AtomicInteger(0);

    public static void initSensorsDataAPI(final Application application) {
        if (application != null && AppUtils.isEnableLogEventReport() && AppUtils.isEventStatisticsService()) {
            SAConfigOptions sAConfigOptions = new SAConfigOptions();
            sAConfigOptions.setAutoTrackEventType(11);
            sAConfigOptions.enableTrackAppCrash();
            SensorsDataAPI.startWithConfigOptions(application, sAConfigOptions);
            SensorsDataAPI.sharedInstance().trackFragmentAppViewScreen();
            initLogEventConfig();
            LogEventConfigCacheEntity logEventConfigCacheEntity = (LogEventConfigCacheEntity) CacheDiskUtils.getInstance().getParcelable(ConstantUtils.LOG_EVENT_KEY, LogEventConfigCacheEntity.CREATOR);
            if (logEventConfigCacheEntity != null) {
                setLogMinFlushInterval(logEventConfigCacheEntity.getEventList());
            }
            SensorsDataAPI.sharedInstance().setCustomExtraProperties(new CustomProperties() {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass1 */

                @Override // com.slzhibo.library.analytics.CustomProperties
                public String getRuleString() {
                    return "com.slzhibo.library";
                }

                @Override // com.slzhibo.library.analytics.CustomProperties
                public JSONObject getAppPublicProperties(String str) {
                    JSONObject jSONObject = new JSONObject();
                    String networkType = NetworkUtils.networkType(application);
                    try {
                        String str2 = "1";
                        jSONObject.put("wifi", "WIFI".equals(networkType) ? str2 : "0");
                        jSONObject.put("networkType", networkType);
                        jSONObject.put("openId", UserInfoManager.getInstance().getAppOpenId());
                        jSONObject.put("time", String.valueOf(System.currentTimeMillis()));
                        jSONObject.put("isFirstDay", String.valueOf(SASystemUtils.isFirstDay()));
                        if (!TextUtils.equals(str, "AppInstall")) {
                            str2 = "0";
                        }
                        jSONObject.put("isFirstTime", str2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return jSONObject;
                }

                /* JADX WARNING: Removed duplicated region for block: B:18:0x003d A[ADDED_TO_REGION] */
                @Override // com.slzhibo.library.analytics.CustomProperties
                public void dealRawProperties(String str, JSONObject jSONObject) {
                    char c;
                    int hashCode = str.hashCode();
                    if (hashCode != 401582162) {
                        if (hashCode != 1221389025) {
                            if (hashCode == 1967735578 && str.equals("AppEnd")) {
                                c = 1;
                                if (c != 0 || c == 1) {
                                    jSONObject.remove("screenName");
                                    jSONObject.remove(ConstantUtils.WEB_VIEW_TITLE);
                                } else if (c == 2) {
                                    jSONObject.remove("screenName");
                                    return;
                                } else {
                                    return;
                                }
                            }
                        } else if (str.equals("AppStart")) {
                            c = 0;
                            if (c != 0) {
                            }
                            jSONObject.remove("screenName");
                            jSONObject.remove(ConstantUtils.WEB_VIEW_TITLE);
                        }
                    } else if (str.equals("AppViewScreen")) {
                        c = 2;
                        if (c != 0) {
                        }
                        jSONObject.remove("screenName");
                        jSONObject.remove(ConstantUtils.WEB_VIEW_TITLE);
                    }
                    c = 65535;
                    if (c != 0) {
                    }
                    jSONObject.remove("screenName");
                    jSONObject.remove(ConstantUtils.WEB_VIEW_TITLE);
                }
            });
            SensorsDataAPI.sharedInstance().setAnalyticsDataListener(new GetAnalyticsDataListener() {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass2 */

                @Override // com.slzhibo.library.analytics.GetAnalyticsDataListener
                public void getAnalyticsDataList(List<JSONObject> list) {
                    String str = SensorsDataAPIUtils.TAG;
                    LogUtils.iTag(str, "rawList = " + list);
                    LogEventConfigCacheEntity logEventConfigCacheEntity = (LogEventConfigCacheEntity) CacheDiskUtils.getInstance().getParcelable(ConstantUtils.LOG_EVENT_KEY, LogEventConfigCacheEntity.CREATOR);
                    if (logEventConfigCacheEntity == null) {
                        LogUtils.iTag(SensorsDataAPIUtils.TAG, "没有上报配置，就都不上报");
                        SensorsDataAPIUtils.deleteRedundantData(list);
                        return;
                    }
                    List<LogEventConfigEntity> eventList = logEventConfigCacheEntity.getEventList();
                    for (int i = 0; i < eventList.size(); i++) {
                        LogEventConfigEntity logEventConfigEntity = eventList.get(i);
                        ArrayList arrayList = new ArrayList();
                        String events = logEventConfigEntity.getEvents();
                        int timeLimit = logEventConfigEntity.getTimeLimit();
                        int quantityLimit = logEventConfigEntity.getQuantityLimit();
                        if (quantityLimit <= 0) {
                            quantityLimit = 100;
                        }
                        String id = logEventConfigEntity.getId();
                        List asList = Arrays.asList(events.split(","));
                        for (JSONObject jSONObject : list) {
                            if (asList.contains(jSONObject.optString("eventId"))) {
                                arrayList.add(jSONObject);
                            }
                        }
                        if (arrayList.size() != 0) {
                            list.removeAll(arrayList);
                            SensorsDataAPIUtils.assertUploadData(arrayList, timeLimit, quantityLimit, id, application);
                        }
                    }
                    SensorsDataAPIUtils.deleteRedundantData(list);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void deleteRedundantData(List<JSONObject> list) {
        if (list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (JSONObject jSONObject : list) {
                arrayList.add((String) jSONObject.remove("DB_ID_KEY"));
                String str = TAG;
                LogUtils.iTag(str, "删除不在配置内的数据：" + jSONObject.optString("eventId"));
            }
            DbAdapter.getInstance().cleanupEvents(arrayList);
        }
    }

    public static void initLogEventConfig() {
        if (AppUtils.isEventStatisticsService()) {
            EventConfigRetrofit.getInstance().getApiService().getLogEventConfigService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<LogEventConfigEntity>>() {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass5 */
            }).onErrorResumeNext(new HttpResultFunction<List<LogEventConfigEntity>>() {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass4 */
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<List<LogEventConfigEntity>>(false) {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass3 */

                public void accept(List<LogEventConfigEntity> list) {

                    LogUtils.json(SensorsDataAPIUtils.TAG, list);
                    LogEventUtils.eventName = SensorsDataAPIUtils.getEventNames(list);
                    LogEventConfigCacheEntity logEventConfigCacheEntity = new LogEventConfigCacheEntity();
                    logEventConfigCacheEntity.setEventList(list);
                    CacheDiskUtils.getInstance().put(ConstantUtils.LOG_EVENT_KEY, logEventConfigCacheEntity);
                    SensorsDataAPIUtils.setLogMinFlushInterval(list);
                    SensorsDataAPIUtils.initErrorReportData();
                    Log.e("SensorsDataAPIUtils：","initLogEventConfig  accept");
                }

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onError(Throwable th) {
                    super.onError(th);
                    Log.e("SensorsDataAPIUtils：","initLogEventConfig  onError");
                    LogUtils.iTag(SensorsDataAPIUtils.TAG, th.getMessage());
                    SensorsDataAPIUtils.initErrorReportData();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static List<String> getEventNames(List<LogEventConfigEntity> list) {
        ArrayList arrayList = new ArrayList();
        for (LogEventConfigEntity logEventConfigEntity : list) {
            arrayList.addAll(Arrays.asList(logEventConfigEntity.getEvents().split(",")));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static void initErrorReportData() {
        uploadAppInstall();
        LiveDataEntity liveData = DBUtils.getLiveData();
        if (liveData != null) {
            LogEventUtils.uploadLeaveRoom(liveData);
            DBUtils.deleteAll(LiveDataEntity.class);
        }
        AnchorLiveDataEntity anchorLiveData = DBUtils.getAnchorLiveData();
        if (anchorLiveData != null) {
            LogEventUtils.uploadEndLive(anchorLiveData);
            DBUtils.deleteAll(AnchorLiveDataEntity.class);
        }
    }

    private static void uploadAppInstall() {
        boolean z = SPUtils.getInstance().getBoolean("IsAppInstall", true);
        if (AppUtils.isEnableLogEventReport() && z) {
            LogEventUtils.uploadAppInstall();
            SPUtils.getInstance().put("IsAppInstall", false);
        }
    }

    /* access modifiers changed from: private */
    public static void setLogMinFlushInterval(List<LogEventConfigEntity> list) {
        if (list != null) {
            int i = 0;
            for (LogEventConfigEntity logEventConfigEntity : list) {
                int timeLimit = logEventConfigEntity.getTimeLimit();
                if (i == 0) {
                    i = timeLimit;
                }
                i = Math.min(i, timeLimit);
            }
            if (i > 0) {
                SensorsDataAPI.sharedInstance().setFlushInterval(i * 1000);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void assertUploadData(List<JSONObject> list, int i, int i2, String str, Application application) {
        long j = SPUtils.getInstance(ConstantUtils.UPLOAD_TIME_KEY).getLong(str);
        if (list.size() >= i2 || System.currentTimeMillis() - j >= ((long) (i * 1000))) {
            uploadLogEvent(list, application);
            SPUtils.getInstance(ConstantUtils.UPLOAD_TIME_KEY).put(str, System.currentTimeMillis());
        }
    }

    private static void uploadLogEvent(List<JSONObject> list, Application application) {
        final ArrayList arrayList = new ArrayList();
        for (JSONObject jSONObject : list) {
            arrayList.add(jSONObject.remove("DB_ID_KEY"));
        }
        Map<String, Object> deviceInfo = SASystemUtils.getDeviceInfo(application);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("device", new JSONObject(deviceInfo));
            jSONObject2.put("events", new JSONArray((Collection) list));
            jSONObject2.put("appId", UserInfoManager.getInstance().getAppId());
            jSONObject2.put("appVersion", SASystemUtils.getAppVersionName(application));
            jSONObject2.put("lib", application.getString(R.string.fq_live_lib));
            jSONObject2.put("libVersion", SASystemUtils.getLibVersion());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.json(TAG, JSONUtils.formatJson(jSONObject2.toString()));
        Map<String, Object> map = (Map) GsonUtils.fromJson(jSONObject2.toString(), Map.class);
        if (AppUtils.isEventStatisticsService()) {
            EventReportRetrofit.getInstance().getApiService().uploadLogEventService(map).map(new ServerResultFunction<Object>() {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass8 */
            }).onErrorResumeNext(new HttpResultFunction<LogEventConfigEntity>() {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass7 */
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<Object>(false) {
                /* class com.slzhibo.library.utils.SensorsDataAPIUtils.AnonymousClass6 */

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onSubscribe(Disposable disposable) {
                    super.onSubscribe(disposable);
                    LogUtils.iTag(SensorsDataAPIUtils.TAG, "数据统计开始上报：");
                }

                @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                public void accept(Object obj) {
                    String str = SensorsDataAPIUtils.TAG;
                    LogUtils.iTag(str, "数据统计上报成功：" + arrayList);
                    SensorsDataAPIUtils.errorCount.set(0);
                    DbAdapter.getInstance().cleanupEvents(arrayList);
                }

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onError(Throwable th) {
                    super.onError(th);
                    LogUtils.iTag(SensorsDataAPIUtils.TAG, "数据统计上报失败");
                    SensorsDataAPIUtils.errorCount.incrementAndGet();
                    if (SensorsDataAPIUtils.errorCount.get() >= 3) {
                        SensorsDataAPI.sharedInstance().deleteAll();
                    }
                }
            });
        }
    }
}
