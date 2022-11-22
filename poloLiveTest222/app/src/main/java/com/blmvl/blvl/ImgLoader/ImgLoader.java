package com.blmvl.blvl.ImgLoader;

//package p067e.p103c.p104a.p113k;

import android.content.Context;
import android.graphics.drawable.Drawable;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blmvl.blvl.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.Transition;
import com.comod.baselib.BaseAppContext;
//import p067e.p260n.p261a.GlideApp;
//import p067e.p260n.p261a.GlideRequest;
//import us.ceubf.qeqeff.R;

/* renamed from: e.c.a.k.s */
/* loaded from: classes.dex */
public class ImgLoader {

    /* renamed from: a */
    public static DrawableCrossFadeFactory f13055a = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

    /* compiled from: ImgLoader.java */
    /* renamed from: e.c.a.k.s$a */
    /* loaded from: classes.dex */
    public static class C4246a extends SimpleTarget<Drawable> {

        /* renamed from: b */
        public final /* synthetic */ AbstractC4247b f13056b;

        public C4246a(AbstractC4247b bVar) {
            this.f13056b = bVar;
        }

        /* renamed from: a */
        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
            AbstractC4247b bVar = this.f13056b;
            if (bVar != null) {
                bVar.mo9419a(drawable);
            }
        }
    }

    /* compiled from: ImgLoader.java */
    /* renamed from: e.c.a.k.s$b */
    /* loaded from: classes.dex */
    public interface AbstractC4247b {
        /* renamed from: a */
        void mo9419a(Drawable drawable);
    }

//    /* renamed from: a */
//    public static void m9431a(Context context, String str, ImageView imageView) {
//        GlideApp.m4954a(context).load(str).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade(f13055a)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//    }
//
//    /* renamed from: b */
//    public static void m9425b(Context context, String str, ImageView imageView, int i) {
//        GlideApp.m4954a(context).load(str).error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//    }
//
//    /* renamed from: c */
//    public static void m9423c(Context context, String str, ImageView imageView) {
//        GlideApp.m4954a(context).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//    }
//
//    /* renamed from: c */
//    public static void m9421c(String str, ImageView imageView, int i) {
//        GlideApp.m4954a(imageView.getContext()).load(str).error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//    }
//
//    /* renamed from: a */
    public static void m9430a(Context context, String str, ImageView imageView, int i) {
        Glide.with(context).load(str).error(i).placeholder(i).transition(DrawableTransitionOptions.withCrossFade(f13055a)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }
//
//    /* renamed from: b */
    public static void m9424b(String str, ImageView imageView, int i) {
        Glide.with(imageView.getContext()).load(str).error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }
//
//    /* renamed from: c */
//    public static void m9422c(Context context, String str, ImageView imageView, int i) {
//        if (context != null) {
//            try {
//                GlideApp.m4954a(context).load(str).error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    /* renamed from: a */
//    public static void m9428a(String str, ImageView imageView) {
//        m9421c(str, imageView, (int) R.drawable.fq_ic_placeholder_avatar);
//    }
//
//    /* renamed from: b */
//    public static void m9426b(Context context, String str, ImageView imageView) {
//        m9422c(context, str, imageView, R.drawable.fq_ic_placeholder_avatar);
//    }
//
//    /* renamed from: a */
//    public static void m9429a(String str, int i, AbstractC4247b bVar) {
//        GlideApp.m4954a(BaseAppContext.m20167a()).load(str).error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into((GlideRequest<Drawable>) new C4246a(bVar));
//    }
//
//    /* renamed from: a */
//    public static void m9427a(String str, ImageView imageView, int i) {
//        GlideApp.m4954a(imageView.getContext()).load(str).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade(f13055a)).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//    }
}

