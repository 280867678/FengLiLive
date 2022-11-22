package com.slzhibo.library.ui.view.ijkplayer;

import android.view.View;
import android.widget.MediaController;

/* loaded from: classes3.dex */
public interface IMediaController {
    void hide();

    boolean isShowing();

    void setAnchorView(View view);

    void setEnabled(boolean z);

    void setMediaPlayer(MediaController.MediaPlayerControl mediaPlayerControl);

    void show();

    void show(int i);
}
