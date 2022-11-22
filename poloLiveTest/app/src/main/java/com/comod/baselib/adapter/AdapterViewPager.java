package com.comod.baselib.adapter;

//package com.comod.baselib.adapter;

import android.content.Context;
//import android.support.p001v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/* loaded from: classes.dex */
public class AdapterViewPager extends ViewPager {

    /* renamed from: com.comod.baselib.adapter.AdapterViewPager$a */
    /* loaded from: classes.dex */
    public class C0886a implements ViewPager.OnPageChangeListener {
        public C0886a() {
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            AdapterViewPager.this.requestLayout();
        }
    }

    public AdapterViewPager(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public final int m20161a(int i, View view) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int measuredHeight = view != null ? view.getMeasuredHeight() : 0;
        return mode == Integer.MIN_VALUE ? Math.min(measuredHeight, size) : measuredHeight;
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        try {
            View childAt = getChildAt(getCurrentItem());
            if (childAt != null) {
                childAt.measure(i, i2);
            }
            setMeasuredDimension(getMeasuredWidth(), m20161a(i2, childAt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AdapterViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        addOnPageChangeListener(new C0886a());
    }
}

