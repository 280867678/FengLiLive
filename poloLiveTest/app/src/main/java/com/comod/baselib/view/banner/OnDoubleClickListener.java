package com.comod.baselib.view.banner;

import android.view.View;

/* renamed from: e.f.a.e.b.b */
/* loaded from: classes.dex */
public abstract class OnDoubleClickListener implements View.OnClickListener {

    /* renamed from: b */
    public int f13213b = 1000;

    /* renamed from: c */
    public long f13214c = 0;

    /* renamed from: a */
    public abstract void mo9063a(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f13214c > this.f13213b) {
            this.f13214c = currentTimeMillis;
            mo9063a(view);
        }
    }
}
