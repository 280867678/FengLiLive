package com.comod.baselib.view.banner;

import android.view.View;

/* loaded from: classes.dex */
public class ZoomStackPageTransformer extends BasePageTransformer {
    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        view.setTranslationX((-view.getWidth()) * f);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        float f2 = f + 1.0f;
        view.setScaleX(f2);
        view.setScaleY(f2);
        if (f < -0.95f) {
            view.setAlpha(0.0f);
        } else {
            view.setAlpha(1.0f);
        }
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        view.setTranslationX((-view.getWidth()) * f);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        float f2 = f + 1.0f;
        view.setScaleX(f2);
        view.setScaleY(f2);
        if (f > 0.95f) {
            view.setAlpha(0.0f);
        } else {
            view.setAlpha(1.0f);
        }
    }
}
