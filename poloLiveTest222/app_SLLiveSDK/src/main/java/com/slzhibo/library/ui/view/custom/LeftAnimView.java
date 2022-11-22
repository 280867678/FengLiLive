package com.slzhibo.library.ui.view.custom;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.slzhibo.library.model.LeftAnimEntity;
import com.slzhibo.library.ui.interfaces.OnAnimPlayListener;

public abstract class LeftAnimView extends RelativeLayout {
    public String animPropertyName = "translationX";
    public int animType = -1;
    public AnimatorSet animatorSet;
    public boolean isShowing;
    public LeftAnimEntity leftAnimEntity;
    public OnAnimPlayListener listener;
    public Context mContext;

    public abstract void addItemInfo(LeftAnimEntity leftAnimEntity2);

    public abstract void initView(Context context);

    public LeftAnimView(Context context) {
        super(context);
        initContext(context);
    }

    public LeftAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initContext(context);
    }

    public LeftAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initContext(context);
    }

    public void setOnAnimPlayListener(OnAnimPlayListener onAnimPlayListener) {
        this.listener = onAnimPlayListener;
    }

    public void startAnim() {
        if (this.listener != null) {
            setRootViewVisibility(0);
            this.listener.onStart();
        }
    }

    public void endAnim() {
        OnAnimPlayListener onAnimPlayListener = this.listener;
        if (onAnimPlayListener != null) {
            onAnimPlayListener.onEnd(this);
        }
    }

    public void setRootViewVisibility(int i) {
        setVisibility(i);
    }

    public void onRelease() {
        AnimatorSet animatorSet2 = this.animatorSet;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
            this.animatorSet.removeAllListeners();
        }
    }

    private void initContext(Context context) {
        this.mContext = context;
        this.animatorSet = new AnimatorSet();
        initView(context);
    }
}
