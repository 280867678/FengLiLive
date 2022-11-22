package com.slzhibo.library.ui.view.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.slzhibo.library.R;
import com.slzhibo.library.utils.GiftAnimationUtil;

public class CustomAnimImpl implements ICustomAnim {
    @Override // com.slzhibo.library.ui.view.gift.ICustomAnim
    public AnimatorSet startAnim(final GiftFrameLayout giftFrameLayout, View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_jump_enter_left);
        giftFrameLayout.setAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class com.slzhibo.library.ui.view.gift.CustomAnimImpl.AnonymousClass1 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                giftFrameLayout.initLayoutState();
            }

            public void onAnimationEnd(Animation animation) {
                giftFrameLayout.comboAnimation(true);
            }
        });
        giftFrameLayout.ivSvgaImageView.setAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_jump_icon_left));
        return null;
    }

    @Override // com.slzhibo.library.ui.view.gift.ICustomAnim
    public AnimatorSet comboAnim(final GiftFrameLayout giftFrameLayout, View view, boolean z) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_gift_num_bg);
        if (z) {
            linearLayout.setVisibility(View.VISIBLE);
            giftFrameLayout.initGiftCount(String.valueOf(giftFrameLayout.getCombo()));
            giftFrameLayout.comboEndAnim();
            return null;
        }
        ObjectAnimator scaleGiftNum = GiftAnimationUtil.scaleGiftNum(linearLayout);
        scaleGiftNum.addListener(new AnimatorListenerAdapter() {
            /* class com.slzhibo.library.ui.view.gift.CustomAnimImpl.AnonymousClass2 */

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                giftFrameLayout.comboEndAnim();
            }
        });
        scaleGiftNum.start();
        return null;
    }

    @Override // com.slzhibo.library.ui.view.gift.ICustomAnim
    public AnimatorSet endAnim(GiftFrameLayout giftFrameLayout, View view) {
        return GiftAnimationUtil.startAnimation(GiftAnimationUtil.createFadeAnimator(giftFrameLayout, 0.0f, -80.0f, 300, 0), GiftAnimationUtil.createFadeAnimator(giftFrameLayout, 100.0f, 0.0f, 0, 0));
    }
}
