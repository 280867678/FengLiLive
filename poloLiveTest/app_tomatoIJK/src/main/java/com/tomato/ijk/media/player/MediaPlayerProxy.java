package com.tomato.ijk.media.player;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.tomato.ijk.media.player.misc.IMediaDataSource;
import com.tomato.ijk.media.player.misc.ITrackInfo;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes2.dex */
public class MediaPlayerProxy implements IMediaPlayer {
    public final IMediaPlayer mBackEndMediaPlayer;

    public MediaPlayerProxy(IMediaPlayer iMediaPlayer) {
        this.mBackEndMediaPlayer = iMediaPlayer;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public int getAudioSessionId() {
        return this.mBackEndMediaPlayer.getAudioSessionId();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public long getCurrentPosition() {
        return this.mBackEndMediaPlayer.getCurrentPosition();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public String getDataSource() {
        return this.mBackEndMediaPlayer.getDataSource();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public long getDuration() {
        return this.mBackEndMediaPlayer.getDuration();
    }

    public IMediaPlayer getInternalMediaPlayer() {
        return this.mBackEndMediaPlayer;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public MediaInfo getMediaInfo() {
        return this.mBackEndMediaPlayer.getMediaInfo();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public ITrackInfo[] getTrackInfo() {
        return this.mBackEndMediaPlayer.getTrackInfo();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public int getVideoHeight() {
        return this.mBackEndMediaPlayer.getVideoHeight();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public int getVideoSarDen() {
        return this.mBackEndMediaPlayer.getVideoSarDen();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public int getVideoSarNum() {
        return this.mBackEndMediaPlayer.getVideoSarNum();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public int getVideoWidth() {
        return this.mBackEndMediaPlayer.getVideoWidth();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public boolean isLooping() {
        return this.mBackEndMediaPlayer.isLooping();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public boolean isPlayable() {
        return false;
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public boolean isPlaying() {
        return this.mBackEndMediaPlayer.isPlaying();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void pause() throws IllegalStateException {
        this.mBackEndMediaPlayer.pause();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void prepareAsync() throws IllegalStateException {
        this.mBackEndMediaPlayer.prepareAsync();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void release() {
        this.mBackEndMediaPlayer.release();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void reset() {
        this.mBackEndMediaPlayer.reset();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void seekTo(long j) throws IllegalStateException {
        this.mBackEndMediaPlayer.seekTo(j);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setAudioStreamType(int i) {
        this.mBackEndMediaPlayer.setAudioStreamType(i);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.mBackEndMediaPlayer.setDataSource(context, uri);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mBackEndMediaPlayer.setDisplay(surfaceHolder);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setKeepInBackground(boolean z) {
        this.mBackEndMediaPlayer.setKeepInBackground(z);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setLogEnabled(boolean z) {
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setLooping(boolean z) {
        this.mBackEndMediaPlayer.setLooping(z);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnBufferingUpdateListener(final OnBufferingUpdateListener onBufferingUpdateListener) {
        if (onBufferingUpdateListener != null) {
            this.mBackEndMediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.3
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
                public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                    onBufferingUpdateListener.onBufferingUpdate(MediaPlayerProxy.this, i);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnBufferingUpdateListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnCompletionListener(final OnCompletionListener onCompletionListener) {
        if (onCompletionListener != null) {
            this.mBackEndMediaPlayer.setOnCompletionListener(new OnCompletionListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.2
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnCompletionListener
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    onCompletionListener.onCompletion(MediaPlayerProxy.this);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnCompletionListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnErrorListener(final OnErrorListener onErrorListener) {
        if (onErrorListener != null) {
            this.mBackEndMediaPlayer.setOnErrorListener(new OnErrorListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.6
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnErrorListener
                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                    return onErrorListener.onError(MediaPlayerProxy.this, i, i2);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnErrorListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnInfoListener(final OnInfoListener onInfoListener) {
        if (onInfoListener != null) {
            this.mBackEndMediaPlayer.setOnInfoListener(new OnInfoListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.7
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnInfoListener
                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                    return onInfoListener.onInfo(MediaPlayerProxy.this, i, i2);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnInfoListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnPreparedListener(final OnPreparedListener onPreparedListener) {
        if (onPreparedListener != null) {
            this.mBackEndMediaPlayer.setOnPreparedListener(new OnPreparedListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.1
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnPreparedListener
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    onPreparedListener.onPrepared(MediaPlayerProxy.this);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnPreparedListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnSeekCompleteListener(final OnSeekCompleteListener onSeekCompleteListener) {
        if (onSeekCompleteListener != null) {
            this.mBackEndMediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.4
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
                public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                    onSeekCompleteListener.onSeekComplete(MediaPlayerProxy.this);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnSeekCompleteListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnTimedTextListener(final OnTimedTextListener onTimedTextListener) {
        if (onTimedTextListener != null) {
            this.mBackEndMediaPlayer.setOnTimedTextListener(new OnTimedTextListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.8
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnTimedTextListener
                public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {
                    onTimedTextListener.onTimedText(MediaPlayerProxy.this, ijkTimedText);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnTimedTextListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setOnVideoSizeChangedListener(final OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (onVideoSizeChangedListener != null) {
            this.mBackEndMediaPlayer.setOnVideoSizeChangedListener(new OnVideoSizeChangedListener() { // from class: com.tomato.ijk.media.player.MediaPlayerProxy.5
                @Override // com.tomato.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                    onVideoSizeChangedListener.onVideoSizeChanged(MediaPlayerProxy.this, i, i2, i3, i4);
                }
            });
        } else {
            this.mBackEndMediaPlayer.setOnVideoSizeChangedListener(null);
        }
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setScreenOnWhilePlaying(boolean z) {
        this.mBackEndMediaPlayer.setScreenOnWhilePlaying(z);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    @TargetApi(14)
    public void setSurface(Surface surface) {
        this.mBackEndMediaPlayer.setSurface(surface);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setVolume(float f, float f2) {
        this.mBackEndMediaPlayer.setVolume(f, f2);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setWakeMode(Context context, int i) {
        this.mBackEndMediaPlayer.setWakeMode(context, i);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void start() throws IllegalStateException {
        this.mBackEndMediaPlayer.start();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void stop() throws IllegalStateException {
        this.mBackEndMediaPlayer.stop();
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    @TargetApi(14)
    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.mBackEndMediaPlayer.setDataSource(context, uri, map);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setDataSource(FileDescriptor fileDescriptor) throws IOException, IllegalArgumentException, IllegalStateException {
        this.mBackEndMediaPlayer.setDataSource(fileDescriptor);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setDataSource(String str) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.mBackEndMediaPlayer.setDataSource(str);
    }

    @Override // com.tomato.ijk.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) {
        this.mBackEndMediaPlayer.setDataSource(iMediaDataSource);
    }
}