package com.comod.baselib.view;

//package com.comod.baselib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
//import p067e.p130f.p131a.p134c.OnFinishListener;

/* loaded from: classes.dex */
public class CountDownProgressView extends View {

    /* renamed from: b */
    public RectF f1536b;

    /* renamed from: c */
    public Paint f1537c;

    /* renamed from: d */
    public int f1538d;

    /* renamed from: e */
    public int f1539e;

    /* renamed from: f */
    public float f1540f;

    /* renamed from: g */
    public float f1541g;

    /* renamed from: com.comod.baselib.view.CountDownProgressView$a */
    /* loaded from: classes.dex */
    public class RunnableC0891a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ long f1542b;

        /* renamed from: c */
        public final /* synthetic */ OnFinishListener f1543c;

        /* renamed from: com.comod.baselib.view.CountDownProgressView$a$a */
        /* loaded from: classes.dex */
        public class RunnableC0892a implements Runnable {
            public RunnableC0892a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                RunnableC0891a.this.f1543c.onFinish();
            }
        }

        public RunnableC0891a(long j, OnFinishListener bVar) {
            this.f1542b = j;
            this.f1543c = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i = (int) CountDownProgressView.this.f1540f; i >= 0; i--) {
                try {
                    Thread.sleep((long) (((float) this.f1542b) / CountDownProgressView.this.f1540f));
                    if (i == 0 && this.f1543c != null) {
                        CountDownProgressView.this.post(new RunnableC0892a());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CountDownProgressView.this.f1541g = i;
                CountDownProgressView.this.postInvalidate();
            }
        }
    }

    public CountDownProgressView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            int width = getWidth();
            int height = getHeight();
            if (width != height) {
                width = Math.min(width, height);
                height = width;
            }
            this.f1536b.left = this.f1539e / 2;
            this.f1536b.top = this.f1539e / 2;
            this.f1536b.right = width - (this.f1539e / 2);
            this.f1536b.bottom = height - (this.f1539e / 2);
            canvas.drawColor(0);
            this.f1537c.setColor(Color.parseColor("#80000000"));
            this.f1537c.setStyle(Paint.Style.FILL);
            canvas.drawCircle(width / 2.0f, height / 2.0f, (width - this.f1539e) / 2, this.f1537c);
            this.f1537c.setAntiAlias(true);
            this.f1537c.setColor(this.f1538d == 0 ? -1 : this.f1538d);
            this.f1537c.setStyle(Paint.Style.STROKE);
            this.f1537c.setStrokeWidth(this.f1539e);
            canvas.drawArc(this.f1536b, -90.0f, (-(this.f1541g / this.f1540f)) * 360.0f, false, this.f1537c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPaintColor(String str) {
        this.f1538d = Color.parseColor(str);
    }

    public CountDownProgressView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CountDownProgressView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1539e = 5;
        this.f1540f = 100.0f;
        this.f1541g = 0.0f;
        this.f1536b = new RectF();
        this.f1537c = new Paint();
    }

    /* renamed from: a */
    public void m20147a(long j, OnFinishListener bVar) {
        try {
            this.f1540f = 100.0f;
            new Thread(new RunnableC0891a(j, bVar)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

