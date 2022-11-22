package com.slzhibo.library.utils.adapter.animation;



import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: classes6.dex */
public class AlphaInAnimation implements BaseAnimation {
    private static final float DEFAULT_ALPHA_FROM = 0.0f;
    private final float mFrom;

    public AlphaInAnimation() {
        this(0.0f);
    }

    public AlphaInAnimation(float f) {
        this.mFrom = f;
    }

    @Override // com.slzhibo.library.utils.adapter.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", this.mFrom, 1.0f)};
    }
}

