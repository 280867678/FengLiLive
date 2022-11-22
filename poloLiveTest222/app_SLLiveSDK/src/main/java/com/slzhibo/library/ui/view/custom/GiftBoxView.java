package com.slzhibo.library.ui.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.slzhibo.library.R;
import com.slzhibo.library.model.db.GiftBoxEntity;
import com.slzhibo.library.ui.view.widget.badgeView.QBadgeView;
import com.slzhibo.library.ui.view.widget.progress.AnimDownloadProgressButton;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.DBUtils;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.RxViewUtils;
import com.slzhibo.library.utils.UserInfoManager;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GiftBoxView extends RelativeLayout {
    private RelativeLayout boxRoot;
    private Disposable chronographDisposable;
    private Disposable countDownDisposable;
    private Disposable expiredDisposable;
    private List<GiftBoxEntity> giftBoxEntityList;
    private ImageView ivIcon;
    private OnSendGiftBoxMsgListener listener;
    private State mState;
    private AnimDownloadProgressButton progressLoading;
    private QBadgeView qBadgeView;
    private TextView tvShowTip;

    public interface OnSendGiftBoxMsgListener {
        void onSendGiftBoxMsg(GiftBoxEntity giftBoxEntity);

        void onShowDialog(GiftBoxEntity giftBoxEntity);
    }

    public enum State {
        INIT,
        WAITING,
        OPENING,
        LOADING,
        EXPIRED
    }

    public GiftBoxView(Context context) {
        this(context, null);
    }

    public GiftBoxView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GiftBoxView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mState = State.INIT;
        init(context);
    }

    private void init(Context context) {
        RelativeLayout.inflate(context, R.layout.fq_giftbox_layout, this);
        this.ivIcon = (ImageView) findViewById(R.id.iv_box);
        this.boxRoot = (RelativeLayout) findViewById(R.id.rl_box_root);
        this.tvShowTip = (TextView) findViewById(R.id.tv_show_tip);
        this.progressLoading = (AnimDownloadProgressButton) findViewById(R.id.fq_loading_btn);
        this.boxRoot.setVisibility(View.GONE);
        this.qBadgeView = new QBadgeView(context);
        this.qBadgeView.bindTarget(this.boxRoot).setBadgeTextColor(-1).setBadgePadding(1.0f, true).setBadgeGravity(8388661).setBadgeBackgroundColor(ContextCompat.getColor(getContext(), R.color.fq_colorRed)).setBadgeTextSize(8.0f, true).stroke(-1, 1.0f, true);
        this.progressLoading.setVisibility(View.INVISIBLE);
        this.giftBoxEntityList = new ArrayList();
        initListener();
    }

    private void initListener() {
        RxViewUtils.getInstance().throttleFirst(this.boxRoot, 500, new RxViewUtils.RxViewAction() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$GiftBoxView$_uzCsAC7w9jyxfWwlVth7E0Dm10 */

            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
            public final void action(Object obj) {
                GiftBoxView.this.lambda$initListener$0$GiftBoxView(obj);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$GiftBoxView(Object obj) {
        int i;
        OnSendGiftBoxMsgListener onSendGiftBoxMsgListener;
        if (AppUtils.isConsumptionPermissionUser(getContext()) && (i = AnonymousClass3.$SwitchMap$com$slzhibo$library$ui$view$custom$GiftBoxView$State[this.mState.ordinal()]) != 1 && i != 2) {
            if (i == 3) {
                cancelExpiredDisposable();
                GiftBoxEntity remove = this.giftBoxEntityList.remove(0);
                DBUtils.saveOneGiftBox(remove);
                OnSendGiftBoxMsgListener onSendGiftBoxMsgListener2 = this.listener;
                if (onSendGiftBoxMsgListener2 != null) {
                    onSendGiftBoxMsgListener2.onSendGiftBoxMsg(remove);
                }
                if (this.giftBoxEntityList.size() == 0) {
                    showEmptyBox();
                    return;
                }
                setBadgeCount();
                showLoading();
            } else if ((i == 4 || i == 5) && (onSendGiftBoxMsgListener = this.listener) != null) {
                onSendGiftBoxMsgListener.onShowDialog(this.giftBoxEntityList.get(0));
            }
        }
    }

    /* renamed from: com.slzhibo.library.ui.view.custom.GiftBoxView$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$slzhibo$library$ui$view$custom$GiftBoxView$State = new int[State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            $SwitchMap$com$slzhibo$library$ui$view$custom$GiftBoxView$State[State.INIT.ordinal()] = 1;
            $SwitchMap$com$slzhibo$library$ui$view$custom$GiftBoxView$State[State.EXPIRED.ordinal()] = 2;
            $SwitchMap$com$slzhibo$library$ui$view$custom$GiftBoxView$State[State.OPENING.ordinal()] = 3;
            $SwitchMap$com$slzhibo$library$ui$view$custom$GiftBoxView$State[State.LOADING.ordinal()] = 4;
            $SwitchMap$com$slzhibo$library$ui$view$custom$GiftBoxView$State[State.WAITING.ordinal()] = 5;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showEmptyBox() {
        this.mState = State.INIT;
        cancelChronographDisposable();
        this.boxRoot.setVisibility(View.GONE);
    }

    public void showLoading() {
        this.mState = State.LOADING;
        this.ivIcon.setImageResource(R.drawable.fq_imgs_box_close);
        this.tvShowTip.setVisibility(View.INVISIBLE);
        this.progressLoading.setVisibility(View.VISIBLE);
        this.progressLoading.setLoadingEndListener(new AnimDownloadProgressButton.LoadingEndListener() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$GiftBoxView$kqI4Ux9rsYCfiswLlWuVLNfnuI */

            @Override // com.slzhibo.library.ui.view.widget.progress.AnimDownloadProgressButton.LoadingEndListener
            public final void onLoadingEnd() {
                GiftBoxView.this.lambda$showLoading$1$GiftBoxView();
            }
        });
        this.progressLoading.setProgressText("loading...", 100.0f);
    }

    public /* synthetic */ void lambda$showLoading$1$GiftBoxView() {
        this.progressLoading.setVisibility(View.GONE);
        this.progressLoading.setProgress(0.0f);
        showNextBox();
    }

    public void setOnSendGiftBoxMsgListener(OnSendGiftBoxMsgListener onSendGiftBoxMsgListener) {
        this.listener = onSendGiftBoxMsgListener;
    }

    public void startChronographTimer() {
        this.chronographDisposable = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Consumer() {
            /* class com.slzhibo.library.ui.view.custom.$$Lambda$GiftBoxView$CRemDrFGr18Lpk9Z8AfFQeDwj9E */

            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                GiftBoxView.this.lambda$startChronographTimer$2$GiftBoxView((Long) obj);
            }
        });
    }

    public /* synthetic */ void lambda$startChronographTimer$2$GiftBoxView(Long l) throws Exception {
        synchronized (GiftBoxView.class) {
            for (int size = this.giftBoxEntityList.size() - 1; size >= 0; size--) {
                this.giftBoxEntityList.get(size).incrementTime++;
            }
        }
    }

    public void cancelChronographDisposable() {
        Disposable disposable = this.chronographDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.chronographDisposable.dispose();
            this.chronographDisposable = null;
        }
    }

    public void cancelCountDownDisposable() {
        Disposable disposable = this.countDownDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.countDownDisposable.dispose();
            this.countDownDisposable = null;
        }
    }

    public void cancelExpiredDisposable() {
        Disposable disposable = this.expiredDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.expiredDisposable.dispose();
            this.expiredDisposable = null;
        }
    }

    public void cancelLoading() {
        AnimDownloadProgressButton animDownloadProgressButton = this.progressLoading;
        if (animDownloadProgressButton != null) {
            animDownloadProgressButton.cancelAnimaiton();
        }
    }

    public void showBoxList(List<GiftBoxEntity> list, String str) {
        if (list == null || list.size() == 0) {
            DBUtils.deleteGiftBoxList(str);
            this.boxRoot.setVisibility(View.GONE);
            return;
        }
        this.giftBoxEntityList.clear();
        List<String> giftBoxIdList = DBUtils.getGiftBoxIdList(str);
        ArrayList arrayList = new ArrayList();
        for (GiftBoxEntity giftBoxEntity : list) {
            if (!giftBoxIdList.contains(giftBoxEntity.giftBoxUniqueCode)) {
                giftBoxEntity.liveId = str;
                giftBoxEntity.userId = UserInfoManager.getInstance().getUserId();
                arrayList.add(giftBoxEntity);
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            GiftBoxEntity giftBoxEntity2 = (GiftBoxEntity) arrayList.get(size);
            if (giftBoxEntity2.openTime == 0 && giftBoxEntity2.expirationTime == 0) {
                arrayList.remove(size);
            }
        }
        if (arrayList.size() == 0) {
            this.boxRoot.setVisibility(View.GONE);
            return;
        }
        this.giftBoxEntityList.addAll(arrayList);
        startChronographTimer();
        setBadgeCount();
        this.boxRoot.setVisibility(View.VISIBLE);
        showLoading();
    }

    private void showAnim() {
        this.mState = State.LOADING;
        this.boxRoot.setVisibility(View.VISIBLE);
        this.progressLoading.setVisibility(View.GONE);
        this.progressLoading.setProgress(0.0f);
        showNextBox();
    }

    public void addOneBox(GiftBoxEntity giftBoxEntity) {
        this.giftBoxEntityList.add(giftBoxEntity);
        setBadgeCount();
        if (this.mState == State.INIT) {
            startChronographTimer();
            showAnim();
        }
    }

    public void showNextBox() {
        if (this.giftBoxEntityList.size() > 0) {
            GiftBoxEntity giftBoxEntity = this.giftBoxEntityList.get(0);
            long j = giftBoxEntity.incrementTime;
            long j2 = giftBoxEntity.openTime;
            long j3 = giftBoxEntity.expirationTime;
            int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
            if (i < 0) {
                this.mState = State.WAITING;
                this.ivIcon.setImageResource(R.drawable.fq_imgs_box_close);
                startWaitCountDown(j, j2, j3);
                return;
            }
            if (i >= 0) {
                long j4 = j - j2;
                if (j4 < j3) {
                    showOpenBoxAnim();
                    startExpiredCountDown(j3 - j4);
                    return;
                }
            }
            if (j >= j2 + j3) {
                this.mState = State.EXPIRED;
                this.ivIcon.setImageResource(R.drawable.fq_imgs_box_open);
                this.tvShowTip.setVisibility(View.VISIBLE);
                this.tvShowTip.setText(AppUtils.isConsumptionPermissionUser() ? R.string.fq_receive_box : R.string.fq_receive_task_box);
                this.giftBoxEntityList.remove(0);
                setBadgeCount();
                this.progressLoading.setVisibility(View.GONE);
                this.progressLoading.setProgress(0.0f);
                showNextBox();
                return;
            }
            return;
        }
        showEmptyBox();
    }

    private void startWaitCountDown(final long j, final long j2, final long j3) {
        this.tvShowTip.setVisibility(View.VISIBLE);
        this.tvShowTip.setText(AppUtils.isConsumptionPermissionUser() ? DateUtils.secondToString(j2 - j) : getContext().getString(R.string.fq_receive_task_box));
        Observable.interval(1L, TimeUnit.SECONDS).take((j2 - j) + 1).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$GiftBoxView$QTRCPGqEQwOHvaaCSZR2rYACbqU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Long valueOf;
                valueOf = Long.valueOf(((j2 - j) - 1) - ((Long) obj).longValue());
                return valueOf;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Long>() { // from class: com.slzhibo.library.ui.view.custom.GiftBoxView.1
            public void accept(Long l) {
                if (AppUtils.isConsumptionPermissionUser()) {
                    GiftBoxView.this.tvShowTip.setText(DateUtils.secondToString(l.longValue()));
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onComplete() {
                GiftBoxView.this.showOpenBoxAnim();
                GiftBoxView.this.startExpiredCountDown(j3);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                GiftBoxView.this.countDownDisposable = disposable;
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    public void startExpiredCountDown(final long j) {
        Observable.interval(1L, TimeUnit.SECONDS).take(1 + j).map(new Function() { // from class: com.slzhibo.library.ui.view.custom.-$$Lambda$GiftBoxView$ZwDCabyrvAv3GhVtVE52M5G5HNI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Long valueOf;
                valueOf = Long.valueOf(j - ((Long) obj).longValue());
                return valueOf;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Long>() { // from class: com.slzhibo.library.ui.view.custom.GiftBoxView.2
            public void accept(Long l) {
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onComplete() {
                GiftBoxEntity giftBoxEntity = (GiftBoxEntity) GiftBoxView.this.giftBoxEntityList.remove(0);
                GiftBoxView.this.setBadgeCount();
                if (GiftBoxView.this.giftBoxEntityList.size() == 0) {
                    GiftBoxView.this.showEmptyBox();
                } else {
                    GiftBoxView.this.showLoading();
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                GiftBoxView.this.expiredDisposable = disposable;
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showOpenBoxAnim() {
        this.mState = State.OPENING;
        this.ivIcon.setImageResource(R.drawable.fq_imgs_box_open);
        this.tvShowTip.setVisibility(View.VISIBLE);
        this.tvShowTip.setText(AppUtils.isConsumptionPermissionUser() ? R.string.fq_receive_box : R.string.fq_receive_task_box);
    }

    public void setBadgeCount() {
        int size = this.giftBoxEntityList.size();
        if (size == 1) {
            size = 0;
        }
        this.qBadgeView.setBadgeNumber(size);
    }

    public void clear() {
        this.boxRoot.setVisibility(View.GONE);
        this.giftBoxEntityList.clear();
        this.mState = State.INIT;
        cancelExpiredDisposable();
        cancelCountDownDisposable();
        cancelChronographDisposable();
        cancelLoading();
    }

    public void release() {
        clear();
    }
}
