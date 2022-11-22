package com.blmvl.blvl.service;

//package com.blmvl.blvl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.blmvl.blvl.L;
//import p067e.p103c.p104a.p112j.UploadManager;
//import p067e.p130f.p131a.p135d.L;

/* loaded from: classes.dex */
public class UploadService extends Service {

    /* renamed from: b */
    public static UploadManager f1466b;

    /* renamed from: a */
    public static UploadManager m20178a() {
        return f1466b;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        f1466b = new UploadManager(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        L.m9079a("------UploadService-----onDestroy------------");
        UploadManager bVar = f1466b;
        if (bVar != null) {
            bVar.m9625b();
            f1466b = null;
        }
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (f1466b == null) {
            f1466b = new UploadManager(this);
        }
    }
}

