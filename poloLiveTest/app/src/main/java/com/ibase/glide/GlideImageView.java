package com.ibase.glide;

//package com.ibase.glide;

import android.annotation.SuppressLint;
import android.content.Context;
//import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;
//import p067e.p260n.p261a.GlideImageLoader;

@SuppressLint({"CheckResult"})
/* loaded from: classes.dex */
public class GlideImageView extends AppCompatImageView {

    /* renamed from: b */
    public boolean f3882b;

    /* renamed from: c */
    public float f3883c;

    /* renamed from: d */
    public float f3884d;

    /* renamed from: e */
    public GlideImageLoader f3885e;

    public GlideImageView(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public final void m18756a() {
        this.f3885e = GlideImageLoader.m4952a(this);
    }

    @Override // android.support.p004v7.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (!this.f3882b) {
            return;
        }
        if (isPressed()) {
            setAlpha(this.f3883c);
        } else if (!isEnabled()) {
            setAlpha(this.f3884d);
        } else {
            setAlpha(1.0f);
        }
    }

    public GlideImageLoader getImageLoader() {
        if (this.f3885e == null) {
            this.f3885e = GlideImageLoader.m4952a(this);
        }
        return this.f3885e;
    }

    public GlideImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GlideImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3882b = false;
        this.f3883c = 0.4f;
        this.f3884d = 0.3f;
        m18756a();
    }
}

