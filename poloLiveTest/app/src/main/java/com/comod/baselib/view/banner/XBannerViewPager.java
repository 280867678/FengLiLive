package com.comod.baselib.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class XBannerViewPager extends ViewPager {
    /* renamed from: b */
    public boolean f1660b = true;

    /* renamed from: c */
    public AbstractC0903a f1661c;

    /* renamed from: com.comod.baselib.view.banner.XBannerViewPager$a */
    /* loaded from: classes.dex */
    public interface AbstractC0903a {
        /* renamed from: a */
        void mo20085a(float f);
    }

    public XBannerViewPager(Context context) {
        super(context);
    }

    private float getXVelocity() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mVelocityTracker");
            declaredField.setAccessible(true);
            VelocityTracker velocityTracker = (VelocityTracker) declaredField.get(this);
            Field declaredField2 = ViewPager.class.getDeclaredField("mActivePointerId");
            declaredField2.setAccessible(true);
            Field declaredField3 = ViewPager.class.getDeclaredField("mMaximumVelocity");
            declaredField3.setAccessible(true);
            velocityTracker.computeCurrentVelocity(1000, declaredField3.getInt(this));
            return VelocityTrackerCompat.getXVelocity(velocityTracker, declaredField2.getInt(this));
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f1660b && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f1660b) {
            return false;
        }
        if (this.f1661c == null || (motionEvent.getAction() != 3 && motionEvent.getAction() != 1)) {
            return super.onTouchEvent(motionEvent);
        }
        this.f1661c.mo20085a(getXVelocity());
        return false;
    }

    public void setAutoPlayDelegate(AbstractC0903a aVar) {
        this.f1661c = aVar;
    }

    public void setBannerCurrentItemInternal(int i, boolean z) {
        try {
            Method declaredMethod = ViewPager.class.getDeclaredMethod("setCurrentItemInternal", Integer.TYPE, Boolean.TYPE, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, Integer.valueOf(i), Boolean.valueOf(z), true);
            ViewCompat.postInvalidateOnAnimation(this);
        } catch (Exception unused) {
        }
    }

    public void setIsAllowUserScroll(boolean z) {
        this.f1660b = z;
    }

    @Override // android.support.p001v4.view.ViewPager
    public void setPageTransformer(boolean z, PageTransformer pageTransformer) {
        boolean z2 = pageTransformer != null;
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mPageTransformer");
            declaredField.setAccessible(true);
            boolean z3 = z2 != (((PageTransformer) declaredField.get(this)) != null);
            declaredField.set(this, pageTransformer);
            Method declaredMethod = ViewPager.class.getDeclaredMethod("setChildrenDrawingOrderEnabledCompat", Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, Boolean.valueOf(z2));
            Field declaredField2 = ViewPager.class.getDeclaredField("mDrawingOrder");
            declaredField2.setAccessible(true);
            if (z2) {
                declaredField2.setInt(this, z ? 2 : 1);
            } else {
                declaredField2.setInt(this, 0);
            }
            if (z3) {
                Method declaredMethod2 = ViewPager.class.getDeclaredMethod("populate", new Class[0]);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(this, new Object[0]);
            }
        } catch (Exception unused) {
        }
    }

    public void setScrollDuration(int i) {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, new XBannerScroller(getContext(), i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XBannerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


}
