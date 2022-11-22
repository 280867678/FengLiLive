package com.comod.baselib.view.banner;


import android.view.View;

import androidx.viewpager.widget.ViewPager;


/* loaded from: classes.dex */
public abstract class BasePageTransformer implements ViewPager.PageTransformer {

    /* renamed from: com.comod.baselib.view.banner.BasePageTransformer$a */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C0896a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f1603a = new int[Transformer.values().length];

        static {
            try {
                f1603a[Transformer.Default.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1603a[Transformer.Alpha.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1603a[Transformer.Rotate.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1603a[Transformer.Cube.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1603a[Transformer.Flip.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1603a[Transformer.Accordion.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1603a[Transformer.ZoomFade.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1603a[Transformer.ZoomCenter.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1603a[Transformer.ZoomStack.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1603a[Transformer.Stack.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1603a[Transformer.Depth.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1603a[Transformer.Zoom.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1603a[Transformer.Scale.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* renamed from: a */
    public static BasePageTransformer m20109a(Transformer transformer) {
        switch (C0896a.f1603a[transformer.ordinal()]) {
            case 1:
                return new DefaultPageTransformer();
            case 2:
                return new AlphaPageTransformer();
            case 3:
                return new RotatePageTransformer();
            case 4:
                return new CubePageTransformer();
            case 5:
                return new FlipPageTransformer();
            case 6:
                return new AccordionPageTransformer();
            case 7:
                return new ZoomFadePageTransformer();
            case 8:
                return new ZoomCenterPageTransformer();
            case 9:
                return new ZoomStackPageTransformer();
            case 10:
                return new StackPageTransformer();
            case 11:
                return new DepthPageTransformer();
            case 12:
                return new ZoomPageTransformer();
            case 13:
                return new ScalePageTransformer();
            default:
                return new DefaultPageTransformer();
        }
    }

    /* renamed from: a */
    public abstract void mo20084a(View view, float f);

    /* renamed from: b */
    public abstract void mo20083b(View view, float f);

    /* renamed from: c */
    public abstract void mo20082c(View view, float f);

    @Override // android.support.p001v4.view.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            mo20084a(view, f);
        } else if (f <= 0.0f) {
            mo20083b(view, f);
        } else if (f <= 1.0f) {
            mo20082c(view, f);
        } else {
            mo20084a(view, f);
        }
    }
}

