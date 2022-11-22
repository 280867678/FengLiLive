package com.slzhibo.library.analytics.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.slzhibo.library.analytics.data.persistent.PersistentAppEndData;
import com.slzhibo.library.analytics.data.persistent.PersistentAppPaused;
import com.slzhibo.library.analytics.data.persistent.PersistentAppStartTime;
import com.slzhibo.library.analytics.data.persistent.PersistentIdentity;
import com.slzhibo.library.analytics.data.persistent.PersistentRemoteSDKConfig;
import com.slzhibo.library.analytics.data.persistent.PersistentSessionIntervalTime;
import com.slzhibo.library.analytics.data.persistent.PersistentSuperProperties;

import java.util.concurrent.Future;

/* loaded from: classes6.dex */
public class PersistentLoader {
    private static Context context;
    private static volatile PersistentLoader instance;
    private static Future<SharedPreferences> storedPreferences;

    /* loaded from: classes6.dex */
    public interface PersistentName {
        public static final String APP_END_DATA = "app_end_data";
        public static final String APP_PAUSED_TIME = "app_end_time";
        public static final String APP_SESSION_TIME = "session_interval_time";
        public static final String APP_START_TIME = "app_start_time";
        public static final String DISTINCT_ID = "events_distinct_id";
        public static final String FIRST_DAY = "first_day";
        public static final String FIRST_INSTALL = "first_track_installation";
        public static final String FIRST_INSTALL_CALLBACK = "first_track_installation_with_callback";
        public static final String FIRST_START = "first_start";
        public static final String LOGIN_ID = "events_login_id";
        public static final String REMOTE_CONFIG = "sensorsdata_sdk_configuration";
        public static final String SUPER_PROPERTIES = "super_properties";
    }

    private PersistentLoader(Context context2) {
        context = context2.getApplicationContext();
        storedPreferences = new SharedPreferencesLoader().loadPreferences(context2, "com.slzhibo.live.analytics.SensorsDataAPI");
    }

    public static PersistentLoader initLoader(Context context2) {
        if (instance == null) {
            instance = new PersistentLoader(context2);
        }
        return instance;
    }

    public static PersistentIdentity loadPersistent(String str) {
        if (instance == null) {
            throw new RuntimeException("you should call 'PersistentLoader.initLoader(Context)' first");
        } else if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            char c = 65535;
            switch (str.hashCode()) {
                case -1173524450:
                    if (str.equals(PersistentName.APP_SESSION_TIME)) {
                        c = 2;
                        break;
                    }
                    break;
                case -951089033:
                    if (str.equals(PersistentName.SUPER_PROPERTIES)) {
                        c = 5;
                        break;
                    }
                    break;
                case 791585128:
                    if (str.equals(PersistentName.APP_START_TIME)) {
                        c = 3;
                        break;
                    }
                    break;
                case 947194773:
                    if (str.equals(PersistentName.REMOTE_CONFIG)) {
                        c = 4;
                        break;
                    }
                    break;
                case 1521941740:
                    if (str.equals(PersistentName.APP_END_DATA)) {
                        c = 0;
                        break;
                    }
                    break;
                case 1522425871:
                    if (str.equals(PersistentName.APP_PAUSED_TIME)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                return new PersistentAppEndData(storedPreferences);
            }
            if (c == 1) {
                return new PersistentAppPaused(storedPreferences);
            }
            if (c == 2) {
                return new PersistentSessionIntervalTime(storedPreferences);
            }
            if (c == 3) {
                return new PersistentAppStartTime(storedPreferences);
            }
            if (c == 4) {
                return new PersistentRemoteSDKConfig(storedPreferences);
            }
            if (c != 5) {
                return null;
            }
            return new PersistentSuperProperties(storedPreferences);
        }
    }
}
