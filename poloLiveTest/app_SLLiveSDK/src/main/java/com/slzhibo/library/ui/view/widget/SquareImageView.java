package com.slzhibo.library.ui.view.widget;



import android.content.Context;
import android.graphics.Canvas;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/* renamed from: com.slzhibo.library.ui.view.widget.SquareImageView */
/* loaded from: classes2.dex */
public class SquareImageView extends AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
