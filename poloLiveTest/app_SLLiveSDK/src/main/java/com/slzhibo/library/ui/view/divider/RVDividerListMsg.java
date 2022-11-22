package com.slzhibo.library.ui.view.divider;

import android.content.Context;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

public class RVDividerListMsg extends Y_DividerItemDecoration {
    private final int colorRes;
    private final Context context;

    public RVDividerListMsg(Context context2, @ColorRes int i) {
        super(context2);
        this.context = context2;
        this.colorRes = i;
    }

    @Override // com.slzhibo.library.ui.view.divider.decoration.Y_DividerItemDecoration
    public Y_Divider getDivider(int i) {
        Y_DividerBuilder y_DividerBuilder = new Y_DividerBuilder();
        y_DividerBuilder.setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 6.0f, 0.0f, 0.0f);
        return y_DividerBuilder.create();
    }
}
