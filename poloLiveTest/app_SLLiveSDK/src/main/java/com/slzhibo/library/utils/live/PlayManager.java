package com.slzhibo.library.utils.live;







import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.boluouitest2zhibo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slzhibo.library.R;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.ui.view.ijkplayer.IjkVideoView;
import com.slzhibo.library.ui.view.widget.RoundRelativeLayout;
import com.slzhibo.library.utils.AnimUtils;
import com.slzhibo.library.utils.LogManager;
import com.slzhibo.library.utils.NumberUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
//import tv.danmaku.ijk.media.player.IMediaPlayer;
//import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import com.tomato.ijk.media.player.IMediaPlayer;
import com.tomato.ijk.media.player.IjkMediaPlayer;
//import tv.danmaku.ijk.media.player.IMediaPlayer;
//import tv.danmaku.ijk.media.player.IjkMediaPlayer;



/* loaded from: classes6.dex */
public class PlayManager {
    private final int[] childLocal = new int[2];
    private int currentPlayIndex = 0;
    private final AtomicInteger ijkOnErrorCount = new AtomicInteger(0);
    private final AtomicBoolean ijkOnPlaySuccessFlag = new AtomicBoolean(false);
    private boolean isFromRecyclerView;
    private ImageView ivCover;
    private OnPlayListener listener;
    private final Context mContext;
    private RoundRelativeLayout mCurrentPlayView;
    private IjkVideoView mIjkplayerView;
    private final IjkVideoView.OnPlayStateListener onPlayStateListener = new IjkVideoView.OnPlayStateListener() {
        /* class com.slzhibo.library.utils.live.PlayManager.AnonymousClass2 */

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnInfoListener, com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.OnPlayStateListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            LogManager.m1188t("ijk 拉流监听 :" + i + ",extra = " + i2);
            if (i == 3 || i == 10002) {
                if (!PlayManager.this.ijkOnPlaySuccessFlag.get()) {
                    PlayManager.this.ijkOnErrorCount.set(0);
                    PlayManager.this.showPlayerView();
                    if (PlayManager.this.isFromRecyclerView && PlayManager.this.ivCover != null) {
                        AnimUtils.playHideAnimation(PlayManager.this.ivCover, 800);
                    }
                    if (PlayManager.this.listener != null) {
                        PlayManager.this.listener.onPlaySuccess();
                    }
                    PlayManager.this.ijkOnPlaySuccessFlag.set(true);
                }
            } else if (i == 701) {
                if (PlayManager.this.isFromRecyclerView) {
                    PlayManager.this.hidePlayerView();
                }
                if (PlayManager.this.listener != null) {
                    PlayManager.this.listener.onStartBuffering();
                }
            } else if (i == 702) {
                PlayManager.this.showPlayerView();
                if (PlayManager.this.isFromRecyclerView && PlayManager.this.ivCover != null) {
                    AnimUtils.playHideAnimation(PlayManager.this.ivCover, 800);
                }
                if (PlayManager.this.listener != null) {
                    PlayManager.this.listener.onEndBuffering();
                }
            }
            return true;
        }

        @Override // com.tomato.ijk.media.player.IMediaPlayer.OnErrorListener, com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.OnPlayStateListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            LogManager.m1188t("ijk onError ===> what = " + i + ",extra = " + i2);
            if (PlayManager.this.ijkOnErrorCount.get() > 3) {
                if (PlayManager.this.listener != null) {
                    PlayManager.this.listener.onNetError();
                }
            } else if (PlayManager.this.listener != null) {
                PlayManager.this.listener.resetPlay();
            }
            PlayManager.this.ijkOnErrorCount.incrementAndGet();
            return true;
        }
    };
    private final int[] parentLocal = new int[2];
    private int playPosition = -1;
    private RecyclerView recyclerView;
    private final ArrayList<Integer> videoPositionList = new ArrayList<>();

    public interface OnPlayListener {
        void onEndBuffering();

        void onNetError();

        void onPlaySuccess();

        void onScreenshot(Bitmap bitmap);

        void onStartBuffering();

        void resetPlay();
    }

    public PlayManager(Context context) {
        new AtomicInteger(0);
        this.mContext = context;
    }

    public void initRoomPlayManager(ViewGroup viewGroup, String str) {
        this.isFromRecyclerView = false;
        if (viewGroup != null) {
            initIjkVideoView();
            viewGroup.addView(this.mIjkplayerView, 0, new ViewGroup.LayoutParams(-1, -1));
            startPlayWithListener(str);
        }
    }

    public void initRecyclerViewPlayManager(RecyclerView recyclerView2) {
        this.recyclerView = recyclerView2;
        this.isFromRecyclerView = true;
        initIjkVideoView();
    }

    private void initIjkVideoView() {
        Observable.just(true).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() {
            /* class com.slzhibo.library.utils.live.PlayManager.AnonymousClass1 */

            public void accept(Boolean bool) throws Exception {
                try {
                    IjkMediaPlayer.loadLibrariesOnce(null);
                    IjkMediaPlayer.native_profileBegin("libfqplayer.so");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.mIjkplayerView = new IjkVideoView(this.mContext);
        if (!this.isFromRecyclerView) {
            this.mIjkplayerView.setMute(false);
        }
    }

    public void setOnPlayListener(OnPlayListener onPlayListener) {
        this.listener = onPlayListener;
    }

    public OnPlayListener getListener() {
        return this.listener;
    }

    public void startPlayWithListener(String str) {
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            ijkVideoView.setOnPlayStateListener(this.onPlayStateListener);
            this.mIjkplayerView.setVideoPath(str);
            this.mIjkplayerView.start();
        }
    }

    public void switchStream(String str) {
        this.ijkOnPlaySuccessFlag.set(false);
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            ijkVideoView.switchStream(str);
        }
    }

    public void onScreenshot() {
        OnPlayListener onPlayListener;
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null && (onPlayListener = this.listener) != null) {
            onPlayListener.onScreenshot(ijkVideoView.getShortcut());
        }
    }

    public void resumePlay(boolean z) {
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null && z) {
            ijkVideoView.start();
        }
    }

    public void pausePlay() {
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null && ijkVideoView.isPlaying()) {
            this.mIjkplayerView.pause();
        }
    }

    public void stopLastPlay() {
        this.ijkOnPlaySuccessFlag.set(false);
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            ijkVideoView.stopPlayback();
        }
    }

    private void clearAnimationAndSetVisible(View view) {
        if (view != null) {
            if (view.getAnimation() != null) {
                view.getAnimation().setAnimationListener(null);
            }
            view.clearAnimation();
            view.setVisibility(View.VISIBLE);
        }
    }

    public void stopPlay() {
        ViewParent parent;
        this.ijkOnPlaySuccessFlag.set(false);
        ImageView imageView = this.ivCover;
        if (imageView != null) {
            clearAnimationAndSetVisible(imageView);
        }
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            if (this.isFromRecyclerView && (parent = ijkVideoView.getParent()) != null && (parent instanceof RelativeLayout)) {
                ((RelativeLayout) parent).removeView(this.mIjkplayerView);
            }
            this.mIjkplayerView.stopPlayback();
        }
        this.listener = null;
        this.mCurrentPlayView = null;
    }

    public void onDestroyPlay() {
        stopPlay();
        if (this.mIjkplayerView != null) {
            Observable.just(true).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() {
                /* class com.slzhibo.library.utils.live.PlayManager.AnonymousClass3 */

                public void accept(Boolean bool) throws Exception {
                    try {
                        IjkMediaPlayer.native_profileEnd();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void hidePlayerView() {
        ImageView imageView;
        if (this.isFromRecyclerView && (imageView = this.ivCover) != null) {
            clearAnimationAndSetVisible(imageView);
        }
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            ijkVideoView.setVisibility(View.INVISIBLE);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showPlayerView() {
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            ijkVideoView.setVisibility(View.VISIBLE);
        }
    }

    public void releaseLivePlay() {
        stopLastPlay();
        this.listener = null;
    }

    public void onRecyclerViewResume() {
        ImageView imageView = this.ivCover;
        if (imageView != null) {
            clearAnimationAndSetVisible(imageView);
        }
        refreshVideo();
    }

    public void onRecyclerViewPause() {
        stopPlay();
    }

    public void refreshVideo() {
        checkPlayVideo();
        playVideoByPosition();
    }

    public void onScrolled() {
        RoundRelativeLayout roundRelativeLayout = this.mCurrentPlayView;
        if (roundRelativeLayout != null && !isPlayRange(roundRelativeLayout, this.recyclerView)) {
            stopPlay();
        }
    }

    public void onScrollStateChanged() {
        if (this.mCurrentPlayView == null) {
            checkPlayVideo();
            playVideoByPosition();
        }
    }

    private boolean isPlayRange(View view, View view2) {
        if (view == null || view2 == null) {
            return false;
        }
        view.getLocationOnScreen(this.childLocal);
        view2.getLocationOnScreen(this.parentLocal);
        int[] iArr = this.childLocal;
        int i = iArr[1];
        int[] iArr2 = this.parentLocal;
        return i >= iArr2[1] && iArr[1] <= (iArr2[1] + view2.getHeight()) - view.getHeight();
    }

    private void checkPlayVideo() {
        LiveEntity liveEntity;
        this.currentPlayIndex = 0;
        this.videoPositionList.clear();
        BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
        int itemCount = baseQuickAdapter.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (isPlayRange(baseQuickAdapter.getViewByPosition(i, R.id.sq_root), this.recyclerView) && (liveEntity = (LiveEntity) baseQuickAdapter.getItem(i - baseQuickAdapter.getHeaderLayoutCount())) != null && liveEntity.isCoverPreview() && i >= 0 && !this.videoPositionList.contains(Integer.valueOf(i))) {
                this.videoPositionList.add(Integer.valueOf(i));
            }
        }
        if (this.videoPositionList.size() > 1) {
            this.currentPlayIndex = NumberUtils.getIntRandom(this.videoPositionList.size());
        }
    }

    private void playVideoByPosition() {
        if (this.videoPositionList.size() != 0) {
            playVideoByPosition(this.videoPositionList.get(this.currentPlayIndex).intValue());
        }
    }

    public void playVideoByPosition(int i) {
        if (this.playPosition != i) {
            stopPlay();
            BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.findViewHolderForAdapterPosition(i);
            if (baseViewHolder != null) {
                BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
                LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getData().get(i - baseQuickAdapter.getHeaderLayoutCount());
                if (liveEntity != null) {
                    this.playPosition = i;
                    if (!liveEntity.isCoverPreview()) {
                        refreshVideo();
                        return;
                    }
                    this.mCurrentPlayView = (RoundRelativeLayout) baseViewHolder.getView(R.id.sq_root);
                    this.ivCover = (ImageView) baseViewHolder.getView(R.id.iv_cover);
                    IjkVideoView ijkVideoView = this.mIjkplayerView;
                    if (ijkVideoView != null) {
                        ijkVideoView.setMute(true);
                        this.mCurrentPlayView.addView(this.mIjkplayerView, 0, new ViewGroup.LayoutParams(-1, -1));
                        startPlayWithListener(liveEntity.getDefPullStreamUrlStr());
                    }
                }
            }
        }
    }

    public void stopRTMPStream() {
        this.ijkOnPlaySuccessFlag.set(false);
        this.mIjkplayerView.setVisibility(View.GONE);
        this.mIjkplayerView.stopPlayback();
    }

    public void startRTMPStream(String str) {
        this.mIjkplayerView.setVisibility(View.VISIBLE);
        switchStream(str);
    }

//    private static final int IJK_ERROR = -10000;
//    private boolean isFromRecyclerView;
//    private ImageView ivCover;
//    private OnPlayListener listener;
//    private Context mContext;
//    private RoundRelativeLayout mCurrentPlayView;
//    private IjkVideoView mIjkplayerView;
//    private RecyclerView recyclerView;
//    private AtomicInteger slowCount = new AtomicInteger(0);
//    private ArrayList<Integer> videoPositionList = new ArrayList<>();
//    private int currentPlayIndex = 0;
//    private int playPosition = -1;
//    private AtomicInteger ijkOnErrorCount = new AtomicInteger(0);
//    private AtomicBoolean ijkOnPlaySuccessFlag = new AtomicBoolean(false);
//    private IjkVideoView.OnPlayStateListener onPlayStateListener = new IjkVideoView.OnPlayStateListener() { // from class: com.slzhibo.library.utils.live.PlayManager.2
//        @Override // com.slzhibo.library.p115ui.view.ijkplayer.IjkVideoView.OnPlayStateListener, com.tomato.ijk.media.player.IMediaPlayer.OnInfoListener
//        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
//            LogManager.m1188t("ijk 拉流监听 :" + i + ",extra = " + i2);
//            if (i == 3 || i == 10002) {
//                PlayManager.this.ijkOnErrorCount.set(0);
//                if (!PlayManager.this.ijkOnPlaySuccessFlag.get()) {
//                    PlayManager.this.showPlayerView();
//                    if (PlayManager.this.isFromRecyclerView && PlayManager.this.ivCover != null) {
//                        AnimUtils.playHideAnimation(PlayManager.this.ivCover, 800L);
//                    }
//                    if (PlayManager.this.listener != null) {
//                        PlayManager.this.listener.onPlaySuccess();
//                    }
//                    PlayManager.this.ijkOnPlaySuccessFlag.set(true);
//                }
//            } else if (i == 701) {
//                if (PlayManager.this.isFromRecyclerView) {
//                    PlayManager.this.hidePlayerView();
//                }
//                if (PlayManager.this.listener != null) {
//                    PlayManager.this.listener.onStartBuffering();
//                }
//            } else if (i == 702) {
//                PlayManager.this.showPlayerView();
//                if (PlayManager.this.isFromRecyclerView && PlayManager.this.ivCover != null) {
//                    AnimUtils.playHideAnimation(PlayManager.this.ivCover, 800L);
//                }
//                if (PlayManager.this.listener != null) {
//                    PlayManager.this.listener.onEndBuffering();
//                }
//            }
//            return true;
//        }
//
//        @Override // com.slzhibo.library.p115ui.view.ijkplayer.IjkVideoView.OnPlayStateListener, com.tomato.ijk.media.player.IMediaPlayer.OnErrorListener
//        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
//            LogManager.m1188t("ijk onError ===> what = " + i + ",extra = " + i2);
//            if (!PlayManager.this.isFromRecyclerView || PlayManager.this.mIjkplayerView == null) {
//                if (PlayManager.this.ijkOnErrorCount.get() > 3) {
//                    if (PlayManager.this.listener != null) {
//                        PlayManager.this.listener.onNetError();
//                    }
//                } else if (PlayManager.this.listener != null) {
//                    PlayManager.this.listener.resetPlay();
//                }
//                PlayManager.this.ijkOnErrorCount.incrementAndGet();
//                return true;
//            }
//            PlayManager.this.mIjkplayerView.release(false);
//            return false;
//        }
//    };
//
//    /* loaded from: classes6.dex */
//    public interface OnPlayListener {
//        void onEndBuffering();
//
//        void onNetError();
//
//        void onPlayError();
//
//        void onPlaySuccess();
//
//        void onScreenshot(Bitmap bitmap);
//
//        void onStartBuffering();
//
//        void resetPlay();
//    }
//
//    public PlayManager(Context context) {
//        this.mContext = context;
//    }
//
//    public void initRoomPlayManager(ViewGroup viewGroup, String str) {
//        this.isFromRecyclerView = false;
//        if (viewGroup != null) {
//            initIjkVideoView();
//            viewGroup.addView(this.mIjkplayerView, 0, new ViewGroup.LayoutParams(-1, -1));
//            if (!TextUtils.isEmpty(str)) {
//                startPlayWithListener(str);
//            }
//        }
//    }
//
//    public void playVideoCallOut(ViewGroup viewGroup, String str) {
//        this.isFromRecyclerView = false;
//        if (viewGroup != null) {
//            initIjkVideoView();
//            this.mIjkplayerView.setVideoFitParent();
//            viewGroup.addView(this.mIjkplayerView, 0, new ViewGroup.LayoutParams(-1, -1));
//            setLoop(true);
//            setMute(true);
//            startPlayWithListener(str);
//        }
//    }
//
//    public void setLoop(boolean z) {
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.setLoop(z);
//        }
//    }
//
//    public void setMute(boolean z) {
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.setMute(z);
//        }
//    }
//
//    public void initRecyclerViewPlayManager(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//        this.isFromRecyclerView = true;
//        initIjkVideoView();
//    }
//
//    private void initIjkVideoView() {
//        Observable.just(true).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() { // from class: com.slzhibo.library.utils.live.PlayManager.1
//            public void accept(Boolean bool) throws Exception {
//                try {
//                    IjkMediaPlayer.loadLibrariesOnce(null);
//                    IjkMediaPlayer.native_profileBegin("libfqplayer.so");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        this.mIjkplayerView = new IjkVideoView(this.mContext);
//        setUsingMediaCodec(SysConfigInfoManager.getInstance().isDecodingTypeHardware());
//        this.mIjkplayerView.setUsingMediaCodecAutoRotate(true);
//        if (!this.isFromRecyclerView) {
//            setMute(false);
//        }
//    }
//
//    public void setOnPlayListener(OnPlayListener onPlayListener) {
//        this.listener = onPlayListener;
//    }
//
//    public OnPlayListener getListener() {
//        return this.listener;
//    }
//
//    public void startPlayWithListener(String str) {
//        LogManager.m1188t("ijk rtmpUrl :" + str);
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.setOnPlayStateListener(this.onPlayStateListener);
//            this.mIjkplayerView.setVideoPath(str);
//            this.mIjkplayerView.start();
//        }
//    }
//
//    public void switchStream(String str) {
//        LogManager.m1188t("ijk rtmpUrl :" + str);
//        this.ijkOnPlaySuccessFlag.set(false);
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            if (ijkVideoView.getOnPlayStateListener() == null) {
//                this.mIjkplayerView.setOnPlayStateListener(this.onPlayStateListener);
//            }
//            this.mIjkplayerView.switchStream(str);
//        }
//    }
//
//    public void onScreenshot() {
//        OnPlayListener onPlayListener;
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null && (onPlayListener = this.listener) != null) {
//            onPlayListener.onScreenshot(ijkVideoView.getShortcut());
//        }
//    }
//
//    public void resumePlay(boolean z) {
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null && z) {
//            ijkVideoView.start();
//        }
//    }
//
//    public void pausePlay() {
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null && ijkVideoView.isPlaying()) {
//            this.mIjkplayerView.pause();
//        }
//    }
//
//    public void stopPlayByLiveMode() {
//        this.ijkOnPlaySuccessFlag.set(false);
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.stopPlayback();
//        }
//    }
//
//    private void clearAnimationAndSetVisible(View view) {
//        if (view != null) {
//            if (view.getAnimation() != null) {
//                view.getAnimation().setAnimationListener(null);
//            }
//            view.clearAnimation();
//            view.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public void stopPlayByRecyclerMode() {
//        ViewParent parent;
//        this.ijkOnPlaySuccessFlag.set(false);
//        ImageView imageView = this.ivCover;
//        if (imageView != null) {
//            clearAnimationAndSetVisible(imageView);
//        }
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null && this.isFromRecyclerView && (parent = ijkVideoView.getParent()) != null && (parent instanceof RelativeLayout)) {
//            ((RelativeLayout) parent).removeView(this.mIjkplayerView);
//            if (this.mIjkplayerView.isPlaying()) {
//                LogManager.m1188t("stopPlayByRecyclerMode >>>release  ");
//                this.mIjkplayerView.stopPlayback();
//            }
//        }
//        this.listener = null;
//        this.mCurrentPlayView = null;
//        this.playPosition = -1;
//    }
//
//    public void onDestroyPlay() {
//        if (this.isFromRecyclerView) {
//            stopPlayByRecyclerMode();
//        } else {
//            releaseLivePlay();
//        }
//        if (this.mIjkplayerView != null) {
//            Observable.just(true).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() { // from class: com.slzhibo.library.utils.live.PlayManager.3
//                public void accept(Boolean bool) throws Exception {
//                    try {
//                        IjkMediaPlayer.native_profileEnd();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void hidePlayerView() {
//        ImageView imageView;
//        if (this.isFromRecyclerView && (imageView = this.ivCover) != null) {
//            clearAnimationAndSetVisible(imageView);
//        }
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showPlayerView() {
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public void releaseLivePlay() {
//        stopPlayByLiveMode();
//        this.listener = null;
//    }
//
//    public void onRecyclerViewResume() {
//        ImageView imageView = this.ivCover;
//        if (imageView != null) {
//            clearAnimationAndSetVisible(imageView);
//        }
//        refreshVideo();
//    }
//
//    public void onRecyclerViewPause() {
//        stopPlayByRecyclerMode();
//    }
//
//    private void refreshVideo() {
//        checkPlayVideo();
//        playVideoByPosition();
//    }
//
//    public void onScrolled() {
//        ArrayList<Integer> arrayList;
//        if (this.mCurrentPlayView != null && (arrayList = this.videoPositionList) != null && !arrayList.isEmpty() && !isPlayRange(getRecyclerViewAdapterChildView(getRecyclerViewAdapter(), this.videoPositionList.get(this.currentPlayIndex).intValue()), this.recyclerView)) {
//            stopPlayByRecyclerMode();
//        }
//    }
//
//    public void onScrollStateChanged() {
//        if (this.mCurrentPlayView == null) {
//            checkPlayVideo();
//            playVideoByPosition();
//        }
//    }
//
//    private boolean isPlayRange(View view, View view2) {
//        if (view == null || view2 == null) {
//            return false;
//        }
//        int height = view.getHeight();
//        Rect rect = new Rect();
//        view.getLocalVisibleRect(rect);
//        return rect.top == 0 && rect.bottom == height;
//    }
//
//    private void checkPlayVideo() {
//        this.currentPlayIndex = 0;
//        this.videoPositionList.clear();
//        BaseQuickAdapter recyclerViewAdapter = getRecyclerViewAdapter();
//        if (recyclerViewAdapter != null) {
//            int itemCount = recyclerViewAdapter.getItemCount();
//            for (int i = 0; i < itemCount; i++) {
//                LiveEntity recyclerViewAdapterItem = getRecyclerViewAdapterItem(recyclerViewAdapter, i);
//                if (recyclerViewAdapterItem != null && recyclerViewAdapterItem.isCoverPreview() && isPlayRange(getRecyclerViewAdapterChildView(recyclerViewAdapter, i), this.recyclerView) && i >= 0 && !this.videoPositionList.contains(Integer.valueOf(i))) {
//                    this.videoPositionList.add(Integer.valueOf(i));
//                }
//            }
//            if (this.videoPositionList.size() > 1) {
//                this.currentPlayIndex = NumberUtils.getIntRandom(this.videoPositionList.size());
//            }
//        }
//    }
//
//    private void playVideoByPosition() {
//        ArrayList<Integer> arrayList = this.videoPositionList;
//        if (arrayList != null && !arrayList.isEmpty()) {
//            playVideoByPosition(this.videoPositionList.get(this.currentPlayIndex).intValue());
//        }
//    }
//
//    private BaseQuickAdapter getRecyclerViewAdapter() {
//        RecyclerView recyclerView = this.recyclerView;
//        if (recyclerView == null) {
//            return null;
//        }
//        RecyclerView.Adapter adapter = recyclerView.getAdapter();
//        if (adapter instanceof BaseQuickAdapter) {
//            return (BaseQuickAdapter) adapter;
//        }
//        return null;
//    }
//
//    private LiveEntity getRecyclerViewAdapterItem(BaseQuickAdapter baseQuickAdapter, int i) {
//        if (baseQuickAdapter == null) {
//            return null;
//        }
//        try {
//            return (LiveEntity) baseQuickAdapter.getItem(i - baseQuickAdapter.getHeaderLayoutCount());
//        } catch (Exception unused) {
//            return null;
//        }
//    }
//
//    private View getRecyclerViewAdapterChildView(BaseQuickAdapter baseQuickAdapter, int i) {
//        if (baseQuickAdapter == null) {
//            return null;
//        }
//        return baseQuickAdapter.getViewByPosition(i, R.id.sq_root);
//    }
//
//    public void playVideoByPosition(int i) {
//        LiveEntity recyclerViewAdapterItem;
//        if (this.playPosition != i) {
//            stopPlayByRecyclerMode();
//            BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.findViewHolderForAdapterPosition(i);
//            if (baseViewHolder != null && (recyclerViewAdapterItem = getRecyclerViewAdapterItem(getRecyclerViewAdapter(), i)) != null) {
//                this.playPosition = i;
//                if (!recyclerViewAdapterItem.isCoverPreview()) {
//                    refreshVideo();
//                    return;
//                }
//                this.mCurrentPlayView = (RoundRelativeLayout) baseViewHolder.getView(R.id.sq_root);
//                this.ivCover = (ImageView) baseViewHolder.getView(R.id.iv_cover);
//                IjkVideoView ijkVideoView = this.mIjkplayerView;
//                if (ijkVideoView != null && this.mCurrentPlayView != null) {
//                    ijkVideoView.setMute(true);
//                    this.mCurrentPlayView.addView(this.mIjkplayerView, 0, new ViewGroup.LayoutParams(-1, -1));
//                    startPlayWithListener(recyclerViewAdapterItem.getDefPullStreamUrlStr());
//                }
//            }
//        }
//    }
//
//    public void stopRTMPStream() {
//        this.ijkOnPlaySuccessFlag.set(false);
//        this.mIjkplayerView.setVisibility(View.GONE);
//        this.mIjkplayerView.stopPlayback();
//    }
//
//    public void startRTMPStream(String str) {
//        this.mIjkplayerView.setVisibility(View.VISIBLE);
//        switchStream(str);
//    }
//
//    public void setUsingMediaCodec(boolean z) {
//        if (this.mIjkplayerView == null) {
//            return;
//        }
//        if (!SysConfigInfoManager.getInstance().isPlayH265Video()) {
//            this.mIjkplayerView.setUsingMediaCodec(false);
//        } else {
//            this.mIjkplayerView.setUsingMediaCodec(z);
//        }
//    }
}




