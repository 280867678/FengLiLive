package com.slzhibo.library.utils;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;


/* renamed from: e.t.a.i.l */
/* loaded from: classes2.dex */
public class GlideCircleBorderTransform extends BitmapTransformation {

    /* renamed from: a */
    public final String f20259a = GlideCircleBorderTransform.class.getName();

    /* renamed from: b */
    public Paint f20260b = new Paint();

    /* renamed from: c */
    public float f20261c;

    public GlideCircleBorderTransform(float f, int i) {
        this.f20261c = f;
        this.f20260b.setColor(i);
        this.f20260b.setStyle(Paint.Style.STROKE);
        this.f20260b.setAntiAlias(true);
        this.f20260b.setStrokeWidth(f);
        this.f20260b.setDither(true);
    }

//    @Override // p067e.p304t.p305a.p432i.p447p0.BitmapTransformation
//    /* renamed from: a */
//    public Bitmap mo1147a(Context context, BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
//        return m1216a(bitmapPool, bitmap);
//    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return m1216a(pool, toTransform);
    }



    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof GlideCircleBorderTransform;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.f20259a.hashCode();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(this.f20259a.getBytes(Key.CHARSET));
    }

    /* renamed from: a */
    public final Bitmap m1216a(BitmapPool bitmapPool, Bitmap bitmap) {
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - min) / 2, (bitmap.getHeight() - min) / 2, min, min);
        Bitmap bitmap2 = bitmapPool.get(min, min, Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(createBitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        float f = min / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        canvas.drawCircle(f, f, f - (this.f20261c / 2.0f), this.f20260b);
        return bitmap2;
    }


}

