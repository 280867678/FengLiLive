package com.slzhibo.library.ui.view.widget.badgeView;

import android.view.View;

public interface Badge {

    interface OnDragStateChangedListener {
        void onDragStateChanged(int i, Badge badge, View view);
    }

    Badge isNoNumber(boolean z);

    Badge setBadgeBackgroundColor(int i);

    Badge setBadgeGravity(int i);

    Badge setBadgePadding(float f, boolean z);

    Badge setBadgeTextColor(int i);

    Badge setBadgeTextSize(float f, boolean z);

    Badge stroke(int i, float f, boolean z);
}
