package com.slzhibo.library.ui.interfaces.impl;

import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.HJProductEntity;
import com.slzhibo.library.model.YYLinkApplyEntity;
import com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback;

public class SimpleLiveFloatingWindowCallback implements OnLiveFloatingWindowCallback {
    @Override
    public void onAdBannerClickListener(BannerEntity bannerEntity) {

    }

    @Override // com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback
    public void onHJProductWindowClickListener(boolean z, HJProductEntity hJProductEntity) {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback
    public void onLYBluetoothWindowClickListener() {
    }

    @Override // com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback
    public void onYYLinkWindowClickListener(YYLinkApplyEntity yYLinkApplyEntity) {
    }
}
