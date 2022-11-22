package com.slzhibo.library.ui.interfaces;

import com.slzhibo.library.model.GiftDownloadItemEntity;

public interface OnQMInteractCallback {
    void onBackQMInteractConfigListener();

    void onNoticeAnimViewDismissListener();

    void onSendGiftListener(GiftDownloadItemEntity giftDownloadItemEntity);

    void onSendInvitationListener(String str, String str2, String str3, String str4);

    void onTaskStatusUpdateListener(String str, String str2);

    void onUserCardListener(String str);
}
