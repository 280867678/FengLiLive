package com.slzhibo.library.ui.view.widget.guideview;

import android.content.Context;

public class DimenUtil {
    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
