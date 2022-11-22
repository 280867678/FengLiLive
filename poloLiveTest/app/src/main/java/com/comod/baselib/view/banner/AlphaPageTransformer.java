package com.comod.baselib.view.banner;

import android.view.View;

import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class AlphaPageTransformer extends BasePageTransformer {

    /* renamed from: a */
    public float f1602a = 0.4f;

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        float f2 = this.f1602a;
        view.setAlpha(f2 + ((1.0f - f2) * (f + 1.0f)));
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        float f2 = this.f1602a;
        view.setAlpha(f2 + ((1.0f - f2) * (1.0f - f)));
    }
}