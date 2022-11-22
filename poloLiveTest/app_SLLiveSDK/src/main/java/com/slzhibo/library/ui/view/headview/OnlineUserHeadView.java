package com.slzhibo.library.ui.view.headview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.slzhibo.library.R;

public class OnlineUserHeadView extends LinearLayout {
    private TextView tvCount;

    public OnlineUserHeadView(Context context) {
        super(context);
        initView(context);
    }

    public OnlineUserHeadView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout.inflate(context, R.layout.fq_layout_head_view_online_user, this);
        this.tvCount = (TextView) findViewById(R.id.tv_guard_number);
    }

    public void updateGuardCount(String str) {
        this.tvCount.setText(str);
    }
}
