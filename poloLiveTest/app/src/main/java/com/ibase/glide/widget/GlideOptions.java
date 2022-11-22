package com.ibase.glide.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
//import android.support.annotation.CheckResult;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.FloatRange;
//import android.support.annotation.IntRange;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;

import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

/* renamed from: e.n.a.d */
/* loaded from: classes.dex */
public final class GlideOptions extends RequestOptions implements Cloneable {
     // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public RequestOptions apply2(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        return (GlideOptions) super.apply(baseRequestOptions);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    public RequestOptions autoClone() {
        return (GlideOptions) super.autoClone();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions centerCrop() {
        return (GlideOptions) super.centerCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions centerInside() {
        return (GlideOptions) super.centerInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions circleCrop() {
        return (GlideOptions) super.circleCrop();
    }

     // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: decode  reason: avoid collision after fix types in other method */
    public RequestOptions decode2(@NonNull Class<?> cls) {
        return (GlideOptions) super.decode(cls);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions disallowHardwareConfig() {
        return (GlideOptions) super.disallowHardwareConfig();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return (GlideOptions) super.diskCacheStrategy(diskCacheStrategy);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions dontAnimate() {
        return (GlideOptions) super.dontAnimate();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions dontTransform() {
        return (GlideOptions) super.dontTransform();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return (GlideOptions) super.downsample(downsampleStrategy);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return (GlideOptions) super.encodeFormat(compressFormat);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return (GlideOptions) super.encodeQuality(i);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions fitCenter() {
        return (GlideOptions) super.fitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions format(@NonNull DecodeFormat decodeFormat) {
        return (GlideOptions) super.format(decodeFormat);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions frame(@IntRange(from = 0) long j) {
        return (GlideOptions) super.frame(j);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    public RequestOptions lock() {
        return (GlideOptions) super.lock();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions onlyRetrieveFromCache(boolean z) {
        return (GlideOptions) super.onlyRetrieveFromCache(z);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalCenterCrop() {
        return (GlideOptions) super.optionalCenterCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalCenterInside() {
        return (GlideOptions) super.optionalCenterInside();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalCircleCrop() {
        return (GlideOptions) super.optionalCircleCrop();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions optionalFitCenter() {
        return (GlideOptions) super.optionalFitCenter();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions priority(@NonNull Priority priority) {
        return (GlideOptions) super.priority(priority);
    }

     // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: set  reason: avoid collision after fix types in other method */
    public <Y> RequestOptions set2(@NonNull Option<Y> option, @NonNull Y y) {
        return (GlideOptions) super.set((Option<Option<Y>>) option, (Option<Y>) y);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions signature(@NonNull Key key) {
        return (GlideOptions) super.signature(key);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (GlideOptions) super.sizeMultiplier(f);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions skipMemoryCache(boolean z) {
        return (GlideOptions) super.skipMemoryCache(z);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions theme(@Nullable Resources.Theme theme) {
        return (GlideOptions) super.theme(theme);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions timeout(@IntRange(from = 0) int i) {
        return (GlideOptions) super.timeout(i);
    }

     // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @Deprecated
    @SafeVarargs
    @CheckResult
    /* renamed from: transforms  reason: avoid collision after fix types in other method */
    public final RequestOptions transforms2(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideOptions) super.transforms(transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions useAnimationPool(boolean z) {
        return (GlideOptions) super.useAnimationPool(z);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions useUnlimitedSourceGeneratorsPool(boolean z) {
        return (GlideOptions) super.useUnlimitedSourceGeneratorsPool(z);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    public RequestOptions clone() {
        return (GlideOptions) super.clone();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions error(@Nullable Drawable drawable) {
        return (GlideOptions) super.error(drawable);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions fallback(@Nullable Drawable drawable) {
        return (GlideOptions) super.fallback(drawable);
    }

     // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: optionalTransform  reason: avoid collision after fix types in other method */
    public RequestOptions optionalTransform2(@NonNull Transformation<Bitmap> transformation) {
        return (GlideOptions) super.optionalTransform(transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions override(int i, int i2) {
        return (GlideOptions) super.override(i, i2);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions placeholder(@Nullable Drawable drawable) {
        return (GlideOptions) super.placeholder(drawable);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions error(@DrawableRes int i) {
        return (GlideOptions) super.error(i);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions fallback(@DrawableRes int i) {
        return (GlideOptions) super.fallback(i);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public <Y> RequestOptions optionalTransform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        return (GlideOptions) super.optionalTransform((Class) cls, (Transformation) transformation);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions override(int i) {
        return (GlideOptions) super.override(i);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public RequestOptions placeholder(@DrawableRes int i) {
        return (GlideOptions) super.placeholder(i);
    }

     // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    /* renamed from: transform  reason: avoid collision after fix types in other method */
    public RequestOptions transform2(@NonNull Transformation<Bitmap> transformation) {
        return (GlideOptions) super.transform(transformation);
    }

     // com.bumptech.glide.request.BaseRequestOptions
    @SafeVarargs
    @CheckResult
    @NonNull
    /* renamed from: transform  reason: avoid collision after fix types in other method */
    public final RequestOptions transform2(@NonNull Transformation<Bitmap>... transformationArr) {
        return (GlideOptions) super.transform(transformationArr);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    @NonNull
    public <Y> RequestOptions transform(@NonNull Class<Y> cls, @NonNull Transformation<Y> transformation) {
        return (GlideOptions) super.transform((Class) cls, (Transformation) transformation);
    }
}
