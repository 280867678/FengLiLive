package com.comod.view.magicindicator.buildins.commonnavigator;

//package com.comod.view.magicindicator.buildins.commonnavigator;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
//import com.comod.view.magicindicator.R$id;
//import com.comod.view.magicindicator.R$layout;
import com.blmvl.blvl.R;
import com.comod.view.magicindicator.IPagerNavigator;
import com.comod.view.magicindicator.buildins.commonnavigator.titles.IMeasurablePagerTitleView;
import com.comod.view.magicindicator.buildins.commonnavigator.titles.IPagerTitleView;

import java.util.ArrayList;
import java.util.List;
//import p067e.p130f.p148b.p149a.NavigatorHelper;
//import p067e.p130f.p148b.p149a.p150d.IPagerNavigator;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.CommonNavigatorAdapter;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.IMeasurablePagerTitleView;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerIndicator;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerTitleView;
//import p067e.p130f.p148b.p149a.p151e.p152c.p154b.PositionData;

/* loaded from: classes.dex */
public class CommonNavigator extends FrameLayout implements IPagerNavigator, NavigatorHelper.AbstractC4333a {

    /* renamed from: b */
    public HorizontalScrollView f1811b;

    /* renamed from: c */
    public LinearLayout f1812c;

    /* renamed from: d */
    public LinearLayout f1813d;

    /* renamed from: e */
    public IPagerIndicator f1814e;

    /* renamed from: f */
    public CommonNavigatorAdapter f1815f;

    /* renamed from: h */
    public boolean f1817h;

    /* renamed from: i */
    public boolean f1818i;

    /* renamed from: m */
    public int f1822m;

    /* renamed from: n */
    public int f1823n;

    /* renamed from: o */
    public boolean f1824o;

    /* renamed from: j */
    public float f1819j = 0.5f;

    /* renamed from: k */
    public boolean f1820k = true;

    /* renamed from: l */
    public boolean f1821l = true;

    /* renamed from: p */
    public boolean f1825p = true;

    /* renamed from: q */
    public List<PositionData> f1826q = new ArrayList();

    /* renamed from: r */
    public DataSetObserver f1827r = new C0920a();

    /* renamed from: g */
    public NavigatorHelper f1816g = new NavigatorHelper();

    /* renamed from: com.comod.view.magicindicator.buildins.commonnavigator.CommonNavigator$a */
    /* loaded from: classes.dex */
    public class C0920a extends DataSetObserver {
        public C0920a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            CommonNavigator.this.f1816g.m8997e(CommonNavigator.this.f1815f.mo8987a());
            CommonNavigator.this.m19985c();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
        }
    }

    public CommonNavigator(Context context) {
        super(context);
        this.f1816g.setNavigatorScrollListener(this);
    }

    @Override // p067e.p130f.p148b.p149a.p150d.IPagerNavigator
    /* renamed from: b */
    public void mo8990b() {
    }

    /* renamed from: d */
    public final void m19983d() {
        LinearLayout.LayoutParams layoutParams;
        int c = this.f1816g.m9000c();
        for (int i = 0; i < c; i++) {
            IPagerTitleView a = this.f1815f.mo8985a(getContext(), i);
            if (a instanceof View) {
                View view = (View) a;
                if (this.f1817h) {
                    layoutParams = new LinearLayout.LayoutParams(0, -1);
                    layoutParams.weight = this.f1815f.mo8982b(getContext(), i);
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                }
                this.f1812c.addView(view, layoutParams);
            }
        }
        CommonNavigatorAdapter aVar = this.f1815f;
        if (aVar != null) {
            this.f1814e = aVar.mo8986a(getContext());
            if (this.f1814e instanceof View) {
                this.f1813d.addView((View) this.f1814e, new FrameLayout.LayoutParams(-1, -1));
            }
        }
    }

    /* renamed from: e */
    public final void m19982e() {
        this.f1826q.clear();
        int c = this.f1816g.m9000c();
        for (int i = 0; i < c; i++) {
            PositionData aVar = new PositionData();
            View childAt = this.f1812c.getChildAt(i);
            if (childAt != null) {
                aVar.f13256a = childAt.getLeft();
                aVar.f13257b = childAt.getTop();
                aVar.f13258c = childAt.getRight();
                aVar.f13259d = childAt.getBottom();
                if (childAt instanceof IMeasurablePagerTitleView) {
                    IMeasurablePagerTitleView bVar = (IMeasurablePagerTitleView) childAt;
                    aVar.f13260e = bVar.getContentLeft();
                    aVar.f13261f = bVar.getContentTop();
                    aVar.f13262g = bVar.getContentRight();
                    aVar.f13263h = bVar.getContentBottom();
                } else {
                    aVar.f13260e = aVar.f13256a;
                    aVar.f13261f = aVar.f13257b;
                    aVar.f13262g = aVar.f13258c;
                    aVar.f13263h = aVar.f13259d;
                }
            }
            this.f1826q.add(aVar);
        }
    }

    public CommonNavigatorAdapter getAdapter() {
        return this.f1815f;
    }

    public int getLeftPadding() {
        return this.f1823n;
    }

    public IPagerIndicator getPagerIndicator() {
        return this.f1814e;
    }

    public int getRightPadding() {
        return this.f1822m;
    }

    public float getScrollPivotX() {
        return this.f1819j;
    }

    public LinearLayout getTitleContainer() {
        return this.f1812c;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f1815f != null) {
            m19982e();
            IPagerIndicator cVar = this.f1814e;
            if (cVar != null) {
                cVar.mo8980a(this.f1826q);
            }
            if (this.f1825p && this.f1816g.m9003b() == 0) {
                onPageSelected(this.f1816g.m9008a());
                onPageScrolled(this.f1816g.m9008a(), 0.0f, 0);
            }
        }
    }

    @Override // p067e.p130f.p148b.p149a.p150d.IPagerNavigator
    public void onPageScrollStateChanged(int i) {
        if (this.f1815f != null) {
            this.f1816g.m8999c(i);
            IPagerIndicator cVar = this.f1814e;
            if (cVar != null) {
                cVar.onPageScrollStateChanged(i);
            }
        }
    }

    @Override // p067e.p130f.p148b.p149a.p150d.IPagerNavigator
    public void onPageScrolled(int i, float f, int i2) {
        if (this.f1815f != null) {
            this.f1816g.m9006a(i, f, i2);
            IPagerIndicator cVar = this.f1814e;
            if (cVar != null) {
                cVar.onPageScrolled(i, f, i2);
            }
            if (this.f1811b != null && this.f1826q.size() > 0 && i >= 0 && i < this.f1826q.size() && this.f1821l) {
                int min = Math.min(this.f1826q.size() - 1, i);
                int min2 = Math.min(this.f1826q.size() - 1, i + 1);
                float a = this.f1826q.get(min).m8975a() - (this.f1811b.getWidth() * this.f1819j);
                this.f1811b.scrollTo((int) (a + (((this.f1826q.get(min2).m8975a() - (this.f1811b.getWidth() * this.f1819j)) - a) * f)), 0);
            }
        }
    }

    @Override // p067e.p130f.p148b.p149a.p150d.IPagerNavigator
    public void onPageSelected(int i) {
        if (this.f1815f != null) {
            this.f1816g.m8998d(i);
            IPagerIndicator cVar = this.f1814e;
            if (cVar != null) {
                cVar.onPageSelected(i);
            }
        }
    }

    public void setAdapter(CommonNavigatorAdapter aVar) {
        CommonNavigatorAdapter aVar2 = this.f1815f;
        if (aVar2 != aVar) {
            if (aVar2 != null) {
                aVar2.m8981b(this.f1827r);
            }
            this.f1815f = aVar;
            CommonNavigatorAdapter aVar3 = this.f1815f;
            if (aVar3 != null) {
                aVar3.m8984a(this.f1827r);
                this.f1816g.m8997e(this.f1815f.mo8987a());
                if (this.f1812c != null) {
                    this.f1815f.m8983b();
                    return;
                }
                return;
            }
            this.f1816g.m8997e(0);
            m19985c();
        }
    }

    public void setAdjustMode(boolean z) {
        this.f1817h = z;
    }

    public void setEnablePivotScroll(boolean z) {
        this.f1818i = z;
    }

    public void setFollowTouch(boolean z) {
        this.f1821l = z;
    }

    public void setIndicatorOnTop(boolean z) {
        this.f1824o = z;
    }

    public void setLeftPadding(int i) {
        this.f1823n = i;
    }

    public void setReselectWhenLayout(boolean z) {
        this.f1825p = z;
    }

    public void setRightPadding(int i) {
        this.f1822m = i;
    }

    public void setScrollPivotX(float f) {
        this.f1819j = f;
    }

    public void setSkimOver(boolean z) {
        this.f1816g.m9004a(z);
    }

    public void setSmoothScroll(boolean z) {
        this.f1820k = z;
    }

    @Override // p067e.p130f.p148b.p149a.p150d.IPagerNavigator
    /* renamed from: a */
    public void mo8991a() {
        m19985c();
    }

    @Override // p067e.p130f.p148b.p149a.NavigatorHelper.AbstractC4333a
    /* renamed from: b */
    public void mo8993b(int i, int i2, float f, boolean z) {
        LinearLayout linearLayout = this.f1812c;
        if (linearLayout != null) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).mo8976b(i, i2, f, z);
            }
        }
    }

    /* renamed from: c */
    public final void m19985c() {
        View view;
        removeAllViews();
        if (this.f1817h) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout_no_scroll, this);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout, this);
        }
        this.f1811b = view.findViewById(R.id.scroll_view);
        this.f1812c = view.findViewById(R.id.title_container);
        this.f1812c.setPadding(this.f1823n, 0, this.f1822m, 0);
        this.f1813d = view.findViewById(R.id.indicator_container);
        if (this.f1824o) {
            this.f1813d.getParent().bringChildToFront(this.f1813d);
        }
        m19983d();
    }

    @Override // p067e.p130f.p148b.p149a.NavigatorHelper.AbstractC4333a
    /* renamed from: a */
    public void mo8995a(int i, int i2, float f, boolean z) {
        LinearLayout linearLayout = this.f1812c;
        if (linearLayout != null) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).mo8978a(i, i2, f, z);
            }
        }
    }

    @Override // p067e.p130f.p148b.p149a.NavigatorHelper.AbstractC4333a
    /* renamed from: b */
    public void mo8994b(int i, int i2) {
        LinearLayout linearLayout = this.f1812c;
        if (linearLayout != null) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).mo8977b(i, i2);
            }
            if (!this.f1817h && !this.f1821l && this.f1811b != null && this.f1826q.size() > 0) {
                PositionData aVar = this.f1826q.get(Math.min(this.f1826q.size() - 1, i));
                if (this.f1818i) {
                    float a = aVar.m8975a() - (this.f1811b.getWidth() * this.f1819j);
                    if (this.f1820k) {
                        this.f1811b.smoothScrollTo((int) a, 0);
                    } else {
                        this.f1811b.scrollTo((int) a, 0);
                    }
                } else {
                    int scrollX = this.f1811b.getScrollX();
                    int i3 = aVar.f13256a;
                    if (scrollX <= i3) {
                        int scrollX2 = this.f1811b.getScrollX() + getWidth();
                        int i4 = aVar.f13258c;
                        if (scrollX2 >= i4) {
                            return;
                        }
                        if (this.f1820k) {
                            this.f1811b.smoothScrollTo(i4 - getWidth(), 0);
                        } else {
                            this.f1811b.scrollTo(i4 - getWidth(), 0);
                        }
                    } else if (this.f1820k) {
                        this.f1811b.smoothScrollTo(i3, 0);
                    } else {
                        this.f1811b.scrollTo(i3, 0);
                    }
                }
            }
        }
    }

    @Override // p067e.p130f.p148b.p149a.NavigatorHelper.AbstractC4333a
    /* renamed from: a */
    public void mo8996a(int i, int i2) {
        LinearLayout linearLayout = this.f1812c;
        if (linearLayout != null) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).mo8979a(i, i2);
            }
        }
    }
}

