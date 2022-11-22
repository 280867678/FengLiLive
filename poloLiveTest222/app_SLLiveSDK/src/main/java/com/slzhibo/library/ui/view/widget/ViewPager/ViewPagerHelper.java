package com.slzhibo.library.ui.view.widget.ViewPager;



import androidx.viewpager.widget.ViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;


/* renamed from: com.slzhibo.library.ui.view.widget.magicindicator.ViewPagerHelper */
/* loaded from: classes6.dex */
public class ViewPagerHelper {
    public static void bind(final MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.slzhibo.library.ui.view.widget.magicindicator.ViewPagerHelper.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                magicIndicator.onPageScrolled(i, f, i2);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                magicIndicator.onPageSelected(i);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                magicIndicator.onPageScrollStateChanged(i);
            }
        });
    }
}

