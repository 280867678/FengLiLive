package com.ibase.glide;

//package p067e.p260n.p261a.p262i;

import android.os.Handler;
import android.os.Looper;
//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
//import p067e.p260n.p261a.p262i.ProgressResponseBody;

/* renamed from: e.n.a.i.f */
/* loaded from: classes.dex */
public class ProgressResponseBody extends ResponseBody {

    /* renamed from: f */
    public static final Handler f17173f = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    public String f17174b;

    /* renamed from: c */
    public AbstractC4720b f17175c;

    /* renamed from: d */
    public ResponseBody f17176d;

    /* renamed from: e */
    public BufferedSource f17177e;

    /* compiled from: ProgressResponseBody.java */
    /* renamed from: e.n.a.i.f$a */
    /* loaded from: classes.dex */
    public class C4719a extends ForwardingSource {

        /* renamed from: b */
        public long f17178b;

        /* renamed from: c */
        public long f17179c;

        public C4719a(Source source) {
            super(source);
        }

        /* renamed from: a */
        public /* synthetic */ void m4936a() {
            ProgressResponseBody.this.f17175c.mo4935a(ProgressResponseBody.this.f17174b, this.f17178b, ProgressResponseBody.this.contentLength());
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(@NonNull Buffer buffer, long j) throws IOException {
            long read = super.read(buffer, j);
            this.f17178b += read == -1 ? 0L : read;
            if (ProgressResponseBody.this.f17175c != null) {
                long j2 = this.f17179c;
                long j3 = this.f17178b;
                if (j2 != j3) {
                    this.f17179c = j3;
                    ProgressResponseBody.f17173f.post(new Runnable() { // from class: e.n.a.i.c
                        @Override // java.lang.Runnable
                        public final void run() {
                            ProgressResponseBody.C4719a.this.m4936a();
                        }
                    });
                }
            }
            return read;
        }
    }

    /* compiled from: ProgressResponseBody.java */
    /* renamed from: e.n.a.i.f$b */
    /* loaded from: classes.dex */
    public interface AbstractC4720b {
        /* renamed from: a */
        void mo4935a(String str, long j, long j2);
    }

    public ProgressResponseBody(String str, AbstractC4720b bVar, ResponseBody responseBody) {
        this.f17174b = str;
        this.f17175c = bVar;
        this.f17176d = responseBody;
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.f17176d.contentLength();
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.f17176d.contentType();
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource source() {
        if (this.f17177e == null) {
            this.f17177e = Okio.buffer(m4938a(this.f17176d.source()));
        }
        return this.f17177e;
    }

    /* renamed from: a */
    public final Source m4938a(Source source) {
        return new C4719a(source);
    }
}

