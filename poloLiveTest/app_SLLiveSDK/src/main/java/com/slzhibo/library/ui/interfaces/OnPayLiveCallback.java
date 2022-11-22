package com.slzhibo.library.ui.interfaces;

import android.view.View;

import com.slzhibo.library.model.RelationLastLiveEntity;

public interface OnPayLiveCallback {
    void onPayCancelListener();

    void onPayEnterClickListener(View view);

    void onPayExitClickListener();

    void onPayLiveInfoSubmit(String str, String str2, String str3, String str4, RelationLastLiveEntity relationLastLiveEntity);
}
