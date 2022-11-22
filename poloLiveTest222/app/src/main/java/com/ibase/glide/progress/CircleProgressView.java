package com.ibase.glide.progress;

//package com.ibase.glide.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.blmvl.blvl.R;
//import com.ibase.glide.R$styleable;
//import p067e.p260n.p261a.p263j.C4722b;

/* loaded from: classes.dex */
public class CircleProgressView extends ProgressBar {

    /* renamed from: A */
    public Paint f3887A;

    /* renamed from: B */
    public int f3888B;

    /* renamed from: C */
    public int f3889C;

    /* renamed from: b */
    public int f3890b;

    /* renamed from: c */
    public int f3891c;

    /* renamed from: d */
    public int f3892d;

    /* renamed from: e */
    public int f3893e;

    /* renamed from: f */
    public int f3894f;

    /* renamed from: g */
    public int f3895g;

    /* renamed from: h */
    public float f3896h;

    /* renamed from: i */
    public String f3897i;

    /* renamed from: j */
    public String f3898j;

    /* renamed from: k */
    public boolean f3899k;

    /* renamed from: l */
    public boolean f3900l;

    /* renamed from: m */
    public int f3901m;

    /* renamed from: n */
    public int f3902n;

    /* renamed from: o */
    public int f3903o;

    /* renamed from: p */
    public int f3904p;

    /* renamed from: q */
    public int f3905q;

    /* renamed from: r */
    public int f3906r;

    /* renamed from: s */
    public boolean f3907s;

    /* renamed from: t */
    public RectF f3908t;

    /* renamed from: u */
    public RectF f3909u;

    /* renamed from: v */
    public int f3910v;

    /* renamed from: w */
    public Paint f3911w;

    /* renamed from: x */
    public Paint f3912x;

    /* renamed from: y */
    public Paint f3913y;

    /* renamed from: z */
    public Paint f3914z;

    /* renamed from: com.ibase.glide.progress.CircleProgressView$a */
    /* loaded from: classes.dex */
    public class C1630a implements ValueAnimator.AnimatorUpdateListener {
        public C1630a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CircleProgressView.this.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    public CircleProgressView(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public final void m18755a() {
        this.f3911w = new Paint();
        this.f3911w.setColor(this.f3895g);
        this.f3911w.setStyle(Paint.Style.FILL);
        this.f3911w.setTextSize(this.f3894f);
        this.f3911w.setTextSkewX(this.f3896h);
        this.f3911w.setAntiAlias(true);
        this.f3912x = new Paint();
        this.f3912x.setColor(this.f3893e);
        this.f3912x.setStyle(this.f3904p == 2 ? Paint.Style.FILL : Paint.Style.STROKE);
        this.f3912x.setAntiAlias(true);
        this.f3912x.setStrokeWidth(this.f3891c);
        this.f3913y = new Paint();
        this.f3913y.setColor(this.f3892d);
        this.f3913y.setStyle(this.f3904p == 2 ? Paint.Style.FILL : Paint.Style.STROKE);
        this.f3913y.setAntiAlias(true);
        this.f3913y.setStrokeCap(this.f3900l ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        this.f3913y.setStrokeWidth(this.f3890b);
        if (this.f3907s) {
            this.f3914z = new Paint();
            this.f3914z.setStyle(Paint.Style.FILL);
            this.f3914z.setAntiAlias(true);
            this.f3914z.setColor(this.f3903o);
        }
        if (this.f3904p == 2) {
            this.f3887A = new Paint();
            this.f3887A.setStyle(Paint.Style.STROKE);
            this.f3887A.setColor(this.f3906r);
            this.f3887A.setStrokeWidth(this.f3910v);
            this.f3887A.setAntiAlias(true);
        }
    }

    /* renamed from: b */
    public final void m18751b(Canvas canvas) {
        canvas.save();
        canvas.translate(this.f3888B / 2, this.f3889C / 2);
        float progress = (getProgress() * 1.0f) / getMax();
        int i = this.f3901m;
        float acos = (float) ((Math.acos((i - (progress * (i * 2))) / i) * 180.0d) / 3.141592653589793d);
        float f = acos * 2.0f;
        int i2 = this.f3901m;
        this.f3908t = new RectF(-i2, -i2, i2, i2);
        this.f3912x.setStyle(Paint.Style.FILL);
        canvas.drawArc(this.f3908t, acos + 90.0f, 360.0f - f, false, this.f3912x);
        canvas.rotate(180.0f);
        this.f3913y.setStyle(Paint.Style.FILL);
        canvas.drawArc(this.f3908t, 270.0f - acos, f, false, this.f3913y);
        canvas.rotate(180.0f);
        if (this.f3899k) {
            String str = this.f3898j + getProgress() + this.f3897i;
            canvas.drawText(str, (-this.f3911w.measureText(str)) / 2.0f, (-(this.f3911w.descent() + this.f3911w.ascent())) / 2.0f, this.f3911w);
        }
    }

    /* renamed from: c */
    public final void m18749c(Canvas canvas) {
        canvas.save();
        canvas.translate(this.f3888B / 2, this.f3889C / 2);
        if (this.f3907s) {
            canvas.drawCircle(0.0f, 0.0f, this.f3901m - (Math.min(this.f3890b, this.f3891c) / 2), this.f3914z);
        }
        if (this.f3899k) {
            String str = this.f3898j + getProgress() + this.f3897i;
            canvas.drawText(str, (-this.f3911w.measureText(str)) / 2.0f, (-(this.f3911w.descent() + this.f3911w.ascent())) / 2.0f, this.f3911w);
        }
        float progress = ((getProgress() * 1.0f) / getMax()) * 360.0f;
        if (progress != 360.0f) {
            canvas.drawArc(this.f3908t, progress + this.f3902n, 360.0f - progress, false, this.f3912x);
        }
        canvas.drawArc(this.f3908t, this.f3902n, progress, false, this.f3913y);
        canvas.restore();
    }

    public int getInnerBackgroundColor() {
        return this.f3903o;
    }

    public int getInnerPadding() {
        return this.f3905q;
    }

    public int getNormalBarColor() {
        return this.f3893e;
    }

    public int getNormalBarSize() {
        return this.f3891c;
    }

    public int getOuterColor() {
        return this.f3906r;
    }

    public int getOuterSize() {
        return this.f3910v;
    }

    public int getProgressStyle() {
        return this.f3904p;
    }

    public int getRadius() {
        return this.f3901m;
    }

    public int getReachBarColor() {
        return this.f3892d;
    }

    public int getReachBarSize() {
        return this.f3890b;
    }

    public int getStartArc() {
        return this.f3902n;
    }

    public int getTextColor() {
        return this.f3895g;
    }

    public String getTextPrefix() {
        return this.f3898j;
    }

    public int getTextSize() {
        return this.f3894f;
    }

    public float getTextSkewX() {
        return this.f3896h;
    }

    public String getTextSuffix() {
        return this.f3897i;
    }

    @Override // android.view.View
    public void invalidate() {
        m18755a();
        super.invalidate();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        int i = this.f3904p;
        if (i == 0) {
            m18749c(canvas);
        } else if (i == 1) {
            m18751b(canvas);
        } else if (i == 2) {
            m18754a(canvas);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        int max = Math.max(this.f3890b, this.f3891c);
        int max2 = Math.max(max, this.f3910v);
        int i5 = this.f3904p;
        int i6 = 0;
        if (i5 != 0) {
            if (i5 == 1) {
                i6 = getPaddingTop() + getPaddingBottom() + Math.abs(this.f3901m * 2);
                i4 = getPaddingLeft() + getPaddingRight();
                max2 = Math.abs(this.f3901m * 2);
            } else if (i5 != 2) {
                i3 = 0;
            } else {
                i6 = getPaddingTop() + getPaddingBottom() + Math.abs(this.f3901m * 2) + max2;
                i4 = getPaddingLeft() + getPaddingRight() + Math.abs(this.f3901m * 2);
            }
            i3 = i4 + max2;
        } else {
            i6 = getPaddingTop() + getPaddingBottom() + Math.abs(this.f3901m * 2) + max;
            i3 = max + getPaddingLeft() + getPaddingRight() + Math.abs(this.f3901m * 2);
        }
        this.f3888B = ProgressBar.resolveSize(i3, i);
        this.f3889C = ProgressBar.resolveSize(i6, i2);
        setMeasuredDimension(this.f3888B, this.f3889C);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f3904p = bundle.getInt("progressStyle");
            this.f3901m = bundle.getInt("radius");
            this.f3900l = bundle.getBoolean("isReachCapRound");
            this.f3902n = bundle.getInt("startArc");
            this.f3903o = bundle.getInt("innerBgColor");
            this.f3905q = bundle.getInt("innerPadding");
            this.f3906r = bundle.getInt("outerColor");
            this.f3910v = bundle.getInt("outerSize");
            this.f3895g = bundle.getInt("textColor");
            this.f3894f = bundle.getInt("textSize");
            this.f3896h = bundle.getFloat("textSkewX");
            this.f3899k = bundle.getBoolean("textVisible");
            this.f3897i = bundle.getString("textSuffix");
            this.f3898j = bundle.getString("textPrefix");
            this.f3892d = bundle.getInt("reachBarColor");
            this.f3890b = bundle.getInt("reachBarSize");
            this.f3893e = bundle.getInt("normalBarColor");
            this.f3891c = bundle.getInt("normalBarSize");
            m18755a();
            super.onRestoreInstanceState(bundle.getParcelable("state"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("state", super.onSaveInstanceState());
        bundle.putInt("progressStyle", getProgressStyle());
        bundle.putInt("radius", getRadius());
        bundle.putBoolean("isReachCapRound", m18752b());
        bundle.putInt("startArc", getStartArc());
        bundle.putInt("innerBgColor", getInnerBackgroundColor());
        bundle.putInt("innerPadding", getInnerPadding());
        bundle.putInt("outerColor", getOuterColor());
        bundle.putInt("outerSize", getOuterSize());
        bundle.putInt("textColor", getTextColor());
        bundle.putInt("textSize", getTextSize());
        bundle.putFloat("textSkewX", getTextSkewX());
        bundle.putBoolean("textVisible", m18750c());
        bundle.putString("textSuffix", getTextSuffix());
        bundle.putString("textPrefix", getTextPrefix());
        bundle.putInt("reachBarColor", getReachBarColor());
        bundle.putInt("reachBarSize", getReachBarSize());
        bundle.putInt("normalBarColor", getNormalBarColor());
        bundle.putInt("normalBarSize", getNormalBarSize());
        return bundle;
    }

    public void setInnerBackgroundColor(int i) {
        this.f3903o = i;
        invalidate();
    }

    public void setInnerPadding(int i) {
        this.f3905q = C4722b.m4931a(getContext(), i);
        int i2 = (this.f3901m - (this.f3910v / 2)) - this.f3905q;
        float f = -i2;
        float f2 = i2;
        this.f3909u = new RectF(f, f, f2, f2);
        invalidate();
    }

    public void setNormalBarColor(int i) {
        this.f3893e = i;
        invalidate();
    }

    public void setNormalBarSize(int i) {
        this.f3891c = C4722b.m4931a(getContext(), i);
        invalidate();
    }

    public void setOuterColor(int i) {
        this.f3906r = i;
        invalidate();
    }

    public void setOuterSize(int i) {
        this.f3910v = C4722b.m4931a(getContext(), i);
        invalidate();
    }

    public void setProgressInTime(int i, long j) {
        setProgressInTime(i, getProgress(), j);
    }

    public void setProgressStyle(int i) {
        this.f3904p = i;
        invalidate();
    }

    public void setRadius(int i) {
        this.f3901m = C4722b.m4931a(getContext(), i);
        invalidate();
    }

    public void setReachBarColor(int i) {
        this.f3892d = i;
        invalidate();
    }

    public void setReachBarSize(int i) {
        this.f3890b = C4722b.m4931a(getContext(), i);
        invalidate();
    }

    public void setReachCapRound(boolean z) {
        this.f3900l = z;
        invalidate();
    }

    public void setStartArc(int i) {
        this.f3902n = i;
        invalidate();
    }

    public void setTextColor(int i) {
        this.f3895g = i;
        invalidate();
    }

    public void setTextPrefix(String str) {
        this.f3898j = str;
        invalidate();
    }

    public void setTextSize(int i) {
        this.f3894f = C4722b.m4926b(getContext(), i);
        invalidate();
    }

    public void setTextSkewX(float f) {
        this.f3896h = f;
        invalidate();
    }

    public void setTextSuffix(String str) {
        this.f3897i = str;
        invalidate();
    }

    public void setTextVisible(boolean z) {
        this.f3899k = z;
        invalidate();
    }

    public CircleProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setProgressInTime(int i, int i2, long j) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.addUpdateListener(new C1630a());
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j);
        ofInt.start();
    }

    public CircleProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3890b = C4722b.m4931a(getContext(), 2.0f);
        this.f3891c = C4722b.m4931a(getContext(), 2.0f);
        this.f3892d = Color.parseColor("#108ee9");
        this.f3893e = Color.parseColor("#FFD3D6DA");
        this.f3894f = C4722b.m4926b(getContext(), 14.0f);
        this.f3895g = Color.parseColor("#108ee9");
        this.f3897i = "%";
        this.f3898j = "";
        this.f3899k = true;
        this.f3901m = C4722b.m4931a(getContext(), 20.0f);
        this.f3904p = 0;
        this.f3905q = C4722b.m4931a(getContext(), 1.0f);
        this.f3910v = C4722b.m4931a(getContext(), 1.0f);
        m18753a(attributeSet);
        m18755a();
    }

    /* renamed from: c */
    public boolean m18750c() {
        return this.f3899k;
    }

    /* renamed from: b */
    public boolean m18752b() {
        return this.f3900l;
    }

    /* renamed from: a */
    public final void m18753a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircleProgressView);
        this.f3904p = obtainStyledAttributes.getInt(R.styleable.CircleProgressView_cpv_progressStyle, 0);
        this.f3891c = (int) obtainStyledAttributes.getDimension(R.styleable.CircleProgressView_cpv_progressNormalSize, this.f3891c);
        this.f3893e = obtainStyledAttributes.getColor(R.styleable.CircleProgressView_cpv_progressNormalColor, this.f3893e);
        this.f3890b = (int) obtainStyledAttributes.getDimension(R.styleable.CircleProgressView_cpv_progressReachSize, this.f3890b);
        this.f3892d = obtainStyledAttributes.getColor(R.styleable.CircleProgressView_cpv_progressReachColor, this.f3892d);
        this.f3894f = (int) obtainStyledAttributes.getDimension(R.styleable.CircleProgressView_cpv_progressTextSize, this.f3894f);
        this.f3895g = obtainStyledAttributes.getColor(R.styleable.CircleProgressView_cpv_progressTextColor, this.f3895g);
        this.f3896h = obtainStyledAttributes.getDimension(R.styleable.CircleProgressView_cpv_progressTextSkewX, 0.0f);
        if (obtainStyledAttributes.hasValue(R.styleable.CircleProgressView_cpv_progressTextSuffix)) {
            this.f3897i = obtainStyledAttributes.getString(R.styleable.CircleProgressView_cpv_progressTextSuffix);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CircleProgressView_cpv_progressTextPrefix)) {
            this.f3898j = obtainStyledAttributes.getString(R.styleable.CircleProgressView_cpv_progressTextPrefix);
        }
        this.f3899k = obtainStyledAttributes.getBoolean(R.styleable.CircleProgressView_cpv_progressTextVisible, this.f3899k);
        this.f3901m = (int) obtainStyledAttributes.getDimension(R.styleable.CircleProgressView_cpv_radius, this.f3901m);
        int i = this.f3901m;
        this.f3908t = new RectF(-i, -i, i, i);
        int i2 = this.f3904p;
        if (i2 == 0) {
            this.f3900l = obtainStyledAttributes.getBoolean(R.styleable.CircleProgressView_cpv_reachCapRound, true);
            this.f3902n = obtainStyledAttributes.getInt(R.styleable.CircleProgressView_cpv_progressStartArc, 0) + 270;
            if (obtainStyledAttributes.hasValue(R.styleable.CircleProgressView_cpv_innerBackgroundColor)) {
                this.f3903o = obtainStyledAttributes.getColor(R.styleable.CircleProgressView_cpv_innerBackgroundColor, Color.argb(0, 0, 0, 0));
                this.f3907s = true;
            }
        } else if (i2 == 1) {
            this.f3890b = 0;
            this.f3891c = 0;
            this.f3910v = 0;
        } else if (i2 == 2) {
            this.f3902n = obtainStyledAttributes.getInt(R.styleable.CircleProgressView_cpv_progressStartArc, 0) + 270;
            this.f3905q = (int) obtainStyledAttributes.getDimension(R.styleable.CircleProgressView_cpv_innerPadding, this.f3905q);
            this.f3906r = obtainStyledAttributes.getColor(R.styleable.CircleProgressView_cpv_outerColor, this.f3892d);
            this.f3910v = (int) obtainStyledAttributes.getDimension(R.styleable.CircleProgressView_cpv_outerSize, this.f3910v);
            this.f3890b = 0;
            this.f3891c = 0;
            if (!obtainStyledAttributes.hasValue(R.styleable.CircleProgressView_cpv_progressNormalColor)) {
                this.f3893e = 0;
            }
            int i3 = (this.f3901m - (this.f3910v / 2)) - this.f3905q;
            float f = -i3;
            float f2 = i3;
            this.f3909u = new RectF(f, f, f2, f2);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public final void m18754a(Canvas canvas) {
        canvas.save();
        canvas.translate(this.f3888B / 2, this.f3889C / 2);
        canvas.drawArc(this.f3908t, 0.0f, 360.0f, false, this.f3887A);
        float progress = ((getProgress() * 1.0f) / getMax()) * 360.0f;
        canvas.drawArc(this.f3909u, this.f3902n, progress, true, this.f3913y);
        if (progress != 360.0f) {
            canvas.drawArc(this.f3909u, progress + this.f3902n, 360.0f - progress, true, this.f3912x);
        }
        canvas.restore();
    }
}

