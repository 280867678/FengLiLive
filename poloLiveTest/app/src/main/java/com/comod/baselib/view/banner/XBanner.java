package com.comod.baselib.view.banner;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.blmvl.blvl.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;



public class XBanner extends RelativeLayout implements XBannerViewPager.AbstractC0903a, ViewPager.OnPageChangeListener {




    /* renamed from: A */
    public boolean f1607A;

    /* renamed from: B */
    public int f1608B;

    /* renamed from: C */
    public boolean f1609C;

    /* renamed from: D */
    public List<String> f1610D;

    /* renamed from: E */
    public int f1611E;

    /* renamed from: F */
    public AbstractC0900d f1612F;

    /* renamed from: G */
    public RelativeLayout.LayoutParams f1613G;

    /* renamed from: H */
    public boolean f1614H;

    /* renamed from: I */
    public TextView f1615I;

    /* renamed from: J */
    public Drawable f1616J;

    /* renamed from: K */
    public boolean f1617K;

    /* renamed from: L */
    public int f1618L;

    /* renamed from: M */
    public boolean f1619M;

    /* renamed from: N */
    public boolean f1620N;

    /* renamed from: O */
    public boolean f1621O;

    /* renamed from: P */
    public Transformer f1622P;

    /* renamed from: Q */
    public int f1623Q;

    /* renamed from: R */
    public ImageView f1624R;

    /* renamed from: S */
    public boolean f1625S;

    /* renamed from: T */
    public int f1626T;

    /* renamed from: U */
    public int f1627U;

    /* renamed from: V */
    public int f1628V;

    /* renamed from: W */
    public boolean f1629W;

    /* renamed from: a0 */
    public int f1630a0;

    /* renamed from: b */
    public int f1631b;

    /* renamed from: c */
    public float f1632c;

    /* renamed from: d */
    public ViewPager.OnPageChangeListener f1633d;

    /* renamed from: e */
    public AbstractC0899c f1634e;

    /* renamed from: f */
    public RunnableC0898b f1635f;

    /* renamed from: g */
    public LinearLayout f1636g;

    /* renamed from: h */
    public XBannerViewPager f1637h;

    /* renamed from: i */
    public int f1638i;

    /* renamed from: j */
    public int f1639j;

    /* renamed from: k */
    public int f1640k;

    /* renamed from: l */
    public List<?> f1641l;

    /* renamed from: m */
    public List<View> f1642m;

    /* renamed from: n */
    public List<View> f1643n;

    /* renamed from: o */
    public boolean f1644o;

    /* renamed from: p */
    public boolean f1645p;

    /* renamed from: q */
    public int f1646q;

    /* renamed from: r */
    public boolean f1647r;

    /* renamed from: s */
    public int f1648s;

    /* renamed from: t */
    public int f1649t;
    @DrawableRes

    /* renamed from: u */
    public int f1650u;
    @DrawableRes

    /* renamed from: v */
    public int f1651v;

    /* renamed from: w */
    public Drawable f1652w;

    /* renamed from: x */
    public RelativeLayout.LayoutParams f1653x;

    /* renamed from: y */
    public TextView f1654y;

    /* renamed from: z */
    public int f1655z;

    /* renamed from: com.comod.baselib.view.banner.XBanner.b */
    /* loaded from: classes.dex */
    public static class RunnableC0898b implements Runnable {

        /* renamed from: b */
        public final WeakReference<XBanner> f1656b;

        @Override // java.lang.Runnable
        public void run() {
            XBanner xBanner = this.f1656b.get();
            if (xBanner != null) {
                if (xBanner.f1637h != null) {
                    xBanner.f1637h.setCurrentItem(xBanner.f1637h.getCurrentItem() + 1);
                }
                xBanner.m20090g();
            }
        }

        public RunnableC0898b(XBanner xBanner) {
            this.f1656b = new WeakReference<>(xBanner);
        }
    }

    /* renamed from: com.comod.baselib.view.banner.XBanner.c */
    /* loaded from: classes.dex */
    public interface AbstractC0899c {
        /* renamed from: a */
        void mo9577a(XBanner xBanner, Object obj, View view, int i);
    }

    /* renamed from: com.comod.baselib.view.banner.XBanner.d */
    /* loaded from: classes.dex */
    public interface AbstractC0900d {
        /* renamed from: a */
        void mo9582a(XBanner xBanner, Object obj, View view, int i);
    }

    /* renamed from: com.comod.baselib.view.banner.XBanner.e */
    /* loaded from: classes.dex */
    public class C0901e extends PagerAdapter {

        /* renamed from: com.comod.baselib.view.banner.XBanner.e.a */
        /* loaded from: classes.dex */
        public class C0902a extends OnDoubleClickListener {

            /* renamed from: d */
            public final /* synthetic */ int f1658d;

            public C0902a(int i) {
                this.f1658d = i;
            }

            @Override // p067e.p130f.p131a.p136e.p137b.OnDoubleClickListener
            /* renamed from: a */
            public void mo9063a(View view) {
                AbstractC0899c cVar = XBanner.this.f1634e;
                XBanner xBanner = XBanner.this;
                cVar.mo9577a(xBanner, xBanner.f1641l.get(this.f1658d), view, this.f1658d);
            }
        }

        public C0901e() {
        }

        @Override // android.support.p001v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        @Override // android.support.p001v4.view.PagerAdapter
        public int getCount() {
            if (XBanner.this.f1644o) {
                return 1;
            }
            if (!XBanner.this.f1645p && !XBanner.this.f1621O) {
                return XBanner.this.getRealCount();
            }
            return Integer.MAX_VALUE;
        }

        @Override // android.support.p001v4.view.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // android.support.p001v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            if (XBanner.this.getRealCount() == 0) {
                return null;
            }
            int realCount = i % XBanner.this.getRealCount();
            View view = XBanner.this.f1642m == null ? (View) XBanner.this.f1643n.get(realCount) : (View) XBanner.this.f1642m.get(i % XBanner.this.f1642m.size());
            if (viewGroup.equals(view.getParent())) {
                viewGroup.removeView(view);
            }
            if (XBanner.this.f1634e != null && !XBanner.this.f1641l.isEmpty()) {
                view.setOnClickListener(new C0902a(realCount));
            }
            if (XBanner.this.f1612F != null && !XBanner.this.f1641l.isEmpty()) {
                AbstractC0900d dVar = XBanner.this.f1612F;
                XBanner xBanner = XBanner.this;
                dVar.mo9582a(xBanner, xBanner.f1641l.get(realCount), view, realCount);
            }
            viewGroup.addView(view);
            return view;
        }

        @Override // android.support.p001v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public XBanner(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f1645p) {
            if ((!this.f1644o) && (this.f1637h != null)) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    float rawX = motionEvent.getRawX();
                    int left = this.f1637h.getLeft();
                    if (rawX >= left && rawX < XBannerUtils.m9062a(getContext()) - left) {
                        m20088h();
                    }
                } else if (action == 1 || action == 3 || action == 4) {
                    m20090g();
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getBannerCurrentItem() {
        List<?> list;
        if (this.f1637h == null || (list = this.f1641l) == null || list.isEmpty()) {
            return -1;
        }
        return this.f1637h.getCurrentItem() % getRealCount();
    }

    public int getRealCount() {
        List<?> list = this.f1641l;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public XBannerViewPager getViewPager() {
        return this.f1637h;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        m20090g();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m20096d();
    }

    @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.f1633d;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        List<String> list;
        List<?> list2;
        this.f1631b = i;
        this.f1632c = f;
        if (this.f1654y == null || (list2 = this.f1641l) == null || list2.isEmpty() || !(this.f1641l.get(0) instanceof SimpleBannerInfo)) {
            if (!(this.f1654y == null || (list = this.f1610D) == null || list.isEmpty())) {
                if (f > 0.5d) {
                    TextView textView = this.f1654y;
                    List<String> list3 = this.f1610D;
                    textView.setText(list3.get((i + 1) % list3.size()));
                    this.f1654y.setAlpha(f);
                } else {
                    TextView textView2 = this.f1654y;
                    List<String> list4 = this.f1610D;
                    textView2.setText(list4.get(i % list4.size()));
                    this.f1654y.setAlpha(1.0f - f);
                }
            }
        } else if (f > 0.5d) {
            TextView textView3 = this.f1654y;
            List<?> list5 = this.f1641l;
            textView3.setText(((SimpleBannerInfo) list5.get((i + 1) % list5.size())).getXBannerTitle());
            this.f1654y.setAlpha(f);
        } else {
            TextView textView4 = this.f1654y;
            List<?> list6 = this.f1641l;
            textView4.setText(((SimpleBannerInfo) list6.get(i % list6.size())).getXBannerTitle());
            this.f1654y.setAlpha(1.0f - f);
        }
        if (this.f1633d != null && getRealCount() != 0) {
            this.f1633d.onPageScrolled(i % getRealCount(), f, i2);
        }
    }

    @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (getRealCount() != 0) {
            int realCount = i % getRealCount();
            m20107a(realCount);
            ViewPager.OnPageChangeListener onPageChangeListener = this.f1633d;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageSelected(realCount);
            }
        }
    }

    @Override // android.view.View
    public void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            m20090g();
        } else if (8 == i || 4 == i) {
            m20096d();
        }
    }

    public void setAllowUserScrollable(boolean z) {
        this.f1647r = z;
        XBannerViewPager xBannerViewPager = this.f1637h;
        if (xBannerViewPager != null) {
            xBannerViewPager.setIsAllowUserScroll(z);
        }
    }

    public void setAutoPalyTime(int i) {
        this.f1646q = i;
    }

    public void setAutoPlayAble(boolean z) {
        this.f1645p = z;
    }

    public void setBannerCurrentItem(int i) {
        if (!(this.f1637h == null || this.f1641l == null)) {
            if (i <= getRealCount() - 1) {
                if (this.f1645p || this.f1621O) {
                    int currentItem = this.f1637h.getCurrentItem();
                    int realCount = i - (currentItem % getRealCount());
                    if (realCount < 0) {
                        for (int i2 = -1; i2 >= realCount; i2--) {
                            this.f1637h.setCurrentItem(currentItem + i2, false);
                        }
                    } else if (realCount > 0) {
                        for (int i3 = 1; i3 <= realCount; i3++) {
                            this.f1637h.setCurrentItem(currentItem + i3, false);
                        }
                    }
                    if (this.f1645p) {
                        m20090g();
                        return;
                    }
                    return;
                }
                this.f1637h.setCurrentItem(i, false);
            }
        }
    }

    public void setBannerData(@LayoutRes int i, @NonNull List<? extends SimpleBannerInfo> list) {
        this.f1643n = new ArrayList();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f1643n.add(View.inflate(getContext(), i, null));
        }
        if (this.f1643n.isEmpty()) {
            this.f1645p = false;
            this.f1625S = false;
        }
        if (this.f1645p && this.f1643n.size() < 3) {
            this.f1642m = new ArrayList(this.f1643n);
            this.f1642m.add(View.inflate(getContext(), i, null));
            if (this.f1642m.size() == 2) {
                this.f1642m.add(View.inflate(getContext(), i, null));
            }
        }
        m20102a(this.f1643n, list);
    }

    public void setCustomPageTransformer(ViewPager.PageTransformer pageTransformer) {
        XBannerViewPager xBannerViewPager;
        if (pageTransformer != null && (xBannerViewPager = this.f1637h) != null) {
            xBannerViewPager.setPageTransformer(true, pageTransformer);
        }
    }

    @Deprecated
    public void setData(@LayoutRes int i, @NonNull List<?> list, List<String> list2) {
        this.f1643n = new ArrayList();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f1643n.add(View.inflate(getContext(), i, null));
        }
        if (this.f1643n.isEmpty()) {
            this.f1645p = false;
            this.f1625S = false;
        }
        if (this.f1645p && this.f1643n.size() < 3) {
            this.f1642m = new ArrayList(this.f1643n);
            this.f1642m.add(View.inflate(getContext(), i, null));
            if (this.f1642m.size() == 2) {
                this.f1642m.add(View.inflate(getContext(), i, null));
            }
        }
        m20101a(this.f1643n, list, list2);
    }

    public void setHandLoop(boolean z) {
        this.f1621O = z;
    }

    public void setIsClipChildrenMode(boolean z) {
        this.f1625S = z;
    }

    public void setOnItemClickListener(AbstractC0899c cVar) {
        this.f1634e = cVar;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f1633d = onPageChangeListener;
    }

    public void setPageChangeDuration(int i) {
        XBannerViewPager xBannerViewPager = this.f1637h;
        if (xBannerViewPager != null) {
            xBannerViewPager.setScrollDuration(i);
        }
    }

    public void setPageTransformer(Transformer transformer) {
        this.f1622P = transformer;
        XBannerViewPager xBannerViewPager = this.f1637h;
        if (xBannerViewPager != null && transformer != null) {
            xBannerViewPager.setPageTransformer(true, BasePageTransformer.m20109a(transformer));
        }
    }

    public void setPointContainerPosition(int i) {
        if (12 == i) {
            this.f1613G.addRule(12);
        } else if (10 == i) {
            this.f1613G.addRule(10);
        }
    }

    public void setPointPosition(int i) {
        if (1 == i) {
            this.f1653x.addRule(14);
        } else if (i == 0) {
            this.f1653x.addRule(9);
        } else if (2 == i) {
            this.f1653x.addRule(11);
        }
    }

    public void setPointsIsVisible(boolean z) {
        LinearLayout linearLayout = this.f1636g;
        if (linearLayout == null) {
            return;
        }
        if (z) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
        }
    }

    public void setShowIndicatorOnlyOne(boolean z) {
        this.f1617K = z;
    }

    public void setSlideScrollMode(int i) {
        this.f1648s = i;
        XBannerViewPager xBannerViewPager = this.f1637h;
        if (xBannerViewPager != null) {
            xBannerViewPager.setOverScrollMode(i);
        }
    }

    public void setViewPagerMargin(@Dimension int i) {
        this.f1628V = i;
        XBannerViewPager xBannerViewPager = this.f1637h;
        if (xBannerViewPager != null) {
            xBannerViewPager.setPageMargin(XBannerUtils.m9061a(getContext(), i));
        }
    }

    @Deprecated
    public void setmAdapter(AbstractC0900d dVar) {
        this.f1612F = dVar;
    }

    public XBanner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: a */
    public void m20104a(AbstractC0900d dVar) {
        this.f1612F = dVar;
    }

    /* renamed from: b */
    public final void m20100b() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        if (Build.VERSION.SDK_INT >= 16) {
            relativeLayout.setBackground(this.f1652w);
        } else {
            relativeLayout.setBackgroundDrawable(this.f1652w);
        }
        int i = this.f1640k;
        int i2 = this.f1639j;
        relativeLayout.setPadding(i, i2, i, i2);
        this.f1613G = new RelativeLayout.LayoutParams(-1, -2);
        this.f1613G.addRule(this.f1611E);
        if (this.f1625S) {
            RelativeLayout.LayoutParams layoutParams = this.f1613G;
            int i3 = this.f1626T;
            layoutParams.setMargins(i3, 0, i3, this.f1627U);
        }
        addView(relativeLayout, this.f1613G);
        this.f1653x = new RelativeLayout.LayoutParams(-2, -2);
        if (this.f1614H) {
            this.f1615I = new TextView(getContext());
//            this.f1615I.setId(R.id.xbanner_pointId);
            this.f1615I.setGravity(17);
            this.f1615I.setSingleLine(true);
            this.f1615I.setEllipsize(TextUtils.TruncateAt.END);
            this.f1615I.setTextColor(this.f1655z);
            this.f1615I.setTextSize(0, this.f1608B);
            this.f1615I.setVisibility(View.INVISIBLE);
            Drawable drawable = this.f1616J;
            if (drawable != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    this.f1615I.setBackground(drawable);
                } else {
                    this.f1615I.setBackgroundDrawable(drawable);
                }
            }
            relativeLayout.addView(this.f1615I, this.f1653x);
        } else {
            this.f1636g = new LinearLayout(getContext());
            this.f1636g.setOrientation(LinearLayout.HORIZONTAL);
//            this.f1636g.setId(R.id.xbanner_pointId);
            relativeLayout.addView(this.f1636g, this.f1653x);
        }
        LinearLayout linearLayout = this.f1636g;
        if (linearLayout != null) {
            if (this.f1607A) {
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.GONE);
            }
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(15);
        if (this.f1609C) {
            this.f1654y = new TextView(getContext());
            this.f1654y.setGravity(16);
            this.f1654y.setSingleLine(true);
            if (this.f1619M) {
                this.f1654y.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                this.f1654y.setMarqueeRepeatLimit(3);
                this.f1654y.setSelected(true);
            } else {
                this.f1654y.setEllipsize(TextUtils.TruncateAt.END);
            }
            this.f1654y.setTextColor(this.f1655z);
            this.f1654y.setTextSize(0, this.f1608B);
            relativeLayout.addView(this.f1654y, layoutParams2);
        }
        int i4 = this.f1649t;
        if (1 == i4) {
            this.f1653x.addRule(14);
//            layoutParams2.addRule(0, R.id.xbanner_pointId);
        } else if (i4 == 0) {
            this.f1653x.addRule(9);
            this.f1654y.setGravity(21);
//            layoutParams2.addRule(1, R.id.xbanner_pointId);
        } else if (2 == i4) {
            this.f1653x.addRule(11);
//            layoutParams2.addRule(0, R.id.xbanner_pointId);
        }
        m20092f();
    }

    /* renamed from: c */
    public final void m20098c() {
        XBannerViewPager xBannerViewPager = this.f1637h;
        if (xBannerViewPager != null && equals(xBannerViewPager.getParent())) {
            removeView(this.f1637h);
            this.f1637h = null;
        }
        this.f1637h = new XBannerViewPager(getContext());
        this.f1637h.setAdapter(new C0901e());
        this.f1637h.addOnPageChangeListener(this);
        this.f1637h.setOverScrollMode(this.f1648s);
        this.f1637h.setIsAllowUserScroll(this.f1647r);
        setPageTransformer(this.f1622P);
        setPageChangeDuration(this.f1618L);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, this.f1630a0);
        if (this.f1625S) {
            this.f1637h.setClipChildren(false);
            Object obj = this.f1641l.get(0);
            if (obj instanceof SimpleBannerInfo) {
                if (!(((SimpleBannerInfo) obj).getXBannerUrl() instanceof Integer) && this.f1641l.size() > 4) {
                    this.f1637h.setOffscreenPageLimit(3);
                }
            } else if (!(obj instanceof Integer) && this.f1641l.size() > 4) {
                this.f1637h.setOffscreenPageLimit(3);
            }
            this.f1637h.setPageMargin(this.f1628V);
            setClipChildren(false);
            int i = this.f1626T;
            int i2 = this.f1627U;
            layoutParams.setMargins(i, i2, i, this.f1630a0 + i2);
        }
        addView(this.f1637h, 0, layoutParams);
        if (this.f1644o || !this.f1645p || getRealCount() == 0) {
            if (this.f1621O && getRealCount() != 0) {
                this.f1637h.setCurrentItem(1073741823 - (1073741823 % getRealCount()), false);
            }
            m20107a(0);
            return;
        }
        this.f1637h.setAutoPlayDelegate(this);
        this.f1637h.setCurrentItem(1073741823 - (1073741823 % getRealCount()), false);
        m20090g();
    }

    /* renamed from: d */
    public final void m20096d() {
        m20088h();
        if (!this.f1620N && this.f1645p && this.f1637h != null && getRealCount() > 0 && this.f1632c != 0.0f) {
            XBannerViewPager xBannerViewPager = this.f1637h;
            xBannerViewPager.setCurrentItem(xBannerViewPager.getCurrentItem() - 1, false);
            XBannerViewPager xBannerViewPager2 = this.f1637h;
            xBannerViewPager2.setCurrentItem(xBannerViewPager2.getCurrentItem() + 1, false);
        }
        this.f1620N = false;
    }

    /* renamed from: e */
    public final void m20094e() {
        ImageView imageView = this.f1624R;
        if (imageView != null && equals(imageView.getParent())) {
            removeView(this.f1624R);
            this.f1624R = null;
        }
    }

    /* renamed from: f */
    public final void m20092f() {
        if (this.f1623Q != -1 && this.f1624R == null) {
            this.f1624R = new ImageView(getContext());
            this.f1624R.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f1624R.setImageResource(this.f1623Q);
            addView(this.f1624R, new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    /* renamed from: g */
    public void m20090g() {
        m20088h();
        if (this.f1645p) {
            postDelayed(this.f1635f, this.f1646q);
        }
    }

    /* renamed from: h */
    public void m20088h() {
        if (this.f1645p) {
            removeCallbacks(this.f1635f);
        }
    }

    public XBanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1644o = false;
        this.f1645p = true;
        this.f1646q = 5000;
        this.f1647r = true;
        this.f1648s = 0;
        this.f1649t = 1;
        this.f1607A = true;
        this.f1611E = 12;
        this.f1614H = false;
        this.f1617K = false;
        this.f1618L = 1000;
        this.f1619M = false;
        this.f1620N = true;
        this.f1621O = false;
        this.f1623Q = -1;
        this.f1630a0 = 0;
        m20106a(context);
        m20105a(context, attributeSet);
        m20100b();
    }

    /* renamed from: a */
    public final void m20106a(Context context) {
        this.f1635f = new RunnableC0898b(this);
        this.f1638i = XBannerUtils.m9061a(context, 3.0f);
        this.f1639j = XBannerUtils.m9061a(context, 6.0f);
        this.f1640k = XBannerUtils.m9061a(context, 10.0f);
        this.f1626T = XBannerUtils.m9061a(context, 30.0f);
        this.f1627U = XBannerUtils.m9061a(context, 10.0f);
        this.f1628V = XBannerUtils.m9061a(context, 10.0f);
        this.f1608B = XBannerUtils.m9060b(context, 10.0f);
        this.f1622P = Transformer.Default;
        this.f1655z = -1;
        this.f1652w = new ColorDrawable(Color.parseColor("#44aaaaaa"));
    }

    /* renamed from: a */
    public final void m20105a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XBanner);
        if (obtainStyledAttributes != null) {
            this.f1645p = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isAutoPlay, true);
            this.f1621O = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isHandLoop, false);
            this.f1619M = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isTipsMarquee, false);
            this.f1646q = obtainStyledAttributes.getInteger(R.styleable.XBanner_AutoPlayTime, 5000);
            this.f1607A = obtainStyledAttributes.getBoolean(R.styleable.XBanner_pointsVisibility, true);
            this.f1649t = obtainStyledAttributes.getInt(R.styleable.XBanner_pointsPosition, 1);
            this.f1640k = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_pointContainerLeftRightPadding, this.f1640k);
            this.f1638i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_pointLeftRightPadding, this.f1638i);
            this.f1639j = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_pointTopBottomPadding, this.f1639j);
            this.f1611E = obtainStyledAttributes.getInt(R.styleable.XBanner_pointContainerPosition, 12);
            this.f1652w = obtainStyledAttributes.getDrawable(R.styleable.XBanner_pointsContainerBackground);
            this.f1650u = obtainStyledAttributes.getResourceId(R.styleable.XBanner_pointNormal, R.drawable.shape_point_normal);
            this.f1651v = obtainStyledAttributes.getResourceId(R.styleable.XBanner_pointSelect, R.drawable.shape_point_select);
            this.f1655z = obtainStyledAttributes.getColor(R.styleable.XBanner_tipTextColor, this.f1655z);
            this.f1608B = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_tipTextSize, this.f1608B);
            this.f1614H = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isShowNumberIndicator, this.f1614H);
            this.f1616J = obtainStyledAttributes.getDrawable(R.styleable.XBanner_numberIndicatorBacgroud);
            this.f1617K = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isShowIndicatorOnlyOne, this.f1617K);
            this.f1618L = obtainStyledAttributes.getInt(R.styleable.XBanner_pageChangeDuration, this.f1618L);
            this.f1623Q = obtainStyledAttributes.getResourceId(R.styleable.XBanner_placeholderDrawable, this.f1623Q);
            this.f1625S = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isClipChildrenMode, false);
            this.f1626T = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_clipChildrenLeftRightMargin, this.f1626T);
            this.f1627U = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_clipChildrenTopBottomMargin, this.f1627U);
            this.f1628V = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_viewpagerMargin, this.f1628V);
            this.f1629W = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isClipChildrenModeLessThree, false);
            this.f1609C = obtainStyledAttributes.getBoolean(R.styleable.XBanner_isShowTips, false);
            this.f1630a0 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.XBanner_bannerBottomMargin, this.f1630a0);
            obtainStyledAttributes.recycle();
        }
        if (this.f1625S) {
            this.f1622P = Transformer.Scale;
        }
    }

    public void setBannerData(@NonNull List<? extends SimpleBannerInfo> list) {
        setBannerData(R.layout.xbanner_item_image_corner_5, list);
    }

    @Deprecated
    public void setData(@NonNull List<?> list, List<String> list2) {
        setData(R.layout.xbanner_item_image_corner_5, list, list2);
    }

    @Deprecated
    /* renamed from: a */
    public final void m20101a(@NonNull List<View> list, @NonNull List<?> list2, List<String> list3) {
        if (this.f1645p && list.size() < 3 && this.f1642m == null) {
            this.f1645p = false;
        }
        if (!this.f1629W && list.size() < 3) {
            this.f1625S = false;
        }
        this.f1641l = list2;
        this.f1610D = list3;
        this.f1643n = list;
        boolean z = true;
        if (list2.size() > 1) {
            z = false;
        }
        this.f1644o = z;
        m20108a();
        m20098c();
        m20094e();
        if (!list2.isEmpty()) {
            m20094e();
        } else {
            m20092f();
        }
    }

    /* renamed from: a */
    public final void m20102a(@NonNull List<View> list, @NonNull List<? extends SimpleBannerInfo> list2) {
        if (this.f1645p && list.size() < 3 && this.f1642m == null) {
            this.f1645p = false;
        }
        if (!this.f1629W && list.size() < 3) {
            this.f1625S = false;
        }
        this.f1641l = list2;
        this.f1643n = list;
        boolean z = true;
        if (list2.size() > 1) {
            z = false;
        }
        this.f1644o = z;
        m20108a();
        m20098c();
        m20094e();
        if (!list2.isEmpty()) {
            m20094e();
        } else {
            m20092f();
        }
    }

    @Override // com.comod.baselib.view.banner.XBannerViewPager.AbstractC0903a
    /* renamed from: a */
    public void mo20085a(float f) {
        if (this.f1631b < this.f1637h.getCurrentItem()) {
            if (f > 400.0f || (this.f1632c < 0.7f && f > -400.0f)) {
                this.f1637h.setBannerCurrentItemInternal(this.f1631b, true);
            } else {
                this.f1637h.setBannerCurrentItemInternal(this.f1631b + 1, true);
            }
        } else if (f < -400.0f || (this.f1632c > 0.3f && f < 400.0f)) {
            this.f1637h.setBannerCurrentItemInternal(this.f1631b + 1, true);
        } else {
            this.f1637h.setBannerCurrentItemInternal(this.f1631b, true);
        }
    }

    /* renamed from: a */
    public final void m20108a() {
        LinearLayout linearLayout = this.f1636g;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            if (getRealCount() > 0 && (this.f1617K || !this.f1644o)) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 16;
                int i = this.f1638i;
                int i2 = this.f1639j;
                layoutParams.setMargins(i, i2, i, i2);
                for (int i3 = 0; i3 < getRealCount(); i3++) {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(layoutParams);
                    int i4 = this.f1650u;
                    if (!(i4 == 0 || this.f1651v == 0)) {
                        imageView.setImageResource(i4);
                    }
                    this.f1636g.addView(imageView);
                }
            }
        }
        if (this.f1615I == null) {
            return;
        }
        if (getRealCount() <= 0 || (!this.f1617K && this.f1644o)) {
            this.f1615I.setVisibility(View.GONE);
        } else {
            this.f1615I.setVisibility(View.VISIBLE);
        }
    }

    /* renamed from: a */
    public final void m20107a(int i) {
        List<String> list;
        List<?> list2;
        if (((this.f1636g != null) && (this.f1641l != null)) && getRealCount() > 1) {
            for (int i2 = 0; i2 < this.f1636g.getChildCount(); i2++) {
                if (i2 == i) {
                    ((ImageView) this.f1636g.getChildAt(i2)).setImageResource(this.f1651v);
                } else {
                    ((ImageView) this.f1636g.getChildAt(i2)).setImageResource(this.f1650u);
                }
                this.f1636g.getChildAt(i2).requestLayout();
            }
        }
        if (this.f1654y != null && (list2 = this.f1641l) != null && !list2.isEmpty() && (this.f1641l.get(0) instanceof SimpleBannerInfo)) {
            this.f1654y.setText(((SimpleBannerInfo) this.f1641l.get(i)).getXBannerTitle());
        } else if (!(this.f1654y == null || (list = this.f1610D) == null || list.isEmpty())) {
            this.f1654y.setText(this.f1610D.get(i));
        }
        if (!(this.f1615I == null || this.f1643n == null)) {
            if (this.f1617K || !this.f1644o) {
                TextView textView = this.f1615I;
                textView.setText(String.valueOf((i + 1) + "/" + this.f1643n.size()));
            }
        }
    }




}

