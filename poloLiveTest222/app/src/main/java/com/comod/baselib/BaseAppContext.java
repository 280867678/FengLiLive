package com.comod.baselib;

//package com.comod.baselib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import java.util.Stack;

/* loaded from: classes.dex */
public class BaseAppContext extends Application {

    /* renamed from: b */
    public static BaseAppContext f1509b;

    /* renamed from: c */
    public static Stack<Activity> f1510c = new Stack<>();

    /* renamed from: a */
    public static BaseAppContext m20167a() {
        return f1509b;
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        MultiDex.install(this);
        super.attachBaseContext(context);
    }

    /* renamed from: b */
    public void mo20165b(Activity activity) {
        if (activity != null) {
            try {
                if (f1510c != null) {
                    f1510c.remove(activity);
                }
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f1509b = this;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    /* renamed from: a */
    public void mo20166a(Activity activity) {
        try {
            if (f1510c == null) {
                f1510c = new Stack<>();
            }
            f1510c.add(activity);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

