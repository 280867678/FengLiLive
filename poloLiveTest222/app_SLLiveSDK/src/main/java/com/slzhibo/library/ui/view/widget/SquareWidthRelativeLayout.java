package com.slzhibo.library.ui.view.widget;



import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* renamed from: com.slzhibo.library.ui.view.widget.SquareWidthRelativeLayout */
/* loaded from: classes2.dex */
public class SquareWidthRelativeLayout extends RelativeLayout {
    public SquareWidthRelativeLayout(Context context) {
        this(context, null);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    public SquareWidthRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SquareWidthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
