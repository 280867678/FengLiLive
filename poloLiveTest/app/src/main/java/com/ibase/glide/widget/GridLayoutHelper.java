package com.ibase.glide.widget;

//package p067e.p260n.p261a.p264k;

import android.graphics.Point;

/* renamed from: e.n.a.k.a */
/* loaded from: classes.dex */
public class GridLayoutHelper implements LayoutHelper {

    /* renamed from: a */
    public int f17181a;

    /* renamed from: b */
    public int f17182b;

    /* renamed from: c */
    public int f17183c;

    /* renamed from: d */
    public int f17184d;

    public GridLayoutHelper(int i, int i2, int i3, int i4) {
        this.f17181a = i;
        this.f17182b = i2;
        this.f17183c = i3;
        this.f17184d = i4;
    }

    @Override // p067e.p260n.p261a.p264k.LayoutHelper
    /* renamed from: a */
    public Point mo4923a(int i) {
        Point point = new Point();
        point.x = this.f17182b;
        point.y = this.f17183c;
        return point;
    }

    @Override // p067e.p260n.p261a.p264k.LayoutHelper
    /* renamed from: b */
    public Point mo4922b(int i) {
        Point point = new Point();
        int i2 = this.f17181a;
        int i3 = this.f17182b;
        int i4 = this.f17184d;
        point.x = (i % i2) * (i3 + i4);
        point.y = (i / i2) * (this.f17183c + i4);
        return point;
    }
}

