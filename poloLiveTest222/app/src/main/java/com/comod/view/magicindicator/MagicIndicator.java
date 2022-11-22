package com.comod.view.magicindicator;

//package com.comod.view.magicindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
//import p067e.p130f.p148b.p149a.p150d.IPagerNavigator;

/* loaded from: classes.dex */
public class MagicIndicator extends FrameLayout {

    /* renamed from: b */
    public IPagerNavigator f1793b;

    public MagicIndicator(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m19996a(int i, float f, int i2) {
        IPagerNavigator aVar = this.f1793b;
        if (aVar != null) {
            aVar.onPageScrolled(i, f, i2);
        }
    }

    /* renamed from: b */
    public void m19995b(int i) {
        IPagerNavigator aVar = this.f1793b;
        if (aVar != null) {
            aVar.onPageSelected(i);
        }
    }

    public IPagerNavigator getNavigator() {
        return this.f1793b;
    }

    public void setNavigator(IPagerNavigator aVar) {
        IPagerNavigator aVar2 = this.f1793b;
        if (aVar2 != aVar) {
            if (aVar2 != null) {
                aVar2.mo8990b();
            }
            this.f1793b = aVar;
            removeAllViews();
            if (this.f1793b instanceof View) {
                addView((View) this.f1793b, new FrameLayout.LayoutParams(-1, -1));
                this.f1793b.mo8991a();
            }
        }
    }

    public MagicIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public void m19997a(int i) {
        IPagerNavigator aVar = this.f1793b;
        if (aVar != null) {
            aVar.onPageScrollStateChanged(i);
        }
    }
}

