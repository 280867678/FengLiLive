package com.slzhibo.library.ui.view.widget.bgabanner.transformer;

import android.view.View;

import androidx.core.view.ViewCompat;

/* renamed from: com.slzhibo.library.ui.view.widget.bgabanner.transformer.AccordionPageTransformer */
/* loaded from: classes6.dex */
public class AccordionPageTransformer extends BGAPageTransformer {
    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleInvisiblePage(View view, float f) {
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleLeftPage(View view, float f) {
        ViewCompat.setPivotX(view, view.getWidth());
        ViewCompat.setScaleX(view, f + 1.0f);
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleRightPage(View view, float f) {
        ViewCompat.setPivotX(view, 0.0f);
        ViewCompat.setScaleX(view, 1.0f - f);
        ViewCompat.setAlpha(view, 1.0f);
    }
}
