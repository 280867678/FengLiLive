package com.slzhibo.library.utils;



import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.slzhibo.library.R;


/* loaded from: classes6.dex */
public class AnimUtils {
    public static void playHideAnimation(View view) {
        playHideAnimation(view, 300L);
    }

    public static void playHideAnimation(final View view, long j) {
        if (view != null && view.getVisibility() == View.VISIBLE) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(j);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.slzhibo.library.utils.AnimUtils.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.INVISIBLE);
                }
            });
            view.startAnimation(alphaAnimation);
        }
    }

    public static void playScaleAnim(final View view) {
        Spring createSpring = SpringSystem.create().createSpring();
        createSpring.setCurrentValue(1.100000023841858d);
        createSpring.setSpringConfig(new SpringConfig(40.0d, 7.0d));
        createSpring.addListener(new SimpleSpringListener() { // from class: com.slzhibo.library.utils.AnimUtils.2
            @Override // com.wj.rebound.SimpleSpringListener, com.wj.rebound.SpringListener
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                float currentValue = (float) spring.getCurrentValue();
                view.setScaleX(currentValue);
                view.setScaleY(currentValue);
            }
        });
        createSpring.setEndValue(1.0d);
    }

    public static void playLiveScaleAnim(final View view) {
        Spring createSpring = SpringSystem.create().createSpring();
        createSpring.setCurrentValue(1.5d);
        createSpring.setSpringConfig(new SpringConfig(50.0d, 3.0d));
        createSpring.addListener(new SimpleSpringListener() { // from class: com.slzhibo.library.utils.AnimUtils.3
            @Override // com.wj.rebound.SimpleSpringListener, com.wj.rebound.SpringListener
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                float currentValue = (float) spring.getCurrentValue();
                view.setScaleX(currentValue);
                view.setScaleY(currentValue);
            }
        });
        createSpring.setEndValue(1.0d);
    }

    public static ObjectAnimator startShakeByPropertyAnim(View view, float f, long j) {
        if (view == null) {
            return null;
        }
        float f2 = -f;
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe(View.ROTATION, Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(0.1f, f2), Keyframe.ofFloat(0.2f, f), Keyframe.ofFloat(0.3f, f2), Keyframe.ofFloat(0.4f, f), Keyframe.ofFloat(0.5f, f2), Keyframe.ofFloat(0.6f, f), Keyframe.ofFloat(0.7f, f2), Keyframe.ofFloat(0.8f, f), Keyframe.ofFloat(0.9f, f2), Keyframe.ofFloat(1.0f, 0.0f)));
        ofPropertyValuesHolder.setDuration(j);
        ofPropertyValuesHolder.setRepeatCount(-1);
        return ofPropertyValuesHolder;
    }

    public static void stopAnim(ObjectAnimator objectAnimator, View view) {
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setRotation(0.0f);
    }
}

