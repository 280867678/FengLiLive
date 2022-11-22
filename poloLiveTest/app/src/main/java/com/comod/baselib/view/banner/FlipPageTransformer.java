package com.comod.baselib.view.banner;

import android.view.View;

/* loaded from: classes.dex */
public class FlipPageTransformer extends BasePageTransformer {
    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        view.setTranslationX((-view.getWidth()) * f);
        view.setRotationY(180.0f * f);
        if (f > -0.5d) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        view.setTranslationX((-view.getWidth()) * f);
        view.setRotationY(180.0f * f);
        if (f < 0.5d) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
