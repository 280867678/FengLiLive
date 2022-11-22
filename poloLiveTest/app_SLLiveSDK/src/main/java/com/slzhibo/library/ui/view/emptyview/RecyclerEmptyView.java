package com.slzhibo.library.ui.view.emptyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slzhibo.library.R;

public class RecyclerEmptyView extends LinearLayout {
    private final int emptyType;
    private final Context mContext;
    private TextView tvTextTips;

    public RecyclerEmptyView(Context context) {
        this(context, null, 0);
    }

    public RecyclerEmptyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public RecyclerEmptyView(Context context, int i) {
        this(context, null, i);
    }

    public RecyclerEmptyView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, 0, i);
    }

    public RecyclerEmptyView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.emptyType = i2;
        initView();
    }

    private void initView() {
        LinearLayout.inflate(getContext(), R.layout.fq_layout_empty_view, this);
        this.tvTextTips = (TextView) findViewById(R.id.tv_text_tips);
        ((TextView) findViewById(R.id.tv_empty_text)).setText(getEmptyText());
    }

    private String getEmptyText() {
        int i = this.emptyType;
        if (i == 30) {
            this.tvTextTips.setVisibility(View.GONE);
            return this.mContext.getString(R.string.fq_text_empty_tips_anchor);
        } else if (i != 31) {
            this.tvTextTips.setVisibility(View.VISIBLE);
            return this.mContext.getString(R.string.fq_text_list_empty);
        } else {
            this.tvTextTips.setVisibility(View.GONE);
            return this.mContext.getString(R.string.fq_text_empty_tips_live);
        }
    }
}
