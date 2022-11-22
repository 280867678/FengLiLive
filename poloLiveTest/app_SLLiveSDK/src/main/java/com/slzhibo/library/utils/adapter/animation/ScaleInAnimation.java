package com.slzhibo.library.utils.adapter.animation;



import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: classes6.dex */
public class ScaleInAnimation implements BaseAnimation {
    private static final float DEFAULT_SCALE_FROM = 0.5f;
    private final float mFrom;

    public ScaleInAnimation() {
        this(DEFAULT_SCALE_FROM);
    }

    public ScaleInAnimation(float f) {
        this.mFrom = f;
    }

    @Override // com.slzhibo.library.utils.adapter.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new ObjectAnimator[]{ObjectAnimator.ofFloat(view, "scaleX", this.mFrom, 1.0f), ObjectAnimator.ofFloat(view, "scaleY", this.mFrom, 1.0f)};
    }
}

