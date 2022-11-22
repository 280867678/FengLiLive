package com.slzhibo.library.ui.interfaces;

import com.slzhibo.library.model.YYLinkApplyEntity;
import com.slzhibo.library.model.YYRTCEntity;

public interface YYLinkCallback {
    void onClickLinkRTCUserListener(int i, YYLinkApplyEntity yYLinkApplyEntity);

    void onDisconnectLinkListener();

    void onLinkActionMenuListener(int i, YYLinkApplyEntity yYLinkApplyEntity);

    void onSetMuteStatusListener(boolean z);

    void onUserAcceptRefuseLinkListener(boolean z, boolean z2, YYRTCEntity yYRTCEntity);
}
