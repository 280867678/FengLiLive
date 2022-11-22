package com.comod.baselib.adapter;

//package com.comod.baselib.adapter;

//import android.support.annotation.NonNull;
//import android.support.p001v4.app.Fragment;
//import android.support.p001v4.app.FragmentManager;
//import android.support.p001v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CommonPagerAdapter extends FragmentPagerAdapter {

    /* renamed from: a */
    public List<Fragment> f1516a;

    public CommonPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.f1516a = new ArrayList();
    }

    /* renamed from: a */
    public void m20160a(List<Fragment> list) {
        if (!this.f1516a.isEmpty()) {
            this.f1516a.clear();
        }
        this.f1516a.addAll(list);
        notifyDataSetChanged();
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter, android.support.p001v4.view.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.f1516a;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.f1516a.size();
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (i < this.f1516a.size()) {
            return this.f1516a.get(i);
        }
        return this.f1516a.get(0);
    }

    public CommonPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.f1516a = list;
    }
}

