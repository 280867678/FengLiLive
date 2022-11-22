package com.comod.baselib.view.banner;

import android.view.View;

/* loaded from: classes.dex */
public class ScalePageTransformer extends BasePageTransformer {
    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: a */
    public void mo20084a(View view, float f) {
        view.setScaleY(0.9f);
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: b */
    public void mo20083b(View view, float f) {
        view.setScaleY(Math.max(0.9f, 1.0f - Math.abs(f)));
    }

    @Override // com.comod.baselib.view.banner.BasePageTransformer
    /* renamed from: c */
    public void mo20082c(View view, float f) {
        view.setScaleY(Math.max(0.9f, 1.0f - Math.abs(f)));
    }
}
