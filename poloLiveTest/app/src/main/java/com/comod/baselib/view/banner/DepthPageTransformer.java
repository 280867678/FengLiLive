package com.comod.baselib.view.banner;


import android.view.View;

import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class DepthPageTransformer extends BasePageTransformer {

    /* renamed from: a */
    public float f1605a = 0.8f;

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        view.setAlpha(1.0f);
        view.setTranslationX(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        float f2 = 1.0f - f;
        view.setAlpha(f2);
        view.setTranslationX((-view.getWidth()) * f);
        float f3 = this.f1605a;
        float f4 = f3 + ((1.0f - f3) * f2);
        view.setScaleX(f4);
        view.setScaleY(f4);
    }
}
