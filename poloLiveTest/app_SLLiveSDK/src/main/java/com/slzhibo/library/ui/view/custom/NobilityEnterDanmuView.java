package com.slzhibo.library.ui.view.custom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ConvertUtils;
import com.slzhibo.library.R;
import com.slzhibo.library.model.LeftAnimEntity;
import com.slzhibo.library.ui.view.widget.drawabletext.DrawableTextView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.GlideUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NobilityEnterDanmuView extends LeftAnimView {
    private RelativeLayout alAvatarBg;
    private ImageView ivAvatar;
    private ImageView ivBadge;
    private View rootView;
    private TextView tvName;
    private DrawableTextView tvTips;

    private int getAnimDuration(int i) {
        if (i == 5 || i == 6 || i != 7) {
        }
        return 6000;
    }

    private int getAvatarBgTopPadding(int i) {
        if (i != 6) {
            return i != 7 ? 17 : 23;
        }
        return 20;
    }

    public NobilityEnterDanmuView(Context context) {
        super(context);
    }

    public NobilityEnterDanmuView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.slzhibo.library.ui.view.custom.LeftAnimView
    public void initView(Context context) {
        this.animType = ConstantUtils.LEFT_ANIM_ENTER_NOBILITY_TYPE;
        this.rootView = RelativeLayout.inflate(context, R.layout.fq_layout_nobility_enter_anim_view, this);
        this.alAvatarBg = (RelativeLayout) findViewById(R.id.rl_nobility_avatar_bg);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvTips = (DrawableTextView) findViewById(R.id.tv_tips);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.ivBadge = (ImageView) findViewById(R.id.iv_badge);
        setRootViewVisibility(4);
        this.tvName.setSelected(true);
    }

    @Override // com.slzhibo.library.ui.view.custom.LeftAnimView
    public void addItemInfo(final LeftAnimEntity leftAnimEntity) {
        int i;
        if (leftAnimEntity != null) {
            this.leftAnimEntity = leftAnimEntity;
            leftAnimEntity.leftAnimType = this.animType;
            this.tvName.setText(leftAnimEntity.userName);
            this.alAvatarBg.setBackgroundResource(getAvatarBgDrawableRes(leftAnimEntity.nobilityType));
            this.ivBadge.setImageResource(AppUtils.getNobilityEnterBadgeDrawableRes(leftAnimEntity.nobilityType));
            initViewTopPaddingLayoutParams(this.alAvatarBg, leftAnimEntity.nobilityType);
            this.tvName.setTextColor(ContextCompat.getColor(this.mContext, leftAnimEntity.nobilityType == 7 ? R.color.fq_nobility_red : R.color.fq_text_white_color));
            GlideUtils.loadAvatar(this.mContext, this.ivAvatar, leftAnimEntity.avatar);
            DrawableTextView drawableTextView = this.tvTips;
            Context context = this.mContext;
            drawableTextView.setText(context.getString(R.string.fq_nobility_open_left_anim_enter_tips, AppUtils.getNobilityBadgeName(context, leftAnimEntity.nobilityType)));
            int i2 = leftAnimEntity.nobilityType;
            if (i2 == 5) {
                i = R.drawable.fq_gradient_gj_shape;
            } else if (i2 == 6) {
                i = R.drawable.fq_gradient_gw_shape;
            } else if (i2 != 7) {
                i = -1;
            } else {
                i = R.drawable.fq_gradient_hd_shape;
            }
            if (i != -1) {
                this.tvTips.setTextDrawable(ContextCompat.getDrawable(this.mContext, i));
            }
            Observable.timer(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                /* class com.slzhibo.library.ui.view.custom.NobilityEnterDanmuView.AnonymousClass1 */

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
                    NobilityEnterDanmuView nobilityEnterDanmuView = NobilityEnterDanmuView.this;
                    nobilityEnterDanmuView.isShowing = true;
                    nobilityEnterDanmuView.startAnim(nobilityEnterDanmuView.rootView, leftAnimEntity.nobilityType);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startAnim(View view, int i) {
        int width = view.getWidth();
        float f = (float) (-width);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, this.animPropertyName, f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, this.animPropertyName, 0.0f, f);
        ofFloat2.setStartDelay(((long) getAnimDuration(i)) - 2000);
        this.animatorSet.play(ofFloat).before(ofFloat2);
        this.animatorSet.setDuration(2000L);
        this.animatorSet.addListener(new Animator.AnimatorListener() {
            /* class com.slzhibo.library.ui.view.custom.NobilityEnterDanmuView.AnonymousClass2 */

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                NobilityEnterDanmuView.this.startAnim();
            }

            public void onAnimationEnd(Animator animator) {
                NobilityEnterDanmuView.this.endAnim();
            }
        });
        this.animatorSet.start();
    }

    @DrawableRes
    private int getAvatarBgDrawableRes(int i) {
        if (i == 5) {
            return R.drawable.fq_ic_nobility_open_avatar_bg_5;
        }
        if (i == 6) {
            return R.drawable.fq_ic_nobility_open_avatar_bg_6;
        }
        if (i != 7) {
            return R.drawable.fq_ic_nobility_open_avatar_bg_1;
        }
        return R.drawable.fq_ic_nobility_open_avatar_bg_7;
    }

    private void initViewTopPaddingLayoutParams(View view, int i) {
        view.setPadding(0, ConvertUtils.dp2px((float) getAvatarBgTopPadding(i)), 0, 0);
    }
}
