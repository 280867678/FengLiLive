package com.slzhibo.library.ui.view.widget.progress;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.animation.PathInterpolatorCompat;

import com.slzhibo.library.R;
import com.tencent.smtt.sdk.TbsListener;

public class AnimDownloadProgressButton extends AppCompatTextView {
    private LoadingEndListener loadingEndListener;
    private float mAboveTextSize;
    private RectF mBackgroundBounds;
    private int[] mBackgroundColor;
    private Paint mBackgroundPaint;
    private int mBackgroundSecondColor;
    private float mButtonRadius;
    private CharSequence mCurrentText;
    private ButtonController mCustomerController;
    private ButtonController mDefaultController;
    private Paint mDot1Paint;
    private float mDot1transX;
    private Paint mDot2Paint;
    private float mDot2transX;
    private AnimatorSet mDotAnimationSet;
    private LinearGradient mFillBgGradient;
    private int mMaxProgress;
    private int mMinProgress;
    private int[] mOriginBackgroundColor;
    private float mProgress;
    private ValueAnimator mProgressAnimation;
    private LinearGradient mProgressBgGradient;
    private float mProgressPercent;
    private LinearGradient mProgressTextGradient;
    private int mState;
    private int mTextColor;
    private int mTextCoverColor;
    private volatile Paint mTextPaint;

    public interface LoadingEndListener {
        void onLoadingEnd();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int calculateDot1AlphaByTime(int i) {
        double d;
        int i2 = 160;
        if (i >= 0 && i <= 160) {
            return 0;
        }
        if (160 < i && i <= 243) {
            d = 3.072289156626506d;
        } else if (1160 >= i) {
            return 255;
        } else {
            i2 = 1243;
            if (i > 1243) {
                return 255;
            }
            d = -3.072289156626506d;
        }
        return (int) (((double) (i - i2)) * d);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int calculateDot2AlphaByTime(int i) {
        if (i >= 0 && i <= 83) {
            return (int) (((double) i) * 3.072289156626506d);
        }
        if (83 >= i || i > 1000) {
            return (1000 >= i || i > 1083) ? (1083 >= i || i > 1243) ? 255 : 0 : (int) (((double) (i - 1083)) * -3.072289156626506d);
        }
        return 255;
    }

    public AnimDownloadProgressButton(Context context) {
        this(context, null);
    }

    public AnimDownloadProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAboveTextSize = 50.0f;
        this.mProgress = -1.0f;
        if (!isInEditMode()) {
            initController();
            initAttrs(context, attributeSet);
            init();
            setupAnimations();
            return;
        }
        initController();
    }

    private void initController() {
        this.mDefaultController = new DefaultButtonController();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.AppCompatTextView
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ButtonController switchController = switchController();
        if (switchController.enablePress()) {
            if (this.mOriginBackgroundColor == null) {
                this.mOriginBackgroundColor = new int[2];
                int[] iArr = this.mOriginBackgroundColor;
                int[] iArr2 = this.mBackgroundColor;
                iArr[0] = iArr2[0];
                iArr[1] = iArr2[1];
            }
            if (isPressed()) {
                int pressedColor = switchController.getPressedColor(this.mBackgroundColor[0]);
                int pressedColor2 = switchController.getPressedColor(this.mBackgroundColor[1]);
                if (switchController.enableGradient()) {
                    initGradientColor(pressedColor, pressedColor2);
                } else {
                    initGradientColor(pressedColor, pressedColor);
                }
            } else if (switchController.enableGradient()) {
                int[] iArr3 = this.mOriginBackgroundColor;
                initGradientColor(iArr3[0], iArr3[1]);
            } else {
                int[] iArr4 = this.mOriginBackgroundColor;
                initGradientColor(iArr4[0], iArr4[0]);
            }
            invalidate();
        }
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AnimDownloadProgressButton);
        int color = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_background_color, Color.parseColor("#6699ff"));
        initGradientColor(color, color);
        this.mBackgroundSecondColor = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_background_second_color, -3355444);
        this.mButtonRadius = obtainStyledAttributes.getFloat(R.styleable.AnimDownloadProgressButton_progressbtn_radius, (float) (getMeasuredHeight() / 2));
        this.mAboveTextSize = obtainStyledAttributes.getFloat(R.styleable.AnimDownloadProgressButton_progressbtn_text_size, 50.0f);
        this.mTextColor = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_text_color, color);
        this.mTextCoverColor = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_text_covercolor, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.AnimDownloadProgressButton_progressbtn_enable_gradient, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.AnimDownloadProgressButton_progressbtn_enable_press, false);
        DefaultButtonController defaultButtonController = (DefaultButtonController) this.mDefaultController;
        defaultButtonController.setEnableGradient(z);
        defaultButtonController.setEnablePress(z2);
        if (z) {
            initGradientColor(this.mDefaultController.getLighterColor(this.mBackgroundColor[0]), this.mBackgroundColor[0]);
        }
        obtainStyledAttributes.recycle();
    }

    private void init() {
        this.mMaxProgress = 100;
        this.mMinProgress = 0;
        this.mProgress = 0.0f;
        this.mBackgroundPaint = new Paint();
        this.mBackgroundPaint.setAntiAlias(true);
        this.mBackgroundPaint.setStyle(Paint.Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize(this.mAboveTextSize);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, this.mTextPaint);
        }
        this.mDot1Paint = new Paint();
        this.mDot1Paint.setAntiAlias(true);
        this.mDot1Paint.setTextSize(this.mAboveTextSize);
        this.mDot2Paint = new Paint();
        this.mDot2Paint.setAntiAlias(true);
        this.mDot2Paint.setTextSize(this.mAboveTextSize);
        this.mState = 1;
        invalidate();
    }

    private int[] initGradientColor(int i, int i2) {
        this.mBackgroundColor = new int[2];
        int[] iArr = this.mBackgroundColor;
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    private void setupAnimations() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 20.0f);
        ofFloat.setInterpolator(PathInterpolatorCompat.create(0.11f, 0.0f, 0.12f, 1.0f));
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.slzhibo.library.ui.view.widget.progress.AnimDownloadProgressButton.AnonymousClass1 */

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                AnimDownloadProgressButton.this.mDot1transX = floatValue;
                AnimDownloadProgressButton.this.mDot2transX = floatValue;
                AnimDownloadProgressButton.this.invalidate();
            }
        });
        ofFloat.setDuration(1243L);
        ofFloat.setRepeatMode(ValueAnimator.RESTART);
        ofFloat.setRepeatCount(-1);
        final ValueAnimator duration = ValueAnimator.ofInt(0, 1243).setDuration(1243L);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.slzhibo.library.ui.view.widget.progress.AnimDownloadProgressButton.AnonymousClass2 */

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) duration.getAnimatedValue()).intValue();
                int calculateDot1AlphaByTime = AnimDownloadProgressButton.this.calculateDot1AlphaByTime(intValue);
                int calculateDot2AlphaByTime = AnimDownloadProgressButton.this.calculateDot2AlphaByTime(intValue);
                AnimDownloadProgressButton.this.mDot1Paint.setColor(AnimDownloadProgressButton.this.mTextCoverColor);
                AnimDownloadProgressButton.this.mDot2Paint.setColor(AnimDownloadProgressButton.this.mTextCoverColor);
                AnimDownloadProgressButton.this.mDot1Paint.setAlpha(calculateDot1AlphaByTime);
                AnimDownloadProgressButton.this.mDot2Paint.setAlpha(calculateDot2AlphaByTime);
            }
        });
        duration.addListener(new Animator.AnimatorListener() {
            /* class com.slzhibo.library.ui.view.widget.progress.AnimDownloadProgressButton.AnonymousClass3 */

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                AnimDownloadProgressButton.this.mDot1Paint.setAlpha(0);
                AnimDownloadProgressButton.this.mDot2Paint.setAlpha(0);
            }
        });
        duration.setRepeatMode(ValueAnimator.RESTART);
        duration.setRepeatCount(-1);
        this.mDotAnimationSet = new AnimatorSet();
        this.mDotAnimationSet.playTogether(duration, ofFloat);
        this.mProgressAnimation = ValueAnimator.ofFloat(0.0f, 0.3f, 0.5f, 0.8f, 1.0f).setDuration(3000L);
        this.mProgressAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.slzhibo.library.ui.view.widget.progress.$$Lambda$AnimDownloadProgressButton$5q9oCXoi_xbA4VyuZr6fHe93xE */

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimDownloadProgressButton.this.lambda$setupAnimations$0$AnimDownloadProgressButton(valueAnimator);
            }
        });
    }

    public /* synthetic */ void lambda$setupAnimations$0$AnimDownloadProgressButton(ValueAnimator valueAnimator) {
        this.mProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f;
        if (this.mProgress >= 99.0f) {
            this.mProgressAnimation.cancel();
            LoadingEndListener loadingEndListener2 = this.loadingEndListener;
            if (loadingEndListener2 != null) {
                loadingEndListener2.onLoadingEnd();
            }
        }
        invalidate();
    }

    public void cancelAnimaiton() {
        ValueAnimator valueAnimator = this.mProgressAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mProgressAnimation.cancel();
        }
    }

    public void setLoadingEndListener(LoadingEndListener loadingEndListener2) {
        this.loadingEndListener = loadingEndListener2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode()) {
            drawing(canvas);
        }
    }

    private void drawing(Canvas canvas) {
        drawBackground(canvas);
        drawTextAbove(canvas);
    }

    private void drawBackground(Canvas canvas) {
        this.mBackgroundBounds = new RectF();
        if (this.mButtonRadius == 0.0f) {
            this.mButtonRadius = (float) (getMeasuredHeight() / 2);
        }
        RectF rectF = this.mBackgroundBounds;
        rectF.left = 2.0f;
        rectF.top = 2.0f;
        rectF.right = (float) (getMeasuredWidth() - 2);
        this.mBackgroundBounds.bottom = (float) (getMeasuredHeight() - 2);
        ButtonController switchController = switchController();
        int i = this.mState;
        if (i == 0) {
            if (switchController.enableGradient()) {
                this.mFillBgGradient = new LinearGradient(0.0f, (float) (getMeasuredHeight() / 2), (float) getMeasuredWidth(), (float) (getMeasuredHeight() / 2), this.mBackgroundColor, (float[]) null, Shader.TileMode.CLAMP);
                this.mBackgroundPaint.setShader(this.mFillBgGradient);
            } else {
                if (this.mBackgroundPaint.getShader() != null) {
                    this.mBackgroundPaint.setShader(null);
                }
                this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
            }
            RectF rectF2 = this.mBackgroundBounds;
            float f = this.mButtonRadius;
            canvas.drawRoundRect(rectF2, f, f, this.mBackgroundPaint);
        } else if (i == 1) {
            if (switchController.enableGradient()) {
                this.mProgressPercent = this.mProgress / (((float) this.mMaxProgress) + 0.0f);
                int[] iArr = this.mBackgroundColor;
                int[] iArr2 = {iArr[0], iArr[1], this.mBackgroundSecondColor};
                float f2 = this.mProgressPercent;
                this.mProgressBgGradient = new LinearGradient(0.0f, 0.0f, (float) getMeasuredWidth(), 0.0f, iArr2, new float[]{0.0f, f2, f2 + 0.001f}, Shader.TileMode.CLAMP);
                this.mBackgroundPaint.setShader(this.mProgressBgGradient);
            } else {
                this.mProgressPercent = this.mProgress / (((float) this.mMaxProgress) + 0.0f);
                float measuredWidth = (float) getMeasuredWidth();
                int[] iArr3 = {this.mBackgroundColor[0], this.mBackgroundSecondColor};
                float f3 = this.mProgressPercent;
                this.mProgressBgGradient = new LinearGradient(0.0f, 0.0f, measuredWidth, 0.0f, iArr3, new float[]{f3, f3 + 0.001f}, Shader.TileMode.CLAMP);
                this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
                this.mBackgroundPaint.setShader(this.mProgressBgGradient);
            }
            RectF rectF3 = this.mBackgroundBounds;
            float f4 = this.mButtonRadius;
            canvas.drawRoundRect(rectF3, f4, f4, this.mBackgroundPaint);
        } else if (i == 2) {
            if (switchController.enableGradient()) {
                this.mFillBgGradient = new LinearGradient(0.0f, (float) (getMeasuredHeight() / 2), (float) getMeasuredWidth(), (float) (getMeasuredHeight() / 2), this.mBackgroundColor, (float[]) null, Shader.TileMode.CLAMP);
                this.mBackgroundPaint.setShader(this.mFillBgGradient);
            } else {
                this.mBackgroundPaint.setShader(null);
                this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
            }
            RectF rectF4 = this.mBackgroundBounds;
            float f5 = this.mButtonRadius;
            canvas.drawRoundRect(rectF4, f5, f5, this.mBackgroundPaint);
        }
    }

    private void drawTextAbove(Canvas canvas) {
        float height = ((float) (canvas.getHeight() / 2)) - ((this.mTextPaint.descent() / 2.0f) + (this.mTextPaint.ascent() / 2.0f));
        if (this.mCurrentText == null) {
            this.mCurrentText = "";
        }
        float measureText = this.mTextPaint.measureText(this.mCurrentText.toString());
        int i = this.mState;
        if (i == 0) {
            this.mTextPaint.setShader(null);
            this.mTextPaint.setColor(this.mTextCoverColor);
            canvas.drawText(this.mCurrentText.toString(), (((float) getMeasuredWidth()) - measureText) / 2.0f, height, this.mTextPaint);
        } else if (i == 1) {
            float measuredWidth = ((float) getMeasuredWidth()) * this.mProgressPercent;
            float f = measureText / 2.0f;
            float measuredWidth2 = ((float) (getMeasuredWidth() / 2)) - f;
            float measuredWidth3 = ((float) (getMeasuredWidth() / 2)) + f;
            float measuredWidth4 = ((f - ((float) (getMeasuredWidth() / 2))) + measuredWidth) / measureText;
            if (measuredWidth <= measuredWidth2) {
                this.mTextPaint.setShader(null);
                this.mTextPaint.setColor(this.mTextColor);
            } else if (measuredWidth2 >= measuredWidth || measuredWidth > measuredWidth3) {
                this.mTextPaint.setShader(null);
                this.mTextPaint.setColor(this.mTextCoverColor);
            } else {
                this.mProgressTextGradient = new LinearGradient((((float) getMeasuredWidth()) - measureText) / 2.0f, 0.0f, (((float) getMeasuredWidth()) + measureText) / 2.0f, 0.0f, new int[]{this.mTextCoverColor, this.mTextColor}, new float[]{measuredWidth4, measuredWidth4 + 0.001f}, Shader.TileMode.CLAMP);
                this.mTextPaint.setColor(this.mTextColor);
                this.mTextPaint.setShader(this.mProgressTextGradient);
            }
            canvas.drawText(this.mCurrentText.toString(), (((float) getMeasuredWidth()) - measureText) / 2.0f, height, this.mTextPaint);
        } else if (i == 2) {
            this.mTextPaint.setColor(this.mTextCoverColor);
            canvas.drawText(this.mCurrentText.toString(), (((float) getMeasuredWidth()) - measureText) / 2.0f, height, this.mTextPaint);
            canvas.drawCircle(((((float) getMeasuredWidth()) + measureText) / 2.0f) + 4.0f + this.mDot1transX, height, 4.0f, this.mDot1Paint);
            canvas.drawCircle(((((float) getMeasuredWidth()) + measureText) / 2.0f) + 24.0f + this.mDot2transX, height, 4.0f, this.mDot2Paint);
        }
    }

    private ButtonController switchController() {
        ButtonController buttonController = this.mCustomerController;
        if (buttonController != null) {
            return buttonController;
        }
        return this.mDefaultController;
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int i) {
        if (this.mState != i) {
            this.mState = i;
            invalidate();
            if (i == 2) {
                this.mDotAnimationSet.start();
            } else if (i == 0) {
                this.mDotAnimationSet.cancel();
            } else if (i == 1) {
                this.mDotAnimationSet.cancel();
            }
        }
    }

    public void setCurrentText(CharSequence charSequence) {
        this.mCurrentText = charSequence;
        invalidate();
    }

    @TargetApi(19)
    public void setProgressText(String str, float f) {
        if (f >= ((float) this.mMinProgress) && f <= ((float) this.mMaxProgress)) {
            this.mCurrentText = str;
            if (this.mProgressAnimation.isRunning()) {
                this.mProgressAnimation.start();
            } else {
                this.mProgressAnimation.start();
            }
        } else if (f < ((float) this.mMinProgress)) {
            this.mProgress = 0.0f;
        }
    }

    public float getProgress() {
        return this.mProgress;
    }

    public void setProgress(float f) {
        this.mProgress = f;
    }

    public void setProgressBtnBackgroundColor(int i) {
        initGradientColor(i, i);
    }

    public void setProgressBtnBackgroundSecondColor(int i) {
        this.mBackgroundSecondColor = i;
    }

    public float getButtonRadius() {
        return this.mButtonRadius;
    }

    public void setButtonRadius(float f) {
        this.mButtonRadius = f;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        this.mTextColor = i;
    }

    public int getTextCoverColor() {
        return this.mTextCoverColor;
    }

    public void setTextCoverColor(int i) {
        this.mTextCoverColor = i;
    }

    public int getMinProgress() {
        return this.mMinProgress;
    }

    public void setMinProgress(int i) {
        this.mMinProgress = i;
    }

    public int getMaxProgress() {
        return this.mMaxProgress;
    }

    public void setMaxProgress(int i) {
        this.mMaxProgress = i;
    }

    public void setTextSize(float f) {
        this.mAboveTextSize = f;
        this.mTextPaint.setTextSize(f);
    }

    public float getTextSize() {
        return this.mAboveTextSize;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.mState = savedState.state;
            this.mProgress = (float) savedState.progress;
            this.mCurrentText = savedState.currentText;
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        CharSequence charSequence = this.mCurrentText;
        if (charSequence != null) {
            return new SavedState(onSaveInstanceState, (int) this.mProgress, this.mState, charSequence.toString());
        }
        return super.onSaveInstanceState();
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* class com.slzhibo.library.ui.view.widget.progress.AnimDownloadProgressButton.SavedState.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private final String currentText;
        private final int progress;
        private final int state;

        public SavedState(Parcelable parcelable, int i, int i2, String str) {
            super(parcelable);
            this.progress = i;
            this.state = i2;
            this.currentText = str;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.progress = parcel.readInt();
            this.state = parcel.readInt();
            this.currentText = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.progress);
            parcel.writeInt(this.state);
            parcel.writeString(this.currentText);
        }
    }
}
