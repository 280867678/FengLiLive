package com.slzhibo.library.ui.view.widget.PagerTitleView;



/* renamed from: com.slzhibo.library.ui.view.widget.magicindicator.buildins.ArgbEvaluatorHolder */
/* loaded from: classes6.dex */
public class ArgbEvaluatorHolder {
    public static int eval(float f, int i, int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        int i6 = i & 255;
        return ((i3 + ((int) ((((i2 >> 24) & 255) - i3) * f))) << 24) | ((i4 + ((int) ((((i2 >> 16) & 255) - i4) * f))) << 16) | ((i5 + ((int) ((((i2 >> 8) & 255) - i5) * f))) << 8) | (i6 + ((int) (f * ((i2 & 255) - i6))));
    }
}

