package com.comod.view.magicindicator.buildins.commonnavigator;

//package p067e.p130f.p148b.p149a.p151e.p152c.p153a;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

import com.comod.view.magicindicator.buildins.commonnavigator.titles.IPagerTitleView;

/* renamed from: e.f.b.a.e.c.a.a */
/* loaded from: classes.dex */
public abstract class CommonNavigatorAdapter {

    /* renamed from: a */
    public final DataSetObservable f13255a = new DataSetObservable();

    /* renamed from: a */
    public abstract int mo8987a();

    /* renamed from: a */
    public abstract IPagerIndicator mo8986a(Context context);

    /* renamed from: a */
    public abstract IPagerTitleView mo8985a(Context context, int i);

    /* renamed from: a */
    public final void m8984a(DataSetObserver dataSetObserver) {
        this.f13255a.registerObserver(dataSetObserver);
    }

    /* renamed from: b */
    public float mo8982b(Context context, int i) {
        return 1.0f;
    }

    /* renamed from: b */
    public final void m8981b(DataSetObserver dataSetObserver) {
        this.f13255a.unregisterObserver(dataSetObserver);
    }

    /* renamed from: b */
    public final void m8983b() {
        this.f13255a.notifyChanged();
    }
}

