package com.comod.baselib.adapter;

//package p067e.p130f.p148b.p149a;

//import android.support.p001v4.view.ViewPager;
import androidx.viewpager.widget.ViewPager;

import com.comod.view.magicindicator.MagicIndicator;

/* renamed from: e.f.b.a.c */
/* loaded from: classes.dex */
public class ViewPagerHelper {

    /* compiled from: ViewPagerHelper.java */
    /* renamed from: e.f.b.a.c$a */
    /* loaded from: classes.dex */
    public static class C4334a implements ViewPager.OnPageChangeListener {

        /* renamed from: b */
        public final /* synthetic */ MagicIndicator f13254b;

        public C4334a(MagicIndicator magicIndicator) {
            this.f13254b = magicIndicator;
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.f13254b.m19997a(i);
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            this.f13254b.m19996a(i, f, i2);
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            this.f13254b.m19995b(i);
        }
    }

    /* renamed from: a */
    public static void m8992a(MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new C4334a(magicIndicator));
    }
}

