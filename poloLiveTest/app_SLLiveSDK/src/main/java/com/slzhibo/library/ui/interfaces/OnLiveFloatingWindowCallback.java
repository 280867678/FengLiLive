package com.slzhibo.library.ui.interfaces;

import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.HJProductEntity;
import com.slzhibo.library.model.YYLinkApplyEntity;

public interface OnLiveFloatingWindowCallback {
    void onAdBannerClickListener(BannerEntity bannerEntity);

    void onHJProductWindowClickListener(boolean z, HJProductEntity hJProductEntity);

    void onLYBluetoothWindowClickListener();

    void onYYLinkWindowClickListener(YYLinkApplyEntity yYLinkApplyEntity);
}
