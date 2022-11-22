package com.blmvl.blvl.PagerHelper;

//package p067e.p130f.p131a.p135d;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
//import android.support.p001v4.app.Fragment;
//import android.support.p001v4.app.FragmentManager;
//import android.support.p001v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
//import com.comod.baselib.R$drawable;
//import com.comod.baselib.R$id;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.blmvl.blvl.R;
import com.blmvl.blvl.util.DpUtil;
import com.comod.baselib.adapter.CommonPagerAdapter;
import com.comod.baselib.adapter.ViewPagerHelper;
import com.comod.view.magicindicator.MagicIndicator;
import com.comod.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.comod.view.magicindicator.buildins.commonnavigator.CommonNavigatorAdapter;
import com.comod.view.magicindicator.buildins.commonnavigator.IPagerIndicator;
import com.comod.view.magicindicator.buildins.commonnavigator.titles.IPagerTitleView;

import java.util.ArrayList;
import java.util.List;
//import p067e.p130f.p148b.p149a.ViewPagerHelper;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.CommonNavigatorAdapter;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerIndicator;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerTitleView;

/* renamed from: e.f.a.d.g */
/* loaded from: classes.dex */
public abstract class ComViewPagerHelper {

    /* renamed from: a */
    public ViewPager f13193a;

    /* renamed from: b */
    public MagicIndicator f13194b;

    /* compiled from: ComViewPagerHelper.java */
    /* renamed from: e.f.a.d.g$a */
    /* loaded from: classes.dex */
    public class C4321a implements ViewPager.OnPageChangeListener {
        public C4321a() {
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            ComViewPagerHelper.this.m9156a(i);
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            ComViewPagerHelper.this.m9155a(i, f, i2);
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            ComViewPagerHelper.this.mo9148b(i);
        }
    }

    /* compiled from: ComViewPagerHelper.java */
    /* renamed from: e.f.a.d.g$b */
    /* loaded from: classes.dex */
    public class C4322b extends CommonNavigatorAdapter {

        /* renamed from: b */
        public final /* synthetic */ List f13196b;

        /* renamed from: c */
        public final /* synthetic */ List f13197c;

        public C4322b(List list, List list2) {
            this.f13196b = list;
            this.f13197c = list2;
        }

        @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.CommonNavigatorAdapter
        /* renamed from: a */
        public int mo8987a() {
            List list = this.f13196b;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.CommonNavigatorAdapter
        /* renamed from: a */
        public IPagerTitleView mo8985a(Context context, int i) {
            ComViewPagerHelper gVar = ComViewPagerHelper.this;
            return gVar.mo9153a(context, i, gVar.f13193a, this.f13196b, this.f13197c);
        }

        @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.CommonNavigatorAdapter
        /* renamed from: a */
        public IPagerIndicator mo8986a(Context context) {
            return ComViewPagerHelper.this.mo9144c(context);
        }
    }

    public ComViewPagerHelper(Context context, Activity activity, List<String> list, List<Fragment> list2, List<Integer> list3, FragmentManager fragmentManager) {
        try {
            this.f13193a = activity.findViewById(R.id.viewPager);
            this.f13194b = activity.findViewById(R.id.indicator);
            m9151a(context, list, list2, list3, fragmentManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public abstract IPagerTitleView mo9153a(Context context, int i, ViewPager viewPager, List<String> list, List<Integer> list2);

    /* renamed from: a */
    public void m9156a(int i) {
    }

    /* renamed from: a */
    public void m9155a(int i, float f, int i2) {
    }

    /* renamed from: a */
    public boolean mo9157a() {
        return false;
    }

    /* renamed from: b */
    public int m9149b() {
        return 0;
    }

    /* renamed from: b */
    public int m9147b(Context context) {
        return DpUtil.m9101a(context, 15);
    }

    /* renamed from: b */
    public void mo9148b(int i) {
    }

    /* renamed from: c */
    public int m9146c() {
        ViewPager viewPager = this.f13193a;
        if (viewPager != null) {
            return viewPager.getChildCount();
        }
        return 0;
    }

    /* renamed from: c */
    public IPagerIndicator mo9144c(Context context) {
        return null;
    }

    /* renamed from: d */
    public int mo9143d() {
        return 2;
    }

    /* renamed from: e */
    public boolean m9142e() {
        return false;
    }

    /* renamed from: a */
    public final void m9151a(Context context, List<String> list, List<Fragment> list2, List<Integer> list3, FragmentManager fragmentManager) {
        if (list != null && list2 != null && !list.isEmpty() && !list2.isEmpty()) {
            CommonPagerAdapter commonPagerAdapter = new CommonPagerAdapter(fragmentManager, list2);
            this.f13193a.setOffscreenPageLimit(mo9143d());
            this.f13193a.setAdapter(commonPagerAdapter);
            this.f13193a.addOnPageChangeListener(new C4321a());
            ArrayList arrayList = new ArrayList();
            if (list3 != null && !list3.isEmpty()) {
                arrayList.addAll(list3);
            }
            m9152a(context, list, arrayList);
        }
    }

    /* renamed from: c */
    public void m9145c(int i) {
        ViewPager viewPager = this.f13193a;
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
        }
    }

    public ComViewPagerHelper(Context context, View view, List<String> list, List<Fragment> list2, List<Integer> list3, FragmentManager fragmentManager) {
        try {
            this.f13193a = view.findViewById(R.id.viewPager);
            this.f13194b = view.findViewById(R.id.indicator);
            m9151a(context, list, list2, list3, fragmentManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m9152a(Context context, List<String> list, List<Integer> list2) {
        try {
            CommonNavigator commonNavigator = new CommonNavigator(context);
            commonNavigator.setAdjustMode(mo9157a());
            commonNavigator.setAdapter(new C4322b(list, list2));
            this.f13194b.setNavigator(commonNavigator);
            LinearLayout titleContainer = commonNavigator.getTitleContainer();
            if (m9142e()) {
                titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                titleContainer.setDividerPadding(m9147b(context));
                titleContainer.setDividerDrawable(m9154a(context));
            }
            ViewPagerHelper.m8992a(this.f13194b, this.f13193a);
            this.f13193a.setCurrentItem(m9149b());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final Drawable m9154a(Context context) {
        return context.getResources().getDrawable(R.drawable.vertical_line_color_ccc);
    }
}

