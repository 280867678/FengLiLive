package com.slzhibo.library.utils.adapter.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: classes6.dex */
public class SlideInLeftAnimation implements BaseAnimation {
    @Override // com.slzhibo.library.utils.adapter.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationX", -view.getRootView().getWidth(), 0.0f)};
    }
}
