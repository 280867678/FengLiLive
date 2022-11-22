package com.slzhibo.library.ui.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.slzhibo.library.R;
import com.slzhibo.library.SLLiveSDK;
import com.slzhibo.library.base.BasePresenter;
import com.slzhibo.library.download.GiftDownLoadManager;
import com.slzhibo.library.http.ApiRetrofit;
import com.slzhibo.library.http.ApiService;
import com.slzhibo.library.http.CacheApiRetrofit;
import com.slzhibo.library.http.HttpRxObserver;
import com.slzhibo.library.http.RequestParams;
import com.slzhibo.library.http.ResultCallBack;
import com.slzhibo.library.http.function.HttpResultFunction;
import com.slzhibo.library.http.function.ServerResultFunction;
import com.slzhibo.library.model.AnchorEntity;
import com.slzhibo.library.model.BannerEntity;
import com.slzhibo.library.model.BoomStatusEntity;
import com.slzhibo.library.model.CheckTicketEntity;
import com.slzhibo.library.model.ComponentsEntity;
import com.slzhibo.library.model.GiftDownloadItemEntity;
import com.slzhibo.library.model.GuardItemEntity;
import com.slzhibo.library.model.HJProductEntity;
import com.slzhibo.library.model.LYDevEntity;
import com.slzhibo.library.model.LiveEntity;
import com.slzhibo.library.model.LiveInitInfoEntity;
import com.slzhibo.library.model.MyAccountEntity;
import com.slzhibo.library.model.OnLineUsersEntity;
import com.slzhibo.library.model.PKRecordEntity;
import com.slzhibo.library.model.PropConfigEntity;
import com.slzhibo.library.model.QMInteractTaskEntity;
import com.slzhibo.library.model.QMInteractTaskListEntity;
import com.slzhibo.library.model.TaskBoxEntity;
import com.slzhibo.library.model.TrumpetStatusEntity;
import com.slzhibo.library.model.UserEntity;
import com.slzhibo.library.model.WSAddressEntity;
import com.slzhibo.library.model.YYLinkApplyEntity;
import com.slzhibo.library.model.YYNoticeEntity;
import com.slzhibo.library.model.db.GiftBoxEntity;
import com.slzhibo.library.ui.view.dialog.LoadingDialog;
import com.slzhibo.library.ui.view.iview.ISLLiveView;
import com.slzhibo.library.utils.AppUtils;
import com.slzhibo.library.utils.CacheUtils;
import com.slzhibo.library.utils.ConstantUtils;
import com.slzhibo.library.utils.GsonUtils;
import com.slzhibo.library.utils.NumberUtils;
import com.slzhibo.library.utils.UserInfoManager;
import com.slzhibo.library.utils.live.SimpleRxObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SLLivePresenter extends BasePresenter<ISLLiveView> {
    private CompositeDisposable compositeDisposable;
    private LoadingDialog dialog = null;
    private boolean isLiveLeaveAction = false;

    public SLLivePresenter(Context context, ISLLiveView iSLLiveView) {
        super(context, iSLLiveView);
        if (this.compositeDisposable == null) {
            this.compositeDisposable = new CompositeDisposable();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showLoadingDialog(boolean z) {
        LoadingDialog loadingDialog;
        if (this.dialog == null) {
            this.dialog = new LoadingDialog(getContext(), false);
        }
        if (z && (loadingDialog = this.dialog) != null && !loadingDialog.isShowing()) {
            this.dialog.show();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dismissProgressDialog() {
        try {
            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    public void setEnterOrLeaveLiveRoomMsg(final String str) {
        if (!AppUtils.isVisitor()) {
            if (TextUtils.equals(ConstantUtils.ENTER_TYPE, str)) {
                this.isLiveLeaveAction = false;
                compositeDisposableAdd(Observable.timer(10, TimeUnit.MINUTES, AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass1 */

                    public void accept(Long l) throws Exception {
                        SLLivePresenter.this.isLiveLeaveAction = true;
                        ( SLLivePresenter.this).mApiService.getLiveEnterActionService(new RequestParams().getUserLiveActionParams(str)).map(new ServerResultFunction<Object>() {
                            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass1.AnonymousClass2 */
                        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<Object>(SLLivePresenter.this.getContext(), false) {
                            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass1.AnonymousClass1 */

                            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                            public void accept(Object obj) {
                            }

                            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                            public void onSubscribe(Disposable disposable) {
                                super.onSubscribe(disposable);
                                SLLivePresenter.this.compositeDisposableAdd(disposable);
                            }
                        });
                    }
                }));
            } else if (this.isLiveLeaveAction) {
                this.mApiService.getLiveLeaveActionService(new RequestParams().getUserLiveActionParams(str)).map(new ServerResultFunction<Object>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass3 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass2 */

                    @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                    public void accept(Object obj) {
                    }

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }
                });
            }
        }
    }

    public void getGiftList() {
        this.mApiService.giftListV2(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<GiftDownloadItemEntity>>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass6 */
        }).flatMap(new Function<List<GiftDownloadItemEntity>, ObservableSource<List<GiftDownloadItemEntity>>>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass5 */

            public ObservableSource<List<GiftDownloadItemEntity>> apply(List<GiftDownloadItemEntity> list) throws Exception {
                GiftDownLoadManager.getInstance().updateLocalDownloadList(list);
                ArrayList arrayList = new ArrayList();
                for (GiftDownloadItemEntity giftDownloadItemEntity : list) {
                    if (!giftDownloadItemEntity.isLuckyGift()) {
                        arrayList.add(giftDownloadItemEntity);
                    }
                }
                return Observable.just(arrayList);
            }
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<GiftDownloadItemEntity>>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass4 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(List<GiftDownloadItemEntity> list) {
                if (list != null) {
                    ((ISLLiveView) SLLivePresenter.this.getView()).onGiftListSuccess(list);
                }
            }
        });
    }

    public void attentionAnchor(String str, int i) {
        this.mApiService.getAttentionAnchorService(new RequestParams().getAttentionAnchorParams(str, i)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass8 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass7 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onAttentionSuccess();
            }
        });
    }

    public void getAnchorInfo(String str) {
        ApiRetrofit.getInstance().getApiService().getAnchorInfoService(new RequestParams().getAnchorInfoParams(str)).map(new ServerResultFunction<AnchorEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass10 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<AnchorEntity>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass9 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(AnchorEntity anchorEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onAnchorInfoSuccess(anchorEntity);
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onAnchorInfoFail();
            }
        });
    }

    public void getUserCardInfo(String str) {
        this.mApiService.getUserCardInfo(new RequestParams().getUserCardInfoParams(str)).map(new ServerResultFunction<UserEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass12 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<UserEntity>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass11 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(UserEntity userEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onUserCardInfoSuccess(userEntity);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onUserCardInfoFail(i, str);
            }
        });
    }

    public void getUserOver() {
        getUserOver(false, null);
    }

    public void getUserOver(final boolean z, final ResultCallBack<MyAccountEntity> resultCallBack) {
        ApiRetrofit.getInstance().getApiService().getQueryBalanceService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<MyAccountEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass14 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<MyAccountEntity>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass13 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
                if (z) {
                    SLLivePresenter.this.showLoadingDialog(true);
                }
            }

            public void accept(MyAccountEntity myAccountEntity) {
                if (z) {
                    SLLivePresenter.this.dismissProgressDialog();
                }
                ((ISLLiveView) SLLivePresenter.this.getView()).onUserOverSuccess(myAccountEntity);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(myAccountEntity);
                }
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                if (z) {
                    SLLivePresenter.this.dismissProgressDialog();
                }
                ((ISLLiveView) SLLivePresenter.this.getView()).onUserOverFail();
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(0, "");
                }
            }
        });
    }

    public void getCurrentOnlineUserList(final String str, long j) {
        compositeDisposableAdd(Observable.interval(0, j, TimeUnit.SECONDS).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Long>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass15 */

            public void accept(Long l) throws Exception {
                ( SLLivePresenter.this).mApiService.getCurrentOnlineUserListService(new RequestParams().getCurrentOnlineUserList(str)).map(new ServerResultFunction<OnLineUsersEntity>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass15.AnonymousClass2 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<OnLineUsersEntity>(SLLivePresenter.this.getContext(), false) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass15.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(OnLineUsersEntity onLineUsersEntity) {
                        if (onLineUsersEntity != null) {
                            ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAudiencesSuccess(onLineUsersEntity);
                        }
                    }
                });
            }
        }));
    }

    public void getLiveInitInfo(final String str, final String str2, final boolean z, final boolean z2, boolean z3, final boolean z4) {
        this.mApiService.getLiveInitInfoService(new RequestParams().getLiveInitInfoParams(str, str2, z3)).map(new ServerResultFunction<LiveInitInfoEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass17 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<LiveInitInfoEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass16 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
                SLLivePresenter.this.showLoadingDialog(z);
            }

            public void accept(LiveInitInfoEntity liveInitInfoEntity) {
                SLLivePresenter.this.dismissProgressDialog();
                ((ISLLiveView) SLLivePresenter.this.getView()).onLiveInitInfoSuccess(str, str2, liveInitInfoEntity, z2, z4);
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                super.onError(th);
                SLLivePresenter.this.dismissProgressDialog();
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
                ((ISLLiveView) SLLivePresenter.this.getView()).onLiveInitInfoFail(i, str);
            }
        });
    }

    public void getAdImageList(final String str) {
        if (SLLiveSDK.getSingleton().isLiveAdvChannel()) {
            initAdImageList(str);
        } else if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
            SLLiveSDK.getSingleton().sdkCallbackListener.onAdvChannelListener(getContext(), str, new SLLiveSDK.OnAdvChannelCallbackListener() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass18 */

                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataSuccess(Context context, String str) {
                    try {
                        if (TextUtils.isEmpty(str)) {
                            SLLivePresenter.this.initAdImageList(str);
                            return;
                        }
                        List<BannerEntity> list = (List) GsonUtils.fromJson(str, new TypeToken<List<BannerEntity>>() {
                            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass18.AnonymousClass1 */
                        }.getType());
                        if (list != null) {
                            if (!list.isEmpty()) {
                                ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAdListSuccess(str, list);
                                return;
                            }
                        }
                        SLLivePresenter.this.initAdImageList(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        SLLivePresenter.this.initAdImageList(str);
                    }
                }

                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataFail(int i, String str) {
                    SLLivePresenter.this.initAdImageList(str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initAdImageList(final String str) {
        if (CacheUtils.isBannerListByCacheDisk(str)) {
            Observable.just(CacheUtils.getBannerListByCacheDisk(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<BannerEntity>>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass19 */

                public void accept(List<BannerEntity> list) {
                    ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAdListSuccess(str, list);
                }
            });
        } else {
            ApiRetrofit.getInstance().getApiService().getBannerListService(new RequestParams().getBannerListParams(str)).map(new ServerResultFunction<List<BannerEntity>>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass21 */
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<BannerEntity>>(getContext(), false) {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass20 */

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onSubscribe(Disposable disposable) {
                    super.onSubscribe(disposable);
                    SLLivePresenter.this.compositeDisposableAdd(disposable);
                }

                public void accept(List<BannerEntity> list) {
                    if (list != null) {
                        CacheUtils.saveBannerListByCacheDisk(str, list);
                        ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAdListSuccess(str, list);
                    }
                }

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onError(Throwable th) {
                    ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAdListFail(str);
                }
            });
        }
    }

    public void getLiveAdNoticeList() {
        if (SLLiveSDK.getSingleton().isLiveAdvChannel()) {
            initLiveAdNoticeList();
        } else if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
            SLLiveSDK.getSingleton().sdkCallbackListener.onAdvChannelLiveNoticeListener(getContext(), new SLLiveSDK.OnAdvChannelCallbackListener() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass22 */

                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataSuccess(Context context, String str) {
                    try {
                        if (TextUtils.isEmpty(str)) {
                            SLLivePresenter.this.initLiveAdNoticeList();
                            return;
                        }
                        BannerEntity bannerEntity = (BannerEntity) GsonUtils.fromJson(str, BannerEntity.class);
                        if (bannerEntity == null) {
                            SLLivePresenter.this.initLiveAdNoticeList();
                        } else {
                            ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAdNoticeSuccess(bannerEntity);
                        }
                    } catch (Exception unused) {
                        SLLivePresenter.this.initLiveAdNoticeList();
                    }
                }

                @Override // com.slzhibo.library.SLLiveSDK.OnAdvChannelCallbackListener
                public void onAdvDataFail(int i, String str) {
                    SLLivePresenter.this.initLiveAdNoticeList();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initLiveAdNoticeList() {
        if (CacheUtils.isLiveNoticeByCacheDisk()) {
            Observable.just(CacheUtils.getLiveNoticeByCacheDisk()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<BannerEntity>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass23 */

                public void accept(BannerEntity bannerEntity) {
                    ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAdNoticeSuccess(bannerEntity);
                }
            });
        } else {
            ApiRetrofit.getInstance().getApiService().getNoticeListService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<BannerEntity>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass25 */
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<BannerEntity>(getContext(), false) {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass24 */

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onSubscribe(Disposable disposable) {
                    super.onSubscribe(disposable);
                    SLLivePresenter.this.compositeDisposableAdd(disposable);
                }

                public void accept(BannerEntity bannerEntity) {
                    if (bannerEntity != null) {
                        CacheUtils.saveLiveNoticeByCacheDisk(bannerEntity);
                        ((ISLLiveView) SLLivePresenter.this.getView()).onLiveAdNoticeSuccess(bannerEntity);
                    }
                }
            });
        }
    }

    public void getBroadcastClick(String str) {
        this.mApiService.broadcastClickCountUpdate(new RequestParams().getBroadcastClickParams(str)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass27 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass26 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void getPersonalGuardInfo(String str) {
        this.mApiService.getMyGuardInfoService(new RequestParams().getPersonalGuardInfoParams(str)).map(new ServerResultFunction<GuardItemEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass29 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<GuardItemEntity>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass28 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(GuardItemEntity guardItemEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onPersonalGuardInfoSuccess(guardItemEntity);
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onPersonalGuardInfoFail();
            }
        });
    }

    public void getWebSocketAddress(String str, String str2) {
        this.mApiService.getWebSocketAddressService(new RequestParams().getWebSocketAddressParams(str, str2)).map(new ServerResultFunction<WSAddressEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass31 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<WSAddressEntity>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass30 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(WSAddressEntity wSAddressEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onWebSocketAddressSuccess(wSAddressEntity);
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onWebSocketAddressFail();
            }
        });
    }

    public void getLivePopularity(final String str, final String str2) {
        compositeDisposableAdd(Observable.interval(0, 15, TimeUnit.SECONDS).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Long>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass32 */

            public void accept(Long l) throws Exception {
                CacheApiRetrofit.getInstance().getApiService().getLivePopularityService(str2, UserInfoManager.getInstance().getAppId(), str).map(new ServerResultFunction<LiveEntity>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass32.AnonymousClass3 */
                }).onErrorResumeNext(new HttpResultFunction<LiveEntity>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass32.AnonymousClass2 */
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<LiveEntity>(SLLivePresenter.this.getContext(), false) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass32.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(LiveEntity liveEntity) {
                        if (liveEntity != null) {
                            ((ISLLiveView) SLLivePresenter.this.getView()).onLivePopularitySuccess(NumberUtils.string2long(liveEntity.popularity));
                        }
                    }
                });
            }
        }));
    }

    public void getGiftBoxList(long j, final String str) {
        onTimerDelayAction(j, new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass33 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ApiRetrofit.getInstance().getApiService().getGiftBoxListService(new RequestParams().getGiftBoxListParams(str)).map(new ServerResultFunction<List<GiftBoxEntity>>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass33.AnonymousClass2 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<GiftBoxEntity>>(SLLivePresenter.this.getContext(), false) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass33.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onError(Throwable th) {
                    }

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(List<GiftBoxEntity> list) {
                        if (list != null) {
                            ((ISLLiveView) SLLivePresenter.this.getView()).onGiftBoxListSuccess(list);
                        }
                    }
                });
            }
        });
    }

    public void getTaskList(final boolean z) {
        ApiRetrofit.getInstance().getApiService().getTaskBoxList(new RequestParams().getTaskBoxListParams()).map(new ServerResultFunction<List<TaskBoxEntity>>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass35 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<TaskBoxEntity>>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass34 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(List<TaskBoxEntity> list) {
                if (list != null) {
                    ((ISLLiveView) SLLivePresenter.this.getView()).onTaskListSuccess(list, z);
                }
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onTaskListFail();
            }
        });
    }

    public void getTaskTake(final TaskBoxEntity taskBoxEntity) {
        this.mApiService.getTaskBoxTake(new RequestParams().getTaskChangeParams(taskBoxEntity.getId())).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass37 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass36 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
                if (obj != null) {
                    ((ISLLiveView) SLLivePresenter.this.getView()).onTaskTakeSuccess(taskBoxEntity);
                }
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onTaskTakeFail();
            }
        });
    }

    public void changeTaskState(final TaskBoxEntity taskBoxEntity) {
        this.mApiService.changeTaskState(new RequestParams().getTaskChangeParams(taskBoxEntity.getId())).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass39 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass38 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
                if (obj != null) {
                    ((ISLLiveView) SLLivePresenter.this.getView()).onTaskChangeSuccess(taskBoxEntity);
                }
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onTaskChangeFail(taskBoxEntity);
            }
        });
    }

    public void getTrumpetStatus() {
        this.mApiService.getTrumpetStatus(new RequestParams().getDefaultParams()).map(new ServerResultFunction<TrumpetStatusEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass41 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<TrumpetStatusEntity>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass40 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(TrumpetStatusEntity trumpetStatusEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onTrumpetStatusSuccess(trumpetStatusEntity);
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onTrumpetStatusFail();
            }
        });
    }

    public void sendTrumpet(String str) {
        addMapSubscription(this.mApiService.sendTrumpet(new RequestParams().getTrumpetSendParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass42 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onTrumpetSendSuccess();
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onTrumpetSendFail(i);
            }
        }));
    }

    public void updateTrumpetClickCount(String str) {
        this.mApiService.updateClickTrumpetCount(new RequestParams().getTrumpetSendUpdateTrumpetClickCountParams(str)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass44 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass43 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void updateStartLiveNoticeCount(String str) {
        this.mApiService.startLiveNoticeClickCountUpdateService(new RequestParams().getVipSeatListParams(str)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass46 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass45 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void getFragmentConfig(String str) {
        this.mApiService.getPropFragmentConfigService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<PropConfigEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass48 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<PropConfigEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass47 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(PropConfigEntity propConfigEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onFragmentConfigSuccess(propConfigEntity);
            }
        });
    }

    public void getUsePropService(final PropConfigEntity propConfigEntity, String str) {
        this.mApiService.getUsePropService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass50 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass49 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onUseFragmentSuccess(propConfigEntity);
            }
        });
    }

    public void getBoomStatus(String str) {
        this.mApiService.getBoomStatusService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<BoomStatusEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass52 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<BoomStatusEntity>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass51 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(BoomStatusEntity boomStatusEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onBoomStatusSuccess(boomStatusEntity);
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                super.onError(th);
                ((ISLLiveView) SLLivePresenter.this.getView()).onBoomStatusFail();
            }
        });
    }

    public void showUserManageMenu(String str, String str2, ResultCallBack<UserEntity> resultCallBack) {
        addMapSubscription(this.mApiService.getJudgeUserBanPostService(new RequestParams().getJudgeUserBanPostParams(str, str2)), new HttpRxObserver(getContext(), resultCallBack));
    }

    public void watchHistoryReport(String str, long j) {
        this.mApiService.getWatchHistoryService(new RequestParams().getWatchHistoryParams(str, j)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass54 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass53 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void clearCompositeDisposable() {
        CompositeDisposable compositeDisposable2 = this.compositeDisposable;
        if (compositeDisposable2 != null) {
            compositeDisposable2.clear();
        }
    }

    public void compositeDisposableAdd(Disposable disposable) {
        CompositeDisposable compositeDisposable2 = this.compositeDisposable;
        if (compositeDisposable2 != null) {
            compositeDisposable2.add(disposable);
        }
    }

    public void getAnchorFrozenStatus(final ResultCallBack<AnchorEntity> resultCallBack) {
        this.mApiService.getAnchorFrozenStatusService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<AnchorEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass56 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<AnchorEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass55 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(AnchorEntity anchorEntity) {
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(anchorEntity);
                }
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                super.onError(th);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(-1, th.getMessage());
                }
            }
        });
    }

    public void onUserCheckTicket(String str, final boolean z) {
        this.mApiService.getCheckTicketService(new RequestParams().getUserCheckTicketParams(str)).map(new ServerResultFunction<CheckTicketEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass58 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<CheckTicketEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass57 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
                SLLivePresenter.this.showLoadingDialog(z);
            }

            public void accept(CheckTicketEntity checkTicketEntity) {
                SLLivePresenter.this.dismissProgressDialog();
                ((ISLLiveView) SLLivePresenter.this.getView()).onUserCheckTicketSuccess(checkTicketEntity);
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                super.onError(th);
                SLLivePresenter.this.dismissProgressDialog();
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
                ((ISLLiveView) SLLivePresenter.this.getView()).onUserCheckTicketFail(i, str);
            }
        });
    }

    public void initLocalComponentsCache() {
        if (SLLiveSDK.getSingleton().isLiveGameChannel()) {
            ApiRetrofit.getInstance().getApiService().getGameComponentService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<List<ComponentsEntity>>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass60 */
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<ComponentsEntity>>(getContext(), false) {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass59 */

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                public void onSubscribe(Disposable disposable) {
                    super.onSubscribe(disposable);
                    SLLivePresenter.this.compositeDisposableAdd(disposable);
                }

                public void accept(List<ComponentsEntity> list) {
                    CacheUtils.saveLocalComponentsCache(list);
                }
            });
        } else if (SLLiveSDK.getSingleton().sdkCallbackListener != null) {
            loadChannelComponentsData(null);
        }
    }

    private void loadChannelComponentsData(ComponentsEntity componentsEntity) {
        SLLiveSDK.getSingleton().sdkCallbackListener.onAppCommonCallbackListener(getContext(), 274, new SLLiveSDK.OnCommonCallbackListener() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass61 */

            @Override // com.slzhibo.library.SLLiveSDK.OnCommonCallbackListener
            public void onDataFail(Throwable th, int i) {
            }

            @Override // com.slzhibo.library.SLLiveSDK.OnCommonCallbackListener
            public void onDataSuccess(Context context, Object obj) {
                if (obj == null) {
                    SLLivePresenter.this.saveLotteryComponentsCache();
                } else if (obj instanceof String) {
                    try {
                        List<ComponentsEntity> list = (List) GsonUtils.fromJson((String) obj, new TypeToken<List<ComponentsEntity>>() {
                            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass61.AnonymousClass1 */
                        }.getType());
                        if (list != null) {
                            if (!list.isEmpty()) {
                                boolean z = false;
                                for (ComponentsEntity componentsEntity : list) {
                                    if (componentsEntity.isCacheLotteryComponents()) {
                                        z = true;
                                    }
                                }
                                if (!z) {
                                    list.add(0, SLLivePresenter.this.getLocalLotteryComponents());
                                }
                                CacheUtils.saveLocalComponentsCache(list);
                                return;
                            }
                        }
                        SLLivePresenter.this.saveLotteryComponentsCache();
                    } catch (Exception unused) {
                        SLLivePresenter.this.saveLotteryComponentsCache();
                    }
                } else {
                    SLLivePresenter.this.saveLotteryComponentsCache();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ComponentsEntity getLocalLotteryComponents() {
        ComponentsEntity componentsEntity = new ComponentsEntity();
        componentsEntity.callType = 2;
        componentsEntity.gameId = "1";
        componentsEntity.name = getContext().getString(R.string.fq_lottery_menu);
        return componentsEntity;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void saveLotteryComponentsCache() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getLocalLotteryComponents());
        CacheUtils.saveLocalComponentsCache(arrayList);
    }

    public void onGetFP(String str, final boolean z, final boolean z2) {
        ApiRetrofit.getInstance().getApiService().getPKFPService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<PKRecordEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass63 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<PKRecordEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass62 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(PKRecordEntity pKRecordEntity) {
                ((ISLLiveView) SLLivePresenter.this.getView()).onPKLiveRoomFPSuccess(z, z2, pKRecordEntity);
            }
        });
    }

    public void onOpenNobilityMsgNotice(ResultCallBack<Object> resultCallBack) {
        onTimerDelayAction(5, resultCallBack);
    }

    public void onAttentionMsgNotice(ResultCallBack<Object> resultCallBack) {
        onTimerDelayAction(60, resultCallBack);
    }

    public void onTimerDelayAction(long j, final ResultCallBack<Object> resultCallBack) {
        if (j > 0) {
            compositeDisposableAdd(Observable.timer(j, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass64 */

                public void accept(Long l) throws Exception {
//                    ResultCallBack resultCallBack = resultCallBack;
                    if (resultCallBack != null) {
                        resultCallBack.onSuccess(null);
                    }
                }
            }));
        } else if (resultCallBack != null) {
            resultCallBack.onSuccess(null);
        }
    }

    public void onSendOfflinePrivateMsg() {
        ApiRetrofit.getInstance().getApiService().getSendOfflinePrivateMessageService(new RequestParams().getDefaultParams()).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass66 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass65 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void onBuyLiveTicket(String str, final ResultCallBack<MyAccountEntity> resultCallBack) {
        ApiRetrofit.getInstance().getApiService().getBuyLiveTicketService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<MyAccountEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass68 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<MyAccountEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass67 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
                SLLivePresenter.this.showLoadingDialog(true);
            }

            public void accept(MyAccountEntity myAccountEntity) {
                SLLivePresenter.this.dismissProgressDialog();
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(myAccountEntity);
                }
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
                super.onError(th);
                SLLivePresenter.this.dismissProgressDialog();
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(i, str);
                }
            }
        });
    }

    public void sendQMInteractInviteRequest(String str, String str2, String str3, String str4, String str5, final ResultCallBack<Object> resultCallBack) {
        ApiRetrofit.getInstance().getApiService().userCommitTaskListService(new RequestParams().getUserCommitIntimateParams(str, str2, str3, str4, str5)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass70 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass69 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(obj);
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(i, str);
                }
            }
        });
    }

    public void sendUserShowTaskListRequest(long j, final String str) {
        onTimerDelayAction(j, new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass71 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ApiRetrofit.getInstance().getApiService().userShowTaskListService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<QMInteractTaskListEntity>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass71.AnonymousClass2 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<QMInteractTaskListEntity>(SLLivePresenter.this.getContext()) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass71.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(QMInteractTaskListEntity qMInteractTaskListEntity) {
                        List<QMInteractTaskEntity> list;
                        if (qMInteractTaskListEntity != null && (list = qMInteractTaskListEntity.intimateTaskList) != null && !list.isEmpty()) {
                            ((ISLLiveView) SLLivePresenter.this.getView()).onQMInteractShowTaskSuccess(list);
                        }
                    }

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onError(Throwable th) {
                        ((ISLLiveView) SLLivePresenter.this.getView()).onQMInteractShowTaskFail();
                    }
                });
            }
        });
    }

    public void sendUserPendingTaskRequest(String str, final ResultCallBack<QMInteractTaskEntity> resultCallBack) {
        ApiRetrofit.getInstance().getApiService().userPendingTaskService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<QMInteractTaskEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass73 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<QMInteractTaskEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass72 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(QMInteractTaskEntity qMInteractTaskEntity) {
                if (qMInteractTaskEntity == null || TextUtils.isEmpty(qMInteractTaskEntity.taskId)) {
                    onError(-1, "");
                    return;
                }
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(qMInteractTaskEntity);
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(i, str);
                }
            }
        });
    }

    public void sendSeatListRequest(long j, final String str, final String str2, final ResultCallBack<List<YYLinkApplyEntity>> resultCallBack) {
        onTimerDelayAction(j, new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass74 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ApiRetrofit.getInstance().getApiService().voiceRoomSeatListService(new RequestParams().getLiveIdCountParams(str, str2)).map(new ServerResultFunction<List<YYLinkApplyEntity>>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass74.AnonymousClass2 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<List<YYLinkApplyEntity>>(SLLivePresenter.this.getContext()) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass74.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(List<YYLinkApplyEntity> list) {
//                        ResultCallBack resultCallBack;
                        if (list != null && resultCallBack  != null) {
                            resultCallBack.onSuccess(list);
                        }
                    }

                    @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onError(int i, String str) {
                        super.onError(i, str);
//                        ResultCallBack resultCallBack = resultCallBack;
                        if (resultCallBack != null) {
                            resultCallBack.onError(i, str);
                        }
                    }
                });
            }
        });
    }

    public void onSendLinkNoticeRequest(long j, final String str, final ResultCallBack<YYNoticeEntity> resultCallBack) {
        onTimerDelayAction(j, new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass75 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ApiRetrofit.getInstance().getApiService().getUserLinkNoticeService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<YYNoticeEntity>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass75.AnonymousClass2 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<YYNoticeEntity>(SLLivePresenter.this.getContext()) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass75.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(YYNoticeEntity yYNoticeEntity) {
//                        ResultCallBack resultCallBack = resultCallBack;
                        if (resultCallBack != null) {
                            resultCallBack.onSuccess(yYNoticeEntity);
                        }
                    }

                    @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onError(int i, String str) {
                        super.onError(i, str);
//                        ResultCallBack resultCallBack = resultCallBack;
                        if (resultCallBack != null) {
                            resultCallBack.onError(i, str);
                        }
                    }
                });
            }
        });
    }

    public void onSendVoiceRoomLikeAction(String str, String str2, String str3) {
        ApiRetrofit.getInstance().getApiService().voiceRoomLikeActionService(new RequestParams().getYYLinkActionMenuParams(str, str2, str3)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass77 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass76 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void onSendVideoLinkDetailRequest(long j, final String str, final String str2, final ResultCallBack<YYLinkApplyEntity> resultCallBack) {
        onTimerDelayAction(j, new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass78 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ApiRetrofit.getInstance().getApiService().videoRoomLinkDetailService(new RequestParams().getLiveIdCountParams(str, str2)).map(new ServerResultFunction<YYLinkApplyEntity>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass78.AnonymousClass2 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<YYLinkApplyEntity>(SLLivePresenter.this.getContext()) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass78.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(YYLinkApplyEntity yYLinkApplyEntity) {
//                        ResultCallBack resultCallBack = resultCallBack;
                        if (resultCallBack != null) {
                            resultCallBack.onSuccess(yYLinkApplyEntity);
                        }
                    }

                    @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onError(int i, String str) {
                        super.onError(i, str);
//                        ResultCallBack resultCallBack = resultCallBack;
                        if (resultCallBack != null) {
                            resultCallBack.onError(i, str);
                        }
                    }
                });
            }
        });
    }

    public void onSendVideoUserConnectSuccessRequest(String str, String str2, String str3) {
        ApiRetrofit.getInstance().getApiService().videoRoomUserConnectSuccessService(new RequestParams().getVideoLinkUserConnectSuccessParams(str, str2, str3)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass80 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass79 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void onSendUserDisconnectLinkRequest(boolean z, String str, String str2, final ResultCallBack<Object> resultCallBack) {
        Observable<R> observable;
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Map<String, Object> liveIdCountParams = new RequestParams().getLiveIdCountParams(str, str2);
        if (z) {
            observable = apiService.voiceRoomUserDisconnectLinkService(liveIdCountParams).map(new ServerResultFunction<Object>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass81 */
            }).onErrorResumeNext(new HttpResultFunction());
        } else {
            observable = apiService.videoRoomUserDisconnectLinkService(liveIdCountParams).map(new ServerResultFunction<Object>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass82 */
            }).onErrorResumeNext(new HttpResultFunction());
        }
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>( getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass83 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(obj);
                }
            }
        });
    }

    public void onSendVideoRoomUserRTCErrorRequest(String str, String str2) {
        ApiRetrofit.getInstance().getApiService().videoRoomUserRTCErrorService(new RequestParams().getLiveIdCountParams(str, str2)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass85 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass84 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void onSendQuietOptionRequest(boolean z, String str, String str2, int i) {
        Observable<R> observable;
        String userId = UserInfoManager.getInstance().getUserId();
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Map<String, Object> yYLinkMuteSeatParams = new RequestParams().getYYLinkMuteSeatParams(str, str2, userId, i);
        if (z) {
            observable = apiService.voiceRoomMuteActionService(yYLinkMuteSeatParams).map(new ServerResultFunction<Object>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass86 */
            }).onErrorResumeNext(new HttpResultFunction());
        } else {
            observable = apiService.videoRoomAnchorSetQuietService(yYLinkMuteSeatParams).map(new ServerResultFunction<Object>() {
                /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass87 */
            }).onErrorResumeNext(new HttpResultFunction());
        }
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass88 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }
        });
    }

    public void onSendHJShelfDetailRequest(long j, String str, final ResultCallBack<HJProductEntity> resultCallBack) {
        final Map<String, Object> liveId = new RequestParams().getLiveId(str);
        onTimerDelayAction(j, new ResultCallBack<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass89 */

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onError(int i, String str) {
            }

            @Override // com.slzhibo.library.http.ResultCallBack
            public void onSuccess(Object obj) {
                ApiRetrofit.getInstance().getApiService().productDetailWindowService(liveId).map(new ServerResultFunction<HJProductEntity>() {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass89.AnonymousClass2 */
                }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<HJProductEntity>(SLLivePresenter.this.getContext(), false) {
                    /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass89.AnonymousClass1 */

                    @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onSubscribe(Disposable disposable) {
                        super.onSubscribe(disposable);
                        SLLivePresenter.this.compositeDisposableAdd(disposable);
                    }

                    public void accept(HJProductEntity hJProductEntity) {
//                        ResultCallBack resultCallBack = resultCallBack;
                        if (resultCallBack != null) {
                            resultCallBack.onSuccess(hJProductEntity);
                        }
                    }

                    @Override // com.slzhibo.library.utils.live.SimpleRxObserver
                    public void onError(int i, String str) {
                        super.onError(i, str);
//                        ResultCallBack resultCallBack = resultCallBack;
                        if (resultCallBack != null) {
                            resultCallBack.onError(i, str);
                        }
                    }
                });
            }
        });
    }

    public void sendBluetoothConnectionNotice(ResultCallBack<Object> resultCallBack) {
        onTimerDelayAction(5, resultCallBack);
    }

    public void onSendBluetoothSendRequest(String str, String str2, String str3, String str4, String str5, final ResultCallBack<Object> resultCallBack) {
        ApiRetrofit.getInstance().getApiService().bluetoothSendService(new RequestParams().getBluetoothSendParams(str, str2, str3, str4, str5)).map(new ServerResultFunction<LYDevEntity>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass91 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<LYDevEntity>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass90 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            public void accept(LYDevEntity lYDevEntity) {
                if (lYDevEntity != null) {
//                    ResultCallBack resultCallBack = resultCallBack;
                    if (resultCallBack != null) {
                        resultCallBack.onSuccess(lYDevEntity);
                    }
                    ((ISLLiveView) SLLivePresenter.this.getView()).onUserOverSuccess(lYDevEntity.balance);
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(i, str);
                }
                if (AppUtils.isBalanceNotEnoughToRecharge(SLLivePresenter.this.getContext(), i)) {
                }
            }
        });
    }

    public void onSendBluetoothSendRequest(String str, final ResultCallBack<Object> resultCallBack) {
        ApiRetrofit.getInstance().getApiService().bluetoothTeaseService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass93 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext()) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass92 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(obj);
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(i, str);
                }
            }
        });
    }

    public void onSendThermometerBombRequest(String str) {
        ApiRetrofit.getInstance().getApiService().thermometerBombService(new RequestParams().getThermometerBombParams(str)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass95 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass94 */

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
            }

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
            }
        });
    }

    public void sendBluetoothCountdownRequest(String str, final ResultCallBack<Object> resultCallBack) {
        ApiRetrofit.getInstance().getApiService().bluetoothCountdownService(new RequestParams().getLiveId(str)).map(new ServerResultFunction<Object>() {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass97 */
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleRxObserver<Object>(getContext(), false) {
            /* class com.slzhibo.library.ui.presenter.SLLivePresenter.AnonymousClass96 */

            @Override // io.reactivex.Observer, com.slzhibo.library.utils.live.SimpleRxObserver
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                SLLivePresenter.this.compositeDisposableAdd(disposable);
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void accept(Object obj) {
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(obj);
                }
            }

            @Override // com.slzhibo.library.utils.live.SimpleRxObserver
            public void onError(int i, String str) {
                super.onError(i, str);
//                ResultCallBack resultCallBack = resultCallBack;
                if (resultCallBack != null) {
                    resultCallBack.onError(i, str);
                }
            }
        });
    }
}
