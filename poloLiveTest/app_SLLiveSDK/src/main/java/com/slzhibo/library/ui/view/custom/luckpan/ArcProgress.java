package com.slzhibo.library.ui.view.custom.luckpan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.slzhibo.library.R;
import com.slzhibo.library.utils.SystemUtils;
import com.tencent.smtt.sdk.TbsListener;

import java.text.DecimalFormat;

public class ArcProgress extends View {
    private float arcAngle;
    private String bottomText;
    private final int default_finished_color;
    private final float default_stroke_width;
    private final float default_suffix_padding;
    private final float default_suffix_text_size;
    private final int default_text_color;
    private float default_text_size;
    protected Paint finishPaint;
    private int finishedStrokeColor;
    private int max;
    private final int min_size;
    private float progress;
    private final RectF rectF;
    private float strokeWidth;
    private float suffixTextPadding;
    private float suffixTextSize;
    private int textColor;
    private float textSize;
    private Paint unFinishPaint;

    public ArcProgress(Context context) {
        this(context, null);
    }

    public ArcProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArcProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rectF = new RectF();
        this.progress = 0.0f;
        this.default_finished_color = Color.parseColor("#24ffffff");
        this.default_text_color = Color.rgb(66, 145, (int) TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID);
        this.default_text_size = SystemUtils.sp2px(18.0f);
        this.min_size = (int) SystemUtils.dp2px(100.0f);
        this.default_text_size = SystemUtils.sp2px(40.0f);
        this.default_suffix_text_size = SystemUtils.sp2px(15.0f);
        this.default_suffix_padding = SystemUtils.dp2px(4.0f);
        SystemUtils.sp2px(10.0f);
        this.default_stroke_width = SystemUtils.dp2px(8.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ArcProgress, i, 0);
        initByAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        initPainters();
    }

    /* access modifiers changed from: protected */
    @SuppressLint("ResourceType")
    public void initByAttributes(TypedArray typedArray) {
        this.finishedStrokeColor = typedArray.getColor(R.styleable.ArcProgress_arc_finished_color, this.default_finished_color);
        this.textColor = typedArray.getColor(R.styleable.ArcProgress_arc_text_color, this.default_text_color);
        this.textSize = typedArray.getDimension(R.styleable.ArcProgress_arc_text_size, this.default_text_size);
        this.arcAngle = typedArray.getFloat(R.styleable.ArcProgress_arc_angle, 288.0f);
        setMax(typedArray.getInt(R.styleable.ArcProgress_arc_max, 100));
        setProgress(typedArray.getFloat(R.styleable.ArcProgress_arc_progress, 0.0f));
        this.strokeWidth = typedArray.getDimension(R.styleable.ArcProgress_arc_stroke_width, this.default_stroke_width);
        this.suffixTextSize = typedArray.getDimension(R.styleable.ArcProgress_arc_suffix_text_size, this.default_suffix_text_size);
        this.suffixTextPadding = typedArray.getDimension(R.styleable.ArcProgress_arc_suffix_text_padding, this.default_suffix_padding);
        this.bottomText = typedArray.getString(R.styleable.ArcProgress_arc_bottom_text);
    }

    /* access modifiers changed from: protected */
    public void initPainters() {
        this.finishPaint = new TextPaint();
        this.finishPaint.setTextSize(this.textSize);
        this.finishPaint.setAntiAlias(true);
        this.finishPaint.setStrokeWidth(this.strokeWidth);
        this.finishPaint.setStyle(Paint.Style.STROKE);
        this.finishPaint.setStrokeCap(Paint.Cap.ROUND);
        this.finishPaint.setColor(this.finishedStrokeColor);
        this.unFinishPaint = new Paint();
        this.unFinishPaint.setShader(new LinearGradient(0.0f, (float) (getHeight() / 2), (float) getWidth(), (float) (getHeight() / 2), Color.parseColor("#FF8013"), Color.parseColor("#FF4154"), Shader.TileMode.CLAMP));
        this.unFinishPaint.setAntiAlias(true);
        this.unFinishPaint.setStrokeWidth(this.strokeWidth);
        this.unFinishPaint.setStyle(Paint.Style.STROKE);
        this.unFinishPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(float f) {
        this.strokeWidth = f;
        invalidate();
    }

    public float getSuffixTextSize() {
        return this.suffixTextSize;
    }

    public String getBottomText() {
        return this.bottomText;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float f) {
        this.progress = Float.valueOf(new DecimalFormat("#.##").format((double) f)).floatValue();
        if (this.progress > ((float) getMax())) {
            this.progress %= (float) getMax();
        }
        invalidate();
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int i) {
        if (i > 0) {
            this.max = i;
            invalidate();
        }
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
        invalidate();
    }

    public int getFinishedStrokeColor() {
        return this.finishedStrokeColor;
    }

    public float getArcAngle() {
        return this.arcAngle;
    }

    public float getSuffixTextPadding() {
        return this.suffixTextPadding;
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumHeight() {
        return this.min_size;
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumWidth() {
        return this.min_size;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(i, i2);
        int min = Math.min(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
        float f = (float) min;
        this.rectF.set((this.strokeWidth / 2.0f) + ((float) getPaddingLeft()), (this.strokeWidth / 2.0f) + ((float) getPaddingTop()), (f - (this.strokeWidth / 2.0f)) - ((float) getPaddingRight()), (f - (this.strokeWidth / 2.0f)) - ((float) getPaddingBottom()));
        setMeasuredDimension(min, min);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = 270.0f - (this.arcAngle / 2.0f);
        float max2 = (this.progress / ((float) getMax())) * this.arcAngle;
        float f2 = this.progress == 0.0f ? 0.01f : f;
        canvas.drawArc(this.rectF, f, this.arcAngle, false, this.finishPaint);
        canvas.drawArc(this.rectF, f2, max2, false, this.unFinishPaint);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putFloat("stroke_width", getStrokeWidth());
        bundle.putFloat("suffix_text_size", getSuffixTextSize());
        bundle.putFloat("suffix_text_padding", getSuffixTextPadding());
        bundle.putString("bottom_text", getBottomText());
        bundle.putInt("text_color", getTextColor());
        bundle.putFloat("progress", getProgress());
        bundle.putInt("max", getMax());
        bundle.putInt("finished_stroke_color", getFinishedStrokeColor());
        bundle.putFloat("arc_angle", getArcAngle());
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.strokeWidth = bundle.getFloat("stroke_width");
            this.suffixTextSize = bundle.getFloat("suffix_text_size");
            this.suffixTextPadding = bundle.getFloat("suffix_text_padding");
            this.bottomText = bundle.getString("bottom_text");
            this.textSize = bundle.getFloat("text_size");
            this.textColor = bundle.getInt("text_color");
            setMax(bundle.getInt("max"));
            setProgress(bundle.getFloat("progress"));
            this.finishedStrokeColor = bundle.getInt("finished_stroke_color");
            initPainters();
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
