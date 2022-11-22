package com.slzhibo.library.utils;



import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
//import com.example.boluouitest2zhibo.R;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.slzhibo.library.R;
//import com.jess.arms.http.OkHttpStreamFetcher;
//import com.slzhibo.library.R;
//import com.slzhibo.library.ui.interfaces.OnGlideDownloadCallback;
//import com.slzhibo.library.ui.view.widget.svga.SVGADynamicEntity;
//import com.jess.arms.http.imageloader.glide.BlurTransformation;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import com.slzhibo.library.utils.transformations.RoundedCornersTransformation;
import com.slzhibo.library.utils.transformations.BlurTransformation;
//import com.squareup.picasso.Picasso;
//import com.slzhibo.library.utils.transformations.GrayscaleTransformation;
//import com.slzhibo.library.utils.transformations.RoundedCornersTransformation;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* loaded from: classes12.dex */
public class GlideUtils {
    private static final int BLUR_RADIUS = 25;
    private static final int BLUR_SAMPLING = 6;
    private static Map<String, String> cacheMap = new HashMap();

    private GlideUtils() {
    }

//    public static void loadAvatar(Context context, ImageView imageView, @DrawableRes int i) {
//        if (isValidContextForGlide(context)) {
//            Glide.with(context).load(formatDrawableByResId(context, i)).apply((BaseRequestOptions<?>) getCircleImageOptions()).into(imageView);
//        }
//    }
//
//    public static void loadAvatar(Fragment fragment, ImageView imageView, String str) {
//        loadAvatar(fragment, imageView, str, R.drawable.fq_ic_placeholder_avatar);
//    }

    public static void loadAvatar(Context context, ImageView imageView, String str) {
        loadAvatar(context, imageView, str, R.drawable.fq_ic_placeholder_avatar);
    }

//    public static void loadAvatar(Fragment fragment, ImageView imageView, String str, @DrawableRes int i) {
//        loadAvatar(fragment.getContext(), imageView, str, i);
//    }

    public static void loadAvatar(final Context context, final ImageView imageView, String str, @DrawableRes final int i) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i);
                return;
            }
            imageView.setImageResource(i);
            final String formatDownUrl = formatDownUrl(str);
            Log.e("GlideUtils:::","loadAvatar::107");
            if (!isEncryptionAvatarUrl(formatDownUrl)) {
                loadTargetToImageByCircle(context, imageView, formatDownUrl, i);
            } else if (isLocalCachePath(formatDownUrl)) {
                loadTargetToImageByCircle(context, imageView, cacheMapGet(formatDownUrl), i);
            } else {
                try {
                    Glide.with(context).asFile().load(formatDownUrl).into( new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.1


//                        @Override // com.bumptech.glide.request.target.Target
//                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                            onResourceReady((File) obj, (Transition<? super File>) transition);
//                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadStarted(@Nullable Drawable drawable) {
                            super.onLoadStarted(drawable);
                            imageView.setImageResource(i);
                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                            imageView.setImageResource(i);
                        }

                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                            GlideUtils.loadAvatarByObservable(context, imageView, file, i, formatDownUrl);
                        }
                    });
                } catch (Exception unused) {
                    imageView.setImageResource(i);
                } catch (OutOfMemoryError unused2) {
                    imageView.setImageResource(i);
                }
            }
        }
    }

//    public static void loadAvatar(final Context context, final ImageView imageView, String str, final int i, final int i2) {
//        if (isValidContextForGlide(context)) {
//            if (TextUtils.isEmpty(str)) {
//                imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
//                return;
//            }
//            final String formatDownUrl = formatDownUrl(str);
//            if (!isEncryptionAvatarUrl(formatDownUrl)) {
//                loadTargetToImageByCircle(context, imageView, formatDownUrl, i, i2, R.drawable.fq_ic_placeholder_avatar_white);
//            } else if (isLocalCachePath(formatDownUrl)) {
//                loadTargetToImageByCircle(context, imageView, cacheMapGet(formatDownUrl), i, i2, R.drawable.fq_ic_placeholder_avatar_white);
//            } else {
//                try {
//                    Glide.with(context).asFile().load(formatDownUrl).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.2
//                        @Override // com.bumptech.glide.request.target.Target
//                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                            onResourceReady((File) obj, (Transition<? super File>) transition);
//                        }
//
//                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                        public void onLoadStarted(@Nullable Drawable drawable) {
//                            super.onLoadStarted(drawable);
//                            imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
//                        }
//
//                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                        public void onLoadFailed(@Nullable Drawable drawable) {
//                            super.onLoadFailed(drawable);
//                            imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
//                        }
//
//                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
//                            GlideUtils.loadAvatarByObservable(context, imageView, file, R.drawable.fq_ic_placeholder_avatar_white, i, i2, formatDownUrl);
//                        }
//                    });
//                } catch (Exception unused) {
//                    imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
//                } catch (OutOfMemoryError unused2) {
//                    imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
//                }
//            }
//        }
//    }

    public static void loadLivingGif(Context context, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).asGif().load(formatDrawableByResId(context, R.drawable.fq_ic_living_icon_circle_gif_2)).placeholder(R.drawable.fq_ic_living_icon_circle).into(imageView);
        }
    }

//    public static void loadGifForRes(Context context, ImageView imageView, @DrawableRes int i) {
//        if (isValidContextForGlide(context)) {
//            Glide.with(context).asGif().load(formatDrawableByResId(context, i)).into(imageView);
//        }
//    }

//    public static void loadLocalImage(Context context, ImageView imageView, Object obj, int i, @DrawableRes int i2) {
//        Glide.with(context).load(obj).apply((BaseRequestOptions<?>) getBaseRequestOptions().format(DecodeFormat.PREFER_RGB_565).override(imageView.getWidth()).transform(new CenterCrop(), new RoundedCornersTransformation(ConvertUtils.dp2px(i), 0)).placeholder(i2)).into(imageView);
//    }

//    public static void loadLocalImage(Context context, ImageView imageView, Object obj) {
//        Glide.with(context).load(obj).apply((BaseRequestOptions<?>) getBaseRequestOptions().format(DecodeFormat.PREFER_RGB_565).override(imageView.getWidth()).transform(new CenterCrop())).into(imageView);
//    }

    public static void loadImage(Context context, ImageView imageView, @DrawableRes int i) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(formatDrawableByResId(context, i)).centerCrop().into(imageView);
        }
    }

//    public static void loadImage(Context context, ImageView imageView, String str, @DrawableRes int i) {
//        loadImage(context, imageView, str, i, false);
//    }

//    public static void loadImage(final Fragment fragment, final ImageView imageView, String str, @DrawableRes final int i) {
//        if (isValidContextForGlide(fragment)) {
//            if (TextUtils.isEmpty(str)) {
//                imageView.setImageResource(i);
//                return;
//            }
//            final String formatDownUrl = formatDownUrl(str);
//            Log.e("GlideUtils:::228:",formatDownUrl+"\n"+str);
////            if (!isEncryptionAvatarUrl(formatDownUrl)) {
////                loadTargetToImage(fragment, imageView, formatDownUrl, i);
////            } else if (isLocalCachePath(formatDownUrl)) {
////                loadTargetToImage(fragment, imageView, cacheMapGet(formatDownUrl), i);
////            } else {
////                try {
//////                    Glide.with(fragment).asFile().load(formatDownUrl).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.3
//////                        @Override // com.bumptech.glide.request.target.Target
//////                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//////                            onResourceReady((File) obj, (Transition<? super File>) transition);
//////                        }
//////
//////                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//////                        public void onLoadStarted(@Nullable Drawable drawable) {
//////                            super.onLoadStarted(drawable);
//////                            imageView.setImageResource(i);
//////                        }
//////
//////                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//////                        public void onLoadFailed(@Nullable Drawable drawable) {
//////                            super.onLoadFailed(drawable);
//////                            imageView.setImageResource(i);
//////                        }
//////
//////                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
//////                            GlideUtils.loadImageByObservable(fragment, imageView, file, i, formatDownUrl);
//////                        }
//////                    });
////                } catch (Exception unused) {
////                    imageView.setImageResource(i);
////                } catch (OutOfMemoryError unused2) {
////                    imageView.setImageResource(i);
////                }
////            }
//        }
//    }

//    public static void loadImage(final Context context, final ImageView imageView, String str, @DrawableRes final int i, final boolean z) {
//        if (isValidContextForGlide(context)) {
//            if (TextUtils.isEmpty(str)) {
//                imageView.setImageResource(i);
//                return;
//            }
//            final String formatDownUrl = formatDownUrl(str);
//            if (!z && !isEncryptionAvatarUrl(formatDownUrl)) {
//                loadTargetToImage(context, imageView, formatDownUrl, i);
//            } else if (isLocalCachePath(formatDownUrl)) {
//                loadTargetToImage(context, imageView, cacheMapGet(formatDownUrl), i);
//            } else {
//                try {
//                    Glide.with(context).asFile().load(formatDownUrl).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.4
//                        @Override // com.bumptech.glide.request.target.Target
//                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                            onResourceReady((File) obj, (Transition<? super File>) transition);
//                        }
//
//                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                        public void onLoadStarted(@Nullable Drawable drawable) {
//                            super.onLoadStarted(drawable);
//                            imageView.setImageResource(i);
//                        }
//
//                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
//                            GlideUtils.loadImgByObservable(context, imageView, file, i, formatDownUrl, z);
//                        }
//
//                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                        public void onLoadFailed(@Nullable Drawable drawable) {
//                            super.onLoadFailed(drawable);
//                            imageView.setImageResource(i);
//                        }
//                    });
//                } catch (Exception unused) {
//                    imageView.setImageResource(i);
//                } catch (OutOfMemoryError unused2) {
//                    imageView.setImageResource(i);
//                }
//            }
//        }
//    }

//    public static void loadImageByGray(final Context context, final ImageView imageView, String str, @DrawableRes final int i) {
//        if (isValidContextForGlide(context)) {
//            if (TextUtils.isEmpty(str)) {
//                imageView.setImageResource(i);
//                return;
//            }
//            final String formatDownUrl = formatDownUrl(str);
//            if (!isEncryptionAvatarUrl(formatDownUrl)) {
//                loadTargetToImageByGray(context, imageView, formatDownUrl, i);
//            } else if (isLocalCachePath(formatDownUrl)) {
//                loadTargetToImageByGray(context, imageView, cacheMapGet(formatDownUrl), i);
//            } else {
//                try {
//                    Glide.with(context).asFile().load(formatDownUrl).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.5
//                        @Override // com.bumptech.glide.request.target.Target
//                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                            onResourceReady((File) obj, (Transition<? super File>) transition);
//                        }
//
//                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                        public void onLoadStarted(@Nullable Drawable drawable) {
//                            super.onLoadStarted(drawable);
//                            imageView.setImageResource(i);
//                        }
//
//                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
//                            GlideUtils.loadGrayImgByObservable(context, imageView, file, i, formatDownUrl);
//                        }
//
//                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                        public void onLoadFailed(@Nullable Drawable drawable) {
//                            super.onLoadFailed(drawable);
//                            imageView.setImageResource(i);
//                        }
//                    });
//                } catch (Exception unused) {
//                    imageView.setImageResource(i);
//                } catch (OutOfMemoryError unused2) {
//                    imageView.setImageResource(i);
//                }
//            }
//        }
//    }

    public static void loadImageNormal(Context context, ImageView imageView, String str) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).asBitmap().load(formatDownUrl(str)).apply(getBaseRequestOptions()).into(imageView);
//
        }
    }

    public static void loadImage(Context context, ImageView imageView, String str) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).asBitmap().load(formatDownUrl(str)).apply((BaseRequestOptions<?>) getLoadImageOptions()).into(imageView);
        }
    }

    public static void loadImage(Context context, ImageView imageView, String str, int i, int i2) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).asBitmap().load(formatDownUrl(str)).apply(getLoadImageOptions(i, i2)).into(imageView);
        }
    }

//    public static void loadImage(Context context, ImageView imageView, Uri uri, Drawable drawable, int i, int i2) {
//        if (isValidContextForGlide(context)) {
//            Glide.with(context).asBitmap().load(uri).apply((BaseRequestOptions<?>) getLoadImageOptions(i, i2, drawable)).into(imageView);
//        }
//    }
//
    public static void loadImageBlur(Context context, ImageView imageView, String str, @DrawableRes int i) {
        loadImageBlur(context, imageView, str, i, 25, 6);
    }

    public static void loadImageBlur(final Context context, final ImageView imageView, String str, @DrawableRes final int i, final int i2, final int i3) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i);
                return;
            }
            final String formatDownUrl = formatDownUrl(str);
            Log.e("GlideUtils:::388:",formatDownUrl+"\n"+str);
            if (!isEncryptionAvatarUrl(formatDownUrl)) {
                loadTargetToImageByBlur(context, imageView, formatDownUrl, i, i2, i3);
            } else if (isLocalCachePath(formatDownUrl)) {
                loadTargetToImageByBlur(context, imageView, cacheMapGet(formatDownUrl), i, i2, i3);
            } else {
                try {
                    Glide.with(context).asFile().load(formatDownUrl).into( new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.6
//                        @Override // com.bumptech.glide.request.target.Target
//                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                            onResourceReady((File) obj, (Transition<? super File>) transition);
//                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadStarted(@Nullable Drawable drawable) {
                            super.onLoadStarted(drawable);
                            imageView.setImageResource(i);
                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                            imageView.setImageResource(i);
                        }

                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                            GlideUtils.loadImageBlurByObservable(context, imageView, file, i, formatDownUrl, i2, i3);
                        }
                    });
                } catch (Exception unused) {
                    imageView.setImageResource(i);
                } catch (OutOfMemoryError unused2) {
                    imageView.setImageResource(i);
                }
            }
        }
    }

//    public static void loadImageBlur(Context context, ImageView imageView, @DrawableRes int i) {
//        if (isValidContextForGlide(context)) {
//            Glide.with(context).load(formatDrawableByResId(context, i)).apply((BaseRequestOptions<?>) getLoadImageBlurOptions()).into(imageView);
//        }
//    }

//    public static void loadRoundCornersImage(Context context, ImageView imageView, String str, int i) {
//        if (isValidContextForGlide(context)) {
//            Glide.with(context).asBitmap().load(formatDownUrl(str)).apply((BaseRequestOptions<?>) getLoadImageRoundedCornersOptions(RoundedCornersTransformation.CornerType.ALL, i)).into(imageView);
//        }
//    }
//
//    public static void loadRoundCornersImage(Context context, ImageView imageView, File file, int i) {
//        if (isValidContextForGlide(context)) {
//            Glide.with(context).asBitmap().load(file).apply((BaseRequestOptions<?>) getLoadImageRoundedCornersOptions(RoundedCornersTransformation.CornerType.ALL, i)).into(imageView);
//        }
//    }
//
//    public static void loadRoundCornersImage(Fragment fragment, ImageView imageView, String str, int i, @DrawableRes int i2) {
//        loadRoundCornersImage(fragment.getContext(), imageView, str, i, i2);
//    }

//    public static void loadRoundCornersImage(Context context, ImageView imageView, String str, int i, @DrawableRes int i2) {
//        loadRoundCornersImage(context, imageView, str, i, i2, RoundedCornersTransformation.CornerType.ALL, false);
//    }

//    public static void loadRoundCornersImage(final Context context, final ImageView imageView, String str, final int i, @DrawableRes final int i2, final RoundedCornersTransformation.CornerType cornerType, final boolean z) {
//        if (isValidContextForGlide(context)) {
//            if (TextUtils.isEmpty(str)) {
//                imageView.setImageResource(i2);
//                return;
//            }
//            imageView.setImageResource(i2);
//            final String formatDownUrl = formatDownUrl(str);
////            if (!z && !isEncryptionAvatarUrl(formatDownUrl)) {
//////                loadTargetToImageByRoundCorners(context, imageView, formatDownUrl, i, i2, cornerType);
////            } else if (isLocalCachePath(formatDownUrl)) {
//////                loadTargetToImageByRoundCorners(context, imageView, cacheMapGet(formatDownUrl), i, i2, cornerType);
////            } else {
//////                try {
//////                    Glide.with(context).asFile().load(formatDownUrl).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.7
//////                        @Override // com.bumptech.glide.request.target.Target
//////                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//////                            onResourceReady((File) obj, (Transition<? super File>) transition);
//////                        }
//////
//////                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//////                        public void onLoadStarted(@Nullable Drawable drawable) {
//////                            super.onLoadStarted(drawable);
//////                            imageView.setImageResource(i2);
//////                        }
//////
//////                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//////                        public void onLoadFailed(@Nullable Drawable drawable) {
//////                            super.onLoadFailed(drawable);
//////                            imageView.setImageResource(i2);
//////                        }
//////
//////                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
//////                            GlideUtils.loadRoundCornersImgByObservable(context, imageView, file, i, i2, formatDownUrl, cornerType, z);
//////                        }
//////                    });
//////                } catch (Exception unused) {
//////                    imageView.setImageResource(i2);
//////                } catch (OutOfMemoryError unused2) {
//////                    imageView.setImageResource(i2);
//////                }
////            }
//        }
//    }

    public static void loadRoundCornersImage(Context context, ImageView imageView, @DrawableRes int i, int i2, RoundedCornersTransformation.CornerType cornerType) {
        Glide.with(context).asBitmap().load(formatDrawableByResId(context, i)).apply((BaseRequestOptions<?>) getLoadImageRoundedCornersOptions(cornerType, i2)).into(imageView);
    }

//    public static void loadRoundCornersImage(Context context, ImageView imageView, @DrawableRes int i, int i2, int i3, int i4, RoundedCornersTransformation.CornerType cornerType) {
//        Glide.with(context).asBitmap().load(formatDrawableByResId(context, i)).apply((BaseRequestOptions<?>) getLoadImageRoundedCornersOptions(cornerType, i2)).into(imageView);
//    }

    public static void loadGifImage(final Context context, final ImageView imageView, String str, @DrawableRes final int i) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i);
                return;
            }
            final String formatDownUrl = formatDownUrl(str);
            Log.e("GlideUtils:::513:",formatDownUrl+"\n"+str);
            if (!isEncryptionAvatarUrl(formatDownUrl)) {
                loadTargetToGifImage(context, imageView, formatDownUrl, i);
            } else if (isLocalCachePath(formatDownUrl)) {
                loadTargetToGifImage(context, imageView, cacheMapGet(formatDownUrl), i);
            } else {
                try {
                    Glide.with(context).asFile().load(formatDownUrl).into( new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.8
//                        @Override // com.bumptech.glide.request.target.Target
//                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                            onResourceReady((File) obj, (Transition<? super File>) transition);
//                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadStarted(@Nullable Drawable drawable) {
                            super.onLoadStarted(drawable);
                            imageView.setImageResource(i);
                        }

                        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                            GlideUtils.loadGifImgByObservable(context, imageView, file, i, formatDownUrl);
                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                            imageView.setImageResource(i);
                        }
                    });
                } catch (Exception unused) {
                    imageView.setImageResource(i);
                } catch (OutOfMemoryError unused2) {
                    imageView.setImageResource(i);
                }
            }
        }
    }

//    public static void loadGifImage(Fragment fragment, ImageView imageView, String str, @DrawableRes int i) {
//        loadGifImage(fragment.getContext(), imageView, str, i);
//    }

    public static void loadWebpImage(Context context, ImageView imageView, String str, @DrawableRes int i) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i);
                return;
            }
            Glide.with(context).load(formatDownUrl(str)).apply((BaseRequestOptions<?>) geLoadWebpImageOptions(i)).into(imageView);
        }
    }

//    public static void loadWebpImage(Fragment fragment, ImageView imageView, String str, @DrawableRes int i) {
//        if (isValidContextForGlide(fragment)) {
//            if (TextUtils.isEmpty(str)) {
//                imageView.setImageResource(i);
//                return;
//            }
//            Glide.with(fragment).load(formatDownUrl(str)).apply((BaseRequestOptions<?>) geLoadWebpImageOptions(i)).into(imageView);
//        }
//    }

    public static File decodeImage(String str, File file, File file2) {
        if (str.contains("_s1")) {
            return decodeImageS1File(file, file2);
        }
        Log.e("GlideUtils:::579:","formatDownUrl"+"\n"+str);
        if (str.contains("_s3") || str.contains("mback")) {
            return decodeImageS3File(file, file2);
        }
        return decodeImageFile(file, file2);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static java.io.File decodeImageFile(java.io.File r5, java.io.File r6) {
//        Log.e("GlideUtils:::593:","formatDownUrl++str");
//        /*
//            r0 = 0
//            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: all -> 0x0036, Exception -> 0x003a
//            r1.<init>(r5)     // Catch: all -> 0x0036, Exception -> 0x003a
//            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: all -> 0x0030, Exception -> 0x0033
//            r5.<init>(r6)     // Catch: all -> 0x0030, Exception -> 0x0033
//            r2 = 5000(0x1388, float:7.006E-42)
//            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x002e, all -> 0x0055
//        L_0x000f:
//            int r3 = r1.read(r2)     // Catch: Exception -> 0x002e, all -> 0x0055
//            r4 = -1
//            if (r3 == r4) goto L_0x001a
//            r5.write(r2)     // Catch: Exception -> 0x002e, all -> 0x0055
//            goto L_0x000f
//        L_0x001a:
//            r5.flush()     // Catch: Exception -> 0x002e, all -> 0x0055
//            r1.close()     // Catch: Exception -> 0x0021
//            goto L_0x0025
//        L_0x0021:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x0025:
//            r5.close()     // Catch: Exception -> 0x0029
//            goto L_0x002d
//        L_0x0029:
//            r5 = move-exception
//            r5.printStackTrace()
//        L_0x002d:
//            return r6
//        L_0x002e:
//            r6 = move-exception
//            goto L_0x003d
//        L_0x0030:
//            r6 = move-exception
//            r5 = r0
//            goto L_0x0056
//        L_0x0033:
//            r6 = move-exception
//            r5 = r0
//            goto L_0x003d
//        L_0x0036:
//            r6 = move-exception
//            r5 = r0
//            r1 = r5
//            goto L_0x0056
//        L_0x003a:
//            r6 = move-exception
//            r5 = r0
//            r1 = r5
//        L_0x003d:
//            r6.printStackTrace()     // Catch: all -> 0x0055
//            if (r1 == 0) goto L_0x004a
//            r1.close()     // Catch: Exception -> 0x0046
//            goto L_0x004a
//        L_0x0046:
//            r6 = move-exception
//            r6.printStackTrace()
//        L_0x004a:
//            if (r5 == 0) goto L_0x0054
//            r5.close()     // Catch: Exception -> 0x0050
//            goto L_0x0054
//        L_0x0050:
//            r5 = move-exception
//            r5.printStackTrace()
//        L_0x0054:
//            return r0
//        L_0x0055:
//            r6 = move-exception
//        L_0x0056:
//            if (r1 == 0) goto L_0x0060
//            r1.close()     // Catch: Exception -> 0x005c
//            goto L_0x0060
//        L_0x005c:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x0060:
//            if (r5 == 0) goto L_0x006a
//            r5.close()     // Catch: Exception -> 0x0066
//            goto L_0x006a
//        L_0x0066:
//            r5 = move-exception
//            r5.printStackTrace()
//        L_0x006a:
//            goto L_0x006c
//        L_0x006b:
//            throw r6
//        L_0x006c:
//            goto L_0x006b
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.GlideUtils.decodeImageFile(java.io.File, java.io.File):java.io.File");
//    }

    public static File decodeImageFile(File file, File file2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Exception e;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[5000];
                    while (fileInputStream.read(bArr) != -1) {
                        fileOutputStream.write(bArr);
                    }
                    fileOutputStream.flush();
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    return file2;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e7) {
                                e7.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e8) {
                                e8.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e9) {
                e = e9;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileInputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            fileOutputStream = null;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            fileInputStream = null;
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static java.io.File decodeImageS1File(java.io.File r7, java.io.File r8) {
//        Log.e("GlideUtils:::693:","formatDownUrl++str");
//        /*
//            r0 = 0
//            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: all -> 0x0040, Exception -> 0x0044
//            r1.<init>(r7)     // Catch: all -> 0x0040, Exception -> 0x0044
//            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: all -> 0x003a, Exception -> 0x003d
//            r7.<init>(r8)     // Catch: all -> 0x003a, Exception -> 0x003d
//            r2 = 5000(0x1388, float:7.006E-42)
//            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x0038, all -> 0x005f
//        L_0x000f:
//            int r3 = r1.read(r2)     // Catch: Exception -> 0x0038, all -> 0x005f
//            r4 = -1
//            if (r3 == r4) goto L_0x0024
//            r3 = 0
//            r4 = r2[r3]     // Catch: Exception -> 0x0038, all -> 0x005f
//            r5 = 1
//            r6 = r2[r5]     // Catch: Exception -> 0x0038, all -> 0x005f
//            r2[r3] = r6     // Catch: Exception -> 0x0038, all -> 0x005f
//            r2[r5] = r4     // Catch: Exception -> 0x0038, all -> 0x005f
//            r7.write(r2)     // Catch: Exception -> 0x0038, all -> 0x005f
//            goto L_0x000f
//        L_0x0024:
//            r7.flush()     // Catch: Exception -> 0x0038, all -> 0x005f
//            r1.close()     // Catch: Exception -> 0x002b
//            goto L_0x002f
//        L_0x002b:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x002f:
//            r7.close()     // Catch: Exception -> 0x0033
//            goto L_0x0037
//        L_0x0033:
//            r7 = move-exception
//            r7.printStackTrace()
//        L_0x0037:
//            return r8
//        L_0x0038:
//            r8 = move-exception
//            goto L_0x0047
//        L_0x003a:
//            r8 = move-exception
//            r7 = r0
//            goto L_0x0060
//        L_0x003d:
//            r8 = move-exception
//            r7 = r0
//            goto L_0x0047
//        L_0x0040:
//            r8 = move-exception
//            r7 = r0
//            r1 = r7
//            goto L_0x0060
//        L_0x0044:
//            r8 = move-exception
//            r7 = r0
//            r1 = r7
//        L_0x0047:
//            r8.printStackTrace()     // Catch: all -> 0x005f
//            if (r1 == 0) goto L_0x0054
//            r1.close()     // Catch: Exception -> 0x0050
//            goto L_0x0054
//        L_0x0050:
//            r8 = move-exception
//            r8.printStackTrace()
//        L_0x0054:
//            if (r7 == 0) goto L_0x005e
//            r7.close()     // Catch: Exception -> 0x005a
//            goto L_0x005e
//        L_0x005a:
//            r7 = move-exception
//            r7.printStackTrace()
//        L_0x005e:
//            return r0
//        L_0x005f:
//            r8 = move-exception
//        L_0x0060:
//            if (r1 == 0) goto L_0x006a
//            r1.close()     // Catch: Exception -> 0x0066
//            goto L_0x006a
//        L_0x0066:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x006a:
//            if (r7 == 0) goto L_0x0074
//            r7.close()     // Catch: Exception -> 0x0070
//            goto L_0x0074
//        L_0x0070:
//            r7 = move-exception
//            r7.printStackTrace()
//        L_0x0074:
//            goto L_0x0076
//        L_0x0075:
//            throw r8
//        L_0x0076:
//            goto L_0x0075
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.GlideUtils.decodeImageS1File(java.io.File, java.io.File):java.io.File");
//    }

    public static File decodeImageS1File(File file, File file2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Exception e;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[5000];
                    while (fileInputStream.read(bArr) != -1) {
                        byte b2 = bArr[0];
                        bArr[0] = bArr[1];
                        bArr[1] = b2;
                        fileOutputStream.write(bArr);
                    }
                    fileOutputStream.flush();
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    return file2;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e9) {
                        e9.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            fileOutputStream = null;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            fileInputStream = null;
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }


    /* JADX WARN: Removed duplicated region for block: B:55:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static java.io.File decodeImageS3File(java.io.File r9, java.io.File r10) {
//        Log.e("GlideUtils:::799:","formatDownUrl++str");
//        /*
//            r0 = 0
//            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: all -> 0x004d, Exception -> 0x0051
//            r1.<init>(r9)     // Catch: all -> 0x004d, Exception -> 0x0051
//            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch: all -> 0x0047, Exception -> 0x004a
//            r9.<init>(r10)     // Catch: all -> 0x0047, Exception -> 0x004a
//            r2 = 1
//            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x0045, all -> 0x006c
//            r3 = -1
//            r4 = -1
//        L_0x0010:
//            int r5 = r1.read(r2)     // Catch: Exception -> 0x0045, all -> 0x006c
//            if (r5 == r3) goto L_0x0031
//            r6 = 0
//            if (r4 != r3) goto L_0x0020
//            r4 = r2[r6]     // Catch: Exception -> 0x0045, all -> 0x006c
//            r2 = 4096(0x1000, float:5.74E-42)
//            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x0045, all -> 0x006c
//            goto L_0x0010
//        L_0x0020:
//            byte[] r7 = new byte[r5]     // Catch: Exception -> 0x0045, all -> 0x006c
//        L_0x0022:
//            if (r6 >= r5) goto L_0x002d
//            r8 = r2[r6]     // Catch: Exception -> 0x0045, all -> 0x006c
//            r8 = r8 ^ r4
//            byte r8 = (byte) r8     // Catch: Exception -> 0x0045, all -> 0x006c
//            r7[r6] = r8     // Catch: Exception -> 0x0045, all -> 0x006c
//            int r6 = r6 + 1
//            goto L_0x0022
//        L_0x002d:
//            r9.write(r7)     // Catch: Exception -> 0x0045, all -> 0x006c
//            goto L_0x0010
//        L_0x0031:
//            r9.flush()     // Catch: Exception -> 0x0045, all -> 0x006c
//            r1.close()     // Catch: Exception -> 0x0038
//            goto L_0x003c
//        L_0x0038:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x003c:
//            r9.close()     // Catch: Exception -> 0x0040
//            goto L_0x0044
//        L_0x0040:
//            r9 = move-exception
//            r9.printStackTrace()
//        L_0x0044:
//            return r10
//        L_0x0045:
//            r10 = move-exception
//            goto L_0x0054
//        L_0x0047:
//            r10 = move-exception
//            r9 = r0
//            goto L_0x006d
//        L_0x004a:
//            r10 = move-exception
//            r9 = r0
//            goto L_0x0054
//        L_0x004d:
//            r10 = move-exception
//            r9 = r0
//            r1 = r9
//            goto L_0x006d
//        L_0x0051:
//            r10 = move-exception
//            r9 = r0
//            r1 = r9
//        L_0x0054:
//            r10.printStackTrace()     // Catch: all -> 0x006c
//            if (r1 == 0) goto L_0x0061
//            r1.close()     // Catch: Exception -> 0x005d
//            goto L_0x0061
//        L_0x005d:
//            r10 = move-exception
//            r10.printStackTrace()
//        L_0x0061:
//            if (r9 == 0) goto L_0x006b
//            r9.close()     // Catch: Exception -> 0x0067
//            goto L_0x006b
//        L_0x0067:
//            r9 = move-exception
//            r9.printStackTrace()
//        L_0x006b:
//            return r0
//        L_0x006c:
//            r10 = move-exception
//        L_0x006d:
//            if (r1 == 0) goto L_0x0077
//            r1.close()     // Catch: Exception -> 0x0073
//            goto L_0x0077
//        L_0x0073:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x0077:
//            if (r9 == 0) goto L_0x0081
//            r9.close()     // Catch: Exception -> 0x007d
//            goto L_0x0081
//        L_0x007d:
//            r9 = move-exception
//            r9.printStackTrace()
//        L_0x0081:
//            goto L_0x0083
//        L_0x0082:
//            throw r10
//        L_0x0083:
//            goto L_0x0082
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.GlideUtils.decodeImageS3File(java.io.File, java.io.File):java.io.File");
//    }

    public static File decodeImageS3File(File file, File file2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Exception e;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1];
                    byte b2 = -1;
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        if (b2 == -1) {
                            b2 = bArr[0];
                            bArr = new byte[4096];
                        } else {
                            byte[] bArr2 = new byte[read];
                            for (int i = 0; i < read; i++) {
                                bArr2[i] = (byte) (bArr[i] ^ b2);
                            }
                            fileOutputStream.write(bArr2);
                        }
                    }
                    fileOutputStream.flush();
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    return file2;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e9) {
                        e9.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            fileOutputStream = null;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            fileInputStream = null;
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }



    /* JADX WARN: Removed duplicated region for block: B:59:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
//    public static java.io.File decodeVideoFile(java.io.File r11, java.io.File r12) {
//        Log.e("GlideUtils:::916:","formatDownUrl++str");
//        /*
//            r0 = 0
//            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: all -> 0x0066, Exception -> 0x006a
//            r1.<init>(r11)     // Catch: all -> 0x0066, Exception -> 0x006a
//            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch: all -> 0x0060, Exception -> 0x0063
//            r11.<init>(r12)     // Catch: all -> 0x0060, Exception -> 0x0063
//            int r2 = r1.available()     // Catch: Exception -> 0x005e, all -> 0x0085
//            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x005e, all -> 0x0085
//            r3 = -1
//            r4 = -1
//        L_0x0013:
//            int r5 = r2.length     // Catch: Exception -> 0x005e, all -> 0x0085
//            r6 = 0
//            int r5 = r1.read(r2, r6, r5)     // Catch: Exception -> 0x005e, all -> 0x0085
//            if (r5 == r3) goto L_0x004a
//            if (r4 != r3) goto L_0x0036
//            int r4 = r2.length     // Catch: Exception -> 0x005e, all -> 0x0085
//            r5 = 1
//            int r4 = r4 - r5
//            byte[] r7 = new byte[r4]     // Catch: Exception -> 0x005e, all -> 0x0085
//            r8 = r2[r6]     // Catch: Exception -> 0x005e, all -> 0x0085
//        L_0x0024:
//            if (r5 >= r4) goto L_0x0031
//            int r9 = r5 + (-1)
//            r10 = r2[r5]     // Catch: Exception -> 0x005e, all -> 0x0085
//            r10 = r10 ^ r8
//            byte r10 = (byte) r10     // Catch: Exception -> 0x005e, all -> 0x0085
//            r7[r9] = r10     // Catch: Exception -> 0x005e, all -> 0x0085
//            int r5 = r5 + 1
//            goto L_0x0024
//        L_0x0031:
//            r11.write(r7, r6, r4)     // Catch: Exception -> 0x005e, all -> 0x0085
//            r4 = r8
//            goto L_0x0013
//        L_0x0036:
//            int r7 = r2.length     // Catch: Exception -> 0x005e, all -> 0x0085
//            byte[] r7 = new byte[r7]     // Catch: Exception -> 0x005e, all -> 0x0085
//            r8 = 0
//        L_0x003a:
//            int r9 = r2.length     // Catch: Exception -> 0x005e, all -> 0x0085
//            if (r8 >= r9) goto L_0x0046
//            r9 = r2[r8]     // Catch: Exception -> 0x005e, all -> 0x0085
//            r9 = r9 ^ r4
//            byte r9 = (byte) r9     // Catch: Exception -> 0x005e, all -> 0x0085
//            r7[r8] = r9     // Catch: Exception -> 0x005e, all -> 0x0085
//            int r8 = r8 + 1
//            goto L_0x003a
//        L_0x0046:
//            r11.write(r7, r6, r5)     // Catch: Exception -> 0x005e, all -> 0x0085
//            goto L_0x0013
//        L_0x004a:
//            r11.flush()     // Catch: Exception -> 0x005e, all -> 0x0085
//            r1.close()     // Catch: Exception -> 0x0051
//            goto L_0x0055
//        L_0x0051:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x0055:
//            r11.close()     // Catch: Exception -> 0x0059
//            goto L_0x005d
//        L_0x0059:
//            r11 = move-exception
//            r11.printStackTrace()
//        L_0x005d:
//            return r12
//        L_0x005e:
//            r12 = move-exception
//            goto L_0x006d
//        L_0x0060:
//            r12 = move-exception
//            r11 = r0
//            goto L_0x0086
//        L_0x0063:
//            r12 = move-exception
//            r11 = r0
//            goto L_0x006d
//        L_0x0066:
//            r12 = move-exception
//            r11 = r0
//            r1 = r11
//            goto L_0x0086
//        L_0x006a:
//            r12 = move-exception
//            r11 = r0
//            r1 = r11
//        L_0x006d:
//            r12.printStackTrace()     // Catch: all -> 0x0085
//            if (r1 == 0) goto L_0x007a
//            r1.close()     // Catch: Exception -> 0x0076
//            goto L_0x007a
//        L_0x0076:
//            r12 = move-exception
//            r12.printStackTrace()
//        L_0x007a:
//            if (r11 == 0) goto L_0x0084
//            r11.close()     // Catch: Exception -> 0x0080
//            goto L_0x0084
//        L_0x0080:
//            r11 = move-exception
//            r11.printStackTrace()
//        L_0x0084:
//            return r0
//        L_0x0085:
//            r12 = move-exception
//        L_0x0086:
//            if (r1 == 0) goto L_0x0090
//            r1.close()     // Catch: Exception -> 0x008c
//            goto L_0x0090
//        L_0x008c:
//            r0 = move-exception
//            r0.printStackTrace()
//        L_0x0090:
//            if (r11 == 0) goto L_0x009a
//            r11.close()     // Catch: Exception -> 0x0096
//            goto L_0x009a
//        L_0x0096:
//            r11 = move-exception
//            r11.printStackTrace()
//        L_0x009a:
//            goto L_0x009c
//        L_0x009b:
//            throw r12
//        L_0x009c:
//            goto L_0x009b
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.slzhibo.library.utils.GlideUtils.decodeVideoFile(java.io.File, java.io.File):java.io.File");
//    }

    public static File decodeVideoFile(File file, File file2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Exception e;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    byte b2 = -1;
                    while (true) {
                        int read = fileInputStream.read(bArr, 0, bArr.length);
                        if (read == -1) {
                            break;
                        } else if (b2 == -1) {
                            int length = bArr.length - 1;
                            byte[] bArr2 = new byte[length];
                            byte b3 = bArr[0];
                            for (int i = 1; i < length; i++) {
                                bArr2[i - 1] = (byte) (bArr[i] ^ b3);
                            }
                            fileOutputStream.write(bArr2, 0, length);
                            b2 = b3;
                        } else {
                            byte[] bArr3 = new byte[bArr.length];
                            for (int i2 = 0; i2 < bArr.length; i2++) {
                                bArr3[i2] = (byte) (bArr[i2] ^ b2);
                            }
                            fileOutputStream.write(bArr3, 0, read);
                        }
                    }
                    fileOutputStream.flush();
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    return file2;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e7) {
                                e7.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e8) {
                                e8.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e9) {
                e = e9;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileInputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            fileOutputStream = null;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            fileInputStream = null;
            if (fileInputStream != null) {
            }
            if (fileOutputStream != null) {
            }
            try {
                throw th;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

//    public static boolean isEncryptionAvatarUrl(String str) {
//        return TextUtils.equals(UserInfoManager.getInstance().getAppId(), "7") ? !TextUtils.isEmpty(str) && (str.contains("_s1") || str.contains("_s3")) : !TextUtils.isEmpty(str) && (str.contains("_s1") || str.contains("_s3") || str.contains(OkHttpStreamFetcher.IS_NEED_ENCODE));
//    }

    public static boolean isValidContextForGlide(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            return !((Activity) context).isFinishing();
        }
        return true;
    }

//    public static boolean isValidContextForGlide(Fragment fragment) {
//        return (fragment == null || fragment.getActivity() == null || fragment.getActivity().isFinishing()) ? false : true;
//    }

//    public static void loadImageByObservable(final Fragment fragment, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
//        if (isValidContextForGlide(fragment)) {
//            Observable.just(true).map(new Function<Boolean, File>() { // from class: com.slzhibo.library.utils.GlideUtils.10
//                public File apply(Boolean bool) {
//                    return GlideUtils.formatDecodeImage2File(file, str);
//                }
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.9
//                @Override // io.reactivex.Observer
//                public void onComplete() {
//                }
//
//                @Override // io.reactivex.Observer
//                public void onError(Throwable th) {
//                }
//
//                @Override // io.reactivex.Observer
//                public void onSubscribe(Disposable disposable) {
//                }
//
//                public void onNext(File file2) {
//                    if (file2 == null) {
//                        imageView.setImageResource(i);
//                        return;
//                    }
//                    GlideUtils.cacheMapPut(str, file2);
//                    GlideUtils.loadTargetToImage(fragment, imageView, file2.getAbsolutePath(), i);
//                }
//            });
//        }
//    }

//    public static void loadImgByObservable(Context context, ImageView imageView, File file, @DrawableRes int i, String str) {
//        loadImgByObservable(context, imageView, file, i, str, false);
//    }

    public static void loadImgByObservable(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str, final boolean z) {
        if (isValidContextForGlide(context)) {
            Observable.just(true).map(new Function<Boolean, File>() { // from class: com.slzhibo.library.utils.GlideUtils.12
                public File apply(Boolean bool) {
                    return GlideUtils.formatDecodeImage2File(file, str, z);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.11
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(File file2) {
                    if (file2 == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    GlideUtils.cacheMapPut(str, file2);
                    GlideUtils.loadTargetToImage(context, imageView, file2.getAbsolutePath(), i);
                }
            });
        }
    }

//    public static void loadGrayImgByObservable(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
//        if (isValidContextForGlide(context)) {
//            Observable.just(true).map(new Function<Boolean, File>() { // from class: com.slzhibo.library.utils.GlideUtils.14
//                public File apply(Boolean bool) {
//                    return GlideUtils.formatDecodeImage2File(file, str);
//                }
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.13
//                @Override // io.reactivex.Observer
//                public void onComplete() {
//                }
//
//                @Override // io.reactivex.Observer
//                public void onError(Throwable th) {
//                }
//
//                @Override // io.reactivex.Observer
//                public void onSubscribe(Disposable disposable) {
//                }
//
//                public void onNext(File file2) {
//                    if (file2 == null) {
//                        imageView.setImageResource(i);
//                        return;
//                    }
//                    Log.e("GlideUtils:::1154:",str);
////                    GlideUtils.cacheMapPut(str, file2);
////                    GlideUtils.loadTargetToImageByGray(context, imageView, file2.getAbsolutePath(), i);
//                }
//            });
//        }
//    }

    public static void loadGifImgByObservable(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
        if (isValidContextForGlide(context)) {
            Observable.just(true).map(new Function<Boolean, File>() { // from class: com.slzhibo.library.utils.GlideUtils.16
                public File apply(Boolean bool) {
                    return GlideUtils.formatDecodeImage2File(file, str);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.15
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(File file2) {
                    if (file2 == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    GlideUtils.cacheMapPut(str, file2);
                    GlideUtils.loadTargetToGifImage(context, imageView, file2.getAbsolutePath(), i);
                }
            });
        }
    }

    public static void loadAvatarByObservable(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
        if (isValidContextForGlide(context)) {
            Observable.just(true).map(new Function<Boolean, File>() { // from class: com.slzhibo.library.utils.GlideUtils.18
                public File apply(Boolean bool) {
                    return GlideUtils.formatDecodeImage2File(file, str);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.17
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(File file2) {
                    if (file2 == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    Log.e("GlideUtils:::1217:",str);
                    GlideUtils.cacheMapPut(str, file2);
                    GlideUtils.loadTargetToImageByCircle(context, imageView, file2.getAbsolutePath(), i);
                }
            });
        }
    }

    public static void loadAvatarByObservable(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final int i2, final int i3, final String str) {
        if (!isValidContextForGlide(context)) {
            imageView.setImageResource(i);
        } else {
            Observable.just(true).map(new Function() { // from class: com.slzhibo.library.utils.-..Lambda.GlideUtils.0DaIp3fKEATGS26VyjPk9xKCf7Q
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    File formatDecodeImage2File;
                    Boolean bool = (Boolean) obj;
                    formatDecodeImage2File = GlideUtils.formatDecodeImage2File(file, str);
                    return formatDecodeImage2File;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.19
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(File file2) {
                    if (file2 == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    Log.e("GlideUtils:::1255:",str);
                    GlideUtils.cacheMapPut(str, file2);
                    GlideUtils.loadTargetToImageByCircle(context, imageView, file2.getAbsolutePath(), i2, i3, R.drawable.fq_ic_placeholder_avatar_white);
                }
            });
        }
    }

//    public static void loadRoundCornersImgByObservable(Context context, ImageView imageView, File file, int i, @DrawableRes int i2, String str) {
//        loadRoundCornersImgByObservable(context, imageView, file, i, i2, str, RoundedCornersTransformation.CornerType.ALL, false);
//    }

//    public static void loadRoundCornersImgByObservable(final Context context, final ImageView imageView, final File file, final int i, @DrawableRes final int i2, final String str, final RoundedCornersTransformation.CornerType cornerType, final boolean z) {
//        if (!isValidContextForGlide(context)) {
//            imageView.setImageResource(i2);
//        } else {
//            Observable.just(true).map(new Function<Boolean, File>() { // from class: com.slzhibo.library.utils.GlideUtils.21
//                public File apply(Boolean bool) {
//                    return GlideUtils.formatDecodeImage2File(file, str, z);
//                }
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.20
//                @Override // io.reactivex.Observer
//                public void onComplete() {
//                }
//
//                @Override // io.reactivex.Observer
//                public void onError(Throwable th) {
//                }
//
//                @Override // io.reactivex.Observer
//                public void onSubscribe(Disposable disposable) {
//                }
//
//                public void onNext(File file2) {
//                    if (file2 == null) {
//                        imageView.setImageResource(i2);
//                        return;
//                    }
////                    GlideUtils.cacheMapPut(str, file2);
////                    GlideUtils.loadTargetToImageByRoundCorners(context, imageView, file2.getAbsolutePath(), i, i2, cornerType);
//                }
//            });
//        }
//    }

//    public static void loadImageBlurByObservable(Context context, ImageView imageView, File file, @DrawableRes int i, String str) {
//        loadImageBlurByObservable(context, imageView, file, i, str, 25, 6);
//    }

    public static void loadImageBlurByObservable(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str, final int i2, final int i3) {
        if (!isValidContextForGlide(context)) {
            imageView.setImageResource(i);
        } else {
            Observable.just(true).map(new Function<Boolean, File>() { // from class: com.slzhibo.library.utils.GlideUtils.23
                public File apply(Boolean bool) {
                    return GlideUtils.formatDecodeImage2File(file, str);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<File>() { // from class: com.slzhibo.library.utils.GlideUtils.22
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(File file2) {
                    if (file2 == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    Log.e("GlideUtils:::1330:",str);
                    GlideUtils.cacheMapPut(str, file2);
                    GlideUtils.loadTargetToImageByBlur(context, imageView, file2.getAbsolutePath(), i, i2, i3);
                }
            });
        }
    }

    public static File formatDecodeImage2File(File file, String str) {
        return formatDecodeImage2File(file, str, false);
    }

    public static File formatDecodeImage2File(File file, String str, boolean z) {
        String str2 = PathUtils.getExternalAppCachePath() + File.separator + "imgCache";
        FileUtils.createOrExistsDir(str2);
        File file2 = new File(str2, formatImageName(str));
        if (file2.exists()) {
            return file2;
        }
        if (z) {
            return decodeVideoFile(file, file2);
        }
        return decodeImage(str, file, file2);
    }

    public static String formatImageName(String str) {
        String str2 = System.currentTimeMillis() + "";
        if (!TextUtils.isEmpty(str)) {
            str2 = MD5Utils.getMd5(str);
        }
        return str2 + ".webp";
    }

    public static String formatDownUrl(String str) {
        if (RegexUtils.isURL(str)) {
            return str;
        }
        return AppUtils.getImgDownloadURl() + str;
    }

    public static boolean isLocalCachePath(String str) {
        Map<String, String> map = cacheMap;
        if (map == null) {
            return false;
        }
        String str2 = map.get(formatImgUrlMD5(str));
        return !TextUtils.isEmpty(str2) && str2.contains(formatImageName(str));
    }

    public static void cacheMapPut(String str, File file) {
        cacheMap.put(formatImgUrlMD5(str), file.getAbsolutePath());
    }

    public static String cacheMapGet(String str) {
        return cacheMap.get(formatImgUrlMD5(str));
    }

    private static String formatImgUrlMD5(String str) {
        return MD5Utils.getMd5(str);
    }

    public static void loadTargetToImage(Context context, ImageView imageView, @DrawableRes int i) {
        try {
            Glide.with(context).asBitmap().load(Integer.valueOf(i)).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(i);
        }
    }

    public static void loadTargetToImage(Context context, ImageView imageView, String str, @DrawableRes int i) {
        try {
            Glide.with(context).asBitmap().load(str).apply(getLoadImageOptions(i)).into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(i);
        }
    }

//    public static void loadTargetToImage(Fragment fragment, ImageView imageView, String str, @DrawableRes int i) {
//        try {
//            Glide.with(fragment).asBitmap().load(str).apply((BaseRequestOptions<?>) getLoadImageOptions(i)).into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//            imageView.setImageResource(i);
//        }
//    }

    public static void loadTargetToGifImage(Context context, ImageView imageView, String str, @DrawableRes int i) {
        try {
            Glide.with(context).asGif().load(str).apply((BaseRequestOptions<?>) getBaseRequestOptions().centerCrop().placeholder(i)).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(i);
        }
    }

//    /* JADX INFO: Access modifiers changed from: private */
//    public static void loadTargetToImageByRoundCorners(Context context, final ImageView imageView, String str, int i, @DrawableRes final int i2, RoundedCornersTransformation.CornerType cornerType) {
//        try {
//            Glide.with(context).asBitmap().load(str).apply((BaseRequestOptions<?>) getLoadImageRoundedCornersOptions(cornerType, i, i2)).listener(new RequestListener<Bitmap>() { // from class: com.slzhibo.library.utils.GlideUtils.24
//                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
//                    return false;
//                }
//
//                @Override // com.bumptech.glide.request.RequestListener
//                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
//                    imageView.setImageResource(i2);
//                    return true;
//                }
//            }).into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//            imageView.setImageResource(i2);
//        }
//    }
//
    public static void loadTargetToImageByCircle(Context context, ImageView imageView, String str, @DrawableRes int i) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i);
            } else {
                Glide.with(context).asBitmap().load(str).apply((BaseRequestOptions<?>) getCircleImageOptions(i)).into(imageView);
            }
        }
    }

//    public static void loadTargetToImageByCircle(Context context, ImageView imageView, String str, int i, int i2, @DrawableRes int i3) {
//        if (isValidContextForGlide(context)) {
//            if (TextUtils.isEmpty(str)) {
//                imageView.setImageResource(i3);
//            } else {
//                Glide.with(context).asBitmap().load(str).apply((BaseRequestOptions<?>) getCircleImageOptions(i, i2, i3)).into(imageView);
//            }
//        }
//    }

//    public static void loadTargetToImageByGray(Context context, ImageView imageView, String str, @DrawableRes int i) {
//        try {
//            Glide.with(context).asBitmap().load(str).apply((BaseRequestOptions<?>) getGrayImageOptions(i)).into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//            imageView.setImageResource(i);
//        }
//    }
//
//    public static void loadTargetToImageByBlur(Context context, ImageView imageView, String str, @DrawableRes int i) {
//        loadTargetToImageByBlur(context, imageView, str, i, 25, 6);
//    }
//
    public static void loadTargetToImageByBlur(Context context, ImageView imageView, String str, @DrawableRes int i, int i2, int i3) {
        try {
            Glide.with(context).asBitmap().load(str).apply( getLoadImageBlurOptions(i2, i3, i)).into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(i);
        }
    }

//    public static void loadImageForAllFormat(Context context, ImageView imageView, String str, @DrawableRes int i) {
//        if (!TextUtils.isEmpty(str)) {
//            String fileExtension = FileUtils.getFileExtension(str);
//            if (fileExtension.contains("gif")) {
//                loadGifImage(context, imageView, str, i);
//            } else if (fileExtension.contains("webp")) {
//                loadWebpImage(context, imageView, str, i);
//            } else {
//                loadImage(context, imageView, str, i);
//            }
//        } else {
//            imageView.setImageResource(i);
//        }
//    }
//
//    public static SVGADynamicEntity getGuardSVGADynamicEntity(final Context context, final String str, String str2, String str3, String str4) {
//        String str5 = TextUtils.equals(str4, "3") ? "#FFD171" : "#8AEEFF";
//        TextPaint textPaint = new TextPaint();
//        textPaint.setTextSize(30.0f);
//        textPaint.setFakeBoldText(true);
//        textPaint.setColor(Color.parseColor(str5));
//        TextPaint textPaint2 = new TextPaint();
//        textPaint2.setTextSize(30.0f);
//        textPaint2.setColor(-1);
//        textPaint2.setFakeBoldText(true);
//        final SVGADynamicEntity sVGADynamicEntity = new SVGADynamicEntity();
//        Bitmap drawable2Bitmap = ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, AppUtils.getUserGradeBgDrawableRes(str3)));
//        Bitmap drawable2Bitmap2 = ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, AppUtils.getUserGradeIconResource(true, NumberUtils.string2int(str3))));
//        Bitmap drawable2Bitmap3 = ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, TextUtils.equals(str4, "3") ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard));
//        sVGADynamicEntity.setDynamicText(StringUtils.formatStrLen(str2, 5), textPaint, "img_1410");
//        sVGADynamicEntity.setDynamicText(context.getString(R.string.fq_go_to_index), textPaint2, "img_1409");
//        sVGADynamicEntity.setDynamicText(str3, textPaint2, "img_1088");
//        sVGADynamicEntity.setDynamicImage(drawable2Bitmap, "img_1076");
//        if (TextUtils.equals(str4, "3")) {
//            sVGADynamicEntity.setDynamicImage(drawable2Bitmap2, "img_1607");
//        } else {
//            sVGADynamicEntity.setDynamicImage(drawable2Bitmap2, "img_1474");
//        }
//        sVGADynamicEntity.setDynamicImage(drawable2Bitmap3, "img_1077");
//        if (!isValidContextForGlide(context)) {
//            sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_68");
//            return sVGADynamicEntity;
//        } else if (!isEncryptionAvatarUrl(str)) {
//            Glide.with(context).asBitmap().load(str).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.slzhibo.library.utils.GlideUtils.25
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadStarted(@Nullable Drawable drawable) {
//                    super.onLoadStarted(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//
//                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
//                    Bitmap formatAnimAvatarImg = GlideUtils.formatAnimAvatarImg(bitmap);
//                    if (formatAnimAvatarImg == null) {
//                        SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                    } else {
//                        SVGADynamicEntity.this.setDynamicImage(formatAnimAvatarImg, r3);
//                    }
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadFailed(@Nullable Drawable drawable) {
//                    super.onLoadFailed(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//            });
//            return sVGADynamicEntity;
//        } else {
//            Glide.with(context).asFile().load(str).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.26
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                    onResourceReady((File) obj, (Transition<? super File>) transition);
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadStarted(@Nullable Drawable drawable) {
//                    super.onLoadStarted(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//
//                public void onResourceReady(@NonNull final File file, @Nullable Transition<? super File> transition) {
//                    Observable.just(true).map(new Function<Boolean, Bitmap>() { // from class: com.slzhibo.library.utils.GlideUtils.26.2
//                        public Bitmap apply(Boolean bool) {
//                            File formatDecodeImage2File = GlideUtils.formatDecodeImage2File(file, str);
//                            if (formatDecodeImage2File == null) {
//                                return null;
//                            }
//                            return GlideUtils.formatAnimAvatarImg(ImageUtils.getBitmap(formatDecodeImage2File));
//                        }
//                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() { // from class: com.slzhibo.library.utils.GlideUtils.26.1
//                        @Override // io.reactivex.Observer
//                        public void onComplete() {
//                        }
//
//                        @Override // io.reactivex.Observer
//                        public void onError(Throwable th) {
//                        }
//
//                        @Override // io.reactivex.Observer
//                        public void onSubscribe(Disposable disposable) {
//                        }
//
//                        public void onNext(Bitmap bitmap) {
//                            if (bitmap == null) {
//                                AnonymousClass26 r3 = AnonymousClass26.this;
//                                SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                                return;
//                            }
//                            AnonymousClass26 r0 = AnonymousClass26.this;
//                            SVGADynamicEntity.this.setDynamicImage(bitmap, r3);
//                        }
//                    });
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadFailed(@Nullable Drawable drawable) {
//                    super.onLoadFailed(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//            });
//            return sVGADynamicEntity;
//        }
//    }
//
public static SVGADynamicEntity getCarSVGADynamicEntity(final Context context, final String str, String str2, String str3, String str4) {
    TextPaint textPaint = new TextPaint();
    textPaint.setTextSize(30.0f);
    textPaint.setFakeBoldText(true);
    textPaint.setColor(-1);
    TextPaint textPaint2 = new TextPaint();
    textPaint2.setTextSize(30.0f);
    textPaint2.setColor(-1);
    textPaint2.setFakeBoldText(true);
    final SVGADynamicEntity sVGADynamicEntity = new SVGADynamicEntity();
    Bitmap drawable2Bitmap = ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, AppUtils.getUserGradeBgDrawableRes(str3)));
    Bitmap drawable2Bitmap2 = ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, AppUtils.getUserGradeIconResource(true, NumberUtils.string2int(str3))));
    String formatUserNickName = AppUtils.formatUserNickName(str2);
    sVGADynamicEntity.setDynamicText(context.getString(R.string.fq_car_anim_enter_tips, formatUserNickName, str4), textPaint, "img_19");
    sVGADynamicEntity.setDynamicText(str3, textPaint2, "img_146");
    sVGADynamicEntity.setDynamicImage(drawable2Bitmap, "img_144");
    sVGADynamicEntity.setDynamicImage(drawable2Bitmap2, "img_159");
    if (!isValidContextForGlide(context)) {
        sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
        return sVGADynamicEntity;
    } else if (!isEncryptionAvatarUrl(str)) {
        Glide.with(context).asBitmap().load(str).into(new SimpleTarget<Bitmap>() {
            /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass27 */

//            @Override // com.bumptech.glide.request.target.Target
//            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//            }

            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadStarted(@Nullable Drawable drawable) {
                super.onLoadStarted(drawable);
                sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
            }

            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                Bitmap formatAnimAvatarImg = GlideUtils.formatAnimAvatarImg(bitmap);
                if (formatAnimAvatarImg == null) {
                    sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
                } else {
                    sVGADynamicEntity.setDynamicImage(formatAnimAvatarImg, "img_21");
                }
            }

            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
                sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
            }
        });
        return sVGADynamicEntity;
    } else {
        Glide.with(context).asFile().load(str).into(new SimpleTarget<File>() {
            /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass28 */

//            @Override // com.bumptech.glide.request.target.Target
//            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                onResourceReady((File) obj, (Transition<? super File>) transition);
//            }

            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadStarted(@Nullable Drawable drawable) {
                super.onLoadStarted(drawable);
                sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
            }

            public void onResourceReady(@NonNull final File file, @Nullable Transition<? super File> transition) {
                Observable.just(true).map(new Function<Boolean, Bitmap>() {
                    /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass28.AnonymousClass2 */

                    public Bitmap apply(Boolean bool) {
                        File formatDecodeImage2File = GlideUtils.formatDecodeImage2File(file, str);
                        if (formatDecodeImage2File == null) {
                            return null;
                        }
                        return GlideUtils.formatAnimAvatarImg(ImageUtils.getBitmap(formatDecodeImage2File));
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() {
                    /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass28.AnonymousClass1 */

                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                    }

                    public void onNext(Bitmap bitmap) {
                        if (bitmap == null) {
//                            AnonymousClass28 r3 = AnonymousClass28.this;
                            sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
                            return;
                        }
//                        AnonymousClass28 r0 = AnonymousClass28.this;
                        sVGADynamicEntity.setDynamicImage(bitmap, "img_21");
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
//                        AnonymousClass28 r3 = AnonymousClass28.this;
                        sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
                    }
                });
            }

            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
                sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_21");
            }
        });
        return sVGADynamicEntity;
    }
}

    public static SVGADynamicEntity getNobilitySVGADynamicEntity(final Context context, final String str, String str2, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(23.0f);
        textPaint.setFakeBoldText(true);
        textPaint.setColor(-1);
        final SVGADynamicEntity sVGADynamicEntity = new SVGADynamicEntity();
        sVGADynamicEntity.setDynamicText(AppUtils.formatUserNickName(str2), textPaint, "name");
        if (!isValidContextForGlide(context)) {
            sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "border2");
            return sVGADynamicEntity;
        } else if (!isEncryptionAvatarUrl(str)) {
            Glide.with(context).asBitmap().load(str).into(new SimpleTarget<Bitmap>() {
                /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass29 */

//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadStarted(@Nullable Drawable drawable) {
                    super.onLoadStarted(drawable);
                    sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "border2");
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    Bitmap formatAnimAvatarImg = GlideUtils.formatAnimAvatarImg(bitmap, 104, 104);
                    if (formatAnimAvatarImg == null) {
                        sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "border2");
                    } else {
                        sVGADynamicEntity.setDynamicImage(formatAnimAvatarImg, "border2");
                    }
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(@Nullable Drawable drawable) {
                    super.onLoadFailed(drawable);
                    sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "border2");
                }
            });
            return sVGADynamicEntity;
        } else {
            Glide.with(context).asFile().load(str).into(new SimpleTarget<File>() {
                /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass30 */

//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                    onResourceReady((File) obj, (Transition<? super File>) transition);
//                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadStarted(@Nullable Drawable drawable) {
                    super.onLoadStarted(drawable);
                    sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "border2");
                }

                public void onResourceReady(@NonNull final File file, @Nullable Transition<? super File> transition) {
                    Observable.just(true).map(new Function<Boolean, Bitmap>() {
                        /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass30.AnonymousClass2 */

                        public Bitmap apply(Boolean bool) {
                            File formatDecodeImage2File = GlideUtils.formatDecodeImage2File(file, str);
                            if (formatDecodeImage2File == null) {
                                return null;
                            }
                            return GlideUtils.formatAnimAvatarImg(ImageUtils.getBitmap(formatDecodeImage2File), 104, 104);
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() {
                        /* class com.slzhibo.library.utils.GlideUtils.AnonymousClass30.AnonymousClass1 */

                        @Override // io.reactivex.Observer
                        public void onComplete() {
                        }

                        @Override // io.reactivex.Observer
                        public void onError(Throwable th) {
                        }

                        @Override // io.reactivex.Observer
                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(Bitmap bitmap) {
                            if (bitmap == null) {
//                                AnonymousClass30 r3 = AnonymousClass30.this;
                                sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "border2");
                                return;
                            }
//                            AnonymousClass30 r0 = AnonymousClass30.this;
                            sVGADynamicEntity.setDynamicImage(bitmap, "border2");
                        }
                    });
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(@Nullable Drawable drawable) {
                    super.onLoadFailed(drawable);
                    sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "border2");
                }
            });
            return sVGADynamicEntity;
        }
    }
//
//    public static SVGADynamicEntity getFirstKillSVGADynamicEntity(final Context context, final String str, final boolean z) {
//        final SVGADynamicEntity sVGADynamicEntity = new SVGADynamicEntity();
//        if (!isValidContextForGlide(context)) {
//            sVGADynamicEntity.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), "img_14");
//            return sVGADynamicEntity;
//        } else if (!isEncryptionAvatarUrl(str)) {
//            Glide.with(context).asBitmap().load(str).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.slzhibo.library.utils.GlideUtils.31
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadStarted(@Nullable Drawable drawable) {
//                    super.onLoadStarted(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//
//                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
//                    Bitmap formatAnimAvatarImg = GlideUtils.formatAnimAvatarImg(bitmap, Color.parseColor(z ? "#FF5252" : "#527DFF"));
//                    if (formatAnimAvatarImg == null) {
//                        SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                    } else {
//                        SVGADynamicEntity.this.setDynamicImage(formatAnimAvatarImg, r3);
//                    }
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadFailed(@Nullable Drawable drawable) {
//                    super.onLoadFailed(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//            });
//            return sVGADynamicEntity;
//        } else {
//            Glide.with(context).asFile().load(str).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.32
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                    onResourceReady((File) obj, (Transition<? super File>) transition);
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadStarted(@Nullable Drawable drawable) {
//                    super.onLoadStarted(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//
//                public void onResourceReady(@NonNull final File file, @Nullable Transition<? super File> transition) {
//                    Observable.just(true).map(new Function<Boolean, Bitmap>() { // from class: com.slzhibo.library.utils.GlideUtils.32.2
//                        public Bitmap apply(Boolean bool) {
//                            File formatDecodeImage2File = GlideUtils.formatDecodeImage2File(file, str);
//                            if (formatDecodeImage2File == null) {
//                                return null;
//                            }
//                            return GlideUtils.formatAnimAvatarImg(ImageUtils.getBitmap(formatDecodeImage2File), Color.parseColor(z ? "#FF5252" : "#527DFF"));
//                        }
//                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() { // from class: com.slzhibo.library.utils.GlideUtils.32.1
//                        @Override // io.reactivex.Observer
//                        public void onComplete() {
//                        }
//
//                        @Override // io.reactivex.Observer
//                        public void onError(Throwable th) {
//                        }
//
//                        @Override // io.reactivex.Observer
//                        public void onSubscribe(Disposable disposable) {
//                        }
//
//                        public void onNext(Bitmap bitmap) {
//                            if (bitmap == null) {
//                                AnonymousClass32 r3 = AnonymousClass32.this;
//                                SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                                return;
//                            }
//                            AnonymousClass32 r0 = AnonymousClass32.this;
//                            SVGADynamicEntity.this.setDynamicImage(bitmap, r3);
//                        }
//                    });
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadFailed(@Nullable Drawable drawable) {
//                    super.onLoadFailed(drawable);
//                    SVGADynamicEntity.this.setDynamicImage(ImageUtils.drawable2Bitmap(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), r3);
//                }
//            });
//            return sVGADynamicEntity;
//        }
//    }

    public static Bitmap scaleImage(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap getBorderBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        float f = min;
        RectF rectF = new RectF(0.0f, 0.0f, f, f);
        float f2 = i;
        float f3 = min - i;
        RectF rectF2 = new RectF(f2, f2, f3, f3);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF2, rectF2.centerX(), rectF2.centerY(), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (Rect) null, rectF2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        paint.setColor(i2);
        canvas.drawRoundRect(rectF, f, f, paint);
        return createBitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Rect rect = new Rect(0, 0, min, min);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        float f = i;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

//    public static void downloadFile2Drawable(Context context, final String str, final OnGlideDownloadCallback<Drawable> onGlideDownloadCallback) {
//        if (context != null) {
//            Glide.with(context).asFile().load(str).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: com.slzhibo.library.utils.GlideUtils.33
//                @Override // com.bumptech.glide.request.target.Target
//                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
//                    onResourceReady((File) obj, (Transition<? super File>) transition);
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadStarted(@Nullable Drawable drawable) {
//                    super.onLoadStarted(drawable);
//                    OnGlideDownloadCallback onGlideDownloadCallback2 = OnGlideDownloadCallback.this;
//                    if (onGlideDownloadCallback2 != null) {
//                        onGlideDownloadCallback2.onLoadStarted(drawable);
//                    }
//                }
//
//                public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
//                    GlideUtils.saveFileByLocalCache(str, file, OnGlideDownloadCallback.this);
//                }
//
//                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
//                public void onLoadFailed(@Nullable Drawable drawable) {
//                    super.onLoadFailed(drawable);
//                    OnGlideDownloadCallback onGlideDownloadCallback2 = OnGlideDownloadCallback.this;
//                    if (onGlideDownloadCallback2 != null) {
//                        onGlideDownloadCallback2.onLoadFailed(drawable);
//                    }
//                }
//            });
//        }
//    }
//
//    public static Drawable getLocalCacheFile2Drawable(String str) {
//        if (TextUtils.isEmpty(str) || !isLocalCachePath(str)) {
//            return null;
//        }
//        String cacheMapGet = cacheMapGet(str);
//        if (!FileUtils.isExist(cacheMapGet)) {
//            return null;
//        }
//        return new BitmapDrawable(Utils.getApp().getResources(), cacheMapGet);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public static void saveFileByLocalCache(final String str, File file, final OnGlideDownloadCallback<Drawable> onGlideDownloadCallback) {
//        Observable.just(formatDecodeImage2File(file, str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<File>() { // from class: com.slzhibo.library.utils.GlideUtils.34
//            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
//            public void onSubscribe(Disposable disposable) {
//                super.onSubscribe(disposable);
//            }
//
//            public void accept(File file2) {
//                if (file2 == null) {
//                    OnGlideDownloadCallback onGlideDownloadCallback2 = OnGlideDownloadCallback.this;
//                    if (onGlideDownloadCallback2 != null) {
//                        onGlideDownloadCallback2.onLoadFailed(null);
//                        return;
//                    }
//                    return;
//                }
//                GlideUtils.cacheMapPut(str, file2);
//                if (OnGlideDownloadCallback.this != null) {
//                    OnGlideDownloadCallback.this.onLoadSuccess(new BitmapDrawable(Utils.getApp().getResources(), file2.getAbsolutePath()));
//                }
//            }
//
//            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
//            public void onError(Throwable th) {
//                super.onError(th);
//                OnGlideDownloadCallback onGlideDownloadCallback2 = OnGlideDownloadCallback.this;
//                if (onGlideDownloadCallback2 != null) {
//                    onGlideDownloadCallback2.onLoadFailed(null);
//                }
//            }
//        });
//    }

//    public static Drawable getDrawableByGlide(Context context, String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        try {
//            return Glide.with(context).asDrawable().load(formatDownUrl(str)).submit(80, 80).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        } catch (ExecutionException e2) {
//            e2.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Bitmap getBitmapByGlide(Context context, String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        try {
//            return Glide.with(context).asBitmap().load(formatDownUrl(str)).submit(80, 80).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        } catch (ExecutionException e2) {
//            e2.printStackTrace();
//            return null;
//        }
//    }
//
    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap formatAnimAvatarImg(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return getRoundedCornerBitmap(scaleImage(bitmap, 426, 426), 360);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap formatAnimAvatarImg(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        return getBorderBitmap(scaleImage(bitmap, 426, 426), ConvertUtils.dp2px(12.0f), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap formatAnimAvatarImg(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        return getRoundedCornerBitmap(scaleImage(bitmap, i, i2), 360);
    }

    public static void loadMatisseImage(Context context, ImageView imageView, Uri uri, int i, int i2) {
        Glide.with(context).asBitmap().load(uri).apply((BaseRequestOptions<?>) getBaseRequestOptions().fitCenter().override(i, i2).priority(Priority.HIGH)).into(imageView);
    }

    public static void loadMatisseGifImage(Context context, ImageView imageView, Uri uri, int i, int i2) {
        Glide.with(context).asGif().load(uri).apply((BaseRequestOptions<?>) getBaseRequestOptions().fitCenter().override(i, i2).priority(Priority.HIGH)).into(imageView);
    }

    private static RequestOptions getBaseRequestOptions() {
        return new RequestOptions().skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    private static RequestOptions getCircleImageOptions() {
        return getBaseRequestOptions().centerCrop().circleCrop();
    }

    private static RequestOptions getCircleImageOptions(@DrawableRes int i) {
        return getCircleImageOptions().placeholder(i);
    }

//    private static RequestOptions getCircleImageOptions(float f, int i, @DrawableRes int i2) {
//        return getCircleImageOptions(i2).transform(new GlideCircleBorderTransform(f, i));
//    }

    private static RequestOptions getLoadImageOptions() {
        return getBaseRequestOptions().centerCrop();
    }

    private static RequestOptions getLoadImageOptions(@DrawableRes int i) {
        return getLoadImageOptions().placeholder(i);
    }

    private static RequestOptions getLoadImageOptions(int i, int i2) {
        return getLoadImageOptions().override(i, i2);
    }

//    private static RequestOptions getLoadImageOptions(int i, int i2, Drawable drawable) {
//        return getLoadImageOptions(i, i2).override(i, i2).placeholder(drawable);
//    }

//    private static RequestOptions getGrayImageOptions(@DrawableRes int i) {
//        return getBaseRequestOptions().transform(new GrayscaleTransformation()).placeholder(i);
//    }

//    private static RequestOptions getLoadImageBlurOptions() {
//        return getLoadImageBlurOptions(25, 6);
//    }

    private static RequestOptions getLoadImageBlurOptions(int i, int i2) {
        return getBaseRequestOptions().transform(new CenterCrop(), new BlurTransformation(i, i2));
    }

//    private static RequestOptions getLoadImageBlurOptions(@DrawableRes int i) {
//        return getLoadImageBlurOptions(25, 6, i);
//    }

    private static RequestOptions getLoadImageBlurOptions(int i, int i2, @DrawableRes int i3) {
        return getLoadImageBlurOptions(i, i2).placeholder(i3);
    }

    private static RequestOptions getLoadImageRoundedCornersOptions(RoundedCornersTransformation.CornerType cornerType, int i) {
        return getBaseRequestOptions().transform(new CenterCrop(), new RoundedCornersTransformation(ConvertUtils.dp2px(i), 0, cornerType));
    }
//
//    private static RequestOptions getLoadImageRoundedCornersOptions(RoundedCornersTransformation.CornerType cornerType, int i, @DrawableRes int i2) {
//        return getLoadImageRoundedCornersOptions(cornerType, i).placeholder(i2);
//    }

    private static RequestOptions geLoadWebpImageOptions(@DrawableRes int i) {
        CenterCrop centerCrop = new CenterCrop();
        return getBaseRequestOptions().optionalTransform(centerCrop).optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(centerCrop)).placeholder(i);
    }

    public static Drawable formatDrawableByResId(Context context, @DrawableRes int i) {
        return ContextCompat.getDrawable(context, i);
    }




    public static void loadAdBannerImageForRoundView(Context context, ImageView imageView, String str, @DrawableRes int i) {
        if (!TextUtils.isEmpty(str)) {
            String f = FileUtils.getFileExtension(str);
            if (f.contains("gif")) {
                loadGifImage(context, imageView, str, i);
            } else if (f.contains("webp")) {
                loadWebpImage(context, imageView, str, i);
            } else {
                loadImage(context, imageView, str, i);
            }
        } else {
            imageView.setImageResource(i);
        }
    }

    public static void loadImage(Context context, ImageView imageView, String str, @DrawableRes int i) {
        loadImage(context, imageView, str, i, false);
    }

    public static void loadImage(Context context, ImageView imageView, String str, @DrawableRes int i, boolean z) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i);
                return;
            }
            String formatDownUrl = formatDownUrl(str);
            if (!z && !isEncryptionAvatarUrl(formatDownUrl)) {
                loadTargetToImage(context, imageView, formatDownUrl, i);
            } else if (isLocalCachePath(formatDownUrl)) {
                loadTargetToImage(context, imageView, cacheMapGet(formatDownUrl), i);
            } else {
                try {
                    Glide.with(context).asFile().load(formatDownUrl).into( new C3082c0(imageView, i, context, formatDownUrl, z));
                } catch (Exception unused) {
                    imageView.setImageResource(i);
                } catch (OutOfMemoryError unused2) {
                    imageView.setImageResource(i);
                }
            }
        }
    }

    /* renamed from: com.slzhibo.library.utils.GlideUtils.c0 */
    /* loaded from: classes2.dex */
    public static class C3082c0 extends SimpleTarget<File> {

        /* renamed from: b */
        public final /* synthetic */ ImageView f9609b;

        /* renamed from: c */
        public final /* synthetic */ int f9610c;

        /* renamed from: d */
        public final /* synthetic */ Context f9611d;

        /* renamed from: e */
        public final /* synthetic */ String f9612e;

        /* renamed from: f */
        public final /* synthetic */ boolean f9613f;

        public C3082c0(ImageView imageView, int i, Context context, String str, boolean z) {
            this.f9609b = imageView;
            this.f9610c = i;
            this.f9611d = context;
            this.f9612e = str;
            this.f9613f = z;
        }

        /* renamed from: a */
        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
            GlideUtils.loadImgByObservable(this.f9611d, this.f9609b, file, this.f9610c, this.f9612e, this.f9613f);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            super.onLoadFailed(drawable);
            this.f9609b.setImageResource(this.f9610c);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadStarted(@Nullable Drawable drawable) {
            super.onLoadStarted(drawable);
            this.f9609b.setImageResource(this.f9610c);
        }
    }


    public static boolean isEncryptionAvatarUrl(String str) {
        return !TextUtils.isEmpty(str) && (str.contains("_s1") || str.contains("_s3") || str.contains("mback"));
    }






    public static void loadAvatar(Context context, ImageView imageView, String str, int i, int i2) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
            } else if (!isEncryptionAvatarUrl(str)) {
                loadTargetToImageByCircle(context, imageView, str, i, i2, R.drawable.fq_ic_placeholder_avatar_white);
            } else if (isLocalCachePath(str)) {
                loadTargetToImageByCircle(context, imageView, cacheMapGet(str), i, i2, R.drawable.fq_ic_placeholder_avatar_white);
            } else {
                try {
                    Glide.with(context).asFile().load(str).into( new C3110v(imageView, context, i, i2, str));
                } catch (Exception unused) {
                    imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
                } catch (OutOfMemoryError unused2) {
                    imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
                }
            }
        }
    }

    public static void loadTargetToImageByCircle(Context context, ImageView imageView, String str, int i, int i2, @DrawableRes int i3) {
        if (isValidContextForGlide(context)) {
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i3);
            } else {
                Glide.with(context).asBitmap().load(str).apply(getCircleImageOptions(i, i2, i3)).into(imageView);
            }
        }
    }

    public static RequestOptions getCircleImageOptions(float f, int i, @DrawableRes int i2) {
        return getCircleImageOptions(i2).transform(new GlideCircleBorderTransform(f, i));
    }

    /* renamed from: com.slzhibo.library.utils.GlideUtils.v */
    /* loaded from: classes2.dex */
    public static class C3110v extends SimpleTarget<File> {

        /* renamed from: b */
        public final /* synthetic */ ImageView f9705b;

        /* renamed from: c */
        public final /* synthetic */ Context f9706c;

        /* renamed from: d */
        public final /* synthetic */ int f9707d;

        /* renamed from: e */
        public final /* synthetic */ int f9708e;

        /* renamed from: f */
        public final /* synthetic */ String f9709f;

        public C3110v(ImageView imageView, Context context, int i, int i2, String str) {
            this.f9705b = imageView;
            this.f9706c = context;
            this.f9707d = i;
            this.f9708e = i2;
            this.f9709f = str;
        }

        /* renamed from: a */
        public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
            GlideUtils.loadAvatarByObservable(this.f9706c, this.f9705b, file, R.drawable.fq_ic_placeholder_avatar_white, this.f9707d, this.f9708e, this.f9709f);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            super.onLoadFailed(drawable);
            this.f9705b.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadStarted(@Nullable Drawable drawable) {
            super.onLoadStarted(drawable);
            this.f9705b.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
        }
    }
    

}

