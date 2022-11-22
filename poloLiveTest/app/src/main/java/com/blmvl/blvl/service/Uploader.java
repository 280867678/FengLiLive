package com.blmvl.blvl.service;

//package p067e.p103c.p104a.p112j;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blmvl.blvl.L;
import com.blmvl.blvl.bean.AppConfig;
import com.blmvl.blvl.bean.AppUser;
import com.blmvl.blvl.bean.UploadVideoBean;
import com.blmvl.blvl.bean.UploadVideoTaskBean;
//import com.blmvl.blvl.db.UploadVideoTaskBeanDao;
import com.blmvl.blvl.crypt.EncryptManager;
import com.blmvl.blvl.util.ContentUriUtil;
import com.blmvl.blvl.util.CustomWordUtil;
import com.blmvl.blvl.util.DbUtil;
import com.blmvl.blvl.util.HttpCallback;
import com.blmvl.blvl.httpUtil.HttpUtil;
import com.blmvl.blvl.util.ToastUtil;
import com.blmvl.blvl.util.UploadImgUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;
//import p067e.p103c.p104a.p111i.HttpCallback;
//import p067e.p103c.p104a.p111i.HttpUtil;
//import p067e.p103c.p104a.p113k.CustomWordUtil;
//import p067e.p103c.p104a.p113k.DbUtil;
//import p067e.p103c.p104a.p113k.EncryptManager;
//import p067e.p103c.p104a.p113k.UploadImgUtil;
//import p067e.p130f.p131a.p135d.ContentUriUtil;
//import p067e.p130f.p131a.p135d.L;
//import p067e.p130f.p131a.p135d.ToastUtil;

/* renamed from: e.c.a.j.c */
/* loaded from: classes.dex */
public class Uploader {

    /* renamed from: b */
    public AbstractC4228c f12987b;

    /* renamed from: c */
    public UploadVideoTaskBean f12988c;

    /* renamed from: d */
    public C4229d f12989d;

    /* renamed from: f */
    public ThreadPoolExecutor f12991f;

    /* renamed from: g */
    public int f12992g;

    /* renamed from: i */
    public Context f12994i;

    /* renamed from: e */
    public boolean f12990e = false;

    /* renamed from: a */
    public HashMap<String, UploadListener> f12986a = new HashMap<>();

    /* renamed from: h */
    public HandlerC4227b f12993h = new HandlerC4227b();

    /* compiled from: Uploader.java */
    /* renamed from: e.c.a.j.c$b */
    /* loaded from: classes.dex */
    public class HandlerC4227b extends Handler {
        public HandlerC4227b() {
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                Uploader.this.m9602i();
            } else if (i == 1) {
                Uploader.this.m9598k();
            } else if (i == 2) {
                Uploader.this.m9608f();
            } else if (i == 3) {
                Uploader.this.m9616b();
            } else if (i == 4) {
                Uploader.this.m9597l();
            }
        }
    }

    /* compiled from: Uploader.java */
    /* renamed from: e.c.a.j.c$c */
    /* loaded from: classes.dex */
    public interface AbstractC4228c {
        /* renamed from: a */
        void mo9596a(long j);
    }

    /* compiled from: Uploader.java */
    /* renamed from: e.c.a.j.c$d */
    /* loaded from: classes.dex */
    public class C4229d extends Thread {

        /* compiled from: Uploader.java */
        /* renamed from: e.c.a.j.c$d$a */
        /* loaded from: classes.dex */
        public class C4230a extends StringCallback {
            public C4230a() {
            }

            @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
            public void onError(Response<String> response) {
                super.onError(response);
                C4229d.this.m9588d();
            }

            @Override // com.lzy.okgo.callback.Callback
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject parseObject = JSON.parseObject(response.body());
                    if (parseObject == null || !parseObject.containsKey("code") || parseObject.getInteger("code").intValue() != 1) {
                        C4229d.this.m9588d();
                    } else {
                        String string = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                        if (!TextUtils.isEmpty(string)) {
                            Uploader.this.f12988c.setCoverUrl(string);
                            DbUtil.m9460a().update(Uploader.this.f12988c);
                            C4229d.this.m9584f();
                        } else {
                            C4229d.this.m9588d();
                        }
                    }
                } catch (Exception unused) {
                    C4229d.this.m9588d();
                }
            }
        }

        /* compiled from: Uploader.java */
        /* renamed from: e.c.a.j.c$d$b */
        /* loaded from: classes.dex */
        public class C4231b extends StringCallback {
            public C4231b() {
            }

            @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
            public void onError(Response<String> response) {
                super.onError(response);
                C4229d.this.m9583g();
            }

            @Override // com.lzy.okgo.callback.Callback
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject parseObject = JSON.parseObject(response.body());
                    if (parseObject == null || !parseObject.containsKey("code") || parseObject.getInteger("code").intValue() != 1) {
                        C4229d.this.m9583g();
                    } else {
                        Uploader.this.f12988c.setVideoUrl(parseObject.getString(NotificationCompat.CATEGORY_MESSAGE));
                        DbUtil.m9460a().update(Uploader.this.f12988c);
                        C4229d.this.m9586e();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    C4229d.this.m9583g();
                }
            }

            @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                try {
                    Uploader.this.f12992g = (int) ((progress.currentSize * 100) / progress.totalSize);
                    L.m9079a("Progress------>" + Uploader.this.f12992g);
                    L.m9079a("onUpload------>" + Uploader.this.f12990e);
                    if (Uploader.this.f12993h != null) {
                        Uploader.this.f12993h.sendEmptyMessage(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /* compiled from: Uploader.java */
        /* renamed from: e.c.a.j.c$d$c */
        /* loaded from: classes.dex */
        public class C4232c extends HttpCallback {

            /* renamed from: a */
            public final /* synthetic */ UploadVideoBean f12999a;

            public C4232c(UploadVideoBean uploadVideoBean) {
                this.f12999a = uploadVideoBean;
            }

            @Override // p067e.p103c.p104a.p111i.HttpCallback
            public void onError() {
                super.onError();
                C4229d.this.m9590c();
            }

            @Override // p067e.p103c.p104a.p111i.HttpCallback
            public void onException(int i, String str) {
                super.onException(i, str);
                if (!TextUtils.isEmpty(str)) {
                    ToastUtil.m9102c(Uploader.this.f12994i, str);
                }
                C4229d.this.m9590c();
            }

            @Override // p067e.p103c.p104a.p111i.HttpCallback
            public void onNetworkError() {
                super.onNetworkError();
                C4229d.this.m9590c();
            }

            @Override // p067e.p103c.p104a.p111i.HttpCallback
            public void onSuccess(String str, String str2, boolean z, boolean z2) {
                super.onSuccess(str, str2, z, z2);
                try {
                    Uploader.this.f12990e = false;
                    DbUtil.m9460a().delete( Uploader.this.f12988c);
                    if (Uploader.this.f12993h != null) {
                        Uploader.this.f12993h.sendEmptyMessage(4);
                    }
                    ToastUtil.m9102c(Uploader.this.f12994i, String.format("%s 上传成功", CustomWordUtil.m9462a(this.f12999a.title)));
                } catch (Exception unused) {
                    C4229d.this.m9590c();
                }
            }
        }

        public C4229d() {
        }

        /* renamed from: f */
        public final void m9584f() {
            String videoUploadUrl = AppConfig.getInstance().getConfig().getVideoUploadUrl();
            if (TextUtils.isEmpty(videoUploadUrl)) {
                m9583g();
                return;
            }
            String localVideoUrl = Uploader.this.f12988c.getLocalVideoUrl();
            if (Build.VERSION.SDK_INT >= 29) {
                localVideoUrl = ContentUriUtil.m9138a(Uploader.this.f12994i, Uri.parse(localVideoUrl));
            }
            L.m9079a("----->" + localVideoUrl);
            ((PostRequest) ((PostRequest) OkGo.post(videoUploadUrl).params(m9593a(localVideoUrl))).tag(String.format("%s%s", "upload_video", String.valueOf(Uploader.this.f12988c.getTaskId())))).isMultipart(true).execute(new C4231b());
        }

        /* renamed from: g */
        public final void m9583g() {
            Uploader.this.f12990e = false;
            Uploader.this.f12993h.sendEmptyMessage(3);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Uploader.this.f12990e = true;
            m9592b();
        }

        /* renamed from: a */
        public HttpParams m9593a(String str) {
            long currentTimeMillis = System.currentTimeMillis();
            String uploadKey = AppConfig.getInstance().getConfig().getUploadKey();
            EncryptManager a = EncryptManager.m9436a();
            String a2 = a.m9435a(currentTimeMillis + uploadKey);
            HttpParams httpParams = new HttpParams();
            httpParams.put("uuid", AppUser.getInstance().getUser().getUid(), new boolean[0]);
            httpParams.put("timestamp", currentTimeMillis, new boolean[0]);
            httpParams.put("video", new File(str));
            httpParams.put("sign", a2, new boolean[0]);
            return httpParams;
        }

        /* renamed from: b */
        public final void m9592b() {
            String b = UploadImgUtil.m9505b();
            if (TextUtils.isEmpty(b)) {
                m9588d();
                return;
            }
            L.m9079a("----->" + Uploader.this.f12988c.getLocalCoverUrl());
            ((PostRequest) ((PostRequest) OkGo.post(b).params(UploadImgUtil.m9504b(new File(Uploader.this.f12988c.getLocalCoverUrl())))).tag(String.format("%s%s", "upload_video_cover", String.valueOf(Uploader.this.f12988c.getTaskId())))).execute(new C4230a());
        }

        /* renamed from: c */
        public final void m9590c() {
            Uploader.this.f12990e = false;
            Uploader.this.f12993h.sendEmptyMessage(3);
        }

        /* renamed from: d */
        public final void m9588d() {
            Uploader.this.f12990e = false;
            Uploader.this.f12993h.sendEmptyMessage(3);
        }

        /* renamed from: e */
        public final void m9586e() {
            UploadVideoBean uploadVideoBean = new UploadVideoBean();
            uploadVideoBean.title = Uploader.this.f12988c.getTitle();
            uploadVideoBean.thumbUrl = Uploader.this.f12988c.getCoverUrl();
            uploadVideoBean.videoUrl = Uploader.this.f12988c.getVideoUrl();
            uploadVideoBean.tags = Uploader.this.f12988c.getTags();
            uploadVideoBean.thumbHeight = Uploader.this.f12988c.getCoverHeight();
            uploadVideoBean.thumbWidth = Uploader.this.f12988c.getCoverWidth();
            uploadVideoBean.coins = Uploader.this.f12988c.getVideoPrice();
            HttpUtil.m9768a(uploadVideoBean, new C4232c(uploadVideoBean));
        }

        /* renamed from: a */
        public void m9595a() {
            Uploader.this.f12990e = false;
            Uploader.this.f12993h.sendEmptyMessage(1);
        }
    }

    public Uploader(Context context, UploadVideoTaskBean uploadVideoTaskBean, ThreadPoolExecutor threadPoolExecutor, String str, boolean z) {
        this.f12991f = threadPoolExecutor;
        this.f12988c = uploadVideoTaskBean;
        this.f12994i = context;
        if (z) {
            m9606g();
        }
    }

    /* renamed from: k */
    public final void m9598k() {
        if (!this.f12986a.isEmpty()) {
            for (UploadListener aVar : this.f12986a.values()) {
                aVar.mo9636b(m9612d(), m9610e());
            }
        }
    }

    /* renamed from: l */
    public final void m9597l() {
        if (!this.f12986a.isEmpty()) {
            for (UploadListener aVar : this.f12986a.values()) {
                aVar.mo9634d(m9612d(), m9610e());
            }
        }
        AbstractC4228c cVar = this.f12987b;
        if (cVar != null) {
            cVar.mo9596a(m9614c());
        }
    }

    /* renamed from: b */
    public final void m9616b() {
        if (!this.f12986a.isEmpty()) {
            for (UploadListener aVar : this.f12986a.values()) {
                aVar.mo9635c(m9612d(), m9610e());
            }
        }
    }

    /* renamed from: c */
    public long m9614c() {
        return this.f12988c.getTaskId();
    }

    /* renamed from: d */
    public UploadVideoTaskBean m9612d() {
        this.f12988c.setProgress(this.f12992g);
        return this.f12988c;
    }

    /* renamed from: e */
    public boolean m9610e() {
        return this.f12990e;
    }

    /* renamed from: f */
    public final void m9608f() {
        if (!this.f12986a.isEmpty()) {
            for (UploadListener aVar : this.f12986a.values()) {
                aVar.mo9637a(m9612d(), m9610e());
            }
        }
    }

    /* renamed from: g */
    public final void m9606g() {
        DbUtil.m9460a().save(this.f12988c);
    }

    /* renamed from: h */
    public void m9604h() {
        if (this.f12989d == null) {
            this.f12990e = true;
            this.f12993h.sendEmptyMessage(0);
            this.f12989d = new C4229d();
            this.f12991f.execute(this.f12989d);
        }
    }

    /* renamed from: i */
    public final void m9602i() {
        if (!this.f12986a.isEmpty()) {
            for (UploadListener aVar : this.f12986a.values()) {
                aVar.mo9633e(m9612d(), m9610e());
            }
        }
    }

    /* renamed from: j */
    public void m9600j() {
        OkGo.getInstance().cancelTag(String.format("%s%s", "upload_video_cover", String.valueOf(this.f12988c.getTaskId())));
        OkGo.getInstance().cancelTag(String.format("%s%s", "upload_video", String.valueOf(this.f12988c.getTaskId())));
        C4229d dVar = this.f12989d;
        if (dVar != null) {
            this.f12990e = false;
            dVar.m9595a();
            this.f12991f.remove(this.f12989d);
            this.f12989d = null;
        }
    }

    /* renamed from: a */
    public void m9617a(String str, UploadListener aVar) {
        if (aVar == null) {
            m9618a(str);
        } else {
            this.f12986a.put(str, aVar);
        }
    }

    /* renamed from: a */
    public void m9618a(String str) {
        if (this.f12986a.containsKey(str)) {
            this.f12986a.remove(str);
        }
    }

    /* renamed from: a */
    public void m9622a(AbstractC4228c cVar) {
        this.f12987b = cVar;
    }

    /* renamed from: a */
    public void m9623a() {
        DbUtil.m9460a().delete(this.f12988c);
    }
}

