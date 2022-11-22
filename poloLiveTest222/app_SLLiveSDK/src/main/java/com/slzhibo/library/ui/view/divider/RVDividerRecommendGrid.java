package com.slzhibo.library.ui.view.divider;

import android.content.Context;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

public class RVDividerRecommendGrid extends Y_DividerItemDecoration {
    private final int colorRes;
    private final Context context;

    public RVDividerRecommendGrid(Context context2, @ColorRes int i) {
        super(context2);
        this.context = context2;
        this.colorRes = i;
    }

    @Override // com.slzhibo.library.ui.view.divider.decoration.Y_DividerItemDecoration
    public Y_Divider getDivider(int i) {
        if (i == 0) {
            Y_DividerBuilder y_DividerBuilder = new Y_DividerBuilder();
            y_DividerBuilder.setRightSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            y_DividerBuilder.setLeftSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            y_DividerBuilder.setTopSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            y_DividerBuilder.setBottomSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            return y_DividerBuilder.create();
        }
        int i2 = i % 3;
        if (i2 == 0) {
            Y_DividerBuilder y_DividerBuilder2 = new Y_DividerBuilder();
            y_DividerBuilder2.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 16.0f, 0.0f, 0.0f);
            return y_DividerBuilder2.create();
        } else if (i2 == 1) {
            Y_DividerBuilder y_DividerBuilder3 = new Y_DividerBuilder();
            y_DividerBuilder3.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 16.0f, 0.0f, 0.0f);
            return y_DividerBuilder3.create();
        } else if (i2 != 2) {
            Y_DividerBuilder y_DividerBuilder4 = new Y_DividerBuilder();
            y_DividerBuilder4.setRightSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            y_DividerBuilder4.setLeftSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            y_DividerBuilder4.setTopSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            y_DividerBuilder4.setBottomSideLine(false, ContextCompat.getColor(this.context, this.colorRes), 0.0f, 0.0f, 0.0f);
            return y_DividerBuilder4.create();
        } else {
            Y_DividerBuilder y_DividerBuilder5 = new Y_DividerBuilder();
            y_DividerBuilder5.setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 8.0f, 0.0f, 0.0f);
            y_DividerBuilder5.setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 8.0f, 0.0f, 0.0f);
            return y_DividerBuilder5.create();
        }
    }
}
