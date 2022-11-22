package com.slzhibo.library.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomVerticalViewPager extends VerticalViewPager {
    private final boolean disallowIntercept = false;
    private boolean isScroll = true;

    public CustomVerticalViewPager(Context context) {
        super(context);
    }

    public CustomVerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.slzhibo.library.ui.view.widget.VerticalViewPager
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isScroll && super.onTouchEvent(motionEvent);
    }

    @Override // com.slzhibo.library.ui.view.widget.VerticalViewPager
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.disallowIntercept && this.isScroll && super.onInterceptTouchEvent(motionEvent);
    }

    public void setScroll(boolean z) {
        this.isScroll = z;
    }
}
