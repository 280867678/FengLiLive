package com.blmvl.blvl.service;

//package p067e.p103c.p104a.p112j;

import android.content.Context;

import com.blmvl.blvl.R;
import com.blmvl.blvl.bean.UploadTaskInfo;
import com.blmvl.blvl.bean.UploadVideoTaskBean;
import com.blmvl.blvl.bean.UploadVideoTaskBeanDao;
import com.blmvl.blvl.util.DbUtil;
import com.blmvl.blvl.util.SpUtil;
import com.blmvl.blvl.util.ToastUtil;
//import com.blmvl.blvl.db.UploadVideoTaskBeanDao;



import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//import p067e.p103c.p104a.p112j.Uploader;
//import p067e.p103c.p104a.p113k.DbUtil;
//import p067e.p103c.p104a.p113k.SpUtil;
//import p067e.p130f.p131a.p135d.ToastUtil;
//import p522h.p523a.p528b.p533k.QueryBuilder;
import org.greenrobot.greendao.query.QueryBuilder;
//import p522h.p523a.p528b.p533k.WhereCondition;
import org.greenrobot.greendao.query.WhereCondition;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.j.b */
/* loaded from: classes.dex */
public class UploadManager {

    /* renamed from: a */
    public Context f12979a;

    /* renamed from: b */
    public ArrayList<Uploader> f12980b = new ArrayList<>();

    /* renamed from: c */
    public Uploader.AbstractC4228c f12981c = null;

    /* renamed from: d */
    public ThreadPoolExecutor f12982d;

    /* renamed from: e */
    public String f12983e;

    /* renamed from: f */
    public UploadListener f12984f;

    /* compiled from: UploadManager.java */
    /* renamed from: e.c.a.j.b$a */
    /* loaded from: classes.dex */
    public class C4225a implements Uploader.AbstractC4228c {
        public C4225a() {
        }

        @Override // p067e.p103c.p104a.p112j.Uploader.AbstractC4228c
        /* renamed from: a */
        public void mo9596a(long j) {
            int size = UploadManager.this.f12980b.size();
            for (int i = 0; i < size; i++) {
                Uploader cVar = (Uploader) UploadManager.this.f12980b.get(i);
                if (cVar.m9614c() == j) {
                    UploadManager.this.f12980b.remove(cVar);
                    return;
                }
            }
        }
    }

    public UploadManager(Context context) {
        this.f12979a = context;
        m9630a(context);
    }

    /* renamed from: b */
    public void m9624b(long j) {
        int size = this.f12980b.size();
        for (int i = 0; i < size; i++) {
            Uploader cVar = this.f12980b.get(i);
            if (cVar.m9614c() == j) {
                cVar.m9604h();
                return;
            }
        }
    }

    /* renamed from: a */
    public final void m9630a(Context context) {
        this.f12982d = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue(2000));
        this.f12981c = new C4225a();
        this.f12983e = SpUtil.m9573D().m9535i();
        m9629a(context, this.f12983e);
    }

    /* renamed from: b */
    public void m9625b() {
        int size = this.f12980b.size();
        for (int i = 0; i < size; i++) {
            this.f12980b.get(i).m9600j();
        }
    }

    /* renamed from: a */
    public final void m9629a(Context context, String str) {
        try {
            m9625b();
            this.f12980b = new ArrayList<>();
            QueryBuilder<UploadVideoTaskBean> f = DbUtil.m9460a().queryBuilder();
            f.where(UploadVideoTaskBeanDao.Properties.UserId.eq(str), new WhereCondition[0]);
            ArrayList arrayList = (ArrayList) f.build().list();
            if (arrayList != null && arrayList.size() > 0) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    Uploader cVar = new Uploader(context, (UploadVideoTaskBean) arrayList.get(i), this.f12982d, str, false);
                    cVar.m9622a(this.f12981c);
                    cVar.m9617a("public", this.f12984f);
                    this.f12980b.add(cVar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m9628a(UploadVideoTaskBean uploadVideoTaskBean) {
        try {
            Uploader cVar = new Uploader(this.f12979a, uploadVideoTaskBean, this.f12982d, this.f12983e, true);
            cVar.m9622a(this.f12981c);
            cVar.m9617a("public", this.f12984f);
            cVar.m9604h();
            this.f12980b.add(cVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public ArrayList<UploadTaskInfo> m9632a() {
        ArrayList<UploadTaskInfo> arrayList = new ArrayList<>();
        int size = this.f12980b.size();
        for (int i = 0; i < size; i++) {
            Uploader cVar = this.f12980b.get(i);
            UploadVideoTaskBean d = cVar.m9612d();
            UploadTaskInfo uploadTaskInfo = new UploadTaskInfo();
            uploadTaskInfo.setTaskId(d.getTaskId());
            uploadTaskInfo.setLocalCoverUrl(d.getLocalCoverUrl());
            uploadTaskInfo.setLocalVideoUrl(d.getLocalVideoUrl());
            uploadTaskInfo.setTitle(d.getTitle());
            uploadTaskInfo.setTags(d.getTags());
            uploadTaskInfo.setVideoPrice(d.getVideoPrice());
            uploadTaskInfo.setAddTime(d.getAddTime());
            uploadTaskInfo.setProgress(d.getProgress());
            uploadTaskInfo.setDuration(d.getDuration());
            uploadTaskInfo.setOnUpload(cVar.m9610e());
            arrayList.add(uploadTaskInfo);
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m9631a(long j) {
        int size = this.f12980b.size();
        for (int i = 0; i < size; i++) {
            Uploader cVar = this.f12980b.get(i);
            if (cVar.m9614c() == j) {
                cVar.m9600j();
                cVar.m9623a();
                this.f12980b.remove(cVar);
                Context context = this.f12979a;
                ToastUtil.m9102c(context, context.getResources().getString(R.string.str_task_cancelled));
                return;
            }
        }
    }

    /* renamed from: a */
    public void m9627a(UploadListener aVar) {
        this.f12984f = aVar;
        int size = this.f12980b.size();
        for (int i = 0; i < size; i++) {
            this.f12980b.get(i).m9617a("public", aVar);
        }
    }
}

