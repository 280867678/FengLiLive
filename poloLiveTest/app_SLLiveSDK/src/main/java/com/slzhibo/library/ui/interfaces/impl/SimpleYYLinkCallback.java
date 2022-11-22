package com.slzhibo.library.ui.interfaces.impl;

import com.slzhibo.library.model.YYLinkApplyEntity;
import com.slzhibo.library.model.YYRTCEntity;
import com.slzhibo.library.ui.interfaces.YYLinkCallback;

public class SimpleYYLinkCallback implements YYLinkCallback {
    @Override // com.slzhibo.library.ui.interfaces.YYLinkCallback
    public void onClickLinkRTCUserListener(int i, YYLinkApplyEntity yYLinkApplyEntity) {
    }

    @Override // com.slzhibo.library.ui.interfaces.YYLinkCallback
    public void onDisconnectLinkListener() {
    }

    @Override // com.slzhibo.library.ui.interfaces.YYLinkCallback
    public void onLinkActionMenuListener(int i, YYLinkApplyEntity yYLinkApplyEntity) {
    }

    @Override // com.slzhibo.library.ui.interfaces.YYLinkCallback
    public void onSetMuteStatusListener(boolean z) {
    }

    @Override // com.slzhibo.library.ui.interfaces.YYLinkCallback
    public void onUserAcceptRefuseLinkListener(boolean z, boolean z2, YYRTCEntity yYRTCEntity) {
    }
}
