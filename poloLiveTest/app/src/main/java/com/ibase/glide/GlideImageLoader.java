package com.ibase.glide;

//package p067e.p260n.p261a;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

/* renamed from: e.n.a.c */
/* loaded from: classes.dex */
public class GlideImageLoader {

    /* renamed from: a */
    public WeakReference<ImageView> f17159a;

    public GlideImageLoader(ImageView imageView) {
        this.f17159a = new WeakReference<>(imageView);
//        GlideApp.m4954a(m4953a()).asDrawable();
        Glide.with(m4953a()).asDrawable();
    }

    /* renamed from: a */
    public static GlideImageLoader m4952a(ImageView imageView) {
        return new GlideImageLoader(imageView);
    }

    /* renamed from: b */
    public ImageView m4951b() {
        WeakReference<ImageView> weakReference = this.f17159a;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* renamed from: a */
    public Context m4953a() {
        if (m4951b() != null) {
            return m4951b().getContext();
        }
        return null;
    }
}

