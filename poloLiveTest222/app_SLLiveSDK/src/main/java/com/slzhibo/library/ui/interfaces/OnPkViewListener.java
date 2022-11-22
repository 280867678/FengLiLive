package com.slzhibo.library.ui.interfaces;

import android.view.View;

public interface OnPkViewListener {
    void onAttentionAnchor(String str, View view);

    void onEnterLiveRoom(String str);

    void onPkCountDownComplete();

    void onReadyPK(View view);

    void onShowPKRanking();
}
