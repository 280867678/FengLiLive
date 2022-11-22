package com.slzhibo.library.ui.interfaces;

import com.slzhibo.library.model.GiftDownloadItemEntity;

public interface OnHdLotteryCallback {
    void onFloatingWindowCloseListener();

    void onJoinLotteryListener(GiftDownloadItemEntity giftDownloadItemEntity, String str);
}
