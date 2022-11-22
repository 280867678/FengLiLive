package com.slzhibo.library.ui.view.widget.bgabanner;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/* renamed from: com.slzhibo.library.ui.view.widget.bgabanner.BGABannerUtil */
/* loaded from: classes6.dex */
public class BGABannerUtil {
    private BGABannerUtil() {
    }

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, f, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, f, context.getResources().getDisplayMetrics());
    }

    public static ImageView getItemImageView(Context context, @DrawableRes int i, BGALocalImageSize bGALocalImageSize, ImageView.ScaleType scaleType) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(getScaledImageFromResource(context, i, bGALocalImageSize.getMaxWidth(), bGALocalImageSize.getMaxHeight(), bGALocalImageSize.getMinWidth(), bGALocalImageSize.getMinHeight()));
        imageView.setClickable(true);
        imageView.setScaleType(scaleType);
        return imageView;
    }

    public static void resetPageTransformer(List<? extends View> list) {
        if (list != null) {
            for (View view : list) {
                view.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(view, 1.0f);
                ViewCompat.setPivotX(view, view.getMeasuredWidth() * 0.5f);
                ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
                ViewCompat.setTranslationX(view, 0.0f);
                ViewCompat.setTranslationY(view, 0.0f);
                ViewCompat.setScaleX(view, 1.0f);
                ViewCompat.setScaleY(view, 1.0f);
                ViewCompat.setRotationX(view, 0.0f);
                ViewCompat.setRotationY(view, 0.0f);
                ViewCompat.setRotation(view, 0.0f);
            }
        }
    }

    public static boolean isIndexNotOutOfBounds(int i, Collection collection) {
        return isCollectionNotEmpty(collection, new Collection[0]) && i < collection.size();
    }

    public static boolean isCollectionEmpty(Collection collection, Collection... collectionArr) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        for (Collection collection2 : collectionArr) {
            if (collection2 == null || collection2.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCollectionNotEmpty(Collection collection, Collection... collectionArr) {
        return !isCollectionEmpty(collection, collectionArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0023, code lost:
        return null;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Bitmap getScaledImageFromResource(@NonNull Context context, int i, int i2, int i3, float f, float f2) {
        LoadBitmapPair<Throwable> imageFromResource;
        do {
            imageFromResource = getImageFromResource(context, i, i2, i3);
            if (imageFromResource != null && imageFromResource.first != null) {
                break;
            }
            i2 /= 2;
            i3 /= 2;
            if (imageFromResource == null || !(imageFromResource.second instanceof OutOfMemoryError) || i2 <= f) {
                break;
            }
        } while (i3 > f2);
        return (Bitmap) imageFromResource.first;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [int] */
    /* JADX WARN: Type inference failed for: r10v1, types: [com.slzhibo.library.ui.view.widget.bgabanner.BGABannerUtil$LoadBitmapPair<java.lang.Throwable>] */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v3, types: [com.slzhibo.library.ui.view.widget.bgabanner.BGABannerUtil$LoadBitmapPair<java.lang.Throwable>] */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    public static LoadBitmapPair<Throwable> getImageFromResource(@NonNull Context context, int i, int i2, int i3) {
        Throwable th;
        OutOfMemoryError e;
        Exception e2;
        LoadBitmapPair<Throwable> loadBitmapPair;
        InputStream inputStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap.Config config = Bitmap.Config.RGB_565;
        try {
            try {
                try {
                    if (i2 == 0 && i3 == 0) {
                        options.inPreferredConfig = config;
                        inputStream = context.getResources().openRawResource(i);
                        loadBitmapPair = new LoadBitmapPair<>(BitmapFactory.decodeStream(inputStream, null, options), null);
                        inputStream.close();
                    } else {
                        options.inJustDecodeBounds = true;
                        options.inPreferredConfig = config;
                        InputStream openRawResource = context.getResources().openRawResource(i);
                        try {
                            BitmapFactory.decodeStream(openRawResource, null, options);
                            openRawResource.reset();
                            openRawResource.close();
                            int i4 = options.outWidth;
                            int i5 = options.outHeight;
                            int resizedDimension = getResizedDimension(i2, i3, i4, i5);
                            int resizedDimension2 = getResizedDimension(i3, i2, i5, i4);
                            options.inJustDecodeBounds = false;
                            options.inSampleSize = calculateInSampleSize(options, resizedDimension, resizedDimension2);
                            options.inPreferredConfig = config;
                            inputStream = context.getResources().openRawResource(i);
                            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                            inputStream.close();
                            if (decodeStream == null || (decodeStream.getWidth() <= resizedDimension && decodeStream.getHeight() <= resizedDimension2)) {
                                loadBitmapPair = new LoadBitmapPair<>(decodeStream, null);
                            } else {
                                LoadBitmapPair<Throwable> loadBitmapPair2 = new LoadBitmapPair<>(Bitmap.createScaledBitmap(decodeStream, resizedDimension, resizedDimension2, true), null);
                                decodeStream.recycle();
                                loadBitmapPair = loadBitmapPair2;
                            }
                        } catch (Exception e3) {
                            e2 = e3;
//                                context = openRawResource;
                            e2.printStackTrace();
                            LoadBitmapPair<Throwable> loadBitmapPair3 = new LoadBitmapPair<>(null, e2);
//                                if (context == 0) {
//                                    return loadBitmapPair3;
//                                }
//                                context.close();
//                                i2 = loadBitmapPair3;
                            return null;
                        } catch (OutOfMemoryError e4) {
                            e = e4;
//                                context = openRawResource;
                            e.printStackTrace();
                            LoadBitmapPair<Throwable> loadBitmapPair4 = new LoadBitmapPair<>(null, e);
//                                if (context == 0) {
//                                    return loadBitmapPair4;
//                                }
//                                context.close();
//                                i2 = loadBitmapPair4;
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
//                                context = openRawResource;
//                                if (context != 0) {
//                                    try {
//                                        context.close();
//                                    } catch (IOException e5) {
//                                        e5.printStackTrace();
//                                    }
//                                }
                            throw th;
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    return loadBitmapPair;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e8) {
                e2 = e8;
            } catch (OutOfMemoryError e9) {
                e = e9;
            }
        } catch (Exception e10) {
            e2 = e10;
//            context = 0;
        } catch (OutOfMemoryError e11) {
            e = e11;
//            context = 0;
        } catch (Throwable th4) {
            th = th4;
//            context = 0;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.slzhibo.library.ui.view.widget.bgabanner.BGABannerUtil$LoadBitmapPair */
    /* loaded from: classes6.dex */
    public static class LoadBitmapPair<S extends Throwable> extends Pair<Bitmap, S> {
        LoadBitmapPair(@Nullable Bitmap bitmap, @Nullable S s) {
            super(bitmap, s);
        }
    }

    public static int getResizedDimension(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (i == 0) {
            double d = i2;
            double d2 = i4;
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = i3;
            Double.isNaN(d3);
            return (int) (d3 * (d / d2));
        } else if (i2 == 0) {
            return i;
        } else {
            double d4 = i4;
            double d5 = i3;
            Double.isNaN(d4);
            Double.isNaN(d5);
            double d6 = d4 / d5;
            double d7 = i;
            Double.isNaN(d7);
            double d8 = i2;
            if (d7 * d6 <= d8) {
                return i;
            }
            Double.isNaN(d8);
            return (int) (d8 / d6);
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = 1;
        if (!(i == 0 || i2 == 0)) {
            int i4 = options.outHeight;
            int i5 = options.outWidth;
            if (i4 > i2 || i5 > i) {
                int i6 = i4 / 2;
                int i7 = i5 / 2;
                while (i6 / i3 >= i2 && i7 / i3 >= i) {
                    i3 *= 2;
                }
            }
        }
        return i3;
    }
}

