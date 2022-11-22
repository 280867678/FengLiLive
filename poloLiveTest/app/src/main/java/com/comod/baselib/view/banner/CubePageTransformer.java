package com.comod.baselib.view.banner;

import android.view.View;

/* loaded from: classes.dex */
public class CubePageTransformer extends BasePageTransformer {

    /* renamed from: a */
    public float f1604a = 90.0f;

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
        view.setPivotX(view.getMeasuredWidth());
        view.setPivotY(view.getMeasuredHeight() * 0.5f);
        view.setRotationY(0.0f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        view.setPivotX(view.getMeasuredWidth());
        view.setPivotY(view.getMeasuredHeight() * 0.5f);
        view.setRotationY(this.f1604a * f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        view.setPivotX(0.0f);
        view.setPivotY(view.getMeasuredHeight() * 0.5f);
        view.setRotationY(this.f1604a * f);
    }
}
