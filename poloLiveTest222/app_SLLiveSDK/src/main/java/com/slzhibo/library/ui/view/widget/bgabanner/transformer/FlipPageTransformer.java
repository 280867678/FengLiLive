package com.slzhibo.library.ui.view.widget.bgabanner.transformer;

import android.view.View;

import androidx.core.view.ViewCompat;

/* renamed from: com.slzhibo.library.ui.view.widget.bgabanner.transformer.FlipPageTransformer */
/* loaded from: classes6.dex */
public class FlipPageTransformer extends BGAPageTransformer {
    private static final float ROTATION = 180.0f;

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleInvisiblePage(View view, float f) {
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleLeftPage(View view, float f) {
        ViewCompat.setTranslationX(view, (-view.getWidth()) * f);
        ViewCompat.setRotationY(view, ROTATION * f);
        if (f > -0.5d) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleRightPage(View view, float f) {
        ViewCompat.setTranslationX(view, (-view.getWidth()) * f);
        ViewCompat.setRotationY(view, ROTATION * f);
        if (f < 0.5d) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
