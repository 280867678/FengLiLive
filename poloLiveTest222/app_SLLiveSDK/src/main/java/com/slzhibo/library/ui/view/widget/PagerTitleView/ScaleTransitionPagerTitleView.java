package com.slzhibo.library.ui.view.widget.PagerTitleView;



import android.content.Context;




/* renamed from: com.slzhibo.library.ui.view.widget.PagerTitleView.ScaleTransitionPagerTitleView */
/* loaded from: classes6.dex */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
    private float mMinScale = 0.85f;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onEnter(int i, int i2, float f, boolean z) {
        super.onEnter(i, i2, f, z);
        float f2 = this.mMinScale;
        setScaleX(f2 + ((1.0f - f2) * f));
        float f3 = this.mMinScale;
        setScaleY(f3 + ((1.0f - f3) * f));
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onLeave(int i, int i2, float f, boolean z) {
        super.onLeave(i, i2, f, z);
        setScaleX(((this.mMinScale - 1.0f) * f) + 1.0f);
        setScaleY(((this.mMinScale - 1.0f) * f) + 1.0f);
    }

    public float getMinScale() {
        return this.mMinScale;
    }

    public void setMinScale(float f) {
        this.mMinScale = f;
    }
}

