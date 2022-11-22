package com.slzhibo.library.ui.view.headview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slzhibo.library.R;

public class RecommendHeadView extends LinearLayout {
    public RecommendHeadView(Context context) {
        this(context, null);
    }

    public RecommendHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        LinearLayout.inflate(getContext(), R.layout.fq_layout_empty_view_recommend, this);
        TextView textView = (TextView) findViewById(R.id.tv_your_recommend);
    }
}
