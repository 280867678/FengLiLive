package com.blmvl.blvl.fragment;

//package com.example.boluouitest2.comod.baselib.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.boluouitest2.R;


public abstract class AbsFragment extends Fragment {
    /* renamed from: a */
    public abstract void mo20157a(View view);

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        m20159q();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
    }

    @Override // android.support.p001v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        try {
            View inflate = layoutInflater.inflate(mo20158r(), viewGroup, false);
            mo20157a(inflate);
            return inflate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: q */
    public void m20159q() {
    }

    /* renamed from: r */
    public abstract int mo20158r();
}
