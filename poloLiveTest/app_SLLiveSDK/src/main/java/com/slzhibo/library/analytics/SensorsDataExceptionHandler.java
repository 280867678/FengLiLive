package com.slzhibo.library.analytics;

import android.os.Process;

import com.slzhibo.library.analytics.data.DbAdapter;
import com.slzhibo.library.analytics.util.SensorsDataTimer;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes6.dex */
public class SensorsDataExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final int SLEEP_TIMEOUT_MS = 3000;
    private static boolean isTrackCrash = false;
    private static SensorsDataExceptionHandler sInstance;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    private SensorsDataExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static synchronized void init() {
        synchronized (SensorsDataExceptionHandler.class) {
            if (sInstance == null) {
                sInstance = new SensorsDataExceptionHandler();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enableAppCrash() {
        isTrackCrash = true;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, final Throwable th) {
        try {
            SensorsDataAPI.allInstances(new SensorsDataAPI.InstanceProcessor() { // from class: com.slzhibo.library.analytics.SensorsDataExceptionHandler.1
                @Override // com.slzhibo.library.analytics.SensorsDataAPI.InstanceProcessor
                public void process(SensorsDataAPI sensorsDataAPI) {
                    if (SensorsDataExceptionHandler.isTrackCrash) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                StringWriter stringWriter = new StringWriter();
                                PrintWriter printWriter = new PrintWriter(stringWriter);
                                th.printStackTrace(printWriter);
                                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                                    cause.printStackTrace(printWriter);
                                }
                                printWriter.close();
                                jSONObject.put(AopConstants.APP_CRASHED_REASON, stringWriter.toString());
                            } catch (Exception unused) {
                            }
                            sensorsDataAPI.track(AopConstants.APP_CRASH_EVENT_NAME, jSONObject);
                        } catch (Exception unused2) {
                        }
                    }
                    SensorsDataTimer.getInstance().shutdownTimerTask();
                    DbAdapter.getInstance().commitAppEndTime(System.currentTimeMillis());
                    DbAdapter.getInstance().commitActivityCount(0);
                    sensorsDataAPI.flushSync();
                }
            });
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException unused) {
            }
            if (this.mDefaultExceptionHandler != null) {
                this.mDefaultExceptionHandler.uncaughtException(thread, th);
            } else {
                killProcessAndExit();
            }
        } catch (Exception unused2) {
        }
    }

    private void killProcessAndExit() {
        try {
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Exception unused) {
        }
    }
}
