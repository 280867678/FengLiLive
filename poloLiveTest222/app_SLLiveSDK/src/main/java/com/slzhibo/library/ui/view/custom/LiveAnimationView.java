package com.slzhibo.library.ui.view.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.slzhibo.library.R;
import com.slzhibo.library.download.CarDownLoadManager;
import com.slzhibo.library.download.GiftDownLoadManager;
import com.slzhibo.library.download.NobilityDownLoadManager;
import com.slzhibo.library.model.CarDownloadEntity;
import com.slzhibo.library.model.GiftItemEntity;
import com.slzhibo.library.model.LeftAnimEntity;
import com.slzhibo.library.model.SocketMessageEvent;
import com.slzhibo.library.ui.interfaces.impl.SimpleSVGACallBack;
import com.slzhibo.library.ui.view.gift.CustomAnimImpl;
import com.slzhibo.library.ui.view.gift.GiftAnimManage;
import com.slzhibo.library.ui.view.gift.GiftAnimModel;
//import com.slzhibo.library.ui.view.widget.svga.SVGADrawable;
//import com.slzhibo.library.ui.view.widget.svga.SVGADynamicEntity;
//import com.slzhibo.library.ui.view.widget.svga.SVGAImageView;
//import com.slzhibo.library.ui.view.widget.svga.SVGAParser;
//import com.slzhibo.library.ui.view.widget.svga.SVGAVideoEntity;


import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.FileUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.live.LeftAnimManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LiveAnimationView extends RelativeLayout {
    private SimpleSVGACallBack carAnimCallback;
    private Disposable carDisposable;
    private SimpleSVGACallBack giftAnimCallback;
    private GiftAnimManage giftAnimManage;
    private Disposable giftDisposable;
    private SimpleSVGACallBack guardEnterAnimCallback;
    private LeftAnimManager leftAnimManager;
    private Context mContext;
    private OnLeftGiftCallback onGiftNotifyCallback;
    private SVGAImageView svBigAnim;
    private SVGAImageView svCarAnim;
    private SVGAImageView svEnterAnim;
    private SVGAParser svgaParser;

    public interface OnLeftGiftCallback {
        void onLeftGiftClickListener(GiftAnimModel giftAnimModel);

        void onLeftGiftDeleteListener(GiftAnimModel giftAnimModel);
    }

    public LiveAnimationView(Context context) {
        super(context);
        initView(context);
    }

    public LiveAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        RelativeLayout.inflate(context, R.layout.fq_layout_live_animation_view, this);
        this.mContext = context;
        this.svBigAnim = (SVGAImageView) findViewById(R.id.sv_anim_big_gift);
        this.svEnterAnim = (SVGAImageView) findViewById(R.id.sv_anim_enter);
        this.svCarAnim = (SVGAImageView) findViewById(R.id.sv_anim_car);
        initGiftAnimManage();
        initLeftAnimManage();
    }

    private void initLeftAnimManage() {
        this.leftAnimManager = new LeftAnimManager(this.mContext);
        this.leftAnimManager.setAnimLayout((LinearLayout) findViewById(R.id.ll_left_anim));
    }

    private void initListener() {
        SimpleSVGACallBack simpleSVGACallBack = this.giftAnimCallback;
        if (simpleSVGACallBack != null) {
            this.svBigAnim.setCallback(simpleSVGACallBack);
        }
        SimpleSVGACallBack simpleSVGACallBack2 = this.guardEnterAnimCallback;
        if (simpleSVGACallBack2 != null) {
            this.svEnterAnim.setCallback(simpleSVGACallBack2);
        }
        SimpleSVGACallBack simpleSVGACallBack3 = this.carAnimCallback;
        if (simpleSVGACallBack3 != null) {
            this.svCarAnim.setCallback(simpleSVGACallBack3);
        }
    }

    private void initGiftAnimManage() {
        this.giftAnimManage = new GiftAnimManage(this.mContext);
        GiftAnimManage giftAnimManage2 = this.giftAnimManage;
        giftAnimManage2.setGiftLayout((LinearLayout) findViewById(R.id.ll_left_anim_gift), 2);
        giftAnimManage2.setHideMode(false);
        giftAnimManage2.setCustomAnim(new CustomAnimImpl());
        giftAnimManage2.setOnLeftGiftAnimListener(new GiftAnimManage.OnLeftGiftAnimListener() {
            /* class com.slzhibo.library.ui.view.custom.LiveAnimationView.AnonymousClass1 */

            @Override // com.slzhibo.library.ui.view.gift.GiftAnimManage.OnLeftGiftAnimListener
            public void onDelete(GiftAnimModel giftAnimModel) {
                if (LiveAnimationView.this.onGiftNotifyCallback != null) {
                    LiveAnimationView.this.onGiftNotifyCallback.onLeftGiftDeleteListener(giftAnimModel);
                }
            }

            @Override // com.slzhibo.library.ui.view.gift.GiftAnimManage.OnLeftGiftAnimListener
            public void onClick(GiftAnimModel giftAnimModel) {
                if (LiveAnimationView.this.onGiftNotifyCallback != null) {
                    LiveAnimationView.this.onGiftNotifyCallback.onLeftGiftClickListener(giftAnimModel);
                }
            }
        });
    }

    public void setAnimationCallback(SimpleSVGACallBack simpleSVGACallBack, SimpleSVGACallBack simpleSVGACallBack2, OnLeftGiftCallback onLeftGiftCallback, SimpleSVGACallBack simpleSVGACallBack3) {
        this.giftAnimCallback = simpleSVGACallBack;
        this.guardEnterAnimCallback = simpleSVGACallBack2;
        this.onGiftNotifyCallback = onLeftGiftCallback;
        this.carAnimCallback = simpleSVGACallBack3;
        initListener();
    }

    public void setGiftAnimViewVisibility(int i) {
        this.svBigAnim.setVisibility(i);
    }

    public void setGuardEnterAnimViewVisibility(int i) {
        this.svEnterAnim.setVisibility(i);
    }

    public boolean isGiftAnimating() {
        return this.svBigAnim.isAnimating();
    }

    public void stopGiftAnimating() {
        this.svBigAnim.stopAnimation(true);
    }

    public void loadGift(GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
        if (giftItemEntity.isUpdateGiftAnimRes()) {
            GiftDownLoadManager.getInstance().updateAnimOnlineSingleRes(giftItemEntity);
        }
        this.giftAnimManage.loadGift(giftAnimModel);
    }

    public void loadReceiveGift(GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
        if (giftItemEntity.isUpdateGiftAnimRes()) {
            GiftDownLoadManager.getInstance().updateAnimOnlineSingleRes(giftItemEntity);
        }
        this.giftAnimManage.loadReceiveGift(giftAnimModel);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onExceptionBigAnimCallback(SimpleSVGACallBack simpleSVGACallBack) {
        if (simpleSVGACallBack != null) {
            simpleSVGACallBack.onFinished();
        }
    }

    public void loadLocalGiftAnim(final GiftItemEntity giftItemEntity) {
        if (TextUtils.isEmpty(giftItemEntity.giftDirPath) || this.svBigAnim == null) {
            onExceptionBigAnimCallback(this.giftAnimCallback);
            return;
        }
        try {
            Observable.just(true).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$LiveAnimationView$5RYJzQfhtTQFK3kFT7FKij2xbc4
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws Exception {
                    return LiveAnimationView.this.lambda$loadLocalGiftAnim$0$LiveAnimationView(giftItemEntity, (Boolean) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<InputStream>() { // from class: com.slzhibo.library.ui.view.custom.LiveAnimationView.2
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    LiveAnimationView.this.giftDisposable = disposable;
                }

                public void onNext(InputStream inputStream) {
                    if (inputStream == null) {
                        LiveAnimationView liveAnimationView = LiveAnimationView.this;
                        liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.giftAnimCallback);
                        return;
                    }
                    if (LiveAnimationView.this.svgaParser == null) {
                        LiveAnimationView liveAnimationView2 = LiveAnimationView.this;
                        liveAnimationView2.svgaParser = new SVGAParser(liveAnimationView2.mContext);
                    }
                    LiveAnimationView.this.svgaParser.decodeFromInputStream(inputStream, giftItemEntity.giftDirPath, new SVGAParser.ParseCompletion() { // from class: com.slzhibo.library.ui.view.custom.LiveAnimationView.2.1
                        @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                        public void onError() {
//                            AnonymousClass2 r0 = AnonymousClass2.this;
                            LiveAnimationView.this.loadOnlineGiftAnim(giftItemEntity);
                        }

                        @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                        public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
                            LiveAnimationView.this.svBigAnim.setVisibility(View.VISIBLE);
                            LiveAnimationView.this.svBigAnim.setVideoItem(sVGAVideoEntity);
                            LiveAnimationView.this.svBigAnim.startAnimation();
                        }
                    }, true);
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    LiveAnimationView.this.loadOnlineGiftAnim(giftItemEntity);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            onExceptionBigAnimCallback(this.giftAnimCallback);
        }
    }

    public /* synthetic */ InputStream lambda$loadLocalGiftAnim$0$LiveAnimationView(GiftItemEntity giftItemEntity, Boolean bool) throws Exception {
        return getSVGAFileInputStream(giftItemEntity.giftDirPath);
    }

    public void loadOnlineGiftAnim(GiftItemEntity giftItemEntity) {
        GiftDownLoadManager.getInstance().updateAnimOnlineSingleRes(giftItemEntity);
        loadPropAnimation(giftItemEntity.animalUrl);
    }

    public void loadPropAnimation(String str) {
        if (TextUtils.isEmpty(str) || this.svBigAnim == null) {
            onExceptionBigAnimCallback(this.giftAnimCallback);
            return;
        }
        try {
            if (this.svgaParser == null) {
                this.svgaParser = new SVGAParser(this.mContext);
            }
            this.svgaParser.decodeFromURL(new URL(GlideUtils.formatDownUrl(str)), new SVGAParser.ParseCompletion() {
                /* class com.slzhibo.library.ui.view.custom.LiveAnimationView.AnonymousClass3 */

                @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
                    LiveAnimationView.this.svBigAnim.setVisibility(View.VISIBLE);
                    LiveAnimationView.this.svBigAnim.setVideoItem(sVGAVideoEntity);
                    LiveAnimationView.this.svBigAnim.startAnimation();
                }

                @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                public void onError() {
                    LiveAnimationView liveAnimationView = LiveAnimationView.this;
                    liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.giftAnimCallback);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            onExceptionBigAnimCallback(this.giftAnimCallback);
        }
    }

    private void loadCarAnimation(final String str) {
        if (TextUtils.isEmpty(str) || this.svCarAnim == null) {
            onExceptionBigAnimCallback(this.carAnimCallback);
            return;
        }
        try {
            Observable.just(true).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$LiveAnimationView$8HkMhYgkc-QlhE6VU44HI7D65Wg
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) throws Exception {
                    InputStream carSVGAFileInputStream;
                    Boolean bool = (Boolean) obj;
                    carSVGAFileInputStream = AppUtils.getCarSVGAFileInputStream(str);
                    return carSVGAFileInputStream;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<InputStream>() { // from class: com.slzhibo.library.ui.view.custom.LiveAnimationView.4
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    LiveAnimationView.this.carDisposable = disposable;
                }

                public void onNext(InputStream inputStream) {
                    if (inputStream == null) {
                        LiveAnimationView liveAnimationView = LiveAnimationView.this;
                        liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.carAnimCallback);
                        return;
                    }
                    if (LiveAnimationView.this.svgaParser == null) {
                        LiveAnimationView liveAnimationView2 = LiveAnimationView.this;
                        liveAnimationView2.svgaParser = new SVGAParser(liveAnimationView2.mContext);
                    }
                    LiveAnimationView.this.svgaParser.decodeFromInputStream(inputStream, str, new SVGAParser.ParseCompletion() { // from class: com.slzhibo.library.ui.view.custom.LiveAnimationView.4.1
                        @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                        public void onError() {
                            LiveAnimationView liveAnimationView3 = LiveAnimationView.this;
                            liveAnimationView3.onExceptionBigAnimCallback(liveAnimationView3.carAnimCallback);
                        }

                        @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                        public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
                            LiveAnimationView.this.svCarAnim.setVisibility(View.VISIBLE);
                            LiveAnimationView.this.svCarAnim.setVideoItem(sVGAVideoEntity);
                            LiveAnimationView.this.svCarAnim.startAnimation();
                        }
                    }, true);
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    LiveAnimationView liveAnimationView = LiveAnimationView.this;
                    liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.carAnimCallback);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            onExceptionBigAnimCallback(this.carAnimCallback);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public void loadLiveEnterAnimation(String str, final SVGADynamicEntity sVGADynamicEntity) {
        char c;
        String str2;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49:
                if (str.equals("1")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 50:
                if (str.equals("2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (str.equals("3")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            str2 = ConstantUtils.YEAR_GUARD_ENTER_ANIM_PATH;
        } else if (c == 1 || c == 2) {
            str2 = ConstantUtils.MONTH_GUARD_ENTER_ANIM_PATH;
        } else if (c == 3) {
            str2 = ConstantUtils.CAR_ENTER_ANIM_PATH;
        } else {
            return;
        }
        if (this.svgaParser == null) {
            this.svgaParser = new SVGAParser(this.mContext);
        }
        this.svgaParser.decodeFromAssets(str2, new SVGAParser.ParseCompletion() {
            /* class com.slzhibo.library.ui.view.custom.LiveAnimationView.AnonymousClass5 */

            @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
            public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
                LiveAnimationView.this.svEnterAnim.setVisibility(View.VISIBLE);
                LiveAnimationView.this.svEnterAnim.setImageDrawable(new SVGADrawable(sVGAVideoEntity, sVGADynamicEntity));
                LiveAnimationView.this.svEnterAnim.startAnimation();
            }

            @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
            public void onError() {
                LiveAnimationView liveAnimationView = LiveAnimationView.this;
                liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.guardEnterAnimCallback);
            }
        });
    }

    public void loadGuardOpenAnimation(SocketMessageEvent.ResultData resultData) {
        LeftAnimEntity leftAnimEntity = new LeftAnimEntity();
        leftAnimEntity.userName = resultData.userName;
        leftAnimEntity.userId = resultData.userId;
        leftAnimEntity.avatar = resultData.avatar;
        leftAnimEntity.guardType = resultData.guardType;
        leftAnimEntity.leftAnimType = ConstantUtils.LEFT_ANIM_OPEN_GUARD_TYPE;
        this.leftAnimManager.addLeftAnim(leftAnimEntity);
    }

    public void loadNobilityOpenAnimation(SocketMessageEvent.ResultData resultData) {
        LeftAnimEntity leftAnimEntity = new LeftAnimEntity();
        leftAnimEntity.userName = resultData.userName;
        leftAnimEntity.userId = resultData.userId;
        leftAnimEntity.avatar = resultData.avatar;
        leftAnimEntity.nobilityType = resultData.nobilityType;
        leftAnimEntity.leftAnimType = ConstantUtils.LEFT_ANIM_OPEN_NOBILITY_TYPE;
        this.leftAnimManager.addLeftAnim(leftAnimEntity);
    }

    public void loadNobilityEnterAnimation(SocketMessageEvent.ResultData resultData) {
        LeftAnimEntity leftAnimEntity = new LeftAnimEntity();
        leftAnimEntity.userName = resultData.userName;
        leftAnimEntity.userId = resultData.userId;
        leftAnimEntity.avatar = resultData.avatar;
        leftAnimEntity.nobilityType = resultData.nobilityType;
        leftAnimEntity.leftAnimType = ConstantUtils.LEFT_ANIM_ENTER_NOBILITY_TYPE;
        this.leftAnimManager.addLeftAnim(leftAnimEntity);
    }

    public void loadCarJoinAnimation(SocketMessageEvent.ResultData resultData, boolean z) {
        CarDownloadEntity carItemEntity = CarDownLoadManager.getInstance().getCarItemEntity(resultData.carId);
        if (carItemEntity == null) {
            loadNetAnimation(resultData, z);
            return;
        }
        if (z) {
            loadLiveEnterAnimation("0", GlideUtils.getCarSVGADynamicEntity(this.mContext, resultData.avatar, resultData.userName, AppUtils.formatExpGrade(resultData.expGrade), carItemEntity.name));
        }
        loadCarAnimation(carItemEntity.id);
    }

    private void loadNetAnimation(final SocketMessageEvent.ResultData resultData, final boolean z) {
        CarDownloadEntity carDownloadEntity = new CarDownloadEntity();
        carDownloadEntity.imgUrl = resultData.carIcon;
        carDownloadEntity.id = resultData.carId;
        carDownloadEntity.name = resultData.carName;
        carDownloadEntity.animalUrl = resultData.carOnlineUrl;
        if (TextUtils.isEmpty(carDownloadEntity.animalUrl)) {
            CarDownLoadManager.getInstance().updateAnimOnlineAllRes();
            onExceptionBigAnimCallback(this.carAnimCallback);
            if (z) {
                onExceptionBigAnimCallback(this.guardEnterAnimCallback);
                return;
            }
            return;
        }
        CarDownLoadManager.getInstance().updateAnimOnlineSingleRes(carDownloadEntity);
        if (this.svgaParser == null) {
            this.svgaParser = new SVGAParser(this.mContext);
        }
        try {
            this.svgaParser.decodeFromURL(new URL(carDownloadEntity.getAnimalUrl()), new SVGAParser.ParseCompletion() { // from class: com.slzhibo.library.ui.view.custom.LiveAnimationView.6
                @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
                    LiveAnimationView.this.svCarAnim.setVisibility(View.VISIBLE);
                    LiveAnimationView.this.svCarAnim.setVideoItem(sVGAVideoEntity);
                    LiveAnimationView.this.svCarAnim.startAnimation();
                    if (z) {
                        Context context = LiveAnimationView.this.mContext;
                        SocketMessageEvent.ResultData resultData2 = resultData;
                        LiveAnimationView.this.loadLiveEnterAnimation("0", GlideUtils.getCarSVGADynamicEntity(context, resultData2.avatar, resultData2.userName, AppUtils.formatExpGrade(resultData2.expGrade), resultData.carName));
                    }
                }

                @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                public void onError() {
                    LiveAnimationView liveAnimationView = LiveAnimationView.this;
                    liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.carAnimCallback);
                    if (z) {
                        LiveAnimationView liveAnimationView2 = LiveAnimationView.this;
                        liveAnimationView2.onExceptionBigAnimCallback(liveAnimationView2.guardEnterAnimCallback);
                    }
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
            onExceptionBigAnimCallback(this.carAnimCallback);
            if (z) {
                onExceptionBigAnimCallback(this.guardEnterAnimCallback);
            }
        }
    }

    public void onDestroy() {
        GiftAnimManage giftAnimManage2 = this.giftAnimManage;
        if (giftAnimManage2 != null) {
            giftAnimManage2.cleanAll();
        }
        LeftAnimManager leftAnimManager2 = this.leftAnimManager;
        if (leftAnimManager2 != null) {
            leftAnimManager2.cleanAll();
        }
        SVGAImageView sVGAImageView = this.svBigAnim;
        if (sVGAImageView != null) {
            sVGAImageView.setVisibility(View.INVISIBLE);
            this.svBigAnim.stopAnimation(true);
            this.svBigAnim.clearAnimation();
        }
        SVGAImageView sVGAImageView2 = this.svEnterAnim;
        if (sVGAImageView2 != null) {
            sVGAImageView2.setVisibility(View.INVISIBLE);
            this.svEnterAnim.stopAnimation(true);
            this.svEnterAnim.clearAnimation();
        }
        SVGAImageView sVGAImageView3 = this.svCarAnim;
        if (sVGAImageView3 != null) {
            sVGAImageView3.setVisibility(View.INVISIBLE);
            this.svCarAnim.stopAnimation(true);
            this.svCarAnim.clearAnimation();
        }
        Disposable disposable = this.giftDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.giftDisposable.dispose();
            this.giftDisposable = null;
        }
        Disposable disposable2 = this.carDisposable;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.carDisposable.dispose();
            this.carDisposable = null;
        }
        if (this.svgaParser != null) {
            this.svgaParser = null;
        }
    }

    public void loadGuardOpenBigAnim(int i) {
        String str = i == NumberUtils.string2int("3") ? ConstantUtils.YEAR_GUARD_OPEN_ANIM_PATH : ConstantUtils.MONTH_GUARD_OPEN_ANIM_PATH;
        if (this.svgaParser == null) {
            this.svgaParser = new SVGAParser(this.mContext);
        }
        this.svgaParser.decodeFromAssets(str, new SVGAParser.ParseCompletion() {
            /* class com.slzhibo.library.ui.view.custom.LiveAnimationView.AnonymousClass7 */

            @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
            public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
                LiveAnimationView.this.svBigAnim.setVisibility(View.VISIBLE);
                LiveAnimationView.this.svBigAnim.setVideoItem(sVGAVideoEntity);
                LiveAnimationView.this.svBigAnim.startAnimation();
            }

            @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
            public void onError() {
                LiveAnimationView liveAnimationView = LiveAnimationView.this;
                liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.giftAnimCallback);
            }
        });
    }

    private InputStream getSVGAFileInputStream(String str) throws Exception {
        File file = new File(AppUtils.getLocalGiftFilePath(str));
        if (!FileUtils.isFile(file)) {
            return null;
        }
        return new FileInputStream(file);
    }

    public void loadNobilityOpenBigAnimation(GiftItemEntity giftItemEntity) {
        final String str = giftItemEntity.name;
        final String str2 = giftItemEntity.avatar;
        final int i = giftItemEntity.nobilityType;
        String nobilityFilePath = NobilityDownLoadManager.getInstance().getNobilityFilePath(i);
        if (!FileUtils.isFileExists(nobilityFilePath)) {
            NobilityDownLoadManager.getInstance().updateAnimOnlineSingleRes(i);
            return;
        }
        try {
            Observable.just(new FileInputStream(new File(nobilityFilePath))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<InputStream>() {
                /* class com.slzhibo.library.ui.view.custom.LiveAnimationView.AnonymousClass8 */

                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    LiveAnimationView.this.giftDisposable = disposable;
                }

                public void onNext(InputStream inputStream) {
                    if (inputStream == null) {
                        LiveAnimationView liveAnimationView = LiveAnimationView.this;
                        liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.giftAnimCallback);
                        return;
                    }
                    final SVGADynamicEntity nobilitySVGADynamicEntity = GlideUtils.getNobilitySVGADynamicEntity(LiveAnimationView.this.mContext, str2, str, i);
                    if (LiveAnimationView.this.svgaParser == null) {
                        LiveAnimationView liveAnimationView2 = LiveAnimationView.this;
                        liveAnimationView2.svgaParser = new SVGAParser(liveAnimationView2.mContext);
                    }
                    LiveAnimationView.this.svgaParser.decodeFromInputStream(inputStream, String.valueOf(i), new SVGAParser.ParseCompletion() {
                        /* class com.slzhibo.library.ui.view.custom.LiveAnimationView.AnonymousClass8.AnonymousClass1 */

                        @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                        public void onError() {
                            LiveAnimationView liveAnimationView = LiveAnimationView.this;
                            liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.giftAnimCallback);
                        }

                        @Override // com.slzhibo.library.ui.view.widget.svga.SVGAParser.ParseCompletion
                        public void onComplete(SVGAVideoEntity sVGAVideoEntity) {
                            LiveAnimationView.this.svBigAnim.setVisibility(View.VISIBLE);
                            LiveAnimationView.this.svBigAnim.setVideoItem(sVGAVideoEntity, nobilitySVGADynamicEntity);
                            LiveAnimationView.this.svBigAnim.startAnimation();
                        }
                    }, true);
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    LiveAnimationView liveAnimationView = LiveAnimationView.this;
                    liveAnimationView.onExceptionBigAnimCallback(liveAnimationView.giftAnimCallback);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            onExceptionBigAnimCallback(this.giftAnimCallback);
        }
    }
}
