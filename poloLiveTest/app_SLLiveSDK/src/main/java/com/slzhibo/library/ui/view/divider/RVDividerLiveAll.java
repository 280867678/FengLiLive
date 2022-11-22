package com.slzhibo.library.ui.view.divider;

import android.content.Context;
//import android.support.annotation.ColorRes;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

public class RVDividerLiveAll extends Y_DividerItemDecoration {
    private int bannerSpanPosition;
    private final int colorRes;
    private final Context context;
    private boolean isHasBanner = false;
    private final boolean isHeadView = false;
    private final boolean isHeadViewWidth = true;

    public RVDividerLiveAll(Context context2, @ColorRes int i) {
        super(context2);
        this.context = context2;
        this.colorRes = i;
    }

    @Override // com.slzhibo.library.ui.view.divider.decoration.Y_DividerItemDecoration
    public Y_Divider getDivider(int i) {
        if (this.isHeadView) {
            if (i == 0) {
                Y_DividerBuilder y_DividerBuilder = new Y_DividerBuilder();
                y_DividerBuilder.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getHeadViewWidthDp(), 0.0f, 0.0f);
                y_DividerBuilder.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getHeadViewWidthDp(), 0.0f, 0.0f);
                y_DividerBuilder.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getHeadViewWidthDp(), 0.0f, 0.0f);
                y_DividerBuilder.setBottomSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
                return y_DividerBuilder.create();
            }
            int i2 = i % 2;
            if (i2 == 0) {
                Y_DividerBuilder y_DividerBuilder2 = new Y_DividerBuilder();
                y_DividerBuilder2.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                y_DividerBuilder2.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder2.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder2.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                return y_DividerBuilder2.create();
            } else if (i2 == 1) {
                Y_DividerBuilder y_DividerBuilder3 = new Y_DividerBuilder();
                y_DividerBuilder3.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                y_DividerBuilder3.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder3.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder3.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                return y_DividerBuilder3.create();
            }
        } else if (!this.isHasBanner) {
            int i3 = i % 2;
            if (i3 == 0) {
                Y_DividerBuilder y_DividerBuilder4 = new Y_DividerBuilder();
                y_DividerBuilder4.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                y_DividerBuilder4.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder4.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder4.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                return y_DividerBuilder4.create();
            } else if (i3 == 1) {
                Y_DividerBuilder y_DividerBuilder5 = new Y_DividerBuilder();
                y_DividerBuilder5.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                y_DividerBuilder5.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder5.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder5.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                return y_DividerBuilder5.create();
            }
        } else {
            int i4 = this.bannerSpanPosition;
            if (i < i4) {
                int i5 = i % 2;
                if (i5 == 0) {
                    Y_DividerBuilder y_DividerBuilder6 = new Y_DividerBuilder();
                    y_DividerBuilder6.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                    y_DividerBuilder6.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder6.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder6.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                    return y_DividerBuilder6.create();
                } else if (i5 == 1) {
                    Y_DividerBuilder y_DividerBuilder7 = new Y_DividerBuilder();
                    y_DividerBuilder7.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                    y_DividerBuilder7.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder7.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder7.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                    return y_DividerBuilder7.create();
                }
            } else if (i > i4) {
                int i6 = i % 2;
                if (i6 == 0) {
                    Y_DividerBuilder y_DividerBuilder8 = new Y_DividerBuilder();
                    y_DividerBuilder8.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                    y_DividerBuilder8.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder8.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder8.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                    return y_DividerBuilder8.create();
                } else if (i6 == 1) {
                    Y_DividerBuilder y_DividerBuilder9 = new Y_DividerBuilder();
                    y_DividerBuilder9.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, 0.0f, 0.0f);
                    y_DividerBuilder9.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder9.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                    y_DividerBuilder9.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                    return y_DividerBuilder9.create();
                }
            } else {
                Y_DividerBuilder y_DividerBuilder10 = new Y_DividerBuilder();
                y_DividerBuilder10.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder10.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder10.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, 0.0f, 0.0f);
                y_DividerBuilder10.setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), 0.0f, 0.0f);
                return y_DividerBuilder10.create();
            }
        }
        return null;
    }

    private float getTopDp(int i) {
        return this.isHeadView ? (i == 1 || i == 2) ? 10.0f : 0.0f : (i == 0 || i == 1) ? 10.0f : 0.0f;
    }

    private float getHeadViewWidthDp() {
        return this.isHeadViewWidth ? 10.0f : 0.0f;
    }

    public void setBannerSpanPosition(int i) {
        this.bannerSpanPosition = i;
    }

    public void setHasBanner(boolean z) {
        this.isHasBanner = z;
    }
}
