package com.ibase.glide.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

//import com.alibaba.fastjson.util.AntiCollisionHashMap;
import com.blmvl.blvl.R;
import com.ibase.glide.progress.C4722b;
//import com.ibase.glide.R$color;
//import com.ibase.glide.R$mipmap;

import java.util.List;

//import p067e.p260n.p261a.p263j.C4722b;
//import p067e.p260n.p261a.p264k.GridLayoutHelper;
//import p067e.p260n.p261a.p264k.LayoutHelper;

/* loaded from: classes.dex */
public class NineImageView extends ViewGroup {

    /* renamed from: b */
    public List<ImageData> f3935b;

    /* renamed from: c */
    public int f3936c;

    /* renamed from: d */
    public int f3937d;

    /* renamed from: e */
    public int f3938e;

    /* renamed from: f */
    public int f3939f;

    /* renamed from: g */
    public boolean f3940g;

    /* renamed from: h */
    public boolean f3941h;

    /* renamed from: i */
    public int f3942i;

    /* renamed from: j */
    public final Xfermode f3943j;

    /* renamed from: k */
    public final Paint f3944k;

    /* renamed from: l */
    public final Path f3945l;

    /* renamed from: m */
    public RectF f3946m;

    /* renamed from: n */
    public boolean f3947n;

    /* renamed from: o */
    public int f3948o;

    /* renamed from: p */
    public int f3949p;

    /* renamed from: q */
    public int f3950q;

    /* renamed from: r */
    public int f3951r;

    /* renamed from: s */
    public Rect f3952s;

    /* renamed from: t */
    public int f3953t;

    /* renamed from: u */
    public boolean f3954u;

    /* renamed from: v */
    public boolean f3955v;

    /* renamed from: w */
    public AbstractC1633a f3956w;

    /* renamed from: com.ibase.glide.widget.NineImageView$a */
    /* loaded from: classes.dex */
    public interface AbstractC1633a {
        void onItemClick(int i);
    }

    public NineImageView(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public final void m18729a(Context context) {
        int a = C4722b.m4931a(context, 60.0f);
        this.f3939f = a;
        this.f3938e = a;
        m18727b(3);
        m18726c(5);
        this.f3948o = R.color.nine_image_text_color;
        this.f3949p = 20;
        this.f3950q = R.mipmap.image_loading;
        this.f3951r = R.mipmap.image_load_err;
    }

    /* renamed from: b */
    public NineImageView m18727b(int i) {
        this.f3937d = C4722b.m4931a(getContext(), i);
        return this;
    }

    /* renamed from: c */
    public NineImageView m18726c(int i) {
        this.f3942i = C4722b.m4931a(getContext(), i);
        return this;
    }

    @SuppressLint("ResourceType")
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (this.f3941h) {
            canvas.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.f3944k, Canvas.ALL_SAVE_FLAG);
            super.dispatchDraw(canvas);
            if (this.f3936c == 1) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(getResources().getColor(17170445));
                canvas.drawPath(this.f3945l, paint);
            }
            this.f3944k.setXfermode(this.f3943j);
            canvas.drawPath(this.f3945l, this.f3944k);
            this.f3944k.setXfermode(null);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    public List<ImageData> getData() {
        return this.f3935b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < this.f3936c; i5++) {
            ImageCell imageCell = (ImageCell) getChildAt(i5);
            if (!(imageCell == null || imageCell.getVisibility() == View.GONE)) {
                ImageData imageData = this.f3935b.get(i5);
                int i6 = imageData.startX;
                imageCell.layout(i6, imageData.startY, imageCell.getMeasuredWidth() + i6, imageData.startY + imageCell.getMeasuredHeight());
                if (this.f3940g) {
                    this.f3940g = false;
                    imageCell.setData(imageData);
                }
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.f3936c; i5++) {
            ImageData imageData = this.f3935b.get(i5);
            int i6 = imageData.startX + imageData.width;
            int i7 = imageData.startY + imageData.height;
            if (i6 > i3) {
                i3 = i6;
            }
            if (i7 > i4) {
                i4 = i7;
            }
        }
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        float min = Math.min(((mode == Integer.MIN_VALUE || mode == 1073741824) && i3 > size) ? (size * 1.0f) / i3 : 1.0f, ((mode2 == Integer.MIN_VALUE || mode2 == 1073741824) && i4 > size2) ? (size2 * 1.0f) / i4 : 1.0f);
        if (min < 1.0f) {
            i3 = 0;
            i4 = 0;
            for (int i8 = 0; i8 < this.f3936c; i8++) {
                ImageData imageData2 = this.f3935b.get(i8);
                imageData2.startX = (int) (imageData2.startX * min);
                imageData2.startY = (int) (imageData2.startY * min);
                imageData2.width = (int) (imageData2.width * min);
                imageData2.height = (int) (imageData2.height * min);
                int i9 = imageData2.startX + imageData2.width;
                int i10 = imageData2.startY + imageData2.height;
                if (i9 > i3) {
                    i3 = i9;
                }
                if (i10 > i4) {
                    i4 = i10;
                }
            }
        }
        if (this.f3941h) {
            RectF rectF = this.f3946m;
            rectF.right = i3;
            rectF.bottom = i4;
            this.f3945l.reset();
            Path path = this.f3945l;
            RectF rectF2 = this.f3946m;
            int i11 = this.f3942i;
            path.addRoundRect(rectF2, i11, i11, Path.Direction.CW);
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(i3, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(i4, MeasureSpec.EXACTLY));
        for (int i12 = 0; i12 < this.f3935b.size(); i12++) {
            ImageCell imageCell = (ImageCell) getChildAt(i12);
            if (!(imageCell == null || imageCell.getVisibility() == View.GONE)) {
                ImageData imageData3 = this.f3935b.get(i12);
                imageCell.measure(MeasureSpec.makeMeasureSpec(imageData3.width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(imageData3.height, MeasureSpec.EXACTLY));
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f3954u = false;
            this.f3955v = false;
            this.f3953t = m18730a(x, y);
        } else if (action == 1 && this.f3956w != null && !this.f3954u && this.f3953t == m18730a(x, y) && (i = this.f3953t) >= 0) {
            this.f3955v = true;
            this.f3956w.onItemClick(i);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean performClick() {
        return !this.f3955v && super.performClick();
    }

    @Override // android.view.View
    public boolean performLongClick() {
        this.f3954u = true;
        return super.performLongClick();
    }

    public void setData(List<ImageData> list) {
        setData(list, m18728a(list));
    }

    public void setOnItemClickListener(AbstractC1633a aVar) {
        this.f3956w = aVar;
    }

    public void setText(int i, String str) {
        ImageCell a = m18731a(i);
        if (a != null) {
            a.m18739b(str);
        }
    }

    public NineImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setData(List<ImageData> list, LayoutHelper bVar) {
        this.f3935b = list;
        this.f3940g = true;
        if (bVar == null) {
            bVar = m18728a(list);
        }
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        this.f3936c = C4722b.m4928a(list);
        if (this.f3936c > 9) {
            this.f3936c = 9;
            list.get(8).text = "+" + String.valueOf(C4722b.m4928a(list) - 9);
        }
        if (this.f3936c > 0) {
            int i = 0;
            while (i < this.f3936c) {
                ImageData imageData = list.get(i);
                imageData.from(imageData, bVar, i);
                ImageCell imageCell = (ImageCell) getChildAt(i);
                if (imageCell == null) {
                    imageCell = new ImageCell(getContext()).m18743a(this.f3947n).m18735d(this.f3948o).m18734e(this.f3949p).m18741b(this.f3950q).m18747a(this.f3951r).m18737c(this.f3941h ? this.f3942i : 0);
                    addView(imageCell);
                }
                imageCell.setData(imageData);
                imageCell.setVisibility(View.VISIBLE);
                i++;
            }
            while (i < getChildCount()) {
                getChildAt(i).setVisibility(View.GONE);
                i++;
            }
        }
        requestLayout();
        Log.d("--->", "MultiImageView setData() consume time:" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
    }

    public NineImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3943j = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        this.f3944k = new Paint(1);
        this.f3945l = new Path();
        this.f3946m = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.f3952s = new Rect();
        m18729a(context);
    }

    /* renamed from: a */
    public final GridLayoutHelper m18728a(List<ImageData> list) {
        int size = list != null ? list.size() : 0;
        if (size > 3) {
            size = (int) Math.ceil(Math.sqrt(size));
        }
        if (size > 3) {
            size = 3;
        }
        return new GridLayoutHelper(size, this.f3938e, this.f3939f, this.f3937d);
    }

    /* renamed from: a */
    public ImageCell m18731a(int i) {
        View childAt = getChildAt(i);
        if (childAt == null || childAt.getVisibility() != View.VISIBLE) {
            return null;
        }
        return (ImageCell) childAt;
    }

    /* renamed from: a */
    public final int m18730a(int i, int i2) {
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).getHitRect(this.f3952s);
            if (this.f3952s.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }
}
