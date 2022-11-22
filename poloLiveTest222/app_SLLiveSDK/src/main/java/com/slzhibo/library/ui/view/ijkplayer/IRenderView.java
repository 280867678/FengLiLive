package com.slzhibo.library.ui.view.ijkplayer;


import android.view.View;

import androidx.annotation.NonNull;

//import com.tomato.ijk.media.player.IMediaPlayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes3.dex */
public interface IRenderView {

    /* loaded from: classes3.dex */
    public interface IRenderCallback {
        void onSurfaceChanged(@NonNull ISurfaceHolder iSurfaceHolder, int i, int i2, int i3);

        void onSurfaceCreated(@NonNull ISurfaceHolder iSurfaceHolder, int i, int i2);

        void onSurfaceDestroyed(@NonNull ISurfaceHolder iSurfaceHolder);
    }

    /* loaded from: classes3.dex */
    public interface ISurfaceHolder {
        void bindToMediaPlayer(IMediaPlayer iMediaPlayer);

        @NonNull
        IRenderView getRenderView();
    }

    void addRenderCallback(@NonNull IRenderCallback iRenderCallback);

    View getView();

    void removeRenderCallback(@NonNull IRenderCallback iRenderCallback);

    void setAspectRatio(int i);

    void setVideoRotation(int i);

    void setVideoSampleAspectRatio(int i, int i2);

    void setVideoSize(int i, int i2);

    boolean shouldWaitForResize();
}
