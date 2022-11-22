package com.slzhibo;

import com.slzhibo.library.SLLiveSDK;
import io.reactivex.functions.Consumer;
/* compiled from: lambda */
/* renamed from: e.t.a.c */
/* loaded from: classes2.dex */
public final /* synthetic */ class C4783c implements Consumer {

    /* renamed from: b */
    public static final /* synthetic */ C4783c f17515b = new C4783c();

    private /* synthetic */ C4783c() {
    }

    @Override // p481f.p482a.p483a0.Consumer
    public final void accept(Object obj) {
        try {
            SLLiveSDK.m18469a((Throwable) obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
