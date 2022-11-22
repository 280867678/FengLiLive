package com.comod.baselib.view;

//package com.comod.baselib.view;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.blmvl.blvl.R;
//import com.comod.baselib.R.styleable;

/* loaded from: classes.dex */
public class MyViewPager extends ViewPager {

    /* renamed from: b */
    public boolean f1573b;

    public MyViewPager(Context context) {
        this(context, null);
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f1573b) {
            try {
                return super.onInterceptTouchEvent(motionEvent);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f1573b) {
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public void setCanScroll(boolean z) {
        this.f1573b = z;
    }

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MyViewPager);
        this.f1573b = obtainStyledAttributes.getBoolean(R.styleable.MyViewPager_canScroll, true);
        obtainStyledAttributes.recycle();
    }
}

