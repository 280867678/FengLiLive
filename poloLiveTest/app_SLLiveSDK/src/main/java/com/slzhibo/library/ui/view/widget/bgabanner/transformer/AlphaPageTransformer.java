package com.slzhibo.library.ui.view.widget.bgabanner.transformer;

import android.view.View;

import androidx.core.view.ViewCompat;

/* renamed from: com.slzhibo.library.ui.view.widget.bgabanner.transformer.AlphaPageTransformer */
/* loaded from: classes6.dex */
public class AlphaPageTransformer extends BGAPageTransformer {
    private float mMinScale = 0.4f;

    public AlphaPageTransformer() {
    }

    public AlphaPageTransformer(float f) {
        setMinScale(f);
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleInvisiblePage(View view, float f) {
        ViewCompat.setAlpha(view, 0.0f);
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleLeftPage(View view, float f) {
        float f2 = this.mMinScale;
        ViewCompat.setAlpha(view, f2 + ((1.0f - f2) * (f + 1.0f)));
    }

    @Override // com.slzhibo.library.p115ui.view.widget.bgabanner.transformer.BGAPageTransformer
    public void handleRightPage(View view, float f) {
        float f2 = this.mMinScale;
        ViewCompat.setAlpha(view, f2 + ((1.0f - f2) * (1.0f - f)));
    }

    public void setMinScale(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.mMinScale = f;
        }
    }
}
