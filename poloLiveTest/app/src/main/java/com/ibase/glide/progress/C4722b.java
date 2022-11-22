package com.ibase.glide.progress;

//package p067e.p260n.p261a.p263j;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
//import android.support.annotation.ColorRes;
import android.text.TextUtils;

import androidx.annotation.ColorRes;

import java.util.Collection;

/* compiled from: Utils.java */
/* renamed from: e.n.a.j.b */
/* loaded from: classes.dex */
public class C4722b {
    /* renamed from: a */
    public static float m4932a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /* renamed from: b */
    public static float m4927b(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    /* renamed from: a */
    public static int m4931a(Context context, float f) {
        return (int) ((m4932a(context) * f) + 0.5f);
    }

    /* renamed from: b */
    public static int m4926b(Context context, float f) {
        return (int) ((m4927b(context) * f) + 0.5f);
    }

    /* renamed from: a */
    public static String m4929a(String str) {
        int lastIndexOf;
        int i;
        if (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(46)) <= 0 || (i = lastIndexOf + 1) >= str.length()) {
            return "";
        }
        String substring = str.substring(i);
        return !TextUtils.isEmpty(substring) ? substring.toLowerCase() : "";
    }

    /* renamed from: b */
    public static boolean m4924b(String str) {
        return "gif".equals(m4929a(str));
    }

    /* renamed from: b */
    public static Drawable m4925b(Context context, int i, int i2, int i3, String str, int i4, @ColorRes int i5) {
        return new BitmapDrawable(m4930a(context, i, i2, i3, str, i4, i5));
    }

    /* renamed from: a */
    public static Bitmap m4930a(Context context, int i, int i2, int i3, String str, int i4, @ColorRes int i5) {
        int a = m4931a(context, i3);
        Bitmap createBitmap = Bitmap.createBitmap(m4931a(context, i), m4931a(context, i2), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        Paint paint = new Paint(1);
        paint.setColor(context.getResources().getColor(i5));
        float f = a;
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, rectF.width(), rectF.height()), f, f, paint);
        paint.setColor(-1);
        paint.setTextSize(m4931a(context, i4));
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        canvas.drawText(str, rectF.centerX(), (((rectF.bottom + rectF.top) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2.0f, paint);
        return createBitmap;
    }

    /* renamed from: a */
    public static int m4928a(Collection<?> collection) {
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }
}

