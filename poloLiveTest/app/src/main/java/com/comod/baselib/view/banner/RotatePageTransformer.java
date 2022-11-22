package com.comod.baselib.view.banner;

import android.view.View;

/* loaded from: classes.dex */
public class RotatePageTransformer extends BasePageTransformer {

    /* renamed from: a */
    public float f1606a = 15.0f;

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
        view.setPivotX(view.getMeasuredWidth() * 0.5f);
        view.setPivotY(view.getMeasuredHeight());
        view.setRotation(0.0f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        float f2 = this.f1606a * f;
        view.setPivotX(view.getMeasuredWidth() * 0.5f);
        view.setPivotY(view.getMeasuredHeight());
        view.setRotation(f2);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        mo20083b(view, f);
    }
}
