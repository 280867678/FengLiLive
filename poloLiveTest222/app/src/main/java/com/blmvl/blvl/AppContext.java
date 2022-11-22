package com.blmvl.blvl;

//package com.blmvl.blvl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;

import com.blmvl.blvl.bean.DaoMaster;
import com.blmvl.blvl.bean.DaoSession;
import com.blmvl.blvl.service.UploadService;
import com.comod.baselib.BaseAppContext;
//import com.flurry.android.FlurryAgent;
//import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import java.util.Stack;
//import p067e.p103c.p104a.lambda;
//import p067e.p103c.p104a.p106d.DaoMaster;
//import p067e.p103c.p104a.p106d.DaoSession;
//import p067e.p103c.p104a.p113k.CustomHttpDataSource;
//import p067e.p130f.p131a.p135d.L;
//import p067e.p170j.p171a.p172a.p196r0.MediaSource;
//import p067e.p170j.p171a.p172a.p196r0.p201k0.HlsMediaSource;
//import p067e.p170j.p171a.p172a.p217v0.AbstractC4625j;
//import p067e.p291s.p292a.p294e.CacheFactory;
//import p067e.p291s.p292a.p297h.PlayerFactory;
//import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
//import tv.danmaku.ijk.media.exo2.ExoMediaSourceInterceptListener;
//import tv.danmaku.ijk.media.exo2.ExoPlayerCacheManager;
//import tv.danmaku.ijk.media.exo2.ExoSourceManager;

/* loaded from: classes.dex */
public class AppContext extends BaseAppContext {

    /* renamed from: e */
    public static AppContext f336e;

    /* renamed from: f */
    public static Stack<Activity> f337f = new Stack<>();

    /* renamed from: d */
    public DaoSession f338d;

    /* renamed from: com.blmvl.blvl.AppContext$a */
    /* loaded from: classes.dex */
//    public class C0589a implements ExoMediaSourceInterceptListener {
//        public C0589a(AppContext appContext) {
//        }
//
//        /* renamed from: a */
//        public static /* synthetic */ AbstractC4625j m21295a(int i) {
//            return new CustomHttpDataSource("dd", null);
//        }
//
//        @Override // tv.danmaku.ijk.media.exo2.ExoMediaSourceInterceptListener
//        public MediaSource getMediaSource(String str, boolean z, boolean z2, boolean z3, File file) {
//            if (!str.contains(".m3u8")) {
//                return null;
//            }
//            return new HlsMediaSource.C4544b(lambda.f12179a).m7283a(Uri.parse(str));
//        }
//    }

    /* renamed from: a */
    public static AppContext m21299a() {
        return f336e;
    }

    @Override // com.comod.baselib.BaseAppContext, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        MultiDex.install(this);
        super.attachBaseContext(context);
    }

    @Override // com.comod.baselib.BaseAppContext
    /* renamed from: b */
    public void mo20165b(Activity activity) {
        if (activity != null) {
            try {
                if (f337f != null) {
                    f337f.remove(activity);
                }
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            } catch (Throwable th) {
                L.m9079a(th.toString());
            }
        }
    }

    /* renamed from: c */
    public DaoSession m21297c() {
        return this.f338d;
    }

    /* renamed from: d */
    public final void m21296d() {
        this.f338d = new DaoMaster(new DaoMaster.DevOpenHelper(this, "boluo_vl.db").getWritableDatabase()).newSession();
    }

    @Override // com.comod.baselib.BaseAppContext, android.app.Application
    public void onCreate() {
        super.onCreate();
        f336e = this;
//        new FlurryAgent.Builder().withLogEnabled(true).build(this, "N4C76RRY8FSFNZ83F3MP");
//        PlayerFactory.m4632a(Exo2PlayerManager.class);
//        CacheFactory.m4683a(ExoPlayerCacheManager.class);
//        GSYVideoType.setShowType(0);
//        ExoSourceManager.setExoMediaSourceInterceptListener(new C0589a(this));
        m21296d();
        startService(new Intent(this, UploadService.class));
    }

    @Override // com.comod.baselib.BaseAppContext, android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override // com.comod.baselib.BaseAppContext, android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    @Override // com.comod.baselib.BaseAppContext
    /* renamed from: a */
    public void mo20166a(Activity activity) {
        try {
            if (f337f == null) {
                f337f = new Stack<>();
            }
            f337f.add(activity);
        } catch (Throwable th) {
            L.m9079a(th.toString());
        }
    }

    /* renamed from: b */
    public void m21298b() {
        Stack<Activity> stack = f337f;
        if (!(stack == null || stack.isEmpty())) {
            int size = f337f.size();
            for (int i = 0; i < size; i++) {
                if (f337f.get(i) != null) {
                    try {
                        Activity activity = f337f.get(i);
                        if (!activity.isFinishing()) {
                            activity.finish();
                        }
                    } catch (Exception e) {
                        L.m9079a(e.toString());
                    }
                }
            }
            f337f.clear();
        }
    }
}

