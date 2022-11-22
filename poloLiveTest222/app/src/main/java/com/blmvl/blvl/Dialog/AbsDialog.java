package com.blmvl.blvl.Dialog;

//package p067e.p130f.p131a.p132a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

/* renamed from: e.f.a.a.a */
/* loaded from: classes.dex */
public abstract class AbsDialog extends Dialog {
    public AbsDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    /* renamed from: a */
    public void m9245a(Window window) {
        if (window.getAttributes() != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = mo9239f();
            attributes.height = mo9240e();
            attributes.gravity = mo9242c();
            window.setAttributes(attributes);
        }
    }

    /* renamed from: a */
    public boolean mo9246a() {
        return true;
    }

    /* renamed from: b */
    public abstract void mo9243b(Window window);

    /* renamed from: b */
    public boolean mo9244b() {
        return true;
    }

    /* renamed from: c */
    public int mo9242c() {
        return 17;
    }

    /* renamed from: d */
    public abstract int mo9241d();

    /* renamed from: e */
    public int mo9240e() {
        return -2;
    }

    /* renamed from: f */
    public int mo9239f() {
        return -2;
    }

    /* renamed from: g */
    public void m9238g() {
        try {
            setCancelable(mo9246a());
            setCanceledOnTouchOutside(mo9244b());
            Window window = getWindow();
            if (window != null) {
                m9245a(window);
                mo9243b(window);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(mo9241d());
        m9238g();
    }
}

