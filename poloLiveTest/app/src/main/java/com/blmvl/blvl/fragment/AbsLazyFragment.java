package com.blmvl.blvl.fragment;

//package com.example.boluouitest2.comod.baselib.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.boluouitest2.R;



/* loaded from: classes.dex */
public abstract class AbsLazyFragment extends AbsFragment {

    /* renamed from: b */
    public boolean f1519b = false;

    /* renamed from: c */
    public boolean f1520c = false;

    /* renamed from: d */
    public boolean f1521d = false;

    @Override // com.comod.baselib.fragment.AbsFragment
    /* renamed from: a */
    public void mo20157a(View view) {
        this.f1520c = true;
        initView(view);
        m20156t();
    }

    public abstract void initView(View view);

    @Override // android.support.p001v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    public abstract void onLazyLoad();

    @Override // android.support.p001v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.f1521d = z;
        m20156t();
    }

    /* renamed from: t */
    public final void m20156t() {
        if (this.f1520c && this.f1521d && !this.f1519b) {
            onLazyLoad();
            this.f1519b = true;
        }
    }
}



