package com.tomato.ijk.media.player;

import com.tomato.ijk.media.player.misc.IMediaDataSource;

/* loaded from: classes3.dex */
public abstract class AbstractMediaPlayer implements IMediaPlayer {
    private OnBufferingUpdateListener mOnBufferingUpdateListener;
    private OnCompletionListener mOnCompletionListener;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    private OnPreparedListener mOnPreparedListener;
    private OnSeekCompleteListener mOnSeekCompleteListener;
    private OnTimedTextListener mOnTimedTextListener;
    private OnVideoSizeChangedListener mOnVideoSizeChangedListener;

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.mOnBufferingUpdateListener = onBufferingUpdateListener;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnSeekCompleteListener(OnSeekCompleteListener onSeekCompleteListener) {
        this.mOnSeekCompleteListener = onSeekCompleteListener;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnInfoListener(OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public final void setOnTimedTextListener(OnTimedTextListener onTimedTextListener) {
        this.mOnTimedTextListener = onTimedTextListener;
    }

    public void resetListeners() {
        this.mOnPreparedListener = null;
        this.mOnBufferingUpdateListener = null;
        this.mOnCompletionListener = null;
        this.mOnSeekCompleteListener = null;
        this.mOnVideoSizeChangedListener = null;
        this.mOnErrorListener = null;
        this.mOnInfoListener = null;
        this.mOnTimedTextListener = null;
    }

    public final void notifyOnPrepared() {
        OnPreparedListener onPreparedListener = this.mOnPreparedListener;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this);
        }
    }

    public final void notifyOnCompletion() {
        OnCompletionListener onCompletionListener = this.mOnCompletionListener;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(this);
        }
    }

    public final void notifyOnBufferingUpdate(int i) {
        OnBufferingUpdateListener onBufferingUpdateListener = this.mOnBufferingUpdateListener;
        if (onBufferingUpdateListener != null) {
            onBufferingUpdateListener.onBufferingUpdate(this, i);
        }
    }

    public final void notifyOnSeekComplete() {
        OnSeekCompleteListener onSeekCompleteListener = this.mOnSeekCompleteListener;
        if (onSeekCompleteListener != null) {
            onSeekCompleteListener.onSeekComplete(this);
        }
    }

    public final void notifyOnVideoSizeChanged(int i, int i2, int i3, int i4) {
        OnVideoSizeChangedListener onVideoSizeChangedListener = this.mOnVideoSizeChangedListener;
        if (onVideoSizeChangedListener != null) {
            onVideoSizeChangedListener.onVideoSizeChanged(this, i, i2, i3, i4);
        }
    }

    public final boolean notifyOnError(int i, int i2) {
        OnErrorListener onErrorListener = this.mOnErrorListener;
        return onErrorListener != null && onErrorListener.onError(this, i, i2);
    }

    public final boolean notifyOnInfo(int i, int i2) {
        OnInfoListener onInfoListener = this.mOnInfoListener;
        return onInfoListener != null && onInfoListener.onInfo(this, i, i2);
    }

    public final void notifyOnTimedText(IjkTimedText ijkTimedText) {
        OnTimedTextListener onTimedTextListener = this.mOnTimedTextListener;
        if (onTimedTextListener != null) {
            onTimedTextListener.onTimedText(this, ijkTimedText);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) {
        throw new UnsupportedOperationException();
    }
}
