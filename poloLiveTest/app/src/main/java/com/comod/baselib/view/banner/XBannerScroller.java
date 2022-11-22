package com.comod.baselib.view.banner;

import android.content.Context;
import android.widget.Scroller;

/* renamed from: e.f.a.e.b.d */
/* loaded from: classes.dex */
public class XBannerScroller extends Scroller {

    /* renamed from: a */
    public int f13215a;

    public XBannerScroller(Context context, int i) {
        super(context);
        this.f13215a = 800;
        this.f13215a = i;
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.f13215a);
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.f13215a);
    }
}
