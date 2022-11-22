package com.blmvl.blvl.ijkplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;

import androidx.annotation.NonNull;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* loaded from: classes3.dex */
public class IjkVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    private static final int[] s_allAspectRatio = {0, 1, 2, 4, 5};
    private Context mAppContext;
    private int mCurrentBufferPercentage;
    private Map<String, String> mHeaders;
    private IMediaController mMediaController;
    private OnPlayStateListener mOnPlayStateListener;
    private long mPrepareEndTime;
    private IRenderView mRenderView;
    private long mSeekEndTime;
    private int mSeekWhenPrepared;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private TextView subtitleDisplay;
    private final String TAG = "IjkVideoView";
    private int mCurrentState = 0;
    private int mTargetState = 0;
    private IRenderView.ISurfaceHolder mSurfaceHolder = null;
    private IMediaPlayer mMediaPlayer = null;
    private final boolean mCanPause = true;
    private final boolean mCanSeekBack = true;
    private final boolean mCanSeekForward = true;
    private boolean mMuted = false;
    IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.1
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
            IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
            IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
            IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
            if (IjkVideoView.this.mVideoWidth != 0 && IjkVideoView.this.mVideoHeight != 0) {
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        }
    };
    IMediaPlayer.OnPreparedListener mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.2
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
            IjkVideoView.this.mCurrentState = 2;
            if (IjkVideoView.this.mOnPlayStateListener != null) {
                IjkVideoView.this.mOnPlayStateListener.onPrepared(IjkVideoView.this.mMediaPlayer);
            }
            if (IjkVideoView.this.mMediaController != null) {
                IjkVideoView.this.mMediaController.setEnabled(true);
            }
            IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
            IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
            int i = IjkVideoView.this.mSeekWhenPrepared;
            if (i != 0) {
                IjkVideoView.this.seekTo(i);
            }
            if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                if (IjkVideoView.this.mTargetState == 3) {
                    IjkVideoView.this.start();
                }
            } else if (IjkVideoView.this.mRenderView != null) {
                IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mSurfaceWidth != IjkVideoView.this.mVideoWidth || IjkVideoView.this.mSurfaceHeight != IjkVideoView.this.mVideoHeight)) {
                    return;
                }
                if (IjkVideoView.this.mTargetState == 3) {
                    IjkVideoView.this.start();
                    if (IjkVideoView.this.mMediaController != null) {
                        IjkVideoView.this.mMediaController.show();
                    }
                } else if (IjkVideoView.this.isPlaying()) {
                } else {
                    if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                        IjkVideoView.this.mMediaController.show(0);
                    }
                }
            }
        }
    };
    private final IMediaPlayer.OnCompletionListener mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.3
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            IjkVideoView.this.mCurrentState = 5;
            IjkVideoView.this.mTargetState = 5;
            if (IjkVideoView.this.mMediaController != null) {
                IjkVideoView.this.mMediaController.hide();
            }
            if (IjkVideoView.this.mOnPlayStateListener != null) {
                IjkVideoView.this.mOnPlayStateListener.onCompletion(IjkVideoView.this.mMediaPlayer);
            }
        }
    };
    private final IMediaPlayer.OnInfoListener mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.4
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (IjkVideoView.this.mOnPlayStateListener != null) {
                IjkVideoView.this.mOnPlayStateListener.onInfo(iMediaPlayer, i, i2);
            }
            if (i == 3) {
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                return true;
            } else if (i == 901) {
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                return true;
            } else if (i == 902) {
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                return true;
            } else if (i == 10001) {
                IjkVideoView.this.mVideoRotationDegree = i2;
                String str = IjkVideoView.this.TAG;
                Log.d(str, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                if (IjkVideoView.this.mRenderView == null) {
                    return true;
                }
                IjkVideoView.this.mRenderView.setVideoRotation(i2);
                return true;
            } else if (i != 10002) {
                switch (i) {
                    case 700:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        return true;
                    case 701:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                        return true;
                    case 702:
                        Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                        return true;
                    case 703:
                        String str2 = IjkVideoView.this.TAG;
                        Log.d(str2, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                        return true;
                    default:
                        switch (i) {
                            case 800:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                return true;
                            case 801:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                return true;
                            case 802:
                                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                return true;
                            default:
                                return true;
                        }
                }
            } else {
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        }
    };
    private final IMediaPlayer.OnErrorListener mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.5
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            String str = IjkVideoView.this.TAG;
            Log.d(str, "Error: " + i + "," + i2);
            IjkVideoView.this.mCurrentState = -1;
            IjkVideoView.this.mTargetState = -1;
            if (IjkVideoView.this.mMediaController != null) {
                IjkVideoView.this.mMediaController.hide();
            }
            if (IjkVideoView.this.mOnPlayStateListener == null || IjkVideoView.this.mOnPlayStateListener.onError(IjkVideoView.this.mMediaPlayer, i, i2)) {
            }
            return true;
        }
    };
    private final IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.6
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            IjkVideoView.this.mCurrentBufferPercentage = i;
        }
    };
    private final IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.7
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
        }
    };
    private final IMediaPlayer.OnTimedTextListener mOnTimedTextListener = new IMediaPlayer.OnTimedTextListener() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.8
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnTimedTextListener
        public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {
            if (ijkTimedText != null) {
                IjkVideoView.this.subtitleDisplay.setText(ijkTimedText.getText());
            }
        }
    };
    IRenderView.IRenderCallback mSHCallback = new IRenderView.IRenderCallback() { // from class: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.9
        @Override // com.slzhibo.library.ui.view.ijkplayer.IRenderView.IRenderCallback
        public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
            if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                return;
            }
            IjkVideoView.this.mSurfaceWidth = i2;
            IjkVideoView.this.mSurfaceHeight = i3;
            boolean z = true;
            boolean z2 = IjkVideoView.this.mTargetState == 3;
            if (IjkVideoView.this.mRenderView.shouldWaitForResize() && !(IjkVideoView.this.mVideoWidth == i2 && IjkVideoView.this.mVideoHeight == i3)) {
                z = false;
            }
            if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                }
                IjkVideoView.this.start();
            }
        }

        @Override // com.slzhibo.library.ui.view.ijkplayer.IRenderView.IRenderCallback
        public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
            if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                return;
            }
            IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
            if (IjkVideoView.this.mMediaPlayer != null) {
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                return;
            }
            IjkVideoView.this.openVideo();
        }

        @Override // com.slzhibo.library.ui.view.ijkplayer.IRenderView.IRenderCallback
        public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
            if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }
            IjkVideoView.this.mSurfaceHolder = null;
            IjkVideoView.this.releaseWithoutStop();
        }
    };
    private final int mCurrentAspectRatio = s_allAspectRatio[1];
    private final List<Integer> mAllRenders = new ArrayList();
    private final int mCurrentRenderIndex = 0;

    /* loaded from: classes3.dex */
    public static class OnPlayStateListener implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener {
        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            throw null;
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            throw null;
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    public IjkVideoView(Context context) {
        super(context);
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initVideoView(context);
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
        initRenders();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.subtitleDisplay = new TextView(context);
        this.subtitleDisplay.setTextSize(24.0f);
        this.subtitleDisplay.setGravity(17);
        addView(this.subtitleDisplay, new LayoutParams(-1, -2, 80));
    }

    public void setRenderView(IRenderView iRenderView) {
        int i;
        int i2;
        if (this.mRenderView != null) {
            IMediaPlayer iMediaPlayer = this.mMediaPlayer;
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallback(this.mSHCallback);
            this.mRenderView = null;
            removeView(view);
        }
        if (iRenderView != null) {
            this.mRenderView = iRenderView;
            iRenderView.setAspectRatio(this.mCurrentAspectRatio);
            int i3 = this.mVideoWidth;
            if (i3 > 0 && (i2 = this.mVideoHeight) > 0) {
                iRenderView.setVideoSize(i3, i2);
            }
            int i4 = this.mVideoSarNum;
            if (i4 > 0 && (i = this.mVideoSarDen) > 0) {
                iRenderView.setVideoSampleAspectRatio(i4, i);
            }
            View view2 = this.mRenderView.getView();
            view2.setLayoutParams(new LayoutParams(-2, -2, 17));
            addView(view2);
            this.mRenderView.addRenderCallback(this.mSHCallback);
            this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
        }
    }

    public void setRender(int i) {
        if (i == 0) {
            setRenderView(null);
        } else if (i == 1) {
            setRenderView(new SurfaceRenderView(getContext()));
        } else if (i != 2) {
            Log.e(this.TAG, String.format(Locale.getDefault(), "invalid render %d\n", Integer.valueOf(i)));
        } else {
            TextureRenderView textureRenderView = new TextureRenderView(getContext());
            if (this.mMediaPlayer != null) {
                textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                textureRenderView.setAspectRatio(this.mCurrentAspectRatio);
            }
            setRenderView(textureRenderView);
        }
    }

    public void setMute(boolean z) {
        this.mMuted = z;
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return;
        }
        if (z) {
            iMediaPlayer.setVolume(0.0f, 0.0f);
        } else {
            iMediaPlayer.setVolume(1.0f, 1.0f);
        }
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mHeaders = map;
        this.mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
            ((AudioManager) this.mAppContext.getSystemService(Context.AUDIO_SERVICE)).abandonAudioFocus(null);
        }
    }

    @TargetApi(23)
    public void openVideo() {
        if (this.mUri != null && this.mSurfaceHolder != null) {
            release(false);
            ((AudioManager) this.mAppContext.getSystemService(Context.AUDIO_SERVICE)).requestAudioFocus(null, 3, 1);
            try {
                this.mMediaPlayer = createPlayer();
                getContext();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
                this.mMediaPlayer.setOnTimedTextListener(this.mOnTimedTextListener);
                this.mCurrentBufferPercentage = 0;
                this.mUri.getScheme();
                if (Build.VERSION.SDK_INT >= 14) {
                    this.mMediaPlayer.setDataSource(this.mAppContext, this.mUri, this.mHeaders);
                } else {
                    this.mMediaPlayer.setDataSource(this.mUri.toString());
                }
                bindSurfaceHolder(this.mMediaPlayer, this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                System.currentTimeMillis();
                this.mMediaPlayer.prepareAsync();
                setMute(this.mMuted);
                this.mCurrentState = 1;
                attachMediaController();
            } catch (IOException e) {
                String str = this.TAG;
                Log.w(str, "Unable to open content: " + this.mUri, e);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            } catch (IllegalArgumentException e2) {
                String str2 = this.TAG;
                Log.w(str2, "Unable to open content: " + this.mUri, e2);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        }
    }

    public void setMediaController(IMediaController iMediaController) {
        IMediaController iMediaController2 = this.mMediaController;
        if (iMediaController2 != null) {
            iMediaController2.hide();
        }
        this.mMediaController = iMediaController;
        attachMediaController();
    }

    private void attachMediaController() {
        IMediaController iMediaController;
        if (this.mMediaPlayer != null && (iMediaController = this.mMediaController) != null) {
            iMediaController.setMediaPlayer(this);
            this.mMediaController.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
            this.mMediaController.setEnabled(isInPlaybackState());
        }
    }

    public void setOnPlayStateListener(OnPlayStateListener onPlayStateListener) {
        this.mOnPlayStateListener = onPlayStateListener;
    }

    public void bindSurfaceHolder(IMediaPlayer iMediaPlayer, IRenderView.ISurfaceHolder iSurfaceHolder) {
        if (iMediaPlayer != null) {
            if (iSurfaceHolder == null) {
                iMediaPlayer.setDisplay(null);
            } else {
                iSurfaceHolder.bindToMediaPlayer(iMediaPlayer);
            }
        }
    }

    public void releaseWithoutStop() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.setDisplay(null);
        }
    }

    public void release(boolean z) {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer != null) {
            iMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
            ((AudioManager) this.mAppContext.getSystemService(Context.AUDIO_SERVICE)).abandonAudioFocus(null);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisiblity();
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisiblity();
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = i != 4 && i != 24 && i != 25 && i != 164 && i != 82 && i != 5 && i != 6;
        if (isInPlaybackState() && z && this.mMediaController != null) {
            if (i == 79 || i == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                } else {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            } else if (i == 126) {
                if (!this.mMediaPlayer.isPlaying()) {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            } else if (i == 86 || i == 127) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                }
                return true;
            } else {
                toggleMediaControlsVisiblity();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void toggleMediaControlsVisiblity() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
        }
        this.mTargetState = 3;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (isInPlaybackState()) {
            System.currentTimeMillis();
            this.mMediaPlayer.seekTo(i);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = i;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    private boolean isInPlaybackState() {
        int i;
//        Toast.makeText(mAppContext, "是否播放："+String.valueOf((this.mMediaPlayer == null || (i = this.mCurrentState) == -1 || i == 0 || i == 1) ? false : true), Toast.LENGTH_SHORT).show();
        if(this.mMediaPlayer == null){
            Log.e("ijkVideoVew：",String.valueOf(this.mMediaPlayer != null));
        }
        Log.e("ijkVideoVew：", String.valueOf(this.mCurrentState));

        return this.mMediaPlayer != null && (i = this.mCurrentState) != -1 && i != 0 && i != 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.mCanPause;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    private void initRenders() {
        this.mAllRenders.clear();
        this.mAllRenders.add(1);
        this.mAllRenders.get(this.mCurrentRenderIndex).intValue();
        setRender(2);
    }

    public IMediaPlayer createPlayer() {
        if (this.mUri == null) {
            return null;
        }
        IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
        IjkMediaPlayer.native_setLogLevel(3);
        ijkMediaPlayer.setOption(4, "mediacodec", 0L);
        ijkMediaPlayer.setOption(4, "opensles", 0L);
        if (TextUtils.isEmpty("")) {
            ijkMediaPlayer.setOption(4, "overlay-format", 842225234L);
        } else {
            ijkMediaPlayer.setOption(4, "overlay-format", "");
        }
        ijkMediaPlayer.setOption(4, "framedrop", 1L);
        ijkMediaPlayer.setOption(4, "start-on-prepared", 0L);
        ijkMediaPlayer.setOption(1, "http-detect-range-support", 0L);
        ijkMediaPlayer.setOption(1, "analyzemaxduration", 100L);
        ijkMediaPlayer.setOption(1, "probesize", 10240L);
        ijkMediaPlayer.setOption(1, "flush_packets", 1L);
        ijkMediaPlayer.setOption(4, "packet-buffering", 0L);
        ijkMediaPlayer.setOption(2, "skip_loop_filter", 0L);
        return ijkMediaPlayer;
    }

    public ITrackInfo[] getTrackInfo() {
        IMediaPlayer iMediaPlayer = this.mMediaPlayer;
        if (iMediaPlayer == null) {
            return null;
        }
        return iMediaPlayer.getTrackInfo();
    }

    @TargetApi(15)
    public Bitmap getShortcut() {
        Bitmap bitmap;
        IRenderView iRenderView = this.mRenderView;
        if (!(iRenderView instanceof TextureRenderView)) {
            return null;
        }
        TextureRenderView textureRenderView = (TextureRenderView) iRenderView;
        try {
            bitmap = textureRenderView.getBitmap();
        } catch (OutOfMemoryError unused) {
            bitmap = null;
        }
        if (bitmap == null) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), textureRenderView.getTransform(null), true);
        bitmap.recycle();
        return createBitmap;
    }

    public void switchStream(String str) {
        setVideoPath(str);
        setRender(2);
        start();
    }
}
