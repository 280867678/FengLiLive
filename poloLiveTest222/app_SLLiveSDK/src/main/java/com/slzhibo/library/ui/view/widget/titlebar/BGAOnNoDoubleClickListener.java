package com.slzhibo.library.ui.view.widget.titlebar;

import android.view.View;

public abstract class BGAOnNoDoubleClickListener implements View.OnClickListener {
    private long mLastClickTime = 0;
    private final int mThrottleFirstTime = 600;

    public abstract void onNoDoubleClick(View view);

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastClickTime > ((long) this.mThrottleFirstTime)) {
            this.mLastClickTime = currentTimeMillis;
            onNoDoubleClick(view);
        }
    }
}
