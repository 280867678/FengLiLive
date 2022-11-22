package com.slzhibo.library;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
//import com.blmvl.blvl.AppContext;
//import com.blmvl.blvl.Fragment.bus.UpdateBalanceEvent;
//import com.blmvl.blvl.Fragment.xinlei.RankingNewActivity;
//import com.example.boluouitest2zhibo.R;
//import com.example.boluouitest2zhibo.httpUtil.HttpUtil;
//import com.blmvl.blvl.activity.RankingNewActivity;
//import com.google.android.exoplayer2.util.Log;
import com.slzhibo.C4783c;
import com.slzhibo.library.download.GiftConfig;
import com.slzhibo.library.download.GiftDownLoadManager;
import com.slzhibo.library.download.NobilityDownLoadManager;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.HttpRxObserver;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.http.bean.HttpResultPageModel;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.http.utils.EncryptUtil;
import com.slzhibo.library.http.utils.RetryWithDelayUtils;
import com.slzhibo.library.model.ActivityListEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.LiveStatusEntity;
import com.slzhibo.library.model.MLCallUrlEntity;
import com.slzhibo.library.model.NobilityDownLoadEntity;
import com.slzhibo.library.model.SysParamsInfoEntity;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.model.db.ActivityDBEntity;
import com.slzhibo.library.model.event.LoginEvent;
import com.slzhibo.library.model.event.UpdateBalanceEvent;
import com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog;
import com.slzhibo.library.ui.view.widget.pop.basepopup.BasePopupSDK;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.CacheUtils;
import com.slzhibo.library.utils.CommonTransferUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.DBUtils;
import com.slzhibo.library.utils.LogEventUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.StringUtils;
import com.slzhibo.library.utils.SysConfigInfoManager;
import com.slzhibo.library.utils.UserInfoManager;
import com.slzhibo.library.utils.litepal.LitePal;
import com.slzhibo.library.utils.live.LiveManagerUtils;
import com.slzhibo.library.utils.live.SimpleRxObserver;
import com.slzhibo.library.utils.rxlifecycle2.LifecycleTransformer;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.youdao.sdk.app.YouDaoApplication;
import com.slzhibo.library.utils.SensorsDataAPIUtils;
import com.slzhibo.library.download.ResHotLoadManager;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class SLLiveSDK {

    public String ADV_CHANNEL_ID;
    public String ADV_CHANNEL_TYPE;
    public String API_URL;
    public String APP_ID;
    public String APP_KEY;
    public String APP_NAME;
    public String CUSTOMIZE_GAME;
    public String DATA_REPORT_CONFIG_URL;
    public String DATA_REPORT_URL;
    public String GAME_CHANNEL;
    public String IMG_DOWN_URL;
    public String IMG_UP_URL;
    public String YOUDAO_KEY;
    public Application application;
    public boolean isEnableNewYearSkin;
    public SLLiveSDKCallbackListener sdkCallbackListener;
    public String ML_CALL_SERVER_URL;
    private boolean isEnableHomeStartLive;

    /* loaded from: classes5.dex */
    public interface LiveSDKLoginCallbackListener {
        void onLoginFailListener(Context context);

        void onLoginSuccessListener(Context context);
    }

    /* loaded from: classes5.dex */
    public interface LiveSDKLogoutCallbackListener {
        void onLogoutFailListener(Context context);

        void onLogoutSuccessListener(Context context);
    }

    /* loaded from: classes5.dex */
    public interface OnAdvChannelCallbackListener {
        void onAdvDataFail(int i, String str);

        void onAdvDataSuccess(Context context, String str);
    }

    /* loaded from: classes5.dex */
    public interface OnCommonCallbackListener {
        void onDataFail(Throwable th, int i);

        void onDataSuccess(Context context, Object obj);
    }

    /* loaded from: classes5.dex */
    public interface OnLiveStatusCallbackListener {
        void onLiveStatusFail(int i, String str);

        void onLiveStatusSuccess(Context context, LiveStatusEntity liveStatusEntity);
    }

    /* loaded from: classes2.dex */
    public interface SLLiveSDKCallbackListener {
        void onAdClickListener(Context context, String str);

        void onAdvChannelHitsListener(Context context, String str, String str2);

        void onAdvChannelListener(Context context, String str, OnAdvChannelCallbackListener onAdvChannelCallbackListener);

        void onAdvChannelLiveNoticeListener(Context context, OnAdvChannelCallbackListener onAdvChannelCallbackListener);

        void onAppCommonCallbackListener(Context context, int i, OnCommonCallbackListener onCommonCallbackListener);

        boolean onEnterLivePermissionListener(Context context, int i);

        void onGiftRechargeListener(Context context);

        void onIncomeWithdrawalListener(Context context);

        void onLiveGameJSListener(Context context, String str);

        void onLoginListener(Context context);

        void onScoreListener(Context context);

        void onUserHomepageListener(Context context, UserEntity userEntity);

        void onUserOfflineListener(Context context);

        ////////////////////////////////////////////////////////////xinlei
        void onSDKCommonCallbackListener(Context context, int i, Object obj);

        void onEnterLivePermissionListener(LiveEntity liveEntity, OnCommonCallbackListener onCommonCallbackListener);


    }


    private SLLiveSDK() {
        this.YOUDAO_KEY = "1fdd2bb053fdb5bf";
        this.sdkCallbackListener = null;
        this.API_URL = "";
        this.IMG_UP_URL = "";
        this.IMG_DOWN_URL = "";
        this.APP_KEY = "";
        this.APP_ID = "";
        this.APP_NAME = "";
        this.ADV_CHANNEL_TYPE = "";
        this.ADV_CHANNEL_ID = "";
        this.GAME_CHANNEL = "";
        this.CUSTOMIZE_GAME = "";
        this.DATA_REPORT_URL = "";
        this.DATA_REPORT_CONFIG_URL = "";
        this.isEnableNewYearSkin = false;
        this.isEnableHomeStartLive = true;
        RxJavaPlugins.setErrorHandler(C4783c.f17515b);
//        RxJavaPlugins.setErrorHandler($$Lambda$SLLiveSDK$y3IoTNyGznM5nzFRrM0RJKA5rI.INSTANCE);
    }


    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class SingletonHolder {
        private static final SLLiveSDK INSTANCE = new SLLiveSDK();

        private SingletonHolder() {
        }
    }

    public static SLLiveSDK getSingleton() {
        return SingletonHolder.INSTANCE;
    }


    public Application getApplication() {
        Application application = this.application;
        return application == null ? Utils.getApp() : application;
    }



    public void loadOperationActivityDialog(Context context) {
//        m503a ==  just     m489b== subscribeOn   m549b ==  io               m507a == observeOn  m453a == mainThread
//        m494b == subscribe  m1526f==getActivityItemInfoById  f4621id==id   m15748a == newInstance  m15748a == newInstance
        Log.e("SLLiveSDK::200", "loadOperationActivityDialog");
        if (context != null && (context instanceof FragmentActivity)) {
            final FragmentManager supportFragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            ArrayList<ActivityListEntity> operateActivityListByType = CacheUtils.getOperateActivityListByType(false);
            if(operateActivityListByType == null){
                Log.e("SLLiveSDK::20777", "loadOperationActivityDialog operateActivityListByType == null");
                ActivityListEntity e=new ActivityListEntity();
                e.endTime=  1666666666;
                e.id="88";
                e.name="FucK";
                operateActivityListByType = new ArrayList<>();
                operateActivityListByType.add(e);
            }
            Log.e("SLLiveSDK::204", "loadOperationActivityDialog");
            if (operateActivityListByType != null) {
                Log.e("SLLiveSDK::20600", "loadOperationActivityDialog");
                Observable.just(operateActivityListByType).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.36
                    public void accept(ArrayList<ActivityListEntity> arrayList) throws Exception {
                        Log.e("SLLiveSDK::206", "loadOperationActivityDialog");
                        if (arrayList != null && !arrayList.isEmpty()) {
                            Log.e("SLLiveSDK::208", "loadOperationActivityDialog"+arrayList.size());
                            Iterator<ActivityListEntity> it = arrayList.iterator();
                            while (it.hasNext()) {
                                Log.e("SLLiveSDK::211", "loadOperationActivityDialog");
                                ActivityListEntity next = it.next();
                                if (next.isCorrectLink()) {
                                    Log.e("SLLiveSDK::214", "loadOperationActivityDialog");

                                    ActivityDBEntity f = DBUtils.getActivityItemInfoById(next.id);
                                    DBUtils.saveOrUpdateActivityItemInfo(next);
                                    if (f == null) {
                                        Log.e("SLLiveSDK::219", "loadOperationActivityDialog");
                                        GiftBagWebViewDialog.newInstance(next).show(supportFragmentManager);
                                    } else if (f.isShowActivityDialog()) {
                                        Log.e("SLLiveSDK::222", "loadOperationActivityDialog");
                                        GiftBagWebViewDialog.newInstance(next).show(supportFragmentManager);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

    }


    public void initAnim() {
        GiftDownLoadManager.getInstance().updateAnimOnlineRes();
        NobilityDownLoadManager.getInstance().updateAnimOnlineAllRes();
    }



    public void initSDK(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, boolean z3, String str9, String str10, String str11, SLLiveSDKCallbackListener sLLiveSDKCallbackListener) {
        this.application = application;
        this.sdkCallbackListener = sLLiveSDKCallbackListener;
        this.APP_ID = str;
//        Log.e("initSDK::::::APP_ID:", APP_ID);
        this.APP_KEY = str2;
//        Log.e("initSDK::::::APP_KEY:", APP_KEY);
        this.APP_NAME = str3;
//        Log.e("initSDK::::::APP_NAME:", APP_NAME);
        this.API_URL = str4;
//        Log.e("initSDK::::::API_URL:", API_URL);
        this.IMG_UP_URL = str5;
//        Log.e("initSDK::::::IMG_UP_URL:", IMG_UP_URL);
        this.IMG_DOWN_URL = str6;
//        Log.e("initSDK::::::IMG_DOWN_URL:", IMG_DOWN_URL);
        this.ADV_CHANNEL_ID = str7;
//        Log.e("initSDK::::::ADV_CHANNEL_ID:", ADV_CHANNEL_ID);
        this.ADV_CHANNEL_TYPE = str8;
//        Log.e("initSDK::::::ADV_CHANNEL_TYPE:", ADV_CHANNEL_TYPE);
        this.GAME_CHANNEL = str9;
//        Log.e("initSDK::::::GAME_CHANNEL:", GAME_CHANNEL);
        this.CUSTOMIZE_GAME = str10;
//        Log.e("initSDK::::::CUSTOMIZE_GAME:", CUSTOMIZE_GAME);
        updateDataReportUrl(str11);
//        Log.e("initSDK::::::updateDataReportUrl:", str11);
//        CommonTransferUtils.m1583a().m1581a(z);
        CommonTransferUtils.getSingleton().formatSignEncryptKey(z);
//        BasePopupSDK.m1815d().m1820a(application);
        BasePopupSDK.getInstance().init(application);
//        Utils.m21324a(application);
        Utils.init(application);
//        C6112i.m798a(application, this.YOUDAO_KEY);
//            YOUDAO_KEY
        Log.e("initSDK::::::279", "YOUDAO_KEY::" + this.YOUDAO_KEY);
        YouDaoApplication.init(application, this.YOUDAO_KEY);
        LitePal.initialize(application);
        initHttpCacheDir(application);
        UserInfoManager.getInstance().setAppId(str);
        Log.e("initSDK::::::setAppId:", str);
        SysConfigInfoManager.getInstance().setEnableVisitorPermission(z2);
        SysConfigInfoManager.getInstance().setEnableExchangeProportion(z3);
//        SPUtils.m10252a().m10238b(ConstantUtils.SHOW_MOBIE_TIP, false);
        SPUtils.getInstance().put(ConstantUtils.SHOW_MOBIE_TIP, false);
        CacheUtils.cleanCacheDisk(ConstantUtils.GENERAL_TURNTABLE_KEY, ConstantUtils.LUXURY_TURNTABLE_KEY);
        initSDKSendRequestData();
        initQbSdkWebView();


    }

    public void updateDataReportUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.DATA_REPORT_URL = str;
            this.DATA_REPORT_CONFIG_URL = str;
        }
    }

    private void initHttpCacheDir(Application application) {
        try {
            HttpResponseCache.install(new File(application.getCacheDir(), "http"), 134217728L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initSDKSendRequestData() {

        Log.e("","tl/shop/activity/searchActivityListByAppId");
        if (AppUtils.isApiService()) {
            Log.e("SLLiveSDK:::isA:::", "initSDKSendRequestData::314");
            ApiRetrofit.getInstance().getApiService().searchActivityListByAppIdService(new RequestParams().getAppIdParams()).map(new ServerResultFunction<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.41
            }).onErrorResumeNext(new HttpResultFunction<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.40
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<ArrayList<ActivityListEntity>>(false) { // from class: com.slzhibo.library.SLLiveSDK.39
                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, p481f.p482a.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    Log.e("SLLiveSDK:::onError:::", "initSDKSendRequestData319");
                    CacheUtils.saveOperateActivityList(null);
                }
                @Override
                public void accept(ArrayList<ActivityListEntity> arrayList) {
                    Log.e("SLLiveSDK:::accept:::", "initSDKSendRequestData324");
                    if (arrayList != null) {
                        CacheUtils.saveOperateActivityList(arrayList);
                    }
                }
            });
        }


//        if (AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().searchActivityListByAppIdService(new RequestParams().getAppIdParams()).map(new ServerResultFunction<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.44
//            }).onErrorResumeNext(new HttpResultFunction<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.43
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<ArrayList<ActivityListEntity>>(false) { // from class: com.slzhibo.library.SLLiveSDK.42
//                @SuppressLint("Range")
//                public void accept(ArrayList<ActivityListEntity> arrayList) {
//                    if (arrayList != null) {
//                        Log.e("initSDK::::::initSDKSendRequestData:", String.valueOf(arrayList.size()));
//                        CacheUtils.saveOperateActivityList(arrayList);
//                    }
//                }
//
//                @SuppressLint("Range")
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
//                public void onError(Throwable th) {
//                    super.onError(th);
//                    Log.e("initSDK::::::initSDKSendRequestData:onError:", th.getMessage());
//                    CacheUtils.saveOperateActivityList(null);
//                }
//            });
//        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initQbSdkWebView() {
        Observable.just(true).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: e.t.a.b
            @Override // p481f.p482a.p483a0.Consumer
            public final void accept(Object obj) {
                try {
                    Log.e("SLLiveSDK::::::", "initQbSdkWebView::366");
                    SLLiveSDK.this.m18470a((Boolean) obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public void loginSDK(Context context) {
        loginSDK(context, null);
    }

    public void loginSDK(final Context context, final LiveSDKLoginCallbackListener liveSDKLoginCallbackListener) {
        Log.e("SLLiveSDK::", "loginSDK:::::394::");

        if (context != null && AppUtils.isApiService()) {
            Log.e("SLLiveSDK::", "loginSDK397");
            LogEventUtils.uploadLoginButtonClick();

//            ApiRetrofit.getInstance().getApiService().getReadyPKService()

//            ApiRetrofit.getInstance().getApiService().getNobilitySourceListService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.3
//            }).onErrorResumeNext(new HttpResultFunction<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.2
//            }).retryWhen(new RetryWithDelayUtils(3, 5)).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new SimpleRxObserver<List<NobilityDownLoadEntity>>() { // from class: com.slzhibo.library.download.NobilityDownLoadManager.1
//                public void accept(List<NobilityDownLoadEntity> list) {
//                    if (!(list == null || list.isEmpty())) {
//                        for (NobilityDownLoadEntity nobilityDownLoadEntity : list) {
//                            Log.e("SLLiveSDK:userEntity:", nobilityDownLoadEntity.name);
//                        }
//                    }
//                }
//            });


            ApiRetrofit.getInstance().getApiService().getSdkLoginService(new RequestParams().getSDKLoginParams()).map(new ServerResultFunction<UserEntity>() { // from class: com.slzhibo.library.SLLiveSDK.6
            }).onErrorResumeNext(new HttpResultFunction<UserEntity>() { // from class: com.slzhibo.library.SLLiveSDK.5
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpRxObserver(context, new ResultCallBack<UserEntity>() { // from class: com.slzhibo.library.SLLiveSDK.4

                @Override
                public void onSuccess(UserEntity userEntity) {
                    Log.e("SLLiveSDK:userEntity:", "userEntity.getToken()= null");
                    if (userEntity != null) {
                        Log.e("SLLiveSDK:userEntity:", userEntity.getToken());
                        Log.e("SLLiveSDK:userEntity:", userEntity.getUserId());
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.getNobilityType()));
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.isLiveEnterHideBoolean()));
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.isInBanGroup()));
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.isSuperAdmin()));
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.isVisitorUser()));
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.isEnterLivePermissionBoolean()));
                        Log.e("SLLiveSDK:userEntity:", userEntity.getRole());
                        Log.e("SLLiveSDK:userEntity:", userEntity.getUserId());
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.getFollowTargetIds()));
                        Log.e("SLLiveSDK:userEntity:", String.valueOf(userEntity.getShieldTargetIds()));
//                        Log.e("SLLiveSDK:userEntity:",userEntity.isEnterLivePermissionBoolean());

                        UserInfoManager.getInstance().setToken(userEntity.getToken());
                        UserInfoManager.getInstance().setUserId(userEntity.getUserId());
                        UserInfoManager.getInstance().setNobilityType(userEntity.getNobilityType());
                        UserInfoManager.getInstance().setEnterHide(userEntity.isLiveEnterHideBoolean());
                        UserInfoManager.getInstance().setInBanGroup(userEntity.isInBanGroup());
                        UserInfoManager.getInstance().setSuperAdmin(userEntity.isSuperAdmin());
                        UserInfoManager.getInstance().setVisitorUser(userEntity.isVisitorUser());
                        UserInfoManager.getInstance().setEnterLivePermission(userEntity.isEnterLivePermissionBoolean());
                        UserInfoManager.getInstance().setUserRole(userEntity.getRole());
                        LogEventUtils.uploadLoginSuccess(userEntity.getUserId(), true);
                        if (userEntity.isNewUserBoolean()) {
                            LogEventUtils.uploadRegisterSuccess(userEntity.getUserId(), AppUtils.isVisitor() ? "2" : "1", 1);
                        }
                        DBUtils.saveAllAttentionAnchor(userEntity.getFollowTargetIds());
                        DBUtils.saveAllShieldUser(userEntity.getShieldTargetIds());
                        if (!UserInfoManager.getInstance().isVisitorUser()) {
                            EventBus.getDefault().post(new LoginEvent());
                        }
                        LiveSDKLoginCallbackListener liveSDKLoginCallbackListener2 = liveSDKLoginCallbackListener;
                        if (liveSDKLoginCallbackListener2 != null) {
                            liveSDKLoginCallbackListener2.onLoginSuccessListener(context);
                        }
                    }
                }

                @Override // com.slzhibo.library.http.ResultCallBack
                public void onError(int i, String str) {
                    Log.e("SLLiveSDK:userEntity:", "失败了；！！！！");
                    UserInfoManager.getInstance().setToken(null);
                    UserInfoManager.getInstance().setUserId(null);
                    UserInfoManager.getInstance().setNobilityType(-1);
                    LiveSDKLoginCallbackListener liveSDKLoginCallbackListener2 = liveSDKLoginCallbackListener;
                    if (liveSDKLoginCallbackListener2 != null) {
                        liveSDKLoginCallbackListener2.onLoginFailListener(context);
                    }
                }


            }));
        }
    }


    public void initSysConfig() {
        if (AppUtils.isApiService()) {
            Log.e("SLLiveSDK:userEntity:", "initSysConfig 481；！！！！");
            ApiRetrofit.getInstance().getApiService().getSysParamsInfoService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<SysParamsInfoEntity>() { // from class: com.slzhibo.library.SLLiveSDK.3
            }).onErrorResumeNext(new HttpResultFunction<SysParamsInfoEntity>() { // from class: com.slzhibo.library.SLLiveSDK.2
            }).retryWhen(new RetryWithDelayUtils(3, 3)).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new SimpleRxObserver<SysParamsInfoEntity>(false) { // from class: com.slzhibo.library.SLLiveSDK.1
                public void accept(SysParamsInfoEntity sysParamsInfoEntity) {
                    Log.e("SLLiveSDK:userEntity:", "initSysConfig accept；！！！！");
                    if (sysParamsInfoEntity != null) {
                        SysConfigInfoManager.getInstance().setEnableTranslationLevel(sysParamsInfoEntity.enableTranslationLevel);
                        SysConfigInfoManager.getInstance().setEntryNoticeLevelThreshold(sysParamsInfoEntity.entryNoticeLevelThreshold);
                        SysConfigInfoManager.getInstance().setGradeSet10CharacterLimit(sysParamsInfoEntity.gradeSet10CharacterLimit);
                        SysConfigInfoManager.getInstance().setGiftTrumpetPlayPeriod(sysParamsInfoEntity.giftTrumpetPlayPeriod);
                        SysConfigInfoManager.getInstance().setOnlineCountSynInterval(sysParamsInfoEntity.onlineCountSynInterval);
                        SysConfigInfoManager.getInstance().setNobilityTypeThresholdForHasPreventBanned(sysParamsInfoEntity.nobilityTypeThresholdForHasPreventBanned);
                        SysConfigInfoManager.getInstance().setLiveRankConfig(sysParamsInfoEntity.liveRankConfig);
                        SysConfigInfoManager.getInstance().setSocketHeartBeatInterval(NumberUtils.string2long(sysParamsInfoEntity.socketHeartBeatInterval));
                        SysConfigInfoManager.getInstance().setOnlineUserLevelFilter(sysParamsInfoEntity.liveOnlineUserListLevelFilter);
                        SysConfigInfoManager.getInstance().setOnlineUserListSize(sysParamsInfoEntity.liveInitOnlineUserListSize);
                        SysConfigInfoManager.getInstance().setEnableTurntableUpdateTip(sysParamsInfoEntity.isEnableTurntableUpdateTip());
                        SysConfigInfoManager.getInstance().setEnableReport(sysParamsInfoEntity.isEnableReport());
                        SysConfigInfoManager.getInstance().setEnableSticker(sysParamsInfoEntity.isEnableSticker());
                        SysConfigInfoManager.getInstance().setEnableNobility(sysParamsInfoEntity.isEnableNobility());
                        SysConfigInfoManager.getInstance().setEnableTurntable(sysParamsInfoEntity.isEnableTurntable());
                        SysConfigInfoManager.getInstance().setEnableAnchorHomepage(sysParamsInfoEntity.isEnableAnchorHomepage());
                        SysConfigInfoManager.getInstance().setEnableUserHomepage(sysParamsInfoEntity.isEnableUserHomepage());
                        SysConfigInfoManager.getInstance().setEnableGuard(sysParamsInfoEntity.isEnableGuard());
                        SysConfigInfoManager.getInstance().setEnableVisitorLive(sysParamsInfoEntity.isEnableVisitorLive());
                        SysConfigInfoManager.getInstance().setEnableScore(sysParamsInfoEntity.isEnableScore());
                        SysConfigInfoManager.getInstance().setEnableWeekStar(sysParamsInfoEntity.isEnableWeekStar());
                        SysConfigInfoManager.getInstance().setEnableCommerce(sysParamsInfoEntity.isEnableCommerce());
                        SysConfigInfoManager.getInstance().setEnableVideoStreamEncode(sysParamsInfoEntity.isEnableVideoStreamEncode());
                        SysConfigInfoManager.getInstance().setEnableGiftWall(sysParamsInfoEntity.isEnableGiftWall());
                        SysConfigInfoManager.getInstance().setEnableAchievement(sysParamsInfoEntity.isEnableAchievement());
                        SysConfigInfoManager.getInstance().setEnableComponents(sysParamsInfoEntity.isEnableComponents());
                        SysConfigInfoManager.getInstance().setEnableTranslation(sysParamsInfoEntity.isEnableTranslation());
                        SysConfigInfoManager.getInstance().setEnablePrivateMsg(sysParamsInfoEntity.isEnablePrivateMsg());
                        SysConfigInfoManager.getInstance().setEnableFeeTag(sysParamsInfoEntity.isEnableFeeTag());
                        SysConfigInfoManager.getInstance().setEnableLiveHelperJump(sysParamsInfoEntity.isEnableLiveHelperJump());
                        SysConfigInfoManager.getInstance().setWatermarkConfig(sysParamsInfoEntity.watermarkConfig);
                        SysConfigInfoManager.getInstance().setEnableLogEventReport(sysParamsInfoEntity.isEnableLogEventReport());
                        SysConfigInfoManager.getInstance().setEnableQMInteract(sysParamsInfoEntity.isEnableIntimate());
                        SysConfigInfoManager.getInstance().setEnableYYLink(sysParamsInfoEntity.isEnableVideoRoom());
                        SysConfigInfoManager.getInstance().setEnableBluetooth(sysParamsInfoEntity.isEnableBluetooth());
                        SysConfigInfoManager.getInstance().setEnableLocation(sysParamsInfoEntity.isEnableNearbyModule());
                        SysConfigInfoManager.getInstance().setEnableBluetoothTease(sysParamsInfoEntity.isEnableTease());
                        SysConfigInfoManager.getInstance().setEnableCardSetup(sysParamsInfoEntity.isEnableCardSetup());
                        SysConfigInfoManager.getInstance().setEnableAnchorDataCenter(sysParamsInfoEntity.getEnableAnchorDataCenter());
                        SysConfigInfoManager.getInstance().setAnchorDataCenterUrl(sysParamsInfoEntity.anchorDataCenterUrl);
                        SensorsDataAPIUtils.initSensorsDataAPI(SLLiveSDK.this.application);
                        ResHotLoadManager.getInstance().dealResLoad(sysParamsInfoEntity);
//                        Log.e("SLLiveSDK::", "initSysConfig:::525");
                    }
                }

                @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                public void onError(int i, String str) {
                    super.onError(i, str);
                }
            });
        }
    }


    public void onSendMLCallUrlRequest() {
        if (AppUtils.isApiService()) {
            ApiRetrofit.getInstance().getApiService().getMLCallUrlService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<MLCallUrlEntity>() { // from class: com.slzhibo.library.SLLiveSDK.27
            }).onErrorResumeNext(new HttpResultFunction<MLCallUrlEntity>() { // from class: com.slzhibo.library.SLLiveSDK.26
            }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).subscribe(new SimpleRxObserver<MLCallUrlEntity>(false) { // from class: com.slzhibo.library.SLLiveSDK.25
                public void accept(MLCallUrlEntity mLCallUrlEntity) {
                    if (mLCallUrlEntity != null && !TextUtils.isEmpty(mLCallUrlEntity.callUri)) {

                        Log.e("SLLiveSDK::538:::", "onSendMLCallUrlRequest");
//                        SLLiveSDK.this.updateVideoCallServerUrl(mLCallUrlEntity.callUri);
                    }
                }
            });
        }
    }











    /* renamed from: a */

    /**
     * ErrorHandler
     *
     * @param th
     * @throws Exception
     */

    public static /* synthetic */ void m18469a(Throwable th) throws Exception {
        Log.e("SLLiveSDK:::584:Error", th.getMessage());
    }


    /* renamed from: a */
    public /* synthetic */ void m18470a(Boolean bool) throws Exception {
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(this.application, new QbSdk.PreInitCallback() { // from class: com.slzhibo.library.SLLiveSDK.37
            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onCoreInitFinished() {
                Log.e("SLLiveSDK::::::", "m18470a::366onCoreInitFinished");
            }

            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onViewInitFinished(boolean z) {
                Log.e("SLLiveSDK::::::", "m18470a::366onViewInitFinished");
                if (!z) {
                    SLLiveSDK.this.initQbSdkWebView();
                    Log.e("SLLiveSDK::::::", "onViewInitFinished m18470a::366");
                }
            }
        });
        QbSdk.setTbsListener(new TbsListener() { // from class: com.slzhibo.library.SLLiveSDK.38
            @Override // com.tencent.smtt.sdk.TbsListener
            public void onDownloadFinish(int i) {
                Log.e("SLLiveSDK::::::", "m18470a::366onDownloadFinish");
            }

            @Override // com.tencent.smtt.sdk.TbsListener
            public void onDownloadProgress(int i) {
                Log.e("SLLiveSDK::::::", "m18470a::366onDownloadProgress");
            }

            @Override // com.tencent.smtt.sdk.TbsListener
            public void onInstallFinish(int i) {
                Log.e("SLLiveSDK::::::", "m18470a::366onInstallFinish");
            }
        });
    }


    /**
     * GiftBagWebViewDialog
     *
     * @param context
     * @param str
     */
    public void clickBannerReport(Context context, String str) {
//        m549b == io  m484c==map  onErrorResumeNext==m479d     m489b==subscribeOn
//        m507a ==observeOn   mo118a==subscribe
        if (AppUtils.isApiService()) {
            ApiRetrofit.getInstance().getApiService().getClickAdService(new RequestParams().getClickAdParams(str)).map(new ServerResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.34
            }).onErrorResumeNext(new HttpResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.33
            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<Object>(false) { // from class: com.slzhibo.library.SLLiveSDK.32
                @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                public void accept(Object obj) {
                    Log.e("clickBannerReport:", "SLLiveSDK::624");
                }
            });
        }
    }


    /**
     * AppUtil
     *
     * @return
     */
    public boolean isLiveAdvChannel() {
        return TextUtils.isEmpty(this.ADV_CHANNEL_TYPE) || TextUtils.equals(this.ADV_CHANNEL_TYPE, "1");
    }


    /**
     * HomeHotPresenter
     *
     * @param lifecycleTransformer
     */
    public void onAllLiveListUpdate(LifecycleTransformer lifecycleTransformer) {
        onAllLiveListUpdate(lifecycleTransformer, null);
    }

    public void onAllLiveListUpdate(LifecycleTransformer lifecycleTransformer, final ResultCallBack<List<LiveEntity>> resultCallBack) {
        if (AppUtils.isApiService()) {
            Observable a = ApiRetrofit.getInstance().getApiService().getAllListService(new RequestParams().getPageListParams(1, 40)).map(new ServerResultFunction<HttpResultPageModel<LiveEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.35
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
            if (lifecycleTransformer != null) {
                a.compose(lifecycleTransformer);
            }
            a.subscribe(new Consumer() { // from class: e.t.a.a
                @Override // p481f.p482a.p483a0.Consumer
                public final void accept(Object obj) throws Exception {
                    SLLiveSDK.m18471a(resultCallBack, (HttpResultPageModel) obj);
                    Log.e("onAllLiveListUpdate:", "SLLiveSDK::658");
                }
            });
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m18471a(ResultCallBack resultCallBack, HttpResultPageModel httpResultPageModel) throws Exception {
        if (httpResultPageModel != null) {
//            LiveManagerUtils.m1312d().m1315a(httpResultPageModel.dataList);
            LiveManagerUtils.getInstance().setLiveList(httpResultPageModel.dataList);
            if (resultCallBack != null) {
                resultCallBack.onSuccess(httpResultPageModel.dataList);
            }
        }
    }

    private String getAuthStr(byte[] bArr) {
        if (bArr != null && bArr.length >= 1) {
            try {
                return StringUtils.compress(EncryptUtil.DESEncrypt("246887c3-ee20-4fe8-a320-1fde4a8d10b6", StringUtils.toHexString(bArr)));
            } catch (Exception unused) {
            }
        }
        return "";
    }


    /**
     * MLLiveHomeFragment
     * @param context
     * @param i
     */
//    public void toRankingActivity(Context context, int i) {
//        if (!AppUtils.isApiDomainError(context) && i <= 1 && i >= 0) {
//            Intent intent = new Intent(context, RankingNewActivity.class);
//            intent.putExtra(ConstantUtils.RESULT_FLAG, i);
//            ArrayList<String> arrayList = new ArrayList<>();
//            if (AppUtils.isEnableWeekStar()) {
//                arrayList.add(ConstantUtils.RANK_TYPE_WEEKSTAR);
//            }
//            if (AppUtils.isEnableCommerce()) {
//                arrayList.add(ConstantUtils.RANK_TYPE_COMMERCE);
//            }
//            intent.putStringArrayListExtra(ConstantUtils.RESULT_ITEM, arrayList);
//            context.startActivity(intent);
//        }
//    }

    /**
     * LiveInterface
     */
    public void clearUserTokenInfo() {
        UserInfoManager.getInstance().clearTokenInfo();
    }
    /**
     * LiveInterface
     */
    public void updateServerUrl(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            this.API_URL = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            this.IMG_UP_URL = str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            this.IMG_DOWN_URL = str3;
        }
    }

    /**
     * HLiveHomePresenter
     */
    public void onUpdateBalance() {
        EventBus.getDefault().post(new UpdateBalanceEvent());
    }


    /**
     * LiveInterface
     * @param application
     * @param str
     * @param str2
     * @param str3
     * @param str4
     * @param str5
     * @param str6
     * @param str7
     * @param str8
     * @param z
     * @param z2
     * @param z3
     * @param str9
     * @param str10
     * @param str11
     * @param str12
     * @param sLLiveSDKCallbackListener
     */
    public void initSDK(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, boolean z3, String str9, String str10, String str11, String str12, SLLiveSDKCallbackListener sLLiveSDKCallbackListener) {
        this.application = application;
        this.sdkCallbackListener = sLLiveSDKCallbackListener;
        this.APP_ID = str;
        this.APP_KEY = str2;
        this.APP_NAME = str3;
        this.API_URL = str4;
        this.IMG_UP_URL = str5;
        this.IMG_DOWN_URL = str6;
        this.ADV_CHANNEL_ID = str7;
        this.ADV_CHANNEL_TYPE = str8;
        this.GAME_CHANNEL = str9;
        this.CUSTOMIZE_GAME = str10;
        updateDataReportUrl(str11);
        CommonTransferUtils.getSingleton().formatSignEncryptKey(z);
        BasePopupSDK.getInstance().init(application);
        Utils.init(application);
        YouDaoApplication.init(application, str12);
        LitePal.initialize(application);
        initHttpCacheDir(application);
        UserInfoManager.getInstance().setAppId(str);
        SysConfigInfoManager.getInstance().setEnableVisitorPermission(z2);
        SysConfigInfoManager.getInstance().setEnableExchangeProportion(z3);
        SPUtils.getInstance().put(ConstantUtils.SHOW_MOBIE_TIP, false);
        CacheUtils.cleanCacheDisk(ConstantUtils.GENERAL_TURNTABLE_KEY, ConstantUtils.LUXURY_TURNTABLE_KEY);
        initSDKSendRequestData();
        initQbSdkWebView();
    }


    /**
     * LiveSDKCallbackListener
     * @param str
     * @param str2
     */
    public void updateGameChannelField(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.GAME_CHANNEL = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            this.CUSTOMIZE_GAME = str2;
        }
    }


    /**
     * MLHomeHotFragment
     * @return
     */
    public boolean isEnableHomeStartLive() {
        return this.isEnableHomeStartLive;
    }

    //SLLivePresenter
    public boolean isLiveGameChannel() {
        return TextUtils.isEmpty(this.CUSTOMIZE_GAME) || TextUtils.equals(this.CUSTOMIZE_GAME, "1");
    }

}

































































































//
//
////package com.slzhibo.library;
//
//import android.app.Activity;
//import android.app.Application;
//import android.content.Context;
//import android.content.Intent;
//import android.net.http.HttpResponseCache;
//import android.text.TextUtils;
//import androidx.fragment.app.FragmentActivity;
//import androidx.fragment.app.FragmentManager;
//import com.blankj.utilcode.util.SPUtils;
//import com.blankj.utilcode.util.Utils;
//import com.slzhibo.library.analytics.AopConstants;
//import com.slzhibo.library.download.GiftDownLoadManager;
//import com.slzhibo.library.download.NobilityDownLoadManager;
////import com.slzhibo.library.download.ResHotLoadManager;
//import com.slzhibo.library.http.ApiRetrofit;
//import com.slzhibo.library.http.HttpRxObserver;
//import com.slzhibo.library.http.RequestParams;
//import com.slzhibo.library.http.ResultCallBack;
//import com.slzhibo.library.http.StatisticsApiRetrofit;
//import com.slzhibo.library.http.bean.HttpResultPageModel;
//import com.slzhibo.library.http.function.HttpResultFunction;
//import com.slzhibo.library.http.function.ServerResultFunction;
//import com.slzhibo.library.http.utils.EncryptUtil;
//import com.slzhibo.library.http.utils.RetryWithDelayUtils;
//import com.slzhibo.library.model.ActivityListEntity;
//import com.slzhibo.library.model.AppLiveItemEntity;
//import com.slzhibo.library.model.IndexRankEntity;
//import com.slzhibo.library.model.LiveEntity;
//import com.slzhibo.library.model.LiveStatusEntity;
//import com.slzhibo.library.model.MLCallUrlEntity;
//import com.slzhibo.library.model.SysParamsInfoEntity;
//import com.slzhibo.library.model.UserEntity;
//import com.slzhibo.library.model.event.LoginEvent;
//import com.slzhibo.library.model.event.LogoutEvent;
////import com.slzhibo.library.model.event.UpdateBalanceEvent;
//import com.slzhibo.library.model.db.ActivityDBEntity;
////import com.slzhibo.library.ui.activity.home.FastAuthLoginActivity;
////import com.slzhibo.library.ui.activity.home.RankingNewActivity;
////import com.slzhibo.library.ui.activity.mylive.MLMyLiveActivity;
////import com.slzhibo.library.ui.activity.p116ml.MLTypeTagActivity;
////import com.slzhibo.library.ui.interfaces.OnAppRankingCallback;
//import com.slzhibo.library.ui.view.dialog.GiftBagWebViewDialog;
////import com.slzhibo.library.ui.view.task.TaskBoxUtils;
//import com.slzhibo.library.ui.view.widget.pop.basepopup.BasePopupSDK;
//import com.slzhibo.library.utils.AppUtils;
//import com.slzhibo.library.utils.CacheUtils;
//import com.slzhibo.library.utils.CommonTransferUtils;
//import com.slzhibo.library.utils.ConstantUtils;
//import com.slzhibo.library.utils.DBUtils;
//import com.slzhibo.library.utils.GsonUtils;
//import com.slzhibo.library.utils.LogEventUtils;
//import com.slzhibo.library.utils.NumberUtils;
////import com.slzhibo.library.utils.SensorsDataAPIUtils;
//import com.slzhibo.library.utils.StringUtils;
//import com.slzhibo.library.utils.SysConfigInfoManager;
//import com.slzhibo.library.utils.UserInfoManager;
//import com.slzhibo.library.utils.litepal.LitePal;
//import com.slzhibo.library.utils.live.LiveManagerUtils;
//import com.slzhibo.library.utils.live.SimpleRxObserver;
//import com.slzhibo.library.utils.rxlifecycle2.LifecycleTransformer;
//import com.tencent.smtt.sdk.QbSdk;
//import com.tencent.smtt.sdk.TbsListener;
//import com.youdao.sdk.app.YouDaoApplication;
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Consumer;
//import io.reactivex.functions.Function;
//import io.reactivex.plugins.RxJavaPlugins;
//import io.reactivex.schedulers.Schedulers;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import okhttp3.ResponseBody;
//import org.greenrobot.eventbus.EventBus;
//
///* loaded from: classes6.dex */
//public class SLLiveSDK {
//    public String ADV_CHANNEL_ID;
//    public String ADV_CHANNEL_TYPE;
//    public String API_URL;
//    public String APP_ID;
//    public String APP_KEY;
//    public String APP_NAME;
//    public String CUSTOMIZE_GAME;
//    public String DATA_REPORT_CONFIG_URL;
//    public String DATA_REPORT_URL;
//    public String GAME_CHANNEL;
//    public String IMG_DOWN_URL;
//    public String IMG_UP_URL;
//    public String ML_CALL_SERVER_URL;
//    private Application application;
//    private boolean isEnableHomeStartLive;
//    public boolean isEnableNewYearSkin;
//    public SLLiveSDKCallbackListener sdkCallbackListener;
//    public String YOUDAO_KEY;
//
//    /* loaded from: classes6.dex */
//    public interface LiveSDKLoginCallbackListener {
//        void onLoginFailListener(Context context);
//
//        void onLoginSuccessListener(Context context);
//    }
//
//    /* loaded from: classes6.dex */
//    public interface LiveSDKLogoutCallbackListener {
//        void onLogoutFailListener(Context context);
//
//        void onLogoutSuccessListener(Context context);
//    }
//
//    /* loaded from: classes6.dex */
//    public interface OnAdvChannelCallbackListener {
//        void onAdvDataFail(int i, String str);
//
//        void onAdvDataSuccess(Context context, String str);
//    }
//
//    /* loaded from: classes6.dex */
//    public interface OnCommonCallbackListener {
//        void onDataFail(Throwable th, int i);
//
//        void onDataSuccess(Context context, Object obj);
//    }
//
//    /* loaded from: classes6.dex */
//    public interface OnLiveStatusCallbackListener {
//        void onLiveStatusFail(int i, String str);
//
//        void onLiveStatusSuccess(Context context, LiveStatusEntity liveStatusEntity);
//    }
//
//    /* loaded from: classes6.dex */
//    public interface SLLiveSDKCallbackListener {
//        void onAdClickListener(Context context, String str);
//
//        void onAdvChannelHitsListener(Context context, String str, String str2);
//
//        void onAdvChannelListener(Context context, String str, OnAdvChannelCallbackListener onAdvChannelCallbackListener);
//
//        void onAdvChannelLiveNoticeListener(Context context, OnAdvChannelCallbackListener onAdvChannelCallbackListener);
//
//        void onAppCommonCallbackListener(Context context, int i, OnCommonCallbackListener onCommonCallbackListener);
//
//        void onEnterLivePermissionListener(LiveEntity liveEntity, OnCommonCallbackListener onCommonCallbackListener);
//
//        boolean onEnterLivePermissionListener(Context context, int i);
//
//        void onGiftRechargeListener(Context context);
//
//        void onIncomeWithdrawalListener(Context context);
//
//        void onLiveGameJSListener(Context context, String str);
//
//        void onLoginListener(Context context);
//
//        void onSDKCommonCallbackListener(Context context, int i, Object obj);
//
//        void onScoreListener(Context context);
//
//        void onUserHomepageListener(Context context, UserEntity userEntity);
//
//        void onUserOfflineListener(Context context);
//    }
//
//    /* JADX INFO: Access modifiers changed from: package-private */
//    public static /* synthetic */ void lambda$new$0(Throwable th) throws Exception {
//    }
//
//    private SLLiveSDK() {
//        this.sdkCallbackListener = null;
//        this.API_URL = "";
//        this.IMG_UP_URL = "";
//        this.IMG_DOWN_URL = "";
//        this.APP_KEY = "";
//        this.APP_ID = "";
//        this.APP_NAME = "";
//        this.ADV_CHANNEL_TYPE = "";
//        this.ADV_CHANNEL_ID = "";
//        this.GAME_CHANNEL = "";
//        this.CUSTOMIZE_GAME = "";
//        this.DATA_REPORT_URL = "";
//        this.DATA_REPORT_CONFIG_URL = "";
//        this.ML_CALL_SERVER_URL = "";
//        this.isEnableNewYearSkin = false;
//        this.isEnableHomeStartLive = true;
//        this.YOUDAO_KEY = "1fdd2bb053fdb5bf";
//        RxJavaPlugins.setErrorHandler($$Lambda$SLLiveSDK$y3IoTNyGznM5nzFRrM0RJKA5rI.INSTANCE);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    /* loaded from: classes6.dex */
//    public static class SingletonHolder {
//        private static final SLLiveSDK INSTANCE = new SLLiveSDK();
//
//        private SingletonHolder() {
//        }
//    }
//
//    public static SLLiveSDK getSingleton() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    public void initSDK(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, boolean z3, String str9, String str10, String str11, SLLiveSDKCallbackListener sLLiveSDKCallbackListener) {
//        this.application = application;
//        this.sdkCallbackListener = sLLiveSDKCallbackListener;
//        this.APP_ID = str;
//        this.APP_KEY = str2;
//        this.APP_NAME = str3;
//        this.API_URL = str4;
//        this.IMG_UP_URL = str5;
//        this.IMG_DOWN_URL = str6;
//        this.ADV_CHANNEL_ID = str7;
//        this.ADV_CHANNEL_TYPE = str8;
//        this.GAME_CHANNEL = str9;
//        this.CUSTOMIZE_GAME = str10;
//        updateDataReportUrl(str11);
//        CommonTransferUtils.getSingleton().formatSignEncryptKey(z);
//        BasePopupSDK.getInstance().init(application);
//        Utils.init(application);
//        YouDaoApplication.init(application, this.YOUDAO_KEY);
//        LitePal.initialize(application);
//        initHttpCacheDir(application);
//        UserInfoManager.getInstance().setAppId(str);
//        SysConfigInfoManager.getInstance().setEnableVisitorPermission(z2);
//        SysConfigInfoManager.getInstance().setEnableExchangeProportion(z3);
//        SPUtils.getInstance().put(ConstantUtils.SHOW_MOBIE_TIP, false);
//        CacheUtils.cleanCacheDisk(ConstantUtils.GENERAL_TURNTABLE_KEY, ConstantUtils.LUXURY_TURNTABLE_KEY);
//        initSDKSendRequestData();
//        initQbSdkWebView();
//    }
//
//    public void updateServerUrl(String str, String str2, String str3) {
//        if (!TextUtils.isEmpty(str)) {
//            this.API_URL = str;
//        }
//        if (!TextUtils.isEmpty(str2)) {
//            this.IMG_UP_URL = str2;
//        }
//        if (!TextUtils.isEmpty(str3)) {
//            this.IMG_DOWN_URL = str3;
//        }
//    }
//
//    public void updateDataReportUrl(String str) {
//        if (!TextUtils.isEmpty(str)) {
//            this.DATA_REPORT_URL = str;
//            this.DATA_REPORT_CONFIG_URL = str;
//        }
//    }
//
//    public void updateVideoCallServerUrl(String str) {
//        if (!TextUtils.isEmpty(str)) {
//            this.ML_CALL_SERVER_URL = str;
//        }
//    }
//
//    public void updateAdvChannelField(String str, String str2) {
//        if (!TextUtils.isEmpty(str)) {
//            this.ADV_CHANNEL_ID = str;
//        }
//        if (!TextUtils.isEmpty(str2)) {
//            this.ADV_CHANNEL_TYPE = str2;
//        }
//    }
//
//    public void updateGameChannelField(String str, String str2) {
//        if (!TextUtils.isEmpty(str)) {
//            this.GAME_CHANNEL = str;
//        }
//        if (!TextUtils.isEmpty(str2)) {
//            this.CUSTOMIZE_GAME = str2;
//        }
//    }
//
//    public void updateAppId(String str) {
//        if (!TextUtils.isEmpty(str)) {
//            this.APP_ID = str;
//            UserInfoManager.getInstance().setAppId(str);
//        }
//    }
//
//    public void setApiKey(String str, String str2, String str3, String str4) {
//        CommonTransferUtils.getSingleton().setApiKey(str, str2, str3, str4);
//    }
//
//    public boolean isEnableHomeStartLive() {
//        return this.isEnableHomeStartLive;
//    }
//
//    public void setEnableHomeStartLive(boolean z) {
//        this.isEnableHomeStartLive = z;
//    }
//
//    public void initAnim() {
//        GiftDownLoadManager.getInstance().updateAnimOnlineRes();
//        NobilityDownLoadManager.getInstance().updateAnimOnlineAllRes();
//    }
//
//    public Application getApplication() {
//        Application application = this.application;
//        return application == null ? Utils.getApp() : application;
//    }
//
//    public boolean isLiveAdvChannel() {
//        return TextUtils.isEmpty(this.ADV_CHANNEL_TYPE) || TextUtils.equals(this.ADV_CHANNEL_TYPE, "1");
//    }
//
//    public boolean isLiveGameChannel() {
//        return TextUtils.isEmpty(this.CUSTOMIZE_GAME) || TextUtils.equals(this.CUSTOMIZE_GAME, "1");
//    }
//
//    public void toLiveActivity(Context context, String str) {
//        LiveEntity liveEntity = new LiveEntity();
//        liveEntity.liveId = str;
//        AppUtils.startSLLiveActivity(context, liveEntity, "2", context.getString(R.string.fq_live_enter_source_home_page));
//    }
//
////    public void toMyLiveActivity(Context context) {
////        if (!AppUtils.isApiDomainError(context) && AppUtils.isConsumptionPermissionUser(context)) {
////            context.startActivity(new Intent(context, MLMyLiveActivity.class));
////        }
////    }
////
////    public void toVideoCallHomeActivity(Context context) {
////        if (!AppUtils.isApiDomainError(context)) {
////            Intent intent = new Intent(context, MLTypeTagActivity.class);
////            intent.putExtra(ConstantUtils.RESULT_FLAG_VAR, true);
////            context.startActivity(intent);
////        }
////    }
//
//    public void initSysConfig() {
//        if (AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getSysParamsInfoService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<SysParamsInfoEntity>() { // from class: com.slzhibo.library.SLLiveSDK.3
//            }).onErrorResumeNext(new HttpResultFunction<SysParamsInfoEntity>() { // from class: com.slzhibo.library.SLLiveSDK.2
//            }).retryWhen(new RetryWithDelayUtils(3, 3)).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<SysParamsInfoEntity>(false) { // from class: com.slzhibo.library.SLLiveSDK.1
//                public void accept(SysParamsInfoEntity sysParamsInfoEntity) {
//                    if (sysParamsInfoEntity != null) {
//                        SysConfigInfoManager.getInstance().setEnableTranslationLevel(sysParamsInfoEntity.enableTranslationLevel);
//                        SysConfigInfoManager.getInstance().setEntryNoticeLevelThreshold(sysParamsInfoEntity.entryNoticeLevelThreshold);
//                        SysConfigInfoManager.getInstance().setGradeSet10CharacterLimit(sysParamsInfoEntity.gradeSet10CharacterLimit);
//                        SysConfigInfoManager.getInstance().setGiftTrumpetPlayPeriod(sysParamsInfoEntity.giftTrumpetPlayPeriod);
//                        SysConfigInfoManager.getInstance().setOnlineCountSynInterval(sysParamsInfoEntity.onlineCountSynInterval);
//                        SysConfigInfoManager.getInstance().setNobilityTypeThresholdForHasPreventBanned(sysParamsInfoEntity.nobilityTypeThresholdForHasPreventBanned);
//                        SysConfigInfoManager.getInstance().setLiveRankConfig(sysParamsInfoEntity.liveRankConfig);
//                        SysConfigInfoManager.getInstance().setSocketHeartBeatInterval(NumberUtils.string2long(sysParamsInfoEntity.socketHeartBeatInterval));
//                        SysConfigInfoManager.getInstance().setOnlineUserLevelFilter(sysParamsInfoEntity.liveOnlineUserListLevelFilter);
//                        SysConfigInfoManager.getInstance().setOnlineUserListSize(sysParamsInfoEntity.liveInitOnlineUserListSize);
//                        SysConfigInfoManager.getInstance().setEnableTurntableUpdateTip(sysParamsInfoEntity.isEnableTurntableUpdateTip());
//                        SysConfigInfoManager.getInstance().setEnableReport(sysParamsInfoEntity.isEnableReport());
//                        SysConfigInfoManager.getInstance().setEnableSticker(sysParamsInfoEntity.isEnableSticker());
//                        SysConfigInfoManager.getInstance().setEnableNobility(sysParamsInfoEntity.isEnableNobility());
//                        SysConfigInfoManager.getInstance().setEnableTurntable(sysParamsInfoEntity.isEnableTurntable());
//                        SysConfigInfoManager.getInstance().setEnableAnchorHomepage(sysParamsInfoEntity.isEnableAnchorHomepage());
//                        SysConfigInfoManager.getInstance().setEnableUserHomepage(sysParamsInfoEntity.isEnableUserHomepage());
//                        SysConfigInfoManager.getInstance().setEnableGuard(sysParamsInfoEntity.isEnableGuard());
//                        SysConfigInfoManager.getInstance().setEnableVisitorLive(sysParamsInfoEntity.isEnableVisitorLive());
//                        SysConfigInfoManager.getInstance().setEnableScore(sysParamsInfoEntity.isEnableScore());
//                        SysConfigInfoManager.getInstance().setEnableWeekStar(sysParamsInfoEntity.isEnableWeekStar());
//                        SysConfigInfoManager.getInstance().setEnableCommerce(sysParamsInfoEntity.isEnableCommerce());
//                        SysConfigInfoManager.getInstance().setEnableVideoStreamEncode(sysParamsInfoEntity.isEnableVideoStreamEncode());
//                        SysConfigInfoManager.getInstance().setEnableGiftWall(sysParamsInfoEntity.isEnableGiftWall());
//                        SysConfigInfoManager.getInstance().setEnableAchievement(sysParamsInfoEntity.isEnableAchievement());
//                        SysConfigInfoManager.getInstance().setEnableComponents(sysParamsInfoEntity.isEnableComponents());
//                        SysConfigInfoManager.getInstance().setEnableTranslation(sysParamsInfoEntity.isEnableTranslation());
//                        SysConfigInfoManager.getInstance().setEnablePrivateMsg(sysParamsInfoEntity.isEnablePrivateMsg());
//                        SysConfigInfoManager.getInstance().setEnableFeeTag(sysParamsInfoEntity.isEnableFeeTag());
//                        SysConfigInfoManager.getInstance().setEnableLiveHelperJump(sysParamsInfoEntity.isEnableLiveHelperJump());
//                        SysConfigInfoManager.getInstance().setWatermarkConfig(sysParamsInfoEntity.watermarkConfig);
//                        SysConfigInfoManager.getInstance().setEnableLogEventReport(sysParamsInfoEntity.isEnableLogEventReport());
//                        SysConfigInfoManager.getInstance().setEnableQMInteract(sysParamsInfoEntity.isEnableIntimate());
//                        SysConfigInfoManager.getInstance().setEnableYYLink(sysParamsInfoEntity.isEnableVideoRoom());
//                        SysConfigInfoManager.getInstance().setEnableBluetooth(sysParamsInfoEntity.isEnableBluetooth());
//                        SysConfigInfoManager.getInstance().setEnableLocation(sysParamsInfoEntity.isEnableNearbyModule());
//                        SysConfigInfoManager.getInstance().setEnableBluetoothTease(sysParamsInfoEntity.isEnableTease());
//                        SysConfigInfoManager.getInstance().setEnableCardSetup(sysParamsInfoEntity.isEnableCardSetup());
//                        SysConfigInfoManager.getInstance().setEnableAnchorDataCenter(sysParamsInfoEntity.getEnableAnchorDataCenter());
//                        SysConfigInfoManager.getInstance().setAnchorDataCenterUrl(sysParamsInfoEntity.anchorDataCenterUrl);
////                        SensorsDataAPIUtils.initSensorsDataAPI(SLLiveSDK.this.application);
////                        ResHotLoadManager.getInstance().dealResLoad(sysParamsInfoEntity);
//                        Log.e("SLLiveSDK:::","initSysConfig:::1105");
//                    }
//                }
//
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver
//                public void onError(int i, String str) {
//                    super.onError(i, str);
//                }
//            });
//        }
//    }
//
//    public void loadUserInfo(UserEntity userEntity) {
//        UserInfoManager.getInstance().loadUserInfo(userEntity);
//    }
//
//    public boolean isUserLogin() {
//        return UserInfoManager.getInstance().isLogin();
//    }
//
//    public void loginSDK(Context context) {
//        loginSDK(context, null);
//    }
//
//    public void loginSDK(final Context context, final LiveSDKLoginCallbackListener liveSDKLoginCallbackListener) {
//        if (context != null && AppUtils.isApiService()) {
//            LogEventUtils.uploadLoginButtonClick();
//            ApiRetrofit.getInstance().getApiService().getSdkLoginService(new RequestParams().getSDKLoginParams()).map(new ServerResultFunction<UserEntity>() { // from class: com.slzhibo.library.SLLiveSDK.6
//            }).onErrorResumeNext(new HttpResultFunction<UserEntity>() { // from class: com.slzhibo.library.SLLiveSDK.5
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpRxObserver(context, new ResultCallBack<UserEntity>() { // from class: com.slzhibo.library.SLLiveSDK.4
//                public void onSuccess(UserEntity userEntity) {
//                    if (userEntity != null) {
//                        UserInfoManager.getInstance().setToken(userEntity.getToken());
//                        UserInfoManager.getInstance().setUserId(userEntity.getUserId());
//                        UserInfoManager.getInstance().setNobilityType(userEntity.getNobilityType());
//                        UserInfoManager.getInstance().setEnterHide(userEntity.isLiveEnterHideBoolean());
//                        UserInfoManager.getInstance().setInBanGroup(userEntity.isInBanGroup());
//                        UserInfoManager.getInstance().setSuperAdmin(userEntity.isSuperAdmin());
//                        UserInfoManager.getInstance().setVisitorUser(userEntity.isVisitorUser());
//                        UserInfoManager.getInstance().setEnterLivePermission(userEntity.isEnterLivePermissionBoolean());
//                        UserInfoManager.getInstance().setUserRole(userEntity.getRole());
//                        LogEventUtils.uploadLoginSuccess(userEntity.getUserId(), true);
//                        if (userEntity.isNewUserBoolean()) {
//                            LogEventUtils.uploadRegisterSuccess(userEntity.getUserId(), AppUtils.isVisitor() ? "2" : "1", 1);
//                        }
//                        DBUtils.saveAllAttentionAnchor(userEntity.getFollowTargetIds());
//                        DBUtils.saveAllShieldUser(userEntity.getShieldTargetIds());
//                        if (!UserInfoManager.getInstance().isVisitorUser()) {
//                            EventBus.getDefault().post(new LoginEvent());
//                        }
//                        LiveSDKLoginCallbackListener liveSDKLoginCallbackListener2 = liveSDKLoginCallbackListener;
//                        if (liveSDKLoginCallbackListener2 != null) {
//                            liveSDKLoginCallbackListener2.onLoginSuccessListener(context);
//                        }
//                    }
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str) {
//                    UserInfoManager.getInstance().setToken(null);
//                    UserInfoManager.getInstance().setUserId(null);
//                    UserInfoManager.getInstance().setNobilityType(-1);
//                    LiveSDKLoginCallbackListener liveSDKLoginCallbackListener2 = liveSDKLoginCallbackListener;
//                    if (liveSDKLoginCallbackListener2 != null) {
//                        liveSDKLoginCallbackListener2.onLoginFailListener(context);
//                    }
//                }
//            }));
//        }
//    }
//
//    public void exitSDK(Context context) {
//        exitSDK(context, null);
//    }
//
//    public void exitSDK(final Context context, final LiveSDKLogoutCallbackListener liveSDKLogoutCallbackListener) {
//        if (context != null && AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getExitSDKService(new RequestParams().getExitSDKParams()).map(new ServerResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.9
//            }).onErrorResumeNext(new HttpResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.8
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() { // from class: com.slzhibo.library.SLLiveSDK.7
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onSuccess(Object obj) {
//                    EventBus.getDefault().post(new LogoutEvent());
//                    UserInfoManager.getInstance().clearLoginInfo();
////                    TaskBoxUtils.getInstance().clear();////////////////////////////////////////////////////////////////////////
//                    LiveSDKLogoutCallbackListener liveSDKLogoutCallbackListener2 = liveSDKLogoutCallbackListener;
//                    if (liveSDKLogoutCallbackListener2 != null) {
//                        liveSDKLogoutCallbackListener2.onLogoutSuccessListener(context);
//                    }
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str) {
//                    LiveSDKLogoutCallbackListener liveSDKLogoutCallbackListener2 = liveSDKLogoutCallbackListener;
//                    if (liveSDKLogoutCallbackListener2 != null) {
//                        liveSDKLogoutCallbackListener2.onLogoutFailListener(context);
//                    }
//                }
//            }));
//        }
//    }
//
//    public void onUpdateUserAvatar(Context context, final String str) {
//        if (context != null && AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getUpdateUserInfoService(new RequestParams().getUpdateAvatarParams(str)).map(new ServerResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.12
//            }).onErrorResumeNext(new HttpResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.11
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() { // from class: com.slzhibo.library.SLLiveSDK.10
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str2) {
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onSuccess(Object obj) {
//                    UserInfoManager.getInstance().setAvatar(str);
//                }
//            }));
//        }
//    }
//
//    public void onUpdateUserNickName(Context context, final String str) {
//        if (context != null && AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getUpdateUserInfoService(new RequestParams().getUpdateNicknameParams(str)).map(new ServerResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.15
//            }).onErrorResumeNext(new HttpResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.14
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() { // from class: com.slzhibo.library.SLLiveSDK.13
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str2) {
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onSuccess(Object obj) {
//                    UserInfoManager.getInstance().setNickname(str);
//                }
//            }));
//        }
//    }
//
//    public void onUpdateUserSex(Context context, final String str) {
//        if (context != null && AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getUpdateUserInfoService(new RequestParams().getUpdateSexParams(str)).map(new ServerResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.18
//            }).onErrorResumeNext(new HttpResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.17
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() { // from class: com.slzhibo.library.SLLiveSDK.16
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str2) {
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onSuccess(Object obj) {
//                    UserInfoManager.getInstance().setSex(str);
//                }
//            }));
//        }
//    }
//
//    public void onUpdateUserRisk(Context context, final String str) {
//        if (context != null && AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getUpdateUserInfoService(new RequestParams().getUpdateIsRiskParams(str)).map(new ServerResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.21
//            }).onErrorResumeNext(new HttpResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.20
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() { // from class: com.slzhibo.library.SLLiveSDK.19
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str2) {
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onSuccess(Object obj) {
//                    UserInfoManager.getInstance().setRisk(str);
//                }
//            }));
//        }
//    }
//
//    public void onAnchorLiveStatus(final Context context, String str, final OnLiveStatusCallbackListener onLiveStatusCallbackListener) {
//        if (AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getLiveStatusService(new RequestParams().getLiveStatusParams(UserInfoManager.getInstance().getAppId(), str)).map(new ServerResultFunction<LiveStatusEntity>() { // from class: com.slzhibo.library.SLLiveSDK.24
//            }).onErrorResumeNext(new HttpResultFunction<LiveStatusEntity>() { // from class: com.slzhibo.library.SLLiveSDK.23
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpRxObserver(context, new ResultCallBack<LiveStatusEntity>() { // from class: com.slzhibo.library.SLLiveSDK.22
//                public void onSuccess(LiveStatusEntity liveStatusEntity) {
//                    OnLiveStatusCallbackListener onLiveStatusCallbackListener2 = onLiveStatusCallbackListener;
//                    if (onLiveStatusCallbackListener2 != null) {
//                        onLiveStatusCallbackListener2.onLiveStatusSuccess(context, liveStatusEntity);
//                    }
//                }
//
//                @Override // com.slzhibo.library.http.ResultCallBack
//                public void onError(int i, String str2) {
//                    OnLiveStatusCallbackListener onLiveStatusCallbackListener2 = onLiveStatusCallbackListener;
//                    if (onLiveStatusCallbackListener2 != null) {
//                        onLiveStatusCallbackListener2.onLiveStatusFail(i, str2);
//                    }
//                }
//            }));
//        }
//    }
//
//    public void onSendMLCallUrlRequest() {
//        if (AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getMLCallUrlService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<MLCallUrlEntity>() { // from class: com.slzhibo.library.SLLiveSDK.27
//            }).onErrorResumeNext(new HttpResultFunction<MLCallUrlEntity>() { // from class: com.slzhibo.library.SLLiveSDK.26
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<MLCallUrlEntity>(false) { // from class: com.slzhibo.library.SLLiveSDK.25
//                public void accept(MLCallUrlEntity mLCallUrlEntity) {
//                    if (mLCallUrlEntity != null && !TextUtils.isEmpty(mLCallUrlEntity.callUri)) {
//                        SLLiveSDK.this.updateVideoCallServerUrl(mLCallUrlEntity.callUri);
//                    }
//                }
//            });
//        }
//    }
//
////    public void onUpdateBalance() {
////        EventBus.getDefault().post(new UpdateBalanceEvent());
////    }
//
//    public void clearUserTokenInfo() {
//        UserInfoManager.getInstance().clearTokenInfo();
//    }
//
//    public void onAppLiveListCallback(int i, int i2, final ResultCallBack<String> resultCallBack) {
//        if (AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getLiveOpenListService(new RequestParams().getLiveOpenListParams(i, 1, i2)).map(new ServerResultFunction<HttpResultPageModel<LiveEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.30
//            }).flatMap(new Function<HttpResultPageModel<LiveEntity>, ObservableSource<String>>() { // from class: com.slzhibo.library.SLLiveSDK.29
//                public ObservableSource<String> apply(HttpResultPageModel<LiveEntity> httpResultPageModel) throws Exception {
//                    return Observable.just(SLLiveSDK.this.formatAppLiveListJson(httpResultPageModel));
//                }
//            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<String>(false) { // from class: com.slzhibo.library.SLLiveSDK.28
//                public void accept(String str) {
//                    if (resultCallBack != null) {
//                        SLLiveSDK.this.onAllLiveListUpdate(null);
//                        if (TextUtils.isEmpty(str)) {
//                            resultCallBack.onSuccess("");
//                        } else {
//                            resultCallBack.onSuccess(str);
//                        }
//                    }
//                }
//
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver
//                public void onError(int i3, String str) {
//                    super.onError(i3, str);
//                    ResultCallBack resultCallBack2 = resultCallBack;
//                    if (resultCallBack2 != null) {
//                        resultCallBack2.onError(i3, str);
//                    }
//                }
//            });
//        }
//    }
//
////    public void onAppRankingCallback(final OnAppRankingCallback onAppRankingCallback) {
////        if (AppUtils.isApiService()) {
////            ApiRetrofit.getInstance().getApiService().getIndexRankService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<IndexRankEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.33
////            }).onErrorResumeNext(new HttpResultFunction<List<IndexRankEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.32
////            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<IndexRankEntity>>(false) { // from class: com.slzhibo.library.SLLiveSDK.31
////                public void accept(List<IndexRankEntity> list) {
////                    if (list != null) {
////                        List<String> arrayList = new ArrayList<>();
////                        List<String> arrayList2 = new ArrayList<>();
////                        if (list.isEmpty()) {
////                            OnAppRankingCallback onAppRankingCallback2 = onAppRankingCallback;
////                            if (onAppRankingCallback2 != null) {
////                                onAppRankingCallback2.onAppRankingSuccess(arrayList, arrayList2);
////                                return;
////                            }
////                            return;
////                        }
////                        for (IndexRankEntity indexRankEntity : list) {
////                            if (TextUtils.equals(indexRankEntity.getType(), ConstantUtils.RANK_TYPE_INCOME)) {
////                                arrayList = indexRankEntity.getAvatars();
////                            }
////                            if (TextUtils.equals(indexRankEntity.getType(), ConstantUtils.RANK_TYPE_EXPENSE)) {
////                                arrayList2 = indexRankEntity.getAvatars();
////                            }
////                        }
////                        OnAppRankingCallback onAppRankingCallback3 = onAppRankingCallback;
////                        if (onAppRankingCallback3 != null) {
////                            onAppRankingCallback3.onAppRankingSuccess(arrayList, arrayList2);
////                        }
////                    }
////                }
////
////                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
////                public void onError(Throwable th) {
////                    super.onError(th);
////                    OnAppRankingCallback onAppRankingCallback2 = onAppRankingCallback;
////                    if (onAppRankingCallback2 != null) {
////                        onAppRankingCallback2.onAppRankingFail();
////                    }
////                }
////            });
////        }
////    }
//
////    public void toRankingActivity(Context context, int i) {
////        if (!AppUtils.isApiDomainError(context) && i <= 1 && i >= 0) {
////            Intent intent = new Intent(context, RankingNewActivity.class);
////            intent.putExtra(ConstantUtils.RESULT_FLAG, i);
////            ArrayList<String> arrayList = new ArrayList<>();
////            if (AppUtils.isEnableWeekStar()) {
////                arrayList.add(ConstantUtils.RANK_TYPE_WEEKSTAR);
////            }
////            if (AppUtils.isEnableCommerce()) {
////                arrayList.add(ConstantUtils.RANK_TYPE_COMMERCE);
////            }
////            intent.putStringArrayListExtra(ConstantUtils.RESULT_ITEM, arrayList);
////            context.startActivity(intent);
////        }
////    }
//
//    public void statisticsReport(String str) {
//        if (StatisticsApiRetrofit.getInstance().isApiService()) {
//            HashMap hashMap = new HashMap();
//            hashMap.put(AopConstants.APP_EVENT_KEY, str);
//            StatisticsApiRetrofit.getInstance().getApiService().statisticsReportService(hashMap).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<ResponseBody>() { // from class: com.slzhibo.library.SLLiveSDK.34
//                public void accept(ResponseBody responseBody) {
//                }
//            });
//        }
//    }
//
//    public void clickBannerReport(Context context, String str) {
//        if (AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().getClickAdService(new RequestParams().getClickAdParams(str)).map(new ServerResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.37
//            }).onErrorResumeNext(new HttpResultFunction<Object>() { // from class: com.slzhibo.library.SLLiveSDK.36
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<Object>(false) { // from class: com.slzhibo.library.SLLiveSDK.35
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver
//                public void accept(Object obj) {
//                }
//            });
//        }
//    }
//
//    public void onAllLiveListUpdate(LifecycleTransformer lifecycleTransformer) {
//        onAllLiveListUpdate(lifecycleTransformer, null);
//    }
//
//    public void onAllLiveListUpdate(LifecycleTransformer lifecycleTransformer, final ResultCallBack<List<LiveEntity>> resultCallBack) {
//        if (AppUtils.isApiService()) {
//            Observable observeOn = ApiRetrofit.getInstance().getApiService().getAllListService(new RequestParams().getPageListParams(1, 40)).map(new ServerResultFunction<HttpResultPageModel<LiveEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.38
//            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
//            if (lifecycleTransformer != null) {
//                observeOn.compose(lifecycleTransformer);
//            }
//            observeOn.subscribe(new Consumer() { // from class: com.slzhibo.library.-$$Lambda$SLLiveSDK$7JKbLN19axhP3xm80OinL4IY_2k
//                @SuppressLint("CheckResult")
//                @Override // io.reactivex.functions.Consumer
//                public final void accept(Object obj) {
//                    Log.e("SLLiveSDK:::","onAllLiveListUpdate:::");
////                    SLLiveSDK.lambda$onAllLiveListUpdate$1(ResultCallBack.this, (HttpResultPageModel) obj);
//                }
//            });
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: package-private */
//    public static /* synthetic */ void lambda$onAllLiveListUpdate$1(ResultCallBack resultCallBack, HttpResultPageModel httpResultPageModel) throws Exception {
//        if (httpResultPageModel != null) {
//            LiveManagerUtils.getInstance().setLiveList(httpResultPageModel.dataList);
//            if (resultCallBack != null) {
//                resultCallBack.onSuccess(httpResultPageModel.dataList);
//            }
//        }
//    }
//
//    public void loadOperationActivityDialog(Context context) {
//        if (context != null && (context instanceof FragmentActivity)) {
//            final FragmentManager supportFragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//            ArrayList<ActivityListEntity> operateActivityListByType = CacheUtils.getOperateActivityListByType(false);
//            if (operateActivityListByType != null) {
//                Observable.just(operateActivityListByType).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.39
//                    public void accept(ArrayList<ActivityListEntity> arrayList) throws Exception {
//                        if (arrayList != null && !arrayList.isEmpty()) {
//                            Iterator<ActivityListEntity> it2 = arrayList.iterator();
//                            while (it2.hasNext()) {
//                                ActivityListEntity next = it2.next();
//                                if (next.isCorrectLink()) {
//                                    ActivityDBEntity activityItemInfoById = DBUtils.getActivityItemInfoById(next.id);
//                                    DBUtils.saveOrUpdateActivityItemInfo(next);
//                                    if (activityItemInfoById == null) {
//                                        GiftBagWebViewDialog.newInstance(next).show(supportFragmentManager);
//                                    } else if (activityItemInfoById.isShowActivityDialog()) {
//                                        GiftBagWebViewDialog.newInstance(next).show(supportFragmentManager);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                });
//            }
//        }
//    }
//
////    public void toFastAuthLoginActivity(Context context) {
////        if (context instanceof Activity) {
////            Intent intent = ((Activity) context).getIntent();
////            String stringExtra = intent.getStringExtra(ConstantUtils.LOGIN_ACCESS_TOKEN);
////            String stringExtra2 = intent.getStringExtra(ConstantUtils.LOGIN_TYPE_LOGO);
////            Intent intent2 = new Intent(context, FastAuthLoginActivity.class);
////            intent2.putExtra(ConstantUtils.LOGIN_ACCESS_TOKEN, stringExtra);
////            intent2.putExtra(ConstantUtils.LOGIN_TYPE_LOGO, stringExtra2);
////            context.startActivity(intent2);
////        }
////    }
//
//    private String getAuthStr(byte[] bArr) throws Throwable {
//        if (bArr != null && bArr.length >= 1) {
//            try {
//                return StringUtils.compress(EncryptUtil.DESEncrypt("246887c3-ee20-4fe8-a320-1fde4a8d10b6", StringUtils.toHexString(bArr)));
//            } catch (Exception unused) {
//            }
//        }
//        return "";
//    }
//
//    private void initHttpCacheDir(Application application) {
//        try {
//            HttpResponseCache.install(new File(application.getCacheDir(), "http"), 134217728L);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public String formatAppLiveListJson(HttpResultPageModel<LiveEntity> httpResultPageModel) {
//        List<LiveEntity> list;
//        if (httpResultPageModel == null || (list = httpResultPageModel.dataList) == null || list.isEmpty()) {
//            return "";
//        }
//        ArrayList arrayList = new ArrayList();
//        for (LiveEntity liveEntity : list) {
//            AppLiveItemEntity appLiveItemEntity = new AppLiveItemEntity();
//            appLiveItemEntity.appId = liveEntity.appId;
//            appLiveItemEntity.openId = liveEntity.openId;
//            appLiveItemEntity.userId = liveEntity.userId;
//            appLiveItemEntity.liveId = liveEntity.liveId;
//            appLiveItemEntity.nickname = liveEntity.nickname;
//            appLiveItemEntity.avatar = liveEntity.avatar;
//            appLiveItemEntity.sex = liveEntity.sex;
//            appLiveItemEntity.liveCoverUrl = liveEntity.liveCoverUrl;
//            appLiveItemEntity.tag = liveEntity.tag;
//            appLiveItemEntity.topic = liveEntity.topic;
//            appLiveItemEntity.popularity = liveEntity.popularity;
//            appLiveItemEntity.liveStatus = liveEntity.liveStatus;
//            appLiveItemEntity.markerUrl = liveEntity.markerUrl;
//            appLiveItemEntity.leftPendantUrl = liveEntity.pendantUrl;
//            appLiveItemEntity.rightPendantUrl = liveEntity.getCoverIdentityUrl();
//            arrayList.add(appLiveItemEntity);
//        }
//        return GsonUtils.toJson(arrayList);
//    }
//
//    /* JADX INFO: Access modifiers changed from: private */
//    public void initQbSdkWebView() {
//        Observable.just(true).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.slzhibo.library.-$$Lambda$SLLiveSDK$Qqv6EVQx7g0XgyKmrDymeCR4ZLc
//            @Override // io.reactivex.functions.Consumer
//            public final void accept(Object obj) throws Exception {
//                SLLiveSDK.this.lambda$initQbSdkWebView$2$SLLiveSDK((Boolean) obj);
//            }
//        });
//    }
//
//    public /* synthetic */ void lambda$initQbSdkWebView$2$SLLiveSDK(Boolean bool) throws Exception {
//        QbSdk.setDownloadWithoutWifi(true);
//        QbSdk.initX5Environment(this.application, new QbSdk.PreInitCallback() { // from class: com.slzhibo.library.SLLiveSDK.40
//            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
//            public void onCoreInitFinished() {
//            }
//
//            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
//            public void onViewInitFinished(boolean z) {
//                if (!z) {
//                    SLLiveSDK.this.initQbSdkWebView();
//                }
//            }
//        });
//        QbSdk.setTbsListener(new TbsListener() { // from class: com.slzhibo.library.SLLiveSDK.41
//            @Override // com.tencent.smtt.sdk.TbsListener
//            public void onDownloadFinish(int i) {
//            }
//
//            @Override // com.tencent.smtt.sdk.TbsListener
//            public void onDownloadProgress(int i) {
//            }
//
//            @Override // com.tencent.smtt.sdk.TbsListener
//            public void onInstallFinish(int i) {
//            }
//        });
//    }
//
//    private void initSDKSendRequestData() {
//        if (AppUtils.isApiService()) {
//            ApiRetrofit.getInstance().getApiService().searchActivityListByAppIdService(new RequestParams().getAppIdParams()).map(new ServerResultFunction<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.44
//            }).onErrorResumeNext(new HttpResultFunction<ArrayList<ActivityListEntity>>() { // from class: com.slzhibo.library.SLLiveSDK.43
//            }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<ArrayList<ActivityListEntity>>(false) { // from class: com.slzhibo.library.SLLiveSDK.42
//                public void accept(ArrayList<ActivityListEntity> arrayList) {
//                    if (arrayList != null) {
//                        CacheUtils.saveOperateActivityList(arrayList);
//                    }
//                }
//
//                @Override // com.slzhibo.library.utils.live.SimpleRxObserver, io.reactivex.Observer
//                public void onError(Throwable th) {
//                    super.onError(th);
//                    CacheUtils.saveOperateActivityList(null);
//                }
//            });
//        }
//    }
//}
//
//
//
///* compiled from: lambda */
///* renamed from: com.slzhibo.library.-$$Lambda$SLLiveSDK$y3IoTNyGznM5nzFRrM0RJKA5-rI  reason: invalid class name */
///* loaded from: classes6.dex */
//final /* synthetic */ class $$Lambda$SLLiveSDK$y3IoTNyGznM5nzFRrM0RJKA5rI implements Consumer {
//    public static final /* synthetic */ $$Lambda$SLLiveSDK$y3IoTNyGznM5nzFRrM0RJKA5rI INSTANCE = new $$Lambda$SLLiveSDK$y3IoTNyGznM5nzFRrM0RJKA5rI();
//
//    private /* synthetic */ $$Lambda$SLLiveSDK$y3IoTNyGznM5nzFRrM0RJKA5rI() {
//    }
//
//    @Override // io.reactivex.functions.Consumer
//    public final void accept(Object obj) throws Exception {
//        SLLiveSDK.lambda$new$0((Throwable) obj);
//    }
//}

