package com.comod.baselib.view.banner;


import android.view.View;

import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class ZoomPageTransformer extends BasePageTransformer {

    /* renamed from: a */
    public float f1662a = 0.85f;

    /* renamed from: b */
    public float f1663b = 0.65f;

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        float max = Math.max(this.f1662a, f + 1.0f);
        float f2 = 1.0f - max;
        view.setTranslationX(((view.getWidth() * f2) / 2.0f) - (((view.getHeight() * f2) / 2.0f) / 2.0f));
        view.setScaleX(max);
        view.setScaleY(max);
        float f3 = this.f1663b;
        float f4 = this.f1662a;
        view.setAlpha(f3 + (((max - f4) / (1.0f - f4)) * (1.0f - f3)));
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        float max = Math.max(this.f1662a, 1.0f - f);
        float f2 = 1.0f - max;
        view.setTranslationX((-((view.getWidth() * f2) / 2.0f)) + (((view.getHeight() * f2) / 2.0f) / 2.0f));
        view.setScaleX(max);
        view.setScaleY(max);
        float f3 = this.f1663b;
        float f4 = this.f1662a;
        view.setAlpha(f3 + (((max - f4) / (1.0f - f4)) * (1.0f - f3)));
    }
}
