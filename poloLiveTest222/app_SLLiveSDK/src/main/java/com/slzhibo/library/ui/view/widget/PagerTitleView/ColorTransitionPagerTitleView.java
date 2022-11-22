package com.slzhibo.library.ui.view.widget.PagerTitleView;



import android.content.Context;


/* renamed from: com.slzhibo.library.ui.view.widget.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView */
/* loaded from: classes6.dex */
public class ColorTransitionPagerTitleView extends SimplePagerTitleView {
    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onDeselected(int i, int i2) {
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onSelected(int i, int i2) {
    }

    public ColorTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onLeave(int i, int i2, float f, boolean z) {
        setTextColor(ArgbEvaluatorHolder.eval(f, this.mSelectedColor, this.mNormalColor));
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView, com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onEnter(int i, int i2, float f, boolean z) {
        setTextColor(ArgbEvaluatorHolder.eval(f, this.mNormalColor, this.mSelectedColor));
    }
}

