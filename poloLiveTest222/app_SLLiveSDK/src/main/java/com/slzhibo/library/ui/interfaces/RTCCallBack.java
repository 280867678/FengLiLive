package com.slzhibo.library.ui.interfaces;

import io.agora.rtc.video.VideoCanvas;

public interface RTCCallBack {
    void onJoinChannelFail(int i);

    void onJoinFailure();

    void onJoinSuccess(String str);

    VideoCanvas onRemoteUserJoinSuccess(int i);

    void onRtcInitError();

    void onUserDropped(int i);

    void onUserQuit(int i);
}
