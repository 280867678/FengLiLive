package com.slzhibo.library.ui.interfaces.impl;

import android.view.View;

import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.ui.interfaces.OnUserCardCallback;

public class SimpleUserCardCallback implements OnUserCardCallback {
    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onAnchorItemClickListener(AnchorEntity anchorEntity) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onAttentionAnchorListener(View view, AnchorEntity anchorEntity) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onClickAttentionListener(View view) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onClickGuardListener() {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onClickManageListener(View view) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onClickNobilityListener(View view) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onGiftWallClickListener(AnchorEntity anchorEntity) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onUserAchieveListener(UserEntity userEntity, String str) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnUserCardCallback
    public void onUserItemClickListener(UserEntity userEntity) {
    }
}
