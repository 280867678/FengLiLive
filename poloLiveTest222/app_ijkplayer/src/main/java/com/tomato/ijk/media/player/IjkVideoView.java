package com.tomato.ijk.media.player;



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

import com.tomato.ijk.media.player.widget.media.IMediaController;
import com.tomato.ijk.media.player.widget.media.IRenderView;
import com.tomato.ijk.media.player.widget.media.SurfaceRenderView;
import com.tomato.ijk.media.player.widget.media.TextureRenderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView */
/* loaded from: classes2.dex */
public class IjkVideoView extends FrameLayout implements MediaController.MediaPlayerControl {

    /* renamed from: O */
    public static final int[] f7977O = {0, 1, 2, 4, 5};

    /* renamed from: A */
    public long f7978A;

    /* renamed from: B */
    public TextView f7979B;

    /* renamed from: c */
    public Uri f7993c;

    /* renamed from: d */
    public Map<String, String> f7994d;

    /* renamed from: i */
    public int f7999i;

    /* renamed from: j */
    public int f8000j;

    /* renamed from: k */
    public int f8001k;

    /* renamed from: l */
    public int f8002l;

    /* renamed from: m */
    public int f8003m;

    /* renamed from: n */
    public IMediaController f8004n;

    /* renamed from: o */
    public C2831j f8005o;

    /* renamed from: p */
    public int f8006p;

    /* renamed from: q */
    public int f8007q;

    /* renamed from: v */
    public Context f8012v;

    /* renamed from: w */
    public IRenderView f8013w;

    /* renamed from: x */
    public int f8014x;

    /* renamed from: y */
    public int f8015y;

    /* renamed from: z */
    public long f8016z;

    /* renamed from: b */
    public String f7992b = "IjkVideoView";

    /* renamed from: e */
    public int f7995e = 0;

    /* renamed from: f */
    public int f7996f = 0;

    /* renamed from: g */
    public IRenderView.ISurfaceHolder f7997g = null;

    /* renamed from: h */
    public IMediaPlayer f7998h = null;

    /* renamed from: r */
    public boolean f8008r = true;

    /* renamed from: s */
    public boolean f8009s = true;

    /* renamed from: t */
    public boolean f8010t = true;

    /* renamed from: u */
    public boolean f8011u = false;

    /* renamed from: C */
    public IMediaPlayer.OnVideoSizeChangedListener f7980C = new C2822a();

    /* renamed from: D */
    public IMediaPlayer.OnPreparedListener f7981D = new C2823b();

    /* renamed from: E */
    public IMediaPlayer.OnCompletionListener f7982E = new C2824c();

    /* renamed from: F */
    public IMediaPlayer.OnInfoListener f7983F = new C2825d();

    /* renamed from: G */
    public IMediaPlayer.OnErrorListener f7984G = new C2826e();

    /* renamed from: H */
    public IMediaPlayer.OnBufferingUpdateListener f7985H = new C2827f();

    /* renamed from: I */
    public IMediaPlayer.OnSeekCompleteListener f7986I = new C2828g();

    /* renamed from: J */
    public IMediaPlayer.OnTimedTextListener f7987J = new C2829h();

    /* renamed from: K */
    public IRenderView.IRenderCallback f7988K = new C2830i();

    /* renamed from: L */
    public int f7989L = f7977O[1];

    /* renamed from: M */
    public List<Integer> f7990M = new ArrayList();

    /* renamed from: N */
    public int f7991N = 0;

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$a */
    /* loaded from: classes2.dex */
    public class C2822a implements IMediaPlayer.OnVideoSizeChangedListener {
        public C2822a() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            IjkVideoView.this.f7999i = iMediaPlayer.getVideoWidth();
            IjkVideoView.this.f8000j = iMediaPlayer.getVideoHeight();
            IjkVideoView.this.f8014x = iMediaPlayer.getVideoSarNum();
            IjkVideoView.this.f8015y = iMediaPlayer.getVideoSarDen();
            if (IjkVideoView.this.f7999i != 0 && IjkVideoView.this.f8000j != 0) {
                if (IjkVideoView.this.f8013w != null) {
                    IjkVideoView.this.f8013w.setVideoSize(IjkVideoView.this.f7999i, IjkVideoView.this.f8000j);
                    IjkVideoView.this.f8013w.setVideoSampleAspectRatio(IjkVideoView.this.f8014x, IjkVideoView.this.f8015y);
                }
                IjkVideoView.this.requestLayout();
            }
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$b */
    /* loaded from: classes2.dex */
    public class C2823b implements IMediaPlayer.OnPreparedListener {
        public C2823b() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            IjkVideoView.this.f8016z = System.currentTimeMillis();
            IjkVideoView.this.f7995e = 2;
            if (IjkVideoView.this.f8005o != null) {
                IjkVideoView.this.f8005o.onPrepared(IjkVideoView.this.f7998h);
            }
            if (IjkVideoView.this.f8004n != null) {
                IjkVideoView.this.f8004n.setEnabled(true);
            }
            IjkVideoView.this.f7999i = iMediaPlayer.getVideoWidth();
            IjkVideoView.this.f8000j = iMediaPlayer.getVideoHeight();
            int i = IjkVideoView.this.f8007q;
            if (i != 0) {
                IjkVideoView.this.seekTo(i);
            }
            if (IjkVideoView.this.f7999i == 0 || IjkVideoView.this.f8000j == 0) {
                if (IjkVideoView.this.f7996f == 3) {
                    IjkVideoView.this.start();
                }
            } else if (IjkVideoView.this.f8013w != null) {
                IjkVideoView.this.f8013w.setVideoSize(IjkVideoView.this.f7999i, IjkVideoView.this.f8000j);
                IjkVideoView.this.f8013w.setVideoSampleAspectRatio(IjkVideoView.this.f8014x, IjkVideoView.this.f8015y);
                if (IjkVideoView.this.f8013w.shouldWaitForResize() && (IjkVideoView.this.f8001k != IjkVideoView.this.f7999i || IjkVideoView.this.f8002l != IjkVideoView.this.f8000j)) {
                    return;
                }
                if (IjkVideoView.this.f7996f == 3) {
                    IjkVideoView.this.start();
                    if (IjkVideoView.this.f8004n != null) {
                        IjkVideoView.this.f8004n.show();
                    }
                } else if (IjkVideoView.this.isPlaying()) {
                } else {
                    if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.f8004n != null) {
                        IjkVideoView.this.f8004n.show(0);
                    }
                }
            }
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$c */
    /* loaded from: classes2.dex */
    public class C2824c implements IMediaPlayer.OnCompletionListener {
        public C2824c() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            IjkVideoView.this.f7995e = 5;
            IjkVideoView.this.f7996f = 5;
            if (IjkVideoView.this.f8004n != null) {
                IjkVideoView.this.f8004n.hide();
            }
            if (IjkVideoView.this.f8005o != null) {
                IjkVideoView.this.f8005o.onCompletion(IjkVideoView.this.f7998h);
            }
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$d */
    /* loaded from: classes2.dex */
    public class C2825d implements IMediaPlayer.OnInfoListener {
        public C2825d() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (IjkVideoView.this.f8005o != null) {
                IjkVideoView.this.f8005o.onInfo(iMediaPlayer, i, i2);
            }
            if (i == 3) {
                Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_VIDEO_RENDERING_START:");
                return true;
            } else if (i == 901) {
                Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                return true;
            } else if (i == 902) {
                Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                return true;
            } else if (i == 10001) {
                IjkVideoView.this.f8003m = i2;
                String str = IjkVideoView.this.f7992b;
                Log.d(str, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                if (IjkVideoView.this.f8013w == null) {
                    return true;
                }
                IjkVideoView.this.f8013w.setVideoRotation(i2);
                return true;
            } else if (i != 10002) {
                switch (i) {
                    case 700:
                        Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                        return true;
                    case 701:
                        Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_BUFFERING_START:");
                        return true;
                    case 702:
                        Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_BUFFERING_END:");
                        return true;
                    case 703:
                        String str2 = IjkVideoView.this.f7992b;
                        Log.d(str2, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                        return true;
                    default:
                        switch (i) {
                            case 800:
                                Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_BAD_INTERLEAVING:");
                                return true;
                            case 801:
                                Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_NOT_SEEKABLE:");
                                return true;
                            case 802:
                                Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_METADATA_UPDATE:");
                                return true;
                            default:
                                return true;
                        }
                }
            } else {
                Log.d(IjkVideoView.this.f7992b, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$e */
    /* loaded from: classes2.dex */
    public class C2826e implements IMediaPlayer.OnErrorListener {
        public C2826e() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            String str = IjkVideoView.this.f7992b;
            Log.d(str, "Error: " + i + "," + i2);
            IjkVideoView.this.f7995e = -1;
            IjkVideoView.this.f7996f = -1;
            if (IjkVideoView.this.f8004n != null) {
                IjkVideoView.this.f8004n.hide();
            }
            if (IjkVideoView.this.f8005o == null || IjkVideoView.this.f8005o.onError(IjkVideoView.this.f7998h, i, i2)) {
            }
            return true;
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$f */
    /* loaded from: classes2.dex */
    public class C2827f implements IMediaPlayer.OnBufferingUpdateListener {
        public C2827f() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            IjkVideoView.this.f8006p = i;
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$g */
    /* loaded from: classes2.dex */
    public class C2828g implements IMediaPlayer.OnSeekCompleteListener {
        public C2828g() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            IjkVideoView.this.f7978A = System.currentTimeMillis();
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$h */
    /* loaded from: classes2.dex */
    public class C2829h implements IMediaPlayer.OnTimedTextListener {
        public C2829h() {
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnTimedTextListener
        public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {
            if (ijkTimedText != null) {
                IjkVideoView.this.f7979B.setText(ijkTimedText.getText());
            }
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$j */
    /* loaded from: classes2.dex */
    public static class C2831j implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener {
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

    public IjkVideoView(Context context) {
        super(context);
        m14459a(context);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.f8008r;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.f8009s;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.f8010t;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.f7998h != null) {
            return this.f8006p;
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (m14442d()) {
            return (int) this.f7998h.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (m14442d()) {
            return (int) this.f7998h.getDuration();
        }
        return -1;
    }

    @TargetApi(15)
    public Bitmap getShortcut() {
        Bitmap bitmap;
        IRenderView bVar = this.f8013w;
        if (!(bVar instanceof TextureRenderView)) {
            return null;
        }
        TextureRenderView textureRenderView = (TextureRenderView) bVar;
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

    public ITrackInfo[] getTrackInfo() {
        IMediaPlayer iMediaPlayer = this.f7998h;
        if (iMediaPlayer == null) {
            return null;
        }
        return iMediaPlayer.getTrackInfo();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return m14442d() && this.f7998h.isPlaying();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (m14442d() && z && this.f8004n != null) {
            if (i == 79 || i == 85) {
                if (this.f7998h.isPlaying()) {
                    pause();
                    this.f8004n.show();
                } else {
                    start();
                    this.f8004n.hide();
                }
                return true;
            } else if (i == 126) {
                if (!this.f7998h.isPlaying()) {
                    start();
                    this.f8004n.hide();
                }
                return true;
            } else if (i == 86 || i == 127) {
                if (this.f7998h.isPlaying()) {
                    pause();
                    this.f8004n.show();
                }
                return true;
            } else {
                m14430h();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!m14442d() || this.f8004n == null) {
            return false;
        }
        m14430h();
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!m14442d() || this.f8004n == null) {
            return false;
        }
        m14430h();
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (m14442d() && this.f7998h.isPlaying()) {
            this.f7998h.pause();
            this.f7995e = 4;
        }
        this.f7996f = 4;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (m14442d()) {
            System.currentTimeMillis();
            this.f7998h.seekTo(i);
            this.f8007q = 0;
            return;
        }
        this.f8007q = i;
    }

    public void setMediaController(IMediaController aVar) {
        IMediaController aVar2 = this.f8004n;
        if (aVar2 != null) {
            aVar2.hide();
        }
        this.f8004n = aVar;
        m14460a();
    }

    public void setMute(boolean z) {
        this.f8011u = z;
        IMediaPlayer iMediaPlayer = this.f7998h;
        if (iMediaPlayer == null) {
            return;
        }
        if (z) {
            iMediaPlayer.setVolume(0.0f, 0.0f);
        } else {
            iMediaPlayer.setVolume(1.0f, 1.0f);
        }
    }

    public void setOnPlayStateListener(C2831j jVar) {
        this.f8005o = jVar;
    }

    public void setRender(int i) {
        if (i == 0) {
            setRenderView(null);
        } else if (i == 1) {
            setRenderView(new SurfaceRenderView(getContext()));
        } else if (i != 2) {
            Log.e(this.f7992b, String.format(Locale.getDefault(), "invalid render %d\n", Integer.valueOf(i)));
        } else {
            TextureRenderView textureRenderView = new TextureRenderView(getContext());
            if (this.f7998h != null) {
                textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.f7998h);
                textureRenderView.setVideoSize(this.f7998h.getVideoWidth(), this.f7998h.getVideoHeight());
                textureRenderView.setVideoSampleAspectRatio(this.f7998h.getVideoSarNum(), this.f7998h.getVideoSarDen());
                textureRenderView.setAspectRatio(this.f7989L);
            }
            setRenderView(textureRenderView);
        }
    }

    public void setRenderView(IRenderView bVar) {
        int i;
        int i2;
        if (this.f8013w != null) {
            IMediaPlayer iMediaPlayer = this.f7998h;
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(null);
            }
            View view = this.f8013w.getView();
            this.f8013w.removeRenderCallback(this.f7988K);
            this.f8013w = null;
            removeView(view);
        }
        if (bVar != null) {
            this.f8013w = bVar;
            bVar.setAspectRatio(this.f7989L);
            int i3 = this.f7999i;
            if (i3 > 0 && (i2 = this.f8000j) > 0) {
                bVar.setVideoSize(i3, i2);
            }
            int i4 = this.f8014x;
            if (i4 > 0 && (i = this.f8015y) > 0) {
                bVar.setVideoSampleAspectRatio(i4, i);
            }
            View view2 = this.f8013w.getView();
            view2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
            addView(view2);
            this.f8013w.addRenderCallback(this.f7988K);
            this.f8013w.setVideoRotation(this.f8003m);
        }
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        m14458a(uri, (Map<String, String>) null);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (m14442d()) {
            this.f7998h.start();
            this.f7995e = 3;
        }
        this.f7996f = 3;
    }

    /* renamed from: c */
    public final void m14445c() {
        this.f7990M.clear();
        this.f7990M.add(1);
        this.f7990M.get(this.f7991N).intValue();
        setRender(2);
    }

    /* renamed from: d */
    public final boolean m14442d() {
        int i;
        return (this.f7998h == null || (i = this.f7995e) == -1 || i == 0 || i == 1) ? false : true;
    }

    @TargetApi(23)
    /* renamed from: e */
    public final void m14439e() {
        if (this.f7993c != null && this.f7997g != null) {
            m14450a(false);
            ((AudioManager) this.f8012v.getSystemService(Context.AUDIO_SERVICE)).requestAudioFocus(null, 3, 1);
            try {
                this.f7998h = m14449b();
                getContext();
                this.f7998h.setOnPreparedListener(this.f7981D);
                this.f7998h.setOnVideoSizeChangedListener(this.f7980C);
                this.f7998h.setOnCompletionListener(this.f7982E);
                this.f7998h.setOnErrorListener(this.f7984G);
                this.f7998h.setOnInfoListener(this.f7983F);
                this.f7998h.setOnBufferingUpdateListener(this.f7985H);
                this.f7998h.setOnSeekCompleteListener(this.f7986I);
                this.f7998h.setOnTimedTextListener(this.f7987J);
                this.f8006p = 0;
                this.f7993c.getScheme();
                if (Build.VERSION.SDK_INT >= 14) {
                    this.f7998h.setDataSource(this.f8012v, this.f7993c, this.f7994d);
                } else {
                    this.f7998h.setDataSource(this.f7993c.toString());
                }
                m14452a(this.f7998h, this.f7997g);
                this.f7998h.setAudioStreamType(3);
                this.f7998h.setScreenOnWhilePlaying(true);
                System.currentTimeMillis();
                this.f7998h.prepareAsync();
                setMute(this.f8011u);
                this.f7995e = 1;
                m14460a();
            } catch (IOException e) {
                String str = this.f7992b;
                Log.w(str, "Unable to open content: " + this.f7993c, e);
                this.f7995e = -1;
                this.f7996f = -1;
                this.f7984G.onError(this.f7998h, 1, 0);
            } catch (IllegalArgumentException e2) {
                String str2 = this.f7992b;
                Log.w(str2, "Unable to open content: " + this.f7993c, e2);
                this.f7995e = -1;
                this.f7996f = -1;
                this.f7984G.onError(this.f7998h, 1, 0);
            }
        }
    }

    /* renamed from: f */
    public void m14436f() {
        IMediaPlayer iMediaPlayer = this.f7998h;
        if (iMediaPlayer != null) {
            iMediaPlayer.setDisplay(null);
        }
    }

    /* renamed from: g */
    public void m14433g() {
        IMediaPlayer iMediaPlayer = this.f7998h;
        if (iMediaPlayer != null) {
            iMediaPlayer.stop();
            this.f7998h.release();
            this.f7998h = null;
            this.f7995e = 0;
            this.f7996f = 0;
            ((AudioManager) this.f8012v.getSystemService(Context.AUDIO_SERVICE)).abandonAudioFocus(null);
        }
    }

    /* renamed from: h */
    public final void m14430h() {
        if (this.f8004n.isShowing()) {
            this.f8004n.hide();
        } else {
            this.f8004n.show();
        }
    }

    /* renamed from: b */
    public IMediaPlayer m14449b() {
        if (this.f7993c == null) {
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

    /* renamed from: a */
    public final void m14459a(Context context) {
        this.f8012v = context.getApplicationContext();
        m14445c();
        this.f7999i = 0;
        this.f8000j = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f7995e = 0;
        this.f7996f = 0;
        this.f7979B = new TextView(context);
        this.f7979B.setTextSize(24.0f);
        this.f7979B.setGravity(17);
        addView(this.f7979B, new FrameLayout.LayoutParams(-1, -2, 80));
    }

    /* renamed from: com.slzhibo.library.ui.view.ijkplayer.IjkVideoView$i */
    /* loaded from: classes2.dex */
    public class C2830i implements IRenderView.IRenderCallback {
        public C2830i() {
        }

        @Override
        public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder holder, int format, int width, int height) {
            if (holder.getRenderView() != IjkVideoView.this.f8013w) {
                Log.e(IjkVideoView.this.f7992b, "onSurfaceChanged: unmatched render callback\n");
                return;
            }
            IjkVideoView.this.f8001k = width;
            IjkVideoView.this.f8002l = height;
            boolean z = true;
            boolean z2 = IjkVideoView.this.f7996f == 3;
            if (IjkVideoView.this.f8013w.shouldWaitForResize() && !(IjkVideoView.this.f7999i == width && IjkVideoView.this.f8000j == height)) {
                z = false;
            }
            if (IjkVideoView.this.f7998h != null && z2 && z) {
                if (IjkVideoView.this.f8007q != 0) {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.seekTo(ijkVideoView.f8007q);
                }
                IjkVideoView.this.start();
            }
        }

        @Override
        public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder holder, int width, int height) {
            if (holder.getRenderView() != IjkVideoView.this.f8013w) {
                Log.e(IjkVideoView.this.f7992b, "onSurfaceCreated: unmatched render callback\n");
                return;
            }
            IjkVideoView.this.f7997g = holder;
            if (IjkVideoView.this.f7998h != null) {
                IjkVideoView ijkVideoView = IjkVideoView.this;
                ijkVideoView.m14452a(ijkVideoView.f7998h, holder);
                return;
            }
            IjkVideoView.this.m14439e();
        }



        @Override
        public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder holder) {
            if (holder.getRenderView() != IjkVideoView.this.f8013w) {
                Log.e(IjkVideoView.this.f7992b, "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }
            IjkVideoView.this.f7997g = null;
            IjkVideoView.this.m14436f();
        }
    }

    /* renamed from: a */
    public final void m14458a(Uri uri, Map<String, String> map) {
        this.f7993c = uri;
        this.f7994d = map;
        this.f8007q = 0;
        m14439e();
        requestLayout();
        invalidate();
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14459a(context);
    }

    /* renamed from: a */
    public final void m14460a() {
        IMediaController aVar;
        if (this.f7998h != null && (aVar = this.f8004n) != null) {
            aVar.setMediaPlayer(this);
            this.f8004n.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
            this.f8004n.setEnabled(m14442d());
        }
    }

    /* renamed from: a */
    public final void m14452a(IMediaPlayer iMediaPlayer, IRenderView.ISurfaceHolder bVar) {
        if (iMediaPlayer != null) {
            if (bVar == null) {
                iMediaPlayer.setDisplay(null);
            } else {
                bVar.bindToMediaPlayer(iMediaPlayer);
            }
        }
    }

    /* renamed from: a */
    public void m14450a(boolean z) {
        IMediaPlayer iMediaPlayer = this.f7998h;
        if (iMediaPlayer != null) {
            iMediaPlayer.reset();
            this.f7998h.release();
            this.f7998h = null;
            this.f7995e = 0;
            if (z) {
                this.f7996f = 0;
            }
            ((AudioManager) this.f8012v.getSystemService(Context.AUDIO_SERVICE)).abandonAudioFocus(null);
        }
    }

    /* renamed from: a */
    public void m14451a(String str) {
        setVideoPath(str);
        setRender(2);
        start();
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14459a(context);
    }
}
