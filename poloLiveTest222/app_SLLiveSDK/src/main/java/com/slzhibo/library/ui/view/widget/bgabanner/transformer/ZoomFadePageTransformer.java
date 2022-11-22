package com.slzhibo.library.ui.view.widget.bgabanner.transformer;

import android.view.View;

import androidx.core.view.ViewCompat;

/* renamed from: com.slzhibo.library.ui.view.widget.bgabanner.transformer.ZoomFadePageTransformer */
/* loaded from: classes6.dex */
public class ZoomFadePageTransformer extends BGAPageTransformer {
    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleInvisiblePage(View view, float f) {
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleLeftPage(View view, float f) {
        ViewCompat.setTranslationX(view, (-view.getWidth()) * f);
        ViewCompat.setPivotX(view, view.getWidth() * 0.5f);
        ViewCompat.setPivotY(view, view.getHeight() * 0.5f);
        float f2 = f + 1.0f;
        ViewCompat.setScaleX(view, f2);
        ViewCompat.setScaleY(view, f2);
        ViewCompat.setAlpha(view, f2);
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleRightPage(View view, float f) {
        ViewCompat.setTranslationX(view, (-view.getWidth()) * f);
        ViewCompat.setPivotX(view, view.getWidth() * 0.5f);
        ViewCompat.setPivotY(view, view.getHeight() * 0.5f);
        float f2 = 1.0f - f;
        ViewCompat.setScaleX(view, f2);
        ViewCompat.setScaleY(view, f2);
        ViewCompat.setAlpha(view, f2);
    }
}
