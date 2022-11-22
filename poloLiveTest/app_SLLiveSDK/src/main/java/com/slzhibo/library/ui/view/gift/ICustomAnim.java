package com.slzhibo.library.ui.view.gift;



import android.animation.AnimatorSet;
import android.view.View;

/* renamed from: com.slzhibo.library.ui.view.gift.ICustomAnim */
/* loaded from: classes6.dex */
public interface ICustomAnim {
    AnimatorSet comboAnim(GiftFrameLayout giftFrameLayout, View view, boolean z);

    AnimatorSet endAnim(GiftFrameLayout giftFrameLayout, View view);

    AnimatorSet startAnim(GiftFrameLayout giftFrameLayout, View view);
}

