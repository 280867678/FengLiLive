package com.slzhibo.library.ui.view.widget;



import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.slzhibo.library.R;

//import com.example.boluouitest2zhibo.R;


/* loaded from: classes11.dex */
public class LoadingView extends AppCompatImageView {
    private AnimationDrawable frameAnimation;

    public LoadingView(Context context) {
        super(context);
        initView(context);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fq_live_loading_animation));
        this.frameAnimation = (AnimationDrawable) getDrawable();
    }

    public void showLoading() {
        post(new Runnable() { // from class: com.slzhibo.library.ui.view.widget.-$$Lambda$LoadingView$IZ5C_Vv90Sj-dbktjikfo8L2-Ww
            @Override // java.lang.Runnable
            public final void run() {
                LoadingView.this.lambda$showLoading$0$LoadingView();
            }
        });
    }

    public /* synthetic */ void lambda$showLoading$0$LoadingView() {
        AnimationDrawable animationDrawable = this.frameAnimation;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    public void stopLoading() {
        AnimationDrawable animationDrawable = this.frameAnimation;
        if (animationDrawable != null && animationDrawable.isRunning()) {
            this.frameAnimation.stop();
        }
    }

    public void release() {
        AnimationDrawable animationDrawable = this.frameAnimation;
        if (animationDrawable != null && animationDrawable.isRunning()) {
            this.frameAnimation.stop();
        }
        this.frameAnimation = null;
        setImageDrawable(null);
    }
}

