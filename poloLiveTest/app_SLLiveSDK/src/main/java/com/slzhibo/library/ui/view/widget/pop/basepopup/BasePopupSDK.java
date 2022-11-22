package com.slzhibo.library.ui.view.widget.pop.basepopup;



import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* loaded from: classes11.dex */
public final class BasePopupSDK {
    private static volatile Application mApplicationContext;
    private WeakReference<Activity> mTopActivity;

    /* loaded from: classes11.dex */
    private static class SingletonHolder {
        private static BasePopupSDK INSTANCE = new BasePopupSDK();

        private SingletonHolder() {
        }
    }

    private BasePopupSDK() {
    }

    public synchronized void init(Context context) {
        if (mApplicationContext == null) {
            mApplicationContext = (Application) context.getApplicationContext();
            regLifeCallback();
        }
    }

    public Activity getTopActivity() {
        WeakReference<Activity> weakReference = this.mTopActivity;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    private void regLifeCallback() {
        mApplicationContext.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.slzhibo.library.ui.view.widget.pop.basepopup.BasePopupSDK.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                if (BasePopupSDK.this.mTopActivity != null) {
                    BasePopupSDK.this.mTopActivity.clear();
                }
                BasePopupSDK.this.mTopActivity = new WeakReference(activity);
            }
        });
    }

    public static BasePopupSDK getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static Application getApplication() {
        return mApplicationContext;
    }
}

