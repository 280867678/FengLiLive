package com.slzhibo.library.ui.interfaces;

import android.view.View;

import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.GuardItemEntity;
import com.slzhibo.library.model.UserEntity;

public interface OnLivePusherInfoCallback {
    void onClickAdBannerListener(BannerEntity bannerEntity);

    void onClickAnchorAvatarListener(View view);

    void onClickAnchorInfoNoticeListener(View view);

    void onClickAudienceListener(View view);

    void onClickGameNoticeListener(View view);

    void onClickGiftNoticeListener(View view);

    void onClickGuardListener(GuardItemEntity guardItemEntity);

    void onClickLuckNoticeListener(View view);

    void onClickNoticeListener(View view);

    void onClickSysNoticeListener(View view);

    void onClickUserAvatarListener(UserEntity userEntity);

    void onFollowAnchorListener(View view);

    void onNobilityOpenListener();
}
