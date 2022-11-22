package com.slzhibo.library.ui.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.slzhibo.library.R;

import java.util.ArrayList;
import java.util.List;

public class MicVoiceView extends View {
    private boolean isOneFinish;
    private boolean isThreeFinish;
    private boolean isTwoFinish;
    private final List<Integer> mAlphas;
    private int mColor;
    private float mCoreRadius;
    private float mDiffuseSpeed;
    private boolean mIsDiffuse;
    private float mMaxWidth;
    private Paint mPaint;
    private final List<Float> mWidths;

    public MicVoiceView(Context context) {
        this(context, null);
    }

    public MicVoiceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public MicVoiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mColor = -1;
        this.mCoreRadius = 50.0f;
        this.mMaxWidth = 255.0f;
        this.mDiffuseSpeed = 1.0f;
        this.mAlphas = new ArrayList();
        this.mWidths = new ArrayList();
        init();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MicVoiceView, i, 0);
        this.mColor = obtainStyledAttributes.getColor(R.styleable.MicVoiceView_diffuse_color, this.mColor);
        this.mCoreRadius = obtainStyledAttributes.getDimension(R.styleable.MicVoiceView_diffuse_coreRadius, this.mCoreRadius);
        this.mDiffuseSpeed = obtainStyledAttributes.getFloat(R.styleable.MicVoiceView_diffuse_speed, this.mDiffuseSpeed);
        obtainStyledAttributes.recycle();
    }

    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mWidths.clear();
        this.mAlphas.clear();
        this.isOneFinish = true;
        this.isTwoFinish = true;
        this.isThreeFinish = true;
        this.mWidths.add(Float.valueOf(0.0f));
        this.mAlphas.add(255);
        this.mIsDiffuse = false;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mMaxWidth = ((float) (i / 2)) - this.mCoreRadius;
    }

    public void onDraw(Canvas canvas) {
        this.mPaint.setColor(this.mColor);
        for (int i = 0; i < this.mAlphas.size(); i++) {
            if (i == 0) {
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(2.0f);
            } else {
                this.mPaint.setStyle(Paint.Style.FILL);
            }
            this.mPaint.setAlpha(this.mAlphas.get(i).intValue());
            Float f = this.mWidths.get(i);
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.mCoreRadius + f.floatValue(), this.mPaint);
            this.mWidths.set(i, Float.valueOf(f.floatValue() + this.mDiffuseSpeed));
            this.mAlphas.set(i, Integer.valueOf(255.0f - ((this.mWidths.get(i).floatValue() / this.mMaxWidth) * 255.0f) > 0.0f ? (int) (255.0f - ((this.mWidths.get(i).floatValue() / this.mMaxWidth) * 255.0f)) : 1));
            if (i == 0 && f.floatValue() >= this.mMaxWidth * 0.75f && this.mAlphas.size() > 1 && this.isOneFinish) {
                this.mAlphas.set(1, 255);
                this.mWidths.set(1, Float.valueOf(0.0f));
                this.isOneFinish = false;
                this.isTwoFinish = true;
            }
            if (i == 1 && f.floatValue() >= this.mMaxWidth * 0.5f && this.mAlphas.size() > 2 && this.isTwoFinish) {
                this.isTwoFinish = false;
                this.isThreeFinish = true;
                this.mAlphas.set(2, 255);
                this.mWidths.set(2, Float.valueOf(0.0f));
            }
            if (i == 2 && f.floatValue() >= this.mMaxWidth && this.isThreeFinish) {
                this.isThreeFinish = false;
                this.isOneFinish = true;
                this.mAlphas.set(0, 255);
                this.mWidths.set(0, Float.valueOf(0.0f));
            }
        }
        if (this.mWidths.size() < 3) {
            List<Float> list = this.mWidths;
            if (list.get(list.size() - 1).floatValue() > this.mMaxWidth * 0.75f) {
                this.mAlphas.add(255);
                this.mWidths.add(Float.valueOf(0.0f));
            }
        }
        if (this.mIsDiffuse) {
            invalidate();
        }
    }

    public void start() {
        if (!this.mIsDiffuse) {
            this.mIsDiffuse = true;
            invalidate();
        }
    }

    public void stop() {
        this.mIsDiffuse = false;
        this.mWidths.clear();
        this.mAlphas.clear();
        this.mAlphas.add(255);
        this.mWidths.add(Float.valueOf(0.0f));
        invalidate();
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public void setCoreRadius(int i) {
        this.mCoreRadius = (float) i;
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = (float) i;
    }
}
