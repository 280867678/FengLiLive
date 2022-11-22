package com.slzhibo.library.ui.activity.live;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.slzhibo.library.R;
import com.slzhibo.library.base.BaseFragment;
import com.slzhibo.library.download.GiftDownLoadManager;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.BoomStatusEntity;
import com.slzhibo.library.model.ChatEntity;
import com.slzhibo.library.model.CheckTicketEntity;
import com.slzhibo.library.model.ComponentsEntity;
import com.slzhibo.library.model.GiftDownloadItemEntity;
import com.slzhibo.library.model.GuardItemEntity;
import com.slzhibo.library.model.HJProductEntity;
import com.slzhibo.library.model.LiveEndEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.LiveInitInfoEntity;
import com.slzhibo.library.model.LiveItemEntity;
import com.slzhibo.library.model.LotteryBoomDetailEntity;
import com.slzhibo.library.model.MyAccountEntity;
import com.slzhibo.library.model.OnLineUsersEntity;
import com.slzhibo.library.model.PKRecordEntity;
import com.slzhibo.library.model.PropConfigEntity;
import com.slzhibo.library.model.QMInteractTaskEntity;
import com.slzhibo.library.model.TaskBoxEntity;
import com.slzhibo.library.model.TrumpetStatusEntity;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.model.WSAddressEntity;
import com.slzhibo.library.model.WatermarkConfigEntity;
import com.slzhibo.library.model.YYLinkApplyEntity;
import com.slzhibo.library.model.YYNoticeEntity;
import com.slzhibo.library.model.db.GiftBoxEntity;
import com.slzhibo.library.model.db.LiveDataEntity;
import com.slzhibo.library.model.db.WatchRecordEntity;
import com.slzhibo.library.model.event.AttentionEvent;
import com.slzhibo.library.model.event.LiveTopAttentionEvent;
import com.slzhibo.library.ui.presenter.SLLivePresenter;
import com.slzhibo.library.ui.view.custom.ComponentsView;
import com.slzhibo.library.ui.view.custom.GiftBoxView;
import com.slzhibo.library.ui.view.custom.LivePayEnterView;
import com.slzhibo.library.ui.view.custom.LivePusherInfoView;
import com.slzhibo.library.ui.view.custom.YYLinkSeatListView;
import com.slzhibo.library.ui.view.dialog.LoadingDialog;
import com.slzhibo.library.ui.view.dialog.alert.ComposeDialog;
import com.slzhibo.library.ui.view.iview.ISLLiveView;
import com.slzhibo.library.ui.view.widget.badgeView.QBadgeView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.CacheUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DBUtils;
import com.slzhibo.library.utils.DateUtils;
import com.slzhibo.library.utils.GlideUtils;
import com.slzhibo.library.utils.HandlerUtils;
import com.slzhibo.library.utils.LogEventUtils;
import com.slzhibo.library.utils.NetUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.RxTimerUtils;
import com.slzhibo.library.utils.SwipeAnimationController;
import com.slzhibo.library.utils.SysConfigInfoManager;
import com.slzhibo.library.utils.UserInfoManager;
import com.slzhibo.library.utils.live.PlayManager;
import com.slzhibo.library.utils.rxlifecycle2.android.FragmentEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SLLiveFragment extends BaseFragment<SLLivePresenter> implements ISLLiveView {
    private String uploadDataEnterSource;
    private PlayManager playManager;
    private volatile boolean isGetGiftListFail;
    private Handler workHandler;
    private Handler mainHandler;
    private String chargeType = "0";
    private String ticketPrice = "0";
    private String liveEnterWay = "2";
    private LiveEntity liveListItemEntity;
    private boolean isFirstInitPlayManager = false;
    private String liveId;
    private String liveCount;
    private boolean liveStatus = false;
    private AnchorEntity anchorItemEntity;
    private FrameLayout rootView;
    private String pullStreamUrl;
    private String anchorAppId;
    private String anchorId;
    private volatile boolean isPayLive = false;
    private volatile boolean isBuyTicket = false;
    private ImageView mAnchorCoverImg;
    private List<String> pullStreamUrlList = new ArrayList(3);
    private volatile boolean isLoginRequest = false;
    private volatile List<String> shieldedList = new ArrayList();
    private boolean isStartGetAnchorInfo;
    private volatile boolean isLotteryBoomStatus = false;
    private ComponentsEntity cacheRecommendComponents;
    private ComponentsView ivRecommendComponents;
    private volatile boolean isAllBan;
    private boolean isContinueCombo;
    private boolean isGiftListUpdating;
    private boolean isLastVoiceStatusOpen;
    private volatile boolean isNormalBan;
    private boolean isPausing;
    private boolean isRTCStream;
    private volatile boolean isSocketReConn;
    private volatile boolean isSuperBan;
    private boolean isTranOpen;
    private GiftBoxView mGiftBoxView;
    private LiveItemEntity liveItemEntity;
    private boolean startGetGiftListInfo;
    private volatile boolean isFirstLoadTask = true;
    private LiveEndEntity lastLiveEndEntity;
    private volatile boolean getUserBalanceFail;
//    private GiftBackpackDialog giftBottomDialog;
    private double myPriceBalance = 0.0d;
    private double myScoreBalance = 0.0d;
    private int liveType = 1;
    private String liveRecommendGameId;
    private String speakLevel = "1";
    private volatile boolean isBanGroup = false;
    private UserEntity myUserInfoEntity;
    private int nobilityTypeThresholdForHasPreventBanned = 7;
    private int postIntervalTimes = 1;
    private LivePayEnterView livePayEnterView = null;
    private GuardItemEntity guardItemEntity;
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private volatile boolean isPayLiveTipsDialog = false;
    private volatile boolean showGuideRating = true;
    private long livingStartTime = 0;
    private View mLiveGuideView = null;
    private YYLinkSeatListView yyLinkSeatListView;

    private Handler.Callback workCallBack = new Handler.Callback() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$QMhHaJqn53LIiZBaMqZaNCV5cdA
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return SLLiveFragment.this.lambda$new$61$SLLiveFragment(message);
        }
    };

    private RelativeLayout mLiveBgView;
    private ImageView ivMore;
    private TextView tvInput;
    private RelativeLayout mRlControllerView;
    private View giftButton;
    private ImageView ivClosed;
    private ViewStub mLiveGuideViewVs;
    private View titleTopView;
    private RelativeLayout rlWatermarkShadowBg;
    private TextView tvWatermarkTitle;
    private TextView tvWatermarkRoom;
    private TextView tvWatermarkUrl;
    private ImageView ivWatermarkLogo;
    private ImageView ivYYLink;
    private ImageView ivYYLinkBgCover;
    private SwipeAnimationController swipeAnimationController;
    private LoadingDialog loadingDialog;
    private ComponentsView ivComponentsView;
    private QBadgeView qBadgeView;
    private LivePusherInfoView mLivePusherInfoView;
//    private LiveLoadingView mLiveLoadingView;
private Disposable pullTimeOutTimer;

    public /* synthetic */ boolean lambda$new$61$SLLiveFragment(Message message) {
        Toast.makeText(mActivity, "72"+message.what, Toast.LENGTH_SHORT).show();
        switch (message.what) {

//            case 10001:
//                dealChatMsg();
//                return true;
//            case 10002:
//                dealEnterMsg();
//                return true;
//            case 10003:
//                dealGuardEnterMsg();
//                return true;
//            case 10004:
//                dealGiftNotice();
//                return true;
//            case 10005:
//                dealSysNotice();
//                return true;
//            case 10006:
//                dealPrivateMsg();
//                return true;
//            case 10007:
//            case 10008:
//            case 10009:
//            default:
//                return true;
//            case ConstantUtils.SYS_LUCK_HIT /* 10010 */:
//                dealSysLuckNotice();
//                return true;
//            case ConstantUtils.ANCHOR_INFO_NOTICE /* 10011 */:
//                dealAnchorInfoNotice();
//                return true;
//            case ConstantUtils.GAME_NOTICE /* 10012 */:
//                dealGameNotice();
//                return true;
//            case ConstantUtils.INTIMATE_TASK_NOTICE /* 10013 */:
//                dealIntimateTask();
//                return true;
        }
        return true;
    }



    //    private OnFragmentInteractionListener onFragmentInteractionListener;


    public interface OnFragmentInteractionListener {
        void setViewPagerScroll(boolean z);

        void updateLiveRoomInfo();
    }


    public static SLLiveFragment newInstance(LiveEntity liveEntity, String str) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("LIVE_ITEM", liveEntity);
        bundle.putString(ConstantUtils.RESULT_FLAG, str);
        SLLiveFragment sLLiveFragment = new SLLiveFragment();
        sLLiveFragment.setArguments(bundle);
        return sLLiveFragment;
    }


    public void onFragmentPageChangeListener() {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.clearAnimQueue();
//        }
    }

    public void resetLiveRoom(LiveEntity liveEntity, String str) {
        this.uploadDataEnterSource = getString(R.string.fq_hot_list);
        releasePlay();
        onReleaseViewData();
        initGiftDownloadData();
        this.workHandler = HandlerUtils.getInstance().startIOThread(SLLiveFragment.class.getName(), this.workCallBack);
        this.mainHandler = new MainHandler(this, Looper.getMainLooper());
        this.liveEnterWay = str;
        this.liveListItemEntity = liveEntity;
        if (this.liveListItemEntity != null) {
            this.isFirstInitPlayManager = false;
            initSendLiveInitInfoRequest();
        }
        initListener(this.mFragmentRootView);
    }

    public class MainHandler extends Handler {
        public MainHandler(SLLiveFragment sLLiveFragment, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    private void initSendLiveInitInfoRequest() {
        SPUtils.getInstance().put(ConstantUtils.IS_CLOSE_QM_WINDOW, false);
        LiveEntity liveEntity = this.liveListItemEntity;
        this.liveId = liveEntity.liveId;
        this.liveCount = liveEntity.liveCount;
        this.liveStatus = liveEntity.isOnLiving();
        this.anchorItemEntity = getAnchorEntityInfo(this.liveListItemEntity);
        setAnchorCoverImg();
        if (this.isFirstInitPlayManager) {
            updatePullStreamUrl();
            this.playManager.initRoomPlayManager(this.rootView, this.pullStreamUrl);
        }
        if (TextUtils.equals("2", this.liveEnterWay)) {
            ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, true);
        } else if (this.liveListItemEntity.isTimePayLive()) {
            showToast(R.string.fq_pay_time_live_toast);
            onFinishActivity();
        } else if (this.liveListItemEntity.isRelationBoolean()) {
            ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, false);
        } else if (!isPayLiveNeedBuyTicket()) {
            if (this.liveListItemEntity.isPayLiveTicket()) {
                this.isPayLive = isPayLiveTicket();
                this.isBuyTicket = false;
                this.chargeType = this.liveListItemEntity.chargeType;
            }
            sendLiveInitInfoRequest();
        } else if (DBUtils.isPayLiveValidState(this.liveId, this.liveCount)) {
            sendLiveInitInfoRequest(false, true, false);
        } else {
            ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, false);
        }
    }

    public void sendLiveInitInfoRequest() {
        sendLiveInitInfoRequest(false, this.isPayLive, this.isBuyTicket);
    }

    public void sendLiveInitInfoRequest(boolean z, boolean z2, boolean z3) {
        this.isPayLive = z2;
        this.isBuyTicket = z3;
        ((SLLivePresenter) this.mPresenter).getLiveInitInfo(this.liveId, this.liveEnterWay, z, z2, z3, this.isLoginRequest);
    }

    private boolean isPayLiveTicket() {
        LiveEntity liveEntity = this.liveListItemEntity;
        return liveEntity != null && liveEntity.isPayLiveTicket();
    }

    private boolean isPayLiveNeedBuyTicket() {
        LiveEntity liveEntity = this.liveListItemEntity;
        return liveEntity != null && liveEntity.isPayLiveNeedBuyTicket();
    }

    public void onFinishActivity() {
//        stopSocket();
        this.mActivity.onBackPressed();
    }

    public void updatePullStreamUrl() {
        this.pullStreamUrl = this.liveListItemEntity.getDefPullStreamUrlStr();
        this.pullStreamUrlList.clear();
        this.pullStreamUrlList.addAll(this.liveListItemEntity.getPullStreamUrlList());
    }

    private void setAnchorCoverImg() {
        if (this.mAnchorCoverImg.getVisibility() != View.VISIBLE) {
            this.mAnchorCoverImg.setVisibility(View.VISIBLE);
        }
        GlideUtils.loadImageBlur(this.mContext, this.mAnchorCoverImg, this.anchorItemEntity.liveCoverUrl, R.drawable.fq_shape_default_cover_bg);
    }

    private AnchorEntity getAnchorEntityInfo(LiveEntity liveEntity) {
        AnchorEntity anchorEntity = new AnchorEntity();
        anchorEntity.appId = liveEntity.appId;
        anchorEntity.openId = liveEntity.openId;
        anchorEntity.liveId = liveEntity.liveId;
        anchorEntity.liveCount = liveEntity.liveCount;
        anchorEntity.tag = liveEntity.tag;
        anchorEntity.userId = TextUtils.isEmpty(liveEntity.anchorId) ? liveEntity.userId : liveEntity.anchorId;
        anchorEntity.avatar = liveEntity.avatar;
        anchorEntity.sex = liveEntity.sex;
        anchorEntity.nickname = liveEntity.nickname;
        anchorEntity.expGrade = liveEntity.expGrade;
        anchorEntity.liveCoverUrl = TextUtils.isEmpty(liveEntity.liveCoverUrl) ? liveEntity.avatar : liveEntity.liveCoverUrl;
        this.anchorId = anchorEntity.userId;
        this.anchorAppId = anchorEntity.appId;
        return anchorEntity;
    }




    private void initGiftDownloadData() {
        List<GiftDownloadItemEntity> localDownloadListFilterLuckyGift = GiftDownLoadManager.getInstance().getLocalDownloadListFilterLuckyGift();
        if (localDownloadListFilterLuckyGift == null || localDownloadListFilterLuckyGift.size() == 0) {
            this.isGetGiftListFail = true;
            return;
        }
        this.isGetGiftListFail = false;
        initGiftDialog(localDownloadListFilterLuckyGift);
    }

    private void initGiftDialog(List<GiftDownloadItemEntity> list) {
        Toast.makeText(mActivity, "initGiftDialog 355", Toast.LENGTH_SHORT).show();
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog == null) {
//            this.giftBottomDialog = GiftBackpackDialog.create(isBluetoothConnection(), list, new AnonymousClass30());
//        } else {
//            giftBackpackDialog.updateGiftList(list);
//        }
    }

    private void onReleaseViewData() {
//        YYLinkSendApplyDialog yYLinkSendApplyDialog;
//        thermometerDisposable();
//        SPUtils.getInstance().put(ConstantUtils.MUTE_KEY, false);
//        SPUtils.getInstance().put(ConstantUtils.NOTICE_GAME_KEY, true);
//        if (this.isRTCStream || ((yYLinkSendApplyDialog = this.linkSendApplyDialog) != null && yYLinkSendApplyDialog.isUserLinkApplying())) {
//            ((SLLivePresenter) this.mPresenter).onSendUserDisconnectLinkRequest(isVoiceRoomType(), this.liveId, this.liveCount, null);
//            stopRTC();
//        }
//        watchRecordReport();
//        uploadLeaveLiveEvent();
//        if (isLiving()) {
//            ((SLLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(ConstantUtils.LEAVE_TYPE);
//        }
//        this.swipeAnimationController.resetClearScreen();
//        showContentView(258, true);
//        showLiveLoadingView(4);
//        HandlerUtils.getInstance().stopIOThread();
//        stopSocket();
//        dismissDialogs();
//        GiftBoxView giftBoxView = this.mGiftBoxView;
//        if (giftBoxView != null) {
//            giftBoxView.setVisibility(4);
//            this.mGiftBoxView.release();
//        }
//        LiveAdBannerBottomView liveAdBannerBottomView = this.mLiveAdBannerBottomView;
//        if (liveAdBannerBottomView != null) {
//            liveAdBannerBottomView.onReleaseAllView();
//        }
//        TaskBottomDialog taskBottomDialog = this.mTaskBottomDialog;
//        if (taskBottomDialog != null) {
//            taskBottomDialog.onDestroy();
//            this.mTaskBottomDialog = null;
//        }
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.onRelease();
//        }
//        LiveChatMsgView liveChatMsgView = this.mLiveChatMsgView;
//        if (liveChatMsgView != null) {
//            liveChatMsgView.onRelease();
//        }
//        if (this.livePayEnterView != null && isPayLiveNeedBuyTicket()) {
//            this.livePayEnterView.onRelease();
//        }
//        LotteryDialog lotteryDialog = this.mLotteryDialog;
//        if (lotteryDialog != null) {
//            lotteryDialog.onRelease();
//            this.mLotteryDialog = null;
//        }
//        onLotteryBoomClosed();
//        Handler handler = this.mainHandler;
//        if (handler != null) {
//            handler.removeCallbacksAndMessages(null);
//        }
//        Handler handler2 = this.workHandler;
//        if (handler2 != null) {
//            handler2.removeCallbacksAndMessages(null);
//        }
//        Disposable disposable = this.cdDisposable;
//        if (disposable != null && !disposable.isDisposed()) {
//            this.cdDisposable.dispose();
//            this.cdDisposable = null;
//        }
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.release();
//            this.giftBottomDialog = null;
//        }
//        ComponentsWebViewDialog componentsWebViewDialog = this.componentsWebViewDialog;
//        if (componentsWebViewDialog != null) {
//            componentsWebViewDialog.onRelease();
//        }
//        HdLotteryDrawingDialog hdLotteryDrawingDialog = this.hdLotteryDrawingDialog;
//        if (hdLotteryDrawingDialog != null) {
//            hdLotteryDrawingDialog.onReleaseData();
//        }
//        if (this.isEnablePK) {
//            stopLinkMicPk();
//        }
//        LiveAnimationView liveAnimationView = this.liveAnimationView;
//        if (liveAnimationView != null) {
//            liveAnimationView.onDestroy();
//        }
//        if (this.liveEndEvaluationDialog != null) {
//            this.liveEndEvaluationDialog = null;
//        }
//        if (this.qmInteractUserDialog != null) {
//            this.qmInteractUserDialog = null;
//        }
//        clearQMNoticeAnimView();
//        payLiveTipsDialogOnRelease();
//        if (isVoiceRoomType()) {
//            voiceRoomViewDataRelease();
//        }
//        ((SLLivePresenter) this.mPresenter).clearCompositeDisposable();
//        clearAllMapData();
//        resetAllField();
    }


    private void releasePlay() {
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.releaseLivePlay();
        }
    }



    private void onDestroyPlay() {
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.onDestroyPlay();
        }
    }


    public void onNetNone() {
        RxTimerUtils.getInstance().timerBindDestroyFragment(getLifecycleProvider(), 2L, TimeUnit.SECONDS, new RxTimerUtils.RxTimerAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$aX7vnT-aj-28UBWL-AFcDbN2VCo
            @Override // com.slzhibo.library.utils.RxTimerUtils.RxTimerAction
            public final void action(long j) {
                SLLiveFragment.this.lambda$onNetNone$80$SLLiveFragment(j);
            }
        });
    }

    public /* synthetic */ void lambda$onNetNone$80$SLLiveFragment(long j) {
        if (!NetUtils.isNetworkAvailable()) {
            showToast(R.string.fq_text_no_network);
        }
    }

    public void on4G() {
//        NetworkPromptDialog.newInstance(getResources().getString(R.string.fq_text_mobile_net), getResources().getString(R.string.fq_text_go_on), getResources().getString(R.string.fq_text_stop), $$Lambda$SLLiveFragment$9IUj1i8zqes3AKv6_IGVvk5wt9g.INSTANCE, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$FzchQRLCwtdj0n0oQxUDSNhhkQY
//            @Override // android.view.View.OnClickListener
//            public final void onClick(View view) {
//                SLLiveFragment.this.lambda$on4G$82$SLLiveFragment(view);
//            }
//        }).show(getChildFragmentManager());
    }





    @Override
    public SLLivePresenter createPresenter() {
        return new SLLivePresenter(this.mContext, this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fq_fragment_sl_live;
    }

    @Override
    public void initView(View view, @Nullable Bundle bundle) {
        this.workHandler = HandlerUtils.getInstance().startIOThread(SLLiveFragment.class.getName(), this.workCallBack);
        this.mainHandler = new MainHandler(this, Looper.getMainLooper());
        this.playManager = new PlayManager(this.mContext);
        this.shieldedList = DBUtils.getShieldList();
        ((SLLivePresenter) this.mPresenter).initLocalComponentsCache();
        CacheUtils.updateCacheVersion();
        initControlView(view);
        initGiftDownloadData();
        if (this.liveListItemEntity != null) {
            this.isFirstInitPlayManager = true;
            initSendLiveInitInfoRequest();
        }
        SPUtils.getInstance().put(ConstantUtils.MUTE_KEY, false);
    }

    @SuppressLint("WrongConstant")
    private void initControlView(View view) {
        int i = 0;
        try {
            this.mImmersionBar = ImmersionBar.with(this.mActivity);
            this.mImmersionBar.transparentStatusBar().statusBarView(view.findViewById(R.id.title_top_view)).statusBarDarkFont(false).init();
        } catch (Exception unused) {
        }
        this.rootView = (FrameLayout) view.findViewById(R.id.rl_play_root);
        this.mLiveBgView = (RelativeLayout) view.findViewById(R.id.rl_live_bg);
        this.ivMore = (ImageView) view.findViewById(R.id.iv_private_message);
        this.tvInput = (TextView) view.findViewById(R.id.iv_input);
//        this.mLivePusherInfoView = (LivePusherInfoView) view.findViewById(R.id.ll_pusher_info);
        this.mRlControllerView = (RelativeLayout) view.findViewById(R.id.rl_control_layout);
        this.mAnchorCoverImg = (ImageView) view.findViewById(R.id.iv_anchor_cover);
//        this.mLiveLoadingView = (LiveLoadingView) view.findViewById(R.id.live_loading_view);
//        this.vsLiveEndInfoView = (ViewStub) view.findViewById(R.id.live_end_view);
//        this.liveAnimationView = (LiveAnimationView) view.findViewById(R.id.live_anim_view);
//        this.mLiveChatMsgView = (LiveChatMsgView) view.findViewById(R.id.live_chat_msg_view);
        this.giftButton = view.findViewById(R.id.iv_gift);
        this.ivClosed = (ImageView) view.findViewById(R.id.iv_closed);
        this.mLiveGuideViewVs = (ViewStub) view.findViewById(R.id.live_guide_view);
//        this.ivComponentsView = (ComponentsView) view.findViewById(R.id.iv_components);
//        this.mLiveAdBannerBottomView = (LiveAdBannerBottomView) view.findViewById(R.id.live_bottom_banner_view);
//        this.ivRecommendComponents = (ComponentsView) view.findViewById(R.id.iv_recommend_components);
//        this.mGiftBoxView = (GiftBoxView) this.mLivePusherInfoView.findViewById(R.id.gift_box_view);
//        this.vsLivePayEnterView = (ViewStub) view.findViewById(R.id.pay_enter_view);
//        this.vsPKInfoView = (ViewStub) view.findViewById(R.id.fq_pk_info_view);
        this.titleTopView = view.findViewById(R.id.title_top_view);
        this.rlWatermarkShadowBg = (RelativeLayout) view.findViewById(R.id.rl_watermark_shadow_bg);
        this.tvWatermarkTitle = (TextView) view.findViewById(R.id.tv_watermark_title);
        this.tvWatermarkRoom = (TextView) view.findViewById(R.id.tv_watermark_room);
        this.tvWatermarkUrl = (TextView) view.findViewById(R.id.tv_watermark_url);
        this.ivWatermarkLogo = (ImageView) view.findViewById(R.id.iv_watermark_logo);
        this.ivYYLink = (ImageView) view.findViewById(R.id.iv_yy_link);
        this.ivYYLinkBgCover = (ImageView) view.findViewById(R.id.iv_link_cover);
//        this.yyLinkSeatListView = (YYLinkSeatListView) this.mLivePusherInfoView.findViewById(R.id.seat_list_view);
//        this.lyControlWindowViewStub = (ViewStub) view.findViewById(R.id.vs_ly_control_view);
//        this.lyCommandWindowViewStub = (ViewStub) view.findViewById(R.id.vs_ly_command_view);
        this.swipeAnimationController = new SwipeAnimationController(this.mContext, this.mRlControllerView);
        this.loadingDialog = new LoadingDialog(this.mContext);
        ComponentsView componentsView = this.ivComponentsView;
        if (!AppUtils.isEnableComponents()) {
            i = 8;
        }
        componentsView.setVisibility(i);
        this.ivComponentsView.initCoverDrawableRes(R.drawable.fq_ic_live_game);
        if (AppUtils.isEnablePrivateMsg() || AppUtils.isEnableQMInteract()) {
            this.qBadgeView = new QBadgeView(this.mContext);
            this.qBadgeView.bindTarget(this.ivMore).setBadgeTextColor(-1).setBadgePadding(1.0f, true).isNoNumber(true).setBadgeGravity(8388661).setBadgeBackgroundColor(ContextCompat.getColor(this.mContext, R.color.fq_colorRed)).stroke(-1, 1.0f, true);
            updateMoreRedDot();
        }
//        this.mInputTextMsgDialog = new InputTextMsgForAudienceDialog(this.mActivity, this);
//        this.transDialog = TransDialog.newInstance(new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$H60N9vm4bG339vCWBouXzmoaFhE
//            @Override // android.view.View.OnClickListener
//            public final void onClick(View view2) {
//                SLLiveFragment.this.lambda$initControlView$0$SLLiveFragment(view2);
//            }
//        });
//        this.componentsWebViewDialog = new ComponentsWebViewDialog(this.mContext, new WebViewJSCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.2
//            @Override // com.slzhibo.library.ui.interfaces.WebViewJSCallback
//            public void onLiveBalanceUpdate() {
//                ((SLLivePresenter) ((BaseFragment) SLLiveFragment.this).mPresenter).getUserOver();
//            }
//        });
//        this.hdLotteryDrawingDialog = new HdLotteryDrawingDialog(this.mContext, R.style.fq_GeneralDialogStyle, new SimpleHdLotteryCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.3
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleHdLotteryCallback, com.slzhibo.library.ui.interfaces.OnHdLotteryCallback
//            public void onJoinLotteryListener(final GiftDownloadItemEntity giftDownloadItemEntity, final String str) {
//                super.onJoinLotteryListener(giftDownloadItemEntity, str);
//                if (!SLLiveFragment.this.isConsumptionPermissionUserToLogin() || !SLLiveFragment.this.isCanSendGift()) {
//                    return;
//                }
//                if (SLLiveFragment.this.isFirstGetMyBalanceGift) {
//                    SLLiveFragment.this.isFirstGetMyBalanceGift = false;
//                    ((SLLivePresenter) ((BaseFragment) SLLiveFragment.this).mPresenter).getUserOver(true, new ResultCallBack<MyAccountEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.3.1
//                        public void onSuccess(MyAccountEntity myAccountEntity) {
//                            SLLiveFragment.this.sendHdLotteryGift(giftDownloadItemEntity, str);
//                        }
//
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onError(int i2, String str2) {
//                            SLLiveFragment.this.showToast(R.string.fq_userover_loading_fail);
//                        }
//                    });
//                    return;
//                }
//                SLLiveFragment.this.sendHdLotteryGift(giftDownloadItemEntity, str);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleHdLotteryCallback, com.slzhibo.library.ui.interfaces.OnHdLotteryCallback
//            public void onFloatingWindowCloseListener() {
//                super.onFloatingWindowCloseListener();
//                SLLiveFragment.this.mLiveAdBannerBottomView.onReleaseHdLotteryWindowView();
//            }
//        });
    }
    private void updateMoreRedDot() {
        if (this.qBadgeView != null) {
            this.qBadgeView.setBadgeNumber((DBUtils.isUnReadBoolean() || SysConfigInfoManager.getInstance().isEnableQMInteractRedDot()) ? -1 : 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResultError(int i) {

    }

    @Override
    public void onAnchorInfoFail() {
        this.isStartGetAnchorInfo = false;
    }

    @Override
    public void onAnchorInfoSuccess(AnchorEntity anchorEntity) {
        if (anchorEntity != null) {
            anchorEntity.userRole = "1";
            anchorEntity.role = "1";
//            if (AppUtils.isNobilityUser(anchorEntity.nobilityType)) {
//                this.onUserCardCallback = new UserCardCallback(anchorEntity.userId, true);
//                if (this.anchorNobilityAvatarDialog == null) {
//                    this.anchorNobilityAvatarDialog = UserNobilityAvatarDialog.newInstance(anchorEntity, this.onUserCardCallback);
//                    this.anchorNobilityAvatarDialog.show(getChildFragmentManager());
//                } else {
//                    this.bundleArgs = new Bundle();
//                    this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_MANAGER, true);
//                    this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_IMPRESSION, true);
//                    this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_REPORT, true);
//                    this.bundleArgs.putInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, 2);
//                    this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, anchorEntity);
//                    this.anchorNobilityAvatarDialog.setArguments(this.bundleArgs);
//                    this.anchorNobilityAvatarDialog.setOnUserCardCallback(this.onUserCardCallback);
//                    showDialogFragment(this.anchorNobilityAvatarDialog);
//                }
//                this.isStartGetAnchorInfo = false;
//                return;
//            }
//            this.onUserCardCallback = new UserCardCallback(anchorEntity.userId, true);
//            if (this.anchorAvatarDialog == null) {
//                this.anchorAvatarDialog = UserNormalAvatarDialog.newInstance(anchorEntity, this.onUserCardCallback);
//                this.anchorAvatarDialog.show(getChildFragmentManager());
//            } else {
//                this.bundleArgs = new Bundle();
//                this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_MANAGER, true);
//                this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_IMPRESSION, true);
//                this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_REPORT, true);
//                this.bundleArgs.putInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, 2);
//                this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, anchorEntity);
//                this.anchorAvatarDialog.setArguments(this.bundleArgs);
//                this.anchorAvatarDialog.setOnUserCardCallback(this.onUserCardCallback);
//                showDialogFragment(this.anchorAvatarDialog);
//            }
            this.isStartGetAnchorInfo = false;
        }
    }

    @Override
    public void onAttentionSuccess() {
        EventBus.getDefault().postSticky(new AttentionEvent());
        EventBus.getDefault().postSticky(new LiveTopAttentionEvent());
    }

    @Override
    public void onBoomStatusFail() {
        onLotteryBoomClosed();
    }

    private void onLotteryBoomClosed() {
        this.isLotteryBoomStatus = false;
        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
        if (componentsEntity == null || !componentsEntity.isCacheLotteryComponents()) {
            this.ivComponentsView.onLotteryBoomClosed();
        } else {
            this.ivRecommendComponents.onLotteryBoomClosed();
        }
    }

    @Override
    public void onBoomStatusSuccess(BoomStatusEntity boomStatusEntity) {
        LotteryBoomDetailEntity lotteryBoomDetailEntity = boomStatusEntity.rich;
        LotteryBoomDetailEntity lotteryBoomDetailEntity2 = boomStatusEntity.normal;
        if (lotteryBoomDetailEntity != null && lotteryBoomDetailEntity.boomStatus > -1) {
            onLotteryBoomOpen(lotteryBoomDetailEntity.boomPropUrl, lotteryBoomDetailEntity.boomMultiple, lotteryBoomDetailEntity.boomRemainTime, lotteryBoomDetailEntity.boomTotalTime, 20);
        } else if (lotteryBoomDetailEntity2 == null || lotteryBoomDetailEntity2.boomStatus <= -1) {
            onLotteryBoomClosed();
        } else {
            onLotteryBoomOpen(lotteryBoomDetailEntity2.boomPropUrl, lotteryBoomDetailEntity2.boomMultiple, lotteryBoomDetailEntity2.boomRemainTime, lotteryBoomDetailEntity2.boomTotalTime, 1);
        }
    }

    private void onLotteryBoomOpen(String str, int i, int i2, int i3, int i4) {
        this.isLotteryBoomStatus = true;
        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
        if (componentsEntity == null || !componentsEntity.isCacheLotteryComponents()) {
            this.ivComponentsView.onLotteryBoomOpen(str, i, i2, i3, i4);
        } else {
            this.ivRecommendComponents.onLotteryBoomOpen(str, i, i2, i3, i4);
        }
    }

    @Override
    public void onFragmentConfigSuccess(PropConfigEntity propConfigEntity) {
        ComposeDialog.newInstance(propConfigEntity, true, new ComposeDialog.ComposeListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$U-aM1Jeq1nc1Y8xv44wvifQR3pQ
            @Override // com.slzhibo.library.ui.view.dialog.alert.ComposeDialog.ComposeListener
            public final void onClick(PropConfigEntity propConfigEntity2) {
                SLLiveFragment.this.lambda$onFragmentConfigSuccess$13$SLLiveFragment(propConfigEntity2);
            }
        }).show(getChildFragmentManager());
    }

    public /* synthetic */ void lambda$onFragmentConfigSuccess$13$SLLiveFragment(PropConfigEntity propConfigEntity) {
        ((SLLivePresenter) this.mPresenter).getUsePropService(propConfigEntity, this.liveId);
    }

    @Override
    public void onGiftBoxListSuccess(List<GiftBoxEntity> list) {
        if (this.mGiftBoxView != null && !list.isEmpty()) {
            this.mGiftBoxView.setVisibility(View.VISIBLE);
            this.mGiftBoxView.showBoxList(list, this.liveId);
        }
    }

    @Override
    public void onGiftListSuccess(List<GiftDownloadItemEntity> list) {
        LiveItemEntity liveItemEntity;
//        HdLotteryDrawingDialog hdLotteryDrawingDialog;
        if (this.isGiftListUpdating && (liveItemEntity = this.liveItemEntity) != null && liveItemEntity.isEnableHdLottery() ) {//&& (hdLotteryDrawingDialog = this.hdLotteryDrawingDialog) != null) {
            Toast.makeText(mActivity, "hdLotteryDrawingDialog.updateGiftInfo() 757", Toast.LENGTH_SHORT).show();
//            hdLotteryDrawingDialog.updateGiftInfo();
        }
        this.isGiftListUpdating = false;
        this.isGetGiftListFail = false;
        this.startGetGiftListInfo = false;
        initGiftDialog(list);
    }

    @Override
    public void onLiveAdListFail(String str) {

    }

    @Override
    public void onLiveAdListSuccess(String str, List<BannerEntity> list) {
        LivePusherInfoView livePusherInfoView;
//        LiveAdBannerBottomView liveAdBannerBottomView;
        LivePusherInfoView livePusherInfoView2;
        AnchorEntity anchorEntity = this.anchorItemEntity;
        String str2 = anchorEntity == null ? "" : anchorEntity.openId;
        if (TextUtils.equals(str, "2") && (livePusherInfoView2 = this.mLivePusherInfoView) != null) {
            livePusherInfoView2.initAdBannerImages(this.anchorAppId, str2, list);
        }
//        if (TextUtils.equals(str, "7") && (liveAdBannerBottomView = this.mLiveAdBannerBottomView) != null) {
//            liveAdBannerBottomView.initAdBannerImages(this.anchorAppId, str2, list);
//        }
        if (TextUtils.equals(str, "3") && (livePusherInfoView = this.mLivePusherInfoView) != null) {
            livePusherInfoView.initVerticalAdImage(this.anchorAppId, str2, list);
        }
    }

    @Override
    public void onLiveAdNoticeSuccess(BannerEntity bannerEntity) {
        if (!TextUtils.isEmpty(bannerEntity.content)) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgType(10);
            chatEntity.setMsgText(bannerEntity.content);
//            this.mLiveChatMsgView.addChatMsg(chatEntity);
        }
    }

    @Override
    public void onLiveAudiencesSuccess(OnLineUsersEntity onLineUsersEntity) {
        if (!(onLineUsersEntity == null || onLineUsersEntity.getUserEntityList() == null)) {
            this.mLivePusherInfoView.replaceData(onLineUsersEntity.getUserEntityList());
        }
        this.mLivePusherInfoView.updateVipCount(onLineUsersEntity.vipCount);
    }

    @Override
    public void onLiveInitInfoFail(int i, String str) {
        Toast.makeText(mActivity, "onLiveInitInfoFail 825", Toast.LENGTH_SHORT).show();
//        LivePayEnterView livePayEnterView;
//        if (!AppUtils.isTokenInvalidErrorCode(i)) {
//            if (AppUtils.isNoEnterLivePermissionErrorCode(i)) {
//                onNoEnterLivePermission(str);
//            } else if (i == 200164 || i == 300004 || i == 200157 || i == 200171 || i == 200169 || i == 200165) {
//                onFinishActivity();
//            } else if (AppUtils.isKickOutErrorCode(i)) {
//                startKickDialogService();
//                onFinishActivity();
//            } else if (i == 300006) {
//                if (isPayLiveNeedBuyTicket() && (livePayEnterView = this.livePayEnterView) != null) {
//                    livePayEnterView.onReset();
//                }
//                AppUtils.onRechargeListener(this.mContext);
//            } else if (i == 200166) {
//                this.liveCount = "";
//                ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, true);
//            } else {
//                this.isLiveEnd = true;
//                showContentView(258, true);
//                stopPlay();
//                showToast(R.string.fq_live_room_loading_fail_tips);
//                showRoomInfoReload();
//            }
//        }
    }

    @Override
    public void onLiveInitInfoSuccess(String str, String str2, LiveInitInfoEntity liveInitInfoEntity, boolean z, boolean z2) {
        Toast.makeText(mActivity, "onLiveInitInfoSuccess 857", Toast.LENGTH_SHORT).show();
        CharSequence charSequence;
        if (liveInitInfoEntity != null && TextUtils.equals(this.liveId, str)) {
//            this.socketEncryptionKey = liveInitInfoEntity.k;
            this.liveItemEntity = liveInitInfoEntity.liveDto;
            this.myUserInfoEntity = liveInitInfoEntity.myUserDto;
            this.myUserInfoEntity.setUserId(UserInfoManager.getInstance().getUserId());
            if (TextUtils.equals(str2, "2")) {
                AnchorEntity anchorEntity = liveInitInfoEntity.anchorDto;
                AnchorEntity anchorEntity2 = this.anchorItemEntity;
                anchorEntity2.userId = anchorEntity.userId;
                anchorEntity2.appId = anchorEntity.appId;
                anchorEntity2.openId = anchorEntity.openId;
                anchorEntity2.tag = anchorEntity.tag;
                anchorEntity2.avatar = anchorEntity.avatar;
                anchorEntity2.sex = anchorEntity.sex;
                anchorEntity2.nickname = anchorEntity.nickname;
                anchorEntity2.expGrade = anchorEntity.expGrade;
                this.anchorId = anchorEntity2.userId;
                this.anchorAppId = anchorEntity2.appId;
            }
            this.liveStatus = liveInitInfoEntity.isLiving();
            LiveItemEntity liveItemEntity = this.liveItemEntity;
            this.liveCount = liveItemEntity.liveCount;
            AnchorEntity anchorEntity3 = this.anchorItemEntity;
            anchorEntity3.liveCount = this.liveCount;
            anchorEntity3.topic = liveItemEntity.topic;
            this.ticketPrice = liveItemEntity.ticketPrice;
            this.liveType = NumberUtils.string2int(liveItemEntity.liveType);
            LiveItemEntity liveItemEntity2 = this.liveItemEntity;
            this.liveRecommendGameId = liveItemEntity2.gameId;
            this.speakLevel = liveItemEntity2.speakLevel;
            this.isBanGroup = UserInfoManager.getInstance().isInBanGroup();
            if (TextUtils.equals(this.myUserInfoEntity.getUserId(), this.anchorItemEntity.userId)) {
                this.myUserInfoEntity.setRole("1");
            }
            this.isAllBan = this.liveItemEntity.isBanAll();
            this.isSuperBan = this.myUserInfoEntity.isSuperBanPost();
            lambda$onBackThreadReceiveMessage$22$SLLiveFragment();
            this.nobilityTypeThresholdForHasPreventBanned = AppUtils.getNobilityTypeThresholdForHasPreventBanned();
//            this.guardItemEntity = formatAnchorGuardInfo(liveInitInfoEntity.guardDto);
//            this.myselfEnterMessageEvent = liveInitInfoEntity.formatMyselfEnterMessageEvent();
            int string2int = NumberUtils.string2int(this.liveItemEntity.postIntervalTimes);
            if (string2int >= 0) {
                this.postIntervalTimes = string2int;
            }
            startHideTitleTimer(this.liveItemEntity.topic);
//            this.onLineCount.set(NumberUtils.string2long(this.liveItemEntity.onlineUserCount));
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog != null) {
//                inputTextMsgForAudienceDialog.setMyGuardType(NumberUtils.string2int(this.guardItemEntity.userGuardType));
//                this.mInputTextMsgDialog.setSpeakWordLimit(AppUtils.getGradeSet10CharacterLimit());
//                this.mInputTextMsgDialog.setMyRole(this.myUserInfoEntity.getRole());
//                this.mInputTextMsgDialog.setMyUserGrade(this.myUserInfoEntity.getExpGrade());
//            }
            if (AppUtils.isNobilityUser()) {
                ((SLLivePresenter) this.mPresenter).getTrumpetStatus();
            }
            if (this.liveItemEntity.isEnableHdLottery()) {
                LiveItemEntity liveItemEntity3 = this.liveItemEntity;
                charSequence = "2";
                loadHdLotteryDrawInfo(liveItemEntity3.liveDrawRecordId, liveItemEntity3.prizeName, liveItemEntity3.prizeNum, liveItemEntity3.liveDrawScope, liveItemEntity3.giftMarkId, liveItemEntity3.joinNum, liveItemEntity3.giftName, liveItemEntity3.giftPrice, liveItemEntity3.giftImg, NumberUtils.string2long(liveItemEntity3.liveDrawTimeRemain), this.liveItemEntity.drawStatus);
            } else {
                charSequence = "2";
            }
            if (!this.isFirstInitPlayManager || TextUtils.equals(charSequence, this.liveEnterWay) || z2) {
                this.pullStreamUrl = this.liveItemEntity.getDefPullStreamUrlStr();
                switchStream();
            }
            this.liveListItemEntity.pullStreamUrl = this.liveItemEntity.getDefPullStreamUrlStr();
            updatePullStreamUrl();
            showContentView(256, true);
            if (isPayLiveNeedBuyTicket() && !DBUtils.isPayLiveValidState(str, this.liveCount)) {
                long string2long = NumberUtils.string2long(this.liveItemEntity.anchorContribution, -1L);
                if (string2long != -1) {
                    long longValue = string2long + new Double(AppUtils.getFormatVirtualGold(this.ticketPrice)).longValue();
                    this.liveItemEntity.anchorContribution = String.valueOf(longValue);
                }
            }
            this.mLivePusherInfoView.initData(this.liveItemEntity, this.anchorItemEntity, this.guardItemEntity);
            if (z && isLiving()) {
                showPayLiveTips();
                payLiveTipsDialogOnRelease();
                LivePayEnterView livePayEnterView = this.livePayEnterView;
                if (livePayEnterView != null) {
                    livePayEnterView.onRelease();
                }
                if (this.isBuyTicket) {
                    DBUtils.savePayLiveInfo(str, this.liveCount, String.valueOf(System.currentTimeMillis()));
                }
                showToast(R.string.fq_pay_live_ticket_verification_toast);
            }
            if (z2) {
//                stopSocket();
//                this.socketUrl = AppUtils.formatLiveSocketUrl(this.liveItemEntity.wsAddress, str, this.liveCount, this.myUserInfoEntity.getUserId(), "2", this.socketEncryptionKey);
//                initSocket();
            } else if (!this.isSocketReConn) {
                initRoomInfo(liveInitInfoEntity);
            }
        }
    }

    private void initRoomInfo(LiveInitInfoEntity liveInitInfoEntity) {
//        loadOperationActivityAdDialog();
        if (isLiving() || this.isSocketReConn) {
            this.livingStartTime = System.currentTimeMillis();
            LogEventUtils.startLiveDataTimerTask("LeaveRoom", new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    SLLiveFragment.this.saveLiveUploadData();
                }
            }, 60000L, 10000L);
            if (isLiving() && !TextUtils.equals(this.myUserInfoEntity.getUserId(), this.anchorItemEntity.userId) && this.isFirstLoadTask) {
                this.isFirstLoadTask = false;
                initTaskDialog();
            }
            goToLive();
        } else {
            LiveEndEntity liveEndEntity = liveInitInfoEntity.lastLiveData;
            if (liveEndEntity == null) {
                liveEndEntity = new LiveEndEntity();
            }
            this.lastLiveEndEntity = liveEndEntity;
            LiveEndEntity liveEndEntity2 = this.lastLiveEndEntity;
            AnchorEntity anchorEntity = this.anchorItemEntity;
            liveEndEntity2.liveId = anchorEntity.liveId;
            liveEndEntity2.userId = anchorEntity.userId;
            liveEndEntity2.avatar = anchorEntity.avatar;
            liveEndEntity2.sex = anchorEntity.sex;
            liveEndEntity2.nickname = anchorEntity.nickname;
            liveEndEntity2.expGrade = anchorEntity.expGrade;
            goToEnd();
        }
        LogEventUtils.uploadInRoom(this.anchorItemEntity, this.liveId, this.myUserInfoEntity.expGrade, this.uploadDataEnterSource);
        WatchRecordEntity watchRecordEntity = new WatchRecordEntity();
        watchRecordEntity.userId = this.myUserInfoEntity.getUserId();
        AnchorEntity anchorEntity2 = this.anchorItemEntity;
        watchRecordEntity.liveId = anchorEntity2.liveId;
        watchRecordEntity.coverUrl = anchorEntity2.liveCoverUrl;
        watchRecordEntity.label = anchorEntity2.tag;
        watchRecordEntity.title = this.liveItemEntity.topic;
        watchRecordEntity.anchorNickname = anchorEntity2.nickname;
        watchRecordEntity.liveTime = System.currentTimeMillis();
        DBUtils.saveOrUpdateWatchRecord(watchRecordEntity);
    }


    public void goToLive() {
        if (SysConfigInfoManager.getInstance().isEnableLiveGuide()) {
            this.showGuideRating = false;
            setViewPagerScrollEnable(false);
            if (this.mLiveGuideView == null) {
                this.mLiveGuideView = this.mLiveGuideViewVs.inflate();
            }
            this.mLiveGuideView.findViewById(R.id.iv_know).setOnClickListener(new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$Whqzsfo8BM1AOXdlNpOTEJJvl2M
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SLLiveFragment.this.lambda$goToLive$12$SLLiveFragment(view);
                }
            });
        } else {
            setViewPagerScrollEnable(true);
        }
        initWatermarkConfig();
        if (this.liveItemEntity.isVoiceRoomType()) {
            this.ivYYLinkBgCover.setVisibility(View.VISIBLE);
            GlideUtils.loadImage(this.mContext, this.ivYYLinkBgCover, this.liveItemEntity.backgroundUrl, R.drawable.fq_ic_yy_link_live_bg);
            setViewPagerScrollEnable(false);
            this.ivYYLink.setVisibility(View.VISIBLE);
            this.mLivePusherInfoView.setLiveAdBannerViewVisibility(false);
            initSeatData();
            ((SLLivePresenter) this.mPresenter).onSendLinkNoticeRequest(1L, this.liveId, new ResultCallBack<YYNoticeEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.23
                @Override // com.slzhibo.library.http.ResultCallBack
                public void onError(int i, String str) {
                }

                public void onSuccess(YYNoticeEntity yYNoticeEntity) {
                    SLLiveFragment.this.mLivePusherInfoView.setLinkNoticeContent(true, yYNoticeEntity == null ? null : yYNoticeEntity.content);
                }
            });
        } else {
            this.ivYYLink.setVisibility(View.GONE);
            this.ivYYLinkBgCover.setVisibility(View.INVISIBLE);
            this.yyLinkSeatListView.setVisibility(View.INVISIBLE);
            setViewPagerScrollEnable(true);
            this.mLivePusherInfoView.setLiveAdBannerViewVisibility(true);
            ((SLLivePresenter) this.mPresenter).onSendVideoLinkDetailRequest(3L, this.liveId, this.liveCount, new ResultCallBack<YYLinkApplyEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.24
                @Override // com.slzhibo.library.http.ResultCallBack
                public void onError(int i, String str) {
                }

                public void onSuccess(YYLinkApplyEntity yYLinkApplyEntity) {
                    if (yYLinkApplyEntity != null && !TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
                        yYLinkApplyEntity.status = "1";
//                        SLLiveFragment.this.mLiveAdBannerBottomView.showYYSmallWindow(yYLinkApplyEntity, SLLiveFragment.this.getString(R.string.fq_yy_linking));
                    }
                }
            });
        }
        if (this.liveItemEntity.isPKLiveRoom()) {
            GlideUtils.loadImage(this.mContext, this.mAnchorCoverImg, R.drawable.fq_ic_pk_main_bottom_bg);
            String str = this.liveId;
            AnchorEntity anchorEntity = this.anchorItemEntity;
            String str2 = anchorEntity.nickname;
            String str3 = anchorEntity.avatar;
            LiveItemEntity liveItemEntity = this.liveItemEntity;
//            showLinkPKLayoutView(str, str2, str3, liveItemEntity.lianmaiTargetAnchorId, liveItemEntity.lianmaiTargetLiveId, liveItemEntity.lianmaiTargetAnchorAvatar, liveItemEntity.lianmaiTargetAnchorName, NumberUtils.string2long(liveItemEntity.pkTimeRemain), NumberUtils.string2long(this.liveItemEntity.pkPunishTime), this.liveItemEntity.isPKStart(), true);
            if (this.liveItemEntity.isPKStart()) {
                ((SLLivePresenter) this.mPresenter).onGetFP(this.liveId, this.liveItemEntity.isPKEnd(), false);
            }
        }
        showRecommendComponentsView();
//        this.socketUrl = AppUtils.formatLiveSocketUrl(this.liveItemEntity.wsAddress, this.liveId, this.liveCount, this.myUserInfoEntity.getUserId(), "2", this.socketEncryptionKey);
//        initSocket();
        ((SLLivePresenter) this.mPresenter).getCurrentOnlineUserList(this.liveId, AppUtils.getOnlineCountSynInterval());
        ((SLLivePresenter) this.mPresenter).getLivePopularity(this.liveId, this.anchorAppId);
        ((SLLivePresenter) this.mPresenter).getGiftBoxList(5L, this.liveId);
        sendAdImageRequest();
        ((SLLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(ConstantUtils.ENTER_TYPE);
        ((SLLivePresenter) this.mPresenter).sendUserShowTaskListRequest(5L, this.liveId);
        if (AppUtils.isEnableTurntable()) {
            ((SLLivePresenter) this.mPresenter).getBoomStatus(this.liveId);
        }
        ((SLLivePresenter) this.mPresenter).onAttentionMsgNotice(new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.25
            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str4) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                if (!AppUtils.isAttentionAnchor(SLLiveFragment.this.anchorId)) {
                    ChatEntity chatEntity = new ChatEntity();
                    chatEntity.setUid(UserInfoManager.getInstance().getUserId());
                    chatEntity.setMsgText(SLLiveFragment.this.getString(R.string.fq_msg_attention_tips));
                    chatEntity.setMsgType(18);
//                    SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
                }
            }
        });
        ((SLLivePresenter) this.mPresenter).onOpenNobilityMsgNotice(new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.26
            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str4) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ChatEntity chatEntity = new ChatEntity();
                chatEntity.setMsgText(SLLiveFragment.this.getString(AppUtils.isNobilityUser() ? R.string.fq_msg_renew_nobility_tips : R.string.fq_msg_open_nobility_tips));
                chatEntity.setMsgType(17);
//                SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
            }
        });
        ((SLLivePresenter) this.mPresenter).onSendHJShelfDetailRequest(5L, this.liveId, new ResultCallBack<HJProductEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.27
            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str4) {
            }

            public void onSuccess(HJProductEntity hJProductEntity) {
                if (hJProductEntity != null) {
                    SLLiveFragment.this.liveItemEntity.shelfId = hJProductEntity.shelfId;
                    hJProductEntity.productId = hJProductEntity.id;
                    hJProductEntity.productName = hJProductEntity.name;
//                    SLLiveFragment.this.mLiveAdBannerBottomView.showHJProductWindow(false, hJProductEntity);
                }
            }
        });
        if (isBluetoothConnection()) {
//            showLYControlWindowViewStub(false);
            ((SLLivePresenter) this.mPresenter).sendBluetoothConnectionNotice(new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.28
                @Override // com.slzhibo.library.http.ResultCallBack
                public void onError(int i, String str4) {
                }

                @Override // com.slzhibo.library.http.ResultCallBack
                public void onSuccess(Object obj) {
                    ChatEntity chatEntity = new ChatEntity();
                    chatEntity.setMsgText(SLLiveFragment.this.anchorItemEntity.nickname);
                    chatEntity.setTargetId(String.valueOf(1));
                    chatEntity.setMsgType(23);
//                    SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
                }
            });
//            GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//            if (giftBackpackDialog != null) {
//                giftBackpackDialog.setBluetoothConnection(isBluetoothConnection());
//            }
        }
    }

    public boolean isBluetoothConnection() {
        LiveItemEntity liveItemEntity = this.liveItemEntity;
        return liveItemEntity != null && liveItemEntity.isBluetoothDeviceStatus() && isLiving();
    }

    private void sendAdImageRequest() {
        ((SLLivePresenter) this.mPresenter).getAdImageList("2");
        ((SLLivePresenter) this.mPresenter).getAdImageList("7");
        ((SLLivePresenter) this.mPresenter).getAdImageList("3");
        ((SLLivePresenter) this.mPresenter).getLiveAdNoticeList();
    }

    public void showRecommendComponentsView() {
        this.cacheRecommendComponents = CacheUtils.getLocalCacheRecommendComponents(this.liveRecommendGameId);
        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
        if (componentsEntity == null) {
            this.ivRecommendComponents.setVisibility(View.GONE);
        } else if (componentsEntity.isCacheLotteryComponents()) {
            if (AppUtils.isEnableTurntable()) {
                this.ivRecommendComponents.setVisibility(View.VISIBLE);
                this.ivRecommendComponents.initCoverDrawableRes(R.drawable.fq_ic_lottery);
                return;
            }
            this.ivRecommendComponents.setVisibility(View.GONE);
        } else if (!TextUtils.isEmpty(this.cacheRecommendComponents.imgUrl)) {
            this.ivRecommendComponents.setVisibility(View.VISIBLE);
            this.ivRecommendComponents.initCoverImgUrl(this.cacheRecommendComponents.imgUrl);
        } else {
            this.ivRecommendComponents.setVisibility(View.GONE);
        }
    }

    private void initSeatData() {
        ((SLLivePresenter) this.mPresenter).sendSeatListRequest(1L, this.liveId, this.liveCount, new ResultCallBack<List<YYLinkApplyEntity>>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.29
            public void onSuccess(List<YYLinkApplyEntity> list) {
                SLLiveFragment.this.yyLinkSeatListView.setVisibility(View.VISIBLE);
                SLLiveFragment.this.yyLinkSeatListView.initAudienceSeatData(list);
                SLLiveFragment.this.mLivePusherInfoView.updateDanMuMarginTop(ConvertUtils.dp2px(240.0f));
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                SLLiveFragment.this.yyLinkSeatListView.setVisibility(View.INVISIBLE);
            }
        });
    }




    private void initWatermarkConfig() {
        WatermarkConfigEntity watermarkConfig = SysConfigInfoManager.getInstance().getWatermarkConfig();
        if (watermarkConfig != null) {
            StringBuilder sb = new StringBuilder();
            if (watermarkConfig.isEnableLiveRoom()) {
                sb.append(getString(R.string.fq_live_room_num, this.liveId));
            }
            if (watermarkConfig.isEnableDate()) {
                if (watermarkConfig.isEnableLiveRoom()) {
                    sb.append(" | ");
                }
                sb.append(DateUtils.getCurrentDateTime("yyyy.MM.dd"));
            }
            this.tvWatermarkRoom.setText(sb.toString());
            this.tvWatermarkTitle.setText(watermarkConfig.platform);
            this.tvWatermarkUrl.setText(watermarkConfig.downloadUrl);
            if (!TextUtils.isEmpty(watermarkConfig.logoUrl)) {
                GlideUtils.loadImage(this.mContext, this.ivWatermarkLogo, watermarkConfig.logoUrl, R.drawable.fq_ic_live_watermark);
            }
        }
    }

    public void setViewPagerScrollEnable(boolean z) {
        OnFragmentInteractionListener onFragmentInteractionListener = this.onFragmentInteractionListener;
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.setViewPagerScroll(z);
        }
    }


    public /* synthetic */ void lambda$goToLive$12$SLLiveFragment(View view) {
        this.mLiveGuideView.setVisibility(View.GONE);
        SysConfigInfoManager.getInstance().setEnableLiveGuide(false);
        setViewPagerScrollEnable(true);
        this.showGuideRating = true;
        if (SysConfigInfoManager.getInstance().isEnableRatingGuide() && this.isPayLive) {
            this.mLivePusherInfoView.showGuideRating(this.mActivity);
        }
    }


    private void goToEnd() {
//        if (isVideoRoomType()) {
//            disconnectVideoLink(false);
//        }
//        if (isVoiceRoomType()) {
//            voiceRoomViewDataRelease();
//        }
//        dismissDialogs();
//        stopPlay();
//        this.isLiveEnd = true;
//        this.liveStatus = false;
//        showLiveEndView();
    }

//    public void disconnectVideoLink(final boolean z) {
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$1EpsyZ3FCZA8cpEm9N9cXQW9rDE
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$disconnectVideoLink$76$SLLiveFragment(z);
//            }
//        });
//    }
//    public void handlerMainPost(Runnable runnable) {
//        synchronized (this.synchronizedObj) {
//            if (!(this.mainHandler == null || runnable == null)) {
//                this.mainHandler.post(runnable);
//            }
//        }
//    }



    private void initTaskDialog() {
        Toast.makeText(mActivity, "initTaskDialog:::LiveAdBannerBottomView.setTaskBoxVisibility(8):1040", Toast.LENGTH_SHORT).show();
//        this.mLiveAdBannerBottomView.setTaskBoxVisibility(8);
//        if (this.mTaskBottomDialog == null) {
//            this.mTaskBottomDialog = TaskBottomDialog.create(new TaskBottomDialog.TaskClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$chQRu992aDpK1bVXlvPoK6y-aaE
//                @Override // com.slzhibo.library.ui.view.dialog.TaskBottomDialog.TaskClickListener
//                public final void onTaskCallback(TaskBoxEntity taskBoxEntity) {
//                    SLLiveFragment.this.lambda$initTaskDialog$16$SLLiveFragment(taskBoxEntity);
//                }
//            });
//        }
        ((SLLivePresenter) this.mPresenter).getTaskList(false);
    }


    public void saveLiveUploadData() {
        LiveDataEntity liveDataEntity = new LiveDataEntity();
        liveDataEntity.liveId = this.liveId;
        AnchorEntity anchorEntity = this.anchorItemEntity;
        liveDataEntity.anchorId = anchorEntity.openId;
        liveDataEntity.appId = anchorEntity.appId;
        liveDataEntity.endTime = System.currentTimeMillis();
        liveDataEntity.startTime = this.livingStartTime;
        AnchorEntity anchorEntity2 = this.anchorItemEntity;
        liveDataEntity.expGrade = anchorEntity2.expGrade;
        liveDataEntity.nickname = anchorEntity2.nickname;
        liveDataEntity.tag = anchorEntity2.tag;
        liveDataEntity.viewerLevel = this.myUserInfoEntity.expGrade;
        DBUtils.saveOrUpdateLiveData(liveDataEntity);
    }



    public void payLiveTipsDialogOnRelease() {
//        PayLiveTipsDialog payLiveTipsDialog = this.payLiveTipsDialog;
//        if (payLiveTipsDialog != null) {
//            this.isPayLiveTipsDialog = false;
//            payLiveTipsDialog.compositeDisposableClear();
//            dismissDialogFragment(this.payLiveTipsDialog);
//            this.payLiveTipsDialog = null;
//        }
    }

    public void showPayLiveTips() {
        this.mLivePusherInfoView.setChargeTypeTips(isPayLiveTicket(), this.ticketPrice);
        if (SysConfigInfoManager.getInstance().isEnableRatingGuide() && isPayLiveTicket() && this.showGuideRating) {
            this.mLivePusherInfoView.showGuideRating(this.mActivity);
        }
    }

    @SuppressLint("WrongConstant")
    public void showContentView(int i, boolean z) {
        int i2 = 0;
        this.ivClosed.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        this.mRlControllerView.setVisibility(i == 256 ? View.VISIBLE : View.INVISIBLE);
        this.rlWatermarkShadowBg.setVisibility(i == 256 ? View.VISIBLE : View.INVISIBLE);
        LivePayEnterView livePayEnterView = this.livePayEnterView;
        if (livePayEnterView != null) {
            livePayEnterView.setVisibility(i == 259 ? View.VISIBLE : View.INVISIBLE);
        }
//        LiveEndInfoView liveEndInfoView = this.mLiveEndInfoView;
//        if (liveEndInfoView != null) {
//            if (i != 257) {
//                i2 = 4;
//            }
//            liveEndInfoView.setVisibility(i2);
//        }
    }


    public void switchStream() {
        if (!isLiving()) {
            return;
        }
        if (this.isRTCStream) {
            if (isVoiceRoomType()) {
                stopRTC();
//                initSeatData();
//                updateYYLinkIconView(null);
            }
//            showLiveLoadingView(4);
            return;
        }
        startPullTimeOut();
        if (isVideoRoomType()) {
            showLoadingAnim();
        }
        PlayManager playManager = this.playManager;
        if (playManager != null) {
            playManager.switchStream(this.pullStreamUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPullTimeOut() {
        cancelPullTimeOut();
        this.pullTimeOutTimer = Observable.timer(10L, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$PtGLHhIBiPRnSAvVmMTEPwpAJSQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                SLLiveFragment.this.lambda$startPullTimeOut$15$SLLiveFragment((Long) obj);
            }
        });
    }

    public /* synthetic */ void lambda$startPullTimeOut$15$SLLiveFragment(Long l) throws Exception {
        if (this.pullTimeOutTimer != null && this.playManager.getListener() != null) {
            this.playManager.getListener().onNetError();
        }
    }


    private void cancelPullTimeOut() {
        Disposable disposable = this.pullTimeOutTimer;
        if (disposable != null && !disposable.isDisposed()) {
            this.pullTimeOutTimer.dispose();
            this.pullTimeOutTimer = null;
        }
    }


    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadingAnim() {
        showLiveLoadingView(0);
//        this.mLiveLoadingView.showLoadingView();
    }

    public void showLiveLoadingView(int i) {
//        this.mLiveLoadingView.setVisibility(i);
        if (i == 4 || i == 8) {
//            hideLoadingAnim();
//            cancelPullTimeOut();
        }
    }

    public boolean isVideoRoomType() {
        return this.liveType == 1;
    }


    public boolean isLiving() {
        return this.liveStatus;
    }

    public boolean isVoiceRoomType() {
        return this.liveType == 2;
    }

    public void stopRTC() {
        if (this.isRTCStream) {
            this.isRTCStream = false;
//            RTCController rTCController = this.rtcController;
//            if (rTCController != null) {
//                rTCController.onRelease();
//                this.rootView.removeView(this.remoteRTCView);
//            }
//            PlayManager playManager = this.playManager;
//            if (playManager != null) {
//                playManager.startRTMPStream(this.pullStreamUrl);
//            }
        }
    }


    public void loadHdLotteryDrawInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, long j, String str10) {
        if (TextUtils.equals("1", str10)) {
//            this.mLiveAdBannerBottomView.addHdLotteryWindowView(false, str, j);
            UserEntity userEntity = this.myUserInfoEntity;
            int partHdLotteryCount = userEntity == null ? 0 : userEntity.getPartHdLotteryCount();
            AnchorEntity anchorEntity = this.anchorItemEntity;
//            this.hdLotteryDrawingDialog.initDrawInfo(str, str2, str3, str4, str5, str6, str7, str8, str9, j, anchorEntity != null ? anchorEntity.nickname : "", partHdLotteryCount);
            return;
        }
//        this.mLiveAdBannerBottomView.onLotteryEnd();
        if (TextUtils.equals("3", str10)) {
//            this.hdLotteryDrawingDialog.onCompleteLottery(str);
        } else {
//            this.hdLotteryDrawingDialog.onStartTimerLotteryEnd(str);
        }
    }

    private void startHideTitleTimer(String str) {
//        this.mLiveChatMsgView.setLiveTitle(str);
    }

    public void lambda$onBackThreadReceiveMessage$22$SLLiveFragment() {
        UserEntity userEntity;
        if (this.isAllBan && (userEntity = this.myUserInfoEntity) != null && AppUtils.isAudience(userEntity.getRole())) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog != null) {
//                inputTextMsgForAudienceDialog.setBanedAllPost();
//                showReceiveMsgOnChatList(new SocketMessageEvent.ResultData(), getString(R.string.fq_anchor_start_banned), 5);
//            }
        } else if (this.isSuperBan) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog2 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog2 != null) {
//                inputTextMsgForAudienceDialog2.setBandPostBySuperManager();
//            }
        } else if (this.isNormalBan) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog3 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog3 != null) {
//                inputTextMsgForAudienceDialog3.setBandOnePost(DateUtils.getClearTime(this.banPostTimeLeft));
//            }
        } else {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog4 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog4 != null) {
//                inputTextMsgForAudienceDialog4.cancelBandPost();
//            }
        }
    }

    @Override
    public void onLivePopularitySuccess(long j) {

    }

    @Override
    public void onPKLiveRoomFPSuccess(boolean z, boolean z2, PKRecordEntity pKRecordEntity) {

    }

    @Override
    public void onPersonalGuardInfoFail() {

    }

    @Override
    public void onPersonalGuardInfoSuccess(GuardItemEntity guardItemEntity) {

    }

    @Override
    public void onQMInteractShowTaskFail() {

    }

    @Override
    public void onQMInteractShowTaskSuccess(List<QMInteractTaskEntity> list) {

    }

    @Override
    public void onTaskChangeFail(TaskBoxEntity taskBoxEntity) {

    }

    @Override
    public void onTaskChangeSuccess(TaskBoxEntity taskBoxEntity) {

    }

    @Override
    public void onTaskListFail() {

    }

    @Override
    public void onTaskListSuccess(List<TaskBoxEntity> list, boolean z) {

    }

    @Override
    public void onTaskTakeFail() {

    }

    @Override
    public void onTaskTakeSuccess(TaskBoxEntity taskBoxEntity) {

    }

    @Override
    public void onTrumpetSendFail(int i) {

    }

    @Override
    public void onTrumpetSendSuccess() {

    }

    @Override
    public void onTrumpetStatusFail() {

    }

    @Override
    public void onTrumpetStatusSuccess(TrumpetStatusEntity trumpetStatusEntity) {

    }

    @Override
    public void onUseFragmentSuccess(PropConfigEntity propConfigEntity) {

    }

    @Override
    public void onUserCardInfoFail(int i, String str) {

    }

    @Override
    public void onUserCardInfoSuccess(UserEntity userEntity) {

    }

    @Override
    public void onUserCheckTicketFail(int i, String str) {

    }

    @Override
    public void onUserCheckTicketSuccess(CheckTicketEntity checkTicketEntity) {

    }

    @Override
    public void onUserOverFail() {

    }

    @Override
    public void onUserOverSuccess(MyAccountEntity myAccountEntity) {
        if (myAccountEntity == null) {
            this.getUserBalanceFail = true;
            return;
        }
        this.getUserBalanceFail = false;
        if (!TextUtils.isEmpty(myAccountEntity.getAccountBalance())) {
//            if (this.giftBottomDialog != null) {
//                this.myPriceBalance = NumberUtils.string2Double(myAccountEntity.getAccountBalance());
//                this.giftBottomDialog.setUserBalance(this.myPriceBalance);
//            }
//            if (this.mLotteryDialog != null) {
//                this.myPriceBalance = NumberUtils.string2Double(myAccountEntity.getAccountBalance());
//                this.mLotteryDialog.setUserBalance(this.myPriceBalance);
//            }
        }
//        if (!TextUtils.isEmpty(myAccountEntity.score) && this.giftBottomDialog != null) {
//            this.myScoreBalance = NumberUtils.string2Double(myAccountEntity.score);
//            this.giftBottomDialog.setUserScore(NumberUtils.string2Double(myAccountEntity.score));
//        }
    }

    @Override
    public void onWebSocketAddressFail() {

    }

    @Override
    public void onWebSocketAddressSuccess(WSAddressEntity wSAddressEntity) {

    }


}






































//package com.slzhibo.library.ui.activity.live;
//
//import android.animation.Animator;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
////import android.support.annotation.Nullable;
////import android.support.annotation.StringRes;
////import android.support.v4.content.ContextCompat;
//import android.text.TextUtils;
//import android.view.TextureView;
//import android.view.View;
//import android.view.ViewStub;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.StringRes;
//import androidx.core.content.ContextCompat;
//
//import com.blankj.utilcode.util.ConvertUtils;
//import com.blankj.utilcode.util.ImageUtils;
//import com.blankj.utilcode.util.NetworkUtils;
//import com.blankj.utilcode.util.PermissionUtils;
//import com.blankj.utilcode.util.SPUtils;
//import com.blankj.utilcode.util.ScreenUtils;
//import com.slzhibo.library.R;
////import com.slzhibo.library.R$drawable;
////import com.slzhibo.library.R$id;
////import com.slzhibo.library.R$layout;
////import com.slzhibo.library.R.string;
////import com.slzhibo.library.R$style;
//import com.slzhibo.library.SLLiveSDK;
//import com.slzhibo.library.base.BaseFragment;
//import com.slzhibo.library.download.GiftDownLoadManager;
//import com.slzhibo.library.http.ResultCallBack;
//import com.slzhibo.library.model.ActivityListEntity;
//import com.slzhibo.library.model.AnchorEntity;
//import com.slzhibo.library.model.BackpackItemEntity;
//import com.slzhibo.library.model.BannerEntity;
//import com.slzhibo.library.model.BaseGiftBackpackEntity;
//import com.slzhibo.library.model.BoomStatusEntity;
//import com.slzhibo.library.model.ChatEntity;
//import com.slzhibo.library.model.CheckTicketEntity;
//import com.slzhibo.library.model.ComponentsEntity;
//import com.slzhibo.library.model.GiftBatchItemEntity;
//import com.slzhibo.library.model.GiftDownloadItemEntity;
//import com.slzhibo.library.model.GiftIndexEntity;
//import com.slzhibo.library.model.GiftItemEntity;
//import com.slzhibo.library.model.GuardItemEntity;
//import com.slzhibo.library.model.HJProductEntity;
//import com.slzhibo.library.model.LYModelDataEntity;
//import com.slzhibo.library.model.LiveEndEntity;
//import com.slzhibo.library.model.LiveEntity;
//import com.slzhibo.library.model.LiveInitInfoEntity;
//import com.slzhibo.library.model.LiveItemEntity;
//import com.slzhibo.library.model.LotteryBoomDetailEntity;
//import com.slzhibo.library.model.MenuEntity;
//import com.slzhibo.library.model.MyAccountEntity;
//import com.slzhibo.library.model.OnLineUsersEntity;
//import com.slzhibo.library.model.PKRecordEntity;
//import com.slzhibo.library.model.PropConfigEntity;
//import com.slzhibo.library.model.QMInteractTaskEntity;
//import com.slzhibo.library.model.SocketMessageEvent;
//import com.slzhibo.library.model.TaskBoxEntity;
//import com.slzhibo.library.model.TrumpetStatusEntity;
//import com.slzhibo.library.model.UserEntity;
//import com.slzhibo.library.model.UserPrivateMessageEntity;
//import com.slzhibo.library.model.WSAddressEntity;
//import com.slzhibo.library.model.WatermarkConfigEntity;
//import com.slzhibo.library.model.YYLinkApplyEntity;
//import com.slzhibo.library.model.YYNoticeEntity;
//import com.slzhibo.library.model.YYRTCEntity;
//import com.slzhibo.library.model.db.ActivityDBEntity;
//import com.slzhibo.library.model.db.GiftBoxEntity;
//import com.slzhibo.library.model.db.LiveDataEntity;
//import com.slzhibo.library.model.db.MsgDetailListEntity;
//import com.slzhibo.library.model.db.MsgListEntity;
//import com.slzhibo.library.model.db.MsgStatusEntity;
//import com.slzhibo.library.model.db.WatchRecordEntity;
//import com.slzhibo.library.model.event.AttentionEvent;
//import com.slzhibo.library.model.event.BaseEvent;
//import com.slzhibo.library.model.event.ListDataUpdateEvent;
//import com.slzhibo.library.model.event.LiveTopAttentionEvent;
//import com.slzhibo.library.model.event.LoginEvent;
//import com.slzhibo.library.model.event.NobilityOpenEvent;
//import com.slzhibo.library.model.event.UpdateBalanceEvent;
//import com.slzhibo.library.service.KickDialogService;
//import com.slzhibo.library.service.TokenDialogService;
//import com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback;
//import com.slzhibo.library.ui.interfaces.OnLotteryBoomCallback;
//import com.slzhibo.library.ui.interfaces.OnPkViewListener;
//import com.slzhibo.library.ui.interfaces.RTCCallBack;
//import com.slzhibo.library.ui.interfaces.WebViewJSCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener;
//import com.slzhibo.library.ui.interfaces.impl.SimpleHdLotteryCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimpleLivePusherInfoCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimpleOnHJProductCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimpleOnLYDeviceCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimplePayLiveCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimpleQMInteractCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimpleSVGACallBack;
//import com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback;
//import com.slzhibo.library.ui.interfaces.impl.SimpleYYLinkCallback;
//import com.slzhibo.library.ui.presenter.SLLivePresenter;
//import com.slzhibo.library.ui.view.custom.ComponentsView;
//import com.slzhibo.library.ui.view.custom.GiftBoxView;
//import com.slzhibo.library.ui.view.custom.HdLotteryWindowView;
//import com.slzhibo.library.ui.view.custom.LYCommandView;
//import com.slzhibo.library.ui.view.custom.LYControlWindowView;
//import com.slzhibo.library.ui.view.custom.LiveAdBannerBottomView;
//import com.slzhibo.library.ui.view.custom.LiveAnimationView;
//import com.slzhibo.library.ui.view.custom.LiveChatMsgView;
//import com.slzhibo.library.ui.view.custom.LiveEndInfoView;
//import com.slzhibo.library.ui.view.custom.LiveLoadingView;
//import com.slzhibo.library.ui.view.custom.LivePayEnterView;
//import com.slzhibo.library.ui.view.custom.LivePusherInfoView;
//import com.slzhibo.library.ui.view.custom.PKInfoView;
//import com.slzhibo.library.ui.view.custom.TaskBoxView;
//import com.slzhibo.library.ui.view.custom.YYLikeSeatView;
//import com.slzhibo.library.ui.view.custom.YYLinkSeatListView;
//import com.slzhibo.library.ui.view.dialog.BottomDialogUtils;
//import com.slzhibo.library.ui.view.dialog.CommonRuleTipsDialog;
//import com.slzhibo.library.ui.view.dialog.ComponentsDialog;
//import com.slzhibo.library.ui.view.dialog.ComponentsWebViewDialog;
//import com.slzhibo.library.ui.view.dialog.GiftBackpackDialog;
//import com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog;
//import com.slzhibo.library.ui.view.dialog.GiftBoxPresenterDialog;
//import com.slzhibo.library.ui.view.dialog.GiftWallDialog;
//import com.slzhibo.library.ui.view.dialog.GuardListDialog;
//import com.slzhibo.library.ui.view.dialog.GuardOpenContentDialog;
//import com.slzhibo.library.ui.view.dialog.GuardOpenTipsDialog;
//import com.slzhibo.library.ui.view.dialog.HJProductRecommendDialog;
//import com.slzhibo.library.ui.view.dialog.HdLotteryDrawingDialog;
//import com.slzhibo.library.ui.view.dialog.InputTextMsgForAudienceDialog;
//import com.slzhibo.library.ui.view.dialog.LYControlWindowDialog;
//import com.slzhibo.library.ui.view.dialog.LYSendGiftTipsDialog;
//import com.slzhibo.library.ui.view.dialog.LiveActionBottomDialog;
//import com.slzhibo.library.ui.view.dialog.LiveEndEvaluationDialog;
//import com.slzhibo.library.ui.view.dialog.LiveMoreDialog;
//import com.slzhibo.library.ui.view.dialog.LoadingDialog;
//import com.slzhibo.library.ui.view.dialog.LotteryDialog;
//import com.slzhibo.library.ui.view.dialog.NobilityOpenTipsDialog;
//import com.slzhibo.library.ui.view.dialog.PKRankDialog;
//import com.slzhibo.library.ui.view.dialog.PayLiveTipsDialog;
//import com.slzhibo.library.ui.view.dialog.PrivateMsgDialog;
//import com.slzhibo.library.ui.view.dialog.QMInteractUserDialog;
//import com.slzhibo.library.ui.view.dialog.QMTaskListUserDialog;
//import com.slzhibo.library.ui.view.dialog.TaskBottomDialog;
//import com.slzhibo.library.ui.view.dialog.UserAchieveDialog;
//import com.slzhibo.library.ui.view.dialog.UserGuardAvatarDialog;
//import com.slzhibo.library.ui.view.dialog.UserNobilityAvatarDialog;
//import com.slzhibo.library.ui.view.dialog.UserNormalAvatarDialog;
//import com.slzhibo.library.ui.view.dialog.UserSuperAvatarDialog;
//import com.slzhibo.library.ui.view.dialog.WebViewDialog;
//import com.slzhibo.library.ui.view.dialog.WeekStarRankingDialog;
//import com.slzhibo.library.ui.view.dialog.YYLinkActionMenuDialog;
//import com.slzhibo.library.ui.view.dialog.YYLinkAnchorInviteDialog;
//import com.slzhibo.library.ui.view.dialog.YYLinkSendApplyDialog;
//import com.slzhibo.library.ui.view.dialog.YYNoticeManageDialog;
//import com.slzhibo.library.ui.view.dialog.alert.ChatTipDialog;
//import com.slzhibo.library.ui.view.dialog.alert.ComposeDialog;
//import com.slzhibo.library.ui.view.dialog.alert.ComposeSuccessDialog;
//import com.slzhibo.library.ui.view.dialog.alert.WarnDialog;
//import com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment;
//import com.slzhibo.library.ui.view.dialog.confirm.NetworkPromptDialog;
//import com.slzhibo.library.ui.view.dialog.confirm.PermissionDialog;
//import com.slzhibo.library.ui.view.dialog.confirm.SureCancelDialog;
//import com.slzhibo.library.ui.view.dialog.confirm.TransDialog;
//import com.slzhibo.library.ui.view.gift.GiftAnimModel;
//import com.slzhibo.library.ui.view.gift.GiftFrameLayout;
//import com.slzhibo.library.ui.view.iview.ISLLiveView;
//import com.slzhibo.library.ui.view.task.TaskBoxUtils;
//import com.slzhibo.library.ui.view.widget.QMNoticeAnimView;
//import com.slzhibo.library.ui.view.widget.QMNoticeWindow;
//import com.slzhibo.library.ui.view.widget.badgeView.QBadgeView;
//import com.slzhibo.library.ui.view.widget.bgabanner.TopBannerUtils;
//import com.slzhibo.library.utils.AnimUtils;
//import com.slzhibo.library.utils.AppUtils;
//import com.slzhibo.library.utils.CacheUtils;
//import com.slzhibo.library.utils.ConstantUtils;
//import com.slzhibo.library.utils.DBUtils;
//import com.slzhibo.library.utils.DateUtils;
//import com.slzhibo.library.utils.FileUtils;
//import com.slzhibo.library.utils.GlideUtils;
//import com.slzhibo.library.utils.HandlerUtils;
//import com.slzhibo.library.utils.LogEventUtils;
//import com.slzhibo.library.utils.MD5Utils;
//import com.slzhibo.library.utils.NetUtils;
//import com.slzhibo.library.utils.NumberUtils;
//import com.slzhibo.library.utils.ReSizeUtils;
//import com.slzhibo.library.utils.RxTimerUtils;
//import com.slzhibo.library.utils.RxViewUtils;
//import com.slzhibo.library.utils.SwipeAnimationController;
//import com.slzhibo.library.utils.SysConfigInfoManager;
//import com.slzhibo.library.utils.SystemUtils;
//import com.slzhibo.library.utils.TranslationUtils;
//import com.slzhibo.library.utils.UserInfoManager;
//import com.slzhibo.library.utils.immersionbar.ImmersionBar;
//import com.slzhibo.library.utils.live.PlayManager;
//import com.slzhibo.library.utils.live.RTCController;
//import com.slzhibo.library.utils.live.SimpleRxObserver;
//import com.slzhibo.library.utils.picker.ImageSet;
//import com.slzhibo.library.utils.rxlifecycle2.android.FragmentEvent;
//import com.slzhibo.library.websocket.interfaces.BackgroundSocketCallBack;
//import com.slzhibo.library.websocket.interfaces.OnWebSocketStatusListener;
//import com.slzhibo.library.websocket.nvwebsocket.MessageHelper;
//import com.slzhibo.library.websocket.nvwebsocket.WsManager;
//import com.slzhibo.library.websocket.nvwebsocket.WsStatus;
//import io.agora.rtc.RtcEngine;
//import io.agora.rtc.video.VideoCanvas;
//import io.reactivex.Flowable;
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.functions.Action;
//import io.reactivex.functions.Consumer;
//import io.reactivex.functions.Function;
//import io.reactivex.schedulers.Schedulers;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//import org.greenrobot.eventbus.EventBus;
//
///* loaded from: classes3.dex */
//public class SLLiveFragment extends BaseFragment<SLLivePresenter> implements ISLLiveView, BackgroundSocketCallBack, InputTextMsgForAudienceDialog.OnTextSendListener, GiftFrameLayout.BarrageEndAnimationListener, GuardOpenContentDialog.OnOpenGuardCallbackListener, PrivateMsgDialog.SendPrivateMsgListener {
//    private String anchorAppId;
//    private UserNormalAvatarDialog anchorAvatarDialog;
//    private String anchorId;
//    private AnchorEntity anchorItemEntity;
//    private UserNobilityAvatarDialog anchorNobilityAvatarDialog;
//    private String banPostTimeLeft;
//    private Bundle bundleArgs;
//    private ComponentsEntity cacheRecommendComponents;
//    private Disposable cdDisposable;
//    private String chatContent;
//    private ComponentsDialog componentsDialog;
//    private ComponentsWebViewDialog componentsWebViewDialog;
//    private SocketMessageEvent.ResultData curAnchorInfoNoticeEntity;
//    private String curBigAnimSendUserId;
//    private volatile boolean getUserBalanceFail;
//    private GiftBackpackDialog giftBottomDialog;
//    private View giftButton;
//    private GiftWallDialog giftWallDialog;
//    private GuardItemEntity guardItemEntity;
//    private GuardListDialog guardListDialog;
//    private GuardOpenContentDialog guardOpenContentDialog;
//    private HdLotteryDrawingDialog hdLotteryDrawingDialog;
//    private volatile boolean isAllBan;
//    private boolean isContinueCombo;
//    private volatile boolean isGetGiftListFail;
//    private boolean isGiftListUpdating;
//    private boolean isLastVoiceStatusOpen;
//    private volatile boolean isNormalBan;
//    private boolean isPausing;
//    private boolean isRTCStream;
//    private volatile boolean isSocketReConn;
//    private boolean isStartGetAnchorInfo;
//    private volatile boolean isSuperBan;
//    private boolean isTranOpen;
//    private ImageView ivClosed;
//    private ComponentsView ivComponentsView;
//    private ImageView ivMore;
//    private ComponentsView ivRecommendComponents;
//    private ImageView ivWatermarkLogo;
//    private ImageView ivYYLink;
//    private ImageView ivYYLinkBgCover;
//    private LiveEndEntity lastLiveEndEntity;
//    private String lastMsg;
//    private YYLinkActionMenuDialog linkActionMenuDialog;
//    private YYLinkAnchorInviteDialog linkAnchorInviteDialog;
//    private YYLinkSendApplyDialog linkSendApplyDialog;
//    private SureCancelDialog linkUserDisconnectLinkDialog;
//    private LiveAnimationView liveAnimationView;
//    private String liveCount;
//    private LiveEndEvaluationDialog liveEndEvaluationDialog;
//    private String liveId;
//    private LiveItemEntity liveItemEntity;
//    private LiveEntity liveListItemEntity;
//    private LiveMoreDialog liveMoreDialog;
//    private String liveRecommendGameId;
//    private LoadingDialog loadingDialog;
//    private String luckNoticeLiveId;
//    private ViewStub lyCommandWindowViewStub;
//    private LYControlWindowDialog lyControlWindowDialog;
//    private ViewStub lyControlWindowViewStub;
//    private CommonRuleTipsDialog lyPlayDescDialog;
//    private LYSendGiftTipsDialog lySendGiftTipsDialog;
//    private ImageView mAnchorCoverImg;
//    private GiftBoxView mGiftBoxView;
//    private InputTextMsgForAudienceDialog mInputTextMsgDialog;
//    private LiveAdBannerBottomView mLiveAdBannerBottomView;
//    private RelativeLayout mLiveBgView;
//    private LiveChatMsgView mLiveChatMsgView;
//    private ViewStub mLiveGuideViewVs;
//    private LiveLoadingView mLiveLoadingView;
//    private LivePusherInfoView mLivePusherInfoView;
//    private LotteryDialog mLotteryDialog;
//    private Disposable mPKTimerDisposable;
//    private RelativeLayout mRlControllerView;
//    private TaskBottomDialog mTaskBottomDialog;
//    private Disposable mThermometerDisposable;
//    private WeekStarRankingDialog mWeekStarRankingDialog;
//    private Handler mainHandler;
//    private int myNobilityType;
//    private UserEntity myUserInfoEntity;
//    private String myWeekStar;
//    private SocketMessageEvent myselfEnterMessageEvent;
//    private YYNoticeManageDialog noticeManageDialog;
//    private SureCancelDialog offlinePrivateMsgDialog;
//    private OnFragmentInteractionListener onFragmentInteractionListener;
//    private UserCardCallback onUserCardCallback;
//    private PayLiveTipsDialog payLiveTipsDialog;
//    private PKRankDialog pkRankDialog;
//    private PlayManager playManager;
//    private PrivateMsgDialog privateMsgDialog;
//    private HJProductRecommendDialog productRecommendDialog;
//    private String pullStreamUrl;
//    private Disposable pullTimeOutTimer;
//    private QBadgeView qBadgeView;
//    private QMInteractUserDialog qmInteractUserDialog;
//    private SureCancelDialog qmInviteSureDialog;
//    private QMNoticeAnimView qmNoticeAnimView;
//    private QMTaskListUserDialog qmTaskListUserDialog;
//    private volatile boolean reConnectCountOver;
//    private TextureView remoteRTCView;
//    private RelativeLayout rlWatermarkShadowBg;
//    private FrameLayout rootView;
//    private RTCController rtcController;
//    private String socketEncryptionKey;
//    private String socketUrl;
//    private boolean startGetGiftListInfo;
//    private SwipeAnimationController swipeAnimationController;
//    private SocketMessageEvent.ResultData tempSysNoticeResultData;
//    private View titleTopView;
//    private TransDialog transDialog;
//    private boolean trumpetStatus;
//    private TextView tvInput;
//    private TextView tvWatermarkRoom;
//    private TextView tvWatermarkTitle;
//    private TextView tvWatermarkUrl;
//    private String uploadDataEnterSource;
//    private UserAchieveDialog userAchieveDialog;
//    private UserNormalAvatarDialog userAvatarDialog;
//    private UserGuardAvatarDialog userGuardAvatarDialog;
//    private UserNobilityAvatarDialog userNobilityAvatarDialog;
//    private UserSuperAvatarDialog userSuperAvatarDialog;
//    private ViewStub vsLiveEndInfoView;
//    private ViewStub vsLivePayEnterView;
//    private ViewStub vsPKInfoView;
//    private WebViewDialog webViewDialog;
//    private Handler workHandler;
//    private WsManager wsManager;
//    private YYLikeSeatView yyLikeCountView;
//    private YYLinkSeatListView yyLinkSeatListView;
//    private LiveEndInfoView mLiveEndInfoView = null;
//    private View mLiveGuideView = null;
//    private LivePayEnterView livePayEnterView = null;
//    private PKInfoView mPKInfoView = null;
//    private LYControlWindowView lyControlWindowView = null;
//    private LYCommandView lyCommandView = null;
//    private volatile boolean isTaskSocket = false;
//    private volatile boolean isFirstLoadTask = true;
//    private volatile boolean isConnectingChatService = true;
//    private volatile boolean isSocketClose = true;
//    private volatile boolean isSocketError = true;
//    private int postIntervalTimes = 1;
//    private final AtomicInteger clickCount = new AtomicInteger(0);
//    private volatile long countDownTime = this.postIntervalTimes * 3;
//    private double myPriceBalance = 0.0d;
//    private double myScoreBalance = 0.0d;
//    private final AtomicLong onLineCount = new AtomicLong(0);
//    private String speakLevel = "1";
//    private boolean isLiveEnd = false;
//    private volatile boolean isFirstGetMyBalanceGift = true;
//    private volatile boolean isFirstGetMyBalanceLottery = true;
//    private boolean isEnablePK = false;
//    private boolean liveStatus = false;
//    private final long nobilityPlayPeriod = 6000;
//    private final long trumpetPlayPeriod = 5000;
//    private int nobilityTypeThresholdForHasPreventBanned = 7;
//    private volatile boolean asleep = true;
//    private volatile boolean isLotteryBoomStatus = false;
//    private volatile boolean isBanGroup = false;
//    private boolean isLotteryDialogFlag = false;
//    private boolean isAutoGiftDialogFromWeekStar = true;
//    private final Map<String, GiftIndexEntity> myGiftIndexMap = new HashMap(8);
//    private final Map<String, GiftIndexEntity> myPropIndexMap = new HashMap(8);
//    private final Map<String, Map<String, GiftIndexEntity>> receiveGiftMap = new HashMap(128);
//    private final Map<String, Map<String, GiftIndexEntity>> receivePropMap = new HashMap(128);
//    private ConcurrentLinkedQueue<ChatEntity> receiveMsgQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> enterMsgQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> guardEnterMsgQueue = new ConcurrentLinkedQueue<>();
//    private final List<String> pullStreamUrlList = new ArrayList(3);
//    private volatile List<String> shieldedList = new ArrayList();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> giftNoticeQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> anchorInfoNoticeQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> sysNoticeQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> luckNoticeQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> gameNoticeQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> privateMsgQueue = new ConcurrentLinkedQueue<>();
//    private ConcurrentLinkedQueue<SocketMessageEvent.ResultData> intimateTaskQueue = new ConcurrentLinkedQueue<>();
//    private final AtomicBoolean canShowGiftNotice = new AtomicBoolean(true);
//    private final AtomicBoolean canShowEnterMsg = new AtomicBoolean(true);
//    private final AtomicBoolean canShowSysNotice = new AtomicBoolean(true);
//    private final AtomicBoolean canShowGuardEnterMsg = new AtomicBoolean(true);
//    private final AtomicBoolean carFullAnimFinish = new AtomicBoolean(true);
//    private final AtomicBoolean canShowPrivateMsg = new AtomicBoolean(true);
//    private final AtomicBoolean canShowLuckNotice = new AtomicBoolean(true);
//    private final AtomicBoolean canShowAnchorInfoNotice = new AtomicBoolean(true);
//    private final AtomicBoolean canShowGameNotice = new AtomicBoolean(true);
//    private final AtomicBoolean canShowIntimateNotice = new AtomicBoolean(true);
//    private final AtomicInteger curTrumpetCount = new AtomicInteger(0);
//    private long livingStartTime = 0;
//    private long livingEndTime = 0;
//    private final AtomicLong speakTotalCount = new AtomicLong(0);
//    private String chargeType = "0";
//    private String ticketPrice = "0";
//    private String liveEnterWay = "2";
//    private volatile boolean isPayLive = false;
//    private volatile boolean isBuyTicket = false;
//    private volatile boolean isPayLiveTipsDialog = false;
//    private volatile boolean showGuideRating = true;
//    private volatile boolean isLoginRequest = false;
//    private String giftNoticeLiveId = "";
//    private final AtomicInteger linkMicPKType = new AtomicInteger(288);
//    private boolean isFirstInitPlayManager = false;
//    private int liveType = 1;
//    private final AtomicInteger voiceRoomLikeCount = new AtomicInteger(0);
//    private final AtomicBoolean isOperationActivityAdDialog = new AtomicBoolean(false);
//    private String curGameNoticeId = null;
//    private final Handler.Callback workCallBack = new Handler.Callback() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$QMhHaJqn53LIiZBaMqZaNCV5cdA
//        @Override // android.os.Handler.Callback
//        public final boolean handleMessage(Message message) {
//            return SLLiveFragment.this.lambda$new$61$SLLiveFragment(message);
//        }
//    };
//    Object synchronizedObj = new Object();
//
//    @Override
//    public void onResultError(int i) {
//
//    }
//
//    /* loaded from: classes3.dex */
//    public interface OnFragmentInteractionListener {
//        void setViewPagerScroll(boolean z);
//
//        void updateLiveRoomInfo();
//    }
//
//    @Override // com.slzhibo.library.ui.view.dialog.GuardOpenContentDialog.OnOpenGuardCallbackListener
//    public void OnOpenGuardFail() {
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onLiveAdListFail(String str) {
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onPersonalGuardInfoFail() {
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onQMInteractShowTaskFail() {
//    }
//
//    @Override // com.slzhibo.library.ui.view.gift.GiftFrameLayout.BarrageEndAnimationListener
//    public void onStartAnimation(GiftAnimModel giftAnimModel) {
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTaskChangeFail(TaskBoxEntity taskBoxEntity) {
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTaskTakeFail() {
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTrumpetStatusFail() {
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onUserCardInfoFail(int i, String str) {
//    }
//
//    public static SLLiveFragment newInstance(LiveEntity liveEntity, String str) {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("LIVE_ITEM", liveEntity);
//        bundle.putString(ConstantUtils.RESULT_FLAG, str);
//        SLLiveFragment sLLiveFragment = new SLLiveFragment();
//        sLLiveFragment.setArguments(bundle);
//        return sLLiveFragment;
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void onAttachToContext(Context context) {
//        super.onAttachToContext(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            this.onFragmentInteractionListener = (OnFragmentInteractionListener) context;
//        }
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void getBundle(Bundle bundle) {
//        super.getBundle(bundle);
//        this.liveListItemEntity = (LiveEntity) getArguments().getParcelable("LIVE_ITEM");
//        this.liveEnterWay = bundle.getString(ConstantUtils.RESULT_FLAG, "2");
//        this.uploadDataEnterSource = bundle.getString(ConstantUtils.RESULT_ENTER_SOURCE, getString(R.string.fq_hot_list));
//    }
//
//    /* JADX INFO: Access modifiers changed from: protected */
//    @Override // com.slzhibo.library.base.BaseFragment
//    public SLLivePresenter createPresenter() {
//        return new SLLivePresenter(this.mContext, this);
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public int getLayoutId() {
//        return R.layout.fq_fragment_sl_live;
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void initView(View view, @Nullable Bundle bundle) {
//        this.workHandler = HandlerUtils.getInstance().startIOThread(SLLiveFragment.class.getName(), this.workCallBack);
//        this.mainHandler = new MainHandler(this, Looper.getMainLooper());
//        this.playManager = new PlayManager(this.mContext);
//        this.shieldedList = DBUtils.getShieldList();
//        ((SLLivePresenter) this.mPresenter).initLocalComponentsCache();
//        CacheUtils.updateCacheVersion();
//        initControlView(view);
//        initGiftDownloadData();
//        if (this.liveListItemEntity != null) {
//            this.isFirstInitPlayManager = true;
//            initSendLiveInitInfoRequest();
//        }
//        SPUtils.getInstance().put(ConstantUtils.MUTE_KEY, false);
//    }
//
//    public void resetLiveRoom(LiveEntity liveEntity, String str) {
//        this.uploadDataEnterSource = getString(R.string.fq_hot_list);
//        releasePlay();
//        onReleaseViewData();
//        initGiftDownloadData();
//        this.workHandler = HandlerUtils.getInstance().startIOThread(SLLiveFragment.class.getName(), this.workCallBack);
//        this.mainHandler = new MainHandler(this, Looper.getMainLooper());
//        this.liveEnterWay = str;
//        this.liveListItemEntity = liveEntity;
//        if (this.liveListItemEntity != null) {
//            this.isFirstInitPlayManager = false;
//            initSendLiveInitInfoRequest();
//        }
//        initListener(this.mFragmentRootView);
//    }
//
//    private void initSendLiveInitInfoRequest() {
//        SPUtils.getInstance().put(ConstantUtils.IS_CLOSE_QM_WINDOW, false);
//        LiveEntity liveEntity = this.liveListItemEntity;
//        this.liveId = liveEntity.liveId;
//        this.liveCount = liveEntity.liveCount;
//        this.liveStatus = liveEntity.isOnLiving();
//        this.anchorItemEntity = getAnchorEntityInfo(this.liveListItemEntity);
//        setAnchorCoverImg();
//        if (this.isFirstInitPlayManager) {
//            updatePullStreamUrl();
//            this.playManager.initRoomPlayManager(this.rootView, this.pullStreamUrl);
//        }
//        if (TextUtils.equals("2", this.liveEnterWay)) {
//            ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, true);
//        } else if (this.liveListItemEntity.isTimePayLive()) {
//            showToast(R.string.fq_pay_time_live_toast);
//            onFinishActivity();
//        } else if (this.liveListItemEntity.isRelationBoolean()) {
//            ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, false);
//        } else if (!isPayLiveNeedBuyTicket()) {
//            if (this.liveListItemEntity.isPayLiveTicket()) {
//                this.isPayLive = isPayLiveTicket();
//                this.isBuyTicket = false;
//                this.chargeType = this.liveListItemEntity.chargeType;
//            }
//            sendLiveInitInfoRequest();
//        } else if (DBUtils.isPayLiveValidState(this.liveId, this.liveCount)) {
//            sendLiveInitInfoRequest(false, true, false);
//        } else {
//            ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, false);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void sendLiveInitInfoRequest() {
//        sendLiveInitInfoRequest(false, this.isPayLive, this.isBuyTicket);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void sendLiveInitInfoRequest(boolean z, boolean z2, boolean z3) {
//        this.isPayLive = z2;
//        this.isBuyTicket = z3;
//        ((SLLivePresenter) this.mPresenter).getLiveInitInfo(this.liveId, this.liveEnterWay, z, z2, z3, this.isLoginRequest);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onLiveInitInfoSuccess(String str, String str2, LiveInitInfoEntity liveInitInfoEntity, boolean z, boolean z2) {
//        CharSequence charSequence;
//        if (liveInitInfoEntity != null && TextUtils.equals(this.liveId, str)) {
//            this.socketEncryptionKey = liveInitInfoEntity.k;
//            this.liveItemEntity = liveInitInfoEntity.liveDto;
//            this.myUserInfoEntity = liveInitInfoEntity.myUserDto;
//            this.myUserInfoEntity.setUserId(UserInfoManager.getInstance().getUserId());
//            if (TextUtils.equals(str2, "2")) {
//                AnchorEntity anchorEntity = liveInitInfoEntity.anchorDto;
//                AnchorEntity anchorEntity2 = this.anchorItemEntity;
//                anchorEntity2.userId = anchorEntity.userId;
//                anchorEntity2.appId = anchorEntity.appId;
//                anchorEntity2.openId = anchorEntity.openId;
//                anchorEntity2.tag = anchorEntity.tag;
//                anchorEntity2.avatar = anchorEntity.avatar;
//                anchorEntity2.sex = anchorEntity.sex;
//                anchorEntity2.nickname = anchorEntity.nickname;
//                anchorEntity2.expGrade = anchorEntity.expGrade;
//                this.anchorId = anchorEntity2.userId;
//                this.anchorAppId = anchorEntity2.appId;
//            }
//            this.liveStatus = liveInitInfoEntity.isLiving();
//            LiveItemEntity liveItemEntity = this.liveItemEntity;
//            this.liveCount = liveItemEntity.liveCount;
//            AnchorEntity anchorEntity3 = this.anchorItemEntity;
//            anchorEntity3.liveCount = this.liveCount;
//            anchorEntity3.topic = liveItemEntity.topic;
//            this.ticketPrice = liveItemEntity.ticketPrice;
//            this.liveType = NumberUtils.string2int(liveItemEntity.liveType);
//            LiveItemEntity liveItemEntity2 = this.liveItemEntity;
//            this.liveRecommendGameId = liveItemEntity2.gameId;
//            this.speakLevel = liveItemEntity2.speakLevel;
//            this.isBanGroup = UserInfoManager.getInstance().isInBanGroup();
//            if (TextUtils.equals(this.myUserInfoEntity.getUserId(), this.anchorItemEntity.userId)) {
//                this.myUserInfoEntity.setRole("1");
//            }
//            this.isAllBan = this.liveItemEntity.isBanAll();
//            this.isSuperBan = this.myUserInfoEntity.isSuperBanPost();
//            lambda$onBackThreadReceiveMessage$22$SLLiveFragment();
//            this.nobilityTypeThresholdForHasPreventBanned = AppUtils.getNobilityTypeThresholdForHasPreventBanned();
//            this.guardItemEntity = formatAnchorGuardInfo(liveInitInfoEntity.guardDto);
//            this.myselfEnterMessageEvent = liveInitInfoEntity.formatMyselfEnterMessageEvent();
//            int string2int = NumberUtils.string2int(this.liveItemEntity.postIntervalTimes);
//            if (string2int >= 0) {
//                this.postIntervalTimes = string2int;
//            }
//            startHideTitleTimer(this.liveItemEntity.topic);
//            this.onLineCount.set(NumberUtils.string2long(this.liveItemEntity.onlineUserCount));
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog != null) {
//                inputTextMsgForAudienceDialog.setMyGuardType(NumberUtils.string2int(this.guardItemEntity.userGuardType));
//                this.mInputTextMsgDialog.setSpeakWordLimit(AppUtils.getGradeSet10CharacterLimit());
//                this.mInputTextMsgDialog.setMyRole(this.myUserInfoEntity.getRole());
//                this.mInputTextMsgDialog.setMyUserGrade(this.myUserInfoEntity.getExpGrade());
//            }
//            if (AppUtils.isNobilityUser()) {
//                ((SLLivePresenter) this.mPresenter).getTrumpetStatus();
//            }
//            if (this.liveItemEntity.isEnableHdLottery()) {
//                LiveItemEntity liveItemEntity3 = this.liveItemEntity;
//                charSequence = "2";
//                loadHdLotteryDrawInfo(liveItemEntity3.liveDrawRecordId, liveItemEntity3.prizeName, liveItemEntity3.prizeNum, liveItemEntity3.liveDrawScope, liveItemEntity3.giftMarkId, liveItemEntity3.joinNum, liveItemEntity3.giftName, liveItemEntity3.giftPrice, liveItemEntity3.giftImg, NumberUtils.string2long(liveItemEntity3.liveDrawTimeRemain), this.liveItemEntity.drawStatus);
//            } else {
//                charSequence = "2";
//            }
//            if (!this.isFirstInitPlayManager || TextUtils.equals(charSequence, this.liveEnterWay) || z2) {
//                this.pullStreamUrl = this.liveItemEntity.getDefPullStreamUrlStr();
//                switchStream();
//            }
//            this.liveListItemEntity.pullStreamUrl = this.liveItemEntity.getDefPullStreamUrlStr();
//            updatePullStreamUrl();
//            showContentView(256, true);
//            if (isPayLiveNeedBuyTicket() && !DBUtils.isPayLiveValidState(str, this.liveCount)) {
//                long string2long = NumberUtils.string2long(this.liveItemEntity.anchorContribution, -1L);
//                if (string2long != -1) {
//                    long longValue = string2long + new Double(AppUtils.getFormatVirtualGold(this.ticketPrice)).longValue();
//                    this.liveItemEntity.anchorContribution = String.valueOf(longValue);
//                }
//            }
//            this.mLivePusherInfoView.initData(this.liveItemEntity, this.anchorItemEntity, this.guardItemEntity);
//            if (z && isLiving()) {
//                showPayLiveTips();
//                payLiveTipsDialogOnRelease();
//                LivePayEnterView livePayEnterView = this.livePayEnterView;
//                if (livePayEnterView != null) {
//                    livePayEnterView.onRelease();
//                }
//                if (this.isBuyTicket) {
//                    DBUtils.savePayLiveInfo(str, this.liveCount, String.valueOf(System.currentTimeMillis()));
//                }
//                showToast(R.string.fq_pay_live_ticket_verification_toast);
//            }
//            if (z2) {
//                stopSocket();
//                this.socketUrl = AppUtils.formatLiveSocketUrl(this.liveItemEntity.wsAddress, str, this.liveCount, this.myUserInfoEntity.getUserId(), "2", this.socketEncryptionKey);
//                initSocket();
//            } else if (!this.isSocketReConn) {
//                initRoomInfo(liveInitInfoEntity);
//            }
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onLiveInitInfoFail(int i, String str) {
//        LivePayEnterView livePayEnterView;
//        if (!AppUtils.isTokenInvalidErrorCode(i)) {
//            if (AppUtils.isNoEnterLivePermissionErrorCode(i)) {
//                onNoEnterLivePermission(str);
//            } else if (i == 200164 || i == 300004 || i == 200157 || i == 200171 || i == 200169 || i == 200165) {
//                onFinishActivity();
//            } else if (AppUtils.isKickOutErrorCode(i)) {
//                startKickDialogService();
//                onFinishActivity();
//            } else if (i == 300006) {
//                if (isPayLiveNeedBuyTicket() && (livePayEnterView = this.livePayEnterView) != null) {
//                    livePayEnterView.onReset();
//                }
//                AppUtils.onRechargeListener(this.mContext);
//            } else if (i == 200166) {
//                this.liveCount = "";
//                ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, true);
//            } else {
//                this.isLiveEnd = true;
//                showContentView(258, true);
//                stopPlay();
//                showToast(R.string.fq_live_room_loading_fail_tips);
//                showRoomInfoReload();
//            }
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onUserCheckTicketSuccess(CheckTicketEntity checkTicketEntity) {
//        if (checkTicketEntity == null) {
//            showToast(R.string.fq_room_info_fail);
//            onFinishActivity();
//            return;
//        }
//        this.chargeType = checkTicketEntity.chargeType;
//        this.ticketPrice = checkTicketEntity.getPayLivePrice();
//        LiveEntity liveEntity = this.liveListItemEntity;
//        liveEntity.chargeType = this.chargeType;
//        liveEntity.ticketPrice = this.ticketPrice;
//        liveEntity.isPrivateAnchor = checkTicketEntity.isPrivateAnchor;
//        liveEntity.privateAnchorPrice = checkTicketEntity.privateAnchorPrice;
//        liveEntity.appId = checkTicketEntity.anchorAppId;
//        liveEntity.pullStreamUrl = checkTicketEntity.pullStreamUrl;
//        liveEntity.liveStatus = checkTicketEntity.liveStatus;
//        liveEntity.avatar = checkTicketEntity.avatar;
//        liveEntity.liveCoverUrl = checkTicketEntity.getLiveCoverUrl();
//        if (TextUtils.equals("2", this.liveEnterWay)) {
//            setAnchorCoverImg();
//            updatePullStreamUrl();
//        }
//        if (!checkTicketEntity.isNeedBuyTicket()) {
//            if (this.liveListItemEntity.isPayLiveTicket()) {
//                this.isPayLive = isPayLiveTicket();
//                this.isBuyTicket = false;
//                this.chargeType = this.liveListItemEntity.chargeType;
//            }
//            sendLiveInitInfoRequest();
//        } else if (this.liveListItemEntity.isTimePayLive()) {
//            showToast(R.string.fq_pay_time_live_toast);
//            onFinishActivity();
//        } else if (DBUtils.isPayLiveValidState(this.liveId, this.liveCount)) {
//            sendLiveInitInfoRequest(false, true, false);
//        } else {
//            LivePayEnterView livePayEnterView = this.livePayEnterView;
//            if (livePayEnterView != null) {
//                livePayEnterView.onResume();
//            } else {
//                showLivePayEnterView(this.chargeType, this.ticketPrice, checkTicketEntity.startTotalTime, checkTicketEntity.historyLiveEvaluation, checkTicketEntity.payLiveCount, checkTicketEntity.predictTicketEnd);
//            }
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onUserCheckTicketFail(int i, String str) {
//        if (!AppUtils.isTokenInvalidErrorCode(i)) {
//            if (AppUtils.isNoEnterLivePermissionErrorCode(i)) {
//                onNoEnterLivePermission(str);
//            } else if (AppUtils.isKickOutErrorCode(i)) {
//                startKickDialogService();
//                onFinishActivity();
//            } else {
//                onFinishActivity();
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* renamed from: initBan */
//    public void lambda$onBackThreadReceiveMessage$22$SLLiveFragment() {
//        UserEntity userEntity;
//        if (this.isAllBan && (userEntity = this.myUserInfoEntity) != null && AppUtils.isAudience(userEntity.getRole())) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog != null) {
//                inputTextMsgForAudienceDialog.setBanedAllPost();
//                showReceiveMsgOnChatList(new SocketMessageEvent.ResultData(), getString(R.string.fq_anchor_start_banned), 5);
//            }
//        } else if (this.isSuperBan) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog2 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog2 != null) {
//                inputTextMsgForAudienceDialog2.setBandPostBySuperManager();
//            }
//        } else if (this.isNormalBan) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog3 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog3 != null) {
//                inputTextMsgForAudienceDialog3.setBandOnePost(DateUtils.getClearTime(this.banPostTimeLeft));
//            }
//        } else {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog4 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog4 != null) {
//                inputTextMsgForAudienceDialog4.cancelBandPost();
//            }
//        }
//    }
//
//    private void initRoomInfo(LiveInitInfoEntity liveInitInfoEntity) {
//        loadOperationActivityAdDialog();
//        if (isLiving() || this.isSocketReConn) {
//            this.livingStartTime = System.currentTimeMillis();
//            LogEventUtils.startLiveDataTimerTask("LeaveRoom", new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.1
//                @Override // java.lang.Runnable
//                public void run() {
//                    SLLiveFragment.this.saveLiveUploadData();
//                }
//            }, 60000L, 10000L);
//            if (isLiving() && !TextUtils.equals(this.myUserInfoEntity.getUserId(), this.anchorItemEntity.userId) && this.isFirstLoadTask) {
//                this.isFirstLoadTask = false;
//                initTaskDialog();
//            }
//            goToLive();
//        } else {
//            LiveEndEntity liveEndEntity = liveInitInfoEntity.lastLiveData;
//            if (liveEndEntity == null) {
//                liveEndEntity = new LiveEndEntity();
//            }
//            this.lastLiveEndEntity = liveEndEntity;
//            LiveEndEntity liveEndEntity2 = this.lastLiveEndEntity;
//            AnchorEntity anchorEntity = this.anchorItemEntity;
//            liveEndEntity2.liveId = anchorEntity.liveId;
//            liveEndEntity2.userId = anchorEntity.userId;
//            liveEndEntity2.avatar = anchorEntity.avatar;
//            liveEndEntity2.sex = anchorEntity.sex;
//            liveEndEntity2.nickname = anchorEntity.nickname;
//            liveEndEntity2.expGrade = anchorEntity.expGrade;
//            goToEnd();
//        }
//        LogEventUtils.uploadInRoom(this.anchorItemEntity, this.liveId, this.myUserInfoEntity.expGrade, this.uploadDataEnterSource);
//        WatchRecordEntity watchRecordEntity = new WatchRecordEntity();
//        watchRecordEntity.userId = this.myUserInfoEntity.getUserId();
//        AnchorEntity anchorEntity2 = this.anchorItemEntity;
//        watchRecordEntity.liveId = anchorEntity2.liveId;
//        watchRecordEntity.coverUrl = anchorEntity2.liveCoverUrl;
//        watchRecordEntity.label = anchorEntity2.tag;
//        watchRecordEntity.title = this.liveItemEntity.topic;
//        watchRecordEntity.anchorNickname = anchorEntity2.nickname;
//        watchRecordEntity.liveTime = System.currentTimeMillis();
//        DBUtils.saveOrUpdateWatchRecord(watchRecordEntity);
//    }
//
//    @SuppressLint("WrongConstant")
//    private void initControlView(View view) {
//        int i = 0;
//        try {
//            this.mImmersionBar = ImmersionBar.with(this.mActivity);
//            this.mImmersionBar.transparentStatusBar().statusBarView(view.findViewById(R.id.title_top_view)).statusBarDarkFont(false).init();
//        } catch (Exception unused) {
//        }
//        this.rootView = (FrameLayout) view.findViewById(R.id.rl_play_root);
//        this.mLiveBgView = (RelativeLayout) view.findViewById(R.id.rl_live_bg);
//        this.ivMore = (ImageView) view.findViewById(R.id.iv_private_message);
//        this.tvInput = (TextView) view.findViewById(R.id.iv_input);
//        this.mLivePusherInfoView = (LivePusherInfoView) view.findViewById(R.id.ll_pusher_info);
//        this.mRlControllerView = (RelativeLayout) view.findViewById(R.id.rl_control_layout);
//        this.mAnchorCoverImg = (ImageView) view.findViewById(R.id.iv_anchor_cover);
//        this.mLiveLoadingView = (LiveLoadingView) view.findViewById(R.id.live_loading_view);
//        this.vsLiveEndInfoView = (ViewStub) view.findViewById(R.id.live_end_view);
//        this.liveAnimationView = (LiveAnimationView) view.findViewById(R.id.live_anim_view);
//        this.mLiveChatMsgView = (LiveChatMsgView) view.findViewById(R.id.live_chat_msg_view);
//        this.giftButton = view.findViewById(R.id.iv_gift);
//        this.ivClosed = (ImageView) view.findViewById(R.id.iv_closed);
//        this.mLiveGuideViewVs = (ViewStub) view.findViewById(R.id.live_guide_view);
//        this.ivComponentsView = (ComponentsView) view.findViewById(R.id.iv_components);
//        this.mLiveAdBannerBottomView = (LiveAdBannerBottomView) view.findViewById(R.id.live_bottom_banner_view);
//        this.ivRecommendComponents = (ComponentsView) view.findViewById(R.id.iv_recommend_components);
//        this.mGiftBoxView = (GiftBoxView) this.mLivePusherInfoView.findViewById(R.id.gift_box_view);
//        this.vsLivePayEnterView = (ViewStub) view.findViewById(R.id.pay_enter_view);
//        this.vsPKInfoView = (ViewStub) view.findViewById(R.id.fq_pk_info_view);
//        this.titleTopView = view.findViewById(R.id.title_top_view);
//        this.rlWatermarkShadowBg = (RelativeLayout) view.findViewById(R.id.rl_watermark_shadow_bg);
//        this.tvWatermarkTitle = (TextView) view.findViewById(R.id.tv_watermark_title);
//        this.tvWatermarkRoom = (TextView) view.findViewById(R.id.tv_watermark_room);
//        this.tvWatermarkUrl = (TextView) view.findViewById(R.id.tv_watermark_url);
//        this.ivWatermarkLogo = (ImageView) view.findViewById(R.id.iv_watermark_logo);
//        this.ivYYLink = (ImageView) view.findViewById(R.id.iv_yy_link);
//        this.ivYYLinkBgCover = (ImageView) view.findViewById(R.id.iv_link_cover);
//        this.yyLinkSeatListView = (YYLinkSeatListView) this.mLivePusherInfoView.findViewById(R.id.seat_list_view);
//        this.lyControlWindowViewStub = (ViewStub) view.findViewById(R.id.vs_ly_control_view);
//        this.lyCommandWindowViewStub = (ViewStub) view.findViewById(R.id.vs_ly_command_view);
//        this.swipeAnimationController = new SwipeAnimationController(this.mContext, this.mRlControllerView);
//        this.loadingDialog = new LoadingDialog(this.mContext);
//        ComponentsView componentsView = this.ivComponentsView;
//        if (!AppUtils.isEnableComponents()) {
//            i = 8;
//        }
//        componentsView.setVisibility(i);
//        this.ivComponentsView.initCoverDrawableRes(R.drawable.fq_ic_live_game);
//        if (AppUtils.isEnablePrivateMsg() || AppUtils.isEnableQMInteract()) {
//            this.qBadgeView = new QBadgeView(this.mContext);
//            this.qBadgeView.bindTarget(this.ivMore).setBadgeTextColor(-1).setBadgePadding(1.0f, true).isNoNumber(true).setBadgeGravity(8388661).setBadgeBackgroundColor(ContextCompat.getColor(this.mContext, R.color.fq_colorRed)).stroke(-1, 1.0f, true);
//            updateMoreRedDot();
//        }
//        this.mInputTextMsgDialog = new InputTextMsgForAudienceDialog(this.mActivity, this);
//        this.transDialog = TransDialog.newInstance(new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$H60N9vm4bG339vCWBouXzmoaFhE
//            @Override // android.view.View.OnClickListener
//            public final void onClick(View view2) {
//                SLLiveFragment.this.lambda$initControlView$0$SLLiveFragment(view2);
//            }
//        });
//        this.componentsWebViewDialog = new ComponentsWebViewDialog(this.mContext, new WebViewJSCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.2
//            @Override // com.slzhibo.library.ui.interfaces.WebViewJSCallback
//            public void onLiveBalanceUpdate() {
//                ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getUserOver();
//            }
//        });
//        this.hdLotteryDrawingDialog = new HdLotteryDrawingDialog(this.mContext, R.style.fq_GeneralDialogStyle, new SimpleHdLotteryCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.3
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleHdLotteryCallback, com.slzhibo.library.ui.interfaces.OnHdLotteryCallback
//            public void onJoinLotteryListener(final GiftDownloadItemEntity giftDownloadItemEntity, final String str) {
//                super.onJoinLotteryListener(giftDownloadItemEntity, str);
//                if (!SLLiveFragment.this.isConsumptionPermissionUserToLogin() || !SLLiveFragment.this.isCanSendGift()) {
//                    return;
//                }
//                if (SLLiveFragment.this.isFirstGetMyBalanceGift) {
//                    SLLiveFragment.this.isFirstGetMyBalanceGift = false;
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getUserOver(true, new ResultCallBack<MyAccountEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.3.1
//                        public void onSuccess(MyAccountEntity myAccountEntity) {
//                            SLLiveFragment.this.sendHdLotteryGift(giftDownloadItemEntity, str);
//                        }
//
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onError(int i2, String str2) {
//                            SLLiveFragment.this.showToast(R.string.fq_userover_loading_fail);
//                        }
//                    });
//                    return;
//                }
//                SLLiveFragment.this.sendHdLotteryGift(giftDownloadItemEntity, str);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleHdLotteryCallback, com.slzhibo.library.ui.interfaces.OnHdLotteryCallback
//            public void onFloatingWindowCloseListener() {
//                super.onFloatingWindowCloseListener();
//                SLLiveFragment.this.mLiveAdBannerBottomView.onReleaseHdLotteryWindowView();
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$initControlView$0$SLLiveFragment(View view) {
//        this.isTranOpen = true;
//    }
//
//    /* JADX INFO: Access modifiers changed from: package-private */
//    /* renamed from: com.slzhibo.library.ui.activity.live.SLLiveFragment$4  reason: invalid class name */
//    /* loaded from: classes3.dex */
//    public class AnonymousClass4 implements PlayManager.OnPlayListener {
//        AnonymousClass4() {
//        }
//
//        @Override // com.slzhibo.library.utils.live.PlayManager.OnPlayListener
//        public void onStartBuffering() {
//            if (SLLiveFragment.this.isVideoRoomType()) {
//                SLLiveFragment.this.showLoadingAnim();
//            }
//            SLLiveFragment.this.startPullTimeOut();
//        }
//
//        @Override // com.slzhibo.library.utils.live.PlayManager.OnPlayListener
//        public void onEndBuffering() {
//            SLLiveFragment.this.showLiveLoadingView(4);
//        }
//
//        @Override // com.slzhibo.library.utils.live.PlayManager.OnPlayListener
//        public void onPlaySuccess() {
//            SLLiveFragment.this.showLiveLoadingView(4);
//            AnimUtils.playHideAnimation(SLLiveFragment.this.mAnchorCoverImg);
//        }
//
//        @Override // com.slzhibo.library.utils.live.PlayManager.OnPlayListener
//        public void onNetError() {
//            SLLiveFragment.this.dealPlayError();
//        }
//
//        @Override // com.slzhibo.library.utils.live.PlayManager.OnPlayListener
//        public void onScreenshot(final Bitmap bitmap) {
//            Observable.just(AppUtils.getViewBitmap(( SLLiveFragment.this).mActivity, SLLiveFragment.this.mLiveBgView)).flatMap(new Function() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$4$Rk99oG2Fthwh2QjNQFEyumkCxzo
//                @Override // io.reactivex.functions.Function
//                public final Object apply(Object obj) throws Exception {
//                    return SLLiveFragment.AnonymousClass4.lambda$onScreenshot$0(bitmap, (Bitmap) obj);
//                }
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(SLLiveFragment.this.bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new SimpleRxObserver<Bitmap>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.4.1
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
//                public void onSubscribe(Disposable disposable) {
//                    super.onSubscribe(disposable);
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).compositeDisposableAdd(disposable);
//                    if (SLLiveFragment.this.loadingDialog != null) {
//                        SLLiveFragment.this.loadingDialog.show();
//                    }
//                }
//
//                public void accept(Bitmap bitmap2) {
//                    ImageUtils.save(bitmap2, AppUtils.getScreenshotPath(), Bitmap.CompressFormat.PNG, true);
//                }
//
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
//                public void onComplete() {
//                    super.onComplete();
//                    if (SLLiveFragment.this.loadingDialog != null && SLLiveFragment.this.loadingDialog.isShowing()) {
//                        SLLiveFragment.this.loadingDialog.dismiss();
//                    }
//                    Intent intent = new Intent(( SLLiveFragment.this).mContext, ReportLiveActivity.class);
//                    intent.putExtra(ConstantUtils.RESULT_ITEM, SLLiveFragment.this.anchorItemEntity);
//                    SLLiveFragment.this.startActivity(intent);
//                }
//
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
//                public void onError(Throwable th) {
//                    super.onError(th);
//                    if (SLLiveFragment.this.loadingDialog != null && SLLiveFragment.this.loadingDialog.isShowing()) {
//                        SLLiveFragment.this.loadingDialog.dismiss();
//                    }
//                }
//            });
//        }
//
//        /* JADX INFO: Access modifiers changed from: package-private */
//        public static /* synthetic */ ObservableSource lambda$onScreenshot$0(Bitmap bitmap, Bitmap bitmap2) throws Exception {
//            if (bitmap == null) {
//                return Observable.just(bitmap2);
//            }
//            ImageUtils.save(bitmap2, AppUtils.getScreenshotPath(), Bitmap.CompressFormat.PNG, true);
//            return Observable.just(AppUtils.toConformBitmap(bitmap, ImageUtils.getBitmap(AppUtils.getScreenshotPath())));
//        }
//
//        @Override // com.slzhibo.library.utils.live.PlayManager.OnPlayListener
//        public void resetPlay() {
//            SLLiveFragment.this.switchStream();
//        }
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void initListener(View view) {
//        this.playManager.setOnPlayListener(new AnonymousClass4());
//        this.mLivePusherInfoView.setRootView(this.rootView, this.swipeAnimationController);
//        this.mLivePusherInfoView.setGiftAnimListener(new SimpleAnimatorListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.5
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationStart(Animator animator) {
//                SLLiveFragment.this.canShowGiftNotice.set(false);
//            }
//
//            @Override // android.animation.Animator.AnimatorListener
//            public void onAnimationEnd(Animator animator) {
//                SLLiveFragment.this.canShowGiftNotice.set(true);
//                if (SLLiveFragment.this.giftNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.giftNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideGiftNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(10004);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationCancel(Animator animator) {
//                SLLiveFragment.this.canShowGiftNotice.set(true);
//                if (SLLiveFragment.this.giftNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.giftNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideGiftNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(10004);
//                }
//            }
//        });
//        this.mLivePusherInfoView.setCharmAnimListener(new SimpleAnimatorListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.6
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationStart(Animator animator) {
//                SLLiveFragment.this.canShowAnchorInfoNotice.set(false);
//            }
//
//            @Override // android.animation.Animator.AnimatorListener
//            public void onAnimationEnd(Animator animator) {
//                SLLiveFragment.this.canShowAnchorInfoNotice.set(true);
//                if (SLLiveFragment.this.anchorInfoNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.anchorInfoNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideCharmNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(ConstantUtils.ANCHOR_INFO_NOTICE);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationCancel(Animator animator) {
//                SLLiveFragment.this.canShowAnchorInfoNotice.set(true);
//                if (SLLiveFragment.this.anchorInfoNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.anchorInfoNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideCharmNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(ConstantUtils.ANCHOR_INFO_NOTICE);
//                }
//            }
//        });
//        this.mLivePusherInfoView.setSysNoticeAnimListener(new SimpleAnimatorListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.7
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationStart(Animator animator) {
//                SLLiveFragment.this.canShowSysNotice.set(false);
//            }
//
//            @Override // android.animation.Animator.AnimatorListener
//            public void onAnimationEnd(Animator animator) {
//                SLLiveFragment.this.canShowSysNotice.set(true);
//                if (SLLiveFragment.this.sysNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.sysNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideSysNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(10005);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationCancel(Animator animator) {
//                SLLiveFragment.this.canShowSysNotice.set(true);
//                if (SLLiveFragment.this.sysNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.sysNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideSysNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(10005);
//                }
//            }
//        });
//        this.mLivePusherInfoView.setLuckNoticeAnimListener(new SimpleAnimatorListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.8
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationStart(Animator animator) {
//                SLLiveFragment.this.canShowLuckNotice.set(false);
//            }
//
//            @Override // android.animation.Animator.AnimatorListener
//            public void onAnimationEnd(Animator animator) {
//                SLLiveFragment.this.canShowLuckNotice.set(true);
//                if (SLLiveFragment.this.luckNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.luckNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideLuckNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(ConstantUtils.SYS_LUCK_HIT);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationCancel(Animator animator) {
//                SLLiveFragment.this.canShowLuckNotice.set(true);
//                if (SLLiveFragment.this.luckNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.luckNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideLuckNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(ConstantUtils.SYS_LUCK_HIT);
//                }
//            }
//        });
//        this.mLivePusherInfoView.setGameNoticeAnimListener(new SimpleAnimatorListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.9
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationStart(Animator animator) {
//                SLLiveFragment.this.canShowGameNotice.set(false);
//            }
//
//            @Override // android.animation.Animator.AnimatorListener
//            public void onAnimationEnd(Animator animator) {
//                SLLiveFragment.this.canShowGameNotice.set(true);
//                if (SLLiveFragment.this.gameNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.gameNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideGameNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(ConstantUtils.GAME_NOTICE);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
//            public void onAnimationCancel(Animator animator) {
//                SLLiveFragment.this.canShowGameNotice.set(true);
//                if (SLLiveFragment.this.gameNoticeQueue == null) {
//                    return;
//                }
//                if (SLLiveFragment.this.gameNoticeQueue.isEmpty()) {
//                    SLLiveFragment.this.mLivePusherInfoView.hideGameNoticeView();
//                } else {
//                    SLLiveFragment.this.sendWorkHandlerEmptyMessage(ConstantUtils.GAME_NOTICE);
//                }
//            }
//        });
//        this.mLivePusherInfoView.initLivePusherInfoCallback(2, getChildFragmentManager(), new SimpleLivePusherInfoCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.10
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickAnchorAvatarListener(View view2) {
//                if (!SLLiveFragment.this.isStartGetAnchorInfo) {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getAnchorInfo(SLLiveFragment.this.anchorId);
//                    SLLiveFragment.this.isStartGetAnchorInfo = true;
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickGiftNoticeListener(View view2) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.startActivityById(sLLiveFragment.giftNoticeLiveId);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickAnchorInfoNoticeListener(View view2) {
//                if (SLLiveFragment.this.curAnchorInfoNoticeEntity != null) {
//                    if (TextUtils.equals(SLLiveFragment.this.curAnchorInfoNoticeEntity.type, "startLiveNotify")) {
//                        ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).updateStartLiveNoticeCount(SLLiveFragment.this.curAnchorInfoNoticeEntity.forwardLiveId);
//                    }
//                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                    sLLiveFragment.startActivityById(sLLiveFragment.curAnchorInfoNoticeEntity.forwardLiveId);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickSysNoticeListener(View view2) {
//                if (SLLiveFragment.this.tempSysNoticeResultData != null) {
//                    String str = SLLiveFragment.this.tempSysNoticeResultData.sysNoticeType;
//                    char c = 65535;
//                    switch (str.hashCode()) {
//                        case -1603387347:
//                            if (str.equals("openNobilityBroadcast")) {
//                                c = 2;
//                                break;
//                            }
//                            break;
//                        case -941691210:
//                            if (str.equals("universalBroadcast")) {
//                                c = 1;
//                                break;
//                            }
//                            break;
//                        case -370196576:
//                            if (str.equals("generalFlutterScreen")) {
//                                c = 3;
//                                break;
//                            }
//                            break;
//                        case 395254178:
//                            if (str.equals("nobilityTrumpetBroadcast")) {
//                                c = 0;
//                                break;
//                            }
//                            break;
//                    }
//                    if (c == 0) {
//                        SLLiveFragment.this.trumpetNoticeClick();
//                    } else if (c == 1) {
//                        SLLiveFragment.this.sysNoticeClick();
//                    } else if (c == 2) {
//                        SLLiveFragment.this.nobilityNoticeClick();
//                    } else if (c == 3) {
//                        SLLiveFragment.this.generalNoticeClick();
//                    }
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickLuckNoticeListener(View view2) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.startActivityById(sLLiveFragment.luckNoticeLiveId);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickGameNoticeListener(View view2) {
//                ComponentsEntity localCacheComponentsByGameId;
//                if (!TextUtils.isEmpty(SLLiveFragment.this.curGameNoticeId) && (localCacheComponentsByGameId = CacheUtils.getLocalCacheComponentsByGameId(SLLiveFragment.this.curGameNoticeId)) != null) {
//                    SLLiveFragment.this.showComponentsWebViewDialog(true, localCacheComponentsByGameId);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onFollowAnchorListener(View view2) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.attentionAnchorAction(view2, sLLiveFragment.anchorId);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickUserAvatarListener(UserEntity userEntity) {
//                SLLiveFragment.this.showUserCard(userEntity);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickAdBannerListener(BannerEntity bannerEntity) {
//                SLLiveFragment.this.onAdBannerClick(bannerEntity);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onNobilityOpenListener() {
//                SLLiveFragment.this.toNobilityOpenActivity();
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleLivePusherInfoCallback, com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickGuardListener(GuardItemEntity guardItemEntity) {
//                super.onClickGuardListener(guardItemEntity);
//                SLLiveFragment.this.showGuardListDialog(guardItemEntity);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleLivePusherInfoCallback, com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickAudienceListener(View view2) {
//                super.onClickAudienceListener(view2);
//                SLLiveFragment.this.showPayLiveEvaluationDialog();
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleLivePusherInfoCallback, com.slzhibo.library.ui.interfaces.OnLivePusherInfoCallback
//            public void onClickNoticeListener(View view2) {
//                super.onClickNoticeListener(view2);
//                SLLiveFragment.this.showLinkNoticeManageDialog();
//            }
//        });
//        RxViewUtils.getInstance().throttleFirst(this.ivMore, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$tg0TTMpyCpPb_KQZi6ChmxhlKVY
//            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
//            public final void action(Object obj) {
//                SLLiveFragment.this.lambda$initListener$1$SLLiveFragment(obj);
//            }
//        });
//        RxViewUtils.getInstance().throttleFirst(this.giftButton, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$Of6lfJM8iIP0D0HiDeEbqMhyJn4
//            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
//            public final void action(Object obj) {
//                SLLiveFragment.this.lambda$initListener$2$SLLiveFragment(obj);
//            }
//        });
//        RxViewUtils.getInstance().throttleFirst(this.ivClosed, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$_43jFcvUsjtj8sLBCY7XB32d9Q0
//            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
//            public final void action(Object obj) {
//                SLLiveFragment.this.lambda$initListener$3$SLLiveFragment(obj);
//            }
//        });
//        RxViewUtils.getInstance().throttleFirst(this.ivRecommendComponents, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$UePPWI6PS8tZfXHOg-DNU3jxMkk
//            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
//            public final void action(Object obj) {
//                SLLiveFragment.this.lambda$initListener$4$SLLiveFragment(obj);
//            }
//        });
//        RxViewUtils.getInstance().throttleFirst(this.ivComponentsView, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$UilcSAzExYxd0A0lO4mSoSWw8vg
//            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
//            public final void action(Object obj) {
//                SLLiveFragment.this.lambda$initListener$5$SLLiveFragment(obj);
//            }
//        });
//        RxViewUtils.getInstance().throttleFirst(this.tvInput, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$19JSnVxlNAvJO7617E1qVacT1ds
//            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
//            public final void action(Object obj) {
//                SLLiveFragment.this.lambda$initListener$6$SLLiveFragment(obj);
//            }
//        });
//        this.mInputTextMsgDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$TXWURD5vJScNamWC44JpXalE5BQ
//            @Override // android.content.DialogInterface.OnDismissListener
//            public final void onDismiss(DialogInterface dialogInterface) {
//                SLLiveFragment.this.lambda$initListener$7$SLLiveFragment(dialogInterface);
//            }
//        });
//        this.mLiveLoadingView.setOnLiveLoadingListener(new LiveLoadingView.OnLiveLoadingListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.12
//            @Override // com.slzhibo.library.ui.view.custom.LiveLoadingView.OnLiveLoadingListener
//            public void onReloadClickListener(int i) {
//                if (i != 1) {
//                    if (i == 2) {
//                        SLLiveFragment.this.changeLineReloadLoading(0);
//                    }
//                } else if (NetUtils.getNetWorkState() == -1) {
//                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                    sLLiveFragment.showToast(sLLiveFragment.getResources().getString(R.string.fq_text_no_network));
//                } else {
//                    if (SLLiveFragment.this.isVideoRoomType()) {
//                        SLLiveFragment.this.showLoadingAnim();
//                    }
//                    SLLiveFragment.this.pullStreamUrl = "";
//                    SLLiveFragment.this.sendLiveInitInfoRequest();
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.view.custom.LiveLoadingView.OnLiveLoadingListener
//            public void onChangeLineClickListener(int i) {
//                if (i == 1) {
//                    SLLiveFragment.this.changeLineReloadLoading(0);
//                } else if (i == 2) {
//                    SLLiveFragment.this.changeLineReloadLoading(1);
//                } else if (i == 3) {
//                    SLLiveFragment.this.changeLineReloadLoading(2);
//                }
//            }
//        });
//        this.liveAnimationView.setAnimationCallback(new SimpleSVGACallBack() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.13
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleSVGACallBack, com.slzhibo.library.ui.view.widget.svga.SVGACallback
//            public void onFinished() {
//                super.onFinished();
//                SLLiveFragment.this.liveAnimationView.setGiftAnimViewVisibility(4);
//                SLLiveFragment.this.wsManagerNotifyBigAnim();
//            }
//        }, new SimpleSVGACallBack() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.14
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleSVGACallBack, com.slzhibo.library.ui.view.widget.svga.SVGACallback
//            public void onFinished() {
//                super.onFinished();
//                SLLiveFragment.this.liveAnimationView.setGuardEnterAnimViewVisibility(4);
//                SLLiveFragment.this.canShowGuardEnterMsg.set(true);
//            }
//        }, new LiveAnimationView.OnLeftGiftCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.15
//            @Override // com.slzhibo.library.ui.view.custom.LiveAnimationView.OnLeftGiftCallback
//            public void onLeftGiftDeleteListener(GiftAnimModel giftAnimModel) {
//                if (!TextUtils.equals(giftAnimModel.getSendUserId(), SLLiveFragment.this.myUserInfoEntity.getUserId())) {
//                    return;
//                }
//                if (giftAnimModel.isProp) {
//                    ((GiftIndexEntity) SLLiveFragment.this.myPropIndexMap.get(giftAnimModel.getGiftId())).countDownStartTime = System.currentTimeMillis();
//                    return;
//                }
//                ((GiftIndexEntity) SLLiveFragment.this.myGiftIndexMap.get(giftAnimModel.getGiftId())).countDownStartTime = System.currentTimeMillis();
//            }
//
//            @Override // com.slzhibo.library.ui.view.custom.LiveAnimationView.OnLeftGiftCallback
//            public void onLeftGiftClickListener(GiftAnimModel giftAnimModel) {
//                SLLiveFragment.this.showUserCard(AppUtils.formatUserEntity(giftAnimModel));
//            }
//        }, new SimpleSVGACallBack() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.16
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleSVGACallBack, com.slzhibo.library.ui.view.widget.svga.SVGACallback
//            public void onFinished() {
//                super.onFinished();
//                SLLiveFragment.this.carFullAnimFinish.set(true);
//            }
//        });
//        this.mLiveChatMsgView.setOnChatMsgItemClickListener(new ChatMsgListAdapter.OnItemClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.17
//            @Override // com.slzhibo.library.ui.adapter.ChatMsgListAdapter.OnItemClickListener
//            public void onItemClick(ChatEntity chatEntity) {
//                if (AppUtils.isShowUserAvatarDialog(chatEntity)) {
//                    SLLiveFragment.this.showUserCard(AppUtils.formatUserEntity(chatEntity));
//                    return;
//                }
//                switch (chatEntity.getMsgType()) {
//                    case 16:
//                        SLLiveFragment.this.showComponentsWebViewDialog(true, CacheUtils.getLocalCacheComponentsByGameId(chatEntity.getPropId()));
//                        return;
//                    case 17:
//                        SLLiveFragment.this.toNobilityOpenActivity();
//                        return;
//                    case 18:
//                        if (!AppUtils.isAttentionAnchor(SLLiveFragment.this.anchorId)) {
//                            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                            sLLiveFragment.attentionAnchorAction(true, sLLiveFragment.anchorId);
//                            return;
//                        }
//                        return;
//                    case 19:
//                        SLLiveFragment.this.showLotteryDialog();
//                        return;
//                    case 20:
//                        SLLiveFragment.this.trumpetNoticeClick();
//                        return;
//                    case 21:
//                        SLLiveFragment.this.sysNoticeClick();
//                        return;
//                    case 22:
//                        SLLiveFragment.this.showProductRecommendDialog(null);
//                        return;
//                    case 23:
//                        if (TextUtils.equals(chatEntity.getTargetId(), String.valueOf(1)) || TextUtils.equals(chatEntity.getTargetId(), String.valueOf(2))) {
//                            SLLiveFragment.this.showLYControlWindowDialog(0);
//                            return;
//                        } else {
//                            SLLiveFragment.this.showLYControlWindowViewStub(true);
//                            return;
//                        }
//                    default:
//                        return;
//                }
//            }
//        });
//        this.mGiftBoxView.setOnSendGiftBoxMsgListener(new GiftBoxView.OnSendGiftBoxMsgListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.18
//            @Override // com.slzhibo.library.ui.view.custom.GiftBoxView.OnSendGiftBoxMsgListener
//            public void onSendGiftBoxMsg(GiftBoxEntity giftBoxEntity) {
//                if (SLLiveFragment.this.wsManager != null && SLLiveFragment.this.wsManager.getSocketStatus() == WsStatus.CONNECT_SUCCESS) {
//                    SLLiveFragment.this.wsManager.sendGrabGiftBoxMessage(MessageHelper.convertToGrabGiftBoxMsg(giftBoxEntity.giftBoxUniqueCode));
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.view.custom.GiftBoxView.OnSendGiftBoxMsgListener
//            public void onShowDialog(GiftBoxEntity giftBoxEntity) {
//                GiftBoxPresenterDialog.newInstance(giftBoxEntity.presenterAvatar, giftBoxEntity.presenterName).show(SLLiveFragment.this.getChildFragmentManager());
//            }
//        });
//        this.mLiveAdBannerBottomView.setOnRefreshTaskListener(new TaskBoxView.OnRefreshTaskListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.19
//            @Override // com.slzhibo.library.ui.view.custom.TaskBoxView.OnRefreshTaskListener
//            public void onRefreshTask(TaskBoxEntity taskBoxEntity) {
//                SLLiveFragment.this.mTaskBottomDialog.updateSingleData(taskBoxEntity);
//            }
//
//            @Override // com.slzhibo.library.ui.view.custom.TaskBoxView.OnRefreshTaskListener
//            public void onShowDialog() {
//                if (SLLiveFragment.this.isConsumptionPermissionUserToLogin() && SLLiveFragment.this.mTaskBottomDialog != null && !SLLiveFragment.this.mTaskBottomDialog.isAdded()) {
//                    SLLiveFragment.this.mTaskBottomDialog.show(SLLiveFragment.this.getChildFragmentManager());
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.view.custom.TaskBoxView.OnRefreshTaskListener
//            public void onTaskComplete(TaskBoxEntity taskBoxEntity) {
//                if (SLLiveFragment.this.isSocketError) {
//                    SLLiveFragment.this.mLiveAdBannerBottomView.releaseForTaskBox();
//                    SLLiveFragment.this.isTaskSocket = true;
//                    return;
//                }
//                SLLiveFragment.this.isTaskSocket = false;
//                if (SLLiveFragment.this.isLiving()) {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).changeTaskState(taskBoxEntity);
//                }
//            }
//        });
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.setOnDismissListener(new BaseRxDialogFragment.DialogDismissListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$dChWuuvcwwvasvujbmUx7WKjW8w
//                @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment.DialogDismissListener
//                public final void onDialogDismiss(BaseRxDialogFragment baseRxDialogFragment) {
//                    SLLiveFragment.this.lambda$initListener$8$SLLiveFragment(baseRxDialogFragment);
//                }
//            });
//        }
//        this.ivComponentsView.setOnLotteryBoomEndCallback(new OnLotteryBoomCallback() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$iqarpKoVitq-4_VIVERsSlWoS-c
//            @Override // com.slzhibo.library.ui.interfaces.OnLotteryBoomCallback
//            public final void onBoomCountDownEnd() {
//                SLLiveFragment.this.lambda$initListener$9$SLLiveFragment();
//            }
//        });
//        this.ivRecommendComponents.setOnLotteryBoomEndCallback(new OnLotteryBoomCallback() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$eByodGHnwTeGk0J0cZ4-9BdcZQs
//            @Override // com.slzhibo.library.ui.interfaces.OnLotteryBoomCallback
//            public final void onBoomCountDownEnd() {
//                SLLiveFragment.this.lambda$initListener$10$SLLiveFragment();
//            }
//        });
//        this.mLiveAdBannerBottomView.setOnAdBannerClickListener(new OnLiveFloatingWindowCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.20
//            @Override // com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback
//            public void onAdBannerClickListener(BannerEntity bannerEntity) {
//                SLLiveFragment.this.onAdBannerClick(bannerEntity);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback
//            public void onYYLinkWindowClickListener(YYLinkApplyEntity yYLinkApplyEntity) {
//                if (yYLinkApplyEntity != null && AppUtils.isCurrentLoginUser(yYLinkApplyEntity.userId)) {
//                    SLLiveFragment.this.showLinkVoiceApplyDetailDialog(false);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback
//            public void onHJProductWindowClickListener(boolean z, HJProductEntity hJProductEntity) {
//                if (hJProductEntity != null) {
//                    hJProductEntity.setHotFlag(z);
//                }
//                SLLiveFragment.this.showProductRecommendDialog(hJProductEntity);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnLiveFloatingWindowCallback
//            public void onLYBluetoothWindowClickListener() {
//                SLLiveFragment.this.showLYControlWindowViewStub(true);
//            }
//        });
//        this.mLiveAdBannerBottomView.setOnInteractWindowClickListener(new TopBannerUtils.InteractWindowListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.21
//            @Override // com.slzhibo.library.ui.view.widget.bgabanner.TopBannerUtils.InteractWindowListener
//            public void onClick(View view2) {
//                if (view2 instanceof HdLotteryWindowView) {
//                    SLLiveFragment.this.showHdLotteryDrawDialog();
//                }
//                if (view2 instanceof QMNoticeWindow) {
//                    SLLiveFragment.this.mLiveAdBannerBottomView.hideRedPoint();
//                    SLLiveFragment.this.showQMInteractTaskListDialog(null);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.view.widget.bgabanner.TopBannerUtils.InteractWindowListener
//            public void onDelete(View view2) {
//                if (view2 instanceof HdLotteryWindowView) {
//                    if (SLLiveFragment.this.hdLotteryDrawingDialog != null) {
//                        SLLiveFragment.this.hdLotteryDrawingDialog.onReleaseData();
//                    }
//                    SLLiveFragment.this.mLiveAdBannerBottomView.onDeleteHdLotteryWindowView();
//                }
//            }
//        });
//        RxViewUtils.getInstance().throttleFirst(this.ivYYLink, 500, new RxViewUtils.RxViewAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$o8gLPOcG5811zv2d7ORkBCMRzUQ
//            @Override // com.slzhibo.library.utils.RxViewUtils.RxViewAction
//            public final void action(Object obj) {
//                SLLiveFragment.this.lambda$initListener$11$SLLiveFragment(obj);
//            }
//        });
//        this.yyLinkSeatListView.setLinkCallback(new SimpleYYLinkCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.22
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleYYLinkCallback, com.slzhibo.library.ui.interfaces.YYLinkCallback
//            public void onClickLinkRTCUserListener(int i, YYLinkApplyEntity yYLinkApplyEntity) {
//                super.onClickLinkRTCUserListener(i, yYLinkApplyEntity);
//                if (i != 4) {
//                    SLLiveFragment.this.showLinkActionMenuDialog(yYLinkApplyEntity);
//                }
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$initListener$1$SLLiveFragment(Object obj) {
//        showLiveMoreDialog();
//    }
//
//    public /* synthetic */ void lambda$initListener$2$SLLiveFragment(Object obj) {
//        showGiftPanel();
//    }
//
//    public /* synthetic */ void lambda$initListener$3$SLLiveFragment(Object obj) {
//        if (this.isRTCStream) {
//            showLinkUserDisconnectLinkDialog(new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.11
//                @Override // android.view.View.OnClickListener
//                public void onClick(View view) {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onSendUserDisconnectLinkRequest(SLLiveFragment.this.isVoiceRoomType(), SLLiveFragment.this.liveId, SLLiveFragment.this.liveCount, null);
//                    SLLiveFragment.this.onFinishActivity();
//                }
//            });
//        } else {
//            onFinishActivity();
//        }
//    }
//
//    public /* synthetic */ void lambda$initListener$4$SLLiveFragment(Object obj) {
//        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
//        if (componentsEntity == null) {
//            return;
//        }
//        if (componentsEntity.isCacheLotteryComponents()) {
//            showLotteryDialog();
//        } else {
//            showComponentsWebViewDialog(true, this.cacheRecommendComponents);
//        }
//    }
//
//    public /* synthetic */ void lambda$initListener$5$SLLiveFragment(Object obj) {
//        showComponentsDialog();
//    }
//
//    public /* synthetic */ void lambda$initListener$6$SLLiveFragment(Object obj) {
//        showChatFrames();
//    }
//
//    public /* synthetic */ void lambda$initListener$7$SLLiveFragment(DialogInterface dialogInterface) {
//        moveUpViews(false);
//    }
//
//    public /* synthetic */ void lambda$initListener$8$SLLiveFragment(BaseRxDialogFragment baseRxDialogFragment) {
//        if (this.isLotteryDialogFlag) {
//            showDialogFragment(this.mLotteryDialog);
//        }
//    }
//
//    public /* synthetic */ void lambda$initListener$9$SLLiveFragment() {
//        if (AppUtils.isEnableTurntable()) {
//            ((SLLivePresenter) this.mPresenter).getBoomStatus(this.liveId);
//        }
//    }
//
//    public /* synthetic */ void lambda$initListener$10$SLLiveFragment() {
//        if (AppUtils.isEnableTurntable()) {
//            ((SLLivePresenter) this.mPresenter).getBoomStatus(this.liveId);
//        }
//    }
//
//    public /* synthetic */ void lambda$initListener$11$SLLiveFragment(Object obj) {
//        showLinkVoiceApplyDetailDialog(false);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void generalNoticeClick() {
//        if (TextUtils.equals("recommend", this.tempSysNoticeResultData.type) && TextUtils.equals(this.tempSysNoticeResultData.clickEvent, ConstantUtils.SOCKET_MSG_CLICK_EVENT_LIVE)) {
//            noticeClickToActivity(this.tempSysNoticeResultData.forwardLiveId);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void nobilityNoticeClick() {
//        if (AppUtils.highThanBoJue(this.tempSysNoticeResultData.nobilityType) && TextUtils.equals(this.tempSysNoticeResultData.clickEvent, ConstantUtils.SOCKET_MSG_CLICK_EVENT_LIVE)) {
//            noticeClickToActivity(this.tempSysNoticeResultData.forwardLiveId);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void trumpetNoticeClick() {
//        SocketMessageEvent.ResultData resultData = this.tempSysNoticeResultData;
//        if (resultData != null) {
//            ((SLLivePresenter) this.mPresenter).updateTrumpetClickCount(resultData.trumpetId);
//            noticeClickToActivity(this.tempSysNoticeResultData.liveId);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void sysNoticeClick() {
//        SocketMessageEvent.ResultData resultData = this.tempSysNoticeResultData;
//        if (resultData != null) {
//            String str = resultData.id;
//            String str2 = resultData.clickEvent;
//            String str3 = resultData.forwardText;
//            char c = 65535;
//            int hashCode = str2.hashCode();
//            if (hashCode != -370076372) {
//                if (hashCode == -289024721 && str2.equals(ConstantUtils.SOCKET_MSG_CLICK_EVENT_URL)) {
//                    c = 1;
//                }
//            } else if (str2.equals(ConstantUtils.SOCKET_MSG_CLICK_EVENT_LIVE)) {
//                c = 0;
//            }
//            if (c != 0) {
//                if (c == 1) {
//                    try {
//                        WebViewDialog.newInstance("    ", str3).show(getChildFragmentManager());
//                        ((SLLivePresenter) this.mPresenter).getBroadcastClick(str);
//                        return;
//                    } catch (Exception unused) {
//                        return;
//                    }
//                }
//            }
//            ((SLLivePresenter) this.mPresenter).getBroadcastClick(str);
//            noticeClickToActivity(str3);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void goToLive() {
//        if (SysConfigInfoManager.getInstance().isEnableLiveGuide()) {
//            this.showGuideRating = false;
//            setViewPagerScrollEnable(false);
//            if (this.mLiveGuideView == null) {
//                this.mLiveGuideView = this.mLiveGuideViewVs.inflate();
//            }
//            this.mLiveGuideView.findViewById(R.id.iv_know).setOnClickListener(new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$Whqzsfo8BM1AOXdlNpOTEJJvl2M
//                @Override // android.view.View.OnClickListener
//                public final void onClick(View view) {
//                    SLLiveFragment.this.lambda$goToLive$12$SLLiveFragment(view);
//                }
//            });
//        } else {
//            setViewPagerScrollEnable(true);
//        }
//        initWatermarkConfig();
//        if (this.liveItemEntity.isVoiceRoomType()) {
//            this.ivYYLinkBgCover.setVisibility(View.VISIBLE);
//            GlideUtils.loadImage(this.mContext, this.ivYYLinkBgCover, this.liveItemEntity.backgroundUrl, R.drawable.fq_ic_yy_link_live_bg);
//            setViewPagerScrollEnable(false);
//            this.ivYYLink.setVisibility(View.VISIBLE);
//            this.mLivePusherInfoView.setLiveAdBannerViewVisibility(false);
//            initSeatData();
//            ((SLLivePresenter) this.mPresenter).onSendLinkNoticeRequest(1L, this.liveId, new ResultCallBack<YYNoticeEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.23
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str) {
//                }
//
//                public void onSuccess(YYNoticeEntity yYNoticeEntity) {
//                    SLLiveFragment.this.mLivePusherInfoView.setLinkNoticeContent(true, yYNoticeEntity == null ? null : yYNoticeEntity.content);
//                }
//            });
//        } else {
//            this.ivYYLink.setVisibility(View.GONE);
//            this.ivYYLinkBgCover.setVisibility(View.INVISIBLE);
//            this.yyLinkSeatListView.setVisibility(View.INVISIBLE);
//            setViewPagerScrollEnable(true);
//            this.mLivePusherInfoView.setLiveAdBannerViewVisibility(true);
//            ((SLLivePresenter) this.mPresenter).onSendVideoLinkDetailRequest(3L, this.liveId, this.liveCount, new ResultCallBack<YYLinkApplyEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.24
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str) {
//                }
//
//                public void onSuccess(YYLinkApplyEntity yYLinkApplyEntity) {
//                    if (yYLinkApplyEntity != null && !TextUtils.isEmpty(yYLinkApplyEntity.userId)) {
//                        yYLinkApplyEntity.status = "1";
//                        SLLiveFragment.this.mLiveAdBannerBottomView.showYYSmallWindow(yYLinkApplyEntity, SLLiveFragment.this.getString(R.string.fq_yy_linking));
//                    }
//                }
//            });
//        }
//        if (this.liveItemEntity.isPKLiveRoom()) {
//            GlideUtils.loadImage(this.mContext, this.mAnchorCoverImg, R.drawable.fq_ic_pk_main_bottom_bg);
//            String str = this.liveId;
//            AnchorEntity anchorEntity = this.anchorItemEntity;
//            String str2 = anchorEntity.nickname;
//            String str3 = anchorEntity.avatar;
//            LiveItemEntity liveItemEntity = this.liveItemEntity;
//            showLinkPKLayoutView(str, str2, str3, liveItemEntity.lianmaiTargetAnchorId, liveItemEntity.lianmaiTargetLiveId, liveItemEntity.lianmaiTargetAnchorAvatar, liveItemEntity.lianmaiTargetAnchorName, NumberUtils.string2long(liveItemEntity.pkTimeRemain), NumberUtils.string2long(this.liveItemEntity.pkPunishTime), this.liveItemEntity.isPKStart(), true);
//            if (this.liveItemEntity.isPKStart()) {
//                ((SLLivePresenter) this.mPresenter).onGetFP(this.liveId, this.liveItemEntity.isPKEnd(), false);
//            }
//        }
//        showRecommendComponentsView();
//        this.socketUrl = AppUtils.formatLiveSocketUrl(this.liveItemEntity.wsAddress, this.liveId, this.liveCount, this.myUserInfoEntity.getUserId(), "2", this.socketEncryptionKey);
//        initSocket();
//        ((SLLivePresenter) this.mPresenter).getCurrentOnlineUserList(this.liveId, AppUtils.getOnlineCountSynInterval());
//        ((SLLivePresenter) this.mPresenter).getLivePopularity(this.liveId, this.anchorAppId);
//        ((SLLivePresenter) this.mPresenter).getGiftBoxList(5L, this.liveId);
//        sendAdImageRequest();
//        ((SLLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(ConstantUtils.ENTER_TYPE);
//        ((SLLivePresenter) this.mPresenter).sendUserShowTaskListRequest(5L, this.liveId);
//        if (AppUtils.isEnableTurntable()) {
//            ((SLLivePresenter) this.mPresenter).getBoomStatus(this.liveId);
//        }
//        ((SLLivePresenter) this.mPresenter).onAttentionMsgNotice(new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.25
//            @Override // com.slzhibo.library.http.ResultCallBack
//            public void onError(int i, String str4) {
//            }
//
//            @Override // com.slzhibo.library.http.ResultCallBack
//            public void onSuccess(Object obj) {
//                if (!AppUtils.isAttentionAnchor(SLLiveFragment.this.anchorId)) {
//                    ChatEntity chatEntity = new ChatEntity();
//                    chatEntity.setUid(UserInfoManager.getInstance().getUserId());
//                    chatEntity.setMsgText(SLLiveFragment.this.getString(R.string.fq_msg_attention_tips));
//                    chatEntity.setMsgType(18);
//                    SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
//                }
//            }
//        });
//        ((SLLivePresenter) this.mPresenter).onOpenNobilityMsgNotice(new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.26
//            @Override // com.slzhibo.library.http.ResultCallBack
//            public void onError(int i, String str4) {
//            }
//
//            @Override // com.slzhibo.library.http.ResultCallBack
//            public void onSuccess(Object obj) {
//                ChatEntity chatEntity = new ChatEntity();
//                chatEntity.setMsgText(SLLiveFragment.this.getString(AppUtils.isNobilityUser() ? R.string.fq_msg_renew_nobility_tips : R.string.fq_msg_open_nobility_tips));
//                chatEntity.setMsgType(17);
//                SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
//            }
//        });
//        ((SLLivePresenter) this.mPresenter).onSendHJShelfDetailRequest(5L, this.liveId, new ResultCallBack<HJProductEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.27
//            @Override // com.slzhibo.library.http.ResultCallBack
//            public void onError(int i, String str4) {
//            }
//
//            public void onSuccess(HJProductEntity hJProductEntity) {
//                if (hJProductEntity != null) {
//                    SLLiveFragment.this.liveItemEntity.shelfId = hJProductEntity.shelfId;
//                    hJProductEntity.productId = hJProductEntity.id;
//                    hJProductEntity.productName = hJProductEntity.name;
//                    SLLiveFragment.this.mLiveAdBannerBottomView.showHJProductWindow(false, hJProductEntity);
//                }
//            }
//        });
//        if (isBluetoothConnection()) {
//            showLYControlWindowViewStub(false);
//            ((SLLivePresenter) this.mPresenter).sendBluetoothConnectionNotice(new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.28
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str4) {
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onSuccess(Object obj) {
//                    ChatEntity chatEntity = new ChatEntity();
//                    chatEntity.setMsgText(SLLiveFragment.this.anchorItemEntity.nickname);
//                    chatEntity.setTargetId(String.valueOf(1));
//                    chatEntity.setMsgType(23);
//                    SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
//                }
//            });
//            GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//            if (giftBackpackDialog != null) {
//                giftBackpackDialog.setBluetoothConnection(isBluetoothConnection());
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$goToLive$12$SLLiveFragment(View view) {
//        this.mLiveGuideView.setVisibility(View.GONE);
//        SysConfigInfoManager.getInstance().setEnableLiveGuide(false);
//        setViewPagerScrollEnable(true);
//        this.showGuideRating = true;
//        if (SysConfigInfoManager.getInstance().isEnableRatingGuide() && this.isPayLive) {
//            this.mLivePusherInfoView.showGuideRating(this.mActivity);
//        }
//    }
//
//    private void initSeatData() {
//        ((SLLivePresenter) this.mPresenter).sendSeatListRequest(1L, this.liveId, this.liveCount, new ResultCallBack<List<YYLinkApplyEntity>>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.29
//            public void onSuccess(List<YYLinkApplyEntity> list) {
//                SLLiveFragment.this.yyLinkSeatListView.setVisibility(View.VISIBLE);
//                SLLiveFragment.this.yyLinkSeatListView.initAudienceSeatData(list);
//                SLLiveFragment.this.mLivePusherInfoView.updateDanMuMarginTop(ConvertUtils.dp2px(240.0f));
//            }
//
//            @Override // com.slzhibo.library.http.ResultCallBack
//            public void onError(int i, String str) {
//                SLLiveFragment.this.yyLinkSeatListView.setVisibility(View.INVISIBLE);
//            }
//        });
//    }
//
//    private void goToEnd() {
//        if (isVideoRoomType()) {
//            disconnectVideoLink(false);
//        }
//        if (isVoiceRoomType()) {
//            voiceRoomViewDataRelease();
//        }
//        dismissDialogs();
//        stopPlay();
//        this.isLiveEnd = true;
//        this.liveStatus = false;
//        showLiveEndView();
//    }
//
//    private void startHideTitleTimer(String str) {
//        this.mLiveChatMsgView.setLiveTitle(str);
//    }
//
//    private void startKickDialogService() {
//        AppUtils.startDialogService(this.mContext, KickDialogService.class);
//    }
//
//    private void startKickDialogService(String str) {
//        AppUtils.startDialogService(this.mContext, KickDialogService.class, str);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void startTokenDialogService() {
//        AppUtils.startDialogService(this.mContext, TokenDialogService.class);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void startActivityById(String str) {
//        if (!TextUtils.isEmpty(str)) {
//            if (TextUtils.equals(str, this.liveId)) {
//                showToast(R.string.fq_already_in_room);
//                return;
//            }
//            LiveEntity liveEntity = new LiveEntity();
//            liveEntity.liveId = str;
//            resetLiveRoom(liveEntity, "2");
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLoadingAnim() {
//        showLiveLoadingView(0);
//        this.mLiveLoadingView.showLoadingView();
//    }
//
//    private void showReloadButton() {
//        showLiveLoadingView(0);
//        this.mLiveLoadingView.showReloadView(2);
//        dismissDialogs();
//    }
//
//    private void showRoomInfoReload() {
//        showLiveLoadingView(0);
//        this.mLiveLoadingView.showReloadView(1);
//    }
//
//    private void hideLoadingAnim() {
//        this.mLiveLoadingView.stopLoadingViewAnimation();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLiveLoadingView(int i) {
//        this.mLiveLoadingView.setVisibility(i);
//        if (i == 4 || i == 8) {
//            hideLoadingAnim();
//            cancelPullTimeOut();
//        }
//    }
//
//    private void setAnchorCoverImg() {
//        if (this.mAnchorCoverImg.getVisibility() != View.VISIBLE) {
//            this.mAnchorCoverImg.setVisibility(View.VISIBLE);
//        }
//        GlideUtils.loadImageBlur(this.mContext, this.mAnchorCoverImg, this.anchorItemEntity.liveCoverUrl, R.drawable.fq_shape_default_cover_bg);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onGiftListSuccess(List<GiftDownloadItemEntity> list) {
//        LiveItemEntity liveItemEntity;
//        HdLotteryDrawingDialog hdLotteryDrawingDialog;
//        if (this.isGiftListUpdating && (liveItemEntity = this.liveItemEntity) != null && liveItemEntity.isEnableHdLottery() && (hdLotteryDrawingDialog = this.hdLotteryDrawingDialog) != null) {
//            hdLotteryDrawingDialog.updateGiftInfo();
//        }
//        this.isGiftListUpdating = false;
//        this.isGetGiftListFail = false;
//        this.startGetGiftListInfo = false;
//        initGiftDialog(list);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onAttentionSuccess() {
//        EventBus.getDefault().postSticky(new AttentionEvent());
//        EventBus.getDefault().postSticky(new LiveTopAttentionEvent());
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onAnchorInfoSuccess(AnchorEntity anchorEntity) {
//        if (anchorEntity != null) {
//            anchorEntity.userRole = "1";
//            anchorEntity.role = "1";
//            if (AppUtils.isNobilityUser(anchorEntity.nobilityType)) {
//                this.onUserCardCallback = new UserCardCallback(anchorEntity.userId, true);
//                if (this.anchorNobilityAvatarDialog == null) {
//                    this.anchorNobilityAvatarDialog = UserNobilityAvatarDialog.newInstance(anchorEntity, this.onUserCardCallback);
//                    this.anchorNobilityAvatarDialog.show(getChildFragmentManager());
//                } else {
//                    this.bundleArgs = new Bundle();
//                    this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_MANAGER, true);
//                    this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_IMPRESSION, true);
//                    this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_REPORT, true);
//                    this.bundleArgs.putInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, 2);
//                    this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, anchorEntity);
//                    this.anchorNobilityAvatarDialog.setArguments(this.bundleArgs);
//                    this.anchorNobilityAvatarDialog.setOnUserCardCallback(this.onUserCardCallback);
//                    showDialogFragment(this.anchorNobilityAvatarDialog);
//                }
//                this.isStartGetAnchorInfo = false;
//                return;
//            }
//            this.onUserCardCallback = new UserCardCallback(anchorEntity.userId, true);
//            if (this.anchorAvatarDialog == null) {
//                this.anchorAvatarDialog = UserNormalAvatarDialog.newInstance(anchorEntity, this.onUserCardCallback);
//                this.anchorAvatarDialog.show(getChildFragmentManager());
//            } else {
//                this.bundleArgs = new Bundle();
//                this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_MANAGER, true);
//                this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_IMPRESSION, true);
//                this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_REPORT, true);
//                this.bundleArgs.putInt(ConstantUtils.BUNDLE_VALUE_LIVE_TYPE, 2);
//                this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, anchorEntity);
//                this.anchorAvatarDialog.setArguments(this.bundleArgs);
//                this.anchorAvatarDialog.setOnUserCardCallback(this.onUserCardCallback);
//                showDialogFragment(this.anchorAvatarDialog);
//            }
//            this.isStartGetAnchorInfo = false;
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onAnchorInfoFail() {
//        this.isStartGetAnchorInfo = false;
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onUserCardInfoSuccess(UserEntity userEntity) {
//        showUserCard(userEntity);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void getUserBalance(int i) {
//        if (!AppUtils.isConsumptionPermissionUser()) {
//            this.isFirstGetMyBalanceGift = false;
//            this.isFirstGetMyBalanceLottery = false;
//            this.myPriceBalance = 0.0d;
//            this.myScoreBalance = 0.0d;
//            GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//            if (giftBackpackDialog != null) {
//                giftBackpackDialog.setUserBalance(this.myPriceBalance);
//                this.giftBottomDialog.setUserScore(this.myScoreBalance);
//            }
//            LotteryDialog lotteryDialog = this.mLotteryDialog;
//            if (lotteryDialog != null) {
//                lotteryDialog.setUserBalance(this.myPriceBalance);
//            }
//        } else if (i == 0) {
//            if (this.isFirstGetMyBalanceGift) {
//                this.isFirstGetMyBalanceGift = false;
//                this.giftBottomDialog.setUserBalanceTip(R.string.fq_userover_loading);
//                ((SLLivePresenter) this.mPresenter).getUserOver();
//            } else if (this.getUserBalanceFail) {
//                this.giftBottomDialog.setUserBalanceTip(R.string.fq_userover_loading);
//                ((SLLivePresenter) this.mPresenter).getUserOver();
//            }
//        } else if (i != 1) {
//        } else {
//            if (this.isFirstGetMyBalanceLottery) {
//                this.isFirstGetMyBalanceLottery = false;
//                this.mLotteryDialog.setUserBalanceTip(R.string.fq_userover_loading);
//                ((SLLivePresenter) this.mPresenter).getUserOver();
//            } else if (this.getUserBalanceFail) {
//                this.mLotteryDialog.setUserBalanceTip(R.string.fq_userover_loading);
//                ((SLLivePresenter) this.mPresenter).getUserOver();
//            }
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onUserOverSuccess(MyAccountEntity myAccountEntity) {
//        if (myAccountEntity == null) {
//            this.getUserBalanceFail = true;
//            return;
//        }
//        this.getUserBalanceFail = false;
//        if (!TextUtils.isEmpty(myAccountEntity.getAccountBalance())) {
//            if (this.giftBottomDialog != null) {
//                this.myPriceBalance = NumberUtils.string2Double(myAccountEntity.getAccountBalance());
//                this.giftBottomDialog.setUserBalance(this.myPriceBalance);
//            }
//            if (this.mLotteryDialog != null) {
//                this.myPriceBalance = NumberUtils.string2Double(myAccountEntity.getAccountBalance());
//                this.mLotteryDialog.setUserBalance(this.myPriceBalance);
//            }
//        }
//        if (!TextUtils.isEmpty(myAccountEntity.score) && this.giftBottomDialog != null) {
//            this.myScoreBalance = NumberUtils.string2Double(myAccountEntity.score);
//            this.giftBottomDialog.setUserScore(NumberUtils.string2Double(myAccountEntity.score));
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onUserOverFail() {
//        this.getUserBalanceFail = true;
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.setUserBalanceTip(R.string.fq_userover_loading_fail);
//        }
//        LotteryDialog lotteryDialog = this.mLotteryDialog;
//        if (lotteryDialog != null) {
//            lotteryDialog.setUserBalanceTip(R.string.fq_userover_loading_fail);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onLiveAudiencesSuccess(OnLineUsersEntity onLineUsersEntity) {
//        if (!(onLineUsersEntity == null || onLineUsersEntity.getUserEntityList() == null)) {
//            this.mLivePusherInfoView.replaceData(onLineUsersEntity.getUserEntityList());
//        }
//        this.mLivePusherInfoView.updateVipCount(onLineUsersEntity.vipCount);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onPersonalGuardInfoSuccess(GuardItemEntity guardItemEntity) {
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.updateOpenGuardInfo(guardItemEntity);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onLivePopularitySuccess(long j) {
//        this.onLineCount.set(j);
//        this.mLivePusherInfoView.setLivePopularityCount(this.onLineCount.get());
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTaskListSuccess(List<TaskBoxEntity> list, boolean z) {
//        this.mLiveAdBannerBottomView.setTaskBoxVisibility((list == null || list.isEmpty()) ? 8 : 0);
//        TaskBoxUtils.getInstance().clearList();
//        TaskBoxUtils.getInstance().setData(list);
//        this.mTaskBottomDialog.setmData(list);
//        this.mLiveAdBannerBottomView.refreshTaskButtonForTaskBox();
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTaskListFail() {
//        this.mLiveAdBannerBottomView.setTaskBoxVisibility(8);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTaskTakeSuccess(TaskBoxEntity taskBoxEntity) {
//        taskBoxEntity.setStatus(2);
//        this.mLiveAdBannerBottomView.changeRedCountForTaskBox(false);
//        this.mLiveAdBannerBottomView.checkToCountdownForTaskBox();
//        this.mTaskBottomDialog.updateSingleData(taskBoxEntity);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTaskChangeSuccess(TaskBoxEntity taskBoxEntity) {
//        this.mLiveAdBannerBottomView.changeRedCountForTaskBox(true);
//        taskBoxEntity.setStatus(1);
//        this.mTaskBottomDialog.updateSingleData(taskBoxEntity);
//        List<TaskBoxEntity> data = TaskBoxUtils.getInstance().getData();
//        int indexOf = data.indexOf(taskBoxEntity);
//        int i = indexOf + 1;
//        if (i < data.size()) {
//            data.get(i).setStatus(0);
//            this.mTaskBottomDialog.updateSingleData(data.get(indexOf));
//        }
//        this.mLiveAdBannerBottomView.checkToCountdownForTaskBox();
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTrumpetStatusSuccess(TrumpetStatusEntity trumpetStatusEntity) {
//        this.trumpetStatus = trumpetStatusEntity.isEnable();
//        this.curTrumpetCount.set(trumpetStatusEntity.count);
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//        if (inputTextMsgForAudienceDialog != null) {
//            inputTextMsgForAudienceDialog.setTrumpetCount(trumpetStatusEntity.isEnable(), trumpetStatusEntity.count);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTrumpetSendSuccess() {
//        showToast(R.string.fq_send_trumpet_suc);
//        this.curTrumpetCount.decrementAndGet();
//        if (this.curTrumpetCount.get() == 0) {
//            this.curTrumpetCount.set(0);
//        }
//        this.mInputTextMsgDialog.setTrumpetCount(this.trumpetStatus, this.curTrumpetCount.get());
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.updateBackPackCount();
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onTrumpetSendFail(int i) {
//        if (i != 200107) {
//            switch (i) {
//                case 200099:
//                    this.myNobilityType = -1;
//                    UserInfoManager.getInstance().setNobilityType(this.myNobilityType);
//                    if (this.mInputTextMsgDialog != null) {
//                        this.trumpetStatus = false;
//                        this.curTrumpetCount.set(0);
//                        this.mInputTextMsgDialog.setTrumpetCount(false, 0);
//                        return;
//                    }
//                    return;
//                case 200100:
//                    showToast(R.string.fq_nobility_expire);
//                    this.myNobilityType = -1;
//                    UserInfoManager.getInstance().setNobilityType(this.myNobilityType);
//                    InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//                    if (inputTextMsgForAudienceDialog != null) {
//                        this.trumpetStatus = false;
//                        inputTextMsgForAudienceDialog.setTrumpetCount(false, this.curTrumpetCount.get());
//                        return;
//                    }
//                    return;
//                case 200101:
//                    showToast(R.string.fq_trumpet_freezen);
//                    InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog2 = this.mInputTextMsgDialog;
//                    if (inputTextMsgForAudienceDialog2 != null) {
//                        this.trumpetStatus = false;
//                        inputTextMsgForAudienceDialog2.setTrumpetCount(false, this.curTrumpetCount.get());
//                        return;
//                    }
//                    return;
//                case 200102:
//                    showToast(R.string.fq_trumpet_count_not_enough);
//                    if (this.mInputTextMsgDialog != null) {
//                        this.trumpetStatus = true;
//                        this.curTrumpetCount.set(0);
//                        this.mInputTextMsgDialog.setTrumpetCount(true, 0);
//                        return;
//                    }
//                    return;
//                default:
//                    return;
//            }
//        } else {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog3 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog3 != null) {
//                this.trumpetStatus = false;
//                inputTextMsgForAudienceDialog3.setTrumpetCount(false, this.curTrumpetCount.get());
//            }
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onFragmentConfigSuccess(PropConfigEntity propConfigEntity) {
//        ComposeDialog.newInstance(propConfigEntity, true, new ComposeDialog.ComposeListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$U-aM1Jeq1nc1Y8xv44wvifQR3pQ
//            @Override // com.slzhibo.library.ui.view.dialog.alert.ComposeDialog.ComposeListener
//            public final void onClick(PropConfigEntity propConfigEntity2) {
//                SLLiveFragment.this.lambda$onFragmentConfigSuccess$13$SLLiveFragment(propConfigEntity2);
//            }
//        }).show(getChildFragmentManager());
//    }
//
//    public /* synthetic */ void lambda$onFragmentConfigSuccess$13$SLLiveFragment(PropConfigEntity propConfigEntity) {
//        ((SLLivePresenter) this.mPresenter).getUsePropService(propConfigEntity, this.liveId);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onUseFragmentSuccess(PropConfigEntity propConfigEntity) {
//        ComposeSuccessDialog newInstance = ComposeSuccessDialog.newInstance(propConfigEntity.propUrl);
//        newInstance.show(getChildFragmentManager());
//        newInstance.setOnDismissListener(new BaseRxDialogFragment.DialogDismissListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$kcUvbiEPUn2gXdr_xmrFtC8lG5I
//            @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment.DialogDismissListener
//            public final void onDialogDismiss(BaseRxDialogFragment baseRxDialogFragment) {
//                SLLiveFragment.this.lambda$onUseFragmentSuccess$14$SLLiveFragment(baseRxDialogFragment);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$onUseFragmentSuccess$14$SLLiveFragment(BaseRxDialogFragment baseRxDialogFragment) {
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.updateBackPackCount();
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onBoomStatusSuccess(BoomStatusEntity boomStatusEntity) {
//        LotteryBoomDetailEntity lotteryBoomDetailEntity = boomStatusEntity.rich;
//        LotteryBoomDetailEntity lotteryBoomDetailEntity2 = boomStatusEntity.normal;
//        if (lotteryBoomDetailEntity != null && lotteryBoomDetailEntity.boomStatus > -1) {
//            onLotteryBoomOpen(lotteryBoomDetailEntity.boomPropUrl, lotteryBoomDetailEntity.boomMultiple, lotteryBoomDetailEntity.boomRemainTime, lotteryBoomDetailEntity.boomTotalTime, 20);
//        } else if (lotteryBoomDetailEntity2 == null || lotteryBoomDetailEntity2.boomStatus <= -1) {
//            onLotteryBoomClosed();
//        } else {
//            onLotteryBoomOpen(lotteryBoomDetailEntity2.boomPropUrl, lotteryBoomDetailEntity2.boomMultiple, lotteryBoomDetailEntity2.boomRemainTime, lotteryBoomDetailEntity2.boomTotalTime, 1);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onBoomStatusFail() {
//        onLotteryBoomClosed();
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onPKLiveRoomFPSuccess(boolean z, boolean z2, PKRecordEntity pKRecordEntity) {
//        if (z) {
//            onLinkPKEnd(pKRecordEntity.anchorALiveId, pKRecordEntity.anchorAFP, pKRecordEntity.anchorAPopularity, pKRecordEntity.anchorAAssists, pKRecordEntity.anchorBLiveId, pKRecordEntity.anchorBFP, pKRecordEntity.anchorBPopularity, pKRecordEntity.anchorBAssists, z2);
//        } else {
//            initLinkPKAssistData(pKRecordEntity.anchorALiveId, pKRecordEntity.anchorAFP, pKRecordEntity.anchorAPopularity, pKRecordEntity.anchorAAssists, pKRecordEntity.anchorBLiveId, pKRecordEntity.anchorBFP, pKRecordEntity.anchorBPopularity, pKRecordEntity.anchorBAssists);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onQMInteractShowTaskSuccess(List<QMInteractTaskEntity> list) {
//        this.mLiveAdBannerBottomView.initIntimateTaskList(false, list);
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onGiftBoxListSuccess(List<GiftBoxEntity> list) {
//        if (this.mGiftBoxView != null && !list.isEmpty()) {
//            this.mGiftBoxView.setVisibility(View.VISIBLE);
//            this.mGiftBoxView.showBoxList(list, this.liveId);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.dialog.GuardOpenContentDialog.OnOpenGuardCallbackListener
//    public void OnOpenGuardSuccess(GuardItemEntity guardItemEntity) {
//        this.myPriceBalance = NumberUtils.string2Double(guardItemEntity.getAccountBalance());
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.setUserBalance(this.myPriceBalance);
//        }
//        ((SLLivePresenter) this.mPresenter).getPersonalGuardInfo(this.anchorId);
//    }
//
//    private void sendAdImageRequest() {
//        ((SLLivePresenter) this.mPresenter).getAdImageList("2");
//        ((SLLivePresenter) this.mPresenter).getAdImageList("7");
//        ((SLLivePresenter) this.mPresenter).getAdImageList("3");
//        ((SLLivePresenter) this.mPresenter).getLiveAdNoticeList();
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onLiveAdListSuccess(String str, List<BannerEntity> list) {
//        LivePusherInfoView livePusherInfoView;
//        LiveAdBannerBottomView liveAdBannerBottomView;
//        LivePusherInfoView livePusherInfoView2;
//        AnchorEntity anchorEntity = this.anchorItemEntity;
//        String str2 = anchorEntity == null ? "" : anchorEntity.openId;
//        if (TextUtils.equals(str, "2") && (livePusherInfoView2 = this.mLivePusherInfoView) != null) {
//            livePusherInfoView2.initAdBannerImages(this.anchorAppId, str2, list);
//        }
//        if (TextUtils.equals(str, "7") && (liveAdBannerBottomView = this.mLiveAdBannerBottomView) != null) {
//            liveAdBannerBottomView.initAdBannerImages(this.anchorAppId, str2, list);
//        }
//        if (TextUtils.equals(str, "3") && (livePusherInfoView = this.mLivePusherInfoView) != null) {
//            livePusherInfoView.initVerticalAdImage(this.anchorAppId, str2, list);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onLiveAdNoticeSuccess(BannerEntity bannerEntity) {
//        if (!TextUtils.isEmpty(bannerEntity.content)) {
//            ChatEntity chatEntity = new ChatEntity();
//            chatEntity.setMsgType(10);
//            chatEntity.setMsgText(bannerEntity.content);
//            this.mLiveChatMsgView.addChatMsg(chatEntity);
//        }
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.v4.app.Fragment
//    public void onResume() {
//        super.onResume();
//        resumePlay();
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.onResume();
//        }
//        if (this.livePayEnterView != null && isPayLiveNeedBuyTicket()) {
//            this.livePayEnterView.onResume();
//        }
//        if (TaskBoxUtils.getInstance().isPushInBackground() && this.mTaskBottomDialog != null) {
//            this.mLiveAdBannerBottomView.checkToCountdownForTaskBox();
//            TaskBoxUtils.getInstance().setPushInBackground(false);
//        }
//        if (this.isPayLiveTipsDialog && isPayLiveNeedBuyTicket()) {
//            showDialogFragment(this.payLiveTipsDialog);
//        }
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.v4.app.Fragment
//    public void onPause() {
//        super.onPause();
//        pausePlay();
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.onPause();
//        }
//        if (this.livePayEnterView != null && isPayLiveNeedBuyTicket()) {
//            this.livePayEnterView.onPause();
//        }
//        if (SystemUtils.isApplicationInBackground(this.mContext)) {
//            TaskBoxUtils.getInstance().setPushInBackground(true);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void changeLineReloadLoading(int i) {
//        if (i != -1) {
//            String pullStreamUrl = getPullStreamUrl(i);
//            if (!TextUtils.isEmpty(pullStreamUrl)) {
//                this.pullStreamUrl = pullStreamUrl;
//            }
//        }
//        if (NetUtils.getNetWorkState() == -1) {
//            showToast(getResources().getString(R.string.fq_text_no_network));
//        } else {
//            switchStream();
//        }
//    }
//
//    private String getPullStreamUrl(int i) {
//        try {
//            return this.pullStreamUrlList.get(i);
//        } catch (Exception unused) {
//            return "";
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void switchStream() {
//        if (!isLiving()) {
//            return;
//        }
//        if (this.isRTCStream) {
//            if (isVoiceRoomType()) {
//                stopRTC();
//                initSeatData();
//                updateYYLinkIconView(null);
//            }
//            showLiveLoadingView(4);
//            return;
//        }
//        startPullTimeOut();
//        if (isVideoRoomType()) {
//            showLoadingAnim();
//        }
//        PlayManager playManager = this.playManager;
//        if (playManager != null) {
//            playManager.switchStream(this.pullStreamUrl);
//        }
//    }
//
//    private void resumePlay() {
//        PlayManager playManager;
//        boolean z = !isEnableBackgroundPlay();
//        RTCController rTCController = this.rtcController;
//        if (rTCController != null && this.isRTCStream) {
//            if (this.isLastVoiceStatusOpen) {
//                rTCController.muteLocalAudio(false);
//                ((SLLivePresenter) this.mPresenter).onSendQuietOptionRequest(isVoiceRoomType(), this.liveId, String.valueOf(this.liveCount), 0);
//            }
//            if (z) {
//                this.rtcController.muteAllRemoteVideoAudioStreams(false);
//            }
//        }
//        if (!this.isRTCStream && !isLiveEnd() && (playManager = this.playManager) != null) {
//            if (z) {
//                playManager.resumePlay(this.isPausing);
//            }
//            this.isPausing = false;
//        }
//    }
//
//    private void pausePlay() {
//        PlayManager playManager;
//        boolean z = !isEnableBackgroundPlay();
//        if (this.rtcController != null && this.isRTCStream) {
//            this.isLastVoiceStatusOpen = !SPUtils.getInstance().getBoolean(ConstantUtils.MUTE_KEY);
//            if (this.isLastVoiceStatusOpen) {
//                this.rtcController.muteLocalAudio(true);
//                ((SLLivePresenter) this.mPresenter).onSendQuietOptionRequest(isVoiceRoomType(), this.liveId, String.valueOf(this.liveCount), 1);
//            }
//            if (z) {
//                this.rtcController.muteAllRemoteVideoAudioStreams(true);
//            }
//        }
//        if (!this.isRTCStream && !isLiveEnd() && (playManager = this.playManager) != null) {
//            if (z) {
//                playManager.pausePlay();
//            }
//            this.isPausing = true;
//        }
//    }
//
//    private void stopPlay() {
//        PlayManager playManager = this.playManager;
//        if (playManager != null) {
//            playManager.stopLastPlay();
//        }
//    }
//
//    private void releasePlay() {
//        PlayManager playManager = this.playManager;
//        if (playManager != null) {
//            playManager.releaseLivePlay();
//        }
//    }
//
//    private void onDestroyPlay() {
//        PlayManager playManager = this.playManager;
//        if (playManager != null) {
//            playManager.onDestroyPlay();
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void startPullTimeOut() {
//        cancelPullTimeOut();
//        this.pullTimeOutTimer = Observable.timer(10L, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Consumer() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$PtGLHhIBiPRnSAvVmMTEPwpAJSQ
//            @Override // io.reactivex.functions.Consumer
//            public final void accept(Object obj) throws Exception {
//                SLLiveFragment.this.lambda$startPullTimeOut$15$SLLiveFragment((Long) obj);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$startPullTimeOut$15$SLLiveFragment(Long l) throws Exception {
//        if (this.pullTimeOutTimer != null && this.playManager.getListener() != null) {
//            this.playManager.getListener().onNetError();
//        }
//    }
//
//    private void cancelPullTimeOut() {
//        Disposable disposable = this.pullTimeOutTimer;
//        if (disposable != null && !disposable.isDisposed()) {
//            this.pullTimeOutTimer.dispose();
//            this.pullTimeOutTimer = null;
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void dealPlayError() {
//        cancelPullTimeOut();
//        if (!isLiveEnd()) {
//            this.isAutoGiftDialogFromWeekStar = false;
//            showReloadButton();
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.gift.GiftFrameLayout.BarrageEndAnimationListener
//    public void onEndAnimation(GiftAnimModel giftAnimModel) {
//        if (this.myUserInfoEntity != null && TextUtils.equals(giftAnimModel.getSendUserId(), this.myUserInfoEntity.getUserId())) {
//            if (giftAnimModel.isProp) {
//                this.myPropIndexMap.get(giftAnimModel.getGiftId()).countDownStartTime = System.currentTimeMillis();
//                return;
//            }
//            this.myGiftIndexMap.get(giftAnimModel.getGiftId()).countDownStartTime = System.currentTimeMillis();
//        }
//    }
//
//    private void playBigAnim(GiftItemEntity giftItemEntity) {
//        switch (giftItemEntity.bigAnimType) {
//            case ConstantUtils.BIG_ANIM_GIFT_TYPE /* 2304 */:
//                if (!giftItemEntity.isSendSingleGift()) {
//                    this.liveAnimationView.loadPropAnimation(giftItemEntity.animalUrl);
//                    return;
//                } else if (TextUtils.isEmpty(giftItemEntity.giftDirPath) || !FileUtils.isFile(AppUtils.getLocalGiftFilePath(giftItemEntity.giftDirPath))) {
//                    this.liveAnimationView.loadOnlineGiftAnim(giftItemEntity);
//                    return;
//                } else {
//                    this.liveAnimationView.loadLocalGiftAnim(giftItemEntity);
//                    return;
//                }
//            case ConstantUtils.BIG_ANIM_PROP_TYPE /* 2305 */:
//                this.liveAnimationView.loadPropAnimation(giftItemEntity.animalUrl);
//                return;
//            case ConstantUtils.BIG_ANIM_OPEN_NOBILITY_TYPE /* 2306 */:
//                this.liveAnimationView.loadNobilityOpenBigAnimation(giftItemEntity);
//                return;
//            case ConstantUtils.BIG_ANIM_OPEN_GUARD_TYPE /* 2307 */:
//                this.liveAnimationView.loadGuardOpenBigAnim(giftItemEntity.guardType);
//                return;
//            default:
//                return;
//        }
//    }
//
//    @Override // com.slzhibo.library.websocket.interfaces.BackgroundSocketCallBack
//    public void onBackThreadReceiveBigAnimMsg(GiftItemEntity giftItemEntity) {
//        this.curBigAnimSendUserId = giftItemEntity.userId;
//        playBigAnim(giftItemEntity);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void wsManagerNotifyBigAnim() {
//        this.curBigAnimSendUserId = "";
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.notifyBigAnim();
//        }
//    }
//
//    private void wsManagerNotifyAnim() {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.notifyAnim();
//        }
//    }
//
//    private void initGiftDownloadData() {
//        List<GiftDownloadItemEntity> localDownloadListFilterLuckyGift = GiftDownLoadManager.getInstance().getLocalDownloadListFilterLuckyGift();
//        if (localDownloadListFilterLuckyGift == null || localDownloadListFilterLuckyGift.size() == 0) {
//            this.isGetGiftListFail = true;
//            return;
//        }
//        this.isGetGiftListFail = false;
//        initGiftDialog(localDownloadListFilterLuckyGift);
//    }
//
//    private void initGiftDialog(List<GiftDownloadItemEntity> list) {
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog == null) {
//            this.giftBottomDialog = GiftBackpackDialog.create(isBluetoothConnection(), list, new AnonymousClass30());
//        } else {
//            giftBackpackDialog.updateGiftList(list);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: package-private */
//    /* renamed from: com.slzhibo.library.ui.activity.live.SLLiveFragment$30  reason: invalid class name */
//    /* loaded from: classes3.dex */
//    public class AnonymousClass30 implements GiftBackpackDialog.SendClickListener {
//        AnonymousClass30() {
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onSendCallback(boolean z, boolean z2, BaseGiftBackpackEntity baseGiftBackpackEntity) {
//            if (z2) {
//                if (baseGiftBackpackEntity instanceof GiftDownloadItemEntity) {
//                    SLLiveFragment.this.sendGift((GiftDownloadItemEntity) baseGiftBackpackEntity);
//                }
//            } else if (baseGiftBackpackEntity instanceof BackpackItemEntity) {
//                BackpackItemEntity backpackItemEntity = (BackpackItemEntity) baseGiftBackpackEntity;
//                if (backpackItemEntity.isNobilityTrumpetBoolean()) {
//                    SLLiveFragment.this.isLotteryDialogFlag = false;
//                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                    sLLiveFragment.dismissDialogFragment(sLLiveFragment.giftBottomDialog);
//                    SLLiveFragment.this.showInputMsgDialog(true);
//                } else if (!backpackItemEntity.isPropFragmentBoolean()) {
//                    SLLiveFragment.this.sendProp(backpackItemEntity, z);
//                } else if (NumberUtils.string2int(backpackItemEntity.count) < 50) {
//                    ComposeDialog.newInstance(null, false, null).show(SLLiveFragment.this.getChildFragmentManager());
//                } else {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getFragmentConfig(SLLiveFragment.this.liveId);
//                }
//            }
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onRechargeCallback(View view) {
//            AppUtils.onRechargeListener(( SLLiveFragment.this).mContext);
//            LogEventUtils.uploadRechargeClick(SLLiveFragment.this.getString(R.string.fq_gift_recharge_entrance));
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onScoreCallback(View view) {
//            AppUtils.onScoreListener(( SLLiveFragment.this).mContext);
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onGoToWeekStarList() {
//            SLLiveFragment.this.isAutoGiftDialogFromWeekStar = true;
//            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//            sLLiveFragment.mWeekStarRankingDialog = WeekStarRankingDialog.newInstance(sLLiveFragment.anchorItemEntity, new SimpleUserCardCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.30.1
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//                public void onAnchorItemClickListener(AnchorEntity anchorEntity) {
//                    super.onAnchorItemClickListener(anchorEntity);
//                    SLLiveFragment.this.isAutoGiftDialogFromWeekStar = false;
//                    SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                    sLLiveFragment2.dismissDialogFragment(sLLiveFragment2.mWeekStarRankingDialog);
//                    SLLiveFragment.this.startActivityById(anchorEntity.liveId);
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//                public void onUserItemClickListener(UserEntity userEntity) {
//                    super.onUserItemClickListener(userEntity);
//                    if (!TextUtils.isEmpty(userEntity.getUserId())) {
//                        SLLiveFragment.this.showUserCard(userEntity);
//                    }
//                }
//            });
//            SLLiveFragment.this.mWeekStarRankingDialog.show(SLLiveFragment.this.getChildFragmentManager());
//            if (SLLiveFragment.this.mWeekStarRankingDialog != null) {
//                SLLiveFragment.this.mWeekStarRankingDialog.setOnDismissListener(new BaseRxDialogFragment.DialogDismissListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$30$Tfz3Sm5Gh9DYehz8bZEKRCIZJtI
//                    @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment.DialogDismissListener
//                    public final void onDialogDismiss(BaseRxDialogFragment baseRxDialogFragment) {
//                        SLLiveFragment.AnonymousClass30.this.lambda$onGoToWeekStarList$0$SLLiveFragment$30(baseRxDialogFragment);
//                    }
//                });
//            }
//        }
//
//        public /* synthetic */ void lambda$onGoToWeekStarList$0$SLLiveFragment$30(BaseRxDialogFragment baseRxDialogFragment) {
//            if (SLLiveFragment.this.isAutoGiftDialogFromWeekStar && SLLiveFragment.this.giftBottomDialog != null && !SLLiveFragment.this.giftBottomDialog.isAdded()) {
//                SLLiveFragment.this.giftBottomDialog.show(SLLiveFragment.this.getChildFragmentManager());
//            }
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onOpenNobilityCallback() {
//            SLLiveFragment.this.toNobilityOpenActivity();
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onShowLYControlWindowDialog(GiftDownloadItemEntity giftDownloadItemEntity) {
//            if (!giftDownloadItemEntity.isShakeGiftFlag()) {
//                return;
//            }
//            if (SLLiveFragment.this.isBluetoothConnection()) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.dismissDialogFragment(sLLiveFragment.giftBottomDialog);
//                SLLiveFragment.this.showLYControlWindowViewStub(true);
//                return;
//            }
//            SLLiveFragment.this.showToast(R.string.fq_ly_not_support_bluetooth_tips);
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onShowShakeGiftPlayDescDialog() {
//            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//            sLLiveFragment.lyPlayDescDialog = CommonRuleTipsDialog.newInstance(ConstantUtils.APP_PARAM_SHAKE_GIFT_RULE, sLLiveFragment.getString(R.string.fq_ly_play_desc), true, SLLiveFragment.this.getString(R.string.fq_yx_i_know), 0.57d);
//            SLLiveFragment.this.lyPlayDescDialog.show(SLLiveFragment.this.getChildFragmentManager());
//            SLLiveFragment.this.lyPlayDescDialog.setOnDismissListener(new BaseRxDialogFragment.DialogDismissListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.30.2
//                @Override // com.slzhibo.library.ui.view.dialog.base.BaseRxDialogFragment.DialogDismissListener
//                public void onDialogDismiss(BaseRxDialogFragment baseRxDialogFragment) {
//                    SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                    sLLiveFragment2.showDialogFragment(sLLiveFragment2.giftBottomDialog);
//                }
//            });
//        }
//
//        @Override // com.slzhibo.library.ui.view.dialog.GiftBackpackDialog.SendClickListener
//        public void onThermometerBombCallback() {
//            ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onSendThermometerBombRequest(SLLiveFragment.this.anchorId);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void sendProp(BackpackItemEntity backpackItemEntity, boolean z) {
//        if (!NetUtils.isNetworkAvailable() || isSocketClose()) {
//            showToast(R.string.fq_text_no_network_send_prop);
//        } else if (backpackItemEntity == null) {
//            showToast(R.string.fq_please_choose_prop);
//        } else {
//            GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//            if (giftBackpackDialog != null && giftBackpackDialog.isResumed()) {
//                this.giftBottomDialog.showDownCountAndFlyAnim(z);
//            }
//            WsManager wsManager = this.wsManager;
//            if (wsManager != null) {
//                wsManager.sendPropSendMessage(MessageHelper.convertToPropSend("1", backpackItemEntity.id, this.liveCount));
//            }
//        }
//    }
//
//    private void initTaskDialog() {
//        this.mLiveAdBannerBottomView.setTaskBoxVisibility(8);
//        if (this.mTaskBottomDialog == null) {
//            this.mTaskBottomDialog = TaskBottomDialog.create(new TaskBottomDialog.TaskClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$chQRu992aDpK1bVXlvPoK6y-aaE
//                @Override // com.slzhibo.library.ui.view.dialog.TaskBottomDialog.TaskClickListener
//                public final void onTaskCallback(TaskBoxEntity taskBoxEntity) {
//                    SLLiveFragment.this.lambda$initTaskDialog$16$SLLiveFragment(taskBoxEntity);
//                }
//            });
//        }
//        ((SLLivePresenter) this.mPresenter).getTaskList(false);
//    }
//
//    public /* synthetic */ void lambda$initTaskDialog$16$SLLiveFragment(TaskBoxEntity taskBoxEntity) {
//        if (taskBoxEntity.getStatus() == 1 && !this.isSocketError) {
//            ((SLLivePresenter) this.mPresenter).getTaskTake(taskBoxEntity);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void sendGift(GiftDownloadItemEntity giftDownloadItemEntity) {
//        if (giftDownloadItemEntity != null) {
//            GiftItemEntity formatGiftItemEntity = GiftDownLoadManager.getInstance().formatGiftItemEntity(giftDownloadItemEntity);
//            if (!NetUtils.isNetworkAvailable()) {
//                showToast(R.string.fq_text_no_network_send_gift);
//            } else if (isSocketClose()) {
//                showToast(R.string.fq_text_network_error_send_gift);
//            } else if (formatGiftItemEntity == null) {
//                showToast(R.string.fq_please_choose_gift);
//            } else if (this.getUserBalanceFail) {
//                showToast(R.string.fq_userover_loading_fail);
//                this.giftBottomDialog.setUserBalanceTip(R.string.fq_userover_loading);
//                ((SLLivePresenter) this.mPresenter).getUserOver();
//            } else if (!isSendGift(formatGiftItemEntity)) {
//                dismissDialogFragment(this.giftBottomDialog);
//                if (!formatGiftItemEntity.isScoreGift()) {
//                    AppUtils.onRechargeListener(this.mContext);
//                    LogEventUtils.uploadRechargeClick(getString(R.string.fq_gift_recharge_entrance));
//                } else if (AppUtils.isEnableScore()) {
//                    AppUtils.onScoreListener(this.mContext);
//                } else {
//                    showToast(R.string.fq_score_not_enough_tips);
//                }
//            } else if (!GiftDownLoadManager.getInstance().checkGiftExist(formatGiftItemEntity)) {
//                onGiftResUpdateAndTips(true);
//            } else {
//                GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//                if (giftBackpackDialog != null && giftBackpackDialog.isResumed()) {
//                    this.giftBottomDialog.showDownCountAndFlyAnim(false);
//                }
//                String iPAddress = NetworkUtils.getIPAddress(true);
//                formatGiftItemEntity.userId = this.myUserInfoEntity.getUserId();
//                formatGiftItemEntity.avatar = UserInfoManager.getInstance().getAvatar();
//                formatGiftItemEntity.role = this.myUserInfoEntity.getRole();
//                formatGiftItemEntity.anchorId = this.anchorId;
//                formatGiftItemEntity.anchorName = this.anchorItemEntity.nickname;
//                formatGiftItemEntity.liveId = this.liveId;
//                formatGiftItemEntity.expGrade = this.myUserInfoEntity.getExpGrade();
//                formatGiftItemEntity.liveCount = this.liveCount;
//                if (TextUtils.isEmpty(iPAddress)) {
//                    iPAddress = "172.19.24.10";
//                }
//                formatGiftItemEntity.clientIp = iPAddress;
//                formatGiftItemEntity.guardType = NumberUtils.string2int(this.guardItemEntity.userGuardType);
//                if (formatGiftItemEntity.isScoreGift()) {
//                    this.myScoreBalance -= NumberUtils.mul(this.giftBottomDialog.getGiftNum(), NumberUtils.string2Double(formatGiftItemEntity.price));
//                    if (this.myScoreBalance < 0.0d) {
//                        this.myScoreBalance = 0.0d;
//                    }
//                    this.giftBottomDialog.setUserScore(this.myScoreBalance);
//                } else {
//                    this.myPriceBalance -= NumberUtils.mul(this.giftBottomDialog.getGiftNum(), NumberUtils.string2Double(formatGiftItemEntity.price));
//                    if (this.myPriceBalance < 0.0d) {
//                        this.myPriceBalance = 0.0d;
//                    }
//                    this.giftBottomDialog.setUserBalance(this.myPriceBalance);
//                }
//                WsManager wsManager = this.wsManager;
//                if (wsManager != null) {
//                    wsManager.sendGiftMessage(MessageHelper.convertToGiftMsg(formatGiftItemEntity));
//                }
//            }
//        }
//    }
//
//    private boolean isSendGift(GiftItemEntity giftItemEntity) {
//        int giftNum = this.giftBottomDialog.getGiftNum() == 0 ? 1 : this.giftBottomDialog.getGiftNum();
//        giftItemEntity.isScoreGift();
//        return (giftItemEntity.isScoreGift() ? this.myScoreBalance : this.myPriceBalance) >= NumberUtils.mul((double) giftNum, NumberUtils.string2Double(giftItemEntity.price));
//    }
//
//    private void showGiftPanel() {
//        AnchorEntity anchorEntity = this.anchorItemEntity;
//        LogEventUtils.uploadGiftButtonClick(anchorEntity.openId, anchorEntity.appId, anchorEntity.nickname, this.liveId, this.myUserInfoEntity.expGrade);
//        AnimUtils.playScaleAnim(this.giftButton);
//        if (isCanSendGift() && this.giftBottomDialog != null) {
//            getUserBalance(0);
//            if (!this.giftBottomDialog.isAdded()) {
//                this.isLotteryDialogFlag = false;
//                showDialogFragment(this.giftBottomDialog);
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public boolean isCanSendGift() {
//        if (TextUtils.equals(this.myUserInfoEntity.getUserId(), this.anchorId)) {
//            showToast(R.string.fq_no_send_gift_myself);
//            return false;
//        } else if (this.isGetGiftListFail) {
//            showToast(R.string.fq_gift_loading);
//            if (!this.startGetGiftListInfo) {
//                this.startGetGiftListInfo = true;
//                ((SLLivePresenter) this.mPresenter).getGiftList();
//            }
//            return false;
//        } else if (!this.isGiftListUpdating) {
//            return true;
//        } else {
//            showToast(R.string.fq_gift_res_update);
//            return false;
//        }
//    }
//
//    /* JADX WARN: Removed duplicated region for block: B:16:0x006b  */
//    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
//    /* JADX WARN: Removed duplicated region for block: B:26:0x00f7  */
//    /* JADX WARN: Removed duplicated region for block: B:30:0x0109  */
//    /*
//        Code decompiled incorrectly, please refer to instructions dump.
//    */
//    private void playMySelfAnimOnMainThread(SocketMessageEvent.ResultData resultData) {
//        boolean z;
//        GiftIndexEntity giftIndexEntity;
//        GiftItemEntity giftItemEntity = GiftDownLoadManager.getInstance().getGiftItemEntity(resultData.markId);
//        if (giftItemEntity == null) {
//            onGiftResUpdateAndTips(true);
//            return;
//        }
//        boolean z2 = !TextUtils.equals(resultData.giftNum, "1");
//        giftItemEntity.onlineDefaultUrl = giftItemEntity.animalUrl;
//        if (z2) {
//            GiftBatchItemEntity giftBatchByNum = giftItemEntity.getGiftBatchByNum(resultData.giftNum);
//            giftItemEntity.markId = MD5Utils.getMd5(resultData.userId + resultData.markId + resultData.giftNum);
//            if (giftBatchByNum != null) {
//                giftItemEntity.animalUrl = giftBatchByNum.animalUrl;
//                giftItemEntity.active_time = giftBatchByNum.active_time;
//                giftItemEntity.duration = giftBatchByNum.duration;
//                if (!TextUtils.isEmpty(giftItemEntity.animalUrl)) {
//                    z = true;
//                    giftIndexEntity = this.myGiftIndexMap.get(giftItemEntity.markId);
//                    if (giftIndexEntity != null) {
//                        giftIndexEntity = new GiftIndexEntity();
//                        giftIndexEntity.sendIndex++;
//                        this.isContinueCombo = false;
//                        this.myGiftIndexMap.put(giftItemEntity.markId, giftIndexEntity);
//                    } else if (giftIndexEntity.countDownStartTime == 0) {
//                        giftIndexEntity.sendIndex++;
//                        this.isContinueCombo = false;
//                    } else if (System.currentTimeMillis() - giftIndexEntity.countDownStartTime > NumberUtils.formatMillisecond(giftItemEntity.active_time)) {
//                        giftIndexEntity.sendIndex = 1;
//                        giftIndexEntity.countDownStartTime = 0L;
//                        this.isContinueCombo = false;
//                    } else {
//                        giftIndexEntity.sendIndex++;
//                        this.isContinueCombo = true;
//                        giftIndexEntity.countDownStartTime = 0L;
//                    }
//                    giftItemEntity.sendUserName = resultData.userName;
//                    giftItemEntity.userId = resultData.userId;
//                    giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
//                    giftItemEntity.avatar = resultData.avatar;
//                    giftItemEntity.role = resultData.role;
//                    giftItemEntity.userRole = resultData.userRole;
//                    giftItemEntity.sex = resultData.sex;
//                    giftItemEntity.expGrade = AppUtils.formatExpGrade(resultData.expGrade);
//                    giftItemEntity.guardType = NumberUtils.string2int(resultData.guardType);
//                    giftItemEntity.nobilityType = resultData.nobilityType;
//                    giftItemEntity.weekStar = resultData.isWeekStar;
//                    giftItemEntity.openId = resultData.openId;
//                    giftItemEntity.appId = resultData.appId;
//                    giftItemEntity.giftNum = resultData.giftNum;
//                    giftItemEntity.marks = resultData.markUrls;
//                    if (!z2) {
//                        if (z) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$6Y-pLGlzmdK1BxmkDDYIInH-VYQ
//                                @Override // java.lang.Runnable
//                                public final void run() {
//                                    SLLiveFragment.this.lambda$playMySelfAnimOnMainThread$17$SLLiveFragment();
//                                }
//                            });
//                            WsManager wsManager = this.wsManager;
//                            if (wsManager != null) {
//                                wsManager.addLocalAnim(giftItemEntity);
//                            }
//                        }
//                    } else if (giftItemEntity.isBigAnim()) {
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$lWDatU67mp3LZyE4_yHQ93o_ego
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$playMySelfAnimOnMainThread$18$SLLiveFragment();
//                            }
//                        });
//                        WsManager wsManager2 = this.wsManager;
//                        if (wsManager2 != null) {
//                            wsManager2.addLocalAnim(giftItemEntity);
//                        }
//                    }
//                    playMySelfAnimGift(giftItemEntity);
//                    showSelfGiftMsgOnChatList(giftItemEntity);
//                    LogEventUtils.uploadSendGift(this.anchorItemEntity, giftItemEntity, resultData.giftNum, this.liveId, this.myUserInfoEntity.expGrade);
//                    updateGiftThermometerScale(giftItemEntity, resultData);
//                }
//            }
//        }
//        z = false;
//        giftIndexEntity = this.myGiftIndexMap.get(giftItemEntity.markId);
//        if (giftIndexEntity != null) {
//        }
//        giftItemEntity.sendUserName = resultData.userName;
//        giftItemEntity.userId = resultData.userId;
//        giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
//        giftItemEntity.avatar = resultData.avatar;
//        giftItemEntity.role = resultData.role;
//        giftItemEntity.userRole = resultData.userRole;
//        giftItemEntity.sex = resultData.sex;
//        giftItemEntity.expGrade = AppUtils.formatExpGrade(resultData.expGrade);
//        giftItemEntity.guardType = NumberUtils.string2int(resultData.guardType);
//        giftItemEntity.nobilityType = resultData.nobilityType;
//        giftItemEntity.weekStar = resultData.isWeekStar;
//        giftItemEntity.openId = resultData.openId;
//        giftItemEntity.appId = resultData.appId;
//        giftItemEntity.giftNum = resultData.giftNum;
//        giftItemEntity.marks = resultData.markUrls;
//        if (!z2) {
//        }
//        playMySelfAnimGift(giftItemEntity);
//        showSelfGiftMsgOnChatList(giftItemEntity);
//        LogEventUtils.uploadSendGift(this.anchorItemEntity, giftItemEntity, resultData.giftNum, this.liveId, this.myUserInfoEntity.expGrade);
//        updateGiftThermometerScale(giftItemEntity, resultData);
//    }
//
//    private void playReceiveAnimGift(final GiftItemEntity giftItemEntity) {
//        if (giftItemEntity != null) {
//            final GiftAnimModel giftAnimModel = new GiftAnimModel();
//            giftAnimModel.setGiftId(giftItemEntity.markId);
//            giftAnimModel.setProp(giftItemEntity.isPropBigAnimType());
//            giftAnimModel.setOnLineUrl(giftItemEntity.animalUrl);
//            giftAnimModel.setOnlineDefaultUrl(giftItemEntity.onlineDefaultUrl);
//            giftAnimModel.setEffectType(String.valueOf(giftItemEntity.effect_type));
//            giftAnimModel.setGiftName(giftItemEntity.name);
//            giftAnimModel.setGiftCount(1);
//            giftAnimModel.setGiftPic(giftItemEntity.imgurl);
//            giftAnimModel.setGiftPrice(giftItemEntity.price);
//            giftAnimModel.setSendGiftTime(Long.valueOf(System.currentTimeMillis()));
//            giftAnimModel.setCurrentStart(true);
//            giftAnimModel.setGiftDirPath(giftItemEntity.giftDirPath);
//            giftAnimModel.setGiftShowTime(NumberUtils.formatMillisecond(giftItemEntity.duration));
//            giftAnimModel.setSendIndex(giftItemEntity.sendIndex);
//            giftAnimModel.setAnimationListener(this);
//            giftAnimModel.setGiftNum(giftItemEntity.giftNum);
//            giftAnimModel.setSendUserId(giftItemEntity.userId);
//            giftAnimModel.setSendUserName(giftItemEntity.sendUserName);
//            giftAnimModel.setSendUserExpGrade(giftItemEntity.expGrade);
//            giftAnimModel.setSendUserGuardType(giftItemEntity.guardType);
//            giftAnimModel.setSendUserNobilityType(giftItemEntity.nobilityType);
//            giftAnimModel.setSendUserRole(giftItemEntity.userRole);
//            giftAnimModel.setSendRole(giftItemEntity.role);
//            giftAnimModel.setSendUserSex(giftItemEntity.sex);
//            giftAnimModel.setAppId(giftItemEntity.appId);
//            giftAnimModel.setOpenId(giftItemEntity.openId);
//            giftAnimModel.setSendUserAvatar(giftItemEntity.avatar);
//            giftAnimModel.setJumpCombo(giftItemEntity.sendIndex);
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$x2mAlTTJQ7yScSbvhfki1iZfY_Q
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$playReceiveAnimGift$19$SLLiveFragment(giftAnimModel, giftItemEntity);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$playReceiveAnimGift$19$SLLiveFragment(GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
//        this.liveAnimationView.loadReceiveGift(giftAnimModel, giftItemEntity);
//    }
//
//    private void playReceiveAnimOnMainThread(SocketMessageEvent.ResultData resultData) {
//        WsManager wsManager;
//        long currentTimeMillis = System.currentTimeMillis();
//        GiftItemEntity giftItemEntity = GiftDownLoadManager.getInstance().getGiftItemEntity(resultData.markId);
//        if (giftItemEntity == null) {
//            onGiftResUpdateAndTips(true);
//            return;
//        }
//        giftItemEntity.onlineDefaultUrl = giftItemEntity.animalUrl;
//        boolean z = false;
//        boolean z2 = !TextUtils.equals(resultData.giftNum, "1");
//        if (z2) {
//            GiftBatchItemEntity giftBatchByNum = giftItemEntity.getGiftBatchByNum(resultData.giftNum);
//            giftItemEntity.markId = MD5Utils.getMd5(resultData.userId + resultData.markId + resultData.giftNum);
//            if (giftBatchByNum != null) {
//                giftItemEntity.animalUrl = giftBatchByNum.animalUrl;
//                giftItemEntity.active_time = giftBatchByNum.active_time;
//                giftItemEntity.duration = giftBatchByNum.duration;
//                if (!TextUtils.isEmpty(giftItemEntity.animalUrl)) {
//                    z = true;
//                }
//            }
//        }
//        if (!this.receiveGiftMap.containsKey(resultData.userId)) {
//            HashMap hashMap = new HashMap(8);
//            GiftIndexEntity giftIndexEntity = new GiftIndexEntity();
//            giftIndexEntity.createTime = currentTimeMillis;
//            giftIndexEntity.sendIndex = 1;
//            hashMap.put(giftItemEntity.markId, giftIndexEntity);
//            this.receiveGiftMap.put(resultData.userId, hashMap);
//            giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
//        } else {
//            Map<String, GiftIndexEntity> map = this.receiveGiftMap.get(resultData.userId);
//            GiftIndexEntity giftIndexEntity2 = map.get(giftItemEntity.markId);
//            if (giftIndexEntity2 == null) {
//                giftIndexEntity2 = new GiftIndexEntity();
//                giftIndexEntity2.createTime = currentTimeMillis;
//                giftIndexEntity2.sendIndex = 1;
//                map.put(giftItemEntity.markId, giftIndexEntity2);
//            } else {
//                if (currentTimeMillis - giftIndexEntity2.createTime < NumberUtils.formatMillisecond(giftItemEntity.active_time + giftItemEntity.duration)) {
//                    giftIndexEntity2.sendIndex++;
//                } else {
//                    giftIndexEntity2.sendIndex = 1;
//                }
//                giftIndexEntity2.createTime = currentTimeMillis;
//            }
//            giftItemEntity.sendIndex = giftIndexEntity2.sendIndex;
//        }
//        giftItemEntity.sendUserName = resultData.userName;
//        giftItemEntity.userId = resultData.userId;
//        giftItemEntity.avatar = resultData.avatar;
//        giftItemEntity.role = resultData.role;
//        giftItemEntity.userRole = resultData.userRole;
//        giftItemEntity.expGrade = AppUtils.formatExpGrade(resultData.expGrade);
//        giftItemEntity.sex = resultData.sex;
//        giftItemEntity.nobilityType = resultData.nobilityType;
//        giftItemEntity.guardType = NumberUtils.string2int(resultData.guardType);
//        giftItemEntity.openId = resultData.openId;
//        giftItemEntity.appId = resultData.appId;
//        giftItemEntity.giftNum = resultData.giftNum;
//        if (z2) {
//            if (z && (wsManager = this.wsManager) != null) {
//                wsManager.addReceiveBigAnim(giftItemEntity);
//            }
//            if (giftItemEntity.sendIndex == 0) {
//                giftItemEntity.sendIndex = 1;
//            }
//        } else if (giftItemEntity.isBigAnim()) {
//            WsManager wsManager2 = this.wsManager;
//            if (wsManager2 != null) {
//                wsManager2.addReceiveBigAnim(giftItemEntity);
//            }
//            if (giftItemEntity.sendIndex == 0) {
//                giftItemEntity.sendIndex = 1;
//            }
//        }
//        if (NumberUtils.string2int(resultData.giftNum) > 1) {
//            showReceiveMsgOnChatList(resultData, AppUtils.appendBatchGiftString(giftItemEntity), 1);
//        } else if (giftItemEntity.isBigAnim()) {
//            showReceiveMsgOnChatList(resultData, AppUtils.appendGiftStringWithIndex(giftItemEntity), 1);
//        } else {
//            int i = giftItemEntity.sendIndex;
//            if (i == 1) {
//                showReceiveMsgOnChatList(resultData, AppUtils.appendGiftStringNoIndex(giftItemEntity), 1);
//            } else if (i != 0 && i % 10 == 0) {
//                showReceiveMsgOnChatList(resultData, AppUtils.appendGiftStringWithIndex(giftItemEntity), 1);
//            }
//        }
//        playReceiveAnimGift(giftItemEntity);
//        wsManagerNotifyAnim();
//    }
//
//    private void playMySelfAnimGift(final GiftItemEntity giftItemEntity) {
//        if (giftItemEntity != null) {
//            final GiftAnimModel giftAnimModel = new GiftAnimModel();
//            giftAnimModel.setGiftId(giftItemEntity.markId);
//            giftAnimModel.setProp(giftItemEntity.isPropBigAnimType());
//            giftAnimModel.setOnLineUrl(giftItemEntity.animalUrl);
//            giftAnimModel.setOnlineDefaultUrl(giftItemEntity.onlineDefaultUrl);
//            giftAnimModel.setEffectType(String.valueOf(giftItemEntity.effect_type));
//            giftAnimModel.setGiftName(giftItemEntity.name);
//            giftAnimModel.setGiftCount(1);
//            giftAnimModel.setGiftPic(giftItemEntity.imgurl);
//            giftAnimModel.setGiftPrice(giftItemEntity.price);
//            giftAnimModel.setSendGiftTime(Long.valueOf(System.currentTimeMillis()));
//            giftAnimModel.setCurrentStart(true);
//            giftAnimModel.setGiftDirPath(giftItemEntity.giftDirPath);
//            giftAnimModel.setGiftShowTime(NumberUtils.formatMillisecond(giftItemEntity.duration));
//            giftAnimModel.setSendIndex(giftItemEntity.sendIndex);
//            giftAnimModel.setAnimationListener(this);
//            giftAnimModel.setGiftNum(giftItemEntity.giftNum);
//            giftAnimModel.setSendUserId(this.myUserInfoEntity.getUserId());
//            giftAnimModel.setSendUserName(UserInfoManager.getInstance().getUserNickname());
//            giftAnimModel.setSendUserExpGrade(giftItemEntity.expGrade);
//            giftAnimModel.setSendUserGuardType(giftItemEntity.guardType);
//            giftAnimModel.setSendUserNobilityType(giftItemEntity.nobilityType);
//            giftAnimModel.setSendUserRole(giftItemEntity.userRole);
//            giftAnimModel.setSendRole(giftItemEntity.role);
//            giftAnimModel.setSendUserSex(giftItemEntity.sex);
//            giftAnimModel.setAppId(giftItemEntity.appId);
//            giftAnimModel.setOpenId(giftItemEntity.openId);
//            giftAnimModel.setSendUserAvatar(giftItemEntity.avatar);
//            if (this.isContinueCombo) {
//                giftAnimModel.setJumpCombo(giftItemEntity.sendIndex);
//            } else {
//                giftAnimModel.setJumpCombo(0);
//            }
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$er5GBYn-BjsSf1SSZ0dTv0_wNWw
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$playMySelfAnimGift$20$SLLiveFragment(giftAnimModel, giftItemEntity);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$playMySelfAnimGift$20$SLLiveFragment(GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
//        this.liveAnimationView.loadGift(giftAnimModel, giftItemEntity);
//    }
//
//    private boolean isSocketClose() {
//        return this.isSocketClose || this.isSocketError;
//    }
//
//    private void initSocket() {
//        if (this.wsManager == null) {
//            addSocketTipMsg(R.string.fq_start_connect_socket);
//            this.wsManager = WsManager.getInstance();
//            this.wsManager.init(this, this.socketUrl, AppUtils.getSocketHeartBeatInterval());
//            this.wsManager.setOnWebSocketListener(new AnonymousClass31());
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: package-private */
//    /* renamed from: com.slzhibo.library.ui.activity.live.SLLiveFragment$31  reason: invalid class name */
//    /* loaded from: classes3.dex */
//    public class AnonymousClass31 implements OnWebSocketStatusListener {
//        AnonymousClass31() {
//        }
//
//        @Override // com.slzhibo.library.websocket.interfaces.OnWebSocketStatusListener
//        public void onOpen(boolean z) {
//            if (SLLiveFragment.this.isTaskSocket) {
//                SLLiveFragment.this.isTaskSocket = false;
//            }
//            SLLiveFragment.this.isSocketReConn = z;
//            SLLiveFragment.this.isConnectingChatService = false;
//            SLLiveFragment.this.reConnectCountOver = false;
//            if (z) {
//                SLLiveFragment.this.addSocketTipMsg(R.string.fq_reconnect_suc);
//                SLLiveFragment.this.sendLiveInitInfoRequest();
//                SLLiveFragment.this.handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$31$hfn5_oJDxkTYzW_6Vfx8vE8vTis
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.AnonymousClass31.this.lambda$onOpen$0$SLLiveFragment$31();
//                    }
//                });
//            } else {
//                SLLiveFragment.this.addSocketTipMsg(R.string.fq_connect_suc);
//                if (AppUtils.isConsumptionPermissionUser() && SLLiveFragment.this.myselfEnterMessageEvent != null) {
//                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                    sLLiveFragment.onBackThreadReceiveMessage(sLLiveFragment.myselfEnterMessageEvent);
//                }
//                if (SLLiveFragment.this.isBluetoothConnection()) {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).sendBluetoothCountdownRequest(SLLiveFragment.this.liveId, new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.31.1
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onError(int i, String str) {
//                        }
//
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onSuccess(Object obj) {
//                            if (SLLiveFragment.this.lyControlWindowView == null) {
//                                SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                                sLLiveFragment2.lyControlWindowView = (LYControlWindowView) sLLiveFragment2.lyControlWindowViewStub.inflate();
//                            }
//                            SLLiveFragment.this.lyControlWindowView.startCountdown();
//                        }
//                    });
//                }
//            }
//            if (SLLiveFragment.this.myUserInfoEntity != null && SLLiveFragment.this.myUserInfoEntity.isOfflinePrivateMsg()) {
//                ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onSendOfflinePrivateMsg();
//            }
//            SLLiveFragment.this.isSocketClose = false;
//            SLLiveFragment.this.isSocketError = false;
//            SLLiveFragment.this.handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$31$0_7KGyiXm-DYZJTDYoguCHMW__U
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.AnonymousClass31.this.lambda$onOpen$1$SLLiveFragment$31();
//                }
//            });
//        }
//
//        public /* synthetic */ void lambda$onOpen$0$SLLiveFragment$31() {
//            SLLiveFragment.this.changePrivateMessageNetStatus(true);
//        }
//
//        public /* synthetic */ void lambda$onOpen$1$SLLiveFragment$31() {
//            SLLiveFragment.this.tvInput.setText(R.string.fq_talk_something);
//        }
//
//        @Override // com.slzhibo.library.websocket.interfaces.OnWebSocketStatusListener
//        public void onClose() {
//            SLLiveFragment.this.isSocketClose = true;
//        }
//
//        @Override // com.slzhibo.library.websocket.interfaces.OnWebSocketStatusListener
//        public void onError(boolean z, String str) {
//            if (str == null || !str.contains("HTTP/1.1 403")) {
//                SLLiveFragment.this.isSocketError = true;
//                SLLiveFragment.this.addSocketTipMsg(R.string.fq_connect_fail);
//                return;
//            }
//            SLLiveFragment.this.startTokenDialogService();
//            SLLiveFragment.this.onFinishActivity();
//        }
//
//        @Override // com.slzhibo.library.websocket.interfaces.OnWebSocketStatusListener
//        public void reConnecting() {
//            SLLiveFragment.this.isConnectingChatService = true;
//            SLLiveFragment.this.addSocketTipMsg(R.string.fq_start_reconnect_socket);
//            SLLiveFragment.this.handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$31$-pbJ-ZdfjuL04nPfHEoqo5FNzRk
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.AnonymousClass31.this.lambda$reConnecting$2$SLLiveFragment$31();
//                }
//            });
//            ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getWebSocketAddress(SLLiveFragment.this.liveId, "2");
//        }
//
//        public /* synthetic */ void lambda$reConnecting$2$SLLiveFragment$31() {
//            SLLiveFragment.this.tvInput.setText(R.string.fq_reconnect_socket_input_title);
//        }
//
//        @Override // com.slzhibo.library.websocket.interfaces.OnWebSocketStatusListener
//        public void reConnectCountOver() {
//            SLLiveFragment.this.isConnectingChatService = false;
//            SLLiveFragment.this.reConnectCountOver = true;
//            SLLiveFragment.this.handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$31$jYFKsHkawHbBzzDfq1cRTLHPuBs
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.AnonymousClass31.this.lambda$reConnectCountOver$3$SLLiveFragment$31();
//                }
//            });
//        }
//
//        public /* synthetic */ void lambda$reConnectCountOver$3$SLLiveFragment$31() {
//            SLLiveFragment.this.tvInput.setText(R.string.fq_click_reconnect);
//            SLLiveFragment.this.changePrivateMessageNetStatus(false);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onWebSocketAddressSuccess(WSAddressEntity wSAddressEntity) {
//        this.socketUrl = AppUtils.formatLiveSocketUrl(wSAddressEntity.wsAddress, this.liveId, this.liveCount, this.myUserInfoEntity.getUserId(), "2", wSAddressEntity.k);
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.reconnect(this.socketUrl);
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.iview.ISLLiveView
//    public void onWebSocketAddressFail() {
//        addSocketTipMsg(R.string.fq_connect_fail);
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.resetCount();
//            this.wsManager.setStatus(WsStatus.CONNECT_FAIL);
//            this.isConnectingChatService = false;
//            this.reConnectCountOver = true;
//            TextView textView = this.tvInput;
//            if (textView != null) {
//                textView.setText(R.string.fq_click_reconnect);
//            }
//        }
//        changePrivateMessageNetStatus(false);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void addSocketTipMsg(@StringRes int i) {
//        final ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgText(getString(i));
//        chatEntity.setMsgType(7);
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$M1hS4WYcRZB2jHBbe5IRT1kuPUc
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$addSocketTipMsg$21$SLLiveFragment(chatEntity);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$addSocketTipMsg$21$SLLiveFragment(ChatEntity chatEntity) {
//        this.mLiveChatMsgView.addChatMsg(chatEntity);
//    }
//
//    private void stopSocket() {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.stopService();
//            this.wsManager = null;
//        }
//    }
//
//    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
//    /* JADX WARN: Code restructure failed: missing block: B:357:0x06f0, code lost:
//        if (r1.equals("1") != false) goto L_0x06f4;
//     */
//    /* JADX WARN: Code restructure failed: missing block: B:377:0x076c, code lost:
//        if (r1.equals("recommend") != false) goto L_0x0786;
//     */
//    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
//    /* JADX WARN: Removed duplicated region for block: B:18:0x0050  */
//    /* JADX WARN: Removed duplicated region for block: B:360:0x06f6  */
//    /* JADX WARN: Removed duplicated region for block: B:364:0x0711  */
//    @Override // com.slzhibo.library.websocket.interfaces.BackgroundSocketCallBack
//    /*
//        Code decompiled incorrectly, please refer to instructions dump.
//    */
//    public void onBackThreadReceiveMessage(SocketMessageEvent socketMessageEvent) {
//        char c;
//        char c2;
//        int i = 0;
//        if (!AppUtils.isSocketEventSuc(socketMessageEvent.code)) {
//            showToast(socketMessageEvent.message);
//            String str = socketMessageEvent.code;
//            int hashCode = str.hashCode();
//            if (hashCode != 1445) {
//                if (hashCode == 1477268995 && str.equals(ConstantUtils.GIFT_NEED_UPDATE)) {
//                    c2 = 0;
//                    if (c2 != 0) {
//                        onGiftResUpdateAndTips(false);
//                        return;
//                    } else if (c2 == 1) {
//                        this.isNormalBan = true;
//                        this.banPostTimeLeft = socketMessageEvent.resultData.remainTime;
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$YQ3kN3WT2U-_3htRYS2fnGWyqeU
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$22$SLLiveFragment();
//                            }
//                        });
//                        return;
//                    } else {
//                        return;
//                    }
//                }
//                c2 = 65535;
//                if (c2 != 0) {
//                }
//            } else {
//                if (str.equals("-2")) {
//                    c2 = 1;
//                    if (c2 != 0) {
//                    }
//                }
//                c2 = 65535;
//                if (c2 != 0) {
//                }
//            }
//        } else {
//            final SocketMessageEvent.ResultData resultData = socketMessageEvent.resultData;
//            if (resultData != null && !this.mActivity.isFinishing() && this.mainHandler != null && this.workHandler != null) {
//                String str2 = socketMessageEvent.messageType;
//                switch (str2.hashCode()) {
//                    case -1981791680:
//                        if (str2.equals("msgBroadcast")) {
//                            c = 28;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1603387347:
//                        if (str2.equals("openNobilityBroadcast")) {
//                            c = 20;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1598856750:
//                        if (str2.equals("banPostAll")) {
//                            c = '\b';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1483460454:
//                        if (str2.equals("convertPaidRoom")) {
//                            c = ')';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1469736309:
//                        if (str2.equals("audioSeatChange")) {
//                            c = '9';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1330790228:
//                        if (str2.equals("intimateTaskAccept")) {
//                            c = '-';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1302490523:
//                        if (str2.equals("consumeNotify")) {
//                            c = 22;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1268961704:
//                        if (str2.equals("intimateTaskCharge")) {
//                            c = '0';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1256385881:
//                        if (str2.equals("tokenInvalidNotify")) {
//                            c = 15;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1244355024:
//                        if (str2.equals("lianmaiEnd")) {
//                            c = '\"';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1242004544:
//                        if (str2.equals("chatReceipt")) {
//                            c = 29;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1088127726:
//                        if (str2.equals("audioDisconnect")) {
//                            c = '4';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1052404422:
//                        if (str2.equals("updatePaidRoomPullStreamUrl")) {
//                            c = '*';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1039689911:
//                        if (str2.equals("notify")) {
//                            c = 11;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1033917736:
//                        if (str2.equals("lianmaiSuccess")) {
//                            c = 31;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -1000581427:
//                        if (str2.equals("audioSeatStatus")) {
//                            c = ':';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -993690229:
//                        if (str2.equals("propSend")) {
//                            c = 26;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -992867598:
//                        if (str2.equals("grabGiftBoxBroadcast")) {
//                            c = 25;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -941691210:
//                        if (str2.equals("universalBroadcast")) {
//                            c = 19;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -939529900:
//                        if (str2.equals("userBluetoothDetail")) {
//                            c = 'G';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -842142792:
//                        if (str2.equals("intimateTaskRefuse")) {
//                            c = '.';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -635004876:
//                        if (str2.equals("audioConnect")) {
//                            c = '2';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -634778976:
//                        if (str2.equals("forbidLive")) {
//                            c = '\r';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -633171513:
//                        if (str2.equals("audioEnterSeat")) {
//                            c = '=';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -566357442:
//                        if (str2.equals("buyLiveTicket")) {
//                            c = 30;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -535278809:
//                        if (str2.equals("pkStart")) {
//                            c = ' ';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -476274530:
//                        if (str2.equals("anchorBluetoothTask")) {
//                            c = 'F';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -370196576:
//                        if (str2.equals("generalFlutterScreen")) {
//                            c = 21;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -355282718:
//                        if (str2.equals("liveDrawFinished")) {
//                            c = '\'';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -339185956:
//                        if (str2.equals("balance")) {
//                            c = 3;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -337843889:
//                        if (str2.equals("banPost")) {
//                            c = 7;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -236148015:
//                        if (str2.equals("liveControl")) {
//                            c = '\f';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -234370210:
//                        if (str2.equals("audioCancelApply")) {
//                            c = '7';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -148968596:
//                        if (str2.equals("turntableStatusUpdate")) {
//                            c = 27;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -98444021:
//                        if (str2.equals("buyShelfProduct")) {
//                            c = 'D';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case -21216891:
//                        if (str2.equals("postInterval")) {
//                            c = 4;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 3052376:
//                        if (str2.equals("chat")) {
//                            c = 2;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 3172656:
//                        if (str2.equals("gift")) {
//                            c = 0;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 16548271:
//                        if (str2.equals("userPrivateMessage")) {
//                            c = '(';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 96667762:
//                        if (str2.equals("entry")) {
//                            c = 1;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 98509126:
//                        if (str2.equals("goOut")) {
//                            c = '\n';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 99368259:
//                        if (str2.equals("liveAdminGoOut")) {
//                            c = '\t';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 102846135:
//                        if (str2.equals(ConstantUtils.LEAVE_TYPE)) {
//                            c = 5;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 106691808:
//                        if (str2.equals("pkEnd")) {
//                            c = '$';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 132753966:
//                        if (str2.equals("firstKill")) {
//                            c = '#';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 154838451:
//                        if (str2.equals("endExplainProduct")) {
//                            c = 'C';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 188071978:
//                        if (str2.equals("audioPlay")) {
//                            c = '<';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 317295308:
//                        if (str2.equals("userGrade")) {
//                            c = 16;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 395254178:
//                        if (str2.equals("nobilityTrumpetBroadcast")) {
//                            c = 18;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 441119852:
//                        if (str2.equals("putGiftBox")) {
//                            c = 23;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 441614241:
//                        if (str2.equals("intimateTaskShow")) {
//                            c = ',';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 443530333:
//                        if (str2.equals("audioConnectInvite")) {
//                            c = '5';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 487782924:
//                        if (str2.equals("liveAdminBanPost")) {
//                            c = 6;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 692415848:
//                        if (str2.equals("audioConnectRefuse")) {
//                            c = '6';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 720694193:
//                        if (str2.equals("intimateTaskChargeComplete")) {
//                            c = '1';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 798249924:
//                        if (str2.equals("liveSetting")) {
//                            c = 14;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 805483582:
//                        if (str2.equals("intimateTaskStart")) {
//                            c = '+';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 842238472:
//                        if (str2.equals("hideShelf")) {
//                            c = '@';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1225787890:
//                        if (str2.equals("liveDrawStart")) {
//                            c = '&';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1253899162:
//                        if (str2.equals("audioConnectApply")) {
//                            c = '>';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1316826486:
//                        if (str2.equals("intimateTaskBidFailed")) {
//                            c = '/';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1332305199:
//                        if (str2.equals("audioConnectSuccess")) {
//                            c = '3';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1374344608:
//                        if (str2.equals("assistKing")) {
//                            c = '%';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1453750945:
//                        if (str2.equals("updateGameRecommend")) {
//                            c = '?';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1536462846:
//                        if (str2.equals("audioQuiet")) {
//                            c = '8';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1585377747:
//                        if (str2.equals("notifyFP")) {
//                            c = '!';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1603829384:
//                        if (str2.equals("displayShelf")) {
//                            c = 'A';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1614129402:
//                        if (str2.equals("startExplainProduct")) {
//                            c = 'B';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1680327801:
//                        if (str2.equals("giftTrumpet")) {
//                            c = 17;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 1702862769:
//                        if (str2.equals("anchorBluetoothNotice")) {
//                            c = 'E';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 2021199175:
//                        if (str2.equals("grabGiftBoxNotified")) {
//                            c = 24;
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    case 2095840248:
//                        if (str2.equals("audioUserLike")) {
//                            c = ';';
//                            break;
//                        }
//                        c = 65535;
//                        break;
//                    default:
//                        c = 65535;
//                        break;
//                }
//                switch (c) {
//                    case 0:
//                        if (!resultData.isQMTaskGift() || NumberUtils.string2int(resultData.giftNum) <= 1) {
//                            dealGiftMsgFormSocket(resultData);
//                            return;
//                        }
//                        int string2int = NumberUtils.string2int(resultData.giftNum);
//                        resultData.giftNum = "1";
//                        while (i < string2int) {
//                            dealGiftMsgFormSocket(resultData);
//                            i++;
//                        }
//                        return;
//                    case 1:
//                        dealEnterMsgFromSocket(resultData);
//                        return;
//                    case 2:
//                        if (resultData.isPrivateMsg()) {
//                            resultData.sysNoticeType = "chat";
//                            dealPrivateMsgFromSocket(resultData);
//                            return;
//                        }
//                        dealChatMsgFromSocket(resultData);
//                        return;
//                    case 3:
//                        dealUserBalanceMsgFromSocket(resultData);
//                        return;
//                    case 4:
//                        int string2int2 = NumberUtils.string2int(resultData.postIntervalTimes);
//                        if (string2int2 >= 0) {
//                            this.postIntervalTimes = string2int2;
//                            return;
//                        }
//                        return;
//                    case 5:
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$gmjTF8shn3NZtqhRCasg_NjM8iw
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$23$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case 6:
//                        dealSuperBanPostMsgFromSocket(resultData);
//                        return;
//                    case 7:
//                        dealBanPostMsgFromSocket(resultData);
//                        return;
//                    case '\b':
//                        dealBannedAllPostMsgFormSocket(resultData);
//                        return;
//                    case '\t':
//                    case '\n':
//                        dealKickOutMsgFromSocket(resultData);
//                        return;
//                    case 11:
//                        dealNotifyMsgFromSocket(resultData);
//                        return;
//                    case '\f':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$hLok2xWpfjd7C6N13JYe2pt647I
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$24$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case '\r':
//                        return;
//                    case 14:
//                        dealLiveSettingMsgFromSocket(resultData);
//                        return;
//                    case 15:
//                        startTokenDialogService();
//                        onFinishActivity();
//                        return;
//                    case 16:
//                        if (TextUtils.equals(resultData.userId, this.myUserInfoEntity.getUserId())) {
//                            this.myUserInfoEntity.setExpGrade(resultData.afterGrade);
//                            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//                            if (inputTextMsgForAudienceDialog != null) {
//                                inputTextMsgForAudienceDialog.setMyUserGrade(this.myUserInfoEntity.getExpGrade());
//                            }
//                        }
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$HkZcu_yv-Fs0PxcLczNu5HelMqo
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$25$SLLiveFragment();
//                            }
//                        });
//                        dealExpGradeUpdate(resultData);
//                        return;
//                    case 17:
//                        if (!resultData.isQMTaskGift() || NumberUtils.string2int(resultData.giftNum) <= 1) {
//                            dealGiftNoticeMsgFromSocket(resultData);
//                            return;
//                        }
//                        int string2int3 = NumberUtils.string2int(resultData.giftNum);
//                        resultData.giftNum = "1";
//                        while (i < string2int3) {
//                            dealGiftNoticeMsgFromSocket(resultData);
//                            i++;
//                        }
//                        return;
//                    case 18:
//                        resultData.sysNoticeType = "nobilityTrumpetBroadcast";
//                        dealSysNoticeMsgFromSocket(resultData);
//                        return;
//                    case 19:
//                        resultData.sysNoticeType = "universalBroadcast";
//                        dealSysNoticeMsgFromSocket(resultData);
//                        return;
//                    case 20:
//                        resultData.sysNoticeType = "openNobilityBroadcast";
//                        dealSysNoticeMsgFromSocket(resultData);
//                        return;
//                    case 21:
//                        resultData.sysNoticeType = "generalFlutterScreen";
//                        String str3 = resultData.type;
//                        switch (str3.hashCode()) {
//                            case -1671569065:
//                                if (str3.equals("startLiveNotify")) {
//                                    i = 3;
//                                    break;
//                                }
//                                i = -1;
//                                break;
//                            case -648591709:
//                                if (str3.equals("dayRankUp")) {
//                                    i = 2;
//                                    break;
//                                }
//                                i = -1;
//                                break;
//                            case 989204668:
//                                break;
//                            case 1525144764:
//                                if (str3.equals("hitNotify")) {
//                                    i = 1;
//                                    break;
//                                }
//                                i = -1;
//                                break;
//                            default:
//                                i = -1;
//                                break;
//                        }
//                        if (i == 0) {
//                            dealSysNoticeMsgFromSocket(resultData);
//                            return;
//                        } else if (i != 1) {
//                            if (i == 2) {
//                                resultData.sysNoticeType = "dayRankUp";
//                                dealAnchorInfoNoticeMsgFromSocket(resultData);
//                                return;
//                            } else if (i == 3) {
//                                resultData.sysNoticeType = "startLiveNotify";
//                                dealAnchorInfoNoticeMsgFromSocket(resultData);
//                                return;
//                            } else {
//                                return;
//                            }
//                        } else if (AppUtils.isEnableTurntable()) {
//                            dealSysLuckMsgFromSocket(resultData);
//                            return;
//                        } else {
//                            return;
//                        }
//                    case 22:
//                        dealConsumeMsgFormSocket(resultData);
//                        return;
//                    case 23:
//                        dealGiftBoxMsgFromSocket(resultData);
//                        return;
//                    case 24:
//                        showToast(getString(R.string.fq_gift_box_toast_tips, resultData.presenterName, resultData.propNum, resultData.propName));
//                        return;
//                    case 25:
//                        dealGiftBoxMsg(resultData);
//                        return;
//                    case 26:
//                        dealPropMsgFormSocket(resultData);
//                        return;
//                    case 27:
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$og4sWrCCe_xVqU-fSEN2x6R4d5E
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$26$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case 28:
//                        if (CacheUtils.isLocalCacheComponents(resultData.gameId)) {
//                            String str4 = resultData.type;
//                            int hashCode2 = str4.hashCode();
//                            if (hashCode2 == 49) {
//                                break;
//                            } else {
//                                if (hashCode2 == 50 && str4.equals("2")) {
//                                    i = 1;
//                                    if (i != 0) {
//                                        dealBroadcastMsgFromSocket(resultData);
//                                        return;
//                                    } else if (i == 1) {
//                                        resultData.sysNoticeType = "msgBroadcastGame";
//                                        if (SPUtils.getInstance().getBoolean(ConstantUtils.NOTICE_GAME_KEY, true)) {
//                                            dealGameNoticeMsgFromSocket(resultData);
//                                            return;
//                                        }
//                                        return;
//                                    } else {
//                                        return;
//                                    }
//                                }
//                                i = -1;
//                                if (i != 0) {
//                                }
//                            }
//                        } else {
//                            return;
//                        }
//                        break;
//                    case 29:
//                        DBUtils.updatePrivateMsgDetail(resultData.senderId, resultData.messageId, resultData.status);
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$KNfESfqfgpqK7jqkNSFz8DZ5-zI
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$27$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case 30:
//                        updateAnchorContribution(resultData);
//                        return;
//                    case 31:
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$bXDbWNNJEpl_DMB0VjzGGNWh3bI
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$28$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case ' ':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$lRPp879aV6uF-Bh0f1gYK76MY0o
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$29$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case '!':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$k51AiaaA8dzkHupfJl57YazyJ7c
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$30$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case '\"':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$DoWhmQwhsnRHZnn_a49sbU24320
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$31$SLLiveFragment();
//                            }
//                        });
//                        return;
//                    case '#':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$a8SznhKFUY-i2k7fq3yPjuue2to
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$32$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case '$':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$AosqeuIx2oufjfzl3IoJPD-JbQc
//                            @Override // java.lang.Runnable
//                            public final void run() {
//                                SLLiveFragment.this.lambda$onBackThreadReceiveMessage$33$SLLiveFragment(resultData);
//                            }
//                        });
//                        return;
//                    case '%':
//                        if (!isLiveEnd()) {
//                            resultData.sysNoticeType = "assistKing";
//                            dealGameNoticeMsgFromSocket(resultData);
//                            return;
//                        }
//                        return;
//                    case '&':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.32
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                SocketMessageEvent.ResultData resultData2 = resultData;
//                                String str5 = resultData2.joinNum;
//                                String str6 = resultData2.content;
//                                String str7 = resultData2.price;
//                                String str8 = resultData2.icon;
//                                long string2int4 = NumberUtils.string2int(resultData2.duration, 0) * 60;
//                                SLLiveFragment.this.liveItemEntity.giftMarkId = resultData.markId;
//                                SLLiveFragment.this.liveItemEntity.drawStatus = "1";
//                                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                SocketMessageEvent.ResultData resultData3 = resultData;
//                                sLLiveFragment.loadHdLotteryDrawInfo(resultData3.liveDrawRecordId, resultData3.prizeName, resultData3.prizeNum, resultData3.scope, resultData3.markId, str5, str6, str7, str8, string2int4, "1");
//                            }
//                        });
//                        return;
//                    case '\'':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.33
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                SLLiveFragment.this.mLiveAdBannerBottomView.onLotteryEnd();
//                                SLLiveFragment.this.hdLotteryDrawingDialog.onLotteryEnd();
//                            }
//                        });
//                        return;
//                    case '(':
//                        resultData.sysNoticeType = "userPrivateMessage";
//                        dealPrivateMsgFromSocket(resultData);
//                        return;
//                    case ')':
//                        if (!isLiveEnd()) {
//                            this.isPayLive = true;
//                            this.chargeType = resultData.chargeType;
//                            this.ticketPrice = resultData.chargePrice;
//                            LiveEntity liveEntity = this.liveListItemEntity;
//                            liveEntity.chargeType = this.chargeType;
//                            liveEntity.ticketPrice = this.ticketPrice;
//                            handlerMainPost(new AnonymousClass34(resultData));
//                            return;
//                        }
//                        return;
//                    case '*':
//                        ((SLLivePresenter) this.mPresenter).onTimerDelayAction(3L, new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.35
//                            @Override // com.slzhibo.library.http.ResultCallBack
//                            public void onError(int i2, String str5) {
//                            }
//
//                            @Override // com.slzhibo.library.http.ResultCallBack
//                            public void onSuccess(Object obj) {
//                                EventBus.getDefault().post(new ListDataUpdateEvent(true));
//                                SLLiveFragment.this.liveListItemEntity.pullStreamUrl = resultData.pullStreamUrl;
//                                SLLiveFragment.this.updatePullStreamUrl();
//                                SLLiveFragment.this.switchStream();
//                            }
//                        });
//                        return;
//                    case '+':
//                        dealIntimateTaskFromSocket(resultData);
//                        return;
//                    case ',':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.36
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                SLLiveFragment.this.mLiveAdBannerBottomView.dealIntimateTaskShowFromSocket(false, resultData);
//                                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                if (sLLiveFragment.isDialogFragmentAdded(sLLiveFragment.qmTaskListUserDialog)) {
//                                    SLLiveFragment.this.qmTaskListUserDialog.onSendTaskListRequest();
//                                } else if (!TextUtils.equals(resultData.putUserId, UserInfoManager.getInstance().getUserId()) && SLLiveFragment.this.mLiveAdBannerBottomView.getTaskListSize() > 1) {
//                                    SLLiveFragment.this.mLiveAdBannerBottomView.showRedPoint();
//                                }
//                            }
//                        });
//                        return;
//                    case '-':
//                    case '.':
//                    case '/':
//                        showToast(resultData.text);
//                        return;
//                    case '0':
//                        if (isDialogFragmentAdded(this.qmTaskListUserDialog)) {
//                            this.qmTaskListUserDialog.updateTaskChargeList(resultData.intimateTaskChargeList);
//                        }
//                        this.mLiveAdBannerBottomView.dealIntimateTaskChargeFormSocket(resultData);
//                        return;
//                    case '1':
//                        if (isDialogFragmentAdded(this.qmTaskListUserDialog)) {
//                            this.qmTaskListUserDialog.completeTaskCharge(resultData.taskId);
//                        }
//                        this.mLiveAdBannerBottomView.dealIntimateTaskChargeCompleteFromSocket(resultData);
//                        return;
//                    case '2':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.37
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                    SocketMessageEvent.ResultData resultData2 = resultData;
//                                    sLLiveFragment.startRTC(resultData2.rtcAppId, resultData2.rtcToken, NumberUtils.string2int(resultData2.rtcUid), resultData.rtcRoomId);
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '3':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.38
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    if (SLLiveFragment.this.isVideoRoomType()) {
//                                        SLLiveFragment.this.addYYLinkChatMsgNotice(( SLLiveFragment.this).mContext.getString(R.string.fq_yy_chat_msg_video_audience_apply_tips, resultData.userName));
//                                        SLLiveFragment.this.mLiveAdBannerBottomView.showYYSmallWindow(SLLiveFragment.this.formatVideoLinkDetailInfo(resultData), SLLiveFragment.this.getString(R.string.fq_yy_linking));
//                                        if (AppUtils.isCurrentLoginUser(resultData.userId) && SLLiveFragment.this.linkSendApplyDialog != null) {
//                                            if (SLLiveFragment.this.linkSendApplyDialog.isAdded()) {
//                                                SLLiveFragment.this.linkSendApplyDialog.updateCurrentUserLinkingStatus();
//                                            } else {
//                                                SLLiveFragment.this.linkSendApplyDialog.resetTime();
//                                            }
//                                        }
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '4':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.39
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    SLLiveFragment.this.addYYLinkChatMsgNotice(( SLLiveFragment.this).mContext.getString(R.string.fq_yy_chat_msg_disconnect_tips, resultData.userName));
//                                    if (AppUtils.isCurrentLoginUser(resultData.userId) && resultData.isToastMsg()) {
//                                        SLLiveFragment.this.showToast(resultData.text);
//                                    }
//                                    if (SLLiveFragment.this.linkSendApplyDialog != null) {
//                                        SLLiveFragment.this.linkSendApplyDialog.clearDiffTime();
//                                    }
//                                    if (!SLLiveFragment.this.isVideoRoomType()) {
//                                        SLLiveFragment.this.yyLinkSeatListView.removeUserLink(resultData.userId);
//                                        if (AppUtils.isCurrentLoginUser(resultData.userId)) {
//                                            if (TextUtils.equals("1", resultData.type)) {
//                                                SLLiveFragment.this.stopRTC();
//                                            }
//                                            SLLiveFragment.this.updateYYLinkIconView(null);
//                                            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                            sLLiveFragment.dismissDialogFragment(sLLiveFragment.linkSendApplyDialog);
//                                        }
//                                    } else if (AppUtils.isCurrentLoginUser(resultData.userId)) {
//                                        SLLiveFragment.this.disconnectVideoLink(false);
//                                    } else {
//                                        SLLiveFragment.this.mLiveAdBannerBottomView.dismissYYSmallWindow();
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '5':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.40
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    SLLiveFragment.this.showAnchorInviteUserDialog(resultData.anchorAvatar);
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '6':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.41
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    if (AppUtils.isCurrentLoginUser(resultData.userId)) {
//                                        if (resultData.isToastMsg()) {
//                                            SLLiveFragment.this.showToast(resultData.text);
//                                        }
//                                        if (SLLiveFragment.this.isVideoRoomType()) {
//                                            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                            sLLiveFragment.dismissDialogFragment(sLLiveFragment.linkSendApplyDialog);
//                                        }
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '7':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.42
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    if (AppUtils.isCurrentLoginUser(resultData.userId)) {
//                                        SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                        if (sLLiveFragment.isDialogFragmentAdded(sLLiveFragment.linkSendApplyDialog)) {
//                                            SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                                            sLLiveFragment2.dismissDialogFragment(sLLiveFragment2.linkSendApplyDialog);
//                                        }
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '8':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$VtlOCC66iWPBbfRlUFPDzOxX29U
//                                @Override // java.lang.Runnable
//                                public final void run() {
//                                    SLLiveFragment.this.lambda$onBackThreadReceiveMessage$34$SLLiveFragment(resultData);
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '9':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.43
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    int string2int4 = NumberUtils.string2int(resultData.quantity, 8);
//                                    SLLiveFragment.this.yyLinkSeatListView.changeSeatNumMode(string2int4);
//                                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                    if (sLLiveFragment.isDialogFragmentAdded(sLLiveFragment.linkSendApplyDialog)) {
//                                        SLLiveFragment.this.linkSendApplyDialog.updateVoiceSeatChange(string2int4);
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case ':':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.44
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    SLLiveFragment.this.yyLinkSeatListView.switchUserLockMode(NumberUtils.string2int(resultData.seat), resultData.seatStatus);
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case ';':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.45
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    YYLinkSeatListView yYLinkSeatListView = SLLiveFragment.this.yyLinkSeatListView;
//                                    SocketMessageEvent.ResultData resultData2 = resultData;
//                                    yYLinkSeatListView.updateLikeCount(resultData2.userId, resultData2.quantity);
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '<':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.46
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    if (SLLiveFragment.this.isVideoRoomType()) {
//                                        List<String> list = resultData.userIdList;
//                                        if (list == null || list.isEmpty()) {
//                                            SLLiveFragment.this.mLiveAdBannerBottomView.stopYYVoiceAnim();
//                                        } else {
//                                            SLLiveFragment.this.mLiveAdBannerBottomView.showYYVoiceAnim();
//                                        }
//                                    }
//                                    if (SLLiveFragment.this.isVoiceRoomType()) {
//                                        SLLiveFragment.this.yyLinkSeatListView.setUserSpeakBySocket(resultData.userIdList);
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '=':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.47
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    if (SLLiveFragment.this.isVoiceRoomType()) {
//                                        SLLiveFragment.this.addYYLinkChatMsgNotice(( SLLiveFragment.this).mContext.getString(R.string.fq_yy_chat_msg_voice_audience_apply_tips, resultData.userName));
//                                    }
//                                    SLLiveFragment.this.yyLinkSeatListView.userJoinLinkSeat(SLLiveFragment.this.formatUserJoinLinkSeat(resultData));
//                                    if (AppUtils.isCurrentLoginUser(resultData.userId)) {
//                                        SLLiveFragment.this.updateYYLinkIconView("0");
//                                        if (SLLiveFragment.this.linkSendApplyDialog == null) {
//                                            return;
//                                        }
//                                        if (SLLiveFragment.this.linkSendApplyDialog.isAdded()) {
//                                            SLLiveFragment.this.linkSendApplyDialog.updateCurrentUserLinkingStatus();
//                                        } else {
//                                            SLLiveFragment.this.linkSendApplyDialog.resetTime();
//                                        }
//                                    }
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '>':
//                        if (resultData.isCurrentLive(this.liveId, String.valueOf(this.liveCount))) {
//                            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.48
//                                @Override // java.lang.Runnable
//                                public void run() {
//                                    SLLiveFragment.this.addYYLinkChatMsgNotice(SLLiveFragment.this.isVoiceRoomType() ? ( SLLiveFragment.this).mContext.getString(R.string.fq_yy_apply_up, resultData.userName) : ( SLLiveFragment.this).mContext.getString(R.string.fq_yy_apply_video_up, resultData.userName));
//                                }
//                            });
//                            return;
//                        }
//                        return;
//                    case '?':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.49
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                SLLiveFragment.this.liveRecommendGameId = resultData.gameId;
//                                SLLiveFragment.this.showRecommendComponentsView();
//                            }
//                        });
//                        return;
//                    case '@':
//                        this.liveItemEntity.shelfId = null;
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.50
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                SLLiveFragment.this.mLiveAdBannerBottomView.showHJProductShelfWindow(false, false);
//                            }
//                        });
//                        return;
//                    case 'A':
//                        this.liveItemEntity.shelfId = resultData.shelfId;
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.51
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                SLLiveFragment.this.mLiveAdBannerBottomView.showHJProductShelfWindow(false, true);
//                            }
//                        });
//                        return;
//                    case 'B':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.52
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                HJProductEntity hJProductEntity = new HJProductEntity();
//                                SocketMessageEvent.ResultData resultData2 = resultData;
//                                hJProductEntity.productId = resultData2.productId;
//                                hJProductEntity.productName = resultData2.productName;
//                                hJProductEntity.image = resultData2.image;
//                                hJProductEntity.setHotFlag(true);
//                                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                if (sLLiveFragment.isDialogFragmentAdded(sLLiveFragment.productRecommendDialog)) {
//                                    SLLiveFragment.this.productRecommendDialog.updateHotProductItemStatus(resultData.productId, true);
//                                }
//                                SLLiveFragment.this.mLiveAdBannerBottomView.showHJProductDetailWindow(hJProductEntity);
//                            }
//                        });
//                        return;
//                    case 'C':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.53
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                if (sLLiveFragment.isDialogFragmentAdded(sLLiveFragment.productRecommendDialog)) {
//                                    SLLiveFragment.this.productRecommendDialog.updateHotProductItemStatus(resultData.productId, false);
//                                }
//                                SLLiveFragment.this.mLiveAdBannerBottomView.showHJProductDetailWindow(null);
//                            }
//                        });
//                        return;
//                    case 'D':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.54
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                ChatEntity chatEntity = new ChatEntity();
//                                chatEntity.setMsgText(resultData.productName);
//                                chatEntity.setMsgType(22);
//                                chatEntity.setMsgSendName(resultData.userName);
//                                SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
//                            }
//                        });
//                        return;
//                    case 'E':
//                        LiveItemEntity liveItemEntity = this.liveItemEntity;
//                        if (liveItemEntity != null) {
//                            liveItemEntity.setBluetoothDeviceStatus(resultData.isBluetoothConnection());
//                            GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//                            if (giftBackpackDialog != null) {
//                                giftBackpackDialog.setBluetoothConnection(resultData.isBluetoothConnection());
//                            }
//                        }
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.55
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                if (resultData.isBluetoothConnection()) {
//                                    ChatEntity chatEntity = new ChatEntity();
//                                    chatEntity.setMsgText(SLLiveFragment.this.getString(R.string.fq_ly_bluetooth_connection_msg_tips_2));
//                                    chatEntity.setTargetId(String.valueOf(2));
//                                    chatEntity.setMsgType(23);
//                                    SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
//                                    return;
//                                }
//                                if (SLLiveFragment.this.lyControlWindowView != null) {
//                                    SLLiveFragment.this.lyControlWindowView.onReleaseData();
//                                }
//                                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                sLLiveFragment.dismissDialogFragment(sLLiveFragment.lyControlWindowDialog);
//                            }
//                        });
//                        return;
//                    case 'F':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.56
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                if (AppUtils.isCurrentLoginUser(resultData.userId) && resultData.isBluetoothWaveBandType()) {
//                                    SLLiveFragment.this.showToast(R.string.fq_ly_bluetooth_connection_msg_tips_4);
//                                }
//                                if (resultData.isBluetoothWaveBandType()) {
//                                    ChatEntity chatEntity = new ChatEntity();
//                                    chatEntity.setMsgSendName(resultData.userName);
//                                    chatEntity.setMsgText(resultData.modelName);
//                                    chatEntity.setTargetId(String.valueOf(3));
//                                    chatEntity.setMsgType(23);
//                                    SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity);
//                                }
//                                if (resultData.isBluetoothFreeType()) {
//                                    ChatEntity chatEntity2 = new ChatEntity();
//                                    chatEntity2.setMsgText(resultData.userName);
//                                    chatEntity2.setTargetId(String.valueOf(4));
//                                    chatEntity2.setMsgType(23);
//                                    SLLiveFragment.this.mLiveChatMsgView.addChatMsg(chatEntity2);
//                                }
//                            }
//                        });
//                        return;
//                    case 'G':
//                        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.57
//                            @Override // java.lang.Runnable
//                            public void run() {
//                                if (SLLiveFragment.this.lyCommandView == null) {
//                                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                    sLLiveFragment.lyCommandView = (LYCommandView) sLLiveFragment.lyCommandWindowViewStub.inflate();
//                                }
//                                LYCommandView lYCommandView = SLLiveFragment.this.lyCommandView;
//                                SocketMessageEvent.ResultData resultData2 = resultData;
//                                lYCommandView.initData(resultData2.waitNum, resultData2.duration);
//                            }
//                        });
//                        return;
//                    default:
//                        return;
//                }
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$25$SLLiveFragment() {
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.updateUserGradeInfo(this.myUserInfoEntity.getExpGrade());
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$27$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        PrivateMsgDialog privateMsgDialog = this.privateMsgDialog;
//        if (privateMsgDialog != null && privateMsgDialog.isAdded()) {
//            this.privateMsgDialog.changeMsgStatus(resultData.messageId, resultData.status);
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$28$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (!isLiveEnd()) {
//            String str = this.liveId;
//            AnchorEntity anchorEntity = this.anchorItemEntity;
//            showLinkPKLayoutView(str, anchorEntity.nickname, anchorEntity.avatar, resultData.matcherUserId, resultData.matcherLiveId, resultData.matcherAvatar, resultData.matcherUserName, 0L, 0L, false, false);
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$29$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (!isLiveEnd()) {
//            this.linkMicPKType.set(ConstantUtils.PK_TYPE_PK_PROCESSING);
//            PKInfoView pKInfoView = this.mPKInfoView;
//            if (pKInfoView != null) {
//                pKInfoView.onPKStart(NumberUtils.string2long(resultData.pkCountDownTime), NumberUtils.string2long(resultData.punishCountDownTime));
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$30$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (!isLiveEnd()) {
//            initLinkPKAssistData(resultData.anchorALiveId, resultData.anchorAFP, resultData.anchorAPopularity, resultData.anchorAAssists, resultData.anchorBLiveId, resultData.anchorBFP, resultData.anchorBPopularity, resultData.anchorBAssists);
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$31$SLLiveFragment() {
//        if (!isLiveEnd()) {
//            dismissDialogFragment(this.pkRankDialog);
//            stopLinkMicPk();
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$32$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        PKInfoView pKInfoView;
//        if (!isLiveEnd() && (pKInfoView = this.mPKInfoView) != null) {
//            pKInfoView.dealFirstKill(resultData.liveId, resultData.avatar);
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$33$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (!isLiveEnd()) {
//            stopPKTimerDisposable();
//            onLinkPKEnd(resultData.anchorALiveId, resultData.anchorAFP, resultData.anchorAPopularity, resultData.anchorAAssists, resultData.anchorBLiveId, resultData.anchorBFP, resultData.anchorBPopularity, resultData.anchorBAssists, false);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: package-private */
//    /* renamed from: com.slzhibo.library.ui.activity.live.SLLiveFragment$34  reason: invalid class name */
//    /* loaded from: classes3.dex */
//    public class AnonymousClass34 implements Runnable {
//        final /* synthetic */ SocketMessageEvent.ResultData val$resultData;
//
//        AnonymousClass34(SocketMessageEvent.ResultData resultData) {
//            this.val$resultData = resultData;
//        }
//
//        @Override // java.lang.Runnable
//        public void run() {
//            if (SLLiveFragment.this.onFragmentInteractionListener != null) {
//                ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onTimerDelayAction(4L, new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.34.1
//                    @Override // com.slzhibo.library.http.ResultCallBack
//                    public void onError(int i, String str) {
//                    }
//
//                    @Override // com.slzhibo.library.http.ResultCallBack
//                    public void onSuccess(Object obj) {
//                        SLLiveFragment.this.onFragmentInteractionListener.updateLiveRoomInfo();
//                    }
//                });
//            }
//            if (!this.val$resultData.isNeedBuyTicket()) {
//                SLLiveFragment.this.showPayLiveTips();
//                return;
//            }
//            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//            sLLiveFragment.payLiveTipsDialog = PayLiveTipsDialog.newInstance(this.val$resultData.freeWatchTime, AppUtils.formatMoneyUnitStr(( sLLiveFragment).mContext, SLLiveFragment.this.ticketPrice, false), new SimplePayLiveCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.34.2
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimplePayLiveCallback, com.slzhibo.library.ui.interfaces.OnPayLiveCallback
//                public void onPayEnterClickListener(final View view) {
//                    super.onPayEnterClickListener(view);
//                    SLLiveFragment.this.isPayLiveTipsDialog = false;
//                    if (SLLiveFragment.this.isConsumptionPermissionUser()) {
//                        view.setEnabled(false);
//                        ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onBuyLiveTicket(SLLiveFragment.this.liveId, new ResultCallBack<MyAccountEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.34.2.1
//                            public void onSuccess(MyAccountEntity myAccountEntity) {
//                                view.setEnabled(true);
//                                DBUtils.savePayLiveInfo(SLLiveFragment.this.liveId, SLLiveFragment.this.liveCount, String.valueOf(System.currentTimeMillis()));
//                                SLLiveFragment.this.showToast(R.string.fq_pay_live_ticket_verification_toast);
//                                SLLiveFragment.this.updateUserBalance(myAccountEntity.getAccountBalance());
//                                SLLiveFragment.this.isFirstGetMyBalanceGift = false;
//                                SLLiveFragment.this.showPayLiveTips();
//                                SLLiveFragment.this.payLiveTipsDialogOnRelease();
//                            }
//
//                            @Override // com.slzhibo.library.http.ResultCallBack
//                            public void onError(int i, String str) {
//                                view.setEnabled(true);
//                                if (i == 300006) {
//                                    AppUtils.onRechargeListener(( SLLiveFragment.this).mContext);
//                                }
//                            }
//                        });
//                        return;
//                    }
//                    AppUtils.onLoginListener(( SLLiveFragment.this).mContext);
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimplePayLiveCallback, com.slzhibo.library.ui.interfaces.OnPayLiveCallback
//                public void onPayExitClickListener() {
//                    super.onPayExitClickListener();
//                    SLLiveFragment.this.isPayLiveTipsDialog = false;
//                    SLLiveFragment.this.onFinishActivity();
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimplePayLiveCallback, com.slzhibo.library.ui.interfaces.OnPayLiveCallback
//                public void onPayCancelListener() {
//                    super.onPayCancelListener();
//                    SLLiveFragment.this.isPayLiveTipsDialog = false;
//                }
//            });
//            SLLiveFragment.this.payLiveTipsDialog.show(SLLiveFragment.this.getChildFragmentManager());
//            SLLiveFragment.this.isPayLiveTipsDialog = true;
//        }
//    }
//
//    public /* synthetic */ void lambda$onBackThreadReceiveMessage$34$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (AppUtils.isCurrentLoginUser(resultData.userId)) {
//            RTCController rTCController = this.rtcController;
//            if (rTCController != null) {
//                rTCController.muteLocalAudio(resultData.isMuteStatus());
//                showToast(!resultData.isMuteStatus() ? R.string.fq_yy_user_open_microphone_tips : R.string.fq_yy_user_disable_microphone_tips);
//                SPUtils.getInstance().put(ConstantUtils.MUTE_KEY, resultData.isMuteStatus());
//            }
//            if (resultData.isToastMsg()) {
//                showToast(resultData.text);
//            }
//        }
//        if (isVoiceRoomType()) {
//            this.yyLinkSeatListView.switchUserMuteMode(resultData.userId, !resultData.isMuteStatus());
//            if (AppUtils.isCurrentLoginUser(resultData.userId)) {
//                updateYYLinkIconView(resultData.isQuiet);
//                if (isDialogFragmentAdded(this.linkSendApplyDialog)) {
//                    this.linkSendApplyDialog.updateCurrentUserMuteStatus(resultData.isQuiet);
//                }
//            }
//        }
//        if (isVideoRoomType()) {
//            this.mLiveAdBannerBottomView.updateYYLinkMuteStatus(resultData.isQuiet);
//            if (isDialogFragmentAdded(this.linkSendApplyDialog)) {
//                this.linkSendApplyDialog.updateCurrentUserMuteStatus(resultData.isQuiet);
//            }
//        }
//    }
//
//    private void dealIntimateTaskFromSocket(SocketMessageEvent.ResultData resultData) {
//        synchronized (SLLiveFragment.class) {
//            if (this.intimateTaskQueue == null) {
//                this.intimateTaskQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.intimateTaskQueue.size() == 9999) {
//                this.intimateTaskQueue.poll();
//            }
//            this.intimateTaskQueue.offer(resultData);
//            sendWorkHandlerEmptyMessage(ConstantUtils.INTIMATE_TASK_NOTICE);
//        }
//    }
//
//    private void dealPrivateMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        synchronized (SLLiveFragment.class) {
//            if (this.privateMsgQueue == null) {
//                this.privateMsgQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.privateMsgQueue.size() == 9999) {
//                this.privateMsgQueue.poll();
//            }
//            this.privateMsgQueue.offer(resultData);
//            sendWorkHandlerEmptyMessage(10006);
//        }
//    }
//
//    private void dealGameNoticeMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        synchronized (SLLiveFragment.class) {
//            if (this.gameNoticeQueue == null) {
//                this.gameNoticeQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.gameNoticeQueue.size() == 9999) {
//                this.gameNoticeQueue.poll();
//            }
//            this.gameNoticeQueue.offer(resultData);
//        }
//        sendWorkHandlerEmptyMessage(ConstantUtils.GAME_NOTICE);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* renamed from: dealLotteryMsgFromSocket */
//    public void lambda$onBackThreadReceiveMessage$26$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (AppUtils.isEnableTurntable() && !isLuxuryBoomStatus()) {
//            onLotteryBoomOpen(resultData.propUrl, resultData.boomMultiple, resultData.boomRemainTime, resultData.boomTotalTime, resultData.turntableType);
//            LotteryDialog lotteryDialog = this.mLotteryDialog;
//            if (lotteryDialog != null && lotteryDialog.isResumed()) {
//                this.mLotteryDialog.executeBoom(resultData.turntableType == 20);
//            }
//        }
//    }
//
//    private void dealSysLuckMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        synchronized (SLLiveFragment.class) {
//            if (this.luckNoticeQueue == null) {
//                this.luckNoticeQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.luckNoticeQueue.size() == 9999) {
//                this.luckNoticeQueue.poll();
//            }
//            this.luckNoticeQueue.offer(resultData);
//        }
//        sendWorkHandlerEmptyMessageDelayed(ConstantUtils.SYS_LUCK_HIT, 4000L);
//    }
//
//    private void dealGiftBoxMsgFromSocket(final SocketMessageEvent.ResultData resultData) {
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$L6W6YJR6mGVu123oQIj_cx-tsFM
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$dealGiftBoxMsgFromSocket$35$SLLiveFragment(resultData);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$dealGiftBoxMsgFromSocket$35$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        GiftBoxEntity giftBoxEntity = new GiftBoxEntity();
//        giftBoxEntity.expirationTime = NumberUtils.string2long(resultData.expirationTime);
//        giftBoxEntity.openTime = NumberUtils.string2long(resultData.openTime);
//        giftBoxEntity.giftBoxUniqueCode = resultData.giftBoxUniqueCode;
//        giftBoxEntity.presenterAvatar = resultData.presenterAvatar;
//        giftBoxEntity.presenterId = resultData.presenterId;
//        giftBoxEntity.presenterName = resultData.presenterName;
//        giftBoxEntity.userId = this.myUserInfoEntity.getUserId();
//        giftBoxEntity.liveId = this.liveId;
//        GiftBoxView giftBoxView = this.mGiftBoxView;
//        if (giftBoxView != null) {
//            giftBoxView.setVisibility(View.VISIBLE);
//            this.mGiftBoxView.addOneBox(giftBoxEntity);
//        }
//    }
//
//    private void dealGiftNoticeMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        synchronized (SLLiveFragment.class) {
//            if (this.giftNoticeQueue == null) {
//                this.giftNoticeQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.giftNoticeQueue.size() == 9999) {
//                this.giftNoticeQueue.poll();
//            }
//            this.giftNoticeQueue.offer(resultData);
//        }
//        sendWorkHandlerEmptyMessage(10004);
//    }
//
//    private void dealAnchorInfoNoticeMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        synchronized (SLLiveFragment.class) {
//            if (this.anchorInfoNoticeQueue == null) {
//                this.anchorInfoNoticeQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.anchorInfoNoticeQueue.size() == 9999) {
//                this.anchorInfoNoticeQueue.poll();
//            }
//            this.anchorInfoNoticeQueue.offer(resultData);
//        }
//        sendWorkHandlerEmptyMessage(ConstantUtils.ANCHOR_INFO_NOTICE);
//    }
//
//    private void dealSysNoticeMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        synchronized (SLLiveFragment.class) {
//            if (this.sysNoticeQueue == null) {
//                this.sysNoticeQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.sysNoticeQueue.size() == 9999) {
//                this.sysNoticeQueue.poll();
//            }
//            this.sysNoticeQueue.offer(resultData);
//        }
//        sendWorkHandlerEmptyMessage(10005);
//    }
//
//    private void dealConsumeMsgFormSocket(final SocketMessageEvent.ResultData resultData) {
//        char c;
//        updateAnchorContribution(resultData);
//        String str = resultData.type;
//        int hashCode = str.hashCode();
//        if (hashCode == -903754697) {
//            if (str.equals(ConstantUtils.SOCKET_CONSUME_TYPE_RENEW_NOBILITY)) {
//                c = 2;
//            }
//            c = 65535;
//        } else if (hashCode != -736437516) {
//            if (hashCode == 1524196987 && str.equals(ConstantUtils.SOCKET_CONSUME_TYPE_OPEN_GUARD)) {
//                c = 0;
//            }
//            c = 65535;
//        } else {
//            if (str.equals("openNobility")) {
//                c = 1;
//            }
//            c = 65535;
//        }
//        if (c == 0) {
//            if (isVoiceRoomType()) {
//                this.yyLinkSeatListView.updateUserGuardType(resultData.userId, resultData.guardType);
//            }
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$QR83NxP3Dms3VYhm2IYse4iabdw
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealConsumeMsgFormSocket$36$SLLiveFragment(resultData);
//                }
//            });
//            addBigAnim(resultData, ConstantUtils.BIG_ANIM_OPEN_GUARD_TYPE);
//            this.liveAnimationView.loadGuardOpenAnimation(resultData);
//        } else if (c == 1) {
//            if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.userId)) {
//                ((SLLivePresenter) this.mPresenter).getTrumpetStatus();
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$xNjDSgs5oi9L4LNaEAUbyQQW52Y
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealConsumeMsgFormSocket$37$SLLiveFragment();
//                    }
//                });
//                this.myNobilityType = resultData.nobilityType;
//                UserInfoManager.getInstance().setNobilityType(this.myNobilityType);
//            }
//            if (isVoiceRoomType()) {
//                this.yyLinkSeatListView.updateUserNobility(resultData.userId, resultData.nobilityType);
//            }
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$VKbUQSkjOjnXausEMlwd2zE0M2g
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealConsumeMsgFormSocket$38$SLLiveFragment(resultData);
//                }
//            });
//            if (resultData.isBigAnimRegionShowNotify() && AppUtils.isCanShowOpenNobilityAnim(resultData.nobilityType)) {
//                addBigAnim(resultData, ConstantUtils.BIG_ANIM_OPEN_NOBILITY_TYPE);
//            }
//            if (resultData.isLeftAnimRegionShowNotify()) {
//                this.liveAnimationView.loadNobilityOpenAnimation(resultData);
//            }
//        } else if (c == 2) {
//            if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.userId)) {
//                ((SLLivePresenter) this.mPresenter).getTrumpetStatus();
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$8eRqPku0CBVctulepKEbIwgLrug
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealConsumeMsgFormSocket$39$SLLiveFragment();
//                    }
//                });
//                this.myNobilityType = resultData.nobilityType;
//                UserInfoManager.getInstance().setNobilityType(this.myNobilityType);
//            }
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$iVe9EZJdphbGnkhhn6a9KGkUVUE
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealConsumeMsgFormSocket$40$SLLiveFragment(resultData);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$dealConsumeMsgFormSocket$36$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.updateOpenGuardCount(resultData.anchorGuardCount);
//        }
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgType(12);
//        chatEntity.setMsgSendName(resultData.userName);
//        chatEntity.setExpGrade(resultData.expGrade);
//        chatEntity.setGuardType(NumberUtils.string2int(resultData.guardType));
//        chatEntity.setNobilityType(resultData.nobilityType);
//        chatEntity.setMarkUrls(resultData.markUrls);
//        chatEntity.setMsgText(AppUtils.getGuardTypeStr(this.mContext, resultData.guardType));
//        this.mLiveChatMsgView.addChatMsg(chatEntity);
//        this.mLiveChatMsgView.updateGuardTypeItemDataByUid(resultData.userId, NumberUtils.string2int(resultData.guardType));
//        this.mLivePusherInfoView.sortUserList(resultData.userId, resultData.guardType, resultData.expGrade, resultData.nobilityType, resultData.role);
//        if (TextUtils.equals(resultData.userId, this.myUserInfoEntity.getUserId())) {
//            GuardItemEntity guardItemEntity = this.guardItemEntity;
//            guardItemEntity.userGuardType = resultData.guardType;
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog != null) {
//                inputTextMsgForAudienceDialog.setMyGuardType(NumberUtils.string2int(guardItemEntity.userGuardType));
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$dealConsumeMsgFormSocket$37$SLLiveFragment() {
//        this.giftBottomDialog.updateBackPackCount();
//    }
//
//    public /* synthetic */ void lambda$dealConsumeMsgFormSocket$38$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (resultData.isChatRegionShowNotify()) {
//            ChatEntity chatEntity = new ChatEntity();
//            chatEntity.setMsgType(14);
//            chatEntity.setMsgSendName(resultData.userName);
//            chatEntity.setExpGrade(resultData.expGrade);
//            chatEntity.setGuardType(NumberUtils.string2int(resultData.guardType));
//            chatEntity.setNobilityType(resultData.nobilityType);
//            chatEntity.setMarkUrls(resultData.markUrls);
//            chatEntity.setMsgText(getString(R.string.fq_nobility_msg_open_tips, AppUtils.getNobilityBadgeName(this.mContext, resultData.nobilityType)));
//            this.mLiveChatMsgView.addChatMsg(chatEntity);
//        }
//        this.mLiveChatMsgView.updateNobilityTypeItemDataByUid(resultData.userId, resultData.nobilityType);
//        this.mLivePusherInfoView.sortUserList(resultData.userId, resultData.guardType, resultData.expGrade, resultData.nobilityType, resultData.role);
//    }
//
//    public /* synthetic */ void lambda$dealConsumeMsgFormSocket$39$SLLiveFragment() {
//        this.giftBottomDialog.updateBackPackCount();
//    }
//
//    public /* synthetic */ void lambda$dealConsumeMsgFormSocket$40$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (resultData.isChatRegionShowNotify()) {
//            ChatEntity chatEntity = new ChatEntity();
//            chatEntity.setMsgType(14);
//            chatEntity.setMsgSendName(resultData.userName);
//            chatEntity.setExpGrade(resultData.expGrade);
//            chatEntity.setGuardType(NumberUtils.string2int(resultData.guardType));
//            chatEntity.setNobilityType(resultData.nobilityType);
//            chatEntity.setMarkUrls(resultData.markUrls);
//            chatEntity.setMsgText(getString(R.string.fq_nobility_msg_renewal_tips, AppUtils.getNobilityBadgeName(this.mContext, resultData.nobilityType)));
//            this.mLiveChatMsgView.addChatMsg(chatEntity);
//        }
//        this.mLiveChatMsgView.updateNobilityTypeItemDataByUid(resultData.userId, resultData.nobilityType);
//        this.mLivePusherInfoView.sortUserList(resultData.userId, resultData.guardType, resultData.expGrade, resultData.nobilityType, resultData.role);
//    }
//
//    private void dealMyPropMsgFormSocket(SocketMessageEvent.ResultData resultData) {
//        GiftItemEntity giftItemEntity = new GiftItemEntity();
//        giftItemEntity.bigAnimType = ConstantUtils.BIG_ANIM_PROP_TYPE;
//        giftItemEntity.duration = NumberUtils.string2int(resultData.duration);
//        giftItemEntity.activeTime = NumberUtils.string2int(resultData.activeTime);
//        GiftIndexEntity giftIndexEntity = this.myPropIndexMap.get(resultData.propId);
//        if (giftIndexEntity == null) {
//            giftIndexEntity = new GiftIndexEntity();
//            giftIndexEntity.sendIndex++;
//            this.isContinueCombo = false;
//            this.myPropIndexMap.put(resultData.propId, giftIndexEntity);
//        } else if (giftIndexEntity.countDownStartTime == 0) {
//            giftIndexEntity.sendIndex++;
//            this.isContinueCombo = false;
//        } else if (System.currentTimeMillis() - giftIndexEntity.countDownStartTime > NumberUtils.formatMillisecond(giftItemEntity.activeTime)) {
//            giftIndexEntity.sendIndex = 1;
//            giftIndexEntity.countDownStartTime = 0L;
//            this.isContinueCombo = false;
//        } else {
//            giftIndexEntity.sendIndex++;
//            this.isContinueCombo = true;
//            giftIndexEntity.countDownStartTime = 0L;
//        }
//        giftItemEntity.sendUserName = resultData.userName;
//        giftItemEntity.userId = resultData.userId;
//        giftItemEntity.avatar = resultData.avatar;
//        giftItemEntity.role = resultData.role;
//        giftItemEntity.userRole = resultData.userRole;
//        giftItemEntity.sex = resultData.sex;
//        giftItemEntity.weekStar = resultData.isWeekStar;
//        giftItemEntity.expGrade = AppUtils.formatExpGrade(resultData.expGrade);
//        giftItemEntity.guardType = NumberUtils.string2int(resultData.guardType);
//        giftItemEntity.nobilityType = resultData.nobilityType;
//        giftItemEntity.appId = resultData.appId;
//        giftItemEntity.openId = resultData.openId;
//        giftItemEntity.animalUrl = resultData.animalUrl;
//        giftItemEntity.markId = resultData.propId;
//        giftItemEntity.name = resultData.propName;
//        giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
//        giftItemEntity.effect_type = NumberUtils.string2int(resultData.animalType);
//        giftItemEntity.animalType = giftItemEntity.effect_type;
//        giftItemEntity.imgurl = resultData.coverUrl;
//        giftItemEntity.giftNum = "1";
//        giftItemEntity.marks = resultData.markUrls;
//        if (giftItemEntity.isBigProp()) {
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$J41EyLPvde_cfm2H9NHeJhC5x38
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealMyPropMsgFormSocket$41$SLLiveFragment();
//                }
//            });
//            WsManager wsManager = this.wsManager;
//            if (wsManager != null) {
//                wsManager.addLocalAnim(giftItemEntity);
//            }
//        }
//        playMySelfAnimGift(giftItemEntity);
//        showSelfGiftMsgOnChatList(giftItemEntity);
//        LogEventUtils.uploadSendProp(this.anchorItemEntity, giftItemEntity, resultData.giftNum, this.liveId, this.myUserInfoEntity.expGrade);
//    }
//
//    private void dealReceivePropMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        long currentTimeMillis = System.currentTimeMillis();
//        GiftItemEntity giftItemEntity = new GiftItemEntity();
//        giftItemEntity.bigAnimType = ConstantUtils.BIG_ANIM_PROP_TYPE;
//        giftItemEntity.animalUrl = resultData.animalUrl;
//        giftItemEntity.duration = NumberUtils.string2int(resultData.duration);
//        giftItemEntity.activeTime = NumberUtils.string2int(resultData.activeTime);
//        if (!this.receivePropMap.containsKey(resultData.userId)) {
//            HashMap hashMap = new HashMap(8);
//            GiftIndexEntity giftIndexEntity = new GiftIndexEntity();
//            giftIndexEntity.createTime = currentTimeMillis;
//            giftIndexEntity.sendIndex = 1;
//            hashMap.put(resultData.propId, giftIndexEntity);
//            this.receivePropMap.put(resultData.userId, hashMap);
//            giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
//        } else {
//            Map<String, GiftIndexEntity> map = this.receivePropMap.get(resultData.userId);
//            GiftIndexEntity giftIndexEntity2 = map.get(resultData.propId);
//            if (giftIndexEntity2 == null) {
//                giftIndexEntity2 = new GiftIndexEntity();
//                giftIndexEntity2.createTime = currentTimeMillis;
//                giftIndexEntity2.sendIndex = 1;
//                map.put(resultData.propId, giftIndexEntity2);
//            } else {
//                if (currentTimeMillis - giftIndexEntity2.createTime < NumberUtils.formatMillisecond(giftItemEntity.activeTime + giftItemEntity.duration)) {
//                    giftIndexEntity2.sendIndex++;
//                } else {
//                    giftIndexEntity2.sendIndex = 1;
//                }
//                giftIndexEntity2.createTime = currentTimeMillis;
//            }
//            giftItemEntity.sendIndex = giftIndexEntity2.sendIndex;
//        }
//        giftItemEntity.sendUserName = resultData.userName;
//        giftItemEntity.userId = resultData.userId;
//        giftItemEntity.role = resultData.role;
//        giftItemEntity.userRole = resultData.userRole;
//        giftItemEntity.sex = resultData.sex;
//        giftItemEntity.guardType = NumberUtils.string2int(resultData.guardType);
//        giftItemEntity.nobilityType = resultData.nobilityType;
//        giftItemEntity.expGrade = AppUtils.formatExpGrade(resultData.expGrade);
//        giftItemEntity.avatar = resultData.avatar;
//        giftItemEntity.effect_type = NumberUtils.string2int(resultData.animalType);
//        giftItemEntity.animalType = giftItemEntity.effect_type;
//        giftItemEntity.imgurl = resultData.coverUrl;
//        String str = resultData.propName;
//        giftItemEntity.name = str;
//        String str2 = resultData.propId;
//        giftItemEntity.markId = str2;
//        resultData.giftName = str;
//        resultData.markId = str2;
//        giftItemEntity.giftNum = "1";
//        giftItemEntity.marks = resultData.markUrls;
//        playReceiveAnimGift(giftItemEntity);
//        if (giftItemEntity.isBigProp()) {
//            WsManager wsManager = this.wsManager;
//            if (wsManager != null) {
//                wsManager.addReceiveBigAnim(giftItemEntity);
//            }
//            showReceiveMsgOnChatList(resultData, AppUtils.appendGiftStringWithIndex(giftItemEntity), 1);
//        } else {
//            int i = giftItemEntity.sendIndex;
//            if (i == 1) {
//                showReceiveMsgOnChatList(resultData, AppUtils.appendGiftStringNoIndex(giftItemEntity), 1);
//            } else if (i != 0 && i % 10 == 0) {
//                showReceiveMsgOnChatList(resultData, AppUtils.appendGiftStringWithIndex(giftItemEntity), 1);
//            }
//        }
//        wsManagerNotifyAnim();
//    }
//
//    private void dealLiveSettingMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        String str = resultData.changeType;
//        if (((str.hashCode() == 1559674578 && str.equals("speakLevel")) ? (char) 0 : (char) 65535) == 0) {
//            this.speakLevel = resultData.changeValue;
//            final ChatEntity chatEntity = new ChatEntity();
//            chatEntity.setMsgText(getString(R.string.fq_speak_level_tip_for_socket, this.speakLevel));
//            chatEntity.setExpGrade(this.speakLevel);
//            chatEntity.setMsgType(11);
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$ul0bLgGy1fG7QpTB8wPzuynGofI
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealLiveSettingMsgFromSocket$42$SLLiveFragment(chatEntity);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$dealLiveSettingMsgFromSocket$42$SLLiveFragment(ChatEntity chatEntity) {
//        this.mLiveChatMsgView.addChatMsg(chatEntity);
//    }
//
//    private void dealBroadcastMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        final ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgText(resultData.content);
//        chatEntity.setPropId(resultData.gameId);
//        chatEntity.setMsgType(16);
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$znZxLWUa2Y4cZ-7cSxSC99zDNWc
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$dealBroadcastMsgFromSocket$43$SLLiveFragment(chatEntity);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$dealBroadcastMsgFromSocket$43$SLLiveFragment(ChatEntity chatEntity) {
//        this.mLiveChatMsgView.addChatMsg(chatEntity);
//    }
//
//    private void dealKickOutMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        if (TextUtils.equals(this.anchorId, resultData.userId)) {
//            resultData.userName = getString(R.string.fq_anchor);
//        }
//        showReceiveMsgOnChatList(resultData, resultData.userName, 9);
//        if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.targetId)) {
//            startKickDialogService(resultData.tipsText);
//            this.mainHandler.post(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.58
//                @Override // java.lang.Runnable
//                public void run() {
//                    SLLiveFragment.this.onFinishActivity();
//                }
//            });
//        }
//    }
//
//    private void dealGiftMsgFormSocket(final SocketMessageEvent.ResultData resultData) {
//        if (!resultData.isScoreGift()) {
//            updateAnchorContribution(resultData);
//        }
//        UserEntity userEntity = this.myUserInfoEntity;
//        if (userEntity == null || !TextUtils.equals(userEntity.getUserId(), resultData.userId)) {
//            playReceiveAnimOnMainThread(resultData);
//            return;
//        }
//        playMySelfAnimOnMainThread(resultData);
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.59
//            @Override // java.lang.Runnable
//            public void run() {
//                if (SLLiveFragment.this.liveItemEntity.isHdLotterySuccessToast(resultData.markId) && SLLiveFragment.this.hdLotteryDrawingDialog != null) {
//                    SLLiveFragment.this.hdLotteryDrawingDialog.updatePartCount(NumberUtils.string2int(resultData.giftNum, 1));
//                }
//            }
//        });
//    }
//
//    private void dealPropMsgFormSocket(SocketMessageEvent.ResultData resultData) {
//        if (resultData.isPriceProps()) {
//            updateAnchorContribution(resultData);
//        }
//        if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.userId)) {
//            dealMyPropMsgFormSocket(resultData);
//        } else {
//            dealReceivePropMsgFromSocket(resultData);
//        }
//    }
//
//    private void dealEnterMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        this.isConnectingChatService = false;
//        this.isSocketClose = false;
//        this.isSocketError = false;
//        if (!resultData.isEnterHideBoolean()) {
//            synchronized (SLLiveFragment.class) {
//                if (this.enterMsgQueue == null) {
//                    this.enterMsgQueue = new ConcurrentLinkedQueue<>();
//                }
//                if (this.enterMsgQueue.size() == 9999) {
//                    this.enterMsgQueue.poll();
//                }
//                this.enterMsgQueue.offer(resultData);
//                sendWorkHandlerEmptyMessage(10002);
//            }
//            if (resultData.isEnterGuardType() || AppUtils.hasCar(resultData.carId)) {
//                synchronized (SLLiveFragment.class) {
//                    if (this.guardEnterMsgQueue == null) {
//                        this.guardEnterMsgQueue = new ConcurrentLinkedQueue<>();
//                    }
//                    if (this.guardEnterMsgQueue.size() == 9999) {
//                        this.guardEnterMsgQueue.poll();
//                    }
//                    this.guardEnterMsgQueue.offer(resultData);
//                    sendWorkHandlerEmptyMessage(10003);
//                }
//            }
//            if (!resultData.isHighNobility()) {
//                return;
//            }
//            if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.userId) || resultData.isPlayNobilityEnterAnim()) {
//                this.liveAnimationView.loadNobilityEnterAnimation(resultData);
//            }
//        } else if (TextUtils.equals(resultData.userId, this.myUserInfoEntity.getUserId())) {
//            showToast(R.string.fq_hide_enter_live);
//            this.myNobilityType = resultData.nobilityType;
//            UserInfoManager.getInstance().setNobilityType(this.myNobilityType);
//        }
//    }
//
//    private void dealChatMsgFromSocket(final SocketMessageEvent.ResultData resultData) {
//        if (!this.shieldedList.contains(resultData.userId)) {
//            this.chatContent = resultData.content;
//            if (this.isTranOpen) {
//                TranslationUtils.translationFromText(this.chatContent, new TranslationUtils.OnTransListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$4bacdlD7NTKpBvDMU2BxreSScK0
//                    @Override // com.slzhibo.library.utils.TranslationUtils.OnTransListener
//                    public final void onSuc(String str) {
//                        SLLiveFragment.this.lambda$dealChatMsgFromSocket$44$SLLiveFragment(resultData, str);
//                    }
//                });
//            } else {
//                showReceiveMsgOnChatList(resultData, this.chatContent, 3);
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$dealChatMsgFromSocket$44$SLLiveFragment(SocketMessageEvent.ResultData resultData, String str) {
//        showReceiveMsgOnChatList(resultData, str, 3);
//    }
//
//    private void dealUserBalanceMsgFromSocket(final SocketMessageEvent.ResultData resultData) {
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$YZYBrbTU4wtdSAYVgQFex8KAwEw
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$dealUserBalanceMsgFromSocket$45$SLLiveFragment(resultData);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$dealUserBalanceMsgFromSocket$45$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        updateUserBalance(resultData.getAccountBalance());
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* renamed from: dealLiveControlMsgFromSocket */
//    public void lambda$onBackThreadReceiveMessage$24$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog;
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog2;
//        boolean isManager = resultData.isManager();
//        if (isManager) {
//            showReceiveMsgOnChatList(resultData, resultData.userName, 6);
//            if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.targetId)) {
//                this.myUserInfoEntity.setRole("3");
//                if (this.isSuperBan) {
//                    InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog3 = this.mInputTextMsgDialog;
//                    if (inputTextMsgForAudienceDialog3 != null) {
//                        inputTextMsgForAudienceDialog3.setBandPostBySuperManager();
//                    }
//                } else if (this.isNormalBan) {
//                    InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog4 = this.mInputTextMsgDialog;
//                    if (inputTextMsgForAudienceDialog4 != null) {
//                        inputTextMsgForAudienceDialog4.setBandOnePost(DateUtils.getClearTime(this.banPostTimeLeft));
//                    }
//                } else if (this.isAllBan && (inputTextMsgForAudienceDialog2 = this.mInputTextMsgDialog) != null) {
//                    inputTextMsgForAudienceDialog2.cancelBandPost();
//                }
//            }
//        } else if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.targetId)) {
//            this.myUserInfoEntity.setRole("2");
//            if (this.isAllBan && (inputTextMsgForAudienceDialog = this.mInputTextMsgDialog) != null) {
//                inputTextMsgForAudienceDialog.setBanedAllPost();
//            }
//        }
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog5 = this.mInputTextMsgDialog;
//        if (inputTextMsgForAudienceDialog5 != null) {
//            inputTextMsgForAudienceDialog5.setMyRole(this.myUserInfoEntity.getRole());
//        }
//        String str = isManager ? "3" : "2";
//        this.mLiveChatMsgView.updateRoleItemDataByUid(resultData.targetId, str);
//        if (isVoiceRoomType()) {
//            this.yyLinkSeatListView.updateUserRole(resultData.targetId, str);
//        }
//        this.mLivePusherInfoView.sortUserList(resultData.targetId, null, null, -1, str);
//    }
//
//    private void dealNotifyMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        String str = resultData.type;
//        if (!TextUtils.isEmpty(str)) {
//            final String str2 = resultData.typeMsg;
//            char c = 65535;
//            int hashCode = str.hashCode();
//            if (hashCode != 50) {
//                if (hashCode == 1568 && str.equals("11")) {
//                    c = 1;
//                }
//            } else if (str.equals("2")) {
//                c = 0;
//            }
//            if (c == 0) {
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$-9PpdExdXJ2pWjP5EIdUHOS1Jbg
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealNotifyMsgFromSocket$46$SLLiveFragment(str2);
//                    }
//                });
//            } else if (c == 1) {
//                this.isBanGroup = true;
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$dealNotifyMsgFromSocket$46$SLLiveFragment(String str) {
//        startHideTitleTimer(str);
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgType(8);
//        chatEntity.setMsgText(str);
//        this.mLiveChatMsgView.addChatMsg(chatEntity);
//    }
//
//    private void dealBannedAllPostMsgFormSocket(SocketMessageEvent.ResultData resultData) {
//        this.isAllBan = resultData.isBanAll();
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$WYlhM-LZmGfW6UJjuX6JeBeV8FU
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$dealBannedAllPostMsgFormSocket$47$SLLiveFragment();
//            }
//        });
//        showReceiveMsgOnChatList(resultData, getString(this.isAllBan ? R.string.fq_text_input_banned_hint : R.string.fq_anchor_cancel_banned), 5);
//    }
//
//    public /* synthetic */ void lambda$dealBannedAllPostMsgFormSocket$47$SLLiveFragment() {
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog;
//        if (this.isAllBan) {
//            if (AppUtils.isAudience(this.myUserInfoEntity.getRole()) && (inputTextMsgForAudienceDialog = this.mInputTextMsgDialog) != null) {
//                inputTextMsgForAudienceDialog.setBanedAllPost();
//            }
//        } else if (this.isSuperBan) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog2 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog2 != null) {
//                inputTextMsgForAudienceDialog2.setBandPostBySuperManager();
//            }
//        } else if (this.isNormalBan) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog3 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog3 != null) {
//                inputTextMsgForAudienceDialog3.setBandOnePost(DateUtils.getClearTime(this.banPostTimeLeft));
//            }
//        } else {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog4 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog4 != null) {
//                inputTextMsgForAudienceDialog4.cancelBandPost();
//            }
//        }
//    }
//
//    private void dealBanPostMsgFromSocket(final SocketMessageEvent.ResultData resultData) {
//        final boolean isSomeoneBanPost = resultData.isSomeoneBanPost();
//        if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.targetId)) {
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$t4zQ7cyn8kOLnB9xKqk1mVVlcyY
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealBanPostMsgFromSocket$48$SLLiveFragment(isSomeoneBanPost, resultData);
//                }
//            });
//        }
//        if (TextUtils.equals(getString(R.string.fq_system), resultData.userName) || TextUtils.equals(this.anchorId, resultData.userId)) {
//            resultData.userName = getString(R.string.fq_anchor);
//        }
//        showReceiveMsgOnChatList(resultData, resultData.userName, 4);
//    }
//
//    public /* synthetic */ void lambda$dealBanPostMsgFromSocket$48$SLLiveFragment(boolean z, SocketMessageEvent.ResultData resultData) {
//        if (z) {
//            executeMyNormalBan(resultData.duration);
//        } else {
//            clearMyNormalBan();
//        }
//    }
//
//    private void dealSuperBanPostMsgFromSocket(SocketMessageEvent.ResultData resultData) {
//        if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.targetId)) {
//            this.isSuperBan = true;
//            if (!this.isAllBan && this.mInputTextMsgDialog != null) {
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$N8h-nDTEqJXSdN9tl7914EIAqmo
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealSuperBanPostMsgFromSocket$49$SLLiveFragment();
//                    }
//                });
//            }
//        }
//        showReceiveMsgOnChatList(resultData, resultData.userName, 4);
//    }
//
//    public /* synthetic */ void lambda$dealSuperBanPostMsgFromSocket$49$SLLiveFragment() {
//        this.mInputTextMsgDialog.setBandPostBySuperManager();
//    }
//
//    private void executeMyNormalBan(String str) {
//        this.isNormalBan = true;
//        this.banPostTimeLeft = str;
//        if (this.mInputTextMsgDialog != null) {
//            if (!this.isAllBan && !this.isSuperBan) {
//                this.mInputTextMsgDialog.setBandOnePost(DateUtils.getClearTime(this.banPostTimeLeft));
//            }
//            dismissInputMsgDialog();
//        }
//    }
//
//    private void clearMyNormalBan() {
//        this.isNormalBan = false;
//        if (this.isAllBan && AppUtils.isAudience(this.myUserInfoEntity.getRole())) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog != null) {
//                inputTextMsgForAudienceDialog.setBanedAllPost();
//            }
//        } else if (this.isSuperBan) {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog2 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog2 != null) {
//                inputTextMsgForAudienceDialog2.setBandPostBySuperManager();
//            }
//        } else {
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog3 = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog3 != null) {
//                inputTextMsgForAudienceDialog3.cancelBandPost();
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* renamed from: dealLeaveMsgFromSocket */
//    public void lambda$onBackThreadReceiveMessage$23$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (AppUtils.isAnchor(resultData.role)) {
//            this.isAutoGiftDialogFromWeekStar = false;
//            LiveEndEntity liveEndEntity = resultData.lastLiveData;
//            if (liveEndEntity == null) {
//                liveEndEntity = new LiveEndEntity();
//            }
//            this.lastLiveEndEntity = liveEndEntity;
//            LiveEndEntity liveEndEntity2 = this.lastLiveEndEntity;
//            String str = liveEndEntity2.appId;
//            String str2 = liveEndEntity2.openId;
//            AnchorEntity anchorEntity = this.anchorItemEntity;
//            liveEndEntity2.userId = anchorEntity.userId;
//            liveEndEntity2.nickname = anchorEntity.nickname;
//            liveEndEntity2.avatar = anchorEntity.avatar;
//            liveEndEntity2.expGrade = anchorEntity.expGrade;
//            liveEndEntity2.liveId = anchorEntity.liveId;
//            liveEndEntity2.sex = anchorEntity.sex;
//            liveEndEntity2.appId = str;
//            liveEndEntity2.openId = str2;
//            clearQMNoticeAnimView();
//            ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue = this.intimateTaskQueue;
//            if (concurrentLinkedQueue != null) {
//                concurrentLinkedQueue.clear();
//            }
//            if (this.isEnablePK) {
//                stopLinkMicPk();
//            }
//            if (isPayLiveTicket() && isLiving()) {
//                payLiveTipsDialogOnRelease();
//                if (DBUtils.isPayLiveValidState(this.liveId, this.liveCount)) {
//                    showPayLiveEvaluationDialog();
//                } else {
//                    dismissDialogFragment(this.liveEndEvaluationDialog);
//                }
//                DBUtils.deletePayLiveInfo(this.liveId, this.liveCount);
//            }
//            goToEnd();
//            watchRecordReport();
//            this.livingStartTime = 0L;
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showUserCard(UserEntity userEntity) {
//        if (TextUtils.equals(userEntity.getUserId(), this.myUserInfoEntity.getUserId())) {
//            showUserAvatarDialog(userEntity, false);
//        } else if (TextUtils.equals(userEntity.getUserId(), this.anchorId)) {
//            if (!this.isStartGetAnchorInfo) {
//                ((SLLivePresenter) this.mPresenter).getAnchorInfo(userEntity.getUserId());
//                this.isStartGetAnchorInfo = true;
//            }
//        } else if (userEntity.isRankHideBoolean()) {
//            NobilityOpenTipsDialog.newInstance(13, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$DOYSrhCE9FWMVGvqGkJ5LVZ6DXc
//                @Override // android.view.View.OnClickListener
//                public final void onClick(View view) {
//                    SLLiveFragment.this.lambda$showUserCard$50$SLLiveFragment(view);
//                }
//            }).show(getChildFragmentManager());
//        } else {
//            showUserAvatarDialog(userEntity, true);
//        }
//    }
//
//    public /* synthetic */ void lambda$showUserCard$50$SLLiveFragment(View view) {
//        toNobilityOpenActivity();
//    }
//
//    private void showUserAvatarDialog(UserEntity userEntity, boolean z) {
//        userEntity.getAvatar();
//        String name = TextUtils.isEmpty(userEntity.getName()) ? userEntity.nickname : userEntity.getName();
//        String userId = userEntity.getUserId();
//        String sex = userEntity.getSex();
//        String signature = userEntity.getSignature();
//        String expGrade = userEntity.getExpGrade();
//        int guardType = userEntity.getGuardType();
//        String role = userEntity.getRole();
//        int nobilityType = userEntity.getNobilityType();
//        boolean contains = this.shieldedList.contains(userId);
//        boolean isHouseManager = AppUtils.isHouseManager(role);
//        boolean isYearGuard = AppUtils.isYearGuard(guardType);
//        boolean z2 = (!isHouseManager || !AppUtils.isAudience(this.myUserInfoEntity.getRole())) && z;
//        if (AppUtils.isHouseSuperManager(role)) {
//            this.userSuperAvatarDialog = UserSuperAvatarDialog.newInstance(userId, name, sex, signature, expGrade, guardType);
//            this.userSuperAvatarDialog.show(getChildFragmentManager());
//        } else if (AppUtils.isNobilityUser(nobilityType)) {
//            this.onUserCardCallback = new UserCardCallback(userId, name, 1, isHouseManager, contains, isYearGuard, AppUtils.cannotBannedNobility(nobilityType, this.nobilityTypeThresholdForHasPreventBanned));
//            if (this.userNobilityAvatarDialog == null) {
//                this.userNobilityAvatarDialog = UserNobilityAvatarDialog.newInstance(userEntity, z2, this.onUserCardCallback);
//                this.userNobilityAvatarDialog.show(getChildFragmentManager());
//                return;
//            }
//            this.bundleArgs = new Bundle();
//            this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_MANAGER, z2);
//            this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_IMPRESSION, false);
//            this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_REPORT, false);
//            this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, AppUtils.formatAnchorEntity(userEntity));
//            this.userNobilityAvatarDialog.setArguments(this.bundleArgs);
//            this.userNobilityAvatarDialog.setOnUserCardCallback(this.onUserCardCallback);
//            showDialogFragment(this.userNobilityAvatarDialog);
//        } else if (AppUtils.isGuardUser(guardType)) {
//            this.onUserCardCallback = new UserCardCallback(userId, name, 2, isHouseManager, contains, isYearGuard, false);
//            if (this.userGuardAvatarDialog == null) {
//                this.userGuardAvatarDialog = UserGuardAvatarDialog.newInstance(userEntity, z2, this.onUserCardCallback);
//                this.userGuardAvatarDialog.show(getChildFragmentManager());
//                return;
//            }
//            this.bundleArgs = new Bundle();
//            this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_MANAGER, z2);
//            this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, AppUtils.formatAnchorEntity(userEntity));
//            this.userGuardAvatarDialog.setArguments(this.bundleArgs);
//            this.userGuardAvatarDialog.setOnUserCardCallback(this.onUserCardCallback);
//            showDialogFragment(this.userGuardAvatarDialog);
//        } else {
//            this.onUserCardCallback = new UserCardCallback(userId, name, 3, isHouseManager, contains, isYearGuard, false);
//            if (this.userAvatarDialog == null) {
//                this.onUserCardCallback = new UserCardCallback(userId, name, 3, isHouseManager, contains, isYearGuard, false);
//                this.userAvatarDialog = UserNormalAvatarDialog.newInstance(userEntity, z2, this.onUserCardCallback);
//                this.userAvatarDialog.show(getChildFragmentManager());
//                return;
//            }
//            this.bundleArgs = new Bundle();
//            this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_MANAGER, z2);
//            this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_IMPRESSION, false);
//            this.bundleArgs.putBoolean(ConstantUtils.BUNDLE_VALUE_REPORT, false);
//            this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, AppUtils.formatAnchorEntity(userEntity));
//            this.userAvatarDialog.setArguments(this.bundleArgs);
//            this.userAvatarDialog.setOnUserCardCallback(this.onUserCardCallback);
//            showDialogFragment(this.userAvatarDialog);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
//    public void userAvatarDialogManager(final int i, final boolean z, final boolean z2, boolean z3, boolean z4, final String str, final String str2) {
//        char c;
//        String role = this.myUserInfoEntity.getRole();
//        switch (role.hashCode()) {
//            case 49:
//                if (role.equals("1")) {
//                    c = 0;
//                    break;
//                }
//                c = 65535;
//                break;
//            case 50:
//                if (role.equals("2")) {
//                    c = 1;
//                    break;
//                }
//                c = 65535;
//                break;
//            case 51:
//                if (role.equals("3")) {
//                    c = 2;
//                    break;
//                }
//                c = 65535;
//                break;
//            case 52:
//            default:
//                c = 65535;
//                break;
//            case 53:
//                if (role.equals("5")) {
//                    c = 3;
//                    break;
//                }
//                c = 65535;
//                break;
//        }
//        if (c == 0) {
//            ((SLLivePresenter) this.mPresenter).showUserManageMenu(this.liveId, str2, new ResultCallBack<UserEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.60
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i2, String str3) {
//                }
//
//                /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
//                    if (android.text.TextUtils.equals(r3, r9.this$0.userAvatarDialog.getTargetId()) != false) goto L_0x002b;
//                 */
//                /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
//                    r2 = true;
//                 */
//                /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
//                    if (android.text.TextUtils.equals(r3, r9.this$0.userGuardAvatarDialog.getTargetId()) != false) goto L_0x002b;
//                 */
//                /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
//                    if (android.text.TextUtils.equals(r3, r9.this$0.userNobilityAvatarDialog.getTargetId()) != false) goto L_0x002b;
//                 */
//                /*
//                    Code decompiled incorrectly, please refer to instructions dump.
//                */
//                public void onSuccess(UserEntity userEntity) {
//                    int i2 = i;
//                    boolean z5 = false;
//                    if (i2 == 1) {
//                        SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                        if (sLLiveFragment.isShowDialogFragment(sLLiveFragment.userNobilityAvatarDialog)) {
//                        }
//                    } else if (i2 == 2) {
//                        SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                        if (sLLiveFragment2.isShowDialogFragment(sLLiveFragment2.userGuardAvatarDialog)) {
//                        }
//                    } else if (i2 == 3) {
//                        SLLiveFragment sLLiveFragment3 = SLLiveFragment.this;
//                        if (sLLiveFragment3.isShowDialogFragment(sLLiveFragment3.userAvatarDialog)) {
//                        }
//                    }
//                    if (z5) {
//                        SLLiveFragment.this.showAnchorPermissionDialog(z, userEntity.isBanPostBoolean(), z2, str2, str);
//                    }
//                }
//            });
//        } else if (c == 1) {
//            showAudiencePermissionDialog(z2, str2, str);
//        } else if (c != 2) {
//            if (c == 3) {
//                showSuperControlPermissionDialog(str2, str);
//            }
//        } else if (z || z3 || z4) {
//            showAudiencePermissionDialog(z2, str2, str);
//        } else {
//            ((SLLivePresenter) this.mPresenter).showUserManageMenu(this.liveId, str2, new ResultCallBack<UserEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.61
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i2, String str3) {
//                }
//
//                /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
//                    if (android.text.TextUtils.equals(r3, r4.this$0.userAvatarDialog.getTargetId()) != false) goto L_0x002b;
//                 */
//                /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
//                    r2 = true;
//                 */
//                /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
//                    if (android.text.TextUtils.equals(r3, r4.this$0.userGuardAvatarDialog.getTargetId()) != false) goto L_0x002b;
//                 */
//                /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
//                    if (android.text.TextUtils.equals(r3, r4.this$0.userNobilityAvatarDialog.getTargetId()) != false) goto L_0x002b;
//                 */
//                /*
//                    Code decompiled incorrectly, please refer to instructions dump.
//                */
//                public void onSuccess(UserEntity userEntity) {
//                    int i2 = i;
//                    boolean z5 = false;
//                    if (i2 == 1) {
//                        SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                        if (sLLiveFragment.isShowDialogFragment(sLLiveFragment.userNobilityAvatarDialog)) {
//                        }
//                    } else if (i2 == 2) {
//                        SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                        if (sLLiveFragment2.isShowDialogFragment(sLLiveFragment2.userGuardAvatarDialog)) {
//                        }
//                    } else if (i2 == 3) {
//                        SLLiveFragment sLLiveFragment3 = SLLiveFragment.this;
//                        if (sLLiveFragment3.isShowDialogFragment(sLLiveFragment3.userAvatarDialog)) {
//                        }
//                    }
//                    if (z5) {
//                        SLLiveFragment.this.showControlPermissionDialog(z2, userEntity.isBanPostBoolean(), str2, str);
//                    }
//                }
//            });
//        }
//    }
//
//    private void showAudiencePermissionDialog(boolean z, final String str, final String str2) {
//        LiveActionBottomDialog.create("2", z, new LiveActionBottomDialog.OnLiveActionListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$T7HhS8jbT9zELRQv1AexecCGiQw
//            @Override // com.slzhibo.library.ui.view.dialog.LiveActionBottomDialog.OnLiveActionListener
//            public final void onLiveAction(int i, boolean z2) {
//                SLLiveFragment.this.lambda$showAudiencePermissionDialog$51$SLLiveFragment(str, str2, i, z2);
//            }
//        }).show(getChildFragmentManager());
//    }
//
//    public /* synthetic */ void lambda$showAudiencePermissionDialog$51$SLLiveFragment(String str, String str2, int i, boolean z) {
//        if (i == 3) {
//            dismissUserAvatarDialog();
//            clickShielded(z, str, str2);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showControlPermissionDialog(boolean z, boolean z2, final String str, final String str2) {
//        LiveActionBottomDialog.create("3", z, z2, isPayLiveNeedBuyTicket(), new LiveActionBottomDialog.OnLiveActionListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$RMSb62nXjoQN23iTMIoU0BYVRH0
//            @Override // com.slzhibo.library.ui.view.dialog.LiveActionBottomDialog.OnLiveActionListener
//            public final void onLiveAction(int i, boolean z3) {
//                SLLiveFragment.this.lambda$showControlPermissionDialog$52$SLLiveFragment(str, str2, i, z3);
//            }
//        }).show(getChildFragmentManager());
//    }
//
//    public /* synthetic */ void lambda$showControlPermissionDialog$52$SLLiveFragment(String str, String str2, int i, boolean z) {
//        dismissUserAvatarDialog();
//        if (i == 2) {
//            clickBanned(z, str, str2);
//        } else if (i == 3) {
//            clickShielded(z, str, str2);
//        } else if (i == 4) {
//            clickKickOut(str, str2);
//        }
//    }
//
//    private void showSuperControlPermissionDialog(final String str, final String str2) {
//        LiveActionBottomDialog.create("5", new LiveActionBottomDialog.OnLiveActionListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$sRfdGZ6eEZYN0NU901s9Eb6Npwk
//            @Override // com.slzhibo.library.ui.view.dialog.LiveActionBottomDialog.OnLiveActionListener
//            public final void onLiveAction(int i, boolean z) {
//                SLLiveFragment.this.lambda$showSuperControlPermissionDialog$53$SLLiveFragment(str, str2, i, z);
//            }
//        }).show(getChildFragmentManager());
//    }
//
//    public /* synthetic */ void lambda$showSuperControlPermissionDialog$53$SLLiveFragment(String str, String str2, int i, boolean z) {
//        WsManager wsManager;
//        dismissUserAvatarDialog();
//        if (i == 2) {
//            WsManager wsManager2 = this.wsManager;
//            if (wsManager2 != null) {
//                wsManager2.sendSuperBannedMessage(MessageHelper.convertToSuperBanMsg(this.liveId, str, str2));
//            }
//        } else if (i == 4 && (wsManager = this.wsManager) != null) {
//            wsManager.sendSuperGoOutMessage(MessageHelper.convertToSuperGoOutMsg(this.liveId, this.liveCount, str, str2, String.valueOf(this.liveType)));
//        }
//    }
//
//    private void clickBanned(boolean z, final String str, final String str2) {
//        if (z) {
//            BottomDialogUtils.showBannedDialog(this.mContext, new BottomDialogUtils.LiveBottomBannedMenuListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$6WOxi5Khnmu8zcAZ-SbUbHZnsgE
//                @Override // com.slzhibo.library.ui.view.dialog.BottomDialogUtils.LiveBottomBannedMenuListener
//                public final void onLiveBottomBannedMenuListener(long j) {
//                    SLLiveFragment.this.lambda$clickBanned$54$SLLiveFragment(str, str2, j);
//                }
//            });
//            return;
//        }
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.sendBannedMessage(MessageHelper.convertToBanMsg(str, str2, ImageSet.ID_ALL_MEDIA, "2"));
//        }
//    }
//
//    public /* synthetic */ void lambda$clickBanned$54$SLLiveFragment(String str, String str2, long j) {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.sendBannedMessage(MessageHelper.convertToBanMsg(str, str2, String.valueOf(j), "1"));
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showAnchorPermissionDialog(boolean z, boolean z2, boolean z3, final String str, final String str2) {
//        LiveActionBottomDialog.create("1", z, z3, z2, true, new LiveActionBottomDialog.OnLiveActionListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$w0yijOxWu9l5IGQZe2WUg75wcRc
//            @Override // com.slzhibo.library.ui.view.dialog.LiveActionBottomDialog.OnLiveActionListener
//            public final void onLiveAction(int i, boolean z4) {
//                SLLiveFragment.this.lambda$showAnchorPermissionDialog$55$SLLiveFragment(str2, str, i, z4);
//            }
//        }).show(getChildFragmentManager());
//    }
//
//    public /* synthetic */ void lambda$showAnchorPermissionDialog$55$SLLiveFragment(final String str, final String str2, int i, boolean z) {
//        dismissUserAvatarDialog();
//        if (i == 1) {
//            clickCtrl(z, str2, str);
//        } else if (i == 2) {
//            clickBanned(z, str2, str);
//        } else if (i == 3) {
//            clickShielded(z, str2, str);
//        } else if (i == 4) {
//            clickKickOut(str2, str);
//        } else if (i == 5) {
//            ((SLLivePresenter) this.mPresenter).getAnchorFrozenStatus(new ResultCallBack<AnchorEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.62
//                public void onSuccess(AnchorEntity anchorEntity) {
//                    if (anchorEntity == null || anchorEntity.isFrozenFlag()) {
//                        SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                        sLLiveFragment.showToast(sLLiveFragment.getString(R.string.fq_create_chat_fail));
//                        return;
//                    }
//                    boolean z2 = false;
//                    if (SLLiveFragment.this.privateMsgDialog == null) {
//                        SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                        String str3 = str;
//                        String str4 = str2;
//                        if (sLLiveFragment2.isSocketError || SLLiveFragment.this.isSocketClose) {
//                            z2 = true;
//                        }
//                        sLLiveFragment2.privateMsgDialog = PrivateMsgDialog.newInstance(str3, str4, z2);
//                        SLLiveFragment.this.privateMsgDialog.setSendPrivateMsgListener(SLLiveFragment.this);
//                        SLLiveFragment.this.privateMsgDialog.show(SLLiveFragment.this.getChildFragmentManager());
//                    } else if (!SLLiveFragment.this.privateMsgDialog.isAdded()) {
//                        Bundle bundle = new Bundle();
//                        bundle.putBoolean(PrivateMsgDialog.TYPE_FORM_ANCHOR, true);
//                        bundle.putInt(PrivateMsgDialog.CONTENT_TYPE_KEY, 2);
//                        bundle.putString(PrivateMsgDialog.TARGET_ID_KEY, str2);
//                        bundle.putString(PrivateMsgDialog.TARGET_NAME_KEY, str);
//                        String str5 = PrivateMsgDialog.TYPE_SOCKET_STATUS;
//                        if (SLLiveFragment.this.isSocketError || SLLiveFragment.this.isSocketClose) {
//                            z2 = true;
//                        }
//                        bundle.putBoolean(str5, z2);
//                        SLLiveFragment.this.privateMsgDialog.setArguments(bundle);
//                        SLLiveFragment.this.privateMsgDialog.show(SLLiveFragment.this.getChildFragmentManager());
//                    }
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i2, String str3) {
//                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                    sLLiveFragment.showToast(sLLiveFragment.getString(R.string.fq_create_chat_fail));
//                }
//            });
//        }
//    }
//
//    private void clickKickOut(String str, String str2) {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.sendKickOutMessage(MessageHelper.convertToKickOutMsg(this.liveId, this.liveCount, str, str2, String.valueOf(this.liveType)));
//        }
//    }
//
//    private void clickCtrl(boolean z, String str, String str2) {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.sendCtrlMessage(MessageHelper.convertToCtrlMsg(this.liveId, str, str2, z));
//        }
//    }
//
//    private void clickShielded(boolean z, String str, String str2) {
//        if (!z) {
//            this.shieldedList.remove(str);
//            DBUtils.updateShieldUser(str, false);
//            showToast(getString(R.string.fq_cancel_shielded, str2));
//        } else if (!this.shieldedList.contains(str)) {
//            this.shieldedList.add(str);
//            DBUtils.updateShieldUser(str, true);
//            showToast(getString(R.string.fq_shielded) + str2);
//        }
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.sendShieldMessage(MessageHelper.convertToShieldMsg(this.liveId, str, z));
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void toggleTrans() {
//        if (!AppUtils.isEnableTranslation(this.myUserInfoEntity.getExpGrade()) && !AppUtils.isHouseSuperManager(this.myUserInfoEntity.getRole())) {
//            WarnDialog.newInstance("TRANSLATION_TIP", getString(R.string.fq_enable_translation_level_tips, AppUtils.getEnableTranslationLevel())).show(getChildFragmentManager());
//        } else if (this.isTranOpen) {
//            this.isTranOpen = false;
//            showToast(R.string.fq_close_tran);
//        } else {
//            TransDialog transDialog = this.transDialog;
//            if (transDialog != null) {
//                transDialog.show(getChildFragmentManager());
//            }
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.dialog.InputTextMsgForAudienceDialog.OnTextSendListener
//    public void onTextSend(String str, int i) {
//        if (isLoginUser()) {
//            if (isSocketClose()) {
//                showToast(R.string.fq_text_network_error_chat);
//            } else if (i == 0 || i == 1 || i == 2) {
//                onSendSocketMsg(str, i);
//            } else if (i == 3) {
//                if (!this.trumpetStatus) {
//                    showToast(R.string.fq_trumpet_freezen);
//                } else if (this.curTrumpetCount.get() == 0) {
//                    showToast(R.string.fq_trumpet_count_not_enough);
//                    NobilityOpenTipsDialog.newInstance(12, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$gZkR5EqkTIJtcTJnCR7pPPzFwSE
//                        @Override // android.view.View.OnClickListener
//                        public final void onClick(View view) {
//                            SLLiveFragment.this.lambda$onTextSend$56$SLLiveFragment(view);
//                        }
//                    }).show(getFragmentManager());
//                } else {
//                    ((SLLivePresenter) this.mPresenter).sendTrumpet(str);
//                }
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$onTextSend$56$SLLiveFragment(View view) {
//        toNobilityOpenActivity();
//    }
//
//    @Override // com.slzhibo.library.ui.view.dialog.InputTextMsgForAudienceDialog.OnTextSendListener
//    public void selectTypeDialog(View view, int i) {
//        if (isConsumptionPermissionUserToLogin()) {
//            if (i != 1) {
//                if (i != 2) {
//                    if (i == 3) {
//                        if (AppUtils.highThanBoJue(this.myNobilityType)) {
//                            this.mInputTextMsgDialog.selectTrumpet(view);
//                            return;
//                        }
//                        dismissInputMsgDialog();
//                        NobilityOpenTipsDialog.newInstance(12, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$5L6gJzvem-7SX5qbEu3oriif3Xk
//                            @Override // android.view.View.OnClickListener
//                            public final void onClick(View view2) {
//                                SLLiveFragment.this.lambda$selectTypeDialog$58$SLLiveFragment(view2);
//                            }
//                        }).show(getFragmentManager());
//                    }
//                } else if (AppUtils.highThanBoJue(this.myNobilityType)) {
//                    this.mInputTextMsgDialog.selectNobility();
//                } else {
//                    dismissInputMsgDialog();
//                    NobilityOpenTipsDialog.newInstance(11, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$hPC0Kzm4syhHCpbKHnADsZB2vbs
//                        @Override // android.view.View.OnClickListener
//                        public final void onClick(View view2) {
//                            SLLiveFragment.this.lambda$selectTypeDialog$57$SLLiveFragment(view2);
//                        }
//                    }).show(getFragmentManager());
//                }
//            } else if (AppUtils.isGuardUser(NumberUtils.string2int(this.guardItemEntity.userGuardType))) {
//                this.mInputTextMsgDialog.selectGuard();
//            } else {
//                dismissInputMsgDialog();
//                GuardOpenTipsDialog.newInstance(11, this.guardItemEntity, this).show(getChildFragmentManager());
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$selectTypeDialog$57$SLLiveFragment(View view) {
//        toNobilityOpenActivity();
//    }
//
//    public /* synthetic */ void lambda$selectTypeDialog$58$SLLiveFragment(View view) {
//        toNobilityOpenActivity();
//    }
//
//    public void onSendSocketMsg(String str, int i) {
//        if (TextUtils.equals(this.myUserInfoEntity.getUserId(), this.anchorId)) {
//            this.myUserInfoEntity.setRole("1");
//            InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//            if (inputTextMsgForAudienceDialog != null) {
//                inputTextMsgForAudienceDialog.setMyRole(this.myUserInfoEntity.getRole());
//            }
//        }
//        if (AppUtils.isAudience(this.myUserInfoEntity.getRole())) {
//            if (AppUtils.isGuardUser(NumberUtils.string2int(this.guardItemEntity.userGuardType)) || AppUtils.isNobilityUser() || NumberUtils.string2int(this.speakLevel) <= NumberUtils.string2int(this.myUserInfoEntity.getExpGrade())) {
//                if (!AppUtils.isNobilityUser()) {
//                    this.clickCount.incrementAndGet();
//                    if (this.postIntervalTimes == 0) {
//                        this.clickCount.getAndSet(2);
//                    } else {
//                        if (this.clickCount.get() == 3) {
//                            startCDCountDown(this.postIntervalTimes * 3);
//                        }
//                        if (this.clickCount.get() > 3) {
//                            ChatTipDialog.newInstance(String.format(getString(R.string.fq_text_CD), Long.valueOf(this.countDownTime))).show(getChildFragmentManager(), "CD");
//                            return;
//                        }
//                    }
//                }
//                if (TextUtils.equals(str, this.lastMsg)) {
//                    ChatTipDialog.newInstance(getString(R.string.fq_text_same_content)).show(getChildFragmentManager(), "same_content");
//                    return;
//                }
//                this.lastMsg = str;
//            } else {
//                ChatTipDialog.newInstance(getString(R.string.fq_banned_speak_level_tip, this.speakLevel), true).show(getChildFragmentManager(), "speakLevel");
//                return;
//            }
//        }
//        if (this.isBanGroup) {
//            final ChatEntity chatEntity = new ChatEntity();
//            chatEntity.setMsgSendName(UserInfoManager.getInstance().getUserNickname());
//            chatEntity.setMsgText(str);
//            chatEntity.setMsgType(3);
//            chatEntity.setSex(UserInfoManager.getInstance().getUserSex());
//            chatEntity.setRole(this.myUserInfoEntity.getRole());
//            chatEntity.setUserRole(this.myUserInfoEntity.getUserRole());
//            chatEntity.setUid(this.myUserInfoEntity.getUserId());
//            chatEntity.setUserAvatar(UserInfoManager.getInstance().getAvatar());
//            chatEntity.setExpGrade(this.myUserInfoEntity.getExpGrade());
//            chatEntity.setGuardType(NumberUtils.string2int(this.guardItemEntity.userGuardType));
//            chatEntity.setWeekStar(this.myWeekStar);
//            chatEntity.setDanmuType(i);
//            chatEntity.setNobilityType(this.myNobilityType);
//            chatEntity.setMarkUrls(this.myUserInfoEntity.getMarkUrls());
//            this.mLiveChatMsgView.addChatMsg(chatEntity);
//            if (i == 1 || i == 2) {
//                handlerWorkPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$AHF05y5SJlTWMB9kago6TCNm5Yg
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$onSendSocketMsg$59$SLLiveFragment(chatEntity);
//                    }
//                });
//                return;
//            }
//            return;
//        }
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.sendChatMessage(MessageHelper.convertToChatMsg(str, i));
//        }
//    }
//
//    public /* synthetic */ void lambda$onSendSocketMsg$59$SLLiveFragment(ChatEntity chatEntity) {
//        this.mLivePusherInfoView.addDanmuMsg(chatEntity);
//    }
//
//    private void startCDCountDown(final int i) {
//        Observable.interval(1L, TimeUnit.SECONDS).map(new Function() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$4fmmn6I3hV_OFVDJs3mo1JmgkOQ
//            @Override // io.reactivex.functions.Function
//            public final Object apply(Object obj) {
//                Long valueOf;
//                valueOf = Long.valueOf(i - ((Long) obj).longValue());
//                return valueOf;
//            }
//        }).take(i + 1).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).compose(bindUntilEvent(FragmentEvent.DESTROY)).subscribe(new Observer<Long>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.63
//            @Override // io.reactivex.Observer
//            public void onComplete() {
//            }
//
//            @Override // io.reactivex.Observer
//            public void onError(Throwable th) {
//            }
//
//            @Override // io.reactivex.Observer
//            public void onSubscribe(Disposable disposable) {
//                SLLiveFragment.this.cdDisposable = disposable;
//            }
//
//            public void onNext(Long l) {
//                SLLiveFragment.this.countDownTime = l.longValue();
//                if (SLLiveFragment.this.countDownTime == 0) {
//                    SLLiveFragment.this.clickCount.getAndSet(2);
//                }
//            }
//        });
//    }
//
//    private void showChatFrames() {
//        if (this.isConnectingChatService) {
//            showToast(R.string.fq_start_connect_socket);
//        } else if (this.reConnectCountOver) {
//            WsManager wsManager = this.wsManager;
//            if (wsManager != null) {
//                wsManager.resetCount();
//                this.wsManager.reconnect();
//            }
//        } else {
//            showInputMsgDialog(false);
//            moveUpViews(true);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showInputMsgDialog(boolean z) {
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//        if (inputTextMsgForAudienceDialog != null && !inputTextMsgForAudienceDialog.isShowing()) {
//            this.mInputTextMsgDialog.show(z);
//        }
//    }
//
//    private void moveUpViews(boolean z) {
//        float f = 0.0f;
//        this.mLiveChatMsgView.setTranslationY(z ? -(ScreenUtils.getScreenHeight() * 0.42f) : 0.0f);
//        this.mLivePusherInfoView.setTranslationY(z ? -(ScreenUtils.getScreenHeight() / 4.0f) : 0.0f);
//        LiveAnimationView liveAnimationView = this.liveAnimationView;
//        if (z) {
//            f = -(ScreenUtils.getScreenHeight() / 3.0f);
//        }
//        liveAnimationView.setTranslationY(f);
//    }
//
//    private void addMsgToQueue(ChatEntity chatEntity) {
//        synchronized (SLLiveFragment.class) {
//            if (this.receiveMsgQueue == null) {
//                this.receiveMsgQueue = new ConcurrentLinkedQueue<>();
//            }
//            if (this.receiveMsgQueue.size() == 9999) {
//                this.receiveMsgQueue.poll();
//            }
//            this.receiveMsgQueue.offer(chatEntity);
//            if (this.asleep) {
//                this.asleep = false;
//                sendWorkHandlerEmptyMessage(10001);
//            }
//        }
//    }
//
//    private void showReceiveMsgOnChatList(SocketMessageEvent.ResultData resultData, String str, int i) {
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setAnchorId(this.anchorId);
//        chatEntity.setMsgSendName(resultData.userName);
//        chatEntity.setMsgText(str);
//        chatEntity.setMsgType(i);
//        chatEntity.setSex(resultData.sex);
//        chatEntity.setRole(resultData.role);
//        chatEntity.setUserRole(resultData.userRole);
//        chatEntity.setUid(resultData.userId);
//        chatEntity.setWeekStar(resultData.isWeekStar);
//        chatEntity.setGiftName(resultData.giftName);
//        chatEntity.setGiftNum(resultData.giftNum);
//        chatEntity.setUserAvatar(resultData.avatar);
//        chatEntity.setTargetAvatar(resultData.targetAvatar);
//        chatEntity.setTargetName(resultData.targetName);
//        chatEntity.setTargetId(resultData.targetId);
//        chatEntity.setExpGrade(AppUtils.formatExpGrade(resultData.expGrade));
//        chatEntity.setGuardType(NumberUtils.string2int(resultData.guardType));
//        chatEntity.setSomeoneBanPost(resultData.isSomeoneBanPost());
//        chatEntity.setAppId(resultData.appId);
//        chatEntity.setOpenId(resultData.openId);
//        chatEntity.setMarkUrls(resultData.markUrls);
//        if (resultData.isOpenGuardDanmu()) {
//            chatEntity.setDanmuType(1);
//        }
//        if (resultData.isOpenNobilityDanmu()) {
//            chatEntity.setDanmuType(2);
//        }
//        chatEntity.setNobilityType(resultData.nobilityType);
//        if (i == 6) {
//            chatEntity.setSetManager(TextUtils.equals(resultData.action, "1"));
//        }
//        if (i == 15) {
//            chatEntity.setMsgSendName(TextUtils.equals(resultData.userId, this.anchorId) ? this.mContext.getString(R.string.fq_anchor) : resultData.userName);
//            chatEntity.setExpGrade(resultData.afterGrade);
//        }
//        addMsgToQueue(chatEntity);
//        if (i == 3 && ((resultData.isOpenGuardDanmu() || resultData.isOpenNobilityDanmu()) && (AppUtils.isGuardUser(NumberUtils.string2int(resultData.guardType)) || AppUtils.isNobilityUser(resultData.nobilityType)))) {
//            this.mLivePusherInfoView.addDanmuMsg(chatEntity);
//        }
//        if (TextUtils.equals(resultData.userId, UserInfoManager.getInstance().getUserId())) {
//            this.speakTotalCount.incrementAndGet();
//        }
//        if (TextUtils.equals(chatEntity.getUid(), UserInfoManager.getInstance().getUserId())) {
//            LogEventUtils.uploadBarrageSend(this.anchorItemEntity, chatEntity.getDanmuType(), this.myUserInfoEntity.expGrade);
//        }
//    }
//
//    private void showSelfGiftMsgOnChatList(GiftItemEntity giftItemEntity) {
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgSendName(UserInfoManager.getInstance().getUserNickname());
//        chatEntity.setUid(this.myUserInfoEntity.getUserId());
//        chatEntity.setMsgType(1);
//        chatEntity.setGiftName(giftItemEntity.name);
//        chatEntity.setUserAvatar(giftItemEntity.avatar);
//        chatEntity.setRole(giftItemEntity.role);
//        chatEntity.setUserRole(giftItemEntity.userRole);
//        chatEntity.setExpGrade(AppUtils.formatExpGrade(giftItemEntity.expGrade));
//        chatEntity.setGuardType(giftItemEntity.guardType);
//        chatEntity.setSex(giftItemEntity.sex);
//        chatEntity.setNobilityType(giftItemEntity.nobilityType);
//        chatEntity.setWeekStar(giftItemEntity.weekStar);
//        chatEntity.setGiftNum(giftItemEntity.giftNum);
//        chatEntity.setOpenId(giftItemEntity.openId);
//        chatEntity.setAppId(giftItemEntity.appId);
//        chatEntity.setMarkUrls(giftItemEntity.marks);
//        if (!giftItemEntity.isSendSingleGift()) {
//            chatEntity.setMsgText(AppUtils.appendBatchGiftString(giftItemEntity));
//        } else if (giftItemEntity.isBigAnim()) {
//            chatEntity.setMsgText(AppUtils.appendGiftStringWithIndex(giftItemEntity));
//        } else {
//            int i = giftItemEntity.sendIndex;
//            if (i == 1) {
//                chatEntity.setMsgText(AppUtils.appendGiftStringNoIndex(giftItemEntity));
//            } else if (i != 0 && i % 10 == 0) {
//                chatEntity.setMsgText(AppUtils.appendGiftStringWithIndex(giftItemEntity));
//            } else {
//                return;
//            }
//        }
//        addMsgToQueue(chatEntity);
//    }
//
//    public /* synthetic */ boolean lambda$new$61$SLLiveFragment(Message message) {
//        switch (message.what) {
//            case 10001:
//                dealChatMsg();
//                return true;
//            case 10002:
//                dealEnterMsg();
//                return true;
//            case 10003:
//                dealGuardEnterMsg();
//                return true;
//            case 10004:
//                dealGiftNotice();
//                return true;
//            case 10005:
//                dealSysNotice();
//                return true;
//            case 10006:
//                dealPrivateMsg();
//                return true;
//            case 10007:
//            case 10008:
//            case 10009:
//            default:
//                return true;
//            case ConstantUtils.SYS_LUCK_HIT /* 10010 */:
//                dealSysLuckNotice();
//                return true;
//            case ConstantUtils.ANCHOR_INFO_NOTICE /* 10011 */:
//                dealAnchorInfoNotice();
//                return true;
//            case ConstantUtils.GAME_NOTICE /* 10012 */:
//                dealGameNotice();
//                return true;
//            case ConstantUtils.INTIMATE_TASK_NOTICE /* 10013 */:
//                dealIntimateTask();
//                return true;
//        }
//    }
//
//    private void dealIntimateTask() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowIntimateNotice.get() && (poll = this.intimateTaskQueue.poll()) != null) {
//            this.canShowIntimateNotice.set(false);
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$CED_LQ-0Z_GcCWulxLH0asyiZMM
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealIntimateTask$62$SLLiveFragment(poll);
//                }
//            });
//        }
//    }
//
//    private void dealPrivateMsg() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowPrivateMsg.get() && (poll = this.privateMsgQueue.poll()) != null) {
//            if (TextUtils.equals(poll.sysNoticeType, "userPrivateMessage")) {
//                this.canShowPrivateMsg.set(false);
//                final List<UserPrivateMessageEntity> list = poll.userPrivateMessageDetailsDTOList;
//                if (list != null && !list.isEmpty()) {
//                    ArrayList arrayList = new ArrayList();
//                    handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$jTOqQpg9k_7yCTl7pHGBKto-S-c
//                        @Override // java.lang.Runnable
//                        public final void run() {
//                            SLLiveFragment.this.lambda$dealPrivateMsg$63$SLLiveFragment(poll, list);
//                        }
//                    });
//                    for (UserPrivateMessageEntity userPrivateMessageEntity : list) {
//                        if (userPrivateMessageEntity != null) {
//                            arrayList.add(userPrivateMessageEntity.messageId);
//                            addPrivateMsgData(userPrivateMessageEntity.messageId, userPrivateMessageEntity.userId, userPrivateMessageEntity.avatar, userPrivateMessageEntity.userName, userPrivateMessageEntity.content, userPrivateMessageEntity.winningFlag, userPrivateMessageEntity.flagContent);
//                        }
//                    }
//                    WsManager wsManager = this.wsManager;
//                    if (wsManager != null) {
//                        wsManager.sendUserPrivateMsgReceiptMessage(MessageHelper.convertUserPrivateMsgReceiptMsg(poll.offlineFlag, arrayList));
//                    }
//                }
//                this.canShowPrivateMsg.set(true);
//                sendWorkHandlerEmptyMessage(10006);
//            } else if (TextUtils.equals(UserInfoManager.getInstance().getUserId(), poll.userId)) {
//                sendWorkHandlerEmptyMessage(10006);
//            } else {
//                this.canShowPrivateMsg.set(false);
//                addPrivateMsgData(poll.messageId, poll.userId, poll.avatar, poll.userName, poll.content, "", "");
//                WsManager wsManager2 = this.wsManager;
//                if (wsManager2 != null) {
//                    wsManager2.sendChatReceiptMessage(MessageHelper.convertToChatReceiptMsg(poll.userId, poll.messageId));
//                }
//                this.canShowPrivateMsg.set(true);
//                sendWorkHandlerEmptyMessage(10006);
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$eyAYiZF4Yc5pey2XhzAIJnBiR30
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealPrivateMsg$64$SLLiveFragment();
//                    }
//                });
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$dealPrivateMsg$63$SLLiveFragment(SocketMessageEvent.ResultData resultData, List list) {
//        LiveMoreDialog liveMoreDialog = this.liveMoreDialog;
//        if (liveMoreDialog != null && liveMoreDialog.isAdded()) {
//            this.liveMoreDialog.updateShowPrivateMsgRedDot();
//        }
//        if (resultData.isOfflinePrivateMsgFlag() && DBUtils.isShowOfflinePrivateMsgDialog(list)) {
//            this.offlinePrivateMsgDialog = SureCancelDialog.newInstance(null, this.mContext.getString(R.string.fq_hd_offline_private_msg_tips), this.mContext.getString(R.string.fq_hd_not_prompt_tips), this.mContext.getString(R.string.fq_hd_view_detail_tips), R.color.fq_text_black, new View.OnClickListener(this) { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.64
//                @Override // android.view.View.OnClickListener
//                public void onClick(View view) {
//                }
//            }, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.65
//                @Override // android.view.View.OnClickListener
//                public void onClick(View view) {
//                    SLLiveFragment.this.showPrivateMessageDialog();
//                }
//            });
//            this.offlinePrivateMsgDialog.show(getChildFragmentManager());
//        }
//    }
//
//    public /* synthetic */ void lambda$dealPrivateMsg$64$SLLiveFragment() {
//        LiveMoreDialog liveMoreDialog = this.liveMoreDialog;
//        if (liveMoreDialog != null && liveMoreDialog.isAdded()) {
//            this.liveMoreDialog.updateShowPrivateMsgRedDot();
//        }
//    }
//
//    private void dealGameNotice() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowGameNotice.get() && (poll = this.gameNoticeQueue.poll()) != null) {
//            if (SPUtils.getInstance().getBoolean(ConstantUtils.NOTICE_GAME_KEY, true) || !TextUtils.equals(poll.sysNoticeType, "msgBroadcastGame")) {
//                this.canShowGameNotice.set(false);
//                this.curGameNoticeId = poll.gameId;
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$bRyRXQeC56iZUyfpvvYp4SeXahY
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealGameNotice$66$SLLiveFragment(poll);
//                    }
//                });
//            } else if (this.gameNoticeQueue.isEmpty()) {
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$ArAIRGVcsdbfHxDs89nPsXVOUV0
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealGameNotice$65$SLLiveFragment();
//                    }
//                });
//            } else {
//                sendWorkHandlerEmptyMessage(ConstantUtils.GAME_NOTICE);
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$dealGameNotice$65$SLLiveFragment() {
//        this.mLivePusherInfoView.hideGameNoticeView();
//    }
//
//    public /* synthetic */ void lambda$dealGameNotice$66$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (TextUtils.equals(resultData.sysNoticeType, "msgBroadcastGame")) {
//            this.mLivePusherInfoView.setGameNoticeAnim(resultData, this.trumpetPlayPeriod);
//        } else if (TextUtils.equals(resultData.sysNoticeType, "assistKing")) {
//            this.mLivePusherInfoView.setPkAssistNoticeAnim(resultData, this.trumpetPlayPeriod);
//        }
//    }
//
//    private void dealSysLuckNotice() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowLuckNotice.get() && (poll = this.luckNoticeQueue.poll()) != null) {
//            this.canShowLuckNotice.set(false);
//            this.luckNoticeLiveId = poll.forwardLiveId;
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$8ijQsWwNlIY9AzoUYheLIoJUzdw
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealSysLuckNotice$67$SLLiveFragment(poll);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$dealSysLuckNotice$67$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        this.mLivePusherInfoView.setLuckNoticeAnim(resultData, this.liveId, this.trumpetPlayPeriod);
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgText(getString(R.string.fq_msg_attention_tips));
//        chatEntity.setMsgType(19);
//        chatEntity.setTargetName(resultData.anchorName);
//        chatEntity.setMsgSendName(resultData.userName);
//        chatEntity.setPropId(resultData.drawWay);
//        chatEntity.setPropName(resultData.propName);
//        chatEntity.setPropNum(resultData.propCount);
//        chatEntity.setTargetId(TextUtils.equals(this.liveId, resultData.forwardLiveId) ? null : resultData.forwardLiveId);
//        this.mLiveChatMsgView.addChatMsg(chatEntity);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* renamed from: cancelBigAnim */
//    public void lambda$playMySelfAnimOnMainThread$18$SLLiveFragment() {
//        if (this.liveAnimationView.isGiftAnimating() && !TextUtils.equals(this.myUserInfoEntity.getUserId(), this.curBigAnimSendUserId)) {
//            this.liveAnimationView.stopGiftAnimating();
//        }
//    }
//
//    private void addBigAnim(SocketMessageEvent.ResultData resultData, int i) {
//        GiftItemEntity giftItemEntity = new GiftItemEntity();
//        giftItemEntity.name = resultData.userName;
//        giftItemEntity.bigAnimType = i;
//        giftItemEntity.avatar = resultData.avatar;
//        giftItemEntity.nobilityType = resultData.nobilityType;
//        giftItemEntity.guardType = NumberUtils.string2int(resultData.guardType);
//        if (TextUtils.equals(resultData.userId, this.myUserInfoEntity.getUserId())) {
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$M50L7tdpa7NYUjJ_uVrEVqeaYaQ
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$addBigAnim$68$SLLiveFragment();
//                }
//            });
//            WsManager wsManager = this.wsManager;
//            if (wsManager != null) {
//                wsManager.addLocalAnim(giftItemEntity);
//                return;
//            }
//            return;
//        }
//        WsManager wsManager2 = this.wsManager;
//        if (wsManager2 != null) {
//            wsManager2.addReceiveBigAnim(giftItemEntity);
//        }
//    }
//
//    private void dealGuardEnterMsg() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowGuardEnterMsg.get() && this.carFullAnimFinish.get() && (poll = this.guardEnterMsgQueue.poll()) != null) {
//            this.canShowGuardEnterMsg.set(false);
//            final String formatExpGrade = AppUtils.formatExpGrade(poll.expGrade);
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$OlmTLEWU4QE6QBmetSP5tRQHtiI
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealGuardEnterMsg$69$SLLiveFragment(poll, formatExpGrade);
//                }
//            });
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue = this.guardEnterMsgQueue;
//        if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
//            sendWorkHandlerEmptyMessage(10003);
//        }
//    }
//
//    public /* synthetic */ void lambda$dealGuardEnterMsg$69$SLLiveFragment(SocketMessageEvent.ResultData resultData, String str) {
//        if (!resultData.isEnterGuardType() || !AppUtils.hasCar(resultData.carId)) {
//            if (resultData.isEnterGuardType()) {
//                this.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, GlideUtils.getGuardSVGADynamicEntity(this.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
//            } else if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.userId)) {
//                this.carFullAnimFinish.set(false);
//                this.liveAnimationView.loadCarJoinAnimation(resultData, true);
//            } else if (resultData.isOnPlayCarAnim()) {
//                this.carFullAnimFinish.set(false);
//                this.liveAnimationView.loadCarJoinAnimation(resultData, true);
//            } else {
//                this.canShowGuardEnterMsg.set(true);
//            }
//        } else if (TextUtils.equals(this.myUserInfoEntity.getUserId(), resultData.userId)) {
//            this.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, GlideUtils.getGuardSVGADynamicEntity(this.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
//            this.liveAnimationView.loadCarJoinAnimation(resultData, false);
//            this.carFullAnimFinish.set(false);
//        } else {
//            this.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, GlideUtils.getGuardSVGADynamicEntity(this.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
//            if (resultData.isOnPlayCarAnim()) {
//                this.carFullAnimFinish.set(false);
//                this.liveAnimationView.loadCarJoinAnimation(resultData, false);
//            }
//        }
//    }
//
//    private void dealGiftNotice() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowGiftNotice.get() && (poll = this.giftNoticeQueue.poll()) != null) {
//            this.canShowGiftNotice.set(false);
//            this.giftNoticeLiveId = poll.liveId;
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$2MC_rdqNXM9cT93QvHpmZ4lQ3PY
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealGiftNotice$70$SLLiveFragment(poll);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$dealGiftNotice$70$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        this.mLivePusherInfoView.setGiftNoticeAnim(resultData.userName, resultData.anchorName, resultData.giftNum, resultData.giftName, resultData.markId, AppUtils.getGiftNoticeInterval());
//    }
//
//    private void dealAnchorInfoNotice() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowAnchorInfoNotice.get() && (poll = this.anchorInfoNoticeQueue.poll()) != null) {
//            this.canShowAnchorInfoNotice.set(false);
//            this.curAnchorInfoNoticeEntity = poll;
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$pXU73Flm3hRMUSB8hpmz0Wam2xo
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealAnchorInfoNotice$71$SLLiveFragment(poll);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$dealAnchorInfoNotice$71$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        char c;
//        String str = resultData.sysNoticeType;
//        int hashCode = str.hashCode();
//        if (hashCode != -1671569065) {
//            if (hashCode == -648591709 && str.equals("dayRankUp")) {
//                c = 0;
//            }
//            c = 65535;
//        } else {
//            if (str.equals("startLiveNotify")) {
//                c = 1;
//            }
//            c = 65535;
//        }
//        if (c == 0) {
//            this.mLivePusherInfoView.setCharmNoticeAnim(resultData.anchorName, resultData.anchorNewRank, AppUtils.getGiftNoticeInterval());
//        } else if (c == 1) {
//            this.mLivePusherInfoView.setAnchorOpenNoticeAnim(resultData, AppUtils.getGiftNoticeInterval());
//        }
//    }
//
//    private void dealSysNotice() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowSysNotice.get() && (poll = this.sysNoticeQueue.poll()) != null) {
//            this.canShowSysNotice.set(false);
//            this.tempSysNoticeResultData = poll;
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$LQpFuFu0byJ7XKatVyJQDjjbTRo
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$dealSysNotice$72$SLLiveFragment(poll);
//                }
//            });
//        }
//    }
//
//    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
//    public /* synthetic */ void lambda$dealSysNotice$72$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        char c;
//        String str = resultData.sysNoticeType;
//        switch (str.hashCode()) {
//            case -1603387347:
//                if (str.equals("openNobilityBroadcast")) {
//                    c = 2;
//                    break;
//                }
//                c = 65535;
//                break;
//            case -941691210:
//                if (str.equals("universalBroadcast")) {
//                    c = 1;
//                    break;
//                }
//                c = 65535;
//                break;
//            case -370196576:
//                if (str.equals("generalFlutterScreen")) {
//                    c = 3;
//                    break;
//                }
//                c = 65535;
//                break;
//            case 395254178:
//                if (str.equals("nobilityTrumpetBroadcast")) {
//                    c = 0;
//                    break;
//                }
//                c = 65535;
//                break;
//            default:
//                c = 65535;
//                break;
//        }
//        if (c == 0) {
//            this.mLivePusherInfoView.setSysNobilityTrumpetAnim(resultData, this.trumpetPlayPeriod);
//            ChatEntity chatEntity = new ChatEntity();
//            chatEntity.setMsgText(resultData.content);
//            chatEntity.setMsgType(20);
//            chatEntity.setMsgSendName(resultData.userName);
//            chatEntity.setTargetName(resultData.anchorName);
//            this.mLiveChatMsgView.addChatMsg(chatEntity);
//        } else if (c == 1) {
//            this.mLivePusherInfoView.setSysNoticeAnim(resultData.content, this.nobilityPlayPeriod);
//            ChatEntity chatEntity2 = new ChatEntity();
//            chatEntity2.setMsgText(resultData.content);
//            chatEntity2.setMsgType(21);
//            this.mLiveChatMsgView.addChatMsg(chatEntity2);
//        } else if (c != 2) {
//            if (c == 3 && TextUtils.equals("recommend", resultData.type)) {
//                this.mLivePusherInfoView.setSysNobilityRecommendHotAnim(resultData, this.nobilityPlayPeriod);
//            }
//        } else if (resultData.nobilityType != 1 || !TextUtils.equals(resultData.type, "2")) {
//            this.mLivePusherInfoView.setSysNobilityOpenAnim(resultData, this.liveId, this.nobilityPlayPeriod);
//        }
//    }
//
//    private void dealChatMsg() {
//        ChatEntity poll;
//        final LinkedList linkedList = new LinkedList();
//        synchronized (SLLiveFragment.class) {
//            if (this.receiveMsgQueue != null && !this.receiveMsgQueue.isEmpty()) {
//                for (int i = 0; i < 5 && (poll = this.receiveMsgQueue.poll()) != null; i++) {
//                    linkedList.add(poll);
//                }
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$kU8c3fP29Jppq2zKechAT_13WO8
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealChatMsg$73$SLLiveFragment(linkedList);
//                    }
//                });
//                return;
//            }
//            this.asleep = true;
//        }
//    }
//
//    public /* synthetic */ void lambda$dealChatMsg$73$SLLiveFragment(List list) {
//        this.mLiveChatMsgView.addChatMsgList(list);
//        sendWorkHandlerEmptyMessageDelayed(10001, 1000L);
//    }
//
//    private void dealEnterMsg() {
//        final SocketMessageEvent.ResultData poll;
//        if (this.canShowEnterMsg.get() && (poll = this.enterMsgQueue.poll()) != null) {
//            this.canShowEnterMsg.set(false);
//            if (TextUtils.equals(this.myUserInfoEntity.getUserId(), poll.userId)) {
//                this.myNobilityType = poll.nobilityType;
//                UserInfoManager.getInstance().setNobilityType(this.myNobilityType);
//                this.myWeekStar = poll.isWeekStar;
//            } else if (AppUtils.isNobilityUser(poll.nobilityType) && !poll.isPlayNobilityEnterAnim()) {
//                this.canShowEnterMsg.set(true);
//                ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue = this.enterMsgQueue;
//                if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
//                    sendWorkHandlerEmptyMessageDelayed(10002, 1000L);
//                    return;
//                }
//                return;
//            }
//            final String formatExpGrade = AppUtils.formatExpGrade(poll.expGrade);
//            if (!TextUtils.isEmpty(formatExpGrade)) {
//                handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$aduAfj6KaJ3iEVaSx_sdBMLoOck
//                    @Override // java.lang.Runnable
//                    public final void run() {
//                        SLLiveFragment.this.lambda$dealEnterMsg$74$SLLiveFragment(poll, formatExpGrade);
//                    }
//                });
//            }
//        }
//    }
//
//    public /* synthetic */ void lambda$dealEnterMsg$74$SLLiveFragment(SocketMessageEvent.ResultData resultData, String str) {
//        if (!TextUtils.isEmpty(resultData.userId) || AppUtils.isChatEnterMsg(resultData.role, resultData.guardType, resultData.carId, resultData.nobilityType)) {
//            updateUserList(resultData);
//            ChatEntity chatEntity = new ChatEntity();
//            chatEntity.setMsgType(0);
//            chatEntity.setMsgSendName(resultData.userName);
//            chatEntity.setExpGrade(str);
//            chatEntity.setRole(resultData.role);
//            chatEntity.setUserRole(resultData.userRole);
//            chatEntity.setGuardType(NumberUtils.string2int(resultData.guardType));
//            chatEntity.setMsgText(getString(R.string.fq_live_join_notify_nobility));
//            chatEntity.setUserAvatar(resultData.avatar);
//            chatEntity.setUid(resultData.userId);
//            chatEntity.setSex(resultData.sex);
//            chatEntity.setCarIcon(resultData.carIcon);
//            chatEntity.setNobilityType(resultData.nobilityType);
//            chatEntity.setWeekStar(resultData.isWeekStar);
//            chatEntity.setOpenId(resultData.openId);
//            chatEntity.setAppId(resultData.appId);
//            chatEntity.setMarkUrls(resultData.markUrls);
//            this.mLiveChatMsgView.addChatMsg(chatEntity);
//        } else {
//            this.mLiveChatMsgView.setUserGradeInfo(str, resultData.userName);
//        }
//        this.canShowEnterMsg.set(true);
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue = this.enterMsgQueue;
//        if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
//            sendWorkHandlerEmptyMessageDelayed(10002, 1000L);
//        }
//    }
//
//    private void updateUserList(SocketMessageEvent.ResultData resultData) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setAvatar(resultData.avatar);
//        userEntity.setUserId(resultData.userId);
//        userEntity.setName(resultData.userName);
//        userEntity.setSex(resultData.sex);
//        userEntity.setExpGrade(AppUtils.formatExpGrade(resultData.expGrade));
//        userEntity.setGuardType(NumberUtils.string2int(resultData.guardType));
//        userEntity.setRole(resultData.role);
//        userEntity.setUserRole(resultData.userRole);
//        userEntity.setNobilityType(resultData.nobilityType);
//        this.mLivePusherInfoView.addUserItem(userEntity);
//    }
//
//    private void dealGiftBoxMsg(SocketMessageEvent.ResultData resultData) {
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgSendName(resultData.userName);
//        chatEntity.setMsgText(getString(R.string.fq_giftbox_tips));
//        chatEntity.setMsgType(13);
//        chatEntity.setTargetName(resultData.presenterName);
//        chatEntity.setPropId(resultData.propId);
//        chatEntity.setPropName(resultData.propName);
//        chatEntity.setPropNum(resultData.propNum);
//        addMsgToQueue(chatEntity);
//    }
//
//    private void dealExpGradeUpdate(SocketMessageEvent.ResultData resultData) {
//        showReceiveMsgOnChatList(resultData, "dealExpGradeUpdate", 15);
//    }
//
//    private void dismissDialogs() {
//        dismissUserAvatarDialog();
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.dismissDedicateTopDialog();
//        }
//        ComponentsWebViewDialog componentsWebViewDialog = this.componentsWebViewDialog;
//        if (componentsWebViewDialog != null && componentsWebViewDialog.isShowing()) {
//            this.componentsWebViewDialog.dismiss();
//        }
//        HdLotteryDrawingDialog hdLotteryDrawingDialog = this.hdLotteryDrawingDialog;
//        if (hdLotteryDrawingDialog != null && hdLotteryDrawingDialog.isShowing()) {
//            this.hdLotteryDrawingDialog.dismiss();
//        }
//        dismissInputMsgDialog();
//        dismissDialogFragment(this.giftBottomDialog, this.mTaskBottomDialog, this.mLotteryDialog, this.mWeekStarRankingDialog, this.guardListDialog, this.guardOpenContentDialog, this.giftWallDialog, this.userAchieveDialog, this.componentsDialog, this.privateMsgDialog, this.liveMoreDialog, this.webViewDialog, this.pkRankDialog, this.offlinePrivateMsgDialog, this.payLiveTipsDialog, this.qmInteractUserDialog, this.qmInviteSureDialog, this.qmTaskListUserDialog, this.linkSendApplyDialog, this.linkAnchorInviteDialog, this.linkActionMenuDialog, this.noticeManageDialog, this.linkUserDisconnectLinkDialog, this.productRecommendDialog, this.lyControlWindowDialog, this.lyPlayDescDialog, this.lySendGiftTipsDialog);
//        LivePusherInfoView livePusherInfoView2 = this.mLivePusherInfoView;
//        if (livePusherInfoView2 != null) {
//            livePusherInfoView2.clearLiveGuide();
//        }
//        LYCommandView lYCommandView = this.lyCommandView;
//        if (lYCommandView != null) {
//            lYCommandView.onReleaseData();
//        }
//        LYControlWindowView lYControlWindowView = this.lyControlWindowView;
//        if (lYControlWindowView != null) {
//            lYControlWindowView.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    private void dismissUserAvatarDialog() {
//        dismissDialogFragment(this.userAvatarDialog, this.userSuperAvatarDialog, this.userGuardAvatarDialog, this.userNobilityAvatarDialog, this.anchorAvatarDialog, this.anchorNobilityAvatarDialog);
//    }
//
//    private void dismissInputMsgDialog() {
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//        if (inputTextMsgForAudienceDialog != null && inputTextMsgForAudienceDialog.isShowing()) {
//            this.mInputTextMsgDialog.dismiss();
//        }
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment, com.slzhibo.library.utils.rxlifecycle2.components.support.RxFragment, android.support.v4.app.Fragment
//    public void onDestroy() {
//        onReleaseViewData();
//        onDestroyViewData();
//        super.onDestroy();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void sendWorkHandlerEmptyMessage(int i) {
//        synchronized (this.synchronizedObj) {
//            if (this.workHandler != null && !this.workHandler.hasMessages(i)) {
//                this.workHandler.sendEmptyMessage(i);
//            }
//        }
//    }
//
//    private void sendWorkHandlerEmptyMessageDelayed(int i, long j) {
//        synchronized (this.synchronizedObj) {
//            if (this.workHandler != null && !this.workHandler.hasMessages(i)) {
//                this.workHandler.sendEmptyMessageDelayed(i, j);
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void handlerMainPost(Runnable runnable) {
//        synchronized (this.synchronizedObj) {
//            if (!(this.mainHandler == null || runnable == null)) {
//                this.mainHandler.post(runnable);
//            }
//        }
//    }
//
//    private void handlerWorkPost(Runnable runnable) {
//        synchronized (this.synchronizedObj) {
//            if (!(this.workHandler == null || runnable == null)) {
//                this.workHandler.post(runnable);
//            }
//        }
//    }
//
//    private GuardItemEntity formatAnchorGuardInfo(GuardItemEntity guardItemEntity) {
//        if (guardItemEntity == null) {
//            GuardItemEntity guardItemEntity2 = new GuardItemEntity();
//            AnchorEntity anchorEntity = this.anchorItemEntity;
//            guardItemEntity2.anchorId = anchorEntity.userId;
//            guardItemEntity2.anchorName = anchorEntity.nickname;
//            guardItemEntity2.expGrade = this.myUserInfoEntity.getExpGrade();
//            guardItemEntity2.anchorGuardCount = this.liveItemEntity.anchorGuardCount;
//            guardItemEntity2.liveCount = this.liveCount;
//            return guardItemEntity2;
//        }
//        AnchorEntity anchorEntity2 = this.anchorItemEntity;
//        guardItemEntity.anchorId = anchorEntity2.userId;
//        guardItemEntity.anchorName = anchorEntity2.nickname;
//        guardItemEntity.expGrade = this.myUserInfoEntity.getExpGrade();
//        guardItemEntity.anchorGuardCount = this.liveItemEntity.anchorGuardCount;
//        guardItemEntity.liveCount = this.liveCount;
//        return guardItemEntity;
//    }
//
//    private AnchorEntity getAnchorEntityInfo(LiveEntity liveEntity) {
//        AnchorEntity anchorEntity = new AnchorEntity();
//        anchorEntity.appId = liveEntity.appId;
//        anchorEntity.openId = liveEntity.openId;
//        anchorEntity.liveId = liveEntity.liveId;
//        anchorEntity.liveCount = liveEntity.liveCount;
//        anchorEntity.tag = liveEntity.tag;
//        anchorEntity.userId = TextUtils.isEmpty(liveEntity.anchorId) ? liveEntity.userId : liveEntity.anchorId;
//        anchorEntity.avatar = liveEntity.avatar;
//        anchorEntity.sex = liveEntity.sex;
//        anchorEntity.nickname = liveEntity.nickname;
//        anchorEntity.expGrade = liveEntity.expGrade;
//        anchorEntity.liveCoverUrl = TextUtils.isEmpty(liveEntity.liveCoverUrl) ? liveEntity.avatar : liveEntity.liveCoverUrl;
//        this.anchorId = anchorEntity.userId;
//        this.anchorAppId = anchorEntity.appId;
//        return anchorEntity;
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void toNobilityOpenActivity() {
//        AppUtils.toNobilityOpenActivity(this.mContext, this.anchorItemEntity);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void attentionAnchorAction(View view, String str) {
//        if (isConsumptionPermissionUserToLogin() && AppUtils.isAttentionUser(this.mContext, str)) {
//            boolean z = !view.isSelected();
//            view.setSelected(z);
//            attentionAnchorAction(z, str);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void attentionAnchorAction(boolean z, String str) {
//        if (isConsumptionPermissionUserToLogin()) {
//            showToast(z ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
//            ((SLLivePresenter) this.mPresenter).attentionAnchor(str, z ? 1 : 0);
//            this.mLivePusherInfoView.setFollowed(z);
//            DBUtils.attentionAnchor(str, z);
//            this.mLiveChatMsgView.updateAttentionAnchor(z);
//            if (this.mLiveEndInfoView == null) {
//                this.mLiveEndInfoView = (LiveEndInfoView) this.vsLiveEndInfoView.inflate();
//                this.mLiveEndInfoView.setVisibility(View.INVISIBLE);
//            }
//            this.mLiveEndInfoView.setTvAttentionText(z);
//            AnchorEntity anchorEntity = this.anchorItemEntity;
//            LogEventUtils.uploadFollow(anchorEntity.openId, anchorEntity.appId, anchorEntity.tag, anchorEntity.expGrade, anchorEntity.nickname, getString(R.string.fq_live_room), z, this.liveId);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showGuardListDialog(final GuardItemEntity guardItemEntity) {
//        this.guardItemEntity = guardItemEntity;
//        this.guardListDialog = GuardListDialog.newInstance(2, guardItemEntity, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$yl6pqQCxZ0OJhA6mo2B90GeZQRA
//            @Override // android.view.View.OnClickListener
//            public final void onClick(View view) {
//                SLLiveFragment.this.lambda$showGuardListDialog$75$SLLiveFragment(guardItemEntity, view);
//            }
//        });
//        this.guardListDialog.show(getChildFragmentManager());
//    }
//
//    public /* synthetic */ void lambda$showGuardListDialog$75$SLLiveFragment(GuardItemEntity guardItemEntity, View view) {
//        if (isConsumptionPermissionUserToLogin() && AppUtils.isEnableGuard()) {
//            this.guardOpenContentDialog = GuardOpenContentDialog.newInstance(guardItemEntity, this);
//            this.guardOpenContentDialog.show(getChildFragmentManager());
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showGiftWallDialog(AnchorEntity anchorEntity) {
//        if (this.giftWallDialog == null) {
//            this.giftWallDialog = GiftWallDialog.newInstance(anchorEntity);
//            this.giftWallDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, anchorEntity);
//        this.giftWallDialog.setArguments(this.bundleArgs);
//        showDialogFragment(this.giftWallDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showUserAchieveDialog(UserEntity userEntity, String str) {
//        if (this.userAchieveDialog == null) {
//            this.userAchieveDialog = UserAchieveDialog.newInstance(userEntity, str);
//            this.userAchieveDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, userEntity);
//        this.bundleArgs.putString(ConstantUtils.RESULT_COUNT, str);
//        this.userAchieveDialog.setArguments(this.bundleArgs);
//        showDialogFragment(this.userAchieveDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showComponentsWebViewDialog(boolean z, ComponentsEntity componentsEntity) {
//        if (isConsumptionPermissionUserToLogin()) {
//            if (componentsEntity == null || !componentsEntity.isCorrectLink()) {
//                showToast(R.string.fq_game_url_invalid);
//            } else if (this.componentsWebViewDialog != null) {
//                AnchorEntity anchorEntity = this.anchorItemEntity;
//                this.componentsWebViewDialog.showDialog(z, anchorEntity == null ? "" : anchorEntity.openId, this.anchorAppId, componentsEntity);
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showPrivateMessageDialog() {
//        if (isConsumptionPermissionUserToLogin()) {
//            boolean equals = TextUtils.equals(this.anchorId, this.myUserInfoEntity.userId);
//            boolean z = this.isSocketError || this.isSocketClose;
//            PrivateMsgDialog privateMsgDialog = this.privateMsgDialog;
//            if (privateMsgDialog == null) {
//                if (equals) {
//                    this.privateMsgDialog = PrivateMsgDialog.newInstance(true, z);
//                } else {
//                    this.privateMsgDialog = PrivateMsgDialog.newInstance(z);
//                }
//                this.privateMsgDialog.setSendPrivateMsgListener(this);
//                this.privateMsgDialog.show(getChildFragmentManager());
//            } else if (!privateMsgDialog.isAdded()) {
//                Bundle bundle = new Bundle();
//                if (equals) {
//                    bundle.putBoolean(PrivateMsgDialog.TYPE_FORM_ANCHOR, true);
//                }
//                bundle.putInt(PrivateMsgDialog.CONTENT_TYPE_KEY, 1);
//                bundle.putBoolean(PrivateMsgDialog.TYPE_SOCKET_STATUS, z);
//                this.privateMsgDialog.setArguments(bundle);
//                this.privateMsgDialog.show(getChildFragmentManager());
//            }
//            MsgStatusEntity msgStatusEntity = new MsgStatusEntity();
//            msgStatusEntity.appId = UserInfoManager.getInstance().getAppId();
//            msgStatusEntity.userId = UserInfoManager.getInstance().getUserId();
//            msgStatusEntity.readStatus = "1";
//            DBUtils.saveOrUpdateMsgStatus(msgStatusEntity);
//            updateMoreRedDot();
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void changePrivateMessageNetStatus(boolean z) {
//        PrivateMsgDialog privateMsgDialog = this.privateMsgDialog;
//        if (privateMsgDialog != null && privateMsgDialog.isAdded()) {
//            this.privateMsgDialog.changeNetStatus(z);
//        }
//    }
//
//    private void showLiveMoreDialog() {
//        boolean z = !TextUtils.isEmpty(this.liveItemEntity.shelfId);
//        LiveMoreDialog.OnMenuItemClickListener onMenuItemClickListener = new LiveMoreDialog.OnMenuItemClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.66
//            @Override // com.slzhibo.library.ui.view.dialog.LiveMoreDialog.OnMenuItemClickListener
//            public void onItemClick(int i, MenuEntity menuEntity) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.dismissDialogFragment(sLLiveFragment.liveMoreDialog);
//                switch (menuEntity.getMenuType()) {
//                    case 273:
//                        if (SLLiveFragment.this.isConsumptionPermissionUserToLogin()) {
//                            SLLiveFragment.this.toggleTrans();
//                            return;
//                        }
//                        return;
//                    case 274:
//                        SLLiveFragment.this.showPrivateMessageDialog();
//                        return;
//                    case 275:
//                        if (SLLiveFragment.this.isConsumptionPermissionUserToLogin()) {
//                            if (SLLiveFragment.this.isLiveEnd()) {
//                                SLLiveFragment.this.showToast(R.string.fq_qm_live_end_start_task_tips);
//                                return;
//                            } else if (SLLiveFragment.this.mLiveAdBannerBottomView.isOneselfInitiateTask()) {
//                                SLLiveFragment.this.showQMInteractTaskListDialog(null);
//                                return;
//                            } else {
//                                ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).sendUserPendingTaskRequest(SLLiveFragment.this.liveId, new ResultCallBack<QMInteractTaskEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.66.1
//                                    public void onSuccess(QMInteractTaskEntity qMInteractTaskEntity) {
//                                        SLLiveFragment.this.showQMInteractTaskListDialog(qMInteractTaskEntity);
//                                    }
//
//                                    @Override // com.slzhibo.library.http.ResultCallBack
//                                    public void onError(int i2, String str) {
//                                        SLLiveFragment.this.showQMInteractUserDialog();
//                                    }
//                                });
//                                return;
//                            }
//                        } else {
//                            return;
//                        }
//                    case 276:
//                        SLLiveFragment.this.showLinkVoiceApplyDetailDialog(false);
//                        return;
//                    case 277:
//                        SLLiveFragment.this.showProductRecommendDialog(null);
//                        return;
//                    case 278:
//                        SLLiveFragment.this.showLYControlWindowDialog(0);
//                        return;
//                    default:
//                        return;
//                }
//            }
//        };
//        if (this.liveMoreDialog == null) {
//            this.liveMoreDialog = LiveMoreDialog.newInstance(this.isTranOpen, isVideoRoomType(), z, onMenuItemClickListener);
//            this.liveMoreDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putBoolean(ConstantUtils.RESULT_FLAG, this.isTranOpen);
//        this.bundleArgs.putBoolean(ConstantUtils.RESULT_ITEM, isVideoRoomType());
//        this.bundleArgs.putBoolean(ConstantUtils.RESULT_FLAG_VAR, z);
//        this.liveMoreDialog.setArguments(this.bundleArgs);
//        this.liveMoreDialog.setOnItemClickListener(onMenuItemClickListener);
//        showDialogFragment(this.liveMoreDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLotteryDialog() {
//        if (this.mLotteryDialog == null) {
//            this.mLotteryDialog = LotteryDialog.newInstance(this.liveId, new LotteryDialog.OnLotteryDialogCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.67
//                @Override // com.slzhibo.library.ui.view.dialog.LotteryDialog.OnLotteryDialogCallback
//                public void onClickAnchorAvatarListener(AnchorEntity anchorEntity) {
//                    SLLiveFragment.this.startActivityById(anchorEntity.liveId);
//                }
//
//                @Override // com.slzhibo.library.ui.view.dialog.LotteryDialog.OnLotteryDialogCallback
//                public void onClickUserAvatarListener(UserEntity userEntity) {
//                    SLLiveFragment.this.showUserCard(userEntity);
//                }
//
//                @Override // com.slzhibo.library.ui.view.dialog.LotteryDialog.OnLotteryDialogCallback
//                public void onJumpBackpackDialogListener() {
//                    if (SLLiveFragment.this.giftBottomDialog != null) {
//                        SLLiveFragment.this.getUserBalance(0);
//                        SLLiveFragment.this.isLotteryDialogFlag = true;
//                        SLLiveFragment.this.giftBottomDialog.initTagSelector(false);
//                        SLLiveFragment.this.giftBottomDialog.show(SLLiveFragment.this.getChildFragmentManager());
//                    }
//                }
//            });
//            this.mLotteryDialog.show(getChildFragmentManager());
//        } else {
//            this.bundleArgs = new Bundle();
//            this.bundleArgs.putString(ConstantUtils.RESULT_ITEM, this.liveId);
//            this.mLotteryDialog.setArguments(this.bundleArgs);
//            showDialogFragment(this.mLotteryDialog);
//        }
//        getUserBalance(1);
//    }
//
//    private void showComponentsDialog() {
//        if (this.componentsDialog == null) {
//            this.componentsDialog = ComponentsDialog.newInstance(this.isLotteryBoomStatus, new ComponentsDialog.OnGameItemClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.68
//                @Override // com.slzhibo.library.ui.view.dialog.ComponentsDialog.OnGameItemClickListener
//                public void onItemClick(int i, ComponentsEntity componentsEntity) {
//                    if (componentsEntity != null) {
//                        SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                        sLLiveFragment.dismissDialogFragment(sLLiveFragment.componentsDialog);
//                        if (componentsEntity.isCacheLotteryComponents()) {
//                            SLLiveFragment.this.showLotteryDialog();
//                        } else {
//                            SLLiveFragment.this.showComponentsWebViewDialog(true, componentsEntity);
//                        }
//                    }
//                }
//            });
//            this.componentsDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putBoolean(ConstantUtils.RESULT_FLAG, this.isLotteryBoomStatus);
//        this.componentsDialog.setArguments(this.bundleArgs);
//        showDialogFragment(this.componentsDialog);
//    }
//
//    private void showWebViewH5Dialog(BannerEntity bannerEntity) {
//        this.webViewDialog = WebViewDialog.newInstance(bannerEntity.name, bannerEntity.url);
//        this.webViewDialog.show(getChildFragmentManager());
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showPKRankDialog() {
//        if (this.pkRankDialog == null) {
//            this.pkRankDialog = PKRankDialog.newInstance(this.liveId, new SimpleUserCardCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.69
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//                public void onAnchorItemClickListener(AnchorEntity anchorEntity) {
//                    super.onAnchorItemClickListener(anchorEntity);
//                    SLLiveFragment.this.showUserCard(AppUtils.formatUserEntity(anchorEntity));
//                }
//            });
//            this.pkRankDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putString(ConstantUtils.RESULT_ID, this.liveId);
//        this.pkRankDialog.setArguments(this.bundleArgs);
//        showDialogFragment(this.pkRankDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showQMInteractUserDialog() {
//        SimpleQMInteractCallback simpleQMInteractCallback = new SimpleQMInteractCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.70
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleQMInteractCallback, com.slzhibo.library.ui.interfaces.OnQMInteractCallback
//            public void onSendInvitationListener(final String str, final String str2, final String str3, final String str4) {
//                if (SLLiveFragment.this.isConsumptionPermissionUserToLogin()) {
//                    if (SLLiveFragment.this.isFirstGetMyBalanceGift) {
//                        SLLiveFragment.this.isFirstGetMyBalanceGift = false;
//                        ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getUserOver(true, new ResultCallBack<MyAccountEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.70.1
//                            public void onSuccess(MyAccountEntity myAccountEntity) {
//                                SLLiveFragment.this.showQMInviteSureDialog(str, str2, str3, str4);
//                            }
//
//                            @Override // com.slzhibo.library.http.ResultCallBack
//                            public void onError(int i, String str5) {
//                                SLLiveFragment.this.showToast(R.string.fq_userover_loading_fail);
//                            }
//                        });
//                        return;
//                    }
//                    SLLiveFragment.this.showQMInviteSureDialog(str, str2, str3, str4);
//                }
//            }
//        };
//        if (this.qmInteractUserDialog == null) {
//            this.qmInteractUserDialog = QMInteractUserDialog.newInstance(this.liveId, this.anchorId, simpleQMInteractCallback);
//            this.qmInteractUserDialog.show(getChildFragmentManager());
//        } else {
//            this.bundleArgs = new Bundle();
//            this.bundleArgs.putString(ConstantUtils.RESULT_ID, this.liveId);
//            this.bundleArgs.putString(ConstantUtils.RESULT_ITEM, this.anchorId);
//            this.qmInteractUserDialog.setArguments(this.bundleArgs);
//            this.qmInteractUserDialog.setOnQMInteractCallback(simpleQMInteractCallback);
//            showDialogFragment(this.qmInteractUserDialog);
//        }
//        SysConfigInfoManager.getInstance().setEnableQMInteractRedDot(false);
//        updateMoreRedDot();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showQMInviteSureDialog(final String str, final String str2, final String str3, final String str4) {
//        if (this.myPriceBalance <= 0.0d) {
//            AppUtils.onRechargeListener(this.mContext);
//        } else if (NumberUtils.mul(NumberUtils.string2Double(str3), NumberUtils.string2Double(str4)) > this.myPriceBalance) {
//            AppUtils.onRechargeListener(this.mContext);
//        } else {
//            this.qmInviteSureDialog = SureCancelDialog.newInstance(null, this.mContext.getString(R.string.fq_qm_invite_sure_tips), this.mContext.getString(R.string.fq_btn_cancel), this.mContext.getString(R.string.fq_qm_sure_send), R.color.fq_text_black, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.71
//                @Override // android.view.View.OnClickListener
//                public void onClick(View view) {
//                }
//            }, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.72
//                @Override // android.view.View.OnClickListener
//                public void onClick(View view) {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).sendQMInteractInviteRequest(SLLiveFragment.this.liveId, str, str2, str3, str4, new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.72.1
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onSuccess(Object obj) {
//                            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                            if (sLLiveFragment.isDialogFragmentAdded(sLLiveFragment.qmInteractUserDialog)) {
//                                SLLiveFragment.this.qmInteractUserDialog.resetTask();
//                            }
//                            SLLiveFragment.this.showToast(R.string.fq_qm_interact_task_publish_success);
//                            SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                            sLLiveFragment2.dismissDialogFragment(sLLiveFragment2.qmInteractUserDialog);
//                        }
//
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onError(int i, String str5) {
//                            if (TextUtils.equals(String.valueOf(i), ConstantUtils.GIFT_NEED_UPDATE)) {
//                                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                                if (sLLiveFragment.isDialogFragmentAdded(sLLiveFragment.qmInteractUserDialog)) {
//                                    SLLiveFragment.this.qmInteractUserDialog.sendGiftRequest(true);
//                                }
//                            }
//                        }
//                    });
//                }
//            });
//            this.qmInviteSureDialog.show(getChildFragmentManager());
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showQMInteractTaskListDialog(QMInteractTaskEntity qMInteractTaskEntity) {
//        SimpleQMInteractCallback simpleQMInteractCallback = new SimpleQMInteractCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.73
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleQMInteractCallback, com.slzhibo.library.ui.interfaces.OnQMInteractCallback
//            public void onBackQMInteractConfigListener() {
//                super.onBackQMInteractConfigListener();
//                if (SLLiveFragment.this.isLiveEnd()) {
//                    SLLiveFragment.this.showToast(R.string.fq_qm_live_end_start_task_tips);
//                } else if (SLLiveFragment.this.mLiveAdBannerBottomView.isOneselfInitiateTask()) {
//                    SLLiveFragment.this.showToast(R.string.fq_qm_start_task_wait_tips);
//                } else {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).sendUserPendingTaskRequest(SLLiveFragment.this.liveId, new ResultCallBack<QMInteractTaskEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.73.1
//                        public void onSuccess(QMInteractTaskEntity qMInteractTaskEntity2) {
//                            SLLiveFragment.this.showToast(R.string.fq_qm_start_task_wait_tips);
//                        }
//
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onError(int i, String str) {
//                            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                            sLLiveFragment.dismissDialogFragment(sLLiveFragment.qmTaskListUserDialog);
//                            SLLiveFragment.this.showQMInteractUserDialog();
//                        }
//                    });
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleQMInteractCallback, com.slzhibo.library.ui.interfaces.OnQMInteractCallback
//            public void onSendGiftListener(final GiftDownloadItemEntity giftDownloadItemEntity) {
//                super.onSendGiftListener(giftDownloadItemEntity);
//                if (!SLLiveFragment.this.isConsumptionPermissionUserToLogin() || !SLLiveFragment.this.isCanSendGift()) {
//                    return;
//                }
//                if (SLLiveFragment.this.isFirstGetMyBalanceGift) {
//                    SLLiveFragment.this.isFirstGetMyBalanceGift = false;
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getUserOver(true, new ResultCallBack<MyAccountEntity>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.73.2
//                        public void onSuccess(MyAccountEntity myAccountEntity) {
//                            SLLiveFragment.this.sendGift(giftDownloadItemEntity);
//                        }
//
//                        @Override // com.slzhibo.library.http.ResultCallBack
//                        public void onError(int i, String str) {
//                            SLLiveFragment.this.showToast(R.string.fq_userover_loading_fail);
//                        }
//                    });
//                    return;
//                }
//                SLLiveFragment.this.sendGift(giftDownloadItemEntity);
//            }
//        };
//        if (this.qmTaskListUserDialog == null) {
//            this.qmTaskListUserDialog = QMTaskListUserDialog.newInstance(this.liveId, qMInteractTaskEntity, simpleQMInteractCallback);
//            this.qmTaskListUserDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putString(ConstantUtils.RESULT_ID, this.liveId);
//        this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, qMInteractTaskEntity);
//        this.qmTaskListUserDialog.setArguments(this.bundleArgs);
//        this.qmTaskListUserDialog.setOnQMInteractCallback(simpleQMInteractCallback);
//        showDialogFragment(this.qmTaskListUserDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void loadHdLotteryDrawInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, long j, String str10) {
//        if (TextUtils.equals("1", str10)) {
//            this.mLiveAdBannerBottomView.addHdLotteryWindowView(false, str, j);
//            UserEntity userEntity = this.myUserInfoEntity;
//            int partHdLotteryCount = userEntity == null ? 0 : userEntity.getPartHdLotteryCount();
//            AnchorEntity anchorEntity = this.anchorItemEntity;
//            this.hdLotteryDrawingDialog.initDrawInfo(str, str2, str3, str4, str5, str6, str7, str8, str9, j, anchorEntity != null ? anchorEntity.nickname : "", partHdLotteryCount);
//            return;
//        }
//        this.mLiveAdBannerBottomView.onLotteryEnd();
//        if (TextUtils.equals("3", str10)) {
//            this.hdLotteryDrawingDialog.onCompleteLottery(str);
//        } else {
//            this.hdLotteryDrawingDialog.onStartTimerLotteryEnd(str);
//        }
//    }
//
//    public void sendHdLotteryGift(GiftDownloadItemEntity giftDownloadItemEntity, String str) {
//        if (this.myPriceBalance <= 0.0d) {
//            AppUtils.onRechargeListener(this.mContext);
//            return;
//        }
//        if (TextUtils.equals("2", str) && !AppUtils.isAttentionAnchor(this.anchorId)) {
//            attentionAnchorAction(true, this.anchorId);
//        }
//        sendGift(giftDownloadItemEntity);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showHdLotteryDrawDialog() {
//        HdLotteryDrawingDialog hdLotteryDrawingDialog = this.hdLotteryDrawingDialog;
//        if (hdLotteryDrawingDialog != null) {
//            hdLotteryDrawingDialog.show();
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showPayLiveEvaluationDialog() {
//        if (this.liveEndEvaluationDialog == null) {
//            this.liveEndEvaluationDialog = LiveEndEvaluationDialog.newInstance(this.liveId, this.liveCount, this.chargeType, this.livingStartTime, this.ticketPrice);
//            this.liveEndEvaluationDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putString("liveId", this.liveId);
//        this.bundleArgs.putString("liveCount", this.liveCount);
//        this.bundleArgs.putString("chargeType", this.chargeType);
//        this.bundleArgs.putLong("watchDuration", this.livingStartTime);
//        this.bundleArgs.putString("ticketPrice", this.ticketPrice);
//        this.liveEndEvaluationDialog.setArguments(this.bundleArgs);
//        showDialogFragment(this.liveEndEvaluationDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLinkVoiceApplyDetailDialog(boolean z) {
//        boolean isVoiceRoomType = isVoiceRoomType();
//        if (isConsumptionPermissionUserToLogin()) {
//            if (isLiveEnd()) {
//                showToast(isVoiceRoomType ? R.string.fq_yy_live_end_start_link_tips_2 : R.string.fq_yy_live_end_start_link_tips);
//            } else if (this.isEnablePK) {
//                showToast(R.string.fq_yy_pk_enable_tips);
//            } else if (!this.isBanGroup || this.isRTCStream) {
//                SimpleYYLinkCallback simpleYYLinkCallback = new SimpleYYLinkCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.74
//                    @Override // com.slzhibo.library.ui.interfaces.impl.SimpleYYLinkCallback, com.slzhibo.library.ui.interfaces.YYLinkCallback
//                    public void onDisconnectLinkListener() {
//                        super.onDisconnectLinkListener();
//                        if (SLLiveFragment.this.isVoiceRoomType()) {
//                            SLLiveFragment.this.updateYYLinkIconView(null);
//                            SLLiveFragment.this.stopRTC();
//                        }
//                        if (SLLiveFragment.this.isVideoRoomType()) {
//                            SLLiveFragment.this.disconnectVideoLink(false);
//                        }
//                    }
//                };
//                int currentLinkMode = this.yyLinkSeatListView.getCurrentLinkMode();
//                if (this.linkSendApplyDialog == null) {
//                    this.linkSendApplyDialog = YYLinkSendApplyDialog.newInstance(this.anchorItemEntity, currentLinkMode, isVoiceRoomType, this.isRTCStream, z, simpleYYLinkCallback);
//                    this.linkSendApplyDialog.show(getChildFragmentManager());
//                    return;
//                }
//                this.bundleArgs = new Bundle();
//                this.bundleArgs.putInt(ConstantUtils.RESULT_COUNT, currentLinkMode);
//                this.bundleArgs.putBoolean(ConstantUtils.RESULT_FLAG, isVoiceRoomType);
//                this.bundleArgs.putBoolean(ConstantUtils.RESULT_ENTER_SOURCE, this.isRTCStream);
//                this.bundleArgs.putBoolean(ConstantUtils.RESULT_FLAG_VAR, z);
//                this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, this.anchorItemEntity);
//                this.linkSendApplyDialog.setArguments(this.bundleArgs);
//                this.linkSendApplyDialog.setLinkCallback(simpleYYLinkCallback);
//                showDialogFragment(this.linkSendApplyDialog);
//            } else {
//                showToast(R.string.fq_yy_pk_ban_group_code_tips);
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showAnchorInviteUserDialog(String str) {
//        SimpleYYLinkCallback simpleYYLinkCallback = new SimpleYYLinkCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.75
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleYYLinkCallback, com.slzhibo.library.ui.interfaces.YYLinkCallback
//            public void onUserAcceptRefuseLinkListener(boolean z, boolean z2, YYRTCEntity yYRTCEntity) {
//                if (z2 && yYRTCEntity != null) {
//                    SLLiveFragment.this.startRTC(yYRTCEntity.rtcAppId, yYRTCEntity.rtcToken, NumberUtils.string2int(yYRTCEntity.rtcUid), yYRTCEntity.rtcRoomId);
//                    SLLiveFragment.this.showLinkVoiceApplyDetailDialog(true);
//                }
//            }
//        };
//        boolean isVoiceRoomType = isVoiceRoomType();
//        if (this.linkAnchorInviteDialog == null) {
//            this.linkAnchorInviteDialog = YYLinkAnchorInviteDialog.newInstance(this.liveId, String.valueOf(this.liveCount), str, isVoiceRoomType, simpleYYLinkCallback);
//            this.linkAnchorInviteDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putString(ConstantUtils.RESULT_ID, this.liveId);
//        this.bundleArgs.putString(ConstantUtils.RESULT_COUNT, String.valueOf(this.liveCount));
//        this.bundleArgs.putString(ConstantUtils.RESULT_ITEM, str);
//        this.bundleArgs.putBoolean(ConstantUtils.RESULT_FLAG, isVoiceRoomType);
//        this.linkAnchorInviteDialog.setArguments(this.bundleArgs);
//        this.linkAnchorInviteDialog.setLinkCallback(simpleYYLinkCallback);
//        this.linkAnchorInviteDialog.show(getChildFragmentManager());
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLinkActionMenuDialog(YYLinkApplyEntity yYLinkApplyEntity) {
//        if (yYLinkApplyEntity != null) {
//            SimpleYYLinkCallback simpleYYLinkCallback = new SimpleYYLinkCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.76
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleYYLinkCallback, com.slzhibo.library.ui.interfaces.YYLinkCallback
//                public void onLinkActionMenuListener(int i, YYLinkApplyEntity yYLinkApplyEntity2) {
//                    if (yYLinkApplyEntity2 != null) {
//                        if (i == 4) {
//                            SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                            sLLiveFragment.dismissDialogFragment(sLLiveFragment.linkActionMenuDialog);
//                            SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                            sLLiveFragment2.showUserCard(sLLiveFragment2.formatUserEntity(yYLinkApplyEntity2));
//                        } else if (i == 1) {
//                            if (NumberUtils.string2int(yYLinkApplyEntity2.surplusNum) > 0) {
//                                SLLiveFragment.this.showLikeSeatView(yYLinkApplyEntity2);
//                                SLLiveFragment sLLiveFragment3 = SLLiveFragment.this;
//                                sLLiveFragment3.dismissDialogFragment(sLLiveFragment3.linkActionMenuDialog);
//                                return;
//                            }
//                            SLLiveFragment.this.showToast(R.string.fq_yy_like_count_over);
//                        } else if (i == 3) {
//                            SLLiveFragment sLLiveFragment4 = SLLiveFragment.this;
//                            sLLiveFragment4.dismissDialogFragment(sLLiveFragment4.linkActionMenuDialog);
//                        }
//                    }
//                }
//            };
//            if (this.linkActionMenuDialog == null) {
//                this.linkActionMenuDialog = YYLinkActionMenuDialog.newInstance(this.liveId, String.valueOf(this.liveCount), yYLinkApplyEntity, simpleYYLinkCallback);
//                this.linkActionMenuDialog.show(getChildFragmentManager());
//                return;
//            }
//            this.bundleArgs = new Bundle();
//            this.bundleArgs.putString(ConstantUtils.RESULT_ID, this.liveId);
//            this.bundleArgs.putString(ConstantUtils.RESULT_COUNT, String.valueOf(this.liveCount));
//            this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, yYLinkApplyEntity);
//            this.linkActionMenuDialog.setArguments(this.bundleArgs);
//            this.linkActionMenuDialog.setLinkCallback(simpleYYLinkCallback);
//            showDialogFragment(this.linkActionMenuDialog);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLinkNoticeManageDialog() {
//        String liveTitle = this.mLiveChatMsgView.getLiveTitle();
//        String linkNoticeContent = this.mLivePusherInfoView.getLinkNoticeContent();
//        if (this.noticeManageDialog == null) {
//            this.noticeManageDialog = YYNoticeManageDialog.newInstance(this.liveId, liveTitle, linkNoticeContent);
//            this.noticeManageDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putString(ConstantUtils.RESULT_ID, this.liveId);
//        this.bundleArgs.putString(ConstantUtils.RESULT_ITEM, liveTitle);
//        this.bundleArgs.putString(ConstantUtils.RESULT_FLAG, linkNoticeContent);
//        this.noticeManageDialog.setArguments(this.bundleArgs);
//        showDialogFragment(this.noticeManageDialog);
//    }
//
//    private void showLinkUserDisconnectLinkDialog(View.OnClickListener onClickListener) {
//        this.linkUserDisconnectLinkDialog = SureCancelDialog.newInstance(null, getString(R.string.fq_yy_link_leave_tips), getString(R.string.fq_btn_cancel), getString(R.string.fq_yy_link_leave), R.color.fq_text_black, onClickListener);
//        this.linkUserDisconnectLinkDialog.show(getChildFragmentManager());
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showProductRecommendDialog(HJProductEntity hJProductEntity) {
//        String str = this.liveItemEntity.shelfId;
//        SimpleOnHJProductCallback simpleOnHJProductCallback = new SimpleOnHJProductCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.77
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleOnHJProductCallback, com.slzhibo.library.ui.interfaces.OnHJProductCallback
//            public void onBuyProductSuccessCallback(MyAccountEntity myAccountEntity) {
//                super.onBuyProductSuccessCallback(myAccountEntity);
//                SLLiveFragment.this.onUserOverSuccess(myAccountEntity);
//            }
//        };
//        if (this.productRecommendDialog == null) {
//            this.productRecommendDialog = HJProductRecommendDialog.newInstance(str, this.anchorId, hJProductEntity, simpleOnHJProductCallback);
//            this.productRecommendDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putString(ConstantUtils.RESULT_ID, str);
//        this.bundleArgs.putString(ConstantUtils.RESULT_FLAG, this.anchorId);
//        this.bundleArgs.putParcelable(ConstantUtils.RESULT_ITEM, hJProductEntity);
//        this.productRecommendDialog.setArguments(this.bundleArgs);
//        this.productRecommendDialog.setOnHJProductCallback(simpleOnHJProductCallback);
//        showDialogFragment(this.productRecommendDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLYControlWindowDialog(int i) {
//        if (!isBluetoothConnection()) {
//            showToast(R.string.fq_ly_anchor_bluetooth_tips);
//            return;
//        }
//        SimpleOnLYDeviceCallback simpleOnLYDeviceCallback = new SimpleOnLYDeviceCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.78
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleOnLYDeviceCallback, com.slzhibo.library.ui.interfaces.OnLYDeviceCallback
//            public void onModelSelectedCallback(int i2, LYModelDataEntity.Data data) {
//                super.onModelSelectedCallback(i2, data);
//                SLLiveFragment.this.showLYSendGiftTipsDialog(false, i2, data);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimpleOnLYDeviceCallback, com.slzhibo.library.ui.interfaces.OnLYDeviceCallback
//            public void onShowControlWindowView() {
//                super.onShowControlWindowView();
//                SLLiveFragment.this.showLYControlWindowViewStub(true);
//            }
//        };
//        if (this.lyControlWindowDialog == null) {
//            this.lyControlWindowDialog = LYControlWindowDialog.newInstance(this.liveId, i, simpleOnLYDeviceCallback);
//            this.lyControlWindowDialog.show(getChildFragmentManager());
//            return;
//        }
//        this.bundleArgs = new Bundle();
//        this.bundleArgs.putString(ConstantUtils.RESULT_ID, this.liveId);
//        this.bundleArgs.putInt(ConstantUtils.RESULT_FLAG, i);
//        this.lyControlWindowDialog.setArguments(this.bundleArgs);
//        this.lyControlWindowDialog.setCallback(simpleOnLYDeviceCallback);
//        showDialogFragment(this.lyControlWindowDialog);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLYControlWindowViewStub(boolean z) {
//        if (!isBluetoothConnection()) {
//            showToast(R.string.fq_ly_anchor_bluetooth_tips);
//            return;
//        }
//        if (this.lyControlWindowView == null) {
//            this.lyControlWindowView = (LYControlWindowView) this.lyControlWindowViewStub.inflate();
//        }
//        if (!z || this.lyControlWindowView.getVisibility() != View.VISIBLE) {
//            this.lyControlWindowView.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
//            this.lyControlWindowView.updateModelData(this.liveId);
//            this.lyControlWindowView.setCallback(new SimpleOnLYDeviceCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.79
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleOnLYDeviceCallback, com.slzhibo.library.ui.interfaces.OnLYDeviceCallback
//                public void onModelSelectedCallback(int i, LYModelDataEntity.Data data) {
//                    super.onModelSelectedCallback(i, data);
//                    if (i != -2) {
//                        SLLiveFragment.this.showLYSendGiftTipsDialog(true, i, data);
//                    } else if (SLLiveFragment.this.isConsumptionPermissionUserToLogin()) {
//                        SLLiveFragment.this.showLYControlWindowDialog(-2);
//                    }
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleOnLYDeviceCallback, com.slzhibo.library.ui.interfaces.OnLYDeviceCallback
//                public void onShowModelDataDialog() {
//                    super.onShowModelDataDialog();
//                    SLLiveFragment.this.showLYControlWindowDialog(0);
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleOnLYDeviceCallback, com.slzhibo.library.ui.interfaces.OnLYDeviceCallback
//                public void onFreeCountdownCompleteCallback() {
//                    super.onFreeCountdownCompleteCallback();
//                    SLLiveFragment.this.mLiveAdBannerBottomView.showLYBluetoothView(true);
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleOnLYDeviceCallback, com.slzhibo.library.ui.interfaces.OnLYDeviceCallback
//                public void onClose() {
//                    if (SLLiveFragment.this.lyControlWindowDialog != null) {
//                        SLLiveFragment.this.lyControlWindowDialog.resetCustomPaint();
//                    }
//                }
//            });
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLYSendGiftTipsDialog(boolean z, int i, final LYModelDataEntity.Data data) {
//        GiftDownloadItemEntity shakeGiftItem;
//        if (isConsumptionPermissionUserToLogin()) {
//            if (isSocketClose()) {
//                showToast(R.string.fq_ly_socket_error_send_tips);
//            } else if (i == 5) {
//                ((SLLivePresenter) this.mPresenter).onSendBluetoothSendRequest(this.liveId, new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.80
//                    @Override // com.slzhibo.library.http.ResultCallBack
//                    public void onError(int i2, String str) {
//                    }
//
//                    @Override // com.slzhibo.library.http.ResultCallBack
//                    public void onSuccess(Object obj) {
//                        SLLiveFragment.this.mLiveAdBannerBottomView.showLYBluetoothView(false);
//                        if (SLLiveFragment.this.lyControlWindowView != null) {
//                            SLLiveFragment.this.lyControlWindowView.startCountdown();
//                        }
//                    }
//                });
//            } else if (data != null && (shakeGiftItem = GiftDownLoadManager.getInstance().getShakeGiftItem()) != null) {
//                final ResultCallBack<Object> resultCallBack = new ResultCallBack<Object>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.81
//                    @Override // com.slzhibo.library.http.ResultCallBack
//                    public void onSuccess(Object obj) {
//                        SLLiveFragment.this.showLYControlWindowViewStub(true);
//                    }
//
//                    @Override // com.slzhibo.library.http.ResultCallBack
//                    public void onError(int i2, String str) {
//                        if (TextUtils.equals(String.valueOf(i2), ConstantUtils.GIFT_NEED_UPDATE)) {
//                            SLLiveFragment.this.onGiftResUpdateAndTips(false);
//                            if (SLLiveFragment.this.lyControlWindowView != null) {
//                                SLLiveFragment.this.lyControlWindowView.setVisibility(View.INVISIBLE);
//                            }
//                        }
//                    }
//                };
//                final String str = shakeGiftItem.markId;
//                if (!z || i == -2) {
//                    ((SLLivePresenter) this.mPresenter).onSendBluetoothSendRequest(data.id, data.waveBand, this.liveId, str, data.totalPrice, resultCallBack);
//                } else if (!SysConfigInfoManager.getInstance().isLYSendGiftPrompt(i)) {
//                    String string = getString(R.string.fq_ly_send_gift_tips, String.valueOf(NumberUtils.string2long(data.totalPrice) / NumberUtils.string2long(shakeGiftItem.price, 1L)), shakeGiftItem.name, AppUtils.formatDisplayPrice(data.totalPrice, false), data.name);
//                    View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.82
//                        @Override // android.view.View.OnClickListener
//                        public void onClick(View view) {
//                            SLLivePresenter sLLivePresenter = (SLLivePresenter) ( SLLiveFragment.this).mPresenter;
//                            LYModelDataEntity.Data data2 = data;
//                            sLLivePresenter.onSendBluetoothSendRequest(data2.id, data2.waveBand, SLLiveFragment.this.liveId, str, data.totalPrice, resultCallBack);
//                        }
//                    };
//                    if (this.lySendGiftTipsDialog == null) {
//                        this.lySendGiftTipsDialog = LYSendGiftTipsDialog.newInstance(string, i, onClickListener);
//                        this.lySendGiftTipsDialog.show(getChildFragmentManager());
//                        return;
//                    }
//                    this.bundleArgs = new Bundle();
//                    this.bundleArgs.putString(ConstantUtils.RESULT_ITEM, string);
//                    this.bundleArgs.putInt(ConstantUtils.RESULT_FLAG, i);
//                    this.lySendGiftTipsDialog.setArguments(this.bundleArgs);
//                    this.lySendGiftTipsDialog.setOnSureListener(onClickListener);
//                    showDialogFragment(this.lySendGiftTipsDialog);
//                } else {
//                    ((SLLivePresenter) this.mPresenter).onSendBluetoothSendRequest(data.id, data.waveBand, this.liveId, str, data.totalPrice, resultCallBack);
//                }
//            }
//        }
//    }
//
//    private void loadOperationActivityAdDialog() {
//        ArrayList<ActivityListEntity> operateActivityListByType;
//        if (!this.isOperationActivityAdDialog.get() && (operateActivityListByType = CacheUtils.getOperateActivityListByType(true)) != null) {
//            Observable.just(operateActivityListByType).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.83
//                public void accept(ArrayList<ActivityListEntity> arrayList) throws Exception {
//                    if (arrayList != null && !arrayList.isEmpty()) {
//                        Iterator<ActivityListEntity> it2 = arrayList.iterator();
//                        while (it2.hasNext()) {
//                            ActivityListEntity next = it2.next();
//                            if (next.isCorrectLink()) {
//                                ActivityDBEntity activityItemInfoById = DBUtils.getActivityItemInfoById(next.id);
//                                DBUtils.saveOrUpdateActivityItemInfo(next);
//                                if (activityItemInfoById == null) {
//                                    SLLiveFragment.this.showGiftBagWebViewDialog(next);
//                                } else if (activityItemInfoById.isShowActivityDialog()) {
//                                    SLLiveFragment.this.showGiftBagWebViewDialog(next);
//                                }
//                            }
//                        }
//                    }
//                }
//            });
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showGiftBagWebViewDialog(ActivityListEntity activityListEntity) {
//        GiftBagWebViewDialog.newInstance(activityListEntity).show(getChildFragmentManager());
//        this.isOperationActivityAdDialog.set(true);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void disconnectVideoLink(final boolean z) {
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$1EpsyZ3FCZA8cpEm9N9cXQW9rDE
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$disconnectVideoLink$76$SLLiveFragment(z);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$disconnectVideoLink$76$SLLiveFragment(boolean z) {
//        stopRTC();
//        dismissDialogFragment(this.linkSendApplyDialog);
//        this.linkSendApplyDialog = null;
//        this.mLiveAdBannerBottomView.dismissYYSmallWindow();
//        if (z) {
//            ((SLLivePresenter) this.mPresenter).onSendVideoRoomUserRTCErrorRequest(this.liveId, this.liveCount);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void updateYYLinkIconView(String str) {
//        if (!TextUtils.isEmpty(str)) {
//            this.ivYYLink.setImageResource(TextUtils.equals(str, "0") ? R.drawable.fq_ic_anchor_connect_ing : R.drawable.fq_ic_anchor_connect_off);
//        } else {
//            this.ivYYLink.setImageResource(R.drawable.fq_ic_anchor_connect_default);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showDialogFragment(BaseRxDialogFragment baseRxDialogFragment) {
//        if (baseRxDialogFragment != null && !baseRxDialogFragment.isAdded()) {
//            baseRxDialogFragment.show(getChildFragmentManager());
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void dismissDialogFragment(BaseRxDialogFragment baseRxDialogFragment) {
//        if (baseRxDialogFragment != null && baseRxDialogFragment.isAdded()) {
//            baseRxDialogFragment.dismiss();
//        }
//    }
//
//    private void dismissDialogFragment(BaseRxDialogFragment... baseRxDialogFragmentArr) {
//        for (BaseRxDialogFragment baseRxDialogFragment : baseRxDialogFragmentArr) {
//            if (baseRxDialogFragment != null && baseRxDialogFragment.isAdded()) {
//                baseRxDialogFragment.dismiss();
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public boolean isShowDialogFragment(BaseRxDialogFragment baseRxDialogFragment) {
//        return baseRxDialogFragment != null && baseRxDialogFragment.isResumed();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public boolean isDialogFragmentAdded(BaseRxDialogFragment baseRxDialogFragment) {
//        return baseRxDialogFragment != null && baseRxDialogFragment.isAdded();
//    }
//
//    private void noticeClickToActivity(final String str) {
//        RxTimerUtils.getInstance().timer(this.mContext, 200L, TimeUnit.MILLISECONDS, new RxTimerUtils.RxTimerAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$iDDxXY98XeWfHmhZxBQwdtO0Jqs
//            @Override // com.slzhibo.library.utils.RxTimerUtils.RxTimerAction
//            public final void action(long j) {
//                SLLiveFragment.this.lambda$noticeClickToActivity$77$SLLiveFragment(str, j);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$noticeClickToActivity$77$SLLiveFragment(String str, long j) {
//        startActivityById(str);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void onAdBannerClick(BannerEntity bannerEntity) {
//        if (bannerEntity != null) {
//            if (bannerEntity.isLiveSDKCallback()) {
//                AppUtils.onCustomAdBannerClickListener(this.mContext, bannerEntity.url);
//            } else if (bannerEntity.isGameComponents()) {
//                ComponentsEntity localCacheComponentsById = CacheUtils.getLocalCacheComponentsById(bannerEntity.componentId);
//                if (localCacheComponentsById == null || !localCacheComponentsById.isCacheLotteryComponents()) {
//                    showComponentsWebViewDialog(true, localCacheComponentsById);
//                } else {
//                    showLotteryDialog();
//                }
//            } else if (bannerEntity.isJumpLiveRoom()) {
//                startActivityById(bannerEntity.url);
//            } else if (!bannerEntity.isJumpWebUrl()) {
//            } else {
//                if (bannerEntity.isJumpCustomUrl()) {
//                    AppUtils.onSysWebView(this.mContext, bannerEntity.url);
//                } else {
//                    showWebViewH5Dialog(bannerEntity);
//                }
//            }
//        }
//    }
//
//    private void onLotteryBoomOpen(String str, int i, int i2, int i3, int i4) {
//        this.isLotteryBoomStatus = true;
//        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
//        if (componentsEntity == null || !componentsEntity.isCacheLotteryComponents()) {
//            this.ivComponentsView.onLotteryBoomOpen(str, i, i2, i3, i4);
//        } else {
//            this.ivRecommendComponents.onLotteryBoomOpen(str, i, i2, i3, i4);
//        }
//    }
//
//    private void onLotteryBoomClosed() {
//        this.isLotteryBoomStatus = false;
//        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
//        if (componentsEntity == null || !componentsEntity.isCacheLotteryComponents()) {
//            this.ivComponentsView.onLotteryBoomClosed();
//        } else {
//            this.ivRecommendComponents.onLotteryBoomClosed();
//        }
//    }
//
//    private boolean isLuxuryBoomStatus() {
//        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
//        return (componentsEntity == null || !componentsEntity.isCacheLotteryComponents()) ? this.ivComponentsView.isBoomStatus() && this.ivComponentsView.isLuxuryBoomType() : this.ivRecommendComponents.isBoomStatus() && this.ivRecommendComponents.isLuxuryBoomType();
//    }
//
//    private void showLinkPKLayoutView(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, long j2, boolean z, boolean z2) {
//        if (this.mPKInfoView == null) {
//            this.mPKInfoView = (PKInfoView) this.vsPKInfoView.inflate();
//        }
//        this.isEnablePK = true;
//        this.mLivePusherInfoView.setLiveAdBannerViewVisibility(false);
//        this.rootView.setBackgroundResource(R.drawable.fq_ic_pk_main_bottom_bg);
//        ReSizeUtils.reSizeAudienceViewSmall(this.rootView.getChildAt(0), getContributionViewTopMarginHeight());
//        this.mPKInfoView.adjustViewLayout(ReSizeUtils.getPKVideoViewHeight(), getPKViewTopMarginHeight());
//        if (z2) {
//            this.mPKInfoView.showLMSuccessView(str, str2, str3, str4, str5, str6, str7, j, j2, z);
//        } else {
//            this.mPKInfoView.showLMSuccessView(str, str2, str3, str4, str5, str6, str7, true);
//        }
//        this.mPKInfoView.setOnPkViewListener(new OnPkViewListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.84
//            @Override // com.slzhibo.library.ui.interfaces.OnPkViewListener
//            public void onReadyPK(View view) {
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnPkViewListener
//            public void onAttentionAnchor(String str8, View view) {
//                if (SLLiveFragment.this.isConsumptionPermissionUserToLogin()) {
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).attentionAnchor(str8, 1);
//                    SLLiveFragment.this.showToast(R.string.fq_text_attention_success);
//                    DBUtils.attentionAnchor(str8, true);
//                    view.setVisibility(View.INVISIBLE);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnPkViewListener
//            public void onEnterLiveRoom(String str8) {
//                SLLiveFragment.this.startActivityById(str8);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnPkViewListener
//            public void onShowPKRanking() {
//                SLLiveFragment.this.showPKRankDialog();
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.OnPkViewListener
//            public void onPkCountDownComplete() {
//                SLLiveFragment.this.mPKTimerDisposable = Observable.timer(30L, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.84.1
//                    public void accept(Long l) throws Exception {
//                        if (SLLiveFragment.this.linkMicPKType.get() != 281 || SLLiveFragment.this.linkMicPKType.get() != 288) {
//                            ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onGetFP(SLLiveFragment.this.liveId, SLLiveFragment.this.liveItemEntity.isPKEnd(), true);
//                        }
//                    }
//                });
//                ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).compositeDisposableAdd(SLLiveFragment.this.mPKTimerDisposable);
//            }
//        });
//    }
//
//    private void stopLinkMicPk() {
//        stopPKTimerDisposable();
//        this.mLivePusherInfoView.setLiveAdBannerViewVisibility(true);
//        this.linkMicPKType.set(288);
//        ReSizeUtils.reAudienceSizeViewBig(this.rootView.getChildAt(0));
//        this.rootView.setBackgroundResource(0);
//        PKInfoView pKInfoView = this.mPKInfoView;
//        if (pKInfoView != null) {
//            pKInfoView.onRelease();
//        }
//        this.isEnablePK = false;
//    }
//
//    private void initLinkPKAssistData(String str, String str2, String str3, List<String> list, String str4, String str5, String str6, List<String> list2) {
//        PKInfoView pKInfoView = this.mPKInfoView;
//        if (pKInfoView != null) {
//            pKInfoView.initAssistData(str, str2, str3, list, str4, str5, str6, list2);
//        }
//    }
//
//    private void onLinkPKEnd(String str, String str2, String str3, List<String> list, String str4, String str5, String str6, List<String> list2, boolean z) {
//        this.linkMicPKType.set(ConstantUtils.PK_TYPE_PK_ENDING);
//        PKInfoView pKInfoView = this.mPKInfoView;
//        if (pKInfoView != null) {
//            pKInfoView.onPKEnd(str, str2, str3, list, str4, str5, str6, list2, z);
//        }
//    }
//
//    private int getContributionViewTopMarginHeight() {
//        return this.mLivePusherInfoView.getContributionViewTopMarginHeight();
//    }
//
//    private int getPKViewTopMarginHeight() {
//        return getContributionViewTopMarginHeight() - this.titleTopView.getHeight();
//    }
//
//    private void watchRecordReport() {
//        if (AppUtils.isConsumptionPermissionUser() && this.livingStartTime != 0 && isLiving()) {
//            this.livingEndTime = System.currentTimeMillis();
//            long j = ((this.livingEndTime - this.livingStartTime) % 3600000) / 60000;
//            if (j >= 1) {
//                ((SLLivePresenter) this.mPresenter).watchHistoryReport(this.liveId, j);
//            }
//        }
//    }
//
//    private boolean isPayLiveNeedBuyTicket() {
//        LiveEntity liveEntity = this.liveListItemEntity;
//        return liveEntity != null && liveEntity.isPayLiveNeedBuyTicket();
//    }
//
//    private boolean isPayLiveTicket() {
//        LiveEntity liveEntity = this.liveListItemEntity;
//        return liveEntity != null && liveEntity.isPayLiveTicket();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public boolean isLiving() {
//        return this.liveStatus;
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public boolean isLiveEnd() {
//        return this.isLiveEnd;
//    }
//
//    private void updateAnchorContribution(final SocketMessageEvent.ResultData resultData) {
//        if (resultData != null) {
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$KcLia5f_bS_fkXx6fCIxk1Acf_U
//                @Override // java.lang.Runnable
//                public final void run() {
//                    SLLiveFragment.this.lambda$updateAnchorContribution$78$SLLiveFragment(resultData);
//                }
//            });
//        }
//    }
//
//    public /* synthetic */ void lambda$updateAnchorContribution$78$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        this.mLivePusherInfoView.setAnchorContribution(AppUtils.formatContributionValue(resultData));
//    }
//
//    private void onNoEnterLivePermission(String str) {
//        if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
//            onFinishActivity();
//            if (!SLLiveSDK.getSingleton().sdkCallbackListener.onEnterLivePermissionListener(this.mContext, 2)) {
//                showToast(str);
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void onFinishActivity() {
//        stopSocket();
//        this.mActivity.onBackPressed();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    @SuppressLint("WrongConstant")
//    public void showContentView(int i, boolean z) {
//        int i2 = 0;
//        this.ivClosed.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
//        this.mRlControllerView.setVisibility(i == 256 ? View.VISIBLE : View.INVISIBLE);
//        this.rlWatermarkShadowBg.setVisibility(i == 256 ? View.VISIBLE : View.INVISIBLE);
//        LivePayEnterView livePayEnterView = this.livePayEnterView;
//        if (livePayEnterView != null) {
//            livePayEnterView.setVisibility(i == 259 ? View.VISIBLE : View.INVISIBLE);
//        }
//        LiveEndInfoView liveEndInfoView = this.mLiveEndInfoView;
//        if (liveEndInfoView != null) {
//            if (i != 257) {
//                i2 = 4;
//            }
//            liveEndInfoView.setVisibility(i2);
//        }
//    }
//
//    private void stopPKTimerDisposable() {
//        Disposable disposable = this.mPKTimerDisposable;
//        if (disposable != null && !disposable.isDisposed()) {
//            this.mPKTimerDisposable.dispose();
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void setViewPagerScrollEnable(boolean z) {
//        OnFragmentInteractionListener onFragmentInteractionListener = this.onFragmentInteractionListener;
//        if (onFragmentInteractionListener != null) {
//            onFragmentInteractionListener.setViewPagerScroll(z);
//        }
//    }
//
//    private void initWatermarkConfig() {
//        WatermarkConfigEntity watermarkConfig = SysConfigInfoManager.getInstance().getWatermarkConfig();
//        if (watermarkConfig != null) {
//            StringBuilder sb = new StringBuilder();
//            if (watermarkConfig.isEnableLiveRoom()) {
//                sb.append(getString(R.string.fq_live_room_num, this.liveId));
//            }
//            if (watermarkConfig.isEnableDate()) {
//                if (watermarkConfig.isEnableLiveRoom()) {
//                    sb.append(" | ");
//                }
//                sb.append(DateUtils.getCurrentDateTime("yyyy.MM.dd"));
//            }
//            this.tvWatermarkRoom.setText(sb.toString());
//            this.tvWatermarkTitle.setText(watermarkConfig.platform);
//            this.tvWatermarkUrl.setText(watermarkConfig.downloadUrl);
//            if (!TextUtils.isEmpty(watermarkConfig.logoUrl)) {
//                GlideUtils.loadImage(this.mContext, this.ivWatermarkLogo, watermarkConfig.logoUrl, R.drawable.fq_ic_live_watermark);
//            }
//        }
//    }
//
//    private void uploadLeaveLiveEvent() {
//        LogEventUtils.shutDownTimerTask("LeaveRoom", false);
//        if (this.livingStartTime != 0 && isLiving()) {
//            this.livingEndTime = System.currentTimeMillis();
//            long j = ((this.livingEndTime - this.livingStartTime) % 3600000) / 60000;
//            if (j >= 1) {
//                LogEventUtils.uploadLeaveRoom(this.anchorItemEntity, String.valueOf(j), this.liveId, this.myUserInfoEntity.expGrade);
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void saveLiveUploadData() {
//        LiveDataEntity liveDataEntity = new LiveDataEntity();
//        liveDataEntity.liveId = this.liveId;
//        AnchorEntity anchorEntity = this.anchorItemEntity;
//        liveDataEntity.anchorId = anchorEntity.openId;
//        liveDataEntity.appId = anchorEntity.appId;
//        liveDataEntity.endTime = System.currentTimeMillis();
//        liveDataEntity.startTime = this.livingStartTime;
//        AnchorEntity anchorEntity2 = this.anchorItemEntity;
//        liveDataEntity.expGrade = anchorEntity2.expGrade;
//        liveDataEntity.nickname = anchorEntity2.nickname;
//        liveDataEntity.tag = anchorEntity2.tag;
//        liveDataEntity.viewerLevel = this.myUserInfoEntity.expGrade;
//        DBUtils.saveOrUpdateLiveData(liveDataEntity);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void updateUserBalance(String str) {
//        this.myPriceBalance = NumberUtils.string2Double(str);
//        if (this.myPriceBalance < 0.0d) {
//            this.myPriceBalance = 0.0d;
//        }
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.setUserBalance(this.myPriceBalance);
//        }
//        LotteryDialog lotteryDialog = this.mLotteryDialog;
//        if (lotteryDialog != null) {
//            lotteryDialog.setUserBalance(this.myPriceBalance);
//        }
//    }
//
//    private void addPrivateMsgData(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
//        final MsgDetailListEntity msgDetailListEntity = new MsgDetailListEntity();
//        msgDetailListEntity.userId = UserInfoManager.getInstance().getUserId();
//        msgDetailListEntity.targetId = str2;
//        msgDetailListEntity.targetAvatar = str3;
//        msgDetailListEntity.msg = str5;
//        msgDetailListEntity.type = 2;
//        msgDetailListEntity.targetName = str4;
//        msgDetailListEntity.time = String.valueOf(System.currentTimeMillis());
//        msgDetailListEntity.status = 1;
//        msgDetailListEntity.winningFlag = str6;
//        msgDetailListEntity.flagContent = str7;
//        msgDetailListEntity.messageId = str;
//        DBUtils.saveOnePrivateMsgDetail(msgDetailListEntity);
//        final MsgListEntity msgListEntity = new MsgListEntity();
//        msgListEntity.userId = UserInfoManager.getInstance().getUserId();
//        msgListEntity.appId = UserInfoManager.getInstance().getAppId();
//        msgListEntity.targetId = str2;
//        msgListEntity.time = String.valueOf(System.currentTimeMillis());
//        DBUtils.saveOrUpdateMsgList(msgListEntity);
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$pvEFijyGSzyE8Ezq9vmQ8sFCG8g
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$addPrivateMsgData$79$SLLiveFragment(msgListEntity, msgDetailListEntity);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$addPrivateMsgData$79$SLLiveFragment(MsgListEntity msgListEntity, MsgDetailListEntity msgDetailListEntity) {
//        PrivateMsgDialog privateMsgDialog = this.privateMsgDialog;
//        if (privateMsgDialog == null || !privateMsgDialog.isAdded()) {
//            MsgStatusEntity msgStatusEntity = new MsgStatusEntity();
//            msgStatusEntity.appId = UserInfoManager.getInstance().getAppId();
//            msgStatusEntity.userId = UserInfoManager.getInstance().getUserId();
//            msgStatusEntity.readStatus = "0";
//            DBUtils.saveOrUpdateMsgStatus(msgStatusEntity);
//            updateMoreRedDot();
//            return;
//        }
//        this.privateMsgDialog.dealMsg(msgListEntity, msgDetailListEntity);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showPayLiveTips() {
//        this.mLivePusherInfoView.setChargeTypeTips(isPayLiveTicket(), this.ticketPrice);
//        if (SysConfigInfoManager.getInstance().isEnableRatingGuide() && isPayLiveTicket() && this.showGuideRating) {
//            this.mLivePusherInfoView.showGuideRating(this.mActivity);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void payLiveTipsDialogOnRelease() {
//        PayLiveTipsDialog payLiveTipsDialog = this.payLiveTipsDialog;
//        if (payLiveTipsDialog != null) {
//            this.isPayLiveTipsDialog = false;
//            payLiveTipsDialog.compositeDisposableClear();
//            dismissDialogFragment(this.payLiveTipsDialog);
//            this.payLiveTipsDialog = null;
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showRecommendComponentsView() {
//        this.cacheRecommendComponents = CacheUtils.getLocalCacheRecommendComponents(this.liveRecommendGameId);
//        ComponentsEntity componentsEntity = this.cacheRecommendComponents;
//        if (componentsEntity == null) {
//            this.ivRecommendComponents.setVisibility(View.GONE);
//        } else if (componentsEntity.isCacheLotteryComponents()) {
//            if (AppUtils.isEnableTurntable()) {
//                this.ivRecommendComponents.setVisibility(View.VISIBLE);
//                this.ivRecommendComponents.initCoverDrawableRes(R.drawable.fq_ic_lottery);
//                return;
//            }
//            this.ivRecommendComponents.setVisibility(View.GONE);
//        } else if (!TextUtils.isEmpty(this.cacheRecommendComponents.imgUrl)) {
//            this.ivRecommendComponents.setVisibility(View.VISIBLE);
//            this.ivRecommendComponents.initCoverImgUrl(this.cacheRecommendComponents.imgUrl);
//        } else {
//            this.ivRecommendComponents.setVisibility(View.GONE);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void updatePullStreamUrl() {
//        this.pullStreamUrl = this.liveListItemEntity.getDefPullStreamUrlStr();
//        this.pullStreamUrlList.clear();
//        this.pullStreamUrlList.addAll(this.liveListItemEntity.getPullStreamUrlList());
//    }
//
//    private void updateMoreRedDot() {
//        if (this.qBadgeView != null) {
//            this.qBadgeView.setBadgeNumber((DBUtils.isUnReadBoolean() || SysConfigInfoManager.getInstance().isEnableQMInteractRedDot()) ? -1 : 0);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* renamed from: addQMNoticeAnimView */
//    public void lambda$dealIntimateTask$62$SLLiveFragment(SocketMessageEvent.ResultData resultData) {
//        if (this.qmNoticeAnimView == null) {
//            this.qmNoticeAnimView = new QMNoticeAnimView(this.mContext);
//            this.qmNoticeAnimView.setOnQMInteractCallback(new SimpleQMInteractCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.85
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleQMInteractCallback, com.slzhibo.library.ui.interfaces.OnQMInteractCallback
//                public void onTaskStatusUpdateListener(String str, String str2) {
//                    super.onTaskStatusUpdateListener(str, str2);
//                    onNoticeAnimViewDismissListener();
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleQMInteractCallback, com.slzhibo.library.ui.interfaces.OnQMInteractCallback
//                public void onNoticeAnimViewDismissListener() {
//                    super.onNoticeAnimViewDismissListener();
//                    SLLiveFragment.this.canShowIntimateNotice.set(true);
//                    if (!SLLiveFragment.this.intimateTaskQueue.isEmpty()) {
//                        SLLiveFragment.this.sendWorkHandlerEmptyMessage(ConstantUtils.INTIMATE_TASK_NOTICE);
//                    }
//                }
//
//                @Override // com.slzhibo.library.ui.interfaces.impl.SimpleQMInteractCallback, com.slzhibo.library.ui.interfaces.OnQMInteractCallback
//                public void onUserCardListener(String str) {
//                    super.onUserCardListener(str);
//                    ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).getUserCardInfo(str);
//                }
//            });
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
//            layoutParams.gravity = 17;
//            this.rootView.addView(this.qmNoticeAnimView, layoutParams);
//        }
//        this.qmNoticeAnimView.showNoticeAnimView(false, resultData.putAvatar, resultData.putName, resultData.giftName, resultData.giftNum, resultData.taskName, resultData.putUserId, resultData.taskId);
//    }
//
//    private void clearQMNoticeAnimView() {
//        QMNoticeAnimView qMNoticeAnimView = this.qmNoticeAnimView;
//        if (qMNoticeAnimView != null) {
//            qMNoticeAnimView.onRelease();
//            this.rootView.removeView(this.qmNoticeAnimView);
//            this.qmNoticeAnimView = null;
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public YYLinkApplyEntity formatUserJoinLinkSeat(SocketMessageEvent.ResultData resultData) {
//        YYLinkApplyEntity yYLinkApplyEntity = new YYLinkApplyEntity();
//        yYLinkApplyEntity.rtcUid = NumberUtils.string2long(resultData.rtcUid);
//        yYLinkApplyEntity.userId = resultData.userId;
//        yYLinkApplyEntity.userName = resultData.userName;
//        yYLinkApplyEntity.sex = resultData.sex;
//        yYLinkApplyEntity.userAvatar = resultData.userAvatar;
//        yYLinkApplyEntity.likeCount = resultData.likeCount;
//        yYLinkApplyEntity.isQuiet = "0";
//        yYLinkApplyEntity.seatStatus = "1";
//        yYLinkApplyEntity.expGrade = resultData.expGrade;
//        yYLinkApplyEntity.guardType = resultData.guardType;
//        yYLinkApplyEntity.nobilityType = resultData.nobilityType;
//        yYLinkApplyEntity.seat = resultData.seat;
//        yYLinkApplyEntity.userOpenId = resultData.userOpenId;
//        yYLinkApplyEntity.userAppId = resultData.userAppId;
//        yYLinkApplyEntity.role = resultData.role;
//        return yYLinkApplyEntity;
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public YYLinkApplyEntity formatVideoLinkDetailInfo(SocketMessageEvent.ResultData resultData) {
//        YYLinkApplyEntity yYLinkApplyEntity = new YYLinkApplyEntity();
//        yYLinkApplyEntity.userId = resultData.userId;
//        yYLinkApplyEntity.userName = resultData.userName;
//        yYLinkApplyEntity.userAvatar = resultData.userAvatar;
//        yYLinkApplyEntity.status = "1";
//        yYLinkApplyEntity.isQuiet = resultData.isQuiet;
//        return yYLinkApplyEntity;
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public UserEntity formatUserEntity(YYLinkApplyEntity yYLinkApplyEntity) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setAvatar(yYLinkApplyEntity.userAvatar);
//        userEntity.setUserId(yYLinkApplyEntity.userId);
//        userEntity.setName(yYLinkApplyEntity.userName);
//        userEntity.setSex(yYLinkApplyEntity.sex);
//        userEntity.setExpGrade(yYLinkApplyEntity.expGrade);
//        userEntity.setGuardType(NumberUtils.string2int(yYLinkApplyEntity.guardType));
//        userEntity.setNobilityType(yYLinkApplyEntity.nobilityType);
//        userEntity.setRole(yYLinkApplyEntity.role);
//        userEntity.setOpenId(yYLinkApplyEntity.userOpenId);
//        userEntity.setAppId(yYLinkApplyEntity.userAppId);
//        return userEntity;
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void addYYLinkChatMsgNotice(String str) {
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setMsgType(10);
//        chatEntity.setMsgText(str);
//        this.mLiveChatMsgView.addChatMsg(chatEntity);
//    }
//
//    private boolean isEnableBackgroundPlay() {
//        return SysConfigInfoManager.getInstance().isEnableBackgroundPlay();
//    }
//
//    @Override // com.slzhibo.library.base.BaseFragment
//    public void onEventMainThread(BaseEvent baseEvent) {
//        super.onEventMainThread(baseEvent);
//        if (baseEvent instanceof LoginEvent) {
//            this.isLoginRequest = true;
//            ((SLLivePresenter) this.mPresenter).getUserOver();
//            if (isPayLiveNeedBuyTicket()) {
//                ((SLLivePresenter) this.mPresenter).onUserCheckTicket(this.liveId, false);
//            } else {
//                sendLiveInitInfoRequest();
//            }
//        } else {
//            if (baseEvent instanceof UpdateBalanceEvent) {
//                ((SLLivePresenter) this.mPresenter).getUserOver();
//            }
//            if (baseEvent instanceof NobilityOpenEvent) {
//                NobilityOpenEvent nobilityOpenEvent = (NobilityOpenEvent) baseEvent;
//                if (nobilityOpenEvent.isOpenSuccess) {
//                    showToast(nobilityOpenEvent.toastTips);
//                    updateUserBalance(nobilityOpenEvent.accountBalance);
//                }
//            }
//        }
//    }
//
//    public void onNetNone() {
//        RxTimerUtils.getInstance().timerBindDestroyFragment(getLifecycleProvider(), 2L, TimeUnit.SECONDS, new RxTimerUtils.RxTimerAction() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$aX7vnT-aj-28UBWL-AFcDbN2VCo
//            @Override // com.slzhibo.library.utils.RxTimerUtils.RxTimerAction
//            public final void action(long j) {
//                SLLiveFragment.this.lambda$onNetNone$80$SLLiveFragment(j);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$onNetNone$80$SLLiveFragment(long j) {
//        if (!NetUtils.isNetworkAvailable()) {
//            showToast(R.string.fq_text_no_network);
//        }
//    }
//
//    public void on4G() {
//        NetworkPromptDialog.newInstance(getResources().getString(R.string.fq_text_mobile_net), getResources().getString(R.string.fq_text_go_on), getResources().getString(R.string.fq_text_stop), $$Lambda$SLLiveFragment$9IUj1i8zqes3AKv6_IGVvk5wt9g.INSTANCE, new View.OnClickListener() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$FzchQRLCwtdj0n0oQxUDSNhhkQY
//            @Override // android.view.View.OnClickListener
//            public final void onClick(View view) {
//                SLLiveFragment.this.lambda$on4G$82$SLLiveFragment(view);
//            }
//        }).show(getChildFragmentManager());
//    }
//
//    public /* synthetic */ void lambda$on4G$82$SLLiveFragment(View view) {
//        onFinishActivity();
//    }
//
//    private void resetAllField() {
//        this.liveId = "";
//        this.liveCount = "";
//        this.pullStreamUrl = "";
//        this.luckNoticeLiveId = "";
//        this.giftNoticeLiveId = "";
//        this.curAnchorInfoNoticeEntity = null;
//        this.curBigAnimSendUserId = "";
//        this.asleep = true;
//        this.lastMsg = "";
//        this.liveStatus = false;
//        this.canShowGiftNotice.set(true);
//        this.canShowAnchorInfoNotice.set(true);
//        this.canShowGameNotice.set(true);
//        this.canShowEnterMsg.set(true);
//        this.canShowGuardEnterMsg.set(true);
//        this.canShowSysNotice.set(true);
//        this.canShowLuckNotice.set(true);
//        this.carFullAnimFinish.set(true);
//        this.canShowIntimateNotice.set(true);
//        this.isPausing = false;
//        this.isLiveEnd = false;
//        this.isNormalBan = false;
//        this.isAllBan = false;
//        this.isSuperBan = false;
//        this.reConnectCountOver = false;
//        this.isStartGetAnchorInfo = false;
//        this.isGiftListUpdating = false;
//        this.getUserBalanceFail = false;
//        this.isGetGiftListFail = false;
//        this.startGetGiftListInfo = false;
//        this.isContinueCombo = false;
//        this.isSocketReConn = false;
//        this.isTaskSocket = false;
//        this.isFirstLoadTask = true;
//        this.isConnectingChatService = true;
//        this.isSocketClose = true;
//        this.isSocketError = true;
//        this.postIntervalTimes = 1;
//        this.clickCount.set(0);
//        this.curTrumpetCount.set(0);
//        this.countDownTime = this.postIntervalTimes * 3;
//        this.myPriceBalance = 0.0d;
//        this.myScoreBalance = 0.0d;
//        this.onLineCount.set(0L);
//        this.speakLevel = "1";
//        this.myUserInfoEntity = null;
//        this.guardItemEntity = null;
//        this.anchorItemEntity = null;
//        this.isFirstGetMyBalanceLottery = true;
//        this.isFirstGetMyBalanceGift = true;
//        this.livingStartTime = 0L;
//        this.livingEndTime = 0L;
//        this.speakTotalCount.set(0L);
//        this.chargeType = "0";
//        this.isPayLive = false;
//        this.isBuyTicket = false;
//        this.isPayLiveTipsDialog = false;
//        this.showGuideRating = true;
//        this.ticketPrice = "0";
//        this.isFirstInitPlayManager = false;
//        this.isLotteryBoomStatus = false;
//        this.isLoginRequest = false;
//        this.isEnablePK = false;
//        this.uploadDataEnterSource = getString(R.string.fq_hot_list);
//        this.isRTCStream = false;
//        this.isBanGroup = false;
//        this.liveRecommendGameId = "";
//    }
//
//    private void onReleaseViewData() {
//        YYLinkSendApplyDialog yYLinkSendApplyDialog;
//        thermometerDisposable();
//        SPUtils.getInstance().put(ConstantUtils.MUTE_KEY, false);
//        SPUtils.getInstance().put(ConstantUtils.NOTICE_GAME_KEY, true);
//        if (this.isRTCStream || ((yYLinkSendApplyDialog = this.linkSendApplyDialog) != null && yYLinkSendApplyDialog.isUserLinkApplying())) {
//            ((SLLivePresenter) this.mPresenter).onSendUserDisconnectLinkRequest(isVoiceRoomType(), this.liveId, this.liveCount, null);
//            stopRTC();
//        }
//        watchRecordReport();
//        uploadLeaveLiveEvent();
//        if (isLiving()) {
//            ((SLLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(ConstantUtils.LEAVE_TYPE);
//        }
//        this.swipeAnimationController.resetClearScreen();
//        showContentView(258, true);
//        showLiveLoadingView(4);
//        HandlerUtils.getInstance().stopIOThread();
//        stopSocket();
//        dismissDialogs();
//        GiftBoxView giftBoxView = this.mGiftBoxView;
//        if (giftBoxView != null) {
//            giftBoxView.setVisibility(View.INVISIBLE);
//            this.mGiftBoxView.release();
//        }
//        LiveAdBannerBottomView liveAdBannerBottomView = this.mLiveAdBannerBottomView;
//        if (liveAdBannerBottomView != null) {
//            liveAdBannerBottomView.onReleaseAllView();
//        }
//        TaskBottomDialog taskBottomDialog = this.mTaskBottomDialog;
//        if (taskBottomDialog != null) {
//            taskBottomDialog.onDestroy();
//            this.mTaskBottomDialog = null;
//        }
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.onRelease();
//        }
//        LiveChatMsgView liveChatMsgView = this.mLiveChatMsgView;
//        if (liveChatMsgView != null) {
//            liveChatMsgView.onRelease();
//        }
//        if (this.livePayEnterView != null && isPayLiveNeedBuyTicket()) {
//            this.livePayEnterView.onRelease();
//        }
//        LotteryDialog lotteryDialog = this.mLotteryDialog;
//        if (lotteryDialog != null) {
//            lotteryDialog.onRelease();
//            this.mLotteryDialog = null;
//        }
//        onLotteryBoomClosed();
//        Handler handler = this.mainHandler;
//        if (handler != null) {
//            handler.removeCallbacksAndMessages(null);
//        }
//        Handler handler2 = this.workHandler;
//        if (handler2 != null) {
//            handler2.removeCallbacksAndMessages(null);
//        }
//        Disposable disposable = this.cdDisposable;
//        if (disposable != null && !disposable.isDisposed()) {
//            this.cdDisposable.dispose();
//            this.cdDisposable = null;
//        }
//        GiftBackpackDialog giftBackpackDialog = this.giftBottomDialog;
//        if (giftBackpackDialog != null) {
//            giftBackpackDialog.release();
//            this.giftBottomDialog = null;
//        }
//        ComponentsWebViewDialog componentsWebViewDialog = this.componentsWebViewDialog;
//        if (componentsWebViewDialog != null) {
//            componentsWebViewDialog.onRelease();
//        }
//        HdLotteryDrawingDialog hdLotteryDrawingDialog = this.hdLotteryDrawingDialog;
//        if (hdLotteryDrawingDialog != null) {
//            hdLotteryDrawingDialog.onReleaseData();
//        }
//        if (this.isEnablePK) {
//            stopLinkMicPk();
//        }
//        LiveAnimationView liveAnimationView = this.liveAnimationView;
//        if (liveAnimationView != null) {
//            liveAnimationView.onDestroy();
//        }
//        if (this.liveEndEvaluationDialog != null) {
//            this.liveEndEvaluationDialog = null;
//        }
//        if (this.qmInteractUserDialog != null) {
//            this.qmInteractUserDialog = null;
//        }
//        clearQMNoticeAnimView();
//        payLiveTipsDialogOnRelease();
//        if (isVoiceRoomType()) {
//            voiceRoomViewDataRelease();
//        }
//        ((SLLivePresenter) this.mPresenter).clearCompositeDisposable();
//        clearAllMapData();
//        resetAllField();
//    }
//
//    private void clearAllMapData() {
//        ConcurrentLinkedQueue<ChatEntity> concurrentLinkedQueue = this.receiveMsgQueue;
//        if (concurrentLinkedQueue != null) {
//            concurrentLinkedQueue.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue2 = this.enterMsgQueue;
//        if (concurrentLinkedQueue2 != null) {
//            concurrentLinkedQueue2.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue3 = this.guardEnterMsgQueue;
//        if (concurrentLinkedQueue3 != null) {
//            concurrentLinkedQueue3.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue4 = this.giftNoticeQueue;
//        if (concurrentLinkedQueue4 != null) {
//            concurrentLinkedQueue4.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue5 = this.sysNoticeQueue;
//        if (concurrentLinkedQueue5 != null) {
//            concurrentLinkedQueue5.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue6 = this.gameNoticeQueue;
//        if (concurrentLinkedQueue6 != null) {
//            concurrentLinkedQueue6.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue7 = this.anchorInfoNoticeQueue;
//        if (concurrentLinkedQueue7 != null) {
//            concurrentLinkedQueue7.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue8 = this.luckNoticeQueue;
//        if (concurrentLinkedQueue8 != null) {
//            concurrentLinkedQueue8.clear();
//        }
//        Map<String, GiftIndexEntity> map = this.myGiftIndexMap;
//        if (map != null) {
//            map.clear();
//        }
//        Map<String, GiftIndexEntity> map2 = this.myPropIndexMap;
//        if (map2 != null) {
//            map2.clear();
//        }
//        Map<String, Map<String, GiftIndexEntity>> map3 = this.receivePropMap;
//        if (map3 != null) {
//            map3.clear();
//        }
//        Map<String, Map<String, GiftIndexEntity>> map4 = this.receiveGiftMap;
//        if (map4 != null) {
//            map4.clear();
//        }
//        List<String> list = this.pullStreamUrlList;
//        if (list != null) {
//            list.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue9 = this.privateMsgQueue;
//        if (concurrentLinkedQueue9 != null) {
//            concurrentLinkedQueue9.clear();
//        }
//        ConcurrentLinkedQueue<SocketMessageEvent.ResultData> concurrentLinkedQueue10 = this.intimateTaskQueue;
//        if (concurrentLinkedQueue10 != null) {
//            concurrentLinkedQueue10.clear();
//        }
//    }
//
//    public void onDestroyViewData() {
//        onDestroyPlay();
//        InputTextMsgForAudienceDialog inputTextMsgForAudienceDialog = this.mInputTextMsgDialog;
//        if (inputTextMsgForAudienceDialog != null) {
//            inputTextMsgForAudienceDialog.onDestroy();
//            this.mInputTextMsgDialog = null;
//        }
//        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
//        if (livePusherInfoView != null) {
//            livePusherInfoView.onDestroy();
//        }
//        LiveLoadingView liveLoadingView = this.mLiveLoadingView;
//        if (liveLoadingView != null) {
//            liveLoadingView.onDestroy();
//        }
//        SwipeAnimationController swipeAnimationController = this.swipeAnimationController;
//        if (swipeAnimationController != null) {
//            swipeAnimationController.onDestroy();
//        }
//        LiveAdBannerBottomView liveAdBannerBottomView = this.mLiveAdBannerBottomView;
//        if (liveAdBannerBottomView != null) {
//            liveAdBannerBottomView.onDestroyWebView();
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.dialog.PrivateMsgDialog.SendPrivateMsgListener
//    public void sendPrivateMsg(MsgDetailListEntity msgDetailListEntity) {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.sendChatMessage(MessageHelper.convertToPrivateChatMsg(msgDetailListEntity));
//        }
//    }
//
//    @Override // com.slzhibo.library.ui.view.dialog.PrivateMsgDialog.SendPrivateMsgListener
//    public void onReConnSocket() {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.resetCount();
//            this.wsManager.reconnect();
//        }
//    }
//
//    public void onFragmentPageChangeListener() {
//        WsManager wsManager = this.wsManager;
//        if (wsManager != null) {
//            wsManager.clearAnimQueue();
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* loaded from: classes3.dex */
//    public class MainHandler extends Handler {
//        public MainHandler(SLLiveFragment sLLiveFragment, Looper looper) {
//            super(looper);
//        }
//
//        @Override // android.os.Handler
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* loaded from: classes3.dex */
//    public class UserCardCallback extends SimpleUserCardCallback {
//        private boolean isAnchorUserCard;
//        private boolean isCtrlTarget;
//        private boolean isEmperorNobilityTarget;
//        private boolean isShieldTarget;
//        private boolean isYearGuardTarget;
//        private final String targetId;
//        private String targetName;
//        private int userDialogType;
//
//        public UserCardCallback(String str, boolean z) {
//            this.isAnchorUserCard = false;
//            this.targetId = str;
//            this.isAnchorUserCard = z;
//        }
//
//        public UserCardCallback(String str, String str2, int i, boolean z, boolean z2, boolean z3, boolean z4) {
//            this.isAnchorUserCard = false;
//            this.targetId = str;
//            this.targetName = str2;
//            this.userDialogType = i;
//            this.isCtrlTarget = z;
//            this.isShieldTarget = z2;
//            this.isYearGuardTarget = z3;
//            this.isEmperorNobilityTarget = z4;
//        }
//
//        @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//        public void onClickAttentionListener(View view) {
//            super.onClickAttentionListener(view);
//            if (this.isAnchorUserCard) {
//                SLLiveFragment.this.attentionAnchorAction(view, this.targetId);
//            }
//        }
//
//        @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//        public void onClickManageListener(View view) {
//            super.onClickManageListener(view);
//            if (this.isAnchorUserCard) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.dismissDialogFragment(sLLiveFragment.anchorNobilityAvatarDialog);
//                SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                sLLiveFragment2.dismissDialogFragment(sLLiveFragment2.anchorAvatarDialog);
//                SLLiveFragment.this.playManager.onScreenshot();
//                return;
//            }
//            SLLiveFragment.this.userAvatarDialogManager(this.userDialogType, this.isCtrlTarget, this.isShieldTarget, this.isYearGuardTarget, this.isEmperorNobilityTarget, this.targetName, this.targetId);
//        }
//
//        @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//        public void onClickGuardListener() {
//            super.onClickGuardListener();
//            if (this.isAnchorUserCard) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.dismissDialogFragment(sLLiveFragment.anchorNobilityAvatarDialog);
//                SLLiveFragment sLLiveFragment2 = SLLiveFragment.this;
//                sLLiveFragment2.dismissDialogFragment(sLLiveFragment2.anchorAvatarDialog);
//                SLLiveFragment sLLiveFragment3 = SLLiveFragment.this;
//                sLLiveFragment3.showGuardListDialog(sLLiveFragment3.guardItemEntity);
//            }
//        }
//
//        @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//        public void onClickNobilityListener(View view) {
//            super.onClickNobilityListener(view);
//            SLLiveFragment.this.toNobilityOpenActivity();
//        }
//
//        @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//        public void onUserAchieveListener(UserEntity userEntity, String str) {
//            super.onUserAchieveListener(userEntity, str);
//            SLLiveFragment.this.showUserAchieveDialog(userEntity, str);
//        }
//
//        @Override // com.slzhibo.library.ui.interfaces.impl.SimpleUserCardCallback, com.slzhibo.library.ui.interfaces.OnUserCardCallback
//        public void onGiftWallClickListener(AnchorEntity anchorEntity) {
//            super.onGiftWallClickListener(anchorEntity);
//            SLLiveFragment.this.showGiftWallDialog(anchorEntity);
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void startRTC(String str, String str2, int i, String str3) {
//        if (!PermissionUtils.isGranted("android.permission.RECORD_AUDIO")) {
//            PermissionDialog.newInstance("MIC_TIP").show(getChildFragmentManager());
//            return;
//        }
//        this.isRTCStream = true;
//        if (this.playManager != null) {
//            showLiveLoadingView(4);
//            this.playManager.stopRTMPStream();
//        }
//        if (this.rtcController == null) {
//            this.rtcController = new RTCController(this.mContext);
//        }
//        this.rtcController.setCallBack(new RTCCallBack() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.86
//            @Override // com.slzhibo.library.ui.interfaces.RTCCallBack
//            public void onJoinSuccess(String str4) {
//                ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onSendVideoUserConnectSuccessRequest(SLLiveFragment.this.liveId, SLLiveFragment.this.liveCount, str4);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.RTCCallBack
//            public VideoCanvas onRemoteUserJoinSuccess(int i2) {
//                if (SLLiveFragment.this.isVoiceRoomType()) {
//                    return null;
//                }
//                return SLLiveFragment.this.addRemoteVideoRenderView(i2);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.RTCCallBack
//            public void onJoinFailure() {
//                if (SLLiveFragment.this.isVideoRoomType()) {
//                    SLLiveFragment.this.disconnectVideoLink(true);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.RTCCallBack
//            public void onUserQuit(int i2) {
//                if (!SLLiveFragment.this.isVoiceRoomType()) {
//                    SLLiveFragment.this.isVideoRoomType();
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.RTCCallBack
//            public void onUserDropped(int i2) {
//                if (!SLLiveFragment.this.isVoiceRoomType() && SLLiveFragment.this.isVideoRoomType()) {
//                    SLLiveFragment.this.disconnectVideoLink(true);
//                }
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.RTCCallBack
//            public void onRtcInitError() {
//                SLLiveFragment.this.disconnectVideoLink(true);
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.RTCCallBack
//            public void onJoinChannelFail(int i2) {
//                SLLiveFragment.this.disconnectVideoLink(true);
//            }
//        });
//        this.rtcController.startRtc(str, i, str3, str2, this.liveType);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void stopRTC() {
//        if (this.isRTCStream) {
//            this.isRTCStream = false;
//            RTCController rTCController = this.rtcController;
//            if (rTCController != null) {
//                rTCController.onRelease();
//                this.rootView.removeView(this.remoteRTCView);
//            }
//            PlayManager playManager = this.playManager;
//            if (playManager != null) {
//                playManager.startRTMPStream(this.pullStreamUrl);
//            }
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public VideoCanvas addRemoteVideoRenderView(int i) {
//        TextureView textureView = this.remoteRTCView;
//        this.remoteRTCView = RtcEngine.CreateTextureView(this.mContext);
//        ReSizeUtils.reAudienceSizeViewBig(this.remoteRTCView);
//        FrameLayout frameLayout = this.rootView;
//        if (frameLayout != null) {
//            if (textureView != null) {
//                frameLayout.removeView(textureView);
//            }
//            this.rootView.addView(this.remoteRTCView, 0);
//        }
//        return new VideoCanvas(this.remoteRTCView, 1, i);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void showLikeSeatView(YYLinkApplyEntity yYLinkApplyEntity) {
//        this.voiceRoomLikeCount.set(NumberUtils.string2int(yYLinkApplyEntity.surplusNum) - 1);
//        if (this.yyLikeCountView == null) {
//            this.yyLikeCountView = new YYLikeSeatView(this.mContext);
//            this.yyLikeCountView.setListener(new YYLikeSeatView.OnProgressListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.87
//                @Override // com.slzhibo.library.ui.view.custom.YYLikeSeatView.OnProgressListener
//                public void onStart(boolean z, String str) {
//                    if (!z) {
//                        SLLiveFragment.this.voiceRoomLikeCount.decrementAndGet();
//                        if (SLLiveFragment.this.voiceRoomLikeCount.get() < 0) {
//                            SLLiveFragment.this.yyLikeCountView.stopClick(true);
//                            return;
//                        }
//                        SLLiveFragment.this.yyLikeCountView.setLikeCount(String.valueOf(SLLiveFragment.this.voiceRoomLikeCount.get()));
//                        ((SLLivePresenter) ( SLLiveFragment.this).mPresenter).onSendVoiceRoomLikeAction(SLLiveFragment.this.liveId, String.valueOf(SLLiveFragment.this.liveCount), str);
//                    }
//                }
//
//                @Override // com.slzhibo.library.ui.view.custom.YYLikeSeatView.OnProgressListener
//                public void onFinish() {
//                    SLLiveFragment.this.yyLikeCountView.setVisibility(View.GONE);
//                }
//            });
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
//            layoutParams.gravity = 81;
//            layoutParams.leftMargin = 20;
//            layoutParams.rightMargin = 20;
//            layoutParams.bottomMargin = 50;
//            this.rootView.addView(this.yyLikeCountView, layoutParams);
//        }
//        this.yyLikeCountView.stopClick(false);
//        this.yyLikeCountView.setLikeCount(String.valueOf(this.voiceRoomLikeCount.get()));
//        this.yyLikeCountView.setTargetName(yYLinkApplyEntity.userName);
//        this.yyLikeCountView.setUserId(yYLinkApplyEntity.userId);
//        this.yyLikeCountView.setVisibility(View.VISIBLE);
//        this.yyLikeCountView.startAnim();
//    }
//
//    public boolean isVideoRoomType() {
//        return this.liveType == 1;
//    }
//
//    public boolean isVoiceRoomType() {
//        return this.liveType == 2;
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public boolean isBluetoothConnection() {
//        LiveItemEntity liveItemEntity = this.liveItemEntity;
//        return liveItemEntity != null && liveItemEntity.isBluetoothDeviceStatus() && isLiving();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void onGiftResUpdateAndTips(boolean z) {
//        if (z) {
//            showToast(R.string.fq_gift_res_update);
//        }
//        if (!this.isGiftListUpdating) {
//            this.isGiftListUpdating = true;
//            ((SLLivePresenter) this.mPresenter).getGiftList();
//        }
//        handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.-$$Lambda$SLLiveFragment$gWoiA6geKrzfMaYOqZmiguOcumI
//            @Override // java.lang.Runnable
//            public final void run() {
//                SLLiveFragment.this.lambda$onGiftResUpdateAndTips$83$SLLiveFragment();
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$onGiftResUpdateAndTips$83$SLLiveFragment() {
//        dismissDialogFragment(this.giftBottomDialog);
//    }
//
//    private void updateGiftThermometerScale(final GiftItemEntity giftItemEntity, SocketMessageEvent.ResultData resultData) {
//        if (giftItemEntity.isShakeGiftFlag() && this.giftBottomDialog != null && isBluetoothConnection() && resultData.isUpdateThermometerScale()) {
//            handlerMainPost(new Runnable() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.88
//                @Override // java.lang.Runnable
//                public void run() {
//                    SLLiveFragment.this.giftBottomDialog.updateGiftThermometerScale(giftItemEntity.markId, true);
//                    SLLiveFragment.this.thermometerDisposable();
//                    SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                    GiftItemEntity giftItemEntity2 = giftItemEntity;
//                    sLLiveFragment.startThermometerTimeCount(giftItemEntity2.duration + giftItemEntity2.active_time, giftItemEntity2.markId);
//                }
//            });
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void startThermometerTimeCount(long j, final String str) {
//        this.mThermometerDisposable = Flowable.intervalRange(0L, j + 1, 0L, 1L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.89
//            @Override // io.reactivex.functions.Action
//            public void run() throws Exception {
//                SLLiveFragment.this.giftBottomDialog.updateGiftThermometerScale(str, false);
//            }
//        }).subscribe();
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void thermometerDisposable() {
//        Disposable disposable = this.mThermometerDisposable;
//        if (disposable != null && !disposable.isDisposed()) {
//            this.mThermometerDisposable.dispose();
//        }
//    }
//
//    private void showLiveEndView() {
//        setAnchorCoverImg();
//        showLiveLoadingView(4);
//        setViewPagerScrollEnable(false);
//        if (this.mLiveEndInfoView == null) {
//            this.mLiveEndInfoView = (LiveEndInfoView) this.vsLiveEndInfoView.inflate();
//        }
//        showContentView(257, false);
//        this.mLiveEndInfoView.initData(this.lastLiveEndEntity);
//        this.mLiveEndInfoView.setLiveEndClickListener(new LiveEndInfoView.LiveEndClickListener() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.90
//            @Override // com.slzhibo.library.ui.view.custom.LiveEndInfoView.LiveEndClickListener
//            public void onAttentionClick(View view) {
//                SLLiveFragment sLLiveFragment = SLLiveFragment.this;
//                sLLiveFragment.attentionAnchorAction(view, sLLiveFragment.anchorId);
//            }
//
//            @Override // com.slzhibo.library.ui.view.custom.LiveEndInfoView.LiveEndClickListener
//            public void onGoHomeClick() {
//                SLLiveFragment.this.onFinishActivity();
//            }
//
//            @Override // com.slzhibo.library.ui.view.custom.LiveEndInfoView.LiveEndClickListener
//            public void onNavBackClick() {
//                SLLiveFragment.this.setViewPagerScrollEnable(true);
//                SLLiveFragment.this.showContentView(256, true);
//                if (!SLLiveFragment.this.isLiving() && SLLiveFragment.this.liveListItemEntity != null) {
//                    SLLiveFragment.this.goToLive();
//                }
//            }
//        });
//    }
//
//    private void showLivePayEnterView(String str, String str2, String str3, String str4, String str5, String str6) {
//        if (this.livePayEnterView == null) {
//            this.livePayEnterView = (LivePayEnterView) this.vsLivePayEnterView.inflate();
//        }
//        showContentView(259, false);
//        this.livePayEnterView.initData(str, str2, str3, str4, str5, str6);
//        this.livePayEnterView.setOnPayLiveEnterCallback(new SimplePayLiveCallback() { // from class: com.slzhibo.library.ui.activity.live.SLLiveFragment.91
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimplePayLiveCallback, com.slzhibo.library.ui.interfaces.OnPayLiveCallback
//            public void onPayExitClickListener() {
//                SLLiveFragment.this.onFinishActivity();
//            }
//
//            @Override // com.slzhibo.library.ui.interfaces.impl.SimplePayLiveCallback, com.slzhibo.library.ui.interfaces.OnPayLiveCallback
//            public void onPayEnterClickListener(View view) {
//                if (SLLiveFragment.this.isConsumptionPermissionUser()) {
//                    SLLiveFragment.this.livePayEnterView.onRelease();
//                    SLLiveFragment.this.sendLiveInitInfoRequest(true, true, true);
//                    return;
//                }
//                AppUtils.onLoginListener(( SLLiveFragment.this).mContext);
//            }
//        });
//    }
//
//    private void voiceRoomViewDataRelease() {
//        updateYYLinkIconView(null);
//        YYLinkSeatListView yYLinkSeatListView = this.yyLinkSeatListView;
//        if (yYLinkSeatListView != null) {
//            yYLinkSeatListView.clearSeatInfo();
//        }
//        YYLikeSeatView yYLikeSeatView = this.yyLikeCountView;
//        if (yYLikeSeatView != null) {
//            yYLikeSeatView.setVisibility(View.GONE);
//            this.yyLikeCountView.onRelease();
//        }
//    }
//}


