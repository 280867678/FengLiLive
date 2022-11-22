package com.slzhibo.library.ui.interfaces;

import android.view.View;

import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.UserEntity;

public interface OnUserCardCallback {
    void onAnchorItemClickListener(AnchorEntity anchorEntity);

    void onAttentionAnchorListener(View view, AnchorEntity anchorEntity);

    void onClickAttentionListener(View view);

    void onClickGuardListener();

    void onClickManageListener(View view);

    void onClickNobilityListener(View view);

    void onGiftWallClickListener(AnchorEntity anchorEntity);

    void onUserAchieveListener(UserEntity userEntity, String str);

    void onUserItemClickListener(UserEntity userEntity);
}
