package com.blmvl.blvl;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.blmvl.blvl.ijkplayer.IjkVideoView;
//import com.slzhibo.library.R;
//import com.slzhibo.library.model.LiveEntity;
//
//import com.slzhibo.library.ui.view.widget.RoundRelativeLayout;
import com.slzhibo.library.utils.AnimUtils;
//import com.slzhibo.library.utils.LogManager;
//import com.slzhibo.library.utils.NumberUtils;
//import com.slzhibo.library.utils.adapter.BaseQuickAdapter;
//import com.slzhibo.library.utils.adapter.BaseViewHolder;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes3.dex */
public class PlayManager {
    private boolean isFromRecyclerView;
    private ImageView ivCover;
    private OnPlayListener listener;
    private final Context mContext;
//    private RoundRelativeLayout mCurrentPlayView;
    private IjkVideoView mIjkplayerView;
    private RecyclerView recyclerView;
    private final ArrayList<Integer> videoPositionList = new ArrayList<>();
    private final int[] childLocal = new int[2];
    private final int[] parentLocal = new int[2];
    private final int currentPlayIndex = 0;
    private final int playPosition = -1;
    private final AtomicInteger ijkOnErrorCount = new AtomicInteger(0);
    private final AtomicBoolean ijkOnPlaySuccessFlag = new AtomicBoolean(false);
    private final IjkVideoView.OnPlayStateListener onPlayStateListener = new IjkVideoView.OnPlayStateListener() { // from class: com.slzhibo.library.utils.live.PlayManager.2
        @Override // com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.OnPlayStateListener, com.tomato.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
//            LogManager.t("ijk 拉流监听 :" + i + ",extra = " + i2);
            if (i == 3 || i == 10002) {
                if (!PlayManager.this.ijkOnPlaySuccessFlag.get()) {
                    PlayManager.this.ijkOnErrorCount.set(0);
                    PlayManager.this.showPlayerView();
                    if (PlayManager.this.isFromRecyclerView && PlayManager.this.ivCover != null) {
                        AnimUtils.playHideAnimation(PlayManager.this.ivCover, 800L);
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
                    AnimUtils.playHideAnimation(PlayManager.this.ivCover, 800L);
                }
                if (PlayManager.this.listener != null) {
                    PlayManager.this.listener.onEndBuffering();
                }
            }
            return true;
        }

        @Override // com.slzhibo.library.ui.view.ijkplayer.IjkVideoView.OnPlayStateListener, com.tomato.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
//            LogManager.t("ijk onError ===> what = " + i + ",extra = " + i2);
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

    /* loaded from: classes3.dex */
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

//    public void initRoomPlayManager(ViewGroup viewGroup, String str) {
//        this.isFromRecyclerView = false;
//        if (viewGroup != null) {
//            initIjkVideoView();
//            viewGroup.addView(this.mIjkplayerView, 0, new ViewGroup.LayoutParams(-1, -1));
//            startPlayWithListener(str);
//        }
//    }
//
//    public void initRecyclerViewPlayManager(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//        this.isFromRecyclerView = true;
//        initIjkVideoView();
//    }

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
//        if (!this.isFromRecyclerView) {
//            this.mIjkplayerView.setMute(false);
//        }
//    }

//    public void setOnPlayListener(OnPlayListener onPlayListener) {
//        this.listener = onPlayListener;
//    }
//
//    public OnPlayListener getListener() {
//        return this.listener;
//    }

//    public void startPlayWithListener(String str) {
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.setOnPlayStateListener(this.onPlayStateListener);
//            this.mIjkplayerView.setVideoPath(str);
//            this.mIjkplayerView.start();
//        }
//    }
//
//    public void switchStream(String str) {
//        this.ijkOnPlaySuccessFlag.set(false);
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.switchStream(str);
//        }
//    }

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

//    public void pausePlay() {
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null && ijkVideoView.isPlaying()) {
//            this.mIjkplayerView.pause();
//        }
//    }
//
//    public void stopLastPlay() {
//        this.ijkOnPlaySuccessFlag.set(false);
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            ijkVideoView.stopPlayback();
//        }
//    }

    private void clearAnimationAndSetVisible(View view) {
        if (view != null) {
            if (view.getAnimation() != null) {
                view.getAnimation().setAnimationListener(null);
            }
            view.clearAnimation();
            view.setVisibility(View.VISIBLE);
        }
    }

//    public void stopPlay() {
//        ViewParent parent;
//        this.ijkOnPlaySuccessFlag.set(false);
//        ImageView imageView = this.ivCover;
//        if (imageView != null) {
//            clearAnimationAndSetVisible(imageView);
//        }
//        IjkVideoView ijkVideoView = this.mIjkplayerView;
//        if (ijkVideoView != null) {
//            if (this.isFromRecyclerView && (parent = ijkVideoView.getParent()) != null && (parent instanceof RelativeLayout)) {
//                ((RelativeLayout) parent).removeView(this.mIjkplayerView);
//            }
//            this.mIjkplayerView.stopPlayback();
//        }
//        this.listener = null;
//        this.mCurrentPlayView = null;
//    }

//    public void onDestroyPlay() {
//        stopPlay();
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

    public void hidePlayerView() {
        ImageView imageView;
        if (this.isFromRecyclerView && (imageView = this.ivCover) != null) {
            clearAnimationAndSetVisible(imageView);
        }
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            ijkVideoView.setVisibility(View.INVISIBLE);
        }
    }

    public void showPlayerView() {
        IjkVideoView ijkVideoView = this.mIjkplayerView;
        if (ijkVideoView != null) {
            ijkVideoView.setVisibility(View.VISIBLE);
        }
    }

//    public void releaseLivePlay() {
//        stopLastPlay();
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

//    public void onRecyclerViewPause() {
//        stopPlay();
//    }

//    public void refreshVideo() {
//        checkPlayVideo();
//        playVideoByPosition();
//    }

//    public void onScrolled() {
//        RoundRelativeLayout roundRelativeLayout = this.mCurrentPlayView;
//        if (roundRelativeLayout != null && !isPlayRange(roundRelativeLayout, this.recyclerView)) {
//            stopPlay();
//        }
//    }

//    public void onScrollStateChanged() {
//        if (this.mCurrentPlayView == null) {
//            checkPlayVideo();
//            playVideoByPosition();
//        }
//    }

//    private boolean isPlayRange(View view, View view2) {
//        if (view == null || view2 == null) {
//            return false;
//        }
//        view.getLocationOnScreen(this.childLocal);
//        view2.getLocationOnScreen(this.parentLocal);
//        int[] iArr = this.childLocal;
//        int i = iArr[1];
//        int[] iArr2 = this.parentLocal;
//        return i >= iArr2[1] && iArr[1] <= (iArr2[1] + view2.getHeight()) - view.getHeight();
//    }

//    private void checkPlayVideo() {
//        LiveEntity liveEntity;
//        this.currentPlayIndex = 0;
//        this.videoPositionList.clear();
//        BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
//        int itemCount = baseQuickAdapter.getItemCount();
//        for (int i = 0; i < itemCount; i++) {
//            if (isPlayRange(baseQuickAdapter.getViewByPosition(i, R.id.sq_root), this.recyclerView) && (liveEntity = (LiveEntity) baseQuickAdapter.getItem(i - baseQuickAdapter.getHeaderLayoutCount())) != null && liveEntity.isCoverPreview() && i >= 0 && !this.videoPositionList.contains(Integer.valueOf(i))) {
//                this.videoPositionList.add(Integer.valueOf(i));
//            }
//        }
//        if (this.videoPositionList.size() > 1) {
//            this.currentPlayIndex = NumberUtils.getIntRandom(this.videoPositionList.size());
//        }
//    }

//    private void playVideoByPosition() {
//        if (this.videoPositionList.size() != 0) {
//            playVideoByPosition(this.videoPositionList.get(this.currentPlayIndex).intValue());
//        }
//    }

//    public void playVideoByPosition(int i) {
//        if (this.playPosition != i) {
//            stopPlay();
//            BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.findViewHolderForAdapterPosition(i);
//            if (baseViewHolder != null) {
//                BaseQuickAdapter baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
//                LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getData().get(i - baseQuickAdapter.getHeaderLayoutCount());
//                if (liveEntity != null) {
//                    this.playPosition = i;
//                    if (!liveEntity.isCoverPreview()) {
//                        refreshVideo();
//                        return;
//                    }
//                    this.mCurrentPlayView = (RoundRelativeLayout) baseViewHolder.getView(R.id.sq_root);
//                    this.ivCover = (ImageView) baseViewHolder.getView(R.id.iv_cover);
//                    IjkVideoView ijkVideoView = this.mIjkplayerView;
//                    if (ijkVideoView != null) {
//                        ijkVideoView.setMute(true);
//                        this.mCurrentPlayView.addView(this.mIjkplayerView, 0, new ViewGroup.LayoutParams(-1, -1));
//                        startPlayWithListener(liveEntity.getDefPullStreamUrlStr());
//                    }
//                }
//            }
//        }
//    }

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
}
