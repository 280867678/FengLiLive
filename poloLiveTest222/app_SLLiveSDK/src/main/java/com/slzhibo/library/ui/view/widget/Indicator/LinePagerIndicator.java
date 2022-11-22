package com.slzhibo.library.ui.view.widget.Indicator;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

//import com.example.boluouitest2zhibo.TitleView.UIUtil;
//import com.blmvl.blvl.util.UIUtil;
import com.slzhibo.library.ui.view.widget.PagerTitleView.ArgbEvaluatorHolder;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.model.PositionData;

import java.util.Arrays;
import java.util.List;

/* renamed from: com.slzhibo.library.ui.view.widget.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator */
/* loaded from: classes6.dex */
public class LinePagerIndicator extends View implements IPagerIndicator {
    public static final int MODE_EXACTLY = 2;
    public static final int MODE_MATCH_EDGE = 0;
    public static final int MODE_WRAP_CONTENT = 1;
    private List<Integer> mColors;
    private float mLineHeight;
    private float mLineWidth;
    private int mMode;
    private Paint mPaint;
    private List<PositionData> mPositionDataList;
    private float mRoundRadius;
    private float mXOffset;
    private float mYOffset;
    private Interpolator mStartInterpolator = new LinearInterpolator();
    private Interpolator mEndInterpolator = new LinearInterpolator();
    private RectF mLineRect = new RectF();

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
    public void onPageScrollStateChanged(int i) {
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
    public void onPageSelected(int i) {
    }



    public LinePagerIndicator(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mLineHeight = UIUtil.dip2px(context, 3.0d);
        this.mLineWidth = UIUtil.dip2px(context, 10.0d);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF = this.mLineRect;
        float f = this.mRoundRadius;
        canvas.drawRoundRect(rectF, f, f, this.mPaint);
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
    public void onPageScrolled(int i, float f, int i2) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6 = 0;
        int i3 = 0;
        List<PositionData> list = this.mPositionDataList;
        if (list != null && !list.isEmpty()) {
            List<Integer> list2 = this.mColors;
            if (list2 != null && list2.size() > 0) {
                this.mPaint.setColor(ArgbEvaluatorHolder.eval(f, this.mColors.get(Math.abs(i) % this.mColors.size()).intValue(), this.mColors.get(Math.abs(i + 1) % this.mColors.size()).intValue()));
            }
            PositionData imitativePositionData = FragmentContainerHelper.getImitativePositionData(this.mPositionDataList, i);
            PositionData imitativePositionData2 = FragmentContainerHelper.getImitativePositionData(this.mPositionDataList, i + 1);
            int i4 = this.mMode;
            if (i4 == 0) {
                f6 = this.mXOffset;
                f5 = imitativePositionData.mLeft + f6;
                f4 = imitativePositionData2.mLeft + f6;
                f2 = imitativePositionData.mRight - f6;
                i3 = imitativePositionData2.mRight;
            } else if (i4 == 1) {
                f6 = this.mXOffset;
                f5 = imitativePositionData.mContentLeft + f6;
                f4 = imitativePositionData2.mContentLeft + f6;
                f2 = imitativePositionData.mContentRight - f6;
                i3 = imitativePositionData2.mContentRight;
            } else {
                f5 = imitativePositionData.mLeft + ((imitativePositionData.width() - this.mLineWidth) / 2.0f);
                f4 = imitativePositionData2.mLeft + ((imitativePositionData2.width() - this.mLineWidth) / 2.0f);
                f2 = ((imitativePositionData.width() + this.mLineWidth) / 2.0f) + imitativePositionData.mLeft;
                f3 = ((imitativePositionData2.width() + this.mLineWidth) / 2.0f) + imitativePositionData2.mLeft;
                this.mLineRect.left = f5 + ((f4 - f5) * this.mStartInterpolator.getInterpolation(f));
                this.mLineRect.right = f2 + ((f3 - f2) * this.mEndInterpolator.getInterpolation(f));
                this.mLineRect.top = (getHeight() - this.mLineHeight) - this.mYOffset;
                this.mLineRect.bottom = getHeight() - this.mYOffset;
                invalidate();
            }
            f3 = i3 - f6;
            this.mLineRect.left = f5 + ((f4 - f5) * this.mStartInterpolator.getInterpolation(f));
            this.mLineRect.right = f2 + ((f3 - f2) * this.mEndInterpolator.getInterpolation(f));
            this.mLineRect.top = (getHeight() - this.mLineHeight) - this.mYOffset;
            this.mLineRect.bottom = getHeight() - this.mYOffset;
            invalidate();
        }
    }

    @Override // com.slzhibo.library.p115ui.view.widget.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
    public void onPositionDataProvide(List<PositionData> list) {
        this.mPositionDataList = list;
    }



    public float getYOffset() {
        return this.mYOffset;
    }

    public void setYOffset(float f) {
        this.mYOffset = f;
    }

    public float getXOffset() {
        return this.mXOffset;
    }

    public void setXOffset(float f) {
        this.mXOffset = f;
    }

    public float getLineHeight() {
        return this.mLineHeight;
    }

    public void setLineHeight(float f) {
        this.mLineHeight = f;
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setLineWidth(float f) {
        this.mLineWidth = f;
    }

    public float getRoundRadius() {
        return this.mRoundRadius;
    }

    public void setRoundRadius(float f) {
        this.mRoundRadius = f;
    }

    public int getMode() {
        return this.mMode;
    }

    public void setMode(int i) {
        if (i == 2 || i == 0 || i == 1) {
            this.mMode = i;
            return;
        }
        throw new IllegalArgumentException("mode " + i + " not supported.");
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public List<Integer> getColors() {
        return this.mColors;
    }

    public void setColors(Integer... numArr) {
        this.mColors = Arrays.asList(numArr);
    }

    public Interpolator getStartInterpolator() {
        return this.mStartInterpolator;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.mStartInterpolator = interpolator;
        if (this.mStartInterpolator == null) {
            this.mStartInterpolator = new LinearInterpolator();
        }
    }

    public Interpolator getEndInterpolator() {
        return this.mEndInterpolator;
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.mEndInterpolator = interpolator;
        if (this.mEndInterpolator == null) {
            this.mEndInterpolator = new LinearInterpolator();
        }
    }
}

