package com.comod.view.magicindicator.buildins.commonnavigator.titles;

//package com.comod.view.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
//import p067e.p130f.p148b.p149a.p151e.p152c.p153a.IMeasurablePagerTitleView;

/* loaded from: classes.dex */
public class CommonPagerTitleView extends FrameLayout implements IMeasurablePagerTitleView {

    /* renamed from: b */
    public AbstractC0922b f1906b;

    /* renamed from: c */
    public AbstractC0921a f1907c;

    /* renamed from: com.comod.view.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView$a */
    /* loaded from: classes.dex */
    public interface AbstractC0921a {
        int getContentBottom();

        int getContentLeft();

        int getContentRight();

        int getContentTop();
    }

    /* renamed from: com.comod.view.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView$b */
    /* loaded from: classes.dex */
    public interface AbstractC0922b {
        /* renamed from: a */
        void mo19970a(int i, int i2);

        /* renamed from: a */
        void mo19969a(int i, int i2, float f, boolean z);

        /* renamed from: b */
        void mo19968b(int i, int i2);

        /* renamed from: b */
        void mo19967b(int i, int i2, float f, boolean z);
    }

    public CommonPagerTitleView(Context context) {
        super(context);
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerTitleView
    /* renamed from: a */
    public void mo8979a(int i, int i2) {
        AbstractC0922b bVar = this.f1906b;
        if (bVar != null) {
            bVar.mo19970a(i, i2);
        }
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerTitleView
    /* renamed from: b */
    public void mo8977b(int i, int i2) {
        AbstractC0922b bVar = this.f1906b;
        if (bVar != null) {
            bVar.mo19968b(i, i2);
        }
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IMeasurablePagerTitleView
    public int getContentBottom() {
        AbstractC0921a aVar = this.f1907c;
        if (aVar != null) {
            return aVar.getContentBottom();
        }
        return getBottom();
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IMeasurablePagerTitleView
    public int getContentLeft() {
        AbstractC0921a aVar = this.f1907c;
        if (aVar != null) {
            return aVar.getContentLeft();
        }
        return getLeft();
    }

    public AbstractC0921a getContentPositionDataProvider() {
        return this.f1907c;
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IMeasurablePagerTitleView
    public int getContentRight() {
        AbstractC0921a aVar = this.f1907c;
        if (aVar != null) {
            return aVar.getContentRight();
        }
        return getRight();
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IMeasurablePagerTitleView
    public int getContentTop() {
        AbstractC0921a aVar = this.f1907c;
        if (aVar != null) {
            return aVar.getContentTop();
        }
        return getTop();
    }

    public AbstractC0922b getOnPagerTitleChangeListener() {
        return this.f1906b;
    }

    public void setContentPositionDataProvider(AbstractC0921a aVar) {
        this.f1907c = aVar;
    }

    public void setContentView(View view) {
        setContentView(view, null);
    }

    public void setOnPagerTitleChangeListener(AbstractC0922b bVar) {
        this.f1906b = bVar;
    }

    public void setContentView(View view, FrameLayout.LayoutParams layoutParams) {
        removeAllViews();
        if (view != null) {
            if (layoutParams == null) {
                layoutParams = new FrameLayout.LayoutParams(-1, -1);
            }
            addView(view, layoutParams);
        }
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerTitleView
    /* renamed from: a */
    public void mo8978a(int i, int i2, float f, boolean z) {
        AbstractC0922b bVar = this.f1906b;
        if (bVar != null) {
            bVar.mo19969a(i, i2, f, z);
        }
    }

    @Override // p067e.p130f.p148b.p149a.p151e.p152c.p153a.IPagerTitleView
    /* renamed from: b */
    public void mo8976b(int i, int i2, float f, boolean z) {
        AbstractC0922b bVar = this.f1906b;
        if (bVar != null) {
            bVar.mo19967b(i, i2, f, z);
        }
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null), null);
    }
}

