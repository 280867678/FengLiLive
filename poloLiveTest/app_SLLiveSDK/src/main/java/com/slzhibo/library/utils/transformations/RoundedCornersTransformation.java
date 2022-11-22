package com.slzhibo.library.utils.transformations;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;


import androidx.annotation.NonNull;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;
//import p067e.p304t.p305a.p432i.p447p0.BitmapTransformation;
/* loaded from: classes2.dex */
public class RoundedCornersTransformation extends BitmapTransformation {

    /* renamed from: a */
    public int f9785a;

    /* renamed from: b */
    public int f9786b;

    /* renamed from: c */
    public int f9787c;

    /* renamed from: d */
    public CornerType f9788d;



    /* loaded from: classes2.dex */
    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    /* renamed from: com.slzhibo.library.utils.transformations.RoundedCornersTransformation$a */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C3240a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f9789a = new int[CornerType.values().length];

        static {
            try {
                f9789a[CornerType.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9789a[CornerType.TOP_LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9789a[CornerType.TOP_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9789a[CornerType.BOTTOM_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9789a[CornerType.BOTTOM_RIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9789a[CornerType.TOP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9789a[CornerType.BOTTOM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9789a[CornerType.LEFT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9789a[CornerType.RIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9789a[CornerType.OTHER_TOP_LEFT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9789a[CornerType.OTHER_TOP_RIGHT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9789a[CornerType.OTHER_BOTTOM_LEFT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9789a[CornerType.OTHER_BOTTOM_RIGHT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9789a[CornerType.DIAGONAL_FROM_TOP_LEFT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9789a[CornerType.DIAGONAL_FROM_TOP_RIGHT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public RoundedCornersTransformation(int i, int i2) {
        this(i, i2, CornerType.ALL);
    }

//    @Override // p067e.p304t.p305a.p432i.p447p0.BitmapTransformation
//    /* renamed from: a */
//    public Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        Bitmap bitmap2 = bitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
//        bitmap2.setHasAlpha(true);
//        Canvas canvas = new Canvas(bitmap2);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
//        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
//        m13076l(canvas, paint, width, height);
//        return bitmap2;
//    }
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        Bitmap bitmap2 = pool.get(width, height, Bitmap.Config.ARGB_8888);
        bitmap2.setHasAlpha(true);
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(toTransform, tileMode, tileMode));
        m13076l(canvas, paint, width, height);
        return bitmap2;
    }

    /* renamed from: b */
    public final void m13086b(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9786b;
        RectF rectF = new RectF(f - i, f2 - i, f, f2);
        int i2 = this.f9785a;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.f9787c;
        canvas.drawRect(new RectF(i3, i3, f - this.f9785a, f2), paint);
        int i4 = this.f9785a;
        canvas.drawRect(new RectF(f - i4, this.f9787c, f, f2 - i4), paint);
    }

    /* renamed from: c */
    public final void m13085c(Canvas canvas, Paint paint, float f, float f2) {
        RectF rectF = new RectF(this.f9787c, f2 - this.f9786b, f, f2);
        int i = this.f9785a;
        canvas.drawRoundRect(rectF, i, i, paint);
        int i2 = this.f9787c;
        canvas.drawRect(new RectF(i2, i2, f, f2 - this.f9785a), paint);
    }

    /* renamed from: d */
    public final void m13084d(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        int i2 = this.f9786b;
        RectF rectF = new RectF(i, i, i + i2, i + i2);
        int i3 = this.f9785a;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.f9786b;
        RectF rectF2 = new RectF(f - i4, f2 - i4, f, f2);
        int i5 = this.f9785a;
        canvas.drawRoundRect(rectF2, i5, i5, paint);
        int i6 = this.f9787c;
        canvas.drawRect(new RectF(i6, i6 + this.f9785a, f - this.f9786b, f2), paint);
        int i7 = this.f9787c;
        canvas.drawRect(new RectF(this.f9786b + i7, i7, f, f2 - this.f9785a), paint);
    }

    /* renamed from: e */
    public final void m13083e(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9786b;
        int i2 = this.f9787c;
        RectF rectF = new RectF(f - i, i2, f, i2 + i);
        int i3 = this.f9785a;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.f9787c;
        int i5 = this.f9786b;
        RectF rectF2 = new RectF(i4, f2 - i5, i4 + i5, f2);
        int i6 = this.f9785a;
        canvas.drawRoundRect(rectF2, i6, i6, paint);
        int i7 = this.f9787c;
        int i8 = this.f9785a;
        canvas.drawRect(new RectF(i7, i7, f - i8, f2 - i8), paint);
        int i9 = this.f9787c;
        int i10 = this.f9785a;
        canvas.drawRect(new RectF(i9 + i10, i9 + i10, f, f2), paint);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof RoundedCornersTransformation) {
            RoundedCornersTransformation roundedCornersTransformation = (RoundedCornersTransformation) obj;
            if (roundedCornersTransformation.f9785a == this.f9785a && roundedCornersTransformation.f9786b == this.f9786b && roundedCornersTransformation.f9787c == this.f9787c && roundedCornersTransformation.f9788d == this.f9788d) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public final void m13082f(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        RectF rectF = new RectF(i, i, i + this.f9786b, f2);
        int i2 = this.f9785a;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.f9787c;
        canvas.drawRect(new RectF(this.f9785a + i3, i3, f, f2), paint);
    }

    /* renamed from: g */
    public final void m13081g(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        RectF rectF = new RectF(i, i, f, i + this.f9786b);
        int i2 = this.f9785a;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        RectF rectF2 = new RectF(f - this.f9786b, this.f9787c, f, f2);
        int i3 = this.f9785a;
        canvas.drawRoundRect(rectF2, i3, i3, paint);
        int i4 = this.f9787c;
        int i5 = this.f9785a;
        canvas.drawRect(new RectF(i4, i4 + i5, f - i5, f2), paint);
    }

    /* renamed from: h */
    public final void m13080h(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        RectF rectF = new RectF(i, i, f, i + this.f9786b);
        int i2 = this.f9785a;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.f9787c;
        RectF rectF2 = new RectF(i3, i3, i3 + this.f9786b, f2);
        int i4 = this.f9785a;
        canvas.drawRoundRect(rectF2, i4, i4, paint);
        int i5 = this.f9787c;
        int i6 = this.f9785a;
        canvas.drawRect(new RectF(i5 + i6, i5 + i6, f, f2), paint);
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return "com.slzhibo.library.utils.transformations.RoundedCornersTransformation.1".hashCode() + (this.f9785a * 10000) + (this.f9786b * 1000) + (this.f9787c * 100) + (this.f9788d.ordinal() * 10);
    }

    /* renamed from: i */
    public final void m13079i(Canvas canvas, Paint paint, float f, float f2) {
        RectF rectF = new RectF(this.f9787c, f2 - this.f9786b, f, f2);
        int i = this.f9785a;
        canvas.drawRoundRect(rectF, i, i, paint);
        RectF rectF2 = new RectF(f - this.f9786b, this.f9787c, f, f2);
        int i2 = this.f9785a;
        canvas.drawRoundRect(rectF2, i2, i2, paint);
        int i3 = this.f9787c;
        int i4 = this.f9785a;
        canvas.drawRect(new RectF(i3, i3, f - i4, f2 - i4), paint);
    }

    /* renamed from: j */
    public final void m13078j(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        RectF rectF = new RectF(i, i, i + this.f9786b, f2);
        int i2 = this.f9785a;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        RectF rectF2 = new RectF(this.f9787c, f2 - this.f9786b, f, f2);
        int i3 = this.f9785a;
        canvas.drawRoundRect(rectF2, i3, i3, paint);
        int i4 = this.f9787c;
        int i5 = this.f9785a;
        canvas.drawRect(new RectF(i4 + i5, i4, f, f2 - i5), paint);
    }

    /* renamed from: k */
    public final void m13077k(Canvas canvas, Paint paint, float f, float f2) {
        RectF rectF = new RectF(f - this.f9786b, this.f9787c, f, f2);
        int i = this.f9785a;
        canvas.drawRoundRect(rectF, i, i, paint);
        int i2 = this.f9787c;
        canvas.drawRect(new RectF(i2, i2, f - this.f9785a, f2), paint);
    }

    /* renamed from: l */
    public final void m13076l(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        float f3 = f - i;
        float f4 = f2 - i;
        switch (C3240a.f9789a[this.f9788d.ordinal()]) {
            case 1:
                int i2 = this.f9787c;
                RectF rectF = new RectF(i2, i2, f3, f4);
                int i3 = this.f9785a;
                canvas.drawRoundRect(rectF, i3, i3, paint);
                return;
            case 2:
                m13075m(canvas, paint, f3, f4);
                return;
            case 3:
                m13074n(canvas, paint, f3, f4);
                return;
            case 4:
                m13087a(canvas, paint, f3, f4);
                return;
            case 5:
                m13086b(canvas, paint, f3, f4);
                return;
            case 6:
                m13073o(canvas, paint, f3, f4);
                return;
            case 7:
                m13085c(canvas, paint, f3, f4);
                return;
            case 8:
                m13082f(canvas, paint, f3, f4);
                return;
            case 9:
                m13077k(canvas, paint, f3, f4);
                return;
            case 10:
                m13079i(canvas, paint, f3, f4);
                return;
            case 11:
                m13078j(canvas, paint, f3, f4);
                return;
            case 12:
                m13081g(canvas, paint, f3, f4);
                return;
            case 13:
                m13080h(canvas, paint, f3, f4);
                return;
            case 14:
                m13084d(canvas, paint, f3, f4);
                return;
            case 15:
                m13083e(canvas, paint, f3, f4);
                return;
            default:
                int i4 = this.f9787c;
                RectF rectF2 = new RectF(i4, i4, f3, f4);
                int i5 = this.f9785a;
                canvas.drawRoundRect(rectF2, i5, i5, paint);
                return;
        }
    }

    /* renamed from: m */
    public final void m13075m(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        int i2 = this.f9786b;
        RectF rectF = new RectF(i, i, i + i2, i + i2);
        int i3 = this.f9785a;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.f9787c;
        int i5 = this.f9785a;
        canvas.drawRect(new RectF(i4, i4 + i5, i4 + i5, f2), paint);
        int i6 = this.f9787c;
        canvas.drawRect(new RectF(this.f9785a + i6, i6, f, f2), paint);
    }

    /* renamed from: n */
    public final void m13074n(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9786b;
        int i2 = this.f9787c;
        RectF rectF = new RectF(f - i, i2, f, i2 + i);
        int i3 = this.f9785a;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.f9787c;
        canvas.drawRect(new RectF(i4, i4, f - this.f9785a, f2), paint);
        int i5 = this.f9785a;
        canvas.drawRect(new RectF(f - i5, this.f9787c + i5, f, f2), paint);
    }

    /* renamed from: o */
    public final void m13073o(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        RectF rectF = new RectF(i, i, f, i + this.f9786b);
        int i2 = this.f9785a;
        canvas.drawRoundRect(rectF, i2, i2, paint);
        int i3 = this.f9787c;
        canvas.drawRect(new RectF(i3, i3 + this.f9785a, f, f2), paint);
    }

    public String toString() {
        return "RoundedTransformation(radius=" + this.f9785a + ", margin=" + this.f9787c + ", diameter=" + this.f9786b + ", cornerType=" + this.f9788d.name() + ")";
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(("com.slzhibo.library.utils.transformations.RoundedCornersTransformation.1" + this.f9785a + this.f9786b + this.f9787c + this.f9788d).getBytes(Key.CHARSET));
    }

    public RoundedCornersTransformation(int i, int i2, CornerType cornerType) {
        this.f9785a = i;
        this.f9786b = this.f9785a * 2;
        this.f9787c = i2;
        this.f9788d = cornerType;
    }

    /* renamed from: a */
    public final void m13087a(Canvas canvas, Paint paint, float f, float f2) {
        int i = this.f9787c;
        int i2 = this.f9786b;
        RectF rectF = new RectF(i, f2 - i2, i + i2, f2);
        int i3 = this.f9785a;
        canvas.drawRoundRect(rectF, i3, i3, paint);
        int i4 = this.f9787c;
        canvas.drawRect(new RectF(i4, i4, i4 + this.f9786b, f2 - this.f9785a), paint);
        int i5 = this.f9787c;
        canvas.drawRect(new RectF(this.f9785a + i5, i5, f, f2), paint);
    }
}

