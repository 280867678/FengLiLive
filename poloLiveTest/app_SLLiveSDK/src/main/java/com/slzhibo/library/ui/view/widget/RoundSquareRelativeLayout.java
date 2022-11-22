package com.slzhibo.library.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;

public class RoundSquareRelativeLayout extends RoundRelativeLayout {
    public RoundSquareRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundSquareRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundSquareRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.slzhibo.library.ui.view.widget.RoundRelativeLayout
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }
}
