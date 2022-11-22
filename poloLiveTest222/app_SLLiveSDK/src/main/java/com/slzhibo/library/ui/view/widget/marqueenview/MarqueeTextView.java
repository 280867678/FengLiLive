package com.slzhibo.library.ui.view.widget.marqueenview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import androidx.appcompat.widget.AppCompatTextView;

import com.slzhibo.library.R;

public class MarqueeTextView extends AppCompatTextView {
    private int distance;
    private int duration;
    private int mFirstScrollDelay;
    private boolean mPaused;
    private int mRollingInterval;
    private int mScrollMode;
    private Scroller mScroller;
    private int mXPaused;
    private int status;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mXPaused = 0;
        this.mPaused = true;
        this.status = 0;
        initView(context, attributeSet, i);
    }

    private void initView(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MarqueeTextView);
        this.mRollingInterval = obtainStyledAttributes.getInt(R.styleable.MarqueeTextView_scroll_interval, 3000);
        this.mScrollMode = obtainStyledAttributes.getInt(R.styleable.MarqueeTextView_scroll_mode, 100);
        this.mFirstScrollDelay = obtainStyledAttributes.getInt(R.styleable.MarqueeTextView_scroll_first_delay, 1000);
        obtainStyledAttributes.recycle();
        setSingleLine();
        setEllipsize(null);
    }

    public void startScroll() {
        this.mPaused = true;
        int calculateScrollingLen = calculateScrollingLen();
        if (calculateScrollingLen < (getWidth() - getPaddingLeft()) - getPaddingRight()) {
            Scroller scroller = this.mScroller;
            if (scroller != null) {
                scroller.startScroll(0, 0, (int) ((((float) calculateScrollingLen) / 2.0f) - (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / 2.0f)), 0, 0);
            } else {
                setGravity(17);
            }
        } else {
            setGravity(8388627);
            this.mXPaused = 0;
            this.status = 1;
            resumeScroll();
        }
    }

    public void resumeScroll() {
        if (this.mPaused) {
            setHorizontallyScrolling(true);
            if (this.mScroller == null) {
                this.mScroller = new Scroller(getContext(), new LinearInterpolator());
                setScroller(this.mScroller);
            }
            int calculateScrollingLen = calculateScrollingLen();
            int i = this.status;
            if (i == 1) {
                this.distance = calculateScrollingLen - this.mXPaused;
                this.status = 2;
            } else if (i == 2) {
                this.distance = getWidth();
                this.mXPaused = getWidth() * -1;
                this.status = 1;
            }
            this.duration = Double.valueOf((((double) (this.mRollingInterval * this.distance)) * 1.0d) / ((double) calculateScrollingLen)).intValue();
            this.mScroller.startScroll(this.mXPaused, 0, this.distance, 0, this.duration);
            invalidate();
            this.mPaused = false;
        }
    }

    public void stopScroll() {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            this.mPaused = true;
            scroller.startScroll(0, 0, 0, 0, 0);
        }
    }

    private int calculateScrollingLen() {
        TextPaint paint = getPaint();
        Rect rect = new Rect();
        String charSequence = getText().toString();
        paint.getTextBounds(charSequence, 0, charSequence.length(), rect);
        return rect.width();
    }

    public void computeScroll() {
        super.computeScroll();
        Scroller scroller = this.mScroller;
        if (scroller == null || !scroller.isFinished() || this.mPaused) {
            return;
        }
        if (this.mScrollMode == 101) {
            stopScroll();
            return;
        }
        this.mPaused = true;
        if (this.status == 1) {
            this.mXPaused = 0;
            postDelayed(new Runnable() {
                /* class com.slzhibo.library.ui.view.widget.marqueenview.MarqueeTextView.AnonymousClass1 */

                public void run() {
                    MarqueeTextView.this.resumeScroll();
                }
            }, 2000);
            return;
        }
        resumeScroll();
    }

    public int getRndDuration() {
        return this.mRollingInterval;
    }

    public void setRndDuration(int i) {
        this.mRollingInterval = i;
    }

    public void setScrollMode(int i) {
        this.mScrollMode = i;
    }

    public int getScrollMode() {
        return this.mScrollMode;
    }

    public void setScrollFirstDelay(int i) {
        this.mFirstScrollDelay = i;
    }

    public int getScrollFirstDelay() {
        return this.mFirstScrollDelay;
    }

    public void startWithText(final String str) {
        post(new Runnable() {
            /* class com.slzhibo.library.ui.view.widget.marqueenview.MarqueeTextView.AnonymousClass2 */

            public void run() {
                MarqueeTextView.this.setText(str);
                MarqueeTextView.this.startScroll();
            }
        });
    }
}
