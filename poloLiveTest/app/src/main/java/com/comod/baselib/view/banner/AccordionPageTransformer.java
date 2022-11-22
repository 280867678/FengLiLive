package com.comod.baselib.view.banner;

import android.view.View;

/* loaded from: classes.dex */
public class AccordionPageTransformer extends BasePageTransformer {
    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        view.setPivotX(view.getWidth());
        view.setScaleX(f + 1.0f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        view.setPivotX(0.0f);
        view.setScaleX(1.0f - f);
        view.setAlpha(1.0f);
    }
}
