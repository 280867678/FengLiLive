package com.ibase.glide.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
//import android.support.annotation.ColorRes;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.p004v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.blmvl.blvl.R;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
//import com.ibase.glide.GlideApp;
//import com.ibase.glide.R$color;
//import com.ibase.glide.R$drawable;

//import com.ibase.glide.GlideApp;
//import com.ibase.glide.GlideRequest;
//import com.ibase.glide.progress.C4722b;
//import com.ibase.glide.widget.NineImageView;p263j.C4722b;

/* loaded from: classes.dex */
public class ImageCell extends AppCompatImageView {

    /* renamed from: p */
    public static Drawable f3916p;

    /* renamed from: q */
    public static Drawable f3917q;

    /* renamed from: b */
    public ImageData f3918b;

    /* renamed from: c */
    public int f3919c;

    /* renamed from: d */
    public boolean f3920d;

    /* renamed from: e */
    public boolean f3921e;

    /* renamed from: f */
    public int f3922f;

    /* renamed from: g */
    public int f3923g;

    /* renamed from: h */
    public boolean f3924h;

    /* renamed from: i */
    public Drawable f3925i;

    /* renamed from: j */
    public int f3926j;

    /* renamed from: k */
    public int f3927k;

    /* renamed from: l */
    public Rect f3928l;

    /* renamed from: m */
    public int f3929m;

    /* renamed from: n */
    public Paint f3930n;

    /* renamed from: o */
    public Paint.FontMetricsInt f3931o;

    /* renamed from: com.ibase.glide.widget.ImageCell$a */
    /* loaded from: classes.dex */
    public class C1631a extends C1632b {

        /* renamed from: c */
        public final /* synthetic */ String f3932c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1631a(ImageView imageView, String str) {
            super(imageView);
            this.f3932c = str;
        }

        @Override // com.ibase.glide.widget.ImageCell.C1632b
        /* renamed from: a */
        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
            if (drawable instanceof GifDrawable) {
                ImageCell.this.f3920d = true;
                if (!ImageCell.this.f3921e) {
                    drawable = new BitmapDrawable(((GifDrawable) drawable).getFirstFrame());
                }
            } else {
                ImageCell.this.f3920d = false;
            }
            ImageCell.this.m18742b();
            super.mo18732a(drawable, transition);
        }

        @Override // com.ibase.glide.widget.ImageCell.C1632b, com.bumptech.glide.request.target.ImageViewTarget, com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            ImageCell.this.f3920d = C4722b.m4924b(this.f3932c);
            ImageCell.this.m18742b();
            super.onLoadFailed(drawable);
        }
    }

    public ImageCell(Context context) {
        this(context, null);
    }

    /* renamed from: d */
    public ImageCell m18735d(@ColorRes int i) {
        this.f3930n.setColor(getResources().getColor(i));
        return this;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.f3925i;
        if (drawable != null) {
            this.f3924h = true;
            drawable.setBounds(this.f3928l);
            this.f3925i.draw(canvas);
        }
        if (!TextUtils.isEmpty(this.f3918b.text)) {
            canvas.drawColor(getResources().getColor(R.color.nine_image_text_background_color));
            Paint.FontMetricsInt fontMetricsInt = this.f3931o;
            int i = fontMetricsInt.bottom;
            float height = ((getHeight() / 2.0f) + ((i - fontMetricsInt.top) / 2.0f)) - i;
            canvas.drawText(this.f3918b.text, getWidth() / 2.0f, height, this.f3930n);
        }
    }

    /* renamed from: e */
    public ImageCell m18734e(int i) {
        this.f3930n.setTextSize(C4722b.m4931a(getContext(), i));
        this.f3931o = this.f3930n.getFontMetricsInt();
        return this;
    }

    public Drawable getGifDrawable() {
        if (f3916p == null) {
            f3916p = C4722b.m4925b(getContext().getApplicationContext(), 24, 14, 2, "GIF", 11, R.color.nine_image_text_background_color);
        }
        return f3916p;
    }

    public Drawable getLongDrawable() {
        if (f3917q == null) {
            f3917q = C4722b.m4925b(getContext().getApplicationContext(), 25, 14, 2, "长图", 10, R.color.nine_image_text_background_color);
        }
        return f3917q;
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f3925i != null) {
            Rect rect = this.f3928l;
            int i5 = i3 - i;
            int i6 = this.f3929m;
            int i7 = i4 - i2;
            rect.set((i5 - i6) - this.f3926j, (i7 - this.f3927k) - i6, i5 - i6, i7 - i6);
        }
    }

    public void setData(ImageData imageData) {
        this.f3918b = imageData;
        if (imageData != null) {
            m18744a(imageData.url);
        }
    }

    public ImageCell(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: b */
    public ImageCell m18741b(@DrawableRes int i) {
        this.f3922f = i;
        return this;
    }

    /* renamed from: c */
    public ImageCell m18737c(int i) {
        this.f3919c = i;
        return this;
    }

    public ImageCell(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3930n = new Paint(1);
        m18748a();
    }

    /* renamed from: a */
    public final void m18748a() {
        this.f3928l = new Rect();
        this.f3929m = C4722b.m4931a(getContext(), 3.0f);
        this.f3930n.setTextAlign(Paint.Align.CENTER);
    }

    /* renamed from: b */
    public final void m18742b() {
        if (this.f3920d && !this.f3921e) {
            this.f3925i = getGifDrawable();
        } else if (m18738c()) {
            this.f3925i = getLongDrawable();
        } else {
            this.f3925i = null;
        }
        if (this.f3925i != null) {
            this.f3926j = C4722b.m4931a(getContext(), this.f3925i.getIntrinsicWidth());
            this.f3927k = C4722b.m4931a(getContext(), this.f3925i.getIntrinsicHeight());
            if (!this.f3924h) {
                postInvalidate();
            }
        }
    }

    /* renamed from: c */
    public boolean m18738c() {
        ImageData imageData = this.f3918b;
        int i = imageData != null ? imageData.realWidth : 0;
        ImageData imageData2 = this.f3918b;
        int i2 = imageData2 != null ? imageData2.realHeight : 0;
        return i != 0 && i2 != 0 && i < i2 && i2 / i >= 4;
    }

    /* renamed from: a */
    public ImageCell m18743a(boolean z) {
        this.f3921e = z;
        return this;
    }

    /* renamed from: com.ibase.glide.widget.ImageCell$b */
    /* loaded from: classes.dex */
    public class C1632b extends DrawableImageViewTarget {
        public C1632b(ImageView imageView) {
            super(imageView);
        }

        /* renamed from: a */
        public final void m18733a() {
            if (ImageCell.this.f3919c == 0) {
                getView().setBackgroundResource(R.drawable.drawable_image_bg_0dp);
            } else if (ImageCell.this.f3919c == 5) {
                getView().setBackgroundResource(R.drawable.drawable_image_bg_5dp);
            } else {
                GradientDrawable gradientDrawable = (GradientDrawable) ImageCell.this.getResources().getDrawable(R.drawable.drawable_image_bg_0dp);
                gradientDrawable.setCornerRadius(ImageCell.this.f3919c);
                getView().setBackgroundDrawable(gradientDrawable);
            }
        }

        @Override // com.bumptech.glide.request.target.ImageViewTarget, com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            m18733a();
            getView().setScaleType(ImageView.ScaleType.FIT_CENTER);
            super.onLoadFailed(drawable);
        }

        @Override // com.bumptech.glide.request.target.ImageViewTarget, com.bumptech.glide.request.target.ViewTarget, com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadStarted(Drawable drawable) {
            m18733a();
            getView().setScaleType(ImageView.ScaleType.FIT_CENTER);
            super.onLoadStarted(drawable);
        }

        /* renamed from: a */
        public void mo18732a(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
            getView().setBackgroundResource(0);
            getView().setScaleType(ImageView.ScaleType.CENTER_CROP);
            super.onResourceReady(drawable, transition);
        }
    }

    /* renamed from: a */
    public ImageCell m18747a(@DrawableRes int i) {
        this.f3923g = i;
        return this;
    }

    /* renamed from: a */
    public void m18744a(String str) {
        GlideApp.m4954a(getContext()).load(str).placeholder(this.f3922f).error(this.f3923g).fitCenter().transition((TransitionOptions<?, ? super Drawable>) new DrawableTransitionOptions().dontTransition()).thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.DATA).into( new C1631a(this, str));
    }

    /* renamed from: b */
    public ImageCell m18739b(String str) {
        ImageData imageData = this.f3918b;
        if (imageData != null) {
            imageData.text = str;
            postInvalidate();
        }
        return this;
    }
}
