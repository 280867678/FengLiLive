package com.slzhibo.library.ui.view.custom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.slzhibo.library.R;
import com.slzhibo.library.model.LeftAnimEntity;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.GlideUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class GuardOpenDanmuView extends LeftAnimView {
    private long animDuration;
    private ImageView ivAvatar;
    private View rlBg;
    private View rootView;
    private TextView tvName;
    private TextView tvTip;

    public GuardOpenDanmuView(Context context) {
        this(context, null);
    }

    public GuardOpenDanmuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GuardOpenDanmuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.animDuration = 6000;
    }

    @Override // com.slzhibo.library.ui.view.custom.LeftAnimView
    public void initView(Context context) {
        this.animType = ConstantUtils.LEFT_ANIM_OPEN_GUARD_TYPE;
        this.rootView = RelativeLayout.inflate(context, R.layout.fq_layout_open_guard_anim_view, this);
        this.rlBg = findViewById(R.id.fq_rl_name_bg);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.tvName = (TextView) findViewById(R.id.fq_name);
        this.tvTip = (TextView) findViewById(R.id.fq_tv_open_guard_tip);
        setRootViewVisibility(4);
        this.tvName.setSelected(true);
    }

    @Override // com.slzhibo.library.ui.view.custom.LeftAnimView
    public void addItemInfo(LeftAnimEntity leftAnimEntity) {
        String str;
        if (leftAnimEntity != null) {
            this.leftAnimEntity = leftAnimEntity;
            leftAnimEntity.leftAnimType = this.animType;
            this.tvName.setText(leftAnimEntity.userName);
            int i = R.color.fq_guard_mouth_text_bg;
            int i2 = R.drawable.fq_ic_guard_mouth_open_tip;
            String str2 = leftAnimEntity.guardType;
            char c = 65535;
            switch (str2.hashCode()) {
                case 49:
                    if (str2.equals("1")) {
                        c = 0;
                        break;
                    }
                    break;
                case 50:
                    if (str2.equals("2")) {
                        c = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str2.equals("3")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                str = this.mContext.getString(R.string.fq_guard_week);
            } else if (c == 1) {
                str = this.mContext.getString(R.string.fq_guard_month);
            } else if (c == 2) {
                String string = this.mContext.getString(R.string.fq_guard_year);
                this.animDuration = 10000;
                str = string;
                i = R.color.fq_guard_year_text_bg;
                i2 = R.drawable.fq_ic_guard_year_open_tip;
            } else {
                return;
            }
            String string2 = this.mContext.getString(R.string.fq_open_guard_tip);
            SpannableString spannableString = new SpannableString(string2 + str);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, i)), string2.length(), spannableString.length(), 33);
            this.tvTip.setText(spannableString);
            this.rlBg.setBackgroundResource(i2);
            GlideUtils.loadAvatar(this.mContext, this.ivAvatar, leftAnimEntity.avatar, R.drawable.fq_ic_placeholder_avatar);
            Observable.timer(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                /* class com.slzhibo.library.ui.view.custom.GuardOpenDanmuView.AnonymousClass1 */

                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(Long l) {
                    GuardOpenDanmuView guardOpenDanmuView = GuardOpenDanmuView.this;
                    guardOpenDanmuView.isShowing = false;
                    guardOpenDanmuView.startAnim(guardOpenDanmuView.rootView);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startAnim(View view) {
        int width = view.getWidth();
        float f = (float) (-width);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, this.animPropertyName, f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, this.animPropertyName, 0.0f, f);
        ofFloat2.setStartDelay(this.animDuration - 2000);
        this.animatorSet.play(ofFloat).before(ofFloat2);
        this.animatorSet.setDuration(2000L);
        this.animatorSet.addListener(new Animator.AnimatorListener() {
            /* class com.slzhibo.library.ui.view.custom.GuardOpenDanmuView.AnonymousClass2 */

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                GuardOpenDanmuView.this.startAnim();
            }

            public void onAnimationEnd(Animator animator) {
                GuardOpenDanmuView.this.endAnim();
            }
        });
        this.animatorSet.start();
    }
}
