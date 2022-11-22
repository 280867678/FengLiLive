package com.blmvl.blvl.util;

//package p067e.p130f.p131a.p136e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blmvl.blvl.R;
//import com.comod.baselib.R$id;
//import com.comod.baselib.R$layout;
//import p067e.p130f.p131a.p135d.DpUtil;

/* renamed from: e.f.a.e.a */
/* loaded from: classes.dex */
public class CustomToast extends Toast {
    public CustomToast(Context context, CharSequence charSequence, int i, int i2, int i3, int i4) {
        super(context);
        m9064a(context, charSequence, i, i2, i3, i4);
    }

    public static Toast makeText(Context context, CharSequence charSequence, int i) {
        return new CustomToast(context, charSequence, i, 80, 0, DpUtil.m9101a(context, 50));
    }

    /* renamed from: a */
    public final void m9064a(Context context, CharSequence charSequence, int i, int i2, int i3, int i4) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(charSequence);
        setDuration(i);
        setGravity(i2, i3, i4);
        setView(inflate);
    }

    /* renamed from: a */
    public static Toast m9065a(Context context, CharSequence charSequence, int i) {
        return new CustomToast(context, charSequence, i, 17, 0, 0);
    }
}

