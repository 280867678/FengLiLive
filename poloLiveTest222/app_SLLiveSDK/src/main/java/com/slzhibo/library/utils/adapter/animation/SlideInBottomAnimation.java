package com.slzhibo.library.utils.adapter.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: classes6.dex */
public class SlideInBottomAnimation implements BaseAnimation {
    @Override // com.slzhibo.library.utils.adapter.animation.BaseAnimation
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0.0f)};
    }
}
