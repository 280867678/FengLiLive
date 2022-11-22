package com.comod.view.magicindicator.buildins.commonnavigator;

//package p067e.p130f.p148b.p149a.p151e.p152c.p153a;

import java.util.List;
//import p067e.p130f.p148b.p149a.p151e.p152c.p154b.PositionData;

/* renamed from: e.f.b.a.e.c.a.c */
/* loaded from: classes.dex */
public interface IPagerIndicator {
    /* renamed from: a */
    void mo8980a(List<PositionData> list);

    void onPageScrollStateChanged(int i);

    void onPageScrolled(int i, float f, int i2);

    void onPageSelected(int i);
}

